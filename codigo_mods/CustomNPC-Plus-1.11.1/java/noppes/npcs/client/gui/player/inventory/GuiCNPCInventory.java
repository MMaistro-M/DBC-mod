/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Mouse
 */
package noppes.npcs.client.gui.player.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.gui.player.inventory.GuiAbilities;
import noppes.npcs.client.gui.player.inventory.GuiFaction;
import noppes.npcs.client.gui.player.inventory.GuiParty;
import noppes.npcs.client.gui.player.inventory.GuiProfiles;
import noppes.npcs.client.gui.player.inventory.GuiQuestLog;
import noppes.npcs.client.gui.player.inventory.GuiSettings;
import noppes.npcs.client.gui.util.GuiEffectBar;
import noppes.npcs.client.gui.util.GuiMenuSideButton;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.config.ConfigClient;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.data.CustomEffect;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerEffect;
import org.lwjgl.input.Mouse;
import tconstruct.client.tabs.InventoryTabCustomNpc;
import tconstruct.client.tabs.TabRegistry;

public class GuiCNPCInventory
extends GuiNPCInterface {
    public static final ResourceLocation specialIcons = new ResourceLocation("customnpcs", "textures/gui/icons.png");
    public static int activeTab = -100;
    protected Minecraft field_146297_k = Minecraft.func_71410_x();
    private GuiEffectBar effectBar;

    public GuiCNPCInventory() {
        this.xSize = 280;
        this.ySize = 180;
        this.drawDefaultBackground = false;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.guiTop += 10;
        TabRegistry.updateTabValues(this.guiLeft, this.guiTop, InventoryTabCustomNpc.class);
        TabRegistry.addTabsToList(this.field_146292_n);
        int y = 3;
        GuiMenuSideButton questsButton = new GuiMenuSideButton(-100, this.guiLeft + this.xSize + 37, this.guiTop + y, 22, 22, "");
        questsButton.rightSided = true;
        questsButton.active = activeTab == -100;
        questsButton.renderIconPosX = 32;
        questsButton.renderResource = specialIcons;
        this.addSideButton(questsButton);
        if (ClientCacheHandler.allowParties) {
            GuiMenuSideButton partyButton = new GuiMenuSideButton(-101, this.guiLeft + this.xSize + 37, this.guiTop + (y += 21), 22, 22, "");
            partyButton.rightSided = true;
            partyButton.active = activeTab == -101;
            partyButton.renderResource = specialIcons;
            this.addSideButton(partyButton);
        }
        if (ConfigClient.enableFactionTab) {
            GuiMenuSideButton factionButton = new GuiMenuSideButton(-102, this.guiLeft + this.xSize + 37, this.guiTop + (y += 21), 22, 22, "");
            factionButton.rightSided = true;
            factionButton.active = activeTab == -102;
            factionButton.renderIconPosX = 48;
            factionButton.renderResource = specialIcons;
            this.addSideButton(factionButton);
        }
        if (ClientCacheHandler.allowProfiles) {
            GuiMenuSideButton profileButton = new GuiMenuSideButton(-104, this.guiLeft + this.xSize + 37, this.guiTop + (y += 21), 22, 22, "");
            profileButton.rightSided = true;
            profileButton.active = activeTab == -104;
            profileButton.renderIconPosX = 64;
            profileButton.renderResource = specialIcons;
            this.addSideButton(profileButton);
        }
        GuiMenuSideButton abilitiesButton = new GuiMenuSideButton(-105, this.guiLeft + this.xSize + 37, this.guiTop + (y += 21), 22, 22, "");
        abilitiesButton.rightSided = true;
        abilitiesButton.active = activeTab == -105;
        abilitiesButton.renderIconPosX = 80;
        abilitiesButton.renderResource = specialIcons;
        this.addSideButton(abilitiesButton);
        GuiMenuSideButton clientButton = new GuiMenuSideButton(-103, this.guiLeft + this.xSize + 37, this.guiTop + (y += 21), 22, 22, "");
        clientButton.rightSided = true;
        clientButton.active = activeTab == -103;
        clientButton.renderIconPosX = 16;
        clientButton.renderResource = specialIcons;
        this.addSideButton(clientButton);
        int effectBarX = this.guiLeft - 40;
        int effectBarY = this.guiTop + 10;
        int effectBarWidth = 28;
        int effectBarHeight = this.ySize;
        this.effectBar = new GuiEffectBar(effectBarX, effectBarY, effectBarWidth, effectBarHeight);
    }

    private void updateEffectBar() {
        if (this.effectBar != null) {
            this.effectBar.entries.clear();
            PlayerData data = PlayerData.get((EntityPlayer)this.field_146297_k.field_71439_g);
            if (data != null && data.effectData != null) {
                for (PlayerEffect pe : data.effectData.getEffects().values()) {
                    CustomEffect effect = CustomEffectController.getInstance().get(pe.id, pe.index);
                    if (effect == null) continue;
                    this.effectBar.entries.add(new GuiEffectBar.EffectEntry(effect, pe));
                }
            }
        }
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        this.updateEffectBar();
        super.func_73863_a(mouseX, mouseY, partialTicks);
        if (!ConfigClient.HideEffectsBar && this.effectBar != null && !this.effectBar.entries.isEmpty() && activeTab != -100) {
            this.effectBar.func_73863_a(mouseX, mouseY, partialTicks);
        }
    }

    public void func_146274_d() {
        int delta = Mouse.getDWheel();
        if (delta != 0 && this.effectBar != null) {
            int mouseX = Mouse.getX() * this.field_146294_l / this.field_146297_k.field_71443_c;
            int mouseY = this.field_146295_m - Mouse.getY() * this.field_146295_m / this.field_146297_k.field_71440_d - 1;
            if (mouseX >= this.effectBar.x && mouseX < this.effectBar.x + this.effectBar.field_146294_l && mouseY >= this.effectBar.y && mouseY < this.effectBar.y + this.effectBar.field_146295_m) {
                this.effectBar.mouseScrolled(delta / 120);
            }
        }
        super.func_146274_d();
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton.field_146127_k <= -100) {
            if (guibutton.field_146127_k == -100 && activeTab != -100) {
                activeTab = -100;
                this.field_146297_k.func_147108_a((GuiScreen)new GuiQuestLog());
            }
            if (guibutton.field_146127_k == -101 && activeTab != -101) {
                activeTab = -101;
                this.field_146297_k.func_147108_a((GuiScreen)new GuiParty());
            }
            if (guibutton.field_146127_k == -102 && activeTab != -102) {
                activeTab = -102;
                this.field_146297_k.func_147108_a((GuiScreen)new GuiFaction());
            }
            if (guibutton.field_146127_k == -103 && activeTab != -103) {
                activeTab = -103;
                this.field_146297_k.func_147108_a((GuiScreen)new GuiSettings());
            }
            if (guibutton.field_146127_k == -104 && activeTab != -104) {
                activeTab = -104;
                this.field_146297_k.func_147108_a((GuiScreen)new GuiProfiles());
            }
            if (guibutton.field_146127_k == -105 && activeTab != -105) {
                activeTab = -105;
                this.field_146297_k.func_147108_a((GuiScreen)new GuiAbilities());
            }
        }
    }

    @Override
    public void save() {
    }
}

