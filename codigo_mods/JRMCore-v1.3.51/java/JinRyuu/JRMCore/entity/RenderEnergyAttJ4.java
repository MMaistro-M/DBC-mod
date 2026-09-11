/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.entity.EntityEnergyAttJ4;
import JinRyuu.JRMCore.entity.RenderJRMC;
import JinRyuu.JRMCore.m.mEnergy5;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderEnergyAttJ4
extends RenderJRMC {
    private mEnergy5 ener;
    public final int lvnm = 10;
    public float[][] lightVertRotation = new float[10][3];

    public RenderEnergyAttJ4() {
        super(new mEnergy5(), 0.5f);
        this.ener = new mEnergy5();
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.lightVertRotation[i][j] = 0.0f;
            }
        }
    }

    public void renderEnergy(EntityEnergyAttJ4 e, double par2, double par4, double par6, float par8, float par9) {
        byte type = e.getType();
        double x = e.field_70165_t;
        double y = e.field_70163_u;
        double z = e.field_70161_v;
        GL11.glPushMatrix();
        GL11.glEnable((int)2977);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDepthMask((boolean)false);
        GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
        GL11.glRotatef((float)(e.field_70126_B + (e.field_70177_z - e.field_70126_B) * par9 - 180.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(e.field_70127_C + (e.field_70125_A - e.field_70127_C) * par9), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        this.tex(5220343);
        float seb_szaz = 5.0f;
        float seb_one = seb_szaz / 100.0f;
        float max = 1.0f;
        float ti = e.field_70173_aa;
        float curr = ti * seb_one;
        curr = curr >= max ? max : curr;
        GL11.glScalef((float)curr, (float)curr, (float)curr);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2977);
        GL11.glPopMatrix();
        if (type == 0) {
            this.fieldPass3(e, par2, par4, par6, par9, curr, 5220343);
        } else {
            this.lightning(e, par2, par4, par6, par9, curr, 5220343);
        }
    }

    private void lightning(EntityEnergyAttJ4 e, double par2, double par4, double par6, float par9, float var20, int col) {
        GL11.glPushMatrix();
        Tessellator tessellator2 = Tessellator.field_78398_a;
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        double[] adouble = new double[8];
        double[] adouble1 = new double[8];
        double d3 = 0.0;
        double d4 = 0.0;
        GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
        float t = e.field_70173_aa;
        float scale = 0.03f * t;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        GL11.glRotatef((float)((float)e.field_70173_aa * 45.0f), (float)1.0f, (float)1.0f, (float)1.0f);
        boolean k1 = false;
        float sc = e.getSize() / 2.0f;
        for (int i = 0; i < e.number_of_lightVerts; ++i) {
            if (!JRMCoreClient.mc.func_147113_T()) {
                this.lightVertRotation[i][0] = (int)(Math.random() * 11.0) * 36;
                this.lightVertRotation[i][1] = (float)(Math.random() * 2.0) - 1.0f;
                this.lightVertRotation[i][2] = (float)(Math.random() * 2.0) - 1.0f;
            }
            GL11.glRotated((double)(i * (360 / e.number_of_lightVerts)), (double)0.0, (double)0.0, (double)1.0);
            GL11.glRotatef((float)this.lightVertRotation[i][0], (float)this.lightVertRotation[i][1], (float)this.lightVertRotation[i][2], (float)0.0f);
            Random random = new Random(e.lightVert[i]);
            Random random1 = new Random(e.lightVert[i]);
            for (int j = 0; j < 3; ++j) {
                int k = 7;
                int l = 0;
                if (j > 0) {
                    k = 7 - j;
                }
                if (j > 0) {
                    l = k - 2;
                }
                double d5 = adouble[k] - d3;
                double d6 = adouble1[k] - d4;
                for (int i1 = k; i1 >= l; --i1) {
                    double d7 = d5;
                    double d8 = d6;
                    d5 += (double)(random1.nextInt(31) - 15) * (double)0.07f;
                    d6 += (double)(random1.nextInt(31) - 15) * (double)0.07f;
                    tessellator2.func_78371_b(5);
                    float f2 = 0.5f;
                    tessellator2.func_78369_a(0.9f * f2, 0.9f * f2, 1.0f * f2, 0.3f - (float)e.field_70173_aa * 0.013f);
                    double d9 = 0.1 + (double)k1 * 0.2;
                    double d10 = 0.1 + (double)k1 * 0.2;
                    for (int j1 = 0; j1 < 5; ++j1) {
                        double d11 = 0.0 - d9;
                        double d12 = 0.0 - d9;
                        if (j1 == 1 || j1 == 2) {
                            d11 += d9 * 2.0 * (double)sc;
                        }
                        if (j1 == 2 || j1 == 3) {
                            d12 += d9 * 2.0 * (double)sc;
                        }
                        double d13 = 0.0 - d10;
                        double d14 = 0.0 - d10;
                        if (j1 == 1 || j1 == 2) {
                            d13 += d10 * 2.0 * (double)sc;
                        }
                        if (j1 == 2 || j1 == 3) {
                            d14 += d10 * 2.0 * (double)sc;
                        }
                        if (i1 >= 8) continue;
                        tessellator2.func_78377_a(d13 + d5 * (double)sc, -((double)(i1 * 1 - 7)) * (double)sc, d14 + d6 * (double)sc);
                        tessellator2.func_78377_a(d11 + d7 * (double)sc, -((double)((i1 + 1) * 1 - 7)) * (double)sc, d12 + d8 * (double)sc);
                    }
                    tessellator2.func_78381_a();
                }
            }
        }
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2896);
        GL11.glEnable((int)3553);
        GL11.glPopMatrix();
    }

    private void fieldPass3(EntityEnergyAttJ4 e, double par2, double par4, double par6, float par9, float var20, int col) {
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2977);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)2896);
        GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
        this.tex(col);
        GL11.glScalef((float)(var20 / 2.0f), (float)(var20 / 2.0f), (float)(var20 / 2.0f));
        float t = e.field_70173_aa;
        float scale = 0.3f * t;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        GL11.glRotatef((float)((float)e.field_70173_aa * 45.0f), (float)1.0f, (float)1.0f, (float)1.0f);
        float sc2 = 2.6f;
        GL11.glColor4f((float)0.7f, (float)0.9f, (float)1.0f, (float)(0.6f - (float)e.field_70173_aa * 0.03f));
        this.ener.render();
        sc2 = 2.6f;
        GL11.glScalef((float)sc2, (float)sc2, (float)sc2);
        GL11.glColor4f((float)0.7f, (float)0.9f, (float)1.0f, (float)(0.3f - (float)e.field_70173_aa * 0.015f));
        this.ener.render();
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2977);
        GL11.glDisable((int)2896);
        GL11.glPopMatrix();
    }

    public void tex(int col) {
        float h2 = (float)(col >> 16 & 0xFF) / 255.0f;
        float h3 = (float)(col >> 8 & 0xFF) / 255.0f;
        float h4 = (float)(col & 0xFF) / 255.0f;
        GL11.glColor4f((float)h2, (float)h3, (float)h4, (float)0.5f);
        ResourceLocation txx = new ResourceLocation("jinryuumodscore:allw.png");
        this.field_76990_c.field_78724_e.func_110577_a(txx);
    }

    protected float handleRotationFloat(Entity par1Entity, float par2) {
        return (float)par1Entity.field_70173_aa + par2;
    }

    @Override
    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderEnergy((EntityEnergyAttJ4)par1Entity, par2, par4, par6, par8, par9);
    }
}

