/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import kamkeel.npcs.client.renderer.lightning.AttachedLightningRenderer;
import kamkeel.npcs.entity.EntityAbilityZone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.fx.CustomFX;
import noppes.npcs.client.fx.ZoneParticleFX;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderZone
extends Render {
    private static final int CIRCLE_SEGMENTS = 32;
    private static final int ACCENT_LINE_COUNT = 12;
    private static final Random RANDOM = new Random();
    private final Map<Integer, Integer> lastParticleTick = new HashMap<Integer, Integer>();
    private final Map<Integer, AttachedLightningRenderer.LightningState> lightningStates = new HashMap<Integer, AttachedLightningRenderer.LightningState>();
    private static final int TRIGGER_REVEAL_TICKS = 10;

    public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        EntityAbilityZone zone = (EntityAbilityZone)entity;
        Ctx c = new Ctx();
        c.zone = zone;
        c.shape = zone.getShape();
        c.radius = zone.getRadius();
        c.height = zone.getZoneHeight();
        int inner = zone.getInnerColor();
        c.ir = (float)(inner >> 16 & 0xFF) / 255.0f;
        c.ig = (float)(inner >> 8 & 0xFF) / 255.0f;
        c.ib = (float)(inner & 0xFF) / 255.0f;
        int outer = zone.getOuterColor();
        c.outerR = (float)(outer >> 16 & 0xFF) / 255.0f;
        c.outerG = (float)(outer >> 8 & 0xFF) / 255.0f;
        c.outerB = (float)(outer & 0xFF) / 255.0f;
        c.outerEnabled = zone.isOuterColorEnabled();
        c.particleDensity = zone.getParticleDensity();
        c.particleScale = zone.getParticleScale();
        c.animSpeed = zone.getAnimSpeed();
        c.lightningDensity = zone.getLightningDensity();
        c.groundFill = zone.isGroundFill();
        c.groundAlpha = zone.getGroundAlpha();
        c.rings = zone.isRings();
        c.ringCount = zone.getRingCount();
        c.border = zone.isBorder();
        c.borderSpeed = zone.getBorderSpeed();
        c.accents = zone.isAccents();
        c.accentStyle = zone.getAccentStyle();
        c.lightning = zone.isLightning();
        c.particles = zone.isParticles();
        c.particleMotion = zone.getParticleMotion();
        c.particleDir = zone.getParticleDir();
        c.particleSize = zone.getParticleSize();
        c.particleGlow = zone.isParticleGlow();
        c.time = (float)zone.field_70173_aa + partialTicks;
        c.pulse = c.time * 0.1f * c.animSpeed;
        c.rotation = c.time * 1.5f * c.animSpeed;
        c.brightness = 1.0f;
        c.trapVisible = true;
        c.triggerReveal = false;
        c.revealFade = 0.0f;
        if (zone.getZoneType() == EntityAbilityZone.ZoneType.TRAP) {
            float ticksSinceTrigger;
            c.trapVisible = zone.isVisible();
            if (zone.getTriggerFlashTick() >= 0 && (ticksSinceTrigger = (float)(zone.field_70173_aa - zone.getTriggerFlashTick()) + partialTicks) < 10.0f) {
                c.triggerReveal = true;
                c.revealFade = Math.max(0.0f, 1.0f - ticksSinceTrigger / 10.0f);
            }
            if (!c.trapVisible && !c.triggerReveal) {
                return;
            }
            if (!zone.isArmed()) {
                c.brightness = 0.4f;
            } else {
                c.pulse = c.time * 0.2f;
            }
        }
        c.flash = c.triggerReveal && c.revealFade > 0.8f;
        c.newTick = this.isNewTick(zone);
        this.setupRenderState();
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        GL11.glDepthMask((boolean)false);
        this.renderZone(c);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
        this.restoreRenderState();
    }

    private boolean isNewTick(EntityAbilityZone zone) {
        int id = zone.func_145782_y();
        int tick = zone.field_70173_aa;
        Integer last = this.lastParticleTick.get(id);
        if (last == null || last != tick) {
            this.lastParticleTick.put(id, tick);
            return true;
        }
        return false;
    }

    private AttachedLightningRenderer.LightningState getLightningState(EntityAbilityZone zone) {
        int id = zone.func_145782_y();
        AttachedLightningRenderer.LightningState state = this.lightningStates.get(id);
        if (state == null) {
            state = new AttachedLightningRenderer.LightningState();
            this.lightningStates.put(id, state);
        }
        return state;
    }

    private void renderZone(Ctx c) {
        if (c.triggerReveal) {
            c.brightness = c.revealFade;
            c.groundFill = true;
            c.groundAlpha = 0.35f * c.revealFade;
            c.rings = true;
            c.ringCount = 3;
            c.accents = true;
            c.accentStyle = 2;
            c.border = true;
        }
        float rR = this.ringR(c);
        float rG = this.ringG(c);
        float rB = this.ringB(c);
        if (c.groundFill) {
            if (c.flash) {
                this.renderShapeFill(c.shape, c.radius, 1.0f, 1.0f, 1.0f, 0.5f);
            } else {
                this.renderShapeFill(c.shape, c.radius, c.ir, c.ig, c.ib, c.groundAlpha * c.brightness);
            }
        }
        if (c.rings) {
            int count = Math.max(1, Math.min(5, c.ringCount));
            for (int i = 0; i < count; ++i) {
                float frac = ((float)i + 1.0f) / (float)(count + 1);
                float ra = (0.4f + 0.3f * RenderZone.sin((double)c.pulse + (double)i * Math.PI / 3.0)) * c.brightness;
                float yOff = 0.02f + (float)i * 0.01f;
                this.renderRingLine(c.shape, c.radius * frac, rR, rG, rB, ra, yOff);
            }
            float edgeA = (0.5f + 0.4f * RenderZone.sin((double)c.pulse + (double)count * Math.PI / 3.0)) * c.brightness;
            this.renderRingLine(c.shape, c.radius, rR, rG, rB, edgeA, 0.02f + (float)count * 0.01f);
        }
        if (c.border) {
            this.renderRotatingBorder(c.shape, c.radius, rR, rG, rB, 0.7f * c.brightness, c.rotation * c.borderSpeed, 0.05f);
        }
        if (c.accents) {
            float aa = 0.5f * c.brightness * (0.3f + 0.2f * RenderZone.sin(c.pulse * 0.7f));
            EntityAbilityZone.AccentStyle style = EntityAbilityZone.AccentStyle.values()[Math.max(0, Math.min(c.accentStyle, EntityAbilityZone.AccentStyle.values().length - 1))];
            switch (style) {
                case SWAYING: {
                    this.renderSwayingAccents(c, c.ir, c.ig, c.ib, aa, c.height);
                    break;
                }
                case FLICKERING: {
                    this.renderFlickeringAccents(c, c.ir, c.ig, c.ib, aa, c.height);
                    break;
                }
                default: {
                    this.renderVerticalAccents(c.shape, c.radius, c.ir, c.ig, c.ib, aa, c.height, c.rotation * 0.3f);
                }
            }
        }
        if (c.lightning) {
            int innerColor = c.zone.getInnerColor();
            int outerColor = c.outerEnabled ? c.zone.getOuterColor() : innerColor;
            AttachedLightningRenderer.LightningState state = this.getLightningState(c.zone);
            if (c.newTick) {
                state.tick();
                float density = 0.4f * c.brightness * c.lightningDensity;
                if (RANDOM.nextFloat() < density) {
                    double[] p1 = this.randomPosInZone(c);
                    double[] p2 = this.randomPosInZone(c);
                    double y1 = RANDOM.nextDouble() * (double)c.height * 0.4;
                    double y2 = RANDOM.nextDouble() * (double)c.height * 0.4;
                    float disp = c.radius * 0.15f;
                    state.addArc(AttachedLightningRenderer.createArcBetween(p1[0], y1, p1[2], p2[0], y2, p2[2], disp, outerColor, innerColor, 6));
                }
                if (RANDOM.nextFloat() < density * 0.5f * c.lightningDensity) {
                    double[] edgePos = this.randomEdgePos(c);
                    double ex = edgePos[0];
                    double ez = edgePos[1];
                    float disp = c.radius * 0.12f;
                    state.addArc(AttachedLightningRenderer.createArcBetween(ex, 0.0, ez, ex + (RANDOM.nextDouble() - 0.5) * 0.5, (double)c.height * (0.3 + RANDOM.nextDouble() * 0.4), ez + (RANDOM.nextDouble() - 0.5) * 0.5, disp, outerColor, innerColor, 5));
                }
            }
            state.render();
            GL11.glDepthMask((boolean)false);
        }
        if (c.particles && c.newTick && c.particleDensity > 0.0f) {
            if (c.particleDir != null && !c.particleDir.isEmpty()) {
                if (c.particleDir.startsWith("mc:")) {
                    this.spawnVanillaParticle(c);
                } else {
                    this.spawnCustomFXParticle(c);
                }
            } else {
                this.spawnDefaultParticles(c);
            }
        }
    }

    private void spawnVanillaParticle(Ctx c) {
        String particleName = c.particleDir.substring(3);
        int count = Math.round(3.0f * c.particleDensity);
        for (int i = 0; i < count; ++i) {
            double[] pos = this.randomPosInZone(c);
            double[] motion = this.getMotionForStyle(c.particleMotion);
            c.zone.field_70170_p.func_72869_a(particleName, c.zone.field_70165_t + pos[0], c.zone.field_70163_u + pos[1], c.zone.field_70161_v + pos[2], motion[0], motion[1], motion[2]);
        }
    }

    private void spawnCustomFXParticle(Ctx c) {
        int count = Math.round(3.0f * c.particleDensity);
        for (int i = 0; i < count; ++i) {
            double[] pos = this.randomPosInZone(c);
            double[] motion = this.getMotionForStyle(c.particleMotion);
            CustomFX fx = new CustomFX(c.zone.field_70170_p, null, c.particleDir, c.zone.field_70165_t + pos[0], c.zone.field_70163_u + pos[1], c.zone.field_70161_v + pos[2], 0.0, 0.0, 0.0);
            fx.field_70159_w = motion[0];
            fx.field_70181_x = motion[1];
            fx.field_70179_y = motion[2];
            fx.setMaxAge(20 + RANDOM.nextInt(20));
            fx.field_70130_N = -1;
            fx.field_70131_O = -1;
            fx.scaleX1 = c.particleScale * 10.0f;
            fx.scaleY1 = c.particleScale * 10.0f;
            fx.scaleX2 = fx.scaleX1;
            fx.scaleY2 = fx.scaleY1;
            fx.scaleXRate = 0.0f;
            fx.scaleYRate = 0.0f;
            fx.HEXColor = c.zone.getInnerColor();
            fx.facePlayer = true;
            fx.glows = c.particleGlow;
            fx.field_70145_X = true;
            fx.alpha1 = 0.8f;
            fx.alpha2 = 0.0f;
            fx.alphaRate = -0.04f;
            Minecraft.func_71410_x().field_71452_i.func_78873_a((EntityFX)fx);
        }
    }

    private void spawnDefaultParticles(Ctx c) {
        int count = Math.round(3.0f * c.particleDensity);
        EntityAbilityZone.ParticleMotion motion = EntityAbilityZone.ParticleMotion.values()[Math.max(0, Math.min(c.particleMotion, EntityAbilityZone.ParticleMotion.values().length - 1))];
        for (int i = 0; i < count; ++i) {
            int age;
            float gravity;
            double mz;
            double my;
            double mx;
            double[] pos = this.randomPosInZone(c);
            switch (motion) {
                case DRIFTING: {
                    mx = (RANDOM.nextDouble() - 0.5) * 0.005;
                    my = 0.005 + RANDOM.nextDouble() * 0.01;
                    mz = (RANDOM.nextDouble() - 0.5) * 0.005;
                    gravity = 0.0f;
                    age = 40 + RANDOM.nextInt(20);
                    break;
                }
                case SPARKS: {
                    mx = (RANDOM.nextDouble() - 0.5) * 0.08;
                    my = RANDOM.nextDouble() * 0.06;
                    mz = (RANDOM.nextDouble() - 0.5) * 0.08;
                    gravity = 0.0f;
                    age = 4 + RANDOM.nextInt(6);
                    break;
                }
                default: {
                    mx = (RANDOM.nextDouble() - 0.5) * 0.01;
                    my = 0.01 + RANDOM.nextDouble() * 0.02;
                    mz = (RANDOM.nextDouble() - 0.5) * 0.01;
                    gravity = -0.01f;
                    age = 30 + RANDOM.nextInt(20);
                }
            }
            float scale = (1.5f + RANDOM.nextFloat() * 1.0f) * c.particleScale;
            this.spawnParticle(c.zone, pos[0], pos[1], pos[2], mx, my, mz, c.ir, c.ig, c.ib, 0.7f, scale, age, gravity, c.particleGlow);
        }
    }

    private double[] getMotionForStyle(int motionOrdinal) {
        EntityAbilityZone.ParticleMotion motion = EntityAbilityZone.ParticleMotion.values()[Math.max(0, Math.min(motionOrdinal, EntityAbilityZone.ParticleMotion.values().length - 1))];
        switch (motion) {
            case DRIFTING: {
                return new double[]{(RANDOM.nextDouble() - 0.5) * 0.005, 0.005 + RANDOM.nextDouble() * 0.01, (RANDOM.nextDouble() - 0.5) * 0.005};
            }
            case SPARKS: {
                return new double[]{(RANDOM.nextDouble() - 0.5) * 0.08, RANDOM.nextDouble() * 0.06, (RANDOM.nextDouble() - 0.5) * 0.08};
            }
        }
        return new double[]{(RANDOM.nextDouble() - 0.5) * 0.01, 0.01 + RANDOM.nextDouble() * 0.02, (RANDOM.nextDouble() - 0.5) * 0.01};
    }

    private float ringR(Ctx c) {
        return c.outerEnabled ? c.outerR : c.ir;
    }

    private float ringG(Ctx c) {
        return c.outerEnabled ? c.outerG : c.ig;
    }

    private float ringB(Ctx c) {
        return c.outerEnabled ? c.outerB : c.ib;
    }

    private static float sin(double v) {
        return (float)Math.sin(v);
    }

    private void renderSwayingAccents(Ctx c, float r, float g, float b, float a, float height) {
        if (height <= 0.1f) {
            return;
        }
        float rotRad = (float)Math.toRadians(c.rotation * 0.2f);
        Tessellator tess = Tessellator.field_78398_a;
        if (c.shape == EntityAbilityZone.ZoneShape.SQUARE) {
            this.renderSquareSwayingAccents(c, r, g, b, a, height, rotRad, tess);
        } else {
            for (int i = 0; i < 12; ++i) {
                float baseAngle = (float)(Math.PI * 2 * (double)i) / 12.0f + rotRad;
                double lx = Math.cos(baseAngle) * (double)c.radius;
                double lz = Math.sin(baseAngle) * (double)c.radius;
                float swayX = 0.15f * RenderZone.sin(c.time * 0.08f + (float)i * 1.7f);
                float swayZ = 0.15f * RenderZone.sin(c.time * 0.06f + (float)i * 2.3f);
                tess.func_78371_b(1);
                tess.func_78369_a(r, g, b, a);
                tess.func_78377_a(lx, 0.01, lz);
                tess.func_78369_a(r, g, b, 0.0f);
                tess.func_78377_a(lx + (double)swayX, (double)height, lz + (double)swayZ);
                tess.func_78381_a();
            }
        }
    }

    private void renderSquareSwayingAccents(Ctx c, float r, float g, float b, float a, float height, float rotRad, Tessellator tess) {
        int perSide = Math.max(1, 3);
        int idx = 0;
        for (int side = 0; side < 4; ++side) {
            for (int j = 0; j < perSide; ++j) {
                double lz;
                double lx;
                float frac = ((float)j + 0.5f) / (float)perSide;
                switch (side) {
                    case 0: {
                        lx = -c.radius + frac * 2.0f * c.radius;
                        lz = -c.radius;
                        break;
                    }
                    case 1: {
                        lx = c.radius;
                        lz = -c.radius + frac * 2.0f * c.radius;
                        break;
                    }
                    case 2: {
                        lx = c.radius - frac * 2.0f * c.radius;
                        lz = c.radius;
                        break;
                    }
                    default: {
                        lx = -c.radius;
                        lz = c.radius - frac * 2.0f * c.radius;
                    }
                }
                float swayX = 0.15f * RenderZone.sin(c.time * 0.08f + (float)idx * 1.7f);
                float swayZ = 0.15f * RenderZone.sin(c.time * 0.06f + (float)idx * 2.3f);
                tess.func_78371_b(1);
                tess.func_78369_a(r, g, b, a);
                tess.func_78377_a(lx, 0.01, lz);
                tess.func_78369_a(r, g, b, 0.0f);
                tess.func_78377_a(lx + (double)swayX, (double)height, lz + (double)swayZ);
                tess.func_78381_a();
                ++idx;
            }
        }
    }

    private void renderFlickeringAccents(Ctx c, float r, float g, float b, float baseAlpha, float height) {
        if (height <= 0.1f) {
            return;
        }
        float rotRad = (float)Math.toRadians(c.rotation * 0.4f);
        Tessellator tess = Tessellator.field_78398_a;
        if (c.shape == EntityAbilityZone.ZoneShape.SQUARE) {
            this.renderSquareFlickeringAccents(c, r, g, b, baseAlpha, height, tess);
        } else {
            for (int i = 0; i < 12; ++i) {
                float baseAngle = (float)(Math.PI * 2 * (double)i) / 12.0f + rotRad;
                double lx = Math.cos(baseAngle) * (double)c.radius;
                double lz = Math.sin(baseAngle) * (double)c.radius;
                float flicker = 0.3f + 0.7f * Math.abs(RenderZone.sin(c.time * 0.7f + (float)i * 3.14f));
                float lineAlpha = baseAlpha * flicker;
                float lineHeight = height * (0.6f + 0.4f * flicker);
                tess.func_78371_b(1);
                tess.func_78369_a(r, g, b, lineAlpha);
                tess.func_78377_a(lx, 0.01, lz);
                tess.func_78369_a(r, g, b, 0.0f);
                tess.func_78377_a(lx, (double)lineHeight, lz);
                tess.func_78381_a();
            }
        }
    }

    private void renderSquareFlickeringAccents(Ctx c, float r, float g, float b, float baseAlpha, float height, Tessellator tess) {
        int perSide = Math.max(1, 3);
        int idx = 0;
        for (int side = 0; side < 4; ++side) {
            for (int j = 0; j < perSide; ++j) {
                double lz;
                double lx;
                float frac = ((float)j + 0.5f) / (float)perSide;
                switch (side) {
                    case 0: {
                        lx = -c.radius + frac * 2.0f * c.radius;
                        lz = -c.radius;
                        break;
                    }
                    case 1: {
                        lx = c.radius;
                        lz = -c.radius + frac * 2.0f * c.radius;
                        break;
                    }
                    case 2: {
                        lx = c.radius - frac * 2.0f * c.radius;
                        lz = c.radius;
                        break;
                    }
                    default: {
                        lx = -c.radius;
                        lz = c.radius - frac * 2.0f * c.radius;
                    }
                }
                float flicker = 0.3f + 0.7f * Math.abs(RenderZone.sin(c.time * 0.7f + (float)idx * 3.14f));
                float lineAlpha = baseAlpha * flicker;
                float lineHeight = height * (0.6f + 0.4f * flicker);
                tess.func_78371_b(1);
                tess.func_78369_a(r, g, b, lineAlpha);
                tess.func_78377_a(lx, 0.01, lz);
                tess.func_78369_a(r, g, b, 0.0f);
                tess.func_78377_a(lx, (double)lineHeight, lz);
                tess.func_78381_a();
                ++idx;
            }
        }
    }

    private double[] randomPosInZone(Ctx c) {
        if (c.shape == EntityAbilityZone.ZoneShape.SQUARE) {
            double px = (RANDOM.nextDouble() * 2.0 - 1.0) * (double)c.radius;
            double pz = (RANDOM.nextDouble() * 2.0 - 1.0) * (double)c.radius;
            return new double[]{px, 0.0, pz};
        }
        double angle = RANDOM.nextDouble() * Math.PI * 2.0;
        double dist = Math.sqrt(RANDOM.nextDouble()) * (double)c.radius;
        double px = Math.cos(angle) * dist;
        double pz = Math.sin(angle) * dist;
        return new double[]{px, 0.0, pz};
    }

    private double[] randomEdgePos(Ctx c) {
        if (c.shape == EntityAbilityZone.ZoneShape.SQUARE) {
            int side = RANDOM.nextInt(4);
            double frac = RANDOM.nextDouble() * 2.0 - 1.0;
            switch (side) {
                case 0: {
                    return new double[]{frac * (double)c.radius, -c.radius};
                }
                case 1: {
                    return new double[]{frac * (double)c.radius, c.radius};
                }
                case 2: {
                    return new double[]{-c.radius, frac * (double)c.radius};
                }
            }
            return new double[]{c.radius, frac * (double)c.radius};
        }
        double angle = RANDOM.nextDouble() * Math.PI * 2.0;
        double dist = (double)c.radius * (0.5 + RANDOM.nextDouble() * 0.5);
        return new double[]{Math.cos(angle) * dist, Math.sin(angle) * dist};
    }

    private void spawnParticle(EntityAbilityZone zone, double offsetX, double offsetY, double offsetZ, double mx, double my, double mz, float r, float g, float b, float alpha, float scale, int maxAge, float gravity, boolean glow) {
        ZoneParticleFX particle = new ZoneParticleFX(zone.field_70170_p, zone.field_70165_t + offsetX, zone.field_70163_u + offsetY, zone.field_70161_v + offsetZ, mx, my, mz, r, g, b, alpha, scale, maxAge, gravity, glow);
        Minecraft.func_71410_x().field_71452_i.func_78873_a((EntityFX)particle);
    }

    private void setupRenderState() {
        GL11.glPushAttrib((int)1048575);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2884);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)2896);
        GL11.glShadeModel((int)7425);
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)240.0f);
    }

    private void restoreRenderState() {
        GL11.glShadeModel((int)7424);
        GL11.glEnable((int)2896);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2884);
        GL11.glEnable((int)3553);
        GL11.glPopAttrib();
    }

    private void renderShapeFill(EntityAbilityZone.ZoneShape shape, float radius, float r, float g, float b, float a) {
        GL11.glEnable((int)32823);
        GL11.glPolygonOffset((float)-1.0f, (float)-1.0f);
        switch (shape) {
            case CIRCLE: {
                this.renderCircleFill(radius, r, g, b, a);
                break;
            }
            case SQUARE: {
                this.renderSquareFill(radius, r, g, b, a);
            }
        }
        GL11.glDisable((int)32823);
    }

    private void renderCircleFill(float radius, float r, float g, float b, float a) {
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78371_b(6);
        tess.func_78369_a(r, g, b, a);
        tess.func_78377_a(0.0, 0.0, 0.0);
        for (int i = 0; i <= 32; ++i) {
            double angle = Math.PI * 2 * (double)i / 32.0;
            tess.func_78377_a(Math.cos(angle) * (double)radius, 0.0, Math.sin(angle) * (double)radius);
        }
        tess.func_78381_a();
    }

    private void renderSquareFill(float radius, float r, float g, float b, float a) {
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78371_b(7);
        tess.func_78369_a(r, g, b, a);
        tess.func_78377_a((double)(-radius), 0.0, (double)(-radius));
        tess.func_78377_a((double)radius, 0.0, (double)(-radius));
        tess.func_78377_a((double)radius, 0.0, (double)radius);
        tess.func_78377_a((double)(-radius), 0.0, (double)radius);
        tess.func_78381_a();
    }

    private void renderRingLine(EntityAbilityZone.ZoneShape shape, float radius, float r, float g, float b, float a, float yOffset) {
        switch (shape) {
            case CIRCLE: {
                this.renderCircleRingLine(radius, r, g, b, a, yOffset);
                break;
            }
            case SQUARE: {
                this.renderSquareRingLine(radius, r, g, b, a, yOffset);
            }
        }
    }

    private void renderCircleRingLine(float radius, float r, float g, float b, float a, float yOffset) {
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78371_b(2);
        tess.func_78369_a(r, g, b, a);
        for (int i = 0; i < 32; ++i) {
            double angle = Math.PI * 2 * (double)i / 32.0;
            tess.func_78377_a(Math.cos(angle) * (double)radius, (double)yOffset, Math.sin(angle) * (double)radius);
        }
        tess.func_78381_a();
    }

    private void renderSquareRingLine(float radius, float r, float g, float b, float a, float yOffset) {
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78371_b(2);
        tess.func_78369_a(r, g, b, a);
        tess.func_78377_a((double)(-radius), (double)yOffset, (double)(-radius));
        tess.func_78377_a((double)radius, (double)yOffset, (double)(-radius));
        tess.func_78377_a((double)radius, (double)yOffset, (double)radius);
        tess.func_78377_a((double)(-radius), (double)yOffset, (double)radius);
        tess.func_78381_a();
    }

    private void renderRotatingBorder(EntityAbilityZone.ZoneShape shape, float radius, float r, float g, float b, float a, float rotationDeg, float yOffset) {
        int dashCount = 16;
        if (shape == EntityAbilityZone.ZoneShape.SQUARE) {
            float perimeter = radius * 8.0f;
            float dashLen = perimeter / (float)dashCount;
            float offset = rotationDeg % 360.0f / 360.0f * perimeter;
            Tessellator tess = Tessellator.field_78398_a;
            for (int i = 0; i < dashCount; i += 2) {
                float startDist = ((float)i * dashLen + offset) % perimeter;
                float endDist = startDist + dashLen;
                tess.func_78371_b(3);
                tess.func_78369_a(r, g, b, a);
                int steps = 4;
                for (int s = 0; s <= steps; ++s) {
                    float d = startDist + (endDist - startDist) * (float)s / (float)steps;
                    double[] pos = this.perimeterPos(d % perimeter, radius);
                    tess.func_78377_a(pos[0], (double)yOffset, pos[1]);
                }
                tess.func_78381_a();
            }
        } else {
            float dashAngle = (float)Math.PI * 2 / (float)dashCount;
            float rotRad = (float)Math.toRadians(rotationDeg);
            Tessellator tess = Tessellator.field_78398_a;
            for (int i = 0; i < dashCount; i += 2) {
                float startAngle = (float)i * dashAngle + rotRad;
                float endAngle = startAngle + dashAngle;
                tess.func_78371_b(3);
                tess.func_78369_a(r, g, b, a);
                int steps = 4;
                for (int s = 0; s <= steps; ++s) {
                    float segAngle = startAngle + (endAngle - startAngle) * (float)s / (float)steps;
                    tess.func_78377_a(Math.cos(segAngle) * (double)radius, (double)yOffset, Math.sin(segAngle) * (double)radius);
                }
                tess.func_78381_a();
            }
        }
    }

    private double[] perimeterPos(float dist, float radius) {
        float sideLen = radius * 2.0f;
        if (dist < sideLen) {
            return new double[]{-radius + dist, -radius};
        }
        if ((dist -= sideLen) < sideLen) {
            return new double[]{radius, -radius + dist};
        }
        if ((dist -= sideLen) < sideLen) {
            return new double[]{radius - dist, radius};
        }
        return new double[]{-radius, radius - (dist -= sideLen)};
    }

    private void renderVerticalAccents(EntityAbilityZone.ZoneShape shape, float radius, float r, float g, float b, float a, float height, float rotationDeg) {
        if (height <= 0.1f) {
            return;
        }
        Tessellator tess = Tessellator.field_78398_a;
        if (shape == EntityAbilityZone.ZoneShape.SQUARE) {
            float[][] corners;
            int perSide = Math.max(1, 3);
            for (int side = 0; side < 4; ++side) {
                for (int j = 0; j < perSide; ++j) {
                    double lz;
                    double lx;
                    float frac = ((float)j + 0.5f) / (float)perSide;
                    switch (side) {
                        case 0: {
                            lx = -radius + frac * 2.0f * radius;
                            lz = -radius;
                            break;
                        }
                        case 1: {
                            lx = radius;
                            lz = -radius + frac * 2.0f * radius;
                            break;
                        }
                        case 2: {
                            lx = radius - frac * 2.0f * radius;
                            lz = radius;
                            break;
                        }
                        default: {
                            lx = -radius;
                            lz = radius - frac * 2.0f * radius;
                        }
                    }
                    tess.func_78371_b(1);
                    tess.func_78369_a(r, g, b, a);
                    tess.func_78377_a(lx, 0.01, lz);
                    tess.func_78369_a(r, g, b, 0.0f);
                    tess.func_78377_a(lx, (double)height, lz);
                    tess.func_78381_a();
                }
            }
            for (float[] corner : corners = new float[][]{{-radius, -radius}, {radius, -radius}, {radius, radius}, {-radius, radius}}) {
                tess.func_78371_b(1);
                tess.func_78369_a(r, g, b, a);
                tess.func_78377_a((double)corner[0], 0.01, (double)corner[1]);
                tess.func_78369_a(r, g, b, 0.0f);
                tess.func_78377_a((double)corner[0], (double)height, (double)corner[1]);
                tess.func_78381_a();
            }
        } else {
            float rotRad = (float)Math.toRadians(rotationDeg);
            for (int i = 0; i < 12; ++i) {
                float angle = (float)(Math.PI * 2 * (double)i) / 12.0f + rotRad;
                double lx = Math.cos(angle) * (double)radius;
                double lz = Math.sin(angle) * (double)radius;
                tess.func_78371_b(1);
                tess.func_78369_a(r, g, b, a);
                tess.func_78377_a(lx, 0.01, lz);
                tess.func_78369_a(r, g, b, 0.0f);
                tess.func_78377_a(lx, (double)height, lz);
                tess.func_78381_a();
            }
        }
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return null;
    }

    private static class Ctx {
        EntityAbilityZone zone;
        EntityAbilityZone.ZoneShape shape;
        float radius;
        float height;
        float ir;
        float ig;
        float ib;
        float outerR;
        float outerG;
        float outerB;
        boolean outerEnabled;
        float time;
        float pulse;
        float rotation;
        float brightness;
        boolean flash;
        boolean newTick;
        float particleDensity;
        float particleScale;
        float animSpeed;
        float lightningDensity;
        boolean groundFill;
        boolean rings;
        boolean border;
        boolean accents;
        boolean lightning;
        boolean particles;
        float groundAlpha;
        float borderSpeed;
        int ringCount;
        int accentStyle;
        int particleMotion;
        int particleSize;
        boolean particleGlow;
        String particleDir;
        boolean trapVisible;
        boolean triggerReveal;
        float revealFade;

        private Ctx() {
        }
    }
}

