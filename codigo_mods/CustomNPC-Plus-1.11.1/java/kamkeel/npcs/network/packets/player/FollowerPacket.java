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
package kamkeel.npcs.network.packets.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumPlayerPacket;
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.constants.EnumRoleType;

public class FollowerPacket
extends AbstractPacket {
    public static final String packetName = "Player|Follower";
    private Action type;

    public FollowerPacket() {
    }

    public FollowerPacket(Action action) {
        this.type = action;
    }

    @Override
    public boolean needsNPC() {
        return true;
    }

    @Override
    public Enum getType() {
        return EnumPlayerPacket.FollowerAction;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.type.ordinal());
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        Action requestedAction = Action.values()[in.readInt()];
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        EntityPlayerMP playerMP = (EntityPlayerMP)player;
        if (this.npc.advanced.role != EnumRoleType.Follower) {
            return;
        }
        switch (requestedAction) {
            case Hire: {
                NoppesUtilPlayer.hireFollower(playerMP, this.npc);
                break;
            }
            case Extend: {
                NoppesUtilPlayer.extendFollower(playerMP, this.npc);
                GuiDataPacket.sendGuiData(playerMP, this.npc.roleInterface.writeToNBT(new NBTTagCompound()));
                break;
            }
            case State: {
                NoppesUtilPlayer.changeFollowerState(playerMP, this.npc);
                GuiDataPacket.sendGuiData(playerMP, this.npc.roleInterface.writeToNBT(new NBTTagCompound()));
            }
        }
    }

    public static enum Action {
        Hire,
        Extend,
        State;

    }
}

