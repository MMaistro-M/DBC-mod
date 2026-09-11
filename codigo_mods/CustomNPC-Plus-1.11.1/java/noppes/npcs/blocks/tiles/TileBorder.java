/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderPearl
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 */
package noppes.npcs.blocks.tiles;

import java.util.List;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import noppes.npcs.controllers.data.Availability;

public class TileBorder
extends TileEntity
implements IEntitySelector {
    public Availability availability = new Availability();
    public AxisAlignedBB boundingbox;
    public int rotation = 0;
    public int height = 10;
    public String message = "availability.areaNotAvailble";

    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.readExtraNBT(compound);
    }

    public void readExtraNBT(NBTTagCompound compound) {
        this.availability.readFromNBT(compound.func_74775_l("BorderAvailability"));
        this.rotation = compound.func_74762_e("BorderRotation");
        this.height = compound.func_74762_e("BorderHeight");
        this.message = compound.func_74779_i("BorderMessage");
    }

    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        this.writeExtraNBT(compound);
    }

    public void writeExtraNBT(NBTTagCompound compound) {
        compound.func_74782_a("BorderAvailability", (NBTBase)this.availability.writeToNBT(new NBTTagCompound()));
        compound.func_74768_a("BorderRotation", this.rotation);
        compound.func_74768_a("BorderHeight", this.height);
        compound.func_74778_a("BorderMessage", this.message);
    }

    public void func_145845_h() {
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        AxisAlignedBB box = AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + this.height + 1), (double)(this.field_145849_e + 1));
        List list = this.field_145850_b.func_82733_a(Entity.class, box, (IEntitySelector)this);
        for (Entity entity : list) {
            if (entity instanceof EntityEnderPearl) {
                EntityEnderPearl pearl = (EntityEnderPearl)entity;
                if (!(pearl.func_85052_h() instanceof EntityPlayer) || this.availability.isAvailable((EntityPlayer)pearl.func_85052_h())) continue;
                entity.field_70128_L = true;
                continue;
            }
            EntityPlayer player = (EntityPlayer)entity;
            if (this.availability.isAvailable(player)) continue;
            int posX = this.field_145851_c;
            int posZ = this.field_145849_e;
            int posY = this.field_145848_d;
            if (this.rotation == 0) {
                --posZ;
            } else if (this.rotation == 2) {
                ++posZ;
            } else if (this.rotation == 1) {
                ++posX;
            } else if (this.rotation == 3) {
                --posX;
            }
            while (!this.field_145850_b.func_147437_c(posX, posY, posZ)) {
                ++posY;
            }
            player.func_70634_a((double)posX + 0.5, (double)posY, (double)posZ + 0.5);
            if (this.message.isEmpty()) continue;
            player.func_146105_b((IChatComponent)new ChatComponentTranslation(this.message, new Object[0]));
        }
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound compound = pkt.func_148857_g();
        this.rotation = compound.func_74762_e("Rotation");
    }

    public Packet func_145844_m() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74768_a("Rotation", this.rotation);
        S35PacketUpdateTileEntity packet = new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, compound);
        return packet;
    }

    public boolean canUpdate() {
        return true;
    }

    public boolean func_82704_a(Entity var1) {
        return var1 instanceof EntityPlayerMP || var1 instanceof EntityEnderPearl;
    }
}

