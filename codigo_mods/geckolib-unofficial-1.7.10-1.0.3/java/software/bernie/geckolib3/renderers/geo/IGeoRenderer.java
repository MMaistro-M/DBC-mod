/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Matrix3f
 *  javax.vecmath.Matrix4f
 *  javax.vecmath.Tuple3f
 *  javax.vecmath.Tuple4f
 *  javax.vecmath.Vector3d
 *  javax.vecmath.Vector3f
 *  javax.vecmath.Vector4f
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 */
package software.bernie.geckolib3.renderers.geo;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Tuple4f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import net.geckominecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.geo.render.built.GeoQuad;
import software.bernie.geckolib3.geo.render.built.GeoVertex;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.particles.emitter.BedrockEmitter;
import software.bernie.geckolib3.util.MatrixStack;
import software.bernie.geckolib3.util.PositionUtils;

public interface IGeoRenderer<T> {
    public static final MatrixStack MATRIX_STACK = new MatrixStack();

    default public void render(GeoModel model, T animatable, float partialTicks, float red, float green, float blue, float alpha) {
        GlStateManager.disableCull();
        GlStateManager.enableRescaleNormal();
        GL11.glBlendFunc((int)770, (int)771);
        GlStateManager.enableBlend();
        GL11.glEnable((int)3553);
        this.renderEarly(model, animatable, partialTicks, red, green, blue, alpha);
        this.renderLate(model, animatable, partialTicks, red, green, blue, alpha);
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78371_b(7);
        for (GeoBone group : model.topLevelBones) {
            this.renderRecursively(tess, animatable, group, red, green, blue, alpha);
        }
        Tessellator.field_78398_a.func_78381_a();
        this.renderAfter(model, animatable, partialTicks, red, green, blue, alpha);
        GlStateManager.disableBlend();
        GlStateManager.enableCull();
    }

    default public boolean isBoneRenderOverriden(T animatable, GeoBone bone) {
        return false;
    }

    default public void drawOverridenBone(T animatable, GeoBone bone) {
    }

    default public void renderRecursively(Tessellator builder, T animatable, GeoBone bone, float red, float green, float blue, float alpha) {
        MATRIX_STACK.push();
        MATRIX_STACK.translate(bone);
        MATRIX_STACK.moveToPivot(bone);
        MATRIX_STACK.rotate(bone);
        MATRIX_STACK.scale(bone);
        MATRIX_STACK.moveBackFromPivot(bone);
        if (this.isBoneRenderOverriden(animatable, bone)) {
            this.drawOverridenBone(animatable, bone);
            MATRIX_STACK.pop();
            return;
        }
        if (!bone.isHidden()) {
            for (GeoCube cube : bone.childCubes) {
                MATRIX_STACK.push();
                GlStateManager.pushMatrix();
                this.renderCube(builder, cube, red, green, blue, alpha);
                GlStateManager.popMatrix();
                MATRIX_STACK.pop();
            }
        }
        if (!bone.childBonesAreHiddenToo()) {
            for (GeoBone childBone : bone.childBones) {
                this.renderRecursively(builder, animatable, childBone, red, green, blue, alpha);
            }
        }
        MATRIX_STACK.pop();
    }

    default public void renderCube(Tessellator builder, GeoCube cube, float red, float green, float blue, float alpha) {
        MATRIX_STACK.moveToPivot(cube);
        MATRIX_STACK.rotate(cube);
        MATRIX_STACK.moveBackFromPivot(cube);
        for (GeoQuad quad : cube.quads) {
            Vector3f normal = new Vector3f((float)quad.normal.getX(), (float)quad.normal.getY(), (float)quad.normal.getZ());
            MATRIX_STACK.getNormalMatrix().transform((Tuple3f)normal);
            if ((cube.size.y == 0.0f || cube.size.z == 0.0f) && normal.x < 0.0f) {
                normal.x *= -1.0f;
            }
            if ((cube.size.x == 0.0f || cube.size.z == 0.0f) && normal.y < 0.0f) {
                normal.y *= -1.0f;
            }
            if ((cube.size.x == 0.0f || cube.size.y == 0.0f) && normal.z < 0.0f) {
                normal.z *= -1.0f;
            }
            for (GeoVertex vertex : quad.vertices) {
                Vector4f vector4f = new Vector4f(vertex.position.x, vertex.position.y, vertex.position.z, 1.0f);
                MATRIX_STACK.getModelMatrix().transform((Tuple4f)vector4f);
                builder.func_78369_a(red, green, blue, alpha);
                builder.func_78375_b(normal.x, normal.y, normal.z);
                builder.func_78374_a((double)vector4f.x, (double)vector4f.y, (double)vector4f.z, (double)vertex.textureU, (double)vertex.textureV);
            }
        }
    }

