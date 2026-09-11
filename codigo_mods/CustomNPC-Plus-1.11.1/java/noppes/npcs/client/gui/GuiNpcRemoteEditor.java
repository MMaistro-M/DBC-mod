/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiYesNo
 *  net.minecraft.client.gui.GuiYesNoCallback
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.npc.RemoteDeletePacket;
import kamkeel.npcs.network.packets.request.npc.RemoteFreezeGetPacket;
import kamkeel.npcs.network.packets.request.npc.RemoteFreezePacket;
import kamkeel.npcs.network.packets.request.npc.RemoteGlobalMenuPacket;
import kamkeel.npcs.network.packets.request.npc.RemoteMainMenuPacket;
import kamkeel.npcs.network.packets.request.npc.RemoteNpcsGetPacket;
import kamkeel.npcs.network.packets.request.npc.RemoteResetPacket;
import kamkeel.npcs.network.packets.request.npc.RemoteTpToNpcPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNpcRemoteEditor
extends GuiNPCInterface
implements IScrollData,
GuiYesNoCallback {
    private GuiCustomScroll scroll;
    private HashMap<String, Integer> data = new HashMap();
    private String search = "";

    public GuiNpcRemoteEditor() {
        this.xSize = 256;
        this.setBackground("menubg.png");
        PacketClient.sendClient(new RemoteNpcsGetPacket());
        PacketClient.sendClient(new RemoteFreezeGetPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        if (this.scroll == null) {
            this.scroll = new GuiCustomScroll((GuiScreen)this, 0, 0);
            this.scroll.setSize(165, 188);
        }
        this.scroll.guiLeft = this.guiLeft + 4;
        this.scroll.guiTop = this.guiTop + 4;
        this.addScroll(this.scroll);
        String title = StatCollector.func_74838_a((String)"remote.title");
        int x = (this.xSize - this.field_146289_q.func_78256_a(title)) / 2;
        this.addTextField(new GuiNpcTextField(66, this, this.field_146289_q, this.guiLeft + 4, this.guiTop + 5 + this.scroll.ySize, this.scroll.xSize, 20, this.search));
        this.addLabel(new GuiNpcLabel(0, title, this.guiLeft + x, this.guiTop - 8));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 170, this.guiTop + 6, 82, 20, "selectServer.edit"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 170, this.guiTop + 28, 82, 20, "selectWorld.deleteButton"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 170, this.guiTop + 50, 82, 20, "remote.reset"));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 170, this.guiTop + 132, 82, 20, "remote.freeze"));
        this.addButton(new GuiNpcButton(4, this.guiLeft + 170, this.guiTop + 72, 82, 20, "remote.tp"));
        this.addButton(new GuiNpcButton(5, this.guiLeft + 170, this.guiTop + 110, 82, 20, "remote.resetall"));
        this.addButton(new GuiNpcButton(6, this.guiLeft + 170, this.guiTop + 165, 82, 20, "menu.global"));
    }

    public void func_73878_a(boolean flag, int i) {
        if (flag) {
            PacketClient.sendClient(new RemoteDeletePacket(this.data.get(this.scroll.getSelected())));
        }
        NoppesUtil.openGUI((EntityPlayer)this.player, this);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 3) {
            PacketClient.sendClient(new RemoteFreezePacket());
        }
        if (id == 5) {
            for (int ids : this.data.values()) {
                PacketClient.sendClient(new RemoteResetPacket(ids));
                Entity entity = this.player.field_70170_p.func_73045_a(ids);
                if (entity == null || !(entity instanceof EntityNPCInterface)) continue;
                ((EntityNPCInterface)entity).reset();
            }
        }
        if (id == 6) {
            NoppesUtil.setLastNpc(null);
            PacketClient.sendClient(new RemoteGlobalMenuPacket());
        }
        if (!this.data.containsKey(this.scroll.getSelected())) {
            return;
        }
        if (id == 0) {
            PacketClient.sendClient(new RemoteMainMenuPacket(this.data.get(this.scroll.getSelected())));
        }
        if (id == 1) {
            GuiYesNo guiyesno = new GuiYesNo((GuiYesNoCallback)this, "Confirm", StatCollector.func_74838_a((String)"gui.delete"), 0);
            this.displayGuiScreen((GuiScreen)guiyesno);
        }
        if (id == 2) {
            int selected = this.data.get(this.scroll.getSelected());
            PacketClient.sendClient(new RemoteResetPacket(selected));
            Entity entity = this.player.field_70170_p.func_73045_a(selected);
            if (entity != null && entity instanceof EntityNPCInterface) {
                ((EntityNPCInterface)entity).reset();
            }
        }
        if (id == 4) {
            PacketClient.sendClient(new RemoteTpToNpcPacket(this.data.get(this.scroll.getSelected())));
            this.close();
        }
    }

    @Override
    public void func_73864_a(int i, int j, int k) {
        super.func_73864_a(i, j, k);
        this.scroll.func_73864_a(i, j, k);
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(66) != null && this.getTextField(66).func_146206_l()) {
            if (i == 1) {
                this.close();
                return;
            }
            if (this.search.equals(this.getTextField(66).func_146179_b())) {
                return;
            }
            this.search = this.getTextField(66).func_146179_b().toLowerCase();
            this.scroll.resetScroll();
            this.scroll.setList(this.getNPCSearch());
        }
        if (i == 1) {
            this.close();
        }
    }

    private List<String> getNPCSearch() {
        if (this.search.isEmpty()) {
            return new ArrayList<String>(this.data.keySet());
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : this.data.keySet()) {
            if (!name.toLowerCase().contains(this.search)) continue;
            list.add(name);
        }
        return list;
    }

    @Override
    public void save() {
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        this.data = data;
        this.scroll.resetScroll();
        this.scroll.setList(this.getNPCSearch());
    }

    @Override
    public void setSelected(String selected) {
        this.getButton(3).setDisplayText(selected);
    }
}

