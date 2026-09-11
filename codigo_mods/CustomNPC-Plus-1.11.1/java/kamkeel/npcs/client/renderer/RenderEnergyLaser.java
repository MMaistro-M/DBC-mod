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
import java.util.HashMap;
import java.util.Map;
import kamkeel.npcs.client.renderer.RenderEnergy;
import kamkeel.npcs.client.renderer.lightning.AttachedLightningRenderer;
import kamkeel.npcs.entity.EntityAbilityLaser;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderEnergyLaser
extends RenderEnergy {
    private final Map<Integer, AttachedLightningRenderer.LightningState> tipLightningStates = new HashMap<Integer, AttachedLightningRenderer.LightningState>();

    public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        double horzZ;
        double horzY;
        double horzX;
        double horzLen;
        EntityAbilityLaser laser = (EntityAbilityLaser)entity;
        this.setupRenderState();
        float proximityAlpha = this.getProximityAlphaFactor(laser, x, y, z, true);
        if (laser.isCharging()) {
            this.renderChargingOrb(laser, x, y, z, partialTicks, proximityAlpha);
            this.restoreRenderState();
            return;
        }
        double startX = laser.getInterpolatedStartX(partialTicks);
        double startY = laser.getInterpolatedStartY(partialTicks);
        double startZ = laser.getInterpolatedStartZ(partialTicks);
        double endX = laser.getInterpolatedEndX(partialTicks);
        double endY = laser.getInterpolatedEndY(partialTicks);
        double endZ = laser.getInterpolatedEndZ(partialTicks);
        float width = laser.getInterpolatedLaserWidth(partialTicks);
        float alpha = 1.0f;
        double offsetX = x - laser.field_70165_t;
        double renderEndX = endX + offsetX;
        double renderStartX = startX + offsetX;
        double dx = renderEndX - renderStartX;
        double offsetY = y - laser.field_70163_u;
        double renderEndY = endY + offsetY;
        double renderStartY = startY + offsetY;
        double dy = renderEndY - renderStartY;
        double offsetZ = z - laser.field_70161_v;
        double renderEndZ = endZ + offsetZ;
        double renderStartZ = startZ + offsetZ;
        double dz = renderEndZ - renderStartZ;
        double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
        if (len < 0.001) {
            this.restoreRenderState();
            return;
        }
        dx /= len;
        dy /= len;
        dz /= len;
        double upX = 0.0;
        double upY = 1.0;
        double upZ = 0.0;
        if (Math.abs(dy) > 0.9) {
            upX = 1.0;
            upY = 0.0;
            upZ = 0.0;
        }
        if ((horzLen = Math.sqrt((horzX = dy * upZ - dz * upY) * horzX + (horzY = dz * upX - dx * upZ) * horzY + (horzZ = dx * upY - dy * upX) * horzZ)) > 0.0) {
            horzX /= horzLen;
            horzY /= horzLen;
            horzZ /= horzLen;
        }
        double vertX = dy * horzZ - dz * horzY;
        double vertY = dz * horzX - dx * horzZ;
        double vertZ = dx * horzY - dy * horzX;
        float rotationSpeed = laser.getRotationSpeed();
        if (rotationSpeed != 0.0f) {
            double angle = (double)(((float)laser.field_70173_aa + partialTicks) * rotationSpeed) * Math.PI / 180.0;
            double cosA = Math.cos(angle);
            double sinA = Math.sin(angle);
            double rHorzX = horzX * cosA + vertX * sinA;
            double rHorzY = horzY * cosA + vertY * sinA;
            double rHorzZ = horzZ * cosA + vertZ * sinA;
            double rVertX = -horzX * sinA + vertX * cosA;
            double rVertY = -horzY * sinA + vertY * cosA;
            double rVertZ = -horzZ * sinA + vertZ * cosA;
            horzX = rHorzX;
            horzY = rHorzY;
            horzZ = rHorzZ;
            vertX = rVertX;
            vertY = rVertY;
            vertZ = rVertZ;
        }
        float innerScale = 0.6f;
        float innerWidth = width * innerScale;
        if (laser.isOuterColorEnabled()) {
            float outerWidth = innerWidth + laser.getOuterColorWidth() * width;
            GL11.glDepthMask((boolean)false);
            this.renderBeamRectangle(renderStartX, renderStartY, renderStartZ, renderEndX, renderEndY, renderEndZ, horzX, horzY, horzZ, vertX, vertY, vertZ, outerWidth, laser.getOuterColor(), alpha * 0.4f * proximityAlpha);
            float midWidth = innerWidth + (outerWidth - innerWidth) * 0.5f;
            this.renderBeamRectangle(renderStartX, renderStartY, renderStartZ, renderEndX, renderEndY, renderEndZ, horzX, horzY, horzZ, vertX, vertY, vertZ, midWidth, laser.getOuterColor(), alpha * 0.7f * proximityAlpha);
            GL11.glDepthMask((boolean)true);
        }
        this.renderBeamRectangle(renderStartX, renderStartY, renderStartZ, renderEndX, renderEndY, renderEndZ, horzX, horzY, horzZ, vertX, vertY, vertZ, innerWidth, laser.getInnerColor(), alpha * laser.getInnerAlpha() * proximityAlpha);
        if (laser.hasLightningEffect() && laser.getCurrentLength() > 0.1f) {
            this.renderLaserLightning(laser, renderStartX, renderStartY, renderStartZ, renderEndX, renderEndY, renderEndZ, width);
        }
        this.restoreRenderState();
    }

    private void renderChargingOrb(EntityAbilityLaser laser, double x, double y, double z, float partialTicks, float proximityAlpha) {
        float chargeProgress;
        float headSize = laser.getInterpolatedLaserWidth(partialTicks) * 0.5f;
        float size = headSize * (chargeProgress = laser.getInterpolatedChargeProgress(partialTicks));
        if (size <= 0.01f) {
            return;
        }
        int innerColor = laser.getInnerColor();
        int outerColor = laser.getOuterColor();
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        float pulseTime = (float)laser.field_70173_aa + partialTicks;
        float scaleModifier = (float)Math.sin(pulseTime * 0.2f) * 0.08f;
        float scale = size * (1.0f + scaleModifier);
        if (laser.hasLightningEffect()) {
            this.renderAttachedLightning(laser, 0.3f, scale);
        }
        GL11.glPushMatrix();
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        float innerScale = 0.6f;
        if (laser.isOuterColorEnabled()) {
            float outerScale = innerScale + laser.getOuterColorWidth();
            float outerAlpha = laser.getOuterColorAlpha() * proximityAlpha;
            GL11.glDepthMask((boolean)false);
            GL11.glPushMatrix();
            GL11.glScalef((float)outerScale, (float)outerScale, (float)outerScale);
            this.renderCube(outerColor, outerAlpha, 0.5f);
            GL11.glPopMatrix();
            GL11.glDepthMask((boolean)true);
        }
        GL11.glScalef((float)innerScale, (float)innerScale, (float)innerScale);
        this.renderCube(innerColor, laser.getInnerAlpha() * proximityAlpha, 0.5f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    private void renderLaserLightning(EntityAbilityLaser laser, double startX, double startY, double startZ, double endX, double endY, double endZ, float width) {
        AttachedLightningRenderer.LightningState state = this.getLightningState(laser);
        float density = laser.getLightningDensity();
        float innerRadius = width * 0.6f * 0.5f;
        float radius = innerRadius + laser.getLightningRadius() * width;
        int outerColor = laser.getOuterColor();
        int innerColor = laser.getInnerColor();
        int fadeTime = laser.getLightningFadeTime();
        double midX = (startX + endX) * 0.5;
        double midY = (startY + endY) * 0.5;
        double midZ = (startZ + endZ) * 0.5;
        GL11.glPushMatrix();
        GL11.glTranslated((double)midX, (double)midY, (double)midZ);
        state.update(density, radius, outerColor, innerColor, fadeTime);
        state.render();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated((double)endX, (double)endY, (double)endZ);
        AttachedLightningRenderer.LightningState tipState = this.getTipLightningState(laser);
        tipState.update(density * 0.7f, radius * 0.8f, outerColor, innerColor, fadeTime);
        tipState.render();
        GL11.glPopMatrix();
    }

    private AttachedLightningRenderer.LightningState getTipLightningState(EntityAbilityLaser laser) {
        int id = laser.func_145782_y();
        AttachedLightningRenderer.LightningState state = this.tipLightningStates.get(id);
        if (state == null) {
            state = new AttachedLightningRenderer.LightningState();
            this.tipLightningStates.put(id, state);
        }
        return state;
    }

    private void renderBeamRectangle(double x1, double y1, double z1, double x2, double y2, double z2, double horzX, double horzY, double horzZ, double vertX, double vertY, double vertZ, float width, int color, float alpha) {
        float[] rgb = this.extractRGB(color);
        float r = rgb[0];
        float g = rgb[1];
        float b = rgb[2];
        float halfWidth = width * 0.5f;
        double s1x = x1 - horzX * (double)halfWidth - vertX * (double)halfWidth;
        double s1y = y1 - horzY * (double)halfWidth - vertY * (double)halfWidth;
        double s1z = z1 - horzZ * (double)halfWidth - vertZ * (double)halfWidth;
        double s2x = x1 + horzX * (double)halfWidth - vertX * (double)halfWidth;
        double s2y = y1 + horzY * (double)halfWidth - vertY * (double)halfWidth;
        double s2z = z1 + horzZ * (double)halfWidth - vertZ * (double)halfWidth;
        double s3x = x1 + horzX * (double)halfWidth + vertX * (double)halfWidth;
        double s3y = y1 + horzY * (double)halfWidth + vertY * (double)halfWidth;
        double s3z = z1 + horzZ * (double)halfWidth + vertZ * (double)halfWidth;
        double s4x = x1 - horzX * (double)halfWidth + vertX * (double)halfWidth;
        double s4y = y1 - horzY * (double)halfWidth + vertY * (double)halfWidth;
        double s4z = z1 - horzZ * (double)halfWidth + vertZ * (double)halfWidth;
        double e1x = x2 - horzX * (double)halfWidth - vertX * (double)halfWidth;
        double e1y = y2 - horzY * (double)halfWidth - vertY * (double)halfWidth;
        double e1z = z2 - horzZ * (double)halfWidth - vertZ * (double)halfWidth;
        double e2x = x2 + horzX * (double)halfWidth - vertX * (double)halfWidth;
        double e2y = y2 + horzY * (double)halfWidth - vertY * (double)halfWidth;
        double e2z = z2 + horzZ * (double)halfWidth - vertZ * (double)halfWidth;
        double e3x = x2 + horzX * (double)halfWidth + vertX * (double)halfWidth;
        double e3y = y2 + horzY * (double)halfWidth + vertY * (double)halfWidth;
        double e3z = z2 + horzZ * (double)halfWidth + vertZ * (double)halfWidth;
        double e4x = x2 - horzX * (double)halfWidth + vertX * (double)halfWidth;
        double e4y = y2 - horzY * (double)halfWidth + vertY * (double)halfWidth;
        double e4z = z2 - horzZ * (double)halfWidth + vertZ * (double)halfWidth;
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78382_b();
        tess.func_78369_a(r, g, b, alpha);
        tess.func_78377_a(s1x, s1y, s1z);
        tess.func_78377_a(s2x, s2y, s2z);
        tess.func_78377_a(e2x, e2y, e2z);
        tess.func_78377_a(e1x, e1y, e1z);
        tess.func_78377_a(s4x, s4y, s4z);
        tess.func_78377_a(e4x, e4y, e4z);
        tess.func_78377_a(e3x, e3y, e3z);
        tess.func_78377_a(s3x, s3y, s3z);
        tess.func_78377_a(s1x, s1y, s1z);
        tess.func_78377_a(e1x, e1y, e1z);
        tess.func_78377_a(e4x, e4y, e4z);
        tess.func_78377_a(s4x, s4y, s4z);
        tess.func_78377_a(s2x, s2y, s2z);
        tess.func_78377_a(s3x, s3y, s3z);
        tess.func_78377_a(e3x, e3y, e3z);
        tess.func_78377_a(e2x, e2y, e2z);
        tess.func_78377_a(s1x, s1y, s1z);
        tess.func_78377_a(s4x, s4y, s4z);
        tess.func_78377_a(s3x, s3y, s3z);
        tess.func_78377_a(s2x, s2y, s2z);
        tess.func_78377_a(e1x, e1y, e1z);
        tess.func_78377_a(e2x, e2y, e2z);
        tess.func_78377_a(e3x, e3y, e3z);
        tess.func_78377_a(e4x, e4y, e4z);
        tess.func_78381_a();
    }
}

