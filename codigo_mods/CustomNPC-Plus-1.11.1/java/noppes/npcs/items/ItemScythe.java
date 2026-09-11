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

public class ItemScythe
extends ItemNpcWeaponInterface {
    public ItemScythe(int par1, Item.ToolMaterial tool) {
        super(par1, tool);
    }

    @Override
    public void renderSpecial() {
        GL11.glScalef((float)1.0f, (float)1.3f, (float)1.0f);
        GL11.glTranslatef((float)0.0f, (float)-0.2f, (float)-0.16f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
    }
}

