/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.entity.EntityEnergyAttJ2;
import JinRyuu.JRMCore.entity.RenderJRMC;
import JinRyuu.JRMCore.m.mEnergy5;
import JinRyuu.JRMCore.m.mEnergy7;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderEnergyAttJ2
extends RenderJRMC {
    private mEnergy5 ener;
    private mEnergy7 ener2;
    private final String[] jutsus = new String[]{"rasengan", "chidori", "FireStyle_FireBall", "Earth Wall", "Mud Wall", "Water Pistol"};
    private double dist;

    public RenderEnergyAttJ2() {
        super(new mEnergy5(), 0.5f);
        this.ener = new mEnergy5();
        this.ener2 = new mEnergy7();
    }

    public void renderEnergy(EntityEnergyAttJ2 par1Entity, double par2, double par4, double par6, float par8, float par9) {
        if (par1Entity.getjtsre() > 2) {
            byte type = par1Entity.getType();
            byte perc = par1Entity.getPerc();
            int dam = par1Entity.getDam();
            byte den = par1Entity.getDen();
            int col = par1Entity.getCol();
            float size = par1Entity.getSizePerc();
            boolean b = false;
            if (par1Entity.shootingEntity != null) {
                b = par1Entity.shootingEntity.func_145782_y() == JRMCoreClient.mc.field_71439_g.func_145782_y();
            }
            float var13 = this.handleRotationFloat(par1Entity, par9);
            double sx = par1Entity.strtX();
            double sy = par1Entity.strtY();
            double sz = par1Entity.strtZ();
            double x = sx - par1Entity.field_70165_t;
            double y = sy - par1Entity.field_70163_u;
            double z = sz - par1Entity.field_70161_v;
            double tsx = sx - (double)par1Entity.trgtX();
            double tsy = sy - (double)par1Entity.trgtY();
            double tsz = sz - (double)par1Entity.trgtZ();
            this.dist = par1Entity.getShrink() ? (this.dist -= (double)(par1Entity.getSpe() * 20.0f)) : (double)MathHelper.func_76133_a((double)(x * x + y * y + z * z));
            float var20 = size * 2.0f;
            if (var20 > 30.0f) {
                var20 = 30.0f;
            }
            if (type == 1) {
                GL11.glPushMatrix();
                GL11.glEnable((int)2977);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                if (par1Entity.getjtsre() != 3) {
                    GL11.glDepthMask((boolean)false);
                }
                GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                if (par1Entity.getjtsre() == 3) {
                    ResourceLocation tx = new ResourceLocation("jinryuunarutoc:jutsu/" + this.jutsus[par1Entity.getjtsre() - 1] + ".png");
                    this.field_76990_c.field_78724_e.func_110577_a(tx);
                    float seb_szaz = 10.0f;
                    float seb_one = seb_szaz / 100.0f;
                    float max = 15.0f;
                    float ti = par1Entity.field_70173_aa;
                    float curr = ti * seb_one;
                    curr = curr >= max ? max : curr;
                    GL11.glTranslatef((float)0.0f, (float)(-curr / 2.0f), (float)0.0f);
                    GL11.glScalef((float)curr, (float)curr, (float)curr);
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    GL11.glRotatef((float)(par1Entity.field_70173_aa * 10), (float)1.0f, (float)1.0f, (float)0.0f);
                    this.ener.func_78088_a(par1Entity, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
                    GL11.glColor4f((float)1.0f, (float)0.8f, (float)0.8f, (float)0.4f);
                    GL11.glScalef((float)1.2f, (float)1.2f, (float)1.2f);
                    GL11.glRotatef((float)(par1Entity.field_70173_aa * 5), (float)0.0f, (float)1.0f, (float)1.0f);
                    this.ener.func_78088_a(par1Entity, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
                } else if (par1Entity.getjtsre() == 6) {
                    ResourceLocation tx = new ResourceLocation("jinryuumodscore:allw.png");
                    this.field_76990_c.field_78724_e.func_110577_a(tx);
                    GL11.glDepthMask((boolean)true);
                    float seb_szaz = 10.0f;
                    float seb_one = seb_szaz / 100.0f;
                    float max = 1.0f;
                    float ti = par1Entity.field_70173_aa;
                    float curr = ti * seb_one;
                    curr = curr >= max ? max : curr;
                    GL11.glTranslatef((float)0.0f, (float)(-curr / 2.0f), (float)0.0f);
                    GL11.glScalef((float)curr, (float)curr, (float)curr);
                    GL11.glColor4f((float)0.3f, (float)0.6f, (float)0.8f, (float)0.7f);
                    GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)(-par1Entity.field_70177_z), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)par1Entity.field_70125_A, (float)0.0f, (float)0.0f, (float)1.0f);
                    this.ener2.func_78088_a(par1Entity, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
                    GL11.glDepthMask((boolean)true);
                    GL11.glColor4f((float)0.3f, (float)0.45f, (float)0.8f, (float)0.5f);
                    GL11.glScalef((float)(curr * 1.2f), (float)(curr * 1.2f), (float)(curr * 1.2f));
                    this.ener2.func_78088_a(par1Entity, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
                }
                GL11.glDisable((int)3042);
                GL11.glDisable((int)2977);
                GL11.glPopMatrix();
            }
        }
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
        this.renderEnergy((EntityEnergyAttJ2)par1Entity, par2, par4, par6, par8, par9);
    }
}

