/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 */
package riskyken.armourersWorkshop.client.guidebook;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import riskyken.armourersWorkshop.client.guidebook.BookChapterBase;
import riskyken.armourersWorkshop.client.guidebook.BookPage;
import riskyken.armourersWorkshop.client.guidebook.IBook;
import riskyken.armourersWorkshop.client.guidebook.IBookPage;

@SideOnly(value=Side.CLIENT)
public class BookChapter
extends BookChapterBase {
    private final int numberOfParagraphs;

    public BookChapter(IBook parentBook, String name, int numberOfParagraphs) {
        super(parentBook, name);
        this.numberOfParagraphs = numberOfParagraphs;
    }

    private ArrayList<IBookPage> createPagesForText(String text) {
        FontRenderer fontRenderer = Minecraft.func_71410_x().field_71466_p;
        List lines = fontRenderer.func_78271_c(text, 104);
        ArrayList<IBookPage> pages = new ArrayList<IBookPage>();
        int linesPerPage = MathHelper.func_76128_c((double)(130 / fontRenderer.field_78288_b));
        ArrayList<String> pageLines = new ArrayList<String>();
        for (int i = 0; i < lines.size(); ++i) {
            String line = (String)lines.get(i);
            if (line.isEmpty() & pageLines.size() == 0) continue;
            pageLines.add((String)lines.get(i));
            if (pageLines.size() < linesPerPage) continue;
            pages.add(new BookPage(this.parentBook, pageLines));
            pageLines = new ArrayList();
        }
        if (pageLines.size() > 0) {
            pages.add(new BookPage(this.parentBook, pageLines));
        }
        return pages;
    }

    @Override
    public void createPages() {
        String chapterText = "";
        for (int i = 0; i < this.numberOfParagraphs; ++i) {
            chapterText = chapterText + this.getParagraphText(i + 1);
            if (i == this.numberOfParagraphs) continue;
            chapterText = chapterText + "\n\n";
        }
        chapterText = chapterText.replaceAll("&n", "\n");
        chapterText = chapterText.replaceAll("&p", "\n\n");
        this.pages.addAll(this.createPagesForText(chapterText));
    }

    private String getParagraphText(int paragraphNumber) {
        return this.getLocalizedText(this.getUnlocalizedName() + ".paragraph" + paragraphNumber);
    }

    private String getLocalizedText(String unlocalizedText) {
        return StatCollector.func_74838_a((String)unlocalizedText);
    }
}

