/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package noppes.npcs.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.blocks.tiles.TileBorder;
import noppes.npcs.constants.EnumGuiType;

public class BlockBorder
extends BlockContainer {
    public int renderId = -1;

    public BlockBorder() {
        super(Material.field_151576_e);
        this.func_149722_s();
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        if (side == 1) {
            return this.field_149761_L;
        }
        return Blocks.field_150339_S.func_149691_a(side, meta);
    }

    public boolean func_149727_a(World par1World, int i, int j, int k, EntityPlayer player, int par6, float par7, float par8, float par9) {
        ItemStack currentItem = player.field_71071_by.func_70448_g();
        if (currentItem != null && currentItem.func_77973_b() == CustomItems.wand) {
            CustomNpcs.proxy.openGui(i, j, k, EnumGuiType.Border, player);
            return true;
        }
        return false;
    }

    public void func_149689_a(World par1World, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        int l = MathHelper.func_76128_c((double)((double)(par5EntityLivingBase.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        l %= 4;
        TileBorder tile = (TileBorder)par1World.func_147438_o(x, y, z);
        TileBorder adjacent = this.getTile(par1World, x - 1, y, z);
        if (adjacent == null) {
            adjacent = this.getTile(par1World, x, y, z - 1);
        }
        if (adjacent == null) {
            adjacent = this.getTile(par1World, x, y, z + 1);
        }
        if (adjacent == null) {
            adjacent = this.getTile(par1World, x + 1, y, z);
        }
        if (adjacent != null) {
            NBTTagCompound compound = new NBTTagCompound();
            adjacent.writeExtraNBT(compound);
            tile.readExtraNBT(compound);
        }
        tile.rotation = l;
        if (par5EntityLivingBase instanceof EntityPlayer && par1World.field_72995_K) {
            CustomNpcs.proxy.openGui(x, y, z, EnumGuiType.Border, (EntityPlayer)par5EntityLivingBase);
        }
    }

    private TileBorder getTile(World world, int x, int y, int z) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileBorder) {
            return (TileBorder)tile;
        }
        return null;
    }

    public int func_149645_b() {
        return this.renderId;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileBorder();
    }
}

