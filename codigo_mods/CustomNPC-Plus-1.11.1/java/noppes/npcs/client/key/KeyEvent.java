/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.key;

import noppes.npcs.client.key.KeyPreset;

public class KeyEvent {
    public final KeyPreset.PressType type;
    public final KeyPreset preset;
    public final int keyCode;
    public final boolean isMouse;
    public final long timestamp;

    public KeyEvent(KeyPreset.PressType type, KeyPreset preset) {
        this.type = type;
        this.preset = preset;
        this.keyCode = preset.keyCode();
        this.isMouse = preset.isMouseKey();
        this.timestamp = System.currentTimeMillis();
    }

    public boolean is(KeyPreset.PressType t) {
        return this.type == t;
    }

    public boolean isPress() {
        return this.is(KeyPreset.PressType.PRESS);
    }

    public boolean isHold() {
        return this.is(KeyPreset.PressType.HOLD);
    }

    public boolean isRelease() {
        return this.is(KeyPreset.PressType.RELEASE);
    }

    public boolean isSinglePress() {
        return this.is(KeyPreset.PressType.SINGLE_PRESS);
    }

    public boolean isPressRelease() {
        return this.is(KeyPreset.PressType.PRESS_RELEASE);
    }
}

