/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiYesNo
 *  net.minecraft.client.gui.GuiYesNoCallback
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import kamkeel.npcs.network.packets.player.MailActionPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.player.GuiMailmanWrite;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.controllers.data.PlayerMail;
import noppes.npcs.controllers.data.PlayerMailData;

public class GuiMailbox
extends GuiNPCInterface
implements IGuiData,
ICustomScrollListener,
GuiYesNoCallback {
    private GuiCustomScroll scroll;
    private PlayerMailData data;
    private PlayerMail selected;

    public GuiMailbox() {
        this.xSize = 256;
        this.setBackground("menubg.png");
        MailActionPacket.RequestMailData();
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        if (this.scroll == null) {
            this.scroll = new GuiCustomScroll(this, 0);
            this.scroll.setSize(165, 186);
        }
        this.scroll.guiLeft = this.guiLeft + 4;
        this.scroll.guiTop = this.guiTop + 4;
        this.addScroll(this.scroll);
        String title = StatCollector.func_74838_a((String)"mailbox.name");
        int x = (this.xSize - this.field_146289_q.func_78256_a(title)) / 2;
        this.addLabel(new GuiNpcLabel(0, title, this.guiLeft + x, this.guiTop - 8));
        if (this.selected != null) {
            this.addLabel(new GuiNpcLabel(3, StatCollector.func_74838_a((String)"mailbox.sender") + ":", this.guiLeft + 170, this.guiTop + 6));
            this.addLabel(new GuiNpcLabel(1, this.selected.sender, this.guiLeft + 174, this.guiTop + 18));
            this.addLabel(new GuiNpcLabel(2, StatCollector.func_74837_a((String)"mailbox.timesend", (Object[])new Object[]{this.getTimePast()}), this.guiLeft + 174, this.guiTop + 30));
        }
        this.addButton(new GuiNpcButton(0, this.guiLeft + 4, this.guiTop + 192, 82, 20, "mailbox.read"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 88, this.guiTop + 192, 82, 20, "selectWorld.deleteButton"));
        this.getButton(1).setEnabled(this.selected != null);
    }

    private String getTimePast() {
        if (this.selected.timePast > 86400000L) {
            int days = (int)(this.selected.timePast / 86400000L);
            if (days == 1) {
                return days + " " + StatCollector.func_74838_a((String)"mailbox.day");
            }
            return days + " " + StatCollector.func_74838_a((String)"mailbox.days");
        }
        if (this.selected.timePast > 3600000L) {
            int hours = (int)(this.selected.timePast / 3600000L);
            if (hours == 1) {
                return hours + " " + StatCollector.func_74838_a((String)"mailbox.hour");
            }
            return hours + " " + StatCollector.func_74838_a((String)"mailbox.hours");
        }
        int minutes = (int)(this.selected.timePast / 60000L);
        if (minutes == 1) {
            return minutes + " " + StatCollector.func_74838_a((String)"mailbox.minute");
        }
        return minutes + " " + StatCollector.func_74838_a((String)"mailbox.minutes");
    }

    public void func_73878_a(boolean flag, int i) {
        if (flag && this.selected != null) {
            MailActionPacket.DeleteMail(this.selected.time, this.selected.sender);
            this.selected = null;
        }
        NoppesUtil.openGUI((EntityPlayer)this.player, this);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (this.scroll.selected < 0) {
            return;
        }
        if (id == 0) {
            GuiMailmanWrite.parent = this;
            GuiMailmanWrite.mail = this.selected;
            MailActionPacket.OpenMail(this.selected.time, this.selected.sender);
            this.selected = null;
            this.scroll.selected = -1;
        }
        if (id == 1) {
            GuiYesNo guiyesno = new GuiYesNo((GuiYesNoCallback)this, "Confirm", StatCollector.func_74838_a((String)"gui.delete"), 0);
            this.displayGuiScreen((GuiScreen)guiyesno);
        }
    }

    @Override
    public void func_73864_a(int i, int j, int k) {
        super.func_73864_a(i, j, k);
        this.scroll.func_73864_a(i, j, k);
    }

    @Override
    public void func_73869_a(char c, int i) {
        if (i == 1 || this.isInventoryKey(i)) {
            this.close();
        }
    }

    @Override
    public void save() {
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        PlayerMailData data = new PlayerMailData();
        data.loadNBTData(compound);
        ArrayList<String> list = new ArrayList<String>();
        Collections.sort(data.playermail, new Comparator<PlayerMail>(){

            @Override
            public int compare(PlayerMail o1, PlayerMail o2) {
                if (o1.time == o2.time) {
                    return 0;
                }
                return o1.time > o2.time ? -1 : 1;
            }
        });
        for (PlayerMail mail : data.playermail) {
            list.add(mail.subject);
        }
        this.data = data;
        this.scroll.clear();
        this.selected = null;
        this.scroll.setUnsortedList(list);
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        this.selected = this.data.playermail.get(guiCustomScroll.selected);
        this.func_73866_w_();
        if (this.selected != null && !this.selected.beenRead) {
            this.selected.beenRead = true;
            MailActionPacket.ReadMail(this.selected.time, this.selected.sender);
        }
    }
}

