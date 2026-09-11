/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.entity.player.EntityPlayer
 */
package noppes.npcs.client.gui.mainmenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.NoppesStringUtils;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.global.GuiNpcManagePlayerData;
import noppes.npcs.client.gui.global.GuiNpcNaturalSpawns;
import noppes.npcs.client.gui.player.inventory.GuiCNPCInventory;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcSquareButton;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNPCGlobalMainMenu
extends GuiNPCInterface2 {
    private final Map<Integer, GuiNpcSquareButton> buttonMap = new HashMap<Integer, GuiNpcSquareButton>();
    private GuiNpcSquareButton bankButton;
    private GuiNpcSquareButton factionButton;
    private GuiNpcSquareButton dialogButton;
    private GuiNpcSquareButton questButton;
    private GuiNpcSquareButton transportButton;
    private GuiNpcSquareButton playerdataButton;
    private GuiNpcSquareButton recipeButton;
    private GuiNpcSquareButton spawnButton;
    private GuiNpcSquareButton linkedButton;
    private GuiNpcSquareButton animationButton;
    private GuiNpcSquareButton tagButton;
    private GuiNpcSquareButton effectsButton;
    private GuiNpcSquareButton magicsButton;
    private GuiNpcSquareButton abilitiesButton;
    private GuiNpcSquareButton auctionsButton;

    public GuiNPCGlobalMainMenu(EntityNPCInterface npc) {
        super(npc, 5);
    }

    public void registerButton(GuiNpcSquareButton button) {
        this.buttonMap.put(button.field_146127_k, button);
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.bankButton = new GuiNpcSquareButton(2, 0, 0, 20, "global.banks", -13421773);
        this.registerButton(this.bankButton);
        this.bankButton.setIconPos(24, 24, 72, 32).setIconTexture(GuiCNPCInventory.specialIcons);
        this.factionButton = new GuiNpcSquareButton(3, 0, 0, 20, "menu.factions", -13421773);
        this.registerButton(this.factionButton);
        this.factionButton.setIconPos(24, 24, 0, 32).setIconTexture(GuiCNPCInventory.specialIcons);
        this.dialogButton = new GuiNpcSquareButton(4, 0, 0, 20, "dialog.dialogs", -13421773);
        this.registerButton(this.dialogButton);
        this.dialogButton.setIconPos(24, 24, 24, 32).setIconTexture(GuiCNPCInventory.specialIcons);
        this.questButton = new GuiNpcSquareButton(11, 0, 0, 20, "quest.quests", -13421773);
        this.registerButton(this.questButton);
        this.questButton.setIconPos(24, 24, 0, 56).setIconTexture(GuiCNPCInventory.specialIcons);
        this.transportButton = new GuiNpcSquareButton(12, 0, 0, 20, "global.transport", -13421773);
        this.registerButton(this.transportButton);
        this.transportButton.setIconPos(24, 24, 96, 32).setIconTexture(GuiCNPCInventory.specialIcons);
        this.playerdataButton = new GuiNpcSquareButton(13, 0, 0, 20, "global.playerdata", -13421773);
        this.registerButton(this.playerdataButton);
        this.playerdataButton.setIconPos(24, 24, 216, 32).setIconTexture(GuiCNPCInventory.specialIcons);
        this.recipeButton = new GuiNpcSquareButton(14, 0, 0, 20, "global.recipes", -13421773);
        this.registerButton(this.recipeButton);
        this.recipeButton.setIconPos(24, 24, 48, 32).setIconTexture(GuiCNPCInventory.specialIcons);
        this.spawnButton = new GuiNpcSquareButton(15, 0, 0, 20, NoppesStringUtils.translate("global.naturalspawn"), -13421773);
        this.registerButton(this.spawnButton);
        this.spawnButton.setIconPos(24, 24, 144, 32).setIconTexture(GuiCNPCInventory.specialIcons);
        this.linkedButton = new GuiNpcSquareButton(16, 0, 0, 20, "global.linked", -13421773);
        this.registerButton(this.linkedButton);
        this.linkedButton.setIconPos(24, 24, 168, 32).setIconTexture(GuiCNPCInventory.specialIcons);
        this.animationButton = new GuiNpcSquareButton(18, 0, 0, 20, "menu.animations", -13421773);
        this.registerButton(this.animationButton);
        this.animationButton.setIconPos(24, 24, 120, 32).setIconTexture(GuiCNPCInventory.specialIcons);
        this.tagButton = new GuiNpcSquareButton(17, 0, 0, 20, "menu.tags", -13421773);
        this.registerButton(this.tagButton);
        this.tagButton.setIconPos(24, 24, 192, 32).setIconTexture(GuiCNPCInventory.specialIcons);
        this.effectsButton = new GuiNpcSquareButton(19, 0, 0, 20, "global.customeffects", -13421773);
        this.registerButton(this.effectsButton);
        this.effectsButton.setIconPos(24, 24, 24, 56).setIconTexture(GuiCNPCInventory.specialIcons);
        this.magicsButton = new GuiNpcSquareButton(20, 0, 0, 20, "global.magic", -13421773);
        this.registerButton(this.magicsButton);
        this.magicsButton.setIconPos(24, 24, 48, 56).setIconTexture(GuiCNPCInventory.specialIcons);
        this.abilitiesButton = new GuiNpcSquareButton(21, 0, 0, 20, "global.abilities", -13421773);
        this.registerButton(this.abilitiesButton);
        this.abilitiesButton.setIconPos(24, 24, 72, 56).setIconTexture(GuiCNPCInventory.specialIcons);
        this.auctionsButton = new GuiNpcSquareButton(22, 0, 0, 20, "global.auction", -13421773);
        this.registerButton(this.auctionsButton);
        this.auctionsButton.setIconPos(24, 24, 96, 56).setIconTexture(GuiCNPCInventory.specialIcons);
        this.layoutButtons();
        for (GuiNpcButton guiNpcButton : this.buttonMap.values()) {
            this.addButton(guiNpcButton);
        }
    }

    private int[] computeOptimalLayout(int total, int padding) {
        int availWidth = this.xSize - 2 * padding;
        int availHeight = this.ySize - 2 * padding;
        double bestRatio = -1.0;
        int bestRows = 1;
        int bestCandidate = 0;
        for (int r = 1; r <= total; ++r) {
            int usedHeight;
            int usedWidth;
            double ratio;
            int candidateVert;
            int cols = (int)Math.ceil((double)total / (double)r);
            int candidateHoriz = (availWidth - (cols + 1) * padding) / cols;
            int candidate = Math.min(candidateHoriz, candidateVert = (availHeight - (r + 1) * padding) / r);
            if (candidate <= 0 || !((ratio = (double)((usedWidth = candidate * cols + (cols + 1) * padding) * (usedHeight = candidate * r + (r + 1) * padding)) / (double)(availWidth * availHeight)) > bestRatio) && (!(Math.abs(ratio - bestRatio) < 1.0E-4) || candidate <= bestCandidate)) continue;
            bestRatio = ratio;
            bestCandidate = candidate;
            bestRows = r;
        }
        if (bestCandidate <= 0) {
            bestCandidate = availHeight - 2 * padding;
            bestRows = 1;
        }
        return new int[]{bestRows, bestCandidate};
    }

    private void layoutButtons() {
        ArrayList<GuiNpcSquareButton> buttons = new ArrayList<GuiNpcSquareButton>(this.buttonMap.values());
        Collections.sort(buttons, new Comparator<GuiNpcSquareButton>(){

            @Override
            public int compare(GuiNpcSquareButton b1, GuiNpcSquareButton b2) {
                return Integer.compare(b1.field_146127_k, b2.field_146127_k);
            }
        });
        int total = buttons.size();
        if (total == 0) {
            return;
        }
        int padding = 2;
        int[] optimal = this.computeOptimalLayout(total, padding);
        int rows = optimal[0];
        int buttonSize = optimal[1];
        int base = total / rows;
        int extra = total % rows;
        int[] rowCounts = new int[rows];
        for (int i = 0; i < rows; ++i) {
            rowCounts[i] = base + (i < extra ? 1 : 0);
        }
        int totalHeight = rows * buttonSize + (rows + 1) * padding;
        int startY = this.guiTop + (this.ySize - totalHeight) / 2;
        int index = 0;
        for (int row = 0; row < rows; ++row) {
            int count = rowCounts[row];
            int rowWidth = count * buttonSize + (count + 1) * padding;
            int rowStartX = this.guiLeft + (this.xSize - rowWidth) / 2;
            int yPos = startY + padding + row * (buttonSize + padding);
            for (int col = 0; col < count && index < total; ++index, ++col) {
                GuiNpcSquareButton btn = (GuiNpcSquareButton)((Object)buttons.get(index));
                btn.updatePositionAndSize(rowStartX + padding + col * (buttonSize + padding), yPos, buttonSize);
            }
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 11) {
            NoppesUtil.requestOpenGUI(EnumGuiType.ManageQuests);
        }
        if (id == 2) {
            NoppesUtil.requestOpenGUI(EnumGuiType.ManageBanks);
        }
        if (id == 3) {
            NoppesUtil.requestOpenGUI(EnumGuiType.ManageFactions);
        }
        if (id == 17) {
            NoppesUtil.requestOpenGUI(EnumGuiType.ManageTags);
        }
        if (id == 18) {
            NoppesUtil.requestOpenGUI(EnumGuiType.ManageAnimations);
        }
        if (id == 4) {
            NoppesUtil.requestOpenGUI(EnumGuiType.ManageDialogs);
        }
        if (id == 12) {
            NoppesUtil.requestOpenGUI(EnumGuiType.ManageTransport);
        }
        if (id == 13) {
            NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNpcManagePlayerData(this.npc));
        }
        if (id == 14) {
            NoppesUtil.requestOpenGUI(EnumGuiType.ManageRecipes, 4, 0, 0);
        }
        if (id == 15) {
            NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNpcNaturalSpawns(this.npc));
        }
        if (id == 16) {
            NoppesUtil.requestOpenGUI(EnumGuiType.ManageLinked);
        }
        if (id == 20) {
            NoppesUtil.requestOpenGUI(EnumGuiType.ManageMagic);
        }
        if (id == 19) {
            NoppesUtil.requestOpenGUI(EnumGuiType.ManageEffects);
        }
        if (id == 21) {
            NoppesUtil.requestOpenGUI(EnumGuiType.ManageAbilities);
        }
        if (id == 22) {
            NoppesUtil.requestOpenGUI(EnumGuiType.ManageAuction);
        }
    }

    @Override
    public void save() {
    }
}

