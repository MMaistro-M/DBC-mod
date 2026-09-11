/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
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
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import noppes.npcs.client.ClientEventHandler;
import noppes.npcs.client.EntityUtil;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.entity.data.ModelData;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiModelInterface2
extends GuiNPCInterface2 {
    public ModelData playerdata;
    protected static float rotation = 0.0f;
    private GuiNpcButton left;
    private GuiNpcButton right;
    private GuiNpcButton zoom;
    private GuiNpcButton unzoom;
    protected static float zoomed = 60.0f;
    public int xOffset = 0;
    public int yOffset = 0;
    public EntityNPCInterface npc;
    private long start = -1L;

    public GuiModelInterface2(EntityNPCInterface npc) {
        this(npc, true);
    }

    public GuiModelInterface2(EntityNPCInterface npc, boolean hasMenuNpc) {
        super(hasMenuNpc ? npc : null);
        this.npc = npc;
        this.playerdata = ((EntityCustomNpc)npc).modelData;
        this.drawDefaultBackground = false;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.unzoom = new GuiNpcButton(666, this.guiLeft + 148 + this.xOffset, this.guiTop + 200 + this.yOffset, 20, 20, "-");
        this.addButton(this.unzoom);
        this.zoom = new GuiNpcButton(667, this.guiLeft + 214 + this.xOffset, this.guiTop + 200 + this.yOffset, 20, 20, "+");
        this.addButton(this.zoom);
        this.left = new GuiNpcButton(668, this.guiLeft + 170 + this.xOffset, this.guiTop + 200 + this.yOffset, 20, 20, "<");
        this.addButton(this.left);
        this.right = new GuiNpcButton(669, this.guiLeft + 192 + this.xOffset, this.guiTop + 200 + this.yOffset, 20, 20, ">");
        this.addButton(this.right);
    }

    @Override
    protected void func_146284_a(GuiButton btn) {
        if (btn.field_146127_k == 670) {
            this.close();
        }
    }

    @Override
    public boolean func_73868_f() {
        return false;
    }

    @Override
    public void func_73863_a(int par1, int par2, float par3) {
        if (Mouse.isButtonDown((int)0)) {
            if (this.left.func_146116_c(this.field_146297_k, par1, par2)) {
                rotation += par3 * 2.0f;
            } else if (this.right.func_146116_c(this.field_146297_k, par1, par2)) {
                rotation -= par3 * 2.0f;
            } else if (this.zoom.func_146116_c(this.field_146297_k, par1, par2)) {
                zoomed += par3 * 2.0f;
            } else if (this.unzoom.func_146116_c(this.field_146297_k, par1, par2) && zoomed > 10.0f) {
                zoomed -= par3 * 2.0f;
            }
        }
        super.func_73863_a(par1, par2, par3);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Object entity = this.playerdata.getEntity(this.npc);
        if (entity == null) {
            entity = this.npc;
        }
        EntityUtil.Copy((EntityLivingBase)this.npc, entity);
        int l = this.guiLeft + 190 + this.xOffset;
        int i1 = this.guiTop + 180 + this.yOffset;
        GL11.glEnable((int)2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)l, (float)i1, (float)50.0f);
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
        GL11.glRotatef((float)(-((float)Math.atan(f6 / 80.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        entity.field_70760_ar = entity.field_70761_aq = rotation;
        entity.field_70126_B = entity.field_70177_z = (float)Math.atan(f5 / 80.0f) * 40.0f + rotation;
        entity.field_70125_A = -((float)Math.atan(f6 / 80.0f)) * 20.0f;
        entity.field_70758_at = entity.field_70759_as = entity.field_70177_z;
        GL11.glTranslatef((float)0.0f, (float)entity.field_70129_M, (float)0.0f);
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
        GL11.glClear((int)256);
    }

    @Override
    protected void drawBackground() {
        super.drawBackground();
        int xPosGradient = this.guiLeft + 10;
        int yPosGradient = this.guiTop + 10;
        this.func_73733_a(xPosGradient, yPosGradient, 200 + xPosGradient, 180 + yPosGradient, -1072689136, -804253680);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    @Override
    public void func_73869_a(char par1, int par2) {
        boolean hadSubGui = this.hasSubGui();
        super.func_73869_a(par1, par2);
        if (par2 == 1 && !hadSubGui) {
            this.close();
        }
    }

    @Override
    public void close() {
        this.field_146297_k.func_147108_a((GuiScreen)null);
        this.field_146297_k.func_71381_h();
    }

    @Override
    public void save() {
    }

    public void setSave(boolean saveNPC) {
        if (this.getMenu() != null) {
            this.getMenu().saveNPC = saveNPC;
        }
    }
}

