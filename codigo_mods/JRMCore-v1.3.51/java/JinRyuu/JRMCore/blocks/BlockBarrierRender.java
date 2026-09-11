/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore.blocks;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.blocks.BlockBarrierTileEntity;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class BlockBarrierRender
extends TileEntitySpecialRenderer {
    float visibility = 0.0f;
    int startedTick1 = 0;
    int startedTick2 = 0;

    public void renderAModelAt(BlockBarrierTileEntity tileentity, double d, double d1, double d2, float f) {
        if (!this.inInRenderDistance(this.distanceFromBlock(d, d1, d2))) {
            return;
        }
        if (this.visibility > 0.0f) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1 + 0.5f), (float)((float)d2 + 0.5f));
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glPushMatrix();
            boolean view2 = JRMCoreClient.mc.field_71474_y.field_74320_O == 2;
            GL11.glRotatef((float)RenderManager.field_78727_a.field_78735_i, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(RenderManager.field_78727_a.field_78732_j * (float)(view2 ? 1 : -1)), (float)1.0f, (float)0.0f, (float)0.0f);
            ResourceLocation tx = new ResourceLocation(JRMCoreH.tjjrmc + ":textures/blocks/tile.BlockBarrier.png");
            this.func_147499_a(tx);
            float scale = 0.0038f;
            GL11.glScalef((float)scale, (float)scale, (float)scale);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)this.visibility);
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glDisable((int)2896);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            int size = 256;
            this.drawTexturedModalRect((float)(-size) / 2.0f, (float)(-size) / 2.0f, 0, 0, size, size, 0.0f);
            GL11.glEnable((int)2896);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            GL11.glPopMatrix();
        }
        if (JRMCoreClient.mc.field_71439_g.func_71045_bC() != null && JRMCoreClient.mc.field_71439_g.func_71045_bC().func_77977_a().equals("tile.BlockBarrier")) {
            this.startedTick1 = JRMCoreClient.mc.field_71439_g.field_70173_aa;
            if (this.visibility < 1.0f) {
                this.visibility += (float)(JRMCoreClient.mc.field_71439_g.field_70173_aa - this.startedTick2) / 15.0f;
                this.startedTick2 = JRMCoreClient.mc.field_71439_g.field_70173_aa;
            }
            if (this.visibility > 1.0f) {
                this.visibility = 1.0f;
            }
        } else {
            this.startedTick2 = JRMCoreClient.mc.field_71439_g.field_70173_aa;
            if (this.visibility > 0.0f) {
                this.visibility -= (float)(JRMCoreClient.mc.field_71439_g.field_70173_aa - this.startedTick1) / 15.0f;
                this.startedTick1 = JRMCoreClient.mc.field_71439_g.field_70173_aa;
            }
            if (this.visibility < 0.0f) {
                this.visibility = 0.0f;
            }
        }
    }

    public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f) {
        this.renderAModelAt((BlockBarrierTileEntity)tileentity, d, d1, d2, f);
    }

    public void drawTexturedModalRect(float x, float y, int u, int v, float width, float height, float z) {
        float f = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a((double)x, (double)(y + 0.0f), (double)z, (double)((float)(u + 0) * f), (double)((float)(v + 0) * f1));
        tessellator.func_78374_a((double)x, (double)(y + height), (double)z, (double)((float)(u + 0) * f), (double)(((float)v + height) * f1));
        tessellator.func_78374_a((double)(x + width), (double)(y + height), (double)z, (double)(((float)u + width) * f), (double)(((float)v + height) * f1));
        tessellator.func_78374_a((double)(x + width), (double)(y + 0.0f), (double)z, (double)(((float)u + width) * f), (double)((float)(v + 0) * f1));
        tessellator.func_78381_a();
    }

    private double distanceFromBlock(double x, double y, double z) {
        double longest = 0.0;
        if (x < 0.0) {
            x *= -1.0;
        }
        if (x > longest) {
            longest = x;
        }
        if (y < 0.0) {
            y *= -1.0;
        }
        if (y > longest) {
            longest = y;
        }
        if (z < 0.0) {
            z *= -1.0;
        }
        if (z > longest) {
            longest = z;
        }
        return longest;
    }

    private boolean inInRenderDistance(double distance) {
        if (JGConfigClientSettings.renderdistanceMultiplierBarrierBlock == 100) {
            return true;
        }
        double d1 = 64.0;
        return distance < d1 * ((double)JGConfigClientSettings.renderdistanceMultiplierBarrierBlock / 100.0);
    }
}

