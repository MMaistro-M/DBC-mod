/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiListExtended$IGuiListEntry
 *  net.minecraft.client.resources.I18n
 */
package com.blamejared.controlling.client.gui;

import com.blamejared.controlling.client.gui.GuiNewKeyBindingList;
import com.blamejared.controlling.client.gui.ISort;
import java.util.Comparator;
import java.util.List;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.resources.I18n;

public enum SortOrder {
    NONE(entries -> {}),
    AZ(entries -> entries.sort(Comparator.comparing(entry -> ((GuiNewKeyBindingList.KeyEntry)entry).getKeybinding().func_151464_g()))),
    ZA(entries -> entries.sort(Comparator.comparing(entry -> ((GuiNewKeyBindingList.KeyEntry)entry).getKeybinding().func_151464_g()).reversed()));

    private final ISort sorter;

    private SortOrder(ISort sorter) {
        this.sorter = sorter;
    }

    public SortOrder cycle() {
        return SortOrder.values()[(this.ordinal() + 1) % SortOrder.values().length];
    }

    public void sort(List<GuiListExtended.IGuiListEntry> list) {
        this.sorter.sort(list);
    }

    public String getName() {
        switch (this) {
            default: {
                return I18n.func_135052_a((String)"options.sortNone", (Object[])new Object[0]);
            }
            case AZ: {
                return I18n.func_135052_a((String)"options.sortAZ", (Object[])new Object[0]);
            }
            case ZA: 
        }
        return I18n.func_135052_a((String)"options.sortZA", (Object[])new Object[0]);
    }
}

