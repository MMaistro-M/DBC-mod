/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui.advanced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.ability.CustomAbilitiesGetPacket;
import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumScrollData;

public class SubGuiAbilitySelect
extends SubGuiInterface
implements ICustomScrollListener,
IScrollData {
    public static final int FILTER_ALL = 0;
    public static final int FILTER_CUSTOM_ONLY = 1;
    public static final int FILTER_BUILTIN_ONLY = 2;
    private GuiCustomScroll scroll;
    private HashMap<String, Integer> customData = new HashMap();
    private HashMap<String, Integer> builtInData = new HashMap();
    private String selectedName = null;
    private String search = "";
    private final int filterMode;

    public SubGuiAbilitySelect() {
        this(0);
    }

    public SubGuiAbilitySelect(int filterMode) {
        this.filterMode = filterMode;
        this.setBackground("menubg.png");
        this.xSize = 220;
        this.ySize = 216;
        PacketClient.sendClient(new CustomAbilitiesGetPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 5;
        this.addLabel(new GuiNpcLabel(0, "ability.select", this.guiLeft + 10, y));
        y += 14;
        if (this.scroll == null) {
            this.scroll = new GuiCustomScroll(this, 0);
            this.scroll.setSize(200, 140);
        }
        this.scroll.guiLeft = this.guiLeft + 10;
        this.scroll.guiTop = y;
        this.scroll.setList(this.getFilteredList());
        if (this.selectedName != null) {
            this.scroll.setSelected(this.selectedName);
        }
        this.addScroll(this.scroll);
        this.addTextField(new GuiNpcTextField(10, this, this.field_146289_q, this.guiLeft + 10, y += 143, 200, 20, this.search));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 10, y += 24, 95, 20, "gui.select"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 115, y, 95, 20, "gui.cancel"));
        this.getButton(0).setEnabled(this.selectedName != null);
    }

    private List<String> getFilteredList() {
        HashMap<String, Integer> merged = new HashMap<String, Integer>();
        if (this.filterMode != 2) {
            merged.putAll(this.customData);
        }
        if (this.filterMode != 1) {
            merged.putAll(this.builtInData);
        }
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry entry : merged.entrySet()) {
            String name = (String)entry.getKey();
            UserType ut = UserType.fromOrdinal((Integer)entry.getValue());
            if (!ut.allowsNpc() || !this.search.isEmpty() && !name.toLowerCase().contains(this.search.toLowerCase())) continue;
            list.add(name);
        }
        return list;
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 0 && this.selectedName != null) {
            this.close();
        } else if (id == 2) {
            this.selectedName = null;
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
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll scroll) {
        if (scroll.id == 0) {
            this.selectedName = scroll.getSelected();
            this.func_73866_w_();
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
        if (scroll.id == 0 && selection != null && !selection.isEmpty()) {
            this.selectedName = selection;
            this.close();
        }
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        if (type == EnumScrollData.CUSTOM_ABILITIES) {
            this.customData = data;
        } else if (type == EnumScrollData.BUILTIN_ABILITIES) {
            this.builtInData = data;
        }
        if (this.scroll != null) {
            this.scroll.setList(this.getFilteredList());
            if (this.selectedName != null) {
                this.scroll.setSelected(this.selectedName);
            }
        }
        this.func_73866_w_();
    }

    @Override
    public void setSelected(String selected) {
        this.selectedName = selected;
        if (this.scroll != null) {
            this.scroll.setSelected(selected);
        }
    }

    public String getSelectedName() {
        return this.selectedName;
    }
}

