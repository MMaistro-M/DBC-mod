/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package riskyken.armourersWorkshop.utils;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import riskyken.armourersWorkshop.api.common.painting.IPantable;
import riskyken.armourersWorkshop.api.common.skin.cubes.ICubeColour;
import riskyken.armourersWorkshop.common.blocks.BlockColourable;
import riskyken.armourersWorkshop.common.blocks.BlockLocation;
import riskyken.armourersWorkshop.common.skin.cubes.CubeColour;
import riskyken.armourersWorkshop.utils.UtilColour;
import riskyken.armourersWorkshop.utils.UtilItems;

public final class BlockUtils {
    private BlockUtils() {
    }

    public static int determineOrientation(int x, int y, int z, EntityLivingBase entity) {
        int l;
        if (MathHelper.func_76135_e((float)((float)entity.field_70165_t - (float)x)) < 2.0f && MathHelper.func_76135_e((float)((float)entity.field_70161_v - (float)z)) < 2.0f) {
            double d0 = entity.field_70163_u + (double)entity.func_70047_e() - (double)entity.field_70129_M;
            if (d0 - (double)y > 2.0) {
                return 1;
            }
            if ((double)y - d0 > 0.0) {
                return 0;
            }
        }
        return (l = MathHelper.func_76128_c((double)((double)(entity.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3) == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
    }

    public static int determineOrientationSide(EntityLivingBase entity) {
        int rotation = MathHelper.func_76128_c((double)((double)(entity.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        rotation = BlockUtils.determineOrientationSideMeta(rotation);
        return rotation;
    }

    public static int determineOrientationSideMeta(int metadata) {
        return metadata == 0 ? 3 : (metadata == 3 ? 5 : (metadata == 1 ? 4 : 2));
    }

    public static ForgeDirection determineDirectionSideMeta(int metadata) {
        return ForgeDirection.getOrientation((int)BlockUtils.determineOrientationSideMeta(metadata));
    }

    public static ForgeDirection determineDirectionSide(EntityLivingBase entity) {
        return ForgeDirection.getOrientation((int)BlockUtils.determineOrientationSide(entity));
    }

    public static int getColourFromTileEntity(World world, int x, int y, int z, int side) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null & te instanceof IPantable) {
            return ((IPantable)te).getColour(side);
        }
        return UtilColour.getMinecraftColor(0, UtilColour.ColourFamily.MINECRAFT);
    }

    public static ICubeColour getColourFromTileEntity(World world, int x, int y, int z) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null & te instanceof IPantable) {
            return ((IPantable)te).getColour();
        }
        return new CubeColour();
    }

    public static void dropInventoryBlocks(World world, int x, int y, int z) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null & te instanceof IInventory) {
            BlockUtils.dropInventoryBlocks(world, (IInventory)te, x, y, z);
        }
    }

    public static void dropInventoryBlocks(World world, IInventory inventory, int x, int y, int z) {
        for (int i = 0; i < inventory.func_70302_i_(); ++i) {
            ItemStack stack = inventory.func_70301_a(i);
            if (stack == null) continue;
            UtilItems.spawnItemInWorld(world, x, y, z, stack);
        }
    }

    public static ArrayList<BlockLocation> findTouchingBlockFaces(World world, int x, int y, int z, int side, int radius) {
        ForgeDirection dir = ForgeDirection.getOrientation((int)side);
        ArrayList<BlockLocation> blockFaces = new ArrayList<BlockLocation>();
        ArrayList<BlockLocation> openList = new ArrayList<BlockLocation>();
        ArrayList<BlockLocation> closedList = new ArrayList<BlockLocation>();
        openList.add(new BlockLocation(x, y, z).offset(dir));
        ForgeDirection[] sides = ForgeDirection.VALID_DIRECTIONS;
        while (!openList.isEmpty()) {
            BlockLocation loc = (BlockLocation)openList.get(0);
            openList.remove(0);
            Block block = world.func_147439_a(loc.x, loc.y, loc.z);
            if (block instanceof BlockColourable) {
                blockFaces.add(loc);
            }
            if (world.func_147437_c(loc.x, loc.y, loc.z)) {
                for (int i = 0; i < sides.length; ++i) {
                    BlockLocation sideLoc = loc.offset(sides[i]);
                    Block sideBlock = world.func_147439_a(sideLoc.x, sideLoc.y, sideLoc.z);
                    if (closedList.contains(sideLoc)) continue;
                    closedList.add(sideLoc);
                    boolean validCube = false;
                    for (int ix = 0; ix < 3; ++ix) {
                        for (int iy = 0; iy < 3; ++iy) {
                            for (int iz = 0; iz < 3; ++iz) {
                                Block validBlock = world.func_147439_a(sideLoc.x + ix - 1, sideLoc.y + iy - 1, sideLoc.z + iz - 1);
                                if (!(validBlock instanceof BlockColourable)) continue;
                                validCube = true;
                            }
                        }
                    }
                    if (!(sideLoc.getDistance(x, y, z) < (double)radius & validCube)) continue;
                    openList.add(sideLoc);
                }
            }
            if (closedList.size() <= 5000) continue;
            break;
        }
        return blockFaces;
    }

    public static class BlockFace
    extends BlockLocation {
        public final int face;

        public BlockFace(int x, int y, int z, int face) {
            super(x, y, z);
            this.face = face;
        }

        @Override
        public int hashCode() {
            int prime = 31;
            int result = super.hashCode();
            result = 31 * result + this.face;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!super.equals(obj)) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            BlockFace other = (BlockFace)obj;
            return this.face == other.face;
        }

        @Override
        public String toString() {
            return "BlockFace [face=" + this.face + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + "]";
        }
    }
}

