/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.KeyBinding
 */
package com.blamejared.controlling.client.gui;

import com.blamejared.controlling.client.gui.GuiNewKeyBindingList;
import java.util.function.Predicate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public enum DisplayMode {
    ALL(keyEntry -> true),
    NONE(keyEntry -> keyEntry.getKeybinding().func_151463_i() == 0),
    CONFLICTING(keyEntry -> {
        for (KeyBinding key : Minecraft.func_71410_x().field_71474_y.field_74324_K) {
            if (key.func_151464_g().equals(keyEntry.getKeybinding().func_151464_g()) || key.func_151463_i() == 0 || key.func_151463_i() != keyEntry.getKeybinding().func_151463_i()) continue;
            return true;
        }
        return false;
    });

    private final Predicate<GuiNewKeyBindingList.KeyEntry> predicate;

    private DisplayMode(Predicate<GuiNewKeyBindingList.KeyEntry> predicate) {
        this.predicate = predicate;
    }

    public Predicate<GuiNewKeyBindingList.KeyEntry> getPredicate() {
        return this.predicate;
    }
}

