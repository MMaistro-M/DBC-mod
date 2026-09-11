/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcMenu;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public abstract class GuiNPCInterface2
extends GuiNPCInterface {
    private ResourceLocation background = new ResourceLocation("customnpcs:textures/gui/menubg.png");
    private GuiNpcMenu menu;

    public GuiNPCInterface2(EntityNPCInterface npc) {
        this(npc, -1);
    }

    public GuiNPCInterface2(EntityNPCInterface npc, int activeMenu) {
        super(npc);
        this.xSize = 420;
        this.ySize = 200;
        this.menu = new GuiNpcMenu(this, activeMenu, npc);
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.menu.initGui(this.guiLeft, this.guiTop, this.xSize);
    }

    @Override
    public void func_73864_a(int i, int j, int k) {
        if (!this.hasSubGui()) {
            this.menu.mouseClicked(i, j, k);
        }
        super.func_73864_a(i, j, k);
    }

    public void delete() {
        this.npc.delete();
        this.displayGuiScreen(null);
        this.field_146297_k.func_71381_h();
    }

    @Override
    public abstract void save();

    @Override
    public void func_73863_a(int i, int j, float f) {
        if (this.drawDefaultBackground) {
            this.func_146276_q_();
        }
        this.drawBackground();
        this.menu.drawElements(this.getFontRenderer(), i, j, this.field_146297_k, f);
        boolean bo = this.drawDefaultBackground;
        this.drawDefaultBackground = false;
        super.func_73863_a(i, j, f);
        this.drawDefaultBackground = bo;
    }

    @Override
    protected void drawBackground() {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(this.background);
        this.func_73729_b(this.guiLeft, this.guiTop, 0, 0, 200, 220);
        this.func_73729_b(this.guiLeft + this.xSize - 230, this.guiTop, 26, 0, 230, 220);
    }

    protected GuiNpcMenu getMenu() {
        return this.menu;
    }
}

