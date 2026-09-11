/*
 * Decompiled with CFR 0.152.
 */
package com.eliotlash.mclib.utils;

import com.eliotlash.mclib.utils.Color;
import com.eliotlash.mclib.utils.Interpolations;
import net.geckominecraft.client.renderer.GlStateManager;

public class ColorUtils {
    public static final int HALF_BLACK = -2013265920;
    public static final Color COLOR = new Color();

    public static int multiplyColor(int color, float factor) {
        COLOR.set(color, true);
        ColorUtils.COLOR.r *= factor;
        ColorUtils.COLOR.g *= factor;
        ColorUtils.COLOR.b *= factor;
        return COLOR.getRGBAColor();
    }

    public static int setAlpha(int color, float alpha) {
        COLOR.set(color, true);
        ColorUtils.COLOR.a = alpha;
        return COLOR.getRGBAColor();
    }

    public static void interpolate(Color target, int a, int b, float x) {
        ColorUtils.interpolate(target, a, b, x, true);
    }

    public static void interpolate(Color target, int a, int b, float x, boolean alpha) {
        target.set(a, alpha);
        COLOR.set(b, alpha);
        target.r = Interpolations.lerp(target.r, ColorUtils.COLOR.r, x);
        target.g = Interpolations.lerp(target.g, ColorUtils.COLOR.g, x);
        target.b = Interpolations.lerp(target.b, ColorUtils.COLOR.b, x);
        if (alpha) {
            target.a = Interpolations.lerp(target.a, ColorUtils.COLOR.a, x);
        }
    }

    public static void bindColor(int color) {
        COLOR.set(color, true);
        GlStateManager.color(ColorUtils.COLOR.r, ColorUtils.COLOR.g, ColorUtils.COLOR.b, ColorUtils.COLOR.a);
    }

    public static int rgbaToInt(float r, float g, float b, float a) {
        COLOR.set(r, g, b, a);
        return COLOR.getRGBAColor();
    }

    public static int parseColor(String color) {
        return ColorUtils.parseColor(color, 0);
    }

    public static int parseColor(String color, int orDefault) {
        try {
            return ColorUtils.parseColorWithException(color);
        }
        catch (Exception exception) {
            return orDefault;
        }
    }

    public static int parseColorWithException(String color) throws Exception {
        if (color.startsWith("#")) {
            color = color.substring(1);
        }
        if (color.length() == 6 || color.length() == 8) {
            if (color.length() == 8) {
                String alpha = color.substring(0, 2);
                String rest = color.substring(2);
                int a = Integer.parseInt(alpha, 16) << 24;
                int rgb = Integer.parseInt(rest, 16);
                return a + rgb;
            }
            return Integer.parseInt(color, 16);
        }
        throw new Exception("Given color \"" + color + "\" can't be parsed!");
    }
}

