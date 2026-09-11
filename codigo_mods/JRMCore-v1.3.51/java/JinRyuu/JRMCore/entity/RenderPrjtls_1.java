/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.entity.EntityPrjtls_1;
import JinRyuu.JRMCore.entity.ModelPrjtls_1;
import JinRyuu.JRMCore.entity.ModelPrjtls_2;
import JinRyuu.JRMCore.entity.ModelPrjtls_3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderPrjtls_1
extends Render {
    private ModelPrjtls_1 mod1;
    private ModelPrjtls_2 mod2;
    private ModelPrjtls_3 mod3;
    String[] wt = new String[]{"Rocket1", "Rocket2", "Rocket3", "Rocket2", "Rocket2", "Rocket2", "Rocket2"};

    public RenderPrjtls_1() {
        this.mod1 = new ModelPrjtls_1();
        this.mod2 = new ModelPrjtls_2();
        this.mod3 = new ModelPrjtls_3();
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return null;
    }

    public void renderItemKunai(EntityPrjtls_1 Entity2, double par2, double par4, double par6, float par8, float par9) {
        int wt = Entity2.getWpnTyp();
        if (wt > -1) {
            ResourceLocation txx = new ResourceLocation("jinryuudragonbc:projectiles/" + this.wt[wt] + ".png");
            this.field_76990_c.field_78724_e.func_110577_a(txx);
            GL11.glPushMatrix();
            GL11.glEnable((int)2977);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glDepthMask((boolean)true);
            GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
            GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(-Entity2.field_70177_z), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(Entity2.field_70125_A - 90.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
            float sc = 0.7f;
            sc = wt == 0 ? 0.7f : (wt == 1 || wt == 5 ? 1.3f : (wt == 2 ? 2.0f : 0.2f));
            GL11.glScalef((float)sc, (float)sc, (float)sc);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            if (wt == 0) {
                this.mod1.func_78088_a((Entity)Entity2, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
            } else if (wt == 1 || wt == 5) {
                this.mod2.func_78088_a((Entity)Entity2, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
            } else if (wt == 2) {
                this.mod3.func_78088_a((Entity)Entity2, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
            } else {
                this.mod2.func_78088_a((Entity)Entity2, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
            }
            GL11.glDisable((int)3042);
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glDepthMask((boolean)true);
            GL11.glPopMatrix();
        }
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderItemKunai((EntityPrjtls_1)par1Entity, par2, par4, par6, par8, par9);
    }
}

