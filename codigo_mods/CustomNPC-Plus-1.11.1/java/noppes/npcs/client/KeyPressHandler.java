/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.InputEvent$KeyInputEvent
 *  cpw.mods.fml.common.gameevent.InputEvent$MouseInputEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.player.EntityPlayer
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 */
package noppes.npcs.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import kamkeel.npcs.network.packets.player.InputDevicePacket;
import kamkeel.npcs.network.packets.player.SpecialKeyStatePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.CustomNpcs;
import noppes.npcs.client.ClientAbilityState;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.gui.hud.ClientHudManager;
import noppes.npcs.client.gui.hud.EnumHudComponent;
import noppes.npcs.client.gui.hud.ability.AbilityHotbarComponent;
import noppes.npcs.client.gui.player.inventory.GuiCNPCInventory;
import noppes.npcs.controllers.data.PlayerData;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import tconstruct.client.tabs.InventoryTabCustomNpc;

public class KeyPressHandler {
    private static final boolean[] mouseButtonsDown = new boolean[16];
    private static boolean specialKeyHeld = false;
    private static boolean hudKeyHeld = false;

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.func_71410_x();
        KeyPressHandler.updateHeldStates(mc);
        KeyPressHandler.handleNPCButton(mc);
        KeyPressHandler.handleAbilityCycleKeys(mc);
        if (!Keyboard.isRepeatEvent()) {
            int key = Keyboard.getEventKey();
            boolean keyDown = Keyboard.isKeyDown((int)key);
            if (mc.field_71441_e != null && mc.field_71462_r == null && keyDown && ClientAbilityState.shouldSuppressMovementInput() && KeyPressHandler.isMovementKey(key)) {
                KeyBinding.func_74510_a((int)key, (boolean)false);
                return;
            }
            InputDevicePacket.sendKeyboard(key, keyDown);
        }
    }

    @SubscribeEvent
    public void onMousePress(InputEvent.MouseInputEvent event) {
        Minecraft mc = Minecraft.func_71410_x();
        KeyPressHandler.updateHeldStates(mc);
        KeyPressHandler.handleNPCButton(mc);
        KeyPressHandler.handleAbilityCycleKeys(mc);
        int button = Mouse.getEventButton();
        if (button == -1 && Mouse.getEventDWheel() == 0) {
            return;
        }
        InputDevicePacket.sendMouse(button, Mouse.getEventDWheel(), button >= 0 && Mouse.getEventButtonState());
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }
        for (int i = 0; i < mouseButtonsDown.length; ++i) {
            if (!mouseButtonsDown[i] || i >= Mouse.getButtonCount() || Mouse.isButtonDown((int)i)) continue;
            KeyPressHandler.mouseButtonsDown[i] = false;
        }
        KeyPressHandler.updateHeldStates(Minecraft.func_71410_x());
    }

    public static void trackMouseButton(int button, boolean down) {
        if (button >= 0 && button < mouseButtonsDown.length) {
            KeyPressHandler.mouseButtonsDown[button] = down;
        }
        KeyPressHandler.updateHeldStates(Minecraft.func_71410_x());
    }

    private static void updateHeldStates(Minecraft mc) {
        boolean specialDown;
        boolean noScreen = mc.field_71462_r == null;
        boolean bl = specialDown = noScreen && KeyPressHandler.isKeyBindDown(ClientProxy.SpecialKey);
        if (specialDown != specialKeyHeld) {
            specialKeyHeld = specialDown;
            if (mc.field_71439_g != null) {
                PlayerData data = CustomNpcs.proxy.getPlayerData((EntityPlayer)mc.field_71439_g);
                if (data != null) {
                    data.setSpecialKeyDown(specialDown);
                }
                SpecialKeyStatePacket.send(specialDown);
            }
        }
        hudKeyHeld = noScreen && KeyPressHandler.isKeyBindDown(ClientProxy.AbilityHudKey);
    }

    private static void handleNPCButton(Minecraft mc) {
        if (ClientProxy.NPCButton == null) {
            return;
        }
        if (ClientProxy.NPCButton.func_151468_f()) {
            if (mc.field_71462_r == null) {
                InventoryTabCustomNpc.tabHelper();
            } else if (mc.field_71462_r instanceof GuiCNPCInventory) {
                mc.func_71381_h();
            }
        }
    }

    private static void handleAbilityCycleKeys(Minecraft mc) {
        if (mc.field_71462_r != null || mc.field_71441_e == null) {
            return;
        }
        AbilityHotbarComponent comp = (AbilityHotbarComponent)ClientHudManager.getInstance().getHudComponents().get((Object)EnumHudComponent.AbilityHotbar);
        if (comp == null || !comp.hasAnyAbilities()) {
            return;
        }
        if (ClientProxy.AbilityNextKey != null && ClientProxy.AbilityNextKey.func_151468_f()) {
            comp.onCycleNext();
        }
        if (ClientProxy.AbilityPrevKey != null && ClientProxy.AbilityPrevKey.func_151468_f()) {
            comp.onCyclePrev();
        }
    }

    public static boolean isSpecialKeyHeld() {
        return specialKeyHeld;
    }

    public static boolean isHudKeyHeld() {
        return hudKeyHeld;
    }

    public static boolean isKeyBindDown(KeyBinding keyBinding) {
        if (keyBinding == null) {
            return false;
        }
        int keyCode = keyBinding.func_151463_i();
        if (keyCode == 0) {
            return false;
        }
        if (keyCode < 0) {
            int mouseButton = keyCode + 100;
            return mouseButton >= 0 && mouseButton < mouseButtonsDown.length && mouseButtonsDown[mouseButton];
        }
        return keyCode < Keyboard.getKeyCount() && Keyboard.isKeyDown((int)keyCode);
    }

    private static boolean isMovementKey(int keyCode) {
        Minecraft mc = Minecraft.func_71410_x();
        return keyCode == mc.field_71474_y.field_74351_w.func_151463_i() || keyCode == mc.field_71474_y.field_74368_y.func_151463_i() || keyCode == mc.field_71474_y.field_74370_x.func_151463_i() || keyCode == mc.field_71474_y.field_74366_z.func_151463_i() || keyCode == mc.field_71474_y.field_74314_A.func_151463_i() || keyCode == mc.field_71474_y.field_74311_E.func_151463_i() || keyCode == mc.field_71474_y.field_151444_V.func_151463_i();
    }
}

