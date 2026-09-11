/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiYesNo
 *  net.minecraft.client.gui.GuiYesNoCallback
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.player.CheckPlayerValue;
import kamkeel.npcs.network.packets.request.party.PartySetQuestPacket;
import kamkeel.npcs.network.packets.request.quest.QuestLogToServerPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.NBTTags;
import noppes.npcs.QuestLogData;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.TextBlockClient;
import noppes.npcs.client.gui.player.inventory.GuiCNPCInventory;
import noppes.npcs.client.gui.util.GuiButtonNextPage;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiMenuSideButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IPartyData;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.util.ValueUtil;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import tconstruct.client.tabs.AbstractTab;

public class GuiQuestLog
extends GuiCNPCInventory
implements ICustomScrollListener,
IGuiData,
IPartyData,
GuiYesNoCallback {
    private final ResourceLocation resource = new ResourceLocation("customnpcs", "textures/gui/standardbg.png");
    private EntityPlayer player;
    private GuiCustomScroll scroll;
    private HashMap<Integer, GuiMenuSideButton> sideButtons = new HashMap();
    private QuestLogData data = new QuestLogData();
    private boolean noQuests = false;
    private byte questPages = 1;
    private static long lastClicked = System.currentTimeMillis();
    private boolean isPartySet = false;
    private HashMap<String, String> questAlertsOnOpen;
    private String trackedQuestKeyOnOpen;
    private float sideButtonScroll = 0.0f;
    private float destSideButtonScroll = 0.0f;

    public GuiQuestLog() {
        this.player = this.field_146297_k.field_71439_g;
        this.xSize = 280;
        this.ySize = 180;
        this.drawDefaultBackground = false;
        PacketClient.sendClient(new CheckPlayerValue(CheckPlayerValue.Type.QuestLog));
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.sideButtons.clear();
        this.noQuests = false;
        if (this.data.categories.isEmpty()) {
            this.noQuests = true;
            return;
        }
        ArrayList<String> categories = new ArrayList<String>();
        categories.addAll(this.data.categories.keySet());
        Collections.sort(categories, String.CASE_INSENSITIVE_ORDER);
        int i = 0;
        for (String category : categories) {
            if (this.data.selectedCategory.isEmpty()) {
                this.data.selectedCategory = category;
            }
            this.sideButtons.put(i, new GuiMenuSideButton(i, this.guiLeft - 69, this.guiTop + 2 + i * 21, 70, 22, category));
            ++i;
        }
        this.sideButtons.get((Object)Integer.valueOf((int)categories.indexOf((Object)this.data.selectedCategory))).active = true;
        if (this.scroll == null) {
            this.scroll = new GuiCustomScroll(this, 0);
        }
        this.scroll.setList((List<String>)this.data.categories.get(this.data.selectedCategory));
        this.scroll.setSize(134, 174);
        this.scroll.guiLeft = this.guiLeft + 5;
        this.scroll.guiTop = this.guiTop + 15;
        this.addScroll(this.scroll);
        this.addButton(new GuiButtonNextPage(1, this.guiLeft + 286, this.guiTop + 176, true));
        this.addButton(new GuiButtonNextPage(2, this.guiLeft + 144, this.guiTop + 176, false));
        boolean showParty = false;
        boolean showTrackAlerts = true;
        if (this.data.partyQuests.containsKey(this.data.selectedCategory + ":" + this.data.selectedQuest)) {
            showParty = true;
            if (this.data.partyOptions.containsKey(this.data.selectedCategory + ":" + this.data.selectedQuest) && this.data.partyOptions.get(this.data.selectedCategory + ":" + this.data.selectedQuest).get(0).contains("only")) {
                showTrackAlerts = false;
            }
        }
        String partyQuestName = ClientCacheHandler.party != null ? ClientCacheHandler.party.getCurrentQuestName() : null;
        this.isPartySet = Objects.equals(partyQuestName, this.data.selectedQuest);
        if (showParty) {
            this.addButton(new GuiButtonNextPage(11, this.guiLeft + 286, this.guiTop + 176, true));
            this.addButton(new GuiButtonNextPage(10, this.guiLeft + 144, this.guiTop + 176, false));
            GuiNpcButton partyButton = new GuiNpcButton(3, this.guiLeft + 150, this.guiTop + 151, 50, 20, new String[]{"party.party", "party.partying"}, this.isPartySet ? 1 : 0);
            this.addButton(partyButton);
            boolean bl = partyButton.field_146124_l = ClientCacheHandler.party != null && this.data.hasSelectedQuest() && ClientCacheHandler.party.getPartyLeaderName().equals(this.player.func_70005_c_());
            if (partyButton.field_146124_l && this.isPartySet) {
                partyButton.packedFGColour = 3329330;
            }
            this.getButton((int)11).field_146125_m = this.questPages == 0 && this.data.hasSelectedQuest();
            this.getButton((int)10).field_146125_m = this.questPages == 1 && this.data.hasSelectedQuest();
        }
        GuiNpcButton trackingButton = new GuiNpcButton(4, this.guiLeft + 260, this.guiTop + 151, 50, 20, new String[]{"quest.track", "quest.tracking"}, this.data.trackedQuestKey.equals(this.data.selectedCategory + ":" + this.data.selectedQuest) ? 1 : 0);
        this.addButton(trackingButton);
        GuiNpcButton alertButton = new GuiNpcButton(5, this.guiLeft + 205, this.guiTop + 151, 50, 20, new String[]{"quest.alerts", "quest.noAlerts"}, this.data.getQuestAlerts() != false ? 0 : 1);
        this.addButton(alertButton);
        if (this.getButton(1) != null) {
            boolean bl = this.getButton((int)1).field_146125_m = this.questPages == 1 && this.data.hasSelectedQuest();
        }
        if (this.getButton(2) != null) {
            boolean bl = this.getButton((int)2).field_146125_m = this.questPages == 2 && this.data.hasSelectedQuest();
        }
        if (this.getButton(3) != null) {
            boolean bl = this.getButton((int)3).field_146125_m = !this.data.selectedQuest.isEmpty() && this.getButton((int)1).field_146125_m;
        }
        if (this.getButton(4) != null) {
            this.getButton((int)4).field_146125_m = !this.data.selectedQuest.isEmpty() && this.getButton((int)1).field_146125_m;
            boolean bl = this.getButton((int)4).field_146124_l = showTrackAlerts && !this.isPartySet;
        }
        if (this.getButton(5) != null) {
            this.getButton((int)5).field_146125_m = this.getButton((int)4).field_146125_m;
            boolean bl = this.getButton((int)5).field_146124_l = showTrackAlerts && !this.isPartySet;
        }
        if (trackingButton.field_146124_l && trackingButton.getValue() == 1) {
            trackingButton.packedFGColour = 3329330;
        }
    }

    public void func_73878_a(boolean flag, int i) {
        if (flag) {
            String key;
            if (i == 0 && this.data.partyQuests.containsKey(key = this.data.selectedCategory + ":" + this.data.selectedQuest)) {
                int questID = this.data.partyQuests.get(key);
                PacketClient.sendClient(new PartySetQuestPacket(questID));
            }
            this.func_73866_w_();
        }
        this.displayGuiScreen(this);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton instanceof AbstractTab) {
            return;
        }
        if (guibutton.field_146127_k <= -100) {
            super.func_146284_a(guibutton);
            return;
        }
        if (lastClicked > System.currentTimeMillis() - 5L) {
            return;
        }
        if (guibutton.field_146127_k == 11) {
            this.questPages = 1;
            lastClicked = System.currentTimeMillis();
        } else if (guibutton.field_146127_k == 10) {
            this.questPages = 0;
        } else if (guibutton.field_146127_k == 1) {
            this.questPages = (byte)2;
        } else if (guibutton.field_146127_k == 2) {
            this.questPages = 1;
            lastClicked = System.currentTimeMillis();
        }
        if (guibutton.field_146127_k == 3) {
            if (Objects.equals(ClientCacheHandler.party.getCurrentQuestName(), this.data.selectedQuest)) {
                PacketClient.sendClient(new PartySetQuestPacket(-1));
            } else {
                GuiYesNo yesnoDisband = new GuiYesNo((GuiYesNoCallback)this, "Confirm", StatCollector.func_74838_a((String)"party.setQuestConfirm"), 0);
                this.displayGuiScreen((GuiScreen)yesnoDisband);
            }
        }
        if (guibutton.field_146127_k == 4) {
            this.data.trackedQuestKey = !this.data.trackedQuestKey.equals(this.data.selectedCategory + ":" + this.data.selectedQuest) ? this.data.selectedCategory + ":" + this.data.selectedQuest : "";
        }
        if (guibutton.field_146127_k == 5) {
            this.data.toggleQuestAlerts();
        }
        this.func_73866_w_();
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        String title;
        if (this.scroll != null) {
            this.scroll.visible = !this.noQuests;
        }
        this.func_146276_q_();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(this.resource);
        this.func_73729_b(this.guiLeft, this.guiTop, 0, 0, 252, 195);
        this.func_73729_b(this.guiLeft + 252, this.guiTop, 188, 0, 67, 195);
        super.func_73863_a(i, j, f);
        if (this.noQuests) {
            this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"quest.noquests"), this.guiLeft + 84, this.guiTop + 80, CustomNpcResourceListener.DefaultTextColor);
            return;
        }
        float SMOOTHING_FACTOR = 0.1f;
        int maxScroll = Math.max(0, this.sideButtons.size() - 9) * 22;
        if (this.isMouseInScrollZone(i, j)) {
            this.destSideButtonScroll = ValueUtil.clamp(this.destSideButtonScroll - Math.signum(Mouse.getDWheel()) * 22.0f, (float)(-maxScroll), 0.0f);
        }
        this.sideButtonScroll += (this.destSideButtonScroll - this.sideButtonScroll) * 0.1f;
        for (Map.Entry<Integer, GuiMenuSideButton> entry : this.sideButtons.entrySet()) {
            int buttonNumber = entry.getKey();
            GuiMenuSideButton button = entry.getValue();
            float rawYPosition = (float)(this.guiTop + 2 + buttonNumber * 21) + this.sideButtonScroll;
            int smoothedYPosition = Math.round(rawYPosition);
            if (smoothedYPosition < this.guiTop || smoothedYPosition >= this.guiTop + 176) continue;
            button.field_146129_i = smoothedYPosition;
            button.func_146112_a(this.field_146297_k, i, j);
        }
        this.field_146289_q.func_78276_b(this.data.selectedCategory, this.guiLeft + 5, this.guiTop + 5, CustomNpcResourceListener.DefaultTextColor);
        if (!this.data.hasSelectedQuest()) {
            return;
        }
        if (this.questPages == 1) {
            this.drawProgress();
            title = StatCollector.func_74838_a((String)"gui.text");
            this.field_146289_q.func_78276_b(title, this.guiLeft + 284 - this.field_146289_q.func_78256_a(title), this.guiTop + 179, CustomNpcResourceListener.DefaultTextColor);
            if (this.data.partyQuests.containsKey(this.data.selectedCategory + ":" + this.data.selectedQuest)) {
                title = StatCollector.func_74838_a((String)"party.party");
                this.field_146289_q.func_78276_b(title, this.guiLeft + 170, this.guiTop + 179, CustomNpcResourceListener.DefaultTextColor);
            }
        } else if (this.questPages == 0) {
            this.drawPartyOptions();
            title = StatCollector.func_74838_a((String)"quest.objectives");
            this.field_146289_q.func_78276_b(title, this.guiLeft + 284 - this.field_146289_q.func_78256_a(title), this.guiTop + 179, CustomNpcResourceListener.DefaultTextColor);
        } else {
            this.drawQuestText();
            title = StatCollector.func_74838_a((String)"quest.objectives");
            this.field_146289_q.func_78276_b(title, this.guiLeft + 170, this.guiTop + 179, CustomNpcResourceListener.DefaultTextColor);
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(this.guiLeft + 148), (float)this.guiTop, (float)0.0f);
        GL11.glScalef((float)1.24f, (float)1.24f, (float)1.24f);
        this.field_146289_q.func_78276_b(this.data.selectedQuest, (130 - this.field_146289_q.func_78256_a(this.data.selectedQuest)) / 2, 4, CustomNpcResourceListener.DefaultTextColor);
        GL11.glPopMatrix();
        this.func_73730_a(this.guiLeft + 142, this.guiLeft + 312, this.guiTop + 17, -16777216 + CustomNpcResourceListener.DefaultTextColor);
    }

    private void drawQuestText() {
        TextBlockClient block = new TextBlockClient(this.data.getQuestText(), 174, true, this.player);
        int yoffset = this.guiTop + 5;
        for (int i = 0; i < block.lines.size(); ++i) {
            String text = ((IChatComponent)block.lines.get(i)).func_150254_d();
            this.field_146289_q.func_78276_b(text, this.guiLeft + 142, this.guiTop + 20 + i * this.field_146289_q.field_78288_b, CustomNpcResourceListener.DefaultTextColor);
        }
    }

    private void drawProgress() {
        String complete = this.data.getComplete();
        if (complete != null && !complete.isEmpty()) {
            this.field_146289_q.func_78276_b(StatCollector.func_74837_a((String)"quest.completewith", (Object[])new Object[]{complete}), this.guiLeft + 144, this.guiTop + 105, CustomNpcResourceListener.DefaultTextColor);
        }
        int yoffset = this.guiTop + 22;
        for (String process : this.data.getQuestStatus()) {
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

    private void drawPartyOptions() {
        int yoffset = this.guiTop + 22;
        for (String process : this.data.getPartyOptions()) {
            List<String> parts = Arrays.asList(process.split(":"));
            String drawString = StatCollector.func_74838_a((String)parts.get(0)) + ": " + StatCollector.func_74838_a((String)parts.get(1));
            this.field_146289_q.func_78276_b("- " + drawString, this.guiLeft + 144, yoffset, CustomNpcResourceListener.DefaultTextColor);
            yoffset += 10;
        }
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
    }

    @Override
    public void func_73864_a(int i, int j, int k) {
        super.func_73864_a(i, j, k);
        if (k == 0) {
            if (this.scroll != null) {
                this.scroll.func_73864_a(i, j, k);
            }
            for (GuiMenuSideButton button : new ArrayList<GuiMenuSideButton>(this.sideButtons.values())) {
                if (!button.func_146116_c(this.field_146297_k, i, j)) continue;
                this.sideButtonPressed(button);
            }
        }
    }

    private void sideButtonPressed(GuiMenuSideButton button) {
        if (button.active) {
            return;
        }
        NoppesUtil.clickSound();
        this.data.selectedCategory = button.field_146126_j;
        this.data.selectedQuest = "";
        if (this.scroll != null) {
            this.scroll.selected = -1;
        }
        this.func_73866_w_();
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll scroll) {
        if (!scroll.hasSelected()) {
            return;
        }
        this.data.selectedQuest = scroll.getSelected();
        this.func_73866_w_();
    }

    @Override
    public void func_73869_a(char c, int i) {
        if (i == 1 || i == this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i()) {
            this.field_146297_k.func_147108_a(null);
            this.field_146297_k.func_71381_h();
        }
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        QuestLogData data = new QuestLogData();
        data.readNBT(compound);
        this.data = data;
        this.questAlertsOnOpen = new HashMap<String, String>(data.questAlerts);
        this.trackedQuestKeyOnOpen = data.trackedQuestKey;
        this.func_73866_w_();
    }

    @Override
    public void setPartyData(NBTTagCompound compound) {
        if (compound.func_74764_b("PartyUUID")) {
            UUID uuid = UUID.fromString(compound.func_74779_i("PartyUUID"));
            ClientCacheHandler.party = new Party(uuid);
            ClientCacheHandler.party.readFromNBT(compound);
            this.func_73866_w_();
        }
    }

    @Override
    public void func_146281_b() {
        super.func_146281_b();
        this.save();
    }

    @Override
    public void save() {
        if (!(this.data == null || Objects.equals(this.questAlertsOnOpen, this.data.questAlerts) && Objects.equals(this.trackedQuestKeyOnOpen, this.data.trackedQuestKey))) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.func_74782_a("Alerts", (NBTBase)NBTTags.nbtStringStringMap(this.data.questAlerts));
            PacketClient.sendClient(new QuestLogToServerPacket(compound, this.data.trackedQuestKey));
        }
    }

    public boolean isMouseInScrollZone(int x, int y) {
        int scrollZoneLeft = this.guiLeft - 69;
        int scrollZoneRight = this.guiLeft - 69 + 70;
        int scrollZoneTop = this.guiTop + 2;
        int scrollZoneBottom = this.guiTop + 2 + 168;
        return x >= scrollZoneLeft && x <= scrollZoneRight && y >= scrollZoneTop && y <= scrollZoneBottom;
    }
}

