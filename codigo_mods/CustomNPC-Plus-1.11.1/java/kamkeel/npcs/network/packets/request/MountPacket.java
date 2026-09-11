/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package kamkeel.npcs.network.packets.request;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.PacketUtil;
import kamkeel.npcs.network.enums.EnumItemPacketType;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.ServerEventsHandler;

public final class MountPacket
extends AbstractPacket {
    public static final String packetName = "Request|MountPacket";
    private Action action;
    private NBTTagCompound compound;

    public MountPacket() {
    }

    public MountPacket(Action action, NBTTagCompound nbtTagCompound) {
        this.action = action;
        this.compound = nbtTagCompound;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.MountPacket;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.TOOL_MOUNTER;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.action.ordinal());
        if (this.action == Action.Spawn) {
            ByteBufUtils.writeNBT(out, this.compound);
        }
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, EnumItemPacketType.MOUNTER, player)) {
            return;
        }
        Action requestedAction = Action.values()[in.readInt()];
        switch (requestedAction) {
            case Player: {
                player.func_70078_a(ServerEventsHandler.mounted);
                break;
            }
            case Spawn: {
                Entity entity = EntityList.func_75615_a((NBTTagCompound)ByteBufUtils.readNBT(in), (World)player.field_70170_p);
                player.field_70170_p.func_72838_d(entity);
                entity.func_70078_a(ServerEventsHandler.mounted);
            }
        }
    }

    public static void Player() {
        PacketClient.sendClient(new MountPacket(Action.Player, new NBTTagCompound()));
    }

    public static void Spawn(NBTTagCompound compound) {
        PacketClient.sendClient(new MountPacket(Action.Spawn, compound));
    }

    private static enum Action {
        Player,
        Spawn;

    }
}

