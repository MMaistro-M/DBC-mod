/*
 * Decompiled with CFR 0.152.
 */
package com.eliotlash.mclib.utils.resources;

import com.eliotlash.mclib.utils.Color;
import com.eliotlash.mclib.utils.resources.Pixels;

public enum PixelAccessor {
    BYTE{

        @Override
        public void get(Pixels pixels, int index, Color color) {
            int offset = 0;
            color.a = pixels.hasAlpha() ? (float)(pixels.pixelBytes[(index *= pixels.pixelLength) + offset++] & 0xFF) / 255.0f : 1.0f;
            color.b = (float)(pixels.pixelBytes[index + offset++] & 0xFF) / 255.0f;
            color.g = (float)(pixels.pixelBytes[index + offset++] & 0xFF) / 255.0f;
            color.r = (float)(pixels.pixelBytes[index + offset] & 0xFF) / 255.0f;
        }

        @Override
        public void set(Pixels pixels, int index, Color color) {
            index *= pixels.pixelLength;
            int offset = 0;
            if (pixels.hasAlpha()) {
                pixels.pixelBytes[index + offset++] = (byte)(color.a * 255.0f);
            }
            pixels.pixelBytes[index + offset++] = (byte)(color.b * 255.0f);
            pixels.pixelBytes[index + offset++] = (byte)(color.g * 255.0f);
            pixels.pixelBytes[index + offset] = (byte)(color.r * 255.0f);
        }
    }
    ,
    INT{

        @Override
        public void get(Pixels pixels, int index, Color color) {
            int c = pixels.pixelInts[index];
            int a = c >> 24 & 0xFF;
            int b = c >> 16 & 0xFF;
            int g = c >> 8 & 0xFF;
            int r = c & 0xFF;
            color.r = (float)r / 255.0f;
            color.g = (float)g / 255.0f;
            color.b = (float)b / 255.0f;
            color.a = (float)a / 255.0f;
        }

        @Override
        public void set(Pixels pixels, int index, Color color) {
            pixels.pixelInts[index] = ((int)(color.a * 255.0f) << 24) + ((int)(color.b * 255.0f) << 16) + ((int)(color.g * 255.0f) << 8) + (int)(color.r * 255.0f);
        }
    };


    public abstract void get(Pixels var1, int var2, Color var3);

    public abstract void set(Pixels var1, int var2, Color var3);
}

