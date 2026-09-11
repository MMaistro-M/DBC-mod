/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.constants.EnumParticleType;
import noppes.npcs.entity.EntityProjectile;
import noppes.npcs.items.ItemNpcInterface;
import org.lwjgl.opengl.GL11;

public class ItemSlingshot
extends ItemNpcInterface {
    public ItemSlingshot(int par1) {
        super(par1);
        this.field_77777_bU = 1;
        this.func_77656_e(384);
        this.func_77637_a(CustomItems.tabWeapon);
    }

    public void func_77615_a(ItemStack par1ItemStack, World worldObj, EntityPlayer player, int par4) {
        if (worldObj.field_72995_K) {
            return;
        }
        int ticks = this.func_77626_a(par1ItemStack) - par4;
        if (ticks < 6) {
            return;
        }
        if (!player.field_71075_bZ.field_75098_d && !this.consumeItem(player, Item.func_150898_a((Block)Blocks.field_150347_e))) {
            return;
        }
        par1ItemStack.func_77972_a(1, (EntityLivingBase)player);
        EntityProjectile projectile = new EntityProjectile(worldObj, (EntityLivingBase)player, new ItemStack(Blocks.field_150347_e), false);
        projectile.damage = 4.0f;
        projectile.punch = 1;
        projectile.setRotating(true);
        if (ticks > 24) {
            projectile.setParticleEffect(EnumParticleType.Crit);
            projectile.punch = 2;
        }
        projectile.setHasGravity(true);
        projectile.setSpeed(14);
        projectile.shoot(1.0f);
        worldObj.func_72956_a((Entity)player, "random.bow", 1.0f, field_77697_d.nextFloat() * 0.3f + 0.8f);
        worldObj.func_72838_d((Entity)projectile);
    }

    @Override
    public void renderSpecial() {
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glTranslatef((float)0.0f, (float)0.5f, (float)0.0f);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        return par1ItemStack;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }
}

