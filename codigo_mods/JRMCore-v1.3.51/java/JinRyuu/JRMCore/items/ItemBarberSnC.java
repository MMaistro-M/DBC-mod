/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IShearable
 */
package JinRyuu.JRMCore.items;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHJFC;
import JinRyuu.JRMCore.mod_JRMCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class ItemBarberSnC
extends Item {
    public static Entity barberTarget;

    public ItemBarberSnC(int par2, float par3, boolean par4) {
        this.func_77625_d(1);
        this.func_77656_e(10);
        this.func_77637_a(mod_JRMCore.JRMCore);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add(JRMCoreH.trl("jrmc", "BarberSnC.line1"));
    }

    public String getTextureFile() {
        return JRMCoreH.tjjrmc + ":";
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a(JRMCoreH.tjjrmc + ":" + this.func_77658_a().substring(5));
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par3EntityPlayer.field_71075_bZ.field_75098_d || par3EntityPlayer.field_71071_by.func_146028_b((Item)this)) {
            // empty if block
        }
        if (par2World.field_72995_K) {
            barberTarget = null;
            par3EntityPlayer.openGui((Object)mod_JRMCore.instance, 8, par3EntityPlayer.field_70170_p, (int)par3EntityPlayer.field_70165_t, (int)par3EntityPlayer.field_70163_u, (int)par3EntityPlayer.field_70161_v);
        }
        if (!par3EntityPlayer.field_71075_bZ.field_75098_d) {
            par1ItemStack.func_77972_a(1, (EntityLivingBase)par3EntityPlayer);
        }
        return par1ItemStack;
    }

    public boolean func_111207_a(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity) {
        if (entity.field_70170_p.field_72995_K) {
            if (JRMCoreH.JFC() && JRMCoreHJFC.isChildNPC((Entity)entity)) {
                itemstack.func_77972_a(1, entity);
                barberTarget = entity;
                player.openGui((Object)mod_JRMCore.instance, 8, player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
            }
            return true;
        }
        if (entity instanceof IShearable) {
            IShearable target = (IShearable)entity;
            if (target.isShearable(itemstack, (IBlockAccess)entity.field_70170_p, (int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v)) {
                ArrayList drops = target.onSheared(itemstack, (IBlockAccess)entity.field_70170_p, (int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v, EnchantmentHelper.func_77506_a((int)Enchantment.field_77346_s.field_77352_x, (ItemStack)itemstack));
                Random rand = new Random();
                for (ItemStack stack : drops) {
                    EntityItem ent = entity.func_70099_a(stack, 1.0f);
                    ent.field_70181_x += (double)(rand.nextFloat() * 0.05f);
                    ent.field_70159_w += (double)((rand.nextFloat() - rand.nextFloat()) * 0.1f);
                    ent.field_70179_y += (double)((rand.nextFloat() - rand.nextFloat()) * 0.1f);
                }
                itemstack.func_77972_a(1, entity);
            }
            return true;
        }
        return false;
    }

    private boolean getChild() {
        return false;
    }
}

