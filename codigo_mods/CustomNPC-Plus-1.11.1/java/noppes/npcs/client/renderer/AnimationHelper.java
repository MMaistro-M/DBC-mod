/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package noppes.npcs.client.renderer;

import net.minecraft.client.Minecraft;

public class AnimationHelper {
    public static float getFrameVOffset(int totalHeight, int frameCount, int frametime) {
        if (frameCount <= 1 || totalHeight <= 0) {
            return 0.0f;
        }
        frametime = Math.max(1, frametime);
        long millis = Minecraft.func_71386_F();
        int tick = (int)(millis / 50L);
        int frameIdx = tick / frametime % frameCount;
        int frameHeight = totalHeight / frameCount;
        return (float)(frameIdx * frameHeight) / (float)totalHeight;
    }

    public static float getFrameVSize(int totalHeight, int frameCount) {
        if (frameCount <= 1 || totalHeight <= 0) {
            return 1.0f;
        }
        int frameHeight = totalHeight / frameCount;
        return (float)frameHeight / (float)totalHeight;
    }
}

