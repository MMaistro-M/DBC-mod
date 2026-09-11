/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.geckolib3.renderers.geo;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderHurtColor
extends RendererLivingEntity {
    private static RenderHurtColor instance;

    public static RenderHurtColor getInstance() {
        if (instance == null) {
            instance = new RenderHurtColor(null, 0.0f);
        }
        return instance;
    }

    public static boolean set(EntityLivingBase entity, float partialTicks) {
        return true;
    }

    public static void unset() {
    }

    public RenderHurtColor(ModelBase modelBaseIn, float shadowSizeIn) {
        super(modelBaseIn, shadowSizeIn);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return null;
    }
}

