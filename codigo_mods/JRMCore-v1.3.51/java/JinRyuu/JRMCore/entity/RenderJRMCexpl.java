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

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.entity.EntityJRMCexpl;
import JinRyuu.JRMCore.entity.RenderJRMC;
import JinRyuu.JRMCore.m.mBlob;
import JinRyuu.JRMCore.m.mEnergy5;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderJRMCexpl
extends RenderJRMC {
    private mBlob aModel = new mBlob();
    private mEnergy5 bModel = new mEnergy5();

    public RenderJRMCexpl() {
        super((ModelBase)new mBlob(), 0.0f);
    }

    public void renderJRMCexpl(EntityJRMCexpl par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.field_76989_e = 0.0f;
        if (par1Entity.type != 10 && par1Entity.type < 3 && JGConfigClientSettings.CLIENT_DA7 && par1Entity.type != 0) {
            boolean ext = JGConfigClientSettings.CLIENT_DA5;
            GL11.glPushMatrix();
            float var13 = this.handleRotationFloat(par1Entity, par9);
            float size = this.handleSizeFloat(par1Entity, par9);
            Random rand = new Random();
            float randfloat = (float)((double)rand.nextInt(5) * 0.1);
            float var20 = size;
            GL11.glTranslatef((float)((float)par2 + 0.0f), (float)((float)par4 + 0.0f), (float)((float)par6 + 0.0f));
            GL11.glRotatef((float)(par1Entity.field_70126_B + (par1Entity.field_70177_z - par1Entity.field_70126_B) * par9 - 180.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(par1Entity.field_70127_C + (par1Entity.field_70125_A - par1Entity.field_70127_C) * par9), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            if (!ext) {
                ResourceLocation txx = new ResourceLocation(JRMCoreH.tjjrmc + ":en.png");
                this.field_76990_c.field_78724_e.func_110577_a(txx);
            } else if (ext) {
                ResourceLocation txx = new ResourceLocation(JRMCoreH.tjjrmc + ":en2.png");
                this.field_76990_c.field_78724_e.func_110577_a(txx);
            }
            JRMCoreClient.mc.field_71460_t.func_78483_a(0.0);
            GL11.glDisable((int)2896);
            if (ext) {
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glAlphaFunc((int)516, (float)0.003921569f);
                GL11.glDepthMask((boolean)false);
            }
            float t = par1Entity.field_70173_aa;
            float sp = t * par1Entity.explsiz / 5.0f;
            GL11.glPushMatrix();
            if (!ext) {
                GL11.glScalef((float)var20, (float)var20, (float)var20);
                this.aModel.renderModel(par1Entity, 0.0f, 0.0f, 0.0625f, var13);
            } else if (ext) {
                GL11.glRotatef((float)(t * 10.0f), (float)0.5f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)sp, (float)sp, (float)sp);
                float szaz = par1Entity.MaxAge;
                float egy = szaz / 100.0f;
                float calc = (float)par1Entity.Age / egy / 100.0f;
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(1.0f - calc));
                this.bModel.renderModel(par1Entity, 0.0f, 0.0f, 0.0625f, var13);
            }
            GL11.glPopMatrix();
            GL11.glEnable((int)2896);
            GL11.glDisable((int)3042);
            JRMCoreClient.mc.field_71460_t.func_78463_b(0.0);
            GL11.glDepthMask((boolean)false);
            GL11.glPopMatrix();
        }
    }

    protected float handleRotationFloat(Entity par1Entity, float par2) {
        return (float)par1Entity.field_70173_aa + par2;
    }

    protected float handleSizeFloat(EntityJRMCexpl par1Entity, float par2) {
        float size = par1Entity.explsiz;
        float f = (float)par1Entity.MaxAge * 0.2f;
        float f2 = (float)par1Entity.MaxAge - f;
        float ticksExsisted = par1Entity.field_70173_aa;
        ticksExsisted = ticksExsisted < f ? ticksExsisted / f * size : (ticksExsisted > f2 ? ((float)par1Entity.MaxAge - ticksExsisted) / f * size : size);
        return ticksExsisted;
    }

    @Override
    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderJRMCexpl((EntityJRMCexpl)par1Entity, par2, par4, par6, par8, par9);
    }
}