    public GeoModelProvider getGeoModelProvider();

    public ResourceLocation getTextureLocation(T var1);

    default public void renderEarly(GeoModel model, T animatable, float ticks, float red, float green, float blue, float alpha) {
    }

    default public void renderLate(GeoModel model, T animatable, float ticks, float red, float green, float blue, float alpha) {
    }

    default public void renderAfter(GeoModel model, T animatable, float ticks, float red, float green, float blue, float alpha) {
        this.drawParticles(model, animatable, ticks);
    }

    default public Color getRenderColor(T animatable, float partialTicks) {
        return Color.ofRGBA(255, 255, 255, 255);
    }

    default public Integer getUniqueID(T animatable) {
        return animatable.hashCode();
    }

    default public void drawParticles(GeoModel model, T animatableArg, float ticks) {
        if (!(animatableArg instanceof IAnimatable)) {
            return;
        }
        IAnimatable animatable = (IAnimatable)animatableArg;
        HashMap<String, AnimationController> controllerMap = animatable.getFactory().getOrCreateAnimationData(this.getUniqueID(animatableArg)).getAnimationControllers();
        for (AnimationController controller : controllerMap.values()) {
            for (int i = 0; i < controller.emitters.size(); ++i) {
                BedrockEmitter emitter = (BedrockEmitter)controller.emitters.get(i);
                String locator = ((BedrockEmitter)controller.emitters.get((int)i)).locator + "_locator";
                if (emitter.locator == null || !model.getBone(locator).isPresent()) continue;
                GeoBone bone = model.getBone(locator).get();
                this.renderParticle(emitter, bone, ticks);
            }
        }
    }

    default public void renderParticle(BedrockEmitter emitter, GeoBone locator, float ticks) {
        emitter.prevGlobal.x = emitter.lastGlobal.x;
        emitter.prevGlobal.y = emitter.lastGlobal.y;
        emitter.prevGlobal.z = emitter.lastGlobal.z;
        FloatBuffer buffer = BufferUtils.createFloatBuffer((int)16);
        Vector3d position = PositionUtils.getCurrentRenderPos();
        double posX = position.x;
        double posY = position.y;
        double posZ = position.z;
        emitter.lastGlobal.x = posX;
        emitter.lastGlobal.y = posY;
        emitter.lastGlobal.z = posZ;
        RenderHelper.func_74518_a();
        GL11.glPushMatrix();
        Matrix4f curRot = PositionUtils.getCurrentMatrix();
        PositionUtils.setInitialWorldPos();
        Matrix4f cur2 = PositionUtils.getCurrentRotation(curRot, PositionUtils.getCurrentMatrix());
        emitter.rotation.setIdentity();
        MATRIX_STACK.push();
        MATRIX_STACK.getModelMatrix().mul(new Matrix4f(cur2.m00, cur2.m01, cur2.m02, 0.0f, cur2.m10, cur2.m11, cur2.m12, 0.0f, cur2.m20, cur2.m21, cur2.m22, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f));
        GeoBone[] bonePath = this.getPathFromRoot(locator);
        for (int i = 0; i < bonePath.length; ++i) {
            GeoBone bone = bonePath[i];
            MATRIX_STACK.translate(bone);
            MATRIX_STACK.moveToPivot(bone);
            MATRIX_STACK.rotate(bone);
            MATRIX_STACK.scale(bone);
            MATRIX_STACK.moveBackFromPivot(bone);
        }
        MATRIX_STACK.moveToPivot(locator);
        Matrix4f full = MATRIX_STACK.getModelMatrix();
        emitter.rotation = new Matrix3f(full.m00, full.m01, full.m02, full.m10, full.m11, full.m12, full.m20, full.m21, full.m22);
        emitter.lastGlobal.x += (double)full.m03;
        emitter.lastGlobal.y += (double)full.m13;
        emitter.lastGlobal.z += (double)full.m23;
        MATRIX_STACK.pop();
        emitter.render(Minecraft.func_71410_x().field_71428_T.field_74281_c);
        RenderHelper.func_74519_b();
        GL11.glPopMatrix();
    }

    default public GeoBone[] getPathFromRoot(GeoBone bone) {
        ArrayList<GeoBone> bones = new ArrayList<GeoBone>();
        while (bone != null) {
            bones.add(0, bone);
            bone = bone.parent;
        }
        return bones.toArray(new GeoBone[0]);
    }
}

