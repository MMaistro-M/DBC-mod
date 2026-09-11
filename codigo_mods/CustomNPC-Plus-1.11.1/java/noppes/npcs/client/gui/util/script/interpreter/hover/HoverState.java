/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.hover;

import noppes.npcs.client.gui.util.script.interpreter.ScriptDocument;
import noppes.npcs.client.gui.util.script.interpreter.ScriptLine;
import noppes.npcs.client.gui.util.script.interpreter.hover.TokenHoverInfo;
import noppes.npcs.client.gui.util.script.interpreter.token.Token;

public class HoverState {
    private static final long HOVER_DELAY_MS = 250L;
    private Token hoveredToken;
    private long hoverStartTime;
    private boolean tooltipVisible;
    private TokenHoverInfo hoverInfo;
    private int lastMouseX;
    private int lastMouseY;
    private int tokenScreenX;
    private int tokenScreenY;
    private int tokenWidth;
    private int tooltipBoxX;
    private int tooltipBoxY;
    private int tooltipBoxWidth;
    private int tooltipBoxHeight;
    private int tooltipPanelX;
    private int tooltipPanelY;
    private int tooltipPanelW;
    private int tooltipPanelH;
    private int scrollbarThumbX;
    private int scrollbarThumbY;
    private int scrollbarThumbH;
    private int scrollbarX;
    private int scrollbarTrackTop;
    private int scrollbarTrackHeight;
    private boolean isDraggingScrollbar;
    private int dragStartMouseY;
    private int dragStartScrollOffset;
    private boolean isDraggingTooltip;
    private int tooltipDragOffsetX;
    private int tooltipDragOffsetY;
    private boolean tooltipPositionOverridden;
    private int overriddenTooltipX;
    private int overriddenTooltipY;
    private boolean isResizingTooltip;
    private int resizeInitMouseX;
    private int resizeInitMouseY;
    private int resizeInitW;
    private int resizeInitH;
    private boolean tooltipSizeOverridden;
    private int overriddenTooltipW;
    private int overriddenTooltipH;
    private static final int MIN_TOOLTIP_W = 80;
    private static final int MIN_TOOLTIP_H = 30;
    private float targetScrollOffset;
    private float tooltipScrollOffsetF;
    private int tooltipMaxScroll;
    private Token pinnedToken;
    private TokenHoverInfo pinnedHoverInfo;
    private boolean clickToPinEnabled = true;
    private int lockedTooltipMouseX;
    private int lockedTooltipMouseY;
    private boolean isTooltipPositionLocked;

    public void update(int mouseX, int mouseY, ScriptDocument document, int viewportX, int viewportY, int viewportWidth, int viewportHeight, float scrollOffset, int lineHeight, int gutterWidth) {
        this.lastMouseX = mouseX;
        this.lastMouseY = mouseY;
        if (mouseX < viewportX || mouseX > viewportX + viewportWidth || mouseY < viewportY || mouseY > viewportY + viewportHeight) {
            this.clearHover();
            return;
        }
        if (document == null) {
            this.clearHover();
            return;
        }
        int relativeY = mouseY - viewportY;
        int lineIndex = (int)(scrollOffset + (float)relativeY / (float)lineHeight);
        ScriptLine line = null;
        for (ScriptLine l : document.getLines()) {
            if (l.getLineIndex() != lineIndex) continue;
            line = l;
            break;
        }
        if (line == null) {
            this.clearHover();
            return;
        }
        int relativeX = mouseX - viewportX;
        int globalPos = line.getGlobalStart() + this.getCharacterIndexAtX(line, relativeX);
        Token token = line.getTokenAt(globalPos);
        if (token == null) {
            this.clearHover();
            return;
        }
        if (token != this.hoveredToken) {
            this.hoveredToken = token;
            this.hoverStartTime = System.currentTimeMillis();
            this.tooltipVisible = false;
            this.hoverInfo = null;
            this.resetTooltipScroll();
            this.calculateTokenPosition(line, token, viewportX, viewportY, scrollOffset, lineHeight);
        } else {
            long elapsed = System.currentTimeMillis() - this.hoverStartTime;
            if (elapsed >= 250L && !this.tooltipVisible) {
                this.tooltipVisible = true;
                this.hoverInfo = TokenHoverInfo.fromToken(token);
                this.lockedTooltipMouseX = this.lastMouseX;
                this.lockedTooltipMouseY = this.lastMouseY;
                this.isTooltipPositionLocked = true;
            }
        }
    }

