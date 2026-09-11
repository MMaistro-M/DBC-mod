/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render.item;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.render.ItemStackRenderHelper;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.client.render.item.RenderItemEquipmentSkin;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.addons.ModAddonManager;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.utils.EventState;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class RenderItemSwordSkin
extends RenderItemEquipmentSkin {
    private final IItemRenderer itemRenderer;

    public RenderItemSwordSkin(IItemRenderer itemRenderer) {
        this.itemRenderer = itemRenderer;
    }

    @Override
    public boolean handleRenderType(ItemStack stack, IItemRenderer.ItemRenderType type) {
        if (this.canRenderModel(stack)) {
            if (type == IItemRenderer.ItemRenderType.INVENTORY) {
                if (this.itemRenderer != null) {
                    return this.itemRenderer.handleRenderType(stack, type);
                }
                return false;
            }
            return true;
        }
        if (this.itemRenderer != null) {
            return this.itemRenderer.handleRenderType(stack, type);
        }
        return false;
    }

    @Override
    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack stack, IItemRenderer.ItemRendererHelper helper) {
        if (this.canRenderModel(stack)) {
            if (type == IItemRenderer.ItemRenderType.INVENTORY) {
                if (this.itemRenderer != null) {
                    return this.itemRenderer.shouldUseRenderHelper(type, stack, helper);
                }
                return false;
            }
            return type == IItemRenderer.ItemRenderType.ENTITY;
        }
        if (this.itemRenderer != null) {
            return this.itemRenderer.shouldUseRenderHelper(type, stack, helper);
        }
        return false;
    }

    @Override
    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object ... data) {
        if (this.canRenderModel(stack) & type != IItemRenderer.ItemRenderType.INVENTORY) {
            if (type != IItemRenderer.ItemRenderType.ENTITY) {
                GL11.glPopMatrix();
                GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)-10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            GL11.glPushMatrix();
            GL11.glScalef((float)1.0f, (float)-1.0f, (float)1.0f);
            GL11.glScalef((float)1.6f, (float)1.6f, (float)1.6f);
            boolean isBlocking = false;
            if (data.length >= 2 && data[1] instanceof AbstractClientPlayer & data[0] instanceof RenderBlocks) {
                RenderBlocks renderBlocks = (RenderBlocks)data[0];
                AbstractClientPlayer player = (AbstractClientPlayer)data[1];
                isBlocking = player.func_70632_aY();
            }
            float scale = 0.0625f;
            switch (type) {
                case EQUIPPED: {
                    GL11.glTranslatef((float)(2.0f * scale), (float)(-1.0f * scale), (float)(0.0f * scale));
                    if (isBlocking) {
                        GL11.glTranslatef((float)(-0.0f * scale), (float)(2.0f * scale), (float)(1.0f * scale));
                    }
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break;
                }
                case ENTITY: {
                    GL11.glScalef((float)-1.0f, (float)1.0f, (float)1.0f);
                    GL11.glTranslatef((float)0.0f, (float)(-10.0f * scale), (float)0.0f);
                    break;
                }
                case EQUIPPED_FIRST_PERSON: {
                    GL11.glScalef((float)-1.0f, (float)1.0f, (float)1.0f);
                    GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break;
                }
            }
            GL11.glPushAttrib((int)8192);
            GL11.glEnable((int)2884);
            ModRenderHelper.enableAlphaBlend();
            ModAddonManager.onWeaponRender(type, EventState.PRE);
            SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stack);
            Skin skin = ClientSkinCache.INSTANCE.getSkin(skinPointer);
            if (skin != null) {
                ItemStackRenderHelper.renderSkinWithHelper(skin, skinPointer, false, false);
            }
            ModAddonManager.onWeaponRender(type, EventState.POST);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
            if (type != IItemRenderer.ItemRenderType.ENTITY) {
                GL11.glPushMatrix();
            }
        } else if (this.itemRenderer != null) {
            this.itemRenderer.renderItem(type, stack, data);
        } else {
            this.renderNomalIcon(stack);
        }
    }

    private boolean canRenderModel(ItemStack stack) {
        if (SkinNBTHelper.stackHasSkinData(stack)) {
            SkinPointer skinData = SkinNBTHelper.getSkinPointerFromStack(stack);
            if (ClientSkinCache.INSTANCE.isSkinInCache(skinData)) {
                return true;
            }
            ClientSkinCache.INSTANCE.requestSkinFromServer(skinData);
            return false;
        }
        return false;
    }

    private void renderNomalIcon(ItemStack stack) {
        IIcon icon = stack.func_77973_b().getIcon(stack, 0);
        this.renderItem.func_94149_a(0, 0, icon, icon.func_94211_a(), icon.func_94216_b());
        icon = stack.func_77973_b().getIcon(stack, 1);
        this.renderItem.func_94149_a(0, 0, icon, icon.func_94211_a(), icon.func_94216_b());
    }
}

