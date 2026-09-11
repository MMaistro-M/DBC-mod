/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import noppes.npcs.client.ClientEventHandler;
import noppes.npcs.client.EntityUtil;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.data.ModelData;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiModelInterface
extends GuiNPCInterface {
    public ModelData playerdata;
    private static float rotation = 0.0f;
    private GuiNpcButton left;
    private GuiNpcButton right;
    private GuiNpcButton zoom;
    private GuiNpcButton unzoom;
    private static float zoomed = 60.0f;
    public float minSize = 10.0f;
    public float maxSize = 100.0f;
    public int xOffset = 0;
    public int xOffsetButton = 0;
    public int yOffset = 0;
    public int yOffsetButton = 0;
    public boolean followMouse = true;
    public boolean drawNPConSub = true;
    public boolean allowRotate = true;
    public boolean drawRenderButtons = true;
    public boolean drawXButton = true;
    public EntityCustomNpc npc;
    private final long start = -1L;

    public GuiModelInterface(EntityCustomNpc npc) {
        this.npc = npc;
        this.playerdata = npc.modelData;
        this.xSize = 380;
        this.drawDefaultBackground = false;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        if (this.drawRenderButtons) {
            this.unzoom = new GuiNpcButton(666, this.guiLeft + 148 + this.xOffset + this.xOffsetButton, this.guiTop + 200 + this.yOffset + this.yOffsetButton, 20, 20, "-");
            this.addButton(this.unzoom);
            this.zoom = new GuiNpcButton(667, this.guiLeft + 214 + this.xOffset + this.xOffsetButton, this.guiTop + 200 + this.yOffset + this.yOffsetButton, 20, 20, "+");
            this.addButton(this.zoom);
            this.left = new GuiNpcButton(668, this.guiLeft + 170 + this.xOffset + this.xOffsetButton, this.guiTop + 200 + this.yOffset + this.yOffsetButton, 20, 20, "<");
            this.addButton(this.left);
            this.right = new GuiNpcButton(669, this.guiLeft + 192 + this.xOffset + this.xOffsetButton, this.guiTop + 200 + this.yOffset + this.yOffsetButton, 20, 20, ">");
            this.addButton(this.right);
        }
        if (this.drawXButton) {
            this.addButton(new GuiNpcButton(670, this.field_146294_l - 22, 2, 20, 20, "X"));
        }
    }

    @Override
    protected void func_146284_a(GuiButton btn) {
        super.func_146284_a(btn);
        if (btn.field_146127_k == 670) {
            this.close();
        }
    }

    @Override
    public boolean func_73868_f() {
        return false;
    }

    @Override
    public boolean isMouseOverRenderer(int x, int y) {
        if (!this.allowRotate) {
            return false;
        }
        int centerX = this.guiLeft + 190 + this.xOffset;
        int centerY = this.guiTop + 180 + this.yOffset;
        int xBuffer = 100;
        int yBuffer = 150;
        return this.mouseX >= centerX - xBuffer && this.mouseX <= centerX + xBuffer && this.mouseY >= centerY - yBuffer && this.mouseY <= centerY + yBuffer;
    }

    public void preRender(EntityLivingBase entity) {
        EntityUtil.Copy((EntityLivingBase)this.npc, entity);
    }

    public void postRender(EntityLivingBase entity) {
    }

    @Override
    public void func_73863_a(int par1, int par2, float par3) {
        if (Mouse.isButtonDown((int)0)) {
            if (this.left.func_146116_c(this.field_146297_k, par1, par2)) {
                rotation += par3 * 1.5f;
            } else if (this.right.func_146116_c(this.field_146297_k, par1, par2)) {
                rotation -= par3 * 1.5f;
            } else if (this.zoom.func_146116_c(this.field_146297_k, par1, par2) && zoomed < this.maxSize) {
                zoomed += par3;
            } else if (this.unzoom.func_146116_c(this.field_146297_k, par1, par2) && zoomed > this.minSize) {
                zoomed -= par3;
            }
        }
        if (this.isMouseOverRenderer(par1, par2)) {
            zoomed += (float)Mouse.getDWheel() * 0.035f;
            if (Mouse.isButtonDown((int)0) || Mouse.isButtonDown((int)1)) {
                rotation -= (float)Mouse.getDX() * 0.75f;
            }
        }
        if (zoomed > this.maxSize) {
            zoomed = this.maxSize;
        }
        if (zoomed < this.minSize) {
            zoomed = this.minSize;
        }
        if (this.hasSubGui() && !this.drawNPConSub) {
            return;
        }
        this.func_146276_q_();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Object entity = this.playerdata.getEntity(this.npc);
        if (entity == null) {
            entity = this.npc;
        }
        this.preRender((EntityLivingBase)entity);
        int l = this.guiLeft + 190 + this.xOffset;
        int i1 = this.guiTop + 180 + this.yOffset;
        GL11.glEnable((int)2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)l, (float)i1, (float)60.0f);
        GL11.glScalef((float)(-zoomed), (float)zoomed, (float)zoomed);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        float f2 = entity.field_70761_aq;
        float f3 = entity.field_70177_z;
        float f4 = entity.field_70125_A;
        float f7 = entity.field_70759_as;
        float f5 = (float)l - (float)par1;
        float f6 = (float)(i1 - 50) - (float)par2;
        GL11.glRotatef((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-((float)Math.atan(f6 / 800.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        entity.field_70760_ar = entity.field_70761_aq = rotation;
        entity.field_70126_B = entity.field_70177_z = (float)Math.atan(f5 / 80.0f) * 40.0f + rotation;
        entity.field_70125_A = this.followMouse ? -((float)Math.atan(f6 / 40.0f)) * 20.0f : 0.0f;
        entity.field_70759_as = this.followMouse ? entity.field_70177_z : rotation;
        entity.field_70758_at = entity.field_70759_as;
        GL11.glTranslatef((float)0.0f, (float)entity.field_70129_M, (float)1.0f);
        RenderManager.field_78727_a.field_78735_i = 180.0f;
        ClientEventHandler.renderingEntityInGUI = true;
        try {
            RenderManager.field_78727_a.func_147940_a((Entity)entity, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        }
        catch (Exception e) {
            this.playerdata.setEntityClass(null);
        }
        ClientEventHandler.renderingEntityInGUI = false;
        entity.field_70760_ar = entity.field_70761_aq = f2;
        entity.field_70126_B = entity.field_70177_z = f3;
        entity.field_70125_A = f4;
        entity.field_70758_at = entity.field_70759_as = f7;
        GL11.glPopMatrix();
        RenderHelper.func_74518_a();
        GL11.glDisable((int)32826);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
        GL11.glDisable((int)3553);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)500.065f);
        super.func_73863_a(par1, par2, par3);
        GL11.glPopMatrix();
        this.postRender((EntityLivingBase)entity);
    }

    @Override
    public void func_73869_a(char par1, int par2) {
        super.func_73869_a(par1, par2);
    }

    @Override
    public void close() {
        this.field_146297_k.func_147108_a(null);
        this.field_146297_k.func_71381_h();
    }

    @Override
    public void save() {
    }
}

