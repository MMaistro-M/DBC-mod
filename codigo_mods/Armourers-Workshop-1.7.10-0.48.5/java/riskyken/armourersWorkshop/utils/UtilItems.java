/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public final class UtilItems {
    public static int getIntensityFromStack(ItemStack stack, int defaultValue) {
        NBTTagCompound stackNbt;
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        if (!(stackNbt = stack.func_77978_p()).func_74764_b("intensity")) {
            stackNbt.func_74768_a("intensity", defaultValue);
        }
        return stackNbt.func_74762_e("intensity");
    }

    public static void setIntensityOnStack(ItemStack stack, int intensity) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        NBTTagCompound stackNbt = stack.func_77978_p();
        stackNbt.func_74768_a("intensity", intensity);
    }

    public static void spawnItemAtEntity(Entity entity, ItemStack stack) {
        UtilItems.spawnItemInWorld(entity.field_70170_p, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, stack);
    }

    public static void spawnItemInWorld(World world, double x, double y, double z, ItemStack stack) {
        float f = 0.7f;
        double xV = (double)(world.field_73012_v.nextFloat() * f) + (double)(1.0f - f) * 0.5;
        double yV = (double)(world.field_73012_v.nextFloat() * f) + (double)(1.0f - f) * 0.5;
        double zV = (double)(world.field_73012_v.nextFloat() * f) + (double)(1.0f - f) * 0.5;
        EntityItem entityitem = new EntityItem(world, x + xV, y + yV, z + zV, stack);
        world.func_72838_d((Entity)entityitem);
    }
}

