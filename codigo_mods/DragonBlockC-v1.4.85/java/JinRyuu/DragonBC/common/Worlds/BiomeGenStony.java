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
import JinRyuu.DragonBC.common.Npcs.EntityDino01;
import JinRyuu.DragonBC.common.Npcs.EntityDino02;
import JinRyuu.DragonBC.common.Npcs.EntityDino03;
import JinRyuu.DragonBC.common.Npcs.EntityRRMecha;
import JinRyuu.DragonBC.common.Npcs.EntitySabertooth;
import JinRyuu.DragonBC.common.Npcs.db.EntityBearThief;
import JinRyuu.DragonBC.common.Npcs.db.EntityTigerBandit;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityMajorMetallitron;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityRedRibbonSoldier;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityRedRibbonSoldier2;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityRedRibbonSoldier3;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityRedRibbonSoldierB;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityRedRibbonSoldierB2;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityRedRibbonSoldierB3;
import JinRyuu.DragonBC.common.Worlds.BiomeDecoratorDBC;
import JinRyuu.DragonBC.common.Worlds.BiomeGenBaseDBC;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenStony
extends BiomeGenBase {
    BiomeDecoratorDBC customBiomeDecorator;
    public static final BiomeGenBase Stony = BiomeGenBaseDBC.Stony;

    public BiomeGenStony(int par1) {
        super(par1);
        this.func_76735_a("Dirty Stony");
        this.field_76752_A = BlocksDBC.BlockNamekDirt;
        this.field_76753_B = BlocksDBC.BlockAlienStone;
        this.field_76760_I = new BiomeDecoratorDBC();
        this.customBiomeDecorator = (BiomeDecoratorDBC)this.field_76760_I;
        this.customBiomeDecorator.NamekTreePerChunk = 0;
        this.customBiomeDecorator.WarenaiOrePerChunk = 0;
        this.customBiomeDecorator.NamekianHouseChunk = 0;
        this.customBiomeDecorator.NamekFreezaSoldiersChunk = 0;
        this.setMinMaxHeight(0.4f, 1.5f);
        this.func_76732_a(0.8f, 0.9f);
        this.field_76750_F = 0.5f;
        this.field_76762_K.clear();
        this.field_76755_L.clear();
        if (DBCConfig.spwnrt_dino1 > 0) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityDino01.class, DBCConfig.spwnrt_dino1, 1, 1));
        }
        if (DBCConfig.spwnrt_dino2 > 0) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityDino02.class, DBCConfig.spwnrt_dino2, 1, 1));
        }
        if (DBCConfig.spwnrt_dino3 > 0) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityDino03.class, DBCConfig.spwnrt_dino3, 1, 1));
        }
        if (DBCConfig.spwnrt_rrmech1 > 0) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityRRMecha.class, DBCConfig.spwnrt_rrmech1, 1, 1));
        }
        if (DBCConfig.spwnrt_sabert > 0) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntitySabertooth.class, DBCConfig.spwnrt_sabert, 1, 1));
        }
        if (DBCConfig.SpawnrateBearThief > 0) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityBearThief.class, DBCConfig.SpawnrateBearThief, 1, 1));
        }
        if (DBCConfig.SpawnrateTigerBandit > 0) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityTigerBandit.class, DBCConfig.SpawnrateTigerBandit, 1, 1));
        }
        if (DBCConfig.SpawnrateRRMajor > 0) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityMajorMetallitron.class, DBCConfig.SpawnrateRRMajor, 1, 1));
        }
        if (DBCConfig.SpawnrateRRSoldiers > 0) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityRedRibbonSoldier.class, DBCConfig.SpawnrateRRSoldiers, 1, 1));
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityRedRibbonSoldier2.class, DBCConfig.SpawnrateRRSoldiers, 1, 1));
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityRedRibbonSoldier3.class, DBCConfig.SpawnrateRRSoldiers, 1, 1));
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityRedRibbonSoldierB.class, DBCConfig.SpawnrateRRSoldiers, 1, 1));
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityRedRibbonSoldierB2.class, DBCConfig.SpawnrateRRSoldiers, 1, 1));
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityRedRibbonSoldierB3.class, DBCConfig.SpawnrateRRSoldiers, 1, 1));
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

    public int getBiomeFoliageColor() {
        double var1 = MathHelper.func_76131_a((float)this.getFloatTemperature2(), (float)0.0f, (float)1.0f);
        double var3 = MathHelper.func_76131_a((float)this.func_76727_i(), (float)0.0f, (float)1.0f);
        return ColorizerFoliage.func_77470_a((double)var1, (double)var3);
    }
}

