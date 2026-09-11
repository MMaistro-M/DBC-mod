/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fluids.BlockFluidClassic
 *  net.minecraftforge.fluids.Fluid
 */
package JinRyuu.DragonBC.common.Blocks;

import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockHealingPods
extends BlockFluidClassic {
    @SideOnly(value=Side.CLIENT)
    protected IIcon stillIcon;
    @SideOnly(value=Side.CLIENT)
    protected IIcon flowingIcon;

    public BlockHealingPods(Fluid fluid, Material material) {
        super(fluid, material);
        this.func_149647_a(mod_DragonBC.DragonBlockC);
    }

    public IIcon func_149691_a(int side, int meta) {
        return side == 0 || side == 1 ? this.stillIcon : this.flowingIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister register) {
        this.stillIcon = register.func_94245_a(JRMCoreH.tjdbcAssts + ":" + this.func_149739_a() + "Still");
        this.flowingIcon = register.func_94245_a(JRMCoreH.tjdbcAssts + ":" + this.func_149739_a() + "Flowing");
    }

    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
        if (world.func_147439_a(x, y, z).func_149688_o().func_76224_d()) {
            return false;
        }
        return super.canDisplace(world, x, y, z);
    }

    public boolean displaceIfPossible(World world, int x, int y, int z) {
        if (world.func_147439_a(x, y, z).func_149688_o().func_76224_d()) {
            return false;
        }
        return super.displaceIfPossible(world, x, y, z);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {
        double d7;
        int l;
        if (this.field_149764_J == Material.field_151586_h) {
            if (p_149734_5_.nextInt(10) == 0 && ((l = p_149734_1_.func_72805_g(p_149734_2_, p_149734_3_, p_149734_4_)) <= 0 || l >= 8)) {
                p_149734_1_.func_72869_a("suspended", (double)((float)p_149734_2_ + p_149734_5_.nextFloat()), (double)((float)p_149734_3_ + p_149734_5_.nextFloat()), (double)((float)p_149734_4_ + p_149734_5_.nextFloat()), 0.0, 0.0, 0.0);
            }
            for (l = 0; l < 0; ++l) {
                int i1 = p_149734_5_.nextInt(4);
                int j1 = p_149734_2_;
                int k1 = p_149734_4_;
                if (i1 == 0) {
                    j1 = p_149734_2_ - 1;
                }
                if (i1 == 1) {
                    ++j1;
                }
                if (i1 == 2) {
                    k1 = p_149734_4_ - 1;
                }
                if (i1 == 3) {
                    ++k1;
                }
                if (p_149734_1_.func_147439_a(j1, p_149734_3_, k1).func_149688_o() != Material.field_151579_a || !p_149734_1_.func_147439_a(j1, p_149734_3_ - 1, k1).func_149688_o().func_76230_c() && !p_149734_1_.func_147439_a(j1, p_149734_3_ - 1, k1).func_149688_o().func_76224_d()) continue;
                float f = 0.0625f;
                double d0 = (float)p_149734_2_ + p_149734_5_.nextFloat();
                double d1 = (float)p_149734_3_ + p_149734_5_.nextFloat();
                double d2 = (float)p_149734_4_ + p_149734_5_.nextFloat();
                if (i1 == 0) {
                    d0 = (float)p_149734_2_ - f;
                }
                if (i1 == 1) {
                    d0 = (float)(p_149734_2_ + 1) + f;
                }
                if (i1 == 2) {
                    d2 = (float)p_149734_4_ - f;
                }
                if (i1 == 3) {
                    d2 = (float)(p_149734_4_ + 1) + f;
                }
                double d3 = 0.0;
                double d4 = 0.0;
                if (i1 == 0) {
                    d3 = -f;
                }
                if (i1 == 1) {
                    d3 = f;
                }
                if (i1 == 2) {
                    d4 = -f;
                }
                if (i1 == 3) {
                    d4 = f;
                }
                p_149734_1_.func_72869_a("splash", d0, d1, d2, d3, 0.0, d4);
            }
        }
        if (this.field_149764_J == Material.field_151586_h && p_149734_5_.nextInt(64) == 0 && (l = p_149734_1_.func_72805_g(p_149734_2_, p_149734_3_, p_149734_4_)) > 0 && l < 8) {
            p_149734_1_.func_72980_b((double)((float)p_149734_2_ + 0.5f), (double)((float)p_149734_3_ + 0.5f), (double)((float)p_149734_4_ + 0.5f), "liquid.water", p_149734_5_.nextFloat() * 0.25f + 0.75f, p_149734_5_.nextFloat() * 1.0f + 0.5f, false);
        }
        if (this.field_149764_J == Material.field_151587_i && p_149734_1_.func_147439_a(p_149734_2_, p_149734_3_ + 1, p_149734_4_).func_149688_o() == Material.field_151579_a && !p_149734_1_.func_147439_a(p_149734_2_, p_149734_3_ + 1, p_149734_4_).func_149662_c()) {
            if (p_149734_5_.nextInt(100) == 0) {
                double d5 = (float)p_149734_2_ + p_149734_5_.nextFloat();
                double d6 = (double)p_149734_3_ + this.field_149756_F;
                d7 = (float)p_149734_4_ + p_149734_5_.nextFloat();
                p_149734_1_.func_72869_a("lava", d5, d6, d7, 0.0, 0.0, 0.0);
                p_149734_1_.func_72980_b(d5, d6, d7, "liquid.lavapop", 0.2f + p_149734_5_.nextFloat() * 0.2f, 0.9f + p_149734_5_.nextFloat() * 0.15f, false);
            }
            if (p_149734_5_.nextInt(200) == 0) {
                p_149734_1_.func_72980_b((double)p_149734_2_, (double)p_149734_3_, (double)p_149734_4_, "liquid.lava", 0.2f + p_149734_5_.nextFloat() * 0.2f, 0.9f + p_149734_5_.nextFloat() * 0.15f, false);
            }
        }
        if (p_149734_5_.nextInt(10) == 0 && World.func_147466_a((IBlockAccess)p_149734_1_, (int)p_149734_2_, (int)(p_149734_3_ - 1), (int)p_149734_4_) && !p_149734_1_.func_147439_a(p_149734_2_, p_149734_3_ - 2, p_149734_4_).func_149688_o().func_76230_c()) {
            double d5 = (float)p_149734_2_ + p_149734_5_.nextFloat();
            double d6 = (double)p_149734_3_ - 1.05;
            d7 = (float)p_149734_4_ + p_149734_5_.nextFloat();
            if (this.field_149764_J == Material.field_151586_h) {
                p_149734_1_.func_72869_a("dripWater", d5, d6, d7, 0.0, 0.0, 0.0);
            } else {
                p_149734_1_.func_72869_a("dripLava", d5, d6, d7, 0.0, 0.0, 0.0);
            }
        }
    }
}

