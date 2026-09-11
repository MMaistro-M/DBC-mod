/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common.Entitys;

import JinRyuu.DragonBC.common.Entitys.EntityEnergyFM;
import JinRyuu.DragonBC.common.Npcs.ModelEnergy;
import JinRyuu.DragonBC.common.Npcs.RenderDBC;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderEnergyFM
extends RenderDBC {
    private ModelEnergy ener = new ModelEnergy();
    private double dist;

    public RenderEnergyFM() {
        super((ModelBase)new ModelEnergy(), 0.5f);
    }

    public void renderEnergy(EntityEnergyFM par1Entity, double par2, double par4, double par6, float par8, float par9) {
        double d;
        int shrink = par1Entity.getShrink();
        int col = JRMCoreH.techCol[1];
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
        this.dist = shrink > 0 ? ((d = this.dist - (double)((float)par1Entity.getSpe() * 0.05f)) < 0.0 ? 0.0 : d) : (double)MathHelper.func_76133_a((double)(x * x + y * y + z * z));
        int sbh = (int)(par1Entity.field_70163_u > 80.0 ? par1Entity.field_70163_u - 80.0 : 1.0) * 4;
        float var20 = 1.0f + (float)(sbh / 5 * 100) * 0.02f * 0.15f;
        double sx2 = sx - JRMCoreClient.mc.field_71439_g.field_70165_t;
        double sy2 = sy - JRMCoreClient.mc.field_71439_g.field_70163_u;
        double sz2 = sz - JRMCoreClient.mc.field_71439_g.field_70161_v;
        GL11.glPushMatrix();
        GL11.glDisable((int)2896);
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)240.0f);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
        GL11.glRotatef((float)(par1Entity.field_70126_B + (par1Entity.field_70177_z - par1Entity.field_70126_B) * par9 - 180.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(par1Entity.field_70127_C + (par1Entity.field_70125_A - par1Entity.field_70127_C) * par9), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        this.tex(col);
        GL11.glScalef((float)(var20 / 2.0f), (float)(var20 / 2.0f), (float)(var20 / 2.0f));
        this.ener.renderModel((byte)1, par1Entity, 0.0f, 0.0f, 0.0625f, var13, false);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2896);
        GL11.glDisable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
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
        this.renderEnergy((EntityEnergyFM)par1Entity, par2, par4, par6, par8, par9);
    }
}

