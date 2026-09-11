/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityHanging
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.geckolib3.renderers.geo;

import com.eliotlash.mclib.utils.Interpolations;
import com.google.common.collect.Lists;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
import net.geckominecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimatableModel;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.DummyVanilaModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public abstract class GeoReplacedEntityRenderer<T extends IAnimatable>
extends RendererLivingEntity
implements IGeoRenderer {
    private final AnimatedGeoModel<T> modelProvider;
    private final T animatable;
    protected final List<GeoLayerRenderer> layerRenderers = Lists.newArrayList();
    private IAnimatable currentAnimatable;
    private static Map<Class<? extends IAnimatable>, GeoReplacedEntityRenderer> renderers = new ConcurrentHashMap<Class<? extends IAnimatable>, GeoReplacedEntityRenderer>();

    public GeoReplacedEntityRenderer(AnimatedGeoModel<T> modelProvider, T animatable) {
        super((ModelBase)new DummyVanilaModel(), 0.0f);
        this.field_76990_c = RenderManager.field_78727_a;
        this.modelProvider = modelProvider;
        this.animatable = animatable;
    }

    public static void registerReplacedEntity(Class<? extends IAnimatable> itemClass, GeoReplacedEntityRenderer renderer) {
        renderers.put(itemClass, renderer);
    }

    public static GeoReplacedEntityRenderer getRenderer(Class<? extends IAnimatable> item) {
        return renderers.get(item);
    }

    public void func_76986_a(Entity entityObj, double x, double y, double z, float entityYaw, float partialTicks) {
        Entity leashHolder;
        if (!(entityObj instanceof EntityLivingBase)) {
            return;
        }
        EntityLivingBase entity = (EntityLivingBase)entityObj;
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        boolean shouldSit = entity.field_70154_o != null && entity.field_70154_o.shouldRiderSit();
        EntityModelData entityModelData = new EntityModelData();
        entityModelData.isSitting = shouldSit;
        entityModelData.isChild = entity.func_70631_g_();
        float f = Interpolations.lerpYaw(entity.field_70760_ar, entity.field_70761_aq, partialTicks);
        float f1 = Interpolations.lerpYaw(entity.field_70758_at, entity.field_70759_as, partialTicks);
        float netHeadYaw = f1 - f;
        if (shouldSit && entity.field_70154_o instanceof EntityLivingBase) {
            EntityLivingBase livingentity = (EntityLivingBase)entity.field_70154_o;
            f = Interpolations.lerpYaw(livingentity.field_70760_ar, livingentity.field_70761_aq, partialTicks);
            netHeadYaw = f1 - f;
            float f3 = com.eliotlash.mclib.utils.MathHelper.wrapDegrees(netHeadYaw);
            if (f3 < -85.0f) {
                f3 = -85.0f;
            }
            if (f3 >= 85.0f) {
                f3 = 85.0f;
            }
            f = f1 - f3;
            if (f3 * f3 > 2500.0f) {
                f += f3 * 0.2f;
            }
            netHeadYaw = f1 - f;
        }
        float headPitch = Interpolations.lerp(entity.field_70127_C, entity.field_70125_A, partialTicks);
        float f7 = this.func_77044_a(entity, partialTicks);
        this.applyRotations(entity, f7, f, partialTicks);
        float limbSwingAmount = 0.0f;
        float limbSwing = 0.0f;
        if (!shouldSit && entity.func_70089_S()) {
            limbSwingAmount = Interpolations.lerp(entity.field_70722_aY, entity.field_70721_aZ, partialTicks);
            limbSwing = entity.field_70754_ba - entity.field_70721_aZ * (1.0f - partialTicks);
            if (entity.func_70631_g_()) {
                limbSwing *= 3.0f;
            }
            if (limbSwingAmount > 1.0f) {
                limbSwingAmount = 1.0f;
            }
        }
        entityModelData.headPitch = -headPitch;
        entityModelData.netHeadYaw = -netHeadYaw;
        AnimationEvent<T> predicate = new AnimationEvent<T>(this.animatable, limbSwing, limbSwingAmount, partialTicks, !(limbSwingAmount > -0.15f) || !(limbSwingAmount < 0.15f), Collections.singletonList(entityModelData));
        GeoModel model = this.modelProvider.getModel(this.modelProvider.getModelLocation(this.animatable));
        if (this.modelProvider instanceof IAnimatableModel) {
            this.modelProvider.setLivingAnimations(this.animatable, this.getUniqueID(entity), (AnimationEvent)predicate);
        }
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0f, 0.01f, 0.0f);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(this.func_110775_a((Entity)entity));
        Color renderColor = this.getRenderColor(entity, partialTicks);
        if (!entity.func_98034_c((EntityPlayer)Minecraft.func_71410_x().field_71439_g)) {
            this.render(model, entity, partialTicks, (float)renderColor.getRed() / 255.0f, (float)renderColor.getGreen() / 255.0f, (float)renderColor.getBlue() / 255.0f, (float)renderColor.getAlpha() / 255.0f);
        }
        if (entity instanceof EntityPlayer) {
            for (GeoLayerRenderer layerRenderer : this.layerRenderers) {
                layerRenderer.doRenderLayer(entity, limbSwing, limbSwingAmount, partialTicks, f7, netHeadYaw, headPitch, 0.0625f);
            }
        }
        if (entity instanceof EntityLiving && (leashHolder = ((EntityLiving)entity).func_110166_bE()) != null) {
            this.renderLeash((EntityLiving)entity, x, y, z, entityYaw, partialTicks);
        }
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
    }

    protected void func_77041_b(EntityLivingBase entitylivingbaseIn, float partialTickTime) {
    }

    @Nullable
    protected ResourceLocation func_110775_a(Entity entity) {
        return this.getTextureLocation(this.currentAnimatable);
    }

    @Override
    public AnimatedGeoModel getGeoModelProvider() {
        return this.modelProvider;
    }

    protected void applyRotations(EntityLivingBase entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        String s;
        if (!entityLiving.func_70608_bn()) {
            GlStateManager.rotate(180.0f - rotationYaw, 0.0f, 1.0f, 0.0f);
        }
        if (entityLiving.field_70725_aQ > 0) {
            float f = ((float)entityLiving.field_70725_aQ + partialTicks - 1.0f) / 20.0f * 1.6f;
            if ((f = MathHelper.func_76129_c((float)f)) > 1.0f) {
                f = 1.0f;
            }
            GlStateManager.rotate(f * this.func_77037_a(entityLiving), 0.0f, 0.0f, 1.0f);
        } else if ((entityLiving instanceof EntityLiving && ((EntityLiving)entityLiving).func_94056_bM() || entityLiving instanceof EntityPlayer) && ("Dinnerbone".equals(s = ChatFormatting.stripFormatting((String)entityLiving.func_70005_c_())) || "Grumm".equals(s)) && !(entityLiving instanceof EntityPlayer)) {
            GlStateManager.translate(0.0, (double)(entityLiving.field_70131_O + 0.1f), 0.0);
            GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);
        }
    }

    protected boolean isVisible(EntityLivingBase livingEntityIn) {
        return !livingEntityIn.func_82150_aj();
    }

    private static float getFacingAngle(EnumFacing facingIn) {
        switch (facingIn) {
            case SOUTH: {
                return 90.0f;
            }
            case WEST: {
                return 0.0f;
            }
            case NORTH: {
                return 270.0f;
            }
            case EAST: {
                return 180.0f;
            }
        }
        return 0.0f;
    }

    protected float func_77037_a(EntityLivingBase entityLivingBaseIn) {
        return 90.0f;
    }

    protected float getSwingProgress(EntityLivingBase livingBase, float partialTickTime) {
        return livingBase.func_70678_g(partialTickTime);
    }

    protected float func_77044_a(EntityLivingBase livingBase, float partialTicks) {
        return (float)livingBase.field_70173_aa + partialTicks;
    }

    public final boolean addLayer(GeoLayerRenderer<? extends EntityLivingBase> layer) {
        return this.layerRenderers.add(layer);
    }

    public ResourceLocation getTextureLocation(Object instance) {
        return this.modelProvider.getTextureLocation(this.animatable);
    }

    protected void renderLeash(EntityLiving entityLivingIn, double x, double y, double z, float entityYaw, float partialTicks) {
        Entity entity = entityLivingIn.func_110166_bE();
        if (entity != null) {
            y -= (1.6 - (double)entityLivingIn.field_70131_O) * 0.5;
            Tessellator tessellator = Tessellator.field_78398_a;
            double d0 = this.interpolateValue(entity.field_70126_B, entity.field_70177_z, partialTicks * 0.5f) * 0.01745329238474369;
            double d1 = this.interpolateValue(entity.field_70127_C, entity.field_70125_A, partialTicks * 0.5f) * 0.01745329238474369;
            double d2 = Math.cos(d0);
            double d3 = Math.sin(d0);
            double d4 = Math.sin(d1);
            if (entity instanceof EntityHanging) {
                d2 = 0.0;
                d3 = 0.0;
                d4 = -1.0;
            }
            double d5 = Math.cos(d1);
            double d6 = this.interpolateValue(entity.field_70169_q, entity.field_70165_t, partialTicks) - d2 * 0.7 - d3 * 0.5 * d5;
            double d7 = this.interpolateValue(entity.field_70167_r + (double)entity.func_70047_e() * 0.7, entity.field_70163_u + (double)entity.func_70047_e() * 0.7, partialTicks) - d4 * 0.5 - 0.25;
            double d8 = this.interpolateValue(entity.field_70166_s, entity.field_70161_v, partialTicks) - d3 * 0.7 + d2 * 0.5 * d5;
            double d9 = this.interpolateValue(entityLivingIn.field_70760_ar, entityLivingIn.field_70761_aq, partialTicks) * 0.01745329238474369 + 1.5707963267948966;
            d2 = Math.cos(d9) * (double)entityLivingIn.field_70130_N * 0.4;
            d3 = Math.sin(d9) * (double)entityLivingIn.field_70130_N * 0.4;
            double d10 = this.interpolateValue(entityLivingIn.field_70169_q, entityLivingIn.field_70165_t, partialTicks) + d2;
            double d11 = this.interpolateValue(entityLivingIn.field_70167_r, entityLivingIn.field_70163_u, partialTicks);
            double d12 = this.interpolateValue(entityLivingIn.field_70166_s, entityLivingIn.field_70161_v, partialTicks) + d3;
            x += d2;
            z += d3;
            double d13 = (float)(d6 - d10);
            double d14 = (float)(d7 - d11);
            double d15 = (float)(d8 - d12);
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableCull();
            tessellator.func_78371_b(5);
            for (int j = 0; j <= 24; ++j) {
                float f = 0.5f;
                float f1 = 0.4f;
                float f2 = 0.3f;
                if (j % 2 == 0) {
                    f *= 0.7f;
                    f1 *= 0.7f;
                    f2 *= 0.7f;
                }
                float f3 = (float)j / 24.0f;
                tessellator.func_78369_a(f, f1, f2, 1.0f);
                tessellator.func_78377_a(x + d13 * (double)f3 + 0.0, y + d14 * (double)(f3 * f3 + f3) * 0.5 + (double)((24.0f - (float)j) / 18.0f + 0.125f), z + d15 * (double)f3);
                tessellator.func_78369_a(f, f1, f2, 1.0f);
                tessellator.func_78377_a(x + d13 * (double)f3 + 0.025, y + d14 * (double)(f3 * f3 + f3) * 0.5 + (double)((24.0f - (float)j) / 18.0f + 0.125f) + 0.025, z + d15 * (double)f3);
            }
            tessellator.func_78381_a();
            tessellator.func_78371_b(5);
            for (int k = 0; k <= 24; ++k) {
                float f4 = 0.5f;
                float f5 = 0.4f;
                float f6 = 0.3f;
                if (k % 2 == 0) {
                    f4 *= 0.7f;
                    f5 *= 0.7f;
                    f6 *= 0.7f;
                }
                float f7 = (float)k / 24.0f;
                tessellator.func_78369_a(f4, f5, f6, 1.0f);
                tessellator.func_78377_a(x + d13 * (double)f7 + 0.0, y + d14 * (double)(f7 * f7 + f7) * 0.5 + (double)((24.0f - (float)k) / 18.0f + 0.125f) + 0.025, z + d15 * (double)f7);
                tessellator.func_78369_a(f4, f5, f6, 1.0f);
                tessellator.func_78377_a(x + d13 * (double)f7 + 0.025, y + d14 * (double)(f7 * f7 + f7) * 0.5 + (double)((24.0f - (float)k) / 18.0f + 0.125f), z + d15 * (double)f7 + 0.025);
            }
            tessellator.func_78381_a();
            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
            GlStateManager.enableCull();
        }
    }

    private double interpolateValue(double start, double end, double pct) {
        return start + (end - start) * pct;
    }

    static {
        AnimationController.addModelFetcher(object -> {
            GeoReplacedEntityRenderer renderer = renderers.get(object.getClass());
            return renderer == null ? null : renderer.getGeoModelProvider();
        });
    }
}

