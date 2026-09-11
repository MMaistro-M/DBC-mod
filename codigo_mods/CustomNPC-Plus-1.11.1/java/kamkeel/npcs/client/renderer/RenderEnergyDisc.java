/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.Vec3
 *  org.lwjgl.opengl.GL11
 */
package kamkeel.npcs.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kamkeel.npcs.client.renderer.RenderEnergy;
import kamkeel.npcs.entity.EntityAbilityDisc;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import noppes.npcs.util.MathUtil;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderEnergyDisc
extends RenderEnergy {
    public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        double motionZ;
        double motionY;
        double motionX;
        double speedSq;
        if (this.shouldSkipInitialActiveRender(entity)) {
            return;
        }
        EntityAbilityDisc disc = (EntityAbilityDisc)entity;
        this.setupRenderState();
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        float size = disc.getInterpolatedSize(partialTicks);
        float radius = disc.getInterpolatedDiscRadius(partialTicks) * size;
        float thickness = disc.getInterpolatedDiscThickness(partialTicks) * size;
        float pulseTime = (float)entity.field_70173_aa + partialTicks;
        float scaleModifier = (float)Math.sin(pulseTime * 0.1f) * 0.03f;
        float scale = 1.0f + scaleModifier;
        if (disc.hasLightningEffect()) {
            this.renderAttachedLightning(disc, 0.3f, radius * scale);
        }
        GL11.glPushMatrix();
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        if (disc.isVertical()) {
            GL11.glRotatef((float)disc.getTravelYaw(), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        }
        if ((speedSq = (motionX = entity.field_70159_w) * motionX + (motionY = entity.field_70181_x) * motionY + (motionZ = entity.field_70179_y) * motionZ) > 1.0E-4) {
            Vec3 motionVec = Vec3.func_72443_a((double)motionX, (double)motionY, (double)motionZ);
            float motionYaw = MathUtil.getYaw(motionVec);
            float motionPitch = MathUtil.getPitch(motionVec);
            GL11.glRotatef((float)motionYaw, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)motionPitch, (float)1.0f, (float)0.0f, (float)0.0f);
        }
        GL11.glRotatef((float)disc.getInterpolatedRotationY(partialTicks), (float)0.0f, (float)1.0f, (float)0.0f);
        float innerScale = 0.6f;
        float proximityAlpha = this.getProximityAlphaFactor(disc, x, y, z);
        if (disc.isOuterColorEnabled()) {
            float outerScale = innerScale + disc.getOuterColorWidth();
            GL11.glDepthMask((boolean)false);
            this.renderFlatSquare(disc.getOuterColor(), disc.getOuterColorAlpha() * proximityAlpha, radius * outerScale, thickness);
            GL11.glDepthMask((boolean)true);
        }
        this.renderFlatSquare(disc.getInnerColor(), disc.getInnerAlpha() * proximityAlpha, radius * innerScale, thickness * 1.2f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        this.restoreRenderState();
    }

    private void renderFlatSquare(int color, float alpha, float halfSize, float thickness) {
        float halfThick = thickness * 0.5f;
        this.renderCubeCustom(color, alpha, halfSize, halfThick, halfSize);
    }

    private void renderCubeCustom(int color, float alpha, float halfX, float halfY, float halfZ) {
        float[] rgb = this.extractRGB(color);
        float r = rgb[0];
        float g = rgb[1];
        float b = rgb[2];
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78382_b();
        tess.func_78369_a(r, g, b, alpha);
        tess.func_78375_b(0.0f, 1.0f, 0.0f);
        tess.func_78377_a((double)(-halfX), (double)halfY, (double)halfZ);
        tess.func_78377_a((double)halfX, (double)halfY, (double)halfZ);
        tess.func_78377_a((double)halfX, (double)halfY, (double)(-halfZ));
        tess.func_78377_a((double)(-halfX), (double)halfY, (double)(-halfZ));
        tess.func_78381_a();
        tess.func_78382_b();
        tess.func_78369_a(r, g, b, alpha);
        tess.func_78375_b(0.0f, -1.0f, 0.0f);
        tess.func_78377_a((double)(-halfX), (double)(-halfY), (double)(-halfZ));
        tess.func_78377_a((double)halfX, (double)(-halfY), (double)(-halfZ));
        tess.func_78377_a((double)halfX, (double)(-halfY), (double)halfZ);
        tess.func_78377_a((double)(-halfX), (double)(-halfY), (double)halfZ);
        tess.func_78381_a();
        tess.func_78382_b();
        tess.func_78369_a(r, g, b, alpha);
        tess.func_78375_b(0.0f, 0.0f, 1.0f);
        tess.func_78377_a((double)(-halfX), (double)(-halfY), (double)halfZ);
        tess.func_78377_a((double)halfX, (double)(-halfY), (double)halfZ);
        tess.func_78377_a((double)halfX, (double)halfY, (double)halfZ);
        tess.func_78377_a((double)(-halfX), (double)halfY, (double)halfZ);
        tess.func_78381_a();
        tess.func_78382_b();
        tess.func_78369_a(r, g, b, alpha);
        tess.func_78375_b(0.0f, 0.0f, -1.0f);
        tess.func_78377_a((double)halfX, (double)(-halfY), (double)(-halfZ));
        tess.func_78377_a((double)(-halfX), (double)(-halfY), (double)(-halfZ));
        tess.func_78377_a((double)(-halfX), (double)halfY, (double)(-halfZ));
        tess.func_78377_a((double)halfX, (double)halfY, (double)(-halfZ));
        tess.func_78381_a();
        tess.func_78382_b();
        tess.func_78369_a(r, g, b, alpha);
        tess.func_78375_b(1.0f, 0.0f, 0.0f);
        tess.func_78377_a((double)halfX, (double)(-halfY), (double)halfZ);
        tess.func_78377_a((double)halfX, (double)(-halfY), (double)(-halfZ));
        tess.func_78377_a((double)halfX, (double)halfY, (double)(-halfZ));
        tess.func_78377_a((double)halfX, (double)halfY, (double)halfZ);
        tess.func_78381_a();
        tess.func_78382_b();
        tess.func_78369_a(r, g, b, alpha);
        tess.func_78375_b(-1.0f, 0.0f, 0.0f);
        tess.func_78377_a((double)(-halfX), (double)(-halfY), (double)(-halfZ));
        tess.func_78377_a((double)(-halfX), (double)(-halfY), (double)halfZ);
        tess.func_78377_a((double)(-halfX), (double)halfY, (double)halfZ);
        tess.func_78377_a((double)(-halfX), (double)halfY, (double)(-halfZ));
        tess.func_78381_a();
    }
}

