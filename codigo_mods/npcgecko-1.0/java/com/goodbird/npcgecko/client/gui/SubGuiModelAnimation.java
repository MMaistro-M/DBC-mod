/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.ResourceLocation
 */
package com.goodbird.npcgecko.client.gui;

import com.goodbird.npcgecko.client.gui.GuiStringSelection;
import com.goodbird.npcgecko.data.CustomModelData;
import com.goodbird.npcgecko.mixin.IDataDisplay;
import com.goodbird.npcgecko.utils.AnimationFileUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.entity.EntityNPCInterface;
import software.bernie.geckolib3.resource.GeckoLibCache;

public class SubGuiModelAnimation
extends SubGuiInterface
implements ITextfieldListener,
ISubGuiListener {
    public SubGuiModelAnimation(EntityNPCInterface npc) {
        this.npc = npc;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 44;
        this.addSelectionBlock(1, y, "Animation File:", this.getModelData(this.npc).getAnimFile());
        this.addSelectionBlock(2, y += 23, "Idle:", this.getModelData(this.npc).getIdleAnim());
        this.addSelectionBlock(3, y += 23, "Walk:", this.getModelData(this.npc).getWalkAnim());
        this.addSelectionBlock(4, y += 23, "Melee Attack:", this.getModelData(this.npc).getMeleeAttackAnim());
        this.addSelectionBlock(5, y += 23, "Ranged Attack:", this.getModelData(this.npc).getRangedAttackAnim());
        this.addSelectionBlock(6, y + 23, "Hurt:", this.getModelData(this.npc).getHurtAnim());
        this.addButton(new GuiNpcButton(670, this.field_146294_l - 22, 2, 20, 20, "X"));
    }

    public void addSelectionBlock(int id, int y, String label, String value) {
        this.addLabel(new GuiNpcLabel(id, label, this.guiLeft - 85, y + 5, 0xFFFFFF));
        this.addTextField(new GuiNpcTextField(id, this, this.field_146289_q, this.guiLeft - 10, y, 200, 20, value));
        this.addButton(new GuiNpcButton(id, this.guiLeft + 193, y, 80, 20, "mco.template.button.select"));
    }

    public CustomModelData getModelData(EntityNPCInterface npc) {
        return ((IDataDisplay)((Object)npc.display)).getCustomModelData();
    }

    @Override
    protected void func_146284_a(GuiButton button) {
        super.func_146284_a(button);
        if (button.field_146127_k == 670) {
            this.close();
        }
        if (button.field_146127_k == 1) {
            this.setSubGui(new GuiStringSelection(this, "Selecting geckolib animation file:", AnimationFileUtil.getAnimationFileList(), name -> this.getModelData(this.npc).setAnimFile((String)name)));
        }
        if (button.field_146127_k == 2) {
            this.setSubGui(new GuiStringSelection(this, "Selecting geckolib idle animation:", AnimationFileUtil.getAnimationList(this.getModelData(this.npc).getAnimFile()), name -> this.getModelData(this.npc).setIdleAnim((String)name)));
        }
        if (button.field_146127_k == 3) {
            this.setSubGui(new GuiStringSelection(this, "Selecting geckolib walk animation:", AnimationFileUtil.getAnimationList(this.getModelData(this.npc).getAnimFile()), name -> this.getModelData(this.npc).setWalkAnim((String)name)));
        }
        if (button.field_146127_k == 4) {
            this.setSubGui(new GuiStringSelection(this, "Selecting geckolib melee attack animation:", AnimationFileUtil.getAnimationList(this.getModelData(this.npc).getAnimFile()), name -> this.getModelData(this.npc).setMeleeAttackAnim((String)name)));
        }
        if (button.field_146127_k == 5) {
            this.setSubGui(new GuiStringSelection(this, "Selecting geckolib ranged attack animation:", AnimationFileUtil.getAnimationList(this.getModelData(this.npc).getAnimFile()), name -> this.getModelData(this.npc).setRangedAttackAnim((String)name)));
        }
        if (button.field_146127_k == 6) {
            this.setSubGui(new GuiStringSelection(this, "Selecting geckolib hurt animation:", AnimationFileUtil.getAnimationList(this.getModelData(this.npc).getAnimFile()), name -> this.getModelData(this.npc).setHurtAnim((String)name)));
        }
    }

    public boolean isValidAnimFile(String name) {
        return GeckoLibCache.getInstance().getAnimations().containsKey(new ResourceLocation(name));
    }

    public boolean isValidAnimation(String name) {
        return AnimationFileUtil.getAnimationList(this.getModelData(this.npc).getAnimFile()).contains(name);
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == 1 && this.isValidAnimFile(textfield.func_146179_b())) {
            if (!textfield.isEmpty()) {
                this.getModelData(this.npc).setAnimFile(textfield.func_146179_b());
            } else {
                textfield.func_146180_a(this.getModelData(this.npc).getAnimFile());
            }
        }
        if (textfield.id == 2 && (this.isValidAnimation(textfield.func_146179_b()) || textfield.func_146179_b().isEmpty())) {
            this.getModelData(this.npc).setIdleAnim(textfield.func_146179_b());
        }
        if (textfield.id == 3 && (this.isValidAnimation(textfield.func_146179_b()) || textfield.func_146179_b().isEmpty())) {
            this.getModelData(this.npc).setWalkAnim(textfield.func_146179_b());
        }
        if (textfield.id == 4 && (this.isValidAnimation(textfield.func_146179_b()) || textfield.func_146179_b().isEmpty())) {
            this.getModelData(this.npc).setMeleeAttackAnim(textfield.func_146179_b());
        }
        if (textfield.id == 5 && (this.isValidAnimation(textfield.func_146179_b()) || textfield.func_146179_b().isEmpty())) {
            this.getModelData(this.npc).setRangedAttackAnim(textfield.func_146179_b());
        }
        if (textfield.id == 6 && (this.isValidAnimation(textfield.func_146179_b()) || textfield.func_146179_b().isEmpty())) {
            this.getModelData(this.npc).setHurtAnim(textfield.func_146179_b());
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subGuiInterface) {
        this.func_73866_w_();
    }
}

