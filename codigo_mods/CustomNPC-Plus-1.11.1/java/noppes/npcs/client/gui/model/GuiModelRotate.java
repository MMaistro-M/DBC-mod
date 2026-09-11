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
import noppes.npcs.client.gui.util.GuiModelInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcSlider;
import noppes.npcs.client.gui.util.ISliderListener;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.data.ModelData;
import noppes.npcs.entity.data.ModelRotatePart;

public class GuiModelRotate
extends GuiModelInterface
implements ISliderListener {
    private GuiScreen parent;
    private int type = 6;
    private ModelRotatePart part;
    private GuiNpcSlider rotateX;
    private GuiNpcSlider rotateY;
    private GuiNpcSlider rotateZ;

    public GuiModelRotate(GuiScreen parent, ModelData data, EntityCustomNpc npc) {
        super(npc);
        this.parent = parent;
        this.xOffset = 100;
        this.ySize = 230;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop;
        this.addLabel(new GuiNpcLabel(27, "movement.rotation", this.guiLeft + 55, y + 5, 0xFFFFFF));
        this.addButton(new GuiNpcButton(14, this.guiLeft + 110, y, 60, 20, new String[]{"gui.enabled", "gui.disabled"}, this.playerdata.enableRotation ? 0 : 1));
        y += 24;
        if (this.playerdata.enableRotation) {
            this.addLabel(new GuiNpcLabel(26, "gui.settings", this.guiLeft + 55, y + 5, 0xFFFFFF));
            if (this.type == 6) {
                this.addButton(new GuiNpcButton(30, this.guiLeft + 120, y += 14, 60, 20, new String[]{"gui.yes", "gui.no"}, this.playerdata.rotation.whileStanding ? 0 : 1));
                this.addLabel(new GuiNpcLabel(30, "puppet.standing", this.guiLeft + 30, y + 5, 0xFFFFFF));
                this.addButton(new GuiNpcButton(31, this.guiLeft + 120, y += 22, 60, 20, new String[]{"gui.yes", "gui.no"}, this.playerdata.rotation.whileMoving ? 0 : 1));
                this.addLabel(new GuiNpcLabel(31, "puppet.walking", this.guiLeft + 30, y + 5, 0xFFFFFF));
                this.addButton(new GuiNpcButton(32, this.guiLeft + 120, y += 22, 60, 20, new String[]{"gui.yes", "gui.no"}, this.playerdata.rotation.whileAttacking ? 0 : 1));
                this.addLabel(new GuiNpcLabel(32, "puppet.attacking", this.guiLeft + 30, y + 5, 0xFFFFFF));
                y += 24;
            } else {
                this.addButton(new GuiNpcButton(6, this.guiLeft + 110, y, 60, 20, "selectServer.edit"));
                y += 24;
            }
            this.addLabel(new GuiNpcLabel(20, "model.head", this.guiLeft + 55, y + 5, 0xFFFFFF));
            if (this.type == 0) {
                this.drawSlider(y, this.playerdata.rotation.head);
                y += 90;
            } else {
                this.addButton(new GuiNpcButton(0, this.guiLeft + 110, y, 60, 20, "selectServer.edit"));
                y += 24;
            }
            this.addLabel(new GuiNpcLabel(21, "model.body", this.guiLeft + 55, y + 5, 0xFFFFFF));
            if (this.type == 1) {
                this.drawSlider(y, this.playerdata.rotation.body);
                y += 90;
            } else {
                this.addButton(new GuiNpcButton(1, this.guiLeft + 110, y, 60, 20, "selectServer.edit"));
                y += 24;
            }
            this.addLabel(new GuiNpcLabel(22, "model.larm", this.guiLeft + 55, y + 5, 0xFFFFFF));
            if (this.type == 2) {
                this.drawSlider(y, this.playerdata.rotation.larm);
                y += 90;
            } else {
                this.addButton(new GuiNpcButton(2, this.guiLeft + 110, y, 60, 20, "selectServer.edit"));
                y += 24;
            }
            this.addLabel(new GuiNpcLabel(23, "model.rarm", this.guiLeft + 55, y + 5, 0xFFFFFF));
            if (this.type == 3) {
                this.drawSlider(y, this.playerdata.rotation.rarm);
                y += 90;
            } else {
                this.addButton(new GuiNpcButton(3, this.guiLeft + 110, y, 60, 20, "selectServer.edit"));
                y += 24;
            }
            this.addLabel(new GuiNpcLabel(24, "model.lleg", this.guiLeft + 55, y + 5, 0xFFFFFF));
            if (this.type == 4) {
                this.drawSlider(y, this.playerdata.rotation.lleg);
                y += 90;
            } else {
                this.addButton(new GuiNpcButton(4, this.guiLeft + 110, y, 60, 20, "selectServer.edit"));
                y += 24;
            }
            this.addLabel(new GuiNpcLabel(25, "model.rleg", this.guiLeft + 55, y + 5, 0xFFFFFF));
            if (this.type == 5) {
                this.drawSlider(y, this.playerdata.rotation.rleg);
                y += 90;
            } else {
                this.addButton(new GuiNpcButton(5, this.guiLeft + 110, y, 60, 20, "selectServer.edit"));
                y += 24;
            }
        }
    }

    private void drawSlider(int y, ModelRotatePart config) {
        this.part = config;
        this.addButton(new GuiNpcButton(29, this.guiLeft + 100, y, 80, 20, new String[]{"gui.enabled", "gui.disabled"}, config.disabled ? 1 : 0));
        this.addLabel(new GuiNpcLabel(10, "X", this.guiLeft, (y += 22) + 5, 0xFFFFFF));
        this.rotateX = new GuiNpcSlider(this, 10, this.guiLeft + 50, y, config.rotationX + 0.5f);
        this.addSlider(this.rotateX);
        this.addButton(new GuiNpcButton(170, this.guiLeft + 8, y, 40, 20, "Reset"));
        this.addLabel(new GuiNpcLabel(11, "Y", this.guiLeft, (y += 22) + 5, 0xFFFFFF));
        this.rotateY = new GuiNpcSlider(this, 11, this.guiLeft + 50, y, config.rotationY + 0.5f);
        this.addSlider(this.rotateY);
        this.addButton(new GuiNpcButton(171, this.guiLeft + 8, y, 40, 20, "Reset"));
        this.addLabel(new GuiNpcLabel(12, "Z", this.guiLeft, (y += 22) + 5, 0xFFFFFF));
        this.rotateZ = new GuiNpcSlider(this, 12, this.guiLeft + 50, y, config.rotationZ + 0.5f);
        this.addSlider(this.rotateZ);
        this.addButton(new GuiNpcButton(172, this.guiLeft + 8, y, 40, 20, "Reset"));
    }

    @Override
    protected void func_146284_a(GuiButton btn) {
        super.func_146284_a(btn);
        if (btn.field_146127_k < 7) {
            this.type = btn.field_146127_k;
            this.func_73866_w_();
        }
        if (!(btn instanceof GuiNpcButton)) {
            return;
        }
        GuiNpcButton button = (GuiNpcButton)btn;
        if (btn.field_146127_k == 14) {
            this.playerdata.enableRotation = button.getValue() == 0;
            this.func_73866_w_();
        }
        if (btn.field_146127_k == 29) {
            boolean bl = this.part.disabled = button.getValue() == 1;
        }
        if (btn.field_146127_k == 30) {
            boolean bl = this.playerdata.rotation.whileStanding = button.getValue() == 0;
        }
        if (btn.field_146127_k == 31) {
            boolean bl = this.playerdata.rotation.whileMoving = button.getValue() == 0;
        }
        if (btn.field_146127_k == 32) {
            boolean bl = this.playerdata.rotation.whileAttacking = button.getValue() == 0;
        }
        if (btn.field_146127_k == 170 || btn.field_146127_k == 171 || btn.field_146127_k == 172) {
            if (btn.field_146127_k == 170) {
                this.part.rotationX = 0.0f;
                this.rotateX.sliderValue = 0.5f;
                int percent = (int)(this.rotateX.sliderValue * 360.0f);
                this.rotateX.setString(percent + "%");
            } else if (btn.field_146127_k == 171) {
                this.part.rotationY = 0.0f;
                this.rotateY.sliderValue = 0.5f;
                int percent = (int)(this.rotateY.sliderValue * 360.0f);
                this.rotateY.setString(percent + "%");
            } else {
                this.part.rotationZ = 0.0f;
                this.rotateZ.sliderValue = 0.5f;
                int percent = (int)(this.rotateZ.sliderValue * 360.0f);
                this.rotateZ.setString(percent + "%");
            }
            this.npc.updateHitbox();
        }
    }

    @Override
    public void close() {
        this.field_146297_k.func_147108_a(this.parent);
    }

    @Override
    public void mouseDragged(GuiNpcSlider slider) {
        int percent = (int)(slider.sliderValue * 360.0f);
        slider.setString(percent + "%");
        if (slider.field_146127_k == 10) {
            this.part.rotationX = slider.sliderValue - 0.5f;
        }
        if (slider.field_146127_k == 11) {
            this.part.rotationY = slider.sliderValue - 0.5f;
        }
        if (slider.field_146127_k == 12) {
            this.part.rotationZ = slider.sliderValue - 0.5f;
        }
        this.npc.updateHitbox();
    }

    @Override
    public void mousePressed(GuiNpcSlider slider) {
    }

    @Override
    public void mouseReleased(GuiNpcSlider slider) {
    }
}

