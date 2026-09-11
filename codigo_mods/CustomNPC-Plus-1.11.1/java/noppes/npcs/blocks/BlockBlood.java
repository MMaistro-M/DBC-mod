/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package noppes.npcs.blocks;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;

public class BlockBlood
extends Block {
    @SideOnly(value=Side.CLIENT)
    private IIcon blockIcon2;
    @SideOnly(value=Side.CLIENT)
    private IIcon blockIcon3;
    private final int renderId = RenderingRegistry.getNextAvailableRenderId();

    public BlockBlood() {
        super(Material.field_151576_e);
        this.func_149722_s();
        this.func_149647_a(CustomItems.tabMisc);
        this.func_149676_a(0.01f, 0.01f, 0.01f, 0.99f, 0.99f, 0.99f);
        this.func_149715_a(0.08f);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int metadata) {
        if ((metadata += side) % 3 == 1) {
            return this.blockIcon2;
        }
        if (metadata % 3 == 2) {
            return this.blockIcon3;
        }
        return this.field_149761_L;
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        return null;
    }

    public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4) {
        return AxisAlignedBB.func_72330_a((double)par2, (double)par3, (double)par4, (double)par2, (double)par3, (double)par4);
    }

    public boolean func_149686_d() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a(this.func_149641_N());
        this.blockIcon2 = par1IconRegister.func_94245_a(this.func_149641_N() + "2");
        this.blockIcon3 = par1IconRegister.func_94245_a(this.func_149641_N() + "3");
    }

    public boolean func_149646_a(IBlockAccess world, int par2, int par3, int par4, int par5) {
        Block block = world.func_147439_a(par2, par3, par4);
        return block != Blocks.field_150350_a && block.func_149686_d();
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149701_w() {
        return 1;
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack item) {
        int var6 = MathHelper.func_76128_c((double)((double)(par5EntityLiving.field_70177_z / 90.0f) + 0.5)) & 3;
        par1World.func_72921_c(par2, par3, par4, var6, 2);
    }

    public int func_149645_b() {
        return this.renderId;
    }
}

