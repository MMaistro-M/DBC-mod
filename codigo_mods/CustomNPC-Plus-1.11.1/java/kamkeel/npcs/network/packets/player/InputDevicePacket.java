/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  org.lwjgl.input.Keyboard
 */
package kamkeel.npcs.network.packets.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumPlayerPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.EventHooks;
import org.lwjgl.input.Keyboard;

public class InputDevicePacket
extends AbstractPacket {
    public static final String packetName = "Player|InputDevice";
    private Type type;
    private int key;
    private int scroll;
    private boolean isKeyPressed;
    private boolean ctrlPressed;
    private boolean shiftPressed;
    private boolean altPressed;
    private boolean metaPressed;
    private String keysDownList;

    public InputDevicePacket() {
    }

    private InputDevicePacket(Type type, int key, boolean isDown) {
        this.type = type;
        this.key = key;
        this.isKeyPressed = isDown;
    }

    public static void sendMouse(int mouseButton, int scroll, boolean isDown) {
        InputDevicePacket packet = new InputDevicePacket(Type.MOUSE, mouseButton, isDown);
        packet.scroll = scroll;
        packet.commonDataGrabber();
        PacketClient.sendClient(packet);
    }

    public static void sendKeyboard(int key, boolean isDown) {
        InputDevicePacket packet = new InputDevicePacket(Type.KEYBOARD, key, isDown);
        packet.commonDataGrabber();
        PacketClient.sendClient(packet);
    }

    private void commonDataGrabber() {
        this.ctrlPressed = Keyboard.isKeyDown((int)157) || Keyboard.isKeyDown((int)29);
        this.shiftPressed = Keyboard.isKeyDown((int)54) || Keyboard.isKeyDown((int)42);
        this.altPressed = Keyboard.isKeyDown((int)184) || Keyboard.isKeyDown((int)56);
        this.metaPressed = Keyboard.isKeyDown((int)220) || Keyboard.isKeyDown((int)219);
        StringBuilder keysDownString = new StringBuilder();
        for (int i = 0; i < Keyboard.getKeyCount(); ++i) {
            if (!Keyboard.isKeyDown((int)i)) continue;
            keysDownString.append((Object)i).append(",");
        }
        if (keysDownString.length() > 0) {
            keysDownString.deleteCharAt(keysDownString.length() - 1);
        }
        this.keysDownList = keysDownString.toString();
    }

    @Override
    public Enum getType() {
        return EnumPlayerPacket.InputDevice;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.type.ordinal());
        out.writeInt(this.key);
        out.writeBoolean(this.isKeyPressed);
        if (this.type == Type.MOUSE) {
            out.writeInt(this.scroll);
        }
        out.writeBoolean(this.ctrlPressed);
        out.writeBoolean(this.shiftPressed);
        out.writeBoolean(this.altPressed);
        out.writeBoolean(this.metaPressed);
        ByteBufUtils.writeString(out, this.keysDownList);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        EntityPlayerMP playerMP = (EntityPlayerMP)player;
        Type incomingType = Type.values()[in.readInt()];
        boolean isMouse = incomingType == Type.MOUSE;
        int inputKey = in.readInt();
        boolean isKeyDown = in.readBoolean();
        int scroll = isMouse ? in.readInt() : 0;
        boolean ctrl = in.readBoolean();
        boolean shift = in.readBoolean();
        boolean alt = in.readBoolean();
        boolean meta = in.readBoolean();
        int[] pressedKeys = InputDevicePacket.getPressedArray(ByteBufUtils.readString(in));
        if (isMouse) {
            EventHooks.onPlayerMouseClicked(playerMP, inputKey, scroll, isKeyDown, ctrl, shift, alt, meta, pressedKeys);
        } else {
            EventHooks.onPlayerKeyPressed(playerMP, inputKey, ctrl, shift, alt, meta, isKeyDown, pressedKeys);
        }
    }

    private static int[] getPressedArray(String pressedKeys) {
        int[] keysDown;
        if (pressedKeys == null) {
            return new int[0];
        }
        String[] split = pressedKeys.split(",");
        if (pressedKeys.length() > 0) {
            keysDown = new int[split.length];
            try {
                for (int i = 0; i < split.length; ++i) {
                    keysDown[i] = Integer.parseInt(split[i]);
                }
            }
            catch (NumberFormatException numberFormatException) {}
        } else {
            keysDown = new int[]{};
        }
        return keysDown;
    }

    private static enum Type {
        MOUSE,
        KEYBOARD;

    }
}