    public void update(int mouseX, int mouseY, Token token, int tokenX, int tokenY, int tokenW) {
        this.lastMouseX = mouseX;
        this.lastMouseY = mouseY;
        this.clickToPinEnabled = false;
        if (this.pinnedToken != null) {
            this.tooltipVisible = true;
            this.hoverInfo = this.pinnedHoverInfo;
            return;
        }
        if (token == null) {
            this.clearHover();
            return;
        }
        if (token != this.hoveredToken) {
            this.hoveredToken = token;
            this.hoverStartTime = System.currentTimeMillis();
            this.tooltipVisible = false;
            this.hoverInfo = null;
            this.resetTooltipScroll();
            this.tokenScreenX = tokenX;
            this.tokenScreenY = tokenY;
            this.tokenWidth = tokenW;
        } else {
            long elapsed = System.currentTimeMillis() - this.hoverStartTime;
            if (elapsed >= 250L && !this.tooltipVisible) {
                this.tooltipVisible = true;
                this.hoverInfo = TokenHoverInfo.fromToken(token);
                this.lockedTooltipMouseX = this.lastMouseX;
                this.lockedTooltipMouseY = this.lastMouseY;
                this.isTooltipPositionLocked = true;
            }
        }
    }

    public void clearHover() {
        if (this.hoveredToken != null) {
            this.hoveredToken = null;
            this.hoverStartTime = 0L;
            if (this.pinnedToken == null) {
                this.tooltipVisible = false;
                this.hoverInfo = null;
                this.tooltipBoxHeight = 0;
                this.tooltipBoxWidth = 0;
                this.tooltipBoxY = 0;
                this.tooltipBoxX = 0;
                this.tooltipPanelH = 0;
                this.tooltipPanelW = 0;
                this.tooltipPanelY = 0;
                this.tooltipPanelX = 0;
                this.isDraggingScrollbar = false;
                this.isDraggingTooltip = false;
                this.tooltipPositionOverridden = false;
                this.isResizingTooltip = false;
                this.tooltipSizeOverridden = false;
                this.tooltipScrollOffsetF = 0.0f;
                this.targetScrollOffset = 0.0f;
                this.tooltipMaxScroll = 0;
                this.isDraggingScrollbar = false;
                this.isTooltipPositionLocked = false;
            }
        }
    }

    public void hideTooltip() {
        this.tooltipVisible = false;
    }

    public void setClickToPinEnabled(boolean enabled) {
        this.clickToPinEnabled = enabled;
    }

    public boolean isClickToPinEnabled() {
        return this.clickToPinEnabled;
    }

    public void pinToken(Token token, int tokenX, int tokenY, int tokenW) {
        if (token == null) {
            return;
        }
        this.pinnedToken = token;
        this.pinnedHoverInfo = TokenHoverInfo.fromToken(token);
        this.tooltipVisible = this.pinnedHoverInfo != null && this.pinnedHoverInfo.hasContent();
        this.tokenScreenX = tokenX;
        this.tokenScreenY = tokenY;
        this.tokenWidth = tokenW;
        this.hoveredToken = token;
        if (this.tooltipVisible && !this.isTooltipPositionLocked) {
            this.lockedTooltipMouseX = this.lastMouseX;
            this.lockedTooltipMouseY = this.lastMouseY;
            this.isTooltipPositionLocked = true;
        }
    }

    public void unpin() {
        this.pinnedToken = null;
        this.pinnedHoverInfo = null;
        this.tooltipVisible = false;
        this.hoverInfo = null;
        this.isTooltipPositionLocked = false;
    }

    public boolean isPinned() {
        return this.pinnedToken != null;
    }

    public int getLockedMouseX() {
        return this.lockedTooltipMouseX;
    }

    public int getLockedMouseY() {
        return this.lockedTooltipMouseY;
    }

    public boolean isPositionLocked() {
        return this.isTooltipPositionLocked;
    }

    private int getCharacterIndexAtX(ScriptLine line, int x) {
        String text = line.getText();
        if (text == null || text.isEmpty()) {
            return 0;
        }
        int accumWidth = 0;
        for (int i = 0; i < text.length(); ++i) {
            int charWidth = line.getRenderedWidth(i, i + 1);
            if (accumWidth + charWidth / 2 > x) {
                return i;
            }
            accumWidth += charWidth;
        }
        return text.length();
    }

