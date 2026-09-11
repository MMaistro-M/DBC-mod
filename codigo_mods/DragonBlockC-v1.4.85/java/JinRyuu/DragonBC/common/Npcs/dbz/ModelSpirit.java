/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.DragonBC.common.Npcs.dbz;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelSpirit
extends ModelBase {
    public ModelRenderer Body;
    public ModelRenderer Body1;
    public ModelRenderer Body2;
    public ModelRenderer Body3;
    public ModelRenderer Body4;
    public ModelRenderer Body5;
    public ModelRenderer Body6;
    public ModelRenderer Body7;
    public ModelRenderer Body8;
    public ModelRenderer BodyR;
    public ModelRenderer BodyL;
    public ModelRenderer BodyTop;
    public ModelRenderer BodyBot;
    public ModelRenderer BodyFront;
    public ModelRenderer Bodytail1;
    public ModelRenderer Bodytail2;
    public ModelRenderer Bodytail3;
    public ModelRenderer Bodytail4;

    public ModelSpirit() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Body8 = new ModelRenderer((ModelBase)this, 1, 23);
        this.Body8.func_78793_a(-1.5f, 3.1f, 2.5f);
        this.Body8.func_78790_a(-2.5f, -2.0f, -2.7f, 4, 4, 5, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 0, 10);
        this.Body1.func_78793_a(-1.8f, -1.6f, -2.2f);
        this.Body1.func_78790_a(-3.0f, -3.0f, -3.0f, 6, 6, 6, 0.0f);
        this.BodyR = new ModelRenderer((ModelBase)this, 1, 23);
        this.BodyR.func_78793_a(-3.2f, 1.8f, 0.4f);
        this.BodyR.func_78790_a(-2.5f, -2.0f, -2.7f, 4, 4, 5, 0.0f);
        this.BodyBot = new ModelRenderer((ModelBase)this, 1, 47);
        this.BodyBot.func_78793_a(-1.1f, 5.7f, 0.4f);
        this.BodyBot.func_78790_a(-2.5f, -1.5f, -2.7f, 5, 3, 5, 0.0f);
        this.Body5 = new ModelRenderer((ModelBase)this, 21, 33);
        this.Body5.func_78793_a(1.2f, 3.1f, -1.6f);
        this.Body5.func_78790_a(-3.0f, -3.0f, -3.0f, 6, 6, 6, 0.0f);
        this.Body6 = new ModelRenderer((ModelBase)this, 0, 33);
        this.Body6.func_78793_a(-2.0f, 2.8f, -2.3f);
        this.Body6.func_78790_a(-2.5f, -2.5f, -2.5f, 5, 5, 5, 0.0f);
        this.Bodytail2 = new ModelRenderer((ModelBase)this, 45, 54);
        this.Bodytail2.func_78793_a(-1.0f, -0.1f, 0.7f);
        this.Bodytail2.func_78790_a(-2.5f, -2.0f, -2.7f, 4, 4, 5, 0.0f);
        this.BodyTop = new ModelRenderer((ModelBase)this, 1, 1);
        this.BodyTop.func_78793_a(0.9f, -4.2f, 0.1f);
        this.BodyTop.func_78790_a(-2.5f, -1.5f, -2.7f, 5, 3, 5, 0.0f);
        this.setRotateAngle(this.BodyTop, 0.0f, 0.0f, -0.011152261f);
        this.BodyFront = new ModelRenderer((ModelBase)this, 21, 24);
        this.BodyFront.func_78793_a(1.4f, 0.4f, -4.9f);
        this.BodyFront.func_78790_a(-2.5f, -2.5f, -1.5f, 4, 5, 3, 0.0f);
        this.Bodytail3 = new ModelRenderer((ModelBase)this, 49, 46);
        this.Bodytail3.func_78793_a(0.0f, 0.9f, 2.7f);
        this.Bodytail3.func_78790_a(-1.5f, -1.5f, -1.9f, 3, 3, 4, 0.0f);
        this.Bodytail4 = new ModelRenderer((ModelBase)this, 53, 39);
        this.Bodytail4.func_78793_a(0.0f, 1.1f, 1.9f);
        this.Bodytail4.func_78790_a(-0.6f, -1.5f, -1.9f, 1, 2, 4, 0.0f);
        this.Body3 = new ModelRenderer((ModelBase)this, 1, 11);
        this.Body3.func_78793_a(1.0f, -1.5f, 2.1f);
        this.Body3.func_78790_a(-3.0f, -3.0f, -2.5f, 6, 6, 5, 0.0f);
        this.Body7 = new ModelRenderer((ModelBase)this, 1, 33);
        this.Body7.func_78793_a(1.5f, 2.9f, 2.7f);
        this.Body7.func_78790_a(-2.0f, -2.5f, -2.7f, 4, 5, 5, 0.0f);
        this.BodyL = new ModelRenderer((ModelBase)this, 36, 22);
        this.BodyL.func_78793_a(3.6f, 0.7f, 0.1f);
        this.BodyL.func_78790_a(-2.0f, -2.5f, -2.7f, 4, 5, 5, 0.0f);
        this.Body4 = new ModelRenderer((ModelBase)this, 26, 12);
        this.Body4.func_78793_a(-1.9f, -0.8f, 2.2f);
        this.Body4.func_78790_a(-2.5f, -3.0f, -2.0f, 5, 6, 4, 0.0f);
        this.Bodytail1 = new ModelRenderer((ModelBase)this, 26, 53);
        this.Bodytail1.func_78793_a(0.6f, 1.8f, 4.9f);
        this.Bodytail1.func_78790_a(-2.0f, -2.5f, -2.7f, 4, 5, 5, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 65, 1);
        this.Body.func_78793_a(0.0f, 3.8f, 0.0f);
        this.Body.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 25, 11);
        this.Body2.func_78793_a(2.1f, -1.9f, -2.3f);
        this.Body2.func_78790_a(-2.5f, -2.5f, -2.5f, 5, 5, 5, 0.0f);
        this.Body.func_78792_a(this.Body8);
        this.Body.func_78792_a(this.Body1);
        this.Body.func_78792_a(this.BodyR);
        this.Body.func_78792_a(this.BodyBot);
        this.Body.func_78792_a(this.Body5);
        this.Body.func_78792_a(this.Body6);
        this.Bodytail1.func_78792_a(this.Bodytail2);
        this.Body.func_78792_a(this.BodyTop);
        this.Body.func_78792_a(this.BodyFront);
        this.Bodytail2.func_78792_a(this.Bodytail3);
        this.Bodytail3.func_78792_a(this.Bodytail4);
        this.Body.func_78792_a(this.Body3);
        this.Body.func_78792_a(this.Body7);
        this.Body.func_78792_a(this.BodyL);
        this.Body.func_78792_a(this.Body4);
        this.Body.func_78792_a(this.Bodytail1);
        this.Body.func_78792_a(this.Body2);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Body.func_78785_a(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
        float ex = par7Entity.field_70173_aa;
        float sin = MathHelper.func_76126_a((float)(ex * 0.5f)) * 0.01f;
        float sin2 = MathHelper.func_76126_a((float)(ex * 1.0f)) * -0.01f;
        float sin3 = MathHelper.func_76126_a((float)(ex * 1.0f)) * 0.01f;
        ModelRenderer[] segments = new ModelRenderer[]{this.Body, this.Body1, this.Body2, this.Body3, this.Body4, this.Body5, this.Body6, this.Body7, this.Body8, this.BodyL, this.BodyTop, this.BodyBot, this.BodyFront, this.Bodytail1};
        for (int i = 0; i < segments.length; ++i) {
            segments[i].field_78795_f = sin * (float)(i / 2 == 0 ? 1 : -1);
            segments[i].field_78796_g = sin2 * (float)(i / 2 == 0 ? 1 : -1);
            segments[i].field_78808_h = sin3 * (float)(i / 2 == 0 ? 1 : -1);
        }
    }
}

