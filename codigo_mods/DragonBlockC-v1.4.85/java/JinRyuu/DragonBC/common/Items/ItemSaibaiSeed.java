/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Items;

import JinRyuu.DragonBC.common.Npcs.EntitySaibaiman;
import JinRyuu.DragonBC.common.mod_DragonBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSaibaiSeed
extends Item {
    private boolean alwaysEdible;
    private int ticker = 0;

    public ItemSaibaiSeed() {
        this.func_77656_e(1);
        this.func_77637_a(mod_DragonBC.DragonBlockC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
    }

    public String getTextureFile() {
        return "jinryuudragonbc:";
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("jinryuudragonbc:" + this.func_77658_a());
    }

    public ItemStack func_77654_b(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        return par1ItemStack;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer p, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (world.func_147439_a(x, y, z) != null) {
            if (!world.field_72995_K && this.ticker == 0) {
                EntitySaibaiman mr = new EntitySaibaiman(world);
                mr.func_70029_a(p.field_70170_p);
                mr.func_70012_b((float)x + 0.5f, (float)y + 0.5f, (float)z + 0.5f, 0.0f, 0.0f);
                mr.setSpwner((Entity)p);
                world.func_72838_d((Entity)mr);
                this.ticker = 20;
                --stack.field_77994_a;
                return true;
            }
            return false;
        }
        return false;
    }

    public void func_77663_a(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if (!p_77663_2_.field_72995_K && this.ticker > 0) {
            --this.ticker;
        }
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.none;
    }
}

