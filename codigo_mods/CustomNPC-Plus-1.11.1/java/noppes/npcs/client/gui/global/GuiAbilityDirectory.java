/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiYesNo
 *  net.minecraft.client.gui.GuiYesNoCallback
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import kamkeel.npcs.client.renderer.TelegraphRenderer;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.AbilityVariant;
import kamkeel.npcs.controllers.data.ability.data.ChainedAbility;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import kamkeel.npcs.controllers.data.ability.preview.AbilityPreviewExecutor;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.ability.BuiltInAbilityGetPacket;
import kamkeel.npcs.network.packets.request.ability.ChainedAbilityClonePacket;
import kamkeel.npcs.network.packets.request.ability.ChainedAbilityGetPacket;
import kamkeel.npcs.network.packets.request.ability.ChainedAbilityRemovePacket;
import kamkeel.npcs.network.packets.request.ability.ChainedAbilitySavePacket;
import kamkeel.npcs.network.packets.request.ability.CustomAbilitiesGetPacket;
import kamkeel.npcs.network.packets.request.ability.CustomAbilityClonePacket;
import kamkeel.npcs.network.packets.request.ability.CustomAbilityGetPacket;
import kamkeel.npcs.network.packets.request.ability.CustomAbilityRemovePacket;
import kamkeel.npcs.network.packets.request.ability.CustomAbilitySavePacket;
import kamkeel.npcs.network.packets.request.category.AbilityCategoryMovePacket;
import kamkeel.npcs.network.packets.request.category.CategoryItemsRequestPacket;
import kamkeel.npcs.network.packets.request.category.CategoryListRequestPacket;
import kamkeel.npcs.network.packets.request.category.CategoryRemovePacket;
import kamkeel.npcs.network.packets.request.category.CategorySavePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import noppes.npcs.client.ClientEventHandler;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.advanced.SubGuiAbilityTypeSelect;
import noppes.npcs.client.gui.advanced.SubGuiAbilityVariantSelect;
import noppes.npcs.client.gui.advanced.SubGuiChainedAbilityConfig;
import noppes.npcs.client.gui.advanced.SubGuiDuplicateNameConfirm;
import noppes.npcs.client.gui.global.GuiNpcManageAbilities;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiDirectoryCategorized;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiTexturedButton;
import noppes.npcs.client.gui.util.GuiUtil;
import noppes.npcs.client.gui.util.IAbilityConfigCallback;
import noppes.npcs.client.gui.util.IChainedAbilityConfigCallback;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.controllers.data.AnimationData;
import noppes.npcs.controllers.data.Category;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public class GuiAbilityDirectory
extends GuiDirectoryCategorized
implements IAbilityConfigCallback,
IChainedAbilityConfigCallback {
    private EntityNPCInterface npc;
    private EntityNPCInterface originalNpc;
    private int viewMode = 1;
    private boolean currentIsBuiltIn = false;
    private HashMap<String, Integer> builtInData = new HashMap();
    private Ability selectedAbility = null;
    private ChainedAbility selectedChain = null;
    private int userTypeFilter = 0;
    private static final String[] FILTER_LABELS = new String[]{"gui.both", "gui.npcs", "gui.players"};
    private Ability pendingSaveAbility = null;
    private ChainedAbility pendingSaveChain = null;
    private String pendingTypeId = null;
    private AbilityPreviewExecutor previewExecutor;
    private long prevTick = 0L;
    private double npcStartX;
    private double npcStartY;
    private double npcStartZ;
    private boolean trackingMovement = false;
    private static final float NPC_FACING_YAW = 310.0f;
    private static final float CAMERA_PITCH = 5.0f;

    public GuiAbilityDirectory(EntityNPCInterface npc) {
        this.originalNpc = npc;
        this.npc = GuiAbilityDirectory.createFakeNPC(npc);
        this.rightPanelPercent = 0.38f;
        this.minRightPanelW = 200;
        this.zoomed = 50.0f;
        this.previewExecutor = new AbilityPreviewExecutor();
        this.requestCategoryList();
        PacketClient.sendClient(new CustomAbilitiesGetPacket());
    }

    private static EntityNPCInterface createFakeNPC(EntityNPCInterface original) {
        EntityCustomNpc fake = new EntityCustomNpc((World)Minecraft.func_71410_x().field_71441_e);
        fake.display.readToNBT(original.display.writeToNBT(new NBTTagCompound()));
        fake.display.name = "ability preview";
        fake.field_70131_O = original.field_70131_O;
        fake.field_70130_N = original.field_70130_N;
        fake.display.animationData.setEnabled(true);
        return fake;
    }

    private boolean isBuiltInMode() {
        return this.viewMode == 0;
    }

    private boolean isCustomMode() {
        return this.viewMode == 1;
    }

    private boolean isChainedMode() {
        return this.viewMode == 2;
    }

    private int getCategoryType() {
        return this.isChainedMode() ? 5 : 4;
    }

    @Override
    protected boolean hasCategories() {
        return !this.isBuiltInMode();
    }

    @Override
    protected void computeLayout() {
        if (this.isBuiltInMode()) {
            this.leftPanelPercent = 0.0f;
            this.minLeftPanelW = 0;
        } else {
            this.leftPanelPercent = 0.15f;
            this.minLeftPanelW = 120;
        }
        super.computeLayout();
    }

    @Override
    protected void drawPanels() {
        if (this.isBuiltInMode()) {
            if (this.rightPanelW > 0) {
                GuiUtil.drawRectD(this.rightX - 1, this.contentY - 1, this.rightX + this.rightPanelW + 1, this.originY + this.usableH + 1, this.panelBorder);
            }
        } else {
            super.drawPanels();
        }
    }

    @Override
    protected void initLeftPanel() {
        if (this.isBuiltInMode()) {
            return;
        }
        super.initLeftPanel();
    }

    @Override
    protected String getTitle() {
        if (this.isBuiltInMode()) {
            return "Abilities (Built-in)";
        }
        if (this.isChainedMode()) {
            return "Chained Abilities";
        }
        return "Abilities";
    }

    @Override
    protected int initExtraTopBarButtons(int x, int topBtnY) {
        int topBtnW = 55;
        String[] labels = new String[]{"gui.builtin", "gui.custom", "gui.chained"};
        int[] colors = new int[]{0x55FFFF, 0xFFFFFF, 0xFFFF55};
        GuiNpcButton viewBtn = new GuiNpcButton(60, x, topBtnY, topBtnW, this.btnH, labels[this.viewMode]);
        viewBtn.setTextColor(colors[this.viewMode]);
        this.addButton(viewBtn);
        x += topBtnW + 2;
        if (!this.isChainedMode()) {
            this.addButton(new GuiNpcButton(61, x, topBtnY, topBtnW, this.btnH, FILTER_LABELS[this.userTypeFilter]));
            x += topBtnW + 2;
        }
        return x;
    }

    @Override
    protected void initTopBar(int topBtnY) {
        super.initTopBar(topBtnY);
        if (this.isBuiltInMode()) {
            if (this.getButton(50) != null) {
                this.getButton((int)50).field_146124_l = false;
            }
            if (this.getButton(54) != null) {
                this.getButton((int)54).field_146124_l = false;
            }
        }
    }

    @Override
    protected void requestCategoryList() {
        if (this.isBuiltInMode()) {
            return;
        }
        PacketClient.sendClient(new CategoryListRequestPacket(this.getCategoryType()));
    }

    @Override
    protected void requestItemsInCategory(int catId) {
        if (this.isBuiltInMode()) {
            return;
        }
        PacketClient.sendClient(new CategoryItemsRequestPacket(this.getCategoryType(), catId));
    }

    @Override
    protected void requestItemData(int itemId) {
    }

    @Override
    protected void onSaveCategory(Category cat) {
        PacketClient.sendClient(new CategorySavePacket(this.getCategoryType(), cat.writeNBT(new NBTTagCompound())));
    }

    @Override
    protected void onRemoveCategory(int catId) {
        PacketClient.sendClient(new CategoryRemovePacket(this.getCategoryType(), catId));
    }

    @Override
    protected void onAddItem(int catId) {
        if (this.isBuiltInMode()) {
            return;
        }
        if (this.isChainedMode()) {
            ChainedAbility newChain = new ChainedAbility("New Chain");
            this.setSubGui(new SubGuiChainedAbilityConfig(newChain, this));
        } else {
            this.setSubGui(new SubGuiAbilityTypeSelect());
        }
    }

    @Override
    protected void onRemoveItem(int itemId) {
    }

    @Override
    protected void onEditItem() {
        this.stopPreviewPlayback();
        if (this.isChainedMode() && this.selectedChain != null) {
            this.setSubGui(new SubGuiChainedAbilityConfig(this.selectedChain, this));
        } else if (!this.currentIsBuiltIn && this.selectedAbility != null) {
            this.setSubGui(this.selectedAbility.createConfigGui(this));
        }
    }

    @Override
    protected void onCloneItem() {
        if (this.currentIsBuiltIn) {
            return;
        }
        if (this.isChainedMode() && this.selectedChain != null) {
            PacketClient.sendClient(new ChainedAbilityClonePacket(this.selectedChain.getName()));
            if (this.selectedCatId >= 0) {
                this.requestItemsInCategory(this.selectedCatId);
            }
        } else if (this.selectedAbility != null) {
            PacketClient.sendClient(new CustomAbilityClonePacket(this.selectedAbility.getName()));
            if (this.selectedCatId >= 0 && this.isCustomMode()) {
                this.requestItemsInCategory(this.selectedCatId);
            }
        }
    }

    @Override
    protected void onItemReceived(NBTTagCompound compound) {
        if (this.isChainedMode() && compound.func_74764_b("Entries")) {
            this.selectedChain = new ChainedAbility();
            this.selectedChain.readNBT(compound);
            this.setPrevItemName(this.selectedChain.getName());
        } else {
            this.currentIsBuiltIn = compound.func_74764_b("BuiltIn") && compound.func_74767_n("BuiltIn");
            this.selectedAbility = AbilityController.Instance.fromNBT(compound);
            if (this.selectedAbility != null) {
                this.setPrevItemName(this.selectedAbility.getName());
            }
        }
    }

    @Override
    protected boolean hasSelectedItem() {
        if (this.isChainedMode()) {
            return this.selectedChain != null;
        }
        if (this.currentIsBuiltIn) {
            return this.selectedAbility != null;
        }
        return this.selectedAbility != null;
    }

    @Override
    protected int getSelectedItemId() {
        return -1;
    }

    @Override
    protected void sendMovePacket(int itemId, int destCatId) {
    }

    @Override
    protected void onMoveNewItem(int catId) {
        String name = this.prevItemName;
        if (name != null && !name.isEmpty()) {
            PacketClient.sendClient(new AbilityCategoryMovePacket(this.getCategoryType(), name, catId));
            if (this.selectedCatId >= 0) {
                this.requestItemsInCategory(this.selectedCatId);
            }
        }
    }

    @Override
    protected GuiScreen getWindowedVariant() {
        return new GuiNpcManageAbilities(this.originalNpc, false);
    }

    @Override
    protected void saveCurrentItem() {
    }

    @Override
    protected void executeMoveItems(int destCatId) {
        for (String name : this.moveSelection) {
            PacketClient.sendClient(new AbilityCategoryMovePacket(this.getCategoryType(), name, destCatId));
        }
        this.movePhase = 0;
        this.moveSelection.clear();
        if (this.selectedCatId >= 0) {
            this.requestItemsInCategory(this.selectedCatId);
        }
        this.func_73866_w_();
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll scroll) {
        if (scroll.id == 0) {
            if (this.isBuiltInMode()) {
                String selected = this.itemScroll.getSelected();
                if (selected != null && !selected.equals(this.prevItemName)) {
                    this.stopPreviewPlayback();
                    this.currentIsBuiltIn = true;
                    PacketClient.sendClient(new BuiltInAbilityGetPacket(selected));
                    this.prevItemName = selected;
                }
                return;
            }
            if (this.movePhase == 1) {
                if (this.moveSelection.size() > 5) {
                    HashSet<String> trimmed = new HashSet<String>();
                    int count = 0;
                    for (String s : this.moveSelection) {
                        if (count++ >= 5) break;
                        trimmed.add(s);
                    }
                    this.moveSelection.clear();
                    this.moveSelection.addAll(trimmed);
                    this.itemScroll.setSelectedList(this.moveSelection);
                }
                if (this.getButton(55) != null) {
                    this.getButton((int)55).field_146124_l = !this.moveSelection.isEmpty();
                }
                return;
            }
            String selected = this.itemScroll.getSelected();
            if (selected != null && !selected.equals(this.prevItemName)) {
                this.stopPreviewPlayback();
                this.prevItemName = selected;
                this.currentIsBuiltIn = false;
                if (this.isChainedMode()) {
                    PacketClient.sendClient(new ChainedAbilityGetPacket(selected));
                } else {
                    PacketClient.sendClient(new CustomAbilityGetPacket(selected));
                }
            }
            return;
        }
        super.customScrollClicked(i, j, k, scroll);
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
        if (scroll.id == 0 && this.hasSelectedItem() && this.movePhase == 0) {
            this.onEditItem();
        }
    }

    @Override
    protected List<String> getItemSearchList() {
        if (this.selectedCatId < 0 && !this.isBuiltInMode()) {
            return new ArrayList<String>();
        }
        ArrayList<String> list = new ArrayList<String>();
        HashMap data = this.isBuiltInMode() ? this.builtInData : this.itemData;
        for (Map.Entry entry : data.entrySet()) {
            String name = (String)entry.getKey();
            if (!this.itemSearch.isEmpty() && !name.toLowerCase().contains(this.itemSearch)) continue;
            if (!this.isChainedMode() && this.userTypeFilter != 0) {
                UserType ut = UserType.fromOrdinal((Integer)entry.getValue());
                if (this.userTypeFilter == 1 && !ut.allowsNpc() || this.userTypeFilter == 2 && !ut.allowsPlayer()) continue;
            }
            list.add(name);
        }
        return list;
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        if (type == EnumScrollData.BUILTIN_ABILITIES) {
            this.builtInData = data;
            if (this.isBuiltInMode()) {
                this.itemScroll.setList(this.getItemSearchList());
                this.func_73866_w_();
            }
            return;
        }
        if (type == EnumScrollData.CUSTOM_ABILITIES) {
            return;
        }
        if (type == EnumScrollData.CHAINED_ABILITIES) {
            return;
        }
        super.setData(list, data, type);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 60) {
            this.viewMode = (this.viewMode + 1) % 3;
            this.stopPreviewPlayback();
            this.clearSelection();
            if (this.isBuiltInMode()) {
                this.selectedCatId = 0;
                PacketClient.sendClient(new CustomAbilitiesGetPacket());
            } else {
                this.selectedCatId = -1;
                this.requestCategoryList();
            }
            this.func_73866_w_();
            return;
        }
        if (id == 61) {
            this.userTypeFilter = (this.userTypeFilter + 1) % 3;
            this.itemScroll.setList(this.getItemSearchList());
            this.func_73866_w_();
            return;
        }
        if (id == 91) {
            if (this.previewExecutor.isPaused()) {
                this.previewExecutor.play();
            } else {
                this.startPreviewPlayback();
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
            this.stopPreviewPlayback();
            this.func_73866_w_();
            return;
        }
        if (id == 53 && this.prevItemName != null && !this.prevItemName.isEmpty() && this.movePhase == 0) {
            int confirmId = this.isChainedMode() ? 3 : 2;
            GuiYesNo guiyesno = new GuiYesNo((GuiYesNoCallback)this, this.prevItemName, StatCollector.func_74838_a((String)"gui.delete"), confirmId);
            this.displayGuiScreen((GuiScreen)guiyesno);
            return;
        }
        super.func_146284_a(guibutton);
    }

    private void clearSelection() {
        this.prevItemName = "";
        this.selectedAbility = null;
        this.selectedChain = null;
        this.currentIsBuiltIn = false;
        this.itemSearch = "";
        this.movePhase = 0;
        this.moveSelection.clear();
        this.trackingMovement = false;
    }

    @Override
    public void func_73878_a(boolean result, int id) {
        NoppesUtil.openGUI((EntityPlayer)this.player, this);
        if (!result) {
            return;
        }
        if (id == 2 && this.prevItemName != null && !this.prevItemName.isEmpty()) {
            PacketClient.sendClient(new CustomAbilityRemovePacket(this.prevItemName));
            this.selectedAbility = null;
            this.prevItemName = "";
            if (this.selectedCatId >= 0) {
                this.requestItemsInCategory(this.selectedCatId);
            }
            this.func_73866_w_();
            return;
        }
        if (id == 3 && this.prevItemName != null && !this.prevItemName.isEmpty()) {
            PacketClient.sendClient(new ChainedAbilityRemovePacket(this.prevItemName));
            this.selectedChain = null;
            this.prevItemName = "";
            if (this.selectedCatId >= 0) {
                this.requestItemsInCategory(this.selectedCatId);
            }
            this.func_73866_w_();
            return;
        }
        super.func_73878_a(result, id);
    }

    @Override
    protected void onSubGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiChainedAbilityConfig) {
            if (this.pendingSaveChain != null) {
                PacketClient.sendClient(new ChainedAbilitySavePacket(this.pendingSaveChain.writeNBT(false)));
                this.prevItemName = this.pendingSaveChain.getName();
                this.pendingSaveChain = null;
                if (this.selectedCatId >= 0 && this.isChainedMode()) {
                    this.requestItemsInCategory(this.selectedCatId);
                }
            }
            this.func_73866_w_();
            return;
        }
        if (subgui instanceof SubGuiAbilityVariantSelect) {
            Ability newAbility;
            SubGuiAbilityVariantSelect gui = (SubGuiAbilityVariantSelect)subgui;
            int idx = gui.getSelectedIndex();
            if (idx >= 0 && this.pendingTypeId != null && (newAbility = AbilityController.Instance.create(this.pendingTypeId)) != null) {
                gui.getVariants().get(idx).apply(newAbility);
                newAbility.setId(UUID.randomUUID().toString());
                this.pendingTypeId = null;
                this.selectedAbility = newAbility;
                this.prevItemName = newAbility.getName();
                this.setSubGui(newAbility.createConfigGui(this));
                return;
            }
            this.pendingTypeId = null;
        } else if (subgui instanceof SubGuiAbilityTypeSelect) {
            String typeId = ((SubGuiAbilityTypeSelect)subgui).getSelectedTypeId();
            if (typeId != null) {
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
                    this.selectedAbility = newAbility;
                    this.prevItemName = newAbility.getName();
                    this.setSubGui(newAbility.createConfigGui(this));
                    return;
                }
            }
        } else if (subgui instanceof SubGuiDuplicateNameConfirm) {
            SubGuiDuplicateNameConfirm gui = (SubGuiDuplicateNameConfirm)subgui;
            if (this.pendingSaveAbility != null) {
                if (gui.isConfirmed()) {
                    PacketClient.sendClient(new CustomAbilitySavePacket(this.pendingSaveAbility.writeNBT(false)));
                    this.prevItemName = this.pendingSaveAbility.getName();
                    this.pendingSaveAbility = null;
                    if (this.selectedCatId >= 0 && this.isCustomMode()) {
                        this.requestItemsInCategory(this.selectedCatId);
                    }
                } else {
                    if (gui.isBack()) {
                        Ability ability = this.pendingSaveAbility;
                        this.pendingSaveAbility = null;
                        this.setSubGui(ability.createConfigGui(this));
                        return;
                    }
                    this.pendingSaveAbility = null;
                }
            }
        } else if (this.pendingSaveAbility != null) {
            if (this.hasDuplicateName(this.pendingSaveAbility)) {
                this.setSubGui(new SubGuiDuplicateNameConfirm());
                return;
            }
            PacketClient.sendClient(new CustomAbilitySavePacket(this.pendingSaveAbility.writeNBT(false)));
            this.prevItemName = this.pendingSaveAbility.getName();
            this.pendingSaveAbility = null;
            if (this.selectedCatId >= 0 && this.isCustomMode()) {
                this.requestItemsInCategory(this.selectedCatId);
            }
        }
        this.func_73866_w_();
    }

    private boolean hasDuplicateName(Ability ability) {
        String name = ability.getName();
        if (name == null || name.isEmpty()) {
            return false;
        }
        if (!this.itemData.containsKey(name)) {
            return false;
        }
        String uuid = ability.getId();
        if (uuid == null || uuid.isEmpty()) {
            return true;
        }
        Ability existing = AbilityController.Instance.getCustomAbilityByName(name);
        return existing == null || !uuid.equals(existing.getId());
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
    protected void initRightPanel(int startY) {
        int bottomRows = 2;
        boolean hasPlayback = this.hasSelectedItem();
        if (hasPlayback) {
            ++bottomRows;
        }
        int bottomH = bottomRows * (this.btnH + this.gap) + 14;
        this.previewX = this.rightX;
        this.previewY = this.contentY;
        this.previewW = this.rightPanelW;
        this.previewH = this.contentH - bottomH - this.gap;
        if (hasPlayback) {
            int playY = this.contentY + this.previewH + this.gap;
            String animTexture = "customnpcs:textures/gui/animation.png";
            int btnX = this.rightX + 4;
            boolean isPlaying = this.previewExecutor.isPlaying() && !this.previewExecutor.isPaused();
            boolean isPaused = this.previewExecutor.isPaused();
            boolean isActive = this.previewExecutor.isActive();
            if (!isPlaying || isPaused) {
                String statusKey = isPaused ? "animation.paused" : "animation.stopped";
                this.addLabel(new GuiNpcLabel(90, statusKey, btnX, playY + 5, 0xFFFFFF));
                this.addButton(new GuiTexturedButton(91, "", btnX + 65, playY, 11, 20, animTexture, 18, 71));
            } else {
                this.addLabel(new GuiNpcLabel(90, "animation.playing", btnX, playY + 5, 0xFFFFFF));
                this.addButton(new GuiTexturedButton(92, "", btnX + 65, playY, 14, 20, animTexture, 0, 71));
            }
            if (isActive) {
                this.addButton(new GuiTexturedButton(93, "", btnX + 85, playY, 14, 20, animTexture, 33, 71));
            }
        }
        int btnY = this.contentY + this.contentH - this.btnH * 2 - this.gap;
        int halfW = (this.rightPanelW - this.gap) / 2;
        GuiNpcButton editBtn = new GuiNpcButton(51, this.rightX, btnY, halfW, this.btnH, "gui.edit");
        editBtn.field_146124_l = this.hasSelectedItem() && this.movePhase == 0 && !this.currentIsBuiltIn;
        this.addButton(editBtn);
        GuiNpcButton cloneBtn = new GuiNpcButton(52, this.rightX + halfW + this.gap, btnY, halfW, this.btnH, "gui.copy");
        cloneBtn.field_146124_l = this.hasSelectedItem() && this.movePhase == 0 && !this.currentIsBuiltIn;
        this.addButton(cloneBtn);
        int removeY = btnY + this.btnH + this.gap;
        GuiNpcButton removeBtn = new GuiNpcButton(53, this.rightX, removeY, this.rightPanelW, this.btnH, "gui.remove");
        removeBtn.field_146124_l = this.hasSelectedItem() && this.movePhase == 0 && !this.currentIsBuiltIn;
        removeBtn.setTextColor(0xFF5555);
        this.addButton(removeBtn);
    }

    @Override
    protected void drawItemPreview(int centerX, int centerY, int mouseX, int mouseY, float partialTicks) {
        if (this.selectedAbility == null && this.selectedChain == null) {
            return;
        }
        boolean previewing = this.previewExecutor.isActive();
        if (previewing) {
            this.drawAbilityPreview(partialTicks);
        } else {
            this.drawStaticNpcPreview(centerX, centerY, mouseX, mouseY);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void drawAbilityPreview(float partialTicks) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.npc.field_70761_aq = 310.0f;
        this.npc.field_70760_ar = 310.0f;
        this.npc.field_70177_z = 310.0f;
        this.npc.field_70126_B = 310.0f;
        this.npc.field_70759_as = 310.0f;
        this.npc.field_70758_at = 310.0f;
        this.npc.field_70125_A = 0.0f;
        double npcDeltaX = 0.0;
        double npcDeltaY = 0.0;
        double npcDeltaZ = 0.0;
        if (this.trackingMovement) {
            double interpX = this.npc.field_70169_q + (this.npc.field_70165_t - this.npc.field_70169_q) * (double)partialTicks;
            double interpY = this.npc.field_70167_r + (this.npc.field_70163_u - this.npc.field_70167_r) * (double)partialTicks;
            double interpZ = this.npc.field_70166_s + (this.npc.field_70161_v - this.npc.field_70166_s) * (double)partialTicks;
            npcDeltaX = interpX - this.npcStartX;
            npcDeltaY = interpY - this.npcStartY;
            npcDeltaZ = interpZ - this.npcStartZ;
        }
        int npcScreenX = this.previewX + (int)((float)this.previewW * 0.33f);
        int npcScreenY = this.previewY + (int)((float)this.previewH * 0.8f);
        float scale = (float)Math.min(this.previewW, this.previewH) / 200.0f;
        float renderZoom = this.zoomed * scale;
        GL11.glEnable((int)3089);
        this.setScissorClip(this.previewX, this.previewY, this.previewW, this.previewH);
        try {
            GL11.glEnable((int)2903);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)npcScreenX, (float)npcScreenY, (float)500.0f);
            GL11.glScalef((float)(-renderZoom), (float)renderZoom, (float)renderZoom);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)5.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)this.rotation, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            RenderHelper.func_74519_b();
            GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)this.npc.field_70129_M, (float)0.0f);
            RenderManager.field_78727_a.field_78735_i = 180.0f;
            ClientEventHandler.renderingEntityInGUI = true;
            try {
                RenderManager.field_78727_a.func_147940_a((Entity)this.npc, npcDeltaX, npcDeltaY, npcDeltaZ, 0.0f, partialTicks);
            }
            catch (Exception exception) {
                // empty catch block
            }
            this.renderPreviewEntities(partialTicks);
            this.renderPreviewTelegraph(partialTicks);
            ClientEventHandler.renderingEntityInGUI = false;
            GL11.glPopMatrix();
            RenderHelper.func_74518_a();
            GL11.glDisable((int)32826);
            OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
            GL11.glDisable((int)3553);
            OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
        }
        finally {
            ClientEventHandler.renderingEntityInGUI = false;
            GL11.glDisable((int)3089);
        }
        GL11.glClear((int)256);
    }

    private void drawStaticNpcPreview(int centerX, int centerY, int mouseX, int mouseY) {
        float scale = (float)Math.min(this.previewW, this.previewH) / 200.0f;
        float renderZoom = this.zoomed * scale;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        EntityNPCInterface entity = this.npc;
        GL11.glEnable((int)2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)centerX, (float)centerY, (float)60.0f);
        GL11.glScalef((float)(-renderZoom), (float)renderZoom, (float)renderZoom);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        float f2 = ((EntityLivingBase)entity).field_70761_aq;
        float f3 = ((EntityLivingBase)entity).field_70177_z;
        float f4 = ((EntityLivingBase)entity).field_70125_A;
        float f7 = ((EntityLivingBase)entity).field_70759_as;
        float f5 = (float)centerX - (float)mouseX;
        float f6 = (float)(centerY - 50) - (float)mouseY;
        GL11.glRotatef((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-((float)Math.atan(f6 / 800.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        ((EntityLivingBase)entity).field_70760_ar = ((EntityLivingBase)entity).field_70761_aq = this.rotation;
        ((EntityLivingBase)entity).field_70126_B = ((EntityLivingBase)entity).field_70177_z = (float)Math.atan(f5 / 80.0f) * 40.0f + this.rotation;
        ((EntityLivingBase)entity).field_70125_A = -((float)Math.atan(f6 / 80.0f)) * 20.0f;
        ((EntityLivingBase)entity).field_70758_at = ((EntityLivingBase)entity).field_70759_as = ((EntityLivingBase)entity).field_70177_z;
        GL11.glTranslatef((float)0.0f, (float)((EntityLivingBase)entity).field_70129_M, (float)1.0f);
        RenderManager.field_78727_a.field_78735_i = 180.0f;
        ClientEventHandler.renderingEntityInGUI = true;
        try {
            RenderManager.field_78727_a.func_147940_a((Entity)entity, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        }
        catch (Exception exception) {
            // empty catch block
        }
        ClientEventHandler.renderingEntityInGUI = false;
        ((EntityLivingBase)entity).field_70760_ar = ((EntityLivingBase)entity).field_70761_aq = f2;
        ((EntityLivingBase)entity).field_70126_B = ((EntityLivingBase)entity).field_70177_z = f3;
        ((EntityLivingBase)entity).field_70125_A = f4;
        ((EntityLivingBase)entity).field_70758_at = ((EntityLivingBase)entity).field_70759_as = f7;
        GL11.glPopMatrix();
        RenderHelper.func_74518_a();
        GL11.glDisable((int)32826);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
        GL11.glDisable((int)3553);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
        GL11.glClear((int)256);
    }

    private void renderPreviewEntities(float partialTicks) {
        List<Entity> entities = this.previewExecutor.getPreviewEntities();
        if (entities.isEmpty()) {
            return;
        }
        boolean lightingWasEnabled = GL11.glIsEnabled((int)2896);
        double refX = this.trackingMovement ? this.npcStartX : this.npc.field_70165_t;
        double refY = this.trackingMovement ? this.npcStartY : this.npc.field_70163_u;
        double refZ = this.trackingMovement ? this.npcStartZ : this.npc.field_70161_v;
        for (Entity entity : entities) {
            if (entity == null || entity.field_70128_L) continue;
            double offsetX = entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * (double)partialTicks - refX;
            double offsetY = entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * (double)partialTicks - refY - (double)this.npc.field_70129_M;
            double offsetZ = entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * (double)partialTicks - refZ;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)2896);
            try {
                RenderManager.field_78727_a.func_147940_a(entity, offsetX, offsetY, offsetZ, entity.field_70177_z, partialTicks);
            }
            catch (Exception exception) {}
        }
        if (lightingWasEnabled) {
            GL11.glEnable((int)2896);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void renderPreviewTelegraph(float partialTicks) {
        TelegraphInstance telegraph = this.previewExecutor.getTelegraph();
        if (telegraph == null || TelegraphRenderer.Instance == null) {
            return;
        }
        double refX = this.trackingMovement ? this.npcStartX : this.npc.field_70165_t;
        double refY = this.trackingMovement ? this.npcStartY : this.npc.field_70163_u;
        double refZ = this.trackingMovement ? this.npcStartZ : this.npc.field_70161_v;
        double offsetX = telegraph.getInterpolatedX(partialTicks) - refX;
        double offsetY = telegraph.getInterpolatedY(partialTicks) - refY - (double)this.npc.field_70129_M;
        double offsetZ = telegraph.getInterpolatedZ(partialTicks) - refZ;
        TelegraphRenderer.Instance.renderTelegraphInGUI(telegraph, offsetX, offsetY, offsetZ, 1.0f, partialTicks);
    }

    private void setScissorClip(int x, int y, int w, int h) {
        ScaledResolution sr = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        int scale = sr.func_78325_e();
        int scaledY = this.field_146297_k.field_71440_d - (y + h) * scale;
        GL11.glScissor((int)(x * scale), (int)scaledY, (int)(w * scale), (int)(h * scale));
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        if (this.previewExecutor.isActive()) {
            this.npc.field_70761_aq = 310.0f;
            this.npc.field_70760_ar = 310.0f;
            this.npc.field_70177_z = 310.0f;
            this.npc.field_70126_B = 310.0f;
            this.npc.field_70759_as = 310.0f;
            this.npc.field_70758_at = 310.0f;
        }
        this.tickPreview();
        super.func_73863_a(mouseX, mouseY, partialTicks);
    }

    private void tickPreview() {
        if (this.previewExecutor.isPlaying() && !this.previewExecutor.isPaused()) {
            long time;
            long l = time = this.field_146297_k.field_71441_e != null ? this.field_146297_k.field_71441_e.func_82737_E() : System.currentTimeMillis() / 50L;
            if (time != this.prevTick) {
                this.npc.display.animationData.increaseTime();
                this.previewExecutor.tick();
                this.prevTick = time;
                if (!this.previewExecutor.isActive()) {
                    this.trackingMovement = false;
                    this.func_73866_w_();
                }
            }
        }
    }

    private void startPreviewPlayback() {
        this.npcStartX = this.npc.field_70165_t;
        this.npcStartY = this.npc.field_70163_u;
        this.npcStartZ = this.npc.field_70161_v;
        this.trackingMovement = true;
        if (this.isChainedMode() && this.selectedChain != null) {
            this.previewExecutor.startChainPreview(this.selectedChain, this.npc);
        } else if (this.selectedAbility != null) {
            this.previewExecutor.startPreview(this.selectedAbility, this.npc);
        }
    }

    private void stopPreviewPlayback() {
        this.previewExecutor.stop();
        this.trackingMovement = false;
        AnimationData data = this.npc.display.animationData;
        data.setAnimation(new Animation());
    }

    @Override
    protected void drawOverlay(int mouseX, int mouseY, float partialTicks) {
        super.drawOverlay(mouseX, mouseY, partialTicks);
        if (this.previewExecutor.isActive() && !this.hasSubGui()) {
            String status = this.previewExecutor.getStatusString();
            this.field_146289_q.func_85187_a(status, this.previewX + 4, this.previewY + this.previewH - 12, 0xFFFFFF, true);
        }
    }

    @Override
    protected void drawItemDetails(int x, int y, int w) {
        if (this.isChainedMode() && this.selectedChain != null) {
            this.field_146289_q.func_85187_a(this.selectedChain.getDisplayName(), x, y, 0xFFFFFF, true);
            this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"ability.entries") + ": " + this.selectedChain.getEntries().size(), x, y += 14, 0xB5B5B5, false);
            this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"ability.cooldown") + ": " + this.selectedChain.getCooldownTicks() + "t", x, y += 12, 0xB5B5B5, false);
            UserType ut = this.selectedChain.getAllowedBy();
            this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"ability.allowed") + ": " + ut.name(), x, y += 12, 0xB5B5B5, false);
        } else if (this.selectedAbility != null) {
            this.field_146289_q.func_85187_a(this.selectedAbility.getDisplayName(), x, y, 0xFFFFFF, true);
            String typeId = this.selectedAbility.getTypeId();
            String typeName = StatCollector.func_74838_a((String)typeId);
            this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"gui.type") + ": " + typeName, x, y += 14, 16756237, false);
            this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"ability.cooldown") + ": " + this.selectedAbility.getCooldownTicks() + "t", x, y += 12, 0xB5B5B5, false);
            this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"ability.windup") + ": " + this.selectedAbility.getWindUpTicks() + "t", x, y += 12, 0xB5B5B5, false);
            UserType ut = this.selectedAbility.getAllowedBy();
            this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"ability.allowed") + ": " + ut.name(), x, y += 12, 0xB5B5B5, false);
            if (this.selectedAbility.hasDamage()) {
                this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"ability.damage") + ": " + this.selectedAbility.getDisplayDamage(), x, y += 12, 0xFF5555, false);
            }
            if (this.currentIsBuiltIn) {
                this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"gui.builtin.tag"), x, y += 12, 0x55FF55, false);
            }
        }
    }

    @Override
    public void func_146281_b() {
        this.stopPreviewPlayback();
        if (this.npc != null) {
            this.npc.display.animationData.setEnabled(false);
        }
        super.func_146281_b();
    }

    @Override
    public void save() {
    }
}

