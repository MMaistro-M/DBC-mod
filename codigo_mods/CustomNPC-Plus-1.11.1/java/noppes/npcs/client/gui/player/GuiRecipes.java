/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.gui.util.GuiButtonNextPage;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.controllers.RecipeController;
import noppes.npcs.controllers.data.RecipeCarpentry;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiRecipes
extends GuiNPCInterface {
    private static final ResourceLocation resource = new ResourceLocation("customnpcs", "textures/gui/slot.png");
    private int page = 0;
    private GuiNpcLabel label;
    private GuiNpcButton left;
    private GuiNpcButton right;
    private List<IRecipe> recipes = new ArrayList<IRecipe>();
    private String search = "";

    public GuiRecipes() {
        this.ySize = 182;
        this.xSize = 256;
        this.setBackground("recipes.png");
        this.closeOnEsc = true;
        this.recipes.addAll(RecipeController.Instance.carpentryRecipes.values());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.guiTop += 10;
        this.addTextField(new GuiNpcTextField(3, this, this.field_146289_q, this.guiLeft + 2, this.guiTop - 25, 250, 20, this.search));
        this.addLabel(new GuiNpcLabel(0, "Recipe List", this.guiLeft + 5, this.guiTop + 5));
        this.label = new GuiNpcLabel(1, "", this.guiLeft + 5, this.guiTop + 168);
        this.addLabel(this.label);
        this.left = new GuiButtonNextPage(1, this.guiLeft + 150, this.guiTop + 164, true);
        this.addButton(this.left);
        this.right = new GuiButtonNextPage(2, this.guiLeft + 80, this.guiTop + 164, false);
        this.addButton(this.right);
        this.updateButton();
    }

    private void updateButton() {
        this.right.field_146124_l = this.page > 0;
        this.right.field_146125_m = this.right.field_146124_l;
        this.left.field_146124_l = this.page + 1 < MathHelper.func_76123_f((float)((float)this.recipes.size() / 4.0f));
        this.left.field_146125_m = this.left.field_146124_l;
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.search.equals(this.getTextField(3).func_146179_b())) {
            return;
        }
        this.search = this.getTextField(3).func_146179_b().toLowerCase();
        this.recipes = this.getSearchList();
        this.updateButton();
    }

    private List<IRecipe> getSearchList() {
        if (this.search.isEmpty()) {
            return new ArrayList<RecipeCarpentry>(RecipeController.Instance.carpentryRecipes.values());
        }
        ArrayList<IRecipe> list = new ArrayList<IRecipe>();
        for (IRecipe iRecipe : RecipeController.Instance.carpentryRecipes.values()) {
            if (iRecipe.func_77571_b() == null || iRecipe.func_77571_b().func_82833_r() == null || iRecipe.func_77571_b().func_82833_r().trim().equals("") || !iRecipe.func_77571_b().func_82833_r().toLowerCase().contains(this.search.toLowerCase())) continue;
            list.add(iRecipe);
        }
        return list;
    }

    @Override
    protected void func_146284_a(GuiButton button) {
        if (!button.field_146124_l) {
            return;
        }
        if (button == this.right) {
            --this.page;
        }
        if (button == this.left) {
            ++this.page;
        }
        this.updateButton();
    }

    @Override
    public void func_73863_a(int xMouse, int yMouse, float f) {
        int index;
        super.func_73863_a(xMouse, yMouse, f);
        this.field_146297_k.field_71446_o.func_110577_a(resource);
        this.label.label = this.page + 1 + "/" + MathHelper.func_76123_f((float)((float)this.recipes.size() / 4.0f));
        this.label.x = this.guiLeft + (256 - Minecraft.func_71410_x().field_71466_p.func_78256_a(this.label.label)) / 2;
        ArrayList<ItemOverlayData> itemOverlays = new ArrayList<ItemOverlayData>();
        ArrayList<TextOverlayData> textOverlays = new ArrayList<TextOverlayData>();
        for (int i = 0; i < 4 && (index = i + this.page * 4) < this.recipes.size(); ++i) {
            RecipeCarpentry recipe;
            IRecipe irecipe = this.recipes.get(index);
            if (irecipe.func_77571_b() == null) continue;
            int x = this.guiLeft + 5 + i / 2 * 126;
            int y = this.guiTop + 15 + i % 2 * 76;
            this.drawItem(irecipe.func_77571_b(), x + 98, y + 28, xMouse, yMouse);
            if (this.func_146978_c(x + 98 - this.guiLeft, y + 28 - this.guiTop, 16, 16, xMouse, yMouse)) {
                itemOverlays.add(new ItemOverlayData(x + 98, y + 28, irecipe.func_77571_b()));
            }
            if (irecipe instanceof RecipeCarpentry) {
                recipe = (RecipeCarpentry)irecipe;
                if (!recipe.isGlobal && recipe.availability != null && !recipe.availability.isDefault()) {
                    int iconColor;
                    String iconStr;
                    int outputX = x + 98;
                    int outputY = y + 38;
                    float scale = 1.0f;
                    if (!recipe.availability.isAvailable((EntityPlayer)this.field_146297_k.field_71439_g)) {
                        iconStr = "?";
                        iconColor = 12402229;
                    } else {
                        iconStr = "!";
                        iconColor = CustomNpcResourceListener.DefaultTextColor;
                    }
                    int iconWidth = (int)((float)this.field_146289_q.func_78256_a(iconStr) * scale);
                    int iconHeight = (int)((float)this.field_146289_q.field_78288_b * scale);
                    int iconX = outputX + (16 - iconWidth) / 2;
                    int iconY = outputY + 16;
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)iconX, (float)iconY, (float)0.0f);
                    GL11.glScalef((float)scale, (float)scale, (float)1.0f);
                    this.field_146289_q.func_78276_b(iconStr, 0, 0, iconColor);
                    GL11.glPopMatrix();
                    if (this.func_146978_c(iconX - this.guiLeft, iconY - this.guiTop, iconWidth, iconHeight, xMouse, yMouse)) {
                        List<String> tooltipLines = recipe.availability.isAvailableText((EntityPlayer)this.field_146297_k.field_71439_g);
                        if (tooltipLines.isEmpty()) {
                            tooltipLines.add(StatCollector.func_74838_a((String)"gui.available"));
                        }
                        textOverlays.add(new TextOverlayData(iconX, iconY, iconWidth, iconHeight, tooltipLines));
                    }
                }
            }
            if (!(irecipe instanceof RecipeCarpentry)) continue;
            recipe = (RecipeCarpentry)irecipe;
            int gridX = x + (72 - recipe.field_77576_b * 18) / 2;
            int gridY = y + (72 - recipe.field_77577_c * 18) / 2;
            for (int j = 0; j < recipe.field_77576_b; ++j) {
                for (int k = 0; k < recipe.field_77577_c; ++k) {
                    this.field_146297_k.field_71446_o.func_110577_a(resource);
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    this.func_73729_b(gridX + j * 18, gridY + k * 18, 0, 0, 18, 18);
                    ItemStack item = recipe.getCraftingItem(j + k * recipe.field_77576_b);
                    if (item == null) continue;
                    this.drawItem(item, gridX + j * 18 + 1, gridY + k * 18 + 1, xMouse, yMouse);
                    if (!this.func_146978_c(gridX + j * 18 + 1 - this.guiLeft, gridY + k * 18 + 1 - this.guiTop, 16, 16, xMouse, yMouse)) continue;
                    itemOverlays.add(new ItemOverlayData(gridX + j * 18 + 1, gridY + k * 18 + 1, item));
                }
            }
        }
        for (ItemOverlayData iod : itemOverlays) {
            this.func_146285_a(iod.item, xMouse, yMouse);
        }
        for (TextOverlayData tod : textOverlays) {
            this.drawHoveringText(tod.textLines, xMouse, yMouse, this.field_146289_q);
        }
    }

    private void drawItem(ItemStack item, int x, int y, int xMouse, int yMouse) {
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        RenderHelper.func_74520_c();
        GuiRecipes.field_146296_j.field_77023_b = 100.0f;
        field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.field_71446_o, item, x, y);
        field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.field_71446_o, item, x, y);
        GuiRecipes.field_146296_j.field_77023_b = 0.0f;
        RenderHelper.func_74518_a();
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }

    private void drawOverlay(ItemStack item, int x, int y, int xMouse, int yMouse) {
        if (this.func_146978_c(x - this.guiLeft, y - this.guiTop, 16, 16, xMouse, yMouse)) {
            this.func_146285_a(item, xMouse, yMouse);
        }
    }

    protected boolean func_146978_c(int p_146978_1_, int p_146978_2_, int p_146978_3_, int p_146978_4_, int p_146978_5_, int p_146978_6_) {
        int k1 = this.guiLeft;
        int l1 = this.guiTop;
        return (p_146978_5_ -= k1) >= p_146978_1_ - 1 && p_146978_5_ < p_146978_1_ + p_146978_3_ + 1 && (p_146978_6_ -= l1) >= p_146978_2_ - 1 && p_146978_6_ < p_146978_2_ + p_146978_4_ + 1;
    }

    @Override
    public void save() {
    }

    private static class TextOverlayData {
        public final int x;
        public final int y;
        public final int width;
        public final int height;
        public final List<String> textLines;

        public TextOverlayData(int x, int y, int width, int height, String text) {
            this(x, y, width, height, Collections.singletonList(text));
        }

        public TextOverlayData(int x, int y, int width, int height, List<String> textLines) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.textLines = textLines;
        }
    }

    private static class ItemOverlayData {
        public final int x;
        public final int y;
        public final ItemStack item;

        public ItemOverlayData(int x, int y, ItemStack item) {
            this.x = x;
            this.y = y;
            this.item = item;
        }
    }
}

