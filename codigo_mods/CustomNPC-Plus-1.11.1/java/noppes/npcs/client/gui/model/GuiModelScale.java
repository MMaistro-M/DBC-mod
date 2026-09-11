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
import noppes.npcs.entity.data.ModelScalePart;

public class GuiModelScale
extends GuiModelInterface
implements ISliderListener {
    private GuiScreen parent;
    private int type = 0;
    private int offset = 10;
    private GuiNpcSlider scaleWidth;
    private GuiNpcSlider scaleHeight;
    private GuiNpcSlider scaleDepth;

    public GuiModelScale(GuiScreen parent, ModelData data, EntityCustomNpc npc) {
        super(npc);
        this.parent = parent;
        this.xOffset = 100;
        this.ySize = 230;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 2;
        this.addLabel(new GuiNpcLabel(20, "Head", this.guiLeft + 55, y + 5, 0xFFFFFF));
        if (this.type == 0) {
            this.drawSlider(y, this.playerdata.modelScale.head);
            y += 88;
        } else {
            this.addButton(new GuiNpcButton(0, this.guiLeft + 110, y, 60, 20, "Edit"));
            y += 24;
        }
        this.addLabel(new GuiNpcLabel(21, "Body", this.guiLeft + 55, y + 5, 0xFFFFFF));
        if (this.type == 1) {
            this.drawSlider(y, this.playerdata.modelScale.body);
            y += 88;
        } else {
            this.addButton(new GuiNpcButton(1, this.guiLeft + 110, y, 60, 20, "Edit"));
            y += 24;
        }
        this.addLabel(new GuiNpcLabel(22, "Arms", this.guiLeft + 55, y + 5, 0xFFFFFF));
        if (this.type == 2) {
            this.drawSlider(y, this.playerdata.modelScale.arms);
            y += 88;
        } else {
            this.addButton(new GuiNpcButton(2, this.guiLeft + 110, y, 60, 20, "Edit"));
            y += 24;
        }
        this.addLabel(new GuiNpcLabel(23, "Legs", this.guiLeft + 55, y + 5, 0xFFFFFF));
        if (this.type == 3) {
            this.drawSlider(y, this.playerdata.modelScale.legs);
            y += 88;
        } else {
            this.addButton(new GuiNpcButton(3, this.guiLeft + 110, y, 60, 20, "Edit"));
            y += 24;
        }
    }

    private void drawSlider(int y, ModelScalePart config) {
        this.addLabel(new GuiNpcLabel(10, "Width", this.guiLeft - 25 + this.offset, (y += 20) + 5, 0xFFFFFF));
        this.scaleWidth = new GuiNpcSlider(this, 10, this.guiLeft + 50 + this.offset, y, config.scaleX - 0.5f);
        this.addSlider(this.scaleWidth);
        this.addButton(new GuiNpcButton(170, this.guiLeft + 8 + this.offset, y, 40, 20, "Reset"));
        this.addLabel(new GuiNpcLabel(11, "Height", this.guiLeft - 25 + this.offset, (y += 22) + 5, 0xFFFFFF));
        this.scaleHeight = new GuiNpcSlider(this, 11, this.guiLeft + 50 + this.offset, y, config.scaleY - 0.5f);
        this.addSlider(this.scaleHeight);
        this.addButton(new GuiNpcButton(171, this.guiLeft + 8 + this.offset, y, 40, 20, "Reset"));
        this.addLabel(new GuiNpcLabel(12, "Depth", this.guiLeft - 25 + this.offset, (y += 22) + 5, 0xFFFFFF));
        this.scaleDepth = new GuiNpcSlider(this, 12, this.guiLeft + 50 + this.offset, y, config.scaleZ - 0.5f);
        this.addSlider(this.scaleDepth);
        this.addButton(new GuiNpcButton(172, this.guiLeft + 8 + this.offset, y, 40, 20, "Reset"));
    }

    @Override
    protected void func_146284_a(GuiButton btn) {
        super.func_146284_a(btn);
        if (btn.field_146127_k < 4) {
            this.type = btn.field_146127_k;
            this.func_73866_w_();
        } else {
            ModelScalePart config = this.playerdata.modelScale.head;
            if (this.type == 1) {
                config = this.playerdata.modelScale.body;
            } else if (this.type == 2) {
                config = this.playerdata.modelScale.arms;
            } else if (this.type == 3) {
                config = this.playerdata.modelScale.legs;
            }
            if (btn.field_146127_k == 170) {
                config.scaleX = 1.0f;
                this.scaleWidth.sliderValue = 0.5f;
                int percent = (int)(50.0f + this.scaleWidth.sliderValue * 100.0f);
                this.scaleWidth.setString(percent + "%");
                this.npc.updateHitbox();
            } else if (btn.field_146127_k == 171) {
                config.scaleY = 1.0f;
                this.scaleHeight.sliderValue = 0.5f;
                int percent = (int)(50.0f + this.scaleHeight.sliderValue * 100.0f);
                this.scaleHeight.setString(percent + "%");
                this.npc.updateHitbox();
            } else if (btn.field_146127_k == 172) {
                config.scaleZ = 1.0f;
                this.scaleDepth.sliderValue = 0.5f;
                int percent = (int)(50.0f + this.scaleDepth.sliderValue * 100.0f);
                this.scaleDepth.setString(percent + "%");
                this.npc.updateHitbox();
            }
        }
    }

    @Override
    public void close() {
        this.field_146297_k.func_147108_a(this.parent);
    }

    @Override
    public void mouseDragged(GuiNpcSlider slider) {
        int percent = (int)(50.0f + slider.sliderValue * 100.0f);
        slider.setString(percent + "%");
        ModelScalePart config = this.playerdata.modelScale.head;
        if (this.type == 1) {
            config = this.playerdata.modelScale.body;
        } else if (this.type == 2) {
            config = this.playerdata.modelScale.arms;
        } else if (this.type == 3) {
            config = this.playerdata.modelScale.legs;
        }
        if (slider.field_146127_k == 10) {
            config.scaleX = slider.sliderValue + 0.5f;
        }
        if (slider.field_146127_k == 11) {
            config.scaleY = slider.sliderValue + 0.5f;
        }
        if (slider.field_146127_k == 12) {
            config.scaleZ = slider.sliderValue + 0.5f;
        }
        this.npc.updateHitbox();
    }

    @Override
    public void mousePressed(GuiNpcSlider slider) {
        this.allowRotate = false;
    }

    @Override
    public void mouseReleased(GuiNpcSlider slider) {
        this.allowRotate = true;
    }
}

