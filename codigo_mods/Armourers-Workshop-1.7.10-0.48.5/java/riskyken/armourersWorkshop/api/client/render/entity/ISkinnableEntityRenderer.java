/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.entity.EntityLivingBase
 */
package riskyken.armourersWorkshop.api.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import riskyken.armourersWorkshop.api.common.skin.IEntityEquipment;

@SideOnly(value=Side.CLIENT)
public interface ISkinnableEntityRenderer<ENTITY extends EntityLivingBase> {
    public void render(ENTITY var1, RendererLivingEntity var2, double var3, double var5, double var7, IEntityEquipment var9);
}

