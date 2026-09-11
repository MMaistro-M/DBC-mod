/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import noppes.npcs.items.ItemRenderInterface;
import org.lwjgl.opengl.GL11;

public class NpcItemRenderer
implements IItemRenderer {
    private static final ResourceLocation enchant = new ResourceLocation("textures/misc/enchanted_item_glint.png");

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return false;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack par2ItemStack, Object ... data) {
        if (!(par2ItemStack.func_77973_b() instanceof ItemRenderInterface)) {
            return;
        }
        EntityLivingBase par1EntityLiving = (EntityLivingBase)data[1];
        GL11.glTranslatef((float)0.9375f, (float)0.0625f, (float)0.0f);
        GL11.glRotatef((float)-315.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        ((ItemRenderInterface)par2ItemStack.func_77973_b()).renderSpecial();
        GL11.glRotatef((float)-20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)-50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.09375f, (float)0.0625f, (float)0.0f);
        this.renderItem3d(par1EntityLiving, par2ItemStack);
    }

    public void renderItem3d(EntityLivingBase par1EntityLiving, ItemStack par2ItemStack) {
        Minecraft mc = Minecraft.func_71410_x();
        TextureManager texturemanager = mc.func_110434_K();
        int par3 = 0;
        texturemanager.func_110577_a(texturemanager.func_130087_a(par2ItemStack.func_94608_d()));
        Tessellator tessellator = Tessellator.field_78398_a;
        IIcon icon = par1EntityLiving.func_70620_b(par2ItemStack, par3);
        if (icon == null) {
            return;
        }
        float f = icon.func_94209_e();
        float f1 = icon.func_94212_f();
        float f2 = icon.func_94206_g();
        float f3 = icon.func_94210_h();
        float f4 = 0.0f;
        float f5 = 0.3f;
        GL11.glEnable((int)32826);
        GL11.glTranslatef((float)(-f4), (float)(-f5), (float)0.0f);
        float f6 = 1.5f;
        GL11.glScalef((float)f6, (float)f6, (float)f6);
        GL11.glRotatef((float)50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)335.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)-0.9375f, (float)-0.0625f, (float)0.0f);
        ItemRenderer.func_78439_a((Tessellator)tessellator, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
        if (par2ItemStack.hasEffect(par3)) {
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
}

