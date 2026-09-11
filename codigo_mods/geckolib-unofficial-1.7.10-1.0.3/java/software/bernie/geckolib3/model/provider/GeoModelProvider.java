/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.geckolib3.model.provider;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.resource.GeckoLibCache;

public abstract class GeoModelProvider<T> {
    public double seekTime;
    public double lastGameTickTime;
    public boolean shouldCrashOnMissing = false;

    public GeoModel getModel(ResourceLocation location) {
        if (GeckoLibCache.getInstance().getGeoModels().containsKey(location)) {
            return GeckoLibCache.getInstance().getGeoModels().get(location);
        }
        return GeckoLibCache.getInstance().getGeoModels().get(new ResourceLocation("geckolib3", "geo/testdiagonal2.geo.json"));
    }

    public abstract ResourceLocation getModelLocation(T var1);

    public abstract ResourceLocation getTextureLocation(T var1);
}

