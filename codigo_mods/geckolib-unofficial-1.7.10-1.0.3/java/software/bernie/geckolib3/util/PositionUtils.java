/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Matrix3f
 *  javax.vecmath.Matrix4d
 *  javax.vecmath.Matrix4f
 *  javax.vecmath.Vector3d
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.Vec3
 *  org.lwjgl.opengl.GL11
 */
package software.bernie.geckolib3.util;

import com.eliotlash.mclib.utils.MatrixUtils;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4d;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class PositionUtils {
    public static void setInitialWorldPos() {
        EntityLivingBase camera = Minecraft.func_71410_x().field_71451_h;
        GL11.glLoadIdentity();
        if (Minecraft.func_71410_x().field_71474_y.field_74320_O == 0) {
            GL11.glScaled((double)-1.0, (double)1.0, (double)-1.0);
        } else if (Minecraft.func_71410_x().field_71474_y.field_74320_O == 1) {
            GL11.glScaled((double)-1.0, (double)1.0, (double)-1.0);
        } else {
            GL11.glScaled((double)1.0, (double)1.0, (double)1.0);
        }
        GL11.glRotatef((float)(-camera.field_70125_A), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)camera.field_70177_z, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslated((double)(-RenderManager.field_78725_b), (double)(-RenderManager.field_78726_c), (double)(-RenderManager.field_78723_d));
        Vector3d additional = PositionUtils.getCameraShift();
        GL11.glTranslated((double)additional.x, (double)additional.y, (double)additional.z);
    }

    public static Vector3d getCameraShift() {
        Vector3d res = new Vector3d(0.0, 0.0, 0.0);
        if (Minecraft.func_71410_x().field_71474_y.field_74320_O == 0) {
            return res;
        }
        if (Minecraft.func_71410_x().field_71474_y.field_74320_O == 1) {
            Vec3 look = Minecraft.func_71410_x().field_71439_g.func_70040_Z();
            res = new Vector3d(look.field_72450_a, look.field_72448_b, look.field_72449_c);
            res.scale(4.0);
            return res;
        }
        Vec3 look = Minecraft.func_71410_x().field_71439_g.func_70040_Z();
        res = new Vector3d(look.field_72450_a, look.field_72448_b, look.field_72449_c);
        res.scale(-4.0);
        return res;
    }

    public static Vector3d getCurrentRenderPos() {
        EntityLivingBase camera = Minecraft.func_71410_x().field_71451_h;
        Matrix4f matrix4f = PositionUtils.getCurrentMatrix();
        MatrixUtils.Transformation transformation = MatrixUtils.extractTransformations(null, matrix4f);
        double dl = matrix4f.m03;
        double du = matrix4f.m13;
        double dz = matrix4f.m23;
        if (Minecraft.func_71410_x().field_71474_y.field_74320_O == 1) {
            dz += 4.0;
        }
        if (Minecraft.func_71410_x().field_71474_y.field_74320_O == 2) {
            dz *= -1.0;
            dl *= -1.0;
            dz -= 4.0;
        }
        Matrix4d rotMatrixX = new Matrix4d();
        rotMatrixX.rotX((double)(camera.field_70125_A / 360.0f) * Math.PI * 2.0);
        Matrix4d rotMatrixY = new Matrix4d();
        rotMatrixY.rotY((double)(-camera.field_70177_z / 360.0f) * Math.PI * 2.0);
        Vector3d vecZ = new Vector3d(0.0, 0.0, 1.0);
        rotMatrixX.transform(vecZ);
        rotMatrixY.transform(vecZ);
        vecZ.scale(-1.0);
        Vector3d vecL = new Vector3d(1.0, 0.0, 0.0);
        rotMatrixX.transform(vecL);
        rotMatrixY.transform(vecL);
        vecL.scale(-1.0);
        Vector3d vecU = new Vector3d(0.0, 1.0, 0.0);
        rotMatrixX.transform(vecU);
        rotMatrixY.transform(vecU);
        vecZ.scale(dz);
        vecU.scale(du);
        vecL.scale(dl);
        Vector3d pos = new Vector3d(vecZ.x + vecU.x + vecL.x, vecZ.y + vecU.y + vecL.y, vecZ.z + vecU.z + vecL.z);
        Vector3d res = new Vector3d(pos.x + RenderManager.field_78725_b, pos.y + RenderManager.field_78726_c, pos.z + RenderManager.field_78723_d);
        return res;
    }

    public static Matrix4f getCurrentMatrix() {
        MatrixUtils.matrix = null;
        MatrixUtils.captureMatrix();
        return MatrixUtils.matrix;
    }

    public static Matrix4f getBasicRotation() {
        EntityLivingBase camera = Minecraft.func_71410_x().field_71451_h;
        Matrix4f basicRot = new Matrix4f();
        basicRot.rotX((float)((double)(camera.field_70125_A / 360.0f) * Math.PI / 2.0));
        Matrix4f yRot = new Matrix4f();
        yRot.rotY((float)((double)(camera.field_70177_z / 360.0f) * Math.PI / 2.0));
        basicRot.mul(yRot);
        return basicRot;
    }

    public static void changeToRot(Matrix4f current, Matrix4f rot) {
        current.m00 = rot.m00;
        current.m01 = rot.m01;
        current.m02 = rot.m02;
        current.m10 = rot.m10;
        current.m11 = rot.m11;
        current.m12 = rot.m12;
        current.m20 = rot.m20;
        current.m21 = rot.m21;
        current.m22 = rot.m22;
    }

    public static Matrix3f getRotBlock(Matrix4f rot) {
        return new Matrix3f(rot.m00, rot.m01, rot.m02, rot.m10, rot.m11, rot.m12, rot.m20, rot.m21, rot.m22);
    }

    public static Matrix4f getCurrentRotation(Matrix4f old, Matrix4f base) {
        base.invert();
        Matrix4f rot = new Matrix4f(old.m00, old.m01, old.m02, 0.0f, old.m10, old.m11, old.m12, 0.0f, old.m20, old.m21, old.m22, 0.0f, 0.0f, 0.0f, 0.0f, old.m33);
        base.mul(rot);
        return base;
    }
}

