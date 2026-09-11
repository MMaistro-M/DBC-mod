/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.request.jobs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.PacketUtil;
import kamkeel.npcs.network.enums.EnumItemPacketType;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.constants.EnumJobType;
import noppes.npcs.controllers.ServerCloneController;
import noppes.npcs.roles.JobSpawner;

public final class JobSpawnerAddPacket
extends AbstractPacket {
    public static String packetName = "Request|JobSpawnerAdd";
    private boolean isServer;
    private String selected;
    private int activeTab;
    private int slot;
    private NBTTagCompound compound;

    public JobSpawnerAddPacket() {
    }

    public JobSpawnerAddPacket(boolean isServer, String selected, int activeTab, int slot) {
        this.isServer = isServer;
        this.selected = selected;
        this.activeTab = activeTab;
        this.slot = slot;
    }

    public JobSpawnerAddPacket(boolean isServer, int slot, NBTTagCompound compound) {
        this.isServer = isServer;
        this.slot = slot;
        this.compound = compound;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.JobSpawnerAdd;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.NPC_ADVANCED_JOB;
    }

    @Override
    public boolean needsNPC() {
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeBoolean(this.isServer);
        if (this.isServer) {
            ByteBufUtils.writeString(out, this.selected);
            out.writeInt(this.activeTab);
            out.writeInt(this.slot);
        } else {
            out.writeInt(this.slot);
            ByteBufUtils.writeNBT(out, this.compound);
        }
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, EnumItemPacketType.WAND, player)) {
            return;
        }
        if (this.npc.advanced.job != EnumJobType.Spawner) {
            return;
        }
        JobSpawner job = (JobSpawner)this.npc.jobInterface;
        boolean useServerClone = in.readBoolean();
        if (useServerClone) {
            NBTTagCompound compound = ServerCloneController.Instance.getCloneData(null, ByteBufUtils.readString(in), in.readInt());
            job.setJobCompound(in.readInt(), compound);
        } else {
            job.setJobCompound(in.readInt(), ByteBufUtils.readNBT(in));
        }
        GuiDataPacket.sendGuiData((EntityPlayerMP)player, job.getTitles());
    }
}

