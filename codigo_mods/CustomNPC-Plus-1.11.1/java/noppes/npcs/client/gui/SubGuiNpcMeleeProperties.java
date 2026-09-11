/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.DataStats;
import noppes.npcs.client.gui.util.GuiButtonBiDirectional;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumPotionType;

public class SubGuiNpcMeleeProperties
extends SubGuiInterface
implements ITextfieldListener {
    private DataStats stats;

    public SubGuiNpcMeleeProperties(DataStats stats) {
        this.stats = stats;
        this.setBackground("menubg.png");
        this.xSize = 256;
        this.ySize = 216;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(1, "stats.meleestrength", this.guiLeft + 5, this.guiTop + 15));
        this.addTextField(new GuiNpcTextField(1, this, this.field_146289_q, this.guiLeft + 85, this.guiTop + 10, 160, 18, String.format("%.0f", Float.valueOf(this.stats.getAttackStrength())) + ""));
        this.getTextField((int)1).floatsOnly = true;
        this.getTextField(1).setMinMaxDefaultFloat(0.0f, Float.MAX_VALUE, 5.0f);
        this.addLabel(new GuiNpcLabel(2, "stats.meleerange", this.guiLeft + 5, this.guiTop + 45));
        this.addTextField(new GuiNpcTextField(2, this, this.field_146289_q, this.guiLeft + 85, this.guiTop + 40, 40, 18, this.stats.attackRange + ""));
        this.getTextField((int)2).integersOnly = true;
        this.getTextField(2).setMinMaxDefault(1, Integer.MAX_VALUE, 2);
        this.addLabel(new GuiNpcLabel(3, "stats.meleespeed", this.guiLeft + 5, this.guiTop + 75));
        this.addTextField(new GuiNpcTextField(3, this, this.field_146289_q, this.guiLeft + 85, this.guiTop + 70, 40, 18, this.stats.attackSpeed + ""));
        this.getTextField((int)3).integersOnly = true;
        this.getTextField(3).setMinMaxDefault(1, Integer.MAX_VALUE, 20);
        this.addLabel(new GuiNpcLabel(4, "enchantment.knockback", this.guiLeft + 5, this.guiTop + 105));
        this.addTextField(new GuiNpcTextField(4, this, this.field_146289_q, this.guiLeft + 85, this.guiTop + 100, 40, 18, this.stats.knockback + ""));
        this.getTextField((int)4).integersOnly = true;
        this.getTextField(4).setMinMaxDefault(0, Integer.MAX_VALUE, 0);
        this.addLabel(new GuiNpcLabel(5, "stats.meleeeffect", this.guiLeft + 5, this.guiTop + 135));
        this.addButton(new GuiButtonBiDirectional(5, this.guiLeft + 85, this.guiTop + 130, 100, 20, EnumPotionType.getLangKeys(), this.stats.potionType.ordinal()));
        if (this.stats.potionType == EnumPotionType.Manual) {
            this.addLabel(new GuiNpcLabel(8, "effect.potionid", this.guiLeft + 198, this.guiTop + 119));
            this.addTextField(new GuiNpcTextField(8, this, this.field_146289_q, this.guiLeft + 200, this.guiTop + 132, 40, 18, this.stats.potionManualId + ""));
            this.getTextField((int)8).integersOnly = true;
            this.getTextField(8).setMinMaxDefault(0, Integer.MAX_VALUE, 0);
        }
        int y = this.guiTop + 160;
        if (this.stats.potionType != EnumPotionType.None) {
            this.addLabel(new GuiNpcLabel(6, "gui.time", this.guiLeft + 5, y + 5));
            this.addTextField(new GuiNpcTextField(6, this, this.field_146289_q, this.guiLeft + 85, y, 40, 18, this.stats.potionDuration + ""));
            this.getTextField((int)6).integersOnly = true;
            this.getTextField(6).setMinMaxDefault(1, Integer.MAX_VALUE, 5);
            if (this.stats.potionType != EnumPotionType.Fire) {
                this.addLabel(new GuiNpcLabel(7, "stats.amplify", this.guiLeft + 5, (y += 30) + 5));
                if (this.stats.potionType == EnumPotionType.Manual) {
                    this.addTextField(new GuiNpcTextField(7, this, this.field_146289_q, this.guiLeft + 85, y, 52, 18, this.stats.potionAmp + ""));
                    this.getTextField((int)7).integersOnly = true;
                    this.getTextField(7).setMinMaxDefault(0, 255, 0);
                } else {
                    this.addButton(new GuiButtonBiDirectional(7, this.guiLeft + 85, y, 52, 20, new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}, this.stats.potionAmp));
                }
            }
        }
        this.addButton(new GuiNpcButton(66, this.guiLeft + 190, this.guiTop + this.ySize - 26, 60, 20, "gui.done"));
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == 1) {
            this.stats.setAttackStrength(Float.parseFloat(textfield.func_146179_b()));
        } else if (textfield.id == 2) {
            this.stats.attackRange = textfield.getInteger();
        } else if (textfield.id == 3) {
            this.stats.attackSpeed = textfield.getInteger();
        } else if (textfield.id == 4) {
            this.stats.knockback = textfield.getInteger();
        } else if (textfield.id == 6) {
            this.stats.potionDuration = textfield.getInteger();
        } else if (textfield.id == 7) {
            this.stats.potionAmp = textfield.getInteger();
        } else if (textfield.id == 8) {
            this.stats.potionManualId = textfield.getInteger();
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 5) {
            EnumPotionType newType = EnumPotionType.fromOrdinal(button.getValue());
            if (this.stats.potionType == EnumPotionType.Manual && newType != EnumPotionType.Manual) {
                this.stats.potionAmp = 0;
            }
            this.stats.potionType = newType;
            this.func_73866_w_();
        }
        if (button.field_146127_k == 7) {
            this.stats.potionAmp = button.getValue();
        }
        if (button.field_146127_k == 66) {
            this.close();
        }
    }
}

