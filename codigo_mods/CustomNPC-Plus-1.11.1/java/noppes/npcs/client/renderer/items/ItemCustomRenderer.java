/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.Entity$EnumEntitySize
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer.items;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import noppes.npcs.api.item.IItemCustomizable;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.renderer.AnimationHelper;
import noppes.npcs.client.renderer.ImageData;
import noppes.npcs.items.ItemCustomizable;
import noppes.npcs.scripted.NpcAPI;
import org.lwjgl.opengl.GL11;

public class ItemCustomRenderer
implements IItemRenderer {
    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    private static final ResourceLocation enchant = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    private final Random random = new Random();
    private int item3dRenderTicks = 1;

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return type != IItemRenderer.ItemRenderType.FIRST_PERSON_MAP;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return helper == IItemRenderer.ItemRendererHelper.ENTITY_ROTATION && Minecraft.func_71410_x().field_71474_y.field_74347_j || helper == IItemRenderer.ItemRendererHelper.ENTITY_BOBBING;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack itemStack, Object ... data) {
        IItemStack iItemStack = NpcAPI.Instance().getIItemStack(itemStack);
        if (!(iItemStack instanceof IItemCustomizable)) {
            return;
        }
        IItemCustomizable scriptCustomItem = (IItemCustomizable)iItemStack;
        ImageData imageData = ClientCacheHandler.getImageData(scriptCustomItem.getTexture());
        if (!imageData.imageLoaded()) {
            return;
        }
        if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glPushMatrix();
            this.renderInventoryCustomItem(scriptCustomItem);
            GL11.glPopMatrix();
            return;
        }
        if (type == IItemRenderer.ItemRenderType.ENTITY) {
            GL11.glPushMatrix();
            EntityItem entityItem = (EntityItem)data[1];
            if (!entityItem.field_70158_ak) {
                entityItem.field_70158_ak = true;
                float entityXZSize = (float)Math.sqrt(Math.pow(scriptCustomItem.getScaleX().floatValue(), 2.0) + Math.pow(scriptCustomItem.getScaleZ().floatValue(), 2.0));
                this.setEntitySize(entityItem, entityXZSize * 0.25f, scriptCustomItem.getScaleY().floatValue() * 0.25f);
                double XSize = entityItem.field_70121_D.field_72336_d - entityItem.field_70121_D.field_72340_a;
                double YSize = entityItem.field_70121_D.field_72337_e - entityItem.field_70121_D.field_72338_b;
                double ZSize = entityItem.field_70121_D.field_72334_f - entityItem.field_70121_D.field_72339_c;
                entityItem.field_70121_D.field_72336_d = entityItem.field_70121_D.field_72340_a + XSize * (double)scriptCustomItem.getScaleX().floatValue();
                entityItem.field_70121_D.field_72337_e = entityItem.field_70121_D.field_72338_b + YSize * (double)scriptCustomItem.getScaleY().floatValue();
                entityItem.field_70121_D.field_72334_f = entityItem.field_70121_D.field_72339_c + ZSize * (double)scriptCustomItem.getScaleZ().floatValue();
            }
            float entityRenderTicks = Minecraft.func_71410_x().field_71428_T.field_74281_c;
            float bobbing = MathHelper.func_76126_a((float)(((float)entityItem.field_70292_b + entityRenderTicks) / 10.0f + entityItem.field_70290_d)) * 0.1f + 0.1f;
            if (Minecraft.func_71410_x().field_71474_y.field_74347_j) {
                if (!scriptCustomItem.isNormalItem()) {
                    GL11.glRotatef((float)scriptCustomItem.getRotationX().floatValue(), (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glRotatef((float)scriptCustomItem.getRotationY().floatValue(), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)scriptCustomItem.getRotationZ().floatValue(), (float)0.0f, (float)0.0f, (float)1.0f);
                }
                GL11.glRotatef((float)(scriptCustomItem.getRotationXRate().floatValue() * entityRenderTicks % 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)(scriptCustomItem.getRotationYRate().floatValue() * entityRenderTicks % 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(scriptCustomItem.getRotationZRate().floatValue() * entityRenderTicks % 360.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            }
            if (!RenderItem.field_82407_g) {
                if (!scriptCustomItem.isNormalItem()) {
                    GL11.glScalef((float)scriptCustomItem.getScaleX().floatValue(), (float)scriptCustomItem.getScaleY().floatValue(), (float)scriptCustomItem.getScaleZ().floatValue());
                    GL11.glTranslatef((float)0.0f, (float)((Math.max(scriptCustomItem.getScaleY().floatValue(), 1.0f) - 1.0f) * 0.25f), (float)0.0f);
                }
                GL11.glTranslatef((float)0.0f, (float)(-bobbing), (float)0.0f);
            }
            int color = scriptCustomItem.getColor();
            float itemRed = (float)(color >> 16 & 0xFF) / 255.0f;
            float itemGreen = (float)(color >> 8 & 0xFF) / 255.0f;
            float itemBlue = (float)(color & 0xFF) / 255.0f;
            GL11.glColor4f((float)itemRed, (float)itemGreen, (float)itemBlue, (float)1.0f);
            this.renderEntityCustomItem(scriptCustomItem, itemStack, entityItem);
            GL11.glPopMatrix();
            return;
        }
        if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON && scriptCustomItem.isNormalItem()) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.9375f, (float)0.0625f, (float)0.0f);
            GL11.glRotatef((float)-315.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslatef((float)0.135f, (float)0.2f, (float)0.07f);
            int color = scriptCustomItem.getColor();
            float itemRed = (float)(color >> 16 & 0xFF) / 255.0f;
            float itemGreen = (float)(color >> 8 & 0xFF) / 255.0f;
            float itemBlue = (float)(color & 0xFF) / 255.0f;
            GL11.glColor4f((float)itemRed, (float)itemGreen, (float)itemBlue, (float)1.0f);
            GL11.glRotatef((float)-20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)-50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.09375f, (float)0.0625f, (float)0.0f);
            EntityLivingBase entityLivingBase = (EntityLivingBase)data[1];
            this.renderItem3d(scriptCustomItem, entityLivingBase, itemStack);
            GL11.glPopMatrix();
            return;
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.9375f, (float)0.0625f, (float)0.0f);
        GL11.glRotatef((float)-315.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        ((ItemCustomizable)itemStack.func_77973_b()).renderOffset(scriptCustomItem);
        if (scriptCustomItem.isNormalItem()) {
            GL11.glTranslatef((float)-0.05f, (float)0.3f, (float)0.3f);
            GL11.glRotatef((float)50.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)-80.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)80.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)(scriptCustomItem.getRotationXRate().floatValue() * (float)this.item3dRenderTicks % 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(scriptCustomItem.getRotationYRate().floatValue() * (float)this.item3dRenderTicks % 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(scriptCustomItem.getRotationZRate().floatValue() * (float)this.item3dRenderTicks % 360.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glScalef((float)0.6f, (float)0.6f, (float)0.6f);
        } else {
            GL11.glTranslatef((float)scriptCustomItem.getTranslateX().floatValue(), (float)scriptCustomItem.getTranslateY().floatValue(), (float)scriptCustomItem.getTranslateZ().floatValue());
            GL11.glRotatef((float)scriptCustomItem.getRotationX().floatValue(), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)scriptCustomItem.getRotationY().floatValue(), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)scriptCustomItem.getRotationZ().floatValue(), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)(scriptCustomItem.getRotationXRate().floatValue() * (float)this.item3dRenderTicks % 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(scriptCustomItem.getRotationYRate().floatValue() * (float)this.item3dRenderTicks % 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(scriptCustomItem.getRotationZRate().floatValue() * (float)this.item3dRenderTicks % 360.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glScalef((float)scriptCustomItem.getScaleX().floatValue(), (float)scriptCustomItem.getScaleY().floatValue(), (float)scriptCustomItem.getScaleZ().floatValue());
        }
        int color = scriptCustomItem.getColor();
        float itemRed = (float)(color >> 16 & 0xFF) / 255.0f;
        float itemGreen = (float)(color >> 8 & 0xFF) / 255.0f;
        float itemBlue = (float)(color & 0xFF) / 255.0f;
        GL11.glColor4f((float)itemRed, (float)itemGreen, (float)itemBlue, (float)1.0f);
        GL11.glRotatef((float)-20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)-50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.09375f, (float)0.0625f, (float)0.0f);
        EntityLivingBase entityLivingBase = (EntityLivingBase)data[1];
        this.renderItem3d(scriptCustomItem, entityLivingBase, itemStack);
        GL11.glPopMatrix();
    }

    private void setEntitySize(EntityItem entityItem, float p_70105_1_, float p_70105_2_) {
        float f2;
        if (p_70105_1_ != entityItem.field_70130_N || p_70105_2_ != entityItem.field_70131_O) {
            f2 = entityItem.field_70130_N;
            entityItem.field_70130_N = p_70105_1_;
            entityItem.field_70131_O = p_70105_2_;
            entityItem.field_70121_D.field_72336_d = entityItem.field_70121_D.field_72340_a + (double)entityItem.field_70130_N;
            entityItem.field_70121_D.field_72334_f = entityItem.field_70121_D.field_72339_c + (double)entityItem.field_70130_N;
            entityItem.field_70121_D.field_72337_e = entityItem.field_70121_D.field_72338_b + (double)entityItem.field_70131_O;
            if (entityItem.field_70130_N > f2 && !entityItem.field_70148_d && !entityItem.field_70170_p.field_72995_K) {
                entityItem.func_70091_d((double)(f2 - entityItem.field_70130_N), 0.0, (double)(f2 - entityItem.field_70130_N));
            }
        }
        entityItem.field_70168_am = (double)(f2 = p_70105_1_ % 2.0f) < 0.375 ? Entity.EnumEntitySize.SIZE_1 : ((double)f2 < 0.75 ? Entity.EnumEntitySize.SIZE_2 : ((double)f2 < 1.0 ? Entity.EnumEntitySize.SIZE_3 : ((double)f2 < 1.375 ? Entity.EnumEntitySize.SIZE_4 : ((double)f2 < 1.75 ? Entity.EnumEntitySize.SIZE_5 : Entity.EnumEntitySize.SIZE_6))));
    }

    public void renderEntityCustomItem(IItemCustomizable scriptCustomItem, ItemStack itemStack, EntityItem entityItem) {
        int pass = 0;
        GL11.glPushMatrix();
        ImageData entityImageData = ClientCacheHandler.getImageData(scriptCustomItem.getTexture());
        entityImageData.bindTexture();
        if (RenderItem.field_82407_g) {
            GL11.glTranslatef((float)0.0f, (float)-0.05f, (float)0.0f);
            GL11.glScalef((float)1.025641f, (float)1.025641f, (float)1.025641f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)scriptCustomItem.getTranslateX().floatValue(), (float)scriptCustomItem.getTranslateY().floatValue(), (float)scriptCustomItem.getTranslateZ().floatValue());
        }
        Tessellator tessellator = Tessellator.field_78398_a;
        float entityVOff = 0.0f;
        float entityVEnd = 1.0f;
        if (entityImageData.isAnimated()) {
            entityVOff = entityImageData.getCurrentFrameVOffset();
            entityVEnd = entityVOff + (float)entityImageData.getFrameHeight() / (float)entityImageData.getTotalHeight();
        } else if (scriptCustomItem.isTextureAnimated() != null && scriptCustomItem.isTextureAnimated().booleanValue() && scriptCustomItem.getFrameCount() != null && scriptCustomItem.getFrameCount() > 1) {
            entityVOff = AnimationHelper.getFrameVOffset(entityImageData.getTotalHeight(), scriptCustomItem.getFrameCount(), scriptCustomItem.getFrameTime());
            entityVEnd = entityVOff + AnimationHelper.getFrameVSize(entityImageData.getTotalHeight(), scriptCustomItem.getFrameCount());
        }
        float f14 = 0.0f;
        float f15 = 1.0f;
        float f4 = entityVOff;
        float f5 = entityVEnd;
        float f6 = 1.0f;
        float f7 = 0.5f;
        float f8 = 0.25f;
        if (RenderManager.field_78727_a.field_78733_k.field_74347_j) {
            GL11.glPushMatrix();
            float f9 = 0.0625f;
            float f10 = 0.021875f;
            int j = itemStack.field_77994_a;
            int b0 = j < 2 ? 1 : (j < 16 ? 2 : (j < 32 ? 3 : 4));
            if (!scriptCustomItem.isNormalItem()) {
                GL11.glScalef((float)scriptCustomItem.getScaleX().floatValue(), (float)scriptCustomItem.getScaleY().floatValue(), (float)scriptCustomItem.getScaleZ().floatValue());
            }
            GL11.glTranslatef((float)(-f7), (float)(-f8), (float)(-((f9 + f10) * (float)b0 / 2.0f)));
            for (int k = 0; k < b0; ++k) {
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(f9 + f10));
                ImageData loopImageData = ClientCacheHandler.getImageData(scriptCustomItem.getTexture());
                int entityFrameH = loopImageData.isAnimated() ? loopImageData.getFrameHeight() : (scriptCustomItem.isTextureAnimated() != null && scriptCustomItem.isTextureAnimated() != false && scriptCustomItem.getFrameCount() != null && scriptCustomItem.getFrameCount() > 1 ? loopImageData.getTotalHeight() / scriptCustomItem.getFrameCount() : loopImageData.getTotalHeight());
                ItemRenderer.func_78439_a((Tessellator)tessellator, (float)f15, (float)f4, (float)f14, (float)f5, (int)loopImageData.getTotalWidth(), (int)entityFrameH, (float)f9);
                if (!itemStack.hasEffect(pass)) continue;
                GL11.glDepthFunc((int)514);
                GL11.glDisable((int)2896);
                RenderManager.field_78727_a.field_78724_e.func_110577_a(RES_ITEM_GLINT);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)768, (int)1);
                float f11 = 0.76f;
                GL11.glColor4f((float)(0.5f * f11), (float)(0.25f * f11), (float)(0.8f * f11), (float)1.0f);
                GL11.glMatrixMode((int)5890);
                GL11.glPushMatrix();
                float f12 = 0.125f;
                GL11.glScalef((float)f12, (float)f12, (float)f12);
                float f13 = (float)(Minecraft.func_71386_F() % 3000L) / 3000.0f * 8.0f;
                GL11.glTranslatef((float)f13, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)-50.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                ItemRenderer.func_78439_a((Tessellator)tessellator, (float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f, (int)255, (int)255, (float)f9);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glScalef((float)f12, (float)f12, (float)f12);
                f13 = (float)(Minecraft.func_71386_F() % 4873L) / 4873.0f * 8.0f;
                GL11.glTranslatef((float)(-f13), (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                ItemRenderer.func_78439_a((Tessellator)tessellator, (float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f, (int)255, (int)255, (float)f9);
                GL11.glPopMatrix();
                GL11.glMatrixMode((int)5888);
                GL11.glDisable((int)3042);
                GL11.glEnable((int)2896);
                GL11.glDepthFunc((int)515);
            }
            GL11.glPopMatrix();
        } else {
            int j = itemStack.field_77994_a;
            int b0 = j < 2 ? 1 : (j < 16 ? 2 : (j < 32 ? 3 : 4));
            for (int l = 0; l < b0; ++l) {
                GL11.glPushMatrix();
                if (l > 0) {
                    float f10 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.3f;
                    float f16 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.3f;
                    float f17 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.3f;
                    GL11.glTranslatef((float)f10, (float)f16, (float)f17);
                }
                if (!RenderItem.field_82407_g) {
                    GL11.glRotatef((float)(180.0f - RenderManager.field_78727_a.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
                }
                tessellator.func_78382_b();
                tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
                tessellator.func_78374_a((double)(0.0f - f7), (double)(0.0f - f8), 0.0, (double)f14, (double)f5);
                tessellator.func_78374_a((double)(f6 - f7), (double)(0.0f - f8), 0.0, (double)f15, (double)f5);
                tessellator.func_78374_a((double)(f6 - f7), (double)(1.0f - f8), 0.0, (double)f15, (double)f4);
                tessellator.func_78374_a((double)(0.0f - f7), (double)(1.0f - f8), 0.0, (double)f14, (double)f4);
                tessellator.func_78381_a();
                GL11.glPopMatrix();
            }
        }
        GL11.glPopMatrix();
    }

    public void renderInventoryCustomItem(IItemCustomizable scriptCustomItem) {
        GL11.glPushMatrix();
        int color = scriptCustomItem.getColor();
        float itemRed = (float)(color >> 16 & 0xFF) / 255.0f;
        float itemGreen = (float)(color >> 8 & 0xFF) / 255.0f;
        float itemBlue = (float)(color & 0xFF) / 255.0f;
        GL11.glColor4f((float)itemRed, (float)itemGreen, (float)itemBlue, (float)1.0f);
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3008);
        ImageData imageData = ClientCacheHandler.getImageData(scriptCustomItem.getTexture());
        imageData.bindTexture();
        float vOff = 0.0f;
        float vEnd = 1.0f;
        if (imageData.isAnimated()) {
            vOff = imageData.getCurrentFrameVOffset();
            vEnd = vOff + (float)imageData.getFrameHeight() / (float)imageData.getTotalHeight();
        } else if (scriptCustomItem.isTextureAnimated() != null && scriptCustomItem.isTextureAnimated().booleanValue() && scriptCustomItem.getFrameCount() != null && scriptCustomItem.getFrameCount() > 1) {
            vOff = AnimationHelper.getFrameVOffset(imageData.getTotalHeight(), scriptCustomItem.getFrameCount(), scriptCustomItem.getFrameTime());
            vEnd = vOff + AnimationHelper.getFrameVSize(imageData.getTotalHeight(), scriptCustomItem.getFrameCount());
        }
        this.renderCustomItemSlot(0, 0, 16, 16, itemRed, itemGreen, itemBlue, vOff, vEnd);
        GL11.glEnable((int)2896);
        GL11.glDisable((int)3008);
        if (scriptCustomItem.getMCItemStack().hasEffect(0)) {
            this.renderEffect(Minecraft.func_71410_x().func_110434_K(), 0, 0);
        }
        GL11.glEnable((int)2896);
        GL11.glPopMatrix();
    }

    public void renderEffect(TextureManager manager, int x, int y) {
        GL11.glDepthFunc((int)514);
        GL11.glDisable((int)2896);
        GL11.glDepthMask((boolean)false);
        manager.func_110577_a(RES_ITEM_GLINT);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)3042);
        GL11.glColor4f((float)0.5f, (float)0.25f, (float)0.8f, (float)1.0f);
        this.renderGlint(x - 2, y - 2, 20, 20);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)3008);
        GL11.glEnable((int)2896);
        GL11.glDepthFunc((int)515);
    }

    private void renderGlint(int p_77018_2_, int p_77018_3_, int p_77018_4_, int p_77018_5_) {
        for (int j1 = 0; j1 < 2; ++j1) {
            OpenGlHelper.func_148821_a((int)772, (int)1, (int)0, (int)0);
            float f = 0.00390625f;
            float f1 = 0.00390625f;
            float f2 = (float)(Minecraft.func_71386_F() % (long)(3000 + j1 * 1873)) / (3000.0f + (float)(j1 * 1873)) * 256.0f;
            float f3 = 0.0f;
            Tessellator tessellator = Tessellator.field_78398_a;
            float f4 = 4.0f;
            if (j1 == 1) {
                f4 = -1.0f;
            }
            tessellator.func_78382_b();
            tessellator.func_78374_a((double)(p_77018_2_ + 0), (double)(p_77018_3_ + p_77018_5_), 0.0, (double)((f2 + (float)p_77018_5_ * f4) * f), (double)((f3 + (float)p_77018_5_) * f1));
            tessellator.func_78374_a((double)(p_77018_2_ + p_77018_4_), (double)(p_77018_3_ + p_77018_5_), 0.0, (double)((f2 + (float)p_77018_4_ + (float)p_77018_5_ * f4) * f), (double)((f3 + (float)p_77018_5_) * f1));
            tessellator.func_78374_a((double)(p_77018_2_ + p_77018_4_), (double)(p_77018_3_ + 0), 0.0, (double)((f2 + (float)p_77018_4_) * f), (double)((f3 + 0.0f) * f1));
            tessellator.func_78374_a((double)(p_77018_2_ + 0), (double)(p_77018_3_ + 0), 0.0, (double)((f2 + 0.0f) * f), (double)((f3 + 0.0f) * f1));
            tessellator.func_78381_a();
        }
    }

    public void renderCustomItemSlot(int posX, int posY, int imageWidth, int imageHeight, float itemRed, float itemGreen, float itemBlue) {
        this.renderCustomItemSlot(posX, posY, imageWidth, imageHeight, itemRed, itemGreen, itemBlue, 0.0f, 1.0f);
    }

    public void renderCustomItemSlot(int posX, int posY, int imageWidth, int imageHeight, float itemRed, float itemGreen, float itemBlue, float vOff, float vEnd) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78369_a(itemRed, itemGreen, itemBlue, 1.0f);
        tessellator.func_78374_a((double)posX, (double)(posY + imageHeight), 0.0, 0.0, (double)vEnd);
        tessellator.func_78374_a((double)(posX + imageWidth), (double)(posY + imageHeight), 0.0, 1.0, (double)vEnd);
        tessellator.func_78374_a((double)(posX + imageWidth), (double)posY, 0.0, 1.0, (double)vOff);
        tessellator.func_78374_a((double)posX, (double)posY, 0.0, 0.0, (double)vOff);
        tessellator.func_78381_a();
    }

    public void renderItem3d(IItemCustomizable scriptCustomItem, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        ++this.item3dRenderTicks;
        Minecraft mc = Minecraft.func_71410_x();
        TextureManager texturemanager = mc.func_110434_K();
        int par3 = 0;
        ImageData item3dImageData = ClientCacheHandler.getImageData(scriptCustomItem.getTexture());
        item3dImageData.bindTexture();
        Tessellator tessellator = Tessellator.field_78398_a;
        IIcon icon = entityLivingBase.func_70620_b(itemStack, par3);
        if (icon == null) {
            return;
        }
        GL11.glEnable((int)32826);
        float f4 = 0.0f;
        float f5 = 0.3f;
        GL11.glTranslatef((float)(-f4), (float)(-f5), (float)0.0f);
        GL11.glRotatef((float)50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)335.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)-0.9375f, (float)-0.0625f, (float)0.0f);
        float item3dVOff = 0.0f;
        float item3dVEnd = 1.0f;
        if (item3dImageData.isAnimated()) {
            item3dVOff = item3dImageData.getCurrentFrameVOffset();
            item3dVEnd = item3dVOff + (float)item3dImageData.getFrameHeight() / (float)item3dImageData.getTotalHeight();
        } else if (scriptCustomItem.isTextureAnimated() != null && scriptCustomItem.isTextureAnimated().booleanValue() && scriptCustomItem.getFrameCount() != null && scriptCustomItem.getFrameCount() > 1) {
            item3dVOff = AnimationHelper.getFrameVOffset(item3dImageData.getTotalHeight(), scriptCustomItem.getFrameCount(), scriptCustomItem.getFrameTime());
            item3dVEnd = item3dVOff + AnimationHelper.getFrameVSize(item3dImageData.getTotalHeight(), scriptCustomItem.getFrameCount());
        }
        ItemCustomRenderer.renderCustomItemIn2D(scriptCustomItem, tessellator, 1.0f, item3dVOff, 0.0f, item3dVEnd, 0.0625f);
        if (itemStack.hasEffect(par3)) {
            GL11.glDepthFunc((int)514);
            GL11.glDisable((int)2896);
            texturemanager.func_110577_a(enchant);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)768, (int)1);
            float f7 = 0.76f;
            GL11.glColor4f((float)(0.5f * f7), (float)(0.25f * f7), (float)(0.8f * f7), (float)1.0f);
            GL11.glMatrixMode((int)5890);
            GL11.glPushMatrix();
            float f8 = 0.125f;
            GL11.glScalef((float)f8, (float)f8, (float)f8);
            float f9 = (float)(Minecraft.func_71386_F() % 3000L) / 3000.0f * 8.0f;
            GL11.glTranslatef((float)f9, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)-50.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            ItemRenderer.func_78439_a((Tessellator)tessellator, (float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f, (int)256, (int)256, (float)0.0625f);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)f8, (float)f8, (float)f8);
            f9 = (float)(Minecraft.func_71386_F() % 4873L) / 4873.0f * 8.0f;
            GL11.glTranslatef((float)(-f9), (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            ItemRenderer.func_78439_a((Tessellator)tessellator, (float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f, (int)256, (int)256, (float)0.0625f);
            GL11.glPopMatrix();
            GL11.glMatrixMode((int)5888);
            GL11.glDisable((int)3042);
            GL11.glEnable((int)2896);
            GL11.glDepthFunc((int)515);
        }
        GL11.glDisable((int)32826);
    }

    public static void renderCustomItemIn2D(IItemCustomizable wrapper, Tessellator p_78439_0_, float p_78439_1_, float p_78439_2_, float p_78439_3_, float p_78439_4_, float p_78439_7_) {
        float f9;
        float f8;
        float f7;
        int k;
        ImageData imageData = ClientCacheHandler.getImageData(wrapper.getTexture());
        int width = imageData.getTotalWidth();
        int height = imageData.isAnimated() ? imageData.getFrameHeight() : (wrapper.isTextureAnimated() != null && wrapper.isTextureAnimated() != false && wrapper.getFrameCount() != null && wrapper.getFrameCount() > 1 ? imageData.getTotalHeight() / wrapper.getFrameCount() : imageData.getTotalHeight());
        p_78439_0_.func_78382_b();
        p_78439_0_.func_78375_b(0.0f, 0.0f, 1.0f);
        p_78439_0_.func_78374_a(0.0, 0.0, 0.0, (double)p_78439_1_, (double)p_78439_4_);
        p_78439_0_.func_78374_a(1.0, 0.0, 0.0, (double)p_78439_3_, (double)p_78439_4_);
        p_78439_0_.func_78374_a(1.0, 1.0, 0.0, (double)p_78439_3_, (double)p_78439_2_);
        p_78439_0_.func_78374_a(0.0, 1.0, 0.0, (double)p_78439_1_, (double)p_78439_2_);
        p_78439_0_.func_78381_a();
        p_78439_0_.func_78382_b();
        p_78439_0_.func_78375_b(0.0f, 0.0f, -1.0f);
        p_78439_0_.func_78374_a(0.0, 1.0, (double)(0.0f - p_78439_7_), (double)p_78439_1_, (double)p_78439_2_);
        p_78439_0_.func_78374_a(1.0, 1.0, (double)(0.0f - p_78439_7_), (double)p_78439_3_, (double)p_78439_2_);
        p_78439_0_.func_78374_a(1.0, 0.0, (double)(0.0f - p_78439_7_), (double)p_78439_3_, (double)p_78439_4_);
        p_78439_0_.func_78374_a(0.0, 0.0, (double)(0.0f - p_78439_7_), (double)p_78439_1_, (double)p_78439_4_);
        p_78439_0_.func_78381_a();
        float f5 = 0.5f * (p_78439_1_ - p_78439_3_) / (float)width;
        float f6 = 0.5f * (p_78439_4_ - p_78439_2_) / (float)height;
        p_78439_0_.func_78382_b();
        p_78439_0_.func_78375_b(-1.0f, 0.0f, 0.0f);
        for (k = 0; k < width; ++k) {
            f7 = (float)k / (float)width;
            f8 = p_78439_1_ + (p_78439_3_ - p_78439_1_) * f7 - f5;
            p_78439_0_.func_78374_a((double)f7, 0.0, (double)(0.0f - p_78439_7_), (double)f8, (double)p_78439_4_);
            p_78439_0_.func_78374_a((double)f7, 0.0, 0.0, (double)f8, (double)p_78439_4_);
            p_78439_0_.func_78374_a((double)f7, 1.0, 0.0, (double)f8, (double)p_78439_2_);
            p_78439_0_.func_78374_a((double)f7, 1.0, (double)(0.0f - p_78439_7_), (double)f8, (double)p_78439_2_);
        }
        p_78439_0_.func_78381_a();
        p_78439_0_.func_78382_b();
        p_78439_0_.func_78375_b(1.0f, 0.0f, 0.0f);
        for (k = 0; k < width; ++k) {
            f7 = (float)k / (float)width;
            f8 = p_78439_1_ + (p_78439_3_ - p_78439_1_) * f7 - f5;
            f9 = f7 + 1.0f / (float)width;
            p_78439_0_.func_78374_a((double)f9, 1.0, (double)(0.0f - p_78439_7_), (double)f8, (double)p_78439_2_);
            p_78439_0_.func_78374_a((double)f9, 1.0, 0.0, (double)f8, (double)p_78439_2_);
            p_78439_0_.func_78374_a((double)f9, 0.0, 0.0, (double)f8, (double)p_78439_4_);
            p_78439_0_.func_78374_a((double)f9, 0.0, (double)(0.0f - p_78439_7_), (double)f8, (double)p_78439_4_);
        }
        p_78439_0_.func_78381_a();
        p_78439_0_.func_78382_b();
        p_78439_0_.func_78375_b(0.0f, 1.0f, 0.0f);
        for (k = 0; k < height; ++k) {
            f7 = (float)k / (float)height;
            f8 = p_78439_4_ + (p_78439_2_ - p_78439_4_) * f7 - f6;
            f9 = f7 + 1.0f / (float)height;
            p_78439_0_.func_78374_a(0.0, (double)f9, 0.0, (double)p_78439_1_, (double)f8);
            p_78439_0_.func_78374_a(1.0, (double)f9, 0.0, (double)p_78439_3_, (double)f8);
            p_78439_0_.func_78374_a(1.0, (double)f9, (double)(0.0f - p_78439_7_), (double)p_78439_3_, (double)f8);
            p_78439_0_.func_78374_a(0.0, (double)f9, (double)(0.0f - p_78439_7_), (double)p_78439_1_, (double)f8);
        }
        p_78439_0_.func_78381_a();
        p_78439_0_.func_78382_b();
        p_78439_0_.func_78375_b(0.0f, -1.0f, 0.0f);
        for (k = 0; k < height; ++k) {
            f7 = (float)k / (float)height;
            f8 = p_78439_4_ + (p_78439_2_ - p_78439_4_) * f7 - f6;
            p_78439_0_.func_78374_a(1.0, (double)f7, 0.0, (double)p_78439_3_, (double)f8);
            p_78439_0_.func_78374_a(0.0, (double)f7, 0.0, (double)p_78439_1_, (double)f8);
            p_78439_0_.func_78374_a(0.0, (double)f7, (double)(0.0f - p_78439_7_), (double)p_78439_1_, (double)f8);
            p_78439_0_.func_78374_a(1.0, (double)f7, (double)(0.0f - p_78439_7_), (double)p_78439_3_, (double)f8);
        }
        p_78439_0_.func_78381_a();
    }
}

