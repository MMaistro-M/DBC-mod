/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFlower
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeDecorator
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.feature.WorldGenFlowers
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent$Decorate$EventType
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent$Post
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent$Pre
 *  net.minecraftforge.event.terraingen.OreGenEvent$GenerateMinable$EventType
 *  net.minecraftforge.event.terraingen.OreGenEvent$Post
 *  net.minecraftforge.event.terraingen.OreGenEvent$Pre
 *  net.minecraftforge.event.terraingen.TerrainGen
 */
package JinRyuu.DragonBC.common.Worlds;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.Villages.ChkInSt;
import JinRyuu.DragonBC.common.Villages.NamekianHouse1;
import JinRyuu.DragonBC.common.Worlds.WorldGenNamekMedMoss;
import JinRyuu.DragonBC.common.Worlds.WorldGenNamekTrees;
import JinRyuu.DragonBC.common.Worlds.WorldGenOWTrees;
import JinRyuu.DragonBC.common.Worlds.WorldGenOre;
import JinRyuu.DragonBC.common.m.WorldGenMahagonyTree;
import JinRyuu.DragonBC.common.m.WorldGenMapleTree;
import JinRyuu.DragonBC.common.m.WorldGenSakuraTree;
import cpw.mods.fml.common.eventhandler.Event;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BiomeDecoratorDBC
extends BiomeDecorator {
    protected int field_76803_B;
    protected int field_76832_z;
    protected int WarenaiOrePerChunk;
    protected int NamekMedMossPerChunk;
    protected int NamekTreePerChunk;
    protected int NamekianHouseChunk;
    protected int NamekFreezaSoldiersChunk;
    protected int ChkInStChunk;
    protected int OWtri1PerChunk;
    protected int OWtri2PerChunk;
    protected int OWtri3PerChunk;
    protected int yellowFlowersPerChunk;
    protected int OWtri4PerChunk;
    protected int roseBushPerChunk;
    protected WorldGenerator NamekTreeGen;
    protected WorldGenerator NamekMedMossGen;
    protected WorldGenerator WarenaiOreGen = new WorldGenOre(BlocksDBC.BlockOreWrenai, 5, Blocks.field_150348_b);
    protected WorldGenerator NamekianHouse;
    protected WorldGenerator ChkInSt;

    public BiomeDecoratorDBC() {
        this.NamekTreeGen = new WorldGenNamekTrees(true);
        this.NamekianHouse = new NamekianHouse1();
        this.NamekMedMossGen = new WorldGenNamekMedMoss(false);
        this.ChkInSt = new ChkInSt();
        this.field_150514_p = new WorldGenFlowers((Block)Blocks.field_150327_N);
    }

    public void func_150512_a(World p_150512_1_, Random p_150512_2_, BiomeGenBase p_150512_3_, int p_150512_4_, int p_150512_5_) {
        if (this.field_76815_a != null) {
            throw new RuntimeException("Already decorating!!");
        }
        this.field_76815_a = p_150512_1_;
        this.field_76813_b = p_150512_2_;
        this.field_76814_c = p_150512_4_;
        this.field_76811_d = p_150512_5_;
        this.func_150513_a(p_150512_3_);
        this.field_76815_a = null;
        this.field_76813_b = null;
    }

    protected void func_150513_a(BiomeGenBase p_150513_1_) {
        int var7;
        int var4;
        int var2;
        int i1;
        int l;
        int k;
        int var72;
        int var42;
        int var3;
        int j;
        MinecraftForge.EVENT_BUS.post((Event)new DecorateBiomeEvent.Pre(this.field_76815_a, this.field_76813_b, this.field_76814_c, this.field_76811_d));
        this.func_76797_b();
        boolean doGen = TerrainGen.decorate((World)this.field_76815_a, (Random)this.field_76813_b, (int)this.field_76814_c, (int)this.field_76811_d, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.TREE);
        for (j = 0; doGen && j < this.OWtri1PerChunk; ++j) {
            var3 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            var42 = this.field_76813_b.nextInt(50);
            var72 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            new WorldGenOWTrees(false).func_76484_a(this.field_76815_a, this.field_76813_b, var3, var42, var72);
        }
        for (j = 0; doGen && j < this.OWtri2PerChunk; ++j) {
            var3 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            var42 = this.field_76813_b.nextInt(50);
            var72 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            new WorldGenMahagonyTree().func_76484_a(this.field_76815_a, this.field_76813_b, var3, var42, var72);
        }
        for (j = 0; doGen && j < this.OWtri3PerChunk; ++j) {
            var3 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            var42 = this.field_76813_b.nextInt(50);
            var72 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            new WorldGenSakuraTree().func_76484_a(this.field_76815_a, this.field_76813_b, var3, var42, var72);
        }
        for (j = 0; doGen && j < this.OWtri4PerChunk; ++j) {
            var3 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            var42 = this.field_76813_b.nextInt(50);
            var72 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            new WorldGenMapleTree().func_76484_a(this.field_76815_a, this.field_76813_b, var3, var42, var72);
        }
        doGen = TerrainGen.decorate((World)this.field_76815_a, (Random)this.field_76813_b, (int)this.field_76814_c, (int)this.field_76811_d, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.FLOWERS);
        for (j = 0; doGen && j < this.yellowFlowersPerChunk; ++j) {
            k = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            l = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            i1 = this.field_76813_b.nextInt(50);
            String s = "dandelion";
            BlockFlower blockflower = BlockFlower.func_149857_e((String)s);
            if (blockflower.func_149688_o() == Material.field_151579_a) continue;
            this.field_150514_p.func_150550_a((Block)blockflower, BlockFlower.func_149856_f((String)s));
            this.field_150514_p.func_76484_a(this.field_76815_a, this.field_76813_b, k, i1, l);
        }
        doGen = TerrainGen.decorate((World)this.field_76815_a, (Random)this.field_76813_b, (int)this.field_76814_c, (int)this.field_76811_d, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.FLOWERS);
        for (j = 0; doGen && j < this.roseBushPerChunk; ++j) {
            k = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            l = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            i1 = this.field_76813_b.nextInt(50);
            String s = "tulipRed";
            BlockFlower blockflower = BlockFlower.func_149857_e((String)s);
            if (blockflower.func_149688_o() == Material.field_151579_a) continue;
            this.field_150514_p.func_150550_a((Block)blockflower, BlockFlower.func_149856_f((String)s));
            this.field_150514_p.func_76484_a(this.field_76815_a, this.field_76813_b, k, i1, l);
        }
        for (var2 = 0; doGen && var2 < this.NamekMedMossPerChunk; ++var2) {
            int var32 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            var4 = 65 + this.field_76813_b.nextInt(50);
            var7 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            this.NamekMedMossGen.func_76484_a(this.field_76815_a, this.field_76813_b, var32, var4, var7);
        }
        doGen = TerrainGen.decorate((World)this.field_76815_a, (Random)this.field_76813_b, (int)this.field_76814_c, (int)this.field_76811_d, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.TREE);
        for (var2 = 0; doGen && var2 < this.NamekTreePerChunk; ++var2) {
            int var33 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            var4 = 65 + this.field_76813_b.nextInt(50);
            var7 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            this.NamekTreeGen.func_76484_a(this.field_76815_a, this.field_76813_b, var33, var4, var7);
        }
        doGen = TerrainGen.decorate((World)this.field_76815_a, (Random)this.field_76813_b, (int)this.field_76814_c, (int)this.field_76811_d, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.CUSTOM);
        for (var2 = 0; doGen && var2 < this.WarenaiOrePerChunk; ++var2) {
            int var34 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            var4 = 32 + this.field_76813_b.nextInt(128);
            var7 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            this.WarenaiOreGen.func_76484_a(this.field_76815_a, this.field_76813_b, var34, var4, var7);
        }
        for (var2 = 0; doGen && var2 < this.NamekianHouseChunk; ++var2) {
            int var35 = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            var4 = 65 + this.field_76813_b.nextInt(20);
            var7 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            this.NamekianHouse.func_76484_a(this.field_76815_a, this.field_76813_b, var35, var4, var7);
        }
        MinecraftForge.EVENT_BUS.post((Event)new DecorateBiomeEvent.Post(this.field_76815_a, this.field_76813_b, this.field_76814_c, this.field_76811_d));
    }

    protected void func_76797_b() {
        MinecraftForge.ORE_GEN_BUS.post((Event)new OreGenEvent.Pre(this.field_76815_a, this.field_76813_b, this.field_76814_c, this.field_76811_d));
        if (TerrainGen.generateOre((World)this.field_76815_a, (Random)this.field_76813_b, (WorldGenerator)this.field_76823_i, (int)this.field_76814_c, (int)this.field_76811_d, (OreGenEvent.GenerateMinable.EventType)OreGenEvent.GenerateMinable.EventType.DIRT)) {
            this.func_76795_a(20, this.field_76823_i, 0, 256);
        }
        if (TerrainGen.generateOre((World)this.field_76815_a, (Random)this.field_76813_b, (WorldGenerator)this.field_76818_l, (int)this.field_76814_c, (int)this.field_76811_d, (OreGenEvent.GenerateMinable.EventType)OreGenEvent.GenerateMinable.EventType.IRON)) {
            this.func_76795_a(20, this.field_76818_l, 0, 64);
        }
        if (TerrainGen.generateOre((World)this.field_76815_a, (Random)this.field_76813_b, (WorldGenerator)this.field_76819_m, (int)this.field_76814_c, (int)this.field_76811_d, (OreGenEvent.GenerateMinable.EventType)OreGenEvent.GenerateMinable.EventType.GOLD)) {
            this.func_76795_a(2, this.field_76819_m, 0, 32);
        }
        MinecraftForge.ORE_GEN_BUS.post((Event)new OreGenEvent.Post(this.field_76815_a, this.field_76813_b, this.field_76814_c, this.field_76811_d));
    }

    private int nextInt(int i) {
        if (i <= 1) {
            return 0;
        }
        return this.field_76813_b.nextInt(i);
    }
}

