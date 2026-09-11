/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.IPoint3D;
import riskyken.armourersWorkshop.api.common.IRectangle3D;
import riskyken.armourersWorkshop.api.common.skin.Rectangle3D;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.model.skin.AbstractModelSkin;
import riskyken.armourersWorkshop.client.render.SkinModelRenderer;
import riskyken.armourersWorkshop.client.render.SkinPartRenderData;
import riskyken.armourersWorkshop.client.render.SkinPartRenderer;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

@SideOnly(value=Side.CLIENT)
public final class ItemStackRenderHelper {
    @Deprecated
    public static void renderItemAsArmourModel(ItemStack stack, boolean showSkinPaint) {
        if (SkinNBTHelper.stackHasSkinData(stack)) {
            SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stack);
            ItemStackRenderHelper.renderItemModelFromSkinPointer(skinPointer, showSkinPaint, false);
        }
    }

    @Deprecated
    public static void renderItemModelFromSkinPointer(ISkinPointer skinPointer, boolean showSkinPaint, boolean doLodLoading) {
        ItemStackRenderHelper.renderItemModelFromSkin(ClientSkinCache.INSTANCE.getSkin(skinPointer), skinPointer, showSkinPaint, doLodLoading);
    }

    @Deprecated
    public static void renderItemModelFromSkin(Skin skin, ISkinPointer skinPointer, boolean showSkinPaint, boolean doLodLoading) {
        if (skin == null) {
            return;
        }
        float blockScale = 16.0f;
        float mcScale = 1.0f / blockScale;
        float scale = 1.0f;
        float offsetX = 0.0f;
        float offsetY = 0.0f;
        float offsetZ = 0.0f;
        float scaleX = 1.0f;
        float scaleY = 1.0f;
        float scaleZ = 1.0f;
        int width = 1;
        int height = 1;
        int depth = 1;
        Rectangle3D sb = skin.getSkinBounds();
        width = Math.max(width, sb.getWidth());
        height = Math.max(height, sb.getHeight());
        depth = Math.max(depth, sb.getDepth());
        scaleX = Math.min(scaleX, 1.0f / (float)width);
        scaleY = Math.min(scaleY, 1.0f / (float)height);
        scaleZ = Math.min(scaleZ, 1.0f / (float)depth);
        scale = Math.min(scale, scaleX);
        scale = Math.min(scale, scaleY);
        scale = Math.min(scale, scaleZ);
        offsetX = (float)(-sb.getX()) - (float)width / 2.0f;
        offsetY = (float)(-sb.getY()) - (float)height / 2.0f;
        offsetZ = (float)(-sb.getZ()) - (float)depth / 2.0f;
        GL11.glPushMatrix();
        GL11.glScalef((float)(scale * blockScale), (float)(scale * blockScale), (float)(scale * blockScale));
        GL11.glTranslatef((float)(offsetX * mcScale), (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)(offsetY * mcScale), (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(offsetZ * mcScale));
        if (skin.getSkinType() == SkinTypeRegistry.skinWings) {
            GL11.glTranslated((double)(-offsetX * mcScale), (double)0.0, (double)0.0);
        }
        ItemStackRenderHelper.renderSkinWithHelper(skin, skinPointer, showSkinPaint, doLodLoading);
        GL11.glPopMatrix();
    }

    public static void renderSkinWithHelper(Skin skin, ISkinPointer skinPointer, boolean showSkinPaint, boolean doLodLoading) {
        AbstractModelSkin targetModel;
        ISkinType skinType = skinPointer.getIdentifier().getSkinType();
        if (skinType == null) {
            skinType = skin.getSkinType();
        }
        if ((targetModel = SkinModelRenderer.INSTANCE.getModelForEquipmentType(skinType)) == null) {
            ItemStackRenderHelper.renderSkinWithoutHelper(skinPointer, doLodLoading);
            return;
        }
        targetModel.render(null, null, skin, showSkinPaint, skinPointer.getSkinDye(), null, true, 0.0, doLodLoading);
    }

    public static void renderSkinWithoutHelper(ISkinPointer skinPointer, boolean doLodLoading) {
        Skin skin = ClientSkinCache.INSTANCE.getSkin(skinPointer);
        if (skin == null) {
            return;
        }
        float scale = 0.0625f;
        for (int i = 0; i < skin.getParts().size(); ++i) {
            GL11.glPushMatrix();
            SkinPart skinPart = skin.getParts().get(i);
            IPoint3D offset = skinPart.getPartType().getOffset();
            GL11.glTranslated((double)((float)offset.getX() * scale), (double)((float)(offset.getY() + 1) * scale), (double)((float)offset.getZ() * scale));
            SkinPartRenderData renderData = new SkinPartRenderData(skinPart, 0.0625f, skinPointer.getSkinDye(), null, 0.0, true, true, true, null);
            SkinPartRenderer.INSTANCE.renderPart(renderData);
            GL11.glPopMatrix();
        }
    }

    public static void drawBounds(IRectangle3D rec, int r, int g, int b) {
        float scale = 0.0625f;
        AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)((float)rec.getX() * scale), (double)((float)rec.getY() * scale), (double)((float)rec.getZ() * scale), (double)((float)(rec.getX() + rec.getWidth()) * scale), (double)((float)(rec.getY() + rec.getHeight()) * scale), (double)((float)(rec.getZ() + rec.getDepth()) * scale));
        GL11.glEnable((int)3042);
        GL11.glDisable((int)2896);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glLineWidth((float)1.0f);
        GL11.glDisable((int)3553);
        GL11.glColor4f((float)((float)r / 255.0f), (float)((float)g / 255.0f), (float)((float)b / 255.0f), (float)1.0f);
        RenderGlobal.func_147590_a((AxisAlignedBB)aabb, (int)0);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2896);
        GL11.glDisable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }
}

