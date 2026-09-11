/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.ColorizerFoliage
 *  net.minecraft.world.ColorizerGrass
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 */
package JinRyuu.DragonBC.common.Worlds;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.Npcs.EntityHell01;
import JinRyuu.DragonBC.common.Npcs.EntityHell02;
import JinRyuu.DragonBC.common.Worlds.BiomeDecoratorDBC;
import JinRyuu.DragonBC.common.Worlds.BiomeGenBaseDBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class x1BiomeGenOW2
extends BiomeGenBaseDBC {
    BiomeDecoratorDBC customBiomeDecorator;
    final float HUE = Color.RGBtoHSB(255, 102, 102, null)[0];

    public x1BiomeGenOW2(int par1) {
        super(par1);
        this.func_76735_a("Other World type 2");
        this.field_76752_A = Blocks.field_150349_c;
        this.field_76753_B = Blocks.field_150349_c;
        this.field_76760_I = new BiomeDecoratorDBC();
        this.customBiomeDecorator = (BiomeDecoratorDBC)this.field_76760_I;
        this.customBiomeDecorator.OWtri2PerChunk = 6;
        this.setMinMaxHeight(0.1f, 0.11f);
        this.func_76739_b(16421912);
        this.func_76745_m();
        this.func_76732_a(1.0f, 0.0f);
        this.field_76759_H = 0xFF0000;
        this.field_76761_J.clear();
        this.field_76762_K.clear();
        this.field_76755_L.clear();
        if (!DBCConfig.DsblO && DBCConfig.spwnrt_ogre > 0) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityHell01.class, DBCConfig.spwnrt_ogre, 1, 2));
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityHell02.class, DBCConfig.spwnrt_ogre, 1, 2));
        }
    }

    public void func_76728_a(World world, Random random, int par3, int par4) {
        super.func_76728_a(world, random, par3, par4);
    }

    public BiomeGenBase func_76732_a(float par1, float par2) {
        if (par1 > 0.1f && par1 < 0.2f) {
            throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
        }
        this.field_76750_F = par1;
        this.field_76751_G = par2;
        return this;
    }

    public BiomeGenBase setMinMaxHeight(float par1, float par2) {
        this.field_76748_D = par1;
        this.field_76749_E = par2;
        return this;
    }

    public final int getIntTemperature2() {
        return (int)(this.field_76750_F * 65536.0f);
    }

    public final float getFloatTemperature2() {
        return this.field_76750_F;
    }

    public BiomeGenBase func_76739_b(int par1) {
        this.field_76790_z = Color.getHSBColor(0.42f - (float)par1 * 0.05f, 0.5f + (float)par1 * 0.1f, 1.0f).getRGB();
        return this;
    }

    public int func_76731_a(float par1) {
        if ((par1 /= 3.0f) < -1.0f) {
            par1 = -1.0f;
        }
        if (par1 > 1.0f) {
            par1 = 1.0f;
        }
        return Color.getHSBColor(this.HUE, 0.5f + par1 * 0.1f, 1.0f).getRGB();
    }

    @SideOnly(value=Side.CLIENT)
    public int func_150558_b(int p_150558_1_, int p_150558_2_, int p_150558_3_) {
        double d0 = MathHelper.func_76131_a((float)this.func_150564_a(p_150558_1_, p_150558_2_, p_150558_3_), (float)0.0f, (float)1.0f);
        double d1 = MathHelper.func_76131_a((float)this.func_76727_i(), (float)0.0f, (float)1.0f);
        return this.getModdedBiomeGrassColor(ColorizerGrass.func_77480_a((double)d0, (double)d1));
    }

    @SideOnly(value=Side.CLIENT)
    public int func_150571_c(int p_150571_1_, int p_150571_2_, int p_150571_3_) {
        double d0 = MathHelper.func_76131_a((float)this.func_150564_a(p_150571_1_, p_150571_2_, p_150571_3_), (float)0.0f, (float)1.0f);
        double d1 = MathHelper.func_76131_a((float)this.func_76727_i(), (float)0.0f, (float)1.0f);
        return this.getModdedBiomeFoliageColor(ColorizerFoliage.func_77470_a((double)d0, (double)d1));
    }
}

