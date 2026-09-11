/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package JinRyuu.DragonBC.common.Villages;

import JinRyuu.JRMCore.blocks.BlocksJRMC;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class builds
extends WorldGenerator {
    protected boolean notComplete = false;
    protected int x = 0;
    protected int y = 0;
    protected int z = 0;
    protected int r = 0;
    protected int flp = 0;
    protected boolean check = false;
    public static int SizeX = 0;
    public static int SizeZ = 0;
    public static int SizeY = 0;
    public static int MidPointX = 0;
    public static int MidPointZ = 0;
    protected Block air = Blocks.field_150350_a;
    protected Block b56 = BlocksJRMC.stoneSingleSlab2;
    protected Block b97 = Blocks.field_150344_f;
    protected Block c = BlocksJRMC.BlockColoredStone2;
    protected Block c2 = BlocksJRMC.BlockColoredStone2;
    protected Block s = BlocksJRMC.stoneSingleSlab2;
    protected Block f = BlocksJRMC.BlockFence2;
    protected Block stairCompactCobblestone = Blocks.field_150446_ar;
    protected Block stairCompactPlanks = Blocks.field_150476_ad;
    protected Block stoneSingleSlab = Blocks.field_150333_U;
    protected Block woodSingleSlab = Blocks.field_150376_bx;
    protected Block fence = Blocks.field_150422_aJ;
    protected Block planks = Blocks.field_150344_f;
    protected Block leaves = Blocks.field_150362_t;
    protected Block glass = Blocks.field_150359_w;
    protected Block wood = Blocks.field_150364_r;
    protected Block stone = Blocks.field_150348_b;
    protected Block sand = Blocks.field_150354_m;
    protected Block sandstone = Blocks.field_150322_A;
    protected Block grass = Blocks.field_150349_c;
    protected Block dirt = Blocks.field_150346_d;
    protected Block oak_stairs = Blocks.field_150476_ad;
    protected Block doorWood = Blocks.field_150466_ao;
    protected Block iron_door = Blocks.field_150454_av;
    protected Block iron_block = Blocks.field_150339_S;
    protected Block torchWood = Blocks.field_150350_a;
    protected Block glowStone = Blocks.field_150426_aN;
    protected Block bookShelf = Blocks.field_150342_X;
    protected Block stoneDoubleSlab = Blocks.field_150417_aV;
    protected Block signWall = Blocks.field_150444_as;
    protected Block blockSteel = Blocks.field_150339_S;
    protected Block ladder = Blocks.field_150468_ap;
    protected Block stoneBrick = Blocks.field_150417_aV;
    protected Block woodDoubleSlab = Blocks.field_150344_f;
    protected Block sandStone = Blocks.field_150322_A;
    protected Block b116 = Blocks.field_150457_bL;
    protected Block b121 = BlocksJRMC.BlockStairs2;
    protected Block b113 = Blocks.field_150467_bQ;
    protected World w;
    protected boolean respawn = false;

    public void b(int par2, int par3, int par4, Block par5) {
        this.b(par2, par3, par4, par5, 0);
    }

    protected void b(int par2, int par3, int par4, Block par5, int par6) {
        this.func_150516_a(this.w, par2, par3, par4, par5, par6);
    }

    public boolean getNotComplete() {
        return this.notComplete;
    }

    public void setR(int i) {
        this.r = i;
    }

    public void setFlp(int i) {
        this.flp = i;
    }

    public void setCheck(boolean b) {
        this.check = b;
    }

    protected Block[] GetValidSpawnBlocks() {
        return new Block[]{Blocks.field_150349_c};
    }

    public boolean LocationIsValidSpawn(World world, int i, int j, int k) {
        this.w = world;
        int distanceToAir = 0;
        Block checkID = world.func_147439_a(i, j, k);
        while (checkID != Blocks.field_150350_a) {
            checkID = world.func_147439_a(i, j + ++distanceToAir, k);
        }
        if (distanceToAir > 6) {
            return false;
        }
        Block blockID = world.func_147439_a(i, j += distanceToAir - 1, k);
        Block blockIDAbove = world.func_147439_a(i, j + 1, k);
        Block blockIDBelow = world.func_147439_a(i, j - 1, k);
        for (Block x : this.GetValidSpawnBlocks()) {
            if (blockIDAbove != Blocks.field_150350_a) {
                return false;
            }
            if (blockID == x) {
                return true;
            }
            if (blockID != Blocks.field_150433_aE || blockIDBelow != x) continue;
            return true;
        }
        return false;
    }

    public boolean func_76484_a(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_) {
        return false;
    }

    public void setWorld(World world) {
        this.w = world;
    }

    public boolean generateBuilding(World world, Random random, int i, int j, int k) {
        return false;
    }

    public void setRespawn(boolean b) {
        this.respawn = b;
    }
}

