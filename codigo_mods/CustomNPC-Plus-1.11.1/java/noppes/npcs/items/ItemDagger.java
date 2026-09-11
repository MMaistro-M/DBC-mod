/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item$ToolMaterial
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.items;

import net.minecraft.item.Item;
import noppes.npcs.items.ItemNpcWeaponInterface;
import org.lwjgl.opengl.GL11;

public class ItemDagger
extends ItemNpcWeaponInterface {
    public ItemDagger(int par1, Item.ToolMaterial tool) {
        super(par1, tool);
    }

    @Override
    public void renderSpecial() {
        GL11.glScalef((float)0.6f, (float)0.6f, (float)0.6f);
        GL11.glTranslatef((float)0.14f, (float)0.22f, (float)0.06f);
    }
}

