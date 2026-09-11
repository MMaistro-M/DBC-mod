/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  org.lwjgl.opengl.GL11
 */
package kamkeel.npcs.client.renderer;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import kamkeel.npcs.controllers.data.telegraph.Telegraph;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import kamkeel.npcs.controllers.data.telegraph.TelegraphManager;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

public class TelegraphRenderer {
    public static TelegraphRenderer Instance;
    private static final int CIRCLE_SEGMENTS = 32;
    private static final int CONE_SEGMENTS = 16;

    public TelegraphRenderer() {
        Instance = this;
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        if (TelegraphManager.ClientInstance == null || !TelegraphManager.ClientInstance.hasTelegraphs()) {
            return;
        }
        Minecraft mc = Minecraft.func_71410_x();
        EntityClientPlayerMP player = mc.field_71439_g;
        if (player == null) {
            return;
        }
        float partialTicks = event.partialTicks;
        double playerX = player.field_70142_S + (player.field_70165_t - player.field_70142_S) * (double)partialTicks;
        double playerY = player.field_70137_T + (player.field_70163_u - player.field_70137_T) * (double)partialTicks;
        double playerZ = player.field_70136_U + (player.field_70161_v - player.field_70136_U) * (double)partialTicks;
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)1048575);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2884);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)2896);
        GL11.glDepthMask((boolean)false);
        for (TelegraphInstance instance : TelegraphManager.ClientInstance.getTelegraphs()) {
            this.renderTelegraph(instance, playerX, playerY, playerZ, partialTicks);
        }
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2896);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2884);
        GL11.glEnable((int)3553);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    private void renderTelegraph(TelegraphInstance instance, double playerX, double playerY, double playerZ, float partialTicks) {
        Telegraph telegraph = instance.getTelegraph();
        if (telegraph == null || telegraph.getType() == TelegraphType.NONE) {
            return;
        }
        double renderX = instance.getInterpolatedX(partialTicks) - playerX;
        double renderY = instance.getInterpolatedY(partialTicks) - playerY + (double)telegraph.getHeightOffset();
        double renderZ = instance.getInterpolatedZ(partialTicks) - playerZ;
        int color = instance.getAnimatedColor(partialTicks);
        float alpha = (float)(color >> 24 & 0xFF) / 255.0f;
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float green = (float)(color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(color & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glTranslated((double)renderX, (double)renderY, (double)renderZ);
        GL11.glRotatef((float)(-instance.getInterpolatedYaw(partialTicks)), (float)0.0f, (float)1.0f, (float)0.0f);
        switch (telegraph.getType()) {
            case CIRCLE: {
                this.renderCircle(telegraph.getRadius(), red, green, blue, alpha);
                break;
            }
            case RING: {
                this.renderRing(telegraph.getRadius(), telegraph.getInnerRadius(), red, green, blue, alpha);
                break;
            }
            case LINE: {
                this.renderLine(telegraph.getLength(), telegraph.getWidth(), red, green, blue, alpha);
                break;
            }
            case CONE: {
                this.renderCone(telegraph.getLength(), telegraph.getAngle(), telegraph.getInnerRadius(), red, green, blue, alpha);
                break;
            }
            case POINT: {
                this.renderPoint(red, green, blue, alpha);
                break;
            }
            case SQUARE: {
                this.renderSquare(telegraph.getRadius(), red, green, blue, alpha);
                break;
            }
        }
        GL11.glPopMatrix();
    }

    private void renderCircle(float radius, float r, float g, float b, float a) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78371_b(6);
        tessellator.func_78369_a(r, g, b, a);
        tessellator.func_78377_a(0.0, 0.0, 0.0);
        for (int i = 0; i <= 32; ++i) {
            double angle = Math.PI * 2 * (double)i / 32.0;
            double x = Math.cos(angle) * (double)radius;
            double z = Math.sin(angle) * (double)radius;
            tessellator.func_78377_a(x, 0.0, z);
        }
        tessellator.func_78381_a();
        this.renderCircleBorder(radius, r, g, b, Math.min(1.0f, a * 2.0f));
    }

    private void renderCircleBorder(float radius, float r, float g, float b, float a) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78371_b(2);
        tessellator.func_78369_a(r, g, b, a);
        for (int i = 0; i < 32; ++i) {
            double angle = Math.PI * 2 * (double)i / 32.0;
            double x = Math.cos(angle) * (double)radius;
            double z = Math.sin(angle) * (double)radius;
            tessellator.func_78377_a(x, 0.01, z);
        }
        tessellator.func_78381_a();
    }

    private void renderRing(float outerRadius, float innerRadius, float r, float g, float b, float a) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78371_b(5);
        tessellator.func_78369_a(r, g, b, a);
        for (int i = 0; i <= 32; ++i) {
            double angle = Math.PI * 2 * (double)i / 32.0;
            double cosA = Math.cos(angle);
            double sinA = Math.sin(angle);
            tessellator.func_78377_a(cosA * (double)outerRadius, 0.0, sinA * (double)outerRadius);
            tessellator.func_78377_a(cosA * (double)innerRadius, 0.0, sinA * (double)innerRadius);
        }
        tessellator.func_78381_a();
        this.renderCircleBorder(outerRadius, r, g, b, Math.min(1.0f, a * 2.0f));
        this.renderCircleBorder(innerRadius, r, g, b, Math.min(1.0f, a * 2.0f));
    }

    private void renderLine(float length, float width, float r, float g, float b, float a) {
        float halfWidth = width / 2.0f;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78369_a(r, g, b, a);
        tessellator.func_78377_a((double)(-halfWidth), 0.0, 0.0);
        tessellator.func_78377_a((double)halfWidth, 0.0, 0.0);
        tessellator.func_78377_a((double)halfWidth, 0.0, (double)length);
        tessellator.func_78377_a((double)(-halfWidth), 0.0, (double)length);
        tessellator.func_78381_a();
        tessellator.func_78371_b(2);
        tessellator.func_78369_a(r, g, b, Math.min(1.0f, a * 2.0f));
        tessellator.func_78377_a((double)(-halfWidth), 0.01, 0.0);
        tessellator.func_78377_a((double)halfWidth, 0.01, 0.0);
        tessellator.func_78377_a((double)halfWidth, 0.01, (double)length);
        tessellator.func_78377_a((double)(-halfWidth), 0.01, (double)length);
        tessellator.func_78381_a();
    }

    private void renderCone(float length, float angle, float innerRadius, float r, float g, float b, float a) {
        float halfAngleRad = (float)Math.toRadians(angle / 2.0f);
        Tessellator tessellator = Tessellator.field_78398_a;
        if (innerRadius <= 0.0f) {
            double z;
            double x;
            float segmentAngle;
            int i;
            tessellator.func_78371_b(6);
            tessellator.func_78369_a(r, g, b, a);
            tessellator.func_78377_a(0.0, 0.0, 0.0);
            for (i = 0; i <= 16; ++i) {
                segmentAngle = -halfAngleRad + halfAngleRad * 2.0f * (float)i / 16.0f;
                x = Math.sin(segmentAngle) * (double)length;
                z = Math.cos(segmentAngle) * (double)length;
                tessellator.func_78377_a(x, 0.0, z);
            }
            tessellator.func_78381_a();
            tessellator.func_78371_b(3);
            tessellator.func_78369_a(r, g, b, Math.min(1.0f, a * 2.0f));
            tessellator.func_78377_a(0.0, 0.01, 0.0);
            for (i = 0; i <= 16; ++i) {
                segmentAngle = -halfAngleRad + halfAngleRad * 2.0f * (float)i / 16.0f;
                x = Math.sin(segmentAngle) * (double)length;
                z = Math.cos(segmentAngle) * (double)length;
                tessellator.func_78377_a(x, 0.01, z);
            }
            tessellator.func_78377_a(0.0, 0.01, 0.0);
            tessellator.func_78381_a();
        } else {
            int i;
            tessellator.func_78371_b(5);
            tessellator.func_78369_a(r, g, b, a);
            for (int i2 = 0; i2 <= 16; ++i2) {
                float segmentAngle = -halfAngleRad + halfAngleRad * 2.0f * (float)i2 / 16.0f;
                double sinA = Math.sin(segmentAngle);
                double cosA = Math.cos(segmentAngle);
                tessellator.func_78377_a(sinA * (double)length, 0.0, cosA * (double)length);
                tessellator.func_78377_a(sinA * (double)innerRadius, 0.0, cosA * (double)innerRadius);
            }
            tessellator.func_78381_a();
            float borderAlpha = Math.min(1.0f, a * 2.0f);
            tessellator.func_78371_b(3);
            tessellator.func_78369_a(r, g, b, borderAlpha);
            for (i = 0; i <= 16; ++i) {
                float segmentAngle = -halfAngleRad + halfAngleRad * 2.0f * (float)i / 16.0f;
                tessellator.func_78377_a(Math.sin(segmentAngle) * (double)length, 0.01, Math.cos(segmentAngle) * (double)length);
            }
            tessellator.func_78381_a();
            tessellator.func_78371_b(3);
            tessellator.func_78369_a(r, g, b, borderAlpha);
            for (i = 0; i <= 16; ++i) {
                float segmentAngle = -halfAngleRad + halfAngleRad * 2.0f * (float)i / 16.0f;
                tessellator.func_78377_a(Math.sin(segmentAngle) * (double)innerRadius, 0.01, Math.cos(segmentAngle) * (double)innerRadius);
            }
            tessellator.func_78381_a();
            tessellator.func_78371_b(1);
            tessellator.func_78369_a(r, g, b, borderAlpha);
            double sinStart = Math.sin(-halfAngleRad);
            double cosStart = Math.cos(-halfAngleRad);
            tessellator.func_78377_a(sinStart * (double)innerRadius, 0.01, cosStart * (double)innerRadius);
            tessellator.func_78377_a(sinStart * (double)length, 0.01, cosStart * (double)length);
            double sinEnd = Math.sin(halfAngleRad);
            double cosEnd = Math.cos(halfAngleRad);
            tessellator.func_78377_a(sinEnd * (double)innerRadius, 0.01, cosEnd * (double)innerRadius);
            tessellator.func_78377_a(sinEnd * (double)length, 0.01, cosEnd * (double)length);
            tessellator.func_78381_a();
        }
    }

    private void renderPoint(float r, float g, float b, float a) {
        float size = 0.5f;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78369_a(r, g, b, a);
        tessellator.func_78377_a(0.0, 0.0, (double)(-size));
        tessellator.func_78377_a((double)size, 0.0, 0.0);
        tessellator.func_78377_a(0.0, 0.0, (double)size);
        tessellator.func_78377_a((double)(-size), 0.0, 0.0);
        tessellator.func_78381_a();
    }

    private void renderSquare(float radius, float r, float g, float b, float a) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78369_a(r, g, b, a);
        tessellator.func_78377_a((double)(-radius), 0.0, (double)(-radius));
        tessellator.func_78377_a((double)radius, 0.0, (double)(-radius));
        tessellator.func_78377_a((double)radius, 0.0, (double)radius);
        tessellator.func_78377_a((double)(-radius), 0.0, (double)radius);
        tessellator.func_78381_a();
        tessellator.func_78371_b(2);
        tessellator.func_78369_a(r, g, b, Math.min(1.0f, a * 2.0f));
        tessellator.func_78377_a((double)(-radius), 0.01, (double)(-radius));
        tessellator.func_78377_a((double)radius, 0.01, (double)(-radius));
        tessellator.func_78377_a((double)radius, 0.01, (double)radius);
        tessellator.func_78377_a((double)(-radius), 0.01, (double)radius);
        tessellator.func_78381_a();
    }

    public void renderTelegraphInGUI(TelegraphInstance instance, double centerX, double centerY, double centerZ, float scale, float partialTicks) {
        if (instance == null) {
            return;
        }
        Telegraph telegraph = instance.getTelegraph();
        if (telegraph == null || telegraph.getType() == TelegraphType.NONE) {
            return;
        }
        int color = instance.getAnimatedColor(partialTicks);
        float alpha = (float)(color >> 24 & 0xFF) / 255.0f;
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float green = (float)(color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(color & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)1048575);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2884);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)2896);
        GL11.glDepthMask((boolean)false);
        GL11.glTranslated((double)centerX, (double)centerY, (double)centerZ);
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        GL11.glRotatef((float)(-instance.getInterpolatedYaw(partialTicks)), (float)0.0f, (float)1.0f, (float)0.0f);
        switch (telegraph.getType()) {
            case CIRCLE: {
                this.renderCircle(telegraph.getRadius(), red, green, blue, alpha);
                break;
            }
            case RING: {
                this.renderRing(telegraph.getRadius(), telegraph.getInnerRadius(), red, green, blue, alpha);
                break;
            }
            case LINE: {
                this.renderLine(telegraph.getLength(), telegraph.getWidth(), red, green, blue, alpha);
                break;
            }
            case CONE: {
                this.renderCone(telegraph.getLength(), telegraph.getAngle(), telegraph.getInnerRadius(), red, green, blue, alpha);
                break;
            }
            case POINT: {
                this.renderPoint(red, green, blue, alpha);
                break;
            }
            case SQUARE: {
                this.renderSquare(telegraph.getRadius(), red, green, blue, alpha);
                break;
            }
        }
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2896);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2884);
        GL11.glEnable((int)3553);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
}

