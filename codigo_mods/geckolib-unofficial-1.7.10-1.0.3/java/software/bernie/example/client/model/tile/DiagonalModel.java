/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.example.client.model.tile;

import net.minecraft.util.ResourceLocation;
import software.bernie.example.block.tile.DiagonalTileEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DiagonalModel
extends AnimatedGeoModel<DiagonalTileEntity> {
    @Override
    public ResourceLocation getAnimationFileLocation(DiagonalTileEntity entity) {
        return new ResourceLocation("geckolib3", "animations/bat.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(DiagonalTileEntity animatable) {
        return new ResourceLocation("geckolib3", "geo/testdiagonal.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DiagonalTileEntity entity) {
        return new ResourceLocation("geckolib3", "textures/block/testdiagonal.png");
    }
}

