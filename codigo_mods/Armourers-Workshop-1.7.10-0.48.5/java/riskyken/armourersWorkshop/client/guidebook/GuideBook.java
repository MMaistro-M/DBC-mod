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
import riskyken.armourersWorkshop.client.guidebook.BookBase;
import riskyken.armourersWorkshop.client.guidebook.BookChapter;
import riskyken.armourersWorkshop.client.guidebook.BookChapterCredits;
import riskyken.armourersWorkshop.client.guidebook.BookPageContents;
import riskyken.armourersWorkshop.client.guidebook.BookPageRecipe;
import riskyken.armourersWorkshop.client.guidebook.IBook;
import riskyken.armourersWorkshop.client.guidebook.IBookChapter;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.items.ModItems;

@SideOnly(value=Side.CLIENT)
public class GuideBook
extends BookBase {
    private IBookChapter chapterContents;
    private IBookChapter chapterIntroduction;
    private IBookChapter chapterArmourer;
    private IBookChapter chapterEquipmentTemplates;
    private IBookChapter chapterPaintingTools;
    private IBookChapter chapterEquipmentWardrobe;
    private IBookChapter chapterRecipes;
    private IBookChapter chapterCredits;

    public GuideBook() {
        super("armourersWorkshop".toLowerCase() + ":guideBook");
        this.registerChapters();
        this.createPages();
    }

    private void registerChapters() {
        this.chapterContents = new BookChapter(this, "contents", 0);
        this.chapterIntroduction = new BookChapter(this, "introduction", 2);
        this.chapterArmourer = new BookChapter(this, "armourer", 2);
        this.chapterEquipmentTemplates = new BookChapter(this, "equipmentTemplates", 1);
        this.chapterPaintingTools = new BookChapter(this, "paintingTools", 4);
        this.chapterEquipmentWardrobe = new BookChapter(this, "equipmentWardrobe", 2);
        this.chapterRecipes = new BookChapter(this, "recipes", 0);
        this.chapterCredits = new BookChapterCredits(this, "credits");
        this.chapterContents.addPage(new BookPageContents(this));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModBlocks.armourerBrain));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModBlocks.armourLibrary));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModBlocks.colourable));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModBlocks.colourableGlass));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModBlocks.colourableGlowing));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModBlocks.colourableGlassGlowing));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModBlocks.colourMixer));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModBlocks.mannequin));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModBlocks.skinningTable));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModBlocks.dyeTable));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModItems.paintbrush));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModItems.paintRoller));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModItems.burnTool));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModItems.dodgeTool));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModItems.shadeNoiseTool));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModItems.colourNoiseTool));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModItems.colourPicker));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModItems.soap));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModItems.hueTool));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModItems.guideBook));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModItems.mannequinTool));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModItems.dyeBottle));
        this.chapterRecipes.addPage(new BookPageRecipe((IBook)this, ModItems.armourersHammer));
        this.addChapter(this.chapterContents);
        this.addChapter(this.chapterIntroduction);
        this.addChapter(this.chapterArmourer);
        this.addChapter(this.chapterEquipmentTemplates);
        this.addChapter(this.chapterPaintingTools);
        this.addChapter(this.chapterEquipmentWardrobe);
        this.addChapter(this.chapterRecipes);
        this.addChapter(this.chapterCredits);
    }

    private void createPages() {
        for (int i = 0; i < this.getNumberOfChapters(); ++i) {
            this.getChapterNumber(i).createPages();
        }
    }
}

