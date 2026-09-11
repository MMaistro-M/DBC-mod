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
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
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
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.blocks.tiles.TileColorable;
import noppes.npcs.blocks.tiles.TileShortLamp;
import noppes.npcs.items.ItemNpcTool;

public class BlockShortLamp
extends BlockContainer {
    public int renderId = -1;

    public BlockShortLamp() {
        super(Material.field_151575_d);
        this.func_149715_a(1.0f);
        this.func_149676_a(0.25f, 0.0f, 0.25f, 0.75f, 1.0f, 0.75f);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        ItemStack item = player.field_71071_by.func_70448_g();
        if (TileColorable.allowColorChange(item) != TileColorable.ColorChangeType.DYE) {
            return false;
        }
        int meta = world.func_72805_g(x, y, z);
        TileColorable tile = (TileColorable)world.func_147438_o(x, y, z);
        int color = ColorUtil.colorTableInts[BlockColored.func_150031_c((int)item.func_77960_j())];
        if (tile.color != color) {
            NoppesUtilServer.consumeItemStack(1, player);
            tile.color = color;
            world.func_147471_g(x, y, z);
        }
        return true;
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        this.func_149719_a((IBlockAccess)world, x, y, z);
        return super.func_149668_a(world, x, y, z);
    }

    public void func_149666_a(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
        list.add(new ItemStack(item, 1, 2));
        list.add(new ItemStack(item, 1, 3));
        list.add(new ItemStack(item, 1, 4));
    }

    public int func_149692_a(int meta) {
        return meta;
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        int l = MathHelper.func_76128_c((double)((double)(entity.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        TileColorable tile = (TileColorable)world.func_147438_o(x, y, z);
        tile.rotation = l %= 4;
        tile.color = ColorUtil.colorTableInts[15 - stack.func_77960_j()];
        if (stack.func_77942_o() && stack.func_77978_p().func_74764_b(ItemNpcTool.BRUSH_COLOR_TAG)) {
            tile.color = stack.func_77978_p().func_74762_e(ItemNpcTool.BRUSH_COLOR_TAG);
        }
        world.func_72921_c(x, y, z, stack.func_77960_j(), 2);
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        this.func_149676_a(0.25f, 0.0f, 0.25f, 0.75f, 1.0f, 0.75f);
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
    public void func_149651_a(IIconRegister reg) {
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        if (meta == 1) {
            return Blocks.field_150348_b.func_149691_a(side, 0);
        }
        if (meta == 2) {
            return Blocks.field_150339_S.func_149691_a(side, 0);
        }
        if (meta == 3) {
            return Blocks.field_150340_R.func_149691_a(side, 0);
        }
        if (meta == 4) {
            return Blocks.field_150484_ah.func_149691_a(side, 0);
        }
        return Blocks.field_150344_f.func_149691_a(side, 0);
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileShortLamp();
    }

    public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
        super.func_149681_a(world, x, y, z, meta, player);
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        TileEntity tileentity = world.func_147438_o(x, y, z);
        ItemStack stack = new ItemStack((Block)this, 1, world.func_72805_g(x, y, z));
        if (tileentity instanceof TileShortLamp) {
            NBTTagCompound compound = new NBTTagCompound();
            tileentity.func_145841_b(compound);
            NBTTagCompound brushCompound = new NBTTagCompound();
            brushCompound.func_74768_a(ItemNpcTool.BRUSH_COLOR_TAG, compound.func_74762_e(ItemNpcTool.BRUSH_COLOR_TAG));
            stack.func_77982_d(brushCompound);
        }
        return stack;
    }
}

