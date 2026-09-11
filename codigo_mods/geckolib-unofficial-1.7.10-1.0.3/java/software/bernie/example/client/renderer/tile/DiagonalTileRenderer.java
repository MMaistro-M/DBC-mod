/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.example.client.renderer.tile;

import software.bernie.example.block.tile.DiagonalTileEntity;
import software.bernie.example.client.model.tile.DiagonalModel;
import software.bernie.geckolib3.collision.ComplexBB;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class DiagonalTileRenderer
extends GeoBlockRenderer<DiagonalTileEntity> {
    public DiagonalTileRenderer() {
        super(new DiagonalModel());
    }

    @Override
    public void renderEarly(GeoModel model, DiagonalTileEntity animatable, float ticks, float red, float green, float blue, float alpha) {
        super.renderEarly(model, (Object)animatable, ticks, red, green, blue, alpha);
        animatable.boundingBox = new ComplexBB(model, (double)animatable.field_145851_c + 0.5, animatable.field_145848_d, (double)animatable.field_145849_e + 0.5);
    }
}

