/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 */
package riskyken.armourersWorkshop.api.common.skin.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.entity.EntityLivingBase;
import riskyken.armourersWorkshop.api.client.render.entity.ISkinnableEntityRenderer;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;

public interface ISkinnableEntity {
    public Class<? extends EntityLivingBase> getEntityClass();

    @SideOnly(value=Side.CLIENT)
    public Class<? extends ISkinnableEntityRenderer> getRendererClass();

    public boolean canUseWandOfStyle();

    public boolean canUseSkinsOnEntity();

    public void getValidSkinTypes(ArrayList<ISkinType> var1);
}

