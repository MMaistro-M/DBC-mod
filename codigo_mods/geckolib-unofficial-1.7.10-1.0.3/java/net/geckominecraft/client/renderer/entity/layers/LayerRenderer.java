/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 */
package net.geckominecraft.client.renderer.entity.layers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;

@SideOnly(value=Side.CLIENT)
public interface LayerRenderer<E extends EntityLivingBase> {
    public void doRenderLayer(E var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8);

    public boolean shouldCombineTextures();
}

