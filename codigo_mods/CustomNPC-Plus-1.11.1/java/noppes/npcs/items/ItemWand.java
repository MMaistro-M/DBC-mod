/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.items;

import net.minecraft.item.ItemStack;
import noppes.npcs.CustomItems;
import noppes.npcs.items.ItemNpcInterface;
import org.lwjgl.opengl.GL11;

public class ItemWand
extends ItemNpcInterface {
    public ItemWand(int par1) {
        super(par1);
        this.func_77637_a(CustomItems.tabMisc);
    }

    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }

    @Override
    public void renderSpecial() {
        GL11.glScalef((float)0.54f, (float)0.54f, (float)0.54f);
        GL11.glTranslatef((float)0.1f, (float)0.5f, (float)0.1f);
    }
}

