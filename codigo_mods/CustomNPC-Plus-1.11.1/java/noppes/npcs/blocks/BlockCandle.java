/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package noppes.npcs.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.blocks.BlockLightable;
import noppes.npcs.blocks.tiles.TileCandle;
import noppes.npcs.blocks.tiles.TileVariant;

public class BlockCandle
extends BlockLightable {
    public BlockCandle(boolean lit) {
        super(Blocks.field_150348_b, lit);
        this.func_149676_a(0.3f, 0.0f, 0.3f, 0.7f, 0.5f, 0.7f);
    }

    @Override
    public int maxRotation() {
        return 8;
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        TileEntity tileentity = world.func_147438_o(x, y, z);
        if (!(tileentity instanceof TileVariant)) {
            super.func_149719_a(world, x, y, z);
            return;
        }
        TileVariant tile = (TileVariant)tileentity;
        if (tile.variant == 2) {
            float xOffset = 0.0f;
            float yOffset = 0.0f;
            if (tile.rotation == 0) {
                yOffset = 0.2f;
            } else if (tile.rotation == 4) {
                yOffset = -0.2f;
            } else if (tile.rotation == 6) {
                xOffset = 0.2f;
            } else if (tile.rotation == 2) {
                xOffset = -0.2f;
            }
            this.func_149676_a(0.2f + xOffset, 0.4f, 0.2f + yOffset, 0.8f + xOffset, 0.9f, 0.8f + yOffset);
        } else if (tile.variant == 1) {
            this.func_149676_a(0.1f, 0.1f, 0.1f, 0.9f, 0.8f, 0.9f);
        } else {
            this.func_149676_a(0.3f, 0.0f, 0.3f, 0.7f, 0.5f, 0.7f);
        }
    }

    public int func_149660_a(World world, int x, int y, int z, int side, float p_149660_6_, float p_149660_7_, float p_149660_8_, int meta) {
        return side;
    }

    public void func_149714_e(World world, int x, int y, int z, int meta) {
        TileCandle tile = (TileCandle)world.func_147438_o(x, y, z);
        if (meta == 1) {
            tile.variant = 0;
        } else if (meta == 0) {
            tile.variant = 1;
        } else {
            tile.variant = 2;
            if (meta == 2) {
                tile.rotation = 0;
            } else if (meta == 3) {
                tile.rotation = 4;
            } else if (meta == 4) {
                tile.rotation = 6;
            } else if (meta == 5) {
                tile.rotation = 2;
            }
        }
        world.func_72921_c(x, y, z, 0, 4);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a(this.func_149641_N());
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int meta) {
        return this.field_149761_L;
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileCandle();
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random p_149734_5_) {
        if (this == this.unlitBlock()) {
            return;
        }
        TileCandle tile = (TileCandle)world.func_147438_o(x, y, z);
        if (tile.variant == 1) {
            if (tile.rotation % 2 == 0) {
                world.func_72869_a("smoke", (double)((float)x + 0.5f), (double)((float)y + 0.66f), (double)((float)z + 0.13f), 0.0, 0.0, 0.0);
                world.func_72869_a("flame", (double)((float)x + 0.5f), (double)((float)y + 0.65f), (double)((float)z + 0.13f), 0.0, 0.0, 0.0);
                world.func_72869_a("smoke", (double)((float)x + 0.5f), (double)((float)y + 0.66f), (double)((float)z + 0.87f), 0.0, 0.0, 0.0);
                world.func_72869_a("flame", (double)((float)x + 0.5f), (double)((float)y + 0.65f), (double)((float)z + 0.87f), 0.0, 0.0, 0.0);
                world.func_72869_a("smoke", (double)((float)x + 0.13f), (double)((float)y + 0.66f), (double)((float)z + 0.5f), 0.0, 0.0, 0.0);
                world.func_72869_a("flame", (double)((float)x + 0.13f), (double)((float)y + 0.65f), (double)((float)z + 0.5f), 0.0, 0.0, 0.0);
                world.func_72869_a("smoke", (double)((float)x + 0.87f), (double)((float)y + 0.66f), (double)((float)z + 0.5f), 0.0, 0.0, 0.0);
                world.func_72869_a("flame", (double)((float)x + 0.87f), (double)((float)y + 0.65f), (double)((float)z + 0.5f), 0.0, 0.0, 0.0);
            } else {
                world.func_72869_a("smoke", (double)((float)x + 0.24f), (double)((float)y + 0.66f), (double)((float)z + 0.24f), 0.0, 0.0, 0.0);
                world.func_72869_a("flame", (double)((float)x + 0.24f), (double)((float)y + 0.65f), (double)((float)z + 0.24f), 0.0, 0.0, 0.0);
                world.func_72869_a("smoke", (double)((float)x + 0.76f), (double)((float)y + 0.66f), (double)((float)z + 0.76f), 0.0, 0.0, 0.0);
                world.func_72869_a("flame", (double)((float)x + 0.76f), (double)((float)y + 0.65f), (double)((float)z + 0.76f), 0.0, 0.0, 0.0);
                world.func_72869_a("smoke", (double)((float)x + 0.24f), (double)((float)y + 0.66f), (double)((float)z + 0.76f), 0.0, 0.0, 0.0);
                world.func_72869_a("flame", (double)((float)x + 0.24f), (double)((float)y + 0.65f), (double)((float)z + 0.76f), 0.0, 0.0, 0.0);
                world.func_72869_a("smoke", (double)((float)x + 0.76f), (double)((float)y + 0.66f), (double)((float)z + 0.24f), 0.0, 0.0, 0.0);
                world.func_72869_a("flame", (double)((float)x + 0.76f), (double)((float)y + 0.65f), (double)((float)z + 0.24f), 0.0, 0.0, 0.0);
            }
        } else {
            float xOffset = 0.5f;
            float yOffset = 0.45f;
            float zOffset = 0.5f;
            if (tile.variant == 2) {
                yOffset = 1.05f;
                if (tile.rotation == 0) {
                    zOffset += 0.12f;
                }
                if (tile.rotation == 4) {
                    zOffset -= 0.12f;
                }
                if (tile.rotation == 6) {
                    xOffset += 0.12f;
                }
                if (tile.rotation == 2) {
                    xOffset -= 0.12f;
                }
            }
            double d0 = (float)x + xOffset;
            double d1 = (float)y + yOffset;
            double d2 = (float)z + zOffset;
            world.func_72869_a("smoke", d0, d1, d2, 0.0, 0.0, 0.0);
            world.func_72869_a("flame", d0, d1, d2, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public Block unlitBlock() {
        return CustomItems.candle_unlit;
    }

    @Override
    public Block litBlock() {
        return CustomItems.candle;
    }
}

