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
import JinRyuu.DragonBC.common.Npcs.EntityAura2;
import JinRyuu.DragonBC.common.Npcs.ModelAura;
import JinRyuu.DragonBC.common.Npcs.ModelAuraG;
import JinRyuu.DragonBC.common.Npcs.RenderDBC;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderAura2
extends RenderDBC {
    private ModelAura aModel;
    private ModelAuraG bModel;
    private String mdid = "jinryuudragonbc";
    private boolean au_fel = true;
    private int au_i = 0;
    private long time_start = 0L;
    private final float seb = 0.03f;
    private final int lvnm = 10;
    private float[][] lightVertRotation = new float[10][7];
    private int lightVertN;

    public RenderAura2() {
        super((ModelBase)new ModelAura(), 0.5f);
        this.aModel = new ModelAura();
        this.bModel = new ModelAuraG();
    }

    public RenderAura2(String mdid) {
        this();
        this.mdid = mdid;
    }

    public void renderAura(EntityAura2 par1Entity, double parX, double parY, double parZ, float par8, float par9) {
        float var13 = par1Entity.getAge();
        boolean rot = par1Entity.getRot();
        this.field_76989_e = 0.0f;
        if (par1Entity.kettleMode == 1) {
            return;
        }
        if (JGConfigClientSettings.CLIENT_DA20) {
            if (JGConfigClientSettings.CLIENT_DA14 && par1Entity.getBol6() == -1) {
                this.func_tad(par1Entity, parX, parY, parZ, par8, par9);
            } else if (JGConfigClientSettings.CLIENT_DA12) {
                this.lightning(par1Entity, parX, parY, parZ, par9, 1.0f, var13, rot);
            }
        } else if (JGConfigClientSettings.CLIENT_DA14) {
            this.func_tad(par1Entity, parX, parY, parZ, par8, par9);
        } else if (JGConfigClientSettings.CLIENT_DA12) {
            this.lightning(par1Entity, parX, parY, parZ, par9, 1.0f, var13, rot);
        }
    }

    private void func_tad(EntityAura2 par1Entity, double parX, double parY, double parZ, float par8, float par9) {
        boolean plyrSP;
        if (!DBCClient.mc.func_147113_T()) {
            boolean cl3b;
            int cl3 = par1Entity.getColL3();
            boolean bl = cl3b = cl3 > 0;
            if (cl3b) {
                if (System.nanoTime() / 100000000L - this.time_start > 1L) {
                    if (this.au_fel) {
                        if (this.au_i >= 8) {
                            this.au_fel = false;
                            --this.au_i;
                        } else {
                            ++this.au_i;
                        }
                    } else if (this.au_i <= 0) {
                        this.au_fel = true;
                        ++this.au_i;
                    } else {
                        --this.au_i;
                    }
                    this.time_start = System.nanoTime() / 100000000L;
                }
            } else if (System.nanoTime() / 10000000L - this.time_start > 1L) {
                if (this.au_fel) {
                    if (this.au_i >= 5) {
                        this.au_fel = false;
                        --this.au_i;
                    } else {
                        ++this.au_i;
                    }
                } else if (this.au_i <= 0) {
                    this.au_fel = true;
                    ++this.au_i;
                } else {
                    --this.au_i;
                }
                this.time_start = System.nanoTime() / 10000000L;
            }
        }
        if (this.au_i > 8) {
            this.au_i = 8;
        } else if (this.au_i < 0) {
            this.au_i = 0;
        }
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
        int cl3 = par1Entity.getColL3();
        String texl2 = par1Entity.getTexL2();
        String texl3 = par1Entity.getTexL3();
        boolean hasl2 = texl2.length() > 2;
        s += s2;
        boolean cl3b = cl3 > 0;
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        ResourceLocation txx3 = new ResourceLocation(this.mdid + ":" + texl3 + ".png");
        ResourceLocation txx2 = new ResourceLocation(this.mdid + ":" + texl2 + ".png");
        ResourceLocation txx = new ResourceLocation(this.mdid + ":" + tex + ".png");
        GL11.glEnable((int)3042);
        float f4 = 0.5f;
        GL11.glColor4f((float)f4, (float)f4, (float)f4, (float)1.0f);
        GL11.glDisable((int)2896);
        GL11.glBlendFunc((int)1, (int)1);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        this.field_76990_c.field_78724_e.func_110577_a(txx);
        boolean bl = plyrSP = DBCClient.mc.field_71439_g.func_70005_c_().equals(par1Entity.getmot()) && DBCClient.mc.field_71474_y.field_74320_O == 0;
        float p = !plyrSP ? par1Entity.getAlp() * ((float)JGConfigClientSettings.CLIENT_DA21 / 10.0f) : (par1Entity.getInner() ? 0.025f * ((float)JGConfigClientSettings.CLIENT_DA21 / 10.0f) : 0.05f * ((float)JGConfigClientSettings.CLIENT_DA21 / 10.0f));
        RenderAura2.glColor4f(c, p);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)2896);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glScalef((float)(1.0f + 0.1f * s + (float)this.au_i * 0.03f), (float)(1.0f + 0.07f * s), (float)(1.0f + 0.1f * s + (float)this.au_i * 0.03f));
        GL11.glTranslatef((float)0.0f, (float)(-0.3f - 0.07f * s), (float)0.0f);
        GL11.glRotatef((float)(var13 * spd2), (float)0.0f, (float)1.0f, (float)0.0f);
        boolean spawn = true;
        float w = 0.75f;
        for (int i2 = 0; i2 < (iner ? 2 : 1); ++i2) {
            if (i2 == 1) {
                if (var13 > (cl3b ? spd / 2.0f : 10.0f)) break;
            }
            for (int i = 0; i < 4; ++i) {
                GL11.glPushMatrix();
                GL11.glRotatef((float)(i * 90), (float)0.0f, (float)1.0f, (float)0.0f);
                if (var13 < 15.0f) {
                    RenderAura2.glColor4f(c, p);
                    if (spawn) {
                        this.aModel.renderModel(par1Entity, 0.0625f, var13, (float)i2 * w, spd);
                    }
                    if (hasl2) {
                        this.field_76990_c.field_78724_e.func_110577_a(txx2);
                        RenderAura2.glColor4f(cl2, p);
                        if (spawn) {
                            this.aModel.renderModel(par1Entity, 0.0625f, var13, (float)i2 * w, spd);
                        }
                    }
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glRotatef((float)(i * 90 + 45), (float)0.0f, (float)1.0f, (float)0.0f);
                this.field_76990_c.field_78724_e.func_110577_a(cl3b && i2 == 1 ? txx3 : txx);
                if (cl3b && i2 == 1) {
                    RenderAura2.cf(c, cl3, p);
                } else {
                    RenderAura2.glColor4f(c, p);
                }
                if (spawn) {
                    this.aModel.renderModel(par1Entity, 0.0625f, var13 + 4.0f, (float)i2 * w, spd);
                }
                if (hasl2) {
                    this.field_76990_c.field_78724_e.func_110577_a(txx2);
                    RenderAura2.glColor4f(cl2, p);
                    if (spawn) {
                        this.aModel.renderModel(par1Entity, 0.0625f, var13 + 4.0f, (float)i2 * w, spd);
                    }
                }
                GL11.glPopMatrix();
                if (!cl3b) continue;
                GL11.glPushMatrix();
                GL11.glScalef((float)0.7f, (float)0.7f, (float)0.7f);
                GL11.glTranslatef((float)0.0f, (float)0.95f, (float)0.0f);
                GL11.glRotatef((float)(i * 90 + 45), (float)0.0f, (float)1.0f, (float)0.0f);
                this.field_76990_c.field_78724_e.func_110577_a(txx3);
                RenderAura2.glColor4f(cl3, p);
                if (spawn) {
                    this.aModel.renderModel(par1Entity, 0.0625f, var13 + 4.0f, (float)i2 * w, spd);
                }
                GL11.glPopMatrix();
            }
        }
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2896);
        GL11.glEnable((int)3553);
        GL11.glPopMatrix();
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
        if (JGConfigClientSettings.CLIENT_DA12) {
            this.lightning(par1Entity, parX, parY, parZ, par9, 1.0f, var13, rot);
        }
    }

    private void lightning(EntityAura2 e, double par2, double par4, double par6, float par9, float var20, float var13, boolean rot) {
        if (var13 < (float)e.getLightLivingTime() && e.getState() > 4.0f && e.getState() < 7.0f && !rot) {
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
            GL11.glTranslatef((float)((float)par2), (float)((float)par4 + e.field_70131_O / 2.0f), (float)((float)par6));
            boolean k1 = false;
            int nu = (int)(Math.random() * 10.0) + 1;
            int nu2 = 1;
            if (!JRMCoreClient.mc.func_147113_T()) {
                this.lightVertN = (int)(Math.random() * 10.0);
            }
            for (int i = 0; i < this.lightVertN; ++i) {
                if (!JRMCoreClient.mc.func_147113_T()) {
                    this.lightVertRotation[i][0] = (float)(Math.random() * 1.0);
                    this.lightVertRotation[i][1] = (float)(Math.random() * 1.0);
                    this.lightVertRotation[i][2] = (float)(Math.random() * 1.0);
                    this.lightVertRotation[i][3] = (float)(Math.random() * (double)1.2f) - 0.6f;
                    this.lightVertRotation[i][4] = (float)(Math.random() * (double)e.field_70131_O) - e.field_70131_O / 2.0f;
                    this.lightVertRotation[i][5] = (float)(Math.random() * (double)1.2f) - 0.6f;
                    this.lightVertRotation[i][6] = (float)(Math.random() * (double)0.2f);
                }
                float sc = 0.05f + this.lightVertRotation[i][6];
                GL11.glRotatef((float)(360.0f * this.lightVertRotation[i][0]), (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)(360.0f * this.lightVertRotation[i][1]), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(360.0f * this.lightVertRotation[i][2]), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)this.lightVertRotation[i][3], (float)this.lightVertRotation[i][4], (float)this.lightVertRotation[i][5]);
                Random random1 = new Random(e.lightVert[i]);
                for (int j = 0; j < nu2; ++j) {
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
                        if (!e.getBol7()) {
                            tessellator2.func_78369_a(0.9f * f2, 0.9f * f2, 1.0f * f2, 0.35f);
                        } else {
                            tessellator2.func_78369_a(1.0f * f2, 0.05f * f2, 0.1f * f2, 0.5f);
                        }
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
    }

    public static void glColor4f(int c, float a) {
        float h2 = (float)(c >> 16 & 0xFF) / 255.0f;
        float h3 = (float)(c >> 8 & 0xFF) / 255.0f;
        float h4 = (float)(c & 0xFF) / 255.0f;
        float h1 = 1.0f;
        GL11.glColor4f((float)(h1 * h2), (float)(h1 * h3), (float)(h1 * h4), (float)a);
    }

    public static void cf(int x, int y, float a) {
        float h2 = (float)(x >> 16 & 0xFF) / 255.0f;
        float h3 = (float)(x >> 8 & 0xFF) / 255.0f;
        float h4 = (float)(x & 0xFF) / 255.0f;
        float y2 = (float)(y >> 16 & 0xFF) / 255.0f;
        float y3 = (float)(y >> 8 & 0xFF) / 255.0f;
        float y4 = (float)(y & 0xFF) / 255.0f;
        float pc = 0.5f;
        pc = pc > 1.0f ? 1.0f : pc;
        float pg = 1.0f - pc;
        float ga = h2 * pg + y2 * pc;
        float hr = h3 * pg + y3 * pc;
        float ah = h4 * pg + y4 * pc;
        h2 = ga;
        h3 = hr;
        h4 = ah;
        GL11.glColor4f((float)h2, (float)h3, (float)h4, (float)a);
    }

    protected float handleRotationFloat(Entity par1Entity, float par2) {
        return (float)par1Entity.field_70173_aa + par2;
    }

    @Override
    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderAura((EntityAura2)par1Entity, par2, par4, par6, par8, par9);
    }
}

