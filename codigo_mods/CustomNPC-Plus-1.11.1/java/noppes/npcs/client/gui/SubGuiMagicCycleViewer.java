/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui;

import noppes.npcs.client.gui.GuiMagicCycleMap;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.MagicController;
import noppes.npcs.controllers.data.MagicCycle;

public class SubGuiMagicCycleViewer
extends SubGuiInterface {
    private final MagicCycle magicCycle;
    private GuiMagicCycleMap magicMap;

    public SubGuiMagicCycleViewer(MagicCycle cycle) {
        this.magicCycle = cycle;
        this.setBackground("menubg.png");
        this.xSize = 256;
        this.ySize = 222;
        this.closeOnEsc = true;
    }

    public SubGuiMagicCycleViewer(int cycleID) {
        this(MagicController.getInstance().getCycle(cycleID));
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        if (this.magicCycle == null) {
            return;
        }
        int mapX = this.guiLeft + 6;
        int mapY = this.guiTop + 6;
        int mapWidth = this.xSize - 13;
        int mapHeight = this.ySize - 17;
        this.magicMap = new GuiMagicCycleMap((GuiNPCInterface)this, mapX, mapY, mapWidth, mapHeight, this.magicCycle);
        this.addDiagram(0, this.magicMap);
    }

    @Override
    public void func_146273_a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if (this.magicMap != null) {
            this.magicMap.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        }
        super.func_146273_a(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    protected void func_146286_b(int mouseX, int mouseY, int state) {
        if (this.magicMap != null) {
            this.magicMap.mouseReleased(mouseX, mouseY, state);
        }
        super.func_146286_b(mouseX, mouseY, state);
    }
}

