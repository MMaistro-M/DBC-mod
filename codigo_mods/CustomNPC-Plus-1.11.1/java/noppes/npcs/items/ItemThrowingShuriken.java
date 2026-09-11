/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.items;

import noppes.npcs.items.ItemThrowingWeapon;
import org.lwjgl.opengl.GL11;

public class ItemThrowingShuriken
extends ItemThrowingWeapon {
    public ItemThrowingShuriken(int par1) {
        super(par1);
    }

    @Override
    public void renderSpecial() {
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glTranslatef((float)-0.1f, (float)0.3f, (float)0.0f);
    }

    public boolean func_77629_n_() {
        return true;
    }
}

