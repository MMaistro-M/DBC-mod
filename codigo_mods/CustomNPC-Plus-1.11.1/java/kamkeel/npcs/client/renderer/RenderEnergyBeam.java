/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.Vec3
 *  org.lwjgl.opengl.GL11
 */
package kamkeel.npcs.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.client.renderer.RenderEnergy;
import kamkeel.npcs.entity.EntityAbilityBeam;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderEnergyBeam
extends RenderEnergy {
    public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        if (this.shouldSkipInitialActiveRender(entity)) {
            return;
        }
        EntityAbilityBeam beam = (EntityAbilityBeam)entity;
        this.setupRenderState();
        float proximityAlpha = this.getProximityAlphaFactor(beam, x, y, z, beam.isAttachedToOwner());
        if (beam.isCharging()) {
            this.renderChargingOrb(beam, x, y, z, partialTicks, proximityAlpha);
            this.restoreRenderState();
            return;
        }
        List<Vec3> trail = beam.getTrailPoints();
        if (trail.isEmpty()) {
            this.restoreRenderState();
            return;
        }
        double headOffsetX = beam.getInterpolatedHeadOffsetX(partialTicks);
        double headOffsetY = beam.getInterpolatedHeadOffsetY(partialTicks);
        double headOffsetZ = beam.getInterpolatedHeadOffsetZ(partialTicks);
        double renderOriginX = beam.getOriginX() - RenderManager.field_78725_b;
        double renderOriginY = beam.getOriginY() - RenderManager.field_78726_c;
        double renderOriginZ = beam.getOriginZ() - RenderManager.field_78723_d;
        float beamWidth = beam.getInterpolatedBeamWidth(partialTicks);
        float headSize = beam.getInterpolatedHeadSize(partialTicks);
        int innerColor = beam.getInnerColor();
        int outerColor = beam.getOuterColor();
        float outerAlpha = beam.getOuterColorAlpha() * proximityAlpha;
        GL11.glPushMatrix();
        GL11.glTranslated((double)renderOriginX, (double)renderOriginY, (double)renderOriginZ);
        double headDistFromOrigin = Math.sqrt(headOffsetX * headOffsetX + headOffsetY * headOffsetY + headOffsetZ * headOffsetZ);
        float innerAlpha = beam.getInnerAlpha() * proximityAlpha;
        if (beam.hasFadingTrail()) {
            this.renderFadingTrail(beam, trail, beamWidth, innerColor, outerColor, beam.isOuterColorEnabled(), beam.getOuterColorWidth(), innerAlpha);
        } else {
            this.renderSmoothTrail(trail, beamWidth, innerColor, outerColor, beam.isOuterColorEnabled(), beam.getOuterColorWidth(), innerAlpha);
        }
        if (beam.shouldRenderTailOrb() && headDistFromOrigin > (double)beam.getHeadSize() * 0.5) {
            this.renderTailOrb(beam.getHeadSize() * 0.8f, innerColor, outerColor, beam.isOuterColorEnabled(), beam.getOuterColorWidth(), outerAlpha, innerAlpha);
        }
        this.renderHead(beam, headOffsetX, headOffsetY, headOffsetZ, headSize, innerColor, outerColor, outerAlpha, innerAlpha, partialTicks);
        GL11.glPopMatrix();
        this.restoreRenderState();
    }

    private void renderChargingOrb(EntityAbilityBeam beam, double x, double y, double z, float partialTicks, float proximityAlpha) {
        float chargeProgress;
        float headSize = beam.getInterpolatedHeadSize(partialTicks);
        float size = headSize * (chargeProgress = beam.getInterpolatedChargeProgress(partialTicks));
        if (size <= 0.01f) {
            return;
        }
        int innerColor = beam.getInnerColor();
        int outerColor = beam.getOuterColor();
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        float pulseTime = (float)beam.field_70173_aa + partialTicks;
        float scaleModifier = (float)Math.sin(pulseTime * 0.2f) * 0.08f;
        float scale = size * (1.0f + scaleModifier);
        if (beam.hasLightningEffect()) {
            this.renderAttachedLightning(beam, 0.3f, scale);
        }
        GL11.glPushMatrix();
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        float innerScale = 0.6f;
        if (beam.isOuterColorEnabled()) {
            float outerScale = innerScale + beam.getOuterColorWidth();
            float outerAlpha = beam.getOuterColorAlpha() * proximityAlpha;
            GL11.glDepthMask((boolean)false);
            GL11.glPushMatrix();
            GL11.glScalef((float)outerScale, (float)outerScale, (float)outerScale);
            this.renderCube(outerColor, outerAlpha, 0.5f);
            GL11.glPopMatrix();
            GL11.glDepthMask((boolean)true);
        }
        GL11.glScalef((float)innerScale, (float)innerScale, (float)innerScale);
        this.renderCube(innerColor, beam.getInnerAlpha() * proximityAlpha, 0.5f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    private void renderTailOrb(float size, int innerColor, int outerColor, boolean outerColorEnabled, float outerColorWidth, float outerAlpha, float innerAlpha) {
        GL11.glPushMatrix();
        float innerScale = 0.6f;
        if (outerColorEnabled) {
            float outerScale = innerScale + outerColorWidth;
            GL11.glDepthMask((boolean)false);
            GL11.glPushMatrix();
            GL11.glScalef((float)(size * outerScale), (float)(size * outerScale), (float)(size * outerScale));
            this.renderCube(outerColor, outerAlpha, 0.5f);
            GL11.glPopMatrix();
            GL11.glDepthMask((boolean)true);
        }
        GL11.glPushMatrix();
        GL11.glScalef((float)(size * innerScale), (float)(size * innerScale), (float)(size * innerScale));
        this.renderCube(innerColor, innerAlpha, 0.5f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    private void renderSmoothTrail(List<Vec3> trail, float width, int innerColor, int outerColor, boolean outerColorEnabled, float outerColorWidth, float innerAlpha) {
        if (trail.size() < 2) {
            return;
        }
        int trailSize = trail.size();
        List<double[]> perpFrames = this.computePerpendiculars(trail);
        float innerScale = 0.6f;
        float innerWidth = width * innerScale;
        if (outerColorEnabled) {
            float outerWidth = innerWidth + outerColorWidth * width;
            GL11.glDepthMask((boolean)false);
            this.renderTrailTube(trail, perpFrames, outerWidth, outerColor, 0.3f, true);
            GL11.glDepthMask((boolean)true);
        }
        this.renderTrailTube(trail, perpFrames, innerWidth, innerColor, innerAlpha, false);
    }

    private void renderFadingTrail(EntityAbilityBeam beam, List<Vec3> trail, float width, int innerColor, int outerColor, boolean outerColorEnabled, float outerColorWidth, float innerAlpha) {
        if (trail.size() < 2) {
            return;
        }
        List<Integer> ages = beam.getTrailPointAges();
        int fadeTime = beam.getTrailFadeTime();
        List<double[]> perpFrames = this.computePerpendiculars(trail);
        float innerScale = 0.6f;
        float innerWidth = width * innerScale;
        if (outerColorEnabled) {
            float outerWidth = innerWidth + outerColorWidth * width;
            GL11.glDepthMask((boolean)false);
            this.renderFadingTrailTube(trail, perpFrames, ages, fadeTime, outerWidth, outerColor, 0.3f);
            GL11.glDepthMask((boolean)true);
        }
        this.renderFadingTrailTube(trail, perpFrames, ages, fadeTime, innerWidth, innerColor, innerAlpha);
    }

    private void renderFadingTrailTube(List<Vec3> trail, List<double[]> perpFrames, List<Integer> ages, int fadeTime, float width, int color, float baseAlpha) {
        float[] rgb = this.extractRGB(color);
        float r = rgb[0];
        float g = rgb[1];
        float b = rgb[2];
        float halfWidth = width * 0.5f;
        int size = trail.size();
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78382_b();
        for (int i = 0; i < size - 1; ++i) {
            Vec3 p1 = trail.get(i);
            Vec3 p2 = trail.get(i + 1);
            double[] frame1 = perpFrames.get(i);
            double[] frame2 = perpFrames.get(i + 1);
            float age1 = i < ages.size() ? (float)ages.get(i).intValue() : 0.0f;
            float age2 = i + 1 < ages.size() ? (float)ages.get(i + 1).intValue() : 0.0f;
            float fade1 = fadeTime > 0 ? 1.0f - Math.min(1.0f, age1 / (float)fadeTime) : 0.0f;
            float fade2 = fadeTime > 0 ? 1.0f - Math.min(1.0f, age2 / (float)fadeTime) : 0.0f;
            float alpha1 = baseAlpha * fade1;
            float alpha2 = baseAlpha * fade2;
            if (alpha1 <= 0.01f && alpha2 <= 0.01f) continue;
            double s1x = p1.field_72450_a - frame1[0] * (double)halfWidth - frame1[3] * (double)halfWidth;
            double s1y = p1.field_72448_b - frame1[1] * (double)halfWidth - frame1[4] * (double)halfWidth;
            double s1z = p1.field_72449_c - frame1[2] * (double)halfWidth - frame1[5] * (double)halfWidth;
            double s2x = p1.field_72450_a + frame1[0] * (double)halfWidth - frame1[3] * (double)halfWidth;
            double s2y = p1.field_72448_b + frame1[1] * (double)halfWidth - frame1[4] * (double)halfWidth;
            double s2z = p1.field_72449_c + frame1[2] * (double)halfWidth - frame1[5] * (double)halfWidth;
            double s3x = p1.field_72450_a + frame1[0] * (double)halfWidth + frame1[3] * (double)halfWidth;
            double s3y = p1.field_72448_b + frame1[1] * (double)halfWidth + frame1[4] * (double)halfWidth;
            double s3z = p1.field_72449_c + frame1[2] * (double)halfWidth + frame1[5] * (double)halfWidth;
            double s4x = p1.field_72450_a - frame1[0] * (double)halfWidth + frame1[3] * (double)halfWidth;
            double s4y = p1.field_72448_b - frame1[1] * (double)halfWidth + frame1[4] * (double)halfWidth;
            double s4z = p1.field_72449_c - frame1[2] * (double)halfWidth + frame1[5] * (double)halfWidth;
            double e1x = p2.field_72450_a - frame2[0] * (double)halfWidth - frame2[3] * (double)halfWidth;
            double e1y = p2.field_72448_b - frame2[1] * (double)halfWidth - frame2[4] * (double)halfWidth;
            double e1z = p2.field_72449_c - frame2[2] * (double)halfWidth - frame2[5] * (double)halfWidth;
            double e2x = p2.field_72450_a + frame2[0] * (double)halfWidth - frame2[3] * (double)halfWidth;
            double e2y = p2.field_72448_b + frame2[1] * (double)halfWidth - frame2[4] * (double)halfWidth;
            double e2z = p2.field_72449_c + frame2[2] * (double)halfWidth - frame2[5] * (double)halfWidth;
            double e3x = p2.field_72450_a + frame2[0] * (double)halfWidth + frame2[3] * (double)halfWidth;
            double e3y = p2.field_72448_b + frame2[1] * (double)halfWidth + frame2[4] * (double)halfWidth;
            double e3z = p2.field_72449_c + frame2[2] * (double)halfWidth + frame2[5] * (double)halfWidth;
            double e4x = p2.field_72450_a - frame2[0] * (double)halfWidth + frame2[3] * (double)halfWidth;
            double e4y = p2.field_72448_b - frame2[1] * (double)halfWidth + frame2[4] * (double)halfWidth;
            double e4z = p2.field_72449_c - frame2[2] * (double)halfWidth + frame2[5] * (double)halfWidth;
            tess.func_78369_a(r, g, b, alpha1);
            tess.func_78377_a(s1x, s1y, s1z);
            tess.func_78377_a(s2x, s2y, s2z);
            tess.func_78369_a(r, g, b, alpha2);
            tess.func_78377_a(e2x, e2y, e2z);
            tess.func_78377_a(e1x, e1y, e1z);
            tess.func_78369_a(r, g, b, alpha1);
            tess.func_78377_a(s4x, s4y, s4z);
            tess.func_78369_a(r, g, b, alpha2);
            tess.func_78377_a(e4x, e4y, e4z);
            tess.func_78377_a(e3x, e3y, e3z);
            tess.func_78369_a(r, g, b, alpha1);
            tess.func_78377_a(s3x, s3y, s3z);
            tess.func_78369_a(r, g, b, alpha1);
            tess.func_78377_a(s1x, s1y, s1z);
            tess.func_78369_a(r, g, b, alpha2);
            tess.func_78377_a(e1x, e1y, e1z);
            tess.func_78377_a(e4x, e4y, e4z);
            tess.func_78369_a(r, g, b, alpha1);
            tess.func_78377_a(s4x, s4y, s4z);
            tess.func_78369_a(r, g, b, alpha1);
            tess.func_78377_a(s2x, s2y, s2z);
            tess.func_78377_a(s3x, s3y, s3z);
            tess.func_78369_a(r, g, b, alpha2);
            tess.func_78377_a(e3x, e3y, e3z);
            tess.func_78377_a(e2x, e2y, e2z);
        }
        tess.func_78381_a();
    }

    private List<double[]> computePerpendiculars(List<Vec3> trail) {
        ArrayList<double[]> frames = new ArrayList<double[]>();
        int size = trail.size();
        for (int i = 0; i < size; ++i) {
            double horzZ;
            double horzY;
            double horzX;
            double horzLen;
            double dz;
            double dy;
            double dx;
            Vec3 p2;
            Vec3 p1;
            double[] frame = new double[6];
            if (i == 0) {
                p1 = trail.get(0);
                p2 = trail.get(1);
                dx = p2.field_72450_a - p1.field_72450_a;
                dy = p2.field_72448_b - p1.field_72448_b;
                dz = p2.field_72449_c - p1.field_72449_c;
            } else if (i == size - 1) {
                p1 = trail.get(size - 2);
                p2 = trail.get(size - 1);
                dx = p2.field_72450_a - p1.field_72450_a;
                dy = p2.field_72448_b - p1.field_72448_b;
                dz = p2.field_72449_c - p1.field_72449_c;
            } else {
                Vec3 p0 = trail.get(i - 1);
                Vec3 p12 = trail.get(i);
                Vec3 p22 = trail.get(i + 1);
                double dx1 = p12.field_72450_a - p0.field_72450_a;
                double dy1 = p12.field_72448_b - p0.field_72448_b;
                double dz1 = p12.field_72449_c - p0.field_72449_c;
                double dx2 = p22.field_72450_a - p12.field_72450_a;
                double dy2 = p22.field_72448_b - p12.field_72448_b;
                double dz2 = p22.field_72449_c - p12.field_72449_c;
                dx = (dx1 + dx2) * 0.5;
                dy = (dy1 + dy2) * 0.5;
                dz = (dz1 + dz2) * 0.5;
            }
            double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
            if (len > 0.001) {
                dx /= len;
                dy /= len;
                dz /= len;
            } else {
                dx = 0.0;
                dy = 0.0;
                dz = 1.0;
            }
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
            frame[0] = horzX;
            frame[1] = horzY;
            frame[2] = horzZ;
            frame[3] = vertX;
            frame[4] = vertY;
            frame[5] = vertZ;
            frames.add(frame);
        }
        return frames;
    }

    private void renderTrailTube(List<Vec3> trail, List<double[]> perpFrames, float width, int color, float baseAlpha, boolean fadeFromStart) {
        float[] rgb = this.extractRGB(color);
        float r = rgb[0];
        float g = rgb[1];
        float b = rgb[2];
        float halfWidth = width * 0.5f;
        int size = trail.size();
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78382_b();
        for (int i = 0; i < size - 1; ++i) {
            float alpha2;
            float alpha1;
            Vec3 p1 = trail.get(i);
            Vec3 p2 = trail.get(i + 1);
            double[] frame1 = perpFrames.get(i);
            double[] frame2 = perpFrames.get(i + 1);
            if (fadeFromStart) {
                float progress1 = (float)i / (float)size;
                float progress2 = (float)(i + 1) / (float)size;
                alpha1 = baseAlpha * progress1;
                alpha2 = baseAlpha * progress2;
            } else {
                alpha1 = baseAlpha;
                alpha2 = baseAlpha;
            }
            double s1x = p1.field_72450_a - frame1[0] * (double)halfWidth - frame1[3] * (double)halfWidth;
            double s1y = p1.field_72448_b - frame1[1] * (double)halfWidth - frame1[4] * (double)halfWidth;
            double s1z = p1.field_72449_c - frame1[2] * (double)halfWidth - frame1[5] * (double)halfWidth;
            double s2x = p1.field_72450_a + frame1[0] * (double)halfWidth - frame1[3] * (double)halfWidth;
            double s2y = p1.field_72448_b + frame1[1] * (double)halfWidth - frame1[4] * (double)halfWidth;
            double s2z = p1.field_72449_c + frame1[2] * (double)halfWidth - frame1[5] * (double)halfWidth;
            double s3x = p1.field_72450_a + frame1[0] * (double)halfWidth + frame1[3] * (double)halfWidth;
            double s3y = p1.field_72448_b + frame1[1] * (double)halfWidth + frame1[4] * (double)halfWidth;
            double s3z = p1.field_72449_c + frame1[2] * (double)halfWidth + frame1[5] * (double)halfWidth;
            double s4x = p1.field_72450_a - frame1[0] * (double)halfWidth + frame1[3] * (double)halfWidth;
            double s4y = p1.field_72448_b - frame1[1] * (double)halfWidth + frame1[4] * (double)halfWidth;
            double s4z = p1.field_72449_c - frame1[2] * (double)halfWidth + frame1[5] * (double)halfWidth;
            double e1x = p2.field_72450_a - frame2[0] * (double)halfWidth - frame2[3] * (double)halfWidth;
            double e1y = p2.field_72448_b - frame2[1] * (double)halfWidth - frame2[4] * (double)halfWidth;
            double e1z = p2.field_72449_c - frame2[2] * (double)halfWidth - frame2[5] * (double)halfWidth;
            double e2x = p2.field_72450_a + frame2[0] * (double)halfWidth - frame2[3] * (double)halfWidth;
            double e2y = p2.field_72448_b + frame2[1] * (double)halfWidth - frame2[4] * (double)halfWidth;
            double e2z = p2.field_72449_c + frame2[2] * (double)halfWidth - frame2[5] * (double)halfWidth;
            double e3x = p2.field_72450_a + frame2[0] * (double)halfWidth + frame2[3] * (double)halfWidth;
            double e3y = p2.field_72448_b + frame2[1] * (double)halfWidth + frame2[4] * (double)halfWidth;
            double e3z = p2.field_72449_c + frame2[2] * (double)halfWidth + frame2[5] * (double)halfWidth;
            double e4x = p2.field_72450_a - frame2[0] * (double)halfWidth + frame2[3] * (double)halfWidth;
            double e4y = p2.field_72448_b - frame2[1] * (double)halfWidth + frame2[4] * (double)halfWidth;
            double e4z = p2.field_72449_c - frame2[2] * (double)halfWidth + frame2[5] * (double)halfWidth;
            tess.func_78369_a(r, g, b, alpha1);
            tess.func_78377_a(s1x, s1y, s1z);
            tess.func_78377_a(s2x, s2y, s2z);
            tess.func_78369_a(r, g, b, alpha2);
            tess.func_78377_a(e2x, e2y, e2z);
            tess.func_78377_a(e1x, e1y, e1z);
            tess.func_78369_a(r, g, b, alpha1);
            tess.func_78377_a(s4x, s4y, s4z);
            tess.func_78369_a(r, g, b, alpha2);
            tess.func_78377_a(e4x, e4y, e4z);
            tess.func_78377_a(e3x, e3y, e3z);
            tess.func_78369_a(r, g, b, alpha1);
            tess.func_78377_a(s3x, s3y, s3z);
            tess.func_78369_a(r, g, b, alpha1);
            tess.func_78377_a(s1x, s1y, s1z);
            tess.func_78369_a(r, g, b, alpha2);
            tess.func_78377_a(e1x, e1y, e1z);
            tess.func_78377_a(e4x, e4y, e4z);
            tess.func_78369_a(r, g, b, alpha1);
            tess.func_78377_a(s4x, s4y, s4z);
            tess.func_78369_a(r, g, b, alpha1);
            tess.func_78377_a(s2x, s2y, s2z);
            tess.func_78377_a(s3x, s3y, s3z);
            tess.func_78369_a(r, g, b, alpha2);
            tess.func_78377_a(e3x, e3y, e3z);
            tess.func_78377_a(e2x, e2y, e2z);
        }
        tess.func_78381_a();
    }

    private void renderHead(EntityAbilityBeam beam, double x, double y, double z, float size, int innerColor, int outerColor, float outerAlpha, float innerAlpha, float partialTicks) {
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        float pulseTime = (float)beam.field_70173_aa + partialTicks;
        float scaleModifier = (float)Math.sin(pulseTime * 0.2f) * 0.1f;
        float scale = size * (1.0f + scaleModifier);
        if (beam.hasLightningEffect()) {
            this.renderAttachedLightning(beam, 0.3f, scale);
        }
        GL11.glRotatef((float)beam.getInterpolatedRotationX(partialTicks), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)beam.getInterpolatedRotationY(partialTicks), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)beam.getInterpolatedRotationZ(partialTicks), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glPushMatrix();
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        float innerScale = 0.6f;
        if (beam.isOuterColorEnabled()) {
            float outerScale = innerScale + beam.getOuterColorWidth();
            GL11.glDepthMask((boolean)false);
            GL11.glPushMatrix();
            GL11.glScalef((float)outerScale, (float)outerScale, (float)outerScale);
            this.renderCube(outerColor, outerAlpha, 0.5f);
            GL11.glPopMatrix();
            GL11.glDepthMask((boolean)true);
        }
        GL11.glScalef((float)innerScale, (float)innerScale, (float)innerScale);
        this.renderCube(innerColor, innerAlpha, 0.5f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}

