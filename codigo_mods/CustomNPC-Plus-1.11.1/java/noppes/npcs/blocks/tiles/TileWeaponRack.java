/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.util.AxisAlignedBB
 */
package noppes.npcs.blocks.tiles;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import noppes.npcs.blocks.tiles.TileNpcContainer;

public class TileWeaponRack
extends TileNpcContainer {
    @Override
    public boolean func_94041_b(int var1, ItemStack itemstack) {
        if (itemstack != null && itemstack.func_77973_b() instanceof ItemBlock) {
            return false;
        }
        return super.func_94041_b(var1, itemstack);
    }

    @Override
    public int func_70302_i_() {
        return 3;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 2), (double)(this.field_145849_e + 1));
    }

    @Override
    public String getName() {
        return "tile.npcWeaponRack.name";
    }

    @Override
    public Packet func_145844_m() {
        NBTTagCompound compound = new NBTTagCompound();
        this.func_145841_b(compound);
        S35PacketUpdateTileEntity packet = new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, compound);
        return packet;
    }

    @Override
    public int powerProvided() {
        int power = 0;
        for (int i = 0; i < 3; ++i) {
            if (this.func_70301_a(i) == null) continue;
            power += 5;
        }
        return power;
    }
}

