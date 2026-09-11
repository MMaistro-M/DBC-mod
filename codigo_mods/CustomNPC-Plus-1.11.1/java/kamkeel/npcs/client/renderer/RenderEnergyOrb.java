/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.Vec3
 *  org.lwjgl.opengl.GL11
 */
package kamkeel.npcs.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kamkeel.npcs.client.renderer.RenderEnergy;
import kamkeel.npcs.entity.EntityAbilityOrb;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import noppes.npcs.util.MathUtil;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderEnergyOrb
extends RenderEnergy {
    private static final int LIGHTNING_FADE_TICKS = 6;

    public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        double motionZ;
        double motionY;
        double motionX;
        double speedSq;
        EntityAbilityOrb orb = (EntityAbilityOrb)entity;
        if (this.shouldSkipInitialActiveRender(entity)) {
            return;
        }
        this.setupRenderState();
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        float size = orb.getInterpolatedSize(partialTicks);
        float pulseTime = (float)entity.field_70173_aa + partialTicks;
        float scaleModifier = (float)Math.sin(pulseTime * 0.15f) * 0.05f;
        float scale = size * (1.0f + scaleModifier);
        if (orb.hasLightningEffect()) {
            this.renderAttachedLightning(orb, 0.3f, scale);
        }
        if ((speedSq = (motionX = entity.field_70159_w) * motionX + (motionY = entity.field_70181_x) * motionY + (motionZ = entity.field_70179_y) * motionZ) > 1.0E-4) {
            Vec3 motionVec = Vec3.func_72443_a((double)motionX, (double)motionY, (double)motionZ);
            float motionYaw = MathUtil.getYaw(motionVec);
            float motionPitch = MathUtil.getPitch(motionVec);
            GL11.glRotatef((float)motionYaw, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)motionPitch, (float)1.0f, (float)0.0f, (float)0.0f);
        }
        GL11.glRotatef((float)orb.getInterpolatedRotationX(partialTicks), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)orb.getInterpolatedRotationY(partialTicks), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)orb.getInterpolatedRotationZ(partialTicks), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glPushMatrix();
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        float innerScale = 0.6f;
        float proximityAlpha = this.getProximityAlphaFactor(orb, x, y, z);
        if (orb.isOuterColorEnabled()) {
            float outerScale = innerScale + orb.getOuterColorWidth();
            GL11.glDepthMask((boolean)false);
            GL11.glPushMatrix();
            GL11.glScalef((float)outerScale, (float)outerScale, (float)outerScale);
            this.renderCube(orb.getOuterColor(), orb.getOuterColorAlpha() * proximityAlpha, 0.5f);
            GL11.glPopMatrix();
            GL11.glDepthMask((boolean)true);
        }
        GL11.glScalef((float)innerScale, (float)innerScale, (float)innerScale);
        this.renderCube(orb.getInnerColor(), orb.getInnerAlpha() * proximityAlpha, 0.5f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        this.restoreRenderState();
    }
}

