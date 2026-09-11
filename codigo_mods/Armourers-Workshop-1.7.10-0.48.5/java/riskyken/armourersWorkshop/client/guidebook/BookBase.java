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
import java.util.ArrayList;
import riskyken.armourersWorkshop.client.guidebook.IBook;
import riskyken.armourersWorkshop.client.guidebook.IBookChapter;
import riskyken.armourersWorkshop.client.guidebook.IBookPage;

@SideOnly(value=Side.CLIENT)
public abstract class BookBase
implements IBook {
    private String name;
    private ArrayList<IBookChapter> chapters;

    public BookBase(String name) {
        this.name = name;
        this.chapters = new ArrayList();
    }

    @Override
    public String getUnlocalizedName() {
        return "book." + this.name;
    }

    @Override
    public int getNumberOfChapters() {
        return this.chapters.size();
    }

    @Override
    public void addChapter(IBookChapter chapter) {
        this.chapters.add(chapter);
    }

    @Override
    public IBookChapter getChapterNumber(int chapterNumber) {
        return this.chapters.get(chapterNumber);
    }

    @Override
    public IBookChapter getChapterFromPageNumber(int pageNumber) {
        return this.chapters.get(this.getChapterIndexFromPageNumber(pageNumber));
    }

    @Override
    public int getChapterIndexFromPageNumber(int pageNumber) {
        int pageCount = pageNumber;
        for (int i = 0; i < this.chapters.size(); ++i) {
            IBookChapter chapter = this.chapters.get(i);
            int pagesInChapter = chapter.getNumberOfPages();
            if (pageCount <= pagesInChapter) {
                return i;
            }
            pageCount -= pagesInChapter;
        }
        return -1;
    }

    @Override
    public int getTotalNumberOfPages() {
        int count = 0;
        for (int i = 0; i < this.chapters.size(); ++i) {
            count += this.chapters.get(i).getNumberOfPages();
        }
        return count;
    }

    @Override
    public IBookPage getPageNumber(int pageNumber) {
        int pageCount = pageNumber;
        for (int i = 0; i < this.chapters.size(); ++i) {
            IBookChapter chapter = this.chapters.get(i);
            int pagesInChapter = chapter.getNumberOfPages();
            if (pageCount <= pagesInChapter) {
                return chapter.getPageNumber(pageCount - 1);
            }
            pageCount -= pagesInChapter;
        }
        return null;
    }

    @Override
    public boolean isFirstPage(int pageNumber) {
        return pageNumber <= 1;
    }

    @Override
    public boolean isLastPage(int pageNumber) {
        return pageNumber >= this.getTotalNumberOfPages();
    }
}

