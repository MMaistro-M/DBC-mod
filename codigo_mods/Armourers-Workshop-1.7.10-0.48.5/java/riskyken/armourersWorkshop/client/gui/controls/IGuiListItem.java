/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.FontRenderer
 */
package riskyken.armourersWorkshop.client.gui.controls;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;

@SideOnly(value=Side.CLIENT)
public interface IGuiListItem {
    public void drawListItem(FontRenderer var1, int var2, int var3, int var4, int var5, boolean var6, int var7);

    public boolean mousePressed(FontRenderer var1, int var2, int var3, int var4, int var5, int var6, int var7);

    public void mouseReleased(FontRenderer var1, int var2, int var3, int var4, int var5, int var6, int var7);

    public String getDisplayName();
}

