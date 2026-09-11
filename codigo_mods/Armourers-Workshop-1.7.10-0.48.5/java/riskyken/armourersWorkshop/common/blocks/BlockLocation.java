/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.common.util.ForgeDirection
 */
package riskyken.armourersWorkshop.common.blocks;

import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockLocation {
    public final int x;
    public final int y;
    public final int z;

    public BlockLocation(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public BlockLocation offset(ForgeDirection direction) {
        return new BlockLocation(this.x + direction.offsetX, this.y + direction.offsetY, this.z + direction.offsetZ);
    }

    public String toString() {
        return "BlockLocation [x=" + this.x + ", y=" + this.y + ", z=" + this.z + "]";
    }

    public double getDistance(double x, double y, double z) {
        double disX = (double)this.x - x;
        double disY = (double)this.y - y;
        double disZ = (double)this.z - z;
        return MathHelper.func_76133_a((double)(disX * disX + disY * disY + disZ * disZ));
    }

    public double getDistance(BlockLocation blockLocation) {
        return this.getDistance(blockLocation.x, blockLocation.y, blockLocation.z);
    }

    public BlockLocation clone() {
        return new BlockLocation(this.x, this.y, this.z);
    }

    public int hashCode() {
        return this.x ^ this.y * 137 ^ this.z * 11317;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        BlockLocation other = (BlockLocation)obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return this.z == other.z;
    }
}

