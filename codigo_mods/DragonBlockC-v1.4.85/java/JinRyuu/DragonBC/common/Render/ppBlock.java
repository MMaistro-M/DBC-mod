/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Render;

import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.DragonBC.common.Render.ppTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ppBlock
extends BlockContainer {
    private final Random field_149933_a = new Random();
    private Class kfueb = ppTileEntity.class;

    public ppBlock() {
        super(Material.field_151573_f);
        this.func_149722_s();
        this.func_149752_b(6000000.0f);
        float var4 = 0.03125f;
        this.func_149676_a(0.5f - var4, 0.0f, 0.5f - var4, 0.5f + var4, 1.0f, 0.5f + var4);
        this.func_149675_a(true);
    }

    public int func_149645_b() {
        return -1;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public void func_149726_b(World p_149726_1_, int x, int y, int z) {
        super.func_149726_b(p_149726_1_, x, y, z);
    }

    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        Item item = ItemsDBC.ItemPP;
        return new ItemStack(item, 1, 0);
    }

    @SideOnly(value=Side.CLIENT)
    public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return ItemsDBC.ItemPP;
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return null;
    }

    public int quantityDropped(int parMetadata, int parFortune, Random parRand) {
        return 0;
    }

    public int func_149745_a(Random p_149745_1_) {
        return 0;
    }

    public int func_149738_a(World par1World) {
        return 20;
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t) {
        TileEntity tile_entity = world.func_147438_o(x, y, z);
        if (tile_entity == null || player.func_70093_af()) {
            return false;
        }
        if (!world.field_72995_K) {
            int i1;
            player.field_71071_by.func_70441_a(new ItemStack(ItemsDBC.ItemPP, 1));
            player.field_71071_by.field_70459_e = true;
            for (i1 = 0; i1 < 255 && world.func_147439_a(x, y + i1, z) instanceof ppBlock; ++i1) {
                world.func_147468_f(x, y + i1, z);
                world.func_147475_p(x, y + i1, z);
            }
            for (i1 = -1; i1 > -255 && world.func_147439_a(x, y + i1, z) instanceof ppBlock; --i1) {
                world.func_147468_f(x, y + i1, z);
                world.func_147475_p(x, y + i1, z);
            }
        }
        return true;
    }

    public TileEntity func_149915_a(World world, int i) {
        return new ppTileEntity();
    }

    public void func_149749_a(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
    }

    public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
        return true;
    }

    public void func_149695_a(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
        TileEntity tile_entity = p_149695_1_.func_147438_o(p_149695_2_, p_149695_3_, p_149695_4_);
        if (tile_entity instanceof ppTileEntity && p_149695_1_.func_147437_c(p_149695_2_, p_149695_3_ - 1, p_149695_4_) && ((ppTileEntity)tile_entity).getF()) {
            float f = 0.7f;
            double d0 = (double)(p_149695_1_.field_73012_v.nextFloat() * f) + (double)(1.0f - f) * 0.5;
            double d1 = (double)(p_149695_1_.field_73012_v.nextFloat() * f) + (double)(1.0f - f) * 0.5;
            double d2 = (double)(p_149695_1_.field_73012_v.nextFloat() * f) + (double)(1.0f - f) * 0.5;
            EntityItem entityitem = new EntityItem(p_149695_1_, (double)p_149695_2_ + d0, (double)p_149695_3_ + d1, (double)p_149695_4_ + d2, new ItemStack(ItemsDBC.ItemPP));
            entityitem.field_145804_b = 10;
            p_149695_1_.func_72838_d((Entity)entityitem);
            for (int i1 = 0; i1 < 255 && p_149695_1_.func_147439_a(p_149695_2_, p_149695_3_ + i1, p_149695_4_) instanceof ppBlock; ++i1) {
                p_149695_1_.func_147468_f(p_149695_2_, p_149695_3_ + i1, p_149695_4_);
                p_149695_1_.func_147475_p(p_149695_2_, p_149695_3_ + i1, p_149695_4_);
            }
        }
    }
}

