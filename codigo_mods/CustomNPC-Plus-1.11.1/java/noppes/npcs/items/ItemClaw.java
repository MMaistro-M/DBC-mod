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

public class ItemClaw
extends ItemNpcWeaponInterface {
    public ItemClaw(int par1, Item.ToolMaterial material) {
        super(par1, material);
    }

    @Override
    public void renderSpecial() {
        GL11.glScalef((float)0.6f, (float)0.6f, (float)0.6f);
        GL11.glTranslatef((float)-0.6f, (float)0.2f, (float)-0.2f);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)-1.0f);
        GL11.glRotatef((float)6.0f, (float)1.0f, (float)0.0f, (float)0.0f);
    }
}

