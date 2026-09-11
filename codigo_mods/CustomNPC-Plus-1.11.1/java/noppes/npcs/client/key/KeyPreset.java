/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 */
package noppes.npcs.client.key;

import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.key.KeyEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class KeyPreset {
    public KeyState defaultState = new KeyState();
    public KeyState currentState = new KeyState();
    public String name;
    public String description;
    public boolean shouldConflict = true;
    public Consumer<KeyEvent> task;
    private long lastActionTime = 0L;
    private long throttleInterval = 25L;
    private long firstThrottleInterval = 500L;
    private boolean isFirstAction = true;
    private long pressStartMs = 0L;
    public boolean isDown;
    private static final long SHORT_PRESS_MS = 250L;
    public static final int LEFT_MOUSE = -100;
    public static final int RIGHT_MOUSE = -99;
    public static final int MIDDLE_MOUSE = -98;
    public static final int MOUSE_4 = -97;
    public static final int MOUSE_5 = -96;

    public KeyPreset(String name) {
        this.name = name;
    }

    public KeyPreset setDefaultState(int keyCode, boolean hasCtrl, boolean hasAlt, boolean hasShift) {
        this.defaultState.setState(keyCode, hasCtrl, hasAlt, hasShift);
        this.currentState.setState(keyCode, hasCtrl, hasAlt, hasShift);
        return this;
    }

    public KeyPreset setDescription(String description) {
        this.description = description;
        return this;
    }

    public KeyPreset setTask(Consumer<KeyEvent> task) {
        this.task = task;
        return this;
    }

    public KeyPreset shouldConflict(boolean shouldConflict) {
        this.shouldConflict = shouldConflict;
        return this;
    }

    public KeyPreset setThrottleInterval(long ms) {
        this.throttleInterval = ms;
        return this;
    }

    public KeyPreset setFirstThrottleInterval(long ms) {
        this.firstThrottleInterval = ms;
        return this;
    }

    public boolean shouldThrottle(PressType pressType) {
        long intervalToUse;
        if (this.throttleInterval <= 0L) {
            return false;
        }
        long currentTime = System.currentTimeMillis();
        long l = intervalToUse = pressType == PressType.HOLD && this.isFirstAction && this.firstThrottleInterval > 0L ? this.firstThrottleInterval : this.throttleInterval;
        if (currentTime - this.lastActionTime < intervalToUse) {
            return true;
        }
        this.lastActionTime = currentTime;
        this.isFirstAction = false;
        return false;
    }

    public void tick() {
        int keyCode = this.keyCode();
        if (keyCode == -1 || keyCode == 0) {
            return;
        }
        this.setDown(this.currentState.isDown());
    }

    public void setDown(boolean down) {
        if (down && !this.isDown) {
            this.pressStartMs = System.currentTimeMillis();
            this.onAction(PressType.PRESS, PressType.PRESS_RELEASE);
            this.isFirstAction = true;
        }
        if (!down && this.isDown) {
            if (this.getPressTime() <= 250L) {
                this.onAction(PressType.SINGLE_PRESS);
            }
            this.onAction(PressType.RELEASE, PressType.PRESS_RELEASE);
            this.pressStartMs = 0L;
        }
        if (down) {
            this.onAction(PressType.HOLD);
        }
        this.isDown = down;
    }

    public long getPressTime() {
        return this.pressStartMs > 0L ? System.currentTimeMillis() - this.pressStartMs : 0L;
    }

    public KeyPreset onAction(PressType ... pressTypes) {
        if (this.task == null) {
            return this;
        }
        for (PressType pressType : pressTypes) {
            if (this.shouldThrottle(pressType)) continue;
            this.task.accept(new KeyEvent(pressType, this));
        }
        return this;
    }

    public boolean isDefault() {
        return this.currentState.equals(this.defaultState);
    }

    public boolean isMouseKey() {
        return this.keyCode() < -1;
    }

    public int keyCode() {
        return this.currentState.keyCode;
    }

    public boolean hasCtrl() {
        return this.currentState.hasCtrl;
    }

    public boolean hasAlt() {
        return this.currentState.hasAlt;
    }

    public boolean hasShift() {
        return this.currentState.hasShift;
    }

    public String getKeyName() {
        return this.currentState.getName();
    }

    public void clear() {
        this.currentState.clear();
    }

    public void writeToNbt(NBTTagCompound c) {
        c.func_74782_a(this.name, (NBTBase)this.currentState.writeToNbt());
    }

    public void readFromNbt(NBTTagCompound compound) {
        this.currentState.readFromNbt(compound.func_74775_l(this.name));
    }

    public boolean equals(Object preset) {
        if (preset == this) {
            return true;
        }
        if (preset instanceof KeyPreset) {
            KeyPreset key = (KeyPreset)preset;
            return key.currentState.equals(this.currentState);
        }
        return false;
    }

    public static boolean isCtrlKeyDown() {
        return Minecraft.field_142025_a ? Keyboard.isKeyDown((int)219) || Keyboard.isKeyDown((int)220) : Keyboard.isKeyDown((int)29) || Keyboard.isKeyDown((int)157);
    }

    public static boolean isAltKeyDown() {
        return Keyboard.isKeyDown((int)56) || Keyboard.isKeyDown((int)184);
    }

    public static boolean isShiftKeyDown() {
        return Keyboard.isKeyDown((int)42) || Keyboard.isKeyDown((int)54);
    }

    public static boolean isNotCtrlAltShift(int key) {
        return key != 219 && key != 220 && key != 29 && key != 157 && key != 42 && key != 54 && key != 56 && key != 184;
    }

    public static class KeyState {
        public int keyCode = -1;
        public boolean hasCtrl;
        public boolean hasAlt;
        public boolean hasShift;

        public void setState(int keyCode, boolean hasCtrl, boolean hasAlt, boolean hasShift) {
            this.keyCode = keyCode;
            this.hasCtrl = hasCtrl;
            this.hasAlt = hasAlt;
            this.hasShift = hasShift;
        }

        public void readFrom(KeyState state) {
            this.keyCode = state.keyCode;
            this.hasCtrl = state.hasCtrl;
            this.hasShift = state.hasShift;
            this.hasAlt = state.hasAlt;
        }

        public void writeTo(KeyState state) {
            state.setState(this.keyCode, this.hasCtrl, this.hasAlt, this.hasShift);
        }

        public boolean isDown() {
            boolean isDown = this.isMouseKey() ? Mouse.isButtonDown((int)(this.keyCode + 100)) : Keyboard.isKeyDown((int)this.keyCode);
            return isDown && KeyPreset.isCtrlKeyDown() == this.hasCtrl && KeyPreset.isAltKeyDown() == this.hasAlt && KeyPreset.isShiftKeyDown() == this.hasShift;
        }

        public boolean hasState() {
            return this.keyCode != -1;
        }

        public void clear() {
            this.keyCode = -1;
            this.hasShift = false;
            this.hasAlt = false;
            this.hasCtrl = false;
        }

        public boolean equals(Object preset) {
            if (preset == this) {
                return true;
            }
            if (preset instanceof KeyState) {
                KeyState state = (KeyState)preset;
                return state.keyCode == this.keyCode && state.hasCtrl == this.hasCtrl && state.hasAlt == this.hasAlt && state.hasShift == this.hasShift;
            }
            return false;
        }

        public NBTTagCompound writeToNbt() {
            NBTTagCompound compound = new NBTTagCompound();
            compound.func_74768_a("keyCode", this.keyCode);
            compound.func_74757_a("hasCtrl", this.hasCtrl);
            compound.func_74757_a("hasShift", this.hasShift);
            compound.func_74757_a("hasAlt", this.hasAlt);
            return compound;
        }

        public void readFromNbt(NBTTagCompound compound) {
            this.keyCode = compound.func_74762_e("keyCode");
            this.hasCtrl = compound.func_74767_n("hasCtrl");
            this.hasShift = compound.func_74767_n("hasShift");
            this.hasAlt = compound.func_74767_n("hasAlt");
        }

        public boolean matches(int keycode, boolean checkModifiers) {
            if (!checkModifiers) {
                return this.keyCode == keycode;
            }
            return this.keyCode == keycode && this.hasCtrl == KeyPreset.isCtrlKeyDown() && this.hasAlt == KeyPreset.isAltKeyDown() && this.hasShift == KeyPreset.isShiftKeyDown();
        }

        public boolean isMouseKey() {
            return this.keyCode < -1;
        }

        public String getName() {
            int code = this.keyCode;
            String name = "";
            if (code == -100) {
                name = "Left Mouse";
            } else if (code == -99) {
                name = "Right Mouse";
            } else if (code == -98) {
                name = "Middle Mouse";
            } else {
                String string = name = code == -1 ? "" : GameSettings.func_74298_c((int)code);
                if (name.contains("Button")) {
                    name = name.replace("Button", "Mouse");
                }
            }
            return (this.hasCtrl ? "CTRL " : "") + (this.hasAlt ? "ALT " : "") + (this.hasShift ? "SHIFT " : "") + name;
        }
    }

    public static enum PressType {
        PRESS,
        HOLD,
        RELEASE,
        SINGLE_PRESS,
        PRESS_RELEASE;

    }
}

