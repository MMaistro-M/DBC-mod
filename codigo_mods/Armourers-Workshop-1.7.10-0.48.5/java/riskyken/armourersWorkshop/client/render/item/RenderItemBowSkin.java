/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render.item;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.IEntityEquipment;
import riskyken.armourersWorkshop.client.model.armourer.ModelArrow;
import riskyken.armourersWorkshop.client.model.skin.ModelSkinBow;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.client.render.SkinModelRenderer;
import riskyken.armourersWorkshop.client.render.SkinPartRenderData;
import riskyken.armourersWorkshop.client.render.SkinPartRenderer;
import riskyken.armourersWorkshop.client.render.item.RenderItemEquipmentSkin;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.skin.cubes.CubeMarkerData;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class RenderItemBowSkin
extends RenderItemEquipmentSkin {
    private final IItemRenderer itemRenderer;

    public RenderItemBowSkin(IItemRenderer itemRenderer) {
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
            }
            GL11.glPushMatrix();
            AbstractClientPlayer player = null;
            int useCount = 0;
            boolean hasArrow = false;
            if (data.length >= 2 && data[1] instanceof AbstractClientPlayer & data[0] instanceof RenderBlocks) {
                RenderBlocks renderBlocks = (RenderBlocks)data[0];
                player = (AbstractClientPlayer)data[1];
                useCount = player.func_71057_bx();
                hasArrow = player.field_71071_by.func_146028_b(Items.field_151032_g);
                IEntityEquipment entityEquipment = SkinModelRenderer.INSTANCE.getPlayerCustomEquipmentData((Entity)player);
                if (!hasArrow && player.field_71075_bZ.field_75098_d) {
                    hasArrow = true;
                }
            }
            float scale = 0.0625f;
            float angle = (float)((double)System.currentTimeMillis() / 5.0 % 360.0);
            switch (type) {
                case EQUIPPED: {
                    GL11.glScalef((float)1.0f, (float)-1.0f, (float)1.0f);
                    GL11.glScalef((float)1.6f, (float)1.6f, (float)1.6f);
                    GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glRotatef((float)-20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glTranslatef((float)(0.0f * scale), (float)(-6.0f * scale), (float)(1.0f * scale));
                    break;
                }
                case ENTITY: {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)0.0f, (float)(-10.0f * scale), (float)0.0f);
                    break;
                }
                case EQUIPPED_FIRST_PERSON: {
                    GL11.glScalef((float)1.6f, (float)1.6f, (float)1.6f);
                    GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)-17.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glRotatef((float)2.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)(0.0f * scale), (float)(-2.0f * scale), (float)(1.0f * scale));
                    if (useCount <= 0) break;
                    GL11.glTranslatef((float)(-5.0f * scale), (float)(3.0f * scale), (float)(1.0f * scale));
                    GL11.glRotatef((float)-6.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glRotatef((float)-16.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)2.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    break;
                }
            }
            GL11.glPushAttrib((int)8192);
            GL11.glEnable((int)2884);
            ModRenderHelper.enableAlphaBlend();
            ModelSkinBow model = SkinModelRenderer.INSTANCE.customBow;
            model.frame = this.getAnimationFrame(useCount);
            SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stack);
            Skin skin = ClientSkinCache.INSTANCE.getSkin(skinPointer);
            model.render((Entity)player, skin, false, skinPointer.getSkinDye(), null, false, 0.0, false);
            if (hasArrow & useCount > 0) {
                GL11.glTranslatef((float)(1.0f * scale), (float)(1.0f * scale), (float)(-12.0f * scale));
                int tarPart = this.getAnimationFrame(useCount);
                if (skin.getParts().get(tarPart).getMarkerBlocks().size() > 0) {
                    CubeMarkerData cmd = skin.getParts().get(tarPart).getMarkerBlocks().get(0);
                    ForgeDirection dir = ForgeDirection.getOrientation((int)(cmd.meta - 1));
                    GL11.glTranslatef((float)((float)(-dir.offsetX + cmd.x) * scale), (float)((float)(-dir.offsetY + cmd.y) * scale), (float)((float)(dir.offsetZ + cmd.z) * scale));
                    GL11.glTranslatef((float)(-0.01f * scale), (float)(0.01f * scale), (float)(-0.01f * scale));
                    SkinPart skinPartArrow = skin.getPart("armourers:bow.arrow");
                    if (skinPartArrow != null) {
                        SkinPartRenderData renderData = new SkinPartRenderData(skinPartArrow, 0.0625f, skinPointer.getSkinDye(), null, 0.0, false, false, false, null);
                        SkinPartRenderer.INSTANCE.renderPart(renderData);
                    } else {
                        ModelArrow.MODEL.render(scale, false);
                    }
                }
            }
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

    private int getAnimationFrame(int useCount) {
        if (useCount >= 18) {
            return 2;
        }
        if (useCount > 13) {
            return 1;
        }
        return 0;
    }

    private boolean canRenderModel(ItemStack stack) {
        if (SkinNBTHelper.stackHasSkinData(stack)) {
            SkinPointer skinData = SkinNBTHelper.getSkinPointerFromStack(stack);
            if (ClientSkinCache.INSTANCE.isSkinInCache(skinData)) {
                Skin skin = ClientSkinCache.INSTANCE.getSkin(skinData);
                if (skin.getPartCount() > 2) {
                    return true;
                }
            } else {
                ClientSkinCache.INSTANCE.requestSkinFromServer(skinData);
            }
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

