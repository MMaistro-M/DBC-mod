/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.config.ConfigItem;
import noppes.npcs.entity.EntityProjectile;
import noppes.npcs.items.ItemNpcInterface;
import org.lwjgl.opengl.GL11;

public class ItemMachineGun
extends ItemNpcInterface {
    public ItemMachineGun(int par1) {
        super(par1);
        this.func_77656_e(80);
        this.func_77637_a(CustomItems.tabWeapon);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (stack.field_77990_d != null && stack.field_77990_d.func_74764_b("ShotsLeft")) {
            list.add(StatCollector.func_74837_a((String)"item.npcMachineGun.info", (Object[])new Object[0]) + ": " + stack.field_77990_d.func_74762_e("ShotsLeft"));
        }
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        if (player.field_70170_p.field_72995_K) {
            return;
        }
        int ticks = this.func_77626_a(stack) - count;
        this.ensureNBT(stack);
        int shotsLeft = stack.field_77990_d.func_74762_e("ShotsLeft");
        boolean isReloading = stack.field_77990_d.func_74767_n("IsReloading");
        if (!isReloading && shotsLeft > 0) {
            if (ticks >= ConfigItem.MachineGunTickSpeed && ticks % ConfigItem.MachineGunTickSpeed == 0) {
                this.fireShot(stack, player);
                shotsLeft = stack.field_77990_d.func_74762_e("ShotsLeft");
                if (shotsLeft <= 0 && this.hasItem(player, CustomItems.bulletBlack)) {
                    stack.field_77990_d.func_74757_a("IsReloading", true);
                }
            }
        } else if (isReloading && shotsLeft < ConfigItem.MachineGunAmmo && this.hasItem(player, CustomItems.bulletBlack)) {
            if (ticks % ConfigItem.MachineGunTickSpeed - 1 == 0) {
                this.reloadShot(stack, player);
                player.field_70170_p.func_72956_a((Entity)player, "customnpcs:gun.ak47.load", 1.0f, 1.0f);
            }
        } else if (ticks % ConfigItem.MachineGunTickSpeed == 0) {
            player.field_70170_p.func_72956_a((Entity)player, "customnpcs:gun.empty", 1.0f, 1.0f);
        }
    }

    public void func_77615_a(ItemStack stack, World par2World, EntityPlayer player, int count) {
        if (stack.field_77990_d == null) {
            return;
        }
        stack.field_77990_d.func_74757_a("IsReloading", false);
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (player.func_71039_bw()) {
            return stack;
        }
        this.ensureNBT(stack);
        int shotsLeft = stack.field_77990_d.func_74762_e("ShotsLeft");
        stack.field_77990_d.func_74757_a("IsReloading", shotsLeft == 0 && this.hasItem(player, CustomItems.bulletBlack));
        player.func_71008_a(stack, this.func_77626_a(stack));
        return stack;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public EnumAction func_77661_b(ItemStack stack) {
        if (stack.field_77990_d == null || !stack.field_77990_d.func_74767_n("IsReloading")) {
            return EnumAction.bow;
        }
        return EnumAction.block;
    }

    @Override
    public void renderSpecial() {
        GL11.glRotatef((float)-6.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glScalef((float)0.8f, (float)0.7f, (float)0.7f);
        GL11.glTranslatef((float)0.2f, (float)0.0f, (float)0.2f);
    }

    private void ensureNBT(ItemStack stack) {
        if (stack.field_77990_d == null) {
            stack.field_77990_d = new NBTTagCompound();
        }
        if (!stack.field_77990_d.func_74764_b("ShotsLeft")) {
            stack.field_77990_d.func_74768_a("ShotsLeft", 0);
        }
        if (!stack.field_77990_d.func_74764_b("IsReloading")) {
            stack.field_77990_d.func_74757_a("IsReloading", false);
        }
    }

    private void fireShot(ItemStack stack, EntityPlayer player) {
        if (!ConfigItem.GunsEnabled && !player.field_71075_bZ.field_75098_d) {
            return;
        }
        int shotsLeft = stack.field_77990_d.func_74762_e("ShotsLeft");
        if (shotsLeft <= 0) {
            return;
        }
        EntityProjectile projectile = new EntityProjectile(player.field_70170_p, (EntityLivingBase)player, new ItemStack(CustomItems.bulletBlack, 1, 0), false);
        projectile.damage = 4.0f;
        projectile.setSpeed(40);
        projectile.shoot(2.0f);
        player.field_70170_p.func_72956_a((Entity)player, "customnpcs:gun.pistol.shot", 0.9f, field_77697_d.nextFloat() * 0.3f + 0.8f);
        player.field_70170_p.func_72838_d((Entity)projectile);
        stack.field_77990_d.func_74768_a("ShotsLeft", shotsLeft - 1);
        if (!player.field_71075_bZ.field_75098_d) {
            this.consumeItem(player, CustomItems.bulletBlack);
        }
    }

    private void reloadShot(ItemStack stack, EntityPlayer player) {
        int shotsLeft = stack.field_77990_d.func_74762_e("ShotsLeft");
        if (shotsLeft < ConfigItem.MachineGunAmmo && this.hasItem(player, CustomItems.bulletBlack)) {
            stack.field_77990_d.func_74768_a("ShotsLeft", shotsLeft + 1);
            if (!player.field_71075_bZ.field_75098_d) {
                this.consumeItem(player, CustomItems.bulletBlack);
            }
        }
    }
}

