/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.entity.EntityAuraFlat;
import JinRyuu.JRMCore.entity.RenderJRMC;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderAuraFlat
extends RenderJRMC {
    public RenderAuraFlat(ModelBase model, float f) {
        super(model, f);
    }

    public void doRender(EntityAuraFlat entity, double d, double d1, double d2, float d3, float f) {
        float f5 = 0.0625f;
        this.func_110776_a(this.func_110775_a((Entity)entity));
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        entity.update();
        float scale = 0.5f;
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glRotatef((float)-30.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)-5.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)((float)entity.aura.getRed() / 255.0f), (float)((float)entity.aura.getGreen() / 255.0f), (float)((float)entity.aura.getBlue() / 255.0f), (float)entity.transparency.lastValue);
        GL11.glTranslatef((float)((float)entity.field_70165_t), (float)((float)entity.field_70163_u), (float)((float)entity.field_70161_v));
        GL11.glRotatef((float)((int)entity.field_70165_t * 30), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((int)entity.field_70161_v * 30), (float)1.0f, (float)0.0f, (float)0.0f);
        this.field_77045_g.func_78088_a((Entity)entity, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f5);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation func_110775_a(Entity entity) {
        return ((EntityAuraFlat)entity).getTexture();
    }

    @Override
    public void func_76986_a(Entity entity, double d0, double d1, double d2, float f, float f1) {
        this.doRender((EntityAuraFlat)entity, d0, d1, d2, f, f1);
    }
}

