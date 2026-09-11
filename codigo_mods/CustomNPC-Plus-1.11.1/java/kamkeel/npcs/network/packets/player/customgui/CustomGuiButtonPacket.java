/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.player.customgui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumPlayerPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.EventHooks;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.containers.ContainerCustomGui;
import noppes.npcs.scripted.NpcAPI;

public final class CustomGuiButtonPacket
extends AbstractPacket {
    public static final String packetName = "Request|CustomGuiButton";
    private NBTTagCompound compound;
    private int id;

    public CustomGuiButtonPacket() {
    }

    public CustomGuiButtonPacket(int id, NBTTagCompound comp) {
        this.compound = comp;
        this.id = id;
    }

    @Override
    public Enum getType() {
        return EnumPlayerPacket.CustomGuiButton;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.id);
        ByteBufUtils.writeNBT(out, this.compound);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player.field_71070_bA instanceof ContainerCustomGui)) {
            return;
        }
        int id = in.readInt();
        NBTTagCompound comp = ByteBufUtils.readNBT(in);
        ((ContainerCustomGui)player.field_71070_bA).customGui.fromNBT(comp);
        EventHooks.onCustomGuiButton((IPlayer)NpcAPI.Instance().getIEntity((Entity)player), ((ContainerCustomGui)player.field_71070_bA).customGui, id);
    }
}

