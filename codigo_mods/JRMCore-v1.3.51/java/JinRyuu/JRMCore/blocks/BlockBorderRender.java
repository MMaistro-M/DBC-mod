/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore.blocks;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.blocks.BlockBorderTileEntity;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class BlockBorderRender
extends TileEntitySpecialRenderer {
    float visibility = 0.0f;
    int startedTick1 = 0;
    int startedTick2 = 0;
    byte[] rnd = new byte[6];
    final int[] rndx = new int[]{0, 0, 1, -1, 0, 0};
    final int[] rndy = new int[]{1, -1, 0, 0, 0, 0};
    final int[] rndz = new int[]{0, 0, 0, 0, 1, -1};

    public void renderAModelAt(BlockBorderTileEntity tileentity, double d, double d1, double d2, float f) {
        if (!this.inInRenderDistance(this.distanceFromBlock(d, d1, d2))) {
            return;
        }
        int ani = 0;
        String n = "";
        if (JRMCoreClient.mc.field_71439_g.func_71045_bC() != null && JRMCoreClient.mc.field_71439_g.func_71045_bC().func_77977_a().equals("tile.BlockBorder")) {
            ani = 0;
            this.visibility = 1.0f;
            n = "Edit";
        } else {
            ani = JRMCoreClient.mc.field_71439_g.field_70173_aa % 128;
            ani *= 2;
            this.visibility = 0.6f;
        }
        boolean[] doit = new boolean[3];
        boolean one = false;
        for (int i = 0; i < 6; ++i) {
            if (tileentity.field_145848_d + this.rndy[i] <= 0 || tileentity.field_145848_d + this.rndy[i] >= 254) continue;
            if (!tileentity.func_145831_w().func_147439_a(tileentity.field_145851_c + this.rndx[i], tileentity.field_145848_d + this.rndy[i], tileentity.field_145849_e + this.rndz[i]).func_149739_a().toLowerCase().contains("air")) {
                this.rnd[i] = 1;
                one = true;
                continue;
            }
            this.rnd[i] = 0;
        }
        if (one) {
            doit[2] = this.rnd[2] == 1 || this.rnd[3] == 1 || this.rnd[4] == 1 || this.rnd[5] == 1;
            if (this.rnd[0] == 1 || this.rnd[1] == 1) {
                if (doit[2]) {
                    if (this.rnd[2] == 1 || this.rnd[3] == 1) {
                        doit[0] = true;
                    }
                    if (this.rnd[4] == 1 || this.rnd[5] == 1) {
                        doit[1] = true;
                    }
                    doit[2] = !(this.rnd[2] != 1 && this.rnd[3] != 1 || this.rnd[4] != 1 && this.rnd[5] != 1);
                } else {
                    doit[0] = true;
                    doit[1] = true;
                }
            }
        } else {
            doit[0] = true;
            doit[1] = true;
            doit[2] = true;
        }
        if (this.visibility > 0.0f) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1 + 0.5f), (float)((float)d2 + 0.5f));
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glPushMatrix();
            boolean view2 = JRMCoreClient.mc.field_71474_y.field_74320_O == 2;
            ResourceLocation tx = new ResourceLocation(JRMCoreH.tjjrmc + ":textures/blocks/tile.BlockBorder" + n + ".png");
            this.func_147499_a(tx);
            float scale = 0.00391f;
            GL11.glScalef((float)scale, (float)scale, (float)scale);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)this.visibility);
            for (int side = 0; side < 3; ++side) {
                if (!doit[side]) continue;
                if (side == 1) {
                    GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                } else if (side == 2) {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)1.0f);
                }
                for (int mrr = 0; mrr < 2; ++mrr) {
                    if (mrr == 1) {
                        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)-1.0f);
                        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    }
                    int sizes = 2;
                    GL11.glPushMatrix();
                    GL11.glEnable((int)3042);
                    GL11.glDisable((int)2896);
                    GL11.glBlendFunc((int)770, (int)771);
                    GL11.glAlphaFunc((int)516, (float)0.003921569f);
                    int size = 256;
                    this.drawTexturedModalRect(-size + size / 2, -size + size / 2, 0 + size + ani, 0 + size + ani, size, size, 0.0f);
                    GL11.glEnable((int)2896);
                    GL11.glDisable((int)3042);
                    GL11.glPopMatrix();
                }
            }
            GL11.glPopMatrix();
            GL11.glPopMatrix();
        }
    }

    public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f) {
        this.renderAModelAt((BlockBorderTileEntity)tileentity, d, d1, d2, f);
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