    private void calculateTokenPosition(ScriptLine line, Token token, int viewportX, int viewportY, float scrollOffset, int lineHeight) {
        int tokenLocalStart = token.getGlobalStart() - line.getGlobalStart();
        tokenLocalStart = Math.max(0, Math.min(tokenLocalStart, line.getText().length()));
        this.tokenScreenX = viewportX + line.getRenderedWidth(0, tokenLocalStart);
        int lineY = line.getLineIndex();
        this.tokenScreenY = viewportY + (int)(((float)lineY - scrollOffset) * (float)lineHeight);
        int tokenLocalEnd = tokenLocalStart + token.getText().length();
        this.tokenWidth = line.getRenderedWidth(tokenLocalStart, tokenLocalEnd);
    }

    public void setTooltipBounds(int x, int y, int width, int height) {
        this.tooltipBoxX = x;
        this.tooltipBoxY = y;
        this.tooltipBoxWidth = width;
        this.tooltipBoxHeight = height;
    }

    public void setTooltipMaxScroll(int maxScroll) {
        this.tooltipMaxScroll = Math.max(0, maxScroll);
    }

    public boolean isMouseOverTooltip(int mouseX, int mouseY) {
        if (!this.tooltipVisible || this.tooltipBoxWidth <= 0) {
            return false;
        }
        return mouseX >= this.tooltipBoxX && mouseX <= this.tooltipBoxX + this.tooltipBoxWidth && mouseY >= this.tooltipBoxY && mouseY <= this.tooltipBoxY + this.tooltipBoxHeight;
    }

    public void scrollTooltip(int wheelDelta) {
        this.targetScrollOffset = Math.max(0.0f, Math.min((float)this.tooltipMaxScroll, this.targetScrollOffset - (float)wheelDelta / 5.0f));
    }

    public int getTooltipScrollOffset() {
        return (int)this.tooltipScrollOffsetF;
    }

    public void resetTooltipScroll() {
        this.tooltipScrollOffsetF = 0.0f;
        this.targetScrollOffset = 0.0f;
    }

    public void updateSmoothScroll() {
        float diff = this.targetScrollOffset - this.tooltipScrollOffsetF;
        this.tooltipScrollOffsetF = Math.abs(diff) < 0.5f ? this.targetScrollOffset : (this.tooltipScrollOffsetF += diff * 0.04f);
    }

    public void setScrollbarThumb(int barX, int thumbY, int thumbH, int trackTop, int trackHeight) {
        this.scrollbarX = barX;
        this.scrollbarThumbX = barX;
        this.scrollbarThumbY = thumbY;
        this.scrollbarThumbH = thumbH;
        this.scrollbarTrackTop = trackTop;
        this.scrollbarTrackHeight = trackHeight;
    }

    public boolean isMouseOverScrollbarThumb(int mx, int my) {
        if (!this.tooltipVisible || this.scrollbarThumbH <= 0) {
            return false;
        }
        return mx >= this.scrollbarX && mx <= this.scrollbarX + 3 && my >= this.scrollbarThumbY && my <= this.scrollbarThumbY + this.scrollbarThumbH;
    }

    public void startScrollbarDrag(int mouseY) {
        this.isDraggingScrollbar = true;
        this.dragStartMouseY = mouseY;
        this.dragStartScrollOffset = (int)this.tooltipScrollOffsetF;
    }

    public void updateScrollbarDrag(int mouseY) {
        if (!this.isDraggingScrollbar || this.scrollbarTrackHeight <= 0 || this.tooltipMaxScroll <= 0) {
            return;
        }
        float scrollRatio = (float)this.scrollbarTrackHeight / (float)Math.max(1, this.scrollbarTrackHeight + this.tooltipMaxScroll);
        int thumbH = Math.max(6, (int)((float)this.scrollbarTrackHeight * scrollRatio));
        int effectiveTrack = this.scrollbarTrackHeight - thumbH;
        if (effectiveTrack <= 0) {
            return;
        }
        int deltaY = mouseY - this.dragStartMouseY;
        float scrollDelta = (float)deltaY / (float)effectiveTrack * (float)this.tooltipMaxScroll;
        this.tooltipScrollOffsetF = this.targetScrollOffset = Math.max(0.0f, Math.min((float)this.tooltipMaxScroll, (float)this.dragStartScrollOffset + scrollDelta));
    }

    public void releaseScrollbarDrag() {
        this.isDraggingScrollbar = false;
    }

    public void setTooltipPanel(int x, int y, int w, int h) {
        this.tooltipPanelX = x;
        this.tooltipPanelY = y;
        this.tooltipPanelW = w;
        this.tooltipPanelH = h;
    }

    public boolean isMouseOverTooltipPanel(int mx, int my) {
        if (!this.tooltipVisible || this.tooltipPanelW <= 0) {
            return false;
        }
        return mx >= this.tooltipPanelX && mx <= this.tooltipPanelX + this.tooltipPanelW && my >= this.tooltipPanelY && my <= this.tooltipPanelY + this.tooltipPanelH;
    }

