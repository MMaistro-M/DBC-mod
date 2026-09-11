/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util.key;

import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.gui.util.GuiUtil;
import noppes.npcs.client.key.KeyPreset;
import noppes.npcs.client.key.KeyPresetManager;
import noppes.npcs.client.util.Color;
import noppes.npcs.util.ValueUtil;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class OverlayKeyPresetViewer {
    public static final ResourceLocation TEXTURE = new ResourceLocation("customnpcs:textures/gui/keypreset_highres.png");
    public int startX;
    public int startY;
    public int endX;
    public int endY;
    public int width;
    public int height;
    public int mouseX;
    public int mouseY;
    public boolean showOverlay;
    public boolean aboveButton;
    public boolean aboveOverlay;
    public int elementSpacing = 5;
    public int yStartSpacing = 5;
    public float scale = 0.75f;
    public Scrollable scroll = new Scrollable();
    public KeyPresetManager manager;
    public OverlayButton viewButton = new OverlayButton();
    public LinkedList<PresetElement> list = new LinkedList();
    public float RELATIVE_MAX_DESC_WIDTH = 0.5f;
    private final FontRenderer font;
    public int bgCol1;
    public int bgCol2;
    public int borderCol1;
    public int borderCol2;
    public boolean hasBorder;
    public boolean openOnClick;

    public OverlayKeyPresetViewer(KeyPresetManager manager) {
        this.font = Minecraft.func_71410_x().field_71466_p;
        this.bgCol1 = -586149872;
        this.bgCol2 = -131059664;
        this.borderCol1 = 0x22FFFFFF;
        this.borderCol2 = -1;
        this.hasBorder = true;
        this.manager = manager;
        manager.keys.forEach(key -> {
            PresetElement element = new PresetElement((KeyPreset)key);
            this.list.add(element);
        });
    }

    public void initGui(int startX, int startY, int width, int height) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        this.endX = startX + width;
        this.endY = startY + height;
        this.scroll.init();
    }

    public void draw(int mouseX, int mouseY, int wheel) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.aboveButton = this.viewButton.isMouseAbove(mouseX, mouseY);
        this.aboveOverlay = this.isMouseAbove(mouseX, mouseY);
        if (this.aboveButton && !this.openOnClick) {
            this.showOverlay = true;
        }
        if (this.showOverlay) {
            this.drawOverlay(wheel);
            if (!(this.openOnClick || this.aboveOverlay || this.aboveButton)) {
                this.showOverlay = false;
            }
        }
        this.viewButton.drawButton();
    }

    public void drawOverlay(int wheel) {
        this.scroll.update(wheel);
        if (this.hasBorder) {
            GuiUtil.drawGradientRectHorizontal(this.startX - 1, this.startY - 1, this.endX + 1, this.startY, this.borderCol2, this.borderCol1);
            GuiUtil.drawGradientRectHorizontal(this.startX - 1, this.endY, this.endX + 1, this.endY + 1, this.borderCol1, this.borderCol2);
            GuiUtil.drawGradientRect(this.startX - 1, this.startY - 1, this.startX, this.endY, this.borderCol2, this.borderCol1);
            GuiUtil.drawGradientRect(this.endX, this.startY, this.endX + 1, this.endY, this.borderCol1, this.borderCol2);
        }
        GuiUtil.drawGradientRect(this.startX, this.startY, this.endX, this.endY, this.bgCol1, this.bgCol2);
        GL11.glEnable((int)3089);
        GuiUtil.setScissorClip(this.startX, this.startY, this.scroll.maxScroll > 0.0f ? this.width - this.scroll.barWidth : this.width, this.height);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(this.startX + 2), (float)((float)(this.startY + this.yStartSpacing) - this.scroll.scrollY), (float)0.0f);
        int height = 0;
        for (int i = 0; i < this.list.size(); ++i) {
            PresetElement element = this.list.get(i);
            GL11.glPushMatrix();
            GL11.glScalef((float)this.scale, (float)this.scale, (float)1.0f);
            GL11.glTranslatef((float)0.0f, (float)Math.round((float)height / this.scale), (float)0.0f);
            element.drawDescription();
            GL11.glPopMatrix();
            element.drawBox(height);
            height += element.getHeight();
        }
        GL11.glPopMatrix();
        GL11.glDisable((int)3089);
        if (this.scroll.maxScroll > 0.0f) {
            this.scroll.drawBar();
        }
    }

    public boolean keyTyped(char c, int i) {
        if (!this.showOverlay) {
            return false;
        }
        for (PresetElement element : this.list) {
            if (!element.isEditing) continue;
            element.keyTyped(i);
            return true;
        }
        if (i == 1) {
            this.showOverlay = false;
            return true;
        }
        return true;
    }

    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        boolean isAboveButton = this.viewButton.isMouseAbove(mouseX, mouseY);
        if (isAboveButton) {
            if (this.openOnClick) {
                this.showOverlay = !this.showOverlay;
                return true;
            }
            this.showOverlay = true;
            return true;
        }
        if (!this.showOverlay) {
            return false;
        }
        boolean isAboveOverlay = this.isMouseAbove(mouseX, mouseY);
        if (this.openOnClick && !isAboveOverlay && !isAboveButton) {
            this.showOverlay = false;
            return true;
        }
        boolean consumed = isAboveOverlay;
        for (PresetElement element : this.list) {
            if (!element.isMouseAboveBox(mouseX, mouseY)) {
                if (element.isEditing) {
                    element.cancelEdit();
                    consumed = true;
                }
            } else {
                element.boxClicked(button);
                consumed = true;
            }
            if (!element.isMouseAboveReset(mouseX, mouseY) || element.key.isDefault()) continue;
            element.key.defaultState.writeTo(element.key.currentState);
            this.manager.save();
            consumed = true;
        }
        return consumed;
    }

    public boolean isMouseAbove(int mouseX, int mouseY) {
        if (this.scroll.isMouseDragging) {
            return true;
        }
        return mouseX >= this.startX && mouseX < this.endX && mouseY >= this.startY && mouseY < this.endY;
    }

    public int getWidth() {
        return (int)((float)this.width / this.scale);
    }

    public boolean isVisible() {
        return this.showOverlay;
    }

    public class OverlayButton {
        public int startX;
        public int startY;
        public int endX;
        public int width;
        public int height;
        public int textureWidth = 252;
        public int textureHeight = 109;
        public float scale = 0.25f;

        public void initGui(int endX, int startY) {
            this.endX = endX;
            this.startY = startY;
            this.startX = (int)((float)endX - (float)this.textureWidth * this.scale / 2.0f);
        }

        public void drawButton() {
            Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE);
            double screenX = (float)this.startX / this.scale;
            double screenY = (float)this.startY / this.scale;
            int oldMinFilter = GL11.glGetTexParameteri((int)3553, (int)10241);
            GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
            GL11.glDisable((int)3042);
            GL11.glPushMatrix();
            float color = OverlayKeyPresetViewer.this.aboveButton ? 1.0f : 0.4f;
            GL11.glColor4f((float)color, (float)color, (float)color, (float)1.0f);
            GL11.glScalef((float)this.scale, (float)this.scale, (float)1.0f);
            GuiUtil.drawTexturedModalRect(screenX, screenY, this.textureWidth, this.textureHeight, 0, 0);
            GL11.glPopMatrix();
            GL11.glEnable((int)3042);
            GL11.glTexParameteri((int)3553, (int)10241, (int)oldMinFilter);
        }

        public boolean isMouseAbove(int mouseX, int mouseY) {
            return mouseX >= this.startX && (float)mouseX < (float)this.startX + (float)this.textureWidth * this.scale / 2.0f && mouseY >= this.startY - 4 && (float)mouseY < (float)this.startY + (float)this.textureHeight * this.scale / 2.0f;
        }
    }

    public class PresetElement {
        public KeyPreset key;
        public boolean isEditing;
        public float boxScreenX;
        public float boxScreenY;
        public KeyPreset.KeyState newState = new KeyPreset.KeyState();

        public PresetElement(KeyPreset key) {
            this.key = key;
        }

        public void drawDescription() {
            boolean hasDescription = this.key.description != null;
            String name = String.format("> %s", this.key.name);
            OverlayKeyPresetViewer.this.font.func_78276_b(name, 0, hasDescription ? 0 : 10, 0xFFFFFF);
            if (!this.key.shouldConflict) {
                OverlayKeyPresetViewer.this.font.func_78276_b("(Conflict-free)", OverlayKeyPresetViewer.this.font.func_78256_a(name) + 5, 0, 16436800);
            }
            if (hasDescription) {
                String description = String.format("- %s", this.key.description);
                OverlayKeyPresetViewer.this.font.func_78279_b(description, 9, ((OverlayKeyPresetViewer)OverlayKeyPresetViewer.this).font.field_78288_b + 3, this.getMaxStringWidth() - 9, 0x888888);
            }
        }

        public void drawBox(int offsetY) {
            Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE);
            GL11.glPushMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            float boxScaleX = 1.75f;
            float boxWidth = 32.0f * boxScaleX;
            float desiredBoxX = (float)this.getMaxStringWidth() * OverlayKeyPresetViewer.this.scale + 8.0f;
            float resetWidth = 10.0f;
            float contentAvailable = (float)(OverlayKeyPresetViewer.this.scroll.maxScroll > 0.0f ? OverlayKeyPresetViewer.this.width - OverlayKeyPresetViewer.this.scroll.barWidth : OverlayKeyPresetViewer.this.width) - 4.0f;
            float maxBoxX = Math.max(0.0f, contentAvailable - boxWidth - resetWidth - 4.0f);
            this.boxScreenX = Math.min(desiredBoxX, maxBoxX);
            GL11.glScalef((float)boxScaleX, (float)1.0f, (float)0.0f);
            if (this.isEditing) {
                new Color(4682419, 1.0f).glColor();
            } else if (this.isMouseAboveBox(OverlayKeyPresetViewer.this.mouseX, OverlayKeyPresetViewer.this.mouseY)) {
                new Color(0x656565, 1.0f).glColor();
            } else {
                new Color(0x545454, 1.0f).glColor();
            }
            this.boxScreenY = offsetY - 5;
            GuiUtil.drawTexturedModalRect(this.boxScreenX / boxScaleX, this.boxScreenY, 32.0, 20.0, 0, 492);
            GL11.glPopMatrix();
            boolean isDefault = this.key.isDefault();
            resetWidth = 10.0f;
            float resetScreenX = this.boxScreenX + boxWidth + 2.0f;
            if (this.isMouseAboveReset(OverlayKeyPresetViewer.this.mouseX, OverlayKeyPresetViewer.this.mouseY) && !isDefault) {
                new Color(0x656565, 1.0f).glColor();
            } else {
                new Color(0x545454, 1.0f).glColor();
            }
            GuiUtil.drawTexturedModalRect(resetScreenX, this.boxScreenY, 10.0, 20.0, 33, 492);
            String name = this.getBoxKeyName();
            float nameWidth = OverlayKeyPresetViewer.this.font.func_78256_a(name);
            float nameScale = OverlayKeyPresetViewer.this.scale;
            float nameBoxRatio = nameWidth * OverlayKeyPresetViewer.this.scale / boxWidth;
            GL11.glPushMatrix();
            if ((double)nameBoxRatio > 0.9) {
                nameScale /= nameWidth / boxWidth / 1.2f;
                GL11.glTranslatef((float)0.0f, (float)(nameBoxRatio * 0.5f), (float)0.0f);
            }
            float nameX = this.boxScreenX + boxWidth / 2.0f - nameWidth / 2.0f * nameScale;
            float nameY = (float)offsetY + 11.5f - (float)(((OverlayKeyPresetViewer)OverlayKeyPresetViewer.this).font.field_78288_b / 2);
            GL11.glScalef((float)nameScale, (float)nameScale, (float)1.0f);
            GL11.glTranslatef((float)(nameX / nameScale), (float)(nameY / nameScale), (float)1.0f);
            OverlayKeyPresetViewer.this.font.func_78261_a(name, 0, 0, this.conflicts() ? 0xFF5555 : -1);
            GL11.glPopMatrix();
            char letter = 'X';
            float letterX = resetScreenX + resetWidth / 2.0f - (float)(OverlayKeyPresetViewer.this.font.func_78263_a(letter) / 2) * OverlayKeyPresetViewer.this.scale + 0.25f;
            GL11.glPushMatrix();
            GL11.glScalef((float)OverlayKeyPresetViewer.this.scale, (float)OverlayKeyPresetViewer.this.scale, (float)1.0f);
            GL11.glTranslatef((float)(letterX / OverlayKeyPresetViewer.this.scale), (float)(nameY / OverlayKeyPresetViewer.this.scale), (float)1.0f);
            OverlayKeyPresetViewer.this.font.func_78261_a("" + letter, 0, 0, isDefault ? -7697782 : -1);
            GL11.glPopMatrix();
        }

        public void boxClicked(int button) {
            if (this.isEditing) {
                this.setKey(-100 + button);
            } else if (button == 0) {
                this.isEditing = true;
            }
        }

        public void keyTyped(int typedKey) {
            if (typedKey == 1) {
                this.setKey(0);
                return;
            }
            this.newState.setState(this.newState.keyCode, KeyPreset.isCtrlKeyDown(), KeyPreset.isAltKeyDown(), KeyPreset.isShiftKeyDown());
            if (KeyPreset.isNotCtrlAltShift(typedKey)) {
                this.setKey(typedKey);
            }
        }

        public void setKey(int keyCode) {
            if (keyCode == 0) {
                this.newState.clear();
            }
            this.newState.keyCode = keyCode;
            this.newState.writeTo(this.key.currentState);
            this.cancelEdit();
            OverlayKeyPresetViewer.this.manager.save();
        }

        public void cancelEdit() {
            this.newState.clear();
            this.isEditing = false;
        }

        public boolean conflicts() {
            return OverlayKeyPresetViewer.this.list.stream().anyMatch(element -> this.key.shouldConflict && element.key.shouldConflict && element.key != this.key && element.key.equals(this.key));
        }

        public String getBoxKeyName() {
            if (this.isEditing) {
                String modifiers = "";
                if (KeyPreset.isCtrlKeyDown()) {
                    modifiers = modifiers + "CTRL ";
                }
                if (KeyPreset.isAltKeyDown()) {
                    modifiers = modifiers + "ALT ";
                }
                if (KeyPreset.isShiftKeyDown()) {
                    modifiers = modifiers + "SHIFT ";
                }
                return !modifiers.isEmpty() ? modifiers : "Press a key";
            }
            return this.key.currentState.getName();
        }

        public boolean isMouseAboveBox(int mouseX, int mouseY) {
            mouseY = (int)((float)mouseY - ((float)(OverlayKeyPresetViewer.this.startY + 5) - OverlayKeyPresetViewer.this.scroll.scrollY));
            float boxScaleX = 1.75f;
            float screenY = this.boxScreenY + 5.0f;
            return (float)(mouseX -= OverlayKeyPresetViewer.this.startX + 2) >= this.boxScreenX && (float)mouseX < this.boxScreenX + 32.0f * boxScaleX && (float)mouseY >= screenY && (float)mouseY < screenY + 10.0f;
        }

        public boolean isMouseAboveReset(int mouseX, int mouseY) {
            mouseX -= OverlayKeyPresetViewer.this.startX + 2;
            mouseY = (int)((float)mouseY - ((float)(OverlayKeyPresetViewer.this.startY + 5) - OverlayKeyPresetViewer.this.scroll.scrollY));
            float boxScaleX = 1.75f;
            float resetWidth = 10.0f;
            float boxWidth = 32.0f * boxScaleX;
            float screenX = this.boxScreenX + boxWidth + 2.0f;
            float screenY = this.boxScreenY + 10.0f;
            return (float)mouseX >= screenX && (float)mouseX < screenX + resetWidth && (float)mouseY >= screenY && (float)mouseY < screenY + 10.0f;
        }

        public int getHeight() {
            List wrappedDescription = OverlayKeyPresetViewer.this.font.func_78271_c(String.format("- %s", this.key.description), this.getMaxStringWidth());
            int nameHeight = ((OverlayKeyPresetViewer)OverlayKeyPresetViewer.this).font.field_78288_b;
            int translations = ((OverlayKeyPresetViewer)OverlayKeyPresetViewer.this).font.field_78288_b;
            int descriptionHeight = wrappedDescription.size() * ((OverlayKeyPresetViewer)OverlayKeyPresetViewer.this).font.field_78288_b;
            return Math.round((float)(nameHeight + descriptionHeight + translations) * OverlayKeyPresetViewer.this.scale) + OverlayKeyPresetViewer.this.elementSpacing;
        }

        public int getMaxStringWidth() {
            return (int)Math.max((float)OverlayKeyPresetViewer.this.getWidth() * OverlayKeyPresetViewer.this.RELATIVE_MAX_DESC_WIDTH, 0.0f);
        }
    }

    public class Scrollable {
        private float scrollY;
        private float targetScrollY;
        private float maxScroll;
        private int totalHeight;
        private int scrollbarHeight;
        private int barWidth = 2;
        private float heightFactor;
        private float scrollFactor;
        private boolean isMouseDragging;
        private int startDragY;

        public void init() {
            this.totalHeight = 0;
            for (PresetElement element : OverlayKeyPresetViewer.this.list) {
                this.totalHeight += element.getHeight();
            }
            float contentTotal = this.totalHeight + OverlayKeyPresetViewer.this.yStartSpacing;
            this.maxScroll = Math.max(0.0f, contentTotal - (float)OverlayKeyPresetViewer.this.height);
            this.heightFactor = contentTotal > 0.0f ? (float)OverlayKeyPresetViewer.this.height / contentTotal : 1.0f;
            this.scrollbarHeight = Math.max((int)((float)OverlayKeyPresetViewer.this.height * this.heightFactor), 20);
            this.scrollFactor = this.maxScroll > 0.0f ? (float)(OverlayKeyPresetViewer.this.height - OverlayKeyPresetViewer.this.yStartSpacing - this.scrollbarHeight) / this.maxScroll : 0.0f;
        }

        public void update(int wheel) {
            if (wheel != 0 && OverlayKeyPresetViewer.this.isMouseAbove(OverlayKeyPresetViewer.this.mouseX, OverlayKeyPresetViewer.this.mouseY)) {
                this.targetScrollY = ValueUtil.clamp(this.targetScrollY - (float)(wheel / 15), 0.0f, this.maxScroll);
            }
            if (Mouse.isButtonDown((int)0)) {
                if (!this.isMouseDragging && this.isMouseAboveBar(OverlayKeyPresetViewer.this.mouseX, OverlayKeyPresetViewer.this.mouseY)) {
                    this.isMouseDragging = true;
                    this.startDragY = (int)((float)OverlayKeyPresetViewer.this.mouseY - this.scrollY * this.scrollFactor);
                }
            } else {
                this.isMouseDragging = false;
            }
            if (this.isMouseDragging) {
                this.scrollY = this.targetScrollY = ValueUtil.clamp((float)(OverlayKeyPresetViewer.this.mouseY - this.startDragY) / this.scrollFactor, 0.0f, this.maxScroll);
            }
            if (this.scrollY != this.targetScrollY) {
                this.scrollY = ValueUtil.lerp(this.scrollY, this.targetScrollY, 0.1f);
                if ((double)Math.abs(this.scrollY - this.targetScrollY) < 0.001) {
                    this.scrollY = this.targetScrollY;
                }
            }
            this.scrollY = ValueUtil.clamp(this.scrollY, 0.0f, this.maxScroll);
        }

        public void drawBar() {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(OverlayKeyPresetViewer.this.endX - this.barWidth - 2), (float)((float)(OverlayKeyPresetViewer.this.startY + OverlayKeyPresetViewer.this.yStartSpacing) + this.scrollY * this.scrollFactor), (float)1.0f);
            GuiUtil.drawRectD(0.0, 1.0, this.barWidth, this.scrollbarHeight - 1, this.isMouseAboveBar(OverlayKeyPresetViewer.this.mouseX, OverlayKeyPresetViewer.this.mouseY) || this.isMouseDragging ? -4539718 : -9013642);
            GL11.glPopMatrix();
        }

        public boolean isMouseAboveBar(int mouseX, int mouseY) {
            int barX = OverlayKeyPresetViewer.this.endX - this.barWidth - 2;
            int barY = (int)((float)(OverlayKeyPresetViewer.this.startY + OverlayKeyPresetViewer.this.yStartSpacing) + this.scrollY * this.scrollFactor);
            return mouseX >= barX && mouseX < barX + this.barWidth && mouseY >= barY && mouseY < barY + this.scrollbarHeight;
        }
    }
}

