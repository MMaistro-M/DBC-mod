/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.tileentities;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.common.blocks.BlockSkinnableChild;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinnable;
import riskyken.armourersWorkshop.utils.ModLogger;

public class TileEntitySkinnableChild
extends TileEntitySkinnable {
    private static final String TAG_PARENT_X = "parentX";
    private static final String TAG_PARENT_Y = "parentY";
    private static final String TAG_PARENT_Z = "parentZ";
    public int parentX;
    public int parentY;
    public int parentZ;

    public boolean isParentValid() {
        return this.getParent() != null;
    }

    public void setParentLocation(int x, int y, int z) {
        this.parentX = x;
        this.parentY = y;
        this.parentZ = z;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    @Override
    public void setBoundsOnBlock(Block block, int xOffset, int yOffset, int zOffset) {
        int x = this.field_145851_c - this.parentX;
        int y = this.field_145848_d - this.parentY;
        int z = this.field_145849_e - this.parentZ;
        int widthOffset = x;
        int heightOffset = y;
        int depthOffset = z;
        if (block != null && !(block instanceof BlockSkinnableChild)) {
            ModLogger.log(Level.ERROR, String.format("Tile entity at X:%d Y:%d Z:%d has an invalid block.", xOffset, yOffset, zOffset));
            if (this.field_145850_b != null) {
                this.field_145850_b.func_147475_p(xOffset, yOffset, zOffset);
            }
            return;
        }
        BlockSkinnableChild child = (BlockSkinnableChild)this.func_145838_q();
        ForgeDirection dir = child.getFacingDirection((IBlockAccess)this.func_145831_w(), this.field_145851_c, this.field_145848_d, this.field_145849_e);
        switch (dir) {
            case NORTH: {
                widthOffset = 1 - x;
                depthOffset = z;
                break;
            }
            case EAST: {
                widthOffset = -x;
                depthOffset = z + 1;
                break;
            }
            case SOUTH: {
                widthOffset = 1 - x;
                depthOffset = 2 + z;
                break;
            }
            case WEST: {
                widthOffset = 2 - x;
                depthOffset = z + 1;
                break;
            }
        }
        super.setBoundsOnBlock(block, widthOffset, heightOffset, depthOffset);
    }

    @Override
    public TileEntitySkinnable getParent() {
        TileEntity te = this.field_145850_b.func_147438_o(this.parentX, this.parentY, this.parentZ);
        if (te != null && te instanceof TileEntitySkinnable) {
            return (TileEntitySkinnable)te;
        }
        return null;
    }

    @Override
    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.parentX = compound.func_74762_e(TAG_PARENT_X);
        this.parentY = compound.func_74762_e(TAG_PARENT_Y);
        this.parentZ = compound.func_74762_e(TAG_PARENT_Z);
    }

    @Override
    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        compound.func_74768_a(TAG_PARENT_X, this.parentX);
        compound.func_74768_a(TAG_PARENT_Y, this.parentY);
        compound.func_74768_a(TAG_PARENT_Z, this.parentZ);
    }
}

