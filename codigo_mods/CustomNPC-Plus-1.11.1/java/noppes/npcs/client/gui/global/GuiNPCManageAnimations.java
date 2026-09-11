/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiYesNo
 *  net.minecraft.client.gui.GuiYesNoCallback
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.animation.AnimationGetPacket;
import kamkeel.npcs.network.packets.request.animation.AnimationRemovePacket;
import kamkeel.npcs.network.packets.request.animation.AnimationSavePacket;
import kamkeel.npcs.network.packets.request.animation.AnimationsGetPacket;
import kamkeel.npcs.network.packets.request.animation.BuiltInAnimationGetPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.global.GuiAnimationDirectory;
import noppes.npcs.client.gui.global.GuiNPCEditAnimation;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiModelInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.GuiTexturedButton;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.controllers.data.AnimationData;
import noppes.npcs.controllers.data.Frame;
import noppes.npcs.controllers.data.FramePart;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNPCManageAnimations
extends GuiModelInterface2
implements IScrollData,
ICustomScrollListener,
ITextfieldListener,
IGuiData,
ISubGuiListener,
GuiYesNoCallback {
    private GuiCustomScroll scrollAnimations;
    private HashMap<String, Integer> customData = new HashMap();
    private HashMap<String, Integer> builtInData = new HashMap();
    private Animation animation = new Animation();
    public boolean playingAnimation = false;
    private long prevTick;
    private String selected = null;
    private String search = "";
    private boolean showingBuiltIn = false;
    private boolean currentIsBuiltIn = false;

    public GuiNPCManageAnimations(EntityNPCInterface npc, boolean save, boolean hasMenuNpc) {
        super(npc, hasMenuNpc);
        this.setSave(save);
        this.xOffset = -78;
        this.yOffset = -33;
        PacketClient.sendClient(new AnimationsGetPacket());
        AnimationData data = npc.display.animationData;
        data.setEnabled(true);
    }

    @Override
    public void func_73866_w_() {
        int playButtonOffsetX;
        super.func_73866_w_();
        GuiNpcButton fullBtn = new GuiNpcButton(66, this.guiLeft + 368, this.guiTop + 8, 45, 20, "gui.fullscreen");
        fullBtn.setTextColor(0x55FF55);
        fullBtn.setHoverText("gui.fullscreen.tooltip");
        this.addButton(fullBtn);
        String toggleLabel = this.showingBuiltIn ? "gui.builtin" : "gui.custom";
        GuiNpcButton toggleBtn = new GuiNpcButton(10, this.guiLeft + 368, this.guiTop + 36, 45, 20, toggleLabel);
        toggleBtn.setTextColor(this.showingBuiltIn ? 0x55FFFF : 0xFFFFFF);
        this.addButton(toggleBtn);
        if (!this.showingBuiltIn) {
            this.addButton(new GuiNpcButton(0, this.guiLeft + 368, this.guiTop + 60, 45, 20, "gui.add"));
            this.addButton(new GuiNpcButton(1, this.guiLeft + 368, this.guiTop + 84, 45, 20, "gui.remove"));
        }
        if (this.scrollAnimations == null) {
            this.scrollAnimations = new GuiCustomScroll((GuiScreen)this, 0, 0);
            this.scrollAnimations.setSize(143, 185);
        }
        this.scrollAnimations.guiLeft = this.guiLeft + 220;
        this.scrollAnimations.guiTop = this.guiTop + 4;
        this.addScroll(this.scrollAnimations);
        this.addTextField(new GuiNpcTextField(55, this, this.field_146289_q, this.guiLeft + 220, this.guiTop + 192, 143, 20, this.search));
        this.scrollAnimations.setList(this.getSearchList());
        if (this.animation.id == -1 && !this.currentIsBuiltIn) {
            return;
        }
        if (!this.currentIsBuiltIn) {
            this.addLabel(new GuiNpcLabel(10, "ID", this.guiLeft + 368, this.guiTop + 192));
            this.addLabel(new GuiNpcLabel(11, this.animation.id + "", this.guiLeft + 368, this.guiTop + 192 + 10));
        } else {
            this.addLabel(new GuiNpcLabel(10, "gui.builtin", this.guiLeft + 368, this.guiTop + 192));
        }
        AnimationData data = this.npc.display.animationData;
        if (!this.playingAnimation) {
            data.setAnimation(new Animation());
            data.animation.smooth = this.animation.smooth;
            data.animation.loop = 0;
            if (this.animation.frames.size() > 0) {
                Frame firstFrame = new Frame();
                firstFrame.parent = data.animation;
                firstFrame.readFromNBT(this.animation.frames.get(0).writeToNBT());
                data.animation.addFrame(firstFrame);
            }
        }
        if (this.animation != null && !this.currentIsBuiltIn) {
            this.addButton(new GuiNpcButton(100, this.guiLeft + 10, this.guiTop + 192, 45, 20, "gui.edit"));
        }
        String animTexture = "customnpcs:textures/gui/animation.png";
        int n = playButtonOffsetX = this.currentIsBuiltIn ? 80 : 140;
        if (this.animation != null && !this.animation.frames.isEmpty()) {
            if (!this.playingAnimation || data.animation.paused) {
                this.addLabel(new GuiNpcLabel(90, data.animation.paused ? "animation.paused" : "animation.stopped", this.guiLeft + playButtonOffsetX - 15, this.guiTop + 198));
                if (data.animation.paused) {
                    this.addLabel(new GuiNpcLabel(94, "", this.guiLeft + playButtonOffsetX + 21, this.guiTop + 198));
                }
                this.addButton(new GuiTexturedButton(91, "", this.guiLeft + playButtonOffsetX + 35, this.guiTop + 192, 11, 20, animTexture, 18, 71));
            } else {
                this.addLabel(new GuiNpcLabel(90, "animation.playing", this.guiLeft + playButtonOffsetX - 15, this.guiTop + 198));
                this.addLabel(new GuiNpcLabel(94, "", this.guiLeft + playButtonOffsetX + 20, this.guiTop + 198));
                this.addButton(new GuiTexturedButton(92, "", this.guiLeft + playButtonOffsetX + 35, this.guiTop + 192, 14, 20, animTexture, 0, 71));
            }
            if (this.playingAnimation) {
                this.addButton(new GuiTexturedButton(93, "", this.guiLeft + playButtonOffsetX + 55, this.guiTop + 192, 14, 20, animTexture, 33, 71));
            }
        }
    }

    @Override
    public void func_73863_a(int par1, int par2, float partialTicks) {
        AnimationData data = this.npc.display.animationData;
        if (!data.isActive() && this.playingAnimation) {
            this.playingAnimation = false;
            this.func_73866_w_();
        } else if (data.isActive()) {
            long time = this.field_146297_k.field_71441_e.func_82737_E();
            if (time != this.prevTick) {
                this.npc.display.animationData.increaseTime();
                GuiNpcLabel label = this.getLabel(94);
                if (label != null) {
                    label.label = label.label + ".";
                    if (label.label.length() % 4 == 0) {
                        label.label = "";
                    }
                }
            }
            this.prevTick = time;
        }
        super.func_73863_a(par1, par2, partialTicks);
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(55) != null && this.getTextField(55).func_146206_l()) {
            if (this.search.equals(this.getTextField(55).func_146179_b())) {
                return;
            }
            this.search = this.getTextField(55).func_146179_b().toLowerCase();
            this.scrollAnimations.resetScroll();
            this.scrollAnimations.setList(this.getSearchList());
        }
    }

    private HashMap<String, Integer> getCurrentData() {
        return this.showingBuiltIn ? this.builtInData : this.customData;
    }

    private List<String> getSearchList() {
        HashMap<String, Integer> data = this.getCurrentData();
        if (this.search.isEmpty()) {
            return new ArrayList<String>(data.keySet());
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : data.keySet()) {
            if (!name.toLowerCase().contains(this.search)) continue;
            list.add(name);
        }
        return list;
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 66) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiAnimationDirectory(this.npc));
            return;
        }
        if (button.field_146127_k == 10) {
            this.showingBuiltIn = !this.showingBuiltIn;
            this.selected = null;
            this.search = "";
            this.animation = new Animation();
            this.currentIsBuiltIn = false;
            this.playingAnimation = false;
            this.scrollAnimations.clear();
            PacketClient.sendClient(new AnimationsGetPacket());
            this.func_73866_w_();
            return;
        }
        if (button.field_146127_k == 0 && !this.showingBuiltIn) {
            this.save();
            String name = "New";
            while (this.customData.containsKey(name)) {
                name = name + "_";
            }
            Animation animation = new Animation(-1, name);
            PacketClient.sendClient(new AnimationSavePacket(animation.writeToNBT()));
        }
        if (button.field_146127_k == 1 && !this.showingBuiltIn && this.customData.containsKey(this.scrollAnimations.getSelected())) {
            GuiYesNo guiyesno = new GuiYesNo((GuiYesNoCallback)this, this.scrollAnimations.getSelected(), StatCollector.func_74838_a((String)"gui.delete"), 1);
            this.displayGuiScreen((GuiScreen)guiyesno);
        }
        AnimationData data = this.npc.display.animationData;
        if (guibutton.field_146127_k == 91) {
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
        } else if (guibutton.field_146127_k == 92) {
            data.animation.paused = true;
        } else if (guibutton.field_146127_k == 93) {
            this.playingAnimation = false;
            data.animation.paused = false;
        }
        if (guibutton.field_146127_k == 100 && !this.currentIsBuiltIn) {
            NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNPCEditAnimation(this, this.animation, this.npc));
        }
        this.func_73866_w_();
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.animation = new Animation();
        this.animation.readFromNBT(compound);
        this.currentIsBuiltIn = compound.func_74764_b("BuiltIn") && compound.func_74767_n("BuiltIn");
        this.setSelected(this.animation.name);
        this.npc.display.animationData.setAnimation(this.animation);
        this.playingAnimation = false;
        this.npc.display.animationData.animation.paused = false;
        this.func_73866_w_();
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        String name = this.scrollAnimations.getSelected();
        if (type == EnumScrollData.ANIMATIONS) {
            this.customData = data;
        } else if (type == EnumScrollData.BUILTIN_ANIMATIONS) {
            this.builtInData = data;
        }
        this.scrollAnimations.setList(this.getSearchList());
        if (name != null) {
            this.scrollAnimations.setSelected(name);
        }
    }

    @Override
    public void setSelected(String selected) {
        this.selected = selected;
        this.scrollAnimations.setSelected(selected);
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0) {
            this.selected = this.scrollAnimations.getSelected();
            HashMap<String, Integer> data = this.getCurrentData();
            if (this.showingBuiltIn) {
                PacketClient.sendClient(new BuiltInAnimationGetPacket(this.selected));
            } else if (data.containsKey(this.selected)) {
                PacketClient.sendClient(new AnimationGetPacket(data.get(this.selected)));
            }
        }
    }

    @Override
    public void save() {
        if (this.selected != null && this.customData.containsKey(this.selected) && this.animation != null && !this.currentIsBuiltIn) {
            PacketClient.sendClient(new AnimationSavePacket(this.animation.writeToNBT()));
        }
    }

    @Override
    public void unFocused(GuiNpcTextField guiNpcTextField) {
        String name;
        if (this.animation.id == -1 || this.currentIsBuiltIn) {
            return;
        }
        if (guiNpcTextField.id == 0 && !(name = guiNpcTextField.func_146179_b()).isEmpty() && !this.customData.containsKey(name)) {
            String old = this.animation.name;
            this.customData.remove(this.animation.name);
            this.animation.name = name;
            this.customData.put(this.animation.name, this.animation.id);
            this.selected = name;
            this.scrollAnimations.replace(old, this.animation.name);
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
    }

    public void func_73878_a(boolean result, int id) {
        NoppesUtil.openGUI((EntityPlayer)this.player, this);
        if (!result) {
            return;
        }
        if (id == 1 && this.customData.containsKey(this.scrollAnimations.getSelected()) && !this.showingBuiltIn) {
            PacketClient.sendClient(new AnimationRemovePacket(this.customData.get(this.selected)));
            this.scrollAnimations.clear();
            this.animation = new Animation();
            this.currentIsBuiltIn = false;
            this.func_73866_w_();
        }
    }
}

