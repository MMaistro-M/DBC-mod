/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.nbt.NBTTagCompound
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.magic.MagicGetAllPacket;
import kamkeel.npcs.network.packets.request.playerdata.PlayerDataDeleteInfoPacket;
import kamkeel.npcs.network.packets.request.playerdata.PlayerDataGetInfoPacket;
import kamkeel.npcs.network.packets.request.playerdata.PlayerDataSaveInfoPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.gui.player.inventory.GuiCNPCInventory;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiMenuTopButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.GuiToggleButton;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IPlayerDataInfo;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumPlayerData;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.data.MagicData;
import noppes.npcs.controllers.data.MagicEntry;
import org.lwjgl.opengl.GL11;

public class SubGuiPlayerData
extends SubGuiInterface
implements IPlayerDataInfo,
ICustomScrollListener,
ITextfieldListener,
IScrollData {
    private int currentTab = 10;
    private int viewMode = 0;
    protected GuiCustomScroll questCatScroll;
    protected GuiCustomScroll questActiveScroll;
    protected GuiCustomScroll questFinishedScroll;
    private String questCatSearch = "";
    private String questActiveSearch = "";
    private String questFinishedSearch = "";
    private HashMap<String, Integer> questCatData = new HashMap();
    private HashMap<String, Integer> questActiveData = new HashMap();
    private HashMap<String, Integer> questFinishedData = new HashMap();
    private String selectedQuestCategory = "";
    protected GuiCustomScroll dialogCatScroll;
    protected GuiCustomScroll dialogReadScroll;
    protected GuiCustomScroll dialogCompactScroll;
    private String dialogCatSearch = "";
    private String dialogReadSearch = "";
    private String dialogCompactSearch = "";
    private HashMap<String, Integer> dialogCatData = new HashMap();
    private HashMap<String, Integer> dialogReadData = new HashMap();
    private String selectedDialogCategory = "";
    protected GuiCustomScroll transCatScroll;
    protected GuiCustomScroll transLocScroll;
    protected GuiCustomScroll transCompactScroll;
    private String transCatSearch = "";
    private String transLocSearch = "";
    private String transCompactSearch = "";
    private HashMap<String, Integer> transCatData = new HashMap();
    private HashMap<String, Integer> transLocData = new HashMap();
    private String selectedTransCategory = "";
    protected GuiCustomScroll singleScroll;
    private String singleSearch = "";
    private HashMap<String, Integer> bankData = new HashMap();
    private HashMap<String, Integer> factionData = new HashMap();
    private GuiCustomScroll magicAllScroll;
    private GuiCustomScroll magicSelectedScroll;
    private MagicData magicData;
    private HashMap<String, Integer> availableMagicElements = new HashMap();
    private GuiNpcTextField splitField;
    private GuiNpcTextField damageField;
    private int dividerOffset1 = 0;
    private int dividerOffset2 = 0;
    private final int dividerWidth = 5;
    private final int minScrollWidth = 50;
    private boolean isResizing = false;
    private int resizingDivider = 0;
    private int initialDragX = 0;
    private final int leftPadding = 6;
    private final int rightPadding = 6;
    private final int scrollTopOffset = 30;
    private final int verticalGapAboveTF = 3;
    private final int textFieldHeight = 20;
    private final int verticalGapBelowTF = 3;
    private final String playerName;

    public SubGuiPlayerData(String playerName) {
        this.playerName = playerName;
        this.xSize = 420;
        this.ySize = 215;
        this.setBackground("menubg.png");
        this.closeOnEsc = true;
        PacketClient.sendClient(new PlayerDataGetInfoPacket(playerName));
        PacketClient.sendClient(new MagicGetAllPacket());
    }

    private int getPaddedLeft() {
        return this.guiLeft + 6;
    }

    private int getPaddedRight() {
        return this.guiLeft + this.xSize - 6;
    }

    private int getAvailableWidth() {
        return this.getPaddedRight() - this.getPaddedLeft();
    }

    private int getScrollHeight() {
        return this.ySize - 56;
    }

    private int getTextFieldY() {
        return this.guiTop + 30 + this.getScrollHeight() + 3;
    }

    private void setupTopButtons() {
        GuiMenuTopButton playerTab = new GuiMenuTopButton(-10, this.guiLeft + 4, this.guiTop - 10, this.playerName);
        GuiMenuTopButton btnQuest = new GuiMenuTopButton(10, playerTab.field_146128_h + playerTab.func_146117_b(), this.guiTop - 10, "quest.quests");
        GuiMenuTopButton btnDialog = new GuiMenuTopButton(11, btnQuest.field_146128_h + btnQuest.getWidth(), this.guiTop - 10, "dialog.dialogs");
        GuiMenuTopButton btnTransport = new GuiMenuTopButton(12, btnDialog.field_146128_h + btnDialog.getWidth(), this.guiTop - 10, "global.transport");
        GuiMenuTopButton btnBank = new GuiMenuTopButton(13, btnTransport.field_146128_h + btnTransport.getWidth(), this.guiTop - 10, "global.banks");
        GuiMenuTopButton btnFaction = new GuiMenuTopButton(14, btnBank.field_146128_h + btnBank.getWidth(), this.guiTop - 10, "menu.factions");
        GuiMenuTopButton btnMagic = new GuiMenuTopButton(15, btnFaction.field_146128_h + btnFaction.getWidth(), this.guiTop - 10, "menu.magics");
        GuiMenuTopButton close = new GuiMenuTopButton(-5, this.guiLeft + this.xSize - 22, this.guiTop - 10, "X");
        playerTab.active = this.currentTab == -10;
        btnQuest.active = this.currentTab == 10;
        btnDialog.active = this.currentTab == 11;
        btnTransport.active = this.currentTab == 12;
        btnBank.active = this.currentTab == 13;
        btnFaction.active = this.currentTab == 14;
        btnMagic.active = this.currentTab == 15;
        this.addTopButton(playerTab);
        this.addTopButton(btnQuest);
        this.addTopButton(btnDialog);
        this.addTopButton(btnTransport);
        this.addTopButton(btnBank);
        this.addTopButton(btnFaction);
        this.addTopButton(btnMagic);
        this.addTopButton(close);
    }

    private GuiCustomScroll ensureScroll(GuiCustomScroll scroll, int id) {
        if (scroll == null) {
            scroll = new GuiCustomScroll((GuiScreen)this, id, 0);
        }
        return scroll;
    }

    private void updateComponentPosition(int fieldId, int newX, int newWidth) {
        GuiNpcTextField tf = this.getTextField(fieldId);
        if (tf != null) {
            tf.field_146209_f = newX;
            tf.field_146218_h = newWidth;
        }
    }

    private void updateLabelPosition(int labelId, int newX) {
        GuiNpcLabel lbl = this.getLabel(labelId);
        if (lbl != null) {
            lbl.x = newX;
        }
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.setupTopButtons();
        if (this.currentTab == 10 || this.currentTab == 11 || this.currentTab == 12) {
            this.addButton(new GuiToggleButton(20, this.guiLeft + this.xSize - 102, this.guiTop + 10, this.viewMode == 0));
            ((GuiToggleButton)this.getButton(20)).setTextureOff(GuiCNPCInventory.specialIcons).setTextureOffPos(16, 0);
            this.getButton(20).setIconTexture(GuiCNPCInventory.specialIcons).setIconPos(16, 16, 16, 0);
        }
        this.guiTop += 7;
        switch (this.currentTab) {
            case 10: {
                this.initQuestTab();
                break;
            }
            case 11: {
                this.initDialogTab();
                break;
            }
            case 12: {
                this.initTransportTab();
                break;
            }
            case 13: 
            case 14: {
                this.initSingleTab();
                break;
            }
            case 15: {
                this.initMagicTab();
                if (this.magicAllScroll == null) break;
                this.magicAllScroll.setList(new ArrayList<String>(this.availableMagicElements.keySet()));
            }
        }
        if (this.currentTab != 15) {
            this.addButton(new GuiNpcButton(30, this.guiLeft + this.xSize - 60, this.guiTop + 10 - 7, 50, 20, "gui.remove"));
        }
    }

    private void initQuestTab() {
        int paddedLeft = this.getPaddedLeft();
        int paddedRight = this.getPaddedRight();
        int availableWidth = this.getAvailableWidth();
        int scrollHeight = this.getScrollHeight();
        if (this.viewMode == 0) {
            if (!this.isResizing) {
                this.dividerOffset1 = (availableWidth - 10) / 3;
                this.dividerOffset2 = this.dividerOffset1 + 5 + (availableWidth - 10) / 3;
            }
            this.questCatScroll = this.ensureScroll(this.questCatScroll, 0);
            this.questCatScroll.guiLeft = paddedLeft;
            this.questCatScroll.guiTop = this.guiTop + 30;
            this.questCatScroll.setSize(this.dividerOffset1, scrollHeight);
            this.questCatScroll.setList(new ArrayList<String>(this.questCatData.keySet()));
            this.questCatScroll.selected = -1;
            this.questCatSearch = "";
            this.addScroll(this.questCatScroll);
            this.addLabel(new GuiNpcLabel(1000, "Categories", paddedLeft, this.guiTop + 15, CustomNpcResourceListener.DefaultTextColor));
            this.questFinishedScroll = this.ensureScroll(this.questFinishedScroll, 1);
            this.questFinishedScroll.guiLeft = paddedLeft + this.dividerOffset1 + 5;
            this.questFinishedScroll.guiTop = this.guiTop + 30;
            this.questFinishedScroll.setSize(this.dividerOffset2 - this.dividerOffset1 - 5, scrollHeight);
            this.questFinishedScroll.setList(new ArrayList<String>());
            this.questFinishedScroll.selected = -1;
            this.questFinishedSearch = "";
            this.addScroll(this.questFinishedScroll);
            this.addLabel(new GuiNpcLabel(1001, "Finished", paddedLeft + this.dividerOffset1 + 5, this.guiTop + 15, CustomNpcResourceListener.DefaultTextColor));
            this.questActiveScroll = this.ensureScroll(this.questActiveScroll, 2);
            this.questActiveScroll.guiLeft = paddedLeft + this.dividerOffset2 + 5;
            this.questActiveScroll.guiTop = this.guiTop + 30;
            this.questActiveScroll.setSize(paddedRight - (paddedLeft + this.dividerOffset2 + 5), scrollHeight);
            this.questActiveScroll.setList(new ArrayList<String>());
            this.questActiveScroll.selected = -1;
            this.questActiveSearch = "";
            this.addScroll(this.questActiveScroll);
            this.addLabel(new GuiNpcLabel(1002, "Active", paddedLeft + this.dividerOffset2 + 5, this.guiTop + 15, CustomNpcResourceListener.DefaultTextColor));
            this.addTextField(new GuiNpcTextField(55, this, this.field_146289_q, paddedLeft, this.getTextFieldY(), this.dividerOffset1, 20, this.questCatSearch));
            this.addTextField(new GuiNpcTextField(56, this, this.field_146289_q, paddedLeft + this.dividerOffset1 + 5, this.getTextFieldY(), this.dividerOffset2 - this.dividerOffset1 - 5, 20, this.questFinishedSearch));
            this.addTextField(new GuiNpcTextField(57, this, this.field_146289_q, paddedLeft + this.dividerOffset2 + 5, this.getTextFieldY(), paddedRight - (paddedLeft + this.dividerOffset2 + 5), 20, this.questActiveSearch));
        } else {
            if (!this.isResizing) {
                this.dividerOffset1 = (availableWidth - 5) / 2;
            }
            this.questFinishedScroll = this.ensureScroll(this.questFinishedScroll, 1);
            this.questFinishedScroll.guiLeft = paddedLeft;
            this.questFinishedScroll.guiTop = this.guiTop + 30;
            this.questFinishedScroll.setSize(this.dividerOffset1, scrollHeight);
            this.questFinishedScroll.setList(new ArrayList<String>(this.questFinishedData.keySet()));
            this.questFinishedSearch = "";
            this.addScroll(this.questFinishedScroll);
            this.addLabel(new GuiNpcLabel(1003, "Finished", paddedLeft, this.guiTop + 15, CustomNpcResourceListener.DefaultTextColor));
            this.questActiveScroll = this.ensureScroll(this.questActiveScroll, 2);
            this.questActiveScroll.guiLeft = paddedLeft + this.dividerOffset1 + 5;
            this.questActiveScroll.guiTop = this.guiTop + 30;
            this.questActiveScroll.setSize(availableWidth - this.dividerOffset1 - 5, scrollHeight);
            this.questActiveScroll.setList(new ArrayList<String>(this.questActiveData.keySet()));
            this.questActiveSearch = "";
            this.addScroll(this.questActiveScroll);
            this.addLabel(new GuiNpcLabel(1004, "Active", paddedLeft + this.dividerOffset1 + 5, this.guiTop + 15, CustomNpcResourceListener.DefaultTextColor));
            this.addTextField(new GuiNpcTextField(55, this, this.field_146289_q, paddedLeft, this.getTextFieldY(), this.dividerOffset1, 20, this.questFinishedSearch));
            this.addTextField(new GuiNpcTextField(56, this, this.field_146289_q, paddedLeft + this.dividerOffset1 + 5, this.getTextFieldY(), availableWidth - this.dividerOffset1 - 5, 20, this.questActiveSearch));
        }
    }

    private void initDialogTab() {
        int paddedLeft = this.getPaddedLeft();
        int availableWidth = this.getAvailableWidth();
        int scrollHeight = this.getScrollHeight();
        if (this.viewMode == 0) {
            if (!this.isResizing) {
                this.dividerOffset1 = (availableWidth - 5) / 2;
            }
            this.dialogCatScroll = this.ensureScroll(this.dialogCatScroll, 0);
            this.dialogCatScroll.guiLeft = paddedLeft;
            this.dialogCatScroll.guiTop = this.guiTop + 30;
            this.dialogCatScroll.setSize(this.dividerOffset1, scrollHeight);
            this.dialogCatScroll.selected = -1;
            this.dialogCatScroll.setList(new ArrayList<String>(this.dialogCatData.keySet()));
            this.addScroll(this.dialogCatScroll);
            this.addLabel(new GuiNpcLabel(2000, "Categories", paddedLeft, this.guiTop + 15, CustomNpcResourceListener.DefaultTextColor));
            this.dialogReadScroll = this.ensureScroll(this.dialogReadScroll, 1);
            this.dialogReadScroll.guiLeft = paddedLeft + this.dividerOffset1 + 5;
            this.dialogReadScroll.guiTop = this.guiTop + 30;
            this.dialogReadScroll.setSize(availableWidth - this.dividerOffset1 - 5, scrollHeight);
            this.dialogReadScroll.selected = -1;
            this.dialogReadScroll.setList(new ArrayList<String>());
            this.addScroll(this.dialogReadScroll);
            this.addLabel(new GuiNpcLabel(2001, "Read", paddedLeft + this.dividerOffset1 + 5, this.guiTop + 15, CustomNpcResourceListener.DefaultTextColor));
            this.addTextField(new GuiNpcTextField(55, this, this.field_146289_q, paddedLeft, this.getTextFieldY(), this.dividerOffset1, 20, this.dialogCatSearch));
            this.addTextField(new GuiNpcTextField(56, this, this.field_146289_q, paddedLeft + this.dividerOffset1 + 5, this.getTextFieldY(), availableWidth - this.dividerOffset1 - 5, 20, this.dialogReadSearch));
        } else {
            this.dialogCompactScroll = this.ensureScroll(this.dialogCompactScroll, 5);
            this.dialogCompactScroll.guiLeft = paddedLeft;
            this.dialogCompactScroll.guiTop = this.guiTop + 30;
            this.dialogCompactScroll.setSize(availableWidth, this.getScrollHeight());
            this.dialogCompactScroll.setList(new ArrayList<String>(this.dialogReadData.keySet()));
            this.dialogCompactScroll.selected = -1;
            this.addScroll(this.dialogCompactScroll);
            this.addLabel(new GuiNpcLabel(2002, "All", paddedLeft, this.guiTop + 15, CustomNpcResourceListener.DefaultTextColor));
            this.addTextField(new GuiNpcTextField(55, this, this.field_146289_q, paddedLeft, this.getTextFieldY(), availableWidth, 20, this.dialogCompactSearch));
        }
    }

    private void initTransportTab() {
        int paddedLeft = this.getPaddedLeft();
        int availableWidth = this.getAvailableWidth();
        int scrollHeight = this.getScrollHeight();
        if (this.viewMode == 0) {
            if (!this.isResizing) {
                this.dividerOffset1 = (availableWidth - 5) / 2;
            }
            this.transCatScroll = this.ensureScroll(this.transCatScroll, 3);
            this.transCatScroll.guiLeft = paddedLeft;
            this.transCatScroll.guiTop = this.guiTop + 30;
            this.transCatScroll.setSize(this.dividerOffset1, scrollHeight);
            this.transCatScroll.selected = -1;
            this.transCatScroll.setList(new ArrayList<String>(this.transCatData.keySet()));
            this.addScroll(this.transCatScroll);
            this.addLabel(new GuiNpcLabel(3000, "Categories", paddedLeft, this.guiTop + 15, CustomNpcResourceListener.DefaultTextColor));
            this.transLocScroll = this.ensureScroll(this.transLocScroll, 4);
            this.transLocScroll.guiLeft = paddedLeft + this.dividerOffset1 + 5;
            this.transLocScroll.guiTop = this.guiTop + 30;
            this.transLocScroll.setSize(availableWidth - this.dividerOffset1 - 5, scrollHeight);
            this.transLocScroll.selected = -1;
            this.transLocScroll.setList(new ArrayList<String>());
            this.addScroll(this.transLocScroll);
            this.addLabel(new GuiNpcLabel(3001, "Locations", paddedLeft + this.dividerOffset1 + 5, this.guiTop + 15, CustomNpcResourceListener.DefaultTextColor));
            this.addTextField(new GuiNpcTextField(60, this, this.field_146289_q, paddedLeft, this.getTextFieldY(), this.dividerOffset1, 20, this.transCatSearch));
            this.addTextField(new GuiNpcTextField(61, this, this.field_146289_q, paddedLeft + this.dividerOffset1 + 5, this.getTextFieldY(), availableWidth - this.dividerOffset1 - 5, 20, this.transLocSearch));
        } else {
            this.transCompactScroll = this.ensureScroll(this.transCompactScroll, 5);
            this.transCompactScroll.guiLeft = paddedLeft;
            this.transCompactScroll.guiTop = this.guiTop + 30;
            this.transCompactScroll.selected = -1;
            this.transCompactScroll.setSize(availableWidth, this.getScrollHeight());
            this.transCompactScroll.setList(new ArrayList<String>(this.transLocData.keySet()));
            this.addScroll(this.transCompactScroll);
            this.addLabel(new GuiNpcLabel(3002, "All", paddedLeft, this.guiTop + 15, CustomNpcResourceListener.DefaultTextColor));
            this.addTextField(new GuiNpcTextField(60, this, this.field_146289_q, paddedLeft, this.getTextFieldY(), availableWidth, 20, this.transCompactSearch));
        }
    }

    private void initSingleTab() {
        int paddedLeft = this.getPaddedLeft();
        int availableWidth = this.getAvailableWidth();
        int scrollHeight = this.getScrollHeight();
        this.singleScroll = this.ensureScroll(this.singleScroll, 0);
        this.singleScroll.guiLeft = paddedLeft;
        this.singleScroll.guiTop = this.guiTop + 30;
        if (this.currentTab == 13) {
            this.singleScroll.setSize(availableWidth, scrollHeight);
            this.singleScroll.setList(new ArrayList<String>(this.bankData.keySet()));
            this.singleScroll.selected = -1;
            this.addLabel(new GuiNpcLabel(4000, "Bank", paddedLeft, this.guiTop + 15, CustomNpcResourceListener.DefaultTextColor));
        } else {
            this.singleScroll.setSize(availableWidth, scrollHeight);
            this.singleScroll.setList(new ArrayList<String>(this.factionData.keySet()));
            this.singleScroll.selected = -1;
            this.addLabel(new GuiNpcLabel(4000, "Faction", paddedLeft, this.guiTop + 15, CustomNpcResourceListener.DefaultTextColor));
        }
        this.addScroll(this.singleScroll);
        this.addTextField(new GuiNpcTextField(70, this, this.field_146289_q, paddedLeft, this.getTextFieldY(), availableWidth, 20, this.singleSearch));
    }

    private void initMagicTab() {
        String sel;
        int id;
        int paddedLeft = this.getPaddedLeft();
        int availableWidth = this.getAvailableWidth();
        int scrollHeight = this.getScrollHeight();
        int localDividerOffset = (availableWidth - 5) / 2;
        this.magicAllScroll = this.ensureScroll(this.magicAllScroll, 100);
        this.magicAllScroll.guiLeft = paddedLeft;
        this.magicAllScroll.guiTop = this.guiTop + 30;
        this.magicAllScroll.setSize(localDividerOffset - 20, scrollHeight);
        this.magicAllScroll.setList(new ArrayList<String>(this.availableMagicElements.keySet()));
        this.magicAllScroll.selected = -1;
        this.addScroll(this.magicAllScroll);
        this.addLabel(new GuiNpcLabel(5000, "menu.magics", paddedLeft, this.guiTop + 15, CustomNpcResourceListener.DefaultTextColor));
        this.magicSelectedScroll = this.ensureScroll(this.magicSelectedScroll, 101);
        this.magicSelectedScroll.guiLeft = 20 + paddedLeft + localDividerOffset + 5;
        this.magicSelectedScroll.guiTop = this.guiTop + 30;
        this.magicSelectedScroll.setSize(availableWidth - localDividerOffset - 5 - 20, scrollHeight);
        ArrayList<String> selectedList = new ArrayList<String>();
        for (String name : this.availableMagicElements.keySet()) {
            int id2 = this.availableMagicElements.get(name);
            if (!this.magicData.hasMagic(id2)) continue;
            selectedList.add(name);
        }
        this.magicSelectedScroll.setList(selectedList);
        this.magicSelectedScroll.selected = -1;
        this.addScroll(this.magicSelectedScroll);
        this.addLabel(new GuiNpcLabel(5001, "Current Magic", paddedLeft + localDividerOffset + 5 + 20, this.guiTop + 15, CustomNpcResourceListener.DefaultTextColor));
        int arrowWidth = 30;
        int arrowHeight = 20;
        int arrowX = paddedLeft + localDividerOffset + (5 - arrowWidth) / 2;
        int addY = this.guiTop + 30 + scrollHeight / 2 - arrowHeight - 2;
        int removeY = this.guiTop + 30 + scrollHeight / 2 + 2;
        this.addButton(new GuiNpcButton(70, arrowX, addY, arrowWidth, arrowHeight, ">"));
        this.addButton(new GuiNpcButton(71, arrowX, removeY, arrowWidth, arrowHeight, "<"));
        int stdWidth = 80;
        int stdHeight = 20;
        int stdX = paddedLeft;
        int stdY = this.guiTop + this.ySize - stdHeight - 6;
        this.addButton(new GuiNpcButton(72, stdX, stdY + 4, stdWidth, stdHeight, "magic.distribute"));
        this.getButton(72).setHoverText("magic.distInfo");
        int tfY = this.getTextFieldY();
        this.addLabel(new GuiNpcLabel(5002, "magic.split", paddedLeft + localDividerOffset + 5 - 60, tfY + 5, CustomNpcResourceListener.DefaultTextColor));
        this.splitField = new GuiNpcTextField(73, this, this.field_146289_q, paddedLeft + localDividerOffset + 5, tfY, 45, 20, "");
        this.addLabel(new GuiNpcLabel(5003, "magic.bonus", paddedLeft + localDividerOffset + 5 + 80, tfY + 5, CustomNpcResourceListener.DefaultTextColor));
        this.damageField = new GuiNpcTextField(74, this, this.field_146289_q, paddedLeft + localDividerOffset + 5 + 200 - 45, tfY, 45, 20, "");
        this.splitField.setFloatsOnly();
        this.damageField.setFloatsOnly();
        this.splitField.setMinMaxDefaultFloat(-100000.0f, 1.0E9f, 0.0f);
        this.damageField.setMinMaxDefaultFloat(0.0f, 1.0E9f, 0.0f);
        this.splitField.enabled = false;
        this.damageField.enabled = false;
        this.addTextField(this.splitField);
        this.addTextField(this.damageField);
        if (this.magicSelectedScroll.hasSelected() && this.magicData.hasMagic(id = this.availableMagicElements.get(sel = this.magicSelectedScroll.getSelected()).intValue())) {
            this.splitField.func_146180_a(this.magicData.getMagicSplit(id) + "");
            this.damageField.func_146180_a(this.magicData.getMagicDamage(id) + "");
            this.splitField.enabled = true;
            this.damageField.enabled = true;
        }
        this.updateMagicSelectedList();
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        super.func_73863_a(mouseX, mouseY, partialTicks);
    }

    @Override
    public void drawBackground() {
        int dividerX;
        super.drawBackground();
        int paddingTopBottom = 25;
        int scrollHeight = this.getScrollHeight();
        int paddedLeft = this.getPaddedLeft();
        int widthPadding = 1;
        GL11.glPushAttrib((int)1048575);
        GL11.glEnable((int)2929);
        if (this.currentTab == 10 && this.viewMode != 0 && !this.hasSubGui()) {
            dividerX = paddedLeft + this.dividerOffset1;
            SubGuiPlayerData.func_73734_a((int)(dividerX + widthPadding), (int)(this.guiTop + 30 + paddingTopBottom), (int)(dividerX + 5 - widthPadding), (int)(this.guiTop + 30 + scrollHeight - paddingTopBottom), (int)-9408400);
        }
        if (this.currentTab == 10 && this.viewMode == 0 && !this.hasSubGui()) {
            int dividerX1 = paddedLeft + this.dividerOffset1;
            int dividerX2 = paddedLeft + this.dividerOffset2;
            SubGuiPlayerData.func_73734_a((int)(dividerX1 + widthPadding), (int)(this.guiTop + 30 + paddingTopBottom), (int)(dividerX1 + 5 - widthPadding), (int)(this.guiTop + 30 + scrollHeight - paddingTopBottom), (int)-9408400);
            SubGuiPlayerData.func_73734_a((int)(dividerX2 + widthPadding), (int)(this.guiTop + 30 + paddingTopBottom), (int)(dividerX2 + 5 - widthPadding), (int)(this.guiTop + 30 + scrollHeight - paddingTopBottom), (int)-9408400);
        } else if (!(this.currentTab != 11 && this.currentTab != 12 || this.viewMode != 0 || this.hasSubGui())) {
            dividerX = paddedLeft + this.dividerOffset1;
            SubGuiPlayerData.func_73734_a((int)(dividerX + widthPadding), (int)(this.guiTop + 30 + paddingTopBottom), (int)(dividerX + 5 - widthPadding), (int)(this.guiTop + 30 + scrollHeight - paddingTopBottom), (int)-9408400);
        }
        GL11.glDisable((int)2929);
        GL11.glPopAttrib();
    }

    @Override
    public void func_73864_a(int mouseX, int mouseY, int mouseButton) {
        int hitMargin = 3;
        int regionTop = this.guiTop + 30;
        int regionBottom = regionTop + this.getScrollHeight();
        int paddedLeft = this.getPaddedLeft();
        int availableWidth = this.getAvailableWidth();
        if (!this.hasSubGui() && mouseY >= regionTop && mouseY <= regionBottom) {
            if (this.viewMode == 0) {
                int dividerX;
                if (this.currentTab == 10) {
                    int dividerX1 = paddedLeft + this.dividerOffset1;
                    int dividerX2 = paddedLeft + this.dividerOffset2;
                    if (mouseX >= dividerX1 - hitMargin && mouseX <= dividerX1 + 5 + hitMargin) {
                        this.isResizing = true;
                        this.resizingDivider = 1;
                        this.initialDragX = mouseX;
                        return;
                    }
                    if (mouseX >= dividerX2 - hitMargin && mouseX <= dividerX2 + 5 + hitMargin) {
                        this.isResizing = true;
                        this.resizingDivider = 2;
                        this.initialDragX = mouseX;
                        return;
                    }
                } else if ((this.currentTab == 11 || this.currentTab == 12) && mouseX >= (dividerX = paddedLeft + this.dividerOffset1) - hitMargin && mouseX <= dividerX + 5 + hitMargin) {
                    this.isResizing = true;
                    this.resizingDivider = 1;
                    this.initialDragX = mouseX;
                    return;
                }
            } else if (this.currentTab == 10) {
                int dividerX;
                int defaultWidth = (availableWidth - 5) / 2;
                if (!this.isResizing) {
                    this.dividerOffset1 = defaultWidth;
                }
                if (mouseX >= (dividerX = paddedLeft + this.dividerOffset1) - hitMargin && mouseX <= dividerX + 5 + hitMargin) {
                    this.isResizing = true;
                    this.resizingDivider = 1;
                    this.initialDragX = mouseX;
                    return;
                }
            }
        }
        super.func_73864_a(mouseX, mouseY, mouseButton);
    }

    @Override
    public void func_146273_a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        int paddedLeft = this.getPaddedLeft();
        int paddedRight = this.getPaddedRight();
        int availableWidth = this.getAvailableWidth();
        if (this.isResizing) {
            int dx = mouseX - this.initialDragX;
            this.initialDragX = mouseX;
            if (this.currentTab == 10) {
                if (this.viewMode == 0) {
                    if (this.resizingDivider == 1) {
                        this.dividerOffset1 += dx;
                        int maxOffset = this.dividerOffset2 - 5 - 50;
                        this.dividerOffset1 = this.clamp(this.dividerOffset1, 50, maxOffset);
                        this.questCatScroll.setSize(this.dividerOffset1, this.getScrollHeight());
                        this.questFinishedScroll.guiLeft = paddedLeft + this.dividerOffset1 + 5;
                        this.questFinishedScroll.setSize(this.dividerOffset2 - this.dividerOffset1 - 5, this.getScrollHeight());
                        this.questActiveScroll.guiLeft = paddedLeft + this.dividerOffset2 + 5;
                        this.questActiveScroll.setSize(availableWidth - (this.dividerOffset2 + 5), this.getScrollHeight());
                        this.updateComponentPosition(55, paddedLeft, this.dividerOffset1);
                        this.updateComponentPosition(56, paddedLeft + this.dividerOffset1 + 5, this.dividerOffset2 - this.dividerOffset1 - 5);
                        this.updateComponentPosition(57, paddedLeft + this.dividerOffset2 + 5, availableWidth - (this.dividerOffset2 + 5));
                        this.updateLabelPosition(1000, paddedLeft);
                        this.updateLabelPosition(1001, paddedLeft + this.dividerOffset1 + 5);
                        this.updateLabelPosition(1002, paddedLeft + this.dividerOffset2 + 5);
                    } else if (this.resizingDivider == 2) {
                        this.dividerOffset2 += dx;
                        int minOffset = this.dividerOffset1 + 5 + 50;
                        int maxOffset = availableWidth - 50;
                        this.dividerOffset2 = this.clamp(this.dividerOffset2, minOffset, maxOffset);
                        this.questFinishedScroll.setSize(this.dividerOffset2 - this.dividerOffset1 - 5, this.getScrollHeight());
                        this.questActiveScroll.guiLeft = paddedLeft + this.dividerOffset2 + 5;
                        this.questActiveScroll.setSize(availableWidth - (this.dividerOffset2 + 5), this.getScrollHeight());
                        this.updateComponentPosition(56, paddedLeft + this.dividerOffset1 + 5, this.dividerOffset2 - this.dividerOffset1 - 5);
                        this.updateComponentPosition(57, paddedLeft + this.dividerOffset2 + 5, availableWidth - (this.dividerOffset2 + 5));
                        this.updateLabelPosition(1001, paddedLeft + this.dividerOffset1 + 5);
                        this.updateLabelPosition(1002, paddedLeft + this.dividerOffset2 + 5);
                    }
                } else if (this.resizingDivider == 1) {
                    this.dividerOffset1 += dx;
                    this.dividerOffset1 = this.clamp(this.dividerOffset1, 50, availableWidth - 5 - 50);
                    this.questFinishedScroll.setSize(this.dividerOffset1, this.getScrollHeight());
                    this.questActiveScroll.guiLeft = paddedLeft + this.dividerOffset1 + 5;
                    this.questActiveScroll.setSize(availableWidth - this.dividerOffset1 - 5, this.getScrollHeight());
                    this.updateComponentPosition(55, paddedLeft, this.dividerOffset1);
                    this.updateComponentPosition(56, paddedLeft + this.dividerOffset1 + 5, availableWidth - this.dividerOffset1 - 5);
                    this.updateLabelPosition(1003, paddedLeft);
                    this.updateLabelPosition(1004, paddedLeft + this.dividerOffset1 + 5);
                }
            } else if ((this.currentTab == 11 || this.currentTab == 12) && this.viewMode == 0) {
                this.dividerOffset1 += dx;
                this.dividerOffset1 = this.clamp(this.dividerOffset1, 50, availableWidth - 50);
                if (this.currentTab == 11) {
                    this.dialogCatScroll.setSize(this.dividerOffset1, this.getScrollHeight());
                    this.dialogReadScroll.guiLeft = paddedLeft + this.dividerOffset1 + 5;
                    this.dialogReadScroll.setSize(availableWidth - this.dividerOffset1 - 5, this.getScrollHeight());
                    this.updateComponentPosition(55, paddedLeft, this.dividerOffset1);
                    this.updateComponentPosition(56, paddedLeft + this.dividerOffset1 + 5, availableWidth - this.dividerOffset1 - 5);
                    this.updateLabelPosition(2000, paddedLeft);
                    this.updateLabelPosition(2001, paddedLeft + this.dividerOffset1 + 5);
                } else if (this.currentTab == 12) {
                    this.transCatScroll.setSize(this.dividerOffset1, this.getScrollHeight());
                    this.transLocScroll.guiLeft = paddedLeft + this.dividerOffset1 + 5;
                    this.transLocScroll.setSize(availableWidth - this.dividerOffset1 - 5, this.getScrollHeight());
                    this.updateComponentPosition(60, paddedLeft, this.dividerOffset1);
                    this.updateComponentPosition(61, paddedLeft + this.dividerOffset1 + 5, availableWidth - this.dividerOffset1 - 5);
                    this.updateLabelPosition(3000, paddedLeft);
                    this.updateLabelPosition(3001, paddedLeft + this.dividerOffset1 + 5);
                }
            }
            return;
        }
        super.func_146273_a(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    protected void func_146286_b(int mouseX, int mouseY, int state) {
        if (this.isResizing) {
            this.isResizing = false;
            this.resizingDivider = 0;
            return;
        }
        super.func_146286_b(mouseX, mouseY, state);
    }

    private int clamp(int value, int min, int max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.currentTab == 10) {
            if (this.viewMode == 0) {
                this.updateQuestSearchField(55, 56, 57);
            } else {
                this.updateQuestSearchFieldCompact(55, 56);
            }
        } else if (this.currentTab == 11) {
            if (this.viewMode == 0) {
                this.updateDialogSearchField(55, 56);
            } else {
                this.updateDialogSearchFieldCompact(55);
            }
        } else if (this.currentTab == 12) {
            if (this.viewMode == 0) {
                this.updateTransportSearchField(60, 61);
            } else {
                this.updateTransportSearchFieldCompact(60);
            }
        } else if (this.currentTab == 13 || this.currentTab == 14) {
            this.updateSingleSearchField(70);
        }
    }

    private void updateQuestSearchField(int catFieldId, int finishedFieldId, int activeFieldId) {
        String newText;
        GuiNpcTextField tf = this.getTextField(catFieldId);
        if (tf != null && tf.func_146206_l() && !this.questCatSearch.equals(newText = tf.func_146179_b().toLowerCase())) {
            this.questCatSearch = newText;
            if (this.questCatScroll != null) {
                this.questCatScroll.setList(this.filterList(this.questCatData, this.questCatSearch));
                this.questCatScroll.resetScroll();
            }
        }
        if ((tf = this.getTextField(finishedFieldId)) != null && tf.func_146206_l() && !this.questFinishedSearch.equals(newText = tf.func_146179_b().toLowerCase())) {
            this.questFinishedSearch = newText;
            if (this.questFinishedScroll != null) {
                this.questFinishedScroll.setList(this.selectedQuestCategory.isEmpty() ? new ArrayList<String>() : this.filterAndTrimListByCategory(this.questFinishedData, this.selectedQuestCategory, this.questFinishedSearch));
                this.questFinishedScroll.resetScroll();
            }
        }
        if ((tf = this.getTextField(activeFieldId)) != null && tf.func_146206_l() && !this.questActiveSearch.equals(newText = tf.func_146179_b().toLowerCase())) {
            this.questActiveSearch = newText;
            if (this.questActiveScroll != null) {
                this.questActiveScroll.setList(this.selectedQuestCategory.isEmpty() ? new ArrayList<String>() : this.filterAndTrimListByCategory(this.questActiveData, this.selectedQuestCategory, this.questActiveSearch));
                this.questActiveScroll.resetScroll();
            }
        }
    }

    private void updateQuestSearchFieldCompact(int finishedFieldId, int activeFieldId) {
        String newText;
        GuiNpcTextField tf = this.getTextField(finishedFieldId);
        if (tf != null && tf.func_146206_l() && !this.questFinishedSearch.equals(newText = tf.func_146179_b().toLowerCase())) {
            this.questFinishedSearch = newText;
            if (this.questFinishedScroll != null) {
                this.questFinishedScroll.setList(this.filterList(this.questFinishedData, this.questFinishedSearch));
                this.questFinishedScroll.resetScroll();
            }
        }
        if ((tf = this.getTextField(activeFieldId)) != null && tf.func_146206_l() && !this.questActiveSearch.equals(newText = tf.func_146179_b().toLowerCase())) {
            this.questActiveSearch = newText;
            if (this.questActiveScroll != null) {
                this.questActiveScroll.setList(this.filterList(this.questActiveData, this.questActiveSearch));
                this.questActiveScroll.resetScroll();
            }
        }
    }

    private void updateDialogSearchField(int catFieldId, int readFieldId) {
        String newText;
        GuiNpcTextField tf = this.getTextField(catFieldId);
        if (tf != null && tf.func_146206_l() && !this.dialogCatSearch.equals(newText = tf.func_146179_b().toLowerCase())) {
            this.dialogCatSearch = newText;
            if (this.dialogCatScroll != null) {
                this.dialogCatScroll.setList(this.filterList(this.dialogCatData, this.dialogCatSearch));
                this.dialogCatScroll.resetScroll();
            }
        }
        if ((tf = this.getTextField(readFieldId)) != null && tf.func_146206_l() && !this.dialogReadSearch.equals(newText = tf.func_146179_b().toLowerCase())) {
            this.dialogReadSearch = newText;
            if (this.dialogReadScroll != null) {
                this.dialogReadScroll.setList(this.selectedDialogCategory.isEmpty() ? new ArrayList<String>() : this.filterAndTrimListByCategory(this.dialogReadData, this.selectedDialogCategory, this.dialogReadSearch));
                this.dialogReadScroll.resetScroll();
            }
        }
    }

    private void updateDialogSearchFieldCompact(int fieldId) {
        String newText;
        GuiNpcTextField tf = this.getTextField(fieldId);
        if (tf != null && tf.func_146206_l() && !this.dialogCompactSearch.equals(newText = tf.func_146179_b().toLowerCase())) {
            this.dialogCompactSearch = newText;
            if (this.dialogCompactScroll != null) {
                this.dialogCompactScroll.setList(this.filterList(this.dialogReadData, this.dialogCompactSearch));
                this.dialogCompactScroll.resetScroll();
            }
        }
    }

    private void updateTransportSearchField(int catFieldId, int locFieldId) {
        String newText;
        GuiNpcTextField tf = this.getTextField(catFieldId);
        if (tf != null && tf.func_146206_l() && !this.transCatSearch.equals(newText = tf.func_146179_b().toLowerCase())) {
            this.transCatSearch = newText;
            if (this.transCatScroll != null) {
                this.transCatScroll.setList(this.filterList(this.transCatData, this.transCatSearch));
                this.transCatScroll.resetScroll();
            }
        }
        if ((tf = this.getTextField(locFieldId)) != null && tf.func_146206_l() && !this.transLocSearch.equals(newText = tf.func_146179_b().toLowerCase())) {
            this.transLocSearch = newText;
            if (this.transLocScroll != null) {
                this.transLocScroll.setList(this.selectedTransCategory.isEmpty() ? new ArrayList<String>() : this.filterAndTrimListByCategory(this.transLocData, this.selectedTransCategory, this.transLocSearch));
                this.transLocScroll.resetScroll();
            }
        }
    }

    private void updateTransportSearchFieldCompact(int fieldId) {
        String newText;
        GuiNpcTextField tf = this.getTextField(fieldId);
        if (tf != null && tf.func_146206_l() && !this.transCompactSearch.equals(newText = tf.func_146179_b().toLowerCase())) {
            this.transCompactSearch = newText;
            if (this.transCompactScroll != null) {
                this.transCompactScroll.setList(this.filterList(this.transLocData, this.transCompactSearch));
                this.transCompactScroll.resetScroll();
            }
        }
    }

    private void updateSingleSearchField(int fieldId) {
        String newText;
        GuiNpcTextField tf = this.getTextField(fieldId);
        if (tf != null && tf.func_146206_l() && !this.singleSearch.equals(newText = tf.func_146179_b().toLowerCase())) {
            this.singleSearch = newText;
            if (this.singleScroll != null) {
                this.singleScroll.setList(this.filterList(this.currentTab == 13 ? this.bankData : this.factionData, this.singleSearch));
                this.singleScroll.resetScroll();
            }
        }
    }

    private List<String> filterList(HashMap<String, Integer> data, String search) {
        ArrayList<String> list = new ArrayList<String>();
        for (String key : data.keySet()) {
            if (!key.toLowerCase().contains(search)) continue;
            list.add(key);
        }
        return list;
    }

    private List<String> filterAndTrimListByCategory(HashMap<String, Integer> data, String category, String search) {
        ArrayList<String> list = new ArrayList<String>();
        String prefix = category + ":";
        for (String key : data.keySet()) {
            String trimmed;
            if (!key.startsWith(prefix) || !(trimmed = key.substring(prefix.length()).trim()).toLowerCase().contains(search)) continue;
            list.add(trimmed);
        }
        return list;
    }

    @Override
    public void func_146284_a(GuiButton button) {
        if (button.field_146127_k == -5) {
            this.close();
            return;
        }
        if (button.field_146127_k >= 10 && button.field_146127_k <= 15) {
            this.currentTab = button.field_146127_k;
            this.func_73866_w_();
            return;
        }
        if (button.field_146127_k == 20) {
            this.viewMode = this.viewMode == 0 ? 1 : 0;
            this.func_73866_w_();
            return;
        }
        if (button.field_146127_k == 30) {
            Integer selectedID = null;
            EnumPlayerData tabType = null;
            if (this.currentTab == 10) {
                if (this.viewMode == 0) {
                    if (this.questCatScroll.getSelected() == null) {
                        return;
                    }
                    if (this.questActiveScroll.getSelected() != null) {
                        selectedID = this.removeSelection(this.questActiveScroll, this.questActiveData, this.questCatScroll.getSelected());
                    } else if (this.questFinishedScroll.getSelected() != null) {
                        selectedID = this.removeSelection(this.questFinishedScroll, this.questFinishedData, this.questCatScroll.getSelected());
                    }
                } else if (this.questActiveScroll.getSelected() != null) {
                    selectedID = this.removeSelection(this.questActiveScroll, this.questActiveData, null);
                } else if (this.questFinishedScroll.getSelected() != null) {
                    selectedID = this.removeSelection(this.questFinishedScroll, this.questFinishedData, null);
                }
                tabType = EnumPlayerData.Quest;
            } else if (this.currentTab == 11) {
                if (this.viewMode == 0) {
                    if (this.dialogReadScroll.getSelected() != null && this.dialogCatScroll.getSelected() != null) {
                        selectedID = this.removeSelection(this.dialogReadScroll, this.dialogReadData, this.dialogCatScroll.getSelected());
                    }
                } else if (this.dialogCompactScroll.getSelected() != null) {
                    selectedID = this.removeSelection(this.dialogCompactScroll, this.dialogReadData, null);
                }
                tabType = EnumPlayerData.Dialog;
            } else if (this.currentTab == 12) {
                if (this.viewMode == 0) {
                    if (this.transLocScroll.getSelected() != null && this.transCatScroll.getSelected() != null) {
                        selectedID = this.removeSelection(this.transLocScroll, this.transLocData, this.transCatScroll.getSelected());
                    }
                } else if (this.transCompactScroll.getSelected() != null) {
                    selectedID = this.removeSelection(this.transCompactScroll, this.transLocData, null);
                }
                tabType = EnumPlayerData.Transport;
            } else if (this.currentTab == 13) {
                if (this.singleScroll.getSelected() != null) {
                    selectedID = this.removeSelection(this.singleScroll, this.bankData, null);
                }
                tabType = EnumPlayerData.Bank;
            } else if (this.currentTab == 14) {
                if (this.singleScroll.getSelected() != null) {
                    selectedID = this.removeSelection(this.singleScroll, this.factionData, null);
                }
                tabType = EnumPlayerData.Factions;
            }
            if (selectedID != null) {
                PacketClient.sendClient(new PlayerDataDeleteInfoPacket(this.playerName, tabType, selectedID));
            }
        }
        if (this.currentTab == 15) {
            String name;
            if (button.field_146127_k == 70) {
                if (this.magicAllScroll != null && this.magicAllScroll.getSelected() != null) {
                    name = this.magicAllScroll.getSelected();
                    int id = this.availableMagicElements.get(name);
                    if (!this.magicData.hasMagic(id)) {
                        this.magicData.addMagic(id, 0.0f, 0.0f);
                    }
                    this.updateMagicSelectedList();
                }
                this.saveMagicCompound();
            } else if (button.field_146127_k == 71) {
                if (this.magicSelectedScroll != null && this.magicSelectedScroll.getSelected() != null) {
                    name = this.magicSelectedScroll.getSelected();
                    int id = this.availableMagicElements.get(name);
                    if (this.magicData.hasMagic(id)) {
                        this.magicData.removeMagic(id);
                        PacketClient.sendClient(new PlayerDataDeleteInfoPacket(this.playerName, EnumPlayerData.Magic, id));
                    }
                    this.updateMagicSelectedList();
                    if (this.splitField != null) {
                        this.splitField.func_146180_a("");
                        this.splitField.enabled = false;
                    }
                    if (this.damageField != null) {
                        this.damageField.func_146180_a("");
                        this.damageField.enabled = false;
                    }
                }
            } else if (button.field_146127_k == 72) {
                int count = this.magicData.getMagics().size();
                if (count > 0) {
                    String name2;
                    int id;
                    float stdSplit = 1.0f / (float)count;
                    for (Integer key : this.magicData.getMagics().keySet()) {
                        MagicEntry entry = this.magicData.getMagic(key);
                        if (entry == null) continue;
                        entry.split = stdSplit;
                    }
                    if (this.magicSelectedScroll != null && this.magicSelectedScroll.getSelected() != null && this.splitField != null && this.magicData.hasMagic(id = this.availableMagicElements.get(name2 = this.magicSelectedScroll.getSelected()).intValue())) {
                        this.splitField.func_146180_a(this.magicData.getMagicSplit(id) + "");
                    }
                }
                this.saveMagicCompound();
            }
        }
        super.func_146284_a(button);
    }

    private void saveMagicCompound() {
        NBTTagCompound magicCompound = new NBTTagCompound();
        this.magicData.writeToNBT(magicCompound);
        PacketClient.sendClient(new PlayerDataSaveInfoPacket(this.playerName, EnumPlayerData.Magic, magicCompound));
    }

    private Integer removeSelection(GuiCustomScroll scroll, Map<String, Integer> data, String prefix) {
        if (scroll.getSelected() == null) {
            return null;
        }
        String key = prefix == null || prefix.isEmpty() ? scroll.getSelected() : prefix + ": " + scroll.getSelected();
        Integer id = data.get(key);
        if (id != null) {
            scroll.list.remove(scroll.selected);
            scroll.selected = -1;
            data.remove(key);
        }
        return id;
    }

    @Override
    public void setQuestData(Map<String, Integer> questCategories, Map<String, Integer> questActive, Map<String, Integer> questFinished) {
        this.questCatData = new HashMap<String, Integer>(questCategories);
        this.questActiveData = new HashMap<String, Integer>(questActive);
        this.questFinishedData = new HashMap<String, Integer>(questFinished);
        if (this.questCatScroll != null) {
            this.questCatScroll.setList(new ArrayList<String>(this.questCatData.keySet()));
        }
    }

    @Override
    public void setDialogData(Map<String, Integer> dialogCategories, Map<String, Integer> dialogRead) {
        this.dialogCatData = new HashMap<String, Integer>(dialogCategories);
        this.dialogReadData = new HashMap<String, Integer>(dialogRead);
        if (this.dialogCatScroll != null) {
            this.dialogCatScroll.setList(new ArrayList<String>(this.dialogCatData.keySet()));
        }
    }

    @Override
    public void setTransportData(Map<String, Integer> transportCategories, Map<String, Integer> transportLocations) {
        this.transCatData = new HashMap<String, Integer>(transportCategories);
        this.transLocData = new HashMap<String, Integer>(transportLocations);
        if (this.transCatScroll != null) {
            this.transCatScroll.setList(new ArrayList<String>(this.transCatData.keySet()));
        }
    }

    @Override
    public void setBankData(Map<String, Integer> bankData) {
        this.bankData = new HashMap<String, Integer>(bankData);
        if (this.singleScroll != null && this.currentTab == 13) {
            this.singleScroll.setList(new ArrayList<String>(this.bankData.keySet()));
        }
    }

    @Override
    public void setFactionData(Map<String, Integer> factionData) {
        this.factionData = new HashMap<String, Integer>(factionData);
        if (this.singleScroll != null && this.currentTab == 14) {
            this.singleScroll.setList(new ArrayList<String>(this.factionData.keySet()));
        }
    }

    @Override
    public void setMagicData(MagicData magicData) {
        this.magicData = magicData;
        this.updateMagicSelectedList();
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        String name;
        int id;
        if (guiCustomScroll == this.questCatScroll) {
            this.selectedQuestCategory = this.questCatScroll.getSelected();
            this.questFinishedScroll.resetScroll();
            this.questActiveScroll.resetScroll();
            if (!this.selectedQuestCategory.isEmpty()) {
                this.questFinishedScroll.setList(this.filterAndTrimListByCategory(this.questFinishedData, this.selectedQuestCategory, this.questFinishedSearch));
                this.questActiveScroll.setList(this.filterAndTrimListByCategory(this.questActiveData, this.selectedQuestCategory, this.questActiveSearch));
            } else {
                this.questFinishedScroll.setList(new ArrayList<String>());
                this.questActiveScroll.setList(new ArrayList<String>());
            }
        } else if (guiCustomScroll == this.dialogCatScroll) {
            this.selectedDialogCategory = this.dialogCatScroll.getSelected();
            this.dialogReadScroll.resetScroll();
            if (!this.selectedDialogCategory.isEmpty()) {
                this.dialogReadScroll.setList(this.filterAndTrimListByCategory(this.dialogReadData, this.selectedDialogCategory, this.dialogReadSearch));
            } else {
                this.dialogReadScroll.setList(new ArrayList<String>());
            }
        } else if (guiCustomScroll == this.transCatScroll) {
            this.selectedTransCategory = this.transCatScroll.getSelected();
            this.transLocScroll.resetScroll();
            if (!this.selectedTransCategory.isEmpty()) {
                this.transLocScroll.setList(this.filterAndTrimListByCategory(this.transLocData, this.selectedTransCategory, this.transLocSearch));
            } else {
                this.transLocScroll.setList(new ArrayList<String>());
            }
        } else if (guiCustomScroll == this.questActiveScroll) {
            this.questFinishedScroll.selected = -1;
        } else if (guiCustomScroll == this.questFinishedScroll) {
            this.questActiveScroll.selected = -1;
        }
        if (guiCustomScroll == this.magicSelectedScroll && this.magicSelectedScroll.getSelected() != null && this.magicData.hasMagic(id = this.availableMagicElements.get(name = this.magicSelectedScroll.getSelected()).intValue())) {
            if (this.splitField != null) {
                this.splitField.func_146180_a(this.magicData.getMagicSplit(id) + "");
                this.splitField.enabled = true;
            }
            if (this.damageField != null) {
                this.damageField.func_146180_a(this.magicData.getMagicDamage(id) + "");
                this.damageField.enabled = true;
            }
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
    }

    @Override
    public void unFocused(GuiNpcTextField textField) {
        if (this.currentTab == 15 && this.magicSelectedScroll != null && this.magicSelectedScroll.getSelected() != null) {
            String name = this.magicSelectedScroll.getSelected();
            int id = this.availableMagicElements.get(name);
            if (this.magicData.hasMagic(id)) {
                if (textField.id == 73) {
                    try {
                        float split;
                        this.magicData.getMagic((int)id).split = split = Float.parseFloat(textField.func_146179_b());
                    }
                    catch (NumberFormatException split) {}
                } else if (textField.id == 74) {
                    try {
                        float dmg;
                        this.magicData.getMagic((int)id).damage = dmg = Float.parseFloat(textField.func_146179_b());
                    }
                    catch (NumberFormatException numberFormatException) {
                        // empty catch block
                    }
                }
            }
            this.saveMagicCompound();
        }
    }

    private void updateMagicSelectedList() {
        ArrayList<String> selected = new ArrayList<String>();
        for (String name : this.availableMagicElements.keySet()) {
            int id = this.availableMagicElements.get(name);
            if (!this.magicData.hasMagic(id)) continue;
            selected.add(name);
        }
        if (this.magicSelectedScroll != null) {
            this.magicSelectedScroll.setList(selected);
        }
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        if (type == EnumScrollData.MAGIC) {
            this.availableMagicElements = new HashMap();
            for (String name : list) {
                int id = data.get(name);
                this.availableMagicElements.put(name, id);
            }
        }
    }

    @Override
    public void setSelected(String selected) {
    }
}

