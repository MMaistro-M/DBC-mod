/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.items;

import java.awt.Color;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.constants.EnumNpcToolMaterial;
import noppes.npcs.items.ItemStaff;

public class ItemElementalStaff
extends ItemStaff {
    public ItemElementalStaff(int par1, EnumNpcToolMaterial material) {
        super(par1, material);
        this.func_77627_a(true);
        this.color = ItemStaff.OrbColor.GENERIC;
    }

    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        float[] color = EntitySheep.field_70898_d[par1ItemStack.func_77960_j()];
        return new Color(color[0], color[1], color[2]).getRGB();
    }

    public boolean func_77623_v() {
        return true;
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int var4 = 0; var4 < 16; ++var4) {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }

    @Override
    public ItemStack getProjectile(ItemStack stack) {
        return new ItemStack(CustomItems.orb, 1, stack.func_77960_j());
    }

    @Override
    public void spawnParticle(ItemStack stack, EntityPlayer player) {
        CustomNpcs.proxy.spawnParticle((EntityLivingBase)player, "Spell", stack.func_77960_j(), 4);
    }
}

