/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.nbt.NBTTagCompound
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.opengl.GL11;

public class Color {
    public int color;
    public float alpha;

    public Color(int color) {
        this.setColor(color, 1.0f);
    }

    public Color(int color, float alpha) {
        this.setColor(color, alpha);
    }

    public Color(int red, int green, int blue, float alpha) {
        this.setColor(red, green, blue, alpha);
    }

    public void setColor(int color, float alpha) {
        this.color = color;
        this.alpha = alpha;
    }

    public void setColor(int red, int green, int blue, float alpha) {
        this.color = (red << 16) + (green << 8) + blue;
        this.alpha = alpha;
    }

    public static Color lerpRGBA(Color color1, Color color2, float fraction) {
        fraction = Math.min(fraction, 1.0f);
        int red = (int)((float)color1.getRed() + (float)(color2.getRed() - color1.getRed()) * fraction);
        int green = (int)((float)color1.getGreen() + (float)(color2.getGreen() - color1.getGreen()) * fraction);
        int blue = (int)((float)color1.getBlue() + (float)(color2.getBlue() - color1.getBlue()) * fraction);
        float newAlpha = color1.alpha + (color2.alpha - color1.alpha) * fraction;
        int newColor = (red << 16) + (green << 8) + blue;
        return new Color(newColor, newAlpha);
    }

    public Color lerpRGBA(Color color2, float fraction) {
        return Color.lerpRGBA(this, color2, fraction);
    }

    public Color multiply(float multi) {
        if (multi == 1.0f) {
            return this.clone();
        }
        int r = (int)((float)this.getRed() * multi);
        int g = (int)((float)this.getGreen() * multi);
        int b = (int)((float)this.getBlue() * multi);
        float a = this.alpha * multi;
        return new Color((r << 16) + (g << 8) + b, a);
    }

    @SideOnly(value=Side.CLIENT)
    public void glColor() {
        GL11.glColor4f((float)this.getRedF(), (float)this.getGreenF(), (float)this.getBlueF(), (float)this.alpha);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound, String name) {
        compound.func_74768_a(name + "Color", this.color);
        compound.func_74776_a(name + "Alpha", this.alpha);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound, String name) {
        this.setColor(compound.func_74762_e(name + "Color"), compound.func_74760_g(name + "Alpha"));
    }

    public int getRed() {
        return this.color >> 16 & 0xFF;
    }

    public float getRedF() {
        return (float)(this.color >> 16 & 0xFF) / 255.0f;
    }

    public int getGreen() {
        return this.color >> 8 & 0xFF;
    }

    public float getGreenF() {
        return (float)(this.color >> 8 & 0xFF) / 255.0f;
    }

    public int getBlue() {
        return this.color & 0xFF;
    }

    public float getBlueF() {
        return (float)(this.color & 0xFF) / 255.0f;
    }

    public static String getColor(int color) {
        String str = Integer.toHexString(color);
        while (str.length() < 6) {
            str = "0" + str;
        }
        return str;
    }

    public static String getColor(int color, float alpha) {
        String str = Color.getColor(color);
        return str + " | " + (int)(alpha * 255.0f);
    }

    public Color clone() {
        return new Color(this.color, this.alpha);
    }
}

