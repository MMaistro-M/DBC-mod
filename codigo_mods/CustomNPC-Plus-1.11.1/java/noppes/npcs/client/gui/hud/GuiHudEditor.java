/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  org.lwjgl.input.Mouse
 */
package noppes.npcs.client.gui.hud;

import java.util.ArrayList;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import noppes.npcs.client.gui.hud.ClientHudManager;
import noppes.npcs.client.gui.hud.CompassHudComponent;
import noppes.npcs.client.gui.hud.EnumHudComponent;
import noppes.npcs.client.gui.hud.HudComponent;
import org.lwjgl.input.Mouse;

public class GuiHudEditor
extends GuiScreen {
    private final GuiScreen parent;
    private HudComponent selectedComponent;
    private int dragOffsetX;
    private int dragOffsetY;
    private boolean dragging = false;
    private boolean resizing = false;
    private int initialScale;
    private int initialMouseX;
    private int initialMouseY;
    private boolean resizingWidth = false;
    private int initialOverlayWidth;
    private int initialWidthMouseX;
    private final int HANDLE_SIZE = 10;
    private final int RESET_POSITION_ID = 100;
    private final int RESET_SIZE_ID = 104;
    private final int CYCLE_LEFT_ID = 101;
    private final int CYCLE_RIGHT_ID = 102;
    private final int COMPONENT_LABEL_ID = 103;

    public GuiHudEditor(GuiScreen parent) {
        this.parent = parent;
        this.selectedComponent = ClientHudManager.getInstance().getHudComponents().get((Object)EnumHudComponent.QuestTracker);
    }

    public void func_73866_w_() {
        this.field_146292_n.clear();
        this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - 60, this.field_146295_m - 45, 120, 20, "Save and Close"));
        this.updateCustomButtons();
    }

    private void updateCustomButtons() {
        for (int i = this.field_146292_n.size() - 1; i >= 0; --i) {
            GuiButton btn = (GuiButton)this.field_146292_n.get(i);
            if (btn.field_146127_k == 0 || btn.field_146127_k == 101 || btn.field_146127_k == 102 || btn.field_146127_k == 103) continue;
            this.field_146292_n.remove(i);
        }
        if (this.selectedComponent != null) {
            ArrayList<GuiButton> customButtons = new ArrayList<GuiButton>();
            this.selectedComponent.addEditorButtons(customButtons);
            int spacingX = 10;
            int spacingY = 5;
            int buttonWidth = 120;
            int startXLeft = this.field_146294_l / 2 - buttonWidth - spacingX / 2;
            int startXRight = this.field_146294_l / 2 + spacingX / 2;
            int rowHeight = 0;
            int numButtons = customButtons.size();
            for (int i = 0; i < numButtons; i += 2) {
                if (i + 1 < numButtons) {
                    int rowY;
                    GuiButton btnLeft = (GuiButton)customButtons.get(i);
                    GuiButton btnRight = (GuiButton)customButtons.get(i + 1);
                    btnLeft.field_146128_h = startXLeft;
                    btnRight.field_146128_h = startXRight;
                    btnLeft.field_146129_i = rowY = this.field_146295_m - 70 - rowHeight;
                    btnRight.field_146129_i = rowY;
                    rowHeight += btnLeft.field_146121_g + spacingY;
                    this.field_146292_n.add(btnLeft);
                    this.field_146292_n.add(btnRight);
                    continue;
                }
                GuiButton btn = (GuiButton)customButtons.get(i);
                btn.field_146128_h = this.field_146294_l / 2 - buttonWidth / 2;
                btn.field_146129_i = this.field_146295_m - 70 - rowHeight;
                this.field_146292_n.add(btn);
                rowHeight += btn.field_146121_g + spacingY;
            }
            GuiButton resetSize = new GuiButton(104, startXRight, this.field_146295_m - 70 - rowHeight, buttonWidth, 20, "Reset Size");
            GuiButton resetPos = new GuiButton(100, startXLeft, this.field_146295_m - 70 - rowHeight, buttonWidth, 20, "Reset Position");
            this.field_146292_n.add(resetPos);
            this.field_146292_n.add(resetSize);
        }
        int groupWidth = 160;
        int groupX = (this.field_146294_l - groupWidth) / 2;
        int cycleY = this.field_146295_m - 20;
        boolean hasLeft = false;
        boolean hasRight = false;
        boolean hasLabel = false;
        for (Object button : this.field_146292_n) {
            GuiButton btn = (GuiButton)button;
            if (btn.field_146127_k == 101) {
                hasLeft = true;
                continue;
            }
            if (btn.field_146127_k == 102) {
                hasRight = true;
                continue;
            }
            if (btn.field_146127_k != 103) continue;
            btn.field_146126_j = this.selectedComponent != null ? this.selectedComponent.getClass().getSimpleName().replace("Component", "") : "None";
            hasLabel = true;
        }
        if (!hasLeft) {
            this.field_146292_n.add(new GuiButton(101, groupX, cycleY, 20, 20, "<"));
        }
        if (!hasLabel) {
            GuiButton labelBtn = new GuiButton(103, groupX + 20, cycleY, 120, 20, this.selectedComponent != null ? this.selectedComponent.getClass().getSimpleName().replace("Component", "") : "None");
            labelBtn.field_146124_l = false;
            this.field_146292_n.add(labelBtn);
        }
        if (!hasRight) {
            this.field_146292_n.add(new GuiButton(102, groupX + 140, cycleY, 20, 20, ">"));
        }
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        this.func_146276_q_();
        for (HudComponent hud : ClientHudManager.getInstance().getHudComponents().values()) {
            hud.renderEditing();
        }
        super.func_73863_a(mouseX, mouseY, partialTicks);
    }

    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) {
        int actualY;
        int actualX;
        float effectiveScale;
        ScaledResolution res;
        for (HudComponent hud : ClientHudManager.getInstance().getHudComponents().values()) {
            if (!(hud instanceof CompassHudComponent)) continue;
            res = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
            effectiveScale = hud.getEffectiveScale(res);
            actualX = (int)(hud.posX / 100.0f * (float)res.func_78326_a());
            actualY = (int)(hud.posY / 100.0f * (float)res.func_78328_b());
            int margin = 5;
            int barWidth = 6;
            int barX = actualX + (int)((float)(hud.overlayWidth + margin) * effectiveScale);
            int barY = actualY + (int)((float)(hud.overlayHeight - 20) / 2.0f * effectiveScale);
            int barHeight = (int)(20.0f * effectiveScale);
            int scaledBarWidth = Math.max(6, (int)((float)barWidth * effectiveScale));
            if (mouseX < barX || mouseX > barX + scaledBarWidth || mouseY < barY || mouseY > barY + barHeight) continue;
            this.resizingWidth = true;
            this.selectedComponent = hud;
            this.initialOverlayWidth = hud.overlayWidth;
            this.initialWidthMouseX = mouseX;
            this.updateCustomButtons();
            return;
        }
        for (HudComponent hud : ClientHudManager.getInstance().getHudComponents().values()) {
            res = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
            effectiveScale = hud.getEffectiveScale(res);
            actualX = (int)(hud.posX / 100.0f * (float)res.func_78326_a());
            actualY = (int)(hud.posY / 100.0f * (float)res.func_78328_b());
            int absWidth = (int)((float)hud.overlayWidth * effectiveScale);
            int absHeight = (int)((float)hud.overlayHeight * effectiveScale);
            if (mouseX < actualX || mouseX > actualX + absWidth || mouseY < actualY || mouseY > actualY + absHeight) continue;
            int scaledHandle = Math.max(8, (int)(10.0f * effectiveScale));
            if (mouseX >= actualX + absWidth - scaledHandle && mouseY >= actualY + absHeight - scaledHandle) {
                this.resizing = true;
                this.selectedComponent = hud;
                this.initialScale = hud.scale;
                this.initialMouseX = mouseX;
                this.initialMouseY = mouseY;
                this.updateCustomButtons();
                return;
            }
            this.dragging = true;
            this.selectedComponent = hud;
            this.dragOffsetX = mouseX - actualX;
            this.dragOffsetY = mouseY - actualY;
            this.updateCustomButtons();
            return;
        }
        super.func_73864_a(mouseX, mouseY, mouseButton);
    }

    protected void func_146286_b(int mouseX, int mouseY, int state) {
        this.dragging = false;
        this.resizing = false;
        this.resizingWidth = false;
        super.func_146286_b(mouseX, mouseY, state);
    }

    public void func_73876_c() {
        if (this.selectedComponent != null) {
            int currentMouseX;
            ScaledResolution res = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
            int scaledWidth = res.func_78326_a();
            int scaledHeight = res.func_78328_b();
            float effectiveScale = this.selectedComponent.getEffectiveScale(res);
            if (this.dragging) {
                int newX = Mouse.getX() * this.field_146294_l / this.field_146297_k.field_71443_c - this.dragOffsetX;
                int newY = scaledHeight - Mouse.getY() * scaledHeight / this.field_146297_k.field_71440_d - this.dragOffsetY;
                if (newX < 0) {
                    newX = 0;
                }
                if (newY < 0) {
                    newY = 0;
                }
                int compW = (int)Math.ceil((float)this.selectedComponent.overlayWidth * effectiveScale);
                int compH = (int)Math.ceil((float)this.selectedComponent.overlayHeight * effectiveScale);
                if (newX + compW > scaledWidth) {
                    newX = scaledWidth - compW;
                }
                if (newY + compH > scaledHeight) {
                    newY = scaledHeight - compH;
                }
                this.selectedComponent.posX = 100.0f * (float)newX / (float)scaledWidth;
                this.selectedComponent.posY = 100.0f * (float)newY / (float)scaledHeight;
            }
            if (this.resizing) {
                currentMouseX = Mouse.getX() * this.field_146294_l / this.field_146297_k.field_71443_c;
                int currentMouseY = scaledHeight - Mouse.getY() * scaledHeight / this.field_146297_k.field_71440_d;
                int actualX = (int)(this.selectedComponent.posX / 100.0f * (float)scaledWidth);
                int actualY = (int)(this.selectedComponent.posY / 100.0f * (float)scaledHeight);
                float initDist = (float)Math.sqrt((double)(this.initialMouseX - actualX) * (double)(this.initialMouseX - actualX) + (double)(this.initialMouseY - actualY) * (double)(this.initialMouseY - actualY));
                float currDist = (float)Math.sqrt((double)(currentMouseX - actualX) * (double)(currentMouseX - actualX) + (double)(currentMouseY - actualY) * (double)(currentMouseY - actualY));
                if (initDist > 1.0f) {
                    this.selectedComponent.scale = (int)((float)this.initialScale * currDist / initDist);
                }
                if (this.selectedComponent.scale < 50) {
                    this.selectedComponent.scale = 50;
                }
                if (this.selectedComponent.scale > 500) {
                    this.selectedComponent.scale = 500;
                }
            }
            if (this.resizingWidth && this.selectedComponent instanceof CompassHudComponent) {
                currentMouseX = Mouse.getX() * this.field_146294_l / this.field_146297_k.field_71443_c;
                int delta = currentMouseX - this.initialWidthMouseX;
                this.selectedComponent.overlayWidth = this.initialOverlayWidth + (int)((float)delta / effectiveScale);
                if (this.selectedComponent.overlayWidth < 50) {
                    this.selectedComponent.overlayWidth = 50;
                }
                if (this.selectedComponent.overlayWidth > 500) {
                    this.selectedComponent.overlayWidth = 500;
                }
            }
        }
        super.func_73876_c();
    }

    protected void func_146284_a(GuiButton button) {
        if (button.field_146127_k == 0) {
            this.close();
        } else if (button.field_146127_k == 100) {
            if (this.selectedComponent != null) {
                int compHeight;
                int compWidth;
                ScaledResolution res = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
                float effectiveScale = this.selectedComponent.getEffectiveScale(res);
                if (this.selectedComponent instanceof CompassHudComponent) {
                    int margin = 5;
                    int widthBar = 6;
                    compWidth = (int)((float)(this.selectedComponent.overlayWidth + margin + widthBar) * effectiveScale);
                    compHeight = (int)((float)this.selectedComponent.overlayHeight * effectiveScale);
                } else {
                    compWidth = (int)((float)this.selectedComponent.overlayWidth * effectiveScale);
                    compHeight = (int)((float)this.selectedComponent.overlayHeight * effectiveScale);
                }
                int centerX = (res.func_78326_a() - compWidth) / 2;
                int centerY = (res.func_78328_b() - compHeight) / 2;
                this.selectedComponent.posX = 100.0f * (float)centerX / (float)res.func_78326_a();
                this.selectedComponent.posY = 100.0f * (float)centerY / (float)res.func_78328_b();
                this.updateCustomButtons();
            }
        } else if (button.field_146127_k == 104) {
            if (this.selectedComponent != null) {
                this.selectedComponent.scale = 100;
                this.updateCustomButtons();
            }
        } else if (button.field_146127_k == 101) {
            this.cycleComponent(-1);
        } else if (button.field_146127_k == 102) {
            this.cycleComponent(1);
        } else if (this.selectedComponent != null) {
            this.selectedComponent.onEditorButtonPressed(button);
            this.updateCustomButtons();
        }
    }

    private void cycleComponent(int direction) {
        this.dragging = false;
        this.resizing = false;
        this.resizingWidth = false;
        ArrayList<HudComponent> components = new ArrayList<HudComponent>(ClientHudManager.getInstance().getHudComponents().values());
        if (components.isEmpty()) {
            return;
        }
        int index = components.indexOf(this.selectedComponent);
        if (index < 0) {
            index = 0;
        }
        if ((index = (index + direction) % components.size()) < 0) {
            index += components.size();
        }
        this.selectedComponent = (HudComponent)components.get(index);
        this.updateCustomButtons();
    }

    protected void func_73869_a(char typedChar, int keyCode) {
        if (keyCode == 1) {
            this.close();
        }
    }

    public void close() {
        for (HudComponent hud : ClientHudManager.getInstance().getHudComponents().values()) {
            hud.isEditting = false;
            hud.save();
        }
        this.field_146297_k.func_147108_a(this.parent);
    }
}

