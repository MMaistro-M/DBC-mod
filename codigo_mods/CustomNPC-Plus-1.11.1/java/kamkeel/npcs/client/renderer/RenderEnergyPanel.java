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
import kamkeel.npcs.entity.EntityEnergyPanel;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderEnergyPanel
extends RenderEnergyBarrier {
    public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        float yawDelta;
        if (this.shouldSkipInitialActiveRender(entity)) {
            return;
        }
        EntityEnergyPanel panel = (EntityEnergyPanel)entity;
        float width = panel.getPanelData().panelWidth;
        float height = panel.getPanelData().panelHeight;
        float healthPercent = panel.getHealthPercent();
        float prevYaw = panel.getPrevPanelYaw();
        float currentYaw = panel.getPanelYaw();
        for (yawDelta = currentYaw - prevYaw; yawDelta > 180.0f; yawDelta -= 360.0f) {
        }
        while (yawDelta < -180.0f) {
            yawDelta += 360.0f;
        }
        float renderYaw = prevYaw + yawDelta * partialTicks;
        this.setupRenderState();
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        if (panel.hasLightningEffect()) {
            this.renderAttachedLightning(panel, 0.9f, Math.max(width, height) * 0.5f);
        }
        GL11.glRotatef((float)(-renderYaw), (float)0.0f, (float)1.0f, (float)0.0f);
        float flashAlpha = this.computeFlashAlpha(panel);
        float pulseTime = (float)entity.field_70173_aa + partialTicks;
        float pulse = (float)Math.sin(pulseTime * 0.08f) * 0.02f;
        float scale = 1.0f + pulse;
        GL11.glPushMatrix();
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        float innerScale = 1.0f;
        float panelThickness = 0.1f;
        if (panel.isOuterColorEnabled()) {
            float outerScale = 1.0f + panel.getOuterColorWidth() * 0.15f;
            float outerAlpha = panel.getOuterColorAlpha() * healthPercent;
            GL11.glDepthMask((boolean)false);
            this.renderPanel(panel.getOuterColor(), outerAlpha + (panel.getOuterColorAlpha() > 0.0f ? flashAlpha : 0.0f), width * 0.5f * outerScale, height * 0.5f * outerScale, panelThickness * 1.5f);
            GL11.glDepthMask((boolean)true);
        }
        this.renderPanel(panel.getInnerColor(), panel.getInnerAlpha(), width * 0.5f * innerScale, height * 0.5f * innerScale, panelThickness);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        this.restoreRenderState();
    }

    private void renderPanel(int color, float alpha, float halfWidth, float halfHeight, float halfThickness) {
        float[] rgb = this.extractRGB(color);
        float r = rgb[0];
        float g = rgb[1];
        float b = rgb[2];
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78382_b();
        tess.func_78369_a(r, g, b, alpha);
        tess.func_78375_b(0.0f, 0.0f, 1.0f);
        tess.func_78377_a((double)(-halfWidth), (double)(-halfHeight), (double)halfThickness);
        tess.func_78377_a((double)halfWidth, (double)(-halfHeight), (double)halfThickness);
        tess.func_78377_a((double)halfWidth, (double)halfHeight, (double)halfThickness);
        tess.func_78377_a((double)(-halfWidth), (double)halfHeight, (double)halfThickness);
        tess.func_78375_b(0.0f, 0.0f, -1.0f);
        tess.func_78377_a((double)halfWidth, (double)(-halfHeight), (double)(-halfThickness));
        tess.func_78377_a((double)(-halfWidth), (double)(-halfHeight), (double)(-halfThickness));
        tess.func_78377_a((double)(-halfWidth), (double)halfHeight, (double)(-halfThickness));
        tess.func_78377_a((double)halfWidth, (double)halfHeight, (double)(-halfThickness));
        tess.func_78375_b(0.0f, 1.0f, 0.0f);
        tess.func_78377_a((double)(-halfWidth), (double)halfHeight, (double)halfThickness);
        tess.func_78377_a((double)halfWidth, (double)halfHeight, (double)halfThickness);
        tess.func_78377_a((double)halfWidth, (double)halfHeight, (double)(-halfThickness));
        tess.func_78377_a((double)(-halfWidth), (double)halfHeight, (double)(-halfThickness));
        tess.func_78375_b(0.0f, -1.0f, 0.0f);
        tess.func_78377_a((double)(-halfWidth), (double)(-halfHeight), (double)(-halfThickness));
        tess.func_78377_a((double)halfWidth, (double)(-halfHeight), (double)(-halfThickness));
        tess.func_78377_a((double)halfWidth, (double)(-halfHeight), (double)halfThickness);
        tess.func_78377_a((double)(-halfWidth), (double)(-halfHeight), (double)halfThickness);
        tess.func_78375_b(1.0f, 0.0f, 0.0f);
        tess.func_78377_a((double)halfWidth, (double)(-halfHeight), (double)halfThickness);
        tess.func_78377_a((double)halfWidth, (double)(-halfHeight), (double)(-halfThickness));
        tess.func_78377_a((double)halfWidth, (double)halfHeight, (double)(-halfThickness));
        tess.func_78377_a((double)halfWidth, (double)halfHeight, (double)halfThickness);
        tess.func_78375_b(-1.0f, 0.0f, 0.0f);
        tess.func_78377_a((double)(-halfWidth), (double)(-halfHeight), (double)(-halfThickness));
        tess.func_78377_a((double)(-halfWidth), (double)(-halfHeight), (double)halfThickness);
        tess.func_78377_a((double)(-halfWidth), (double)halfHeight, (double)halfThickness);
        tess.func_78377_a((double)(-halfWidth), (double)halfHeight, (double)(-halfThickness));
        tess.func_78381_a();
    }
}

