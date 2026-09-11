/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.client.model.bake;

import riskyken.armourersWorkshop.client.render.IRenderBuffer;
import riskyken.armourersWorkshop.client.render.RenderBridge;

public class FaceRenderer {
    private static final IRenderBuffer BUF = RenderBridge.INSTANCE;
    private static final float SCALE = 0.0625f;
    private static final float NORMAL = 0.75f;

    public static void renderFace(double x, double y, double z, byte r, byte g, byte b, byte a, byte face, boolean textured, byte lodLevel) {
        if (face == 0) {
            FaceRenderer.renderNegYFace(x, y, z, r, g, b, a, textured, 0.0625f * (float)lodLevel);
        }
        if (face == 1) {
            FaceRenderer.renderPosYFace(x, y, z, r, g, b, a, textured, 0.0625f * (float)lodLevel);
        }
        if (face == 2) {
            FaceRenderer.renderNegZFace(x, y, z, r, g, b, a, textured, 0.0625f * (float)lodLevel);
        }
        if (face == 3) {
            FaceRenderer.renderPosZFace(x, y, z, r, g, b, a, textured, 0.0625f * (float)lodLevel);
        }
        if (face == 4) {
            FaceRenderer.renderNegXFace(x, y, z, r, g, b, a, textured, 0.0625f * (float)lodLevel);
        }
        if (face == 5) {
            FaceRenderer.renderPosXFace(x, y, z, r, g, b, a, textured, 0.0625f * (float)lodLevel);
        }
    }

    public static void renderPosXFace(double x, double y, double z, byte r, byte g, byte b, byte a, boolean textured, float scale) {
        BUF.setNormal(-0.75f, 0.0f, 0.0f);
        BUF.setColourRGBA_B(r, g, b, a);
        FaceRenderer.addVertex(x * 0.0625, y * 0.0625, z * 0.0625 + (double)scale, 0.0, 0.0, textured);
        FaceRenderer.addVertex(x * 0.0625, y * 0.0625 + (double)scale, z * 0.0625 + (double)scale, 0.0, 1.0, textured);
        FaceRenderer.addVertex(x * 0.0625, y * 0.0625 + (double)scale, z * 0.0625, 1.0, 1.0, textured);
        FaceRenderer.addVertex(x * 0.0625, y * 0.0625, z * 0.0625, 1.0, 0.0, textured);
    }

    public static void renderNegXFace(double x, double y, double z, byte r, byte g, byte b, byte a, boolean textured, float scale) {
        BUF.setNormal(0.75f, 0.0f, 0.0f);
        BUF.setColourRGBA_B(r, g, b, a);
        FaceRenderer.addVertex(x * 0.0625 + (double)scale, y * 0.0625, z * 0.0625, 0.0, 0.0, textured);
        FaceRenderer.addVertex(x * 0.0625 + (double)scale, y * 0.0625 + (double)scale, z * 0.0625, 0.0, 1.0, textured);
        FaceRenderer.addVertex(x * 0.0625 + (double)scale, y * 0.0625 + (double)scale, z * 0.0625 + (double)scale, 1.0, 1.0, textured);
        FaceRenderer.addVertex(x * 0.0625 + (double)scale, y * 0.0625, z * 0.0625 + (double)scale, 1.0, 0.0, textured);
    }

    public static void renderPosYFace(double x, double y, double z, byte r, byte g, byte b, byte a, boolean textured, float scale) {
        BUF.setNormal(0.0f, -0.75f, 0.0f);
        BUF.setColourRGBA_B(r, g, b, a);
        FaceRenderer.addVertex(x * 0.0625, y * 0.0625, z * 0.0625 + (double)scale, 0.0, 0.0, textured);
        FaceRenderer.addVertex(x * 0.0625, y * 0.0625, z * 0.0625, 0.0, 1.0, textured);
        FaceRenderer.addVertex(x * 0.0625 + (double)scale, y * 0.0625, z * 0.0625, 1.0, 1.0, textured);
        FaceRenderer.addVertex(x * 0.0625 + (double)scale, y * 0.0625, z * 0.0625 + (double)scale, 1.0, 0.0, textured);
    }

    public static void renderNegYFace(double x, double y, double z, byte r, byte g, byte b, byte a, boolean textured, float scale) {
        BUF.setNormal(0.0f, 0.75f, 0.0f);
        BUF.setColourRGBA_B(r, g, b, a);
        FaceRenderer.addVertex(x * 0.0625 + (double)scale, y * 0.0625 + (double)scale, z * 0.0625 + (double)scale, 1.0, 1.0, textured);
        FaceRenderer.addVertex(x * 0.0625 + (double)scale, y * 0.0625 + (double)scale, z * 0.0625, 1.0, 0.0, textured);
        FaceRenderer.addVertex(x * 0.0625, y * 0.0625 + (double)scale, z * 0.0625, 0.0, 0.0, textured);
        FaceRenderer.addVertex(x * 0.0625, y * 0.0625 + (double)scale, z * 0.0625 + (double)scale, 0.0, 1.0, textured);
    }

    public static void renderPosZFace(double x, double y, double z, byte r, byte g, byte b, byte a, boolean textured, float scale) {
        BUF.setNormal(0.0f, 0.0f, 0.75f);
        BUF.setColourRGBA_B(r, g, b, a);
        FaceRenderer.addVertex(x * 0.0625 + (double)scale, y * 0.0625, z * 0.0625 + (double)scale, 0.0, 0.0, textured);
        FaceRenderer.addVertex(x * 0.0625 + (double)scale, y * 0.0625 + (double)scale, z * 0.0625 + (double)scale, 0.0, 1.0, textured);
        FaceRenderer.addVertex(x * 0.0625, y * 0.0625 + (double)scale, z * 0.0625 + (double)scale, 1.0, 1.0, textured);
        FaceRenderer.addVertex(x * 0.0625, y * 0.0625, z * 0.0625 + (double)scale, 1.0, 0.0, textured);
    }

    public static void renderNegZFace(double x, double y, double z, byte r, byte g, byte b, byte a, boolean textured, float scale) {
        BUF.setNormal(0.0f, 0.0f, -0.75f);
        BUF.setColourRGBA_B(r, g, b, a);
        FaceRenderer.addVertex(x * 0.0625, y * 0.0625, z * 0.0625, 0.0, 0.0, textured);
        FaceRenderer.addVertex(x * 0.0625, y * 0.0625 + (double)scale, z * 0.0625, 0.0, 1.0, textured);
        FaceRenderer.addVertex(x * 0.0625 + (double)scale, y * 0.0625 + (double)scale, z * 0.0625, 1.0, 1.0, textured);
        FaceRenderer.addVertex(x * 0.0625 + (double)scale, y * 0.0625, z * 0.0625, 1.0, 0.0, textured);
    }

    private static void addVertex(double x, double y, double z, double u, double v, boolean textured) {
        if (textured) {
            BUF.addVertexWithUV(x, y, z, u, v);
        } else {
            BUF.addVertex(x, y, z);
        }
    }
}

