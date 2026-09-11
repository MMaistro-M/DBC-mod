/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.geckolib3.renderers.geo;

import java.util.Collections;
import net.geckominecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatableModel;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import software.bernie.geckolib3.util.AnimationUtils;

public class GeoProjectilesRenderer<T extends Entity>
extends Render
implements IGeoRenderer<T> {
    private final AnimatedGeoModel<T> modelProvider;

    public GeoProjectilesRenderer(RenderManager renderManager, AnimatedGeoModel<T> modelProvider) {
        this.modelProvider = modelProvider;
    }

    public void func_76986_a(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GeoModel model = this.modelProvider.getModel(this.modelProvider.getModelLocation(entity));
        GlStateManager.rotate(entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * partialTicks - 90.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * partialTicks, 0.0f, 0.0f, 1.0f);
        float lastLimbDistance = 0.0f;
        float limbSwing = 0.0f;
        EntityModelData entityModelData = new EntityModelData();
        AnimationEvent<Entity> predicate = new AnimationEvent<Entity>(entity, limbSwing, lastLimbDistance, partialTicks, !(lastLimbDistance > -0.15f) || !(lastLimbDistance < 0.15f), Collections.singletonList(entityModelData));
        if (this.modelProvider instanceof IAnimatableModel) {
            this.modelProvider.setLivingAnimations(entity, this.getUniqueID(entity), (AnimationEvent)predicate);
        }
        GlStateManager.pushMatrix();
        Minecraft.func_71410_x().field_71446_o.func_110577_a(this.getTextureLocation(entity));
        Color renderColor = this.getRenderColor(entity, partialTicks);
        if (!entity.func_98034_c((EntityPlayer)Minecraft.func_71410_x().field_71439_g)) {
            this.render(model, entity, partialTicks, (float)renderColor.getRed() / 255.0f, (float)renderColor.getGreen() / 255.0f, (float)renderColor.getBlue() / 255.0f, (float)renderColor.getAlpha() / 255.0f);
        }
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
    }

    @Override
    public GeoModelProvider<T> getGeoModelProvider() {
        return this.modelProvider;
    }

    public ResourceLocation func_110775_a(Entity instance) {
        return this.getTextureLocation(instance);
    }

    @Override
    public Integer getUniqueID(Entity animatable) {
        return animatable.func_110124_au().hashCode();
    }

    @Override
    public ResourceLocation getTextureLocation(Entity instance) {
        return this.modelProvider.getTextureLocation(instance);
    }

    static {
        AnimationController.addModelFetcher(object -> {
            if (object instanceof Entity) {
                return (IAnimatableModel)((Object)AnimationUtils.getGeoModelForEntity((Entity)object));
            }
            return null;
        });
    }
}

