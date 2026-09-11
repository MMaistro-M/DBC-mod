/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.advanced;

import noppes.npcs.client.gui.util.SubGuiInterface;

public abstract class SubGuiSimpleChoice
extends SubGuiInterface {
    private int result = -1;

    protected final void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return this.result;
    }
}

