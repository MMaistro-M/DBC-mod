/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import noppes.npcs.entity.EntityProjectile;
import noppes.npcs.items.ItemNpcWeaponInterface;
import org.lwjgl.opengl.GL11;

public class ItemKunai
extends ItemNpcWeaponInterface {
    public ItemKunai(int par1, Item.ToolMaterial tool) {
        super(par1, tool);
    }

    public void func_77615_a(ItemStack par1ItemStack, World worldObj, EntityPlayer player, int par4) {
        if (worldObj.field_72995_K) {
            player.func_71038_i();
            return;
        }
        EntityProjectile projectile = new EntityProjectile(worldObj, (EntityLivingBase)player, par1ItemStack, false);
        projectile.damage = this.func_150931_i();
        projectile.destroyedOnEntityHit = false;
        projectile.canBePickedUp = !player.field_71075_bZ.field_75098_d;
        projectile.setIs3D(true);
        projectile.setStickInWall(true);
        projectile.setHasGravity(true);
        projectile.setSpeed(12);
        projectile.shoot(1.0f);
        if (!player.field_71075_bZ.field_75098_d) {
            par1ItemStack.func_77972_a(1, (EntityLivingBase)player);
            if (par1ItemStack.field_77994_a == 0) {
                return;
            }
            player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c] = null;
        }
        worldObj.func_72956_a((Entity)player, "customnpcs:misc.swosh", 1.0f, 1.0f);
        worldObj.func_72838_d((Entity)projectile);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        return par1ItemStack;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    @Override
    public void renderSpecial() {
        GL11.glScalef((float)0.4f, (float)0.4f, (float)0.4f);
        GL11.glTranslatef((float)-0.4f, (float)0.5f, (float)0.1f);
    }

    public boolean func_77629_n_() {
        return true;
    }
}

