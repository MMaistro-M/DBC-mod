/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui;

import java.awt.Color;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.client.render.SkinItemRenderHelper;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.common.inventory.ContainerDyeTable;
import riskyken.armourersWorkshop.common.inventory.slot.SlotDyeBottle;
import riskyken.armourersWorkshop.common.skin.data.SkinDye;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.tileentities.TileEntityDyeTable;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class GuiDyeTable
extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/dyeTable.png");
    private final TileEntityDyeTable tileEntity;
    private SkinDye[] rolloverDyes;

    public GuiDyeTable(InventoryPlayer invPlayer, TileEntityDyeTable tileEntity) {
        super((Container)new ContainerDyeTable(invPlayer, tileEntity));
        this.tileEntity = tileEntity;
        this.field_146999_f = 320;
        this.field_147000_g = 190;
        this.rolloverDyes = new SkinDye[8];
        for (int i = 0; i < 8; ++i) {
            this.rolloverDyes[i] = new SkinDye();
            for (int j = 0; j < 8; ++j) {
                this.rolloverDyes[i].addDye(j, new byte[]{-1, -1, -1, 0});
            }
            this.rolloverDyes[i].addDye(i, new byte[]{-1, -1, -1, -1});
        }
    }

    public void func_73866_w_() {
        super.func_73866_w_();
    }

    protected void func_146976_a(float f1, int mouseX, int mouseY) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(texture);
        this.func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, 256, this.field_147000_g);
        this.func_73729_b(this.field_147003_i + 182 + 56, this.field_147009_r, 174, 0, 82, this.field_147000_g);
        if (ConfigHandler.lockDyesOnSkins) {
            ModRenderHelper.enableAlphaBlend();
            for (int i = 0; i < 8; ++i) {
                SlotDyeBottle dyeSlot = (SlotDyeBottle)this.field_147002_h.func_75139_a(37 + i);
                if (!dyeSlot.isLocked()) continue;
                GuiDyeTable.func_73734_a((int)(this.field_147003_i + dyeSlot.field_75223_e), (int)(this.field_147009_r + dyeSlot.field_75221_f), (int)(this.field_147003_i + dyeSlot.field_75223_e + 16), (int)(this.field_147009_r + dyeSlot.field_75221_f + 16), (int)-1996554240);
            }
        }
    }

    protected void func_146979_b(int mouseX, int mouseY) {
        GuiHelper.renderLocalizedGuiName(this.field_146289_q, this.field_146999_f, this.tileEntity.func_145825_b());
        this.field_146289_q.func_78276_b(I18n.func_135052_a((String)"container.inventory", (Object[])new Object[0]), 46, this.field_147000_g - 96 + 2, 0x404040);
        Slot slot = (Slot)this.field_147002_h.field_75151_b.get(45);
        ItemStack skinStack = slot.func_75211_c();
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(skinStack);
        if (skinPointer != null) {
            GL11.glPushMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderHelper.func_74519_b();
            float boxX = 242.5f;
            float boxY = 102.0f;
            float scale = 11.0f;
            GL11.glTranslatef((float)boxX, (float)boxY, (float)500.0f);
            GL11.glScalef((float)(-scale), (float)scale, (float)scale);
            float rotation = (float)((double)System.currentTimeMillis() / 10.0 % 360.0);
            float fade = (float)((double)System.currentTimeMillis() / 400.0 % Math.PI * 2.0);
            float change = (float)Math.sin(fade);
            float alpha = change * 50.0f;
            GL11.glRotatef((float)-20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
            GuiDyeTable.func_73734_a((int)this.field_147003_i, (int)this.field_147009_r, (int)(this.field_147003_i + 50), (int)(this.field_147009_r + 50), (int)-1);
            int dyeSlot = this.mouseOverDyeSlot(mouseX, mouseY);
            dyeSlot = -1;
            if (dyeSlot != -1) {
                GL11.glPushMatrix();
                SkinItemRenderHelper.renderSkinAsItem(skinPointer, true, false, 140, 176);
                GL11.glPopMatrix();
                GL11.glPopMatrix();
                Color c = new Color(198, 198, 198, 240);
                RenderHelper.func_74518_a();
                GL11.glDisable((int)2929);
                GuiDyeTable.func_73734_a((int)152, (int)20, (int)250, (int)95, (int)c.getRGB());
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glEnable((int)2929);
                RenderHelper.func_74519_b();
                GL11.glPushMatrix();
                GL11.glTranslatef((float)boxX, (float)boxY, (float)200.0f);
                GL11.glScalef((float)(-scale), (float)scale, (float)scale);
                GL11.glRotatef((float)-20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
                for (int i = 0; i < 8; ++i) {
                    if (i == dyeSlot) continue;
                    skinPointer.getSkinDye().addDye(i, this.rolloverDyes[dyeSlot].getDyeColour(i));
                }
                GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
                SkinItemRenderHelper.renderSkinAsItem(skinPointer, true, false, 140, 176);
            } else {
                ModRenderHelper.enableAlphaBlend();
                SkinItemRenderHelper.renderSkinAsItem(skinPointer, true, false, 140, 176);
            }
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)2929);
    }

    private int mouseOverDyeSlot(int mouseX, int mouseY) {
        for (int i = 0; i < 8; ++i) {
            Slot slot = (Slot)this.field_147002_h.field_75151_b.get(37 + i);
            if (!(mouseX - this.field_147003_i >= slot.field_75223_e & mouseX - this.field_147003_i <= slot.field_75223_e + 16) || !(mouseY - this.field_147009_r >= slot.field_75221_f & mouseY - this.field_147009_r <= slot.field_75221_f + 16)) continue;
            return i;
        }
        return -1;
    }
}

