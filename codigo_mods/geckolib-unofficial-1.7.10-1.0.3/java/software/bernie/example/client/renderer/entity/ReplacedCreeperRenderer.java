/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.util.MathHelper
 */
package software.bernie.example.client.renderer.entity;

import net.geckominecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.MathHelper;
import software.bernie.example.client.model.entity.ReplacedCreeperModel;
import software.bernie.example.entity.ReplacedCreeperEntity;
import software.bernie.geckolib3.renderers.geo.GeoReplacedEntityRenderer;

public class ReplacedCreeperRenderer
extends GeoReplacedEntityRenderer<ReplacedCreeperEntity> {
    public ReplacedCreeperRenderer() {
        super(new ReplacedCreeperModel(), new ReplacedCreeperEntity());
    }

    @Override
    protected void func_77041_b(EntityLivingBase entitylivingbaseIn, float partialTickTime) {
        EntityCreeper creeper = (EntityCreeper)entitylivingbaseIn;
        float f = creeper.func_70831_j(partialTickTime);
        float f1 = 1.0f + MathHelper.func_76126_a((float)(f * 100.0f)) * f * 0.01f;
        f = MathHelper.func_76131_a((float)f, (float)0.0f, (float)1.0f);
        f *= f;
        f *= f;
        float f2 = (1.0f + f * 0.4f) * f1;
        float f3 = (1.0f + f * 0.1f) / f1;
        GlStateManager.scale(f2, f3, f2);
    }
}

