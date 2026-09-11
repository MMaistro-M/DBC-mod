/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.RenderBiped
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.model.ModelPony;
import noppes.npcs.client.model.ModelPonyArmor;
import noppes.npcs.client.renderer.RenderNPCInterface;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.entity.EntityNpcPony;
import org.lwjgl.opengl.GL11;

public class RenderNPCPony
extends RenderNPCInterface {
    private final ModelPony modelBipedMain;
    private final ModelPonyArmor modelArmorChestplate;
    private final ModelPonyArmor modelArmor;

    public RenderNPCPony() {
        super(new ModelPony(0.0f), 0.5f);
        this.modelBipedMain = (ModelPony)this.field_77045_g;
        this.modelArmorChestplate = new ModelPonyArmor(1.0f);
        this.modelArmor = new ModelPonyArmor(0.5f);
    }

    protected int setArmorModel(EntityNPCInterface entityplayer, int i, float f) {
        Item item;
        ItemStack itemstack = entityplayer.inventory.armorItemInSlot(i);
        if (itemstack != null && (item = itemstack.func_77973_b()) instanceof ItemArmor) {
            ItemArmor itemarmor = (ItemArmor)item;
            this.func_110776_a(RenderBiped.getArmorResource((Entity)entityplayer, (ItemStack)itemstack, (int)i, null));
            ModelPonyArmor modelbiped = i != 2 ? this.modelArmorChestplate : this.modelArmor;
            modelbiped.head.field_78806_j = i == 0;
            modelbiped.Body.field_78806_j = i == 1;
            modelbiped.BodyBack.field_78806_j = i == 1;
            modelbiped.rightarm.field_78806_j = i == 3;
            modelbiped.LeftArm.field_78806_j = i == 3;
            modelbiped.RightLeg.field_78806_j = i == 3;
            modelbiped.LeftLeg.field_78806_j = i == 3;
            modelbiped.rightarm2.field_78806_j = i == 2;
            modelbiped.LeftArm2.field_78806_j = i == 2;
            modelbiped.RightLeg2.field_78806_j = i == 2;
            modelbiped.LeftLeg2.field_78806_j = i == 2;
            this.func_77042_a(modelbiped);
            float var8 = 1.0f;
            if (itemarmor.func_82812_d() == ItemArmor.ArmorMaterial.CLOTH) {
                int var9 = itemarmor.func_82814_b(itemstack);
                float var10 = (float)(var9 >> 16 & 0xFF) / 255.0f;
                float var11 = (float)(var9 >> 8 & 0xFF) / 255.0f;
                float var12 = (float)(var9 & 0xFF) / 255.0f;
                GL11.glColor3f((float)(var8 * var10), (float)(var8 * var11), (float)(var8 * var12));
                if (itemstack.func_77948_v()) {
                    return 31;
                }
                return 16;
            }
            GL11.glColor3f((float)var8, (float)var8, (float)var8);
            return !itemstack.func_77948_v() ? 1 : 15;
        }
        return -1;
    }

    @Override
    public ResourceLocation func_110775_a(Entity entity) {
        EntityNpcPony pony = (EntityNpcPony)entity;
        boolean check = pony.textureLocation == null || pony.textureLocation != pony.checked;
        ResourceLocation loc = super.func_110775_a((Entity)pony);
        if (check) {
            try {
                IResource resource = Minecraft.func_71410_x().func_110442_L().func_110536_a(loc);
                try (InputStream stream = resource.func_110527_b();){
                    BufferedImage bufferedimage = ImageIO.read(stream);
                    pony.isPegasus = false;
                    pony.isUnicorn = false;
                    Color color = new Color(bufferedimage.getRGB(0, 0), true);
                    Color color1 = new Color(249, 177, 49, 255);
                    Color color2 = new Color(136, 202, 240, 255);
                    Color color3 = new Color(209, 159, 228, 255);
                    Color color4 = new Color(254, 249, 252, 255);
                    if (color.equals(color1)) {
                        // empty if block
                    }
                    if (color.equals(color2)) {
                        pony.isPegasus = true;
                    }
                    if (color.equals(color3)) {
                        pony.isUnicorn = true;
                    }
                    if (color.equals(color4)) {
                        pony.isPegasus = true;
                        pony.isUnicorn = true;
                    }
                    pony.checked = loc;
                }
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        return loc;
    }

    public void renderPlayer(EntityNpcPony pony, double d, double d1, double d2, float f, float f1) {
        ItemStack itemstack = pony.func_70694_bm();
        this.func_77042_a(this.modelBipedMain);
        this.modelBipedMain.heldItemRight = itemstack == null ? 0 : 1;
        this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight;
        this.modelArmorChestplate.heldItemRight = this.modelBipedMain.heldItemRight;
        this.modelArmor.isSneak = this.modelBipedMain.isSneak = pony.func_70093_af();
        this.modelArmorChestplate.isSneak = this.modelBipedMain.isSneak;
        this.modelBipedMain.field_78093_q = false;
        this.modelArmor.field_78093_q = false;
        this.modelArmorChestplate.field_78093_q = false;
        this.modelArmor.isSleeping = this.modelBipedMain.isSleeping = pony.func_70608_bn();
        this.modelArmorChestplate.isSleeping = this.modelBipedMain.isSleeping;
        this.modelArmor.isUnicorn = this.modelBipedMain.isUnicorn = pony.isUnicorn;
        this.modelArmorChestplate.isUnicorn = this.modelBipedMain.isUnicorn;
        this.modelArmor.isPegasus = this.modelBipedMain.isPegasus = pony.isPegasus;
        this.modelArmorChestplate.isPegasus = this.modelBipedMain.isPegasus;
        double d3 = d1 - (double)pony.field_70129_M;
        if (pony.func_70093_af()) {
            d3 -= 0.125;
        }
        super.func_76986_a((EntityLiving)pony, d, d3, d2, f, f1);
        this.modelBipedMain.aimedBow = false;
        this.modelArmor.aimedBow = false;
        this.modelArmorChestplate.aimedBow = false;
        this.modelBipedMain.field_78093_q = false;
        this.modelArmor.field_78093_q = false;
        this.modelArmorChestplate.field_78093_q = false;
        this.modelBipedMain.isSneak = false;
        this.modelArmor.isSneak = false;
        this.modelArmorChestplate.isSneak = false;
        this.modelBipedMain.heldItemRight = 0;
        this.modelArmor.heldItemRight = 0;
        this.modelArmorChestplate.heldItemRight = 0;
    }

    protected void renderSpecials(EntityNpcPony entityplayer, float f) {
        super.func_77029_c((EntityLivingBase)entityplayer, f);
        if (!entityplayer.func_70608_bn()) {
            if (entityplayer.isUnicorn) {
                this.renderDrop(this.field_76990_c, entityplayer, this.modelBipedMain.unicornarm, 1.0f, 0.35f, 0.5375f, -0.45f);
            } else {
                this.renderDrop(this.field_76990_c, entityplayer, this.modelBipedMain.RightArm, 1.0f, -0.0625f, 0.8375f, 0.0625f);
            }
        }
    }

    protected void renderDrop(RenderManager rendermanager, EntityNpcPony entityplayer, ModelRenderer modelrenderer, float f, float f1, float f2, float f3) {
        ItemStack itemstack = entityplayer.func_70694_bm();
        if (itemstack == null) {
            return;
        }
        GL11.glPushMatrix();
        if (modelrenderer != null) {
            modelrenderer.func_78794_c(f * 0.0625f);
        }
        GL11.glTranslatef((float)f1, (float)f2, (float)f3);
        if (itemstack.func_77973_b() instanceof ItemBlock && RenderBlocks.func_147739_a((int)Block.func_149634_a((Item)itemstack.func_77973_b()).func_149645_b())) {
            GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)-0.3125f);
            GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            float f4 = 0.375f * f;
            GL11.glScalef((float)f4, (float)(-f4), (float)f4);
        } else if (itemstack.func_77973_b() instanceof ItemBow) {
            GL11.glTranslatef((float)0.0f, (float)0.125f, (float)0.3125f);
            GL11.glRotatef((float)-20.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            float f5 = 0.625f * f;
            GL11.glScalef((float)f5, (float)(-f5), (float)f5);
            GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        } else if (itemstack.func_77973_b().func_77662_d()) {
            if (itemstack.func_77973_b().func_77629_n_()) {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)0.0f, (float)-0.125f, (float)0.0f);
            }
            GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)0.0f);
            float f6 = 0.625f * f;
            GL11.glScalef((float)f6, (float)(-f6), (float)f6);
            GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        } else {
            GL11.glTranslatef((float)0.25f, (float)0.1875f, (float)-0.1875f);
            float f7 = 0.375f * f;
            GL11.glScalef((float)f7, (float)f7, (float)f7);
            GL11.glRotatef((float)60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        if (itemstack.func_77973_b() == Items.field_151068_bn) {
            for (int j = 0; j <= 1; ++j) {
                int k = itemstack.func_77973_b().func_82790_a(itemstack, j);
                float f9 = (float)(k >> 16 & 0xFF) / 255.0f;
                float f10 = (float)(k >> 8 & 0xFF) / 255.0f;
                float f12 = (float)(k & 0xFF) / 255.0f;
                GL11.glColor4f((float)f9, (float)f10, (float)f12, (float)1.0f);
                this.field_76990_c.field_78721_f.func_78443_a((EntityLivingBase)entityplayer, itemstack, j);
            }
        } else {
            rendermanager.field_78721_f.func_78443_a((EntityLivingBase)entityplayer, itemstack, 0);
        }
        GL11.glPopMatrix();
    }

    protected int func_77032_a(EntityLivingBase entityliving, int i, float f) {
        return this.setArmorModel((EntityNPCInterface)entityliving, i, f);
    }

    protected void func_77029_c(EntityLivingBase entityliving, float f) {
        this.renderSpecials((EntityNpcPony)entityliving, f);
    }

    @Override
    public void func_76986_a(EntityLiving entityliving, double d, double d1, double d2, float f, float f1) {
        this.renderPlayer((EntityNpcPony)entityliving, d, d1, d2, f, f1);
    }

    public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) {
        this.renderPlayer((EntityNpcPony)entity, d, d1, d2, f, f1);
    }
}

