/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package noppes.npcs.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.items.ItemNpcInterface;

public class ItemMusic
extends ItemNpcInterface {
    private boolean shouldRotate = false;

    public ItemMusic() {
        this.func_77637_a(CustomItems.tabMisc);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer player) {
        if (par2World.field_72995_K) {
            return par1ItemStack;
        }
        int note = par2World.field_73012_v.nextInt(24);
        float var7 = (float)Math.pow(2.0, (double)(note - 12) / 12.0);
        String var8 = "harp";
        par2World.func_72908_a(player.field_70165_t, player.field_70163_u, player.field_70161_v, "note." + var8, 3.0f, var7);
        par2World.func_72869_a("note", player.field_70163_u, player.field_70163_u + 1.2, player.field_70163_u, (double)note / 24.0, 0.0, 0.0);
        return par1ItemStack;
    }

    public Item setRotated() {
        this.shouldRotate = true;
        return this;
    }

    public boolean func_77629_n_() {
        return this.shouldRotate;
    }
}

