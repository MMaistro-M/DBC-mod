/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockFence
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package JinRyuu.JRMCore.blocks;

import JinRyuu.JRMCore.mod_JRMCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockJRMCFence
extends BlockFence {
    private String tex;

    public BlockJRMCFence(String tex) {
        super("", Material.field_151573_f);
        this.func_149722_s();
        this.func_149752_b(6000000.0f);
        this.func_149647_a(mod_JRMCore.JRMCore);
        this.tex = tex;
    }

    public void func_149743_a(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
        boolean flag = this.func_149826_e((IBlockAccess)par1World, par2, par3, par4 - 1);
        boolean flag1 = this.func_149826_e((IBlockAccess)par1World, par2, par3, par4 + 1);
        boolean flag2 = this.func_149826_e((IBlockAccess)par1World, par2 - 1, par3, par4);
        boolean flag3 = this.func_149826_e((IBlockAccess)par1World, par2 + 1, par3, par4);
        float f = 0.375f;
        float f1 = 0.625f;
        float f2 = 0.375f;
        float f3 = 0.625f;
        if (flag) {
            f2 = 0.0f;
        }
        if (flag1) {
            f3 = 1.0f;
        }
        if (flag || flag1) {
            this.func_149676_a(f, 0.0f, f2, f1, 1.5f, f3);
            super.func_149743_a(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        }
        f2 = 0.375f;
        f3 = 0.625f;
        if (flag2) {
            f = 0.0f;
        }
        if (flag3) {
            f1 = 1.0f;
        }
        if (flag2 || flag3 || !flag && !flag1) {
            this.func_149676_a(f, 0.0f, f2, f1, 1.5f, f3);
            super.func_149743_a(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        }
        if (flag) {
            f2 = 0.0f;
        }
        if (flag1) {
            f3 = 1.0f;
        }
        this.func_149676_a(f, 0.0f, f2, f1, 1.0f, f3);
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        boolean flag = this.func_149826_e(par1IBlockAccess, par2, par3, par4 - 1);
        boolean flag1 = this.func_149826_e(par1IBlockAccess, par2, par3, par4 + 1);
        boolean flag2 = this.func_149826_e(par1IBlockAccess, par2 - 1, par3, par4);
        boolean flag3 = this.func_149826_e(par1IBlockAccess, par2 + 1, par3, par4);
        float f = 0.375f;
        float f1 = 0.625f;
        float f2 = 0.375f;
        float f3 = 0.625f;
        if (flag) {
            f2 = 0.0f;
        }
        if (flag1) {
            f3 = 1.0f;
        }
        if (flag2) {
            f = 0.0f;
        }
        if (flag3) {
            f1 = 1.0f;
        }
        this.func_149676_a(f, 0.0f, f2, f1, 1.0f, f3);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149655_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        return false;
    }

    public int func_149645_b() {
        return 11;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a("jinryuumodscore:" + this.tex);
    }

    public int func_149745_a(Random par1Random) {
        return 1;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149701_w() {
        return 0;
    }
}

