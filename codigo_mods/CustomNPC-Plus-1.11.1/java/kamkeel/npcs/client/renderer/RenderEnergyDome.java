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
import kamkeel.npcs.client.renderer.RenderEnergyBarrier;
import kamkeel.npcs.entity.EntityEnergyDome;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderEnergyDome
extends RenderEnergyBarrier {
    public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        if (this.shouldSkipInitialActiveRender(entity)) {
            return;
        }
        EntityEnergyDome dome = (EntityEnergyDome)entity;
        float radius = dome.getDomeRadius();
        float healthPercent = dome.getHealthPercent();
        this.setupRenderState();
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        if (dome.hasLightningEffect()) {
            this.renderAttachedLightning(dome, 0.95f, radius);
        }
        float flashAlpha = this.computeFlashAlpha(dome);
        float innerScale = 1.0f;
        if (dome.isOuterColorEnabled()) {
            float outerScale = 1.0f + dome.getOuterColorWidth() * 0.1f;
            float outerAlpha = dome.getOuterColorAlpha() * healthPercent;
            GL11.glDepthMask((boolean)false);
            this.renderSphere(dome.getOuterColor(), outerAlpha + (dome.getOuterColorAlpha() > 0.0f ? flashAlpha : 0.0f), radius * outerScale, 8);
        }
        GL11.glDepthMask((boolean)true);
        this.renderSphere(dome.getInnerColor(), dome.getInnerAlpha() * healthPercent, radius * innerScale, 8);
        GL11.glPopMatrix();
        this.restoreRenderState();
    }

    private void renderSphere(int color, float alpha, float radius, int segments) {
        float[] rgb = this.extractRGB(color);
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78382_b();
        tess.func_78369_a(rgb[0], rgb[1], rgb[2], alpha);
        for (int lat = 0; lat < segments; ++lat) {
            float theta1 = (float)(Math.PI * (double)lat / (double)segments - 1.5707963267948966);
            float theta2 = (float)(Math.PI * (double)(lat + 1) / (double)segments - 1.5707963267948966);
            float y1 = (float)Math.sin(theta1) * radius;
            float y2 = (float)Math.sin(theta2) * radius;
            float r1 = (float)Math.cos(theta1) * radius;
            float r2 = (float)Math.cos(theta2) * radius;
            for (int lon = 0; lon < segments; ++lon) {
                float z4;
                float nz;
                float ny;
                float phi1 = (float)(Math.PI * 2 * (double)lon / (double)segments);
                float phi2 = (float)(Math.PI * 2 * (double)(lon + 1) / (double)segments);
                float x1 = (float)Math.cos(phi1) * r1;
                float z1 = (float)Math.sin(phi1) * r1;
                float x2 = (float)Math.cos(phi2) * r1;
                float z2 = (float)Math.sin(phi2) * r1;
                float x3 = (float)Math.cos(phi2) * r2;
                float z3 = (float)Math.sin(phi2) * r2;
                float x4 = (float)Math.cos(phi1) * r2;
                float nx = (x1 + x2 + x3 + x4) * 0.25f;
                float nLen = (float)Math.sqrt(nx * nx + (ny = (y1 + y1 + y2 + y2) * 0.25f) * ny + (nz = (z1 + z2 + z3 + (z4 = (float)Math.sin(phi1) * r2)) * 0.25f) * nz);
                if (nLen > 0.0f) {
                    nx /= nLen;
                    ny /= nLen;
                    nz /= nLen;
                }
                tess.func_78375_b(nx, ny, nz);
                tess.func_78377_a((double)x1, (double)y1, (double)z1);
                tess.func_78377_a((double)x2, (double)y1, (double)z2);
                tess.func_78377_a((double)x3, (double)y2, (double)z3);
                tess.func_78377_a((double)x4, (double)y2, (double)z4);
            }
        }
        tess.func_78381_a();
    }
}

