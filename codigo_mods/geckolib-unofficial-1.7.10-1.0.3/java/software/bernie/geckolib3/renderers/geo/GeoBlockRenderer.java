/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockDirectional
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.geckolib3.renderers.geo;

import net.geckominecraft.client.renderer.GlStateManager;
import net.minecraft.block.BlockDirectional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public abstract class GeoBlockRenderer<T extends TileEntity>
extends TileEntitySpecialRenderer
implements IGeoRenderer<T> {
    private final AnimatedGeoModel<T> modelProvider;

    public GeoBlockRenderer(AnimatedGeoModel<T> modelProvider) {
        this.modelProvider = modelProvider;
    }

    public void func_147500_a(TileEntity te, double x, double y, double z, float partialTicks) {
        this.render(te, x, y, z, partialTicks);
    }

    public void render(TileEntity tile, double x, double y, double z, float partialTicks) {
        GeoModel model = this.modelProvider.getModel(this.modelProvider.getModelLocation(tile));
        this.modelProvider.setLivingAnimations(tile, this.getUniqueID(tile));
        int light = 15;
        if (tile.func_145831_w() != null) {
            light = tile.func_145831_w().func_72802_i(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 0);
        }
        int lx = light % 65536;
        int ly = light / 65536;
        if (tile.field_145851_c != 0 && tile.field_145848_d != 0 && tile.field_145849_e != 0) {
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)lx, (float)ly);
        }
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.translate(0.0f, 0.01f, 0.0f);
        GlStateManager.translate(0.5, 0.0, 0.5);
        this.rotateBlock(this.getFacing(tile));
        Minecraft.func_71410_x().field_71446_o.func_110577_a(this.getTextureLocation((T)tile));
        Color renderColor = this.getRenderColor(tile, partialTicks);
        this.render(model, tile, partialTicks, (float)renderColor.getRed() / 255.0f, (float)renderColor.getGreen() / 255.0f, (float)renderColor.getBlue() / 255.0f, (float)renderColor.getAlpha() / 255.0f);
        GlStateManager.popMatrix();
    }

    @Override
    public AnimatedGeoModel<T> getGeoModelProvider() {
        return this.modelProvider;
    }

    protected void rotateBlock(EnumFacing facing) {
        switch (facing) {
            case SOUTH: {
                GlStateManager.rotate(180.0f, 0.0f, 1.0f, 0.0f);
                break;
            }
            case WEST: {
                GlStateManager.rotate(90.0f, 0.0f, 1.0f, 0.0f);
                break;
            }
            case NORTH: {
                break;
            }
            case EAST: {
                GlStateManager.rotate(270.0f, 0.0f, 1.0f, 0.0f);
                break;
            }
            case UP: {
                GlStateManager.rotate(90.0f, 1.0f, 0.0f, 0.0f);
                break;
            }
            case DOWN: {
                GlStateManager.rotate(90.0f, -1.0f, 0.0f, 0.0f);
            }
        }
    }

    private EnumFacing getFacing(TileEntity tile) {
        if (tile.field_145854_h instanceof BlockDirectional) {
            return EnumFacing.values()[2 + BlockDirectional.func_149895_l((int)tile.field_145847_g)];
        }
        return EnumFacing.NORTH;
    }

    @Override
    public ResourceLocation getTextureLocation(T instance) {
        return this.modelProvider.getTextureLocation(instance);
    }

    static {
        AnimationController.addModelFetcher(object -> {
            TileEntity tile;
            TileEntitySpecialRenderer renderer;
            if (object instanceof TileEntity && (renderer = TileEntityRendererDispatcher.field_147556_a.func_147547_b(tile = (TileEntity)object)) instanceof GeoBlockRenderer) {
                return ((GeoBlockRenderer)renderer).getGeoModelProvider();
            }
            return null;
        });
    }
}

