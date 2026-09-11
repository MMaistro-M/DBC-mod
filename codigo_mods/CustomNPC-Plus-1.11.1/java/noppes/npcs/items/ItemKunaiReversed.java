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
import noppes.npcs.CustomItems;
import noppes.npcs.items.ItemKunai;
import org.lwjgl.opengl.GL11;

public class ItemKunaiReversed
extends ItemKunai {
    public ItemKunaiReversed(int par1, Item.ToolMaterial tool) {
        super(par1, tool);
    }

    @Override
    public void renderSpecial() {
        GL11.glScalef((float)0.4f, (float)0.4f, (float)0.4f);
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.4f, (float)-0.9f, (float)0.2f);
    }

    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = CustomItems.kunai.func_77617_a(0);
    }
}

