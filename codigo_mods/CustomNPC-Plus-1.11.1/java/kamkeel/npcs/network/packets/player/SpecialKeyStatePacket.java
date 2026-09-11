/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.PlayerData;

public final class SpecialKeyStatePacket
extends AbstractPacket {
    private boolean pressed;

    public SpecialKeyStatePacket() {
    }

    private SpecialKeyStatePacket(boolean pressed) {
        this.pressed = pressed;
    }

    public static void send(boolean pressed) {
        PacketClient.sendClient(new SpecialKeyStatePacket(pressed));
    }

    @Override
    public Enum getType() {
        return EnumPlayerPacket.SpecialKeyState;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeBoolean(this.pressed);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        boolean keyDown = in.readBoolean();
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        PlayerData data = PlayerDataController.Instance.getPlayerData((EntityPlayer)((EntityPlayerMP)player));
        if (data != null) {
            boolean wasDown = data.isSpecialKeyDown();
            data.setSpecialKeyDown(keyDown);
            if (keyDown && !wasDown) {
                if (player.field_70154_o != null) {
                    return;
                }
                if (data.abilityData.isExecutingAbility() || data.abilityData.isExecutingChain()) {
                    data.abilityData.tryCancelAbility();
                } else {
                    data.abilityData.activateAbility(player);
                }
            }
        }
    }
}

