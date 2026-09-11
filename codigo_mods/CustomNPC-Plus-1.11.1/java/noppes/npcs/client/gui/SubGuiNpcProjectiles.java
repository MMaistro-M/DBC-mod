/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.DataStats;
import noppes.npcs.client.gui.SubGuiScriptParticle;
import noppes.npcs.client.gui.util.GuiButtonBiDirectional;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcButtonYesNo;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumParticleType;
import noppes.npcs.constants.EnumPotionType;

public class SubGuiNpcProjectiles
extends SubGuiInterface
implements ITextfieldListener,
ISubGuiListener {
    private DataStats stats;
    private String[] trailNames = new String[]{"gui.none", "trail.smoke", "trail.portal", "trail.redstone", "trail.lightning", "trail.largesmoke", "trail.magic", "trail.enchant", "trail.crit", "trail.explode", "trail.music", "trail.flame", "trail.lava", "trail.splash", "trail.slime", "trail.heart", "trail.angryvillager", "trail.happyvillager", "trail.custom"};

    public SubGuiNpcProjectiles(DataStats stats) {
        this.stats = stats;
        this.setBackground("menubg.png");
        this.xSize = 390;
        this.ySize = 216;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        int internalY;
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(1, "enchantment.arrowDamage", this.guiLeft + 5, this.guiTop + 15));
        this.addTextField(new GuiNpcTextField(1, this, this.field_146289_q, this.guiLeft + 45, this.guiTop + 10, 50, 20, String.format("%.0f", Float.valueOf(this.stats.pDamage)) + ""));
        this.getTextField(1).setFloatsOnly();
        this.getTextField(1).setMinMaxDefaultFloat(0.0f, Float.MAX_VALUE, 5.0f);
        int y = this.guiTop + 10;
        int second = this.guiLeft + 110;
        this.addLabel(new GuiNpcLabel(2, "enchantment.arrowKnockback", second, y + 5));
        this.addTextField(new GuiNpcTextField(2, this, this.field_146289_q, second + 40, y, 50, 20, this.stats.pImpact + ""));
        this.getTextField((int)2).integersOnly = true;
        this.getTextField(2).setMinMaxDefault(0, Integer.MAX_VALUE, 0);
        this.addButton(new GuiNpcButton(6, this.guiLeft + 220, this.guiTop + 10, 60, 20, new String[]{"stats.noglow", "stats.glows"}, this.stats.pGlows ? 1 : 0));
        this.addLabel(new GuiNpcLabel(3, "stats.size", this.guiLeft + 5, (y += 30) + 5));
        this.addTextField(new GuiNpcTextField(3, this, this.field_146289_q, this.guiLeft + 45, y, 50, 20, this.stats.pSize + ""));
        this.getTextField((int)3).integersOnly = true;
        this.getTextField(3).setMinMaxDefault(1, Integer.MAX_VALUE, 10);
        this.addLabel(new GuiNpcLabel(4, "stats.speed", second, y + 5));
        this.addTextField(new GuiNpcTextField(4, this, this.field_146289_q, second + 40, y, 50, 20, this.stats.pSpeed + ""));
        this.getTextField((int)4).integersOnly = true;
        this.getTextField(4).setMinMaxDefault(1, Integer.MAX_VALUE, 10);
        this.addLabel(new GuiNpcLabel(5, "stats.hasgravity", this.guiLeft + 5, (y += 30) + 5));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 80, y, 40, 20, new String[]{"gui.no", "gui.yes"}, this.stats.pPhysics ? 1 : 0));
        if (!this.stats.pPhysics) {
            this.addButton(new GuiNpcButton(1, this.guiLeft + 125, y, 60, 20, new String[]{"gui.constant", "gui.accelerate"}, this.stats.pXlr8 ? 1 : 0));
        }
        this.addLabel(new GuiNpcLabel(6, "stats.explosive", this.guiLeft + 5, (y += 30) + 5));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 80, y, 40, 20, new String[]{"gui.no", "gui.yes"}, this.stats.pExplode ? 1 : 0));
        if (this.stats.pExplode) {
            this.addButton(new GuiNpcButton(3, this.guiLeft + 125, y, 60, 20, new String[]{"gui.none", "gui.small", "gui.medium", "gui.large"}, this.stats.pArea));
        }
        this.addLabel(new GuiNpcLabel(7, "stats.rangedeffect", this.guiLeft + 210, y + 5));
        this.addButton(new GuiButtonBiDirectional(4, this.guiLeft + 280, y, 100, 20, EnumPotionType.getLangKeys(), this.stats.pEffect.ordinal()));
        if (this.stats.pEffect != EnumPotionType.None) {
            internalY = y + 30;
            if (this.stats.pEffect == EnumPotionType.Manual) {
                this.addLabel(new GuiNpcLabel(110, "effect.potionid", this.guiLeft + 210, internalY + 5));
                this.addTextField(new GuiNpcTextField(11, this, this.field_146289_q, this.guiLeft + 330, internalY, 52, 20, this.stats.pManualId + ""));
                this.getTextField((int)11).integersOnly = true;
                this.getTextField(11).setMinMaxDefault(0, Integer.MAX_VALUE, 0);
                internalY += 30;
            }
            this.addLabel(new GuiNpcLabel(50, "gui.time", this.guiLeft + 210, internalY + 5));
            this.addTextField(new GuiNpcTextField(5, this, this.field_146289_q, this.guiLeft + 330, internalY, 52, 20, this.stats.pDur + ""));
            this.getTextField((int)5).integersOnly = true;
            this.getTextField(5).setMinMaxDefault(1, Integer.MAX_VALUE, 5);
            if (this.stats.pEffect == EnumPotionType.Fire) {
                this.addLabel(new GuiNpcLabel(100, "stats.burnItem", this.guiLeft + 210, (internalY += 30) + 5));
                this.addButton(new GuiNpcButtonYesNo(100, this.guiLeft + 330, internalY, 52, 20, this.stats.pBurnItem));
            } else {
                this.addLabel(new GuiNpcLabel(70, "stats.amplify", this.guiLeft + 210, (internalY += 30) + 5));
                if (this.stats.pEffect == EnumPotionType.Manual) {
                    this.addTextField(new GuiNpcTextField(10, this, this.field_146289_q, this.guiLeft + 280, internalY, 52, 20, this.stats.pEffAmp + ""));
                    this.getTextField((int)10).integersOnly = true;
                    this.getTextField(10).setMinMaxDefault(0, 255, 0);
                } else {
                    this.addButton(new GuiButtonBiDirectional(10, this.guiLeft + 280, internalY, 52, 20, new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}, this.stats.pEffAmp));
                }
            }
        }
        this.addLabel(new GuiNpcLabel(8, "stats.trail", this.guiLeft + 5, (y += 30) + 5));
        this.addButton(new GuiNpcButton(5, this.guiLeft + 80, y, 75, 20, this.trailNames, this.stats.pTrail.ordinal()));
        if (this.stats.pTrail == EnumParticleType.Custom) {
            internalY = y + 30;
            this.addLabel(new GuiNpcLabel(90, "trail.custom", this.guiLeft + 5, internalY + 5));
            this.addButton(new GuiNpcButton(90, this.guiLeft + 80, internalY, 75, 20, "gui.edit"));
        }
        this.addButton(new GuiNpcButton(7, this.guiLeft + this.xSize - 40, this.guiTop + 10, 30, 20, new String[]{"2D", "3D"}, this.stats.pRender3D ? 1 : 0));
        if (this.stats.pRender3D) {
            this.addLabel(new GuiNpcLabel(10, "stats.spin", this.guiLeft + this.xSize - 80, this.guiTop + 45));
            this.addButton(new GuiNpcButton(8, this.guiLeft + this.xSize - 40, this.guiTop + 40, 30, 20, new String[]{"gui.no", "gui.yes"}, this.stats.pSpin ? 1 : 0));
            this.addLabel(new GuiNpcLabel(11, "stats.stick", this.guiLeft + this.xSize - 80, this.guiTop + 75));
            this.addButton(new GuiNpcButton(9, this.guiLeft + this.xSize - 40, this.guiTop + 70, 30, 20, new String[]{"gui.no", "gui.yes"}, this.stats.pStick ? 1 : 0));
        }
        this.addButton(new GuiNpcButton(66, this.guiLeft + this.xSize - 50, this.guiTop + 190, 40, 20, "gui.done"));
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == 1) {
            this.stats.pDamage = (float)Math.floor(Float.parseFloat(textfield.func_146179_b()));
        } else if (textfield.id == 2) {
            this.stats.pImpact = textfield.getInteger();
        } else if (textfield.id == 3) {
            this.stats.pSize = textfield.getInteger();
        } else if (textfield.id == 4) {
            this.stats.pSpeed = textfield.getInteger();
        } else if (textfield.id == 5) {
            this.stats.pDur = textfield.getInteger();
        } else if (textfield.id == 10) {
            this.stats.pEffAmp = textfield.getInteger();
        } else if (textfield.id == 11) {
            this.stats.pManualId = textfield.getInteger();
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 0) {
            this.stats.pPhysics = button.getValue() == 1;
            this.func_73866_w_();
        }
        if (button.field_146127_k == 1) {
            boolean bl = this.stats.pXlr8 = button.getValue() == 1;
        }
        if (button.field_146127_k == 2) {
            this.stats.pExplode = button.getValue() == 1;
            this.func_73866_w_();
        }
        if (button.field_146127_k == 3) {
            this.stats.pArea = button.getValue();
        }
        if (button.field_146127_k == 4) {
            EnumPotionType newType = EnumPotionType.fromOrdinal(button.getValue());
            if (this.stats.pEffect == EnumPotionType.Manual && newType != EnumPotionType.Manual) {
                this.stats.pEffAmp = 0;
            }
            this.stats.pEffect = newType;
            this.func_73866_w_();
        }
        if (button.field_146127_k == 5) {
            this.stats.pTrail = EnumParticleType.values()[button.getValue()];
            this.func_73866_w_();
        }
        if (button.field_146127_k == 90) {
            this.setSubGui(new SubGuiScriptParticle(this.stats.pCustom));
        }
        if (button.field_146127_k == 6) {
            boolean bl = this.stats.pGlows = button.getValue() == 1;
        }
        if (button.field_146127_k == 7) {
            this.stats.pRender3D = button.getValue() == 1;
            this.func_73866_w_();
        }
        if (button.field_146127_k == 8) {
            boolean bl = this.stats.pSpin = button.getValue() == 1;
        }
        if (button.field_146127_k == 9) {
            boolean bl = this.stats.pStick = button.getValue() == 1;
        }
        if (button.field_146127_k == 10) {
            this.stats.pEffAmp = button.getValue();
        }
        if (button.field_146127_k == 100) {
            this.stats.pBurnItem = button.getValue() == 1;
            this.func_73866_w_();
        }
        if (button.field_146127_k == 66) {
            this.close();
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
    }
}

