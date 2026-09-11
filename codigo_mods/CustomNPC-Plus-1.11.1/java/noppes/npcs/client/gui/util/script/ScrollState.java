/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Mouse
 */
package noppes.npcs.client.gui.util.script;

import org.lwjgl.input.Mouse;

public class ScrollState {
    private double scrollPos = -1.0;
    private double targetScroll = 0.0;
    private double scrollVelocity = 0.0;
    private long lastScrollTime = 0L;
    private int scrolledLine = 0;
    private int scrollbarDragOffset = 0;
    private boolean clickScrolling = false;
    private static final double TAU = 0.1;
    private static final double SNAP_THRESHOLD = 0.01;
    private static final double MAX_DT = 0.05;

    public void reset() {
        this.scrollPos = 0.0;
        this.targetScroll = 0.0;
        this.scrollVelocity = 0.0;
        this.scrolledLine = 0;
        this.lastScrollTime = System.currentTimeMillis();
    }

    public void initializeIfNeeded(int currentLine) {
        if (this.scrollPos < 0.0) {
            this.scrollPos = currentLine;
            this.lastScrollTime = System.currentTimeMillis();
        }
    }

    public void update(int maxScroll) {
        long nowMs = System.currentTimeMillis();
        double dt = Math.min(0.05, (double)(nowMs - this.lastScrollTime) / 1000.0);
        this.lastScrollTime = nowMs;
        double dist = this.targetScroll - this.scrollPos;
        if (Math.abs(dist) < 0.01) {
            this.scrollPos = this.targetScroll;
            this.scrollVelocity = 0.0;
        } else {
            double alpha = 1.0 - Math.exp(-dt / Math.max(1.0E-6, 0.1));
            double prev = this.scrollPos;
            this.scrollPos += dist * alpha;
            this.scrollVelocity = (this.scrollPos - prev) / (dt > 0.0 ? dt : 1.0E-6);
            if (dist > 0.0 && this.scrollPos > this.targetScroll || dist < 0.0 && this.scrollPos < this.targetScroll) {
                this.scrollPos = this.targetScroll;
                this.scrollVelocity = 0.0;
            }
        }
        this.clampToBounds(maxScroll);
        this.scrolledLine = Math.max(0, Math.min((int)Math.floor(this.scrollPos), maxScroll));
    }

    public void clampToBounds(int maxScroll) {
        if (this.scrollPos < 0.0) {
            this.scrollPos = 0.0;
        }
        if (this.scrollPos > (double)maxScroll) {
            this.scrollPos = maxScroll;
        }
        if (this.targetScroll < 0.0) {
            this.targetScroll = 0.0;
        }
        if (this.targetScroll > (double)maxScroll) {
            this.targetScroll = maxScroll;
        }
        this.scrolledLine = Math.max(0, Math.min(this.scrolledLine, maxScroll));
    }

    public void applyWheelScroll(int wheelDelta, int maxScroll) {
        double sign = Math.copySign(1.0f, wheelDelta);
        this.targetScroll -= sign * 2.0;
        this.clampToBounds(maxScroll);
    }

    public void setTargetScroll(double target, int maxScroll) {
        this.targetScroll = Math.max(0.0, Math.min(target, (double)maxScroll));
    }

    public void scrollToLine(int lineIdx, int visibleLines, int maxScroll) {
        int firstVisible = this.scrolledLine;
        int lastFullyVisible = this.scrolledLine + visibleLines;
        if (lineIdx < firstVisible) {
            this.targetScroll = lineIdx;
        } else if (lineIdx > lastFullyVisible) {
            this.targetScroll = Math.min(lineIdx - visibleLines, maxScroll);
        }
    }

    public double getScrollPos() {
        return this.scrollPos;
    }

    public double getTargetScroll() {
        return this.targetScroll;
    }

    public int getScrolledLine() {
        return this.scrolledLine;
    }

    public double getScrollVelocity() {
        return this.scrollVelocity;
    }

    public boolean isClickScrolling() {
        return this.clickScrolling;
    }

    public int getScrollbarDragOffset() {
        return this.scrollbarDragOffset;
    }

    public double getFractionalOffset() {
        return this.scrollPos - (double)this.scrolledLine;
    }

    public void setClickScrolling(boolean scrolling) {
        this.clickScrolling = scrolling;
    }

    public void setScrollbarDragOffset(int offset) {
        this.scrollbarDragOffset = offset;
    }

    public void setScrolledLine(int line) {
        this.scrolledLine = line;
    }

    public void handleClickScrolling(int yMouse, int areaX, int areaY, int areaHeight, int visibleLines, int linesCount, int maxScroll) {
        this.setClickScrolling(Mouse.isButtonDown((int)0));
        int diff = Math.max(0, linesCount - visibleLines);
        if (diff > 0) {
            int sbSize = Math.max((int)(1.0f * (float)visibleLines / (float)Math.max(1, linesCount) * (float)areaHeight), 2);
            int trackTop = areaY + 1;
            int trackHeight = Math.max(1, areaHeight - 4);
            int thumbRange = Math.max(1, trackHeight - sbSize);
            double linesCountD = Math.max(1.0, (double)linesCount);
            int thumbTop = (int)((double)areaY + 1.0 * this.getScrollPos() / linesCountD * (double)(areaHeight - 4)) + 1;
            if (yMouse < thumbTop || yMouse > thumbTop + sbSize) {
                double centerRatio = (double)(yMouse - trackTop) / (double)trackHeight;
                centerRatio = Math.max(0.0, Math.min(1.0, centerRatio));
                this.setTargetScroll(centerRatio * (double)diff, maxScroll);
                this.setScrollbarDragOffset(sbSize / 2);
            } else {
                int desiredTop = yMouse - this.getScrollbarDragOffset();
                desiredTop = Math.max(trackTop, Math.min(trackTop + thumbRange, desiredTop));
                double ratio = (double)(desiredTop - trackTop) / (double)thumbRange;
                ratio = Math.max(0.0, Math.min(1.0, ratio));
                this.setTargetScroll(ratio * (double)diff, maxScroll);
            }
        }
        if (!this.isClickScrolling()) {
            this.setScrollbarDragOffset(0);
        }
    }

    public void startScrollbarDrag(int yMouse, int areaY, int areaHeight, int linesCount) {
        this.setClickScrolling(true);
        double linesCountD = Math.max(1.0, (double)linesCount);
        int thumbTop = (int)((double)areaY + 1.0 * this.getScrollPos() / linesCountD * (double)(areaHeight - 4)) + 1;
        this.setScrollbarDragOffset(yMouse - thumbTop);
    }
}

