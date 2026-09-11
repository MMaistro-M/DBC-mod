/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockColored
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package noppes.npcs.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.blocks.tiles.TileColorable;
import noppes.npcs.blocks.tiles.TileWallBanner;
import noppes.npcs.items.ItemNpcTool;

public class BlockWallBanner
extends BlockContainer {
    public int renderId = -1;

    public BlockWallBanner() {
        super(Material.field_151576_e);
    }

    public boolean func_149727_a(World par1World, int i, int j, int k, EntityPlayer player, int par6, float par7, float par8, float par9) {
        ItemStack item = player.field_71071_by.func_70448_g();
        if (item == null) {
            return false;
        }
        if (item.func_77973_b() != null && item.func_77973_b() instanceof ItemNpcTool && item.func_77960_j() == 0) {
            return false;
        }
        TileWallBanner tile = (TileWallBanner)par1World.func_147438_o(i, j, k);
        if (tile.canEdit()) {
            return true;
        }
        if (TileColorable.allowColorChange(item) != TileColorable.ColorChangeType.DYE) {
            return false;
        }
        int color = ColorUtil.colorTableInts[BlockColored.func_150031_c((int)item.func_77960_j())];
        if (tile.color != color) {
            NoppesUtilServer.consumeItemStack(1, player);
            tile.color = color;
            par1World.func_147471_g(i, j, k);
        }
        return true;
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        int l = MathHelper.func_76128_c((double)((double)(par5EntityLivingBase.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        TileWallBanner tile = (TileWallBanner)par1World.func_147438_o(par2, par3, par4);
        tile.rotation = l %= 4;
        tile.color = ColorUtil.colorTableInts[15 - par6ItemStack.func_77960_j()];
        if (par6ItemStack.func_77942_o() && par6ItemStack.func_77978_p().func_74764_b(ItemNpcTool.BRUSH_COLOR_TAG)) {
            tile.color = par6ItemStack.func_77978_p().func_74762_e(ItemNpcTool.BRUSH_COLOR_TAG);
        }
        tile.time = System.currentTimeMillis();
        par1World.func_72921_c(par2, par3, par4, par6ItemStack.func_77960_j(), 2);
        if (par5EntityLivingBase instanceof EntityPlayer && par1World.field_72995_K) {
            ((EntityPlayer)par5EntityLivingBase).func_146105_b((IChatComponent)new ChatComponentTranslation("availability.editIcon", new Object[0]));
        }
    }

    public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return null;
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
    }

    public int func_149692_a(int par1) {
        return par1;
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
        if ((meta %= 7) == 1) {
            return Blocks.field_150348_b.func_149691_a(p_149691_1_, 0);
        }
        if (meta == 2) {
            return Blocks.field_150339_S.func_149691_a(p_149691_1_, 0);
        }
        if (meta == 3) {
            return Blocks.field_150340_R.func_149691_a(p_149691_1_, 0);
        }
        if (meta == 4) {
            return Blocks.field_150484_ah.func_149691_a(p_149691_1_, 0);
        }
        return Blocks.field_150344_f.func_149691_a(p_149691_1_, 0);
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileWallBanner();
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        TileEntity tileentity = world.func_147438_o(x, y, z);
        ItemStack stack = new ItemStack((Block)this, 1, world.func_72805_g(x, y, z));
        if (tileentity instanceof TileWallBanner) {
            NBTTagCompound compound = new NBTTagCompound();
            tileentity.func_145841_b(compound);
            NBTTagCompound brushCompound = new NBTTagCompound();
            brushCompound.func_74768_a(ItemNpcTool.BRUSH_COLOR_TAG, compound.func_74762_e(ItemNpcTool.BRUSH_COLOR_TAG));
            stack.func_77982_d(brushCompound);
        }
        return stack;
    }
}

