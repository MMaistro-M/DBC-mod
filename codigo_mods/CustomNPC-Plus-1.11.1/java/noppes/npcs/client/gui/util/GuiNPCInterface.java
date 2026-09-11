/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.client.event.GuiScreenEvent$ActionPerformedEvent$Post
 *  net.minecraftforge.client.event.GuiScreenEvent$ActionPerformedEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import cpw.mods.fml.common.eventhandler.Event;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import noppes.npcs.client.ClientEventHandler;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.TextBlockClient;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiDiagram;
import noppes.npcs.client.gui.util.GuiHoverText;
import noppes.npcs.client.gui.util.GuiMenuSideButton;
import noppes.npcs.client.gui.util.GuiMenuTopButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcSlider;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.GuiScrollWindow;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public abstract class GuiNPCInterface
extends GuiScreen {
    public EntityClientPlayerMP player;
    public boolean drawDefaultBackground = true;
    public EntityNPCInterface npc;
    protected HashMap<Integer, GuiNpcButton> buttons = new HashMap();
    protected HashMap<Integer, GuiMenuTopButton> topbuttons = new HashMap();
    protected HashMap<Integer, GuiMenuSideButton> sidebuttons = new HashMap();
    protected HashMap<Integer, GuiNpcTextField> textfields = new HashMap();
    protected HashMap<Integer, GuiNpcLabel> labels = new HashMap();
    protected HashMap<Integer, GuiCustomScroll> scrolls = new HashMap();
    protected HashMap<Integer, GuiNpcSlider> sliders = new HashMap();
    protected HashMap<Integer, GuiScreen> extra = new HashMap();
    protected HashMap<Integer, GuiScrollWindow> scrollWindows = new HashMap();
    protected HashMap<Integer, GuiDiagram> diagrams = new HashMap();
    public static boolean resizingActive = false;
    public String title;
    private ResourceLocation background = null;
    public boolean closeOnEsc = false;
    public Supplier<Boolean> closeOnEscSupplier = null;
    public int guiLeft;
    public int guiTop;
    public int xSize;
    public int ySize;
    private SubGuiInterface subgui;
    private Consumer<SubGuiInterface> pendingSubGuiResult;
    public int mouseX;
    public int mouseY;
    public int mouseScroll;
    public float bgScale = 1.0f;
    public float bgScaleX = 1.0f;
    public float bgScaleY = 1.0f;
    public float bgScaleZ = 1.0f;
    public int bgTextureHeight = 256;
    protected boolean isPannableGUI = false;
    protected boolean isPanning = false;
    protected double panStartX;
    protected double panStartY;
    public double currentPanX;
    public double currentPanY;
    protected double minPanX;
    protected double maxPanX;
    protected double minPanY;
    protected double maxPanY;
    protected int panViewportX;
    protected int panViewportY;
    protected int panViewportWidth;
    protected int panViewportHeight;
    protected int panAdjMouseX;
    protected int panAdjMouseY;
    private GuiNpcButton rotateLeft;
    private GuiNpcButton rotateRight;
    private GuiNpcButton zoomOut;
    private GuiNpcButton zoomIn;
    public int xOffsetNpc = 0;
    public int xOffsetButton = 0;
    public int xMouseRange = 50;
    public int yOffsetNpc = 0;
    public int yOffsetButton = 0;
    public int yMouseRange = 150;
    public float defaultZoom = 1.0f;
    public float zoom = 1.0f;
    public float rotation;
    public float minZoom = 1.0f;
    public float maxZoom = 2.5f;
    public boolean followMouse = true;
    public boolean allowRotate = true;
    public boolean drawNPConSub;
    public boolean drawNpc;
    public boolean drawRenderButtons;

    public double getPanX() {
        return this.currentPanX;
    }

    public double getPanY() {
        return this.currentPanY;
    }

    public GuiNPCInterface(EntityNPCInterface npc) {
        this.player = Minecraft.func_71410_x().field_71439_g;
        this.npc = npc;
        this.title = "";
        this.xSize = 200;
        this.ySize = 222;
    }

    public GuiNPCInterface() {
        this(null);
    }

    public void setBackground(String texture) {
        this.background = new ResourceLocation("customnpcs", "textures/gui/" + texture);
    }

    public void setBackground(String texture, int textureHeight) {
        this.background = new ResourceLocation("customnpcs", "textures/gui/" + texture);
        this.bgTextureHeight = textureHeight;
    }

    public ResourceLocation getResource(String texture) {
        return new ResourceLocation("customnpcs", "textures/gui/" + texture);
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        GuiNpcTextField.unfocus();
        if (this.subgui != null) {
            this.subgui.func_146280_a(this.field_146297_k, this.field_146294_l, this.field_146295_m);
            this.subgui.func_73866_w_();
        }
        this.guiLeft = (this.field_146294_l - this.xSize) / 2;
        this.guiTop = (this.field_146295_m - this.ySize) / 2;
        this.field_146292_n.clear();
        this.labels.clear();
        this.textfields.clear();
        this.buttons.clear();
        this.sidebuttons.clear();
        this.topbuttons.clear();
        this.scrolls.clear();
        this.sliders.clear();
        this.scrollWindows.clear();
        this.diagrams.clear();
        Keyboard.enableRepeatEvents((boolean)true);
        if (this.drawRenderButtons) {
            this.zoomIn = new GuiNpcButton(0, this.guiLeft + this.xOffsetNpc + this.xOffsetButton, this.guiTop + this.yOffsetNpc + this.yOffsetButton, 20, 20, "-");
            this.zoomOut = new GuiNpcButton(0, this.guiLeft + 22 + this.xOffsetNpc + this.xOffsetButton, this.guiTop + this.yOffsetNpc + this.yOffsetButton, 20, 20, "+");
            this.rotateLeft = new GuiNpcButton(0, this.guiLeft + 44 + this.xOffsetNpc + this.xOffsetButton, this.guiTop + this.yOffsetNpc + this.yOffsetButton, 20, 20, "<");
            this.rotateRight = new GuiNpcButton(0, this.guiLeft + 66 + this.xOffsetNpc + this.xOffsetButton, this.guiTop + this.yOffsetNpc + this.yOffsetButton, 20, 20, ">");
        }
        for (GuiNpcTextField tf : this.textfields.values()) {
            tf.initGui();
        }
    }

    public void func_73876_c() {
        if (this.subgui != null) {
            this.subgui.func_73876_c();
        } else {
            for (GuiNpcTextField tf : this.textfields.values()) {
                if (!tf.enabled) continue;
                tf.func_146178_a();
            }
            super.func_73876_c();
        }
    }

    public void addExtra(GuiHoverText gui) {
        gui.func_146280_a(this.field_146297_k, 350, 250);
        this.extra.put(gui.id, gui);
    }

    public void addScrollableGui(int id, GuiScrollWindow gui) {
        gui.func_146280_a(this.field_146297_k, this.field_146294_l, this.field_146295_m);
        gui.func_73866_w_();
        this.scrollWindows.put(id, gui);
    }

    public void addDiagram(int id, GuiDiagram diagram) {
        diagram.invalidateCache();
        this.diagrams.put(id, diagram);
    }

    public GuiDiagram getDiagram(int id) {
        return this.diagrams.get(id);
    }

    public void func_73864_a(int i, int j, int k) {
        if (this.subgui != null) {
            this.subgui.func_73864_a(i, j, k);
        } else {
            int adjY;
            int adjX = this.isPannableGUI ? this.panAdjustedX(i) : i;
            int n = adjY = this.isPannableGUI ? this.panAdjustedY(j) : j;
            if (this.isPannableGUI && k == 0 && this.isPannableArea(adjX, adjY)) {
                this.isPanning = true;
                this.panStartX = i;
                this.panStartY = j;
                return;
            }
            this.mouseEvent(adjX, adjY, k);
            this.vanillaMouseClicked(adjX, adjY, k);
            for (GuiScrollWindow guiScrollableComponent : this.scrollWindows.values()) {
                guiScrollableComponent.func_73864_a(adjX, adjY, k);
            }
            if (k == 0) {
                for (GuiCustomScroll scroll : new ArrayList<GuiCustomScroll>(this.scrolls.values())) {
                    scroll.func_73864_a(adjX, adjY, k);
                }
            }
            for (GuiDiagram diagram : this.diagrams.values()) {
                if (!diagram.isWithin(adjX, adjY) || !diagram.mouseClicked(adjX, adjY, k)) continue;
                return;
            }
            for (GuiNpcTextField tf : new ArrayList<GuiNpcTextField>(this.textfields.values())) {
                if (!tf.enabled) continue;
                tf.func_146192_a(adjX, adjY, k);
            }
        }
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
                    if (((Object)((Object)this)).equals(this.field_146297_k.field_71462_r)) {
                        MinecraftForge.EVENT_BUS.post((Event)new GuiScreenEvent.ActionPerformedEvent.Post((GuiScreen)this, event.button, this.field_146292_n));
                    }
                }
                if (rightClicked == null) continue;
                rightClicked.set(false);
            }
        }
    }

    public void func_146273_a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if (this.subgui != null) {
            this.subgui.func_146273_a(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
            return;
        }
        if (this.isPannableGUI && this.isPanning) {
            return;
        }
        int adjX = this.isPannableGUI ? this.panAdjustedX(mouseX) : mouseX;
        int adjY = this.isPannableGUI ? this.panAdjustedY(mouseY) : mouseY;
        super.func_146273_a(adjX, adjY, clickedMouseButton, timeSinceLastClick);
    }

    protected void func_146286_b(int mouseX, int mouseY, int state) {
        if (this.subgui != null) {
            this.subgui.func_146286_b(mouseX, mouseY, state);
            return;
        }
        if (this.isPannableGUI && state == 0 && this.isPanning) {
            this.isPanning = false;
            this.panStartX = 0.0;
            this.panStartY = 0.0;
            return;
        }
        int adjX = this.isPannableGUI ? this.panAdjustedX(mouseX) : mouseX;
        int adjY = this.isPannableGUI ? this.panAdjustedY(mouseY) : mouseY;
        super.func_146286_b(adjX, adjY, state);
    }

    public void mouseEvent(int i, int j, int k) {
    }

    protected boolean isPannableArea(int mx, int my) {
        for (Object obj : this.field_146292_n) {
            GuiButton btn = (GuiButton)obj;
            if (!btn.field_146125_m || mx < btn.field_146128_h || mx >= btn.field_146128_h + btn.field_146120_f || my < btn.field_146129_i || my >= btn.field_146129_i + btn.field_146121_g) continue;
            return false;
        }
        for (GuiCustomScroll scroll : this.scrolls.values()) {
            if (!scroll.visible || !scroll.isMouseOver(mx, my)) continue;
            return false;
        }
        for (GuiNpcTextField tf : this.textfields.values()) {
            if (!tf.enabled || mx < tf.field_146209_f || mx >= tf.field_146209_f + tf.field_146218_h || my < tf.field_146210_g || my >= tf.field_146210_g + tf.field_146219_i) continue;
            return false;
        }
        return true;
    }

    protected void computePanBounds(int vpX, int vpY, int vpW, int vpH) {
        this.panViewportX = vpX;
        this.panViewportY = vpY;
        this.panViewportWidth = vpW;
        this.panViewportHeight = vpH;
        int panRange = Math.max(this.field_146294_l, this.field_146295_m) / 2;
        this.minPanX = -panRange;
        this.maxPanX = panRange;
        this.minPanY = -panRange;
        this.maxPanY = panRange;
    }

    protected int panAdjustedX(int rawMouseX) {
        return rawMouseX + (int)this.currentPanX;
    }

    protected int panAdjustedY(int rawMouseY) {
        return rawMouseY + (int)this.currentPanY;
    }

    public boolean hasPanOffset() {
        return this.isPannableGUI && (this.currentPanX != 0.0 || this.currentPanY != 0.0);
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

    public void func_73869_a(char c, int i) {
        boolean isSub = this instanceof SubGuiInterface;
        boolean shouldClose = this.closeOnEsc;
        if (this.closeOnEscSupplier != null) {
            try {
                shouldClose = this.closeOnEscSupplier.get();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        if (shouldClose && !isSub && (i == 1 || !GuiNpcTextField.isFieldActive() && this.isInventoryKey(i))) {
            SubGuiInterface sub = this.getSubGui();
            if (sub != null) {
                sub.close();
            } else {
                this.close();
            }
        }
        if (this.subgui != null) {
            this.subgui.func_73869_a(c, i);
        }
        for (GuiNpcTextField tf : this.textfields.values()) {
            tf.func_146201_a(c, i);
        }
        for (GuiScrollWindow guiScrollableComponent : this.scrollWindows.values()) {
            guiScrollableComponent.func_73869_a(c, i);
        }
    }

    public void func_146281_b() {
        GuiNpcTextField.unfocus();
    }

    public void closeOnEsc(Supplier<Boolean> close) {
        this.closeOnEscSupplier = close;
    }

    public void close() {
        if (GuiNpcTextField.activeTextfield != null) {
            GuiNpcTextField.unfocus();
        }
        Keyboard.enableRepeatEvents((boolean)false);
        this.displayGuiScreen(null);
        this.field_146297_k.func_71381_h();
        this.save();
    }

    public void addButton(GuiNpcButton button) {
        this.buttons.put(button.field_146127_k, button);
        this.field_146292_n.add(button);
    }

    public void addTopButton(GuiMenuTopButton button) {
        this.topbuttons.put(button.field_146127_k, button);
        this.field_146292_n.add(button);
    }

    public void addSideButton(GuiMenuSideButton button) {
        this.sidebuttons.put(button.field_146127_k, button);
        this.field_146292_n.add(button);
    }

    public GuiNpcButton getButton(int i) {
        return this.buttons.get(i);
    }

    public GuiMenuSideButton getSideButton(int i) {
        return this.sidebuttons.get(i);
    }

    public GuiMenuTopButton getTopButton(int i) {
        return this.topbuttons.get(i);
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

    public GuiScrollWindow getScrollableGui(int i) {
        return this.scrollWindows.get(i);
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

    public abstract void save();

    public void func_73863_a(int i, int j, float f) {
        this.mouseX = i;
        this.mouseY = j;
        boolean panning = this.hasPanOffset();
        int drawX = panning ? this.panAdjustedX(i) : i;
        int drawY = panning ? this.panAdjustedY(j) : j;
        this.panAdjMouseX = drawX;
        this.panAdjMouseY = drawY;
        if (this.isPannableGUI && this.isPanning && Mouse.isButtonDown((int)0)) {
            double deltaX = (double)i - this.panStartX;
            double deltaY = (double)j - this.panStartY;
            this.currentPanX = Math.max(this.minPanX, Math.min(this.maxPanX, this.currentPanX - deltaX));
            this.currentPanY = Math.max(this.minPanY, Math.min(this.maxPanY, this.currentPanY - deltaY));
            this.panStartX = i;
            this.panStartY = j;
            panning = this.hasPanOffset();
            drawX = panning ? this.panAdjustedX(i) : i;
            drawY = panning ? this.panAdjustedY(j) : j;
            this.panAdjMouseX = drawX;
            this.panAdjMouseY = drawY;
        } else if (this.isPanning && !Mouse.isButtonDown((int)0)) {
            this.isPanning = false;
        }
        if (this.drawDefaultBackground && this.subgui == null) {
            this.func_146276_q_();
        }
        if (panning) {
            GL11.glPushMatrix();
            GL11.glTranslated((double)(-this.currentPanX), (double)(-this.currentPanY), (double)0.0);
        }
        if (this.background != null && this.field_146297_k.field_71446_o != null) {
            this.drawBackground();
        }
        boolean subGui = this.hasSubGui();
        this.func_73732_a(this.field_146289_q, this.title, this.field_146294_l / 2, this.guiTop + 4, 0xFFFFFF);
        for (GuiNpcLabel label : this.labels.values()) {
            label.drawLabel(this, this.field_146289_q);
        }
        for (GuiCustomScroll scroll : this.scrolls.values()) {
            scroll.updateSubGUI(subGui);
            scroll.drawScreen(drawX, drawY, f, !subGui && scroll.isMouseOver(drawX, drawY) ? Mouse.getDWheel() : 0);
        }
        for (GuiScrollWindow guiScrollableComponent : this.scrollWindows.values()) {
            guiScrollableComponent.drawScreen(drawX, drawY, f, !subGui && guiScrollableComponent.isMouseOver(drawX, drawY) ? Mouse.getDWheel() : 0);
        }
        for (GuiDiagram diagram : this.diagrams.values()) {
            diagram.drawDiagram(drawX, drawY, subGui);
        }
        super.func_73863_a(drawX, drawY, f);
        for (GuiCustomScroll scroll : this.scrolls.values()) {
            if (!scroll.hoverableText) continue;
            scroll.drawHover(drawX, drawY);
        }
        for (GuiNpcButton button : this.buttons.values()) {
            button.updateSubGUI(subGui);
            if (button.hoverableText.isEmpty()) continue;
            button.drawHover(drawX, drawY, subGui);
        }
        for (GuiNpcTextField tf : this.textfields.values()) {
            tf.drawTextBox(drawX, drawY);
            if (!tf.hasHoverText()) continue;
            tf.drawHover(drawX, drawY, subGui);
        }
        for (GuiScrollWindow guiScrollableComponent : this.scrollWindows.values()) {
            if (!guiScrollableComponent.isMouseOver(drawX, drawY)) continue;
            guiScrollableComponent.drawHoverTexts(drawX, drawY);
        }
        for (GuiScreen gui : this.extra.values()) {
            gui.func_73863_a(drawX, drawY, f);
        }
        if (panning) {
            GL11.glPopMatrix();
        }
        if (this.subgui != null) {
            this.subgui.func_73863_a(i, j, f);
        }
        if (this.drawNpc) {
            this.drawNpcWithExtras((EntityLivingBase)this.npc, i, j, f);
        }
    }

    protected void drawBackground() {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)this.guiLeft, (float)this.guiTop, (float)0.0f);
        GL11.glScalef((float)(this.bgScale * this.bgScaleX), (float)(this.bgScale * this.bgScaleY), (float)(this.bgScale * this.bgScaleZ));
        this.field_146297_k.field_71446_o.func_110577_a(this.background);
        int topHeight = this.ySize;
        int bottomHeight = 0;
        int bottomTextureV = 0;
        if (this.ySize < this.bgTextureHeight) {
            bottomHeight = -1;
            topHeight = this.ySize - bottomHeight;
            bottomTextureV = this.bgTextureHeight - bottomHeight;
        }
        if (this.xSize > 256) {
            this.func_73729_b(0, 0, 0, 0, 250, topHeight);
            this.func_73729_b(250, 0, 256 - (this.xSize - 250), 0, this.xSize - 250, topHeight);
            if (bottomHeight > 0) {
                this.func_73729_b(0, topHeight, 0, bottomTextureV, 250, bottomHeight);
                this.func_73729_b(250, topHeight, 256 - (this.xSize - 250), bottomTextureV, this.xSize - 250, bottomHeight);
            }
        } else if (this.xSize < 256) {
            int leftWidth = this.xSize / 2;
            int rightWidth = this.xSize - leftWidth;
            this.func_73729_b(0, 0, 0, 0, leftWidth, topHeight);
            this.func_73729_b(leftWidth, 0, 256 - rightWidth, 0, rightWidth, topHeight);
            if (bottomHeight > 0) {
                this.func_73729_b(0, topHeight, 0, bottomTextureV, leftWidth, bottomHeight);
                this.func_73729_b(leftWidth, topHeight, 256 - rightWidth, bottomTextureV, rightWidth, bottomHeight);
            }
        } else {
            this.func_73729_b(0, 0, 0, 0, this.xSize, topHeight);
            if (bottomHeight > 0) {
                this.func_73729_b(0, topHeight, 0, bottomTextureV, this.xSize, bottomHeight);
            }
        }
        GL11.glPopMatrix();
    }

    protected void drawTextBlock(String text, int x, int y, int lineWidth) {
        TextBlockClient block = new TextBlockClient(StatCollector.func_74838_a((String)text), lineWidth, true, this.player);
        for (int line = 0; line < block.lines.size(); ++line) {
            String lineText = ((IChatComponent)block.lines.get(line)).func_150254_d();
            this.field_146289_q.func_78276_b(lineText, x, y + line * this.field_146289_q.field_78288_b, CustomNpcResourceListener.DefaultTextColor);
        }
    }

    public FontRenderer getFontRenderer() {
        return this.field_146289_q;
    }

    public void elementClicked() {
        if (this.subgui != null) {
            this.subgui.elementClicked();
        }
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

    public void displayGuiScreen(GuiScreen gui) {
        this.field_146297_k.func_147108_a(gui);
    }

    public void setSubGui(SubGuiInterface gui) {
        this.subgui = gui;
        this.subgui.func_146280_a(this.field_146297_k, this.field_146294_l, this.field_146295_m);
        this.subgui.parent = this;
        this.func_73866_w_();
    }

    public void setSubGuiWithResult(SubGuiInterface gui, Consumer<SubGuiInterface> resultHandler) {
        this.pendingSubGuiResult = resultHandler;
        this.setSubGui(gui);
    }

    public void closeSubGui(SubGuiInterface gui) {
        if (this.pendingSubGuiResult != null) {
            Consumer<SubGuiInterface> handler = this.pendingSubGuiResult;
            this.pendingSubGuiResult = null;
            handler.accept(gui);
        }
        if (this.subgui == gui) {
            this.subgui = null;
        }
        this.func_73866_w_();
    }

    public boolean hasSubGui() {
        return this.subgui != null;
    }

    public SubGuiInterface getSubGui() {
        SubGuiInterface sub = this.subgui;
        if (sub != null) {
            while (sub.hasSubGui()) {
                sub = sub.getSubGui();
            }
        }
        return sub;
    }

    public boolean isMouseOverRenderer(int x, int y) {
        if (!this.allowRotate) {
            return false;
        }
        int centerX = this.guiLeft + this.xOffsetNpc;
        int centerY = this.guiTop + this.yOffsetNpc;
        int xRange = this.xMouseRange;
        int yRange = this.yMouseRange;
        return this.mouseX >= centerX - xRange && this.mouseX <= centerX + xRange && this.mouseY >= centerY - yRange && this.mouseY <= centerY + yRange;
    }

    public void drawNpcWithExtras(EntityLivingBase entity, int mouseX, int mouseY, float partialTicks) {
        this.drawNpc(entity, mouseX, mouseY, partialTicks);
    }

    public void drawNpc(EntityLivingBase entity, int mouseX, int mouseY, float partialTicks) {
        if (this.hasSubGui() && !this.drawNPConSub) {
            return;
        }
        if (this.drawRenderButtons) {
            this.rotateLeft.func_146112_a(this.field_146297_k, mouseX, mouseY);
            this.rotateRight.func_146112_a(this.field_146297_k, mouseX, mouseY);
            this.zoomOut.func_146112_a(this.field_146297_k, mouseX, mouseY);
            this.zoomIn.func_146112_a(this.field_146297_k, mouseX, mouseY);
        }
        if (Mouse.isButtonDown((int)0) && this.drawRenderButtons) {
            if (this.rotateLeft.func_146116_c(this.field_146297_k, mouseX, mouseY)) {
                this.rotation += partialTicks * 1.5f;
            } else if (this.rotateRight.func_146116_c(this.field_146297_k, mouseX, mouseY)) {
                this.rotation -= partialTicks * 1.5f;
            } else if (this.zoomOut.func_146116_c(this.field_146297_k, mouseX, mouseY) && this.zoom < this.maxZoom) {
                this.zoom += partialTicks * 0.05f;
            } else if (this.zoomIn.func_146116_c(this.field_146297_k, mouseX, mouseY) && this.zoom > this.minZoom) {
                this.zoom -= partialTicks * 0.05f;
            }
        }
        if (this.isMouseOverRenderer(mouseX, mouseY)) {
            this.zoom += (float)Mouse.getDWheel() * 0.001f;
            if (Mouse.isButtonDown((int)0)) {
                this.rotation -= (float)Mouse.getDX() * 0.75f;
            } else if (Mouse.isButtonDown((int)1)) {
                this.rotation = 0.0f;
                this.zoom = this.defaultZoom;
            }
        }
        if (this.zoom > this.maxZoom) {
            this.zoom = this.maxZoom;
        }
        if (this.zoom < this.minZoom) {
            this.zoom = this.minZoom;
        }
        this.drawNpc(entity, mouseX, mouseY, this.xOffsetNpc, this.yOffsetNpc, this.zoom, this.rotation, partialTicks);
    }

    public void drawNpc(EntityLivingBase entity, int mouseX, int mouseY, int x, int y, float zoomed, float rotation, float partialTicks) {
        EntityNPCInterface npc = null;
        if (entity instanceof EntityNPCInterface) {
            npc = (EntityNPCInterface)entity;
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (npc != null) {
            npc.isDrawn = true;
        }
        GL11.glEnable((int)2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(this.guiLeft + x), (float)(this.guiTop + y), (float)90.0f);
        float scale = 1.0f;
        if ((double)entity.field_70131_O > 2.4) {
            scale = 2.0f / entity.field_70131_O;
        }
        GL11.glScalef((float)(-30.0f * scale * zoomed), (float)(30.0f * scale * zoomed), (float)(30.0f * scale * zoomed));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        float f2 = entity.field_70761_aq;
        float f3 = entity.field_70177_z;
        float f4 = entity.field_70125_A;
        float f7 = entity.field_70759_as;
        float f5 = (float)(this.guiLeft + x) - (float)mouseX;
        float f6 = (float)(this.guiTop + y) - 50.0f * scale * zoomed - (float)mouseY;
        int orientation = 0;
        if (npc != null) {
            orientation = npc.ais.orientation;
            npc.ais.orientation = (int)rotation;
        }
        GL11.glRotatef((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-((float)Math.atan(f6 / 400.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        entity.field_70761_aq = rotation;
        entity.field_70177_z = this.followMouse ? (float)Math.atan(f5 / 80.0f) * 40.0f + rotation : 0.0f;
        entity.field_70125_A = this.followMouse ? -((float)Math.atan(f6 / 40.0f)) * 20.0f : 0.0f;
        entity.field_70759_as = entity.field_70177_z;
        GL11.glTranslatef((float)0.0f, (float)entity.field_70129_M, (float)0.0f);
        RenderManager.field_78727_a.field_78735_i = 180.0f;
        ClientEventHandler.renderingEntityInGUI = true;
        try {
            RenderManager.field_78727_a.func_147940_a((Entity)entity, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        }
        catch (Exception exception) {
            // empty catch block
        }
        ClientEventHandler.renderingEntityInGUI = false;
        entity.field_70760_ar = entity.field_70761_aq = f2;
        entity.field_70126_B = entity.field_70177_z = f3;
        entity.field_70127_C = entity.field_70125_A = f4;
        entity.field_70758_at = entity.field_70759_as = f7;
        if (npc != null) {
            npc.ais.orientation = orientation;
        }
        GL11.glPopMatrix();
        RenderHelper.func_74518_a();
        GL11.glDisable((int)32826);
        if (npc != null) {
            npc.isDrawn = false;
        }
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
        GL11.glDisable((int)3553);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
    }

    public void renderHoveringText(List textLines, int x, int y, FontRenderer font) {
        this.drawHoveringText(textLines, x, y, font);
    }

    public void openLink(String link) {
        try {
            Class<?> oclass = Class.forName("java.awt.Desktop");
            Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
            oclass.getMethod("browse", URI.class).invoke(object, new URI(link));
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }
}

