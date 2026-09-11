/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.tobiasmjc.dbcadditions.client.gui;

import com.tobiasmjc.dbcadditions.inventory.ContainerDBCAPlayer;
import com.tobiasmjc.dbcadditions.inventory.InventoryDBCAPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiContainerDBCA
extends GuiContainer {
    private float xSize_lo;
    private float ySize_lo;

    public GuiContainerDBCA(EntityPlayer player, InventoryPlayer inventoryPlayer, InventoryDBCAPlayer inventoryCustom) {
        super((Container)new ContainerDBCAPlayer(inventoryCustom, player));
    }

    public void func_73863_a(int mouseX, int mouseY, float f) {
        super.func_73863_a(mouseX, mouseY, f);
        this.xSize_lo = mouseX;
        this.ySize_lo = mouseY;
    }

    protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(new ResourceLocation("dbcadditions:gui/inventory.png"));
        this.func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
        this.drawEntityOnScreen(this.field_147003_i + 51, this.field_147009_r + 75, 30, (float)(this.field_147003_i + 51) - this.xSize_lo, (float)(this.field_147009_r + 25) - this.ySize_lo, this.field_146297_k.field_71439_g);
    }

    private void drawEntityOnScreen(int i, int j, int k, float f, float g, EntityClientPlayerMP thePlayer) {
        GuiInventory.func_147046_a((int)(this.field_147003_i + 51), (int)(this.field_147009_r + 75), (int)30, (float)((float)(this.field_147003_i + 51) - this.xSize_lo), (float)((float)(this.field_147009_r + 25) - this.ySize_lo), (EntityLivingBase)this.field_146297_k.field_71439_g);
    }
}

