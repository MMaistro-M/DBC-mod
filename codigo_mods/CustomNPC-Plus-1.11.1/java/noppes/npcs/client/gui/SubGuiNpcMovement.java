/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.DataAI;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumAnimation;
import noppes.npcs.constants.EnumMovingType;
import noppes.npcs.constants.EnumStandingType;

public class SubGuiNpcMovement
extends SubGuiInterface
implements ITextfieldListener {
    private DataAI ai;

    public SubGuiNpcMovement(DataAI ai) {
        this.ai = ai;
        this.setBackground("menubg.png");
        this.xSize = 256;
        this.ySize = 216;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 4;
        this.addLabel(new GuiNpcLabel(0, "movement.type", this.guiLeft + 4, y + 5));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 80, y, 100, 20, EnumMovingType.names(), this.ai.movingType.ordinal()));
        this.addButton(new GuiNpcButton(15, this.guiLeft + 80, y += 22, 100, 20, new String[]{"movement.ground", "movement.flying"}, this.ai.movementType));
        if (this.ai.movementType == 1) {
            this.addLabel(new GuiNpcLabel(18, "fly.speed", this.guiLeft + 4, y + 140));
            this.addTextField(new GuiNpcTextField(18, this, this.guiLeft + 80, y + 135, 40, 20, this.ai.flySpeed + ""));
            this.getTextField((int)18).doublesOnly = true;
            this.getTextField(18).setMinMaxDefaultDouble(0.0, Double.MAX_VALUE, 1.0);
            this.addLabel(new GuiNpcLabel(19, "fly.gravity", this.guiLeft + 4, y + 160));
            this.addTextField(new GuiNpcTextField(19, this, this.guiLeft + 80, y + 155, 40, 20, this.ai.flyGravity + ""));
            this.getTextField((int)19).doublesOnly = true;
            this.getTextField(19).setMinMaxDefaultDouble(0.0, 1.0, 0.0);
            this.addLabel(new GuiNpcLabel(20, "Limit Height", this.guiLeft + 150, y + 116));
            this.addButton(new GuiNpcButton(20, this.guiLeft + 210, y + 110, 40, 20, new String[]{"gui.no", "gui.yes"}, this.ai.hasFlyLimit ? 1 : 0));
            if (this.ai.hasFlyLimit) {
                this.addLabel(new GuiNpcLabel(21, "Height", this.guiLeft + 150, y + 140));
                this.addTextField(new GuiNpcTextField(21, this, this.guiLeft + 185, y + 135, 40, 20, this.ai.flyHeightLimit + ""));
                this.getTextField((int)21).integersOnly = true;
                this.getTextField(21).setMinMaxDefaultDouble(0.0, Double.MAX_VALUE, 1.0);
            }
        }
        if (this.ai.movingType == EnumMovingType.Wandering) {
            this.addTextField(new GuiNpcTextField(4, this, this.guiLeft + 100, y += 22, 40, 20, this.ai.walkingRange + ""));
            this.getTextField((int)4).integersOnly = true;
            this.getTextField(4).setMinMaxDefault(0, Integer.MAX_VALUE, 5);
            this.addLabel(new GuiNpcLabel(4, "gui.range", this.guiLeft + 4, y + 5));
            this.addButton(new GuiNpcButton(5, this.guiLeft + 100, y += 22, 50, 20, new String[]{"gui.no", "gui.yes"}, this.ai.npcInteracting ? 1 : 0));
            this.addLabel(new GuiNpcLabel(5, "movement.wanderinteract", this.guiLeft + 4, y + 5));
        } else if (this.ai.movingType == EnumMovingType.Standing) {
            this.addTextField(new GuiNpcTextField(7, this, this.guiLeft + 99, y += 22, 24, 20, (int)this.ai.bodyOffsetX + ""));
            this.addLabel(new GuiNpcLabel(17, "spawner.posoffset", this.guiLeft + 4, y + 5));
            this.addLabel(new GuiNpcLabel(7, "X:", this.guiLeft + 115, y + 5));
            this.getTextField((int)7).integersOnly = true;
            this.getTextField(7).setMinMaxDefault(0, 10, 5);
            this.addLabel(new GuiNpcLabel(8, "Y:", this.guiLeft + 125, y + 5));
            this.addTextField(new GuiNpcTextField(8, this, this.guiLeft + 135, y, 24, 20, (int)this.ai.bodyOffsetY + ""));
            this.getTextField((int)8).integersOnly = true;
            this.getTextField(8).setMinMaxDefault(0, 10, 5);
            this.addLabel(new GuiNpcLabel(9, "Z:", this.guiLeft + 161, y + 5));
            this.addTextField(new GuiNpcTextField(9, this, this.guiLeft + 171, y, 24, 20, (int)this.ai.bodyOffsetZ + ""));
            this.getTextField((int)9).integersOnly = true;
            this.getTextField(9).setMinMaxDefault(0, 10, 5);
            this.addButton(new GuiNpcButton(3, this.guiLeft + 80, y += 22, 100, 20, new String[]{"stats.normal", "movement.sitting", "movement.lying", "movement.sneaking", "movement.dancing", "movement.aiming", "movement.crawling", "movement.hug"}, this.ai.animationType.ordinal()));
            this.addLabel(new GuiNpcLabel(3, "movement.animation", this.guiLeft + 4, y + 5));
            if (this.ai.animationType != EnumAnimation.LYING) {
                this.addButton(new GuiNpcButton(4, this.guiLeft + 80, y += 22, 80, 20, new String[]{"movement.body", "movement.manual", "movement.stalking", "movement.head"}, this.ai.standingType.ordinal()));
                this.addLabel(new GuiNpcLabel(1, "movement.rotation", this.guiLeft + 4, y + 5));
            } else {
                this.addTextField(new GuiNpcTextField(5, this, this.guiLeft + 99, y += 22, 40, 20, this.ai.orientation + ""));
                this.getTextField((int)5).integersOnly = true;
                this.getTextField(5).setMinMaxDefault(0, 359, 0);
                this.addLabel(new GuiNpcLabel(6, "movement.rotation", this.guiLeft + 4, y + 5));
                this.addLabel(new GuiNpcLabel(5, "(0-359)", this.guiLeft + 142, y + 5));
            }
            if (this.ai.standingType == EnumStandingType.NoRotation || this.ai.standingType == EnumStandingType.HeadRotation) {
                this.addTextField(new GuiNpcTextField(5, this, this.guiLeft + 165, y, 40, 20, this.ai.orientation + ""));
                this.getTextField((int)5).integersOnly = true;
                this.getTextField(5).setMinMaxDefault(0, 359, 0);
                this.addLabel(new GuiNpcLabel(5, "(0-359)", this.guiLeft + 207, y + 5));
            }
        }
        if (this.ai.movingType != EnumMovingType.Standing) {
            this.addButton(new GuiNpcButton(12, this.guiLeft + 80, y += 22, 100, 20, new String[]{"stats.normal", "movement.sneaking", "movement.aiming", "movement.dancing", "movement.crawling", "movement.hug"}, this.ai.animationType.getWalkingAnimation()));
            this.addLabel(new GuiNpcLabel(12, "movement.animation", this.guiLeft + 4, y + 5));
        }
        if (this.ai.movingType == EnumMovingType.MovingPath) {
            this.addButton(new GuiNpcButton(8, this.guiLeft + 80, y += 22, 80, 20, new String[]{"ai.looping", "ai.backtracking"}, this.ai.movingPattern));
            this.addLabel(new GuiNpcLabel(8, "movement.name", this.guiLeft + 4, y + 5));
            this.addButton(new GuiNpcButton(9, this.guiLeft + 80, y += 22, 80, 20, new String[]{"gui.no", "gui.yes"}, this.ai.movingPause ? 1 : 0));
            this.addLabel(new GuiNpcLabel(9, "movement.pauses", this.guiLeft + 4, y + 5));
        }
        this.addButton(new GuiNpcButton(13, this.guiLeft + 100, y += 22, 50, 20, new String[]{"gui.no", "gui.yes"}, this.ai.stopAndInteract ? 1 : 0));
        this.addLabel(new GuiNpcLabel(13, "movement.stopinteract", this.guiLeft + 4, y + 5));
        this.addTextField(new GuiNpcTextField(14, this, this.guiLeft + 80, y += 22, 50, 18, this.ai.getWalkingSpeed() + ""));
        this.getTextField((int)14).integersOnly = true;
        this.getTextField(14).setMinMaxDefault(0, Integer.MAX_VALUE, 4);
        this.addLabel(new GuiNpcLabel(14, "stats.walkspeed", this.guiLeft + 5, y + 5));
        this.addButton(new GuiNpcButton(66, this.guiLeft + 190, this.guiTop + 190, 60, 20, "gui.done"));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 0) {
            this.ai.movingType = EnumMovingType.values()[button.getValue()];
            if (this.ai.movingType != EnumMovingType.Standing) {
                this.ai.animationType = EnumAnimation.NONE;
                this.ai.standingType = EnumStandingType.RotateBody;
                this.ai.bodyOffsetZ = 5.0f;
                this.ai.bodyOffsetY = 5.0f;
                this.ai.bodyOffsetX = 5.0f;
            }
            this.func_73866_w_();
        } else if (button.field_146127_k == 3) {
            this.ai.animationType = EnumAnimation.values()[button.getValue()];
            this.func_73866_w_();
        } else if (button.field_146127_k == 4) {
            this.ai.standingType = EnumStandingType.values()[button.getValue()];
            this.func_73866_w_();
        } else if (button.field_146127_k == 5) {
            this.ai.npcInteracting = button.getValue() == 1;
        } else if (button.field_146127_k == 8) {
            this.ai.movingPattern = button.getValue();
        } else if (button.field_146127_k == 9) {
            this.ai.movingPause = button.getValue() == 1;
        } else if (button.field_146127_k == 12) {
            if (button.getValue() == 0) {
                this.ai.animationType = EnumAnimation.NONE;
            }
            if (button.getValue() == 1) {
                this.ai.animationType = EnumAnimation.SNEAKING;
            }
            if (button.getValue() == 2) {
                this.ai.animationType = EnumAnimation.AIMING;
            }
            if (button.getValue() == 3) {
                this.ai.animationType = EnumAnimation.DANCING;
            }
            if (button.getValue() == 4) {
                this.ai.animationType = EnumAnimation.CRAWLING;
            }
            if (button.getValue() == 5) {
                this.ai.animationType = EnumAnimation.HUG;
            }
        } else if (button.field_146127_k == 13) {
            this.ai.stopAndInteract = button.getValue() == 1;
        } else if (button.field_146127_k == 15) {
            this.ai.movementType = button.getValue();
            this.func_73866_w_();
        } else if (button.field_146127_k == 20) {
            this.ai.hasFlyLimit = button.getValue() == 1;
            this.func_73866_w_();
        } else if (button.field_146127_k == 66) {
            this.close();
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == 7) {
            this.ai.bodyOffsetX = textfield.getInteger();
        } else if (textfield.id == 8) {
            this.ai.bodyOffsetY = textfield.getInteger();
        } else if (textfield.id == 9) {
            this.ai.bodyOffsetZ = textfield.getInteger();
        } else if (textfield.id == 5) {
            this.ai.orientation = textfield.getInteger();
        } else if (textfield.id == 4) {
            this.ai.walkingRange = textfield.getInteger();
        } else if (textfield.id == 14) {
            this.ai.setWalkingSpeed(textfield.getInteger());
        } else if (textfield.id == 18) {
            this.ai.flySpeed = textfield.getDouble();
        } else if (textfield.id == 19) {
            this.ai.flyGravity = textfield.getDouble();
        } else if (textfield.id == 21) {
            this.ai.flyHeightLimit = textfield.getInteger();
        }
    }
}

