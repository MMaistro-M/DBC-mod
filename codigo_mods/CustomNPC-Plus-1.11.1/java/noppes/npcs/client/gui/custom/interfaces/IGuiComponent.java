/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package noppes.npcs.client.gui.custom.interfaces;

import net.minecraft.client.Minecraft;
import noppes.npcs.api.gui.ICustomGuiComponent;

public interface IGuiComponent {
    public int getID();

    public void onRender(Minecraft var1, int var2, int var3, int var4, float var5);

    public ICustomGuiComponent toComponent();
}

