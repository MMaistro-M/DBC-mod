/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui.advanced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.ability.CustomAbilitiesGetPacket;
import kamkeel.npcs.network.packets.request.ability.CustomAbilityGetPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.advanced.GuiNPCAbilities;
import noppes.npcs.client.gui.advanced.SubGuiAbilityConfig;
import noppes.npcs.client.gui.advanced.SubGuiAbilityLoadMode;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumScrollData;

public class SubGuiAbilityLoad
extends SubGuiInterface
implements ICustomScrollListener,
IScrollData,
IGuiData,
ISubGuiListener {
    private final SubGuiAbilityConfig parentConfig;
    private final GuiNPCAbilities parentAbilities;
    private String selectedDisplayName = null;
    private String selectedName = null;
    private HashMap<String, Integer> rawData = new HashMap();
    private GuiCustomScroll scroll;
    private String search = "";
    private boolean waitingForLoad = false;
    private int pendingLoadMode = -1;

    public SubGuiAbilityLoad(SubGuiAbilityConfig parentConfig) {
        this.parentConfig = parentConfig;
        this.parentAbilities = null;
        this.setBackground("menubg.png");
        this.xSize = 220;
        this.ySize = 216;
        PacketClient.sendClient(new CustomAbilitiesGetPacket());
    }

    public SubGuiAbilityLoad(GuiNPCAbilities parentAbilities) {
        this.parentConfig = null;
        this.parentAbilities = parentAbilities;
        this.setBackground("menubg.png");
        this.xSize = 220;
        this.ySize = 216;
        PacketClient.sendClient(new CustomAbilitiesGetPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 5;
        this.addLabel(new GuiNpcLabel(0, "ability.load.select", this.guiLeft + 10, y));
        y += 14;
        if (this.scroll == null) {
            this.scroll = new GuiCustomScroll(this, 0);
            this.scroll.setSize(200, 140);
        }
        this.scroll.guiLeft = this.guiLeft + 10;
        this.scroll.guiTop = y;
        this.scroll.setList(this.getFilteredList());
        if (this.selectedDisplayName != null) {
            this.scroll.setSelected(this.selectedDisplayName);
        }
        this.addScroll(this.scroll);
        this.addTextField(new GuiNpcTextField(10, this, this.field_146289_q, this.guiLeft + 10, y += 143, 200, 20, this.search));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 10, y += 24, 95, 20, "gui.load"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 115, y, 95, 20, "gui.cancel"));
        this.getButton(0).setEnabled(this.selectedName != null);
    }

    private List<String> getFilteredList() {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : this.rawData.entrySet()) {
            String name = entry.getKey();
            UserType ut = UserType.fromOrdinal(entry.getValue());
            if (!ut.allowsNpc() || !this.search.isEmpty() && !name.toLowerCase().contains(this.search.toLowerCase())) continue;
            list.add(name);
        }
        return list;
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 0 && this.selectedName != null) {
            if (this.parentAbilities != null) {
                this.setSubGui(new SubGuiAbilityLoadMode());
            } else {
                this.waitingForLoad = true;
                this.pendingLoadMode = 0;
                PacketClient.sendClient(new CustomAbilityGetPacket(this.selectedName));
            }
        } else if (id == 2) {
            this.close();
        }
    }

    @Override
    public void func_73869_a(char c, int i) {
        String newSearch;
        super.func_73869_a(c, i);
        if (this.getTextField(10) != null && this.getTextField(10).func_146206_l() && !this.search.equals(newSearch = this.getTextField(10).func_146179_b())) {
            this.search = newSearch;
            this.scroll.resetScroll();
            this.scroll.setList(this.getFilteredList());
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiAbilityLoadMode) {
            int mode = ((SubGuiAbilityLoadMode)subgui).getResult();
            if (mode < 0) {
                return;
            }
            if (mode == 1) {
                if (this.parentAbilities != null && this.selectedName != null) {
                    this.parentAbilities.loadAbilityReference(this.selectedName);
                }
                this.close();
            } else {
                this.waitingForLoad = true;
                this.pendingLoadMode = 0;
                PacketClient.sendClient(new CustomAbilityGetPacket(this.selectedName));
            }
        }
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll scroll) {
        if (scroll.id == 0) {
            this.selectedName = this.selectedDisplayName = scroll.getSelected();
            this.func_73866_w_();
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
        if (scroll.id == 0 && selection != null && !selection.isEmpty()) {
            this.selectedDisplayName = selection;
            this.selectedName = selection;
            if (this.parentAbilities != null) {
                this.setSubGui(new SubGuiAbilityLoadMode());
            } else {
                this.waitingForLoad = true;
                this.pendingLoadMode = 0;
                PacketClient.sendClient(new CustomAbilityGetPacket(this.selectedName));
            }
        }
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        if (type == EnumScrollData.CUSTOM_ABILITIES) {
            this.rawData = data;
            if (this.scroll != null) {
                this.scroll.setList(this.getFilteredList());
                if (this.selectedDisplayName != null && this.rawData.containsKey(this.selectedDisplayName)) {
                    this.scroll.setSelected(this.selectedDisplayName);
                }
            }
            this.func_73866_w_();
        }
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        if (this.waitingForLoad) {
            this.waitingForLoad = false;
            Ability loaded = AbilityController.Instance.fromNBT(compound);
            if (loaded != null) {
                if (this.parentConfig != null) {
                    this.parentConfig.loadAbility(loaded);
                } else if (this.parentAbilities != null) {
                    this.parentAbilities.loadAbility(loaded, this.selectedName);
                }
            }
            this.close();
        }
    }

    @Override
    public void setSelected(String selected) {
        this.selectedDisplayName = selected;
        if (this.scroll != null) {
            this.scroll.setSelected(selected);
        }
    }
}

