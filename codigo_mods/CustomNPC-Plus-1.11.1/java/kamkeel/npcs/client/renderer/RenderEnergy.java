/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package kamkeel.npcs.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kamkeel.npcs.client.renderer.lightning.AttachedLightningRenderer;
import kamkeel.npcs.entity.EntityEnergyAbility;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.config.ConfigClient;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public abstract class RenderEnergy
extends Render {
    protected static final ResourceLocation WHITE_TEXTURE = new ResourceLocation("customnpcs", "textures/entity/white.png");
    protected static final int HIDE_INITIAL_ACTIVE_TICKS = 1;

    public RenderEnergy() {
        this.field_76989_e = 0.0f;
    }

    protected boolean shouldSkipInitialActiveRender(Entity entity) {
        if (!(entity instanceof EntityEnergyAbility)) {
            return false;
        }
        EntityEnergyAbility ability = (EntityEnergyAbility)entity;
        return !ability.isPreviewMode() && !ability.isCharging() && ability.field_70173_aa <= 1;
    }

    protected void setupRenderState() {
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)24896);
        GL11.glDisable((int)2884);
        GL11.glEnable((int)32826);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glDisable((int)2896);
        GL11.glDisable((int)3553);
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)240.0f);
    }

    protected void restoreRenderState() {
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    protected float[] extractRGB(int color) {
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        return new float[]{r, g, b};
    }

    protected void renderCube(int color, float alpha, float halfSize) {
        Tessellator tess = Tessellator.field_78398_a;
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        tess.func_78382_b();
        tess.func_78369_a(r, g, b, alpha);
        RenderEnergy.addCubeVertices(tess, 0.0f, 0.0f, 0.0f, halfSize);
        tess.func_78381_a();
    }

    protected void beginCubeBatch() {
        Tessellator.field_78398_a.func_78382_b();
    }

    protected void addBatchedCube(float px, float py, float pz, float halfSize, float r, float g, float b, float alpha) {
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78369_a(r, g, b, alpha);
        RenderEnergy.addCubeVertices(tess, px, py, pz, halfSize);
    }

    protected void endCubeBatch() {
        Tessellator.field_78398_a.func_78381_a();
    }

    private static void addCubeVertices(Tessellator tess, float px, float py, float pz, float h) {
        float x0 = px - h;
        float x1 = px + h;
        float y0 = py - h;
        float y1 = py + h;
        float z0 = pz - h;
        float z1 = pz + h;
        tess.func_78375_b(0.0f, 0.0f, 1.0f);
        tess.func_78377_a((double)x0, (double)y0, (double)z1);
        tess.func_78377_a((double)x1, (double)y0, (double)z1);
        tess.func_78377_a((double)x1, (double)y1, (double)z1);
        tess.func_78377_a((double)x0, (double)y1, (double)z1);
        tess.func_78375_b(0.0f, 0.0f, -1.0f);
        tess.func_78377_a((double)x1, (double)y0, (double)z0);
        tess.func_78377_a((double)x0, (double)y0, (double)z0);
        tess.func_78377_a((double)x0, (double)y1, (double)z0);
        tess.func_78377_a((double)x1, (double)y1, (double)z0);
        tess.func_78375_b(0.0f, 1.0f, 0.0f);
        tess.func_78377_a((double)x0, (double)y1, (double)z1);
        tess.func_78377_a((double)x1, (double)y1, (double)z1);
        tess.func_78377_a((double)x1, (double)y1, (double)z0);
        tess.func_78377_a((double)x0, (double)y1, (double)z0);
        tess.func_78375_b(0.0f, -1.0f, 0.0f);
        tess.func_78377_a((double)x0, (double)y0, (double)z0);
        tess.func_78377_a((double)x1, (double)y0, (double)z0);
        tess.func_78377_a((double)x1, (double)y0, (double)z1);
        tess.func_78377_a((double)x0, (double)y0, (double)z1);
        tess.func_78375_b(1.0f, 0.0f, 0.0f);
        tess.func_78377_a((double)x1, (double)y0, (double)z1);
        tess.func_78377_a((double)x1, (double)y0, (double)z0);
        tess.func_78377_a((double)x1, (double)y1, (double)z0);
        tess.func_78377_a((double)x1, (double)y1, (double)z1);
        tess.func_78375_b(-1.0f, 0.0f, 0.0f);
        tess.func_78377_a((double)x0, (double)y0, (double)z0);
        tess.func_78377_a((double)x0, (double)y0, (double)z1);
        tess.func_78377_a((double)x0, (double)y1, (double)z1);
        tess.func_78377_a((double)x0, (double)y1, (double)z0);
    }

    protected void renderDisc(int color, float alpha, float radius, float thickness, int segments) {
        float z2;
        float x2;
        float z1;
        float x1;
        float angle2;
        float angle1;
        int i;
        float[] rgb = this.extractRGB(color);
        float r = rgb[0];
        float g = rgb[1];
        float b = rgb[2];
        Tessellator tess = Tessellator.field_78398_a;
        float halfThick = thickness * 0.5f;
        tess.func_78382_b();
        tess.func_78369_a(r, g, b, alpha);
        tess.func_78375_b(0.0f, 1.0f, 0.0f);
        for (i = 0; i < segments; ++i) {
            angle1 = (float)(Math.PI * 2 * (double)i / (double)segments);
            angle2 = (float)(Math.PI * 2 * (double)(i + 1) / (double)segments);
            x1 = (float)Math.cos(angle1) * radius;
            z1 = (float)Math.sin(angle1) * radius;
            x2 = (float)Math.cos(angle2) * radius;
            z2 = (float)Math.sin(angle2) * radius;
            tess.func_78377_a(0.0, (double)halfThick, 0.0);
            tess.func_78377_a((double)x1, (double)halfThick, (double)z1);
            tess.func_78377_a((double)x2, (double)halfThick, (double)z2);
            tess.func_78377_a(0.0, (double)halfThick, 0.0);
        }
        tess.func_78381_a();
        tess.func_78382_b();
        tess.func_78369_a(r, g, b, alpha);
        tess.func_78375_b(0.0f, -1.0f, 0.0f);
        for (i = 0; i < segments; ++i) {
            angle1 = (float)(Math.PI * 2 * (double)i / (double)segments);
            angle2 = (float)(Math.PI * 2 * (double)(i + 1) / (double)segments);
            x1 = (float)Math.cos(angle1) * radius;
            z1 = (float)Math.sin(angle1) * radius;
            x2 = (float)Math.cos(angle2) * radius;
            z2 = (float)Math.sin(angle2) * radius;
            tess.func_78377_a(0.0, (double)(-halfThick), 0.0);
            tess.func_78377_a((double)x2, (double)(-halfThick), (double)z2);
            tess.func_78377_a((double)x1, (double)(-halfThick), (double)z1);
            tess.func_78377_a(0.0, (double)(-halfThick), 0.0);
        }
        tess.func_78381_a();
        tess.func_78382_b();
        tess.func_78369_a(r, g, b, alpha);
        for (i = 0; i < segments; ++i) {
            angle1 = (float)(Math.PI * 2 * (double)i / (double)segments);
            angle2 = (float)(Math.PI * 2 * (double)(i + 1) / (double)segments);
            x1 = (float)Math.cos(angle1) * radius;
            z1 = (float)Math.sin(angle1) * radius;
            x2 = (float)Math.cos(angle2) * radius;
            z2 = (float)Math.sin(angle2) * radius;
            float nx = (float)Math.cos((double)(angle1 + angle2) * 0.5);
            float nz = (float)Math.sin((double)(angle1 + angle2) * 0.5);
            tess.func_78375_b(nx, 0.0f, nz);
            tess.func_78377_a((double)x1, (double)(-halfThick), (double)z1);
            tess.func_78377_a((double)x2, (double)(-halfThick), (double)z2);
            tess.func_78377_a((double)x2, (double)halfThick, (double)z2);
            tess.func_78377_a((double)x1, (double)halfThick, (double)z1);
        }
        tess.func_78381_a();
    }

    protected void renderLineSegment(double x1, double y1, double z1, double x2, double y2, double z2, float width, int color, float alpha) {
        double perpZ;
        double perpY;
        double perpX;
        float[] rgb = this.extractRGB(color);
        float r = rgb[0];
        float g = rgb[1];
        float b = rgb[2];
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;
        double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
        if (len < 0.001) {
            return;
        }
        if (Math.abs(dy) < 0.9) {
            perpX = -dz;
            perpY = 0.0;
            perpZ = dx;
        } else {
            perpX = 0.0;
            perpY = dz;
            perpZ = -dy;
        }
        double perpLen = Math.sqrt(perpX * perpX + perpY * perpY + perpZ * perpZ);
        if (perpLen > 0.0) {
            perpX = perpX / perpLen * (double)width * 0.5;
            perpY = perpY / perpLen * (double)width * 0.5;
            perpZ = perpZ / perpLen * (double)width * 0.5;
        }
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78382_b();
        tess.func_78369_a(r, g, b, alpha);
        tess.func_78377_a(x1 - perpX, y1 - perpY, z1 - perpZ);
        tess.func_78377_a(x2 - perpX, y2 - perpY, z2 - perpZ);
        tess.func_78377_a(x2 + perpX, y2 + perpY, z2 + perpZ);
        tess.func_78377_a(x1 + perpX, y1 + perpY, z1 + perpZ);
        tess.func_78381_a();
    }

    protected AttachedLightningRenderer.LightningState getLightningState(EntityEnergyAbility entity) {
        if (entity.lightningState == null) {
            entity.lightningState = new AttachedLightningRenderer.LightningState();
        }
        return (AttachedLightningRenderer.LightningState)entity.lightningState;
    }

    protected void renderAttachedLightning(EntityEnergyAbility entity, float innerScale, float baseSize) {
        int sparkTicks;
        AttachedLightningRenderer.LightningState state = this.getLightningState(entity);
        float density = entity.getLightningDensity();
        float innerRadius = innerScale * baseSize;
        float radius = innerRadius + entity.getLightningRadius() * baseSize;
        int outerColor = entity.getOuterColor();
        int innerColor = entity.getInnerColor();
        int fadeTime = entity.getLightningFadeTime();
        if (entity instanceof EntityEnergyProjectile && (sparkTicks = ((EntityEnergyProjectile)entity).getBarrierSparkTicks()) > 0) {
            density = Math.max(density, 4.0f + (float)sparkTicks * 0.6f);
            radius = Math.max(radius, innerRadius + Math.max(0.35f, baseSize * 0.65f));
            outerColor = 0xF0F6FF;
            innerColor = 0xFFFFFF;
            fadeTime = Math.max(fadeTime, 6);
        }
        state.update(density, radius, outerColor, innerColor, fadeTime);
        state.render();
    }

    protected float getProximityAlphaFactor(EntityEnergyAbility entity, double x, double y, double z) {
        return this.getProximityAlphaFactor(entity, x, y, z, false);
    }

    protected float getProximityAlphaFactor(EntityEnergyAbility entity, double x, double y, double z, boolean alwaysUseProximity) {
        int ageTicks;
        float minAlpha = ConfigClient.ProximityAlphaMin;
        if (minAlpha >= 1.0f) {
            return 1.0f;
        }
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        if (player == null || entity.getOwnerEntityId() != player.func_145782_y()) {
            return 1.0f;
        }
        float maxDist = ConfigClient.ProximityAlphaDistance;
        float dist = (float)Math.sqrt(x * x + y * y + z * z);
        float distFactor = Math.min(1.0f, dist / maxDist);
        float ageFactor = entity.isCharging() || alwaysUseProximity ? 0.0f : ((ageTicks = ConfigClient.ProximityAlphaAgeTicks) <= 0 ? 1.0f : Math.min(1.0f, (float)entity.field_70173_aa / (float)ageTicks));
        float combinedFactor = Math.max(distFactor, ageFactor);
        return minAlpha + (1.0f - minAlpha) * combinedFactor;
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return WHITE_TEXTURE;
    }
}

