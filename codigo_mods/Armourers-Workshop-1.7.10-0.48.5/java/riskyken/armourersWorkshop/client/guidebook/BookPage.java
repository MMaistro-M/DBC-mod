/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.guidebook;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.guidebook.BookPageBase;
import riskyken.armourersWorkshop.client.guidebook.IBook;
import riskyken.armourersWorkshop.utils.UtilColour;

@SideOnly(value=Side.CLIENT)
public class BookPage
extends BookPageBase {
    private final ArrayList<String> lines;

    public BookPage(IBook parentBook, ArrayList<String> lines) {
        super(parentBook);
        this.lines = lines;
    }

    public List<String> getLines() {
        return this.lines;
    }

    @Override
    public void renderPage(FontRenderer fontRenderer, int mouseX, int mouseY, boolean turning, int pageNumber) {
        Minecraft mc = Minecraft.func_71410_x();
        RenderItem itemRender = new RenderItem();
        ItemStack stack = new ItemStack(Blocks.field_150348_b);
        mc.field_71446_o.func_110577_a(bookPageTexture);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.drawPageTitleAndNumber(fontRenderer, pageNumber);
        for (int i = 0; i < this.lines.size(); ++i) {
            fontRenderer.func_78276_b(this.lines.get(i), 5, 5 + fontRenderer.field_78288_b * 2 + i * 9, UtilColour.getMinecraftColor(7, UtilColour.ColourFamily.MINECRAFT));
        }
    }

    @Override
    public void renderRollover(FontRenderer fontRenderer, int mouseX, int mouseY) {
    }
}

