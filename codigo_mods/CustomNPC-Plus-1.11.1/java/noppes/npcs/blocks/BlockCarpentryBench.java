/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package noppes.npcs.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import noppes.npcs.CustomNpcs;
import noppes.npcs.blocks.tiles.TileBlockAnvil;
import noppes.npcs.constants.EnumGuiType;

public class BlockCarpentryBench
extends BlockContainer {
    public int renderId = -1;

    public BlockCarpentryBench() {
        super(Material.field_151576_e);
        this.func_149672_a(field_149780_i);
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        Block block = world.func_147439_a(x, y, z);
        int meta = world.func_72805_g(x, y, z);
        if (block != this) {
            return block.getLightValue(world, x, y, z);
        }
        if (meta >= 4) {
            return 5;
        }
        return this.func_149750_m();
    }

    public boolean func_149727_a(World par1World, int i, int j, int k, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!par1World.field_72995_K) {
            int meta = par1World.func_72805_g(i, j, k);
            if (meta >= 4) {
                player.openGui((Object)CustomNpcs.instance, EnumGuiType.PlayerAnvil.ordinal(), par1World, i, j, k);
            } else {
                player.openGui((Object)CustomNpcs.instance, EnumGuiType.PlayerCarpentryBench.ordinal(), par1World, i, j, k);
            }
        }
        return true;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return this.renderId;
    }

    public boolean func_149686_d() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        if (meta >= 4) {
            return Blocks.field_150467_bQ.func_149691_a(side, 0);
        }
        return Blocks.field_150344_f.func_149691_a(side, 0);
    }

    public int func_149692_a(int par1) {
        return par1 / 4;
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack item) {
        int var6 = MathHelper.func_76128_c((double)((double)(par5EntityLiving.field_70177_z / 90.0f) + 0.5)) & 3;
        par1World.func_72921_c(par2, par3, par4, var6 + item.func_77960_j() * 4, 2);
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileBlockAnvil();
    }
}

