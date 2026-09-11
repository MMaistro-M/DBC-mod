/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.client.config.GuiUtils
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.controls;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.client.config.GuiUtils;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiDropDownList
extends GuiButtonExt {
    private List<DropDownListItem> listItems;
    private int selectedIndex;
    private int hoverIndex;
    private boolean isMouseDownOver;
    private boolean isDroppedDown;
    private int dropButtonX;
    private int dropButtonY;
    private int dropButtonWidth;
    private int dropButtonHeight;
    private IDropDownListCallback callback;
    private boolean mouseDown = false;

    public GuiDropDownList(int id, int xPos, int yPos, int width, String displayString, IDropDownListCallback callback) {
        super(id, xPos, yPos, width, 14, displayString);
        this.callback = callback;
        this.listItems = new ArrayList<DropDownListItem>();
        this.selectedIndex = 0;
        this.hoverIndex = -1;
        this.isMouseDownOver = false;
        this.isDroppedDown = false;
        this.dropButtonHeight = this.field_146121_g;
        this.dropButtonWidth = 14;
        this.dropButtonY = this.field_146129_i;
        this.dropButtonX = this.field_146128_h + this.field_146120_f - this.dropButtonWidth;
    }

    public boolean getIsDroppedDown() {
        return this.isDroppedDown;
    }

    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        if (this.field_146125_m) {
            this.mouseCheck();
            this.field_146123_n = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
            int k = this.func_146114_a(this.field_146123_n);
            GuiUtils.drawContinuousTexturedBox((ResourceLocation)field_146122_a, (int)this.field_146128_h, (int)this.field_146129_i, (int)0, (int)46, (int)this.field_146120_f, (int)this.field_146121_g, (int)200, (int)20, (int)2, (int)3, (int)2, (int)2, (float)this.field_73735_i);
            this.drawDropDownButton(mc, mouseX, mouseY);
            mc.field_71466_p.func_78276_b(this.field_146126_j, this.field_146128_h + 3, this.field_146129_i + 3, 0xFFFFFF);
        }
    }

    public void drawForeground(Minecraft mc, int mouseX, int mouseY, float partialTickTime) {
        this.hoverIndex = -1;
        if (this.isDroppedDown) {
            int listSize = this.listItems.size();
            GuiUtils.drawContinuousTexturedBox((ResourceLocation)field_146122_a, (int)this.field_146128_h, (int)(this.field_146129_i + this.field_146121_g + 1), (int)0, (int)46, (int)this.field_146120_f, (int)(10 * listSize + 4), (int)200, (int)20, (int)2, (int)3, (int)2, (int)2, (float)this.field_73735_i);
            for (int i = 0; i < listSize; ++i) {
                DropDownListItem listItem = this.listItems.get(i);
                int textX = this.field_146128_h + 4;
                int textY = this.field_146129_i + this.field_146121_g + 4 + i * 10;
                int textWidth = this.field_146120_f - 8;
                int textHeight = 8;
                int textColour = 0xFFFFFF;
                if (!listItem.enabled) {
                    textColour = -3407872;
                } else if (mouseX >= textX && mouseY >= textY && mouseX < textX + textWidth && mouseY < textY + textHeight && listItem.enabled) {
                    textColour = 0xFFFFA0;
                    this.hoverIndex = i;
                    GuiDropDownList.func_73734_a((int)textX, (int)textY, (int)(textX + textWidth), (int)(textY + textHeight), (int)0x44CCCCCC);
                }
                mc.field_71466_p.func_78276_b(listItem.displayText, textX, textY, textColour);
            }
        }
        GL11.glEnable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void mouseCheck() {
        if (Loader.isModLoaded((String)"TooManyItems") && Mouse.isCreated()) {
            if (Mouse.isButtonDown((int)0)) {
                this.mouseDown = true;
            } else if (this.mouseDown) {
                this.mouseDown = false;
                Minecraft mc = Minecraft.func_71410_x();
                ScaledResolution reso = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
                double scaleWidth = (double)mc.field_71443_c / reso.func_78327_c();
                double scaleHeight = (double)mc.field_71440_d / reso.func_78324_d();
                int mouseX = (int)((double)Mouse.getX() / scaleWidth);
                int mouseY = (int)((double)(-(Mouse.getY() - mc.field_71440_d)) / scaleHeight);
                this.func_146118_a(mouseX, mouseY);
            }
        }
    }

    private boolean mouseOverDropDownButton(int mouseX, int mouseY) {
        return mouseX >= this.dropButtonX && mouseY >= this.dropButtonY && mouseX < this.dropButtonX + this.dropButtonWidth && mouseY < this.dropButtonY + this.dropButtonHeight;
    }

    private void drawDropDownButton(Minecraft mc, int mouseX, int mouseY) {
        int k = this.func_146114_a(this.mouseOverDropDownButton(mouseX, mouseY));
        GuiUtils.drawContinuousTexturedBox((ResourceLocation)field_146122_a, (int)this.dropButtonX, (int)this.dropButtonY, (int)0, (int)(46 + k * 20), (int)this.dropButtonWidth, (int)this.dropButtonHeight, (int)200, (int)20, (int)2, (int)3, (int)2, (int)2, (float)this.field_73735_i);
        String dropDownArrow = "v";
        if (this.isDroppedDown) {
            dropDownArrow = "^";
        }
        int arrowWidth = mc.field_71466_p.func_78256_a(dropDownArrow);
        mc.field_71466_p.func_78276_b(dropDownArrow, this.dropButtonX + this.dropButtonWidth / 2 - arrowWidth / 2, this.dropButtonY + 3, 0xFFFFFF);
    }

    public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY) {
        int oldHeight = this.field_146121_g;
        if (this.isDroppedDown) {
            this.field_146121_g += 10 * this.listItems.size() + 4;
        }
        if (super.func_146116_c(mc, mouseX, mouseY)) {
            if (this.isDroppedDown) {
                this.field_146121_g = oldHeight;
            }
            this.isMouseDownOver = this.mouseOverDropDownButton(mouseX, mouseY);
            return true;
        }
        if (this.isDroppedDown) {
            this.field_146121_g = oldHeight;
            this.isDroppedDown = false;
        }
        return false;
    }

    public void func_146118_a(int mouseX, int mouseY) {
        if (this.isMouseDownOver && this.mouseOverDropDownButton(mouseX, mouseY)) {
            this.isDroppedDown = !this.isDroppedDown;
            this.isMouseDownOver = false;
        }
        if (this.hoverIndex != -1) {
            this.setListSelectedIndex(this.hoverIndex);
            this.isDroppedDown = false;
            if (this.callback != null) {
                this.callback.onDropDownListChanged(this);
            }
        }
    }

    public void clearList() {
        this.listItems.clear();
    }

    public void addListItem(DropDownListItem listItem) {
        this.listItems.add(listItem);
    }

    public void addListItem(String displayText) {
        this.addListItem(displayText, "", true);
    }

    public void addListItem(String displayText, String tag, boolean enabled) {
        this.listItems.add(new DropDownListItem(displayText, tag, enabled));
    }

    public DropDownListItem getListIndex(int index) {
        return this.listItems.get(index);
    }

    public int getListSelectedIndex() {
        return this.selectedIndex;
    }

    public DropDownListItem getListSelectedItem() {
        return this.listItems.get(this.selectedIndex);
    }

    public void setListSelectedIndex(int index) {
        this.selectedIndex = index;
        this.field_146126_j = this.listItems.get((int)this.selectedIndex).displayText;
    }

    public int getListSize() {
        return this.listItems.size();
    }

    public class DropDownListItem {
        public String displayText;
        public String tag;
        public boolean enabled;

        public DropDownListItem(String displayText, String tag, boolean enabled) {
            this.displayText = displayText;
            this.tag = tag;
            this.enabled = enabled;
        }
    }

    public static interface IDropDownListCallback {
        public void onDropDownListChanged(GuiDropDownList var1);
    }
}

