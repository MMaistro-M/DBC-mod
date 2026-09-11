/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.util.GuiNpcButton;
import org.lwjgl.opengl.GL11;

public class GuiAuctionNavButton
extends GuiNpcButton {
    private static final ResourceLocation DEFAULT_NORMAL = new ResourceLocation("customnpcs", "textures/gui/auction/button.png");
    private static final ResourceLocation DEFAULT_PRESSED = new ResourceLocation("customnpcs", "textures/gui/auction/button_press.png");
    private ResourceLocation textureNormal;
    private ResourceLocation texturePressed;
    private ResourceLocation iconTexture;
    private ItemStack iconItem;
    private int buttonSize;
    private int iconSize;
    private boolean selected;
    private boolean isToggle;
    private final String tooltipKey;
    private List<String> customTooltip;

    public GuiAuctionNavButton(int id, int x, int y, String tooltipKey, ResourceLocation iconTexture) {
        this(id, x, y, 18, 16, tooltipKey, iconTexture, DEFAULT_NORMAL, DEFAULT_PRESSED);
    }

    public GuiAuctionNavButton(int id, int x, int y, String tooltipKey, ItemStack iconItem) {
        super(id, x, y, 18, 18, "");
        this.tooltipKey = tooltipKey;
        this.iconTexture = null;
        this.iconItem = iconItem;
        this.buttonSize = 18;
        this.iconSize = 16;
        this.textureNormal = DEFAULT_NORMAL;
        this.texturePressed = DEFAULT_PRESSED;
        this.selected = false;
        this.customTooltip = null;
    }

    public GuiAuctionNavButton(int id, int x, int y, int buttonSize, int iconSize, String tooltipKey, ResourceLocation iconTexture, ResourceLocation textureNormal, ResourceLocation texturePressed) {
        super(id, x, y, buttonSize, buttonSize, "");
        this.tooltipKey = tooltipKey;
        this.iconTexture = iconTexture;
        this.iconItem = null;
        this.buttonSize = buttonSize;
        this.iconSize = iconSize;
        this.textureNormal = textureNormal;
        this.texturePressed = texturePressed;
        this.selected = false;
        this.customTooltip = null;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public GuiAuctionNavButton setToggle(boolean toggle) {
        this.isToggle = toggle;
        return this;
    }

    public boolean isHovered() {
        return this.field_146123_n;
    }

    public void setCustomTooltip(List<String> tooltip) {
        this.customTooltip = tooltip;
    }

    public List<String> getTooltipLines() {
        if (this.customTooltip != null && !this.customTooltip.isEmpty()) {
            return this.customTooltip;
        }
        ArrayList<String> lines = new ArrayList<String>();
        if (this.tooltipKey != null && !this.tooltipKey.isEmpty()) {
            lines.add(StatCollector.func_74838_a((String)this.tooltipKey));
        }
        return lines;
    }

    public String getTooltipText() {
        return this.tooltipKey != null ? StatCollector.func_74838_a((String)this.tooltipKey) : "";
    }

    @Override
    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        boolean hovered;
        if (!this.field_146125_m) {
            return;
        }
        this.field_146123_n = hovered = mouseX >= this.field_146128_h && mouseX < this.field_146128_h + this.field_146120_f && mouseY >= this.field_146129_i && mouseY < this.field_146129_i + this.field_146121_g;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (this.selected || hovered) {
            mc.func_110434_K().func_110577_a(this.texturePressed);
        } else {
            mc.func_110434_K().func_110577_a(this.textureNormal);
        }
        Gui.func_146110_a((int)this.field_146128_h, (int)this.field_146129_i, (float)0.0f, (float)0.0f, (int)this.buttonSize, (int)this.buttonSize, (float)this.buttonSize, (float)this.buttonSize);
        int iconOffset = (this.buttonSize - this.iconSize) / 2;
        int iconX = this.field_146128_h + iconOffset;
        int iconY = this.field_146129_i + iconOffset;
        if (this.iconItem != null) {
            RenderHelper.func_74520_c();
            GL11.glEnable((int)2896);
            GL11.glEnable((int)32826);
            RenderItem itemRenderer = RenderItem.getInstance();
            itemRenderer.func_82406_b(mc.field_71466_p, mc.field_71446_o, this.iconItem, iconX, iconY);
            GL11.glDisable((int)2896);
            RenderHelper.func_74518_a();
        } else if (this.iconTexture != null) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            mc.func_110434_K().func_110577_a(this.iconTexture);
            Gui.func_146110_a((int)iconX, (int)iconY, (float)0.0f, (float)0.0f, (int)this.iconSize, (int)this.iconSize, (float)this.iconSize, (float)this.iconSize);
        }
    }

    @Override
    public boolean func_146116_c(Minecraft minecraft, int mouseX, int mouseY) {
        if (this.selected && !this.isToggle) {
            return false;
        }
        return this.field_146124_l && this.field_146125_m && mouseX >= this.field_146128_h && mouseX < this.field_146128_h + this.field_146120_f && mouseY >= this.field_146129_i && mouseY < this.field_146129_i + this.field_146121_g;
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= this.field_146128_h && mouseX < this.field_146128_h + this.field_146120_f && mouseY >= this.field_146129_i && mouseY < this.field_146129_i + this.field_146121_g;
    }
}

