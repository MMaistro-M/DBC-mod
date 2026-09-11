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
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import noppes.npcs.items.ItemNpcTool;
import org.lwjgl.opengl.GL11;

public class ItemToolRenderer
implements IItemRenderer {
    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    private static final ResourceLocation PAINTBRUSH_HANDLE = new ResourceLocation("customnpcs", "textures/items/npcBrushHandle.png");
    private static final ResourceLocation PAINTBRUSH_BRUSH = new ResourceLocation("customnpcs", "textures/items/npcBrushHair.png");
    private static final ResourceLocation HAMMER = new ResourceLocation("customnpcs", "textures/items/npcToolHammer.png");
    private static final ResourceLocation MAGIC_BOOK = new ResourceLocation("customnpcs", "textures/items/npcToolMagicBook.png");
    private final Random random = new Random();

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return type != IItemRenderer.ItemRenderType.FIRST_PERSON_MAP;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return helper == IItemRenderer.ItemRendererHelper.ENTITY_ROTATION || helper == IItemRenderer.ItemRendererHelper.ENTITY_BOBBING;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack itemStack, Object ... data) {
        if (itemStack == null || !(itemStack.func_77973_b() instanceof ItemNpcTool)) {
            return;
        }
        if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glPushMatrix();
            this.renderInventoryCustomItem(itemStack);
            GL11.glPopMatrix();
            return;
        }
        if (type == IItemRenderer.ItemRenderType.ENTITY) {
            GL11.glPushMatrix();
            EntityItem entityItem = (EntityItem)data[1];
            if (!entityItem.field_70158_ak) {
                entityItem.field_70158_ak = true;
                float entityXZSize = (float)Math.sqrt(Math.pow(1.0, 2.0) + Math.pow(1.0, 2.0));
                this.setEntitySize(entityItem, entityXZSize * 0.25f, 0.25f);
                double XSize = entityItem.field_70121_D.field_72336_d - entityItem.field_70121_D.field_72340_a;
                double YSize = entityItem.field_70121_D.field_72337_e - entityItem.field_70121_D.field_72338_b;
                double ZSize = entityItem.field_70121_D.field_72334_f - entityItem.field_70121_D.field_72339_c;
                entityItem.field_70121_D.field_72336_d = entityItem.field_70121_D.field_72340_a + XSize * 1.0;
                entityItem.field_70121_D.field_72337_e = entityItem.field_70121_D.field_72338_b + YSize * 1.0;
                entityItem.field_70121_D.field_72334_f = entityItem.field_70121_D.field_72339_c + ZSize * 1.0;
            }
            if (itemStack.func_77960_j() == 1) {
                this.renderEntityCustomItem(itemStack, entityItem, PAINTBRUSH_HANDLE);
                int color = ItemNpcTool.getColor(itemStack.func_77978_p());
                float red = (float)(color >> 16 & 0xFF) / 255.0f;
                float green = (float)(color >> 8 & 0xFF) / 255.0f;
                float blue = (float)(color & 0xFF) / 255.0f;
                GL11.glColor3f((float)red, (float)green, (float)blue);
                this.renderEntityCustomItem(itemStack, entityItem, PAINTBRUSH_BRUSH);
            } else if (itemStack.func_77960_j() == 0) {
                this.renderEntityCustomItem(itemStack, entityItem, HAMMER);
            } else if (itemStack.func_77960_j() == 2) {
                this.renderEntityCustomItem(itemStack, entityItem, MAGIC_BOOK);
            }
            GL11.glPopMatrix();
            return;
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.9375f, (float)0.0625f, (float)0.0f);
        GL11.glRotatef((float)-315.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)0.135f, (float)0.2f, (float)0.07f);
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glRotatef((float)-20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)-50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.09375f, (float)0.0625f, (float)0.0f);
        EntityLivingBase entityLivingBase = (EntityLivingBase)data[1];
        if (itemStack.func_77960_j() == 1) {
            GL11.glPushMatrix();
            this.renderItem3d(entityLivingBase, itemStack, PAINTBRUSH_HANDLE);
            GL11.glPopMatrix();
            int color = ItemNpcTool.getColor(itemStack.func_77978_p());
            float red = (float)(color >> 16 & 0xFF) / 255.0f;
            float green = (float)(color >> 8 & 0xFF) / 255.0f;
            float blue = (float)(color & 0xFF) / 255.0f;
            GL11.glColor3f((float)red, (float)green, (float)blue);
            GL11.glPushMatrix();
            this.renderItem3d(entityLivingBase, itemStack, PAINTBRUSH_BRUSH);
            GL11.glPopMatrix();
        } else if (itemStack.func_77960_j() == 0) {
            GL11.glPushMatrix();
            this.renderItem3d(entityLivingBase, itemStack, HAMMER);
            GL11.glPopMatrix();
        } else if (itemStack.func_77960_j() == 2) {
            GL11.glPushMatrix();
            this.renderItem3d(entityLivingBase, itemStack, MAGIC_BOOK);
            GL11.glPopMatrix();
        }
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

    public void renderEntityCustomItem(ItemStack itemStack, EntityItem entityItem, ResourceLocation location) {
        int pass = 0;
        GL11.glPushMatrix();
        TextureManager textureManager = Minecraft.func_71410_x().func_110434_K();
        textureManager.func_110577_a(location);
        if (RenderItem.field_82407_g) {
            GL11.glTranslatef((float)0.0f, (float)-0.05f, (float)0.0f);
            GL11.glScalef((float)1.025641f, (float)1.025641f, (float)1.025641f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        Tessellator tessellator = Tessellator.field_78398_a;
        float f14 = 0.0f;
        float f15 = 1.0f;
        float f4 = 0.0f;
        float f5 = 1.0f;
        float f6 = 1.0f;
        float f7 = 0.5f;
        float f8 = 0.25f;
        if (RenderManager.field_78727_a.field_78733_k.field_74347_j) {
            GL11.glPushMatrix();
            float f9 = 0.0625f;
            float f10 = 0.021875f;
            int j = itemStack.field_77994_a;
            int b0 = j < 2 ? 1 : (j < 16 ? 2 : (j < 32 ? 3 : 4));
            GL11.glTranslatef((float)(-f7), (float)(-f8), (float)(-((f9 + f10) * (float)b0 / 2.0f)));
            for (int k = 0; k < b0; ++k) {
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(f9 + f10));
                ItemRenderer.func_78439_a((Tessellator)tessellator, (float)1.0f, (float)0.0f, (float)0.0f, (float)1.0f, (int)16, (int)16, (float)f9);
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

    public void renderInventoryCustomItem(ItemStack itemStack) {
        GL11.glPushMatrix();
        TextureManager textureManager = Minecraft.func_71410_x().func_110434_K();
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3008);
        if (itemStack.func_77960_j() == 1) {
            textureManager.func_110577_a(PAINTBRUSH_HANDLE);
            this.renderCustomItemSlot(0, 0, 16, 16, 1.0f, 1.0f, 1.0f);
            int color = ItemNpcTool.getColor(itemStack.func_77978_p());
            float red = (float)(color >> 16 & 0xFF) / 255.0f;
            float green = (float)(color >> 8 & 0xFF) / 255.0f;
            float blue = (float)(color & 0xFF) / 255.0f;
            textureManager.func_110577_a(PAINTBRUSH_BRUSH);
            this.renderCustomItemSlot(0, 0, 16, 16, red, green, blue);
        } else if (itemStack.func_77960_j() == 0) {
            textureManager.func_110577_a(HAMMER);
            this.renderCustomItemSlot(0, 0, 16, 16, 1.0f, 1.0f, 1.0f);
        } else if (itemStack.func_77960_j() == 2) {
            textureManager.func_110577_a(MAGIC_BOOK);
            this.renderCustomItemSlot(0, 0, 16, 16, 1.0f, 1.0f, 1.0f);
        }
        GL11.glEnable((int)2896);
        GL11.glDisable((int)3008);
        if (itemStack.hasEffect(0)) {
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
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78369_a(itemRed, itemGreen, itemBlue, 1.0f);
        tessellator.func_78374_a((double)posX, (double)(posY + imageHeight), 0.0, 0.0, 1.0);
        tessellator.func_78374_a((double)(posX + imageWidth), (double)(posY + imageHeight), 0.0, 1.0, 1.0);
        tessellator.func_78374_a((double)(posX + imageWidth), (double)posY, 0.0, 1.0, 0.0);
        tessellator.func_78374_a((double)posX, (double)posY, 0.0, 0.0, 0.0);
        tessellator.func_78381_a();
    }

    public void renderItem3d(EntityLivingBase entityLivingBase, ItemStack itemStack, ResourceLocation location) {
        Minecraft mc = Minecraft.func_71410_x();
        TextureManager texturemanager = mc.func_110434_K();
        int par3 = 0;
        texturemanager.func_110577_a(location);
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
        ItemToolRenderer.renderCustomItemIn2D(tessellator, 1.0f, 0.0f, 0.0f, 1.0f, 0.0625f);
        if (itemStack.hasEffect(par3)) {
            GL11.glDepthFunc((int)514);
            GL11.glDisable((int)2896);
            texturemanager.func_110577_a(RES_ITEM_GLINT);
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

    public static void renderCustomItemIn2D(Tessellator p_78439_0_, float p_78439_1_, float p_78439_2_, float p_78439_3_, float p_78439_4_, float p_78439_7_) {
        float f9;
        float f8;
        float f7;
        int k;
        int width = 16;
        int height = 16;
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

