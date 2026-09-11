/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLeavesBase
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IShearable
 */
package JinRyuu.DragonBC.common.Blocks;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.mod_DragonBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockNamekLeaves
extends BlockLeavesBase
implements IShearable {
    int[] adjacentTreeBlocks;

    public String getTextureFile() {
        return "jinryuudragonbc:";
    }

    public BlockNamekLeaves(int par2) {
        super(Material.field_151584_j, true);
        this.func_149675_a(true);
        this.func_149647_a(mod_DragonBC.DragonBlockC);
    }

    public void addCreativeItems(ArrayList itemList) {
        itemList.add(new ItemStack((Block)this, 1));
    }

    public int func_149701_w() {
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a("jinryuudragonbc:" + this.func_149739_a());
    }

    public int func_149635_D() {
        double d0 = 0.5;
        double d1 = 1.0;
        return 0xFFFFFF;
    }

    public int func_149741_i(int p_149741_1_) {
        return 0xFFFFFF;
    }

    public void onBlockRemoval(World par1World, int par2, int par3, int par4) {
        int var5 = 1;
        int var6 = var5 + 1;
        if (par1World.func_72904_c(par2 - var6, par3 - var6, par4 - var6, par2 + var6, par3 + var6, par4 + var6)) {
            for (int var7 = -var5; var7 <= var5; ++var7) {
                for (int var8 = -var5; var8 <= var5; ++var8) {
                    for (int var9 = -var5; var9 <= var5; ++var9) {
                        Block var10 = par1World.func_147439_a(par2 + var7, par3 + var8, par4 + var9);
                        if (var10 == null) continue;
                        var10.beginLeavesDecay(par1World, par2 + var7, par3 + var8, par4 + var9);
                    }
                }
            }
        }
    }

    public int func_149745_a(Random p_149745_1_) {
        return p_149745_1_.nextInt(20) == 0 ? 1 : 0;
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.func_150898_a((Block)BlocksDBC.BlockNamekSapling);
    }

    public void func_149636_a(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
        super.func_149636_a(par1World, par2EntityPlayer, par3, par4, par5, par6);
    }

    public int func_149692_a(int par1) {
        return par1 & 3;
    }

    public void func_149724_b(World par1World, int par2, int par3, int par4, Entity par5Entity) {
        super.func_149724_b(par1World, par2, par3, par4, par5Entity);
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack((Block)this, 1, world.func_72805_g(x, y, z) & 3));
        return ret;
    }
}

