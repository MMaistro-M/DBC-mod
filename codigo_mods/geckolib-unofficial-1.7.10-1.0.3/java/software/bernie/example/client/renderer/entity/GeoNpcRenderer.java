/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  org.lwjgl.opengl.GL11
 */
package software.bernie.example.client.renderer.entity;

import java.util.ArrayList;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import software.bernie.example.client.model.entity.GeoNpcModel;
import software.bernie.example.entity.GeoNpcEntity;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GeoNpcRenderer
extends GeoEntityRenderer<GeoNpcEntity> {
    public GeoNpcRenderer() {
        super(new GeoNpcModel());
    }

    @Override
    public void renderAfter(GeoModel model, GeoNpcEntity animatable, float ticks, float red, float green, float blue, float alpha) {
        super.renderAfter(model, (Object)animatable, ticks, red, green, blue, alpha);
        if (model.getBone("held_item").isPresent() && animatable.func_70694_bm() != null) {
            GeoBone bone = model.getBone("held_item").get();
            this.renderItem(animatable, bone, ticks);
        }
    }

    @Override
    public GeoBone[] getPathFromRoot(GeoBone bone) {
        ArrayList<GeoBone> bones = new ArrayList<GeoBone>();
        while (bone != null) {
            bones.add(0, bone);
            bone = bone.parent;
        }
        return bones.toArray(new GeoBone[0]);
    }

    public void renderItem(GeoNpcEntity animatable, GeoBone locator, float ticks) {
        GL11.glPushMatrix();
        float scale = 0.5f;
        GL11.glScaled((double)scale, (double)scale, (double)scale);
        GeoBone[] bonePath = this.getPathFromRoot(locator);
        for (int i = 0; i < bonePath.length; ++i) {
            GeoBone b = bonePath[i];
            GL11.glTranslatef((float)(b.getPositionX() / (16.0f * scale)), (float)(b.getPositionY() / (16.0f * scale)), (float)(b.getPositionZ() / (16.0f * scale)));
            GL11.glTranslatef((float)(b.getPivotX() / (16.0f * scale)), (float)(b.getPivotY() / (16.0f * scale)), (float)(b.getPivotZ() / (16.0f * scale)));
            GL11.glRotated((double)((double)b.getRotationZ() / Math.PI * 180.0), (double)0.0, (double)0.0, (double)1.0);
            GL11.glRotated((double)((double)b.getRotationY() / Math.PI * 180.0), (double)0.0, (double)1.0, (double)0.0);
            GL11.glRotated((double)((double)b.getRotationX() / Math.PI * 180.0), (double)1.0, (double)0.0, (double)0.0);
            GL11.glScalef((float)b.getScaleX(), (float)b.getScaleY(), (float)b.getScaleZ());
            GL11.glTranslatef((float)(-b.getPivotX() / (16.0f * scale)), (float)(-b.getPivotY() / (16.0f * scale)), (float)(-b.getPivotZ() / (16.0f * scale)));
        }
        GL11.glRotatef((float)250.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)40.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.4f, (float)-0.55f, (float)1.7f);
        ItemStack stack = animatable.func_70694_bm();
        RenderManager.field_78727_a.field_78721_f.renderItem((EntityLivingBase)animatable, stack, 0, IItemRenderer.ItemRenderType.INVENTORY);
        GL11.glPopMatrix();
    }

    @Override
    public boolean isBoneRenderOverriden(GeoNpcEntity entity, GeoBone bone) {
        return bone.name.equals("held_item");
    }
}

