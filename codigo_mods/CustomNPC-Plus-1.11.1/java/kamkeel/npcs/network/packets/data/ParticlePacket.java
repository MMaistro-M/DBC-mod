/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.entity.player.EntityPlayer
 */
package kamkeel.npcs.network.packets.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.Random;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;

public final class ParticlePacket
extends AbstractPacket {
    public static final String packetName = "Data|Particle";
    private double x;
    private double y;
    private double z;
    private float height;
    private float width;
    private float yOffset;
    private String particle;

    public ParticlePacket() {
    }

    public ParticlePacket(double x, double y, double z, float height, float width, float yOffset, String particle) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.height = height;
        this.width = width;
        this.yOffset = yOffset;
        this.particle = particle;
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.PARTICLE;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        out.writeDouble(this.x);
        out.writeDouble(this.y);
        out.writeDouble(this.z);
        out.writeFloat(this.height);
        out.writeFloat(this.width);
        out.writeFloat(this.yOffset);
        ByteBufUtils.writeString(out, this.particle);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        ParticlePacket.spawnParticle(in);
    }

    @SideOnly(value=Side.CLIENT)
    public static void spawnParticle(ByteBuf buffer) throws IOException {
        double posX = buffer.readDouble();
        double posY = buffer.readDouble();
        double posZ = buffer.readDouble();
        float height = buffer.readFloat();
        float width = buffer.readFloat();
        float yOffset = buffer.readFloat();
        String particle = ByteBufUtils.readString(buffer);
        if (particle != null) {
            WorldClient worldObj = Minecraft.func_71410_x().field_71441_e;
            Random rand = worldObj.field_73012_v;
            if (particle.equals("heal")) {
                for (int k = 0; k < 6; ++k) {
                    worldObj.func_72869_a("instantSpell", posX + (rand.nextDouble() - 0.5) * (double)width, posY + rand.nextDouble() * (double)height - (double)yOffset, posZ + (rand.nextDouble() - 0.5) * (double)width, 0.0, 0.0, 0.0);
                    worldObj.func_72869_a("spell", posX + (rand.nextDouble() - 0.5) * (double)width, posY + rand.nextDouble() * (double)height - (double)yOffset, posZ + (rand.nextDouble() - 0.5) * (double)width, 0.0, 0.0, 0.0);
                }
            }
        }
    }
}

