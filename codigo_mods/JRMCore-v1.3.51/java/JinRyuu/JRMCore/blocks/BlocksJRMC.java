/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 */
package JinRyuu.JRMCore.blocks;

import JinRyuu.JRMCore.blocks.BlockBarrier;
import JinRyuu.JRMCore.blocks.BlockBorder;
import JinRyuu.JRMCore.blocks.BlockColorablePattern;
import JinRyuu.JRMCore.blocks.BlockColoredStone;
import JinRyuu.JRMCore.blocks.BlockJRMCFence;
import JinRyuu.JRMCore.blocks.BlockJRMCHalfSlab;
import JinRyuu.JRMCore.blocks.BlockJRMCStairs;
import JinRyuu.JRMCore.blocks.BlockJRMCStep;
import JinRyuu.JRMCore.blocks.ItemBlockColoredStone;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class BlocksJRMC {
    public static BlockJRMCHalfSlab stoneSingleSlab;
    public static BlockJRMCHalfSlab stoneDoubleSlab;
    public static Block BlockColoredStone;
    public static Block BlockFence;
    public static Block BlockStairs;
    public static Block BlockColoredStone2;
    public static BlockJRMCHalfSlab stoneSingleSlab2;
    public static Block BlockFence2;
    public static Block BlockStairs2;
    private static final int BlockColorableStonePattern_number = 5;
    public static BlockJRMCHalfSlab[][] BlockColorableStonePattern_slab;
    public static Block[][] BlockColorableStonePattern_fence;
    public static Block[][] BlockColorableStonePattern_stairs;
    public static Block[][] BlockColorableStonePattern;
    public static String mod;
    public static Block BlockBarrier;
    public static Block BlockBorder;

    public static void init() {
        BlockColoredStone = new BlockColoredStone("BlockColoredStone").func_149672_a(Block.field_149769_e).func_149663_c("BlockColoredStone").func_149658_d(mod + "BlockColoredStone");
        stoneSingleSlab = (BlockJRMCHalfSlab)new BlockJRMCStep(false, "BlockColoredStone_8", "BlockColoredStoneS_8").func_149672_a(Block.field_149769_e).func_149663_c("stoneSingleSlab").func_149658_d(mod + "stoneSingleSlab");
        BlockFence = new BlockJRMCFence("BlockColoredStone_0").func_149672_a(Block.field_149769_e).func_149663_c("BlockFence").func_149658_d(mod + "BlockFence");
        BlockStairs = new BlockJRMCStairs(BlockColoredStone, 0, "BlockColoredStone_0").func_149672_a(Block.field_149769_e).func_149663_c("BlockStairs").func_149658_d(mod + "BlockStairs");
        BlockColoredStone2 = new BlockColoredStone("BlockColoredStone2").func_149672_a(Block.field_149769_e).func_149663_c("BlockColoredStone2").func_149658_d(mod + "BlockColoredStone2");
        stoneSingleSlab2 = (BlockJRMCHalfSlab)new BlockJRMCStep(false, "BlockColoredStone2_8", "BlockColoredStone2S_8").func_149672_a(Block.field_149769_e).func_149663_c("stoneSingleSlab2").func_149658_d(mod + "stoneSingleSlab2");
        BlockFence2 = new BlockJRMCFence("BlockColoredStone2_0").func_149672_a(Block.field_149769_e).func_149663_c("BlockFence2").func_149658_d(mod + "BlockFence2");
        BlockStairs2 = new BlockJRMCStairs(BlockColoredStone2, 0, "BlockColoredStone2_0").func_149672_a(Block.field_149769_e).func_149663_c("BlockStairs2").func_149658_d(mod + "BlockStairs2");
        for (int i = 0; i < 5; ++i) {
            int j;
            String name = "Colorable Stone Pattern" + (i + 1);
            for (j = 0; j < 16; ++j) {
                name = "Colorable Stone Pattern" + (i + 1) + "_" + j;
                BlocksJRMC.BlockColorableStonePattern[i][j] = new BlockColorablePattern(name).func_149672_a(Block.field_149769_e).func_149663_c(name).func_149658_d(mod + name);
                BlocksJRMC.BlockColorableStonePattern_slab[i][j] = (BlockJRMCHalfSlab)new BlockJRMCStep(false, name, name).func_149672_a(Block.field_149769_e).func_149663_c(name + "Slab").func_149658_d(mod + name);
            }
            for (j = 0; j < 16; ++j) {
                name = "Colorable Stone Pattern" + (i + 1) + "_" + j;
                BlocksJRMC.BlockColorableStonePattern[i][j] = new BlockColorablePattern(name).func_149672_a(Block.field_149769_e).func_149663_c(name).func_149658_d(mod + name);
                BlocksJRMC.BlockColorableStonePattern_fence[i][j] = new BlockJRMCFence(name).func_149672_a(Block.field_149769_e).func_149663_c(name + "Fence").func_149658_d(mod + name);
            }
            for (j = 0; j < 16; ++j) {
                name = "Colorable Stone Pattern" + (i + 1) + "_" + j;
                BlocksJRMC.BlockColorableStonePattern[i][j] = new BlockColorablePattern(name).func_149672_a(Block.field_149769_e).func_149663_c(name).func_149658_d(mod + name);
                BlocksJRMC.BlockColorableStonePattern_stairs[i][j] = new BlockJRMCStairs(BlockColorableStonePattern[i][j], 0, name).func_149672_a(Block.field_149769_e).func_149663_c(name + "Stairs").func_149658_d(mod + name);
            }
        }
        BlockBarrier = new BlockBarrier().func_149722_s().func_149672_a(Block.field_149769_e).func_149663_c("BlockBarrier").func_149658_d(mod + "BlockBarrier");
        BlockBorder = new BlockBorder().func_149722_s().func_149672_a(Block.field_149769_e).func_149663_c("BlockBorder").func_149658_d(mod + "BlockBorder");
    }

    public static void register() {
        GameRegistry.registerBlock((Block)BlockColoredStone, ItemBlockColoredStone.class, (String)BlockColoredStone.func_149739_a());
        GameRegistry.registerBlock((Block)stoneSingleSlab, (String)stoneSingleSlab.func_149739_a());
        GameRegistry.registerBlock((Block)BlockFence, (String)BlockFence.func_149739_a());
        GameRegistry.registerBlock((Block)BlockStairs, (String)BlockStairs.func_149739_a());
        GameRegistry.registerBlock((Block)BlockColoredStone2, ItemBlockColoredStone.class, (String)BlockColoredStone2.func_149739_a());
        GameRegistry.registerBlock((Block)stoneSingleSlab2, (String)stoneSingleSlab2.func_149739_a());
        GameRegistry.registerBlock((Block)BlockFence2, (String)BlockFence2.func_149739_a());
        GameRegistry.registerBlock((Block)BlockStairs2, (String)BlockStairs2.func_149739_a());
        for (int i = 0; i < 5; ++i) {
            int j;
            for (j = 0; j < 16; ++j) {
                GameRegistry.registerBlock((Block)BlockColorableStonePattern[i][j], (String)BlockColorableStonePattern[i][j].func_149739_a());
            }
            for (j = 0; j < 16; ++j) {
                GameRegistry.registerBlock((Block)BlockColorableStonePattern_slab[i][j], (String)BlockColorableStonePattern_slab[i][j].func_149739_a());
            }
            for (j = 0; j < 16; ++j) {
                GameRegistry.registerBlock((Block)BlockColorableStonePattern_fence[i][j], (String)BlockColorableStonePattern_fence[i][j].func_149739_a());
            }
            for (j = 0; j < 16; ++j) {
                GameRegistry.registerBlock((Block)BlockColorableStonePattern_stairs[i][j], (String)BlockColorableStonePattern_stairs[i][j].func_149739_a());
            }
        }
        GameRegistry.registerBlock((Block)BlockBarrier, (String)BlockBarrier.func_149739_a());
        GameRegistry.registerBlock((Block)BlockBorder, (String)BlockBorder.func_149739_a());
    }

    static {
        BlockColorableStonePattern_slab = new BlockJRMCHalfSlab[5][16];
        BlockColorableStonePattern_fence = new Block[5][16];
        BlockColorableStonePattern_stairs = new Block[5][16];
        BlockColorableStonePattern = new Block[5][16];
        mod = "jinryuumodscore:tile.";
    }
}

