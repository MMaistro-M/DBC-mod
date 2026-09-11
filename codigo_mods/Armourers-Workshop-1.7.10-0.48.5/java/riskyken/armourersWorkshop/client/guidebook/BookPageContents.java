/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.util.StatCollector
 */
package riskyken.armourersWorkshop.client.guidebook;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.StatCollector;
import riskyken.armourersWorkshop.client.guidebook.BookPageBase;
import riskyken.armourersWorkshop.client.guidebook.IBook;
import riskyken.armourersWorkshop.client.guidebook.IBookChapter;
import riskyken.armourersWorkshop.utils.UtilColour;

public class BookPageContents
extends BookPageBase {
    public BookPageContents(IBook parentBook) {
        super(parentBook);
    }

    @Override
    public void renderPage(FontRenderer fontRenderer, int mouseX, int mouseY, boolean turning, int pageNumber) {
        this.drawPageTitleAndNumber(fontRenderer, pageNumber);
        for (int i = 0; i < this.parentBook.getNumberOfChapters(); ++i) {
            IBookChapter chapter = this.parentBook.getChapterNumber(i);
            String chapterTitle = chapter.getUnlocalizedName();
            chapterTitle = StatCollector.func_74838_a((String)(chapterTitle + ".name"));
            fontRenderer.func_78276_b(chapterTitle, 5, 7 + fontRenderer.field_78288_b * 2 + i * 16, UtilColour.getMinecraftColor(7, UtilColour.ColourFamily.MINECRAFT));
        }
    }

    @Override
    public void renderRollover(FontRenderer fontRenderer, int mouseX, int mouseY) {
    }
}

