/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.entity.EntityCusPar;
import JinRyuu.JRMCore.entity.ModelAura;
import JinRyuu.JRMCore.entity.ModelPG;
import JinRyuu.JRMCore.entity.ModelPS;
import JinRyuu.JRMCore.entity.RenderJRMC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderCusPar
extends RenderJRMC {
    private ModelPG model1;
    private ModelPS model2;

    public RenderCusPar() {
        super((ModelBase)new ModelAura(), 0.5f);
    }

    public void renderAura(EntityCusPar entity, double parX, double parY, double parZ, float par8, float par9) {
        int id;
        this.field_76989_e = 0.0f;
        GL11.glPushMatrix();
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)((float)(-parX)), (float)((float)(-parY) - entity.field_70131_O / 2.0f), (float)((float)parZ));
        GL11.glPushMatrix();
        boolean harom = false;
        if (JGConfigClientSettings.CLIENT_GR11 && entity.getdata34() == 0) {
            for (id = entity.getId(); id > 4; id -= 5) {
            }
            this.model1 = new ModelPG(id);
            harom = true;
        }
        if (JGConfigClientSettings.CLIENT_GR10 && entity.getdata34() == 1) {
            for (id = entity.getId(); id > 4; id -= 5) {
            }
            this.model2 = new ModelPS(id);
            harom = true;
        }
        if (!harom) {
            boolean view2 = JRMCoreClient.mc.field_71474_y.field_74320_O == 2;
            GL11.glPushMatrix();
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            ResourceLocation tx = new ResourceLocation(entity.getdata3());
            this.field_76990_c.field_78724_e.func_110577_a(tx);
            if (entity.getdata42() == 0) {
                GL11.glRotatef((float)RenderManager.field_78727_a.field_78735_i, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(RenderManager.field_78727_a.field_78732_j * (float)(view2 ? 1 : -1)), (float)1.0f, (float)0.0f, (float)0.0f);
            } else if (entity.getdata42() == 1 && entity.hasEnt()) {
                GL11.glRotatef((float)entity.getEnt().field_70177_z, (float)0.0f, (float)1.0f, (float)0.0f);
            } else if (entity.getdata42() == 2 && entity.hasEnt()) {
                GL11.glRotatef((float)(entity.getEnt().field_70125_A * (float)(view2 ? 1 : -1)), (float)1.0f, (float)0.0f, (float)0.0f);
            } else if (entity.getdata42() == 3 && entity.hasEnt()) {
                GL11.glRotatef((float)entity.getEnt().field_70177_z, (float)0.0f, (float)1.0f, (float)0.0f);
                if (!view2) {
                    GL11.glRotatef((float)entity.getEnt().field_70125_A, (float)-1.0f, (float)0.0f, (float)0.0f);
                } else {
                    GL11.glRotatef((float)entity.getEnt().field_70125_A, (float)-1.0f, (float)0.0f, (float)0.0f);
                }
            }
            GL11.glTranslatef((float)entity.getdata43(), (float)entity.getdata44(), (float)entity.getdata45());
            this.fieldPass2(entity);
            GL11.glPopMatrix();
        } else {
            ResourceLocation tx = new ResourceLocation("jinryuumodscore:allw.png");
            this.field_76990_c.field_78724_e.func_110577_a(tx);
            GL11.glTranslatef((float)entity.getdata43(), (float)entity.getdata44(), (float)entity.getdata45());
            this.fieldPass3(0, 0, entity);
        }
        GL11.glPopMatrix();
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }

    @Override
    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderAura((EntityCusPar)par1Entity, par2, par4, par6, par8, par9);
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

    private void fieldPass2(EntityCusPar e) {
        GL11.glPushMatrix();
        float scale = this.func_cs(e);
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)2896);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glDepthMask((boolean)false);
        int id = e.getId();
        float rot = (float)(e.field_70173_aa * 5) * e.getRotation_Sp();
        GL11.glRotatef((float)((e.getRotation() ? rot : 0.0f - rot) + e.getdata39()), (float)0.0f, (float)0.0f, (float)1.0f);
        float rot2 = (float)(e.field_70173_aa * 5) * (e.getRotation_Sp2() * 2.0f);
        GL11.glRotatef((float)((e.getRotation2() ? rot2 : 0.0f - rot2) + e.getdata40()), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)e.getdata40(), (float)0.0f, (float)1.0f, (float)0.0f);
        int id_y = id / (256 / e.getdata32());
        id -= id_y * (256 / e.getdata32());
        float f = e.getdata28();
        if (e.getdata35() && e.hasEnt() && JRMCoreClient.mc.field_71439_g != null && e.getEnt().equals((Object)JRMCoreClient.mc.field_71439_g) && JRMCoreClient.mc.field_71474_y.field_74320_O == 0) {
            f *= (float)JGConfigClientSettings.CLIENT_DA3 / 10.0f;
        }
        float clr1 = this.func_rch(e, e.getdata8(), e.getdata11(), e.getdata14(), e.field_70173_aa);
        float clr2 = this.func_rch(e, e.getdata9(), e.getdata12(), e.getdata15(), e.field_70173_aa);
        float clr3 = this.func_rch(e, e.getdata10(), e.getdata13(), e.getdata16(), e.field_70173_aa);
        GL11.glColor4f((float)clr1, (float)clr2, (float)clr3, (float)f);
        this.drawTexturedModalRect(-(e.getdata32() / 2), -(e.getdata32() / 2), id * e.getdata32(), id_y * e.getdata32(), e.getdata32(), e.getdata32(), 0.0f);
        GL11.glEnable((int)2896);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    private void fieldPass3(int guiLeftO, int guiTopO, EntityCusPar e) {
        GL11.glPushMatrix();
        byte type = e.getdata34();
        float scale = type == 0 ? 0.8f : 0.5f;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)2896);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glDepthMask((boolean)false);
        float rot = (float)(e.field_70173_aa * 5) * e.getRotation_Sp();
        GL11.glRotatef((float)((e.getRotation() ? rot : 0.0f - rot) + e.getdata39()), (float)0.0f, (float)0.0f, (float)1.0f);
        float rot2 = (float)(e.field_70173_aa * 5) * (e.getRotation_Sp2() * 2.0f);
        GL11.glRotatef((float)((e.getRotation2() ? rot2 : 0.0f - rot2) + e.getdata40()), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)e.getdata40(), (float)0.0f, (float)1.0f, (float)0.0f);
        float f = e.getdata28();
        if (e.getdata35() && e.hasEnt() && JRMCoreClient.mc.field_71439_g != null && e.getEnt().equals((Object)JRMCoreClient.mc.field_71439_g) && JRMCoreClient.mc.field_71474_y.field_74320_O == 0) {
            f *= (float)JGConfigClientSettings.CLIENT_DA3 / 10.0f;
        }
        if (e.getdata34() == 0) {
            GL11.glColor4f((float)0.3f, (float)0.55f, (float)0.25f, (float)f);
        } else {
            GL11.glColor4f((float)0.56078434f, (float)0.48235294f, (float)0.43137255f, (float)f);
        }
        if (type == 0) {
            this.model1.func_78088_a(e, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
        } else {
            this.model2.func_78088_a(e, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
        }
        GL11.glEnable((int)2896);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    private float func_rch(EntityCusPar entity, float f1, float f2, float f3, float f4) {
        float fl1 = f1;
        if (entity.getdata31() == 1) {
            fl1 = f1 + f2 * f4;
            if (f2 > 0.0f && fl1 > f3) {
                fl1 = f3;
            } else if (f2 < 0.0f && fl1 < f3) {
                fl1 = f3;
            }
        } else if (entity.getdata31() == 2) {
            if (f1 < f3) {
                float szaz = entity.getdata2();
                float egy = szaz / 100.0f;
                float calc = (float)entity.field_70173_aa / egy;
                fl1 = (f3 - f1) / 100.0f * calc + f1;
            } else if (f1 > f3) {
                float szaz = entity.getdata2();
                float egy = szaz / 100.0f;
                float calc = (float)entity.field_70173_aa / egy;
                fl1 = f1 - (f1 - f3) / 100.0f * calc;
            }
        }
        return fl1;
    }

    private float func_cs(EntityCusPar entity) {
        float f1 = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        if (entity.getdata4() == 0) {
            f1 = entity.getdata5();
        } else if (entity.getdata4() == 1) {
            f2 = entity.getdata7();
            f3 = entity.getdata6();
            f1 = entity.getdata5() + (float)entity.field_70173_aa * f2;
            if (f2 > 0.0f && f1 > f3) {
                f1 = f3;
            } else if (f2 < 0.0f && f1 < f3) {
                f1 = f3;
            }
        } else if (entity.getdata4() == 2) {
            f2 = entity.getdata7();
            f3 = entity.getdata6();
            float szaz = entity.getdata2();
            float egy = szaz / 100.0f;
            float calc = (float)entity.field_70173_aa / egy;
            if (f2 > 0.0f) {
                f1 = entity.getdata5() / 100.0f * calc;
            } else if (f2 < 0.0f) {
                f1 = entity.getdata5() - entity.getdata5() / 100.0f * calc;
            }
        }
        return f1;
    }
}

