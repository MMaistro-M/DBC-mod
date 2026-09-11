/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JBRA;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHJYC;
import JinRyuu.JRMCore.entity.ModelBipedBody;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class JRMC_GiTurtleMdl
extends ModelBipedBody {
    private final int VANITY_CRISTMAS_0 = 0;
    private final int VANITY_CRISTMAS_1 = 1;
    private final int VANITY_CRISTMAS_2 = 2;
    private final int VANITY_CRISTMAS_3 = 3;
    private final int VANITY_CRISTMAS_4 = 4;
    public int id = -1;
    public ModelRenderer Head;
    public ModelRenderer Head2;
    public ModelRenderer Head3;
    public ModelRenderer HeadTail1;
    public ModelRenderer HeadTail2;
    public ModelRenderer Larm_1;
    public ModelRenderer Rarm_1;
    public ModelRenderer Body_1;
    public ModelRenderer Larm2_1;
    public ModelRenderer Rarm2_1;
    public ModelRenderer Body2_1;
    public ModelRenderer Body3_1;
    public ModelRenderer Larm_2;
    public ModelRenderer Rarm_2;
    public ModelRenderer Body_2;
    public ModelRenderer Larm2_2;
    public ModelRenderer Rarm2_2;
    public ModelRenderer Body2_2;
    public ModelRenderer Body4_2;
    public ModelRenderer Body3_2;
    public ModelRenderer Lleg_3;
    public ModelRenderer RLeg_3;
    public ModelRenderer Body_3;
    public ModelRenderer Body2_3;
    public ModelRenderer Lleg_4;
    public ModelRenderer RLeg_4;
    public ModelRenderer Lleg2_4;
    public ModelRenderer RLeg2_4;
    private float size = 1.0f;

    public JRMC_GiTurtleMdl(int id) {
        super(0.1f);
        this.id = id;
        if (id == 0) {
            this.field_78090_t = 64;
            this.field_78089_u = 32;
            this.Head2 = new ModelRenderer((ModelBase)this, 0, 15);
            this.Head2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Head2.func_78790_a(-4.5f, -6.4f, -5.0f, 9, 2, 9, 0.01f);
            this.setRotateAngle(this.Head2, -0.09773844f, 0.0f, 0.0f);
            this.HeadTail2 = new ModelRenderer((ModelBase)this, 51, 22);
            this.HeadTail2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.HeadTail2.func_78790_a(-1.0f, -1.3f, 3.2f, 2, 2, 2, 0.01f);
            this.HeadTail1 = new ModelRenderer((ModelBase)this, 48, 15);
            this.HeadTail1.func_78793_a(0.2f, -7.5f, 4.6f);
            this.HeadTail1.func_78790_a(-1.5f, -0.7f, -0.8f, 3, 1, 4, 0.01f);
            this.setRotateAngle(this.HeadTail1, -0.7285004f, 0.0f, 0.0f);
            this.Head = new ModelRenderer((ModelBase)this, 0, 0);
            this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 3, 8, 0.03f);
            this.Head3 = new ModelRenderer((ModelBase)this, 35, 0);
            this.Head3.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Head3.func_78790_a(-3.0f, -8.9f, -2.1f, 6, 2, 7, 0.01f);
            this.Head.func_78792_a(this.Head2);
            this.HeadTail1.func_78792_a(this.HeadTail2);
            this.Head3.func_78792_a(this.HeadTail1);
            this.Head.func_78792_a(this.Head3);
        } else if (id == 1) {
            this.field_78090_t = 64;
            this.field_78089_u = 32;
            this.Larm_1 = new ModelRenderer((ModelBase)this, 31, 4);
            this.Larm_1.field_78809_i = true;
            this.Larm_1.func_78793_a(5.0f, 2.0f, 0.0f);
            this.Larm_1.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 8, 4, 0.02f);
            this.Body_1 = new ModelRenderer((ModelBase)this, 0, 0);
            this.Body_1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Body_1.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.02f);
            this.Body2_1 = new ModelRenderer((ModelBase)this, 0, 25);
            this.Body2_1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Body2_1.func_78790_a(-4.5f, 10.6f, -2.5f, 9, 2, 5, 0.01f);
            this.Rarm_1 = new ModelRenderer((ModelBase)this, 31, 4);
            this.Rarm_1.func_78793_a(-5.0f, 2.0f, 0.0f);
            this.Rarm_1.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 8, 4, 0.02f);
            this.Body3_1 = new ModelRenderer((ModelBase)this, 0, 17);
            this.Body3_1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Body3_1.func_78790_a(-4.5f, -0.6f, -2.5f, 9, 2, 5, 0.01f);
            this.Rarm2_1 = new ModelRenderer((ModelBase)this, 30, 17);
            this.Rarm2_1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Rarm2_1.func_78790_a(-3.7f, 5.5f, -2.5f, 5, 2, 5, 0.01f);
            this.Larm2_1 = new ModelRenderer((ModelBase)this, 30, 17);
            this.Larm2_1.field_78809_i = true;
            this.Larm2_1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Larm2_1.func_78790_a(-1.3f, 5.5f, -2.5f, 5, 2, 5, 0.01f);
            this.Body_1.func_78792_a(this.Body2_1);
            this.Body_1.func_78792_a(this.Body3_1);
            this.Rarm_1.func_78792_a(this.Rarm2_1);
            this.Larm_1.func_78792_a(this.Larm2_1);
        } else if (id == 2) {
            this.field_78090_t = 64;
            this.field_78089_u = 32;
            this.Larm2_2 = new ModelRenderer((ModelBase)this, 36, 21);
            this.Larm2_2.field_78809_i = true;
            this.Larm2_2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Larm2_2.func_78790_a(-1.3f, 5.5f, -2.5f, 5, 2, 5, 0.01f);
            this.Body2_2 = new ModelRenderer((ModelBase)this, 0, 16);
            this.Body2_2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Body2_2.func_78790_a(-4.5f, 10.6f, -2.5f, 9, 2, 5, 0.01f);
            this.Rarm2_2 = new ModelRenderer((ModelBase)this, 36, 21);
            this.Rarm2_2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Rarm2_2.func_78790_a(-3.7f, 5.5f, -2.5f, 5, 2, 5, 0.01f);
            this.Body4_2 = new ModelRenderer((ModelBase)this, 24, 0);
            this.Body4_2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Body4_2.func_78790_a(-4.5f, -0.6f, -2.5f, 9, 2, 5, 0.01f);
            this.Body3_2 = new ModelRenderer((ModelBase)this, 0, 23);
            this.Body3_2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Body3_2.func_78790_a(-5.0f, 11.8f, -3.0f, 10, 2, 6, 0.01f);
            this.Larm_2 = new ModelRenderer((ModelBase)this, 38, 8);
            this.Larm_2.field_78809_i = true;
            this.Larm_2.func_78793_a(5.0f, 2.0f, 0.0f);
            this.Larm_2.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 8, 4, 0.02f);
            this.Body_2 = new ModelRenderer((ModelBase)this, 0, 0);
            this.Body_2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Body_2.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.03f);
            this.Rarm_2 = new ModelRenderer((ModelBase)this, 38, 8);
            this.Rarm_2.func_78793_a(-5.0f, 2.0f, 0.0f);
            this.Rarm_2.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 8, 4, 0.02f);
            this.Larm_2.func_78792_a(this.Larm2_2);
            this.Body_2.func_78792_a(this.Body2_2);
            this.Rarm_2.func_78792_a(this.Rarm2_2);
            this.Body_2.func_78792_a(this.Body4_2);
            this.Body2_2.func_78792_a(this.Body3_2);
        } else if (id == 3) {
            this.field_78090_t = 64;
            this.field_78089_u = 32;
            this.Body_3 = new ModelRenderer((ModelBase)this, 0, 0);
            this.Body_3.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Body_3.func_78790_a(-4.0f, 8.1f, -2.0f, 8, 4, 4, 0.01f);
            this.RLeg_3 = new ModelRenderer((ModelBase)this, 0, 16);
            this.RLeg_3.func_78793_a(-1.9f, 12.0f, 0.0f);
            this.RLeg_3.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 9, 4, 0.01f);
            this.Lleg_3 = new ModelRenderer((ModelBase)this, 0, 16);
            this.Lleg_3.field_78809_i = true;
            this.Lleg_3.func_78793_a(1.9f, 12.0f, 0.0f);
            this.Lleg_3.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 9, 4, 0.01f);
            this.Body2_3 = new ModelRenderer((ModelBase)this, 0, 8);
            this.Body2_3.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Body2_3.func_78790_a(-4.5f, 7.4f, -2.5f, 9, 2, 5, 0.01f);
            this.Body_3.func_78792_a(this.Body2_3);
        } else if (id == 4) {
            this.field_78090_t = 64;
            this.field_78089_u = 32;
            this.Lleg2_4 = new ModelRenderer((ModelBase)this, 1, 1);
            this.Lleg2_4.field_78809_i = true;
            this.Lleg2_4.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Lleg2_4.func_78790_a(-2.3f, 6.4f, -2.5f, 5, 2, 5, 0.01f);
            this.RLeg2_4 = new ModelRenderer((ModelBase)this, 1, 1);
            this.RLeg2_4.func_78793_a(0.0f, 0.0f, 0.0f);
            this.RLeg2_4.func_78790_a(-2.7f, 6.4f, -2.5f, 5, 2, 5, 0.01f);
            this.Lleg_4 = new ModelRenderer((ModelBase)this, 1, 10);
            this.Lleg_4.field_78809_i = true;
            this.Lleg_4.func_78793_a(1.9f, 12.0f, 0.0f);
            this.Lleg_4.func_78790_a(-2.0f, 8.0f, -2.0f, 4, 4, 4, 0.02f);
            this.RLeg_4 = new ModelRenderer((ModelBase)this, 1, 10);
            this.RLeg_4.func_78793_a(-1.9f, 12.0f, 0.0f);
            this.RLeg_4.func_78790_a(-2.0f, 8.0f, -2.0f, 4, 4, 4, 0.02f);
            this.Lleg_4.func_78792_a(this.Lleg2_4);
            this.RLeg_4.func_78792_a(this.RLeg2_4);
        }
    }

    @Override
    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        float f6 = this.size;
        if (JRMCoreH.JYC()) {
            float age = JRMCoreHJYC.JYCAge((EntityPlayer)entity);
            float childScl = JRMCoreHJYC.JYCsizeBasedOnAge((EntityPlayer)entity);
            this.size = childScl = 3.0f - childScl * 2.0f;
        }
        if (this.id == 0) {
            GL11.glScalef((float)(0.5f + 0.5f / f6), (float)(0.5f + 0.5f / f6), (float)(0.5f + 0.5f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) / f6 * (2.0f - (f6 >= 1.5f && f6 <= 2.0f ? (2.0f - f6) / 2.5f : (f6 < 1.5f && f6 >= 1.0f ? (f6 * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
            GL11.glPushMatrix();
            GL11.glScalef((float)1.1f, (float)1.1f, (float)1.1f);
            this.Head.func_78785_a(f5);
            GL11.glPopMatrix();
        } else if (this.id == 1) {
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
            this.Larm_1.func_78785_a(f5);
            this.Body_1.func_78785_a(f5);
            this.Rarm_1.func_78785_a(f5);
        } else if (this.id == 2) {
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
            this.Larm_2.func_78785_a(f5);
            this.Body_2.func_78785_a(f5);
            this.Rarm_2.func_78785_a(f5);
        } else if (this.id == 3) {
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
            this.Body_3.func_78785_a(f5);
            this.RLeg_3.func_78785_a(f5);
            this.Lleg_3.func_78785_a(f5);
        } else if (this.id == 4) {
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
            this.Lleg_4.func_78785_a(f5);
            this.RLeg_4.func_78785_a(f5);
        }
        GL11.glPopMatrix();
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    @Override
    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        super.func_78087_a(par1, par2, par3, par4, par5, par6, entity);
        if (this.id == 0) {
            this.Head.field_78798_e = this.field_78116_c.field_78798_e;
            this.Head.field_78797_d = this.field_78116_c.field_78797_d;
            this.Head.field_78800_c = this.field_78116_c.field_78800_c;
            this.Head.field_78808_h = this.field_78116_c.field_78808_h;
            this.Head.field_78796_g = this.field_78116_c.field_78796_g;
            this.Head.field_78795_f = this.field_78116_c.field_78795_f;
            float s = 0.0f;
            float s2 = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.0f * par2;
            float s3 = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.0f * par2;
            this.HeadTail1.field_78795_f = (s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s)) / 4.0f - 0.7285004f;
            this.HeadTail2.field_78795_f = (s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s)) / 4.0f;
        } else if (this.id == 1) {
            this.Larm_1.field_78798_e = this.field_78113_g.field_78798_e;
            this.Larm_1.field_78797_d = this.field_78113_g.field_78797_d;
            this.Larm_1.field_78800_c = this.field_78113_g.field_78800_c;
            this.Larm_1.field_78808_h = this.field_78113_g.field_78808_h;
            this.Larm_1.field_78796_g = this.field_78113_g.field_78796_g;
            this.Larm_1.field_78795_f = this.field_78113_g.field_78795_f;
            this.Rarm_1.field_78798_e = this.field_78112_f.field_78798_e;
            this.Rarm_1.field_78797_d = this.field_78112_f.field_78797_d;
            this.Rarm_1.field_78800_c = this.field_78112_f.field_78800_c;
            this.Rarm_1.field_78808_h = this.field_78112_f.field_78808_h;
            this.Rarm_1.field_78796_g = this.field_78112_f.field_78796_g;
            this.Rarm_1.field_78795_f = this.field_78112_f.field_78795_f;
            this.Body_1.field_78798_e = this.field_78115_e.field_78798_e;
            this.Body_1.field_78797_d = this.field_78115_e.field_78797_d;
            this.Body_1.field_78800_c = this.field_78115_e.field_78800_c;
            this.Body_1.field_78808_h = this.field_78115_e.field_78808_h;
            this.Body_1.field_78796_g = this.field_78115_e.field_78796_g;
            this.Body_1.field_78795_f = this.field_78115_e.field_78795_f;
        } else if (this.id == 2) {
            this.Larm_2.field_78798_e = this.field_78113_g.field_78798_e;
            this.Larm_2.field_78797_d = this.field_78113_g.field_78797_d;
            this.Larm_2.field_78800_c = this.field_78113_g.field_78800_c;
            this.Larm_2.field_78808_h = this.field_78113_g.field_78808_h;
            this.Larm_2.field_78796_g = this.field_78113_g.field_78796_g;
            this.Larm_2.field_78795_f = this.field_78113_g.field_78795_f;
            this.Rarm_2.field_78798_e = this.field_78112_f.field_78798_e;
            this.Rarm_2.field_78797_d = this.field_78112_f.field_78797_d;
            this.Rarm_2.field_78800_c = this.field_78112_f.field_78800_c;
            this.Rarm_2.field_78808_h = this.field_78112_f.field_78808_h;
            this.Rarm_2.field_78796_g = this.field_78112_f.field_78796_g;
            this.Rarm_2.field_78795_f = this.field_78112_f.field_78795_f;
            this.Body_2.field_78798_e = this.field_78115_e.field_78798_e;
            this.Body_2.field_78797_d = this.field_78115_e.field_78797_d;
            this.Body_2.field_78800_c = this.field_78115_e.field_78800_c;
            this.Body_2.field_78808_h = this.field_78115_e.field_78808_h;
            this.Body_2.field_78796_g = this.field_78115_e.field_78796_g;
            this.Body_2.field_78795_f = this.field_78115_e.field_78795_f;
        } else if (this.id == 3) {
            this.Body_3.field_78798_e = this.field_78115_e.field_78798_e;
            this.Body_3.field_78797_d = this.field_78115_e.field_78797_d;
            this.Body_3.field_78800_c = this.field_78115_e.field_78800_c;
            this.Body_3.field_78808_h = this.field_78115_e.field_78808_h;
            this.Body_3.field_78796_g = this.field_78115_e.field_78796_g;
            this.Body_3.field_78795_f = this.field_78115_e.field_78795_f;
            this.RLeg_3.field_78798_e = this.field_78123_h.field_78798_e;
            this.RLeg_3.field_78797_d = this.field_78123_h.field_78797_d;
            this.RLeg_3.field_78800_c = this.field_78123_h.field_78800_c;
            this.RLeg_3.field_78808_h = this.field_78123_h.field_78808_h;
            this.RLeg_3.field_78796_g = this.field_78123_h.field_78796_g;
            this.RLeg_3.field_78795_f = this.field_78123_h.field_78795_f;
            this.Lleg_3.field_78798_e = this.field_78124_i.field_78798_e;
            this.Lleg_3.field_78797_d = this.field_78124_i.field_78797_d;
            this.Lleg_3.field_78800_c = this.field_78124_i.field_78800_c;
            this.Lleg_3.field_78808_h = this.field_78124_i.field_78808_h;
            this.Lleg_3.field_78796_g = this.field_78124_i.field_78796_g;
            this.Lleg_3.field_78795_f = this.field_78124_i.field_78795_f;
        } else if (this.id == 4) {
            this.RLeg_4.field_78798_e = this.field_78123_h.field_78798_e;
            this.RLeg_4.field_78797_d = this.field_78123_h.field_78797_d;
            this.RLeg_4.field_78800_c = this.field_78123_h.field_78800_c;
            this.RLeg_4.field_78808_h = this.field_78123_h.field_78808_h;
            this.RLeg_4.field_78796_g = this.field_78123_h.field_78796_g;
            this.RLeg_4.field_78795_f = this.field_78123_h.field_78795_f;
            this.Lleg_4.field_78798_e = this.field_78124_i.field_78798_e;
            this.Lleg_4.field_78797_d = this.field_78124_i.field_78797_d;
            this.Lleg_4.field_78800_c = this.field_78124_i.field_78800_c;
            this.Lleg_4.field_78808_h = this.field_78124_i.field_78808_h;
            this.Lleg_4.field_78796_g = this.field_78124_i.field_78796_g;
            this.Lleg_4.field_78795_f = this.field_78124_i.field_78795_f;
        }
    }
}

