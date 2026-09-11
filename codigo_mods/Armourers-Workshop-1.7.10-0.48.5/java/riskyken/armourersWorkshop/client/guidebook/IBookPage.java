/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.FontRenderer
 */
package riskyken.armourersWorkshop.client.guidebook;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;

@SideOnly(value=Side.CLIENT)
public interface IBookPage {
    public void renderPage(FontRenderer var1, int var2, int var3, boolean var4, int var5);

    public void renderRollover(FontRenderer var1, int var2, int var3);
}

