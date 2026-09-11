/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiYesNoCallback
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.GuiScreenEvent$ActionPerformedEvent$Post
 *  net.minecraftforge.client.event.GuiScreenEvent$ActionPerformedEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcSlider;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiScrollWindow
extends GuiScreen
implements ITextfieldListener,
ICustomScrollListener,
GuiYesNoCallback {
    public static final ResourceLocation resource = new ResourceLocation("customnpcs", "textures/gui/misc.png");
    protected GuiNPCInterface parent;
    public boolean drawDefaultBackground = true;
    public EntityNPCInterface npc;
    protected HashMap<Integer, GuiNpcButton> buttons = new HashMap();
    protected HashMap<Integer, GuiNpcTextField> textfields = new HashMap();
    protected HashMap<Integer, GuiNpcLabel> labels = new HashMap();
    protected HashMap<Integer, GuiCustomScroll> scrolls = new HashMap();
    protected HashMap<Integer, GuiNpcSlider> sliders = new HashMap();
    protected ScaledResolution scaledResolution;
    public int clipWidth;
    public int clipHeight;
    public int xPos = 0;
    public int yPos = 0;
    public int maxScrollY = 0;
    public float scrollY = 0.0f;
    public float nextScrollY = 0.0f;
    public float scrollSpeed = 7.0f;
    public int mouseScroll;
    boolean isScrolling;
    public int backgroundColor = 0x66000000;

    public GuiScrollWindow(GuiNPCInterface parent, int posX, int posY, int clipWidth, int clipHeight, int maxScroll) {
        this.parent = parent;
        this.xPos = posX;
        this.yPos = posY;
        this.clipWidth = clipWidth;
        this.clipHeight = clipHeight;
        this.maxScrollY = maxScroll;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.scaledResolution = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        float scrollPerc = 0.0f;
        if (this.yPos + this.clipHeight != 0) {
            scrollPerc = this.nextScrollY / (float)this.clipHeight;
        }
        this.nextScrollY = scrollPerc * (float)this.clipHeight;
        GuiNpcTextField.unfocus();
        this.field_146292_n.clear();
        this.labels.clear();
        this.textfields.clear();
        this.buttons.clear();
        this.scrolls.clear();
        this.sliders.clear();
        Keyboard.enableRepeatEvents((boolean)true);
    }

    public void func_73876_c() {
        for (GuiNpcTextField tf : this.textfields.values()) {
            if (!tf.enabled) continue;
            tf.func_146178_a();
        }
        super.func_73876_c();
    }

    public void func_73864_a(int i, int j, int k) {
        if (i < this.xPos || i > this.xPos + this.clipWidth || j < this.yPos || j > this.yPos + this.clipHeight) {
            return;
        }
        i -= this.xPos;
        j = (int)((float)(j - this.yPos) + this.scrollY);
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
        this.vanillaMouseClicked(i, j, k);
    }

    protected void vanillaMouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 || mouseButton == 1) {
            for (int l = 0; l < this.field_146292_n.size(); ++l) {
                GuiButton guibutton = (GuiButton)this.field_146292_n.get(l);
                AtomicBoolean rightClicked = null;
                if (mouseButton == 1) {
                    if (!(guibutton instanceof GuiNpcButton) || !((GuiNpcButton)guibutton).rightClickable) continue;
                    rightClicked = ((GuiNpcButton)guibutton).rightClicked;
                    rightClicked.set(true);
                }
                if (guibutton.func_146116_c(this.field_146297_k, mouseX, mouseY)) {
                    GuiScreenEvent.ActionPerformedEvent.Pre event = new GuiScreenEvent.ActionPerformedEvent.Pre((GuiScreen)this, guibutton, this.field_146292_n);
                    if (MinecraftForge.EVENT_BUS.post((Event)event)) break;
                    this.field_146290_a = event.button;
                    event.button.func_146113_a(this.field_146297_k.func_147118_V());
                    this.func_146284_a(event.button);
                    if (this.equals(this.field_146297_k.field_71462_r)) {
                        MinecraftForge.EVENT_BUS.post((Event)new GuiScreenEvent.ActionPerformedEvent.Post((GuiScreen)this, event.button, this.field_146292_n));
                    }
                }
                if (rightClicked == null) continue;
                rightClicked.set(false);
            }
        }
    }

    public void mouseEvent(int i, int j, int k) {
    }

    protected void func_146284_a(GuiButton guibutton) {
        this.parent.func_146284_a(guibutton);
    }

    public void buttonEvent(GuiButton guibutton) {
        this.parent.buttonEvent(guibutton);
    }

    public void func_73869_a(char c, int i) {
        for (GuiNpcTextField tf : this.textfields.values()) {
            tf.func_146201_a(c, i);
        }
    }

    public void addButton(GuiNpcButton button) {
        this.buttons.put(button.field_146127_k, button);
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

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        this.scrollY = (float)this.lerp(this.scrollY, this.nextScrollY, partialTicks);
        boolean drawBackground = this.drawDefaultBackground;
        GL11.glPushMatrix();
        GL11.glEnable((int)3089);
        this.setClip(this.xPos, this.yPos, this.clipWidth, this.clipHeight);
        this.drawDefaultBackground = false;
        if (drawBackground) {
            this.func_73733_a(0, 0, this.field_146294_l, this.field_146295_m, this.backgroundColor, this.backgroundColor);
        }
        if (this.maxScrollY > 0) {
            this.setClip(this.xPos, this.yPos, this.clipWidth - 13, this.clipHeight);
        }
        GL11.glTranslatef((float)this.xPos, (float)((float)this.yPos - this.scrollY), (float)0.0f);
        this.drawComponents(mouseX - this.xPos, (int)((float)mouseY + this.scrollY - (float)this.yPos), partialTicks);
        this.drawDefaultBackground = drawBackground;
        GL11.glDisable((int)3089);
        GL11.glTranslatef((float)(-this.xPos), (float)((float)(-this.yPos) + this.scrollY), (float)0.0f);
        if (this.maxScrollY > 0) {
            this.drawScrollBar();
        }
        GL11.glPopMatrix();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks, int mouseScroll) {
        this.adjustScroll(mouseX, mouseY, mouseScroll);
        this.mouseScroll = mouseScroll;
        this.func_73863_a(mouseX, mouseY, partialTicks);
    }

    public void drawComponents(int i, int j, float f) {
        if (this.drawDefaultBackground) {
            this.func_146276_q_();
        }
        boolean subGui = this.parent.hasSubGui();
        for (GuiNpcLabel label : this.labels.values()) {
            label.drawLabel(this, this.field_146289_q);
        }
        for (GuiNpcTextField tf : this.textfields.values()) {
            tf.drawTextBox(i, j);
        }
        for (GuiCustomScroll scroll : this.scrolls.values()) {
            scroll.updateSubGUI(subGui);
            scroll.drawScreen(i, j, f, !subGui && scroll.isMouseOver(i, j) ? this.mouseScroll : 0);
        }
        super.func_73863_a(i, j, f);
        for (GuiCustomScroll scroll : this.scrolls.values()) {
            if (!scroll.hoverableText) continue;
            scroll.drawHover(i, j);
        }
        for (GuiNpcButton button : this.buttons.values()) {
            button.updateSubGUI(subGui);
        }
    }

    public void drawHoverTexts(int mouseX, int mouseY) {
        boolean subGui = this.parent.hasSubGui();
        for (GuiNpcButton button : this.buttons.values()) {
            if (button.hoverableText.isEmpty()) continue;
            button.drawHover(mouseX, mouseY, subGui);
        }
        int localMouseX = mouseX - this.xPos;
        int localMouseY = (int)((float)(mouseY - this.yPos) + this.scrollY);
        for (GuiNpcTextField textField : this.textfields.values()) {
            if (!textField.hasHoverText()) continue;
            textField.drawHover(localMouseX, localMouseY, mouseX, mouseY, subGui);
        }
        for (GuiNpcLabel label : this.labels.values()) {
            if (label.hoverableText.isEmpty()) continue;
            label.drawHover(localMouseX, localMouseY, mouseX, mouseY, subGui, this.field_146289_q);
        }
    }

    public void elementClicked() {
        this.parent.elementClicked();
    }

    public boolean func_73868_f() {
        return false;
    }

    public void doubleClicked() {
    }

    public boolean isInventoryKey(int i) {
        return i == this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i();
    }

    public void func_146276_q_() {
        super.func_146276_q_();
    }

    protected void setClip(int x, int y, int width, int height) {
        if (this.scaledResolution == null) {
            return;
        }
        int scaleFactor = this.scaledResolution.func_78325_e();
        y *= scaleFactor;
        y = this.field_146297_k.field_71440_d - y;
        GL11.glScissor((int)(x *= scaleFactor), (int)(y - (height *= scaleFactor)), (int)(width *= scaleFactor), (int)height);
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= this.xPos && mouseX <= this.clipWidth + this.xPos && mouseY >= this.yPos && mouseY <= this.clipHeight + this.yPos;
    }

    public void adjustScroll(int mouseX, int mouseY, int mouseScroll) {
        mouseX -= this.xPos;
        mouseY -= this.yPos;
        if (Mouse.isButtonDown((int)0) && !this.parent.hasSubGui()) {
            if (mouseX >= this.clipWidth - 9 && mouseX < this.clipWidth - 4 && mouseY >= 4 && mouseY < this.clipHeight) {
                this.isScrolling = true;
            }
        } else {
            this.isScrolling = false;
        }
        if (this.isScrolling) {
            float proportion = (float)(mouseY - 4) / (float)(this.clipHeight - 8);
            this.nextScrollY = (int)(proportion * (float)this.maxScrollY);
        }
        if (!this.parent.hasSubGui()) {
            for (GuiCustomScroll guiCustomScroll : this.scrolls.values()) {
                if (!guiCustomScroll.isMouseOver(mouseX, (int)((float)mouseY + this.scrollY))) continue;
                return;
            }
        }
        if (mouseScroll != 0) {
            this.nextScrollY -= (float)mouseScroll / this.scrollSpeed;
        }
        if (this.nextScrollY < 0.0f) {
            this.nextScrollY = 0.0f;
        }
        if (this.nextScrollY > (float)this.maxScrollY) {
            this.nextScrollY = this.maxScrollY;
        }
    }

    private void drawScrollBar() {
        int y;
        this.field_146297_k.field_71446_o.func_110577_a(resource);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int maxSize = (int)((double)this.clipHeight * ((double)this.clipHeight / (double)(this.clipHeight + this.maxScrollY)));
        int x = this.xPos + this.clipWidth - 9;
        for (int k = y = (int)((float)(this.yPos + 4) + this.scrollY / (float)this.maxScrollY * (float)(this.clipHeight - maxSize - 8)); k < y + maxSize && k - this.clipHeight + maxSize < this.clipHeight + this.yPos; ++k) {
            this.func_73729_b(x, k, 176, 9, 5, 1);
        }
    }

    private double lerp(double a, double b, double lambda) {
        return a + lambda * (b - a);
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        if (this.parent instanceof ICustomScrollListener) {
            ((ICustomScrollListener)((Object)this.parent)).customScrollClicked(i, j, k, guiCustomScroll);
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
        if (this.parent instanceof ICustomScrollListener) {
            ((ICustomScrollListener)((Object)this.parent)).customScrollDoubleClicked(selection, scroll);
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (this.parent instanceof ITextfieldListener) {
            ((ITextfieldListener)((Object)this.parent)).unFocused(textfield);
        }
    }
}

