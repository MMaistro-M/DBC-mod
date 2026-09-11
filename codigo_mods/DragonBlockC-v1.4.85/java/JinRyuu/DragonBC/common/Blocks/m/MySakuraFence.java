/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFence
 *  net.minecraft.block.BlockFenceGate
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package JinRyuu.DragonBC.common.Blocks.m;

import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class MySakuraFence
extends BlockFence {
    private IIcon[] texture;
    static final String[] subBlocks = new String[]{"Sakura"};

    public MySakuraFence(String unlocalizedName, Material material) {
        super(unlocalizedName, material);
        this.func_149663_c(unlocalizedName);
        this.func_149658_d(JRMCoreH.tjdbcAssts + ":" + unlocalizedName);
        this.func_149647_a(mod_DragonBC.DragonBlockC);
        this.func_149672_a(field_149766_f);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item item, CreativeTabs tabs, List list) {
        for (int i = 0; i < subBlocks.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        this.texture = new IIcon[subBlocks.length];
        for (int i = 0; i < this.texture.length; ++i) {
            this.texture[i] = iconRegister.func_94245_a(JRMCoreH.tjdbcAssts + ":Planks" + subBlocks[i]);
        }
    }

    public int func_149692_a(int p_149692_1_) {
        return p_149692_1_;
    }

    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 40;
    }

    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 5;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
        if (p_149691_2_ < 0 || p_149691_2_ >= this.texture.length) {
            p_149691_2_ = 0;
        }
        return this.texture[p_149691_2_];
    }

    public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
        return true;
    }

    public boolean func_149826_e(IBlockAccess p_149826_1_, int p_149826_2_, int p_149826_3_, int p_149826_4_) {
        Block block = p_149826_1_.func_147439_a(p_149826_2_, p_149826_3_, p_149826_4_);
        if (block.func_149688_o().func_76218_k() && block.func_149686_d()) {
            return block.func_149688_o() != Material.field_151572_C;
        }
        return block == this || block instanceof BlockFenceGate;
    }
}

