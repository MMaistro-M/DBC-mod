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
package noppes.npcs.client.gui.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.AbilityVariant;
import kamkeel.npcs.controllers.data.ability.data.ChainedAbility;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import kamkeel.npcs.controllers.data.ability.gui.GuiAbilityInterface;
import kamkeel.npcs.controllers.data.ability.preview.AbilityPreviewExecutor;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.ability.BuiltInAbilityGetPacket;
import kamkeel.npcs.network.packets.request.ability.ChainedAbilitiesGetPacket;
import kamkeel.npcs.network.packets.request.ability.ChainedAbilityGetPacket;
import kamkeel.npcs.network.packets.request.ability.ChainedAbilityRemovePacket;
import kamkeel.npcs.network.packets.request.ability.ChainedAbilitySavePacket;
import kamkeel.npcs.network.packets.request.ability.CustomAbilitiesGetPacket;
import kamkeel.npcs.network.packets.request.ability.CustomAbilityGetPacket;
import kamkeel.npcs.network.packets.request.ability.CustomAbilityRemovePacket;
import kamkeel.npcs.network.packets.request.ability.CustomAbilitySavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.advanced.SubGuiAbilityTypeSelect;
import noppes.npcs.client.gui.advanced.SubGuiAbilityVariantSelect;
import noppes.npcs.client.gui.advanced.SubGuiChainedAbilityConfig;
import noppes.npcs.client.gui.advanced.SubGuiDuplicateNameConfirm;
import noppes.npcs.client.gui.global.GuiAbilityDirectory;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.GuiTexturedButton;
import noppes.npcs.client.gui.util.IAbilityConfigCallback;
import noppes.npcs.client.gui.util.IChainedAbilityConfigCallback;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.data.AnimationData;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNpcManageAbilities
extends GuiAbilityInterface
implements ICustomScrollListener,
ISubGuiListener,
IAbilityConfigCallback,
IChainedAbilityConfigCallback,
ITextfieldListener,
GuiYesNoCallback,
IScrollData,
IGuiData {
    private static final int BTN_REMOVE = 1;
    private static final int BTN_ADD = 2;
    private static final int BTN_TOGGLE_VIEW = 10;
    private static final int BTN_EDIT = 100;
    private static final int BTN_PREVIEW_PLAY = 91;
    private static final int BTN_PREVIEW_PAUSE = 92;
    private static final int BTN_PREVIEW_STOP = 93;
    private static final int BTN_USER_TYPE_FILTER = 11;
    private static final int TF_SEARCH = 55;
    private static final int LBL_PREVIEW_STATUS = 90;
    private static final int SCROLL_MAIN = 0;
    private static final int CONFIRM_REMOVE_ABILITY = 1;
    private static final int CONFIRM_REMOVE_CHAIN = 2;
    private GuiCustomScroll scroll;
    private HashMap<String, Integer> customData = new HashMap();
    private HashMap<String, Integer> builtInData = new HashMap();
    private String selected = null;
    private String search = "";
    private Ability selectedAbility = null;
    private int viewMode = 0;
    private boolean showingBuiltIn = false;
    private boolean showingChained = false;
    private boolean currentIsBuiltIn = false;
    private int userTypeFilter = 0;
    private static final String[] USER_TYPE_FILTER_LABELS = new String[]{"gui.both", "gui.npcs", "gui.players"};
    private HashMap<String, Integer> chainedData = new HashMap();
    private ChainedAbility selectedChain = null;
    private ChainedAbility pendingSaveChain = null;
    private Ability pendingSaveAbility = null;
    private String pendingTypeId = null;
    private AbilityPreviewExecutor previewExecutor;
    private long prevTick = 0L;

    public GuiNpcManageAbilities(EntityNPCInterface npc, boolean hasMenuNpc) {
        super(npc, hasMenuNpc);
        this.xOffset = -38;
        this.yOffset = -3;
        this.previewExecutor = new AbilityPreviewExecutor();
        this.previewExecutor.setParentGui(this);
        PacketClient.sendClient(new CustomAbilitiesGetPacket());
        AnimationData data = npc.display.animationData;
        data.setEnabled(true);
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        GuiNpcButton fullBtn = new GuiNpcButton(66, this.guiLeft + 368, this.guiTop + 8, 45, 20, "gui.fullscreen");
        fullBtn.setTextColor(0x55FF55);
        fullBtn.setHoverText("gui.fullscreen.tooltip");
        this.addButton(fullBtn);
        String toggleLabel = this.showingChained ? "gui.chained" : (this.showingBuiltIn ? "gui.builtin" : "gui.custom");
        GuiNpcButton toggleBtn = new GuiNpcButton(10, this.guiLeft + 368, this.guiTop + 36, 45, 20, toggleLabel);
        if (this.showingChained) {
            toggleBtn.setTextColor(0xFFFF55);
        } else if (this.showingBuiltIn) {
            toggleBtn.setTextColor(0x55FFFF);
        } else {
            toggleBtn.setTextColor(0xFFFFFF);
        }
        this.addButton(toggleBtn);
        if (this.showingChained) {
            this.addButton(new GuiNpcButton(2, this.guiLeft + 368, this.guiTop + 60, 45, 20, "gui.add"));
            this.addButton(new GuiNpcButton(1, this.guiLeft + 368, this.guiTop + 84, 45, 20, "gui.remove"));
            this.getButton(1).setEnabled(this.selected != null && !this.selected.isEmpty() && this.chainedData.containsKey(this.selected));
            this.addButton(new GuiNpcButton(100, this.guiLeft + 368, this.guiTop + 108, 45, 20, "gui.edit"));
            this.getButton(100).setEnabled(this.selected != null && !this.selected.isEmpty() && this.selectedChain != null);
        } else if (!this.showingBuiltIn) {
            this.addButton(new GuiNpcButton(2, this.guiLeft + 368, this.guiTop + 60, 45, 20, "gui.add"));
            this.addButton(new GuiNpcButton(1, this.guiLeft + 368, this.guiTop + 84, 45, 20, "gui.remove"));
            this.getButton(1).setEnabled(this.selected != null && !this.selected.isEmpty() && this.customData.containsKey(this.selected));
            if (!this.currentIsBuiltIn) {
                this.addButton(new GuiNpcButton(100, this.guiLeft + 368, this.guiTop + 108, 45, 20, "gui.edit"));
                this.getButton(100).setEnabled(this.selected != null && !this.selected.isEmpty() && this.selectedAbility != null);
            }
        }
        if (this.scroll == null) {
            this.scroll = new GuiCustomScroll(this, 0);
            this.scroll.setSize(143, 185);
        }
        this.scroll.guiLeft = this.guiLeft + 220;
        this.scroll.guiTop = this.guiTop + 4;
        this.addScroll(this.scroll);
        this.scroll.setList(this.getSearchList());
        if (this.selected != null) {
            this.scroll.setSelected(this.selected);
        }
        this.addTextField(new GuiNpcTextField(55, this, this.field_146289_q, this.guiLeft + 220, this.guiTop + 192, 143, 20, this.search));
        if (!this.showingChained) {
            this.addButton(new GuiNpcButton(11, this.guiLeft + 368, this.guiTop + 192, 45, 20, USER_TYPE_FILTER_LABELS[this.userTypeFilter]));
        }
        if (this.showingChained ? this.selectedChain == null || this.selected == null || this.selectedChain.getEntries().isEmpty() : this.selectedAbility == null || this.selected == null) {
            return;
        }
        String animTexture = "customnpcs:textures/gui/animation.png";
        int playButtonOffsetX = 60;
        boolean isPlaying = this.previewExecutor.isPlaying() && !this.previewExecutor.isPaused();
        boolean isPaused = this.previewExecutor.isPaused();
        boolean isActive = this.previewExecutor.isActive();
        if (!isPlaying || isPaused) {
            String statusKey = isPaused ? "animation.paused" : "animation.stopped";
            this.addLabel(new GuiNpcLabel(90, statusKey, this.guiLeft + playButtonOffsetX, this.guiTop + 198));
            this.addButton(new GuiTexturedButton(91, "", this.guiLeft + playButtonOffsetX + 70, this.guiTop + 192, 11, 20, animTexture, 18, 71));
        } else {
            this.addLabel(new GuiNpcLabel(90, "animation.playing", this.guiLeft + playButtonOffsetX, this.guiTop + 198));
            this.addButton(new GuiTexturedButton(92, "", this.guiLeft + playButtonOffsetX + 70, this.guiTop + 192, 14, 20, animTexture, 0, 71));
        }
        if (isActive) {
            this.addButton(new GuiTexturedButton(93, "", this.guiLeft + playButtonOffsetX + 90, this.guiTop + 192, 14, 20, animTexture, 33, 71));
        }
    }

    private HashMap<String, Integer> getCurrentData() {
        if (this.showingChained) {
            return this.chainedData;
        }
        return this.showingBuiltIn ? this.builtInData : this.customData;
    }

    private List<String> getSearchList() {
        HashMap<String, Integer> data = this.getCurrentData();
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            String name = entry.getKey();
            if (!this.search.isEmpty() && !name.toLowerCase().contains(this.search.toLowerCase())) continue;
            if (!this.showingChained && this.userTypeFilter != 0) {
                UserType ut = UserType.fromOrdinal(entry.getValue());
                if (this.userTypeFilter == 1 && !ut.allowsNpc() || this.userTypeFilter == 2 && !ut.allowsPlayer()) continue;
            }
            list.add(name);
        }
        return list;
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        this.npc.field_70760_ar = this.npc.field_70761_aq = NPC_FACING_YAW;
        this.npc.field_70126_B = this.npc.field_70177_z = NPC_FACING_YAW;
        this.npc.field_70758_at = this.npc.field_70759_as = NPC_FACING_YAW;
        this.tickPreview();
        super.func_73863_a(mouseX, mouseY, partialTicks);
        if (this.previewExecutor.isActive()) {
            String status = this.previewExecutor.getStatusString();
            this.field_146289_q.func_78276_b(status, this.guiLeft + 12, this.guiTop + 175, 0xFFFFFF);
        }
    }

    private void tickPreview() {
        if (this.previewExecutor.isPlaying() && !this.previewExecutor.isPaused()) {
            long time;
            long l = time = this.field_146297_k.field_71441_e != null ? this.field_146297_k.field_71441_e.func_82737_E() : System.currentTimeMillis() / 50L;
            if (time != this.prevTick) {
                this.npc.display.animationData.increaseTime();
                this.previewExecutor.tick();
                this.setPreviewTelegraph(this.previewExecutor.getTelegraph());
                this.prevTick = time;
                if (!this.previewExecutor.isActive()) {
                    this.func_73866_w_();
                }
            }
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        super.func_146284_a(guibutton);
        int id = guibutton.field_146127_k;
        if (id == 66) {
            this.previewExecutor.stop();
            this.field_146297_k.func_147108_a((GuiScreen)new GuiAbilityDirectory(this.npc));
            return;
        }
        if (id == 11) {
            this.userTypeFilter = (this.userTypeFilter + 1) % 3;
            if (this.scroll != null) {
                this.scroll.setList(this.getSearchList());
            }
            this.func_73866_w_();
            return;
        }
        if (id == 10) {
            if (!this.showingBuiltIn && !this.showingChained) {
                this.showingBuiltIn = true;
                this.showingChained = false;
            } else if (this.showingBuiltIn) {
                this.showingBuiltIn = false;
                this.showingChained = true;
            } else {
                this.showingChained = false;
                this.showingBuiltIn = false;
            }
            this.selected = null;
            this.selectedAbility = null;
            this.selectedChain = null;
            this.currentIsBuiltIn = false;
            this.search = "";
            this.previewExecutor.stop();
            if (this.scroll != null) {
                this.scroll.clear();
            }
            if (this.showingChained) {
                PacketClient.sendClient(new ChainedAbilitiesGetPacket());
            } else {
                PacketClient.sendClient(new CustomAbilitiesGetPacket());
            }
            this.func_73866_w_();
            return;
        }
        if (id == 91) {
            if (this.previewExecutor.isPaused()) {
                this.previewExecutor.play();
            } else if (this.showingChained && this.selectedChain != null) {
                this.previewExecutor.startChainPreview(this.selectedChain, this.npc);
            } else if (this.selectedAbility != null) {
                this.previewExecutor.startPreview(this.selectedAbility, this.npc);
            }
            this.func_73866_w_();
            return;
        }
        if (id == 92) {
            this.previewExecutor.pause();
            this.func_73866_w_();
            return;
        }
        if (id == 93) {
            this.previewExecutor.stop();
            this.func_73866_w_();
            return;
        }
        if (this.showingChained) {
            if (id == 2) {
                ChainedAbility newChain = new ChainedAbility("New Chain");
                this.setSubGui(new SubGuiChainedAbilityConfig(newChain, this));
            } else if (id == 1 && this.selected != null) {
                GuiYesNo guiyesno = new GuiYesNo((GuiYesNoCallback)this, this.selected, StatCollector.func_74838_a((String)"gui.delete"), 2);
                this.displayGuiScreen((GuiScreen)guiyesno);
            } else if (id == 100 && this.selectedChain != null) {
                this.previewExecutor.stop();
                this.setSubGui(new SubGuiChainedAbilityConfig(this.selectedChain, this));
            }
            return;
        }
        if (id == 2 && !this.showingBuiltIn) {
            this.setSubGui(new SubGuiAbilityTypeSelect());
        } else if (id == 1 && !this.showingBuiltIn && this.selected != null) {
            GuiYesNo guiyesno = new GuiYesNo((GuiYesNoCallback)this, this.selected, StatCollector.func_74838_a((String)"gui.delete"), 1);
            this.displayGuiScreen((GuiScreen)guiyesno);
        } else if (id == 100 && !this.showingBuiltIn && !this.currentIsBuiltIn && this.selectedAbility != null) {
            this.previewExecutor.stop();
            this.setSubGui(this.selectedAbility.createConfigGui(this));
        }
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(55) != null && this.getTextField(55).func_146206_l() && !this.search.equals(this.getTextField(55).func_146179_b())) {
            this.search = this.getTextField(55).func_146179_b();
            this.scroll.resetScroll();
            this.scroll.setList(this.getSearchList());
        }
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll scroll) {
        String newSelection;
        if (scroll.id == 0 && (newSelection = scroll.getSelected()) != null && !newSelection.equals(this.selected)) {
            this.previewExecutor.stop();
            this.selected = newSelection;
            if (this.showingChained) {
                PacketClient.sendClient(new ChainedAbilityGetPacket(this.selected));
            } else if (this.showingBuiltIn) {
                this.currentIsBuiltIn = true;
                PacketClient.sendClient(new BuiltInAbilityGetPacket(this.selected));
            } else {
                this.currentIsBuiltIn = false;
                PacketClient.sendClient(new CustomAbilityGetPacket(this.selected));
            }
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
        if (scroll.id == 0 && selection != null && !selection.isEmpty()) {
            if (this.showingChained && this.selectedChain != null) {
                this.setSubGui(new SubGuiChainedAbilityConfig(this.selectedChain, this));
            } else if (!(this.showingBuiltIn || this.showingChained || this.currentIsBuiltIn || this.selectedAbility == null)) {
                this.previewExecutor.stop();
                this.setSubGui(this.selectedAbility.createConfigGui(this));
            }
        }
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        if (type == EnumScrollData.CUSTOM_ABILITIES) {
            String prevSelected = this.scroll != null ? this.scroll.getSelected() : null;
            this.customData = data;
            if (!this.showingBuiltIn && !this.showingChained && this.scroll != null) {
                this.scroll.setList(this.getSearchList());
                if (prevSelected != null && this.customData.containsKey(prevSelected)) {
                    this.scroll.setSelected(prevSelected);
                }
            }
            this.func_73866_w_();
        } else if (type == EnumScrollData.BUILTIN_ABILITIES) {
            this.builtInData = data;
            if (this.showingBuiltIn && this.scroll != null) {
                this.scroll.setList(this.getSearchList());
                if (this.selected != null && this.builtInData.containsKey(this.selected)) {
                    this.scroll.setSelected(this.selected);
                }
            }
            this.func_73866_w_();
        } else if (type == EnumScrollData.CHAINED_ABILITIES) {
            String prevSelected = this.scroll != null ? this.scroll.getSelected() : null;
            this.chainedData = data;
            if (this.showingChained && this.scroll != null) {
                this.scroll.setList(this.getSearchList());
                if (prevSelected != null && this.chainedData.containsKey(prevSelected)) {
                    this.scroll.setSelected(prevSelected);
                }
            }
            this.func_73866_w_();
        }
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        if (this.showingChained && compound.func_74764_b("Entries")) {
            this.selectedChain = new ChainedAbility();
            this.selectedChain.readNBT(compound);
            this.selected = this.selectedChain.getName();
            if (this.scroll != null) {
                this.scroll.setSelected(this.selected);
            }
            this.func_73866_w_();
            return;
        }
        this.currentIsBuiltIn = compound.func_74764_b("BuiltIn") && compound.func_74767_n("BuiltIn");
        this.selectedAbility = AbilityController.Instance.fromNBT(compound);
        if (this.selectedAbility != null) {
            this.selected = this.selectedAbility.getName();
            if (this.scroll != null) {
                this.scroll.setSelected(this.selected);
            }
        }
        this.previewExecutor.stop();
        this.func_73866_w_();
    }

    @Override
    public void setSelected(String selected) {
        this.selected = selected;
        if (this.scroll != null) {
            this.scroll.setSelected(selected);
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiChainedAbilityConfig) {
            this.handleChainConfigClosed();
            this.func_73866_w_();
            return;
        }
        if (subgui instanceof SubGuiAbilityVariantSelect ? this.handleVariantSelectClosed((SubGuiAbilityVariantSelect)subgui) : (subgui instanceof SubGuiAbilityTypeSelect ? this.handleTypeSelectClosed((SubGuiAbilityTypeSelect)subgui) : (subgui instanceof SubGuiDuplicateNameConfirm ? this.handleDuplicateNameClosed((SubGuiDuplicateNameConfirm)subgui) : this.pendingSaveAbility != null && this.handlePendingSave()))) {
            return;
        }
        this.func_73866_w_();
    }

    private void handleChainConfigClosed() {
        if (this.pendingSaveChain != null) {
            PacketClient.sendClient(new ChainedAbilitySavePacket(this.pendingSaveChain.writeNBT(false)));
            this.selected = this.pendingSaveChain.getName();
            this.pendingSaveChain = null;
        }
    }

    private boolean handleVariantSelectClosed(SubGuiAbilityVariantSelect gui) {
        Ability newAbility;
        int idx = gui.getSelectedIndex();
        if (idx >= 0 && this.pendingTypeId != null && (newAbility = AbilityController.Instance.create(this.pendingTypeId)) != null) {
            gui.getVariants().get(idx).apply(newAbility);
            newAbility.setId(UUID.randomUUID().toString());
            this.pendingTypeId = null;
            this.openConfig(newAbility);
            return true;
        }
        this.pendingTypeId = null;
        return false;
    }

    private boolean handleTypeSelectClosed(SubGuiAbilityTypeSelect gui) {
        String typeId = gui.getSelectedTypeId();
        if (typeId != null) {
            List<AbilityVariant> variants = AbilityController.Instance.getVariantsForType(typeId);
            if (variants.size() > 1) {
                this.pendingTypeId = typeId;
                this.setSubGui(new SubGuiAbilityVariantSelect(variants));
                return true;
            }
            Ability newAbility = AbilityController.Instance.create(typeId);
            if (newAbility != null) {
                if (variants.size() == 1) {
                    variants.get(0).apply(newAbility);
                }
                newAbility.setId(UUID.randomUUID().toString());
                this.openConfig(newAbility);
                return true;
            }
        }
        return false;
    }

    private boolean handleDuplicateNameClosed(SubGuiDuplicateNameConfirm gui) {
        if (this.pendingSaveAbility == null) {
            return false;
        }
        if (gui.isConfirmed()) {
            PacketClient.sendClient(new CustomAbilitySavePacket(this.pendingSaveAbility.writeNBT(false)));
            this.pendingSaveAbility = null;
            return false;
        }
        if (gui.isBack()) {
            Ability ability = this.pendingSaveAbility;
            this.pendingSaveAbility = null;
            this.setSubGui(ability.createConfigGui(this));
            return true;
        }
        this.pendingSaveAbility = null;
        return false;
    }

    private boolean handlePendingSave() {
        if (this.hasDuplicateName(this.pendingSaveAbility)) {
            this.setSubGui(new SubGuiDuplicateNameConfirm());
            return true;
        }
        PacketClient.sendClient(new CustomAbilitySavePacket(this.pendingSaveAbility.writeNBT(false)));
        this.pendingSaveAbility = null;
        return false;
    }

    private void openConfig(Ability ability) {
        if (ability.isBuiltIn()) {
            return;
        }
        this.selectedAbility = ability;
        this.selected = ability.getName();
        this.previewExecutor.stop();
        this.setSubGui(ability.createConfigGui(this));
    }

    private boolean hasDuplicateName(Ability ability) {
        String name = ability.getName();
        if (name == null || name.isEmpty()) {
            return false;
        }
        if (!this.customData.containsKey(name)) {
            return false;
        }
        String uuid = ability.getId();
        if (uuid == null || uuid.isEmpty()) {
            return true;
        }
        Ability existing = AbilityController.Instance.getCustomAbilityByName(name);
        return existing == null || !uuid.equals(existing.getId());
    }

    public void func_73878_a(boolean result, int id) {
        NoppesUtil.openGUI((EntityPlayer)this.player, this);
        if (!result) {
            return;
        }
        if (id == 1 && this.selected != null) {
            PacketClient.sendClient(new CustomAbilityRemovePacket(this.selected));
            this.scroll.clear();
            this.selected = null;
            this.selectedAbility = null;
            this.func_73866_w_();
        } else if (id == 2 && this.selected != null) {
            PacketClient.sendClient(new ChainedAbilityRemovePacket(this.selected));
            this.scroll.clear();
            this.selected = null;
            this.selectedChain = null;
            this.func_73866_w_();
        }
    }

    @Override
    public void onAbilitySaved(Ability ability) {
        this.pendingSaveAbility = ability;
    }

    @Override
    public void onChainedAbilitySaved(ChainedAbility chain) {
        this.pendingSaveChain = chain;
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
    }

    @Override
    public void save() {
    }

    @Override
    public void func_146281_b() {
        super.func_146281_b();
        this.previewExecutor.stop();
    }
}

