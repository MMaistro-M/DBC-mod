/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package kamkeel.npcs.network.packets.data.script;

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
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import noppes.npcs.CustomNpcs;
import noppes.npcs.client.fx.CustomFX;
import noppes.npcs.scripted.ScriptParticle;

public final class ScriptedParticlePacket
extends AbstractPacket {
    public static final String packetName = "Data|ScriptedParticle";
    private NBTTagCompound compound;

    public ScriptedParticlePacket() {
    }

    public ScriptedParticlePacket(NBTTagCompound compound) {
        this.compound = compound;
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.SCRIPTED_PARTICLE;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        ByteBufUtils.writeNBT(out, this.compound);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (CustomNpcs.side() != Side.CLIENT) {
            return;
        }
        ScriptedParticlePacket.spawnScriptedParticle(player, in);
    }

    @SideOnly(value=Side.CLIENT)
    public static void spawnScriptedParticle(EntityPlayer player, ByteBuf buffer) {
        ScriptParticle particle;
        NBTTagCompound compound;
        Minecraft minecraft = Minecraft.func_71410_x();
        try {
            compound = ByteBufUtils.readNBT(buffer);
            particle = ScriptParticle.fromNBT(compound);
        }
        catch (IOException ignored) {
            return;
        }
        World worldObj = player.field_70170_p;
        if (worldObj == null) {
            return;
        }
        Entity entity = null;
        if (compound.func_74764_b("EntityID")) {
            entity = worldObj.func_73045_a(compound.func_74762_e("EntityID"));
            if (entity != null) {
                worldObj = entity.field_70170_p;
            } else {
                return;
            }
        }
        CustomFX fx = CustomFX.fromScriptedParticle(particle, worldObj, entity);
        for (int i = 0; i < particle.amount; ++i) {
            minecraft.field_71452_i.func_78873_a((EntityFX)fx);
        }
    }
}

