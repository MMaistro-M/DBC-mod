/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.entity.EntityPunch;
import JinRyuu.JRMCore.entity.ModelKiExplosion;
import JinRyuu.JRMCore.entity.RenderJRMC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderPunch
extends RenderJRMC {
    private ModelKiExplosion aModel = new ModelKiExplosion();

    public RenderPunch() {
        super((ModelBase)new ModelKiExplosion(), 0.5f);
    }

    public void renderPunch(EntityPunch par1Entity, double par2, double par4, double par6, float par8, float par9) {
        GL11.glPushMatrix();
        float var13 = this.handleRotationFloat(par1Entity, par9);
        Random rand = new Random();
        float randfloat = (float)((double)rand.nextInt(5) * 0.1);
        float var20 = 0.001f;
        GL11.glTranslatef((float)((float)par2 + 0.0f), (float)((float)par4 + 0.0f), (float)((float)par6 + 0.0f));
        GL11.glRotatef((float)(par1Entity.field_70126_B + (par1Entity.field_70177_z - par1Entity.field_70126_B) * par9 - 180.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(par1Entity.field_70127_C + (par1Entity.field_70125_A - par1Entity.field_70127_C) * par9), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        ResourceLocation txx = new ResourceLocation("jinryuudragonbc:Ki/ki.png");
        this.field_76990_c.field_78724_e.func_110577_a(txx);
        GL11.glEnable((int)2977);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glPushMatrix();
        GL11.glScalef((float)var20, (float)var20, (float)var20);
        this.aModel.renderModel(par1Entity, 0.0f, 0.0f, 0.0625f, var13);
        GL11.glPopMatrix();
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    protected float handleRotationFloat(Entity par1Entity, float par2) {
        return (float)par1Entity.field_70173_aa + par2;
    }

    @Override
    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderPunch((EntityPunch)par1Entity, par2, par4, par6, par8, par9);
    }
}

