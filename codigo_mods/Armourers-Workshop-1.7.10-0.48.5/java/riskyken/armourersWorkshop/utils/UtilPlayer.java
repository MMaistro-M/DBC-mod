/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.common.util.ForgeDirection
 */
package riskyken.armourersWorkshop.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.utils.UtilItems;

public final class UtilPlayer {
    public static ForgeDirection getDirection(int x, int y, int z, EntityPlayer player) {
        return ForgeDirection.getOrientation((int)UtilPlayer.getOrientation(x, y, z, (EntityLivingBase)player));
    }

    public static int getOrientation(int x, int y, int z, EntityLivingBase entity) {
        int l;
        if (MathHelper.func_76135_e((float)((float)entity.field_70165_t - (float)x)) < 2.0f && MathHelper.func_76135_e((float)((float)entity.field_70161_v - (float)z)) < 2.0f) {
            double d0 = entity.field_70163_u + (double)entity.func_70047_e() - (double)entity.field_70129_M;
            if (d0 - (double)y > 2.0) {
                return 1;
            }
            if ((double)y - d0 > 0.0) {
                return 0;
            }
        }
        return (l = MathHelper.func_76128_c((double)((double)(entity.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3) == 0 ? 3 : (l == 1 ? 4 : (l == 2 ? 2 : (l == 3 ? 5 : 0)));
    }

    public static ForgeDirection getDirectionSide(EntityPlayer player) {
        return ForgeDirection.getOrientation((int)UtilPlayer.getOrientationSide((EntityLivingBase)player));
    }

    public static int getOrientationSide(EntityLivingBase entity) {
        int l = MathHelper.func_76128_c((double)((double)(entity.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        return l == 0 ? 3 : (l == 1 ? 4 : (l == 2 ? 2 : (l == 3 ? 5 : 0)));
    }

    public static int getNumberOfItemInInventory(EntityPlayer player, Item item) {
        int itemCount = 0;
        InventoryPlayer inventory = player.field_71071_by;
        for (int i = 0; i < inventory.field_70462_a.length; ++i) {
            if (inventory.field_70462_a[i].func_77973_b() != item) continue;
            itemCount += inventory.field_70462_a[i].field_77994_a;
        }
        return itemCount;
    }

    public static void consumeInventoryItemCount(EntityPlayer player, Item item, int count) {
        int removeCount = count;
        InventoryPlayer inventory = player.field_71071_by;
        for (int i = 0; i < inventory.field_70462_a.length; ++i) {
            if (inventory.field_70462_a[i].func_77973_b() == item) {
                if (inventory.field_70462_a[i].field_77994_a >= removeCount) {
                    removeCount -= inventory.field_70462_a[i].field_77994_a;
                    inventory.field_70462_a[i] = null;
                } else {
                    inventory.field_70462_a[i].field_77994_a = removeCount;
                    removeCount = 0;
                }
            }
            if (removeCount >= 1) continue;
            return;
        }
    }

    public static void giveItem(EntityPlayer player, ItemStack stack) {
        if (stack == null) {
            return;
        }
        if (!player.field_71071_by.func_70441_a(stack) && !player.func_130014_f_().field_72995_K) {
            UtilItems.spawnItemInWorld(player.func_130014_f_(), player.field_70165_t, player.field_70163_u, player.field_70161_v, stack);
        }
    }

    public static boolean isPlayerOp(EntityPlayer player) {
        MinecraftServer server = ArmourersWorkshop.proxy.getServer();
        if (player == null || player.func_146103_bH() == null) {
            return false;
        }
        return server.func_71203_ab().func_152596_g(player.func_146103_bH());
    }

    private static MinecraftServer getIntegratedServer() {
        return null;
    }
}

