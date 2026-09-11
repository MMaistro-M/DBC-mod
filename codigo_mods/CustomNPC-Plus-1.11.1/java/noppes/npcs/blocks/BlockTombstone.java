/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
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
import java.util.List;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
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
import noppes.npcs.blocks.tiles.TileTombstone;
import noppes.npcs.blocks.tiles.TileVariant;
import noppes.npcs.constants.EnumGuiType;

public class BlockTombstone
extends BlockContainer {
    public int renderId = -1;

    public BlockTombstone() {
        super(Material.field_151576_e);
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public boolean func_149727_a(World par1World, int i, int j, int k, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (par1World.field_72995_K) {
            return false;
        }
        ItemStack currentItem = player.field_71071_by.func_70448_g();
        if (currentItem != null && currentItem.func_77973_b() == CustomItems.wand && CustomNpcsPermissions.hasPermission(player, CustomNpcsPermissions.EDIT_TOMBSTONE)) {
            TileBigSign tile = (TileBigSign)par1World.func_147438_o(i, j, k);
            if (tile.func_145832_p() >= 2) {
                return false;
            }
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
        if (par5EntityLivingBase instanceof EntityPlayer && par1World.field_72995_K && par6ItemStack.func_77960_j() < 2) {
            CustomNpcs.proxy.openGui(par2, par3, par4, EnumGuiType.BigSign, (EntityPlayer)par5EntityLivingBase);
        }
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        this.func_149719_a((IBlockAccess)world, x, y, z);
        return super.func_149668_a(world, x, y, z);
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        TileEntity tileentity = world.func_147438_o(x, y, z);
        if (!(tileentity instanceof TileVariant)) {
            super.func_149719_a(world, x, y, z);
            return;
        }
        TileVariant tile = (TileVariant)tileentity;
        if (tile.rotation % 2 == 0) {
            this.func_149676_a(0.0f, 0.0f, 0.3f, 1.0f, 1.0f, 0.7f);
        } else {
            this.func_149676_a(0.3f, 0.0f, 0.0f, 0.7f, 1.0f, 1.0f);
        }
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
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
        return Blocks.field_150348_b.func_149691_a(p_149691_1_, meta);
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileTombstone();
    }
}

