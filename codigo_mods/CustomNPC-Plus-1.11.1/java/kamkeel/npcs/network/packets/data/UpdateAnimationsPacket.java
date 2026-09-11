/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.controllers.data.AnimationData;
import noppes.npcs.entity.EntityNPCInterface;

public final class UpdateAnimationsPacket
extends AbstractPacket {
    public static final String packetName = "Data|UpdateAnimations";
    private NBTTagCompound animationCompound;
    private String playername;

    public UpdateAnimationsPacket() {
    }

    public UpdateAnimationsPacket(NBTTagCompound animationCompound, String playername) {
        this.animationCompound = animationCompound;
        this.playername = playername;
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.UPDATE_ANIMATIONS;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        ByteBufUtils.writeNBT(out, this.animationCompound);
        ByteBufUtils.writeString(out, this.playername);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        NBTTagCompound nbt = ByteBufUtils.readNBT(in);
        AnimationData animationData = null;
        if (nbt.func_74764_b("EntityId")) {
            Entity entity = Minecraft.func_71410_x().field_71441_e.func_73045_a(nbt.func_74762_e("EntityId"));
            if (entity instanceof EntityNPCInterface) {
                animationData = ((EntityNPCInterface)entity).display.animationData;
            }
        } else {
            String playerName = ByteBufUtils.readString(in);
            EntityPlayer sendingPlayer = Minecraft.func_71410_x().field_71441_e.func_72924_a(playerName);
            if (sendingPlayer != null) {
                if (!ClientCacheHandler.playerAnimations.containsKey(sendingPlayer.func_110124_au())) {
                    ClientCacheHandler.playerAnimations.put(sendingPlayer.func_110124_au(), new AnimationData(sendingPlayer));
                }
                animationData = ClientCacheHandler.playerAnimations.get(sendingPlayer.func_110124_au());
                animationData.parent = sendingPlayer;
            }
        }
        if (animationData != null) {
            if (nbt.func_74764_b("Animation")) {
                Animation animation = new Animation();
                animation.readFromNBT(nbt.func_74775_l("Animation"));
                animationData.setAnimation(animation);
            } else {
                animationData.setAnimation(null);
            }
            animationData.readFromNBT(nbt);
            if (nbt.func_74764_b("Frame")) {
                animationData.animation.jumpToFrameAtTime(nbt.func_74762_e("Frame"), nbt.func_74762_e("Time"));
            }
        }
    }
}

