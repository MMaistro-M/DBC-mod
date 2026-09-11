/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.Item
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Blocks;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.Blocks.DBCMaterial;
import JinRyuu.DragonBC.common.mod_DragonBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockDragonBallStone
extends Block {
    private static int tickDragonBall = 0;

    public BlockDragonBallStone() {
        super(DBCMaterial.dragonblock);
        this.func_149675_a(true);
        this.func_149711_c(1.0f);
        float var4 = 0.2f;
        this.func_149676_a(0.5f - var4, 0.2f - var4, 0.5f - var4, 0.5f + var4, var4 + 0.2f, 0.5f + var4);
        this.func_149647_a(mod_DragonBC.DragonBlockC);
    }

    public Item func_149650_a(int metadata, Random random, int fortune) {
        return Item.func_150898_a((Block)BlocksDBC.BlockDragonBall);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a("jinryuudragonbc:" + this.func_149739_a());
    }

    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (!par1World.field_72995_K && par5Random.nextInt(5) == 0) {
            par1World.func_147449_b(par2, par3, par4, BlocksDBC.BlockDragonBall);
        }
    }

    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
        return null;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return 0;
    }

    public boolean func_149662_c() {
        return false;
    }
}

