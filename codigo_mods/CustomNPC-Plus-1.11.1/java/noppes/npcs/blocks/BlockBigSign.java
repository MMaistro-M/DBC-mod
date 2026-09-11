/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package noppes.npcs.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.blocks.tiles.TileBigSign;
import noppes.npcs.blocks.tiles.TileVariant;
import noppes.npcs.constants.EnumGuiType;

public class BlockBigSign
extends BlockContainer {
    public int renderId = -1;

    public BlockBigSign() {
        super(Material.field_151575_d);
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return null;
    }

    public boolean func_149727_a(World par1World, int i, int j, int k, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (par1World.field_72995_K) {
            return false;
        }
        ItemStack currentItem = player.field_71071_by.func_70448_g();
        if (currentItem != null && currentItem.func_77973_b() == CustomItems.wand && CustomNpcsPermissions.hasPermission(player, CustomNpcsPermissions.EDIT_BIGSIGN)) {
            TileBigSign tile = (TileBigSign)par1World.func_147438_o(i, j, k);
            tile.canEdit = true;
            NoppesUtilServer.sendOpenGui(player, EnumGuiType.BigSign, null, i, j, k);
            return true;
        }
        return false;
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        int l = MathHelper.func_76128_c((double)((double)(par5EntityLivingBase.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        TileBigSign tile = (TileBigSign)par1World.func_147438_o(par2, par3, par4);
        tile.rotation = l %= 4;
        par1World.func_72921_c(par2, par3, par4, par6ItemStack.func_77960_j(), 2);
        if (par5EntityLivingBase instanceof EntityPlayer && par1World.field_72995_K) {
            CustomNpcs.proxy.openGui(par2, par3, par4, EnumGuiType.BigSign, (EntityPlayer)par5EntityLivingBase);
        }
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        TileEntity tileentity = world.func_147438_o(x, y, z);
        if (!(tileentity instanceof TileVariant)) {
            super.func_149719_a(world, x, y, z);
            return;
        }
        TileVariant tile = (TileVariant)tileentity;
        int meta = tile.func_145832_p();
        float xStart = 0.0f;
        float zStart = 0.0f;
        float xEnd = 1.0f;
        float zEnd = 1.0f;
        if (tile.rotation == 0) {
            zStart = 0.87f;
        } else if (tile.rotation == 2) {
            zEnd = 0.13f;
        } else if (tile.rotation == 3) {
            xStart = 0.87f;
        } else if (tile.rotation == 1) {
            xEnd = 0.13f;
        }
        this.func_149676_a(xStart, 0.0f, zStart, xEnd, 1.0f, zEnd);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return this.renderId;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int meta) {
        return Blocks.field_150344_f.func_149691_a(p_149691_1_, meta);
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileBigSign();
    }
}

