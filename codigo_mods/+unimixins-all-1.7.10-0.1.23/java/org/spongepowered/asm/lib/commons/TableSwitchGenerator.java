/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.asm.lib.commons;

import org.spongepowered.asm.lib.Label;

public interface TableSwitchGenerator {
    public void generateCase(int var1, Label var2);

    public void generateDefault();
}

