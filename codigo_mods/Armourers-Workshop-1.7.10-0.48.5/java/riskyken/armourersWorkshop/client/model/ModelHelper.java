/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.model;

import org.lwjgl.opengl.GL11;

public final class ModelHelper {
    private static final float CHILD_SCALE = 2.0f;

    public static void enableChildModelScale(boolean headScale, float scale) {
        GL11.glPushMatrix();
        if (headScale) {
            GL11.glScalef((float)0.75f, (float)0.75f, (float)0.75f);
            GL11.glTranslatef((float)0.0f, (float)(16.0f * scale), (float)0.0f);
        } else {
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            GL11.glTranslatef((float)0.0f, (float)(24.0f * scale), (float)0.0f);
        }
    }

    public static void disableChildModelScale() {
        GL11.glPopMatrix();
    }
}

