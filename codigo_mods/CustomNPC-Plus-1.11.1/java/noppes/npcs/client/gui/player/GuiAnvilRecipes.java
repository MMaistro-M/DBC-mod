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
import noppes.npcs.controllers.data.RecipeAnvil;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiAnvilRecipes
extends GuiNPCInterface {
    private static final ResourceLocation SLOT_TEXTURE = new ResourceLocation("customnpcs", "textures/gui/slot.png");
    private int page = 0;
    private GuiNpcLabel lblPage;
    private GuiNpcButton btnLeft;
    private GuiNpcButton btnRight;
    private List<RecipeAnvil> recipes = new ArrayList<RecipeAnvil>();
    private String search = "";

    public GuiAnvilRecipes() {
        this.xSize = 256;
        this.ySize = 182;
        this.setBackground("recipesAnvil.png");
        this.closeOnEsc = true;
        this.recipes.addAll(RecipeController.Instance.anvilRecipes.values());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.guiTop += 10;
        this.addTextField(new GuiNpcTextField(3, this, this.field_146289_q, this.guiLeft + 2, this.guiTop - 25, 250, 20, this.search));
        this.addLabel(new GuiNpcLabel(0, "Anvil Recipe List", this.guiLeft + 5, this.guiTop + 5));
        this.lblPage = new GuiNpcLabel(1, "", this.guiLeft + 5, this.guiTop + 168);
        this.addLabel(this.lblPage);
        this.btnLeft = new GuiButtonNextPage(1, this.guiLeft + 150, this.guiTop + 164, true);
        this.addButton(this.btnLeft);
        this.btnRight = new GuiButtonNextPage(2, this.guiLeft + 80, this.guiTop + 164, false);
        this.addButton(this.btnRight);
        this.updateButton();
    }

    private void updateButton() {
        int maxPages = MathHelper.func_76123_f((float)((float)this.recipes.size() / 8.0f));
        this.btnRight.field_146124_l = this.page > 0;
        this.btnRight.field_146125_m = this.btnRight.field_146124_l;
        this.btnLeft.field_146124_l = this.page < maxPages - 1;
        this.btnLeft.field_146125_m = this.btnLeft.field_146124_l;
        this.lblPage.label = this.page + 1 + "/" + maxPages;
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.search.equals(this.getTextField(3).func_146179_b())) {
            return;
        }
        this.search = this.getTextField(3).func_146179_b().toLowerCase();
        this.recipes = this.getSearchList();
        this.page = 0;
        this.updateButton();
    }

    private List<RecipeAnvil> getSearchList() {
        if (this.search.isEmpty()) {
            return new ArrayList<RecipeAnvil>(RecipeController.Instance.anvilRecipes.values());
        }
        ArrayList<RecipeAnvil> list = new ArrayList<RecipeAnvil>();
        for (RecipeAnvil recipe : RecipeController.Instance.anvilRecipes.values()) {
            ItemStack repairItem = recipe.itemToRepair;
            if (repairItem == null || repairItem.func_82833_r() == null || repairItem.func_82833_r().trim().isEmpty() || !repairItem.func_82833_r().toLowerCase().contains(this.search)) continue;
            list.add(recipe);
        }
        return list;
    }

    @Override
    protected void func_146284_a(GuiButton button) {
        int maxPages;
        if (!button.field_146124_l) {
            return;
        }
        if (button == this.btnRight && this.page > 0) {
            --this.page;
        }
        if (button == this.btnLeft && this.page < (maxPages = MathHelper.func_76123_f((float)((float)this.recipes.size() / 8.0f))) - 1) {
            ++this.page;
        }
        this.updateButton();
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        super.func_73863_a(mouseX, mouseY, partialTicks);
        this.lblPage.label = this.page + 1 + "/" + MathHelper.func_76123_f((float)((float)this.recipes.size() / 8.0f));
        this.lblPage.x = this.guiLeft + (this.xSize - Minecraft.func_71410_x().field_71466_p.func_78256_a(this.lblPage.label)) / 2;
        int recipesPerPage = 8;
        int startIndex = this.page * recipesPerPage;
        int endIndex = Math.min(this.recipes.size(), startIndex + recipesPerPage);
        int baseX = this.guiLeft + 4;
        int baseY = this.guiTop + 28;
        int colSpacing = 124;
        int rowSpacing = 35;
        ArrayList<ItemOverlayData> itemOverlays = new ArrayList<ItemOverlayData>();
        ArrayList<TextOverlayData> textOverlays = new ArrayList<TextOverlayData>();
        for (int i = startIndex; i < endIndex; ++i) {
            ItemStack material;
            RecipeAnvil recipe = this.recipes.get(i);
            if (!recipe.isValid()) continue;
            int localIndex = i - startIndex;
            int col = localIndex / 4;
            int row = localIndex % 4;
            int slotX = baseX + col * colSpacing;
            int slotY = baseY + row * rowSpacing;
            this.field_146297_k.field_71446_o.func_110577_a(SLOT_TEXTURE);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.func_73729_b(slotX, slotY, 0, 0, 18, 18);
            this.func_73729_b(slotX + 22, slotY, 0, 0, 18, 18);
            ItemStack toRepair = recipe.itemToRepair;
            ItemStack halfItem = null;
            if (toRepair != null) {
                halfItem = toRepair.func_77946_l();
                if (halfItem.func_77984_f()) {
                    int halfDamage = halfItem.func_77958_k() / 2;
                    halfItem.func_77964_b(halfDamage);
                }
                this.drawItem(halfItem, slotX + 1, slotY + 1, mouseX, mouseY);
                if (this.func_146978_c(slotX + 1 - this.guiLeft, slotY + 1 - this.guiTop, 16, 16, mouseX, mouseY)) {
                    itemOverlays.add(new ItemOverlayData(slotX + 1, slotY + 1, halfItem));
                }
            }
            if ((material = recipe.repairMaterial) != null) {
                this.drawItem(material, slotX + 23, slotY + 1, mouseX, mouseY);
                if (this.func_146978_c(slotX + 23 - this.guiLeft, slotY + 1 - this.guiTop, 16, 16, mouseX, mouseY)) {
                    itemOverlays.add(new ItemOverlayData(slotX + 23, slotY + 1, material));
                }
            }
            int outputX = col == 0 ? this.guiLeft + 103 : this.guiLeft + 229;
            ItemStack fullOutput = null;
            if (recipe.itemToRepair != null) {
                fullOutput = recipe.itemToRepair.func_77946_l();
                if (fullOutput.func_77984_f()) {
                    fullOutput.func_77964_b(0);
                }
                this.drawItem(fullOutput, outputX, slotY, mouseX, mouseY);
                if (this.func_146978_c(outputX - this.guiLeft, slotY - this.guiTop, 16, 16, mouseX, mouseY)) {
                    itemOverlays.add(new ItemOverlayData(outputX, slotY, fullOutput));
                }
            }
            int percentCenterX = col == 0 ? this.guiLeft + 57 : this.guiLeft + 57 + colSpacing;
            String percentText = Math.round(recipe.getRepairPercentage()) + "%";
            int percentWidth = this.field_146289_q.func_78256_a(percentText);
            int percentTextX = percentCenterX - percentWidth / 2;
            int percentTextY = slotY + 4 + row;
            this.field_146289_q.func_78276_b(percentText, percentTextX, percentTextY, CustomNpcResourceListener.DefaultTextColor);
            if (this.func_146978_c(percentTextX - this.guiLeft, percentTextY - this.guiTop, percentWidth, this.field_146289_q.field_78288_b, mouseX, mouseY)) {
                textOverlays.add(new TextOverlayData(percentTextX, percentTextY, percentWidth, this.field_146289_q.field_78288_b, "" + recipe.getRepairPercentage()));
            }
            if (!recipe.availability.isDefault()) {
                int questionY;
                int questionX;
                int questionHeight;
                String helpChar;
                if (!recipe.availability.isAvailable((EntityPlayer)this.player)) {
                    helpChar = "?";
                    float scale = 1.0f;
                    int questionWidth = (int)((float)this.field_146289_q.func_78256_a(helpChar) * scale);
                    questionHeight = (int)((float)this.field_146289_q.field_78288_b * scale);
                    questionX = percentCenterX - questionWidth / 2 - 1 - row;
                    questionY = percentTextY + this.field_146289_q.field_78288_b;
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)questionX, (float)questionY, (float)0.0f);
                    GL11.glScalef((float)scale, (float)scale, (float)1.0f);
                    this.field_146289_q.func_78276_b(helpChar, 0, 0, 12402229);
                    GL11.glPopMatrix();
                    if (this.func_146978_c(questionX - this.guiLeft, questionY - this.guiTop, questionWidth, questionHeight, mouseX, mouseY)) {
                        List<String> availableText = recipe.availability.isAvailableText((EntityPlayer)this.player);
                        textOverlays.add(new TextOverlayData(questionX, questionY, questionWidth, questionHeight, availableText));
                    }
                } else {
                    helpChar = "!";
                    float scale = 1.0f;
                    int questionWidth = (int)((float)this.field_146289_q.func_78256_a(helpChar) * scale);
                    questionHeight = (int)((float)this.field_146289_q.field_78288_b * scale);
                    questionX = percentCenterX - questionWidth / 2 - 1 - row;
                    questionY = percentTextY + this.field_146289_q.field_78288_b;
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)questionX, (float)questionY, (float)0.0f);
                    GL11.glScalef((float)scale, (float)scale, (float)1.0f);
                    this.field_146289_q.func_78276_b(helpChar, 0, 0, CustomNpcResourceListener.DefaultTextColor);
                    GL11.glPopMatrix();
                    if (this.func_146978_c(questionX - this.guiLeft, questionY - this.guiTop, questionWidth, questionHeight, mouseX, mouseY)) {
                        textOverlays.add(new TextOverlayData(questionX, questionY, questionWidth, questionHeight, StatCollector.func_74838_a((String)"gui.available")));
                    }
                }
            }
            String xpCostStr = this.formatXpCost(recipe.getXpCost());
            int digitCount = xpCostStr.length();
            float xpScale = 1.0f - 0.1f * (float)(digitCount - 1);
            int rawTextWidth = this.field_146289_q.func_78256_a(xpCostStr);
            int scaledTextWidth = (int)((float)rawTextWidth * xpScale);
            int xpCenterX = col == 0 ? this.guiLeft + 76 : this.guiLeft + 76 + colSpacing;
            int xpTextX = xpCenterX - scaledTextWidth / 2 + col;
            int xpTextY = slotY - 4 + row;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)xpTextX, (float)xpTextY, (float)0.0f);
            GL11.glScalef((float)xpScale, (float)xpScale, (float)1.0f);
            this.field_146289_q.func_78276_b(xpCostStr, 0, 0, 4899434);
            GL11.glPopMatrix();
            int xpBoxWidth = scaledTextWidth;
            int xpBoxHeight = this.field_146289_q.field_78288_b;
            if (!this.func_146978_c(xpTextX - this.guiLeft, xpTextY - this.guiTop, xpBoxWidth, xpBoxHeight, mouseX, mouseY)) continue;
            textOverlays.add(new TextOverlayData(xpTextX, xpTextY, xpBoxWidth, xpBoxHeight, "" + recipe.getXpCost()));
        }
        for (ItemOverlayData iod : itemOverlays) {
            this.func_146285_a(iod.item, mouseX, mouseY);
        }
        for (TextOverlayData tod : textOverlays) {
            this.drawHoveringText(tod.textLines, mouseX, mouseY, this.field_146289_q);
        }
    }

    protected boolean func_146978_c(int p_146978_1_, int p_146978_2_, int p_146978_3_, int p_146978_4_, int p_146978_5_, int p_146978_6_) {
        int k1 = this.guiLeft;
        int l1 = this.guiTop;
        return (p_146978_5_ -= k1) >= p_146978_1_ - 1 && p_146978_5_ < p_146978_1_ + p_146978_3_ + 1 && (p_146978_6_ -= l1) >= p_146978_2_ - 1 && p_146978_6_ < p_146978_2_ + p_146978_4_ + 1;
    }

    private String formatXpCost(int xp) {
        if (xp < 1000) {
            return "" + xp;
        }
        double xpK = (double)xp / 1000.0;
        if (xpK == (double)((int)xpK)) {
            return (int)xpK + "K";
        }
        return String.format("%.1fK", xpK);
    }

    private void drawItem(ItemStack item, int x, int y, int mouseX, int mouseY) {
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        RenderHelper.func_74520_c();
        GuiAnvilRecipes.field_146296_j.field_77023_b = 100.0f;
        field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.field_71446_o, item, x, y);
        field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.field_71446_o, item, x, y);
        GuiAnvilRecipes.field_146296_j.field_77023_b = 0.0f;
        RenderHelper.func_74518_a();
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
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

