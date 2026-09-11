/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.config.ConfigItem;
import noppes.npcs.constants.EnumNpcToolMaterial;
import noppes.npcs.enchants.EnchantInterface;
import noppes.npcs.entity.EntityProjectile;
import noppes.npcs.items.ItemBullet;
import noppes.npcs.items.ItemNpcInterface;
import noppes.npcs.util.IProjectileCallback;
import org.lwjgl.opengl.GL11;

public class ItemGun
extends ItemNpcInterface
implements IProjectileCallback {
    private EnumNpcToolMaterial material;

    public ItemGun(int par1, EnumNpcToolMaterial material) {
        super(par1);
        this.field_77777_bU = 1;
        this.material = material;
        this.func_77656_e(material.getMaxUses());
        this.func_77637_a(CustomItems.tabWeapon);
    }

    public void func_77615_a(ItemStack stack, World worldObj, EntityPlayer player, int par4) {
        if (worldObj.field_72995_K) {
            return;
        }
        if (!this.hasBullet(player, stack) || !ConfigItem.GunsEnabled) {
            worldObj.func_72956_a((Entity)player, "customnpcs:gun.empty", 1.0f, 1.0f);
            return;
        }
        int ticks = this.func_77626_a(stack) - par4;
        if (ticks < 10) {
            return;
        }
        stack.func_77972_a(1, (EntityLivingBase)player);
        ItemBullet bullet = (ItemBullet)this.getBullet();
        int damage = (bullet.getBulletDamage() + this.material.getDamageVsEntity() + 1) / 2 + 5;
        damage = (int)((float)damage + (float)(damage * EnchantInterface.getLevel(EnchantInterface.Damage, stack)) * 0.5f);
        EntityProjectile projectile = new EntityProjectile(worldObj, (EntityLivingBase)player, new ItemStack(this.getBullet()), false);
        projectile.damage = damage;
        projectile.callback = this;
        projectile.callbackItem = stack;
        projectile.setSpeed(40);
        projectile.shoot(this.material.getDamageVsEntity() + 1);
        if (!player.field_71075_bZ.field_75098_d && !this.hasInfinite(stack)) {
            this.consumeItem(player, this.getBullet());
        }
        worldObj.func_72956_a((Entity)player, "customnpcs:gun.pistol.shot", 1.0f, field_77697_d.nextFloat() * 0.3f + 0.8f);
        worldObj.func_72838_d((Entity)projectile);
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        int ticks = this.func_77626_a(stack) - count;
        if (ticks == 8 && !player.field_70170_p.field_72995_K) {
            player.field_70170_p.func_72956_a((Entity)player, "customnpcs:gun.pistol.trigger", 1.0f, 1.0f / (player.field_70170_p.field_73012_v.nextFloat() * 0.4f + 0.8f));
        }
    }

    @Override
    public void renderSpecial() {
        GL11.glScalef((float)0.7f, (float)0.7f, (float)0.7f);
        GL11.glTranslatef((float)0.3f, (float)0.3f, (float)0.1f);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        return par1ItemStack;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    private boolean hasBullet(EntityPlayer player, ItemStack stack) {
        if (player.field_71075_bZ.field_75098_d || this.hasInfinite(stack)) {
            return true;
        }
        return this.hasItem(player, this.getBullet());
    }

    private Item getBullet() {
        switch (this.material) {
            case EMERALD: {
                return CustomItems.bulletEmerald;
            }
            case DIA: {
                return CustomItems.bulletDiamond;
            }
            case IRON: {
                return CustomItems.bulletIron;
            }
            case BRONZE: {
                return CustomItems.bulletBronze;
            }
            case GOLD: {
                return CustomItems.bulletGold;
            }
            case STONE: {
                return CustomItems.bulletStone;
            }
            case WOOD: {
                return CustomItems.bulletWood;
            }
        }
        return CustomItems.bulletBlack;
    }

    public boolean hasInfinite(ItemStack stack) {
        return EnchantInterface.getLevel(EnchantInterface.Infinite, stack) > 0;
    }

    @Override
    public int func_77619_b() {
        return this.material.getEnchantability();
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    @Override
    public boolean onImpact(EntityProjectile entityProjectile, EntityLivingBase entity, ItemStack itemstack) {
        int poison;
        int confusion = EnchantInterface.getLevel(EnchantInterface.Confusion, itemstack);
        if (confusion > 0 && entity.func_70681_au().nextInt(4) > confusion) {
            entity.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 100));
        }
        if ((poison = EnchantInterface.getLevel(EnchantInterface.Poison, itemstack)) > 0 && entity.func_70681_au().nextInt(4) > poison) {
            entity.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 100));
        }
        return false;
    }
}

