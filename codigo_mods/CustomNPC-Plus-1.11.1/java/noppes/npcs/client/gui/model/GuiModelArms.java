/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 */
package noppes.npcs.client.gui.model;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import noppes.npcs.client.gui.model.GuiModelColor;
import noppes.npcs.client.gui.util.GuiModelInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.data.ModelPartData;

public class GuiModelArms
extends GuiModelInterface {
    private final String[] arrArm = new String[]{"gui.no", "Both", "Right", "Left"};
    private final String[] arrArmwear = new String[]{"gui.no", "Both", "Left", "Right"};
    private final String[] arrSolidArmwear = new String[]{"gui.no", "Both", "Left", "Right"};
    private final String[] arrClaws = new String[]{"gui.no", "Both", "Left", "Right"};
    private GuiScreen parent;

    public GuiModelArms(GuiScreen parent, EntityCustomNpc npc) {
        super(npc);
        this.parent = parent;
        this.xOffset = 60;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 20;
        this.addButton(new GuiNpcButton(30, this.guiLeft + 50, y += 22, 70, 20, this.arrArm, (int)this.playerdata.hideArms));
        this.addLabel(new GuiNpcLabel(30, "Hide", this.guiLeft, y + 5, 0xFFFFFF));
        if (this.npc.display.modelType == 1 || this.npc.display.modelType == 2) {
            this.addButton(new GuiNpcButton(8, this.guiLeft + 50, y += 22, 70, 20, this.arrArmwear, (int)this.playerdata.armwear));
            this.addLabel(new GuiNpcLabel(8, "Armwear", this.guiLeft, y + 5, 0xFFFFFF));
            this.addButton(new GuiNpcButton(9, this.guiLeft + 50, y += 22, 70, 20, this.arrSolidArmwear, (int)this.playerdata.solidArmwear));
            this.addLabel(new GuiNpcLabel(9, "Solid", this.guiLeft, y + 5, 0xFFFFFF));
        }
        ModelPartData claws = this.playerdata.getPartData("claws");
        this.addButton(new GuiNpcButton(0, this.guiLeft + 50, y += 22, 70, 20, this.arrClaws, claws == null ? 0 : claws.type + 1));
        this.addLabel(new GuiNpcLabel(0, "Claws", this.guiLeft, y + 5, 0xFFFFFF));
        if (claws != null) {
            this.addButton(new GuiNpcButton(10, this.guiLeft + 122, y, 40, 20, claws.getColor()));
        }
    }

    @Override
    protected void func_146284_a(GuiButton btn) {
        super.func_146284_a(btn);
        GuiNpcButton button = (GuiNpcButton)btn;
        if (button.field_146127_k == 8) {
            this.playerdata.armwear = (byte)button.getValue();
        }
        if (button.field_146127_k == 9) {
            this.playerdata.solidArmwear = (byte)button.getValue();
        }
        if (button.field_146127_k == 30) {
            this.playerdata.hideArms = (byte)button.getValue();
        }
        if (button.field_146127_k == 0) {
            if (button.getValue() == 0) {
                this.playerdata.removePart("claws");
            } else {
                ModelPartData data = this.playerdata.getOrCreatePart("claws");
                data.type = (byte)(button.getValue() - 1);
            }
            this.func_73866_w_();
        }
        if (button.field_146127_k == 10) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiModelColor(this, this.playerdata.getPartData("claws"), this.npc));
        }
    }

    @Override
    public void close() {
        this.field_146297_k.func_147108_a(this.parent);
    }
}

