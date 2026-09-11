/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
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
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.config.ConfigItem;
import noppes.npcs.constants.EnumParticleType;
import noppes.npcs.entity.EntityProjectile;
import noppes.npcs.items.ItemNpcInterface;
import org.lwjgl.opengl.GL11;

public class ItemMusket
extends ItemNpcInterface {
    public ItemMusket(int par1) {
        super(par1);
        this.func_77656_e(129);
        this.func_77637_a(CustomItems.tabWeapon);
    }

    public void func_77615_a(ItemStack stack, World par2World, EntityPlayer player, int count) {
        if (player.field_70170_p.field_72995_K) {
            return;
        }
        if (!stack.field_77990_d.func_74767_n("IsLoaded2") && !player.field_71075_bZ.field_75098_d || !ConfigItem.GunsEnabled) {
            player.field_70170_p.func_72956_a((Entity)player, "customnpcs:gun.empty", 1.0f, 1.0f);
            return;
        }
        if (stack.field_77990_d.func_74767_n("Reloading2") && !player.field_71075_bZ.field_75098_d) {
            stack.field_77990_d.func_74757_a("Reloading2", false);
            return;
        }
        stack.func_77972_a(1, (EntityLivingBase)player);
        EntityProjectile projectile = new EntityProjectile(player.field_70170_p, (EntityLivingBase)player, new ItemStack(CustomItems.bulletBlack, 1, 0), false);
        projectile.damage = 16.0f;
        projectile.setSpeed(50);
        projectile.setParticleEffect(EnumParticleType.Smoke);
        projectile.shoot(2.0f);
        if (!player.field_71075_bZ.field_75098_d) {
            this.consumeItem(player, CustomItems.bulletBlack);
        }
        player.field_70170_p.func_72956_a((Entity)player, "random.explode", 0.9f, field_77697_d.nextFloat() * 0.3f + 1.8f);
        player.field_70170_p.func_72956_a((Entity)player, "ambient.weather.thunder", 2.0f, field_77697_d.nextFloat() * 0.3f + 1.8f);
        player.field_70170_p.func_72838_d((Entity)projectile);
        stack.field_77990_d.func_74757_a("IsLoaded2", false);
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        if (player.field_70170_p.field_72995_K) {
            return;
        }
        int ticks = this.func_77626_a(stack) - count;
        if (!player.field_71075_bZ.field_75098_d && stack.field_77990_d.func_74767_n("Reloading2") && this.hasItem(player, CustomItems.bulletBlack)) {
            if (ticks == 60) {
                player.field_70170_p.func_72956_a((Entity)player, "customnpcs:gun.ak47.load", 1.0f, 1.0f);
                stack.field_77990_d.func_74757_a("IsLoaded2", true);
            }
            return;
        }
    }

    @Override
    public void renderSpecial() {
        GL11.glRotatef((float)-6.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glScalef((float)0.7f, (float)0.7f, (float)0.7f);
        GL11.glTranslatef((float)0.4f, (float)0.0f, (float)0.2f);
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (stack.field_77990_d == null) {
            stack.field_77990_d = new NBTTagCompound();
        }
        if (!player.field_71075_bZ.field_75098_d && this.hasItem(player, CustomItems.bulletBlack) && !stack.field_77990_d.func_74767_n("IsLoaded2")) {
            stack.field_77990_d.func_74757_a("Reloading2", true);
        }
        player.func_71008_a(stack, this.func_77626_a(stack));
        return stack;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public EnumAction func_77661_b(ItemStack stack) {
        if (stack.field_77990_d == null || !stack.field_77990_d.func_74767_n("Reloading2")) {
            return EnumAction.bow;
        }
        return EnumAction.block;
    }
}

