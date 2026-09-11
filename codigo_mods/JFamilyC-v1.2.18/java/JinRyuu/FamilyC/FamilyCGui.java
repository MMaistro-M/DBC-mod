/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 */
package JinRyuu.FamilyC;

import JinRyuu.FamilyC.FamilyCClient;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class FamilyCGui
extends Gui {
    protected FontRenderer fontRenderer;

    public FamilyCGui() {
        this.fontRenderer = FamilyCClient.mc.field_71466_p;
    }
}

