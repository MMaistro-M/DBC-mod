/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import org.lwjgl.opengl.GL11;

public class GuiCustomScrollIcons
extends GuiCustomScroll {
    public static final int ICON_NONE = 0;
    public static final int ICON_TAB = 1;
    public static final int ICON_FOLDER = 2;
    private static final ResourceLocation TAB_TEXTURE = new ResourceLocation("customnpcs", "textures/gui/cloner/tab.png");
    private static final ResourceLocation FOLDER_TEXTURE = new ResourceLocation("customnpcs", "textures/gui/cloner/folder.png");
    private static final int ICON_RENDER_SIZE = 10;
    private static final int ICON_PADDING = 14;
    private List<Integer> iconTypes = new ArrayList<Integer>();

    public GuiCustomScrollIcons(GuiScreen parent, int id) {
        super(parent, id);
    }

    public void setListWithIcons(List<String> names, List<Integer> icons) {
        this.setUnsortedList(names);
        this.iconTypes = new ArrayList<Integer>(icons);
    }

    @Override
    protected void drawItems() {
        Minecraft mc = Minecraft.func_71410_x();
        for (int i = 0; i < this.list.size(); ++i) {
            int j = 4;
            int k = 14 * i + 4 - this.scrollY;
            if (k < 4 || k + 12 >= this.ySize) continue;
            int xOffset = this.scrollHeight < this.ySize - 8 ? 0 : 10;
            int iconType = i < this.iconTypes.size() ? this.iconTypes.get(i) : 0;
            int textX = j;
            if (iconType != 0) {
                textX = j + 14;
            }
            if (iconType == 2 || iconType == 1) {
                ResourceLocation tex = iconType == 2 ? FOLDER_TEXTURE : TAB_TEXTURE;
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                mc.func_110434_K().func_110577_a(tex);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                this.drawFullTexture(j, k - 1, 10, 10);
                GL11.glDisable((int)3042);
            }
            String displayString = StatCollector.func_74838_a((String)((String)this.list.get(i)));
            String text = "";
            float maxWidth = (float)(this.xSize + xOffset - 8 - (iconType != 0 ? 14 : 0)) * 0.8f;
            if ((float)this.field_146289_q.func_78256_a(displayString) > maxWidth) {
                for (int h = 0; h < displayString.length(); ++h) {
                    char c = displayString.charAt(h);
                    if ((float)this.field_146289_q.func_78256_a(text = text + c) > maxWidth) break;
                }
                if (displayString.length() > text.length()) {
                    text = text + "...";
                }
            } else {
                text = displayString;
            }
            int itemColor = this.colors.getOrDefault(this.list.get(i), 0xFFFFFF);
            if (this.multipleSelection && this.selectedList.contains(this.list.get(i)) || !this.multipleSelection && this.selected == i) {
                this.func_73728_b(j - 2, k - 4, k + 10, -1);
                this.func_73728_b(j + this.xSize - 18 + xOffset, k - 4, k + 10, -1);
                this.func_73730_a(j - 2, j + this.xSize - 18 + xOffset, k - 3, -1);
                this.func_73730_a(j - 2, j + this.xSize - 18 + xOffset, k + 10, -1);
                this.field_146289_q.func_78276_b(text, textX, k, itemColor);
                continue;
            }
            if (i == this.hover) {
                this.field_146289_q.func_78276_b(text, textX, k, 65280);
                continue;
            }
            this.field_146289_q.func_78276_b(text, textX, k, itemColor);
        }
    }

    private void drawFullTexture(int x, int y, int w, int h) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a((double)x, (double)(y + h), 0.0, 0.0, 1.0);
        tessellator.func_78374_a((double)(x + w), (double)(y + h), 0.0, 1.0, 1.0);
        tessellator.func_78374_a((double)(x + w), (double)y, 0.0, 1.0, 0.0);
        tessellator.func_78374_a((double)x, (double)y, 0.0, 0.0, 0.0);
        tessellator.func_78381_a();
    }
}

