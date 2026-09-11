/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import noppes.npcs.client.ClientEventHandler;
import org.lwjgl.opengl.GL11;

public class RenderCNPCHand
extends ItemRenderer {
    private Minecraft mc;
    private ItemStack itemToRender;
    private float equippedProgress;
    private float prevEquippedProgress;
    private int equippedItemSlot = -1;

    public RenderCNPCHand(Minecraft mc) {
        super(mc);
        this.mc = mc;
    }

    public void renderOverlayInFirstPerson(float partialTickTime) {
        float f1 = this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * partialTickTime;
        EntityClientPlayerMP entityclientplayermp = this.mc.field_71439_g;
        float f2 = entityclientplayermp.field_70127_C + (entityclientplayermp.field_70125_A - entityclientplayermp.field_70127_C) * partialTickTime;
        GL11.glPushMatrix();
        GL11.glRotatef((float)f2, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)(entityclientplayermp.field_70126_B + (entityclientplayermp.field_70177_z - entityclientplayermp.field_70126_B) * partialTickTime), (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        GL11.glPopMatrix();
        float f3 = entityclientplayermp.field_71164_i + (entityclientplayermp.field_71155_g - entityclientplayermp.field_71164_i) * partialTickTime;
        float f4 = entityclientplayermp.field_71163_h + (entityclientplayermp.field_71154_f - entityclientplayermp.field_71163_h) * partialTickTime;
        GL11.glRotatef((float)((entityclientplayermp.field_70125_A - f3) * 0.1f), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)((entityclientplayermp.field_70177_z - f4) * 0.1f), (float)0.0f, (float)1.0f, (float)0.0f);
        ItemStack itemstack = this.itemToRender;
        int i = this.mc.field_71441_e.func_72802_i(MathHelper.func_76128_c((double)entityclientplayermp.field_70165_t), MathHelper.func_76128_c((double)entityclientplayermp.field_70163_u), MathHelper.func_76128_c((double)entityclientplayermp.field_70161_v), 0);
        int j = i % 65536;
        int k = i / 65536;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)j / 1.0f), (float)((float)k / 1.0f));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (itemstack == null && !entityclientplayermp.func_82150_aj()) {
            GL11.glPushMatrix();
            float f13 = 0.8f;
            float f5 = entityclientplayermp.func_70678_g(partialTickTime);
            float f6 = MathHelper.func_76126_a((float)(f5 * (float)Math.PI));
            float f7 = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f5) * (float)Math.PI));
            GL11.glTranslatef((float)(-f7 * 0.3f), (float)(MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f5) * (float)Math.PI * 2.0f)) * 0.4f), (float)(-f6 * 0.4f));
            GL11.glTranslatef((float)(0.8f * f13), (float)(-0.75f * f13 - (1.0f - f1) * 0.6f), (float)(-0.9f * f13));
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glEnable((int)32826);
            f5 = entityclientplayermp.func_70678_g(partialTickTime);
            f6 = MathHelper.func_76126_a((float)(f5 * f5 * (float)Math.PI));
            f7 = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f5) * (float)Math.PI));
            GL11.glRotatef((float)(f7 * 70.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(-f6 * 20.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslatef((float)-1.0f, (float)3.6f, (float)3.5f);
            GL11.glRotatef((float)120.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)200.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glTranslatef((float)5.6f, (float)0.0f, (float)0.0f);
            float f10 = 1.0f;
            GL11.glScalef((float)f10, (float)f10, (float)f10);
            Render render = RenderManager.field_78727_a.func_78713_a((Entity)this.mc.field_71439_g);
            RenderPlayer renderplayer = (RenderPlayer)render;
            ClientEventHandler.renderCNPCSelf.field_77109_a = renderplayer.field_77109_a;
            ClientEventHandler.renderCNPCSelf.field_77111_i = renderplayer.field_77111_i;
            ClientEventHandler.renderCNPCSelf.field_77108_b = renderplayer.field_77108_b;
            ClientEventHandler.renderCNPCSelf.renderFirstPersonArmOverlay((EntityPlayer)this.mc.field_71439_g);
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)32826);
        RenderHelper.func_74518_a();
    }

    public void func_78441_a() {
        float f;
        float f1;
        float f2;
        boolean flag;
        this.prevEquippedProgress = this.equippedProgress;
        EntityClientPlayerMP entityclientplayermp = this.mc.field_71439_g;
        ItemStack itemstack = entityclientplayermp.field_71071_by.func_70448_g();
        boolean bl = flag = this.equippedItemSlot == entityclientplayermp.field_71071_by.field_70461_c && itemstack == this.itemToRender;
        if (this.itemToRender == null && itemstack == null) {
            flag = true;
        }
        if (itemstack != null && this.itemToRender != null && itemstack != this.itemToRender && itemstack.func_77973_b() == this.itemToRender.func_77973_b() && itemstack.func_77960_j() == this.itemToRender.func_77960_j()) {
            this.itemToRender = itemstack;
            flag = true;
        }
        if ((f2 = (f1 = flag ? 1.0f : 0.0f) - this.equippedProgress) < -(f = 0.4f)) {
            f2 = -f;
        }
        if (f2 > f) {
            f2 = f;
        }
        this.equippedProgress += f2;
        if (this.equippedProgress < 0.1f) {
            this.itemToRender = itemstack;
            this.equippedItemSlot = entityclientplayermp.field_71071_by.field_70461_c;
        }
    }
}

