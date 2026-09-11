/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.entity.EntityProjectile;
import noppes.npcs.items.ItemNpcInterface;
import org.lwjgl.opengl.GL11;

public class ItemCrossbow
extends ItemNpcInterface {
    public ItemCrossbow(int par1) {
        super(par1);
        this.func_77656_e(129);
        this.func_77637_a(CustomItems.tabWeapon);
    }

    public void func_77615_a(ItemStack stack, World par2World, EntityPlayer player, int count) {
        if (player.field_70170_p.field_72995_K) {
            return;
        }
        if (stack.field_77990_d.func_74762_e("IsLoaded") == 1 || player.field_71075_bZ.field_75098_d) {
            if (stack.field_77990_d.func_74762_e("Reloading") == 1 && !player.field_71075_bZ.field_75098_d) {
                stack.field_77990_d.func_74768_a("Reloading", 0);
                return;
            }
            stack.func_77972_a(1, (EntityLivingBase)player);
            EntityProjectile projectile = new EntityProjectile(player.field_70170_p, (EntityLivingBase)player, new ItemStack(Items.field_151032_g, 1, 0), false);
            projectile.damage = 10.0f;
            projectile.setSpeed(20);
            projectile.setHasGravity(true);
            projectile.shoot(2.0f);
            if (!player.field_71075_bZ.field_75098_d) {
                this.consumeItem(player, CustomItems.crossbowBolt);
            }
            player.field_70170_p.func_72956_a((Entity)player, "random.bow", 0.9f, field_77697_d.nextFloat() * 0.3f + 0.8f);
            player.field_70170_p.func_72838_d((Entity)projectile);
            stack.field_77990_d.func_74768_a("IsLoaded", 0);
        }
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        if (player.field_70170_p.field_72995_K) {
            return;
        }
        int ticks = this.func_77626_a(stack) - count;
        if (!player.field_71075_bZ.field_75098_d && stack.field_77990_d.func_74762_e("Reloading") == 1 && this.hasItem(player, CustomItems.crossbowBolt)) {
            if (ticks == 20) {
                player.field_70170_p.func_72956_a((Entity)player, "random.click", 1.0f, 1.0f);
                stack.field_77990_d.func_74768_a("IsLoaded", 1);
            }
            return;
        }
    }

    @Override
    public void renderSpecial() {
        GL11.glRotatef((float)96.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)-10.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glScalef((float)0.8f, (float)0.8f, (float)0.8f);
        GL11.glTranslatef((float)0.5f, (float)-0.7f, (float)-0.4f);
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (stack.field_77990_d == null) {
            stack.field_77990_d = new NBTTagCompound();
        }
        if (!player.field_71075_bZ.field_75098_d && this.hasItem(player, CustomItems.crossbowBolt) && stack.field_77990_d.func_74762_e("IsLoaded") == 0) {
            stack.field_77990_d.func_74768_a("Reloading", 1);
        }
        player.func_71008_a(stack, this.func_77626_a(stack));
        return stack;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public EnumAction func_77661_b(ItemStack stack) {
        if (stack.field_77990_d == null || stack.field_77990_d.func_74762_e("Reloading") == 0) {
            return EnumAction.bow;
        }
        return EnumAction.block;
    }
}

