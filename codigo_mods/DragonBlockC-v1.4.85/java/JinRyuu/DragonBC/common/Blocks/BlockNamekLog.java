/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Blocks;

import JinRyuu.DragonBC.common.mod_DragonBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockNamekLog
extends Block {
    @SideOnly(value=Side.CLIENT)
    private IIcon iconGrassTop;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconSnowSide;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconGrassSideOverlay;

    public String getTextureFile() {
        return "jinryuudragonbc:";
    }

    public void addCreativeItems(ArrayList itemList) {
        itemList.add(new ItemStack((Block)this, 1));
    }

    public BlockNamekLog() {
        super(Material.field_151575_d);
        this.func_149647_a(mod_DragonBC.DragonBlockC);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return par1 == 1 ? this.iconGrassTop : (par1 == 0 ? this.iconGrassTop : this.field_149761_L);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149673_e(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        if (par5 == 1) {
            return this.iconGrassTop;
        }
        if (par5 == 0) {
            return this.iconGrassTop;
        }
        return this.field_149761_L;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a("jinryuudragonbc:" + this.func_149739_a() + "Side");
        this.iconGrassTop = par1IconRegister.func_94245_a("jinryuudragonbc:" + this.func_149739_a() + "Top");
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        float var9 = 0.375f;
        float var10 = 0.625f;
        float var11 = 0.375f;
        float var12 = 0.625f;
        this.func_149676_a(var9, 0.0f, var11, var10, 1.0f, var12);
    }

    public int func_149745_a(Random par1Random) {
        return 1;
    }

    public void func_149636_a(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
        super.func_149636_a(par1World, par2EntityPlayer, par3, par4, par5, par6);
    }

    public void onBlockRemoval(World par1World, int par2, int par3, int par4) {
        int var5 = 4;
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

    public boolean func_149655_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        return false;
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public boolean canSustainLeaves(World world, int x, int y, int z) {
        return true;
    }

    public boolean isWood(World world, int x, int y, int z) {
        return true;
    }
}

