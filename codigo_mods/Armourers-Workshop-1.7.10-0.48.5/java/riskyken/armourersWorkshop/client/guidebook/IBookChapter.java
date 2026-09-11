/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 */
package riskyken.armourersWorkshop.client.guidebook;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import riskyken.armourersWorkshop.client.guidebook.IBookPage;

@SideOnly(value=Side.CLIENT)
public interface IBookChapter {
    public void createPages();

    public String getUnlocalizedName();

    public int getNumberOfPages();

    public IBookPage getPageNumber(int var1);

    public void addPage(IBookPage var1);
}

