/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.ChainedAbility;
import kamkeel.npcs.controllers.data.ability.data.IAbilityAction;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.packets.player.ability.AbilityHotbarSavePacket;
import kamkeel.npcs.network.packets.player.ability.AbilityHotbarSelectPacket;
import kamkeel.npcs.network.packets.player.ability.AbilityTogglePacket;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.gui.hud.ability.AbilityIcon;
import noppes.npcs.client.gui.player.inventory.GuiCNPCInventory;
import noppes.npcs.client.gui.util.GuiMenuTopButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.MagicController;
import noppes.npcs.controllers.data.AbilityHotbarData;
import noppes.npcs.controllers.data.Magic;
import noppes.npcs.controllers.data.MagicData;
import noppes.npcs.controllers.data.MagicEntry;
import noppes.npcs.controllers.data.PlayerData;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import tconstruct.client.tabs.AbstractTab;

public class GuiAbilities
extends GuiCNPCInventory
implements ISubGuiListener {
    private final ResourceLocation resource = new ResourceLocation("customnpcs", "textures/gui/standardbg.png");
    private int subTab = 0;
    private static final int BTN_TAB_ABILITIES = -200;
    private static final int BTN_TAB_TOGGLES = -201;
    private static final int BTN_SCROLL_LEFT = -202;
    private static final int BTN_SCROLL_RIGHT = -203;
    private int selLeft = 140;
    private int selTop = 4;
    private int selRight = 316;
    private int selBottom = 150;
    private int infoLeft = 5;
    private int infoTop = 4;
    private int infoRight = 137;
    private int infoBottom = 150;
    private int hbLeft = 5;
    private int hbTop = 153;
    private int hbRight = 316;
    private int hbBottom = 190;
    private int gridPad = 3;
    private int gridCellSize = 22;
    private int gridCols = 7;
    private int hbSlotSize = 18;
    private int hbSlotGap = 2;
    private int hbSlots = 12;
    private int infoPad = 5;
    private int infoIconSize = 40;
    private int dragThreshold = 3;
    private List<String> filteredKeys = new ArrayList<String>();
    private List<String> filteredDisplayNames = new ArrayList<String>();
    private List<AbilityIcon> filteredIcons = new ArrayList<AbilityIcon>();
    private List<Ability> filteredAbilities = new ArrayList<Ability>();
    private List<IAbilityAction> filteredActions = new ArrayList<IAbilityAction>();
    private int scrollRow = 0;
    private int hoveredGridIndex = -1;
    private float[] gridHoverScale;
    private int selectedIndex = -1;
    private AbilityIcon cachedDetailIcon = null;
    private int cachedDetailIndex = -1;
    private int infoScrollOffset = 0;
    private int infoContentHeight = 0;
    private int lastInfoIndex = -1;
    private int hoveredSlotIndex = -1;
    private AbilityIcon[] hotbarIcons = new AbilityIcon[12];
    private boolean isDragging = false;
    private boolean dragPending = false;
    private String draggedKey = null;
    private AbilityIcon draggedIcon = null;
    private int dragSourceSlot = -1;
    private int dragStartX;
    private int dragStartY;
    private int dragMouseX;
    private int dragMouseY;
    private String initialSelectedKey = null;
    private long lastToggleClickTime = 0L;
    private int lastToggleClickIndex = -1;

    public GuiAbilities() {
        this.xSize = 280;
        this.ySize = 180;
        this.hbSlotSize = 23;
        this.gridCellSize = 24;
        this.gridCols = 7;
    }

    @Override
    public void func_73866_w_() {
        PlayerData playerData;
        super.func_73866_w_();
        GuiMenuTopButton tabAbilities = new GuiMenuTopButton(-200, this.guiLeft + this.xSize - 10, this.guiTop - 17, "gui.abilities");
        tabAbilities.active = this.subTab == 0;
        this.addTopButton(tabAbilities);
        GuiMenuTopButton tabToggles = new GuiMenuTopButton(-201, "gui.toggles", tabAbilities);
        tabToggles.active = this.subTab == 1;
        this.addTopButton(tabToggles);
        this.buildFilteredList();
        this.updateHotbarIcons();
        int totalRows = (this.filteredKeys.size() + this.gridCols - 1) / this.gridCols;
        int visibleRows = this.getGridVisibleRows();
        if (totalRows > visibleRows) {
            int scrollBtnY = this.guiTop + this.selBottom + 1;
            int btnW = 14;
            int btnH = 12;
            this.addButton(new GuiNpcButton(-202, this.guiLeft + this.selRight - btnW * 2 - 2, scrollBtnY, btnW, btnH, "<"));
            this.addButton(new GuiNpcButton(-203, this.guiLeft + this.selRight - btnW, scrollBtnY, btnW, btnH, ">"));
            this.getButton(-202).setEnabled(this.scrollRow > 0);
            this.getButton(-203).setEnabled(this.scrollRow < this.getMaxScrollRow());
        }
        if (this.initialSelectedKey == null && (playerData = ClientCacheHandler.playerData) != null && playerData.abilityData != null) {
            this.initialSelectedKey = playerData.abilityData.getSelectedAbilityKey();
        }
    }

    private void buildFilteredList() {
        this.filteredKeys.clear();
        this.filteredDisplayNames.clear();
        this.filteredIcons.clear();
        this.filteredAbilities.clear();
        this.filteredActions.clear();
        this.cachedDetailIcon = null;
        this.cachedDetailIndex = -1;
        final PlayerData playerData = ClientCacheHandler.playerData;
        if (playerData == null || playerData.abilityData == null) {
            return;
        }
        List<String> abilities = playerData.abilityData.getUnlockedAbilityList();
        final ArrayList<String> tempKeys = new ArrayList<String>();
        final ArrayList<String> tempNames = new ArrayList<String>();
        ArrayList<AbilityIcon> tempIcons = new ArrayList<AbilityIcon>();
        final ArrayList<Ability> tempAbilities = new ArrayList<Ability>();
        ArrayList<Ability> tempActions = new ArrayList<Ability>();
        for (String key : abilities) {
            boolean isToggle;
            Ability ability = null;
            noppes.npcs.api.ability.IAbilityAction action = null;
            if (key.startsWith("chain:")) {
                String chainKey = key.substring("chain:".length());
                if (AbilityController.Instance != null) {
                    ChainedAbility chain = AbilityController.Instance.resolveChainedAbility(chainKey);
                    action = chain;
                }
            } else if (AbilityController.Instance != null) {
                action = ability = AbilityController.Instance.resolveAbility(key);
            }
            boolean bl = isToggle = ability != null && ability.isToggleable();
            if (this.subTab == 0 && isToggle || this.subTab == 1 && !isToggle) continue;
            String displayName = action instanceof ChainedAbility ? "\u00a76\u2726 " + ((ChainedAbility)action).getDisplayName() : (ability != null ? ability.getDisplayName() : key);
            tempKeys.add(key);
            tempNames.add(displayName);
            tempIcons.add(AbilityIcon.fromAction(action));
            tempAbilities.add(ability);
            tempActions.add((Ability)action);
        }
        ArrayList<Integer> indices = new ArrayList<Integer>();
        for (int i = 0; i < tempNames.size(); ++i) {
            indices.add(i);
        }
        final boolean sortTogglesFirst = this.subTab == 1;
        Collections.sort(indices, new Comparator<Integer>(){

            @Override
            public int compare(Integer a, Integer b) {
                int typeCompare;
                String typeB;
                boolean bToggled;
                boolean aToggled;
                if (sortTogglesFirst && playerData != null && playerData.abilityData != null && (aToggled = playerData.abilityData.isAbilityToggled((String)tempKeys.get(a))) != (bToggled = playerData.abilityData.isAbilityToggled((String)tempKeys.get(b)))) {
                    return aToggled ? -1 : 1;
                }
                Ability abilityA = (Ability)tempAbilities.get(a);
                Ability abilityB = (Ability)tempAbilities.get(b);
                String typeA = abilityA != null ? abilityA.getTypeId() : "";
                String string = typeB = abilityB != null ? abilityB.getTypeId() : "";
                if (typeA == null) {
                    typeA = "";
                }
                if (typeB == null) {
                    typeB = "";
                }
                if ((typeCompare = typeA.compareToIgnoreCase(typeB)) != 0) {
                    return typeCompare;
                }
                return GuiAbilities.this.stripFormatting((String)tempNames.get(a)).compareToIgnoreCase(GuiAbilities.this.stripFormatting((String)tempNames.get(b)));
            }
        });
        Iterator iterator = indices.iterator();
        while (iterator.hasNext()) {
            int idx = (Integer)iterator.next();
            this.filteredKeys.add((String)tempKeys.get(idx));
            this.filteredDisplayNames.add((String)tempNames.get(idx));
            this.filteredIcons.add((AbilityIcon)((Object)tempIcons.get(idx)));
            this.filteredAbilities.add((Ability)tempAbilities.get(idx));
            this.filteredActions.add((IAbilityAction)tempActions.get(idx));
        }
        this.gridHoverScale = new float[this.filteredKeys.size()];
        Arrays.fill(this.gridHoverScale, 1.0f);
        this.scrollRow = 0;
        this.selectedIndex = -1;
    }

    private String stripFormatting(String s) {
        return s.replaceAll("\u00a7.", "").trim();
    }

    private void updateHotbarIcons() {
        PlayerData playerData = ClientCacheHandler.playerData;
        for (int i = 0; i < this.hbSlots; ++i) {
            AbilityHotbarData slotData;
            this.hotbarIcons[i] = null;
            if (playerData == null || playerData.hotbarData == null || (slotData = playerData.hotbarData.getSlot(i)) == null || slotData.isEmpty() || AbilityController.Instance == null) continue;
            if (slotData.isChainKey()) {
                ChainedAbility chain = AbilityController.Instance.resolveChainedAbility(slotData.getResolveKey());
                if (chain == null) continue;
                this.hotbarIcons[i] = AbilityIcon.fromChainedAbility(chain);
                continue;
            }
            Ability ability = AbilityController.Instance.resolveAbility(slotData.abilityKey);
            if (ability == null) continue;
            this.hotbarIcons[i] = AbilityIcon.fromAbility(ability);
        }
    }

    private int getGridVisibleRows() {
        int usableHeight = this.selBottom - this.selTop - this.gridPad * 2;
        return usableHeight / this.gridCellSize;
    }

    private int getGridStartX() {
        int panelWidth = this.selRight - this.selLeft;
        int usableWidth = panelWidth - this.gridPad * 2;
        int gridWidth = this.gridCols * this.gridCellSize;
        int offsetX = (usableWidth - gridWidth) / 2;
        return this.guiLeft + this.selLeft + this.gridPad + offsetX;
    }

    private int getGridStartY() {
        int panelHeight = this.selBottom - this.selTop;
        int usableHeight = panelHeight - this.gridPad * 2;
        int visibleRows = this.getGridVisibleRows();
        int gridHeight = visibleRows * this.gridCellSize;
        int offsetY = (usableHeight - gridHeight) / 2;
        return this.guiTop + this.selTop + this.gridPad + offsetY;
    }

    private int getMaxScrollRow() {
        int totalRows = (this.filteredKeys.size() + this.gridCols - 1) / this.gridCols;
        return Math.max(0, totalRows - this.getGridVisibleRows());
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        this.func_146276_q_();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(this.resource);
        this.func_73729_b(this.guiLeft, this.guiTop, 0, 0, 252, 195);
        this.func_73729_b(this.guiLeft + 252, this.guiTop, 188, 0, 67, 195);
        super.func_73863_a(mouseX, mouseY, partialTicks);
        this.updateHoverState(mouseX, mouseY);
        this.updateHoverScales();
        this.func_73733_a(this.guiLeft + this.selLeft, this.guiTop + this.selTop, this.guiLeft + this.selRight, this.guiTop + this.selBottom, -1072689136, -1072689136);
        this.func_73733_a(this.guiLeft + this.infoLeft, this.guiTop + this.infoTop, this.guiLeft + this.infoRight, this.guiTop + this.infoBottom, -1072689136, -1072689136);
        this.func_73733_a(this.guiLeft + this.hbLeft, this.guiTop + this.hbTop, this.guiLeft + this.hbRight, this.guiTop + this.hbBottom, -1072689136, -1072689136);
        this.drawIconGrid(mouseX, mouseY);
        this.drawInfoPanel();
        this.drawHotbarSection(mouseX, mouseY);
        if (this.isDragging && this.draggedIcon != null) {
            this.drawDragGhost(mouseX, mouseY);
        }
        this.drawTooltip(mouseX, mouseY);
    }

    private void drawIconGrid(int mouseX, int mouseY) {
        int absIndex;
        int visibleRows = this.getGridVisibleRows();
        int gridStartX = this.getGridStartX();
        int gridStartY = this.getGridStartY();
        int startIndex = this.scrollRow * this.gridCols;
        ScaledResolution sr = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        int scaleFactor = sr.func_78325_e();
        int scissorX = (this.guiLeft + this.selLeft) * scaleFactor;
        int scissorY = this.field_146297_k.field_71440_d - (this.guiTop + this.selBottom) * scaleFactor;
        int scissorW = (this.selRight - this.selLeft) * scaleFactor;
        int scissorH = (this.selBottom - this.selTop) * scaleFactor;
        GL11.glEnable((int)3089);
        GL11.glScissor((int)scissorX, (int)scissorY, (int)scissorW, (int)scissorH);
        int maxVisibleCount = this.gridCols * visibleRows;
        for (int vi = 0; vi < maxVisibleCount && (absIndex = startIndex + vi) < this.filteredKeys.size(); ++vi) {
            int col = vi % this.gridCols;
            int row = vi / this.gridCols;
            int cellX = gridStartX + col * this.gridCellSize;
            int cellY = gridStartY + row * this.gridCellSize;
            boolean isHovered = this.hoveredGridIndex == absIndex && !this.isDragging;
            boolean isSelected = this.selectedIndex == absIndex;
            boolean isDragSource = this.isDragging && this.dragSourceSlot == -1 && this.draggedKey != null && this.draggedKey.equals(this.filteredKeys.get(absIndex));
            int cellToggleState = 0;
            Ability cellAbility = this.filteredAbilities.get(absIndex);
            PlayerData cellPlayerData = ClientCacheHandler.playerData;
            if (cellAbility != null && cellAbility.isToggleable() && cellPlayerData != null && cellPlayerData.abilityData != null) {
                cellToggleState = cellPlayerData.abilityData.getToggleState(this.filteredKeys.get(absIndex));
            }
            int bgColor = isDragSource ? 0x40333333 : (isSelected ? 1615876208 : (isHovered ? 0x60404060 : 1075847216));
            GuiAbilities.func_73734_a((int)cellX, (int)cellY, (int)(cellX + this.gridCellSize - 1), (int)(cellY + this.gridCellSize - 1), (int)bgColor);
            int borderColor = cellToggleState > 0 ? 1999887701 : (isSelected ? -1432774179 : (isHovered ? -1433892660 : 0x40404060));
            this.func_73730_a(cellX, cellX + this.gridCellSize - 2, cellY, borderColor);
            this.func_73730_a(cellX, cellX + this.gridCellSize - 2, cellY + this.gridCellSize - 2, borderColor);
            this.func_73728_b(cellX, cellY, cellY + this.gridCellSize - 2, borderColor);
            this.func_73728_b(cellX + this.gridCellSize - 2, cellY, cellY + this.gridCellSize - 2, borderColor);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            AbilityIcon icon = this.filteredIcons.get(absIndex);
            if (icon == null) continue;
            float hoverScale = this.gridHoverScale[absIndex];
            float targetPixels = (float)(this.gridCellSize - 4) * hoverScale;
            float drawSize = icon.getDrawSize();
            float iconScale = targetPixels / drawSize;
            int iconCenterX = cellX + (this.gridCellSize - 1) / 2;
            int iconCenterY = cellY + (this.gridCellSize - 1) / 2;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)iconCenterX, (float)iconCenterY, (float)0.0f);
            GL11.glScalef((float)iconScale, (float)iconScale, (float)1.0f);
            float alpha = isDragSource ? 0.3f : 1.0f;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            icon.draw(cellToggleState);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)3089);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3553);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void drawInfoPanel() {
        int infoIndex;
        int n = infoIndex = this.hoveredGridIndex >= 0 ? this.hoveredGridIndex : this.selectedIndex;
        if (infoIndex < 0 || infoIndex >= this.filteredKeys.size()) {
            return;
        }
        if (infoIndex != this.lastInfoIndex) {
            this.infoScrollOffset = 0;
            this.lastInfoIndex = infoIndex;
        }
        FontRenderer fr = this.field_146297_k.field_71466_p;
        int panelInnerLeft = this.guiLeft + this.infoLeft + this.infoPad;
        int panelInnerTop = this.guiTop + this.infoTop + this.infoPad;
        int panelInnerWidth = this.infoRight - this.infoLeft - this.infoPad * 2;
        int panelHeight = this.infoBottom - this.infoTop;
        if (this.cachedDetailIndex != infoIndex) {
            this.cachedDetailIcon = this.filteredIcons.get(infoIndex);
            this.cachedDetailIndex = infoIndex;
        }
        Ability ability = this.filteredAbilities.get(infoIndex);
        int toggleState = 0;
        PlayerData togglePlayerData = ClientCacheHandler.playerData;
        if (ability != null && ability.isToggleable() && togglePlayerData != null && togglePlayerData.abilityData != null) {
            toggleState = togglePlayerData.abilityData.getToggleState(this.filteredKeys.get(infoIndex));
        }
        ScaledResolution sr = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        int scaleFactor = sr.func_78325_e();
        int scissorX = (this.guiLeft + this.infoLeft) * scaleFactor;
        int scissorY = this.field_146297_k.field_71440_d - (this.guiTop + this.infoBottom) * scaleFactor;
        int scissorW = (this.infoRight - this.infoLeft) * scaleFactor;
        int scissorH = panelHeight * scaleFactor;
        GL11.glEnable((int)3089);
        GL11.glScissor((int)scissorX, (int)scissorY, (int)scissorW, (int)scissorH);
        int y = panelInnerTop - this.infoScrollOffset;
        if (this.cachedDetailIcon != null) {
            float drawSize = this.cachedDetailIcon.getDrawSize();
            float iconScale = (float)this.infoIconSize / drawSize;
            int iconCX = panelInnerLeft + panelInnerWidth / 2;
            int iconCY = y + this.infoIconSize / 2 + 4;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)iconCX, (float)iconCY, (float)0.0f);
            GL11.glScalef((float)iconScale, (float)iconScale, (float)1.0f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.cachedDetailIcon.draw(toggleState);
            GL11.glPopMatrix();
            GL11.glDisable((int)3042);
            GL11.glEnable((int)3553);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            y += this.infoIconSize + 12;
        }
        String name = this.filteredDisplayNames.get(infoIndex);
        List nameLines = fr.func_78271_c(name, panelInnerWidth);
        for (String line : nameLines) {
            int lineX = panelInnerLeft + (panelInnerWidth - fr.func_78256_a(line)) / 2;
            fr.func_78261_a(line, lineX, y, 0xFFFFFF);
            y += fr.field_78288_b + 1;
        }
        y += 4;
        if (ability != null) {
            int textW;
            float baseHealth;
            float baseDamage;
            int textW2;
            String translated;
            String typeId = ability.getTypeId();
            if (typeId != null && !typeId.isEmpty() && !(translated = StatCollector.func_74838_a((String)typeId)).equals(typeId)) {
                textW2 = fr.func_78256_a(translated);
                fr.func_78261_a("\u00a77" + translated, panelInnerLeft + (panelInnerWidth - textW2) / 2, y, 0xAAAAAA);
                y += fr.field_78288_b + 3;
            }
            if (ability.isToggleable()) {
                String toggleText = "[" + StatCollector.func_74838_a((String)"gui.toggle") + "]";
                textW2 = fr.func_78256_a(toggleText);
                fr.func_78261_a("\u00a7a" + toggleText, panelInnerLeft + (panelInnerWidth - textW2) / 2, y, 0x55FF55);
                y += fr.field_78288_b + 3;
            }
            if (ability.isToggleable()) {
                String prefix;
                String stateText;
                if (toggleState > 0) {
                    String stateLabel = ability.getToggleStateLabel(toggleState);
                    stateText = stateLabel != null ? stateLabel : StatCollector.func_74838_a((String)"gui.toggle.active");
                    prefix = "\u00a72";
                } else {
                    stateText = StatCollector.func_74838_a((String)"gui.toggle.inactive");
                    prefix = "\u00a78";
                }
                int textW3 = fr.func_78256_a(stateText);
                fr.func_78261_a(prefix + stateText, panelInnerLeft + (panelInnerWidth - textW3) / 2, y, toggleState > 0 ? 0x55FF55 : 0x888888);
                y += fr.field_78288_b + 3;
            }
            if (ability.getCooldownTicks() > 0) {
                float seconds = (float)ability.getCooldownTicks() / 20.0f;
                String cdText = StatCollector.func_74838_a((String)"ability.cooldown") + ": " + String.format("%.1f", Float.valueOf(seconds)) + "s";
                int textW22 = fr.func_78256_a(cdText);
                boolean perAbility = ability.isPerAbilityCooldown();
                String cdColor = perAbility ? "\u00a7e" : "\u00a77";
                int cdColorInt = perAbility ? 0xFFFF55 : 0xAAAAAA;
                fr.func_78261_a(cdColor + cdText, panelInnerLeft + (panelInnerWidth - textW22) / 2, y, cdColorInt);
                y += fr.field_78288_b + 3;
                if (perAbility) {
                    String perText = "[" + StatCollector.func_74838_a((String)"ability.perAbilityCooldown") + "]";
                    int ptW = fr.func_78256_a(perText);
                    fr.func_78261_a("\u00a7e" + perText, panelInnerLeft + (panelInnerWidth - ptW) / 2, y, 0xFFFF55);
                    y += fr.field_78288_b + 3;
                }
            }
            if ((baseDamage = ability.getDisplayDamage()) > 0.0f) {
                float displayDamage = baseDamage;
                if (AbilityController.Instance != null && this.field_146297_k.field_71439_g != null) {
                    displayDamage = AbilityController.Instance.fireModifyProjectileDamage(ability, (EntityLivingBase)this.field_146297_k.field_71439_g, baseDamage);
                }
                String label = ability.isDisplayDamageDPS() ? StatCollector.func_74838_a((String)"gui.dps") : StatCollector.func_74838_a((String)"ability.preview.damage");
                String dmgText = label + ": " + String.format("%.1f", Float.valueOf(displayDamage));
                int textW4 = fr.func_78256_a(dmgText);
                fr.func_78261_a("\u00a7c" + dmgText, panelInnerLeft + (panelInnerWidth - textW4) / 2, y, 0xFF5555);
                y += fr.field_78288_b + 3;
            }
            if ((baseHealth = ability.getDisplayBarrierHealth()) > 0.0f) {
                float displayHealth = baseHealth;
                if (AbilityController.Instance != null && this.field_146297_k.field_71439_g != null) {
                    displayHealth = AbilityController.Instance.fireModifyBarrierHealth(ability, (EntityLivingBase)this.field_146297_k.field_71439_g, baseHealth);
                }
                String healthText = StatCollector.func_74838_a((String)"ability.preview.health") + ": " + String.format("%.0f", Float.valueOf(displayHealth));
                int textW5 = fr.func_78256_a(healthText);
                fr.func_78261_a("\u00a7a" + healthText, panelInnerLeft + (panelInnerWidth - textW5) / 2, y, 0x55FF55);
                y += fr.field_78288_b + 3;
            }
            if (ability.isDisplayReflect()) {
                String reflectText = StatCollector.func_74838_a((String)"ability.reflect") + ": " + String.format("%.0f%%", Float.valueOf(ability.getDisplayReflectStrength()));
                textW = fr.func_78256_a(reflectText);
                fr.func_78261_a("\u00a7d" + reflectText, panelInnerLeft + (panelInnerWidth - textW) / 2, y, 0xFF55FF);
                y += fr.field_78288_b + 3;
            }
            if (ability.isDisplayAbsorbing()) {
                String absorbText = "[" + StatCollector.func_74838_a((String)"ability.absorbing") + "]";
                textW = fr.func_78256_a(absorbText);
                fr.func_78261_a("\u00a7b" + absorbText, panelInnerLeft + (panelInnerWidth - textW) / 2, y, 0x55FFFF);
                y += fr.field_78288_b + 3;
            }
            if (ability.hasMagic()) {
                y += 2;
                MagicData magicData = ability.getMagicData();
                if (magicData.isEmpty()) {
                    String magicText = StatCollector.func_74838_a((String)"ability.preview.magic.casters");
                    int textW6 = fr.func_78256_a(magicText);
                    fr.func_78261_a("\u00a79" + magicText, panelInnerLeft + (panelInnerWidth - textW6) / 2, y, 0x5555FF);
                    y += fr.field_78288_b + 3;
                } else {
                    for (Map.Entry<Integer, MagicEntry> entry : magicData.getMagics().entrySet()) {
                        Magic magic = MagicController.getInstance() != null ? MagicController.getInstance().getMagic(entry.getKey()) : null;
                        String magicName = magic != null ? magic.getDisplayName() : "Magic #" + entry.getKey();
                        String splitText = magicName + ": " + Math.round(entry.getValue().split * 100.0f) + "%";
                        int textW7 = fr.func_78256_a(splitText);
                        fr.func_78261_a("\u00a79" + splitText, panelInnerLeft + (panelInnerWidth - textW7) / 2, y, 0x5555FF);
                        y += fr.field_78288_b + 1;
                    }
                    y += 2;
                }
            }
        }
        this.infoContentHeight = y + this.infoScrollOffset - panelInnerTop + this.infoPad * 2;
        GL11.glDisable((int)3089);
        if (this.infoContentHeight > panelHeight) {
            int maxScroll = Math.max(1, this.infoContentHeight - panelHeight);
            if (this.infoScrollOffset > maxScroll) {
                this.infoScrollOffset = maxScroll;
            }
            float scrollPercent = (float)this.infoScrollOffset / (float)maxScroll;
            int barHeight = panelHeight - 4;
            int thumbHeight = Math.max(8, barHeight * panelHeight / this.infoContentHeight);
            int thumbY = this.guiTop + this.infoTop + 2 + (int)((float)(barHeight - thumbHeight) * scrollPercent);
            int barX = this.guiLeft + this.infoRight - 3;
            GuiAbilities.func_73734_a((int)barX, (int)(this.guiTop + this.infoTop + 2), (int)(barX + 2), (int)(this.guiTop + this.infoTop + 2 + barHeight), (int)0x40FFFFFF);
            GuiAbilities.func_73734_a((int)barX, (int)thumbY, (int)(barX + 2), (int)(thumbY + thumbHeight), (int)-1593835521);
        }
    }

    private void drawHotbarSection(int mouseX, int mouseY) {
        FontRenderer fr = this.field_146297_k.field_71466_p;
        PlayerData selPlayerData = ClientCacheHandler.playerData;
        String selectedKey = null;
        if (selPlayerData != null && selPlayerData.abilityData != null) {
            selectedKey = selPlayerData.abilityData.getSelectedAbilityKey();
        }
        int panelWidth = this.hbRight - this.hbLeft;
        int panelHeight = this.hbBottom - this.hbTop;
        int totalSlotsWidth = this.hbSlots * (this.hbSlotSize + this.hbSlotGap) - this.hbSlotGap;
        int hotbarStartX = this.guiLeft + this.hbLeft + (panelWidth - totalSlotsWidth) / 2;
        int hotbarStartY = this.guiTop + this.hbTop + (panelHeight - this.hbSlotSize) / 2;
        for (int i = 0; i < this.hbSlots; ++i) {
            boolean isSourceSlot;
            AbilityHotbarData slotData;
            int sx = hotbarStartX + i * (this.hbSlotSize + this.hbSlotGap);
            int sy = hotbarStartY;
            boolean isHovered = this.hoveredSlotIndex == i;
            boolean isDragHover = this.isDragging && isHovered;
            boolean isSelectedSlot = false;
            if (selectedKey != null && !selectedKey.isEmpty() && selPlayerData.hotbarData != null && (slotData = selPlayerData.hotbarData.getSlot(i)) != null && !slotData.isEmpty() && selectedKey.equals(slotData.abilityKey)) {
                isSelectedSlot = true;
            }
            int bgColor = isDragHover ? -2141167568 : (isSelectedSlot ? 1615876128 : (isHovered ? 0x60505050 : 0x50303030));
            GuiAbilities.func_73734_a((int)sx, (int)sy, (int)(sx + this.hbSlotSize), (int)(sy + this.hbSlotSize), (int)bgColor);
            int borderColor = isDragHover ? -3355580 : (isSelectedSlot ? -3355648 : (isHovered ? -1433892728 : 0x60505050));
            this.func_73730_a(sx, sx + this.hbSlotSize - 1, sy, borderColor);
            this.func_73730_a(sx, sx + this.hbSlotSize - 1, sy + this.hbSlotSize - 1, borderColor);
            this.func_73728_b(sx, sy, sy + this.hbSlotSize - 1, borderColor);
            this.func_73728_b(sx + this.hbSlotSize - 1, sy, sy + this.hbSlotSize - 1, borderColor);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            AbilityIcon icon = this.hotbarIcons[i];
            boolean bl = isSourceSlot = this.isDragging && this.dragSourceSlot == i;
            if (icon != null && !isSourceSlot) {
                AbilityHotbarData slotData2;
                int iconCX = sx + this.hbSlotSize / 2;
                int iconCY = sy + this.hbSlotSize / 2;
                float targetPixels = this.hbSlotSize - 4;
                float drawSize = icon.getDrawSize();
                float s = targetPixels / drawSize;
                int hbToggleState = 0;
                if (selPlayerData != null && selPlayerData.abilityData != null && selPlayerData.hotbarData != null && (slotData2 = selPlayerData.hotbarData.getSlot(i)) != null && !slotData2.isEmpty()) {
                    hbToggleState = selPlayerData.abilityData.getToggleState(slotData2.abilityKey);
                }
                GL11.glPushMatrix();
                GL11.glTranslatef((float)iconCX, (float)iconCY, (float)0.0f);
                GL11.glScalef((float)s, (float)s, (float)1.0f);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                icon.draw(hbToggleState);
                GL11.glPopMatrix();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                continue;
            }
            String slotLabel = String.valueOf(i + 1);
            int labelColor = isSourceSlot ? 0x333333 : 0x666666;
            int lx = sx + (this.hbSlotSize - fr.func_78256_a(slotLabel)) / 2;
            int ly = sy + (this.hbSlotSize - fr.field_78288_b) / 2 + 1;
            fr.func_78276_b(slotLabel, lx, ly, labelColor);
        }
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3553);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void drawDragGhost(int mouseX, int mouseY) {
        if (this.draggedIcon == null) {
            return;
        }
        float targetPixels = this.gridCellSize;
        float drawSize = this.draggedIcon.getDrawSize();
        float ghostScale = targetPixels / drawSize;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)mouseX, (float)mouseY, (float)100.0f);
        GL11.glScalef((float)ghostScale, (float)ghostScale, (float)1.0f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.8f);
        this.draggedIcon.draw(0);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void drawTooltip(int mouseX, int mouseY) {
        String name;
        AbilityHotbarData slotData;
        PlayerData playerData;
        if (this.isDragging) {
            return;
        }
        if (this.hoveredGridIndex >= 0 && this.hoveredGridIndex < this.filteredDisplayNames.size()) {
            ArrayList<String> lines = new ArrayList<String>();
            lines.add(this.filteredDisplayNames.get(this.hoveredGridIndex));
            Ability ability = this.filteredAbilities.get(this.hoveredGridIndex);
            if (ability != null) {
                String translated;
                String typeId = ability.getTypeId();
                if (typeId != null && !typeId.isEmpty() && !(translated = StatCollector.func_74838_a((String)typeId)).equals(typeId)) {
                    lines.add("\u00a77" + translated);
                }
                if (ability.isToggleable()) {
                    lines.add("\u00a7a[" + StatCollector.func_74838_a((String)"gui.toggle") + "]");
                }
                if (ability.getCooldownTicks() > 0) {
                    float seconds = (float)ability.getCooldownTicks() / 20.0f;
                    lines.add("\u00a77" + StatCollector.func_74838_a((String)"ability.cooldown") + ": " + String.format("%.1f", Float.valueOf(seconds)) + "s");
                }
            }
            this.drawHoveringText(lines, mouseX, mouseY, this.field_146297_k.field_71466_p);
            GL11.glDisable((int)2896);
        }
        if (this.hoveredSlotIndex >= 0 && this.hoveredSlotIndex < this.hbSlots && (playerData = ClientCacheHandler.playerData) != null && playerData.hotbarData != null && (slotData = playerData.hotbarData.getSlot(this.hoveredSlotIndex)) != null && !slotData.isEmpty() && (name = this.getHotbarSlotDisplayName(slotData)) != null) {
            ArrayList<String> lines = new ArrayList<String>();
            lines.add(name);
            lines.add("\u00a78Right-click to remove");
            this.drawHoveringText(lines, mouseX, mouseY, this.field_146297_k.field_71466_p);
            GL11.glDisable((int)2896);
        }
    }

    private String getHotbarSlotDisplayName(AbilityHotbarData slotData) {
        if (AbilityController.Instance == null) {
            return null;
        }
        if (slotData.isChainKey()) {
            ChainedAbility chain = AbilityController.Instance.resolveChainedAbility(slotData.getResolveKey());
            return chain != null ? chain.getDisplayName() : null;
        }
        Ability ability = AbilityController.Instance.resolveAbility(slotData.abilityKey);
        return ability != null ? ability.getDisplayName() : null;
    }

    private void updateHoverState(int mouseX, int mouseY) {
        this.hoveredGridIndex = -1;
        this.hoveredSlotIndex = -1;
        int visibleRows = this.getGridVisibleRows();
        int gridStartX = this.getGridStartX();
        int gridStartY = this.getGridStartY();
        int startIndex = this.scrollRow * this.gridCols;
        if (mouseX >= this.guiLeft + this.selLeft && mouseX < this.guiLeft + this.selRight && mouseY >= this.guiTop + this.selTop && mouseY < this.guiTop + this.selBottom) {
            int absIndex;
            for (int vi = 0; vi < this.gridCols * visibleRows && (absIndex = startIndex + vi) < this.filteredKeys.size(); ++vi) {
                int col = vi % this.gridCols;
                int row = vi / this.gridCols;
                int cellX = gridStartX + col * this.gridCellSize;
                int cellY = gridStartY + row * this.gridCellSize;
                if (mouseX < cellX || mouseX >= cellX + this.gridCellSize - 1 || mouseY < cellY || mouseY >= cellY + this.gridCellSize - 1) continue;
                this.hoveredGridIndex = absIndex;
                break;
            }
        }
        int panelWidth = this.hbRight - this.hbLeft;
        int panelHeight = this.hbBottom - this.hbTop;
        int totalSlotsWidth = this.hbSlots * (this.hbSlotSize + this.hbSlotGap) - this.hbSlotGap;
        int hotbarStartX = this.guiLeft + this.hbLeft + (panelWidth - totalSlotsWidth) / 2;
        int hotbarStartY = this.guiTop + this.hbTop + (panelHeight - this.hbSlotSize) / 2;
        for (int i = 0; i < this.hbSlots; ++i) {
            int sx = hotbarStartX + i * (this.hbSlotSize + this.hbSlotGap);
            if (mouseX < sx || mouseX >= sx + this.hbSlotSize || mouseY < hotbarStartY || mouseY >= hotbarStartY + this.hbSlotSize) continue;
            this.hoveredSlotIndex = i;
            break;
        }
    }

    private void updateHoverScales() {
        if (this.gridHoverScale == null) {
            return;
        }
        for (int i = 0; i < this.gridHoverScale.length; ++i) {
            float target = i == this.hoveredGridIndex && !this.isDragging ? 1.12f : 1.0f;
            int n = i;
            this.gridHoverScale[n] = this.gridHoverScale[n] + (target - this.gridHoverScale[i]) * 0.3f;
            if (!(Math.abs(this.gridHoverScale[i] - target) < 0.005f)) continue;
            this.gridHoverScale[i] = target;
        }
    }

    @Override
    public void func_73864_a(int mouseX, int mouseY, int button) {
        if (this.hasSubGui()) {
            super.func_73864_a(mouseX, mouseY, button);
            return;
        }
        if (button == 1 && this.hoveredSlotIndex >= 0) {
            this.saveHotbarSlot(this.hoveredSlotIndex, "");
            this.updateHotbarIcons();
            return;
        }
        if (button == 0) {
            AbilityHotbarData slotData;
            PlayerData playerData;
            if (this.hoveredGridIndex >= 0 && this.hoveredGridIndex < this.filteredKeys.size()) {
                if (this.subTab == 1) {
                    long now = System.currentTimeMillis();
                    if (this.lastToggleClickIndex == this.hoveredGridIndex && now - this.lastToggleClickTime < 400L) {
                        String key = this.filteredKeys.get(this.hoveredGridIndex);
                        Ability ability = this.filteredAbilities.get(this.hoveredGridIndex);
                        if (ability != null && ability.isToggleable()) {
                            PacketHandler.Instance.sendToServer(new AbilityTogglePacket(key));
                            this.lastToggleClickIndex = -1;
                            this.lastToggleClickTime = 0L;
                            this.selectedIndex = this.hoveredGridIndex;
                            this.cachedDetailIcon = null;
                            this.cachedDetailIndex = -1;
                            return;
                        }
                    }
                    this.lastToggleClickIndex = this.hoveredGridIndex;
                    this.lastToggleClickTime = now;
                }
                this.selectedIndex = this.hoveredGridIndex;
                this.cachedDetailIcon = null;
                this.cachedDetailIndex = -1;
                this.dragPending = true;
                this.draggedKey = this.filteredKeys.get(this.hoveredGridIndex);
                this.draggedIcon = this.filteredIcons.get(this.hoveredGridIndex);
                this.dragSourceSlot = -1;
                this.dragStartX = mouseX;
                this.dragStartY = mouseY;
                return;
            }
            if (this.hoveredSlotIndex >= 0 && this.hotbarIcons[this.hoveredSlotIndex] != null && (playerData = ClientCacheHandler.playerData) != null && playerData.hotbarData != null && (slotData = playerData.hotbarData.getSlot(this.hoveredSlotIndex)) != null && !slotData.isEmpty()) {
                this.dragPending = true;
                this.draggedKey = slotData.abilityKey;
                this.draggedIcon = this.hotbarIcons[this.hoveredSlotIndex];
                this.dragSourceSlot = this.hoveredSlotIndex;
                this.dragStartX = mouseX;
                this.dragStartY = mouseY;
                return;
            }
        }
        super.func_73864_a(mouseX, mouseY, button);
    }

    @Override
    public void func_146273_a(int mouseX, int mouseY, int button, long timeSinceLastClick) {
        int dy;
        int dx;
        if (this.dragPending && !this.isDragging && (dx = mouseX - this.dragStartX) * dx + (dy = mouseY - this.dragStartY) * dy > this.dragThreshold * this.dragThreshold) {
            this.isDragging = true;
        }
        if (this.isDragging) {
            this.dragMouseX = mouseX;
            this.dragMouseY = mouseY;
        }
        super.func_146273_a(mouseX, mouseY, button, timeSinceLastClick);
    }

    @Override
    protected void func_146286_b(int mouseX, int mouseY, int state) {
        if (this.isDragging && this.draggedKey != null) {
            this.updateHoverState(mouseX, mouseY);
            if (this.hoveredSlotIndex >= 0) {
                if (this.dragSourceSlot >= 0) {
                    this.swapHotbarSlots(this.dragSourceSlot, this.hoveredSlotIndex);
                } else {
                    this.saveHotbarSlot(this.hoveredSlotIndex, this.draggedKey);
                }
                this.updateHotbarIcons();
            } else if (this.dragSourceSlot >= 0 && this.hoveredGridIndex >= 0) {
                this.saveHotbarSlot(this.dragSourceSlot, "");
                this.updateHotbarIcons();
            }
        }
        this.isDragging = false;
        this.dragPending = false;
        this.draggedKey = null;
        this.draggedIcon = null;
        this.dragSourceSlot = -1;
        super.func_146286_b(mouseX, mouseY, state);
    }

    @Override
    public void func_146274_d() {
        super.func_146274_d();
        int wheel = Mouse.getEventDWheel();
        if (wheel != 0) {
            int mouseX = Mouse.getEventX() * this.field_146294_l / this.field_146297_k.field_71443_c;
            int mouseY = this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.field_71440_d - 1;
            if (mouseX >= this.guiLeft + this.infoLeft && mouseX < this.guiLeft + this.infoRight && mouseY >= this.guiTop + this.infoTop && mouseY < this.guiTop + this.infoBottom) {
                int panelHeight = this.infoBottom - this.infoTop;
                int maxInfoScroll = Math.max(0, this.infoContentHeight - panelHeight);
                this.infoScrollOffset = wheel > 0 ? Math.max(0, this.infoScrollOffset - 10) : Math.min(maxInfoScroll, this.infoScrollOffset + 10);
            } else {
                int maxScroll = this.getMaxScrollRow();
                this.scrollRow = wheel > 0 ? Math.max(0, this.scrollRow - 1) : Math.min(maxScroll, this.scrollRow + 1);
            }
        }
    }

    @Override
    protected void func_146284_a(GuiButton btn) {
        if (btn instanceof AbstractTab) {
            return;
        }
        if (btn.field_146127_k <= -100 && btn.field_146127_k > -200) {
            super.func_146284_a(btn);
            return;
        }
        if (btn.field_146127_k == -200) {
            this.subTab = 0;
            this.func_73866_w_();
            return;
        }
        if (btn.field_146127_k == -201) {
            this.subTab = 1;
            this.func_73866_w_();
            return;
        }
        if (btn.field_146127_k == -202) {
            this.scrollRow = Math.max(0, this.scrollRow - 1);
            this.func_73866_w_();
            return;
        }
        if (btn.field_146127_k == -203) {
            this.scrollRow = Math.min(this.getMaxScrollRow(), this.scrollRow + 1);
            this.func_73866_w_();
            return;
        }
    }

    private void saveHotbarSlot(int slotIndex, String abilityKey) {
        AbilityHotbarData slot;
        PlayerData playerData = ClientCacheHandler.playerData;
        if (playerData != null && playerData.hotbarData != null && (slot = playerData.hotbarData.getSlot(slotIndex)) != null) {
            slot.abilityKey = abilityKey;
        }
        NBTTagCompound compound = new NBTTagCompound();
        NBTTagCompound slotTag = new NBTTagCompound();
        slotTag.func_74768_a("slot", slotIndex);
        slotTag.func_74778_a("abilityKey", abilityKey != null ? abilityKey : "");
        compound.func_74782_a("AbilityHotbar" + slotIndex, (NBTBase)slotTag);
        PacketHandler.Instance.sendToServer(new AbilityHotbarSavePacket(slotIndex, compound));
    }

    private void swapHotbarSlots(int fromSlot, int toSlot) {
        if (fromSlot == toSlot) {
            return;
        }
        PlayerData playerData = ClientCacheHandler.playerData;
        if (playerData == null || playerData.hotbarData == null) {
            return;
        }
        AbilityHotbarData fromData = playerData.hotbarData.getSlot(fromSlot);
        AbilityHotbarData toData = playerData.hotbarData.getSlot(toSlot);
        if (fromData == null || toData == null) {
            return;
        }
        String fromKey = fromData.abilityKey;
        String toKey = toData.abilityKey;
        this.saveHotbarSlot(fromSlot, toKey != null ? toKey : "");
        this.saveHotbarSlot(toSlot, fromKey != null ? fromKey : "");
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
    }

    @Override
    public void func_146281_b() {
        super.func_146281_b();
        PlayerData playerData = ClientCacheHandler.playerData;
        if (playerData != null && playerData.abilityData != null) {
            boolean changed;
            String currentKey = playerData.abilityData.getSelectedAbilityKey();
            boolean bl = changed = this.initialSelectedKey == null && currentKey != null && !currentKey.isEmpty() || this.initialSelectedKey != null && !this.initialSelectedKey.equals(currentKey);
            if (changed) {
                PacketHandler.Instance.sendToServer(new AbilityHotbarSelectPacket(currentKey != null ? currentKey : ""));
            }
        }
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (i == 1 || this.isInventoryKey(i)) {
            this.close();
        }
    }

    @Override
    public void save() {
    }
}

