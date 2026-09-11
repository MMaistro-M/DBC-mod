/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiYesNo
 *  net.minecraft.client.gui.GuiYesNoCallback
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.playerdata.PlayerDataGetNamesPacket;
import kamkeel.npcs.network.packets.request.playerdata.PlayerDataMapRegenPacket;
import kamkeel.npcs.network.packets.request.playerdata.PlayerDataRemovePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.SubGuiPlayerData;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumPlayerData;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNpcManagePlayerData
extends GuiNPCInterface2
implements GuiYesNoCallback,
IScrollData,
ICustomScrollListener,
ISubGuiListener {
    private GuiCustomScroll playerScroll;
    private String selectedPlayer = null;
    private HashMap<String, Integer> playerData = new HashMap();
    private String search = "";

    public GuiNpcManagePlayerData(EntityNPCInterface npcInterface) {
        super(npcInterface);
        PacketClient.sendClient(new PlayerDataGetNamesPacket(EnumPlayerData.Players, ""));
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        if (this.playerScroll == null) {
            this.playerScroll = new GuiCustomScroll((GuiScreen)this, 0, 0);
        }
        this.playerScroll.guiLeft = this.guiLeft + 4;
        this.playerScroll.guiTop = this.guiTop + 16;
        this.playerScroll.setSize(303, 175);
        this.addScroll(this.playerScroll);
        this.addLabel(new GuiNpcLabel(0, StatCollector.func_74838_a((String)"All Players"), this.guiLeft + 10, this.guiTop + 6));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 313, this.guiTop + 10, 98, 20, "gui.remove"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 313, this.guiTop + 32, 98, 20, "gui.view"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 313, this.guiTop + 54, 98, 20, "gui.playerMap"));
        this.addTextField(new GuiNpcTextField(0, this, this.field_146289_q, this.guiLeft + 4, this.guiTop + 193, 303, 20, this.search));
        this.playerScroll.setList(this.getSearchList());
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        super.func_73863_a(i, j, f);
        this.playerScroll.func_73863_a(i, j, f);
    }

    @Override
    public void func_73864_a(int i, int j, int k) {
        super.func_73864_a(i, j, k);
        if (k == 0 && this.playerScroll != null) {
            this.playerScroll.func_73864_a(i, j, k);
        }
    }

    @Override
    public void save() {
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        String newText = this.getTextField(0).func_146179_b();
        if (!this.search.equals(newText)) {
            this.search = newText.toLowerCase();
            this.playerScroll.setList(this.getSearchList());
        }
    }

    private List<String> getSearchList() {
        if (this.search.isEmpty()) {
            return new ArrayList<String>(this.playerData.keySet());
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : this.playerData.keySet()) {
            if (!name.toLowerCase().contains(this.search)) continue;
            list.add(name);
        }
        return list;
    }

    @Override
    public void func_146284_a(GuiButton button) {
        int id = button.field_146127_k;
        if (id == 0 && this.selectedPlayer != null) {
            this.displayGuiScreen((GuiScreen)new GuiYesNo((GuiYesNoCallback)this, this.selectedPlayer, StatCollector.func_74838_a((String)"gui.deleteconfirm"), 0));
        }
        if (id == 1 && this.selectedPlayer != null) {
            this.setSubGui(new SubGuiPlayerData(this.selectedPlayer));
        }
        if (id == 2) {
            this.displayGuiScreen((GuiScreen)new GuiYesNo((GuiYesNoCallback)this, StatCollector.func_74838_a((String)"gui.warning"), StatCollector.func_74838_a((String)"gui.regenconfirm"), 1));
        }
    }

    public void func_73878_a(boolean confirm, int id) {
        if (confirm) {
            if (id == 0 && this.selectedPlayer != null && !this.selectedPlayer.isEmpty()) {
                PacketClient.sendClient(new PlayerDataRemovePacket(EnumPlayerData.Players, this.selectedPlayer));
                this.playerData.remove(this.selectedPlayer);
                this.selectedPlayer = null;
                this.playerScroll.setList(this.getSearchList());
            }
            if (id == 1) {
                PacketClient.sendClient(new PlayerDataMapRegenPacket());
            }
        }
        this.displayGuiScreen(this);
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        this.playerData.clear();
        this.playerData.putAll(data);
        this.playerScroll.setList(this.getSearchList());
    }

    @Override
    public void setSelected(String selected) {
        this.selectedPlayer = selected;
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll scroll) {
        this.selectedPlayer = scroll.getSelected();
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
    }
}

