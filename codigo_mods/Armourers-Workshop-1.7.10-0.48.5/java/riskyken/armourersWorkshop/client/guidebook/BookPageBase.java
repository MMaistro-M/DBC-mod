/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.guidebook;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.guidebook.IBook;
import riskyken.armourersWorkshop.client.guidebook.IBookPage;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.utils.UtilColour;

@SideOnly(value=Side.CLIENT)
public abstract class BookPageBase
implements IBookPage {
    protected static final ResourceLocation bookPageTexture = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/guideBookPage.png");
    protected static final int TEXT_COLOUR = -16777216;
    public static final int PAGE_TEXTURE_WIDTH = 118;
    public static final int PAGE_TEXTURE_HEIGHT = 165;
    public static final int PAGE_MARGIN_LEFT = 10;
    public static final int PAGE_MARGIN_TOP = 7;
    protected static final int PAGE_PADDING_LEFT = 5;
    protected static final int PAGE_PADDING_TOP = 5;
    protected final IBook parentBook;

    public BookPageBase(IBook parentBook) {
        this.parentBook = parentBook;
    }

    protected void renderStringCenter(FontRenderer fontRenderer, String text, int y) {
        int contentWidth = 59;
        int stringWidth = fontRenderer.func_78256_a(text) / 2;
        int xCenter = 52 - fontRenderer.func_78256_a(text) / 2;
        fontRenderer.func_78279_b(text, contentWidth - stringWidth, y, 118, UtilColour.getMinecraftColor(7, UtilColour.ColourFamily.MINECRAFT));
    }

    protected void drawPageTitleAndNumber(FontRenderer fontRenderer, int pageNumber) {
        String chapterTitle = this.parentBook.getChapterFromPageNumber(pageNumber).getUnlocalizedName();
        chapterTitle = StatCollector.func_74838_a((String)(chapterTitle + ".name"));
        this.renderStringCenter(fontRenderer, chapterTitle, 5);
        this.renderStringCenter(fontRenderer, pageNumber + " - " + this.parentBook.getTotalNumberOfPages(), 160 - fontRenderer.field_78288_b);
    }

    protected void drawTestRec(int x, int y, int width, int height) {
        double zLevel = 0.0;
        GL11.glShadeModel((int)7425);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3008);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glDisable((int)3553);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        new Tessellator();
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78382_b();
        tess.func_78369_a(0.0f, 1.0f, 0.0f, 1.0f);
        tess.func_78377_a((double)x, (double)(y + height), zLevel);
        tess.func_78369_a(0.0f, 0.0f, 1.0f, 1.0f);
        tess.func_78377_a((double)(x + width), (double)(y + height), zLevel);
        tess.func_78369_a(1.0f, 0.0f, 0.0f, 1.0f);
        tess.func_78377_a((double)(x + width), (double)y, zLevel);
        tess.func_78369_a(1.0f, 1.0f, 0.0f, 1.0f);
        tess.func_78377_a((double)x, (double)y, zLevel);
        tess.func_78381_a();
        GL11.glEnable((int)3553);
    }

    protected void renderTestRec(int x, int y, int width, int height, float r, float g, float b) {
        double zLevel = 0.0;
        GL11.glEnable((int)3042);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glDisable((int)3553);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        new Tessellator();
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78382_b();
        tess.func_78369_a(r, g, b, 1.0f);
        tess.func_78377_a((double)x, (double)(y + height), zLevel);
        tess.func_78377_a((double)(x + width), (double)(y + height), zLevel);
        tess.func_78377_a((double)(x + width), (double)y, zLevel);
        tess.func_78377_a((double)x, (double)y, zLevel);
        tess.func_78381_a();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
    }

    protected void drawTexturedRec(int x, int y, int u, int v, int width, int height) {
        double zLevel = 0.0;
        float textureFraction = 0.00390625f;
        new Tessellator();
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78382_b();
        tess.func_78369_a(1.0f, 1.0f, 1.0f, 1.0f);
        tess.func_78374_a((double)x, (double)(y + height), zLevel, 0.0, 1.0);
        tess.func_78374_a((double)(x + width), (double)(y + height), zLevel, 1.0, 1.0);
        tess.func_78374_a((double)(x + width), (double)y, zLevel, 1.0, 0.0);
        tess.func_78374_a((double)x, (double)y, zLevel, 0.0, 0.0);
        tess.func_78381_a();
    }

    protected void drawTexturedRec(int x, int y, int width, int height) {
        double zLevel = 0.0;
        new Tessellator();
        Tessellator tess = Tessellator.field_78398_a;
        ModRenderHelper.enableAlphaBlend();
        tess.func_78382_b();
        tess.func_78369_a(1.0f, 1.0f, 1.0f, 1.0f);
        tess.func_78374_a((double)x, (double)(y + height), zLevel, 0.0, 1.0);
        tess.func_78374_a((double)(x + width), (double)(y + height), zLevel, 1.0, 1.0);
        tess.func_78374_a((double)(x + width), (double)y, zLevel, 1.0, 0.0);
        tess.func_78374_a((double)x, (double)y, zLevel, 0.0, 0.0);
        tess.func_78381_a();
    }
}

