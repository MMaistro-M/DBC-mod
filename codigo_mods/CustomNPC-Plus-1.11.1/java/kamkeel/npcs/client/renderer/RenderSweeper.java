/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
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
import kamkeel.npcs.entity.EntityEnergySweeper;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderSweeper
extends Render {
    public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        EntityEnergySweeper sweeper = (EntityEnergySweeper)entity;
        this.setupRenderState();
        float beamLength = sweeper.getBeamLength();
        float beamWidth = sweeper.getBeamWidth();
        float beamHeight = sweeper.getBeamHeight();
        int innerColor = sweeper.getInnerColor();
        int outerColor = sweeper.getOuterColor();
        boolean outerColorEnabled = sweeper.isOuterColorEnabled();
        float outerColorWidth = sweeper.getOuterColorWidth();
        float angle = sweeper.getInterpolatedAngle(partialTicks);
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        GL11.glRotatef((float)(-angle), (float)0.0f, (float)1.0f, (float)0.0f);
        this.renderBeam(beamLength, beamWidth, beamHeight, innerColor, outerColor, outerColorEnabled, outerColorWidth);
        GL11.glPopMatrix();
        this.restoreRenderState();
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

    private void renderBeam(float length, float width, float height, int innerColor, int outerColor, boolean outerColorEnabled, float outerColorWidth) {
        float outerR = (float)(outerColor >> 16 & 0xFF) / 255.0f;
        float outerG = (float)(outerColor >> 8 & 0xFF) / 255.0f;
        float outerB = (float)(outerColor & 0xFF) / 255.0f;
        float innerR = (float)(innerColor >> 16 & 0xFF) / 255.0f;
        float innerG = (float)(innerColor >> 8 & 0xFF) / 255.0f;
        float innerB = (float)(innerColor & 0xFF) / 255.0f;
        float innerScale = 0.6f;
        float innerWidth = width * innerScale;
        float innerHeight = height * innerScale;
        if (outerColorEnabled) {
            float outerWidth = innerWidth + outerColorWidth * width;
            float outerHeight = innerHeight + outerColorWidth * height;
            GL11.glDepthMask((boolean)false);
            this.renderBeamSegment(0.0, 0.0, 0.0, 0.0, 0.0, length, outerWidth, outerHeight, outerR, outerG, outerB, 0.3f);
            GL11.glDepthMask((boolean)true);
            float midWidth = innerWidth + (outerWidth - innerWidth) * 0.5f;
            float midHeight = innerHeight + (outerHeight - innerHeight) * 0.5f;
            this.renderBeamSegment(0.0, 0.0, 0.0, 0.0, 0.0, length, midWidth, midHeight, outerR, outerG, outerB, 0.6f);
        }
        this.renderBeamSegment(0.0, 0.0, 0.0, 0.0, 0.0, length, innerWidth, innerHeight, innerR, innerG, innerB, 1.0f);
    }

    private void renderBeamSegment(double x1, double y1, double z1, double x2, double y2, double z2, float width, float height, float r, float g, float b, float a) {
        double horzZ;
        double horzY;
        double horzX;
        double horzLen;
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;
        double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
        if (len < 0.001) {
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
        float halfWidth = width * 0.5f;
        float halfHeight = height * 0.5f;
        double s1x = x1 - horzX * (double)halfWidth - vertX * (double)halfHeight;
        double s1y = y1 - horzY * (double)halfWidth - vertY * (double)halfHeight;
        double s1z = z1 - horzZ * (double)halfWidth - vertZ * (double)halfHeight;
        double s2x = x1 + horzX * (double)halfWidth - vertX * (double)halfHeight;
        double s2y = y1 + horzY * (double)halfWidth - vertY * (double)halfHeight;
        double s2z = z1 + horzZ * (double)halfWidth - vertZ * (double)halfHeight;
        double s3x = x1 + horzX * (double)halfWidth + vertX * (double)halfHeight;
        double s3y = y1 + horzY * (double)halfWidth + vertY * (double)halfHeight;
        double s3z = z1 + horzZ * (double)halfWidth + vertZ * (double)halfHeight;
        double s4x = x1 - horzX * (double)halfWidth + vertX * (double)halfHeight;
        double s4y = y1 - horzY * (double)halfWidth + vertY * (double)halfHeight;
        double s4z = z1 - horzZ * (double)halfWidth + vertZ * (double)halfHeight;
        double e1x = x2 - horzX * (double)halfWidth - vertX * (double)halfHeight;
        double e1y = y2 - horzY * (double)halfWidth - vertY * (double)halfHeight;
        double e1z = z2 - horzZ * (double)halfWidth - vertZ * (double)halfHeight;
        double e2x = x2 + horzX * (double)halfWidth - vertX * (double)halfHeight;
        double e2y = y2 + horzY * (double)halfWidth - vertY * (double)halfHeight;
        double e2z = z2 + horzZ * (double)halfWidth - vertZ * (double)halfHeight;
        double e3x = x2 + horzX * (double)halfWidth + vertX * (double)halfHeight;
        double e3y = y2 + horzY * (double)halfWidth + vertY * (double)halfHeight;
        double e3z = z2 + horzZ * (double)halfWidth + vertZ * (double)halfHeight;
        double e4x = x2 - horzX * (double)halfWidth + vertX * (double)halfHeight;
        double e4y = y2 - horzY * (double)halfWidth + vertY * (double)halfHeight;
        double e4z = z2 - horzZ * (double)halfWidth + vertZ * (double)halfHeight;
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78382_b();
        tess.func_78369_a(r, g, b, a);
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
        tess.func_78377_a(e1x, e1y, e1z);
        tess.func_78377_a(e2x, e2y, e2z);
        tess.func_78377_a(e3x, e3y, e3z);
        tess.func_78377_a(e4x, e4y, e4z);
        tess.func_78381_a();
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return null;
    }
}

