/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.RenderBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  net.minecraftforge.client.MinecraftForgeClient
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import noppes.npcs.client.model.ModelMPM;
import noppes.npcs.client.model.ModelNPCMale;
import noppes.npcs.client.renderer.RenderNPCInterface;
import noppes.npcs.constants.EnumAnimation;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public class RenderNPCHumanMale
extends RenderNPCInterface {
    private ModelNPCMale modelBipedMain;
    protected ModelNPCMale modelArmorChestplate;
    protected ModelNPCMale modelArmor;
    protected final ModelNPCMale originalBipedMain;
    protected static final ModelMPM steve64 = new ModelMPM(0.0f, false);
    protected static final ModelMPM alex = new ModelMPM(0.0f, true);
    protected static final ModelMPM steveArmorChest = new ModelMPM(1.0f, 0);
    protected static final ModelMPM steveArmor = new ModelMPM(0.5f, 0);
    protected static final ModelMPM alex32armorChest = new ModelMPM(1.0f, 1);
    protected static final ModelMPM alex32armor = new ModelMPM(0.5f, 1);

    public RenderNPCHumanMale(ModelNPCMale mainmodel, ModelNPCMale armorChest, ModelNPCMale armor) {
        super((ModelBase)mainmodel, 0.5f);
        this.modelBipedMain = mainmodel;
        this.originalBipedMain = mainmodel;
        this.modelArmorChestplate = armorChest;
        this.modelArmor = armor;
    }

    protected int func_130006_a(EntityLiving par1EntityLiving, int par2, float par3) {
        Item item;
        ItemStack itemstack = par1EntityLiving.func_130225_q(3 - par2);
        if (itemstack != null && (item = itemstack.func_77973_b()) instanceof ItemArmor) {
            ItemArmor itemarmor = (ItemArmor)item;
            this.func_110776_a(RenderBiped.getArmorResource((Entity)par1EntityLiving, (ItemStack)itemstack, (int)par2, null));
            ModelNPCMale modelbiped = par2 == 2 ? this.modelArmor : this.modelArmorChestplate;
            modelbiped.field_78116_c.field_78806_j = par2 == 0;
            modelbiped.field_78114_d.field_78806_j = par2 == 0;
            modelbiped.field_78115_e.field_78806_j = par2 == 1 || par2 == 2;
            modelbiped.field_78112_f.field_78806_j = par2 == 1;
            modelbiped.field_78113_g.field_78806_j = par2 == 1;
            modelbiped.field_78123_h.field_78806_j = par2 == 2 || par2 == 3;
            modelbiped.field_78124_i.field_78806_j = par2 == 2 || par2 == 3;
            modelbiped = ForgeHooksClient.getArmorModel((EntityLivingBase)par1EntityLiving, (ItemStack)itemstack, (int)par2, (ModelBiped)modelbiped);
            this.func_77042_a((ModelBase)modelbiped);
            modelbiped.field_78095_p = this.field_77045_g.field_78095_p;
            modelbiped.field_78093_q = this.field_77045_g.field_78093_q;
            modelbiped.field_78091_s = this.field_77045_g.field_78091_s;
            float f1 = 1.0f;
            int j = itemarmor.func_82814_b(itemstack);
            if (j != -1) {
                float f2 = (float)(j >> 16 & 0xFF) / 255.0f;
                float f3 = (float)(j >> 8 & 0xFF) / 255.0f;
                float f4 = (float)(j & 0xFF) / 255.0f;
                GL11.glColor3f((float)(f1 * f2), (float)(f1 * f3), (float)(f1 * f4));
                if (itemstack.func_77948_v()) {
                    return 31;
                }
                return 16;
            }
            GL11.glColor3f((float)f1, (float)f1, (float)f1);
            if (itemstack.func_77948_v()) {
                return 15;
            }
            return 1;
        }
        return -1;
    }

    protected int func_77032_a(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
        return this.func_130006_a((EntityLiving)par1EntityLivingBase, par2, par3);
    }

    public void renderPlayer(EntityNPCInterface npc, double d, double d1, double d2, float f, float f1) {
        ItemStack itemstack;
        if (npc instanceof EntityCustomNpc) {
            EntityCustomNpc test = (EntityCustomNpc)npc;
            if (test.modelData.entityClass == null) {
                int modelVal = npc.display.modelType;
                if (modelVal == 1) {
                    this.field_77045_g = steve64;
                    this.modelBipedMain = steve64;
                    this.modelArmorChestplate = steveArmorChest;
                    this.modelArmor = steveArmor;
                } else if (modelVal == 2) {
                    this.field_77045_g = alex;
                    this.modelBipedMain = alex;
                    this.modelArmorChestplate = alex32armorChest;
                    this.modelArmor = alex32armor;
                } else {
                    ((EntityCustomNpc)npc).modelData.bodywear = 0;
                    ((EntityCustomNpc)npc).modelData.armwear = 0;
                    ((EntityCustomNpc)npc).modelData.legwear = 0;
                    this.field_77045_g = this.originalModel;
                    this.modelBipedMain = this.originalBipedMain;
                    this.modelArmorChestplate = steveArmorChest;
                    this.modelArmor = steveArmor;
                }
            }
        }
        this.modelBipedMain.field_78120_m = (itemstack = npc.func_70694_bm()) == null ? 0 : (npc.field_70172_ad > 0 ? 3 : 1);
        this.modelArmor.field_78120_m = this.modelBipedMain.field_78120_m;
        this.modelArmorChestplate.field_78120_m = this.modelBipedMain.field_78120_m;
        this.modelBipedMain.field_78119_l = npc.getOffHand() == null ? 0 : (npc.field_70172_ad > 0 ? 3 : 1);
        this.modelArmor.field_78119_l = this.modelBipedMain.field_78119_l;
        this.modelArmorChestplate.field_78119_l = this.modelBipedMain.field_78119_l;
        this.modelArmor.field_78117_n = this.modelBipedMain.field_78117_n = npc.func_70093_af();
        this.modelArmorChestplate.field_78117_n = this.modelBipedMain.field_78117_n;
        this.modelArmor.isSleeping = this.modelBipedMain.isSleeping = npc.func_70608_bn();
        this.modelArmorChestplate.isSleeping = this.modelBipedMain.isSleeping;
        this.modelBipedMain.isDancing = npc.currentAnimation == EnumAnimation.DANCING;
        this.modelArmor.isDancing = this.modelBipedMain.isDancing;
        this.modelArmorChestplate.isDancing = this.modelBipedMain.isDancing;
        this.modelBipedMain.field_78118_o = npc.currentAnimation == EnumAnimation.AIMING;
        this.modelArmor.field_78118_o = this.modelBipedMain.field_78118_o;
        this.modelArmorChestplate.field_78118_o = this.modelBipedMain.field_78118_o;
        this.modelArmor.field_78093_q = this.modelBipedMain.field_78093_q = npc.func_70115_ae();
        this.modelArmorChestplate.field_78093_q = this.modelBipedMain.field_78093_q;
        double d3 = d1 - (double)npc.field_70129_M;
        if (npc.func_70093_af()) {
            d3 -= 0.125;
        }
        super.func_76986_a((EntityLiving)npc, d, d3, d2, f, f1);
        this.modelBipedMain.field_78118_o = false;
        this.modelArmor.field_78118_o = false;
        this.modelArmorChestplate.field_78118_o = false;
        this.modelBipedMain.field_78117_n = false;
        this.modelArmor.field_78117_n = false;
        this.modelArmorChestplate.field_78117_n = false;
        this.modelBipedMain.field_78120_m = 0;
        this.modelArmor.field_78120_m = 0;
        this.modelArmorChestplate.field_78120_m = 0;
        this.modelBipedMain.field_78119_l = 0;
        this.modelArmor.field_78119_l = 0;
        this.modelArmorChestplate.field_78119_l = 0;
    }

    protected void renderSpecials(EntityNPCInterface npc, float f) {
        float var10;
        float var9;
        float var26;
        int var24;
        int var25;
        boolean pluginMod;
        Class<?> clazz;
        boolean is3D;
        IItemRenderer customRenderer;
        super.func_77029_c((EntityLivingBase)npc, f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        int i = npc.func_70070_b(f);
        int j = i % 65536;
        int k = i / 65536;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)j / 1.0f), (float)((float)k / 1.0f));
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        ItemStack itemstack = npc.inventory.armorItemInSlot(0);
        if (itemstack != null) {
            boolean is3D2;
            GL11.glPushMatrix();
            if (npc instanceof EntityCustomNpc) {
                EntityCustomNpc cnpc = (EntityCustomNpc)npc;
                GL11.glTranslatef((float)0.0f, (float)cnpc.modelData.getBodyY(), (float)0.0f);
                this.modelBipedMain.field_78116_c.func_78794_c(0.0625f);
                GL11.glScalef((float)cnpc.modelData.modelScale.head.scaleX, (float)cnpc.modelData.modelScale.head.scaleY, (float)cnpc.modelData.modelScale.head.scaleZ);
            } else {
                this.modelBipedMain.field_78116_c.func_78794_c(0.0625f);
            }
            IItemRenderer customRenderer2 = MinecraftForgeClient.getItemRenderer((ItemStack)itemstack, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
            boolean bl = is3D2 = customRenderer2 != null && customRenderer2.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D);
            if (itemstack.func_77973_b() instanceof ItemBlock) {
                if (is3D2 || RenderBlocks.func_147739_a((int)Block.func_149634_a((Item)itemstack.func_77973_b()).func_149645_b())) {
                    float var6 = 0.625f;
                    GL11.glTranslatef((float)0.0f, (float)-0.25f, (float)0.0f);
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glScalef((float)var6, (float)(-var6), (float)(-var6));
                }
                this.field_76990_c.field_78721_f.func_78443_a((EntityLivingBase)npc, itemstack, 0);
            }
            GL11.glPopMatrix();
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        ItemStack itemstack2 = npc.func_70694_bm();
        if (itemstack2 != null) {
            float var6;
            GL11.glPushMatrix();
            float y = 0.0f;
            float x = 0.0f;
            if (npc instanceof EntityCustomNpc) {
                EntityCustomNpc cnpc = (EntityCustomNpc)npc;
                y = (cnpc.modelData.modelScale.arms.scaleY - 1.0f) * 0.7f;
                x = (1.0f - cnpc.modelData.modelScale.body.scaleX) * 0.28f + (1.0f - cnpc.modelData.modelScale.arms.scaleX) * 0.175f;
                GL11.glTranslatef((float)x, (float)cnpc.modelData.getBodyY(), (float)0.0f);
            }
            this.modelBipedMain.field_78112_f.func_78794_c(0.0625f);
            if (npc.getModelType() == 2) {
                GL11.glTranslatef((float)-0.0125f, (float)(0.4375f + y), (float)0.0625f);
            } else {
                GL11.glTranslatef((float)-0.0625f, (float)(0.4375f + y), (float)0.0625f);
            }
            customRenderer = MinecraftForgeClient.getItemRenderer((ItemStack)itemstack2, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
            is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack2, IItemRenderer.ItemRendererHelper.BLOCK_3D);
            clazz = itemstack2.func_77973_b().getClass();
            pluginMod = itemstack2.func_77973_b().func_77658_a().contains("plug:");
            if (itemstack2.func_77973_b() instanceof ItemBlock && (is3D || RenderBlocks.func_147739_a((int)Block.func_149634_a((Item)itemstack2.func_77973_b()).func_149645_b()))) {
                var6 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)-0.3125f);
                GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)(-(var6 *= 0.75f)), (float)(-var6), (float)var6);
            } else if (itemstack2.func_77973_b() != null && itemstack2.func_77973_b() == Items.field_151031_f || itemstack2.func_77973_b() instanceof ItemBow && (customRenderer == null || pluginMod)) {
                var6 = 0.625f;
                GL11.glTranslatef((float)0.0f, (float)0.125f, (float)0.3125f);
                GL11.glRotatef((float)-20.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)var6, (float)(-var6), (float)var6);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else if (itemstack2.func_77973_b().func_77662_d() && !is3D) {
                var6 = 0.625f;
                if (itemstack2.func_77973_b().func_77629_n_()) {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)0.0f, (float)-0.125f, (float)0.0f);
                }
                if (npc.field_70172_ad > 0 && npc.stats.resistances.playermelee > 1.0f) {
                    GL11.glTranslatef((float)0.05f, (float)0.0f, (float)-0.1f);
                    GL11.glRotatef((float)-50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)-10.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glRotatef((float)-60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                }
                GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)0.0f);
                GL11.glScalef((float)var6, (float)(-var6), (float)var6);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else if (is3D) {
                var6 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)-0.3125f);
                GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)(-(var6 *= 0.75f)), (float)(-var6), (float)var6);
            } else {
                var6 = 0.375f;
                GL11.glTranslatef((float)0.25f, (float)0.1875f, (float)-0.1875f);
                GL11.glScalef((float)var6, (float)var6, (float)var6);
                GL11.glRotatef((float)60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            if (itemstack2.func_77973_b().func_77623_v()) {
                for (var25 = 0; var25 < itemstack2.func_77973_b().getRenderPasses(itemstack2.func_77960_j()); ++var25) {
                    var24 = itemstack2.func_77973_b().func_82790_a(itemstack2, var25);
                    var26 = (float)(var24 >> 16 & 0xFF) / 255.0f;
                    var9 = (float)(var24 >> 8 & 0xFF) / 255.0f;
                    var10 = (float)(var24 & 0xFF) / 255.0f;
                    GL11.glColor4f((float)var26, (float)var9, (float)var10, (float)1.0f);
                    this.field_76990_c.field_78721_f.func_78443_a((EntityLivingBase)npc, itemstack2, var25);
                }
            } else {
                this.field_76990_c.field_78721_f.func_78443_a((EntityLivingBase)npc, itemstack2, 0);
            }
            GL11.glPopMatrix();
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        itemstack2 = npc.getOffHand();
        if (itemstack2 != null) {
            float var6;
            GL11.glPushMatrix();
            float y = 0.0f;
            float x = 0.0f;
            if (npc instanceof EntityCustomNpc) {
                EntityCustomNpc cnpc = (EntityCustomNpc)npc;
                y = (cnpc.modelData.modelScale.arms.scaleY - 1.0f) * 0.7f;
                x = (1.0f - cnpc.modelData.modelScale.body.scaleX) * -0.28f + (1.0f - cnpc.modelData.modelScale.arms.scaleX) * -0.175f;
                GL11.glTranslatef((float)x, (float)cnpc.modelData.getBodyY(), (float)0.0f);
            }
            this.modelBipedMain.field_78113_g.func_78794_c(0.0625f);
            if (npc.getModelType() == 2) {
                GL11.glTranslatef((float)0.0125f, (float)(0.4375f + y), (float)0.0625f);
            } else {
                GL11.glTranslatef((float)0.0625f, (float)(0.4375f + y), (float)0.0625f);
            }
            customRenderer = MinecraftForgeClient.getItemRenderer((ItemStack)itemstack2, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
            is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack2, IItemRenderer.ItemRendererHelper.BLOCK_3D);
            clazz = itemstack2.func_77973_b().getClass();
            pluginMod = itemstack2.func_77973_b().func_77658_a().contains("plug:");
            if (clazz.getSimpleName().equals("ItemShield") || clazz.getSimpleName().equals("ItemRotatedShield") || clazz.getSimpleName().equals("ItemClaw")) {
                GL11.glTranslatef((float)0.3f, (float)0.0f, (float)0.0f);
            }
            if (itemstack2.func_77973_b() instanceof ItemBlock && (is3D || RenderBlocks.func_147739_a((int)Block.func_149634_a((Item)itemstack2.func_77973_b()).func_149645_b()))) {
                var6 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)-0.3125f);
                GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)(var6 *= 0.75f), (float)(-var6), (float)var6);
            } else if (itemstack2.func_77973_b() != null && itemstack2.func_77973_b() == Items.field_151031_f || itemstack2.func_77973_b() instanceof ItemBow && (customRenderer == null || pluginMod)) {
                var6 = 0.625f;
                GL11.glTranslatef((float)0.0f, (float)0.125f, (float)0.3125f);
                GL11.glRotatef((float)-20.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)var6, (float)(-var6), (float)var6);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else if (itemstack2.func_77973_b().func_77662_d() && !is3D) {
                var6 = 0.625f;
                if (itemstack2.func_77973_b().func_77629_n_()) {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)0.0f, (float)-0.125f, (float)0.0f);
                }
                if (npc.field_70172_ad > 0 && npc.stats.resistances.arrow > 1.0f) {
                    GL11.glTranslatef((float)0.05f, (float)0.0f, (float)-0.1f);
                    GL11.glRotatef((float)50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)-10.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glRotatef((float)60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                }
                GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)0.0f);
                GL11.glScalef((float)var6, (float)(-var6), (float)var6);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else {
                var6 = 0.375f;
                GL11.glTranslatef((float)0.25f, (float)0.1875f, (float)-0.1875f);
                GL11.glScalef((float)var6, (float)var6, (float)var6);
                GL11.glRotatef((float)60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            if (itemstack2.func_77973_b().func_77623_v()) {
                for (var25 = 0; var25 < itemstack2.func_77973_b().getRenderPasses(itemstack2.func_77960_j()); ++var25) {
                    var24 = itemstack2.func_77973_b().func_82790_a(itemstack2, var25);
                    var26 = (float)(var24 >> 16 & 0xFF) / 255.0f;
                    var9 = (float)(var24 >> 8 & 0xFF) / 255.0f;
                    var10 = (float)(var24 & 0xFF) / 255.0f;
                    GL11.glColor4f((float)var26, (float)var9, (float)var10, (float)1.0f);
                    this.field_76990_c.field_78721_f.func_78443_a((EntityLivingBase)npc, itemstack2, var25);
                }
            } else {
                this.field_76990_c.field_78721_f.func_78443_a((EntityLivingBase)npc, itemstack2, 0);
            }
            GL11.glPopMatrix();
        }
    }

    protected void func_77029_c(EntityLivingBase entityliving, float f) {
        this.renderSpecials((EntityNPCInterface)entityliving, f);
    }

    @Override
    public void func_76986_a(EntityLiving entityliving, double d, double d1, double d2, float f, float f1) {
        this.renderPlayer((EntityNPCInterface)entityliving, d, d1, d2, f, f1);
    }

    public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) {
        this.renderPlayer((EntityNPCInterface)entity, d, d1, d2, f, f1);
    }
}

