/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  org.lwjgl.opengl.GL11
 */
package kamkeel.npcs.client.renderer.lightning;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import kamkeel.npcs.client.renderer.lightning.LightningBolt;
import kamkeel.npcs.client.renderer.lightning.Vec3d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class LightningHandler {
    private static final ResourceLocation TEXTURE_OUTER = new ResourceLocation("customnpcs", "textures/effects/lightning_outer.png");
    private static final ResourceLocation TEXTURE_INNER = new ResourceLocation("customnpcs", "textures/effects/lightning_inner.png");
    public static int lightningCount = 0;

    private static Vec3d getRelativeViewVector(Vec3d pos) {
        EntityLivingBase renderEntity = Minecraft.func_71410_x().field_71451_h;
        return new Vec3d((double)((float)renderEntity.field_70165_t) - pos.x, (double)((float)renderEntity.field_70163_u + renderEntity.func_70047_e()) - pos.y, (double)((float)renderEntity.field_70161_v) - pos.z);
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        float partialTicks = event.partialTicks;
        EntityClientPlayerMP entity = Minecraft.func_71410_x().field_71439_g;
        TextureManager textureManager = Minecraft.func_71410_x().field_71446_o;
        double interpPosX = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * (double)partialTicks;
        double interpPosY = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * (double)partialTicks;
        double interpPosZ = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * (double)partialTicks;
        if (LightningBolt.boltList.isEmpty()) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glTranslated((double)(-interpPosX), (double)(-interpPosY), (double)(-interpPosZ));
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2884);
        lightningCount = 0;
        for (LightningBolt bolt : LightningBolt.boltList) {
            this.renderBolt(bolt, tessellator, partialTicks, false);
        }
        for (LightningBolt bolt : LightningBolt.boltList) {
            this.renderBolt(bolt, tessellator, partialTicks, true);
        }
        GL11.glEnable((int)2884);
        GL11.glEnable((int)2896);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }

    private void renderBolt(LightningBolt bolt, Tessellator tessellator, float partialTicks, boolean inner) {
        ++lightningCount;
        float boltAge = bolt.particleAge < 0 ? 0.0f : (float)bolt.particleAge / (float)bolt.particleMaxAge;
        float mainAlpha = !inner ? (1.0f - boltAge) * 0.4f : 1.0f - boltAge * 0.5f;
        int expandTime = (int)(bolt.length * (double)bolt.speed);
        int renderStart = (int)((float)(expandTime / 2 - bolt.particleMaxAge + bolt.particleAge) / (float)(expandTime / 2) * (float)bolt.numSegments0);
        int renderEnd = (int)((float)(bolt.particleAge + expandTime) / (float)expandTime * (float)bolt.numSegments0);
        for (LightningBolt.Segment segment : bolt.segments) {
            Vec3d roundEnd;
            if (segment.segmentNo < renderStart || segment.segmentNo > renderEnd) continue;
            Vec3d playerVec = LightningHandler.getRelativeViewVector(segment.startPoint.point).multiply(-1.0);
            double width = (double)0.008f * (playerVec.mag() / 8.0 + 1.0) * (double)(1.0f + segment.light) * (double)0.4f;
            Vec3d diff1 = playerVec.copy().crossProduct(segment.prevDiff).normalize().multiply(width / (double)segment.sinPrev);
            Vec3d diff2 = playerVec.copy().crossProduct(segment.nextDiff).normalize().multiply(width / (double)segment.sinNext);
            Vec3d startVec = segment.startPoint.point;
            Vec3d endVec = segment.endPoint.point;
            int color = inner ? bolt.colorInner : bolt.colorOuter;
            Color c = new Color(color);
            int alpha = (int)(mainAlpha * segment.light * (float)c.getAlpha());
            tessellator.func_78382_b();
            tessellator.func_78380_c(0xF000F0);
            tessellator.func_78370_a(c.getRed(), c.getGreen(), c.getBlue(), alpha);
            tessellator.func_78377_a(endVec.x - diff2.x, endVec.y - diff2.y, endVec.z - diff2.z);
            tessellator.func_78377_a(startVec.x - diff1.x, startVec.y - diff1.y, startVec.z - diff1.z);
            tessellator.func_78377_a(startVec.x + diff1.x, startVec.y + diff1.y, startVec.z + diff1.z);
            tessellator.func_78377_a(endVec.x + diff2.x, endVec.y + diff2.y, endVec.z + diff2.z);
            tessellator.func_78381_a();
            if (segment.next == null) {
                roundEnd = segment.endPoint.point.copy().add(segment.diff.copy().normalize().multiply(width));
                tessellator.func_78382_b();
                tessellator.func_78380_c(0xF000F0);
                tessellator.func_78370_a(c.getRed(), c.getGreen(), c.getBlue(), alpha);
                tessellator.func_78377_a(roundEnd.x - diff2.x, roundEnd.y - diff2.y, roundEnd.z - diff2.z);
                tessellator.func_78377_a(endVec.x - diff2.x, endVec.y - diff2.y, endVec.z - diff2.z);
                tessellator.func_78377_a(endVec.x + diff2.x, endVec.y + diff2.y, endVec.z + diff2.z);
                tessellator.func_78377_a(roundEnd.x + diff2.x, roundEnd.y + diff2.y, roundEnd.z + diff2.z);
                tessellator.func_78381_a();
            }
            if (segment.prev != null) continue;
            roundEnd = segment.startPoint.point.copy().subtract(segment.diff.copy().normalize().multiply(width));
            tessellator.func_78382_b();
            tessellator.func_78380_c(0xF000F0);
            tessellator.func_78370_a(c.getRed(), c.getGreen(), c.getBlue(), alpha);
            tessellator.func_78377_a(startVec.x - diff1.x, startVec.y - diff1.y, startVec.z - diff1.z);
            tessellator.func_78377_a(roundEnd.x - diff1.x, roundEnd.y - diff1.y, roundEnd.z - diff1.z);
            tessellator.func_78377_a(roundEnd.x + diff1.x, roundEnd.y + diff1.y, roundEnd.z + diff1.z);
            tessellator.func_78377_a(startVec.x + diff1.x, startVec.y + diff1.y, startVec.z + diff1.z);
            tessellator.func_78381_a();
        }
    }

    public static void spawnLightningBolt(World world, Vec3d start, Vec3d end, float ticksPerMeter, long seed, int colorOuter, int colorInner) {
        LightningBolt bolt = new LightningBolt(world, start, end, ticksPerMeter, seed, colorOuter, colorInner);
        bolt.defaultFractal();
        bolt.finalizeBolt();
        LightningBolt.boltList.add(bolt);
    }

    public static void spawnLightningBolt(World world, Vec3d start, Vec3d end, float ticksPerMeter, int colorOuter, int colorInner) {
        LightningHandler.spawnLightningBolt(world, start, end, ticksPerMeter, System.nanoTime(), colorOuter, colorInner);
    }

    public static void spawnSimpleLightningBolt(World world, Vec3d start, Vec3d end, float ticksPerMeter, int colorOuter, int colorInner) {
        LightningBolt bolt = new LightningBolt(world, start, end, ticksPerMeter, System.nanoTime(), colorOuter, colorInner);
        bolt.simpleFractal();
        bolt.finalizeBolt();
        LightningBolt.boltList.add(bolt);
    }
}

