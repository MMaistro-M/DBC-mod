/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 */
package com.goodbird.npcgecko.client.gui;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import noppes.npcs.client.gui.util.GuiNPCStringSlot;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class GuiStringSelection
extends SubGuiInterface {
    public GuiNPCStringSlot slot;
    public Consumer<String> action;
    public String title;
    public List<String> options;

    public GuiStringSelection(GuiScreen parent, String title, List<String> options, Consumer<String> action) {
        this.drawDefaultBackground = false;
        this.parent = parent;
        this.action = action;
        this.title = title;
        this.options = options;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(0, this.title, this.field_146294_l / 2 - this.field_146289_q.func_78256_a(this.title) / 2, 20, 0xFFFFFF));
        this.options.sort(String.CASE_INSENSITIVE_ORDER);
        this.slot = new GuiNPCStringSlot(this.options, this, false, 18);
        this.slot.func_148134_d(4, 5);
        this.addButton(new GuiNpcButton(2, this.field_146294_l / 2 - 100, this.field_146295_m - 44, 98, 20, "gui.back"));
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        this.slot.func_148128_a(i, j, f);
        super.func_73863_a(i, j, f);
    }

    @Override
    public void doubleClicked() {
        this.action.accept(this.slot.selected);
        this.close();
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 2) {
            this.close();
        }
    }
}

