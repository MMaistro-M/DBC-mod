/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.Item$ToolMaterial
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import noppes.npcs.items.ItemDagger;
import org.lwjgl.opengl.GL11;

public class ItemDaggerReversed
extends ItemDagger {
    private ItemDagger dagger;

    public ItemDaggerReversed(int par1, ItemDagger dagger, Item.ToolMaterial tool) {
        super(par1, tool);
        this.dagger = dagger;
    }

    @Override
    public void renderSpecial() {
        GL11.glScalef((float)0.6f, (float)0.6f, (float)0.6f);
        GL11.glTranslatef((float)0.16f, (float)0.6f, (float)-0.16f);
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
    }

    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = this.dagger.func_77617_a(0);
    }
}

