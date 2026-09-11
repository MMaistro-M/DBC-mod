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

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.entity.EntityNull;
import JinRyuu.JRMCore.entity.RenderJRMC;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderNull
extends RenderJRMC {
    private static final ResourceLocation texture = new ResourceLocation(JRMCoreH.tjjrmc + ":npcs/TrainingShadowDummyUniform.png");

    public RenderNull(ModelBase model, float f) {
        super(model, f);
    }

    public void doRender(EntityNull entity, double d, double d1, double d2, float d3, float f) {
        float f5 = 0.0625f;
        this.func_110776_a(this.func_110775_a((Entity)entity));
        GL11.glPushMatrix();
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)-30.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        this.field_77045_g.func_78088_a((Entity)entity, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f5);
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation func_110775_a(Entity entity) {
        return texture;
    }

    @Override
    public void func_76986_a(Entity entity, double d0, double d1, double d2, float f, float f1) {
        this.doRender((EntityNull)entity, d0, d1, d2, f, f1);
    }
}

