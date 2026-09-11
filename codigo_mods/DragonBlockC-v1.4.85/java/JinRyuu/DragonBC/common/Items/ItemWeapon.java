/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Items;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.Render.ppTileEntity;
import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemWeapon
extends ItemSword {
    private int ticker = 0;

    public ItemWeapon(Item.ToolMaterial tm) {
        super(tm);
        this.field_77777_bU = 1;
        this.func_77637_a(mod_DragonBC.DragonBlockC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a(JRMCoreH.tjdbcAssts + ":" + this.func_77658_a());
    }

    public void func_77622_d(ItemStack p_77622_1_, World p_77622_2_, EntityPlayer p_77622_3_) {
        super.func_77622_d(p_77622_1_, p_77622_2_, p_77622_3_);
    }

    public void func_77663_a(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        super.func_77663_a(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);
        if (!p_77663_2_.field_72995_K && this.ticker > 0) {
            --this.ticker;
        }
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer p, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (world.func_147439_a(x, y, z) != null) {
            if (!world.field_72995_K && this.ticker == 0 && world.func_147439_a(x, y, z).func_149742_c(world, x, y + 1, z)) {
                world.func_147449_b(x, y + 1, z, BlocksDBC.ppBlock);
                for (int i = 2; i < 100 && world.func_147437_c(x, y + i, z); ++i) {
                    world.func_147449_b(x, y + i, z, BlocksDBC.ppBlock);
                }
                TileEntity tile_entity = world.func_147438_o(x, y + 1, z);
                if (tile_entity instanceof ppTileEntity) {
                    ((ppTileEntity)tile_entity).setF();
                }
                this.ticker = 20;
                p.field_71071_by.func_146026_a((Item)this);
                stack.field_77994_a = 0;
                return true;
            }
            return false;
        }
        return false;
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        return p_77659_1_;
    }
}

