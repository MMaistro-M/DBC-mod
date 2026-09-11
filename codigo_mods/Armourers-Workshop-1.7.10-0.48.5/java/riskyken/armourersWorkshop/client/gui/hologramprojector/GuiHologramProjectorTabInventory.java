/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiSlider
 *  cpw.mods.fml.client.config.GuiSlider$ISlider
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.inventory.Slot
 */
package riskyken.armourersWorkshop.client.gui.hologramprojector;

import cpw.mods.fml.client.config.GuiSlider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.common.inventory.slot.SlotHidable;

@SideOnly(value=Side.CLIENT)
public class GuiHologramProjectorTabInventory
extends GuiTabPanel
implements GuiSlider.ISlider {
    private static final int INV_SLOT_SIZE = 18;
    private static final int INV_PLAYER_TEX_WIDTH = 176;
    private static final int INV_PLAYER_TEX_HEIGHT = 98;
    private static final int INV_PLAYER_TEX_U = 0;
    private static final int INV_PLAYER_TEX_V = 0;
    private static final int INV_PLAYER_TOP_PAD = 15;
    private static final int INV_PLAYER_LEFT_PAD = 8;
    private static final int INV_MAN_TEX_WIDTH = 176;
    private static final int INV_MAN_TEX_HEIGHT = 40;
    private static final int INV_MAN_TEX_U = 0;
    private static final int INV_MAN_TEX_V = 98;
    private static final int INV_MAN_TOP_PAD = 15;
    private static final int INV_MAN_LEFT_PAD = 26;

    public GuiHologramProjectorTabInventory(int tabId, GuiScreen parent) {
        super(tabId, parent, true);
    }

    @Override
    public void initGui(int xPos, int yPos, int width, int height) {
        super.initGui(xPos, yPos, width, height);
        GuiContainer guiCon = (GuiContainer)this.parent;
        for (int x = 0; x < 9; ++x) {
            Slot slot = (Slot)guiCon.field_147002_h.field_75151_b.get(x);
            if (!(slot instanceof SlotHidable)) continue;
            ((SlotHidable)slot).setDisplayPosition((int)((float)this.width / 2.0f - 88.0f + (float)(x * 18) + 8.0f), this.height + 1 - 1 - 18);
        }
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                Slot slot = (Slot)guiCon.field_147002_h.field_75151_b.get(x + y * 9 + 9);
                if (!(slot instanceof SlotHidable)) continue;
                ((SlotHidable)slot).setDisplayPosition((int)((float)this.width / 2.0f - 88.0f + (float)(x * 18) + 8.0f), this.height + 1 - 72 - 5 + y * 18);
            }
        }
        Slot slot = (Slot)guiCon.field_147002_h.field_75151_b.get(36);
        if (slot instanceof SlotHidable) {
            ((SlotHidable)slot).setDisplayPosition((int)((float)this.width / 2.0f - 8.0f), 16);
        }
    }

    @Override
    public void tabChanged(int tabIndex) {
        GuiContainer guiCon = (GuiContainer)this.parent;
        for (int i = 0; i < guiCon.field_147002_h.field_75151_b.size(); ++i) {
            Slot slot = (Slot)guiCon.field_147002_h.field_75151_b.get(i);
            if (!(slot instanceof SlotHidable)) continue;
            ((SlotHidable)slot).setVisible(tabIndex == this.getTabId());
        }
    }

    @Override
    public void drawBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
        int center = (int)((float)this.width / 2.0f);
        this.func_73729_b(center - 88, this.height - 98 + 6, 0, 0, 176, 98);
        this.func_73729_b(center - 88, 0, 0, 98, 176, 40);
    }

    public void onChangeSliderValue(GuiSlider slider) {
    }
}

