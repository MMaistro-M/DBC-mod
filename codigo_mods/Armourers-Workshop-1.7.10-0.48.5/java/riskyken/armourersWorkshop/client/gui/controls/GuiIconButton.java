/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StringUtils
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.controls;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.gui.GuiHelper;

@SideOnly(value=Side.CLIENT)
public class GuiIconButton
extends GuiButtonExt {
    private final GuiScreen parent;
    private final String hoverText;
    private final ResourceLocation iconTexture;
    private String disableText;
    private int iconPosX;
    private int iconPosY;
    private int iconWidth;
    private int iconHeight;
    private boolean isPressed;
    private boolean horizontal = true;

    public GuiIconButton(GuiScreen parent, int id, int xPos, int yPos, int width, int height, String hoverText, ResourceLocation iconTexture) {
        super(id, xPos, yPos, width, height, "");
        this.parent = parent;
        this.hoverText = hoverText;
        this.iconTexture = iconTexture;
        this.disableText = "";
    }

    public GuiIconButton setIconLocation(int x, int y, int width, int height) {
        this.iconPosX = x;
        this.iconPosY = y;
        this.iconWidth = width;
        this.iconHeight = height;
        return this;
    }

    public GuiIconButton setHorizontal(boolean value) {
        this.horizontal = value;
        return this;
    }

    public void setPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public boolean isPressed() {
        return this.isPressed;
    }

    public void setDisableText(String disableText) {
        this.disableText = disableText;
    }

    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        super.func_146112_a(mc, mouseX, mouseY);
        if (!this.field_146125_m) {
            return;
        }
        this.field_146123_n = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
        int hoverState = this.func_146114_a(this.field_146123_n);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int offsetPos = 0;
        if (hoverState == 0) {
            offsetPos += (this.iconWidth + 1) * 2;
        }
        if (hoverState == 2) {
            offsetPos += this.iconWidth + 1;
        }
        if (this.isPressed) {
            offsetPos += (this.iconWidth + 1) * 2;
        }
        mc.field_71446_o.func_110577_a(this.iconTexture);
        if (this.horizontal) {
            this.func_73729_b(this.field_146128_h + this.field_146120_f / 2 - this.iconWidth / 2, this.field_146129_i + this.field_146121_g / 2 - this.iconHeight / 2, this.iconPosX + offsetPos, this.iconPosY, this.iconWidth, this.iconHeight);
        } else {
            this.func_73729_b(this.field_146128_h + this.field_146120_f / 2 - this.iconWidth / 2, this.field_146129_i + this.field_146121_g / 2 - this.iconHeight / 2, this.iconPosX, this.iconPosY + offsetPos, this.iconWidth, this.iconHeight);
        }
    }

    public void drawRollover(Minecraft mc, int mouseX, int mouseY) {
        ArrayList<String> textList;
        if (!this.field_146125_m) {
            return;
        }
        this.field_146123_n = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
        int hoverState = this.func_146114_a(this.field_146123_n);
        if (hoverState == 0 & this.field_146123_n && !StringUtils.func_151246_b((String)this.disableText)) {
            textList = new ArrayList<String>();
            textList.add(this.disableText);
            GuiHelper.drawHoveringText(textList, mouseX, mouseY, mc.field_71466_p, this.parent.field_146294_l, this.parent.field_146295_m, this.field_73735_i);
        }
        if (hoverState == 2 && !StringUtils.func_151246_b((String)this.hoverText)) {
            textList = new ArrayList();
            textList.add(this.hoverText);
            GuiHelper.drawHoveringText(textList, mouseX, mouseY, mc.field_71466_p, this.parent.field_146294_l, this.parent.field_146295_m, this.field_73735_i);
        }
    }
}

