/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 */
package noppes.npcs.blocks.tiles;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import noppes.npcs.CustomNpcs;
import noppes.npcs.blocks.BlockNpcRedstone;
import noppes.npcs.controllers.data.Availability;

public class TileRedstoneBlock
extends TileEntity {
    public int onRange = 6;
    public int offRange = 10;
    public int onRangeX = 6;
    public int onRangeY = 6;
    public int onRangeZ = 6;
    public int offRangeX = 10;
    public int offRangeY = 10;
    public int offRangeZ = 10;
    public boolean isDetailed = false;
    public Availability availability = new Availability();
    public boolean isActivated = false;
    private int ticks = 10;

    public void func_145845_h() {
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        --this.ticks;
        if (this.ticks > 0) {
            return;
        }
        this.ticks = 20;
        Block block = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        if (block == null || !(block instanceof BlockNpcRedstone)) {
            return;
        }
        if (CustomNpcs.FreezeNPCs) {
            if (this.isActivated) {
                this.setActive(block, false);
            }
            return;
        }
        if (!this.isActivated) {
            int z;
            int y;
            int x = this.isDetailed ? this.onRangeX : this.onRange;
            List<EntityPlayer> list = this.getPlayerList(x, y = this.isDetailed ? this.onRangeY : this.onRange, z = this.isDetailed ? this.onRangeZ : this.onRange);
            if (list.isEmpty()) {
                return;
            }
            for (EntityPlayer player : list) {
                if (!this.availability.isAvailable(player)) continue;
                this.setActive(block, true);
                return;
            }
        } else {
            int x = this.isDetailed ? this.offRangeX : this.offRange;
            int y = this.isDetailed ? this.offRangeY : this.offRange;
            int z = this.isDetailed ? this.offRangeZ : this.offRange;
            List<EntityPlayer> list = this.getPlayerList(x, y, z);
            for (EntityPlayer player : list) {
                if (!this.availability.isAvailable(player)) continue;
                return;
            }
            this.setActive(block, false);
        }
    }

    private void setActive(Block block, boolean bo) {
        this.isActivated = bo;
        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.isActivated ? 1 : 0, 2);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        block.func_149726_b(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    private List<EntityPlayer> getPlayerList(int x, int y, int z) {
        return this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b((double)x, (double)y, (double)z));
    }

    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.onRange = compound.func_74762_e("BlockOnRange");
        this.offRange = compound.func_74762_e("BlockOffRange");
        this.isDetailed = compound.func_74767_n("BlockIsDetailed");
        if (compound.func_74764_b("BlockOnRangeX")) {
            this.isDetailed = true;
            this.onRangeX = compound.func_74762_e("BlockOnRangeX");
            this.onRangeY = compound.func_74762_e("BlockOnRangeY");
            this.onRangeZ = compound.func_74762_e("BlockOnRangeZ");
            this.offRangeX = compound.func_74762_e("BlockOffRangeX");
            this.offRangeY = compound.func_74762_e("BlockOffRangeY");
            this.offRangeZ = compound.func_74762_e("BlockOffRangeZ");
        }
        this.isActivated = compound.func_74767_n("BlockActivated");
        this.availability.readFromNBT(compound);
        if (this.field_145850_b != null) {
            this.setActive(this.func_145838_q(), this.isActivated);
        }
    }

    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        compound.func_74768_a("BlockOnRange", this.onRange);
        compound.func_74768_a("BlockOffRange", this.offRange);
        compound.func_74757_a("BlockActivated", this.isActivated);
        compound.func_74757_a("BlockIsDetailed", this.isDetailed);
        if (this.isDetailed) {
            compound.func_74768_a("BlockOnRangeX", this.onRangeX);
            compound.func_74768_a("BlockOnRangeY", this.onRangeY);
            compound.func_74768_a("BlockOnRangeZ", this.onRangeZ);
            compound.func_74768_a("BlockOffRangeX", this.offRangeX);
            compound.func_74768_a("BlockOffRangeY", this.offRangeY);
            compound.func_74768_a("BlockOffRangeZ", this.offRangeZ);
        }
        this.availability.writeToNBT(compound);
    }

    public boolean canUpdate() {
        return true;
    }
}

