/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.items;

import noppes.npcs.items.ItemMusic;
import org.lwjgl.opengl.GL11;

public class ItemMusicBanjo
extends ItemMusic {
    @Override
    public void renderSpecial() {
        GL11.glScalef((float)0.85f, (float)0.85f, (float)0.85f);
        GL11.glTranslatef((float)0.1f, (float)0.4f, (float)-0.14f);
        GL11.glRotatef((float)-90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
    }
}

