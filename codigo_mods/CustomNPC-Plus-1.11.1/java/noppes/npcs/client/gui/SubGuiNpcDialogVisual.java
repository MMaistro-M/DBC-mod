/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 */
package noppes.npcs.client.gui;

import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.SubGuiColorSelector;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiMenuTopButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcButtonYesNo;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.controllers.data.Dialog;
import noppes.npcs.controllers.data.DialogColorData;
import noppes.npcs.controllers.data.DialogImage;

public class SubGuiNpcDialogVisual
extends SubGuiInterface
implements ISubGuiListener,
ITextfieldListener,
ICustomScrollListener {
    private final Dialog dialog;
    private GuiMenuTopButton[] topButtons;
    private int activeMenu = 1;
    private GuiCustomScroll imageScroll;
    private int selected = -1;
    private int lastColorClicked = -1;
    private int changedId = -1;

    public SubGuiNpcDialogVisual(Dialog dialog) {
        this.dialog = dialog;
        this.setBackground("menubg.png");
        this.xSize = 256;
        this.ySize = 216;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        String color;
        super.func_73866_w_();
        int y = this.guiTop - 10;
        GuiMenuTopButton close = new GuiMenuTopButton(0, this.guiLeft + this.xSize - 22, this.guiTop - 17, "X");
        GuiMenuTopButton general = new GuiMenuTopButton(1, this.guiLeft + 4, this.guiTop - 17, "General");
        GuiMenuTopButton spacing = new GuiMenuTopButton(2, general.field_146128_h + general.getWidth(), this.guiTop - 17, "Spacing");
        GuiMenuTopButton images = new GuiMenuTopButton(3, spacing.field_146128_h + spacing.getWidth(), this.guiTop - 17, "Images");
        GuiMenuTopButton colours = new GuiMenuTopButton(5, images.field_146128_h + images.getWidth(), this.guiTop - 17, "Colors");
        for (GuiMenuTopButton button : this.topButtons = new GuiMenuTopButton[]{general, images, spacing, close, colours}) {
            button.active = button.field_146127_k == this.activeMenu;
            this.addButton(button);
        }
        if (this.activeMenu == 1) {
            this.addButton(new GuiNpcButtonYesNo(10, this.guiLeft + 120, y += 22, this.dialog.hideNPC));
            this.addLabel(new GuiNpcLabel(10, "dialog.hideNPC", this.guiLeft + 4, y + 5));
            this.addButton(new GuiNpcButtonYesNo(11, this.guiLeft + 120, y += 22, this.dialog.showWheel));
            this.addLabel(new GuiNpcLabel(11, "dialog.showWheel", this.guiLeft + 4, y + 5));
            this.addButton(new GuiNpcButtonYesNo(12, this.guiLeft + 120, y += 22, this.dialog.darkenScreen));
            this.addLabel(new GuiNpcLabel(12, "dialog.darkenScreen", this.guiLeft + 4, y + 5));
            this.addButton(new GuiNpcButton(13, this.guiLeft + 120, y += 22, 50, 20, new String[]{"dialog.instant", "dialog.gradual"}, this.dialog.renderGradual ? 1 : 0));
            this.addLabel(new GuiNpcLabel(13, "dialog.renderType", this.guiLeft + 4, y + 5));
            this.addButton(new GuiNpcButton(14, this.guiLeft + 120, y += 22, 50, 20, new String[]{"display.hide", "display.show"}, this.dialog.showPreviousBlocks ? 1 : 0));
            this.addLabel(new GuiNpcLabel(14, "dialog.previousBlocks", this.guiLeft + 4, y + 5));
            this.addButton(new GuiNpcButton(15, this.guiLeft + 120, y += 22, 50, 20, new String[]{"display.hide", "display.show"}, this.dialog.showOptionLine ? 1 : 0));
            this.addLabel(new GuiNpcLabel(15, "dialog.optionLine", this.guiLeft + 4, y + 5));
            color = Integer.toHexString(this.dialog.color);
            while (color.length() < 6) {
                color = 0 + color;
            }
            this.addButton(new GuiNpcButton(16, this.guiLeft + 35, y += 25, 60, 20, color));
            this.addLabel(new GuiNpcLabel(16, "gui.color", this.guiLeft + 4, y + 5));
            this.getButton(16).setTextColor(this.dialog.color);
            color = Integer.toHexString(this.dialog.titleColor);
            while (color.length() < 6) {
                color = 0 + color;
            }
            this.addButton(new GuiNpcButton(17, this.guiLeft + 157, y, 60, 20, color));
            this.addLabel(new GuiNpcLabel(17, "dialog.titleColor", this.guiLeft + 100, y + 5));
            this.getButton(17).setTextColor(this.dialog.titleColor);
            this.addTextField(new GuiNpcTextField(18, this, this.guiLeft + 80, y += 22, 165, 20, this.dialog.textSound));
            this.addLabel(new GuiNpcLabel(18, "dialog.textSound", this.guiLeft + 4, y + 5));
            this.getTextField(18).func_146189_e(this.dialog.renderGradual);
            this.getLabel((int)18).enabled = this.dialog.renderGradual;
            this.addTextField(new GuiNpcTextField(19, this, this.guiLeft + 125, y += 22, 40, 20, "" + this.dialog.textPitch));
            this.addLabel(new GuiNpcLabel(19, "dialog.textPitch", this.guiLeft + 4, y + 5));
            this.getTextField((int)19).floatsOnly = true;
            this.getTextField(19).func_146189_e(this.dialog.renderGradual);
            this.getLabel((int)19).enabled = this.dialog.renderGradual;
        } else if (this.activeMenu == 2) {
            this.addButton(new GuiNpcButton(10, this.guiLeft + 70, y += 22, 50, 20, new String[]{"gui.text", "gui.option", "display.fixed"}, this.dialog.titlePos));
            this.addLabel(new GuiNpcLabel(10, "dialog.titlePos", this.guiLeft + 4, y + 5));
            this.addButton(new GuiNpcButton(24, this.guiLeft + 192, y, 50, 20, new String[]{"display.bottom", "display.top"}, (int)this.dialog.alignment));
            this.addLabel(new GuiNpcLabel(27, "display.alignment", this.guiLeft + 126, y + 5));
            this.addTextField(new GuiNpcTextField(11, this, this.guiLeft + 120, y += 25, 40, 20, "" + this.dialog.textWidth));
            this.addTextField(new GuiNpcTextField(12, this, this.guiLeft + 165, y, 40, 20, "" + this.dialog.textHeight));
            this.addLabel(new GuiNpcLabel(12, "gui.widthHeight", this.guiLeft + 4, y + 5));
            this.getTextField((int)11).integersOnly = true;
            this.getTextField(11).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
            this.getTextField((int)12).integersOnly = true;
            this.getTextField(12).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
            this.addTextField(new GuiNpcTextField(13, this, this.guiLeft + 120, y += 25, 40, 20, "" + this.dialog.textOffsetX));
            this.addTextField(new GuiNpcTextField(14, this, this.guiLeft + 165, y, 40, 20, "" + this.dialog.textOffsetY));
            this.addLabel(new GuiNpcLabel(15, "dialog.textOffset", this.guiLeft + 4, y + 5));
            this.getTextField((int)13).integersOnly = true;
            this.getTextField(13).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
            this.getTextField((int)14).integersOnly = true;
            this.getTextField(14).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
            this.addTextField(new GuiNpcTextField(15, this, this.guiLeft + 120, y += 25, 40, 20, "" + this.dialog.titleOffsetX));
            this.addTextField(new GuiNpcTextField(16, this, this.guiLeft + 165, y, 40, 20, "" + this.dialog.titleOffsetY));
            this.addLabel(new GuiNpcLabel(18, "dialog.titleOffset", this.guiLeft + 4, y + 5));
            this.getTextField((int)15).integersOnly = true;
            this.getTextField(15).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
            this.getTextField((int)16).integersOnly = true;
            this.getTextField(16).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
            this.addTextField(new GuiNpcTextField(17, this, this.guiLeft + 120, y += 25, 40, 20, "" + this.dialog.optionOffsetX));
            this.addTextField(new GuiNpcTextField(18, this, this.guiLeft + 165, y, 40, 20, "" + this.dialog.optionOffsetY));
            this.addLabel(new GuiNpcLabel(21, "dialog.optionOffset", this.guiLeft + 4, y + 5));
            this.getTextField((int)17).integersOnly = true;
            this.getTextField(17).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
            this.getTextField((int)18).integersOnly = true;
            this.getTextField(18).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
            this.addTextField(new GuiNpcTextField(19, this, this.guiLeft + 120, y += 25, 40, 20, "" + this.dialog.optionSpaceX));
            this.addTextField(new GuiNpcTextField(20, this, this.guiLeft + 165, y, 40, 20, "" + this.dialog.optionSpaceY));
            this.addLabel(new GuiNpcLabel(24, "dialog.optionSpacing", this.guiLeft + 4, y + 5));
            this.getTextField((int)19).integersOnly = true;
            this.getTextField(19).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
            this.getTextField((int)20).integersOnly = true;
            this.getTextField(20).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
            this.addTextField(new GuiNpcTextField(21, this, this.guiLeft + 120, y += 25, 40, 20, "" + this.dialog.npcOffsetX));
            this.addTextField(new GuiNpcTextField(22, this, this.guiLeft + 165, y, 40, 20, "" + this.dialog.npcOffsetY));
            this.addLabel(new GuiNpcLabel(25, "dialog.npcOffset", this.guiLeft + 4, y + 5));
            this.getTextField((int)21).integersOnly = true;
            this.getTextField(21).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
            this.getTextField(21).func_146189_e(!this.dialog.hideNPC);
            this.getTextField((int)22).integersOnly = true;
            this.getTextField(22).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
            this.getTextField(22).func_146189_e(!this.dialog.hideNPC);
            this.getLabel((int)25).enabled = !this.dialog.hideNPC;
            this.addTextField(new GuiNpcTextField(23, this, this.guiLeft + 120, y += 25, 40, 20, "" + this.dialog.npcScale));
            this.addLabel(new GuiNpcLabel(26, "dialog.npcScale", this.guiLeft + 4, y + 5));
            this.getTextField((int)23).floatsOnly = true;
            this.getTextField(23).setMinMaxDefaultFloat(-3.4028235E38f, Float.MAX_VALUE, 0.0f);
            this.getTextField(23).func_146189_e(!this.dialog.hideNPC);
            this.getLabel((int)26).enabled = !this.dialog.hideNPC;
        } else if (this.activeMenu == 3) {
            if (this.imageScroll == null) {
                this.imageScroll = new GuiCustomScroll((GuiScreen)this, 0, false);
            }
            this.imageScroll.guiLeft = this.guiLeft + this.xSize;
            this.imageScroll.guiTop = this.guiTop + 25;
            this.imageScroll.setSize(50, this.ySize - 25);
            this.updateScrollData();
            this.addButton(new GuiNpcButton(1, this.guiLeft + this.xSize + 2, this.guiTop + 5, 20, 20, "+"));
            if (this.getSelectedImage() != null) {
                DialogImage dialogImage = this.getSelectedImage();
                this.addButton(new GuiNpcButton(2, this.guiLeft + this.xSize + 24, this.guiTop + 5, 20, 20, "-"));
                this.addTextField(new GuiNpcTextField(10, this, this.guiLeft + 17, y += 25, 30, 20, "" + dialogImage.id));
                this.addTextField(new GuiNpcTextField(11, this, this.guiLeft + 92, y, 150, 20, dialogImage.texture));
                this.addLabel(new GuiNpcLabel(10, "gui.id", this.guiLeft + 4, y + 5));
                this.addLabel(new GuiNpcLabel(11, "display.texture", this.guiLeft + 50, y + 5));
                this.getTextField((int)10).integersOnly = true;
                this.addTextField(new GuiNpcTextField(12, this, this.guiLeft + 120, y += 25, 40, 20, "" + dialogImage.x));
                this.addTextField(new GuiNpcTextField(13, this, this.guiLeft + 165, y, 40, 20, "" + dialogImage.y));
                this.addLabel(new GuiNpcLabel(12, "gui.position", this.guiLeft + 4, y + 5));
                this.getTextField((int)12).integersOnly = true;
                this.getTextField(12).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
                this.getTextField((int)13).integersOnly = true;
                this.getTextField(13).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
                this.addTextField(new GuiNpcTextField(14, this, this.guiLeft + 120, y += 25, 40, 20, "" + dialogImage.width));
                this.addTextField(new GuiNpcTextField(15, this, this.guiLeft + 165, y, 40, 20, "" + dialogImage.height));
                this.addLabel(new GuiNpcLabel(13, "gui.widthHeight", this.guiLeft + 4, y + 5));
                this.getTextField((int)14).integersOnly = true;
                this.getTextField(14).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
                this.getTextField((int)15).integersOnly = true;
                this.getTextField(15).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
                this.addTextField(new GuiNpcTextField(16, this, this.guiLeft + 120, y += 25, 40, 20, "" + dialogImage.textureX));
                this.addTextField(new GuiNpcTextField(17, this, this.guiLeft + 165, y, 40, 20, "" + dialogImage.textureY));
                this.addLabel(new GuiNpcLabel(14, "dialog.textureOffset", this.guiLeft + 4, y + 5));
                this.getTextField((int)16).integersOnly = true;
                this.getTextField(16).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
                this.getTextField((int)17).integersOnly = true;
                this.getTextField(17).setMinMaxDefault(-2147483647, Integer.MAX_VALUE, 0);
                String color2 = Integer.toHexString(dialogImage.color);
                while (color2.length() < 6) {
                    color2 = 0 + color2;
                }
                this.addButton(new GuiNpcButton(18, this.guiLeft + 35, y += 25, 60, 20, color2));
                this.addLabel(new GuiNpcLabel(15, "gui.color", this.guiLeft + 4, y + 5));
                this.getButton(18).setTextColor(dialogImage.color);
                String selectedColor = Integer.toHexString(dialogImage.selectedColor);
                while (selectedColor.length() < 6) {
                    selectedColor = 0 + selectedColor;
                }
                this.addButton(new GuiNpcButton(19, this.guiLeft + 180, y, 60, 20, selectedColor));
                this.addLabel(new GuiNpcLabel(16, "dialog.selectedColor", this.guiLeft + 100, y + 5));
                this.getButton(19).setTextColor(dialogImage.selectedColor);
                this.getButton(19).setEnabled(dialogImage.imageType == 2);
                this.getButton(19).setVisible(dialogImage.imageType == 2);
                this.getLabel((int)16).enabled = dialogImage.imageType == 2;
                this.addTextField(new GuiNpcTextField(20, this, this.guiLeft + 33, y += 25, 30, 20, "" + dialogImage.scale));
                this.addTextField(new GuiNpcTextField(21, this, this.guiLeft + 102, y, 30, 20, "" + dialogImage.alpha));
                this.addTextField(new GuiNpcTextField(22, this, this.guiLeft + 183, y, 45, 20, "" + dialogImage.rotation));
                this.addLabel(new GuiNpcLabel(17, "model.scale", this.guiLeft + 4, y + 5));
                this.addLabel(new GuiNpcLabel(18, "display.alpha", this.guiLeft + 72, y + 5));
                this.addLabel(new GuiNpcLabel(19, "movement.rotation", this.guiLeft + 140, y + 5));
                this.getTextField((int)20).floatsOnly = true;
                this.getTextField((int)21).floatsOnly = true;
                this.getTextField((int)22).floatsOnly = true;
                this.addButton(new GuiNpcButton(23, this.guiLeft + 35, y += 25, 60, 20, new String[]{"gui.screen", "gui.text", "gui.option"}, dialogImage.imageType));
                this.addLabel(new GuiNpcLabel(20, "gui.type", this.guiLeft + 4, y + 5));
                this.addButton(new GuiNpcButton(24, this.guiLeft + 160, y, 60, 20, new String[]{"display.topLeft", "display.topCenter", "display.topRight", "display.left", "display.center", "display.right", "display.botLeft", "display.botCenter", "display.botRight"}, dialogImage.alignment));
                this.addLabel(new GuiNpcLabel(21, "display.alignment", this.guiLeft + 110, y + 5));
                this.getButton(24).setEnabled(dialogImage.imageType == 0);
                this.getButton(24).setVisible(dialogImage.imageType == 0);
                this.getLabel((int)21).enabled = dialogImage.imageType == 0;
            }
        } else if (this.activeMenu == 5) {
            this.addButton(new GuiNpcButtonYesNo(10, this.guiLeft + 180, y += 22, 60, 20, this.dialog.colorData.getEnableColorSettings()));
            this.addLabel(new GuiNpcLabel(10, "dialog.enableColorSettings", this.guiLeft + 4, y + 5));
            if (this.dialog.colorData.getEnableColorSettings()) {
                color = Integer.toHexString(this.dialog.colorData.getLineColor1());
                while (color.length() < 6) {
                    color = 0 + color;
                }
                this.addButton(new GuiNpcButton(11, this.guiLeft + 180, y += 25, 60, 20, color));
                this.addLabel(new GuiNpcLabel(11, "gui.lineColor1", this.guiLeft + 4, y + 5));
                this.getButton(11).setTextColor(this.dialog.colorData.getLineColor1());
                color = Integer.toHexString(this.dialog.colorData.getLineColor2());
                while (color.length() < 6) {
                    color = 0 + color;
                }
                this.addButton(new GuiNpcButton(12, this.guiLeft + 180, y += 25, 60, 20, color));
                this.addLabel(new GuiNpcLabel(12, "gui.lineColor2", this.guiLeft + 4, y + 5));
                this.getButton(12).setTextColor(this.dialog.colorData.getLineColor2());
                color = Integer.toHexString(this.dialog.colorData.getLineColor3());
                while (color.length() < 6) {
                    color = 0 + color;
                }
                this.addButton(new GuiNpcButton(13, this.guiLeft + 180, y += 25, 60, 20, color));
                this.addLabel(new GuiNpcLabel(13, "gui.lineColor3", this.guiLeft + 4, y + 5));
                this.getButton(13).setTextColor(this.dialog.colorData.getLineColor3());
                color = Integer.toHexString(this.dialog.colorData.getSlotColor());
                while (color.length() < 6) {
                    color = 0 + color;
                }
                this.addButton(new GuiNpcButton(14, this.guiLeft + 180, y += 25, 60, 20, color));
                this.addLabel(new GuiNpcLabel(14, "gui.slotColor", this.guiLeft + 4, y + 5));
                this.getButton(14).setTextColor(this.dialog.colorData.getSlotColor());
                color = Integer.toHexString(this.dialog.colorData.getButtonAcceptColor());
                while (color.length() < 6) {
                    color = 0 + color;
                }
                this.addButton(new GuiNpcButton(15, this.guiLeft + 180, y += 25, 60, 20, color));
                this.addLabel(new GuiNpcLabel(15, "gui.buttonAcceptColor", this.guiLeft + 4, y + 5));
                this.getButton(15).setTextColor(this.dialog.colorData.getButtonAcceptColor());
                color = Integer.toHexString(this.dialog.colorData.getButtonRejectColor());
                while (color.length() < 6) {
                    color = 0 + color;
                }
                this.addButton(new GuiNpcButton(16, this.guiLeft + 180, y += 25, 60, 20, color));
                this.addLabel(new GuiNpcLabel(16, "gui.buttonRejectColor", this.guiLeft + 4, y + 5));
                this.getButton(16).setTextColor(this.dialog.colorData.getButtonRejectColor());
            }
        }
    }

    public void updateScrollData() {
        ArrayList<Integer> ids = new ArrayList<Integer>(this.dialog.dialogImages.keySet());
        Collections.sort(ids);
        ArrayList<String> strings = new ArrayList<String>();
        for (int i : ids) {
            strings.add("" + i);
        }
        this.imageScroll.setUnsortedList(strings);
        if (this.changedId != -1) {
            this.selected = this.imageScroll.getList().indexOf("" + this.changedId);
            this.imageScroll.setSelected("" + this.changedId);
            this.changedId = -1;
        }
        this.addScroll(this.imageScroll);
    }

    private void topButtonPressed(GuiMenuTopButton button) {
        NoppesUtil.clickSound();
        int id = button.field_146127_k;
        if (id == 0) {
            this.close();
            return;
        }
        this.save();
        this.activeMenu = id;
        this.func_73866_w_();
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (guibutton instanceof GuiMenuTopButton) {
            this.topButtonPressed((GuiMenuTopButton)guibutton);
        }
        if (this.activeMenu == 1) {
            if (button.field_146127_k == 10) {
                boolean bl = this.dialog.hideNPC = button.getValue() == 1;
            }
            if (button.field_146127_k == 11) {
                boolean bl = this.dialog.showWheel = button.getValue() == 1;
            }
            if (button.field_146127_k == 12) {
                boolean bl = this.dialog.darkenScreen = button.getValue() == 1;
            }
            if (button.field_146127_k == 13) {
                boolean bl = this.dialog.renderGradual = button.getValue() == 1;
            }
            if (button.field_146127_k == 14) {
                boolean bl = this.dialog.showPreviousBlocks = button.getValue() == 1;
            }
            if (button.field_146127_k == 15) {
                boolean bl = this.dialog.showOptionLine = button.getValue() == 1;
            }
            if (button.field_146127_k == 16) {
                this.setSubGui(new SubGuiColorSelector(this.dialog.color));
                this.lastColorClicked = 0;
            }
            if (button.field_146127_k == 17) {
                this.setSubGui(new SubGuiColorSelector(this.dialog.titleColor));
                this.lastColorClicked = 1;
            }
        }
        if (this.activeMenu == 2) {
            if (button.field_146127_k == 10) {
                this.dialog.titlePos = button.getValue();
            }
            if (button.field_146127_k == 24) {
                this.dialog.alignment = (byte)button.getValue();
            }
        }
        if (this.activeMenu == 3) {
            if (button.field_146127_k == 1) {
                if (this.dialog.dialogImages.size() >= ConfigMain.DialogImageLimit) {
                    return;
                }
                int addId = 0;
                if (this.selected != -1 && this.imageScroll.getSelected() != null) {
                    DialogImage selectedImage = (DialogImage)this.dialog.dialogImages.get(Integer.valueOf(this.imageScroll.getSelected()));
                    ArrayList<Integer> keys = new ArrayList<Integer>(this.dialog.dialogImages.keySet());
                    int keyIndex = keys.indexOf(selectedImage.id);
                    if (keyIndex >= 0 && keyIndex < keys.size() - 1) {
                        do {
                            addId = keys.get(keyIndex) + 1;
                        } while (++keyIndex < keys.size() && this.dialog.dialogImages.containsKey(addId));
                    }
                } else if (this.dialog.dialogImages.size() > 0) {
                    addId = (Integer)this.dialog.dialogImages.keySet().toArray()[this.dialog.dialogImages.size() - 1] + 1;
                }
                this.dialog.dialogImages.put(addId, new DialogImage(addId));
                this.updateScrollData();
                this.selected = this.imageScroll.getList().indexOf("" + addId);
            }
            if (button.field_146127_k == 2 && this.imageScroll.getSelected() != null) {
                this.dialog.dialogImages.remove(Integer.valueOf(this.imageScroll.getSelected()));
                --this.selected;
            }
            if (button.field_146127_k == 18 && this.getSelectedImage() != null) {
                DialogImage dialogImage = this.getSelectedImage();
                this.setSubGui(new SubGuiColorSelector(dialogImage.color));
                this.lastColorClicked = 0;
            }
            if (button.field_146127_k == 19 && this.getSelectedImage() != null) {
                DialogImage dialogImage = this.getSelectedImage();
                this.setSubGui(new SubGuiColorSelector(dialogImage.selectedColor));
                this.lastColorClicked = 1;
            }
            if (button.field_146127_k == 23 && this.getSelectedImage() != null) {
                DialogImage dialogImage = this.getSelectedImage();
                dialogImage.imageType = button.getValue();
            }
            if (button.field_146127_k == 24 && this.getSelectedImage() != null) {
                DialogImage dialogImage = this.getSelectedImage();
                dialogImage.alignment = button.getValue();
            }
        }
        if (this.activeMenu == 5) {
            DialogColorData colData = this.dialog.colorData;
            if (button.field_146127_k == 10) {
                colData.setEnableColorSettings(button.getValue() == 1);
            }
            if (button.field_146127_k == 11) {
                this.setSubGui(new SubGuiColorSelector(colData.getLineColor1()));
                this.lastColorClicked = 2;
            }
            if (button.field_146127_k == 12) {
                this.setSubGui(new SubGuiColorSelector(colData.getLineColor2()));
                this.lastColorClicked = 3;
            }
            if (button.field_146127_k == 13) {
                this.setSubGui(new SubGuiColorSelector(colData.getLineColor3()));
                this.lastColorClicked = 4;
            }
            if (button.field_146127_k == 14) {
                this.setSubGui(new SubGuiColorSelector(colData.getSlotColor()));
                this.lastColorClicked = 5;
            }
            if (button.field_146127_k == 15) {
                this.setSubGui(new SubGuiColorSelector(colData.getButtonAcceptColor()));
                this.lastColorClicked = 6;
            }
            if (button.field_146127_k == 16) {
                this.setSubGui(new SubGuiColorSelector(colData.getButtonRejectColor()));
                this.lastColorClicked = 7;
            }
        }
        this.func_73866_w_();
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        super.func_73863_a(i, j, f);
        for (GuiMenuTopButton button : this.topButtons) {
            button.func_146112_a(this.field_146297_k, i, j);
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (this.activeMenu == 1) {
            if (this.lastColorClicked == 0) {
                this.dialog.color = ((SubGuiColorSelector)subgui).color;
            } else if (this.lastColorClicked == 1) {
                this.dialog.titleColor = ((SubGuiColorSelector)subgui).color;
            }
            this.func_73866_w_();
        }
        if (this.activeMenu == 3 && this.getSelectedImage() != null) {
            DialogImage dialogImage = this.getSelectedImage();
            if (this.lastColorClicked == 0) {
                dialogImage.color = ((SubGuiColorSelector)subgui).color;
            } else if (this.lastColorClicked == 1) {
                dialogImage.selectedColor = ((SubGuiColorSelector)subgui).color;
            }
            this.func_73866_w_();
        }
        if (this.activeMenu == 5) {
            DialogColorData colData = this.dialog.colorData;
            if (this.lastColorClicked == 2) {
                colData.setLineColor1(((SubGuiColorSelector)subgui).color);
            } else if (this.lastColorClicked == 3) {
                colData.setLineColor2(((SubGuiColorSelector)subgui).color);
            } else if (this.lastColorClicked == 4) {
                colData.setLineColor3(((SubGuiColorSelector)subgui).color);
            } else if (this.lastColorClicked == 5) {
                colData.setSlotColor(((SubGuiColorSelector)subgui).color);
            } else if (this.lastColorClicked == 6) {
                colData.setButtonAcceptColor(((SubGuiColorSelector)subgui).color);
            } else if (this.lastColorClicked == 7) {
                colData.setButtonRejectColor(((SubGuiColorSelector)subgui).color);
            }
            this.func_73866_w_();
        }
        this.save();
    }

    @Override
    public void save() {
        GuiNpcTextField.unfocus();
    }

    private DialogImage getSelectedImage() {
        if (this.selected > -1) {
            return (DialogImage)this.dialog.dialogImages.get(this.dialog.dialogImages.keySet().toArray(new Integer[0])[this.selected]);
        }
        return null;
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (this.activeMenu == 1) {
            if (textfield.id == 18) {
                this.dialog.textSound = textfield.func_146179_b();
            }
            if (textfield.id == 19) {
                this.dialog.textPitch = textfield.getFloat();
            }
        }
        if (this.activeMenu == 2) {
            if (textfield.id == 11) {
                this.dialog.textWidth = textfield.getInteger();
            }
            if (textfield.id == 12) {
                this.dialog.textHeight = textfield.getInteger();
            }
            if (textfield.id == 13) {
                this.dialog.textOffsetX = textfield.getInteger();
            }
            if (textfield.id == 14) {
                this.dialog.textOffsetY = textfield.getInteger();
            }
            if (textfield.id == 15) {
                this.dialog.titleOffsetX = textfield.getInteger();
            }
            if (textfield.id == 16) {
                this.dialog.titleOffsetY = textfield.getInteger();
            }
            if (textfield.id == 17) {
                this.dialog.optionOffsetX = textfield.getInteger();
            }
            if (textfield.id == 18) {
                this.dialog.optionOffsetY = textfield.getInteger();
            }
            if (textfield.id == 19) {
                this.dialog.optionSpaceX = textfield.getInteger();
            }
            if (textfield.id == 20) {
                this.dialog.optionSpaceY = textfield.getInteger();
            }
            if (textfield.id == 21) {
                this.dialog.npcOffsetX = textfield.getInteger();
            }
            if (textfield.id == 22) {
                this.dialog.npcOffsetY = textfield.getInteger();
            }
            if (textfield.id == 23) {
                this.dialog.npcScale = textfield.getFloat();
            }
        }
        if (this.activeMenu == 3) {
            DialogImage dialogImage = this.getSelectedImage();
            if (dialogImage == null) {
                return;
            }
            if (textfield.id == 10) {
                if (this.imageScroll.getList().contains("" + textfield.getInteger())) {
                    textfield.func_146180_a("" + dialogImage.id);
                    return;
                }
                this.dialog.dialogImages.remove(dialogImage.id);
                dialogImage.id = textfield.getInteger();
                this.dialog.dialogImages.put(dialogImage.id, dialogImage);
                this.changedId = dialogImage.id;
                this.updateScrollData();
                this.func_73866_w_();
            }
            if (textfield.id == 11) {
                dialogImage.texture = textfield.func_146179_b();
            }
            if (textfield.id == 12) {
                dialogImage.x = textfield.getInteger();
            }
            if (textfield.id == 13) {
                dialogImage.y = textfield.getInteger();
            }
            if (textfield.id == 14) {
                dialogImage.width = textfield.getInteger();
            }
            if (textfield.id == 15) {
                dialogImage.height = textfield.getInteger();
            }
            if (textfield.id == 16) {
                dialogImage.textureX = textfield.getInteger();
            }
            if (textfield.id == 17) {
                dialogImage.textureY = textfield.getInteger();
            }
            if (textfield.id == 20) {
                dialogImage.scale = textfield.getFloat();
            }
            if (textfield.id == 21) {
                dialogImage.alpha = textfield.getFloat();
            }
            if (textfield.id == 22) {
                dialogImage.rotation = textfield.getFloat();
            }
        }
        this.save();
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0 && this.imageScroll != null) {
            this.selected = guiCustomScroll.selected;
            this.func_73866_w_();
        }
    }
}

