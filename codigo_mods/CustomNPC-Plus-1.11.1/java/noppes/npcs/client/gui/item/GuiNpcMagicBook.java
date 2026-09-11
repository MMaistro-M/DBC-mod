/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import kamkeel.npcs.network.packets.player.item.MagicCyclesPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.gui.GuiMagicCycleMap;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.data.MagicCycle;

public class GuiNpcMagicBook
extends GuiNPCInterface
implements ICustomScrollListener,
IScrollData,
IGuiData {
    private MagicCycle magicCycle;
    private GuiMagicCycleMap magicMap;
    private GuiCustomScroll leftScroll;
    public HashMap<String, Integer> cycleData = new HashMap();

    public GuiNpcMagicBook() {
        this.xSize = 350;
        this.setBackground("menubg.png");
        this.closeOnEsc = true;
        MagicCyclesPacket.GetAll();
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int spacing = 10;
        int scrollWidth = 120;
        this.addLabel(new GuiNpcLabel(0, "menu.cycles", this.guiLeft + 14, this.guiTop + 10, 0xFFFFFF));
        this.leftScroll = new GuiCustomScroll((GuiScreen)this, 0, 0);
        this.leftScroll.guiLeft = this.guiLeft + spacing;
        this.leftScroll.guiTop = this.guiTop + 22;
        this.leftScroll.setSize(scrollWidth, this.ySize - 32);
        this.leftScroll.setList(new ArrayList<String>(this.cycleData.keySet()));
        this.addScroll(this.leftScroll);
        if (this.magicCycle == null) {
            return;
        }
        String displayName = this.magicCycle.getDisplayName().replace("&", "\u00a7");
        this.leftScroll.setSelected(displayName);
        int mapX = this.guiLeft + scrollWidth + 2 * spacing;
        int mapY = this.guiTop + 22;
        int mapWidth = this.xSize - (scrollWidth + 3 * spacing);
        int mapHeight = this.ySize - 32;
        this.magicMap = new GuiMagicCycleMap((GuiNPCInterface)this, mapX, mapY, mapWidth, mapHeight, this.magicCycle);
        this.addDiagram(0, this.magicMap);
        int labelY = this.guiTop + 10;
        int textWidth = this.field_146289_q.func_78256_a(displayName);
        int labelX = mapX + mapWidth / 2 - textWidth / 2;
        GuiNpcLabel magicLabel = new GuiNpcLabel(1, displayName, labelX, labelY, CustomNpcResourceListener.DefaultTextColor);
        this.addLabel(magicLabel);
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (i == 1) {
            this.close();
        }
    }

    @Override
    public void func_146284_a(GuiButton guibutton) {
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        super.func_73863_a(i, j, f);
    }

    @Override
    protected void drawBackground() {
        super.drawBackground();
        int spacing = 10;
        int scrollWidth = 120;
        int barHeight = 16;
        int barY = this.guiTop + 22 - barHeight - 1;
        if (this.leftScroll != null) {
            int leftRectX = this.guiLeft + spacing;
            GuiNpcMagicBook.func_73734_a((int)leftRectX, (int)barY, (int)(leftRectX + scrollWidth), (int)(barY + barHeight), (int)-13421773);
        }
        if (this.magicMap != null) {
            int mapX = this.guiLeft + scrollWidth + 2 * spacing;
            int mapWidth = this.xSize - (scrollWidth + 3 * spacing);
            GuiNpcMagicBook.func_73734_a((int)mapX, (int)barY, (int)(mapX + mapWidth), (int)(barY + barHeight), (int)-13421773);
        }
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

    @Override
    public void save() {
    }

    @Override
    public void customScrollClicked(int id, int index, int clickType, GuiCustomScroll scroll) {
        if (scroll == this.leftScroll) {
            String selectedName = scroll.getSelected();
            MagicCyclesPacket.GetCycle(this.cycleData.get(selectedName));
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        if (type == EnumScrollData.MAGIC_CYCLES) {
            this.cycleData.clear();
            this.cycleData.putAll(data);
            if (this.leftScroll != null) {
                this.leftScroll.setList(new ArrayList<String>(this.cycleData.keySet()));
            }
            this.func_73866_w_();
        }
    }

    @Override
    public void setSelected(String selected) {
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        if (compound.func_74764_b("MagicCycle")) {
            this.magicCycle = new MagicCycle();
            this.magicCycle.readNBT(compound.func_74775_l("MagicCycle"));
            this.func_73866_w_();
        }
    }
}

