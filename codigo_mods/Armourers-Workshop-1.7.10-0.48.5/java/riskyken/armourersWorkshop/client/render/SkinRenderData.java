/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 */
package riskyken.armourersWorkshop.client.render;

import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;
import riskyken.armourersWorkshop.common.skin.data.SkinDye;

public class SkinRenderData {
    public static final SkinDye BLANK_DYE = new SkinDye();
    private final float scale;
    private final ISkinDye skinDye;
    private final byte[] extraColours;
    private final int lod;
    private final boolean doLodLoading;
    private final boolean showSkinPaint;
    private final boolean itemRender;
    private final ResourceLocation entityTexture;

    public SkinRenderData(float scale, ISkinDye skinDye, byte[] extraColours, double distance, boolean doLodLoading, boolean showSkinPaint, boolean itemRender, ResourceLocation entityTexture) {
        this(scale, skinDye, extraColours, SkinRenderData.getLOD(distance), doLodLoading, showSkinPaint, itemRender, entityTexture);
    }

    public SkinRenderData(float scale, ISkinDye skinDye, byte[] extraColours, int lod, boolean doLodLoading, boolean showSkinPaint, boolean itemRender, ResourceLocation entityTexture) {
        this.scale = scale;
        this.skinDye = skinDye == null ? BLANK_DYE : skinDye;
        this.extraColours = (byte[])(extraColours == null ? null : extraColours);
        this.lod = lod;
        this.doLodLoading = doLodLoading;
        this.showSkinPaint = showSkinPaint;
        this.itemRender = itemRender;
        this.entityTexture = entityTexture;
    }

    public float getScale() {
        return this.scale;
    }

    public ISkinDye getSkinDye() {
        return this.skinDye;
    }

    public byte[] getExtraColours() {
        return this.extraColours;
    }

    public int getLod() {
        return this.lod;
    }

    public boolean isDoLodLoading() {
        return this.doLodLoading;
    }

    public boolean isShowSkinPaint() {
        return this.showSkinPaint;
    }

    public boolean isItemRender() {
        return this.itemRender;
    }

    public ResourceLocation getEntityTexture() {
        return this.entityTexture;
    }

    protected static int getLOD(double distance) {
        int lod = MathHelper.func_76128_c((double)(distance / ConfigHandlerClient.lodDistance));
        return MathHelper.func_76125_a((int)lod, (int)0, (int)ConfigHandlerClient.maxLodLevels);
    }
}

