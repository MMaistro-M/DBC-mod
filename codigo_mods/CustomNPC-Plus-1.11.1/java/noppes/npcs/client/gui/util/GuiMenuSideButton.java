/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.gui.util.GuiNpcButton;
import org.lwjgl.opengl.GL11;

public class GuiMenuSideButton
extends GuiNpcButton {
    public static final ResourceLocation resource = new ResourceLocation("customnpcs", "textures/gui/menusidebutton.png");
    public static final ResourceLocation resource2 = new ResourceLocation("customnpcs", "textures/gui/menusidebutton2.png");
    public boolean active = false;
    public boolean rightSided;
    public int textColor = -1;
    public ItemStack renderStack;
    public ResourceLocation renderResource;
    public int renderIconPosX = 0;
    public int renderIconPosY = 0;

    public GuiMenuSideButton(int id, int x, int y, String s) {
        this(id, x, y, 200, 20, s);
    }

    public GuiMenuSideButton(int id, int x, int y, int width, int height, String s) {
        super(id, x, y, width, height, s);
    }

    @Override
    public int func_146114_a(boolean flag) {
        if (this.active) {
            return 0;
        }
        return super.func_146114_a(flag);
    }

    @Override
    public void func_146112_a(Minecraft minecraft, int i, int j) {
        if (!this.field_146125_m) {
            return;
        }
        FontRenderer fontrenderer = minecraft.field_71466_p;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int width = this.field_146120_f + (this.active ? 2 : 0);
        this.field_146123_n = i >= this.field_146128_h && j >= this.field_146129_i && i < this.field_146128_h + width && j < this.field_146129_i + this.field_146121_g;
        int k = this.func_146114_a(this.field_146123_n);
        if (this.rightSided) {
            minecraft.field_71446_o.func_110577_a(resource2);
            this.func_73729_b(this.field_146128_h, this.field_146129_i, 197 - width, k * 22, width, this.field_146121_g);
        } else {
            minecraft.field_71446_o.func_110577_a(resource);
            this.func_73729_b(this.field_146128_h, this.field_146129_i, 0, k * 22, width, this.field_146121_g);
        }
        this.func_146119_b(minecraft, i, j);
        if (this.renderResource != null) {
            this.field_73735_i = 100.0f;
            minecraft.field_71446_o.func_110577_a(this.renderResource);
            this.func_73729_b(this.field_146128_h + 2, this.field_146129_i + this.field_146121_g / 2 - 8, this.renderIconPosX, this.renderIconPosY, 16, 16);
            this.field_73735_i = 0.0f;
        }
        if (this.renderStack != null) {
            RenderHelper.func_74520_c();
            this.field_73735_i = 100.0f;
            RenderItem itemRenderer = RenderItem.getInstance();
            itemRenderer.field_77023_b = 100.0f;
            GL11.glEnable((int)2896);
            GL11.glEnable((int)32826);
            itemRenderer.func_82406_b(minecraft.field_71466_p, minecraft.field_71446_o, this.renderStack, this.field_146128_h + 2, this.field_146129_i + this.field_146121_g / 2 - 8);
            itemRenderer.func_77021_b(minecraft.field_71466_p, minecraft.field_71446_o, this.renderStack, this.field_146128_h + 2, this.field_146129_i + this.field_146121_g / 2 - 8);
            GL11.glDisable((int)2896);
            GL11.glEnable((int)3042);
            itemRenderer.field_77023_b = 0.0f;
            this.field_73735_i = 0.0f;
            RenderHelper.func_74518_a();
        }
        String text = "";
        float maxWidth = (float)width * 0.75f;
        if ((float)fontrenderer.func_78256_a(this.field_146126_j) > maxWidth) {
            for (int h = 0; h < this.field_146126_j.length(); ++h) {
                char c = this.field_146126_j.charAt(h);
                if ((float)fontrenderer.func_78256_a(text + c) > maxWidth) break;
                text = text + c;
            }
            text = text + "...";
        } else {
            text = this.field_146126_j;
        }
        int color = this.textColor != -1 ? this.textColor : (this.active || this.field_146123_n ? 0xFFFFA0 : 0xE0E0E0);
        this.func_73732_a(fontrenderer, text, this.field_146128_h + width / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, color);
    }

    protected void func_146119_b(Minecraft minecraft, int i, int j) {
    }

    public void func_146118_a(int i, int j) {
    }

    @Override
    public boolean func_146116_c(Minecraft minecraft, int i, int j) {
        return !this.active && this.field_146125_m && this.field_146123_n;
    }
}

