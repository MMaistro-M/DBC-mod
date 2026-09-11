/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util;

import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiUtil;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.ISubGuiListener;

public abstract class GuiDirectory
extends GuiNPCInterface
implements ICustomScrollListener,
ISubGuiListener {
    protected float leftPanelPercent = 0.2f;
    protected float rightPanelPercent = 0.1f;
    protected int minLeftPanelW = 80;
    protected int minRightPanelW = 40;
    protected int minCenterW = 100;
    protected int pad = 10;
    protected int topBarH = 24;
    protected int gap = 4;
    protected int btnH = 20;
    protected int panelBg = -1072689136;
    protected int panelBorder = -13421773;
    protected int topBarBg = -1072162792;
    protected int originX;
    protected int originY;
    protected int usableW;
    protected int usableH;
    protected int leftPanelW;
    protected int rightPanelW;
    protected int contentX;
    protected int contentY;
    protected int contentW;
    protected int contentH;
    protected int rightX;
    protected boolean enableDivider = false;
    protected int dividerWidth = 5;
    protected int dividerLineHeight = 20;
    protected int minDividerPanelW = 50;
    private int dividerOffset;
    private boolean isDragging = false;
    private int dragStartX;

    public GuiDirectory() {
        this.closeOnEsc = true;
        this.drawDefaultBackground = true;
    }

    protected void computeLayout() {
        this.originX = this.pad;
        this.originY = this.pad;
        this.usableW = this.field_146294_l - 2 * this.pad;
        this.usableH = this.field_146295_m - 2 * this.pad;
        this.leftPanelW = Math.max(this.minLeftPanelW, (int)((float)this.usableW * this.leftPanelPercent));
        this.rightPanelW = Math.max(this.minRightPanelW, (int)((float)this.usableW * this.rightPanelPercent));
        if (this.rightPanelPercent <= 0.0f) {
            this.rightPanelW = 0;
        }
        int gapCount = this.rightPanelW > 0 ? 3 : 2;
        this.contentW = this.usableW - this.leftPanelW - this.rightPanelW - gapCount * this.gap;
        if (this.contentW < this.minCenterW) {
            this.contentW = this.minCenterW;
        }
        this.contentX = this.originX + this.leftPanelW + this.gap;
        this.contentY = this.originY + this.topBarH + this.gap;
        this.contentH = this.usableH - this.topBarH - this.gap;
        this.rightX = this.contentX + this.contentW + this.gap;
        if (this.enableDivider && this.dividerOffset == 0) {
            this.dividerOffset = this.leftPanelW;
        }
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.computeLayout();
        this.initTopBar(this.originY + 2);
        this.initLeftPanel();
        this.initCenterPanel();
        if (this.rightPanelW > 0) {
            this.initRightPanel(this.contentY + 4);
        }
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        this.drawTopBar();
        this.drawPanels();
        if (this.enableDivider) {
            this.drawDivider(mouseX, mouseY);
        }
        super.func_73863_a(mouseX, mouseY, partialTicks);
        this.drawOverlay(mouseX, mouseY, partialTicks);
    }

    protected void drawTopBar() {
        GuiUtil.drawRectD(this.originX, this.originY, this.originX + this.usableW, this.originY + this.topBarH, this.topBarBg);
    }

    protected void drawPanels() {
        GuiUtil.drawRectD(this.originX - 1, this.contentY - 1, this.originX + this.leftPanelW + 1, this.originY + this.usableH + 1, this.panelBorder);
        if (this.rightPanelW > 0) {
            GuiUtil.drawRectD(this.rightX - 1, this.contentY - 1, this.rightX + this.rightPanelW + 1, this.originY + this.usableH + 1, this.panelBorder);
        }
    }

    protected void drawDivider(int mouseX, int mouseY) {
        if (!this.enableDivider) {
            return;
        }
        int divX = this.originX + this.dividerOffset;
        int regionTop = this.contentY;
        int regionHeight = this.contentH;
        int handleTop = regionTop + (regionHeight - this.dividerLineHeight) / 2;
        GuiDirectory.func_73734_a((int)(divX + 1), (int)handleTop, (int)(divX + this.dividerWidth - 1), (int)(handleTop + this.dividerLineHeight), (int)-9408400);
    }

    protected void drawOverlay(int mouseX, int mouseY, float partialTicks) {
    }

    @Override
    public void func_73864_a(int mouseX, int mouseY, int mouseButton) {
        if (this.enableDivider && !this.hasSubGui()) {
            int divX = this.originX + this.dividerOffset;
            int regionTop = this.contentY;
            int handleTop = regionTop + (this.contentH - this.dividerLineHeight) / 2;
            int handleBottom = handleTop + this.dividerLineHeight;
            if (mouseX >= divX && mouseX <= divX + this.dividerWidth && mouseY >= handleTop && mouseY <= handleBottom) {
                this.isDragging = true;
                resizingActive = true;
                this.dragStartX = mouseX;
                return;
            }
        }
        super.func_73864_a(mouseX, mouseY, mouseButton);
    }

    @Override
    public void func_146273_a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if (this.isDragging) {
            int dx = mouseX - this.dragStartX;
            this.dragStartX = mouseX;
            this.dividerOffset += dx;
            int maxOffset = this.usableW - (this.rightPanelW > 0 ? this.rightPanelW + this.gap : 0) - this.dividerWidth - this.minDividerPanelW;
            this.dividerOffset = Math.max(this.minDividerPanelW, Math.min(this.dividerOffset, maxOffset));
            this.onDividerMoved(this.dividerOffset);
            return;
        }
        super.func_146273_a(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    protected void func_146286_b(int mouseX, int mouseY, int state) {
        if (this.isDragging) {
            this.isDragging = false;
            resizingActive = false;
            return;
        }
        super.func_146286_b(mouseX, mouseY, state);
    }

    protected void onDividerMoved(int newOffset) {
    }

    protected int getDividerOffset() {
        return this.dividerOffset;
    }

    protected void setDividerOffset(int offset) {
        this.dividerOffset = offset;
    }

    protected abstract void initTopBar(int var1);

    protected abstract void initLeftPanel();

    protected abstract void initCenterPanel();

    protected abstract void initRightPanel(int var1);
}

