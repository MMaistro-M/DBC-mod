/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.api.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.api.common.skin.data.ISkin;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;

public interface ISkinRenderHandler {
    public boolean renderSkinWithHelper(ItemStack var1);

    public boolean renderSkinWithHelper(ItemStack var1, ModelBiped var2);

    public boolean renderSkinWithHelper(ItemStack var1, float var2, float var3, float var4, float var5, float var6);

    public boolean renderSkinWithHelper(ISkinPointer var1);

    public boolean renderSkinWithHelper(ISkinPointer var1, ModelBiped var2);

    public boolean renderSkinWithHelper(ISkinPointer var1, float var2, float var3, float var4, float var5, float var6);

    public boolean renderSkin(ItemStack var1);

    public boolean renderSkin(ISkinPointer var1);

    public boolean renderSkinPart(ISkinPointer var1, ISkinPartType var2);

    public boolean isSkinInModelCache(ItemStack var1);

    public boolean isSkinInModelCache(ISkinPointer var1);

    public void requestSkinModelFromSever(ItemStack var1);

    public void requestSkinModelFromSever(ISkinPointer var1);

    public ModelBase getArmourerHandModel();

    public ISkin getSkinFromModelCache(ISkinPointer var1);

    public boolean isArmourRenderOverridden(EntityPlayer var1, int var2);
}

