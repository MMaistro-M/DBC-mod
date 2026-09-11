/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Matrix4d
 *  javax.vecmath.Matrix4f
 *  javax.vecmath.Vector3d
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  net.minecraftforge.client.MinecraftForgeClient
 *  org.lwjgl.opengl.GL11
 */
package software.bernie.geckolib3.renderers.geo;

import com.eliotlash.mclib.utils.MatrixUtils;
import java.util.Collections;
import java.util.Objects;
import javax.vecmath.Matrix4d;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3d;
import net.geckominecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public abstract class GeoItemRenderer<T extends Item>
implements IGeoRenderer<T>,
IItemRenderer {
    protected AnimatedGeoModel<T> modelProvider;
    protected ItemStack currentItemStack;

    public GeoItemRenderer(AnimatedGeoModel<T> modelProvider) {
        this.modelProvider = modelProvider;
    }

    public void setModel(AnimatedGeoModel<T> model) {
        this.modelProvider = model;
    }

    @Override
    public AnimatedGeoModel<T> getGeoModelProvider() {
        return this.modelProvider;
    }

    public void renderItem(IItemRenderer.ItemRenderType var1, ItemStack itemStack, Object ... var3) {
        GL11.glPushMatrix();
        if (var1 == IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glTranslated((double)-1.0, (double)-1.0, (double)0.0);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        if (var1 != IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslated((double)0.0, (double)-0.5, (double)0.0);
        }
        if (var1 == IItemRenderer.ItemRenderType.ENTITY) {
            // empty if block
        }
        this.render(itemStack.func_77973_b(), itemStack);
        GL11.glPopMatrix();
    }

    public Vector3d getCurrentRenderPos() {
        EntityLivingBase camera = Minecraft.func_71410_x().field_71451_h;
        Matrix4f matrix4f = this.getCurrentMatrix();
        MatrixUtils.Transformation transformation = MatrixUtils.extractTransformations(null, matrix4f);
        double dl = matrix4f.m03;
        double du = matrix4f.m13;
        double dz = matrix4f.m23;
        Matrix4d rotMatrixX = new Matrix4d();
        rotMatrixX.rotX((double)(camera.field_70125_A / 360.0f) * Math.PI * 2.0);
        Matrix4d rotMatrixY = new Matrix4d();
        rotMatrixY.rotY((double)(-camera.field_70177_z / 360.0f) * Math.PI * 2.0);
        Vector3d vecZ = new Vector3d(0.0, 0.0, 1.0);
        rotMatrixX.transform(vecZ);
        rotMatrixY.transform(vecZ);
        Vector3d vecL = new Vector3d(1.0, 0.0, 0.0);
        rotMatrixX.transform(vecL);
        rotMatrixY.transform(vecL);
        vecZ.scale(-1.0);
        Vector3d vecU = new Vector3d(0.0, 1.0, 0.0);
        rotMatrixX.transform(vecU);
        rotMatrixY.transform(vecU);
        vecZ.scale(dz);
        vecU.scale(du);
        vecL.scale(-dl);
        Vector3d pos = new Vector3d(vecZ.x + vecU.x + vecL.x, vecZ.y + vecU.y + vecL.y, vecZ.z + vecU.z + vecL.z);
        Vector3d res = new Vector3d(pos.x + camera.field_70165_t, pos.y + camera.field_70163_u, pos.z + camera.field_70161_v);
        return res;
    }

    public Matrix4f getCurrentMatrix() {
        MatrixUtils.matrix = null;
        MatrixUtils.captureMatrix();
        return MatrixUtils.matrix;
    }

    public boolean handleRenderType(ItemStack is, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType var1, ItemStack var2, IItemRenderer.ItemRendererHelper var3) {
        return true;
    }

    public void render(T animatable, ItemStack itemStack) {
        this.currentItemStack = itemStack;
        GeoModel model = this.modelProvider.getModel(this.modelProvider.getModelLocation(animatable));
        AnimationEvent<IAnimatable> itemEvent = new AnimationEvent<IAnimatable>((IAnimatable)animatable, 0.0f, 0.0f, Minecraft.func_71410_x().field_71428_T.field_74281_c, false, Collections.singletonList(itemStack));
        this.modelProvider.setLivingAnimations(animatable, this.getUniqueID(animatable), (AnimationEvent)itemEvent);
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0f, 0.01f, 0.0f);
        GlStateManager.translate(0.5, 0.5, 0.5);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(this.getTextureLocation(animatable));
        Color renderColor = this.getRenderColor(animatable, 0.0f);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        this.render(model, animatable, 0.0f, (float)renderColor.getRed() / 255.0f, (float)renderColor.getGreen() / 255.0f, (float)renderColor.getBlue() / 255.0f, (float)renderColor.getAlpha() / 255.0f);
        GlStateManager.popMatrix();
    }

    @Override
    public ResourceLocation getTextureLocation(T instance) {
        return this.modelProvider.getTextureLocation(instance);
    }

    @Override
    public Integer getUniqueID(T animatable) {
        return Objects.hash(this.currentItemStack.func_77973_b(), this.currentItemStack.field_77994_a, this.currentItemStack.func_77942_o() ? this.currentItemStack.func_77978_p().toString() : Integer.valueOf(1));
    }

    static {
        AnimationController.addModelFetcher(object -> {
            Item item;
            IItemRenderer renderer;
            if (object instanceof Item && (renderer = MinecraftForgeClient.getItemRenderer((ItemStack)new ItemStack(item = (Item)object), (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.INVENTORY)) instanceof GeoItemRenderer) {
                return ((GeoItemRenderer)renderer).getGeoModelProvider();
            }
            return null;
        });
    }
}

