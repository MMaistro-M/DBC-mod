/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.material.Material
 */
package JinRyuu.DragonBC.common.Blocks.m;

import JinRyuu.DragonBC.common.Blocks.m.LeafyBlock;
import JinRyuu.DragonBC.common.Blocks.m.LogBlock;
import JinRyuu.DragonBC.common.Blocks.m.MapleLeaf;
import JinRyuu.DragonBC.common.Blocks.m.MyFenceGates;
import JinRyuu.DragonBC.common.Blocks.m.MyMahagonyFence;
import JinRyuu.DragonBC.common.Blocks.m.MyMapleFence;
import JinRyuu.DragonBC.common.Blocks.m.MyNormalBlocks;
import JinRyuu.DragonBC.common.Blocks.m.MyPaperWallMahagony;
import JinRyuu.DragonBC.common.Blocks.m.MyPaperWallSakura;
import JinRyuu.DragonBC.common.Blocks.m.MySakuraFence;
import JinRyuu.DragonBC.common.Blocks.m.MySapling;
import JinRyuu.DragonBC.common.Blocks.m.MySlabs;
import JinRyuu.DragonBC.common.Blocks.m.MyWoodPlanks;
import JinRyuu.DragonBC.common.Blocks.m.MyWoodStairs;
import JinRyuu.DragonBC.common.Items.m.MapleLeafItem;
import JinRyuu.DragonBC.common.Items.m.WoodLeafItem;
import JinRyuu.DragonBC.common.Items.m.WoodLogItem;
import JinRyuu.DragonBC.common.Items.m.WoodPaperItem;
import JinRyuu.DragonBC.common.Items.m.WoodPlanksItem;
import JinRyuu.DragonBC.common.Items.m.WoodSaplingItem;
import JinRyuu.DragonBC.common.Items.m.WoodSlabItem;
import JinRyuu.DragonBC.common.mod_DragonBC;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

public final class ModdedBlocks {
    public static Block SakuraStone;
    public static Block SakuraPlank;
    public static Block SakuraLogs;
    public static Block SakuraLeaves;
    public static Block SakuraSaplings;
    public static Block SakuraFence;
    public static Block MahagonyFence;
    public static Block MapleFence;
    public static Block SakuraStairs;
    public static Block MahagonyStairs;
    public static Block MapleStairs;
    public static Block MapleLeafs;
    public static BlockSlab SakuraSlabsSingle;
    public static BlockSlab SakuraSlabsDouble;
    public static Block PaperWallMahagony;
    public static Block PaperWallSakura;
    public static Block SakFenc;
    public static Block MahFenc;
    public static Block MapFenc;

    public static final void init() {
        SakuraStone = new MyNormalBlocks("Sakura_Block", Material.field_151576_e);
        GameRegistry.registerBlock((Block)SakuraStone, (String)"Sakura_Block");
        PaperWallMahagony = new MyPaperWallMahagony("Mahagony", Material.field_151575_d).func_149663_c("MahagonyPaperWall");
        PaperWallSakura = new MyPaperWallSakura("Sakura", Material.field_151575_d).func_149663_c("SakuraPaperWall");
        GameRegistry.registerBlock((Block)PaperWallSakura, WoodPaperItem.class, (String)PaperWallSakura.func_149739_a().substring(5));
        GameRegistry.registerBlock((Block)PaperWallMahagony, WoodPaperItem.class, (String)PaperWallMahagony.func_149739_a().substring(5));
        SakuraPlank = new MyWoodPlanks().func_149663_c("Planks");
        SakFenc = new MyFenceGates(0).func_149663_c("SakFencGate");
        MahFenc = new MyFenceGates(1).func_149663_c("MahFencGate");
        MapFenc = new MyFenceGates(2).func_149663_c("MapFencGate");
        SakuraSlabsSingle = new MySlabs(false);
        SakuraSlabsDouble = new MySlabs(true);
        SakuraStairs = new MyWoodStairs(SakuraStairs, 0).func_149663_c("SakuraStairs").func_149647_a(mod_DragonBC.DragonBlockC);
        MahagonyStairs = new MyWoodStairs(MahagonyStairs, 1).func_149663_c("MahagonyStairs").func_149647_a(mod_DragonBC.DragonBlockC);
        MapleStairs = new MyWoodStairs(MapleStairs, 2).func_149663_c("MapleStairs").func_149647_a(mod_DragonBC.DragonBlockC);
        SakuraFence = new MySakuraFence("SakuraFence", Material.field_151575_d).func_149752_b(4.0f).func_149711_c(3.0f).func_149647_a(mod_DragonBC.DragonBlockC);
        MahagonyFence = new MyMahagonyFence("MahagonyFence", Material.field_151575_d).func_149752_b(5.0f).func_149711_c(4.0f).func_149647_a(mod_DragonBC.DragonBlockC);
        MapleFence = new MyMapleFence("MapleFence", Material.field_151575_d).func_149752_b(5.0f).func_149711_c(4.0f).func_149647_a(mod_DragonBC.DragonBlockC);
        SakuraLogs = new LogBlock().func_149663_c("Log").func_149647_a(mod_DragonBC.DragonBlockC);
        SakuraLeaves = new LeafyBlock().func_149663_c("Leaf").func_149713_g(1).func_149647_a(mod_DragonBC.DragonBlockC);
        SakuraSaplings = new MySapling().func_149663_c("Sapling").func_149647_a(mod_DragonBC.DragonBlockC);
        MapleLeafs = new MapleLeaf().func_149663_c("Maples").func_149713_g(1).func_149647_a(mod_DragonBC.DragonBlockC);
        GameRegistry.registerBlock((Block)MapleLeafs, MapleLeafItem.class, (String)MapleLeafs.func_149739_a().substring(5));
        GameRegistry.registerBlock((Block)SakuraPlank, WoodPlanksItem.class, (String)SakuraPlank.func_149739_a().substring(5));
        GameRegistry.registerBlock((Block)SakuraStairs, (String)"SakuraStairs");
        GameRegistry.registerBlock((Block)MahagonyStairs, (String)"MahagonyStairs");
        GameRegistry.registerBlock((Block)MapleStairs, (String)"MapleStairs");
        GameRegistry.registerBlock((Block)SakuraSlabsSingle, WoodSlabItem.class, (String)SakuraSlabsSingle.func_149739_a().substring(5));
        GameRegistry.registerBlock((Block)SakuraSlabsDouble, WoodSlabItem.class, (String)"DoubleSlab");
        GameRegistry.registerBlock((Block)SakuraFence, (String)"SakuraFence");
        GameRegistry.registerBlock((Block)MahagonyFence, (String)"MahagonyFence");
        GameRegistry.registerBlock((Block)MapleFence, (String)"MapleFence");
        GameRegistry.registerBlock((Block)SakuraLogs, WoodLogItem.class, (String)SakuraLogs.func_149739_a().substring(5));
        GameRegistry.registerBlock((Block)SakuraLeaves, WoodLeafItem.class, (String)SakuraLeaves.func_149739_a().substring(5));
        GameRegistry.registerBlock((Block)SakuraSaplings, WoodSaplingItem.class, (String)SakuraSaplings.func_149739_a().substring(5));
        GameRegistry.registerBlock((Block)SakFenc, (String)"SakuraFenceGate");
        GameRegistry.registerBlock((Block)MahFenc, (String)"MahagonyFenceGate");
        GameRegistry.registerBlock((Block)MapFenc, (String)"MapleFenceGate");
    }
}

