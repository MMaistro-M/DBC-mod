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
import riskyken.armourersWorkshop.client.guidebook.IBookChapter;
import riskyken.armourersWorkshop.client.guidebook.IBookPage;

@SideOnly(value=Side.CLIENT)
public interface IBook {
    public String getUnlocalizedName();

    public int getNumberOfChapters();

    public void addChapter(IBookChapter var1);

    public IBookChapter getChapterNumber(int var1);

    public IBookChapter getChapterFromPageNumber(int var1);

    public int getChapterIndexFromPageNumber(int var1);

    public int getTotalNumberOfPages();

    public IBookPage getPageNumber(int var1);

    public boolean isFirstPage(int var1);

    public boolean isLastPage(int var1);
}

