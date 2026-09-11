/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package com.tobiasmjc.dbcadditions.items;

import JinRyuu.JRMCore.JRMCoreH;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFeast
extends ItemFood {
    private float l = 1.0f;
    private int tier;

    public ItemFeast(int food, int tier) {
        super(food, 0.0f, true);
        this.func_77625_d(8);
        this.func_77655_b("feastTier" + tier);
        this.func_77848_i();
        this.func_111206_d("dbcadditions:feastTier" + tier);
        this.tier = tier;
    }

    protected void func_77849_c(ItemStack item, World world, EntityPlayer player) {
        if (!world.field_72995_K) {
            int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(player);
            byte pwr = JRMCoreH.getByte(player, "jrmcPwrtyp");
            byte rce = JRMCoreH.getByte(player, "jrmcRace");
            byte cls = JRMCoreH.getByte(player, "jrmcClass");
            int maxBody = JRMCoreH.stat((Entity)player, 2, pwr, 2, PlyrAttrbts[2], rce, cls, 0.0f);
            int curBody = JRMCoreH.getInt(player, "jrmcBdy");
            int maxEnergy = JRMCoreH.stat((Entity)player, 5, pwr, 5, PlyrAttrbts[5], rce, cls, JRMCoreH.SklLvl_KiBs(player, (int)pwr));
            int curEnergy = JRMCoreH.getInt(player, "jrmcEnrgy");
            int maxStam = JRMCoreH.stat((Entity)player, 2, pwr, 3, PlyrAttrbts[2], rce, cls, 0.0f);
            int curStam = JRMCoreH.getInt(player, "jrmcStamina");
            int body = (int)((float)curBody + ((float)maxBody * 0.3f > 500.0f ? 500.0f : (float)maxBody * 0.3f) * this.l);
            JRMCoreH.setInt(body > maxBody ? maxBody : body, player, "jrmcBdy");
            int en = (int)((float)curEnergy + ((float)maxEnergy * 0.2f > 500.0f ? 500.0f : (float)maxEnergy * 0.2f) * this.l);
            JRMCoreH.setInt(en > maxEnergy ? maxEnergy : en, player, "jrmcEnrgy");
            int st = (int)((float)curStam + ((float)maxStam * 0.2f > 500.0f ? 500.0f : (float)maxStam * 0.2f) * this.l);
            JRMCoreH.setInt(st > maxStam ? maxStam : st, player, "jrmcStamina");
        }
    }
}

