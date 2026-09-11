/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.DragonBC.common.Npcs.EntityAura;
import JinRyuu.DragonBC.common.Npcs.ModelAura;
import JinRyuu.DragonBC.common.Npcs.RenderDBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderAura
extends RenderDBC {
    private ModelAura aModel = new ModelAura();
    private String mdid = "jinryuudragonbc";

    public RenderAura() {
        super((ModelBase)new ModelAura(), 0.5f);
    }

    public RenderAura(String mdid) {
        this();
        this.mdid = mdid;
    }

    public void renderAura(EntityAura par1Entity, double parX, double parY, double parZ, float par8, float par9) {
        boolean plyrSP;
        this.field_76989_e = 0.0f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)parX + 0.0f), (float)((float)parY + 3.0f), (float)((float)parZ + 0.0f));
        boolean rot = par1Entity.getRot();
        if (rot) {
            GL11.glTranslatef((float)0.0f, (float)-1.5f, (float)0.0f);
            GL11.glRotatef((float)(-par1Entity.field_70177_z), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(par1Entity.field_70125_A - 90.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)1.5f, (float)0.0f);
        }
        GL11.glPushMatrix();
        float spd = par1Entity.getSpd();
        boolean iner = par1Entity.getInner();
        float spd2 = 18.0f / (spd * 0.05f);
        float var13 = par1Entity.getAge();
        float cr = par1Entity.getCRel();
        float s1 = par1Entity.getState();
        float s = s1 + cr * 0.03f;
        float s2 = par1Entity.getState2() * 0.5f;
        int c = par1Entity.getCol();
        String tex = par1Entity.getTex();
        int cl2 = par1Entity.getColL2();
        String texl2 = par1Entity.getTexL2();
        boolean hasl2 = texl2.length() > 2;
        s += s2;
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        ResourceLocation txx2 = new ResourceLocation(this.mdid + ":" + texl2 + ".png");
        ResourceLocation txx = new ResourceLocation(this.mdid + ":" + tex + ".png");
        this.field_76990_c.field_78724_e.func_110577_a(txx);
        boolean bl = plyrSP = DBCClient.mc.field_71439_g.func_70005_c_().equals(par1Entity.getmot()) && DBCClient.mc.field_71474_y.field_74320_O == 0;
        float p = !plyrSP ? par1Entity.getAlp() : (par1Entity.getInner() ? 0.025f : 0.05f);
        RenderAura.glColor4f(c, p);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)2896);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glScalef((float)(1.0f + 0.1f * s), (float)(1.0f + 0.07f * s), (float)(1.0f + 0.1f * s));
        GL11.glTranslatef((float)0.0f, (float)(-0.3f - 0.07f * s), (float)0.0f);
        GL11.glRotatef((float)(var13 * spd2), (float)0.0f, (float)1.0f, (float)0.0f);
        float w = 0.75f;
        for (int i2 = 0; !(i2 >= (iner ? 2 : 1) || i2 == 1 && var13 > 10.0f); ++i2) {
            for (int i = 0; i < 4; ++i) {
                GL11.glPushMatrix();
                GL11.glRotatef((float)(i * 90), (float)0.0f, (float)1.0f, (float)0.0f);
                if (var13 < 15.0f) {
                    this.aModel.renderModel(par1Entity, 0.0625f, var13, (float)i2 * w, spd);
                    if (hasl2) {
                        this.field_76990_c.field_78724_e.func_110577_a(txx2);
                        RenderAura.glColor4f(cl2, p);
                        this.aModel.renderModel(par1Entity, 0.0625f, var13, (float)i2 * w, spd);
                    }
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glRotatef((float)(i * 90 + 45), (float)0.0f, (float)1.0f, (float)0.0f);
                this.field_76990_c.field_78724_e.func_110577_a(txx);
                RenderAura.glColor4f(c, p);
                this.aModel.renderModel(par1Entity, 0.0625f, var13 + 4.0f, (float)i2 * w, spd);
                if (hasl2) {
                    this.field_76990_c.field_78724_e.func_110577_a(txx2);
                    RenderAura.glColor4f(cl2, p);
                    this.aModel.renderModel(par1Entity, 0.0625f, var13 + 4.0f, (float)i2 * w, spd);
                }
                GL11.glPopMatrix();
            }
        }
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
        if (var13 < (float)par1Entity.getLightLivingTime() && par1Entity.getState() > 4.0f && par1Entity.getState() < 7.0f && !rot) {
            GL11.glPushMatrix();
            Tessellator tessellator = Tessellator.field_78398_a;
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2896);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)1);
            double[] adouble = new double[8];
            double[] adouble1 = new double[8];
            double d3 = 0.0;
            double d4 = 0.0;
            Random random = new Random(par1Entity.lightVert);
            for (int i = 7; i >= 0; --i) {
            }
            boolean k1 = false;
            float sc = 0.2f;
            Random random1 = new Random(par1Entity.lightVert);
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
                    tessellator.func_78371_b(5);
                    float f2 = 0.5f;
                    tessellator.func_78369_a(0.9f * f2, 0.9f * f2, 1.0f * f2, 0.3f);
                    double d9 = 0.1 + (double)k1 * 0.2;
                    if (j == 0) {
                        // empty if block
                    }
                    double d10 = 0.1 + (double)k1 * 0.2;
                    if (j == 0) {
                        // empty if block
                    }
                    for (int j1 = 0; j1 < 5; ++j1) {
                        double d11 = parX + 0.0 - d9;
                        double d12 = parZ + 0.0 - d9;
                        if (j1 == 1 || j1 == 2) {
                            d11 += d9 * 2.0 * (double)sc;
                        }
                        if (j1 == 2 || j1 == 3) {
                            d12 += d9 * 2.0 * (double)sc;
                        }
                        double d13 = parX + 0.0 - d10;
                        double d14 = parZ + 0.0 - d10;
                        if (j1 == 1 || j1 == 2) {
                            d13 += d10 * 2.0 * (double)sc;
                        }
                        if (j1 == 2 || j1 == 3) {
                            d14 += d10 * 2.0 * (double)sc;
                        }
                        if (i1 >= 8) continue;
                        tessellator.func_78377_a(d13 + d5 * (double)sc, parY - (double)(i1 * 1 - 7) * (double)sc, d14 + d6 * (double)sc);
                        tessellator.func_78377_a(d11 + d7 * (double)sc, parY - (double)((i1 + 1) * 1 - 7) * (double)sc, d12 + d8 * (double)sc);
                    }
                    tessellator.func_78381_a();
                }
            }
            GL11.glDisable((int)3042);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)3553);
            GL11.glPopMatrix();
        }
    }

    public static void glColor4f(int c, float a) {
        float h2 = (float)(c >> 16 & 0xFF) / 255.0f;
        float h3 = (float)(c >> 8 & 0xFF) / 255.0f;
        float h4 = (float)(c & 0xFF) / 255.0f;
        float h1 = 1.0f;
        GL11.glColor4f((float)(h1 * h2), (float)(h1 * h3), (float)(h1 * h4), (float)a);
    }

    protected float handleRotationFloat(Entity par1Entity, float par2) {
        return (float)par1Entity.field_70173_aa + par2;
    }

    @Override
    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderAura((EntityAura)par1Entity, par2, par4, par6, par8, par9);
    }
}

