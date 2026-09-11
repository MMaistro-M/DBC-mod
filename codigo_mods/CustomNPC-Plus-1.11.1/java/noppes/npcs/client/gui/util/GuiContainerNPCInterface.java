/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiMenuTopButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcSlider;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.containers.ContainerEmpty;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public abstract class GuiContainerNPCInterface
extends GuiContainer {
    public boolean drawDefaultBackground = false;
    public int field_147003_i;
    public int field_147009_r;
    public EntityClientPlayerMP player;
    public EntityNPCInterface npc;
    private HashMap<Integer, GuiNpcButton> buttons = new HashMap();
    private HashMap<Integer, GuiMenuTopButton> topbuttons = new HashMap();
    private HashMap<Integer, GuiNpcTextField> textfields = new HashMap();
    private HashMap<Integer, GuiNpcLabel> labels = new HashMap();
    private HashMap<Integer, GuiCustomScroll> scrolls = new HashMap();
    private HashMap<Integer, GuiNpcSlider> sliders = new HashMap();
    public String title;
    public boolean closeOnEsc = false;
    private SubGuiInterface subgui;
    public int mouseX;
    public int mouseY;

    public GuiContainerNPCInterface(EntityNPCInterface npc, Container cont) {
        super(cont);
        this.player = Minecraft.func_71410_x().field_71439_g;
        this.npc = npc;
        this.title = "Npc Mainmenu";
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        GuiNpcTextField.unfocus();
        this.field_146292_n.clear();
        this.buttons.clear();
        this.topbuttons.clear();
        this.scrolls.clear();
        this.sliders.clear();
        this.labels.clear();
        this.textfields.clear();
        Keyboard.enableRepeatEvents((boolean)true);
        if (this.subgui != null) {
            this.subgui.func_146280_a(this.field_146297_k, this.field_146294_l, this.field_146295_m);
            this.subgui.func_73866_w_();
        }
        this.field_146292_n.clear();
        this.field_147003_i = (this.field_146294_l - this.field_146999_f) / 2;
        this.field_147009_r = (this.field_146295_m - this.field_147000_g) / 2;
    }

    public ResourceLocation getResource(String texture) {
        return new ResourceLocation("customnpcs", "textures/gui/" + texture);
    }

    public void func_73876_c() {
        for (GuiNpcTextField tf : new ArrayList<GuiNpcTextField>(this.textfields.values())) {
            if (!tf.enabled) continue;
            tf.func_146178_a();
        }
        super.func_73876_c();
    }

    protected void func_73864_a(int i, int j, int k) {
        if (this.subgui != null) {
            this.subgui.func_73864_a(i, j, k);
        } else {
            for (GuiNpcTextField tf : new ArrayList<GuiNpcTextField>(this.textfields.values())) {
                if (!tf.enabled) continue;
                tf.func_146192_a(i, j, k);
            }
            if (k == 0) {
                for (GuiCustomScroll scroll : new ArrayList<GuiCustomScroll>(this.scrolls.values())) {
                    scroll.func_73864_a(i, j, k);
                }
            }
            this.mouseEvent(i, j, k);
            super.func_73864_a(i, j, k);
        }
    }

    public void mouseEvent(int i, int j, int k) {
    }

    protected void func_73869_a(char c, int i) {
        if (this.subgui != null) {
            this.subgui.func_73869_a(c, i);
        } else {
            for (GuiNpcTextField tf : new ArrayList<GuiNpcTextField>(this.textfields.values())) {
                tf.func_146201_a(c, i);
            }
            if (this.closeOnEsc && (i == 1 || i == this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i() && !GuiNpcTextField.isFieldActive())) {
                this.close();
            }
        }
    }

    protected void func_146284_a(GuiButton guibutton) {
        if (this.subgui != null) {
            this.subgui.buttonEvent(guibutton);
        } else {
            this.buttonEvent(guibutton);
        }
    }

    public void buttonEvent(GuiButton guibutton) {
    }

    public void close() {
        Keyboard.enableRepeatEvents((boolean)false);
        GuiNpcTextField.unfocus();
        this.save();
        this.player.func_71053_j();
        this.displayGuiScreen(null);
        this.field_146297_k.func_71381_h();
    }

    public void addButton(GuiNpcButton button) {
        this.buttons.put(button.field_146127_k, button);
        this.field_146292_n.add(button);
    }

    public void addTopButton(GuiMenuTopButton button) {
        this.topbuttons.put(button.field_146127_k, button);
        this.field_146292_n.add(button);
    }

    public GuiNpcButton getButton(int i) {
        return this.buttons.get(i);
    }

    public void addTextField(GuiNpcTextField tf) {
        this.textfields.put(tf.id, tf);
    }

    public GuiNpcTextField getTextField(int i) {
        return this.textfields.get(i);
    }

    public void addLabel(GuiNpcLabel label) {
        this.labels.put(label.id, label);
    }

    public GuiNpcLabel getLabel(int i) {
        return this.labels.get(i);
    }

    public GuiMenuTopButton getTopButton(int i) {
        return this.topbuttons.get(i);
    }

    public void addSlider(GuiNpcSlider slider) {
        this.sliders.put(slider.field_146127_k, slider);
        this.field_146292_n.add(slider);
    }

    public GuiNpcSlider getSlider(int i) {
        return this.sliders.get(i);
    }

    public void addScroll(GuiCustomScroll scroll) {
        scroll.func_146280_a(this.field_146297_k, 350, 250);
        this.scrolls.put(scroll.id, scroll);
    }

    public GuiCustomScroll getScroll(int id) {
        return this.scrolls.get(id);
    }

    protected void func_146979_b(int par1, int par2) {
    }

    protected void func_146976_a(float f, int i, int j) {
        this.func_73732_a(this.field_146289_q, StatCollector.func_74838_a((String)this.title), this.field_146294_l / 2, this.field_147009_r - 8, 0xFFFFFF);
        for (GuiNpcLabel label : new ArrayList<GuiNpcLabel>(this.labels.values())) {
            label.drawLabel((GuiScreen)this, this.field_146289_q);
        }
        for (GuiNpcTextField tf : new ArrayList<GuiNpcTextField>(this.textfields.values())) {
            tf.drawTextBox(i, j);
        }
        for (GuiCustomScroll scroll : new ArrayList<GuiCustomScroll>(this.scrolls.values())) {
            scroll.drawScreen(i, j, f, this.hasSubGui() ? 0 : Mouse.getDWheel());
        }
    }

    public abstract void save();

    public void func_73863_a(int i, int j, float f) {
        this.mouseX = i;
        this.mouseY = j;
        Container container = this.field_147002_h;
        if (this.subgui != null) {
            this.field_147002_h = new ContainerEmpty();
        }
        super.func_73863_a(i, j, f);
        this.field_73735_i = 0.0f;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (this.subgui != null) {
            this.field_147002_h = container;
            RenderHelper.func_74518_a();
            this.subgui.func_73863_a(i, j, f);
        }
        boolean subGui = this.hasSubGui();
        for (GuiCustomScroll scroll : new ArrayList<GuiCustomScroll>(this.scrolls.values())) {
            scroll.drawHover(i, j);
        }
        for (GuiNpcButton button : this.buttons.values()) {
            button.drawHover(i, j, subGui);
        }
    }

    public void func_146276_q_() {
        if (this.drawDefaultBackground && this.subgui == null) {
            super.func_146276_q_();
        }
    }

    public FontRenderer getFontRenderer() {
        return this.field_146289_q;
    }

    public void closeSubGui(SubGuiInterface gui) {
        if (this.subgui == gui) {
            this.subgui = null;
        }
    }

    public boolean hasSubGui() {
        return this.subgui != null;
    }

    public SubGuiInterface getSubGui() {
        if (this.hasSubGui() && this.subgui.hasSubGui()) {
            return this.subgui.getSubGui();
        }
        return this.subgui;
    }

    public void displayGuiScreen(GuiScreen gui) {
        this.field_146297_k.func_147108_a(gui);
    }

    public void setSubGui(SubGuiInterface gui) {
        this.subgui = gui;
        this.subgui.func_146280_a(this.field_146297_k, this.field_146294_l, this.field_146295_m);
        this.subgui.parent = this;
        this.func_73866_w_();
    }

    public void drawNpc(int x, int y) {
        this.npc.isDrawn = true;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(this.field_147003_i + x), (float)(this.field_147009_r + y), (float)50.0f);
        float scale = 1.0f;
        if ((double)this.npc.field_70131_O > 2.4) {
            scale = 2.0f / this.npc.field_70131_O;
        }
        GL11.glScalef((float)(-30.0f * scale), (float)(30.0f * scale), (float)(30.0f * scale));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        float f2 = this.npc.field_70761_aq;
        float f3 = this.npc.field_70177_z;
        float f4 = this.npc.field_70125_A;
        float f7 = this.npc.field_70759_as;
        float f5 = (float)(this.field_147003_i + x) - (float)this.mouseX;
        float f6 = (float)(this.field_147009_r + y - 50) - (float)this.mouseY;
        int orientation = 0;
        if (this.npc != null && this.npc.ais != null) {
            orientation = this.npc.ais.orientation;
            this.npc.ais.orientation = 0;
        }
        GL11.glRotatef((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-((float)Math.atan(f6 / 40.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        this.npc.field_70761_aq = (float)Math.atan(f5 / 40.0f) * 20.0f;
        this.npc.field_70177_z = (float)Math.atan(f5 / 40.0f) * 40.0f;
        this.npc.field_70125_A = -((float)Math.atan(f6 / 40.0f)) * 20.0f;
        this.npc.field_70759_as = this.npc.field_70177_z;
        GL11.glTranslatef((float)0.0f, (float)this.npc.field_70129_M, (float)0.0f);
        RenderManager.field_78727_a.field_78735_i = 180.0f;
        RenderManager.field_78727_a.func_147940_a((Entity)this.npc, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        this.npc.field_70761_aq = f2;
        this.npc.field_70177_z = f3;
        this.npc.field_70125_A = f4;
        this.npc.field_70759_as = f7;
        if (this.npc != null && this.npc.ais != null) {
            this.npc.ais.orientation = orientation;
        }
        this.npc.isDrawn = false;
        GL11.glPopMatrix();
        RenderHelper.func_74518_a();
        GL11.glDisable((int)32826);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
        GL11.glDisable((int)3553);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }
}

