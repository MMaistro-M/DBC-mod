/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.ColorizerFoliage
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 */
package JinRyuu.DragonBC.common.Worlds;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.Npcs.EntitySaiyan01;
import JinRyuu.DragonBC.common.Npcs.EntitySaiyan02;
import JinRyuu.DragonBC.common.Worlds.BiomeDecoratorDBC;
import JinRyuu.DragonBC.common.Worlds.BiomeGenBaseDBC;
import java.awt.Color;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.biome.BiomeGenBase;

public class x3BiomeGenVegata
extends BiomeGenBaseDBC {
    BiomeDecoratorDBC customBiomeDecorator;
    final float HUE = Color.RGBtoHSB(255, 102, 102, null)[0];

    public x3BiomeGenVegata(int par1) {
        super(par1);
        this.func_76735_a("Vegeta");
        this.field_76752_A = BlocksDBC.BlockNamekDirt;
        this.field_76753_B = BlocksDBC.BlockAlienStone;
        this.field_76760_I = new BiomeDecoratorDBC();
        this.customBiomeDecorator = (BiomeDecoratorDBC)this.field_76760_I;
        this.func_76739_b(8368696);
        this.setMinMaxHeight(0.0f, 0.7f);
        this.func_76732_a(0.8f, 0.9f);
        this.field_76750_F = 0.5f;
        this.field_76759_H = 0xFF0000;
        this.field_76761_J.clear();
        this.field_76762_K.clear();
        this.field_76755_L.clear();
        if (DBCConfig.spwnrt_syn > 0) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntitySaiyan01.class, DBCConfig.spwnrt_syn, 1, 3));
        }
        if (DBCConfig.spwnrt_syn2 > 0) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntitySaiyan02.class, DBCConfig.spwnrt_syn2, 1, 3));
        }
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

    public int getBiomeFoliageColor() {
        double var1 = MathHelper.func_76131_a((float)this.getFloatTemperature2(), (float)0.0f, (float)1.0f);
        double var3 = MathHelper.func_76131_a((float)this.func_76727_i(), (float)0.0f, (float)1.0f);
        return ColorizerFoliage.func_77470_a((double)var1, (double)var3);
    }
}

