/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common.Render;

import JinRyuu.DragonBC.common.Render.SpacePod01Entity;
import JinRyuu.DragonBC.common.Render.SpacePod01Model;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class SpacePod01RenderPod
extends Render {
    private ModelBase aModel = new SpacePod01Model();
    private static final ResourceLocation shearedSheepTextures = new ResourceLocation("jinryuudragonbc:SpacePodBlock.png");

    public SpacePod01RenderPod() {
        this.field_76989_e = 0.5f;
    }

    public void renderAModelAt(SpacePod01Entity entity, double d, double d1, double d2, float f, float par9) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d1 + 1.0f), (float)((float)d2));
        GL11.glRotatef((float)(180.0f - f), (float)0.0f, (float)1.0f, (float)0.0f);
        ResourceLocation tx = new ResourceLocation("jinryuudragonbc:SpacePodBlock.png");
        this.field_76990_c.field_78724_e.func_110577_a(tx);
        GL11.glEnable((int)2977);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        this.aModel.func_78088_a((Entity)entity, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderAModelAt((SpacePod01Entity)par1Entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return shearedSheepTextures;
    }
}

