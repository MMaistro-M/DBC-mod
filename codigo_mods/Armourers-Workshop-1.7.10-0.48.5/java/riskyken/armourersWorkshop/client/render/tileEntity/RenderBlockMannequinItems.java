/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderBiped
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render.tileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.model.ModelMannequin;
import riskyken.armourersWorkshop.client.render.MannequinFakePlayer;
import riskyken.armourersWorkshop.client.render.SkinModelRenderer;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;
import riskyken.armourersWorkshop.utils.UtilRender;

@SideOnly(value=Side.CLIENT)
public class RenderBlockMannequinItems {
    private RenderPlayer renderPlayer;
    private float scale = 0.0625f;

    public RenderBlockMannequinItems() {
        this.renderPlayer = (RenderPlayer)RenderManager.field_78727_a.field_78729_o.get(EntityPlayer.class);
    }

    public void renderHeadStack(MannequinFakePlayer fakePlayer, ItemStack stack, ModelBiped targetBiped, RenderManager rm, byte[] extraColours, double distance) {
        Item targetItem = stack.func_77973_b();
        if (SkinNBTHelper.stackHasSkinData(stack)) {
            SkinModelRenderer.INSTANCE.renderEquipmentPartFromStack(stack, targetBiped, extraColours, distance, true);
            return;
        }
        if (targetItem instanceof ItemBlock) {
            float blockScale = 0.5f;
            GL11.glRotated((double)Math.toDegrees(targetBiped.field_78116_c.field_78808_h), (double)0.0, (double)0.0, (double)1.0);
            GL11.glRotated((double)Math.toDegrees(targetBiped.field_78116_c.field_78796_g), (double)0.0, (double)1.0, (double)0.0);
            GL11.glRotated((double)Math.toDegrees(targetBiped.field_78116_c.field_78795_f), (double)1.0, (double)0.0, (double)0.0);
            GL11.glTranslatef((float)0.0f, (float)(-4.0f * this.scale), (float)0.0f);
            GL11.glScalef((float)(-blockScale), (float)(-blockScale), (float)blockScale);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            rm.field_78721_f.func_78443_a((EntityLivingBase)fakePlayer, stack, stack.func_77960_j());
        } else if (targetItem instanceof ItemArmor) {
            int passes = targetItem.getRenderPasses(stack.func_77960_j());
            for (int i = 0; i < passes; ++i) {
                ModelBiped armourBiped = ForgeHooksClient.getArmorModel((EntityLivingBase)fakePlayer, (ItemStack)stack, (int)0, (ModelBiped)this.renderPlayer.field_77108_b);
                if (i == 0) {
                    this.bindTexture(RenderBiped.getArmorResource((Entity)fakePlayer, (ItemStack)stack, (int)0, null));
                } else {
                    this.bindTexture(RenderBiped.getArmorResource((Entity)fakePlayer, (ItemStack)stack, (int)0, (String)"overlay"));
                }
                Color c = new Color(targetItem.func_82790_a(stack, i));
                GL11.glColor3f((float)((float)c.getRed() / 255.0f), (float)((float)c.getGreen() / 255.0f), (float)((float)c.getBlue() / 255.0f));
                armourBiped.field_78091_s = false;
                if (armourBiped == this.renderPlayer.field_77108_b) {
                    this.setRotations(targetBiped.field_78116_c, armourBiped.field_78116_c);
                    armourBiped.field_78116_c.field_78806_j = true;
                    armourBiped.field_78116_c.func_78785_a(this.scale);
                    this.resetRotations(targetBiped.field_78116_c);
                    continue;
                }
                try {
                    GL11.glRotated((double)Math.toDegrees(targetBiped.field_78116_c.field_78808_h), (double)0.0, (double)0.0, (double)1.0);
                    GL11.glRotated((double)Math.toDegrees(targetBiped.field_78116_c.field_78796_g), (double)0.0, (double)1.0, (double)0.0);
                    GL11.glRotated((double)Math.toDegrees(targetBiped.field_78116_c.field_78795_f), (double)1.0, (double)0.0, (double)0.0);
                    armourBiped.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, this.scale);
                    continue;
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }
    }

    public void renderChestStack(MannequinFakePlayer fakePlayer, ItemStack stack, ModelMannequin targetBiped, RenderManager rm, byte[] extraColours, double distance) {
        Item targetItem = stack.func_77973_b();
        if (SkinNBTHelper.stackHasSkinData(stack)) {
            SkinModelRenderer.INSTANCE.renderEquipmentPartFromStack(stack, targetBiped, extraColours, distance, true);
            return;
        }
        if (targetItem instanceof ItemArmor) {
            int passes = targetItem.getRenderPasses(stack.func_77960_j());
            for (int i = 0; i < passes; ++i) {
                ModelBiped armourBiped = ForgeHooksClient.getArmorModel((EntityLivingBase)fakePlayer, (ItemStack)stack, (int)1, (ModelBiped)this.renderPlayer.field_77108_b);
                if (i == 0) {
                    this.bindTexture(RenderBiped.getArmorResource((Entity)fakePlayer, (ItemStack)stack, (int)1, null));
                } else {
                    this.bindTexture(RenderBiped.getArmorResource((Entity)fakePlayer, (ItemStack)stack, (int)1, (String)"overlay"));
                }
                Color c = new Color(targetItem.func_82790_a(stack, i));
                GL11.glColor3f((float)((float)c.getRed() / 255.0f), (float)((float)c.getGreen() / 255.0f), (float)((float)c.getBlue() / 255.0f));
                armourBiped.field_78091_s = false;
                if (armourBiped == this.renderPlayer.field_77108_b) {
                    this.setRotations(targetBiped.field_78115_e, armourBiped.field_78115_e);
                    this.setRotations(targetBiped.field_78113_g, armourBiped.field_78113_g);
                    this.setRotations(targetBiped.field_78112_f, armourBiped.field_78112_f);
                    armourBiped.field_78115_e.field_78806_j = true;
                    armourBiped.field_78113_g.field_78806_j = true;
                    armourBiped.field_78112_f.field_78806_j = true;
                    armourBiped.field_78115_e.func_78785_a(this.scale);
                    armourBiped.field_78113_g.func_78785_a(this.scale);
                    armourBiped.field_78112_f.func_78785_a(this.scale);
                    this.resetRotations(targetBiped.field_78115_e);
                    this.resetRotations(targetBiped.field_78113_g);
                    this.resetRotations(targetBiped.field_78112_f);
                    armourBiped = ForgeHooksClient.getArmorModel((EntityLivingBase)fakePlayer, (ItemStack)stack, (int)1, (ModelBiped)this.renderPlayer.field_77111_i);
                    this.setRotations(targetBiped.field_78115_e, armourBiped.field_78115_e);
                    armourBiped.field_78115_e.field_78806_j = true;
                    armourBiped.field_78115_e.func_78785_a(this.scale);
                    this.resetRotations(targetBiped.field_78115_e);
                    continue;
                }
                try {
                    armourBiped.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, this.scale);
                    continue;
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }
    }

    public void renderLegsStack(MannequinFakePlayer fakePlayer, ItemStack stack, ModelBiped targetBiped, RenderManager rm, byte[] extraColours, double distance) {
        Item targetItem = stack.func_77973_b();
        if (SkinNBTHelper.stackHasSkinData(stack)) {
            SkinModelRenderer.INSTANCE.renderEquipmentPartFromStack(stack, targetBiped, extraColours, distance, true);
            return;
        }
        if (targetItem instanceof ItemArmor) {
            int passes = targetItem.getRenderPasses(stack.func_77960_j());
            for (int i = 0; i < passes; ++i) {
                ModelBiped armourBiped = ForgeHooksClient.getArmorModel((EntityLivingBase)fakePlayer, (ItemStack)stack, (int)2, (ModelBiped)this.renderPlayer.field_77111_i);
                if (i == 0) {
                    this.bindTexture(RenderBiped.getArmorResource((Entity)fakePlayer, (ItemStack)stack, (int)2, null));
                } else {
                    this.bindTexture(RenderBiped.getArmorResource((Entity)fakePlayer, (ItemStack)stack, (int)2, (String)"overlay"));
                }
                Color c = new Color(targetItem.func_82790_a(stack, i));
                GL11.glColor3f((float)((float)c.getRed() / 255.0f), (float)((float)c.getGreen() / 255.0f), (float)((float)c.getBlue() / 255.0f));
                armourBiped.field_78091_s = false;
                if (armourBiped == this.renderPlayer.field_77111_i) {
                    this.setRotations(targetBiped.field_78124_i, armourBiped.field_78124_i);
                    this.setRotations(targetBiped.field_78123_h, armourBiped.field_78123_h);
                    armourBiped.field_78115_e.field_78806_j = true;
                    armourBiped.field_78124_i.field_78806_j = true;
                    armourBiped.field_78123_h.field_78806_j = true;
                    armourBiped.field_78115_e.func_78785_a(this.scale);
                    armourBiped.field_78124_i.func_78785_a(this.scale);
                    armourBiped.field_78123_h.func_78785_a(this.scale);
                    this.resetRotations(armourBiped.field_78124_i);
                    this.resetRotations(armourBiped.field_78123_h);
                    continue;
                }
                try {
                    armourBiped.func_78088_a((Entity)fakePlayer, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, this.scale);
                    continue;
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }
    }

    public void renderFeetStack(MannequinFakePlayer fakePlayer, ItemStack stack, ModelBiped targetBiped, RenderManager rm, byte[] extraColours, double distance) {
        Item targetItem = stack.func_77973_b();
        if (SkinNBTHelper.stackHasSkinData(stack)) {
            SkinModelRenderer.INSTANCE.renderEquipmentPartFromStack(stack, targetBiped, extraColours, distance, true);
            return;
        }
        if (targetItem instanceof ItemArmor) {
            int passes = targetItem.getRenderPasses(stack.func_77960_j());
            for (int i = 0; i < passes; ++i) {
                ModelBiped armourBiped = ForgeHooksClient.getArmorModel((EntityLivingBase)fakePlayer, (ItemStack)stack, (int)3, (ModelBiped)this.renderPlayer.field_77108_b);
                if (i == 0) {
                    this.bindTexture(RenderBiped.getArmorResource((Entity)fakePlayer, (ItemStack)stack, (int)3, null));
                } else {
                    this.bindTexture(RenderBiped.getArmorResource((Entity)fakePlayer, (ItemStack)stack, (int)3, (String)"overlay"));
                }
                Color c = new Color(targetItem.func_82790_a(stack, i));
                GL11.glColor3f((float)((float)c.getRed() / 255.0f), (float)((float)c.getGreen() / 255.0f), (float)((float)c.getBlue() / 255.0f));
                armourBiped.field_78091_s = false;
                if (armourBiped == this.renderPlayer.field_77108_b) {
                    this.setRotations(targetBiped.field_78124_i, armourBiped.field_78124_i);
                    this.setRotations(targetBiped.field_78123_h, armourBiped.field_78123_h);
                    armourBiped.field_78124_i.field_78806_j = true;
                    armourBiped.field_78123_h.field_78806_j = true;
                    armourBiped.field_78124_i.func_78785_a(this.scale);
                    armourBiped.field_78123_h.func_78785_a(this.scale);
                    this.resetRotations(armourBiped.field_78124_i);
                    this.resetRotations(armourBiped.field_78123_h);
                    continue;
                }
                try {
                    armourBiped.func_78088_a((Entity)fakePlayer, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, this.scale);
                    continue;
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }
    }

    public void renderRightArmStack(MannequinFakePlayer fakePlayer, ItemStack stack, ModelBiped targetBiped, RenderManager rm, byte[] extraColours, double distance) {
        SkinPointer sp;
        Item targetItem = stack.func_77973_b();
        float blockScale = 0.5f;
        float itemScale = 0.6666666f;
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glTranslatef((float)(-5.0f * this.scale), (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)(2.0f * this.scale), (float)0.0f);
        GL11.glRotated((double)Math.toDegrees(targetBiped.field_78112_f.field_78808_h), (double)0.0, (double)0.0, (double)1.0);
        GL11.glRotated((double)Math.toDegrees(targetBiped.field_78112_f.field_78796_g), (double)0.0, (double)1.0, (double)0.0);
        GL11.glRotated((double)Math.toDegrees(targetBiped.field_78112_f.field_78795_f), (double)1.0, (double)0.0, (double)0.0);
        GL11.glTranslatef((float)(-2.0f * this.scale), (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)(10.0f * this.scale), (float)0.0f);
        if (SkinNBTHelper.stackHasSkinData(stack) && (sp = SkinNBTHelper.getSkinPointerFromStack(stack)).getIdentifier().getSkinType() == SkinTypeRegistry.skinSword | sp.getIdentifier().getSkinType() == SkinTypeRegistry.skinBow) {
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslated((double)(1.0f * this.scale), (double)(0.0f * this.scale), (double)(2.0f * this.scale));
            SkinModelRenderer.INSTANCE.renderEquipmentPartFromStack(stack, null, extraColours, distance, true);
            return;
        }
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)45.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glScalef((float)itemScale, (float)itemScale, (float)itemScale);
        GL11.glRotatef((float)-335.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)-50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        if (targetItem instanceof ItemBlock) {
            GL11.glTranslatef((float)(-3.0f * this.scale), (float)(4.0f * this.scale), (float)(2.0f * this.scale));
            GL11.glScalef((float)(-blockScale), (float)(-blockScale), (float)blockScale);
            GL11.glRotatef((float)30.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)130.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        } else if (!(targetItem instanceof ItemSword)) {
            GL11.glScalef((float)0.75f, (float)0.75f, (float)0.75f);
        }
        if (targetItem.func_77623_v()) {
            int passes = targetItem.getRenderPasses(stack.func_77960_j());
            for (int i = 0; i < passes; ++i) {
                int c = targetItem.func_82790_a(stack, i);
                float r = (float)(c >> 16 & 0xFF) / 255.0f;
                float g = (float)(c >> 8 & 0xFF) / 255.0f;
                float b = (float)(c & 0xFF) / 255.0f;
                GL11.glColor4f((float)r, (float)g, (float)b, (float)1.0f);
                rm.field_78721_f.renderItem((EntityLivingBase)fakePlayer, stack, i, IItemRenderer.ItemRenderType.EQUIPPED);
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        } else {
            int c = targetItem.func_82790_a(stack, 0);
            float r = (float)(c >> 16 & 0xFF) / 255.0f;
            float g = (float)(c >> 8 & 0xFF) / 255.0f;
            float b = (float)(c & 0xFF) / 255.0f;
            GL11.glColor4f((float)r, (float)g, (float)b, (float)1.0f);
            rm.field_78721_f.renderItem((EntityLivingBase)fakePlayer, stack, 0, IItemRenderer.ItemRenderType.EQUIPPED);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    public void renderLeftArmStack(MannequinFakePlayer fakePlayer, ItemStack stack, ModelBiped targetBiped, RenderManager rm, byte[] extraColours, double distance) {
        SkinPointer sp;
        Item targetItem = stack.func_77973_b();
        float blockScale = 0.5f;
        float itemScale = 0.6666666f;
        GL11.glTranslatef((float)(5.0f * this.scale), (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)(2.0f * this.scale), (float)0.0f);
        GL11.glRotated((double)Math.toDegrees(targetBiped.field_78113_g.field_78808_h), (double)0.0, (double)0.0, (double)1.0);
        GL11.glRotated((double)Math.toDegrees(targetBiped.field_78113_g.field_78796_g), (double)0.0, (double)1.0, (double)0.0);
        GL11.glRotated((double)Math.toDegrees(targetBiped.field_78113_g.field_78795_f), (double)1.0, (double)0.0, (double)0.0);
        GL11.glTranslatef((float)(1.0f * this.scale), (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)(10.0f * this.scale), (float)0.0f);
        if (SkinNBTHelper.stackHasSkinData(stack) && (sp = SkinNBTHelper.getSkinPointerFromStack(stack)).getIdentifier().getSkinType() == SkinTypeRegistry.skinSword | sp.getIdentifier().getSkinType() == SkinTypeRegistry.skinBow) {
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslated((double)(0.0f * this.scale), (double)(0.0f * this.scale), (double)(2.0f * this.scale));
            GL11.glScalef((float)-1.0f, (float)1.0f, (float)1.0f);
            GL11.glCullFace((int)1028);
            SkinModelRenderer.INSTANCE.renderEquipmentPartFromStack(stack, null, extraColours, distance, true);
            GL11.glCullFace((int)1029);
            return;
        }
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)45.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glScalef((float)itemScale, (float)itemScale, (float)itemScale);
        GL11.glRotatef((float)-335.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)-50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        if (targetItem instanceof ItemBlock) {
            GL11.glTranslatef((float)(-2.0f * this.scale), (float)(4.0f * this.scale), (float)(2.0f * this.scale));
            GL11.glScalef((float)(-blockScale), (float)(-blockScale), (float)blockScale);
            GL11.glRotatef((float)50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)130.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        } else if (!(targetItem instanceof ItemSword)) {
            GL11.glScalef((float)0.75f, (float)0.75f, (float)0.75f);
        }
        if (targetItem.func_77623_v()) {
            int passes = targetItem.getRenderPasses(stack.func_77960_j());
            for (int i = 0; i < passes; ++i) {
                int c = targetItem.func_82790_a(stack, i);
                float r = (float)(c >> 16 & 0xFF) / 255.0f;
                float g = (float)(c >> 8 & 0xFF) / 255.0f;
                float b = (float)(c & 0xFF) / 255.0f;
                GL11.glColor4f((float)r, (float)g, (float)b, (float)1.0f);
                rm.field_78721_f.renderItem((EntityLivingBase)fakePlayer, stack, i, IItemRenderer.ItemRenderType.EQUIPPED);
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        } else {
            int c = targetItem.func_82790_a(stack, 0);
            float r = (float)(c >> 16 & 0xFF) / 255.0f;
            float g = (float)(c >> 8 & 0xFF) / 255.0f;
            float b = (float)(c & 0xFF) / 255.0f;
            GL11.glColor4f((float)r, (float)g, (float)b, (float)1.0f);
            rm.field_78721_f.renderItem((EntityLivingBase)fakePlayer, stack, 0, IItemRenderer.ItemRenderType.EQUIPPED);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    public void renderWingsStack(MannequinFakePlayer fakePlayer, ItemStack stack, ModelBiped targetBiped, RenderManager rm, byte[] extraColours, double distance) {
        SkinPointer sp;
        Item targetItem = stack.func_77973_b();
        if (SkinNBTHelper.stackHasSkinData(stack) && (sp = SkinNBTHelper.getSkinPointerFromStack(stack)).getIdentifier().getSkinType() == SkinTypeRegistry.skinWings) {
            SkinModelRenderer.INSTANCE.renderEquipmentPartFromStack((Entity)fakePlayer, stack, null, extraColours, distance, true);
            return;
        }
    }

    private void bindTexture(ResourceLocation resourceLocation) {
        UtilRender.bindTexture(resourceLocation);
    }

    private void setRotations(ModelRenderer des, ModelRenderer src) {
        des.field_78795_f = src.field_78795_f;
        des.field_78796_g = src.field_78796_g;
        des.field_78808_h = src.field_78808_h;
    }

    private void resetRotations(ModelRenderer des) {
        des.field_78795_f = 0.0f;
        des.field_78796_g = 0.0f;
        des.field_78808_h = 0.0f;
    }
}

