/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package kamkeel.npcs.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kamkeel.npcs.client.renderer.RenderEnergy;
import kamkeel.npcs.entity.EntityEnergyExplosion;
import net.minecraft.entity.Entity;
import noppes.npcs.config.ConfigClient;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderEnergyExplosion
extends RenderEnergy {
    private static final float MAX_OUTER_ALPHA = 0.85f;
    private static final float MAX_INNER_ALPHA = 1.0f;
    private static final float MAX_SMOKE_CUBE_ALPHA = 0.55f;

    public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        if (this.shouldSkipInitialActiveRender(entity)) {
            return;
        }
        if (!(entity instanceof EntityEnergyExplosion)) {
            return;
        }
        if (ConfigClient.LowResExplosion) {
            return;
        }
        EntityEnergyExplosion explosion = (EntityEnergyExplosion)entity;
        this.setupRenderState();
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        float radius = explosion.getInterpolatedRadius(partialTicks);
        float life = explosion.getLifeProgress(partialTicks);
        if (radius > 0.01f && life < 1.0f) {
            this.renderVoxelBurst(explosion, radius, life);
            if (explosion.hasLightningEffect()) {
                this.renderAttachedLightning(explosion, 0.55f, Math.max(0.7f, radius * 0.45f));
            }
        }
        GL11.glPopMatrix();
        this.restoreRenderState();
    }

    private void renderVoxelBurst(EntityEnergyExplosion explosion, float radius, float life) {
        float pz;
        float py;
        float px;
        int edge;
        int iz;
        int iy;
        int ix;
        int outerColor = explosion.getOuterColor();
        int innerColor = explosion.getInnerColor();
        boolean hasOuter = explosion.isOuterColorEnabled();
        float fade = 1.0f - life;
        float outerAlpha = Math.min(0.85f, (0.25f + fade * 0.75f) * explosion.getOuterColorAlpha());
        float innerAlpha = Math.min(1.0f, (0.3f + fade * 0.8f) * explosion.getInnerAlpha());
        int steps = Math.max(2, Math.min(6, (int)Math.ceil(radius * 0.65f) + 1));
        float cell = radius / (float)steps;
        float cubeHalf = Math.max(0.04f, cell * 0.22f * (0.9f - life * 0.25f));
        float outerScale = hasOuter ? 1.0f + explosion.getOuterColorWidth() * 0.35f : 1.0f;
        long seed = explosion.getRenderSeed();
        float outerR = (float)(outerColor >> 16 & 0xFF) / 255.0f;
        float outerG = (float)(outerColor >> 8 & 0xFF) / 255.0f;
        float outerB = (float)(outerColor & 0xFF) / 255.0f;
        float innerR = (float)(innerColor >> 16 & 0xFF) / 255.0f;
        float innerG = (float)(innerColor >> 8 & 0xFF) / 255.0f;
        float innerB = (float)(innerColor & 0xFF) / 255.0f;
        GL11.glDepthMask((boolean)false);
        this.renderSmokeVoxelShell(radius, life, seed);
        if (hasOuter) {
            float outerHalf = cubeHalf * outerScale;
            float oa = outerAlpha * fade;
            this.beginCubeBatch();
            for (ix = -steps; ix <= steps; ++ix) {
                for (iy = -steps; iy <= steps; ++iy) {
                    for (iz = -steps; iz <= steps; ++iz) {
                        edge = Math.max(Math.abs(ix), Math.max(Math.abs(iy), Math.abs(iz)));
                        if (edge != steps || !this.shouldRenderVoxel(ix, iy, iz, steps, seed, life)) continue;
                        px = (float)ix * cell + this.jitter(ix, iy, iz, seed, 1) * cell * 0.18f;
                        py = (float)iy * cell + this.jitter(ix, iy, iz, seed, 2) * cell * 0.18f;
                        pz = (float)iz * cell + this.jitter(ix, iy, iz, seed, 3) * cell * 0.18f;
                        this.addBatchedCube(px, py, pz, outerHalf, outerR, outerG, outerB, oa);
                    }
                }
            }
            this.endCubeBatch();
        }
        float innerHalf = cubeHalf * 0.9f;
        float ia = innerAlpha * fade;
        this.beginCubeBatch();
        for (ix = -steps; ix <= steps; ++ix) {
            for (iy = -steps; iy <= steps; ++iy) {
                for (iz = -steps; iz <= steps; ++iz) {
                    edge = Math.max(Math.abs(ix), Math.max(Math.abs(iy), Math.abs(iz)));
                    if (edge != steps || !this.shouldRenderVoxel(ix, iy, iz, steps, seed, life)) continue;
                    px = (float)ix * cell + this.jitter(ix, iy, iz, seed, 1) * cell * 0.18f;
                    py = (float)iy * cell + this.jitter(ix, iy, iz, seed, 2) * cell * 0.18f;
                    pz = (float)iz * cell + this.jitter(ix, iy, iz, seed, 3) * cell * 0.18f;
                    this.addBatchedCube(px, py, pz, innerHalf, innerR, innerG, innerB, ia);
                }
            }
        }
        this.endCubeBatch();
        this.renderCoreFlash(innerColor, innerAlpha, radius, cubeHalf, life);
        GL11.glDepthMask((boolean)true);
    }

    private void renderSmokeVoxelShell(float radius, float life, long seed) {
        int smokeSteps = Math.max(2, Math.min(5, (int)Math.ceil(radius * 0.5f) + 1));
        float smokeCell = radius / (float)smokeSteps * 1.2f;
        float smokeJitter = smokeCell * 0.28f;
        float smokeCubeHalf = Math.max(0.07f, smokeCell * 0.24f * (0.95f - life * 0.25f));
        float smokeAlpha = Math.min(0.55f, 0.15f + (1.0f - life) * 0.55f);
        this.beginCubeBatch();
        for (int ix = -smokeSteps; ix <= smokeSteps; ++ix) {
            for (int iy = -smokeSteps; iy <= smokeSteps; ++iy) {
                for (int iz = -smokeSteps; iz <= smokeSteps; ++iz) {
                    int h;
                    int edge = Math.max(Math.abs(ix), Math.max(Math.abs(iy), Math.abs(iz)));
                    if (edge < smokeSteps - 1 || ((h = this.hash(ix * 5, iy * 7, iz * 11, seed ^ 0x4F1BBCDCL)) & 3) != 0 || life > 0.72f && (h >>> 5 & 7) > 2) continue;
                    float px = (float)ix * smokeCell + this.jitter(ix, iy, iz, seed ^ 0x2299AA11L, 1) * smokeJitter;
                    float py = (float)iy * smokeCell + this.jitter(ix, iy, iz, seed ^ 0x2299AA11L, 2) * smokeJitter;
                    float pz = (float)iz * smokeCell + this.jitter(ix, iy, iz, seed ^ 0x2299AA11L, 3) * smokeJitter;
                    boolean isWhite = (h >>> 9 & 1) == 0;
                    float gray = isWhite ? 1.0f : 0.863f;
                    float alpha = smokeAlpha * (0.78f + (float)(h >>> 10 & 0xF) / 15.0f * 0.22f);
                    this.addBatchedCube(px, py, pz, smokeCubeHalf, gray, gray, gray, alpha);
                }
            }
        }
        this.endCubeBatch();
    }

    private void renderCoreFlash(int innerColor, float innerAlpha, float radius, float cubeHalf, float life) {
        float flash = this.saturate((0.6f - life) / 0.6f);
        if (flash <= 0.0f) {
            return;
        }
        float coreHalf = Math.max(cubeHalf, radius * 0.07f) * (1.0f + flash * 0.55f);
        float whiteAlpha = Math.min(1.0f, 0.25f + flash * 0.95f);
        float innerFlashAlpha = Math.min(1.0f, innerAlpha * (0.45f + flash * 0.95f));
        this.renderCube(0xFFFFFF, whiteAlpha, coreHalf * 1.45f);
        this.renderCube(innerColor, innerFlashAlpha, coreHalf * 0.82f);
    }

    private float saturate(float value) {
        if (value < 0.0f) {
            return 0.0f;
        }
        if (value > 1.0f) {
            return 1.0f;
        }
        return value;
    }

    private boolean shouldRenderVoxel(int ix, int iy, int iz, int steps, long seed, float life) {
        int decay;
        int keepMask;
        int h = this.hash(ix, iy, iz, seed);
        int n = keepMask = steps >= 5 ? 3 : 1;
        if ((h & keepMask) != 0) {
            return false;
        }
        return !(life > 0.55f) || (decay = h >>> 3 & 7) <= 2;
    }

    private float jitter(int ix, int iy, int iz, long seed, int channel) {
        int h = this.hash(ix + channel * 17, iy - channel * 31, iz + channel * 47, seed);
        return (float)(h >>> 8 & 0x3FF) / 1023.0f - 0.5f;
    }

    private int hash(int x, int y, int z, long seed) {
        long h = seed;
        h ^= (long)x * 73428767L;
        h ^= (long)y * 912931L;
        h ^= (long)z * 19349663L;
        h ^= h >>> 33;
        h *= -49064778989728563L;
        h ^= h >>> 33;
        return (int)h;
    }
}

