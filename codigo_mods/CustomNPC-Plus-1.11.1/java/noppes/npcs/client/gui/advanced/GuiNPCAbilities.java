/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.client.gui.advanced;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.AbilityAction;
import kamkeel.npcs.controllers.data.ability.AbilityVariant;
import kamkeel.npcs.controllers.data.ability.data.ChainedAbility;
import kamkeel.npcs.controllers.data.ability.data.entry.ChainedAbilityEntry;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.ability.AbilitiesGetAllPacket;
import kamkeel.npcs.network.packets.request.ability.AbilitiesNpcGetPacket;
import kamkeel.npcs.network.packets.request.ability.AbilitiesNpcSavePacket;
import kamkeel.npcs.network.packets.request.ability.CopyAbilityScriptsPacket;
import kamkeel.npcs.network.packets.request.ability.CustomAbilitiesGetPacket;
import kamkeel.npcs.network.packets.request.ability.CustomAbilitySavePacket;
import kamkeel.npcs.util.Register;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.client.gui.advanced.SubGuiAbilityConfig;
import noppes.npcs.client.gui.advanced.SubGuiAbilityEditMode;
import noppes.npcs.client.gui.advanced.SubGuiAbilityLoad;
import noppes.npcs.client.gui.advanced.SubGuiAbilitySaveConfirm;
import noppes.npcs.client.gui.advanced.SubGuiAbilityVariantSelect;
import noppes.npcs.client.gui.advanced.SubGuiChainSelect;
import noppes.npcs.client.gui.advanced.SubGuiChainedAbilityConfig;
import noppes.npcs.client.gui.advanced.SubGuiLoadTypeChoice;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.IAbilityConfigCallback;
import noppes.npcs.client.gui.util.IChainedAbilityConfigCallback;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNPCAbilities
extends GuiNPCInterface2
implements IScrollData,
ICustomScrollListener,
IGuiData,
ITextfieldListener,
ISubGuiListener,
IAbilityConfigCallback,
IChainedAbilityConfigCallback {
    private static final int BTN_SCROLL_TYPE = 50;
    private static final int BTN_ADD_ABILITY = 70;
    private static final int BTN_REMOVE = 71;
    private static final int BTN_EDIT = 72;
    private static final int BTN_MOVE_UP = 73;
    private static final int BTN_MOVE_DOWN = 74;
    private static final int BTN_LOAD = 75;
    private static final int BTN_SAVE_PRESET = 76;
    private static final int BTN_TOGGLE_SLOT = 77;
    private static final int BTN_ADD_CHAIN = 78;
    private static final int BTN_ENABLED = 100;
    private static final int SCROLL_TYPES = 0;
    private static final int SCROLL_NPC = 1;
    private static final int TF_MIN_COOLDOWN = 101;
    private static final int TF_MAX_COOLDOWN = 102;
    private static final int TF_SEARCH = 4;
    private static final int LBL_TYPES = 1;
    private static final int LBL_NPC = 2;
    private static final int LBL_MIN_COOLDOWN = 101;
    private static final int LBL_MAX_COOLDOWN = 102;
    private GuiCustomScroll availableTypesScroll;
    private GuiCustomScroll npcAbilitiesScroll;
    private final HashMap<String, Integer> allAbilityTypes = new HashMap();
    private final HashMap<String, Integer> filteredAbilityTypes = new HashMap();
    private final HashMap<String, String> displayNameToTypeId = new HashMap();
    private final List<AbilityAction> npcSlots = new ArrayList<AbilityAction>();
    private final List<int[]> rowMapping = new ArrayList<int[]>();
    private boolean abilitiesEnabled = false;
    private int minCooldown = 20;
    private int maxCooldown = 60;
    private String search = "";
    private int selectedAbilityIndex = -1;
    private int selectedSlotIndex = -1;
    private int selectedEntryIndex = -1;
    private ChainedAbility pendingChain = null;
    private int pendingChainSlotIdx = -1;
    private boolean editingChainEntry = false;
    private int editChainSlotIdx = -1;
    private int editChainEntryIdx = -1;
    private boolean editingChainEntryParent = false;
    private int pendingSaveSlotIdx = -1;
    private int pendingSaveEntryIdx = -1;
    private final Set<String> existingPresetNames = new HashSet<String>();
    private String pendingTypeId = null;
    public static int modIndex = 0;
    public static ScrollType scrollType = ScrollType.CNPC;

    public GuiNPCAbilities(EntityNPCInterface npc) {
        super(npc);
        PacketClient.sendClient(new AbilitiesGetAllPacket());
        PacketClient.sendClient(new AbilitiesNpcGetPacket());
        PacketClient.sendClient(new CustomAbilitiesGetPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 10;
        this.addButton(new GuiNpcButton(50, this.guiLeft + 5, y, 140, 20, scrollType.toString()));
        this.addLabel(new GuiNpcLabel(101, "ability.minCooldown", this.guiLeft + 210, y + 5));
        GuiNpcTextField minField = new GuiNpcTextField(101, this, this.field_146289_q, this.guiLeft + 260, y, 40, 20, "" + this.minCooldown);
        minField.setIntegersOnly();
        minField.setMinMaxDefault(0, 10000, 20);
        this.addTextField(minField);
        this.addLabel(new GuiNpcLabel(102, "ability.maxCooldown", this.guiLeft + 320, y + 5));
        GuiNpcTextField maxField = new GuiNpcTextField(102, this, this.field_146289_q, this.guiLeft + 370, y, 40, 20, "" + this.maxCooldown);
        maxField.setIntegersOnly();
        maxField.setMinMaxDefault(0, 10000, 60);
        this.addTextField(maxField);
        this.addButton(new GuiNpcButton(100, this.guiLeft + 334, (y += 28) + 145, 76, 20, new String[]{"gui.disabled", "gui.enabled"}, this.abilitiesEnabled ? 1 : 0));
        this.getButton((int)100).packedFGColour = this.abilitiesEnabled ? 65280 : 0xFF0000;
        this.addLabel(new GuiNpcLabel(1, "ability.availableTypes", this.guiLeft + 5, y));
        if (this.availableTypesScroll == null) {
            this.availableTypesScroll = new GuiCustomScroll(this, 0);
            this.availableTypesScroll.setSize(140, 130);
        }
        this.availableTypesScroll.guiLeft = this.guiLeft + 5;
        this.availableTypesScroll.guiTop = y + 12;
        this.availableTypesScroll.setUnsortedList(this.getFilteredTypeList());
        this.addScroll(this.availableTypesScroll);
        this.addTextField(new GuiNpcTextField(4, this, this.field_146289_q, this.guiLeft + 5, y + 145, 140, 18, this.search));
        this.addLabel(new GuiNpcLabel(2, "ability.npcAbilities", this.guiLeft + 210, y));
        if (this.npcAbilitiesScroll == null) {
            this.npcAbilitiesScroll = new GuiCustomScroll(this, 1);
            this.npcAbilitiesScroll.setSize(200, 130);
        } else {
            this.npcAbilitiesScroll.setSize(200, 130);
        }
        this.npcAbilitiesScroll.guiLeft = this.guiLeft + 210;
        this.npcAbilitiesScroll.guiTop = y + 12;
        this.updateNpcAbilitiesList();
        this.addScroll(this.npcAbilitiesScroll);
        int centerX = this.guiLeft + 158;
        this.addButton(new GuiNpcButton(70, centerX, y + 15, 40, 20, ">>>"));
        this.addButton(new GuiNpcButton(71, centerX, y + 37, 40, 20, "<<<"));
        this.getButton(71).setEnabled(this.selectedAbilityIndex >= 0);
        this.addButton(new GuiNpcButton(75, centerX, y + 60, 40, 20, "gui.load"));
        if (this.selectedSlotIndex >= 0 && this.selectedSlotIndex < this.npcSlots.size() && this.selectedEntryIndex == -1) {
            AbilityAction selectedSlot = this.npcSlots.get(this.selectedSlotIndex);
            boolean isEnabled = selectedSlot.isSlotEnabled();
            GuiNpcButton toggleBtn = new GuiNpcButton(77, centerX, y + 112, 40, 20, new String[]{"gui.off", "gui.on"}, isEnabled ? 1 : 0);
            this.addButton(toggleBtn);
        }
        boolean selectedIsBuiltIn = false;
        boolean selectedIsChainEntry = this.selectedEntryIndex >= 0;
        boolean selectedIsRefChainEntry = false;
        if (this.selectedSlotIndex >= 0 && this.selectedSlotIndex < this.npcSlots.size()) {
            Ability a;
            AbilityAction sel = this.npcSlots.get(this.selectedSlotIndex);
            if (!selectedIsChainEntry && !sel.isChain() && (a = sel.getAbility()) != null && a.isBuiltIn()) {
                selectedIsBuiltIn = true;
            }
            if (selectedIsChainEntry && sel.isChain()) {
                Ability resolved;
                ChainedAbility biChain;
                ChainedAbility chainedAbility = biChain = sel.isInlineChain() ? sel.getInlineChain() : sel.getChainedAbility();
                if (biChain != null && this.selectedEntryIndex < biChain.getEntries().size() && (resolved = biChain.getEntries().get(this.selectedEntryIndex).resolve()) != null && resolved.isBuiltIn()) {
                    selectedIsBuiltIn = true;
                }
            }
            if (selectedIsChainEntry && sel.isChainReference()) {
                selectedIsRefChainEntry = true;
            }
        }
        this.addButton(new GuiNpcButton(72, this.guiLeft + 210, y + 145, 55, 20, "gui.edit"));
        this.getButton(72).setEnabled(this.selectedSlotIndex >= 0 && !selectedIsBuiltIn && !selectedIsRefChainEntry);
        this.addButton(new GuiNpcButton(73, this.guiLeft + 270, y + 145, 20, 20, "<"));
        this.addButton(new GuiNpcButton(74, this.guiLeft + 292, y + 145, 20, 20, ">"));
        this.getButton(73).setEnabled(this.selectedSlotIndex > 0 && this.selectedEntryIndex == -1);
        this.getButton(74).setEnabled(this.selectedSlotIndex >= 0 && this.selectedSlotIndex < this.npcSlots.size() - 1 && this.selectedEntryIndex == -1);
        this.addButton(new GuiNpcButton(76, centerX, y + 82, 40, 20, "gui.save"));
        boolean canSave = false;
        if (this.selectedSlotIndex >= 0 && this.selectedSlotIndex < this.npcSlots.size() && !selectedIsBuiltIn) {
            AbilityAction saveSlot = this.npcSlots.get(this.selectedSlotIndex);
            if (this.selectedEntryIndex >= 0) {
                ChainedAbility saveChain;
                ChainedAbility chainedAbility = saveChain = saveSlot.isInlineChain() ? saveSlot.getInlineChain() : null;
                if (saveChain != null && this.selectedEntryIndex < saveChain.getEntries().size()) {
                    canSave = saveChain.getEntries().get(this.selectedEntryIndex).isInline();
                }
            } else if (!saveSlot.isChain() && !saveSlot.isReference()) {
                canSave = true;
            }
        }
        this.getButton(76).setEnabled(canSave);
        this.addButton(new GuiNpcButton(78, centerX, y + 135, 40, 20, "ability.addChain"));
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 100) {
            this.abilitiesEnabled = ((GuiNpcButton)guibutton).getValue() == 1;
            this.func_73866_w_();
            this.save();
            return;
        }
        if (id == 70) {
            String displayName;
            String typeId;
            if (this.availableTypesScroll.hasSelected() && (typeId = this.displayNameToTypeId.get(displayName = this.availableTypesScroll.getSelected())) != null) {
                List<AbilityVariant> variants = AbilityController.Instance.getVariantsForType(typeId);
                if (variants.size() > 1) {
                    this.pendingTypeId = typeId;
                    this.setSubGui(new SubGuiAbilityVariantSelect(variants));
                    return;
                }
                Ability newAbility = AbilityController.Instance.create(typeId);
                if (newAbility != null) {
                    if (variants.size() == 1) {
                        variants.get(0).apply(newAbility);
                    }
                    newAbility.setId(UUID.randomUUID().toString());
                    this.npcSlots.add(AbilityAction.inline(newAbility));
                    this.selectedAbilityIndex = this.npcSlots.size() - 1;
                    this.updateNpcAbilitiesList();
                    this.selectAbilityByIndex(this.selectedAbilityIndex);
                    this.func_73866_w_();
                    this.save();
                }
            }
            return;
        }
        if (id == 71) {
            if (this.selectedSlotIndex >= 0 && this.selectedSlotIndex < this.npcSlots.size()) {
                if (this.selectedEntryIndex >= 0) {
                    ChainedAbility chain;
                    AbilityAction slot = this.npcSlots.get(this.selectedSlotIndex);
                    ChainedAbility chainedAbility = chain = slot.isInlineChain() ? slot.getInlineChain() : null;
                    if (chain != null && this.selectedEntryIndex < chain.getEntries().size()) {
                        chain.removeEntry(this.selectedEntryIndex);
                    }
                } else {
                    this.npcSlots.remove(this.selectedSlotIndex);
                }
                this.selectedSlotIndex = -1;
                this.selectedEntryIndex = -1;
                this.selectedAbilityIndex = -1;
                if (this.npcAbilitiesScroll != null) {
                    this.npcAbilitiesScroll.resetScroll();
                }
                this.updateNpcAbilitiesList();
                this.func_73866_w_();
                this.save();
            }
            return;
        }
        if (id == 72) {
            if (this.selectedSlotIndex >= 0 && this.selectedSlotIndex < this.npcSlots.size()) {
                AbilityAction slot = this.npcSlots.get(this.selectedSlotIndex);
                if (slot.isChain() && this.selectedEntryIndex == -1) {
                    if (slot.isChainReference()) {
                        this.setSubGui(new SubGuiAbilityEditMode());
                    } else {
                        ChainedAbility chain = slot.getInlineChain();
                        if (chain != null) {
                            this.pendingChain = chain;
                            this.pendingChainSlotIdx = this.selectedSlotIndex;
                            this.setSubGui(new SubGuiChainedAbilityConfig(chain, this, true, this.npcSlots));
                        }
                    }
                } else if (slot.isChain() && this.selectedEntryIndex >= 0) {
                    ChainedAbility chain;
                    ChainedAbility chainedAbility = chain = slot.isInlineChain() ? slot.getInlineChain() : slot.getChainedAbility();
                    if (chain != null && this.selectedEntryIndex < chain.getEntries().size()) {
                        ChainedAbilityEntry entry = chain.getEntries().get(this.selectedEntryIndex);
                        Ability resolvedEntry = entry.resolve();
                        if (resolvedEntry != null && resolvedEntry.isBuiltIn()) {
                            return;
                        }
                        if (entry.isInline() && slot.isInlineChain()) {
                            Ability a = entry.getInlineAbility();
                            if (a != null) {
                                this.editingChainEntry = true;
                                this.editChainSlotIdx = this.selectedSlotIndex;
                                this.editChainEntryIdx = this.selectedEntryIndex;
                                a.setNpcInlineEdit(true);
                                this.setSubGui(a.createConfigGui(this));
                            }
                        } else {
                            this.setSubGui(new SubGuiAbilityEditMode());
                        }
                    }
                } else if (slot.isReference()) {
                    this.setSubGui(new SubGuiAbilityEditMode());
                } else {
                    Ability ability = slot.getAbility();
                    if (ability != null) {
                        ability.setNpcInlineEdit(true);
                        this.setSubGui(ability.createConfigGui(this));
                    }
                }
            }
            return;
        }
        if (id == 73) {
            if (this.selectedSlotIndex > 0 && this.selectedEntryIndex == -1) {
                AbilityAction temp = this.npcSlots.get(this.selectedSlotIndex);
                this.npcSlots.set(this.selectedSlotIndex, this.npcSlots.get(this.selectedSlotIndex - 1));
                this.npcSlots.set(this.selectedSlotIndex - 1, temp);
                --this.selectedSlotIndex;
                this.selectedAbilityIndex = this.selectedSlotIndex;
                this.updateNpcAbilitiesList();
                this.func_73866_w_();
                this.save();
            }
            return;
        }
        if (id == 74) {
            if (this.selectedSlotIndex >= 0 && this.selectedSlotIndex < this.npcSlots.size() - 1 && this.selectedEntryIndex == -1) {
                AbilityAction temp = this.npcSlots.get(this.selectedSlotIndex);
                this.npcSlots.set(this.selectedSlotIndex, this.npcSlots.get(this.selectedSlotIndex + 1));
                this.npcSlots.set(this.selectedSlotIndex + 1, temp);
                ++this.selectedSlotIndex;
                this.selectedAbilityIndex = this.selectedSlotIndex;
                this.updateNpcAbilitiesList();
                this.func_73866_w_();
                this.save();
            }
            return;
        }
        if (id == 75) {
            this.setSubGui(new SubGuiLoadTypeChoice());
            return;
        }
        if (id == 76) {
            Ability abilityToSave = null;
            this.pendingSaveSlotIdx = -1;
            this.pendingSaveEntryIdx = -1;
            if (this.selectedSlotIndex >= 0 && this.selectedSlotIndex < this.npcSlots.size()) {
                AbilityAction saveSlot = this.npcSlots.get(this.selectedSlotIndex);
                if (this.selectedEntryIndex >= 0) {
                    ChainedAbilityEntry entry;
                    ChainedAbility chain;
                    ChainedAbility chainedAbility = chain = saveSlot.isInlineChain() ? saveSlot.getInlineChain() : null;
                    if (chain != null && this.selectedEntryIndex < chain.getEntries().size() && (entry = chain.getEntries().get(this.selectedEntryIndex)).isInline()) {
                        abilityToSave = entry.getInlineAbility();
                        this.pendingSaveSlotIdx = this.selectedSlotIndex;
                        this.pendingSaveEntryIdx = this.selectedEntryIndex;
                    }
                } else if (!saveSlot.isChain() && !saveSlot.isReference()) {
                    abilityToSave = saveSlot.getAbility();
                    this.pendingSaveSlotIdx = this.selectedSlotIndex;
                    this.pendingSaveEntryIdx = -1;
                }
                if (abilityToSave != null && !abilityToSave.isBuiltIn()) {
                    if (abilityToSave.getName() == null || abilityToSave.getName().isEmpty()) {
                        abilityToSave.setNpcInlineEdit(true);
                        this.setSubGui(abilityToSave.createConfigGui(this));
                    } else {
                        this.setSubGui(new SubGuiAbilitySaveConfirm(abilityToSave, null, this.existingPresetNames));
                    }
                }
            }
            return;
        }
        if (id == 77) {
            if (this.selectedSlotIndex >= 0 && this.selectedSlotIndex < this.npcSlots.size()) {
                AbilityAction slot = this.npcSlots.get(this.selectedSlotIndex);
                boolean enabled = ((GuiNpcButton)guibutton).getValue() == 1;
                slot.setEnabled(enabled);
                this.updateNpcAbilitiesList();
                this.func_73866_w_();
                this.save();
            }
            return;
        }
        if (id == 78) {
            ChainedAbility newChain = new ChainedAbility();
            newChain.setId(UUID.randomUUID().toString());
            newChain.setName("NewChain");
            this.pendingChain = newChain;
            this.pendingChainSlotIdx = -1;
            this.setSubGui(new SubGuiChainedAbilityConfig(newChain, this, true, this.npcSlots));
            return;
        }
        if (id == 50) {
            if (scrollType != ScrollType.MODDED) {
                ScrollType[] values = ScrollType.values();
                ScrollType next = values[(scrollType.ordinal() + 1) % values.length];
                if (next == ScrollType.MODDED && Register.isEmpty("ability")) {
                    next = ScrollType.ALL;
                }
                scrollType = next;
            } else {
                List<String> list = Register.REGISTERED_NAMESPACES.get("ability");
                if (list != null && !list.isEmpty()) {
                    if (modIndex == list.size() - 1) {
                        scrollType = ScrollType.ALL;
                    } else {
                        modIndex = (modIndex + 1) % list.size();
                    }
                } else {
                    modIndex = 0;
                    scrollType = ScrollType.ALL;
                }
            }
            HashMap<String, Integer> dummyMap = new HashMap<String, Integer>(this.allAbilityTypes);
            this.filteredAbilityTypes.clear();
            this.filteredAbilityTypes.putAll(this.getFilteredData(dummyMap));
            if (this.availableTypesScroll != null) {
                this.availableTypesScroll.resetScroll();
            }
        }
        this.func_73866_w_();
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        if (type == EnumScrollData.ABILITY_TYPES) {
            this.allAbilityTypes.clear();
            this.allAbilityTypes.putAll(data);
            this.filteredAbilityTypes.putAll(this.getFilteredData(this.allAbilityTypes));
            if (this.availableTypesScroll != null) {
                this.availableTypesScroll.setUnsortedList(this.getFilteredTypeList());
            }
        } else if (type == EnumScrollData.CUSTOM_ABILITIES) {
            this.existingPresetNames.clear();
            for (String key : data.keySet()) {
                int tabIndex = key.indexOf(9);
                if (tabIndex > 0) {
                    this.existingPresetNames.add(key.substring(0, tabIndex));
                    continue;
                }
                this.existingPresetNames.add(key);
            }
        }
        this.func_73866_w_();
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        if (!compound.func_74764_b("AbilityActions") && !compound.func_74764_b("Abilities")) {
            return;
        }
        this.abilitiesEnabled = compound.func_74767_n("AbilitiesEnabled");
        this.minCooldown = compound.func_74762_e("AbilityMinCooldown");
        this.maxCooldown = compound.func_74762_e("AbilityMaxCooldown");
        if (this.minCooldown == 0 && this.maxCooldown == 0) {
            this.minCooldown = 20;
            this.maxCooldown = 60;
        }
        this.npcSlots.clear();
        String tagName = compound.func_74764_b("AbilityActions") ? "AbilityActions" : "Abilities";
        NBTTagList actionList = compound.func_150295_c(tagName, 10);
        for (int i = 0; i < actionList.func_74745_c(); ++i) {
            NBTTagCompound slotNBT = actionList.func_150305_b(i);
            AbilityAction slot = AbilityAction.fromNBT(slotNBT);
            if (slot == null) continue;
            this.npcSlots.add(slot);
        }
        this.func_73866_w_();
    }

    @Override
    public void func_73864_a(int i, int j, int k) {
        super.func_73864_a(i, j, k);
        if (k == 0 && this.availableTypesScroll != null) {
            this.availableTypesScroll.func_73864_a(i, j, k);
        }
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(4) != null && this.getTextField(4).func_146206_l()) {
            if (this.search.equals(this.getTextField(4).func_146179_b())) {
                return;
            }
            this.search = this.getTextField(4).func_146179_b().toLowerCase();
            this.availableTypesScroll.setUnsortedList(this.getFilteredTypeList());
            this.availableTypesScroll.resetScroll();
        }
    }

    private HashMap<String, Integer> getFilteredData(Map<String, Integer> data) {
        HashMap<String, Integer> filteredData = new HashMap<String, Integer>();
        if (scrollType == ScrollType.CNPC) {
            data.entrySet().stream().filter(e -> ((String)e.getKey()).startsWith("ability.cnpc.")).forEach(e -> {
                Integer cfr_ignored_0 = (Integer)filteredData.put((String)e.getKey(), (Integer)e.getValue());
            });
        } else if (scrollType == ScrollType.MODDED) {
            List<String> registerList;
            if (!Register.isEmpty("ability") && !(registerList = Register.REGISTERED_NAMESPACES.get("ability")).isEmpty()) {
                String namespace = registerList.get(modIndex);
                data.entrySet().stream().filter(e -> ((String)e.getKey()).startsWith("ability." + namespace + ".")).forEach(e -> {
                    Integer cfr_ignored_0 = (Integer)filteredData.put((String)e.getKey(), (Integer)e.getValue());
                });
            }
        } else {
            filteredData.putAll(data);
        }
        return filteredData;
    }

    private List<String> getFilteredTypeList() {
        ArrayList<String> list = new ArrayList<String>();
        this.displayNameToTypeId.clear();
        for (String typeId : this.filteredAbilityTypes.keySet()) {
            String displayName = I18n.func_135052_a((String)typeId, (Object[])new Object[0]);
            String stripped = displayName.replaceAll("\u00a7.", "");
            if (!this.search.isEmpty() && !stripped.toLowerCase().contains(this.search) && !typeId.toLowerCase().contains(this.search)) continue;
            if (typeId.equals("ability.cnpc.custom")) {
                displayName = "\u00a7d" + displayName;
            } else if (AbilityController.Instance.isConcurrentCapableType(typeId)) {
                displayName = "\u00a7b" + displayName;
            } else if (AbilityController.Instance.isBuiltInType(typeId)) {
                displayName = "\u00a77" + displayName;
            }
            list.add(displayName);
            this.displayNameToTypeId.put(displayName, typeId);
        }
        Collections.sort(list, (a, b) -> String.CASE_INSENSITIVE_ORDER.compare(a.replaceAll("\u00a7.", ""), b.replaceAll("\u00a7.", "")));
        String customEntry = null;
        for (String name : list) {
            String tid = this.displayNameToTypeId.get(name);
            if (tid == null || !tid.equals("ability.cnpc.custom")) continue;
            customEntry = name;
            break;
        }
        if (customEntry != null) {
            list.remove(customEntry);
            list.add(0, customEntry);
        }
        return list;
    }

    private void updateNpcAbilitiesList() {
        ArrayList<String> list = new ArrayList<String>();
        this.rowMapping.clear();
        for (int i = 0; i < this.npcSlots.size(); ++i) {
            AbilityAction slot = this.npcSlots.get(i);
            if (slot.isChain()) {
                list.add(this.getChainHeaderEntry(i, slot));
                this.rowMapping.add(new int[]{i, -1});
                ChainedAbility chain = slot.getChainedAbility();
                if (chain == null && slot.isInlineChain()) {
                    chain = slot.getInlineChain();
                }
                if (chain == null) continue;
                int e = 0;
                while (e < chain.getEntries().size()) {
                    ChainedAbilityEntry entry = chain.getEntries().get(e);
                    list.add(this.getChainEntryDisplay(entry, e));
                    this.rowMapping.add(new int[]{i, e++});
                }
                continue;
            }
            list.add(this.getAbilityListEntry(i));
            this.rowMapping.add(new int[]{i, -1});
        }
        if (this.npcAbilitiesScroll != null) {
            this.npcAbilitiesScroll.setUnsortedList(list);
        }
    }

    private String getAbilityListEntry(int index) {
        AbilityAction slot = this.npcSlots.get(index);
        Ability ability = slot.getAbility();
        if (ability == null) {
            String refId = slot.getReferenceId();
            String shortId = refId != null && refId.length() > 8 ? refId.substring(0, 8) + "..." : refId;
            return "\u00a7c\u25cf\u00a7r " + (index + 1) + ". \u00a7c> [Missing: " + shortId + "]\u00a7r";
        }
        String typeName = this.getDisplayName(ability.getTypeId());
        String customName = ability.getName();
        String colorPrefix = slot.isSlotEnabled() ? "\u00a7a\u25cf\u00a7r " : "\u00a7c\u25cf\u00a7r ";
        String nameDisplay = customName != null && !customName.isEmpty() && !customName.equals(typeName) ? customName + " (" + typeName + ")" : typeName;
        if (slot.isReference()) {
            return colorPrefix + (index + 1) + ". \u00a7e> " + nameDisplay + "\u00a7r";
        }
        if (ability.isBuiltIn()) {
            return colorPrefix + (index + 1) + ". \u00a77" + nameDisplay + "\u00a7r";
        }
        return colorPrefix + (index + 1) + ". " + nameDisplay;
    }

    private String getChainHeaderEntry(int index, AbilityAction slot) {
        String colorPrefix;
        ChainedAbility chain = slot.isInlineChain() ? slot.getInlineChain() : slot.getChainedAbility();
        String chainName = chain != null ? chain.getDisplayName() : "???";
        String string = colorPrefix = slot.isSlotEnabled() ? "\u00a7a\u25cf\u00a7r " : "\u00a7c\u25cf\u00a7r ";
        if (slot.isChainReference()) {
            return colorPrefix + (index + 1) + ". \u00a7e> [Chain] " + chainName + "\u00a7r";
        }
        return colorPrefix + (index + 1) + ". \u00a7d[Chain] \u00a7r" + chainName;
    }

    private String getChainEntryDisplay(ChainedAbilityEntry entry, int entryIndex) {
        Ability resolved;
        if (entry.isInline()) {
            Ability a = entry.getInlineAbility();
            String name = a != null ? a.getDisplayName() : "???";
            return "      \u00a78--- \u00a7r" + name;
        }
        String name = entry.getAbilityReference();
        if (name == null || name.isEmpty()) {
            name = "???";
        }
        if ((resolved = entry.resolve()) != null) {
            name = resolved.getDisplayName();
        }
        return "      \u00a78--- \u00a7e> " + name + "\u00a7r";
    }

    private void selectAbilityByIndex(int index) {
        if (index >= 0 && index < this.npcSlots.size()) {
            String entry = this.getAbilityListEntry(index);
            if (this.npcAbilitiesScroll != null) {
                this.npcAbilitiesScroll.setSelected(entry);
            }
        }
    }

    private String getDisplayName(String typeId) {
        return I18n.func_135052_a((String)typeId, (Object[])new Object[0]);
    }

    @Override
    public void save() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74757_a("AbilitiesEnabled", this.abilitiesEnabled);
        compound.func_74768_a("AbilityMinCooldown", this.minCooldown);
        compound.func_74768_a("AbilityMaxCooldown", this.maxCooldown);
        NBTTagList actionList = new NBTTagList();
        for (AbilityAction slot : this.npcSlots) {
            actionList.func_74742_a((NBTBase)slot.writeNBT(false));
        }
        compound.func_74782_a("AbilityActions", (NBTBase)actionList);
        PacketClient.sendClient(new AbilitiesNpcSavePacket(compound));
    }

    public void loadAbility(Ability loadedAbility) {
        this.loadAbility(loadedAbility, null);
    }

    public void loadAbility(Ability loadedAbility, String sourceAbilityName) {
        if (loadedAbility != null) {
            loadedAbility.setId(UUID.randomUUID().toString());
            this.npcSlots.add(AbilityAction.inline(loadedAbility));
            this.selectedAbilityIndex = this.npcSlots.size() - 1;
            if (this.npcAbilitiesScroll != null) {
                this.npcAbilitiesScroll.resetScroll();
            }
            this.updateNpcAbilitiesList();
            this.selectAbilityByIndex(this.selectedAbilityIndex);
            this.func_73866_w_();
            this.save();
            if (sourceAbilityName != null && !sourceAbilityName.isEmpty()) {
                PacketClient.sendClient(new CopyAbilityScriptsPacket(sourceAbilityName, loadedAbility.getId()));
            }
        }
    }

    public void loadAbilityReference(String referenceId) {
        if (referenceId != null && !referenceId.isEmpty()) {
            this.npcSlots.add(AbilityAction.abilityReference(referenceId));
            this.selectedAbilityIndex = this.npcSlots.size() - 1;
            if (this.npcAbilitiesScroll != null) {
                this.npcAbilitiesScroll.resetScroll();
            }
            this.updateNpcAbilitiesList();
            this.selectAbilityByIndex(this.selectedAbilityIndex);
            this.func_73866_w_();
            this.save();
        }
    }

    @Override
    public void setSelected(String selected) {
        if (this.availableTypesScroll != null) {
            this.availableTypesScroll.setSelected(selected);
        }
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll == this.npcAbilitiesScroll) {
            int scrollIndex = this.npcAbilitiesScroll.selected;
            if (scrollIndex >= 0 && scrollIndex < this.rowMapping.size()) {
                int[] mapping = this.rowMapping.get(scrollIndex);
                this.selectedSlotIndex = mapping[0];
                this.selectedEntryIndex = mapping[1];
                this.selectedAbilityIndex = this.selectedSlotIndex;
            } else {
                this.selectedSlotIndex = -1;
                this.selectedEntryIndex = -1;
                this.selectedAbilityIndex = -1;
            }
            this.func_73866_w_();
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
        GuiNpcButton editBtn;
        if (scroll == this.npcAbilitiesScroll && this.selectedSlotIndex >= 0 && this.selectedSlotIndex < this.npcSlots.size() && (editBtn = this.getButton(72)) != null && editBtn.field_146124_l) {
            this.buttonEvent(new GuiNpcButton(72, 0, 0, 0, 0, ""));
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textField) {
        if (textField.id == 101) {
            this.minCooldown = textField.getInteger();
            if (this.minCooldown > this.maxCooldown) {
                this.maxCooldown = this.minCooldown;
                this.func_73866_w_();
            }
            this.save();
        } else if (textField.id == 102) {
            this.maxCooldown = textField.getInteger();
            if (this.maxCooldown < this.minCooldown) {
                this.minCooldown = this.maxCooldown;
                this.func_73866_w_();
            }
            this.save();
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiAbilityVariantSelect) {
            this.handleVariantSelectClosed((SubGuiAbilityVariantSelect)subgui);
            this.func_73866_w_();
            return;
        }
        if (subgui instanceof SubGuiChainedAbilityConfig) {
            this.handleChainConfigClosed();
            this.func_73866_w_();
            return;
        }
        if (subgui instanceof SubGuiAbilityEditMode) {
            this.handleEditModeClosed((SubGuiAbilityEditMode)subgui);
            return;
        }
        if (subgui instanceof SubGuiAbilitySaveConfirm) {
            this.handleSaveConfirmClosed((SubGuiAbilitySaveConfirm)subgui);
            this.func_73866_w_();
            return;
        }
        if (subgui instanceof SubGuiLoadTypeChoice) {
            this.handleLoadTypeChoiceClosed((SubGuiLoadTypeChoice)subgui);
            return;
        }
        if (subgui instanceof SubGuiChainSelect) {
            this.handleChainSelectClosed((SubGuiChainSelect)subgui);
            return;
        }
        if (subgui instanceof SubGuiAbilityConfig) {
            this.handleAbilityConfigClosed();
            return;
        }
    }

    private void handleVariantSelectClosed(SubGuiAbilityVariantSelect gui) {
        Ability newAbility;
        int idx = gui.getSelectedIndex();
        if (idx >= 0 && this.pendingTypeId != null && (newAbility = AbilityController.Instance.create(this.pendingTypeId)) != null) {
            gui.getVariants().get(idx).apply(newAbility);
            newAbility.setId(UUID.randomUUID().toString());
            this.npcSlots.add(AbilityAction.inline(newAbility));
            this.selectedSlotIndex = this.selectedAbilityIndex = this.npcSlots.size() - 1;
            this.selectedEntryIndex = -1;
            this.updateNpcAbilitiesList();
            this.save();
        }
        this.pendingTypeId = null;
    }

    private void handleChainConfigClosed() {
        if (this.pendingChain != null) {
            if (this.pendingChainSlotIdx < 0) {
                this.npcSlots.add(AbilityAction.inlineChain(this.pendingChain));
                this.selectedSlotIndex = this.npcSlots.size() - 1;
                this.selectedEntryIndex = -1;
                this.selectedAbilityIndex = this.selectedSlotIndex;
                if (this.npcAbilitiesScroll != null) {
                    this.npcAbilitiesScroll.resetScroll();
                }
            }
            this.pendingChain = null;
            this.pendingChainSlotIdx = -1;
        }
        this.updateNpcAbilitiesList();
        this.save();
    }

    private void handleEditModeClosed(SubGuiAbilityEditMode gui) {
        Ability ability;
        int mode = gui.getResult();
        if (mode < 0) {
            return;
        }
        if (this.selectedSlotIndex < 0 || this.selectedSlotIndex >= this.npcSlots.size()) {
            return;
        }
        AbilityAction slot = this.npcSlots.get(this.selectedSlotIndex);
        if (slot.isChain() && this.selectedEntryIndex == -1) {
            ChainedAbility chain;
            if (mode == 0) {
                String sourceChainName = slot.getReferenceId();
                if (slot.convertToInline()) {
                    ChainedAbility chain2 = slot.getInlineChain();
                    if (chain2 != null) {
                        this.pendingChain = chain2;
                        this.pendingChainSlotIdx = this.selectedSlotIndex;
                        this.setSubGui(new SubGuiChainedAbilityConfig(chain2, this, true, this.npcSlots));
                        if (sourceChainName != null && !sourceChainName.isEmpty()) {
                            PacketClient.sendClient(new CopyAbilityScriptsPacket(1, sourceChainName, chain2.getId()));
                        }
                    }
                    this.updateNpcAbilitiesList();
                    this.save();
                }
            } else if (mode == 1 && (chain = slot.getChainedAbility()) != null) {
                this.pendingChain = chain;
                this.pendingChainSlotIdx = this.selectedSlotIndex;
                this.setSubGui(new SubGuiChainedAbilityConfig(chain, this, true, this.npcSlots));
            }
        } else if (slot.isChain() && this.selectedEntryIndex >= 0) {
            ChainedAbility chain;
            Ability entryResolved;
            ChainedAbility resolveChain;
            ChainedAbility chainedAbility = resolveChain = slot.isInlineChain() ? slot.getInlineChain() : slot.getChainedAbility();
            if (resolveChain != null && this.selectedEntryIndex < resolveChain.getEntries().size() && (entryResolved = resolveChain.getEntries().get(this.selectedEntryIndex).resolve()) != null && entryResolved.isBuiltIn()) {
                return;
            }
            if (mode == 0) {
                if (slot.isChainReference()) {
                    slot.convertToInline();
                }
                if ((chain = slot.getInlineChain()) != null && this.selectedEntryIndex < chain.getEntries().size()) {
                    Ability a;
                    ChainedAbilityEntry entry = chain.getEntries().get(this.selectedEntryIndex);
                    String sourceAbilityRef = entry.getAbilityReference();
                    if (entry.convertToInline() && (a = entry.getInlineAbility()) != null && !a.isBuiltIn()) {
                        this.editingChainEntry = true;
                        this.editChainSlotIdx = this.selectedSlotIndex;
                        this.editChainEntryIdx = this.selectedEntryIndex;
                        a.setNpcInlineEdit(true);
                        this.setSubGui(a.createConfigGui(this));
                        if (sourceAbilityRef != null && !sourceAbilityRef.isEmpty()) {
                            PacketClient.sendClient(new CopyAbilityScriptsPacket(sourceAbilityRef, a.getId()));
                        }
                    }
                }
                this.updateNpcAbilitiesList();
                this.save();
            } else if (mode == 1) {
                ChainedAbilityEntry entry;
                Ability resolved;
                ChainedAbility chainedAbility2 = chain = slot.isInlineChain() ? slot.getInlineChain() : slot.getChainedAbility();
                if (chain != null && this.selectedEntryIndex < chain.getEntries().size() && (resolved = (entry = chain.getEntries().get(this.selectedEntryIndex)).resolve()) != null && !resolved.isBuiltIn()) {
                    this.editingChainEntryParent = true;
                    this.setSubGui(resolved.createConfigGui(this));
                }
            }
        } else if (mode == 0) {
            Ability preCheck = slot.getAbility();
            if (preCheck != null && preCheck.isBuiltIn()) {
                return;
            }
            String sourceAbilityRef = slot.getReferenceId();
            if (slot.convertToInline()) {
                Ability ability2 = slot.getAbility();
                if (ability2 != null && !ability2.isBuiltIn()) {
                    ability2.setNpcInlineEdit(true);
                    this.setSubGui(ability2.createConfigGui(this));
                    if (sourceAbilityRef != null && !sourceAbilityRef.isEmpty()) {
                        PacketClient.sendClient(new CopyAbilityScriptsPacket(sourceAbilityRef, ability2.getId()));
                    }
                }
                this.updateNpcAbilitiesList();
                this.save();
            }
        } else if (mode == 1 && (ability = slot.getAbility()) != null && !ability.isBuiltIn()) {
            this.setSubGui(ability.createConfigGui(this));
        }
    }

    private void handleSaveConfirmClosed(SubGuiAbilitySaveConfirm gui) {
        if (gui.wasSaved() && this.pendingSaveSlotIdx >= 0 && this.pendingSaveSlotIdx < this.npcSlots.size()) {
            if (this.pendingSaveEntryIdx >= 0) {
                ChainedAbilityEntry entry;
                Ability a;
                ChainedAbility chain;
                AbilityAction slot = this.npcSlots.get(this.pendingSaveSlotIdx);
                ChainedAbility chainedAbility = chain = slot.isInlineChain() ? slot.getInlineChain() : null;
                if (chain != null && this.pendingSaveEntryIdx < chain.getEntries().size() && (a = (entry = chain.getEntries().get(this.pendingSaveEntryIdx)).getInlineAbility()) != null && a.getName() != null && !a.getName().isEmpty()) {
                    chain.getEntries().set(this.pendingSaveEntryIdx, ChainedAbilityEntry.reference(a.getName(), entry.getDelayTicks()));
                }
            } else {
                AbilityAction slot = this.npcSlots.get(this.pendingSaveSlotIdx);
                Ability a = slot.getAbility();
                if (a != null && a.getName() != null && !a.getName().isEmpty()) {
                    this.npcSlots.set(this.pendingSaveSlotIdx, AbilityAction.abilityReference(a.getName()));
                }
            }
            if (this.npcAbilitiesScroll != null) {
                this.npcAbilitiesScroll.resetScroll();
            }
            this.updateNpcAbilitiesList();
            this.save();
        }
        this.pendingSaveSlotIdx = -1;
        this.pendingSaveEntryIdx = -1;
    }

    private void handleLoadTypeChoiceClosed(SubGuiLoadTypeChoice gui) {
        int result = gui.getResult();
        if (result == 0) {
            this.setSubGui(new SubGuiAbilityLoad(this));
        } else if (result == 1) {
            this.setSubGui(new SubGuiChainSelect());
        }
    }

    private void handleChainSelectClosed(SubGuiChainSelect gui) {
        String chainName = gui.getSelectedName();
        if (chainName != null) {
            this.loadChainReference(chainName);
        }
    }

    private void handleAbilityConfigClosed() {
        this.editingChainEntry = false;
        this.editChainSlotIdx = -1;
        this.editChainEntryIdx = -1;
        this.editingChainEntryParent = false;
        this.updateNpcAbilitiesList();
        this.save();
    }

    @Override
    public void onAbilitySaved(Ability ability) {
        if (this.editingChainEntryParent) {
            PacketClient.sendClient(new CustomAbilitySavePacket(ability.writeNBT(false)));
            return;
        }
        if (this.editingChainEntry) {
            return;
        }
        if (this.selectedAbilityIndex >= 0 && this.selectedAbilityIndex < this.npcSlots.size()) {
            AbilityAction slot = this.npcSlots.get(this.selectedAbilityIndex);
            if (slot.isReference()) {
                PacketClient.sendClient(new CustomAbilitySavePacket(ability.writeNBT(false)));
            } else {
                this.npcSlots.set(this.selectedAbilityIndex, AbilityAction.inline(ability));
            }
        }
    }

    @Override
    public void onChainedAbilitySaved(ChainedAbility chain) {
    }

    public void loadChainReference(String referenceId) {
        if (referenceId != null && !referenceId.isEmpty()) {
            this.npcSlots.add(AbilityAction.chainReference(referenceId));
            this.selectedSlotIndex = this.npcSlots.size() - 1;
            this.selectedEntryIndex = -1;
            this.selectedAbilityIndex = this.selectedSlotIndex;
            if (this.npcAbilitiesScroll != null) {
                this.npcAbilitiesScroll.resetScroll();
            }
            this.updateNpcAbilitiesList();
            this.func_73866_w_();
            this.save();
        }
    }

    public void loadChainInline(ChainedAbility chain) {
        if (chain != null) {
            this.npcSlots.add(AbilityAction.inlineChain(chain));
            this.selectedSlotIndex = this.npcSlots.size() - 1;
            this.selectedEntryIndex = -1;
            this.selectedAbilityIndex = this.selectedSlotIndex;
            if (this.npcAbilitiesScroll != null) {
                this.npcAbilitiesScroll.resetScroll();
            }
            this.updateNpcAbilitiesList();
            this.func_73866_w_();
            this.save();
        }
    }

    public static enum ScrollType {
        CNPC,
        MODDED,
        ALL;


        public String toString() {
            switch (this) {
                case CNPC: {
                    return "CustomNPCs";
                }
                case MODDED: {
                    if (Register.isEmpty("ability")) {
                        return "modded";
                    }
                    String namespace = Register.REGISTERED_NAMESPACES.get("ability").get(modIndex);
                    String displayName = Register.NAMESPACE_DISPLAY_NAMES.get(namespace);
                    return displayName != null ? displayName : namespace;
                }
                case ALL: {
                    return "filter.all";
                }
            }
            return this.name();
        }
    }
}

