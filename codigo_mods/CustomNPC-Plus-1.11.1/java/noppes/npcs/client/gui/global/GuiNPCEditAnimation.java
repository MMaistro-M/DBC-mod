/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 */
package noppes.npcs.client.gui.global;

import java.util.HashMap;
import java.util.Map;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.animation.AnimationSavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import noppes.npcs.client.gui.SubGuiAnimationFrame;
import noppes.npcs.client.gui.SubGuiAnimationOptions;
import noppes.npcs.client.gui.SubGuiColorSelector;
import noppes.npcs.client.gui.util.GuiModelInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcSlider;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.GuiTexturedButton;
import noppes.npcs.client.gui.util.ISliderListener;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumAnimationPart;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.controllers.data.AnimationData;
import noppes.npcs.controllers.data.Frame;
import noppes.npcs.controllers.data.FramePart;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.util.ValueUtil;

public class GuiNPCEditAnimation
extends GuiModelInterface
implements ITextfieldListener,
ISliderListener,
ISubGuiListener {
    private final Animation animation;
    private static int partEditMode;
    private static int sliderSelection;
    private static Frame copiedFrame;
    private EnumAnimationPart editingPart = EnumAnimationPart.HEAD;
    private int frameIndex = 0;
    public boolean playingAnimation = false;
    private Frame prevFrame;
    private final GuiScreen parent;
    private long prevTick;
    private final GuiNpcSlider[] rotationSliders = new GuiNpcSlider[3];
    private final GuiNpcSlider[] pivotSliders = new GuiNpcSlider[3];
    private final GuiNpcSlider frameSlider;
    private int frameOffset;
    private final int visibleFrames = 25;
    private boolean overrideFrame = false;

    public GuiNPCEditAnimation(GuiScreen parent, Animation animation, EntityNPCInterface npc) {
        super((EntityCustomNpc)npc);
        this.parent = parent;
        this.followMouse = false;
        this.xOffset = 0;
        this.yOffset = -21;
        this.animation = animation;
        AnimationData data = npc.display.animationData;
        data.setAnimation(animation);
        data.setEnabled(true);
        int bodyPartX = 280;
        int bodyPartY = -5;
        for (int i = 0; i < 3; ++i) {
            this.rotationSliders[i] = new GuiNpcSlider(this, 90 + i, this.guiLeft + bodyPartX, this.guiTop + bodyPartY + 115 + 20 * i, 0.5f);
            this.pivotSliders[i] = new GuiNpcSlider(this, 95 + i, this.guiLeft + bodyPartX, this.guiTop + bodyPartY + 115 + 20 * i, 0.5f);
        }
        this.frameSlider = new GuiNpcSlider(this, 350, 0, 0, 0.0f);
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        if (this.animation == null) {
            return;
        }
        this.frameIndex = !this.animation.frames.isEmpty() ? this.frameIndex % this.animation.frames.size() : 0;
        this.updateSliders();
        Frame editingFrame = this.editingFrame();
        if (editingFrame != null && editingFrame != this.prevFrame && this.prevFrame != null) {
            HashMap<EnumAnimationPart, FramePart> frameParts = this.prevFrame.frameParts;
            for (Map.Entry<EnumAnimationPart, FramePart> entry : frameParts.entrySet()) {
                if (!editingFrame.frameParts.containsKey(entry.getKey())) continue;
                FramePart part = editingFrame.frameParts.get(entry.getKey());
                FramePart prevPart = this.prevFrame.frameParts.get(entry.getKey());
                part.prevRotations = prevPart.prevRotations;
                part.prevPivots = prevPart.prevPivots;
                part.partialRotationTick = prevPart.partialRotationTick;
                part.partialPivotTick = prevPart.partialPivotTick;
            }
        }
        this.prevFrame = editingFrame;
        FramePart editingPart = this.editingPart();
        AnimationData data = this.npc.display.animationData;
        if (!this.playingAnimation) {
            data.setAnimation(new Animation());
            data.animation.smooth = this.animation.smooth;
            data.animation.loop = 0;
            if (editingFrame != null) {
                data.animation.frames.add(editingFrame);
            }
        }
        for (Frame frame : this.animation.frames) {
            frame.parent = this.animation;
            if (!frame.isCustomized()) {
                frame.speed = this.animation.speed;
                frame.smooth = this.animation.smooth;
            }
            for (Map.Entry<EnumAnimationPart, FramePart> entry : frame.frameParts.entrySet()) {
                FramePart part = entry.getValue();
                part.parent = this.animation;
                if (part.isCustomized()) continue;
                part.speed = frame.speed;
                part.smooth = frame.smooth;
            }
        }
        if (this.animation.loop >= this.animation.frames.size()) {
            this.animation.loop = -1;
        }
        if (!this.playingAnimation || this.overrideFrame) {
            this.animation.currentFrame = this.frameIndex;
            this.animation.jumpToCurrentFrame();
            this.overrideFrame = false;
        }
        this.addLabel(new GuiNpcLabel(10, "animation.frames", this.guiLeft + 40, this.guiTop + 176 - 10, 0xFFFFFF));
        this.addButton(new GuiNpcButton(11, this.guiLeft - 10, this.guiTop + 189 - 10, 45, 20, "gui.add"));
        if (!this.animation.frames.isEmpty()) {
            this.addButton(new GuiNpcButton(12, this.guiLeft + 35, this.guiTop + 189 - 10, 45, 20, "gui.remove"));
            this.addButton(new GuiNpcButton(13, this.guiLeft + 80, this.guiTop + 189 - 10, 45, 20, "gui.copy"));
            this.addButton(new GuiNpcButton(14, this.guiLeft - 10, this.guiTop + 210 - 3, 20, 20, "<"));
            this.addTextField(new GuiNpcTextField(15, this, this.guiLeft + 15, this.guiTop + 212 - 3, 20, 17, this.frameIndex + ""));
            this.getTextField((int)15).integersOnly = true;
            this.getTextField(15).setMinMaxDefault(0, this.animation.frames.size() - 1, this.frameIndex);
            this.addButton(new GuiNpcButton(16, this.guiLeft + 40, this.guiTop + 210 - 3, 20, 20, ">"));
        }
        int playPauseX = 330;
        int playPauseY = 10;
        String animTexture = "customnpcs:textures/gui/animation.png";
        if (data.animation != null && data.animation.frames.size() > 0) {
            if (!this.playingAnimation || data.animation.paused) {
                this.addLabel(new GuiNpcLabel(210, data.animation.paused ? "animation.paused" : "animation.stopped", this.guiLeft + playPauseX - 15, this.guiTop + playPauseY + 203, 0xFFFFFF));
                if (data.animation.paused) {
                    this.addLabel(new GuiNpcLabel(211, "", this.guiLeft + playPauseX + 21, this.guiTop + playPauseY + 203, 0xFFFFFF));
                }
                this.addButton(new GuiTexturedButton(200, "", this.guiLeft + playPauseX + 35, this.guiTop + playPauseY + 197, 11, 20, animTexture, 18, 71));
            } else {
                this.addLabel(new GuiNpcLabel(212, "animation.playing", this.guiLeft + playPauseX - 15, this.guiTop + playPauseY + 203, 0xFFFFFF));
                this.addLabel(new GuiNpcLabel(213, "", this.guiLeft + playPauseX + 20, this.guiTop + playPauseY + 203, 0xFFFFFF));
                this.addButton(new GuiTexturedButton(201, "", this.guiLeft + playPauseX + 35, this.guiTop + playPauseY + 197, 14, 20, animTexture, 0, 71));
            }
            if (this.playingAnimation) {
                this.addButton(new GuiTexturedButton(202, "", this.guiLeft + playPauseX + 55, this.guiTop + playPauseY + 197, 14, 20, animTexture, 33, 71));
            }
        }
        int animationX = -10;
        int animationY = -2;
        this.addTextField(new GuiNpcTextField(30, this, this.guiLeft + animationX, this.guiTop + animationY, 120, 15, this.animation.name));
        this.addLabel(new GuiNpcLabel(31, "stats.speed", this.guiLeft + animationX, this.guiTop + animationY + 24, 0xFFFFFF));
        this.addTextField(new GuiNpcTextField(31, this, this.guiLeft + animationX + 88, this.guiTop + animationY + 22, 30, 15, this.animation.speed + ""));
        this.getTextField((int)31).floatsOnly = true;
        this.getTextField(31).setMinMaxDefaultFloat(0.0f, Float.MAX_VALUE, 1.0f);
        this.addLabel(new GuiNpcLabel(32, "animation.smoothing", this.guiLeft + animationX, this.guiTop + animationY + 46, 0xFFFFFF));
        this.addButton(new GuiNpcButton(32, this.guiLeft + animationX + 55, this.guiTop + animationY + 40, 65, 20, new String[]{"animation.smooth", "animation.linear", "gui.none"}, (int)this.animation.smooth));
        this.addLabel(new GuiNpcLabel(33, "animation.loop", this.guiLeft + animationX, this.guiTop + animationY + 68, 0xFFFFFF));
        this.addButton(new GuiNpcButton(33, this.guiLeft + animationX + 55, this.guiTop + animationY + 62, 65, 20, this.animation.loop == -1 ? "gui.none" : "Frame " + this.animation.loop));
        this.addButton(new GuiNpcButton(34, this.guiLeft + animationX, this.guiTop + animationY + 84, 120, 20, "animation.animationOptions"));
        if (editingFrame != null) {
            int i;
            int frameX = animationX;
            int frameY = playPauseY + 100;
            this.addLabel(new GuiNpcLabel(50, "animation.frame", this.guiLeft + frameX + 50, this.guiTop + frameY - 3, 0xFFFFFF));
            this.addLabel(new GuiNpcLabel(51, "animation.duration", this.guiLeft + frameX, this.guiTop + frameY + 15, 0xFFFFFF));
            this.addTextField(new GuiNpcTextField(51, this, this.guiLeft + frameX + 88, this.guiTop + frameY + 11, 30, 15, editingFrame.duration + ""));
            this.getTextField((int)51).integersOnly = true;
            this.getTextField(51).setMinMaxDefaultFloat(0.0f, 2.1474836E9f, 10.0f);
            this.addButton(new GuiNpcButton(52, this.guiLeft + frameX, this.guiTop + frameY + 31, 80, 20, "animation.frameOptions"));
            this.addButton(new GuiNpcButton(53, this.guiLeft + frameX + this.getButton((int)52).field_146120_f + 5, this.guiTop + frameY + 31, 35, 20, "gui.color"));
            int bodyPartX = 280;
            int bodyPartY = -5;
            this.addButton(new GuiTexturedButton(60, "", this.guiLeft + bodyPartX, this.guiTop + bodyPartY, 22, 23, animTexture, 0, 0));
            this.addButton(new GuiTexturedButton(61, "", this.guiLeft + bodyPartX, this.guiTop + bodyPartY + 23, 22, 23, animTexture, 24, 0));
            this.addButton(new GuiTexturedButton(62, "", this.guiLeft + bodyPartX - 9, this.guiTop + bodyPartY + 23, 8, 23, animTexture, 48, 0));
            this.addButton(new GuiTexturedButton(63, "", this.guiLeft + bodyPartX + 26, this.guiTop + bodyPartY + 23, 8, 23, animTexture, 48, 0));
            this.addButton(new GuiTexturedButton(64, "", this.guiLeft + bodyPartX + 1, this.guiTop + bodyPartY + 48, 10, 23, animTexture, 58, 0));
            this.addButton(new GuiTexturedButton(65, "", this.guiLeft + bodyPartX + 12, this.guiTop + bodyPartY + 48, 10, 23, animTexture, 58, 0));
            this.addButton(new GuiTexturedButton(66, "", this.guiLeft + bodyPartX + 40, this.guiTop + bodyPartY + 2, 17, 23, animTexture, 70, 0));
            for (i = 0; i < 7; ++i) {
                if (!this.buttons.containsKey(60 + i)) continue;
                ((GuiTexturedButton)this.getButton((int)(60 + i))).scale = 1.2f;
                if (editingFrame.frameParts.containsKey((Object)EnumAnimationPart.values()[i])) continue;
                ((GuiTexturedButton)this.getButton((int)(60 + i))).textureX += 96;
            }
            if (editingPart != null) {
                this.addLabel(new GuiNpcLabel(67, editingPart.part.name(), this.guiLeft + bodyPartX + 65, this.guiTop + bodyPartY + 20, 0xFFFFFF));
                this.addButton(new GuiNpcButton(67, this.guiLeft + bodyPartX + 45, this.guiTop + bodyPartY + 35, 60, 20, "gui.remove"));
                this.addButton(new GuiNpcButton(68, this.guiLeft + bodyPartX + 45, this.guiTop + bodyPartY + 57, 60, 20, new String[]{"model.sliders", "model.manual"}, partEditMode));
                if (partEditMode == 0) {
                    int yOffset;
                    this.addButton(new GuiNpcButton(69, this.guiLeft + bodyPartX, this.guiTop + bodyPartY + 80, 60, 20, "model.rotate"));
                    this.addButton(new GuiNpcButton(70, this.guiLeft + bodyPartX + 62, this.guiTop + bodyPartY + 80, 60, 20, "model.pivot"));
                    this.getButton(69).setEnabled(sliderSelection == 1);
                    this.getButton(70).setEnabled(sliderSelection == 0);
                    if (sliderSelection == 0) {
                        for (i = 0; i < 3; ++i) {
                            this.rotationSliders[i].field_146120_f = 122;
                            this.rotationSliders[i].field_146121_g = 20;
                            this.rotationSliders[i].field_146128_h = this.guiLeft + bodyPartX;
                            yOffset = 20;
                            if (i != 0) {
                                yOffset += 3;
                            }
                            this.rotationSliders[i].field_146129_i = this.guiTop + bodyPartY + 105 + (yOffset *= i);
                            this.addSlider(this.rotationSliders[i]);
                        }
                        this.addLabel(new GuiNpcLabel(90, "X", this.guiLeft + bodyPartX - 10, this.guiTop + bodyPartY + 112, 0xFFFFFF));
                        this.addLabel(new GuiNpcLabel(91, "Y", this.guiLeft + bodyPartX - 10, this.guiTop + bodyPartY + 135, 0xFFFFFF));
                        this.addLabel(new GuiNpcLabel(92, "Z", this.guiLeft + bodyPartX - 10, this.guiTop + bodyPartY + 157, 0xFFFFFF));
                    } else {
                        for (i = 0; i < 3; ++i) {
                            this.pivotSliders[i].field_146120_f = 122;
                            this.pivotSliders[i].field_146121_g = 20;
                            this.pivotSliders[i].field_146128_h = this.guiLeft + bodyPartX;
                            yOffset = 20;
                            if (i != 0) {
                                yOffset += 3;
                            }
                            this.pivotSliders[i].field_146129_i = this.guiTop + bodyPartY + 105 + (yOffset *= i);
                            this.addSlider(this.pivotSliders[i]);
                        }
                        this.addLabel(new GuiNpcLabel(95, "X", this.guiLeft + bodyPartX - 10, this.guiTop + bodyPartY + 112, 0xFFFFFF));
                        this.addLabel(new GuiNpcLabel(96, "Y", this.guiLeft + bodyPartX - 10, this.guiTop + bodyPartY + 135, 0xFFFFFF));
                        this.addLabel(new GuiNpcLabel(97, "Z", this.guiLeft + bodyPartX - 10, this.guiTop + bodyPartY + 157, 0xFFFFFF));
                    }
                } else {
                    this.addLabel(new GuiNpcLabel(70, "animation.rotations", this.guiLeft + bodyPartX, this.guiTop + bodyPartY + 85, 0xFFFFFF));
                    this.addTextField(new GuiNpcTextField(70, this, this.guiLeft + bodyPartX, this.guiTop + bodyPartY + 97, 35, 15, editingPart.rotation[0] + ""));
                    this.getTextField((int)70).floatsOnly = true;
                    this.getTextField(70).setMinMaxDefaultFloat(-3.4028235E38f, Float.MAX_VALUE, 0.0f);
                    this.addTextField(new GuiNpcTextField(71, this, this.guiLeft + bodyPartX + 40, this.guiTop + bodyPartY + 97, 35, 15, editingPart.rotation[1] + ""));
                    this.getTextField((int)71).floatsOnly = true;
                    this.getTextField(71).setMinMaxDefaultFloat(-3.4028235E38f, Float.MAX_VALUE, 0.0f);
                    this.addTextField(new GuiNpcTextField(72, this, this.guiLeft + bodyPartX + 80, this.guiTop + bodyPartY + 97, 35, 15, editingPart.rotation[2] + ""));
                    this.getTextField((int)72).floatsOnly = true;
                    this.getTextField(72).setMinMaxDefaultFloat(-3.4028235E38f, Float.MAX_VALUE, 0.0f);
                    this.addLabel(new GuiNpcLabel(80, "animation.pivots", this.guiLeft + bodyPartX, this.guiTop + bodyPartY + 117, 0xFFFFFF));
                    this.addTextField(new GuiNpcTextField(80, this, this.guiLeft + bodyPartX, this.guiTop + bodyPartY + 129, 35, 15, editingPart.pivot[0] + ""));
                    this.getTextField((int)80).floatsOnly = true;
                    this.getTextField(80).setMinMaxDefaultFloat(-3.4028235E38f, Float.MAX_VALUE, 0.0f);
                    this.addTextField(new GuiNpcTextField(81, this, this.guiLeft + bodyPartX + 40, this.guiTop + bodyPartY + 129, 35, 15, editingPart.pivot[1] + ""));
                    this.getTextField((int)81).floatsOnly = true;
                    this.getTextField(81).setMinMaxDefaultFloat(-3.4028235E38f, Float.MAX_VALUE, 0.0f);
                    this.addTextField(new GuiNpcTextField(82, this, this.guiLeft + bodyPartX + 80, this.guiTop + bodyPartY + 129, 35, 15, editingPart.pivot[2] + ""));
                    this.getTextField((int)82).floatsOnly = true;
                    this.getTextField(82).setMinMaxDefaultFloat(-3.4028235E38f, Float.MAX_VALUE, 0.0f);
                    this.addLabel(new GuiNpcLabel(83, "animation.customized", this.guiLeft + bodyPartX, this.guiTop + bodyPartY + 154, 0xFFFFFF));
                    this.addButton(new GuiNpcButton(83, this.guiLeft + bodyPartX + 55, this.guiTop + bodyPartY + 148, 30, 20, new String[]{"gui.yes", "gui.no"}, editingPart.isCustomized() ? 0 : 1));
                    if (editingPart.isCustomized()) {
                        this.addLabel(new GuiNpcLabel(84, "stats.speed", this.guiLeft + bodyPartX, this.guiTop + bodyPartY + 174, 0xFFFFFF));
                        this.addTextField(new GuiNpcTextField(84, this, this.guiLeft + bodyPartX + 60, this.guiTop + bodyPartY + 170, 30, 15, editingPart.speed + ""));
                        this.getTextField((int)84).floatsOnly = true;
                        this.getTextField(84).setMinMaxDefaultFloat(0.0f, Float.MAX_VALUE, 1.0f);
                        this.addLabel(new GuiNpcLabel(85, "animation.smoothing", this.guiLeft + bodyPartX, this.guiTop + bodyPartY + 194, 0xFFFFFF));
                        this.addButton(new GuiNpcButton(85, this.guiLeft + bodyPartX + 55, this.guiTop + bodyPartY + 190, 60, 20, new String[]{"animation.smooth", "animation.linear", "gui.none"}, (int)editingPart.smooth));
                    }
                }
            }
        } else {
            this.addLabel(new GuiNpcLabel(50, "animation.addFrame", this.guiLeft + 270, this.guiTop + 100, 0xFFFFFF));
        }
        this.addSlider(this.frameSlider);
        this.frameSlider.field_146128_h = this.guiLeft + 62;
        this.frameSlider.field_146129_i = this.guiTop + playPauseY + 197;
        this.frameSlider.field_146120_f = 63;
        for (int i = 0; i < this.visibleFrames; ++i) {
            this.addButton(new GuiTexturedButton(300 + i, "", this.guiLeft + 130 + i * 7, this.guiTop + 210, 6, 20, animTexture, 0, 71));
        }
    }

    private void updateSliders() {
        FramePart part = this.editingPart();
        if (part == null) {
            return;
        }
        float[] rotations = part.getRotations();
        float[] pivots = part.getPivots();
        for (int i = 0; i < 3; ++i) {
            this.rotationSliders[i].sliderValue = ValueUtil.clamp((rotations[i] / 360.0f + 1.0f) / 2.0f, 0.0f, 1.0f);
            int label = Math.round(rotations[i]);
            this.rotationSliders[i].setString(label + "");
            this.pivotSliders[i].sliderValue = ValueUtil.clamp((ValueUtil.clamp(pivots[i], -100.0f, 100.0f) / 100.0f + 1.0f) / 2.0f, 0.0f, 1.0f);
            int pivotVal = Math.round(pivots[i]);
            this.pivotSliders[i].setString(pivotVal + "");
        }
    }

    private Frame editingFrame() {
        if (this.animation == null || this.frameIndex >= this.animation.frames.size()) {
            return null;
        }
        return this.animation.frames.get(this.frameIndex);
    }

    private FramePart editingPart() {
        Frame editingFrame = this.editingFrame();
        if (this.animation == null || editingFrame == null) {
            return null;
        }
        return editingFrame.frameParts.get((Object)this.editingPart);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int frameClicked;
        super.func_146284_a(guibutton);
        Frame editingFrame = this.editingFrame();
        FramePart part = this.editingPart();
        int value = guibutton instanceof GuiNpcButton ? ((GuiNpcButton)guibutton).getValue() : 0;
        AnimationData data = this.npc.display.animationData;
        if (guibutton.field_146127_k == 11) {
            this.addFrame();
        } else if (guibutton.field_146127_k == 12) {
            this.animation.frames.remove(this.frameIndex);
            this.updateFrameSlider();
        } else if (guibutton.field_146127_k == 13 && editingFrame != null) {
            if (this.frameIndex < this.animation.frames.size() - 1) {
                this.animation.frames.add(this.frameIndex + 1, editingFrame.copy());
            } else {
                this.animation.frames.add(editingFrame.copy());
            }
            ++this.frameIndex;
            this.updateFrameSlider();
        } else if (guibutton.field_146127_k == 14) {
            this.overrideFrame = true;
            --this.frameIndex;
            if (this.frameIndex == -1) {
                this.frameIndex = this.animation.frames.size() - 1;
            }
        } else if (guibutton.field_146127_k == 16) {
            ++this.frameIndex;
            this.overrideFrame = true;
        } else if (guibutton.field_146127_k >= 60 && guibutton.field_146127_k <= 66) {
            EnumAnimationPart enumPart = EnumAnimationPart.values()[guibutton.field_146127_k - 60];
            if (editingFrame != null && !editingFrame.frameParts.containsKey((Object)enumPart)) {
                FramePart framePart = new FramePart(enumPart);
                framePart.prevRotations = new float[]{0.0f, 0.0f, 0.0f};
                editingFrame.addPart(framePart);
            }
            this.editingPart = enumPart;
            this.updateSliders();
        } else if (guibutton.field_146127_k == 67 && editingFrame != null) {
            editingFrame.removePart(this.editingPart.name());
        } else if (guibutton.field_146127_k == 68 && editingFrame != null) {
            ++partEditMode;
            partEditMode %= 2;
            this.updateSliders();
        } else if (guibutton.field_146127_k == 69 && editingFrame != null) {
            if (partEditMode == 1) {
                this.updateSliders();
            }
            sliderSelection = 0;
        } else if (guibutton.field_146127_k == 70 && editingFrame != null) {
            if (partEditMode == 1) {
                this.updateSliders();
            }
            sliderSelection = 1;
        } else if (guibutton.field_146127_k == 83 && editingFrame != null && part != null) {
            part.setCustomized(!part.isCustomized());
        } else if (guibutton.field_146127_k == 32) {
            this.animation.smooth = (byte)value;
        } else if (guibutton.field_146127_k == 85 && part != null) {
            part.smooth = (byte)value;
        } else if (guibutton.field_146127_k == 33) {
            ++this.animation.loop;
        } else if (guibutton.field_146127_k == 200) {
            if (!this.playingAnimation || !data.isActive()) {
                this.animation.currentFrame = 0;
                this.animation.currentFrameTime = 0;
                for (Frame frame : this.animation.frames) {
                    for (FramePart framePart : frame.frameParts.values()) {
                        framePart.prevRotations = new float[]{0.0f, 0.0f, 0.0f};
                        framePart.prevPivots = new float[]{0.0f, 0.0f, 0.0f};
                    }
                }
            }
            this.playingAnimation = true;
            data.setAnimation(this.animation);
            data.animation.paused = false;
        } else if (guibutton.field_146127_k == 201) {
            data.animation.paused = true;
        } else if (guibutton.field_146127_k == 202) {
            this.playingAnimation = false;
            data.animation.paused = false;
        } else if (guibutton.field_146127_k == 34) {
            this.setSubGui(new SubGuiAnimationOptions(this.animation));
        } else if (guibutton.field_146127_k == 52 && editingFrame != null) {
            this.setSubGui(new SubGuiAnimationFrame(editingFrame));
        } else if (guibutton.field_146127_k == 53 && editingFrame != null) {
            this.setSubGui(new SubGuiColorSelector(editingFrame.getColorMarker()));
        }
        if (guibutton.field_146127_k >= 300 && guibutton.field_146127_k < 325 && (frameClicked = guibutton.field_146127_k - 300) < this.animation.frames.size()) {
            Animation playingAnim = data.animation != null && this.playingAnimation ? data.animation : this.animation;
            int frameOffset = this.playingAnimation ? playingAnim.currentFrame - 24 : this.frameOffset;
            this.frameIndex = frameClicked + Math.max(0, frameOffset);
            this.overrideFrame = true;
        }
        this.func_73866_w_();
    }

    private void addFrame() {
        this.addFrame(new Frame(10));
    }

    private void addFrame(Frame frame) {
        if (this.frameIndex < this.animation.frames.size() - 1) {
            this.animation.frames.add(this.frameIndex + 1, frame);
        } else {
            this.animation.frames.add(frame);
        }
        ++this.frameIndex;
        this.updateFrameSlider();
    }

    @Override
    public void func_73863_a(int par1, int par2, float par3) {
        AnimationData data = this.npc.display.animationData;
        if (!data.isActive() && this.playingAnimation) {
            this.playingAnimation = false;
            this.func_73866_w_();
        } else if (data.isActive()) {
            long time = this.field_146297_k.field_71441_e.func_82737_E();
            if (time != this.prevTick) {
                this.npc.display.animationData.increaseTime();
                GuiNpcLabel label = this.getLabel(213);
                if (label != null) {
                    label.label = label.label + ".";
                    if (label.label.length() % 4 == 0) {
                        label.label = "";
                    }
                }
            }
            this.prevTick = time;
        }
        super.func_73863_a(par1, par2, par3);
        Animation playingAnim = data.animation != null && this.playingAnimation ? data.animation : this.animation;
        for (int i = 0; i < this.visibleFrames; ++i) {
            if (this.getButton(300 + i) == null) continue;
            GuiTexturedButton button = (GuiTexturedButton)this.getButton(300 + i);
            int frameOffset = this.playingAnimation ? playingAnim.currentFrame - 24 : this.frameOffset;
            int sliderFrame = i + Math.max(0, frameOffset);
            if (sliderFrame >= playingAnim.frames.size()) {
                button.color = 0;
                button.alpha = 0.3f;
                continue;
            }
            button.color = playingAnim.frames.get(sliderFrame).getColorMarker();
            if (sliderFrame == playingAnim.currentFrame && this.playingAnimation || sliderFrame == this.frameIndex) {
                button.color = sliderFrame == playingAnim.currentFrame && this.playingAnimation ? 16577074 : 3319890;
                button.field_146129_i = this.guiTop + (sliderFrame == this.frameIndex ? 205 : 200);
                continue;
            }
            button.field_146129_i = this.guiTop + 210;
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        String text = textfield.func_146179_b();
        Frame frame = this.editingFrame();
        FramePart part = this.editingPart();
        if (textfield.id == 30 && !text.isEmpty()) {
            this.animation.name = text.replaceAll("[^a-zA-Z0-9_-]", "_");
        } else if (textfield.id == 15 && this.animation != null && this.animation.frames.size() > 0) {
            this.animation.frames.remove(this.frameIndex);
            this.animation.frames.add(textfield.getInteger(), frame);
            this.frameIndex = textfield.getInteger();
            this.overrideFrame = true;
            this.func_73866_w_();
        } else if (textfield.id == 31) {
            this.animation.speed = textfield.getFloat();
        } else if (textfield.id == 84 && part != null) {
            part.speed = textfield.getFloat();
        } else if (textfield.id == 51 && frame != null) {
            frame.duration = textfield.getInteger();
        } else if (textfield.id >= 70 && textfield.id <= 73 && part != null) {
            part.rotation[textfield.id - 70] = textfield.getFloat();
            this.updateSliders();
        } else if (textfield.id >= 80 && textfield.id <= 83 && part != null) {
            part.pivot[textfield.id - 80] = textfield.getFloat();
            this.updateSliders();
        }
    }

    @Override
    public void close() {
        if (!this.hasSubGui()) {
            if (this.animation != null) {
                PacketClient.sendClient(new AnimationSavePacket(this.animation.writeToNBT()));
            }
            this.displayGuiScreen(this.parent);
        } else {
            this.closeSubGui(this.getSubGui());
        }
    }

    public void updateFrameSlider() {
        GuiNpcSlider guiNpcSlider = this.getSlider(350);
        if (guiNpcSlider != null) {
            int frameScroll = Math.max(0, this.animation.frames.size() - this.visibleFrames);
            this.frameOffset = Math.round(guiNpcSlider.sliderValue * (float)frameScroll);
            guiNpcSlider.field_146126_j = this.frameOffset + " to " + Math.min(this.animation.frames.size() - 1, this.frameOffset + this.visibleFrames - 1);
        }
    }

    @Override
    public void mouseDragged(GuiNpcSlider guiNpcSlider) {
        int value;
        FramePart part;
        int id = guiNpcSlider.field_146127_k;
        if (id == 350) {
            this.updateFrameSlider();
        }
        if ((part = this.editingPart()) == null || this.field_146297_k == null) {
            return;
        }
        if (id >= 90 && id < 93) {
            value = (int)(720.0f * (guiNpcSlider.sliderValue - 0.5f));
            part.rotation[id - 90] = value;
            guiNpcSlider.setString(value + "");
        }
        if (id >= 95 && id < 98) {
            value = (int)(100.0f * (guiNpcSlider.sliderValue - 0.5f));
            guiNpcSlider.setString(value + "");
            part.pivot[id - 95] = value;
        }
    }

    @Override
    public void mousePressed(GuiNpcSlider guiNpcSlider) {
    }

    @Override
    public void mouseReleased(GuiNpcSlider guiNpcSlider) {
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        Frame editingFrame = this.editingFrame();
        if (subgui instanceof SubGuiColorSelector && editingFrame != null) {
            editingFrame.setColorMarker(((SubGuiColorSelector)subgui).color);
        }
    }

    @Override
    public void func_73869_a(char par1, int par2) {
        super.func_73869_a(par1, par2);
        if (GuiScreen.func_146271_m() && par2 != 29 && !GuiNpcTextField.isFieldActive()) {
            switch (par2) {
                case 46: {
                    if (this.editingFrame() == null) break;
                    copiedFrame = this.editingFrame();
                    break;
                }
                case 47: {
                    if (copiedFrame == null) break;
                    Frame frame = new Frame(10);
                    frame.parent = this.animation;
                    frame.readFromNBT(copiedFrame.writeToNBT());
                    this.addFrame(frame);
                    this.func_73866_w_();
                }
            }
        }
    }

    static {
        sliderSelection = 0;
    }
}

