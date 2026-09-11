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
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player.inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.player.CheckPlayerValue;
import kamkeel.npcs.network.packets.request.party.PartyAcceptInvitePacket;
import kamkeel.npcs.network.packets.request.party.PartyDisbandPacket;
import kamkeel.npcs.network.packets.request.party.PartyIgnoreInvitePacket;
import kamkeel.npcs.network.packets.request.party.PartyInfoPacket;
import kamkeel.npcs.network.packets.request.party.PartyInvitePacket;
import kamkeel.npcs.network.packets.request.party.PartyKickPacket;
import kamkeel.npcs.network.packets.request.party.PartyLeavePacket;
import kamkeel.npcs.network.packets.request.party.PartyLogToServerPacket;
import kamkeel.npcs.network.packets.request.party.PartySavePacket;
import kamkeel.npcs.network.packets.request.party.PartySetLeaderPacket;
import kamkeel.npcs.network.packets.request.party.PartySetQuestPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.QuestLogData;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.gui.player.inventory.GuiCNPCInventory;
import noppes.npcs.client.gui.util.GuiButtonNextPage;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IPartyData;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.Quest;
import org.lwjgl.opengl.GL11;
import tconstruct.client.tabs.AbstractTab;

public class GuiParty
extends GuiCNPCInventory
implements ITextfieldListener,
ICustomScrollListener,
IPartyData,
GuiYesNoCallback {
    private final ResourceLocation resource = new ResourceLocation("customnpcs", "textures/gui/standardbg.png");
    private final EntityPlayer player;
    private boolean receivedData;
    private long renderTicks;
    private String selectedPlayer;
    private String selectedInvite;
    private boolean partyChanged = false;
    private boolean isLeader;
    private final HashMap<String, String> invites = new HashMap();
    private boolean showQuestText;
    private final Vector<String> questLogStatus = new Vector();
    private String questCompleteWith;
    private String trackedQuestKey;
    private String originalTracked = "";
    private final QuestLogData data = new QuestLogData();

    public GuiParty() {
        this.player = this.field_146297_k.field_71439_g;
        this.xSize = 280;
        this.ySize = 180;
        this.drawDefaultBackground = false;
        PacketClient.sendClient(new CheckPlayerValue(CheckPlayerValue.Type.TrackedQuest));
        PacketClient.sendClient(new PartyInfoPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.selectedPlayer = null;
        this.selectedInvite = null;
        Party party = ClientCacheHandler.party;
        if (this.receivedData) {
            if (party == null) {
                GuiNpcButton createPartyButton = new GuiNpcButton(200, this.guiLeft + this.xSize / 2 + 40, this.guiTop + this.ySize / 2 + 20, "party.createParty");
                createPartyButton.field_146120_f = 100;
                this.addButton(createPartyButton);
                GuiCustomScroll inviteScroll = new GuiCustomScroll((GuiScreen)this, 210, false);
                inviteScroll.setSize(135, 165);
                inviteScroll.guiLeft = this.guiLeft + 5;
                inviteScroll.guiTop = this.guiTop + 5;
                inviteScroll.setList(new ArrayList<String>(this.invites.keySet()));
                this.addScroll(inviteScroll);
                GuiNpcButton acceptButton = new GuiNpcButton(215, this.guiLeft + 5, this.guiTop + this.ySize - 8, "party.accept");
                acceptButton.field_146120_f = 65;
                this.addButton(acceptButton);
                GuiNpcButton ignoreButton = new GuiNpcButton(220, this.guiLeft + 75, this.guiTop + this.ySize - 8, "party.ignore");
                ignoreButton.field_146120_f = 65;
                this.addButton(ignoreButton);
            } else {
                GuiNpcButton disbandButton;
                GuiCustomScroll playerScroll = new GuiCustomScroll((GuiScreen)this, 300, false);
                playerScroll.setSize(135, 120);
                playerScroll.guiLeft = this.guiLeft + 5;
                playerScroll.guiTop = this.guiTop + 5;
                ArrayList<String> arrayList = new ArrayList<String>();
                Collection<String> playerNames = party.getPlayerNames();
                for (String s : playerNames) {
                    if (party.getPartyLeaderName().equals(s)) {
                        arrayList.add(s + " \u00a7e[" + StatCollector.func_74838_a((String)"party.leader").toUpperCase() + "]");
                        continue;
                    }
                    arrayList.add(s);
                }
                playerScroll.setList(arrayList);
                this.addScroll(playerScroll);
                if (party.getIsLocked()) {
                    int arrowButtonsY = this.guiTop + this.ySize - 8 - 20;
                    this.addButton(new GuiButtonNextPage(400, this.guiLeft + 144, arrowButtonsY, false));
                    this.addLabel(new GuiNpcLabel(401, "quest.objectives", this.guiLeft + 168, this.getButton((int)400).field_146129_i + 3));
                    this.getButton((int)400).field_146125_m = this.getLabel((int)401).enabled = this.showQuestText;
                    this.addButton(new GuiButtonNextPage(405, this.guiLeft + 286, arrowButtonsY, true));
                    String textString = StatCollector.func_74838_a((String)"gui.text");
                    this.addLabel(new GuiNpcLabel(406, textString, this.guiLeft + 284 - this.field_146289_q.func_78256_a(textString), this.getButton((int)405).field_146129_i + 3));
                    this.getLabel((int)406).enabled = !this.getButton((int)400).field_146125_m;
                    this.getButton((int)405).field_146125_m = this.getLabel((int)406).enabled;
                    if (this.isLeader) {
                        disbandButton = new GuiNpcButton(410, this.guiLeft + 164, arrowButtonsY + 19, "party.dropQuest");
                        disbandButton.field_146120_f = 135;
                        this.addButton(disbandButton);
                    }
                }
                GuiNpcLabel friendlyFireLabel = new GuiNpcLabel(321, StatCollector.func_74838_a((String)"party.friendlyFire") + ":", this.guiLeft + 7, this.guiTop + this.ySize - 8 - 39);
                this.addLabel(friendlyFireLabel);
                GuiNpcButton friendlyFireButton = new GuiNpcButton(320, this.guiLeft + 10 + 89, friendlyFireLabel.y - 6, new String[]{"gui.on", "gui.off"}, party.friendlyFire() ? 0 : 1);
                friendlyFireButton.field_146120_f = 40;
                this.addButton(friendlyFireButton);
                boolean bl = this.getButton((int)320).field_146124_l = this.isLeader && !party.getIsLocked();
                if (this.isLeader) {
                    if (!party.getIsLocked()) {
                        GuiNpcButton leaderButton = new GuiNpcButton(305, this.guiLeft + 5, this.guiTop + this.ySize - 8 - 23, "party.setLeader");
                        leaderButton.field_146120_f = 65;
                        this.addButton(leaderButton);
                        GuiNpcButton kickButton = new GuiNpcButton(310, this.guiLeft + 75, this.guiTop + this.ySize - 8 - 23, "party.kick");
                        kickButton.field_146120_f = 65;
                        this.addButton(kickButton);
                        GuiNpcTextField playerTextField = new GuiNpcTextField(325, this, this.guiLeft + this.xSize / 2 + 10, this.guiTop + this.ySize / 2 + 25, 100, 20, "");
                        this.addTextField(playerTextField);
                        GuiNpcButton inviteButton = new GuiNpcButton(330, playerTextField.field_146209_f + 105, playerTextField.field_146210_g, "party.invite");
                        inviteButton.field_146120_f = 50;
                        this.addButton(inviteButton);
                    }
                    disbandButton = new GuiNpcButton(335, this.guiLeft + 5, this.guiTop + this.ySize - 9, "party.disbandParty");
                    disbandButton.field_146120_f = 135;
                    this.addButton(disbandButton);
                } else {
                    GuiNpcButton leaveButton = new GuiNpcButton(335, this.guiLeft + 5, this.guiTop + this.ySize - 9, "party.leaveParty");
                    leaveButton.field_146120_f = 135;
                    this.addButton(leaveButton);
                }
                if (party.getIsLocked()) {
                    GuiNpcButton trackButton = new GuiNpcButton(415, this.guiLeft + 5, this.guiTop + this.ySize - 8 - 23, new String[]{"party.track", "quest.tracking"}, this.data.trackedQuestKey.equals("P:" + this.trackedQuestKey) ? 1 : 0);
                    trackButton.field_146120_f = 135;
                    this.addButton(trackButton);
                    if (trackButton.field_146124_l && trackButton.getValue() == 1) {
                        trackButton.packedFGColour = 3329330;
                    }
                }
            }
        }
    }

    public void func_73878_a(boolean flag, int i) {
        if (flag) {
            switch (i) {
                case 0: {
                    PacketClient.sendClient(new PartySetLeaderPacket(this.selectedPlayer));
                    break;
                }
                case 1: {
                    PacketClient.sendClient(new PartyKickPacket(this.selectedPlayer));
                    break;
                }
                case 2: {
                    PacketClient.sendClient(new PartyDisbandPacket());
                    break;
                }
                case 3: {
                    PacketClient.sendClient(new PartyLeavePacket(this.player.func_70005_c_()));
                    break;
                }
                case 4: {
                    PacketClient.sendClient(new PartySetQuestPacket(-1));
                }
            }
            this.receivedData = false;
            this.func_73866_w_();
        }
        this.displayGuiScreen(this);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton instanceof AbstractTab) {
            return;
        }
        Party party = ClientCacheHandler.party;
        if (guibutton.field_146127_k <= -100) {
            super.func_146284_a(guibutton);
            return;
        }
        switch (guibutton.field_146127_k) {
            case 200: {
                PacketClient.sendClient(new PartyInfoPacket(true));
                this.receivedData = false;
                break;
            }
            case 215: {
                if (this.selectedInvite == null || this.selectedInvite.isEmpty()) break;
                PacketClient.sendClient(new PartyAcceptInvitePacket(this.selectedInvite));
                this.receivedData = false;
                return;
            }
            case 220: {
                if (this.selectedInvite == null || this.selectedInvite.isEmpty()) break;
                PacketClient.sendClient(new PartyIgnoreInvitePacket(this.selectedInvite));
                this.receivedData = false;
                return;
            }
            case 305: {
                if (this.selectedPlayer == null || this.selectedPlayer.isEmpty() || party.getPartyLeaderName().equals(this.selectedPlayer)) break;
                GuiYesNo yesnoLeader = new GuiYesNo((GuiYesNoCallback)this, StatCollector.func_74838_a((String)"party.leaderConfirm"), this.selectedPlayer, 0);
                this.displayGuiScreen((GuiScreen)yesnoLeader);
                return;
            }
            case 310: {
                if (this.selectedPlayer == null || this.selectedPlayer.isEmpty() || party.getPartyLeaderName().equals(this.selectedPlayer)) break;
                GuiYesNo yesnoKick = new GuiYesNo((GuiYesNoCallback)this, StatCollector.func_74838_a((String)"party.kickConfirm"), this.selectedPlayer, 1);
                this.displayGuiScreen((GuiScreen)yesnoKick);
                return;
            }
            case 320: {
                party.toggleFriendlyFire();
                this.partyChanged = true;
                break;
            }
            case 330: {
                String inviteName = this.getTextField(325).func_146179_b();
                if (inviteName.isEmpty() || party.getPlayerNames(true).contains(inviteName.toLowerCase())) break;
                PacketClient.sendClient(new PartyInvitePacket(inviteName));
                this.getTextField(325).func_146180_a("");
                break;
            }
            case 335: {
                if (this.isLeader) {
                    GuiYesNo yesnoDisband = new GuiYesNo((GuiYesNoCallback)this, "Confirm", StatCollector.func_74838_a((String)"party.disbandConfirm"), 2);
                    this.displayGuiScreen((GuiScreen)yesnoDisband);
                    break;
                }
                GuiYesNo yesnoLeave = new GuiYesNo((GuiYesNoCallback)this, "Confirm", StatCollector.func_74838_a((String)"party.leaveConfirm"), 3);
                this.displayGuiScreen((GuiScreen)yesnoLeave);
                break;
            }
            case 400: {
                this.showQuestText = false;
                break;
            }
            case 405: {
                this.showQuestText = true;
                break;
            }
            case 410: {
                if (!this.isLeader) break;
                GuiYesNo yesnoDisband = new GuiYesNo((GuiYesNoCallback)this, "Confirm", StatCollector.func_74838_a((String)"party.dropQuestConfirm"), 4);
                this.displayGuiScreen((GuiScreen)yesnoDisband);
                break;
            }
            case 415: {
                this.data.trackedQuestKey = !this.data.trackedQuestKey.equals("P:" + this.trackedQuestKey) ? "P:" + this.trackedQuestKey : "";
            }
        }
        this.func_73866_w_();
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        ++this.renderTicks;
        this.func_146276_q_();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(this.resource);
        this.func_73729_b(this.guiLeft, this.guiTop, 0, 0, 252, 195);
        this.func_73729_b(this.guiLeft + 252, this.guiTop, 188, 0, 67, 195);
        super.func_73863_a(i, j, f);
        if (!this.receivedData) {
            String periods = "";
            int k = 0;
            while ((long)k < this.renderTicks / 10L % 4L) {
                periods = periods + ".";
                ++k;
            }
            this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"gui.loading") + periods, this.guiLeft + this.xSize / 2, this.guiTop + 80, CustomNpcResourceListener.DefaultTextColor);
            return;
        }
        if (ClientCacheHandler.party == null) {
            this.drawTextBlock("party.messageNoParty", this.guiLeft + 155, this.guiTop + this.ySize / 2 - 20, 160);
        } else if (ClientCacheHandler.party.getQuest() != null) {
            Quest quest = (Quest)ClientCacheHandler.party.getQuest();
            if (this.showQuestText) {
                this.drawTextBlock(quest.getLogText(), this.guiLeft + 142, this.guiTop + 20, 174);
            } else {
                this.drawProgress();
            }
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(this.guiLeft + 148), (float)this.guiTop, (float)0.0f);
            GL11.glScalef((float)1.24f, (float)1.24f, (float)1.24f);
            this.field_146289_q.func_78276_b(quest.getName(), (130 - this.field_146289_q.func_78256_a(quest.getName())) / 2, 4, CustomNpcResourceListener.DefaultTextColor);
            GL11.glPopMatrix();
            this.func_73730_a(this.guiLeft + 142, this.guiLeft + 312, this.guiTop + 17, -16777216 + CustomNpcResourceListener.DefaultTextColor);
        }
    }

    private void drawProgress() {
        String complete = this.questCompleteWith;
        if (complete != null && !complete.isEmpty()) {
            this.field_146289_q.func_78276_b(StatCollector.func_74837_a((String)"quest.completewith", (Object[])new Object[]{complete}), this.guiLeft + 144, this.guiTop + 105, CustomNpcResourceListener.DefaultTextColor);
        }
        int yoffset = this.guiTop + 22;
        for (String process : this.questLogStatus) {
            int index = process.lastIndexOf(":");
            if (index > 0) {
                String name = process.substring(0, index);
                String trans = StatCollector.func_74838_a((String)name);
                if (!trans.equals(name)) {
                    name = trans;
                }
                if (!(trans = StatCollector.func_74838_a((String)("entity." + name + ".name"))).equals("entity." + name + ".name")) {
                    name = trans;
                }
                process = name + process.substring(index);
            }
            this.field_146289_q.func_78276_b("- " + process, this.guiLeft + 144, yoffset, CustomNpcResourceListener.DefaultTextColor);
            yoffset += 10;
        }
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (!(GuiNpcTextField.isFieldActive() || i != 1 && i != this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i())) {
            this.field_146297_k.func_147108_a(null);
            this.field_146297_k.func_71381_h();
        }
    }

    @Override
    public void setPartyData(NBTTagCompound compound) {
        int i;
        this.receivedData = true;
        if (compound.func_74764_b("TrackedQuestID")) {
            this.data.trackedQuestKey = this.originalTracked = compound.func_74779_i("TrackedQuestID");
        }
        if (compound.func_74764_b("QuestPing")) {
            this.questLogStatus.clear();
            this.questCompleteWith = "";
            this.trackedQuestKey = "";
            if (compound.func_74764_b("QuestProgress")) {
                NBTTagList tagList = compound.func_150295_c("QuestProgress", 8);
                for (i = 0; i < tagList.func_74745_c(); ++i) {
                    this.questLogStatus.add(tagList.func_150307_f(i));
                }
            }
            if (compound.func_74764_b("QuestCompleteWith")) {
                this.questCompleteWith = compound.func_74779_i("QuestCompleteWith");
            }
            if (compound.func_74764_b("QuestName")) {
                this.trackedQuestKey = compound.func_74779_i("QuestName");
            }
        } else {
            ClientCacheHandler.party = null;
        }
        if (compound.func_74764_b("PartyUUID")) {
            UUID uuid = UUID.fromString(compound.func_74779_i("PartyUUID"));
            Party party = ClientCacheHandler.party = new Party(uuid);
            party.readFromNBT(compound);
            this.questLogStatus.clear();
            this.questCompleteWith = "";
            this.trackedQuestKey = "";
            if (compound.func_74764_b("QuestProgress")) {
                NBTTagList tagList = compound.func_150295_c("QuestProgress", 8);
                for (int i2 = 0; i2 < tagList.func_74745_c(); ++i2) {
                    this.questLogStatus.add(tagList.func_150307_f(i2));
                }
            }
            if (compound.func_74764_b("QuestCompleteWith")) {
                this.questCompleteWith = compound.func_74779_i("QuestCompleteWith");
            }
            if (compound.func_74764_b("QuestName")) {
                this.trackedQuestKey = compound.func_74779_i("QuestName");
            }
            this.isLeader = ClientCacheHandler.party.getPartyLeaderName().equals(this.player.func_70005_c_());
        } else if (compound.func_74764_b("Disband")) {
            this.isLeader = false;
        } else if (compound.func_74764_b("PartyInvites")) {
            this.invites.clear();
            NBTTagList list = compound.func_150295_c("PartyInvites", 10);
            for (i = 0; i < list.func_74745_c(); ++i) {
                NBTTagCompound listCompound = list.func_150305_b(i);
                String inviteName = listCompound.func_74779_i("PartyLeader");
                String partyUUID = listCompound.func_74779_i("PartyUUID");
                this.invites.put(inviteName, partyUUID);
            }
        }
        this.func_73866_w_();
    }

    @Override
    public void func_146281_b() {
        super.func_146281_b();
        this.save();
    }

    @Override
    public void save() {
        if (this.partyChanged && ClientCacheHandler.party != null) {
            PacketClient.sendClient(new PartySavePacket(ClientCacheHandler.party.writeClientNBT()));
        }
        if (!Objects.equals(this.originalTracked, this.data.trackedQuestKey)) {
            PacketClient.sendClient(new PartyLogToServerPacket(this.data.trackedQuestKey));
        }
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        switch (guiCustomScroll.id) {
            case 210: {
                this.selectedInvite = this.invites.get(guiCustomScroll.getSelected());
                break;
            }
            case 300: {
                this.selectedPlayer = guiCustomScroll.getSelected().replace(" \u00a7e[" + StatCollector.func_74838_a((String)"party.leader").toUpperCase() + "]", "");
            }
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
    }
}

