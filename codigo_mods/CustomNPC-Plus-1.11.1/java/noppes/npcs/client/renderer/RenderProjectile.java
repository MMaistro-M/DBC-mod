/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemPotion
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionHelper
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.entity.EntityProjectile;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderProjectile
extends Render {
    public boolean renderWithColor = true;
    private static final ResourceLocation field_110780_a = new ResourceLocation("textures/entity/arrow.png");
    private static final ResourceLocation field_110798_h = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    private RenderBlocks itemRenderBlocks = new RenderBlocks();

    public void doRenderProjectile(EntityProjectile par1EntityProjectile, double par2, double par4, double par6, float par8, float par9) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
        GL11.glEnable((int)32826);
        float f = (float)par1EntityProjectile.func_70096_w().func_75679_c(23) / 10.0f;
        ItemStack item = par1EntityProjectile.getItemDisplay();
        GL11.glScalef((float)f, (float)f, (float)f);
        Tessellator tessellator = Tessellator.field_78398_a;
        if (par1EntityProjectile.isArrow()) {
            this.func_110777_b((Entity)par1EntityProjectile);
            GL11.glRotatef((float)(par1EntityProjectile.field_70126_B + (par1EntityProjectile.field_70177_z - par1EntityProjectile.field_70126_B) * par9 - 90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(par1EntityProjectile.field_70127_C + (par1EntityProjectile.field_70125_A - par1EntityProjectile.field_70127_C) * par9), (float)0.0f, (float)0.0f, (float)1.0f);
            int b0 = 0;
            float f2 = 0.0f;
            float f3 = 0.5f;
            float f4 = (float)(0 + b0 * 10) / 32.0f;
            float f5 = (float)(5 + b0 * 10) / 32.0f;
            float f6 = 0.0f;
            float f7 = 0.15625f;
            float f8 = (float)(5 + b0 * 10) / 32.0f;
            float f9 = (float)(10 + b0 * 10) / 32.0f;
            float f10 = 0.05625f;
            GL11.glEnable((int)32826);
            float f11 = (float)par1EntityProjectile.arrowShake - par9;
            if (f11 > 0.0f) {
                float f12 = -MathHelper.func_76126_a((float)(f11 * 3.0f)) * f11;
                GL11.glRotatef((float)f12, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            GL11.glRotatef((float)45.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glScalef((float)f10, (float)f10, (float)f10);
            GL11.glTranslatef((float)-4.0f, (float)0.0f, (float)0.0f);
            GL11.glNormal3f((float)f10, (float)0.0f, (float)0.0f);
            tessellator.func_78382_b();
            tessellator.func_78374_a(-7.0, -2.0, -2.0, (double)f6, (double)f8);
            tessellator.func_78374_a(-7.0, -2.0, 2.0, (double)f7, (double)f8);
            tessellator.func_78374_a(-7.0, 2.0, 2.0, (double)f7, (double)f9);
            tessellator.func_78374_a(-7.0, 2.0, -2.0, (double)f6, (double)f9);
            tessellator.func_78381_a();
            GL11.glNormal3f((float)(-f10), (float)0.0f, (float)0.0f);
            tessellator.func_78382_b();
            tessellator.func_78374_a(-7.0, 2.0, -2.0, (double)f6, (double)f8);
            tessellator.func_78374_a(-7.0, 2.0, 2.0, (double)f7, (double)f8);
            tessellator.func_78374_a(-7.0, -2.0, 2.0, (double)f7, (double)f9);
            tessellator.func_78374_a(-7.0, -2.0, -2.0, (double)f6, (double)f9);
            tessellator.func_78381_a();
            for (int i = 0; i < 4; ++i) {
                GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glNormal3f((float)0.0f, (float)0.0f, (float)f10);
                tessellator.func_78382_b();
                tessellator.func_78374_a(-8.0, -2.0, 0.0, (double)f2, (double)f4);
                tessellator.func_78374_a(8.0, -2.0, 0.0, (double)f3, (double)f4);
                tessellator.func_78374_a(8.0, 2.0, 0.0, (double)f3, (double)f5);
                tessellator.func_78374_a(-8.0, 2.0, 0.0, (double)f2, (double)f5);
                tessellator.func_78381_a();
            }
        } else if (par1EntityProjectile.is3D()) {
            GL11.glRotatef((float)(par1EntityProjectile.field_70126_B + (par1EntityProjectile.field_70177_z - par1EntityProjectile.field_70126_B) * par9 - 90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(par1EntityProjectile.field_70127_C + (par1EntityProjectile.field_70125_A - par1EntityProjectile.field_70127_C) * par9 - 180.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            if (item.func_94608_d() == 0 && item.func_77973_b() instanceof ItemBlock && RenderBlocks.func_147739_a((int)Block.func_149634_a((Item)item.func_77973_b()).func_149645_b())) {
                Block block = Block.func_149634_a((Item)item.func_77973_b());
                this.func_110776_a(TextureMap.field_110575_b);
                float f7 = 0.25f;
                int j = block.func_149645_b();
                if (j == 1 || j == 19 || j == 12 || j == 2) {
                    f7 = 0.5f;
                }
                float f5 = 1.0f;
                this.itemRenderBlocks.func_147800_a(block, item.func_77960_j(), 1.0f);
            } else {
                GL11.glTranslatef((float)-0.6f, (float)-0.6f, (float)0.0f);
                if (item.func_77973_b().func_77623_v()) {
                    for (int k = 0; k < item.func_77973_b().getRenderPasses(item.func_77960_j()); ++k) {
                        IIcon icon = item.func_77973_b().getIcon(item, k);
                        float f8 = 1.0f;
                        if (this.renderWithColor) {
                            int i = item.func_77973_b().func_82790_a(item, k);
                            float f5 = (float)(i >> 16 & 0xFF) / 255.0f;
                            float f4 = (float)(i >> 8 & 0xFF) / 255.0f;
                            float f6 = (float)(i & 0xFF) / 255.0f;
                            GL11.glColor4f((float)(f5 * f8), (float)(f4 * f8), (float)(f6 * f8), (float)1.0f);
                            this.field_76990_c.field_78721_f.func_78443_a((EntityLivingBase)Minecraft.func_71410_x().field_71439_g, item, 0);
                            continue;
                        }
                        this.field_76990_c.field_78721_f.func_78443_a((EntityLivingBase)Minecraft.func_71410_x().field_71439_g, item, 0);
                    }
                } else {
                    IIcon icon1 = item.func_77954_c();
                    if (this.renderWithColor) {
                        int l = item.func_77973_b().func_82790_a(item, 0);
                        float f8 = (float)(l >> 16 & 0xFF) / 255.0f;
                        float f9 = (float)(l >> 8 & 0xFF) / 255.0f;
                        float f5 = (float)(l & 0xFF) / 255.0f;
                        float f4 = 1.0f;
                        this.renderDroppedItem(item, icon1, par9, f8 * f4, f9 * f4, f5 * f4, f);
                    } else {
                        this.renderDroppedItem(item, icon1, par9, 1.0f, 1.0f, 1.0f, f);
                    }
                }
            }
        } else {
            IIcon icon = item.func_77973_b().func_77617_a(item.func_77960_j());
            this.func_110776_a(TextureMap.field_110576_c);
            if (item.func_77973_b().func_77623_v()) {
                for (int k = 0; k < item.func_77973_b().getRenderPasses(item.func_77960_j()); ++k) {
                    int i = item.func_77973_b().func_82790_a(item, k);
                    float f5 = (float)(i >> 16 & 0xFF) / 255.0f;
                    float f4 = (float)(i >> 8 & 0xFF) / 255.0f;
                    float f6 = (float)(i & 0xFF) / 255.0f;
                    GL11.glColor4f((float)f5, (float)f4, (float)f6, (float)1.0f);
                }
            }
            if (icon == ItemPotion.func_94589_d((String)"bottle_splash") || icon == ItemPotion.func_94589_d((String)"bottle_drinkable")) {
                int var12 = PotionHelper.func_77915_a((int)item.func_77960_j(), (boolean)false);
                float var13 = (float)(var12 >> 16 & 0xFF) / 255.0f;
                float var14 = (float)(var12 >> 8 & 0xFF) / 255.0f;
                float var15 = (float)(var12 & 0xFF) / 255.0f;
                GL11.glColor3f((float)var13, (float)var14, (float)var15);
                GL11.glPushMatrix();
                this.renderSprite(tessellator, ItemPotion.func_94589_d((String)"overlay"));
                GL11.glPopMatrix();
                GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            }
            this.renderSprite(tessellator, icon);
        }
        if (par1EntityProjectile.is3D() && par1EntityProjectile.glows()) {
            GL11.glDisable((int)2896);
        }
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
        GL11.glEnable((int)2896);
    }

    private void renderSprite(Tessellator par1Tessellator, IIcon par2Icon) {
        float f = par2Icon.func_94209_e();
        float f1 = par2Icon.func_94212_f();
        float f2 = par2Icon.func_94206_g();
        float f3 = par2Icon.func_94210_h();
        float f4 = 1.0f;
        float f5 = 0.5f;
        float f6 = 0.25f;
        GL11.glRotatef((float)(180.0f - this.field_76990_c.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-this.field_76990_c.field_78732_j), (float)1.0f, (float)0.0f, (float)0.0f);
        par1Tessellator.func_78382_b();
        par1Tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        par1Tessellator.func_78374_a((double)(0.0f - f5), (double)(0.0f - f6), 0.0, (double)f, (double)f3);
        par1Tessellator.func_78374_a((double)(f4 - f5), (double)(0.0f - f6), 0.0, (double)f1, (double)f3);
        par1Tessellator.func_78374_a((double)(f4 - f5), (double)(f4 - f6), 0.0, (double)f1, (double)f2);
        par1Tessellator.func_78374_a((double)(0.0f - f5), (double)(f4 - f6), 0.0, (double)f, (double)f2);
        par1Tessellator.func_78381_a();
    }

    private void renderDroppedItem(ItemStack item, IIcon par2Icon, float par4, float par5, float par6, float par7, float par8) {
        Tessellator tessellator = Tessellator.field_78398_a;
        if (par2Icon == null) {
            TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
            ResourceLocation resourcelocation = texturemanager.func_130087_a(item.func_94608_d());
            par2Icon = ((TextureMap)texturemanager.func_110581_b(resourcelocation)).func_94245_a("missingno");
        }
        float f4 = par2Icon.func_94209_e();
        float f5 = par2Icon.func_94212_f();
        float f6 = par2Icon.func_94206_g();
        float f7 = par2Icon.func_94210_h();
        float f8 = 1.0f;
        float f9 = 0.5f;
        float f10 = 0.25f;
        float f12 = 0.0625f;
        if (item.func_94608_d() == 0) {
            this.func_110776_a(TextureMap.field_110575_b);
        } else {
            this.func_110776_a(TextureMap.field_110576_c);
        }
        GL11.glColor4f((float)par5, (float)par6, (float)par7, (float)1.0f);
        ItemRenderer.func_78439_a((Tessellator)tessellator, (float)f5, (float)f6, (float)f4, (float)f7, (int)par2Icon.func_94211_a(), (int)par2Icon.func_94216_b(), (float)f12);
        if (item != null && item.hasEffect(0)) {
            GL11.glDepthFunc((int)514);
            GL11.glDisable((int)2896);
            this.field_76990_c.field_78724_e.func_110577_a(field_110798_h);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)768, (int)1);
            float f13 = 0.76f;
            GL11.glColor4f((float)(0.5f * f13), (float)(0.25f * f13), (float)(0.8f * f13), (float)1.0f);
            GL11.glMatrixMode((int)5890);
            GL11.glPushMatrix();
            GL11.glScalef((float)par8, (float)par8, (float)par8);
            float f15 = (float)(Minecraft.func_71386_F() % 3000L) / 3000.0f * 8.0f;
            GL11.glTranslatef((float)f15, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)-50.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            ItemRenderer.func_78439_a((Tessellator)tessellator, (float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f, (int)255, (int)255, (float)f12);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)par8, (float)par8, (float)par8);
            f15 = (float)(Minecraft.func_71386_F() % 4873L) / 4873.0f * 8.0f;
            GL11.glTranslatef((float)(-f15), (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            ItemRenderer.func_78439_a((Tessellator)tessellator, (float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f, (int)255, (int)255, (float)f12);
            GL11.glPopMatrix();
            GL11.glMatrixMode((int)5888);
            GL11.glDisable((int)3042);
            GL11.glEnable((int)2896);
            GL11.glDepthFunc((int)515);
        }
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderProjectile((EntityProjectile)par1Entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110779_a(EntityProjectile par1EntityProjectile) {
        return par1EntityProjectile.isArrow() ? field_110780_a : this.field_76990_c.field_78724_e.func_130087_a(par1EntityProjectile.getItemDisplay().func_94608_d());
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return this.func_110779_a((EntityProjectile)par1Entity);
    }
}

