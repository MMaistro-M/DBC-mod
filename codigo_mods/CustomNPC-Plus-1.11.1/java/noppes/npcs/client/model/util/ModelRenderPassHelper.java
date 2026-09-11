/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 */
package noppes.npcs.client.model.util;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.NPCRendererHelper;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelRenderPassHelper
extends ModelBase {
    public RendererLivingEntity renderer;
    public EntityLivingBase entity;

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        ModelBase model = NPCRendererHelper.getPassModel(this.renderer);
        model.field_78091_s = this.field_78091_s;
        model.func_78088_a((Entity)this.entity, par2, par3, par4, par5, par6, par7);
    }

    public void func_78086_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
        ModelBase model = NPCRendererHelper.getPassModel(this.renderer);
        model.field_78091_s = this.field_78091_s;
        model.func_78086_a(this.entity, par2, par3, par4);
    }
}

