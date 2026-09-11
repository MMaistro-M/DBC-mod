/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package com.eliotlash.mclib.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DummyEntity
extends EntityLivingBase {
    private final ItemStack[] held = new ItemStack[]{null, null, null, null, new ItemStack(Items.field_151048_u)};

    public DummyEntity(World worldIn) {
        super(worldIn);
    }

    public ItemStack func_70694_bm() {
        return this.held[4];
    }

    public ItemStack func_71124_b(int slot) {
        return this.held[slot];
    }

    public void func_70062_b(int p_70062_1_, ItemStack p_70062_2_) {
        this.held[p_70062_1_] = p_70062_2_;
    }

    public ItemStack[] func_70035_c() {
        return this.held;
    }
}

