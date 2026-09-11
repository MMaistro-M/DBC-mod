/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.customoverlay.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.api.overlay.ICustomOverlayComponent;
import noppes.npcs.client.gui.customoverlay.OverlayCustom;
import noppes.npcs.client.gui.customoverlay.interfaces.IOverlayComponent;
import noppes.npcs.scripted.overlay.ScriptOverlayLine;
import org.lwjgl.opengl.GL11;

public class CustomOverlayLine
extends Gui
implements IOverlayComponent {
    OverlayCustom parent;
    int alignment;
    int id;
    int x1;
    int y1;
    int x2;
    int y2;
    int color;
    int thickness;
    float alpha;
    float rotation;

    public CustomOverlayLine(int id, int x1, int y1, int x2, int y2, int color, int thickness) {
        this.id = id;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.thickness = thickness;
    }

    public void setParent(OverlayCustom parent) {
        this.parent = parent;
    }

    public int getID() {
        return this.id;
    }

    @Override
    public void onRender(Minecraft mc, float partialTicks) {
        double distance = Math.hypot(this.x1 - this.x2, this.y1 - this.y2);
        mc.func_110434_K().func_110577_a(new ResourceLocation("customnpcs:textures/gui/misc.png"));
        GL11.glPushMatrix();
        float red = (float)(this.color >> 16 & 0xFF) / 255.0f;
        float green = (float)(this.color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(this.color & 0xFF) / 255.0f;
        GL11.glTranslatef((float)((float)(this.alignment % 3) * ((float)OverlayCustom.scaledWidth / 2.0f)), (float)((float)(Math.floor(this.alignment / 3) * (double)((float)OverlayCustom.scaledHeight / 2.0f))), (float)0.0f);
        GL11.glTranslatef((float)this.x1, (float)this.y1, (float)this.id);
        GL11.glRotated((double)(-Math.toDegrees(Math.atan2(this.x2 - this.x1, this.y2 - this.y1))), (double)0.0, (double)0.0, (double)1.0);
        GL11.glRotated((double)this.rotation, (double)0.0, (double)0.0, (double)1.0);
        GL11.glScaled((double)this.thickness, (double)distance, (double)0.0);
        int p_73729_1_ = 0;
        int p_73729_2_ = 0;
        int p_73729_3_ = 0;
        int p_73729_4_ = 0;
        int p_73729_5_ = 1;
        int p_73729_6_ = 1;
        float f = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        tessellator.func_78369_a(red, green, blue, this.alpha);
        tessellator.func_78374_a((double)(p_73729_1_ + 0), (double)(p_73729_2_ + p_73729_6_), (double)this.field_73735_i, (double)((float)(p_73729_3_ + 0) * f), (double)((float)(p_73729_4_ + p_73729_6_) * f1));
        tessellator.func_78374_a((double)(p_73729_1_ + p_73729_5_), (double)(p_73729_2_ + p_73729_6_), (double)this.field_73735_i, (double)((float)(p_73729_3_ + p_73729_5_) * f), (double)((float)(p_73729_4_ + p_73729_6_) * f1));
        tessellator.func_78374_a((double)(p_73729_1_ + p_73729_5_), (double)(p_73729_2_ + 0), (double)this.field_73735_i, (double)((float)(p_73729_3_ + p_73729_5_) * f), (double)((float)(p_73729_4_ + 0) * f1));
        tessellator.func_78374_a((double)(p_73729_1_ + 0), (double)(p_73729_2_ + 0), (double)this.field_73735_i, (double)((float)(p_73729_3_ + 0) * f), (double)((float)(p_73729_4_ + 0) * f1));
        tessellator.func_78381_a();
        GL11.glPopMatrix();
    }

    public ICustomOverlayComponent toComponent() {
        ScriptOverlayLine line = new ScriptOverlayLine(this.id, this.x1, this.y1, this.x2, this.y2, this.color, this.thickness);
        line.setAlignment(this.alignment);
        line.setAlpha(this.alpha);
        line.setColor(this.color);
        line.setRotation(this.rotation);
        return line;
    }

    public static CustomOverlayLine fromComponent(ScriptOverlayLine component) {
        CustomOverlayLine line = new CustomOverlayLine(component.getID(), component.getX1(), component.getY1(), component.getX2(), component.getY2(), component.getColor(), component.getThickness());
        line.alignment = component.getAlignment();
        line.alpha = component.getAlpha();
        line.color = component.getColor();
        line.rotation = component.getRotation();
        return line;
    }
}

