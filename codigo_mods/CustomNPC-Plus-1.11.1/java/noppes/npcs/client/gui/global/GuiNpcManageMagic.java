/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.magic.MagicCycleRemovePacket;
import kamkeel.npcs.network.packets.request.magic.MagicCycleSavePacket;
import kamkeel.npcs.network.packets.request.magic.MagicGetAllPacket;
import kamkeel.npcs.network.packets.request.magic.MagicGetPacket;
import kamkeel.npcs.network.packets.request.magic.MagicRemovePacket;
import kamkeel.npcs.network.packets.request.magic.MagicSavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.SubGuiMagic;
import noppes.npcs.client.gui.SubGuiMagicCycle;
import noppes.npcs.client.gui.SubGuiMagicCycleViewer;
import noppes.npcs.client.gui.player.inventory.GuiCNPCInventory;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.GuiToggleButton;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.data.Magic;
import noppes.npcs.controllers.data.MagicCycle;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNpcManageMagic
extends GuiNPCInterface2
implements ISubGuiListener,
ICustomScrollListener,
IScrollData,
IGuiData {
    public GuiCustomScroll leftScroll;
    public GuiCustomScroll rightScroll;
    private boolean viewByCycle = true;
    public HashMap<String, Integer> cycleData = new HashMap();
    public HashMap<String, Integer> magicData = new HashMap();
    private List<String> currentMagicList = new ArrayList<String>();
    private MagicCycle selectedCycle;
    private Magic selectedMagic;
    private boolean isResizing = false;
    private int initialDragX = 0;
    private int dividerOffset = 143;
    private final int dividerWidth = 5;
    private final int minScrollWidth = 50;
    private final int dividerLineHeight = 20;
    private final int dividerLineYOffset = 0;
    private String cycleSearch = "";
    private String magicSearch = "";

    public GuiNpcManageMagic(EntityNPCInterface npc) {
        super(npc);
        PacketClient.sendClient(new MagicGetAllPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 5;
        this.addButton(new GuiToggleButton(50, this.guiLeft + 368, this.guiTop + this.ySize - 60, this.viewByCycle));
        ((GuiToggleButton)this.getButton(50)).setTextureOff(GuiCNPCInventory.specialIcons).setTextureOffPos(16, 0);
        this.getButton(50).setIconTexture(GuiCNPCInventory.specialIcons).setIconPos(16, 16, 16, 0);
        if (!this.viewByCycle) {
            int regionLeft = this.guiLeft + 64;
            int regionRight = this.guiLeft + 355;
            int dividerX = regionLeft + this.dividerOffset;
            if (this.leftScroll == null) {
                this.leftScroll = new GuiCustomScroll((GuiScreen)this, 0, 0);
            }
            this.leftScroll.guiLeft = regionLeft;
            this.leftScroll.guiTop = y;
            this.leftScroll.setSize(dividerX - regionLeft, 185);
            this.leftScroll.setList(new ArrayList<String>(this.cycleData.keySet()));
            this.addScroll(this.leftScroll);
            if (this.rightScroll == null) {
                this.rightScroll = new GuiCustomScroll((GuiScreen)this, 1, 0);
            }
            this.rightScroll.guiLeft = dividerX + 5;
            this.rightScroll.guiTop = y;
            this.rightScroll.setSize(regionRight - (dividerX + 5), 185);
            this.addScroll(this.rightScroll);
            this.addTextField(new GuiNpcTextField(55, this, this.field_146289_q, regionLeft, y + 185 + 3, dividerX - regionLeft, 20, this.cycleSearch));
            this.addTextField(new GuiNpcTextField(66, this, this.field_146289_q, dividerX + 5, y + 185 + 3, regionRight - (dividerX + 5), 20, this.magicSearch));
            this.addButton(new GuiNpcButton(10, this.guiLeft + 3, this.guiTop + 8, 58, 20, "menu.cycles"));
            this.getButton(10).setEnabled(false);
            this.addButton(new GuiNpcButton(4, this.guiLeft + 3, this.guiTop + 38, 58, 20, "gui.add"));
            this.addButton(new GuiNpcButton(5, this.guiLeft + 3, this.guiTop + 61, 58, 20, "gui.remove"));
            this.addButton(new GuiNpcButton(6, this.guiLeft + 3, this.guiTop + 94, 58, 20, "gui.edit"));
            this.addButton(new GuiNpcButton(20, this.guiLeft + 3, this.guiTop + 117, 58, 20, "gui.view"));
            this.addButton(new GuiNpcButton(33, this.guiLeft + 358, this.guiTop + 8, 58, 20, "menu.magics"));
            this.getButton(33).setEnabled(false);
            this.addButton(new GuiNpcButton(0, this.guiLeft + 358, this.guiTop + 38, 58, 20, "gui.add"));
            this.getButton(0).setEnabled(false);
            this.addButton(new GuiNpcButton(1, this.guiLeft + 358, this.guiTop + 61, 58, 20, "gui.remove"));
            this.getButton(1).setEnabled(false);
            this.addButton(new GuiNpcButton(2, this.guiLeft + 358, this.guiTop + 94, 58, 20, "gui.edit"));
            this.addLabel(new GuiNpcLabel(200, "ID", this.guiLeft + 4, this.guiTop + 4 + 3 + 185));
            this.addLabel(new GuiNpcLabel(201, "", this.guiLeft + 4, this.guiTop + 4 + 3 + 195));
        } else {
            if (this.rightScroll == null) {
                this.rightScroll = new GuiCustomScroll((GuiScreen)this, 1, 0);
            }
            this.rightScroll.guiLeft = this.guiLeft + 10;
            this.rightScroll.guiTop = y;
            this.rightScroll.setSize(this.xSize - 75, 185);
            this.addScroll(this.rightScroll);
            this.addTextField(new GuiNpcTextField(66, this, this.field_146289_q, this.guiLeft + 10, y + 185 + 3, this.xSize - 75, 20, this.magicSearch));
            this.addButton(new GuiNpcButton(33, this.guiLeft + 358, this.guiTop + 8, 58, 20, "menu.magics"));
            this.getButton(33).setEnabled(false);
            this.addButton(new GuiNpcButton(0, this.guiLeft + 358, this.guiTop + 38, 58, 20, "gui.add"));
            this.addButton(new GuiNpcButton(1, this.guiLeft + 358, this.guiTop + 61, 58, 20, "gui.remove"));
            this.addButton(new GuiNpcButton(2, this.guiLeft + 358, this.guiTop + 94, 58, 20, "gui.edit"));
        }
        if (this.viewByCycle) {
            this.currentMagicList = new ArrayList<String>(this.magicData.keySet());
            if (this.rightScroll != null) {
                this.rightScroll.setList(this.applyMagicSearchFilter(this.currentMagicList));
            }
        } else if (this.selectedCycle != null) {
            this.updateMagicList();
        } else {
            this.currentMagicList.clear();
            if (this.rightScroll != null) {
                this.rightScroll.setList(this.applyMagicSearchFilter(this.currentMagicList));
            }
        }
        this.addLabel(new GuiNpcLabel(100, "ID", this.guiLeft + 358, this.guiTop + 4 + 3 + 185));
        this.addLabel(new GuiNpcLabel(101, "", this.guiLeft + 358, this.guiTop + 4 + 3 + 195));
        this.updateButtons();
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        super.func_73863_a(mouseX, mouseY, partialTicks);
        if (!this.viewByCycle && !this.hasSubGui()) {
            int regionLeft = this.guiLeft + 64;
            int dividerX = regionLeft + this.dividerOffset;
            int regionTop = this.guiTop + 30;
            int regionHeight = 185;
            int handleTop = regionTop + (regionHeight - 20) / 2 + 0;
            GuiNpcManageMagic.func_73734_a((int)(dividerX + 1), (int)handleTop, (int)(dividerX + 5 - 1), (int)(handleTop + 20), (int)-9408400);
        }
    }

    @Override
    public void func_73864_a(int mouseX, int mouseY, int mouseButton) {
        if (!this.viewByCycle && !this.hasSubGui()) {
            int regionLeft = this.guiLeft + 64;
            int dividerX = regionLeft + this.dividerOffset;
            int regionTop = this.guiTop + 30;
            int regionHeight = 185;
            int handleTop = regionTop + (regionHeight - 20) / 2 + 0;
            int handleBottom = handleTop + 20;
            if (mouseX >= dividerX && mouseX <= dividerX + 5 && mouseY >= handleTop && mouseY <= handleBottom) {
                this.isResizing = true;
                resizingActive = true;
                this.initialDragX = mouseX;
                return;
            }
        }
        super.func_73864_a(mouseX, mouseY, mouseButton);
    }

    public void updateButtons() {
        GuiNpcButton button = this.getButton(2);
        if (button != null) {
            boolean bl = button.field_146124_l = this.selectedMagic != null;
        }
        if (this.viewByCycle && (button = this.getButton(1)) != null) {
            boolean bl = button.field_146124_l = this.selectedMagic != null;
        }
        if ((button = this.getButton(5)) != null) {
            boolean bl = button.field_146124_l = this.selectedCycle != null;
        }
        if ((button = this.getButton(6)) != null) {
            boolean bl = button.field_146124_l = this.selectedCycle != null;
        }
        if ((button = this.getButton(20)) != null) {
            boolean bl = button.field_146124_l = this.selectedCycle != null;
        }
        if (this.getLabel(201) != null) {
            this.getLabel((int)201).label = this.selectedCycle != null ? this.selectedCycle.id + "" : "";
        }
        if (this.getLabel(101) != null) {
            this.getLabel((int)101).label = this.selectedMagic != null ? this.selectedMagic.id + "" : "";
        }
    }

    @Override
    public void save() {
    }

    @Override
    public void func_146273_a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if (!this.viewByCycle && this.isResizing) {
            int dx = mouseX - this.initialDragX;
            this.initialDragX = mouseX;
            this.dividerOffset += dx;
            int regionLeft = this.guiLeft + 64;
            int regionRight = this.guiLeft + 355;
            int minOffset = 50;
            int maxOffset = regionRight - regionLeft - 5 - 50;
            if (this.dividerOffset < minOffset) {
                this.dividerOffset = minOffset;
            }
            if (this.dividerOffset > maxOffset) {
                this.dividerOffset = maxOffset;
            }
            int dividerX = regionLeft + this.dividerOffset;
            this.leftScroll.setSize(dividerX - regionLeft, 185);
            this.rightScroll.guiLeft = dividerX + 5;
            this.rightScroll.setSize(regionRight - (dividerX + 5), 185);
            if (this.getTextField(55) != null) {
                this.getTextField((int)55).field_146218_h = dividerX - regionLeft;
            }
            if (this.getTextField(66) != null) {
                this.getTextField((int)66).field_146218_h = regionRight - (dividerX + 5);
                this.getTextField((int)66).field_146209_f = dividerX + 5;
            }
            return;
        }
        super.func_146273_a(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    protected void func_146286_b(int mouseX, int mouseY, int state) {
        if (this.isResizing) {
            this.isResizing = false;
            resizingActive = false;
            return;
        }
        super.func_146286_b(mouseX, mouseY, state);
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (!this.viewByCycle && this.getTextField(55) != null && this.getTextField(55).func_146206_l() && !this.cycleSearch.equals(this.getTextField(55).func_146179_b())) {
            this.cycleSearch = this.getTextField(55).func_146179_b().toLowerCase();
            this.leftScroll.resetScroll();
            this.leftScroll.setList(this.getCycleSearch());
        }
        if (this.getTextField(66) != null && this.getTextField(66).func_146206_l() && !this.magicSearch.equals(this.getTextField(66).func_146179_b())) {
            this.magicSearch = this.getTextField(66).func_146179_b().toLowerCase();
            this.rightScroll.resetScroll();
            this.rightScroll.setList(this.applyMagicSearchFilter(this.currentMagicList));
        }
    }

    private List<String> getCycleSearch() {
        if (this.cycleSearch.isEmpty()) {
            return new ArrayList<String>(this.cycleData.keySet());
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : this.cycleData.keySet()) {
            if (!name.toLowerCase().contains(this.cycleSearch)) continue;
            list.add(name);
        }
        return list;
    }

    private List<String> applyMagicSearchFilter(List<String> list) {
        if (this.magicSearch.isEmpty()) {
            return new ArrayList<String>(list);
        }
        ArrayList<String> filtered = new ArrayList<String>();
        for (String name : list) {
            if (!name.toLowerCase().contains(this.magicSearch)) continue;
            filtered.add(name);
        }
        return filtered;
    }

    @Override
    public void func_146284_a(GuiButton button) {
        switch (button.field_146127_k) {
            case 50: {
                this.viewByCycle = !this.viewByCycle;
                this.selectedMagic = null;
                this.selectedCycle = null;
                this.cycleSearch = "";
                this.magicSearch = "";
                if (this.viewByCycle) {
                    this.currentMagicList = new ArrayList<String>(this.magicData.keySet());
                } else {
                    this.currentMagicList.clear();
                }
                if (this.rightScroll != null) {
                    this.rightScroll.selected = -1;
                }
                if (this.leftScroll != null) {
                    this.leftScroll.selected = -1;
                }
                this.func_73866_w_();
                return;
            }
            case 4: {
                MagicCycle magicCycle = new MagicCycle();
                magicCycle.name = "New";
                magicCycle.displayName = "New";
                NBTTagCompound compound = new NBTTagCompound();
                magicCycle.writeNBT(compound);
                PacketClient.sendClient(new MagicCycleSavePacket(compound));
                break;
            }
            case 5: {
                if (this.selectedCycle == null) break;
                PacketClient.sendClient(new MagicCycleRemovePacket(this.selectedCycle.id));
                this.selectedCycle = null;
                if (this.leftScroll == null) break;
                this.leftScroll.selected = -1;
                break;
            }
            case 6: {
                if (this.selectedCycle == null) break;
                this.setSubGui(new SubGuiMagicCycle(this, this.selectedCycle));
                break;
            }
            case 0: {
                Magic magic = new Magic();
                magic.name = "New";
                magic.displayName = "New";
                NBTTagCompound compound = new NBTTagCompound();
                magic.writeNBT(compound);
                PacketClient.sendClient(new MagicSavePacket(compound));
                if (this.selectedMagic == null) break;
                this.selectedMagic = null;
                if (this.rightScroll == null) break;
                this.rightScroll.selected = -1;
                break;
            }
            case 1: {
                if (this.selectedMagic == null) break;
                PacketClient.sendClient(new MagicRemovePacket(this.selectedMagic.id));
                this.selectedMagic = null;
                if (this.rightScroll == null) break;
                this.rightScroll.selected = -1;
                break;
            }
            case 2: {
                if (this.selectedMagic == null) break;
                this.setSubGui(new SubGuiMagic(this, this.selectedMagic));
                break;
            }
            case 20: {
                if (this.selectedCycle == null) break;
                this.setSubGui(new SubGuiMagicCycleViewer(this.selectedCycle));
            }
        }
        this.updateButtons();
    }

    @Override
    public void customScrollClicked(int id, int index, int clickType, GuiCustomScroll scroll) {
        if (!this.viewByCycle) {
            if (scroll.id == 0) {
                this.selectedCycle = null;
                String cycleName = scroll.getSelected();
                if (this.cycleData.containsKey(cycleName)) {
                    MagicGetPacket.GetCycle(this.cycleData.get(cycleName));
                }
            } else if (scroll.id == 1) {
                this.selectedMagic = null;
                String magicName = scroll.getSelected();
                if (this.magicData.containsKey(magicName)) {
                    MagicGetPacket.GetMagic(this.magicData.get(magicName));
                }
            }
        } else if (scroll.id == 1) {
            this.selectedMagic = null;
            String magicName = scroll.getSelected();
            if (this.magicData.containsKey(magicName)) {
                MagicGetPacket.GetMagic(this.magicData.get(magicName));
            }
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        PacketClient.sendClient(new MagicGetAllPacket());
        this.func_73866_w_();
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        if (type == EnumScrollData.MAGIC_CYCLES) {
            this.cycleData.clear();
            this.cycleData.putAll(data);
            if (!this.viewByCycle) {
                this.leftScroll.setList(new ArrayList<String>(this.cycleData.keySet()));
            }
        } else if (type == EnumScrollData.MAGIC) {
            this.magicData.clear();
            this.magicData.putAll(data);
            if (!this.viewByCycle) {
                this.updateMagicList();
            } else {
                this.currentMagicList = new ArrayList<String>(this.magicData.keySet());
                if (this.rightScroll != null) {
                    this.rightScroll.setList(this.applyMagicSearchFilter(this.currentMagicList));
                }
            }
        }
        this.func_73866_w_();
    }

    public void updateMagicList() {
        this.currentMagicList.clear();
        if (this.selectedCycle != null) {
            block0: for (int magicId : this.selectedCycle.associations.keySet()) {
                for (String magicName : this.magicData.keySet()) {
                    if (this.magicData.get(magicName) != magicId) continue;
                    this.currentMagicList.add(magicName);
                    continue block0;
                }
            }
        }
        if (this.rightScroll != null) {
            this.rightScroll.setList(this.applyMagicSearchFilter(this.currentMagicList));
        }
    }

    @Override
    public void setSelected(String selected) {
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        if (compound.func_74764_b("Magic")) {
            this.selectedMagic = new Magic();
            this.selectedMagic.readNBT(compound.func_74775_l("Magic"));
        } else if (compound.func_74764_b("MagicCycle")) {
            this.selectedCycle = new MagicCycle();
            this.selectedCycle.readNBT(compound.func_74775_l("MagicCycle"));
            this.updateMagicList();
            this.selectedMagic = null;
            if (this.rightScroll != null) {
                this.rightScroll.selected = -1;
            }
        }
        this.updateButtons();
    }
}

