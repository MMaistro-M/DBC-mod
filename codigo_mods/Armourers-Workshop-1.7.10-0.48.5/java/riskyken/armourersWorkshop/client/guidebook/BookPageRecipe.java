/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.item.crafting.ShapedRecipes
 *  net.minecraft.item.crafting.ShapelessRecipes
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.oredict.ShapedOreRecipe
 *  net.minecraftforge.oredict.ShapelessOreRecipe
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.guidebook;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.MathHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.guidebook.BookPageBase;
import riskyken.armourersWorkshop.client.guidebook.IBook;

@SideOnly(value=Side.CLIENT)
public class BookPageRecipe
extends BookPageBase {
    private static final int TEXT_COLOUR = -16777216;
    private static RenderItem itemRender = new RenderItem();
    private Item item;
    private List<IRecipe> validRecipes;

    public BookPageRecipe(IBook parentBook, Block block) {
        this(parentBook, Item.func_150898_a((Block)block));
    }

    public BookPageRecipe(IBook parentBook, Item item) {
        super(parentBook);
        this.item = item;
        this.validRecipes = new ArrayList<IRecipe>();
        List recipeList = CraftingManager.func_77594_a().func_77592_b();
        for (int i = 0; i < recipeList.size(); ++i) {
            IRecipe recipe = (IRecipe)recipeList.get(i);
            if (recipe.func_77571_b() == null || recipe.func_77571_b().func_77973_b() != item || !(recipe instanceof ShapedRecipes | recipe instanceof ShapelessRecipes | recipe instanceof ShapedOreRecipe | recipe instanceof ShapelessOreRecipe)) continue;
            this.validRecipes.add(recipe);
        }
    }

    @Override
    public void renderPage(FontRenderer fontRenderer, int mouseX, int mouseY, boolean turning, int pageNumber) {
        GL11.glEnable((int)3042);
        this.drawPageTitleAndNumber(fontRenderer, pageNumber);
        ItemStack result = new ItemStack(this.item);
        List lines = fontRenderer.func_78271_c(result.func_82833_r(), 118);
        for (int i = 0; i < lines.size(); ++i) {
            this.renderStringCenter(fontRenderer, (String)lines.get(i), 12 + fontRenderer.field_78288_b * 2 + i * fontRenderer.field_78288_b);
        }
        Minecraft mc = Minecraft.func_71410_x();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (this.validRecipes.size() > 0) {
            this.renderRecipe(mc, fontRenderer, this.validRecipes.get(0), 0, lines.size() * fontRenderer.field_78288_b);
        }
    }

    private void renderRecipe(Minecraft mc, FontRenderer fontRenderer, IRecipe recipe, int x, int y) {
        ShapedOreRecipe shapedOreRecipe;
        int iy;
        int ix;
        RenderHelper.func_74520_c();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)32826);
        GL11.glEnable((int)2929);
        if (recipe instanceof ShapedRecipes) {
            ShapedRecipes shapedRecipe = (ShapedRecipes)recipe;
            for (ix = 0; ix < shapedRecipe.field_77576_b; ++ix) {
                for (iy = 0; iy < shapedRecipe.field_77577_c; ++iy) {
                    itemRender.func_82406_b(fontRenderer, mc.func_110434_K(), shapedRecipe.field_77574_d[0], x, y + 10);
                }
            }
        }
        if (recipe instanceof ShapedOreRecipe) {
            shapedOreRecipe = (ShapedOreRecipe)recipe;
            Object[] input = shapedOreRecipe.getInput();
            int width = (Integer)ReflectionHelper.getPrivateValue(ShapedOreRecipe.class, (Object)shapedOreRecipe, (String[])new String[]{"width"});
            int height = (Integer)ReflectionHelper.getPrivateValue(ShapedOreRecipe.class, (Object)shapedOreRecipe, (String[])new String[]{"height"});
            for (int ix2 = 0; ix2 < width; ++ix2) {
                for (int iy2 = 0; iy2 < height; ++iy2) {
                    ItemStack stack;
                    Object inputObj = input[ix2 + iy2 * width];
                    if (inputObj == null || !(inputObj instanceof ItemStack | inputObj instanceof ArrayList)) continue;
                    if (inputObj instanceof ArrayList) {
                        ArrayList list = (ArrayList)inputObj;
                        stack = (ItemStack)list.get(0);
                    } else {
                        stack = (ItemStack)inputObj;
                    }
                    if (stack.func_77960_j() == Short.MAX_VALUE) {
                        ArrayList subItems = new ArrayList();
                        stack.func_77973_b().func_150895_a(stack.func_77973_b(), null, subItems);
                        int item = MathHelper.func_76128_c((double)((double)(System.currentTimeMillis() + (long)((ix2 + iy2) * 1000)) / 1000.0 % (double)subItems.size()));
                        stack = (ItemStack)subItems.get(item);
                    }
                    itemRender.func_82406_b(fontRenderer, mc.func_110434_K(), stack, x + 32 + ix2 * 18, y + 30 + iy2 * 18 + 10);
                    itemRender.func_77021_b(fontRenderer, mc.func_110434_K(), stack, x + 32 + ix2 * 18, y + 30 + iy2 * 18 + 10);
                }
            }
        }
        if (recipe instanceof ShapelessRecipes) {
            shapedOreRecipe = (ShapelessRecipes)recipe;
        }
        if (recipe instanceof ShapelessOreRecipe) {
            ShapelessOreRecipe shapelessOreRecipe = (ShapelessOreRecipe)recipe;
            ix = 0;
            iy = 0;
            for (int i = 0; i < shapelessOreRecipe.func_77570_a(); ++i) {
                Object inputObj = shapelessOreRecipe.getInput().get(i);
                if (inputObj != null && inputObj instanceof ItemStack | inputObj instanceof ArrayList) {
                    ItemStack stack;
                    if (inputObj instanceof ArrayList) {
                        ArrayList list = (ArrayList)inputObj;
                        stack = (ItemStack)list.get(0);
                    } else {
                        stack = (ItemStack)inputObj;
                    }
                    itemRender.func_82406_b(fontRenderer, mc.func_110434_K(), stack, x + 32 + ix * 18, y + 30 + iy * 18 + 10);
                    itemRender.func_77021_b(fontRenderer, mc.func_110434_K(), stack, x + 32 + ix * 18, y + 30 + iy * 18 + 10);
                }
                if (++ix <= 2) continue;
                ix = 0;
                ++iy;
            }
        }
        itemRender.func_82406_b(fontRenderer, mc.func_110434_K(), recipe.func_77571_b(), x + 50, y + 100);
        GL11.glDisable((int)2896);
    }

    public void drawTexturedModalRect(int p_73729_1_, int p_73729_2_, int p_73729_3_, int p_73729_4_, int p_73729_5_, int p_73729_6_) {
        float zLevel = 1.0f;
        float f = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a((double)(p_73729_1_ + 0), (double)(p_73729_2_ + p_73729_6_), (double)zLevel, (double)((float)(p_73729_3_ + 0) * f), (double)((float)(p_73729_4_ + p_73729_6_) * f1));
        tessellator.func_78374_a((double)(p_73729_1_ + p_73729_5_), (double)(p_73729_2_ + p_73729_6_), (double)zLevel, (double)((float)(p_73729_3_ + p_73729_5_) * f), (double)((float)(p_73729_4_ + p_73729_6_) * f1));
        tessellator.func_78374_a((double)(p_73729_1_ + p_73729_5_), (double)(p_73729_2_ + 0), (double)zLevel, (double)((float)(p_73729_3_ + p_73729_5_) * f), (double)((float)(p_73729_4_ + 0) * f1));
        tessellator.func_78374_a((double)(p_73729_1_ + 0), (double)(p_73729_2_ + 0), (double)zLevel, (double)((float)(p_73729_3_ + 0) * f), (double)((float)(p_73729_4_ + 0) * f1));
        tessellator.func_78381_a();
    }

    @Override
    public void renderRollover(FontRenderer fontRenderer, int mouseX, int mouseY) {
    }
}

