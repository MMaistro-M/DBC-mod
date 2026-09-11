/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common.Entitys;

import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.DragonBC.common.Entitys.EntityMajinAbsorption;
import JinRyuu.DragonBC.common.Npcs.ModelEnergy;
import JinRyuu.DragonBC.common.Npcs.RenderDBC;
import JinRyuu.JRMCore.JRMCoreClient;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderMajinAbsorption
extends RenderDBC {
    private ModelEnergy ener = new ModelEnergy();

    public RenderMajinAbsorption() {
        super((ModelBase)new ModelEnergy(), 0.0f);
    }

    public void renderEnergy(EntityMajinAbsorption par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.field_76989_e = 0.0f;
        if (par1Entity != null && par1Entity.shootingEntity != null) {
            float scaleW;
            int col = par1Entity.getBodyColor();
            boolean b = false;
            if (par1Entity.shootingEntity != null) {
                b = par1Entity.shootingEntity.func_145782_y() == JRMCoreClient.mc.field_71439_g.func_145782_y();
            }
            float var13 = this.handleRotationFloat(par1Entity, par9);
            double x = par1Entity.field_70165_t;
            double y = par1Entity.field_70163_u;
            double z = par1Entity.field_70161_v;
            boolean hasTarget = par1Entity.getMode() == 1;
            boolean isComingBack = par1Entity.getMode() == 2;
            float BASE_SCALE = 1.0f;
            float size = par1Entity.targetH > par1Entity.targetW ? par1Entity.targetH : par1Entity.targetW;
            float sin = (float)(Math.sin(var13) / 2.0);
            float scaleH = hasTarget ? size * 0.65f + 0.5f + sin / 2.0f : 1.0f + sin;
            float f = scaleW = hasTarget ? size * 0.65f + 0.5f - sin / 2.0f : 1.0f - sin;
            if (!DBCClient.mc.func_147113_T()) {
                par1Entity.visualH = par1Entity.visualH + (double)(scaleH / 20.0f * (isComingBack ? -3.0f : 0.3f));
                if (isComingBack ? par1Entity.visualH < (double)scaleH : par1Entity.visualH > (double)scaleH) {
                    par1Entity.visualH = scaleH;
                }
                par1Entity.visualW = par1Entity.visualW + (double)(scaleW / 20.0f * (isComingBack ? -3.0f : 0.3f));
                if (isComingBack ? par1Entity.visualW < (double)scaleW : par1Entity.visualW > (double)scaleW) {
                    par1Entity.visualW = scaleW;
                }
            }
            GL11.glPushMatrix();
            GL11.glDisable((int)2896);
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)240.0f);
            GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
            GL11.glRotatef((float)(par1Entity.field_70126_B + (par1Entity.field_70177_z - par1Entity.field_70126_B) * par9 - 180.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(par1Entity.field_70127_C + (par1Entity.field_70125_A - par1Entity.field_70127_C) * par9), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            this.tex(col);
            GL11.glScaled((double)par1Entity.visualW, (double)par1Entity.visualH, (double)par1Entity.visualW);
            this.ener.renderModel((byte)1, par1Entity, 0.0f, 0.0f, 0.0625f, var13, false);
            GL11.glDepthMask((boolean)true);
            GL11.glEnable((int)2896);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPopMatrix();
        }
    }

    public void func_76979_b(Entity p_76979_1_, double p_76979_2_, double p_76979_4_, double p_76979_6_, float p_76979_8_, float p_76979_9_) {
    }

    public void tex(int col) {
        float h2 = (float)(col >> 16 & 0xFF) / 255.0f;
        float h3 = (float)(col >> 8 & 0xFF) / 255.0f;
        float h4 = (float)(col & 0xFF) / 255.0f;
        GL11.glColor4f((float)h2, (float)h3, (float)h4, (float)1.0f);
        ResourceLocation txx = new ResourceLocation("jinryuudragonbc:majin_absorption.png");
        this.field_76990_c.field_78724_e.func_110577_a(txx);
    }

    protected float handleRotationFloat(Entity par1Entity, float par2) {
        return ((float)par1Entity.field_70173_aa + par2) / 2.0f;
    }

    @Override
    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderEnergy((EntityMajinAbsorption)par1Entity, par2, par4, par6, par8, par9);
    }
}