    public int getTooltipPanelX() {
        return this.tooltipPanelX;
    }

    public int getTooltipPanelY() {
        return this.tooltipPanelY;
    }

    public void startTooltipDrag(int mouseX, int mouseY) {
        this.isDraggingTooltip = true;
        this.tooltipDragOffsetX = mouseX - this.tooltipPanelX;
        this.tooltipDragOffsetY = mouseY - this.tooltipPanelY;
    }

    public void updateTooltipDrag(int mouseX, int mouseY) {
        if (!this.isDraggingTooltip) {
            return;
        }
        this.overriddenTooltipX = mouseX - this.tooltipDragOffsetX;
        this.overriddenTooltipY = mouseY - this.tooltipDragOffsetY;
        this.tooltipPositionOverridden = true;
    }

    public void releaseTooltipDrag() {
        this.isDraggingTooltip = false;
    }

    public boolean isDraggingTooltip() {
        return this.isDraggingTooltip;
    }

    public boolean hasOverriddenPosition() {
        return this.tooltipPositionOverridden;
    }

    public int getOverriddenTooltipX() {
        return this.overriddenTooltipX;
    }

    public int getOverriddenTooltipY() {
        return this.overriddenTooltipY;
    }

    public boolean isMouseOverResizeHandle(int mx, int my) {
        if (!this.tooltipVisible || this.tooltipPanelW <= 0) {
            return false;
        }
        int rx = this.tooltipPanelX + this.tooltipPanelW - 8;
        int ry = this.tooltipPanelY + this.tooltipPanelH - 8;
        return mx >= rx && mx <= this.tooltipPanelX + this.tooltipPanelW && my >= ry && my <= this.tooltipPanelY + this.tooltipPanelH;
    }

    public void startTooltipResize(int mouseX, int mouseY) {
        this.isResizingTooltip = true;
        this.resizeInitMouseX = mouseX;
        this.resizeInitMouseY = mouseY;
        this.resizeInitW = this.tooltipSizeOverridden ? this.overriddenTooltipW : this.tooltipPanelW;
        this.resizeInitH = this.tooltipSizeOverridden ? this.overriddenTooltipH : this.tooltipPanelH;
    }

    public void updateTooltipResize(int mouseX, int mouseY) {
        if (!this.isResizingTooltip) {
            return;
        }
        this.overriddenTooltipW = Math.max(80, this.resizeInitW + (mouseX - this.resizeInitMouseX));
        this.overriddenTooltipH = Math.max(30, this.resizeInitH + (mouseY - this.resizeInitMouseY));
        this.tooltipSizeOverridden = true;
    }

    public void releaseTooltipResize() {
        this.isResizingTooltip = false;
    }

    public boolean isResizingTooltip() {
        return this.isResizingTooltip;
    }

    public boolean hasOverriddenSize() {
        return this.tooltipSizeOverridden;
    }

    public int getOverriddenTooltipW() {
        return this.overriddenTooltipW;
    }

    public int getOverriddenTooltipH() {
        return this.overriddenTooltipH;
    }

    public boolean isDraggingScrollbar() {
        return this.isDraggingScrollbar;
    }

    public int getScrollbarThumbY() {
        return this.scrollbarThumbY;
    }

    public int getScrollbarThumbH() {
        return this.scrollbarThumbH;
    }

    public int getScrollbarX() {
        return this.scrollbarX;
    }

    public boolean isTooltipVisible() {
        return this.tooltipVisible && this.hoverInfo != null && this.hoverInfo.hasContent();
    }

    public TokenHoverInfo getHoverInfo() {
        return this.hoverInfo;
    }

    public Token getHoveredToken() {
        return this.hoveredToken;
    }

    public int getTokenScreenX() {
        return this.tokenScreenX;
    }

    public int getTokenScreenY() {
        return this.tokenScreenY;
    }

    public int getTokenWidth() {
        return this.tokenWidth;
    }

    public int getLastMouseX() {
        return this.lastMouseX;
    }

    public int getLastMouseY() {
        return this.lastMouseY;
    }

    public void setLastMousePosition(int x, int y) {
        this.lastMouseX = x;
        this.lastMouseY = y;
    }

    public float getHoverProgress() {
        if (this.hoveredToken == null) {
            return 0.0f;
        }
        long elapsed = System.currentTimeMillis() - this.hoverStartTime;
        return Math.min(1.0f, (float)elapsed / 250.0f);
    }
}

