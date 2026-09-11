/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.example.client.renderer.entity;

import software.bernie.example.client.model.entity.ExampleEntityModel;
import software.bernie.example.entity.GeoExampleEntity;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ExampleGeoRenderer
extends GeoEntityRenderer<GeoExampleEntity> {
    public ExampleGeoRenderer() {
        super(new ExampleEntityModel());
    }

    @Override
    public Color getRenderColor(GeoExampleEntity animatable, float partialTicks) {
        if (animatable.field_70737_aN > 0 || animatable.field_70725_aQ > 0) {
            return Color.ofRGBA(255, 153, 153, 255);
        }
        return Color.ofRGBA(255, 255, 255, 255);
    }
}

