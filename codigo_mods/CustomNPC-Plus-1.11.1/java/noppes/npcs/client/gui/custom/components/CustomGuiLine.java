/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.custom.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.api.gui.ICustomGuiComponent;
import noppes.npcs.client.gui.custom.GuiCustom;
import noppes.npcs.client.gui.custom.interfaces.IGuiComponent;
import noppes.npcs.scripted.gui.ScriptGuiLine;
import org.lwjgl.opengl.GL11;

public class CustomGuiLine
extends Gui
implements IGuiComponent {
    int id;
    GuiCustom parent;
    int x1;
    int y1;
    int x2;
    int y2;
    int color;
    float alpha;
    float rotation;
    int thickness;

    public CustomGuiLine(int id, int x1, int y1, int x2, int y2, int color, int thickness) {
        this.id = id;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.thickness = thickness;
    }

    public void setParent(GuiCustom parent) {
        this.parent = parent;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void onRender(Minecraft mc, int mouseX, int mouseY, int mouseWheel, float partialTicks) {
        double distance = Math.sqrt(Math.pow(this.x1 - this.x2, 2.0) + Math.pow(this.y1 - this.y2, 2.0));
        mc.func_110434_K().func_110577_a(new ResourceLocation("customnpcs:textures/gui/misc.png"));
        GL11.glPushMatrix();
        float red = (float)(this.color >> 16 & 0xFF) / 255.0f;
        float green = (float)(this.color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(this.color & 0xFF) / 255.0f;
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)this.alpha);
        GL11.glTranslatef((float)(GuiCustom.guiLeft + this.x1), (float)(GuiCustom.guiTop + this.y1), (float)this.id);
        GL11.glRotated((double)(-Math.toDegrees(Math.atan2(this.x2 - this.x1, this.y2 - this.y1))), (double)0.0, (double)0.0, (double)1.0);
        GL11.glRotated((double)this.rotation, (double)0.0, (double)0.0, (double)1.0);
        GL11.glScaled((double)this.thickness, (double)distance, (double)0.0);
        this.func_73729_b(0, 0, 0, 0, 1, 1);
        GL11.glPopMatrix();
    }

    @Override
    public ICustomGuiComponent toComponent() {
        ScriptGuiLine line = new ScriptGuiLine(this.id, this.x1, this.y1, this.x2, this.y2, this.color, this.thickness);
        line.setColor(this.color);
        line.setAlpha(this.alpha);
        line.setRotation(this.rotation);
        return line;
    }

    public static CustomGuiLine fromComponent(ScriptGuiLine component) {
        CustomGuiLine line = new CustomGuiLine(component.getID(), component.getX1(), component.getY1(), component.getX2(), component.getY2(), component.getColor(), component.getThickness());
        line.color = component.getColor();
        line.alpha = component.getAlpha();
        line.rotation = component.getRotation();
        return line;
    }
}

