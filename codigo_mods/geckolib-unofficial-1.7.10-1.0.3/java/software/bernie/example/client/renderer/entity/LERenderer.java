/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.example.client.renderer.entity;

import software.bernie.example.client.model.entity.LEModel;
import software.bernie.example.client.renderer.entity.layer.GeoExampleLayer;
import software.bernie.example.entity.GeoExampleEntityLayer;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class LERenderer
extends GeoEntityRenderer<GeoExampleEntityLayer> {
    public LERenderer() {
        super(new LEModel());
        this.addLayer(new GeoExampleLayer((IGeoRenderer)this));
        this.field_76989_e = 0.2f;
    }
}

