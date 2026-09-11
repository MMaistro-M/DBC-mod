/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 */
package noppes.npcs.client.gui.mainmenu;

import com.mojang.authlib.GameProfile;
import kamkeel.npcs.addon.client.GeckoAddonClient;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.mainmenu.MainmenuDisplayGetPacket;
import kamkeel.npcs.network.packets.request.mainmenu.MainmenuDisplaySavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.DataDisplay;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.GuiNpcTextureCloaks;
import noppes.npcs.client.gui.GuiNpcTextureOverlays;
import noppes.npcs.client.gui.SubGuiCustomHitbox;
import noppes.npcs.client.gui.SubGuiNpcName;
import noppes.npcs.client.gui.SubGuiNpcTint;
import noppes.npcs.client.gui.model.GuiCreationScreen;
import noppes.npcs.client.gui.select.GuiTextureSelection;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.controllers.data.SkinOverlay;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNpcDisplay
extends GuiNPCInterface2
implements ITextfieldListener,
IGuiData {
    private final DataDisplay display;
    public GuiNpcTextField nameText;

    public GuiNpcDisplay(EntityNPCInterface npc) {
        super(npc, 1);
        this.display = npc.display;
        PacketClient.sendClient(new MainmenuDisplayGetPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 4;
        this.addLabel(new GuiNpcLabel(0, "gui.name", this.guiLeft + 5, y + 5));
        this.nameText = new GuiNpcTextField(0, this, this.field_146289_q, this.guiLeft + 50, y, 206, 20, this.display.name);
        this.addTextField(this.nameText);
        this.addButton(new GuiNpcButton(0, this.guiLeft + 253 + 52, y, 110, 20, new String[]{"display.show", "display.hide", "display.showAttacking"}, this.display.showName));
        this.addButton(new GuiNpcButton(14, this.guiLeft + 259, y, 20, 20, Character.toString('\u21bb')));
        this.getButton(14).setIconTexture(new ResourceLocation("customnpcs", "textures/gui/slot.png"));
        this.addButton(new GuiNpcButton(15, this.guiLeft + 259 + 22, y, 20, 20, Character.toString('\u22ee')));
        this.addLabel(new GuiNpcLabel(11, "gui.title", this.guiLeft + 5, (y += 23) + 5));
        this.addTextField(new GuiNpcTextField(11, this, this.field_146289_q, this.guiLeft + 50, y, 206, 20, this.display.title));
        this.addLabel(new GuiNpcLabel(1, "display.model", this.guiLeft + 5, (y += 23) + 5));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 50, y, 110, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(2, "display.size", this.guiLeft + 175, y + 5));
        this.addTextField(new GuiNpcTextField(2, this, this.field_146289_q, this.guiLeft + 203, y, 40, 20, this.display.modelSize + ""));
        this.getTextField((int)2).integersOnly = true;
        this.getTextField(2).setMinMaxDefault(1, ConfigMain.NpcSizeLimit, 5);
        this.addLabel(new GuiNpcLabel(4, "display.texture", this.guiLeft + 5, (y += 23) + 5));
        this.addTextField(new GuiNpcTextField(3, this, this.field_146289_q, this.guiLeft + 80, y, 200, 20, this.display.skinType == 0 ? this.display.texture : this.display.url));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 325, y, 38, 20, "gui.select"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 283, y, 40, 20, new String[]{"display.texture", "display.player", "display.url", "display.urlSix"}, (int)this.display.skinType));
        this.getButton(3).setEnabled(this.display.skinType == 0);
        if (this.display.skinType == 1 && this.display.playerProfile != null) {
            this.getTextField(3).func_146180_a(this.display.playerProfile.getName());
        }
        this.addLabel(new GuiNpcLabel(8, "display.cape", this.guiLeft + 5, (y += 23) + 5));
        this.addTextField(new GuiNpcTextField(8, this, this.field_146289_q, this.guiLeft + 80, y, 200, 20, this.display.cloakTexture));
        this.addButton(new GuiNpcButton(8, this.guiLeft + 283, y, 80, 20, "display.selectTexture"));
        this.addLabel(new GuiNpcLabel(9, "display.overlay", this.guiLeft + 5, (y += 23) + 5));
        this.addTextField(new GuiNpcTextField(9, this, this.field_146289_q, this.guiLeft + 80, y, 200, 20, this.npc.display.skinOverlayData.overlayList.containsKey(0) ? this.npc.display.skinOverlayData.overlayList.get(0).getTexture() : ""));
        this.addButton(new GuiNpcButton(9, this.guiLeft + 283, y, 80, 20, "display.selectTexture"));
        this.addButton(new GuiNpcButton(11, this.guiLeft + 365, y, 50, 20, new String[]{"display.glow", "display.solid"}, !this.npc.display.skinOverlayData.overlayList.containsKey(0) ? 1 : (this.npc.display.skinOverlayData.overlayList.get(0).getGlow() ? 0 : 1)));
        if (!this.npc.display.skinOverlayData.overlayList.containsKey(0)) {
            this.getButton(11).setVisible(false);
            this.getButton(11).setEnabled(false);
        }
        this.addLabel(new GuiNpcLabel(5, "display.livingAnimation", this.guiLeft + 5, (y += 23) + 5));
        this.addButton(new GuiNpcButton(5, this.guiLeft + 120, y, 50, 20, new String[]{"gui.yes", "gui.no"}, this.display.disableLivingAnimation ? 1 : 0));
        this.addButton(new GuiNpcButton(13, this.guiLeft + 185, y, 100, 20, "display.tintsettings"));
        this.addButton(new GuiNpcButton(12, this.guiLeft + 300, y, 100, 20, "display.hitboxsettings"));
        this.addLabel(new GuiNpcLabel(7, "display.visible", this.guiLeft + 5, (y += 23) + 5));
        this.addButton(new GuiNpcButton(7, this.guiLeft + 120, y, 50, 20, new String[]{"gui.yes", "gui.no", "gui.partly"}, this.display.visible));
        this.addLabel(new GuiNpcLabel(10, "display.bossbar", this.guiLeft + 5, (y += 23) + 5));
        this.addButton(new GuiNpcButton(10, this.guiLeft + 60, y, 110, 20, new String[]{"display.hide", "display.show", "display.showAttacking"}, (int)this.display.showBossBar));
        GeckoAddonClient.Instance.geckoNpcDisplayInitGui(this);
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == 0) {
            if (!textfield.isEmpty()) {
                this.display.name = textfield.func_146179_b();
            } else {
                textfield.func_146180_a(this.display.name);
            }
        } else if (textfield.id == 2) {
            this.display.modelSize = textfield.getInteger();
        } else if (textfield.id == 3) {
            if (this.display.skinType == 2 || this.display.skinType == 3) {
                this.display.url = textfield.func_146179_b();
            } else if (this.display.skinType == 1) {
                this.display.playerProfile = !textfield.isEmpty() ? new GameProfile(null, textfield.func_146179_b()) : null;
            } else {
                this.display.texture = textfield.func_146179_b();
            }
        } else if (textfield.id == 8) {
            this.display.cloakTexture = textfield.func_146179_b();
        } else if (textfield.id == 9) {
            if (!textfield.func_146179_b().isEmpty()) {
                boolean isGlow = true;
                if (this.npc.display.skinOverlayData.has(0)) {
                    isGlow = this.npc.display.skinOverlayData.overlayList.get(0).getGlow();
                }
                SkinOverlay skinOverlay = new SkinOverlay(textfield.func_146179_b());
                skinOverlay.setGlow(isGlow);
                skinOverlay.setBlend(isGlow);
                this.npc.display.skinOverlayData.overlayList.put(0, skinOverlay);
            } else {
                this.npc.display.skinOverlayData.overlayList.remove(0);
            }
            this.func_73866_w_();
        } else if (textfield.id == 11) {
            this.display.title = textfield.func_146179_b();
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 0) {
            this.display.showName = button.getValue();
        }
        if (button.field_146127_k == 1) {
            NoppesUtil.openGUI((EntityPlayer)this.player, new GuiCreationScreen(this, (EntityCustomNpc)this.npc));
        }
        if (button.field_146127_k == 2) {
            this.display.skinType = (byte)button.getValue();
            if (this.display.skinType != 3) {
                this.display.url = "";
            }
            this.display.playerProfile = null;
            this.func_73866_w_();
        } else if (button.field_146127_k == 3) {
            this.setSubGui(new GuiTextureSelection(this.npc, this.npc.display.getSkinTexture()));
        } else if (button.field_146127_k == 5) {
            this.display.disableLivingAnimation = button.getValue() == 1;
        } else if (button.field_146127_k == 7) {
            this.display.visible = button.getValue();
        } else if (button.field_146127_k == 8) {
            NoppesUtil.openGUI((EntityPlayer)this.player, (Object)new GuiNpcTextureCloaks(this.npc, this));
        } else if (button.field_146127_k == 9) {
            NoppesUtil.openGUI((EntityPlayer)this.player, (Object)new GuiNpcTextureOverlays(this.npc, this));
        } else if (button.field_146127_k == 10) {
            this.display.showBossBar = (byte)button.getValue();
        } else if (button.field_146127_k == 11) {
            this.npc.display.skinOverlayData.overlayList.get(0).setGlow(button.getValue() == 0);
            this.npc.display.skinOverlayData.overlayList.get(0).setBlend(button.getValue() == 0);
            this.func_73866_w_();
        } else if (button.field_146127_k == 12) {
            this.setSubGui(new SubGuiCustomHitbox(this.display.hitboxData));
        } else if (button.field_146127_k == 13) {
            this.setSubGui(new SubGuiNpcTint(this.display.tintData));
        } else if (button.field_146127_k == 14) {
            String name = this.display.getRandomName();
            this.display.setName(name);
            this.getTextField(0).func_146180_a(name);
        } else if (button.field_146127_k == 15) {
            this.setSubGui(new SubGuiNpcName(this.display));
        }
        GeckoAddonClient.Instance.geckoNpcDisplayActionPerformed(this, button);
    }

    @Override
    public void closeSubGui(SubGuiInterface subgui) {
        super.closeSubGui(subgui);
        this.func_73866_w_();
    }

    @Override
    public void save() {
        if (this.display.skinType == 1) {
            this.display.loadProfile();
        }
        this.npc.textureLocation = null;
        this.field_146297_k.field_71438_f.func_72709_b((Entity)this.npc);
        this.field_146297_k.field_71438_f.func_72703_a((Entity)this.npc);
        PacketClient.sendClient(new MainmenuDisplaySavePacket(this.display.writeToNBT(new NBTTagCompound())));
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.display.readToNBT(compound);
        this.func_73866_w_();
    }
}

