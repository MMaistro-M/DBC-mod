/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidRegistry
 */
package JinRyuu.DragonBC.common.Blocks;

import JinRyuu.DragonBC.common.Blocks.BlockAlienCobbleStone;
import JinRyuu.DragonBC.common.Blocks.BlockAlienStone;
import JinRyuu.DragonBC.common.Blocks.BlockAnyBase;
import JinRyuu.DragonBC.common.Blocks.BlockAnyBase2;
import JinRyuu.DragonBC.common.Blocks.BlockCropMedMoss;
import JinRyuu.DragonBC.common.Blocks.BlockDBCSaplings;
import JinRyuu.DragonBC.common.Blocks.BlockHealingPods;
import JinRyuu.DragonBC.common.Blocks.BlockNamekDirt;
import JinRyuu.DragonBC.common.Blocks.BlockNamekGrass;
import JinRyuu.DragonBC.common.Blocks.BlockNamekLeaves;
import JinRyuu.DragonBC.common.Blocks.BlockNamekLog;
import JinRyuu.DragonBC.common.Blocks.BlockNamekStone;
import JinRyuu.DragonBC.common.Blocks.BlockOreDBC;
import JinRyuu.DragonBC.common.Blocks.BlockOreWrenai;
import JinRyuu.DragonBC.common.Blocks.BlockTCFloor;
import JinRyuu.DragonBC.common.Blocks.BlockTCPort;
import JinRyuu.DragonBC.common.Blocks.BlockWildMedMoss;
import JinRyuu.DragonBC.common.Blocks.BlockYellowCloud;
import JinRyuu.DragonBC.common.Blocks.ItemDBCSaplingsTex;
import JinRyuu.DragonBC.common.Blocks.m.ModdedBlocks;
import JinRyuu.DragonBC.common.Render.ArtGravDevBlock;
import JinRyuu.DragonBC.common.Render.DragonBlock01Block;
import JinRyuu.DragonBC.common.Render.DragonBlockNS01Block;
import JinRyuu.DragonBC.common.Render.DragonBlockNamek01Block;
import JinRyuu.DragonBC.common.Render.DragonBlockS01Block;
import JinRyuu.DragonBC.common.Render.MedPodDoor1Block;
import JinRyuu.DragonBC.common.Render.SaibaiHatchedBlock;
import JinRyuu.DragonBC.common.Render.SpacePod01Block;
import JinRyuu.DragonBC.common.Render.namekian_throneBlock;
import JinRyuu.DragonBC.common.Render.ppBlock;
import JinRyuu.DragonBC.common.mod_DragonBC;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class BlocksDBC {
    public static Block BlockAlienStone;
    public static Block BlockAlienCobbleStone;
    public static Block BlockNamekDragonBallStone;
    public static Block BlockNamekDragonBall;
    public static Block BlockDragonBallStone;
    public static Block BlockDragonBall;
    public static Block BlockOreWrenai;
    public static Block BlockNamekGrass;
    public static Block BlockNamekDirt;
    public static Block BlockNamekStone;
    public static Block BlockNamekLog;
    public static Block BlockNamekLeaves;
    public static Block BlockKiFire;
    public static Block AuraDyn;
    public static Block BlockHellStone;
    public static Block BlockYellowCloud;
    public static Block BlockInvisWall;
    public static Block BlockTCPort;
    public static Block BlockTCFloor;
    public static Fluid medicalLiquid;
    public static Block BlockHealingPods;
    public static Block BlockHealingPodDoor;
    public static Block BlockCropMedMoss;
    public static Block BlockWildMedMoss;
    public static Block SpacePod01Block;
    public static Block SaibaiHatched;
    public static Block ArtGravDevBlock;
    public static BlockDBCSaplings BlockNamekSapling;
    public static Block DBCmobSpawner;
    public static String mod;
    public static Block ppBlock;
    public static Block BlockOreJJay;
    public static Block BlockOreDlog;
    public static Block BlockOreLehnori;
    public static Block BlockOreDnomaid;
    public static String[] BlockOWnams;
    public static Block[] BlockOWtyps;
    public static Block[] BlockOW;
    public static final int BlockJJay = 0;
    public static final int BlockDlog = 1;
    public static final int BlockLehnori = 2;
    public static final int BlockDnomaid = 3;
    public static Block namekian_throneBlock;
    private static int BlockKachiKachin_n;
    public static Block[] BlockKachiKachin;
    private static int BlockKachin_n;
    public static Block[] BlockKachin;

    public static void init() {
        int i;
        FluidRegistry.registerFluid((Fluid)medicalLiquid);
        BlockAlienStone = new BlockAlienStone().func_149663_c("BlockAlienStone").func_149658_d(mod + "BlockAlienStone");
        BlockAlienCobbleStone = new BlockAlienCobbleStone().func_149663_c("BlockAlienCobbleStone").func_149658_d(mod + "BlockAlienCobbleStone");
        BlockNamekDragonBallStone = new DragonBlockNS01Block().func_149663_c("BlockNamekDragonBallStone").func_149658_d(mod + "BlockNamekDragonBallStone");
        BlockNamekDragonBall = new DragonBlockNamek01Block().func_149663_c("BlockNamekDragonBall").func_149658_d(mod + "BlockNamekDragonBall");
        BlockDragonBallStone = new DragonBlockS01Block().func_149663_c("BlockDragonBallStone").func_149658_d(mod + "BlockDragonBallStone");
        BlockDragonBall = new DragonBlock01Block().func_149663_c("BlockDragonBall").func_149658_d(mod + "BlockDragonBall");
        BlockOreWrenai = new BlockOreWrenai().func_149663_c("BlockOreWrenai").func_149658_d(mod + "BlockOreWrenai");
        BlockNamekGrass = new BlockNamekGrass().func_149711_c(0.6f).func_149672_a(Block.field_149779_h).func_149663_c("BlockNamekGrass").func_149658_d(mod + "BlockNamekGrass");
        BlockNamekDirt = new BlockNamekDirt().func_149711_c(0.5f).func_149672_a(Block.field_149767_g).func_149663_c("BlockNamekDirt").func_149658_d(mod + "BlockNamekDirt");
        BlockNamekStone = new BlockNamekStone().func_149711_c(1.5f).func_149672_a(Block.field_149769_e).func_149663_c("BlockNamekStone").func_149658_d(mod + "BlockNamekStone");
        BlockNamekLog = new BlockNamekLog().func_149711_c(2.0f).func_149672_a(Block.field_149766_f).func_149663_c("BlockNamekLog").func_149658_d(mod + "BlockNamekLog");
        BlockNamekLeaves = new BlockNamekLeaves(52).func_149711_c(0.2f).func_149713_g(1).func_149672_a(Block.field_149779_h).func_149663_c("BlockNamekLeaves").func_149658_d(mod + "BlockNamekLeaves");
        BlockNamekSapling = (BlockDBCSaplings)new BlockDBCSaplings().func_149711_c(0.0f).func_149672_a(Block.field_149779_h).func_149663_c("Sapling");
        BlockOreJJay = new BlockOreDBC(Item.ToolMaterial.WOOD.func_77996_d()).func_149663_c("JJayore").func_149658_d(mod + "JJayore");
        BlockOreDlog = new BlockOreDBC(Item.ToolMaterial.STONE.func_77996_d()).func_149663_c("Dlogore").func_149658_d(mod + "Dlogore");
        BlockOreLehnori = new BlockOreDBC(Item.ToolMaterial.STONE.func_77996_d()).func_149663_c("Lehnoriore").func_149658_d(mod + "Lehnoriore");
        BlockOreDnomaid = new BlockOreDBC(Item.ToolMaterial.IRON.func_77996_d()).func_149663_c("Dnomaidore").func_149658_d(mod + "Dnomaidore");
        for (i = 0; i < BlockOW.length; ++i) {
            BlocksDBC.BlockOW[i] = BlockOWtyps[i].func_149663_c(BlockOWnams[i]).func_149658_d(mod + BlockOWnams[i]);
        }
        BlockYellowCloud = new BlockYellowCloud().func_149711_c(5.0f).func_149672_a(Block.field_149779_h).func_149663_c("BlockYellowCloud").func_149658_d(mod + "BlockYellowCloud");
        BlockTCPort = new BlockTCPort().func_149663_c("BlockTCPort").func_149658_d(mod + "BlockTCPort");
        BlockTCFloor = new BlockTCFloor().func_149711_c(50.0f).func_149752_b(2000.0f).func_149672_a(Block.field_149769_e).func_149663_c("BlockTCFloor").func_149658_d(mod + "BlockTCFloor");
        BlockHealingPods = new BlockHealingPods(medicalLiquid, Material.field_151586_h).func_149663_c("BlockHealingPods");
        BlockHealingPodDoor = new MedPodDoor1Block().func_149711_c(50.0f).func_149752_b(2000.0f).func_149663_c("BlockHealingPodDoor");
        BlockCropMedMoss = new BlockCropMedMoss().func_149663_c("BlockCropMedMoss");
        BlockWildMedMoss = new BlockWildMedMoss().func_149663_c("BlockWildMedMoss");
        SpacePod01Block = new SpacePod01Block().func_149711_c(0.2f).func_149713_g(1).func_149672_a(Block.field_149779_h).func_149663_c("SpacePod01Block").func_149658_d(mod + "SpacePod01Block");
        SaibaiHatched = new SaibaiHatchedBlock().func_149711_c(0.2f).func_149713_g(5).func_149672_a(Block.field_149779_h).func_149663_c("SaibaiHatched").func_149658_d(mod + "SaibaiHatched");
        ArtGravDevBlock = new ArtGravDevBlock().func_149711_c(0.2f).func_149713_g(5).func_149672_a(Block.field_149777_j).func_149663_c("ArtGravDevBlock").func_149658_d(mod + "ArtGravDevBlock");
        ppBlock = new ppBlock().func_149672_a(Block.field_149777_j).func_149663_c("ppBlock").func_149658_d(mod + "ppBlock");
        namekian_throneBlock = new namekian_throneBlock().func_149663_c("namekian_throneBlock").func_149658_d(mod + "namekian_throneBlock").func_149647_a(mod_DragonBC.DragonBlockC);
        for (i = 0; i < BlockKachin_n; ++i) {
            BlocksDBC.BlockKachin[i] = new BlockAnyBase2(-1).func_149711_c(50.0f).func_149752_b(2000.0f).func_149663_c("katchin_block" + i).func_149658_d(mod + "katchin_block" + i);
        }
        for (i = 0; i < BlockKachiKachin_n; ++i) {
            BlocksDBC.BlockKachiKachin[i] = new BlockAnyBase2(-1).func_149711_c(-1.0f).func_149752_b(6000000.0f).func_149663_c("kachi_katchin_block" + i).func_149658_d(mod + "kachi_katchin_block" + i);
        }
    }

    public static void register() {
        int i;
        GameRegistry.registerBlock((Block)BlockTCPort, (String)BlockTCPort.func_149739_a());
        GameRegistry.registerBlock((Block)BlockTCFloor, (String)BlockTCFloor.func_149739_a());
        GameRegistry.registerBlock((Block)BlockYellowCloud, (String)BlockYellowCloud.func_149739_a());
        GameRegistry.registerBlock((Block)BlockAlienStone, (String)BlockAlienStone.func_149739_a());
        GameRegistry.registerBlock((Block)BlockAlienCobbleStone, (String)BlockAlienCobbleStone.func_149739_a());
        GameRegistry.registerBlock((Block)BlockNamekDragonBallStone, (String)BlockNamekDragonBallStone.func_149739_a());
        GameRegistry.registerBlock((Block)BlockNamekDragonBall, (String)BlockNamekDragonBall.func_149739_a());
        GameRegistry.registerBlock((Block)BlockDragonBallStone, (String)BlockDragonBallStone.func_149739_a());
        GameRegistry.registerBlock((Block)BlockDragonBall, (String)BlockDragonBall.func_149739_a());
        GameRegistry.registerBlock((Block)BlockOreWrenai, (String)BlockOreWrenai.func_149739_a());
        GameRegistry.registerBlock((Block)BlockNamekGrass, (String)BlockNamekGrass.func_149739_a());
        GameRegistry.registerBlock((Block)BlockNamekLog, (String)BlockNamekLog.func_149739_a());
        GameRegistry.registerBlock((Block)BlockNamekDirt, (String)BlockNamekDirt.func_149739_a());
        GameRegistry.registerBlock((Block)BlockNamekStone, (String)BlockNamekStone.func_149739_a());
        GameRegistry.registerBlock((Block)BlockNamekLeaves, (String)BlockNamekLeaves.func_149739_a());
        GameRegistry.registerBlock((Block)BlockNamekSapling, ItemDBCSaplingsTex.class, (String)BlockNamekSapling.func_149739_a());
        GameRegistry.registerBlock((Block)BlockOreJJay, (String)BlockOreJJay.func_149739_a());
        GameRegistry.registerBlock((Block)BlockOreDlog, (String)BlockOreDlog.func_149739_a());
        GameRegistry.registerBlock((Block)BlockOreLehnori, (String)BlockOreLehnori.func_149739_a());
        GameRegistry.registerBlock((Block)BlockOreDnomaid, (String)BlockOreDnomaid.func_149739_a());
        for (i = 0; i < BlockOW.length; ++i) {
            GameRegistry.registerBlock((Block)BlockOW[i], (String)BlockOW[i].func_149739_a());
        }
        GameRegistry.registerBlock((Block)BlockHealingPods, (String)BlockHealingPods.func_149739_a());
        GameRegistry.registerBlock((Block)BlockHealingPodDoor, (String)BlockHealingPodDoor.func_149739_a());
        GameRegistry.registerBlock((Block)SpacePod01Block, (String)SpacePod01Block.func_149739_a());
        GameRegistry.registerBlock((Block)SaibaiHatched, (String)SaibaiHatched.func_149739_a());
        GameRegistry.registerBlock((Block)ArtGravDevBlock, (String)ArtGravDevBlock.func_149739_a());
        GameRegistry.registerBlock((Block)BlockCropMedMoss, (String)BlockCropMedMoss.func_149739_a());
        GameRegistry.registerBlock((Block)BlockWildMedMoss, (String)BlockWildMedMoss.func_149739_a());
        GameRegistry.registerBlock((Block)ppBlock, (String)ppBlock.func_149739_a());
        GameRegistry.registerBlock((Block)namekian_throneBlock, (String)namekian_throneBlock.func_149739_a());
        for (i = 0; i < BlockKachin_n; ++i) {
            GameRegistry.registerBlock((Block)BlockKachin[i], (String)BlockKachin[i].func_149739_a());
        }
        for (i = 0; i < BlockKachiKachin_n; ++i) {
            GameRegistry.registerBlock((Block)BlockKachiKachin[i], (String)BlockKachiKachin[i].func_149739_a());
        }
        ModdedBlocks.init();
    }

    static {
        medicalLiquid = new Fluid("medicalliquid");
        mod = "jinryuudragonbc:tile.";
        BlockOWnams = new String[]{"JJayblock", "Dlogblock", "Lehnoriblock", "Dnomaidblock"};
        BlockOWtyps = new Block[]{new BlockAnyBase(Item.ToolMaterial.WOOD.func_77996_d()), new BlockAnyBase(Item.ToolMaterial.STONE.func_77996_d()), new BlockAnyBase(Item.ToolMaterial.STONE.func_77996_d()), new BlockAnyBase(Item.ToolMaterial.IRON.func_77996_d())};
        BlockOW = new Block[BlockOWnams.length];
        BlockKachiKachin_n = 16;
        BlockKachiKachin = new Block[BlockKachiKachin_n];
        BlockKachin_n = 3;
        BlockKachin = new Block[BlockKachin_n];
    }
}

