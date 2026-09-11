/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiYesNo
 *  net.minecraft.client.gui.GuiYesNoCallback
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player.inventory;

import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.quest.QuestUntrackPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.gui.hud.GuiHudEditor;
import noppes.npcs.client.gui.player.inventory.GuiCNPCInventory;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.config.ConfigClient;
import org.lwjgl.opengl.GL11;
import tconstruct.client.tabs.AbstractTab;

public class GuiSettings
extends GuiCNPCInventory
implements ITextfieldListener,
GuiYesNoCallback {
    private final ResourceLocation resource = new ResourceLocation("customnpcs", "textures/gui/standardbg.png");

    public GuiSettings() {
        this.xSize = 280;
        this.ySize = 180;
        this.drawDefaultBackground = false;
        this.title = "";
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = 0;
        this.addLabel(new GuiNpcLabel(1, "settings.chatBubbles", this.guiLeft + 8, this.guiTop + 14 + y));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 105, this.guiTop + 9 + y, 50, 20, new String[]{"gui.no", "gui.yes"}, ConfigClient.EnableChatBubbles ? 1 : 0));
        this.addLabel(new GuiNpcLabel(90, "settings.hud", this.guiLeft + 8 + 155, this.guiTop + 14 + y));
        this.addButton(new GuiNpcButton(90, this.guiLeft + 107 + 160, this.guiTop + 9 + y, 45, 20, "gui.edit"));
        this.addLabel(new GuiNpcLabel(2, "settings.dialogSound", this.guiLeft + 8, this.guiTop + 14 + (y += 22)));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 105, this.guiTop + 9 + y, 50, 20, new String[]{"gui.no", "gui.yes"}, ConfigClient.DialogSound ? 1 : 0));
        this.addLabel(new GuiNpcLabel(10, "settings.dialogSpeed", this.guiLeft + 8, this.guiTop + 14 + (y += 22)));
        this.addTextField(new GuiNpcTextField(10, this, this.field_146289_q, this.guiLeft + 107, this.guiTop + 9 + y, 45, 20, ConfigClient.DialogSpeed + ""));
        this.getTextField((int)10).integersOnly = true;
        this.getTextField(10).setMinMaxDefault(1, 20, 10);
        this.addLabel(new GuiNpcLabel(6, "settings.chatAlerts", this.guiLeft + 8, this.guiTop + 14 + (y += 22)));
        this.addButton(new GuiNpcButton(6, this.guiLeft + 105, this.guiTop + 9 + y, 50, 20, new String[]{"gui.no", "gui.yes"}, ConfigClient.ChatAlerts ? 1 : 0));
        this.addLabel(new GuiNpcLabel(7, "settings.bannerAlerts", this.guiLeft + 8, this.guiTop + 14 + (y += 22)));
        this.addButton(new GuiNpcButton(7, this.guiLeft + 105, this.guiTop + 9 + y, 50, 20, new String[]{"gui.no", "gui.yes"}, ConfigClient.BannerAlerts ? 1 : 0));
        this.addLabel(new GuiNpcLabel(8, "settings.effectsBar", this.guiLeft + 8, this.guiTop + 14 + (y += 22)));
        this.addButton(new GuiNpcButton(8, this.guiLeft + 105, this.guiTop + 9 + y, 50, 20, new String[]{"gui.no", "gui.yes"}, ConfigClient.HideEffectsBar ? 1 : 0));
        y += 22;
        this.addButton(new GuiNpcButton(5, this.guiLeft + 8, this.guiTop + 9 + (y += 22), 150, 20, "settings.clearSkin"));
        this.addButton(new GuiNpcButton(4, this.guiLeft + 8 + 155, this.guiTop + 9 + y, 150, 20, "settings.clearTrack"));
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        this.func_146276_q_();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(this.resource);
        this.func_73729_b(this.guiLeft, this.guiTop, 0, 0, 252, 195);
        this.func_73729_b(this.guiLeft + 252, this.guiTop, 188, 0, 67, 195);
        super.func_73863_a(i, j, f);
    }

    public void func_73878_a(boolean flag, int i) {
        if (flag) {
            switch (i) {
                case 0: {
                    PacketClient.sendClient(new QuestUntrackPacket());
                    break;
                }
                case 1: {
                    ClientCacheHandler.clearSkinCache();
                }
            }
            this.func_73866_w_();
        }
        this.displayGuiScreen(this);
    }

    @Override
    protected void func_146284_a(GuiButton btn) {
        if (btn instanceof AbstractTab) {
            return;
        }
        if (btn.field_146127_k <= -100) {
            super.func_146284_a(btn);
            return;
        }
        if (!(btn instanceof GuiNpcButton)) {
            return;
        }
        GuiNpcButton button = (GuiNpcButton)btn;
        if (button.field_146127_k == 1) {
            ConfigClient.EnableChatBubbles = button.getValue() == 1;
            ConfigClient.EnableChatBubblesProperty.set(ConfigClient.EnableChatBubbles);
        }
        if (button.field_146127_k == 2) {
            ConfigClient.DialogSound = button.getValue() == 1;
            ConfigClient.DialogSoundProperty.set(ConfigClient.DialogSound);
        }
        if (button.field_146127_k == 4) {
            GuiYesNo yesNoTrack = new GuiYesNo((GuiYesNoCallback)this, "Confirm", StatCollector.func_74838_a((String)"settings.confirmClearTrack"), 0);
            this.displayGuiScreen((GuiScreen)yesNoTrack);
        }
        if (button.field_146127_k == 5) {
            GuiYesNo yesNoSkin = new GuiYesNo((GuiYesNoCallback)this, "Confirm", StatCollector.func_74838_a((String)"settings.confirmClearSkin"), 1);
            this.displayGuiScreen((GuiScreen)yesNoSkin);
        }
        if (button.field_146127_k == 6) {
            ConfigClient.ChatAlerts = button.getValue() == 1;
            ConfigClient.ChatAlertsProperty.set(ConfigClient.ChatAlerts);
        }
        if (button.field_146127_k == 7) {
            ConfigClient.BannerAlerts = button.getValue() == 1;
            ConfigClient.BannerAlertsProperty.set(ConfigClient.BannerAlerts);
        }
        if (button.field_146127_k == 8) {
            ConfigClient.HideEffectsBar = button.getValue() == 1;
            ConfigClient.HideEffectsBarProperty.set(ConfigClient.HideEffectsBar);
        }
        if (button.field_146127_k == 90) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiHudEditor(this));
        }
        ConfigClient.config.save();
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

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == 10) {
            ConfigClient.DialogSpeed = textfield.getInteger();
            ConfigClient.DialogSpeedProperty.set(ConfigClient.DialogSound);
        }
    }
}

