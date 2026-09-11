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
import java.util.ArrayList;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.IPoint3D;
import riskyken.armourersWorkshop.api.common.IRectangle3D;
import riskyken.armourersWorkshop.api.common.skin.Rectangle3D;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartTypeTextured;
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
public final class SkinItemRenderHelper {
    public static boolean debugShowFullBounds;
    public static boolean debugShowPartBounds;
    public static boolean debugShowTextureBounds;
    public static boolean debugShowTargetBounds;
    public static boolean debugSpin;

    private SkinItemRenderHelper() {
    }

    public static void renderSkinAsItem(ItemStack stack, boolean showSkinPaint, int targetWidth, int targetHeight) {
        if (SkinNBTHelper.stackHasSkinData(stack)) {
            SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stack);
            SkinItemRenderHelper.renderSkinAsItem(skinPointer, showSkinPaint, false, targetWidth, targetHeight);
        }
    }

    public static void renderSkinAsItem(ISkinPointer skinPointer, boolean showSkinPaint, boolean doLodLoading, int targetWidth, int targetHeight) {
        SkinItemRenderHelper.renderSkinAsItem(ClientSkinCache.INSTANCE.getSkin(skinPointer), skinPointer, showSkinPaint, doLodLoading, targetWidth, targetHeight);
    }

    public static void renderSkinAsItem(Skin skin, ISkinPointer skinPointer, boolean showSkinPaint, boolean doLodLoading, int targetWidth, int targetHeight) {
        int i;
        IRectangle3D rec;
        int i2;
        if (skin == null) {
            return;
        }
        float blockScale = 16.0f;
        float mcScale = 1.0f / blockScale;
        ArrayList<Rectangle3D> boundsParts = new ArrayList<Rectangle3D>();
        ArrayList<IRectangle3D> boundsTextures = new ArrayList<IRectangle3D>();
        Object boundsTexture = null;
        for (int i3 = 0; i3 < skin.getPartCount(); ++i3) {
            SkinPart skinPart = skin.getParts().get(i3);
            if (skin.getSkinType() == SkinTypeRegistry.skinBow && i3 > 0) continue;
            Rectangle3D bounds = skinPart.getPartBounds();
            IPoint3D offset = skinPart.getPartType().getItemRenderOffset();
            Rectangle3D rec2 = new Rectangle3D(bounds.getX() + offset.getX(), bounds.getY() + offset.getY(), bounds.getZ() + offset.getZ(), bounds.getWidth(), bounds.getHeight(), bounds.getDepth());
            boundsParts.add(rec2);
        }
        if (skin.hasPaintData()) {
            ArrayList<ISkinPartType> parts = skin.getSkinType().getSkinParts();
            for (int i4 = 0; i4 < parts.size(); ++i4) {
                ISkinPartType part = parts.get(i4);
                if (!(part instanceof ISkinPartTypeTextured) || part.getItemRenderTextureBounds() == null) continue;
                boundsTextures.add(part.getItemRenderTextureBounds());
            }
        }
        int minX = 256;
        int minY = 256;
        int minZ = 256;
        int maxX = -256;
        int maxY = -256;
        int maxZ = -256;
        for (i2 = 0; i2 < boundsParts.size(); ++i2) {
            rec = (IRectangle3D)boundsParts.get(i2);
            minX = Math.min(minX, rec.getX());
            minY = Math.min(minY, rec.getY());
            minZ = Math.min(minZ, rec.getZ());
            maxX = Math.max(maxX, rec.getWidth() + rec.getX());
            maxY = Math.max(maxY, rec.getHeight() + rec.getY());
            maxZ = Math.max(maxZ, rec.getDepth() + rec.getZ());
        }
        for (i2 = 0; i2 < boundsTextures.size(); ++i2) {
            rec = (IRectangle3D)boundsTextures.get(i2);
            minX = Math.min(minX, rec.getX());
            minY = Math.min(minY, rec.getY());
            minZ = Math.min(minZ, rec.getZ());
            maxX = Math.max(maxX, rec.getWidth() + rec.getX());
            maxY = Math.max(maxY, rec.getHeight() + rec.getY());
            maxZ = Math.max(maxZ, rec.getDepth() + rec.getZ());
        }
        Rectangle3D maxBounds = new Rectangle3D(minX, minY, minZ, maxX - minX, maxY - minY, maxZ - minZ);
        GL11.glPushMatrix();
        if (debugSpin) {
            float angle = System.currentTimeMillis() / 10L % 360L;
            GL11.glRotatef((float)angle, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        if (debugShowTargetBounds) {
            SkinItemRenderHelper.drawBounds(new Rectangle3D(-targetWidth / 2, -targetHeight / 2, -targetWidth / 2, targetWidth, targetHeight, targetWidth), 0, 0, 255);
        }
        int biggerSize = Math.max(maxBounds.getWidth(), maxBounds.getHeight());
        biggerSize = Math.max(biggerSize, maxBounds.getDepth());
        float newScaleW = (float)targetWidth / (float)Math.max(maxBounds.getWidth(), maxBounds.getDepth());
        float newScaleH = (float)targetHeight / (float)maxBounds.getHeight();
        float newScale = Math.min(newScaleW, newScaleH);
        GL11.glScalef((float)newScale, (float)newScale, (float)newScale);
        GL11.glTranslated((double)(-((float)maxBounds.getWidth() / 2.0f + (float)maxBounds.getX()) * mcScale), (double)0.0, (double)0.0);
        GL11.glTranslated((double)0.0, (double)(-((float)maxBounds.getHeight() / 2.0f + (float)maxBounds.getY()) * mcScale), (double)0.0);
        GL11.glTranslated((double)0.0, (double)0.0, (double)(-((float)maxBounds.getDepth() / 2.0f + (float)maxBounds.getZ()) * mcScale));
        SkinItemRenderHelper.renderSkinWithHelper(skin, skinPointer, showSkinPaint, doLodLoading);
        if (debugShowFullBounds) {
            SkinItemRenderHelper.drawBounds(maxBounds, 255, 255, 0);
        }
        if (debugShowPartBounds) {
            for (i = 0; i < boundsParts.size(); ++i) {
                SkinItemRenderHelper.drawBounds((IRectangle3D)boundsParts.get(i), 255, 0, 0);
            }
        }
        if (debugShowTextureBounds) {
            for (i = 0; i < boundsTextures.size(); ++i) {
                SkinItemRenderHelper.drawBounds((IRectangle3D)boundsTextures.get(i), 0, 255, 0);
            }
        }
        GL11.glPopMatrix();
    }

    public static void drawBounds(IRectangle3D rec, int r, int g, int b) {
        float scale = 0.0625f;
        AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)((float)rec.getX() * scale), (double)((float)rec.getY() * scale), (double)((float)rec.getZ() * scale), (double)((float)(rec.getX() + rec.getWidth()) * scale), (double)((float)(rec.getY() + rec.getHeight()) * scale), (double)((float)(rec.getZ() + rec.getDepth()) * scale));
        GL11.glEnable((int)3042);
        GL11.glDisable((int)2896);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glColor4f((float)((float)r / 255.0f), (float)((float)g / 255.0f), (float)((float)b / 255.0f), (float)1.0f);
        GL11.glLineWidth((float)1.0f);
        GL11.glDisable((int)3553);
        RenderGlobal.func_147590_a((AxisAlignedBB)aabb, (int)-1);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2896);
        GL11.glDisable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static void renderSkinWithHelper(Skin skin, ISkinPointer skinPointer, boolean showSkinPaint, boolean doLodLoading) {
        AbstractModelSkin targetModel;
        ISkinType skinType = skinPointer.getIdentifier().getSkinType();
        if (skinType == null) {
            skinType = skin.getSkinType();
        }
        if ((targetModel = SkinModelRenderer.INSTANCE.getModelForEquipmentType(skinType)) == null) {
            SkinItemRenderHelper.renderSkinWithoutHelper(skinPointer, doLodLoading);
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
}

