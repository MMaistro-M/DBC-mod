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

public class ItemBroadSword
extends ItemNpcWeaponInterface {
    public ItemBroadSword(Item.ToolMaterial tool) {
        super(tool);
    }

    @Override
    public void renderSpecial() {
        GL11.glScalef((float)1.0f, (float)1.2f, (float)1.0f);
        GL11.glTranslatef((float)-0.12f, (float)0.14f, (float)-0.16f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
    }
}

