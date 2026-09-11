/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.monster.EntitySpider
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.ColorizerFoliage
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 */
package JinRyuu.DragonBC.common.Worlds;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.Npcs.EntityDino01;
import JinRyuu.DragonBC.common.Npcs.EntityDino02;
import JinRyuu.DragonBC.common.Npcs.EntityDino03;
import JinRyuu.DragonBC.common.Npcs.EntityNamekFrog;
import JinRyuu.DragonBC.common.Worlds.BiomeDecoratorDBC;
import JinRyuu.DragonBC.common.Worlds.BiomeGenBaseDBC;
import java.awt.Color;
import java.util.Random;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class x0BiomeGenNamek
extends BiomeGenBaseDBC {
    BiomeDecoratorDBC customBiomeDecorator;

    public x0BiomeGenNamek(int par1) {
        super(par1);
        this.func_76735_a("Namek");
        this.field_76752_A = BlocksDBC.BlockNamekGrass;
        this.field_76753_B = BlocksDBC.BlockNamekDirt;
        this.field_76760_I = new BiomeDecoratorDBC();
        this.customBiomeDecorator = (BiomeDecoratorDBC)this.field_76760_I;
        this.customBiomeDecorator.NamekTreePerChunk = 2;
        this.customBiomeDecorator.NamekMedMossPerChunk = 2;
        this.customBiomeDecorator.WarenaiOrePerChunk = 10;
        this.customBiomeDecorator.NamekianHouseChunk = 60;
        this.customBiomeDecorator.NamekFreezaSoldiersChunk = 8;
        this.func_76739_b(8368696);
        this.setMinMaxHeight(-0.3f, 0.4f);
        this.func_76732_a(0.8f, 0.9f);
        this.field_76750_F = 0.5f;
        this.field_76759_H = 65331;
        this.field_76761_J.clear();
        this.field_76762_K.clear();
        this.field_76755_L.clear();
        this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 1, 4));
        this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 1, 4));
        if (DBCConfig.spwnrt_dino1 > 0) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityDino01.class, DBCConfig.spwnrt_dino1, 1, 1));
        }
        if (DBCConfig.spwnrt_dino2 > 0) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityDino02.class, DBCConfig.spwnrt_dino2, 1, 1));
        }
        if (DBCConfig.spwnrt_dino3 > 0) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityDino03.class, DBCConfig.spwnrt_dino3, 1, 1));
        }
        if (DBCConfig.spwnrt_frg > 0) {
            this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityNamekFrog.class, DBCConfig.spwnrt_frg, 1, 1));
        }
    }

    public void func_76728_a(World world, Random random, int BlockX, int BlockZ) {
        super.func_76728_a(world, random, BlockX, BlockZ);
    }

    public String getTextureFile() {
        return "jinryuudragonbc:watercolor.png";
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
        this.field_76790_z = par1;
        return this;
    }

    public int func_76731_a(float par1) {
        if ((par1 /= 3.0f) < -1.0f) {
            par1 = -1.0f;
        }
        if (par1 > 1.0f) {
            par1 = 1.0f;
        }
        return Color.getHSBColor(0.42f - par1 * 0.05f, 0.5f + par1 * 0.1f, 1.0f).getRGB();
    }

    public int getBiomeFoliageColor() {
        double var1 = MathHelper.func_76131_a((float)this.getFloatTemperature2(), (float)0.0f, (float)1.0f);
        double var3 = MathHelper.func_76131_a((float)this.func_76727_i(), (float)0.0f, (float)1.0f);
        return ColorizerFoliage.func_77470_a((double)var1, (double)var3);
    }
}

