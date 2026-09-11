/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.particles;

import net.geckominecraft.client.renderer.GlStateManager;

public enum BedrockMaterial {
    OPAQUE("particles_opaque"),
    ALPHA("particles_alpha"),
    BLEND("particles_blend"),
    ADDITIVE("particles_add");

    public final String id;

    public static BedrockMaterial fromString(String material) {
        for (BedrockMaterial mat : BedrockMaterial.values()) {
            if (!mat.id.equals(material)) continue;
            return mat;
        }
        return OPAQUE;
    }

    private BedrockMaterial(String id) {
        this.id = id;
    }

    public void beginGL() {
        switch (this) {
            case OPAQUE: {
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                GlStateManager.alphaFunc(516, 0.0f);
                GlStateManager.disableBlend();
                GlStateManager.enableAlpha();
                break;
            }
            case ALPHA: {
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                GlStateManager.alphaFunc(516, 0.1f);
                GlStateManager.disableBlend();
                GlStateManager.enableAlpha();
                break;
            }
            case BLEND: {
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                GlStateManager.alphaFunc(516, 0.0f);
                GlStateManager.enableBlend();
                GlStateManager.enableAlpha();
                break;
            }
            case ADDITIVE: {
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
                GlStateManager.alphaFunc(516, 0.0f);
                GlStateManager.enableBlend();
                GlStateManager.enableAlpha();
            }
        }
    }

    public void endGL() {
        switch (this) {
            case OPAQUE: 
            case ALPHA: 
            case BLEND: 
            case ADDITIVE: {
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                GlStateManager.disableBlend();
                GlStateManager.enableAlpha();
                GlStateManager.alphaFunc(516, 0.1f);
            }
        }
    }
}

