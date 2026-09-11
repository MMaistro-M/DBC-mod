/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package kamkeel.npcs.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kamkeel.npcs.client.renderer.RenderEnergy;
import kamkeel.npcs.entity.EntityEnergySlicer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderEnergySlicer
extends RenderEnergy {
    public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        if (this.shouldSkipInitialActiveRender(entity)) {
            return;
        }
        EntityEnergySlicer slicer = (EntityEnergySlicer)entity;
        float width = slicer.getSliceWidth();
        float thickness = slicer.getSliceThickness();
        if (width <= 0.01f) {
            return;
        }
        this.setupRenderState();
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        if (slicer.hasLightningEffect()) {
            this.renderAttachedLightning(slicer, 0.7f, width * 0.5f);
        }
        float travelYaw = slicer.getTravelYaw();
        float travelPitch = slicer.getTravelPitch();
        GL11.glRotatef((float)travelYaw, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)travelPitch, (float)1.0f, (float)0.0f, (float)0.0f);
        float innerScale = 0.7f;
        float proximityAlpha = this.getProximityAlphaFactor(slicer, x, y, z);
        if (slicer.isOuterColorEnabled()) {
            float outerScale = 1.0f + slicer.getOuterColorWidth() * 0.2f;
            GL11.glDepthMask((boolean)false);
            this.renderBlade(slicer.getOuterColor(), slicer.getOuterColorAlpha() * proximityAlpha, width * 0.5f * outerScale, thickness * 2.0f, 0.05f);
            GL11.glDepthMask((boolean)true);
        }
        this.renderBlade(slicer.getInnerColor(), slicer.getInnerAlpha() * proximityAlpha, width * 0.5f * innerScale, thickness, 0.03f);
        GL11.glPopMatrix();
        this.restoreRenderState();
    }

    private void renderBlade(int color, float alpha, float halfWidth, float halfHeight, float halfDepth) {
        float[] rgb = this.extractRGB(color);
        float r = rgb[0];
        float g = rgb[1];
        float b = rgb[2];
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78382_b();
        tess.func_78369_a(r, g, b, alpha);
        tess.func_78375_b(0.0f, 0.0f, 1.0f);
        tess.func_78377_a((double)(-halfWidth), (double)(-halfHeight), (double)halfDepth);
        tess.func_78377_a((double)halfWidth, (double)(-halfHeight), (double)halfDepth);
        tess.func_78377_a((double)halfWidth, (double)halfHeight, (double)halfDepth);
        tess.func_78377_a((double)(-halfWidth), (double)halfHeight, (double)halfDepth);
        tess.func_78375_b(0.0f, 0.0f, -1.0f);
        tess.func_78377_a((double)halfWidth, (double)(-halfHeight), (double)(-halfDepth));
        tess.func_78377_a((double)(-halfWidth), (double)(-halfHeight), (double)(-halfDepth));
        tess.func_78377_a((double)(-halfWidth), (double)halfHeight, (double)(-halfDepth));
        tess.func_78377_a((double)halfWidth, (double)halfHeight, (double)(-halfDepth));
        tess.func_78369_a(r, g, b, alpha * 0.8f);
        tess.func_78375_b(0.0f, 1.0f, 0.0f);
        tess.func_78377_a((double)(-halfWidth), (double)halfHeight, (double)halfDepth);
        tess.func_78377_a((double)halfWidth, (double)halfHeight, (double)halfDepth);
        tess.func_78377_a((double)halfWidth, (double)halfHeight, (double)(-halfDepth));
        tess.func_78377_a((double)(-halfWidth), (double)halfHeight, (double)(-halfDepth));
        tess.func_78375_b(0.0f, -1.0f, 0.0f);
        tess.func_78377_a((double)(-halfWidth), (double)(-halfHeight), (double)(-halfDepth));
        tess.func_78377_a((double)halfWidth, (double)(-halfHeight), (double)(-halfDepth));
        tess.func_78377_a((double)halfWidth, (double)(-halfHeight), (double)halfDepth);
        tess.func_78377_a((double)(-halfWidth), (double)(-halfHeight), (double)halfDepth);
        tess.func_78369_a(r, g, b, alpha * 0.6f);
        tess.func_78375_b(1.0f, 0.0f, 0.0f);
        tess.func_78377_a((double)halfWidth, (double)(-halfHeight), (double)halfDepth);
        tess.func_78377_a((double)halfWidth, (double)(-halfHeight), (double)(-halfDepth));
        tess.func_78377_a((double)halfWidth, (double)halfHeight, (double)(-halfDepth));
        tess.func_78377_a((double)halfWidth, (double)halfHeight, (double)halfDepth);
        tess.func_78375_b(-1.0f, 0.0f, 0.0f);
        tess.func_78377_a((double)(-halfWidth), (double)(-halfHeight), (double)(-halfDepth));
        tess.func_78377_a((double)(-halfWidth), (double)(-halfHeight), (double)halfDepth);
        tess.func_78377_a((double)(-halfWidth), (double)halfHeight, (double)halfDepth);
        tess.func_78377_a((double)(-halfWidth), (double)halfHeight, (double)(-halfDepth));
        tess.func_78381_a();
    }
}

