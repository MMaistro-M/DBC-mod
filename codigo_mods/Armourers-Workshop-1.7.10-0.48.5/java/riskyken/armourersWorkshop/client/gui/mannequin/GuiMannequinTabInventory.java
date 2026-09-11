/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiUtils
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.inventory.Slot
 */
package riskyken.armourersWorkshop.client.gui.mannequin;

import cpw.mods.fml.client.config.GuiUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.common.inventory.slot.SlotHidable;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMannequin;

@SideOnly(value=Side.CLIENT)
public class GuiMannequinTabInventory
extends GuiTabPanel {
    private static final int INV_SLOT_SIZE = 18;
    private static final int INV_PLAYER_TEX_WIDTH = 176;
    private static final int INV_PLAYER_TEX_HEIGHT = 98;
    private static final int INV_PLAYER_TEX_U = 0;
    private static final int INV_PLAYER_TEX_V = 0;
    private static final int INV_PLAYER_TOP_PAD = 15;
    private static final int INV_PLAYER_LEFT_PAD = 8;
    private static final int INV_MAN_TEX_WIDTH = 38;
    private static final int INV_MAN_TEX_HEIGHT = 38;
    private static final int INV_MAN_TAR_WIDTH = 176;
    private static final int INV_MAN_TAR_HEIGHT = 40;
    private static final int INV_MAN_TEX_U = 0;
    private static final int INV_MAN_TEX_V = 200;
    private static final int INV_MAN_TOP_PAD = 16;
    private static final int INV_MAN_LEFT_PAD = 26;
    private final TileEntityMannequin tileEntity;

    public GuiMannequinTabInventory(int tabId, GuiScreen parent, TileEntityMannequin tileEntity) {
        super(tabId, parent, true);
        this.tileEntity = tileEntity;
    }

    @Override
    public void initGui(int xPos, int yPos, int width, int height) {
        Slot slot;
        super.initGui(xPos, yPos, width, height);
        GuiContainer guiCon = (GuiContainer)this.parent;
        for (int x = 0; x < 9; ++x) {
            Slot slot2 = (Slot)guiCon.field_147002_h.field_75151_b.get(x);
            if (!(slot2 instanceof SlotHidable)) continue;
            ((SlotHidable)slot2).setDisplayPosition((int)((float)this.width / 2.0f - 88.0f + (float)(x * 18) + 8.0f), this.height + 1 - 1 - 18);
        }
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                slot = (Slot)guiCon.field_147002_h.field_75151_b.get(x + y * 9 + 9);
                if (!(slot instanceof SlotHidable)) continue;
                ((SlotHidable)slot).setDisplayPosition((int)((float)this.width / 2.0f - 88.0f + (float)(x * 18) + 8.0f), this.height + 1 - 72 - 5 + y * 18);
            }
        }
        for (int j = 0; j < 5; ++j) {
            for (int i = 0; i < 7; ++i) {
                slot = (Slot)guiCon.field_147002_h.field_75151_b.get(36 + i + j * 7);
                if (!(slot instanceof SlotHidable)) continue;
                ((SlotHidable)slot).setDisplayPosition(this.width / 2 - 88 + 26 + i * 18, 16 + j * 18);
            }
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
        GuiUtils.drawContinuousTexturedBox((int)(center - 88), (int)0, (int)0, (int)200, (int)176, (int)112, (int)38, (int)38, (int)4, (float)this.field_73735_i);
        for (int i = 0; i < 5; ++i) {
            this.func_73729_b(center - 88 + 26 - 1, 15 + i * 18, 25, 113, 128, 18);
        }
    }

    @Override
    public void drawForegroundLayer(int mouseX, int mouseY) {
        super.drawForegroundLayer(mouseX, mouseY);
    }
}

