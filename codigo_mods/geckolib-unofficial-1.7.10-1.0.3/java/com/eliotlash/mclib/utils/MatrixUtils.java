/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  javax.annotation.Nullable
 *  javax.vecmath.Matrix3f
 *  javax.vecmath.Matrix4d
 *  javax.vecmath.Matrix4f
 *  javax.vecmath.Quat4d
 *  javax.vecmath.SingularMatrixException
 *  javax.vecmath.Tuple3f
 *  javax.vecmath.Vector3d
 *  javax.vecmath.Vector3f
 *  javax.vecmath.Vector4d
 *  javax.vecmath.Vector4f
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 */
package com.eliotlash.mclib.utils;

import com.eliotlash.mclib.utils.Interpolations;
import com.eliotlash.mclib.utils.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import javax.annotation.Nullable;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4d;
import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4d;
import javax.vecmath.SingularMatrixException;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4d;
import javax.vecmath.Vector4f;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class MatrixUtils {
    public static final FloatBuffer buffer = BufferUtils.createFloatBuffer((int)16);
    public static final float[] floats = new float[16];
    public static Matrix4f matrix;
    private static final DoubleBuffer doubleBuffer;
    private static final double[] doubles;
    private static final Matrix4d camera;

    public static Matrix4d getCameraMatrix() {
        return new Matrix4d(camera);
    }

    public static Matrix4f readModelView(Matrix4f matrix4f) {
        buffer.clear();
        GL11.glGetFloat((int)2982, (FloatBuffer)buffer);
        buffer.get(floats);
        matrix4f.set(floats);
        matrix4f.transpose();
        return matrix4f;
    }

    private static void readCamera() {
        doubleBuffer.clear();
        GL11.glGetDouble((int)2982, (DoubleBuffer)doubleBuffer);
        doubleBuffer.get(doubles);
        camera.set(doubles);
        camera.transpose();
    }

    public static Matrix4f readModelView() {
        return MatrixUtils.readModelView(new Matrix4f());
    }

    public static Matrix4d readModelViewDouble() {
        doubleBuffer.clear();
        GL11.glGetDouble((int)2982, (DoubleBuffer)doubleBuffer);
        doubleBuffer.get(doubles);
        Matrix4d matrix4d = new Matrix4d();
        matrix4d.set(doubles);
        matrix4d.transpose();
        return matrix4d;
    }

    public static void loadModelView(Matrix4f matrix4f) {
        MatrixUtils.matrixToFloat(floats, matrix4f);
        buffer.clear();
        buffer.put(floats);
        buffer.rewind();
        GL11.glLoadMatrix((FloatBuffer)buffer);
    }

    public static void matrixToFloat(float[] floats, Matrix4f matrix4f) {
        floats[0] = matrix4f.m00;
        floats[1] = matrix4f.m01;
        floats[2] = matrix4f.m02;
        floats[3] = matrix4f.m03;
        floats[4] = matrix4f.m10;
        floats[5] = matrix4f.m11;
        floats[6] = matrix4f.m12;
        floats[7] = matrix4f.m13;
        floats[8] = matrix4f.m20;
        floats[9] = matrix4f.m21;
        floats[10] = matrix4f.m22;
        floats[11] = matrix4f.m23;
        floats[12] = matrix4f.m30;
        floats[13] = matrix4f.m31;
        floats[14] = matrix4f.m32;
        floats[15] = matrix4f.m33;
    }

    public static void matrixToFloatBuffer(FloatBuffer floatBuffer, Matrix4f matrix) {
        floatBuffer.put(matrix.m00);
        floatBuffer.put(matrix.m01);
        floatBuffer.put(matrix.m02);
        floatBuffer.put(matrix.m03);
        floatBuffer.put(matrix.m10);
        floatBuffer.put(matrix.m11);
        floatBuffer.put(matrix.m12);
        floatBuffer.put(matrix.m13);
        floatBuffer.put(matrix.m20);
        floatBuffer.put(matrix.m21);
        floatBuffer.put(matrix.m22);
        floatBuffer.put(matrix.m23);
        floatBuffer.put(matrix.m30);
        floatBuffer.put(matrix.m31);
        floatBuffer.put(matrix.m32);
        floatBuffer.put(matrix.m33);
        floatBuffer.flip();
    }

    public static boolean captureMatrix() {
        if (matrix == null) {
            matrix = MatrixUtils.readModelView(new Matrix4f());
            return true;
        }
        return false;
    }

    public static void ASMAfterCamera() {
        MatrixUtils.readCamera();
    }

    public static void releaseMatrix() {
        matrix = null;
    }

    public static Quat4d matrixToQuaternion(Matrix3f matrix) {
        double tr = matrix.m00 + matrix.m11 + matrix.m22;
        double qw = 0.0;
        double qx = 0.0;
        double qy = 0.0;
        double qz = 0.0;
        if (tr > 0.0) {
            double S = Math.sqrt(tr + 1.0) * 2.0;
            qw = 0.25 * S;
            qx = (double)(matrix.m21 - matrix.m12) / S;
            qy = (double)(matrix.m02 - matrix.m20) / S;
            qz = (double)(matrix.m10 - matrix.m01) / S;
        } else if (matrix.m00 > matrix.m11 & matrix.m00 > matrix.m22) {
            double S = Math.sqrt(1.0 + (double)matrix.m00 - (double)matrix.m11 - (double)matrix.m22) * 2.0;
            qw = (double)(matrix.m21 - matrix.m12) / S;
            qx = 0.25 * S;
            qy = (double)(matrix.m01 + matrix.m10) / S;
            qz = (double)(matrix.m02 + matrix.m20) / S;
        } else if (matrix.m11 > matrix.m22) {
            double S = Math.sqrt(1.0 + (double)matrix.m11 - (double)matrix.m00 - (double)matrix.m22) * 2.0;
            qw = (double)(matrix.m02 - matrix.m20) / S;
            qx = (double)(matrix.m01 + matrix.m10) / S;
            qy = 0.25 * S;
            qz = (double)(matrix.m12 + matrix.m21) / S;
        } else {
            double S = Math.sqrt(1.0 + (double)matrix.m22 - (double)matrix.m00 - (double)matrix.m11) * 2.0;
            qw = (double)(matrix.m10 - matrix.m01) / S;
            qx = (double)(matrix.m02 + matrix.m20) / S;
            qy = (double)(matrix.m12 + matrix.m21) / S;
            qz = 0.25 * S;
        }
        return new Quat4d(qw, qx, qy, qz);
    }

    public static Vector3f getAngularVelocity(Matrix3f rotation) {
        Matrix3f step = new Matrix3f(rotation);
        Matrix3f angularVelocity = new Matrix3f();
        Matrix3f i = new Matrix3f();
        i.setIdentity();
        angularVelocity.setIdentity();
        angularVelocity.mul(2.0f);
        step.add(i);
        step.invert();
        step.mul(4.0f);
        angularVelocity.sub(step);
        Vector3f angularV = new Vector3f(angularVelocity.m21, -angularVelocity.m20, angularVelocity.m10);
        return angularV;
    }

    public static Matrix4f getRotationMatrix(float x, float y, float z, RotationOrder order) {
        Matrix4f mat = new Matrix4f();
        Matrix4f rot = new Matrix4f();
        mat.setIdentity();
        switch (order) {
            case XYZ: {
                rot.rotZ(z);
                mat.mul(rot);
                rot.rotY(y);
                mat.mul(rot);
                rot.rotX(x);
                mat.mul(rot);
                break;
            }
            case ZYX: {
                rot.rotX(x);
                mat.mul(rot);
                rot.rotY(y);
                mat.mul(rot);
                rot.rotZ(z);
                mat.mul(rot);
                break;
            }
            case XZY: {
                rot.rotY(y);
                mat.mul(rot);
                rot.rotZ(z);
                mat.mul(rot);
                rot.rotX(x);
                mat.mul(rot);
                break;
            }
            case YZX: {
                rot.rotX(x);
                mat.mul(rot);
                rot.rotZ(z);
                mat.mul(rot);
                rot.rotY(y);
                mat.mul(rot);
                break;
            }
            case YXZ: {
                rot.rotZ(z);
                mat.mul(rot);
                rot.rotX(x);
                mat.mul(rot);
                rot.rotY(y);
                mat.mul(rot);
                break;
            }
            case ZXY: {
                rot.rotY(y);
                mat.mul(rot);
                rot.rotX(x);
                mat.mul(rot);
                rot.rotZ(z);
                mat.mul(rot);
            }
        }
        return mat;
    }

    public static Matrix4d[] getTransformation() {
        Matrix4d parent = new Matrix4d(camera);
        Matrix4d translation = new Matrix4d();
        Matrix4d rotation = new Matrix4d();
        Matrix4d scale = new Matrix4d();
        translation.setIdentity();
        rotation.setIdentity();
        scale.setIdentity();
        try {
            parent.invert();
        }
        catch (SingularMatrixException e) {
            return null;
        }
        parent.mul(parent, MatrixUtils.readModelViewDouble());
        EntityLivingBase renderViewEntity = Minecraft.func_71410_x().field_71451_h;
        Matrix4d cameraTrans = new Matrix4d();
        cameraTrans.setIdentity();
        cameraTrans.m03 = Interpolations.lerp(renderViewEntity.field_70142_S, renderViewEntity.field_70165_t, (double)Minecraft.func_71410_x().field_71428_T.field_74281_c);
        cameraTrans.m13 = Interpolations.lerp(renderViewEntity.field_70137_T, renderViewEntity.field_70163_u, (double)Minecraft.func_71410_x().field_71428_T.field_74281_c);
        cameraTrans.m23 = Interpolations.lerp(renderViewEntity.field_70136_U, renderViewEntity.field_70161_v, (double)Minecraft.func_71410_x().field_71428_T.field_74281_c);
        parent.mul(cameraTrans, parent);
        Vector4d rx = new Vector4d(parent.m00, parent.m10, parent.m20, 0.0);
        Vector4d ry = new Vector4d(parent.m01, parent.m11, parent.m21, 0.0);
        Vector4d rz = new Vector4d(parent.m02, parent.m12, parent.m22, 0.0);
        rx.normalize();
        ry.normalize();
        rz.normalize();
        rotation.setRow(0, rx);
        rotation.setRow(1, ry);
        rotation.setRow(2, rz);
        translation.setTranslation(new Vector3d(parent.m03, parent.m13, parent.m23));
        scale.m00 = Math.sqrt(parent.m00 * parent.m00 + parent.m10 * parent.m10 + parent.m20 * parent.m20);
        scale.m11 = Math.sqrt(parent.m01 * parent.m01 + parent.m11 * parent.m11 + parent.m21 * parent.m21);
        scale.m22 = Math.sqrt(parent.m02 * parent.m02 + parent.m12 * parent.m12 + parent.m22 * parent.m22);
        return new Matrix4d[]{translation, rotation, scale};
    }

    public static Transformation extractTransformations(@Nullable Matrix4f cameraMatrix, Matrix4f modelView) {
        return MatrixUtils.extractTransformations(cameraMatrix, modelView, MatrixMajor.ROW);
    }

    public static Transformation extractTransformations(@Nullable Matrix4f cameraMatrix, Matrix4f modelView, MatrixMajor major) {
        Matrix4f parent = new Matrix4f(modelView);
        if (cameraMatrix != null) {
            parent.set(cameraMatrix);
            try {
                parent.invert();
            }
            catch (SingularMatrixException e) {
                Transformation transformation = new Transformation();
                transformation.creationException = (Exception)((Object)e);
                return transformation;
            }
            parent.mul(modelView);
        }
        Matrix4f translation = new Matrix4f();
        Matrix4f scale = new Matrix4f();
        Matrix4f rotation = new Matrix4f();
        translation.setIdentity();
        rotation.setIdentity();
        scale.setIdentity();
        translation.m03 = parent.m03;
        translation.m13 = parent.m13;
        translation.m23 = parent.m23;
        Vector4f ax = new Vector4f(parent.m00, parent.m01, parent.m02, 0.0f);
        Vector4f ay = new Vector4f(parent.m10, parent.m11, parent.m12, 0.0f);
        Vector4f az = new Vector4f(parent.m20, parent.m21, parent.m22, 0.0f);
        if (major == MatrixMajor.COLUMN) {
            ax = new Vector4f(parent.m00, parent.m10, parent.m20, 0.0f);
            ay = new Vector4f(parent.m01, parent.m11, parent.m21, 0.0f);
            az = new Vector4f(parent.m02, parent.m12, parent.m22, 0.0f);
        }
        ax.normalize();
        ay.normalize();
        az.normalize();
        rotation.setRow(0, ax);
        rotation.setRow(1, ay);
        rotation.setRow(2, az);
        if (major == MatrixMajor.COLUMN) {
            rotation.transpose();
        }
        scale.m00 = (float)Math.sqrt(parent.m00 * parent.m00 + parent.m01 * parent.m01 + parent.m02 * parent.m02);
        scale.m11 = (float)Math.sqrt(parent.m10 * parent.m10 + parent.m11 * parent.m11 + parent.m12 * parent.m12);
        scale.m22 = (float)Math.sqrt(parent.m20 * parent.m20 + parent.m21 * parent.m21 + parent.m22 * parent.m22);
        if (major == MatrixMajor.COLUMN) {
            scale.m00 = (float)Math.sqrt(parent.m00 * parent.m00 + parent.m10 * parent.m10 + parent.m20 * parent.m20);
            scale.m11 = (float)Math.sqrt(parent.m01 * parent.m01 + parent.m11 * parent.m11 + parent.m21 * parent.m21);
            scale.m22 = (float)Math.sqrt(parent.m02 * parent.m02 + parent.m12 * parent.m12 + parent.m22 * parent.m22);
        }
        return new Transformation(translation, rotation, scale);
    }

    static {
        doubleBuffer = BufferUtils.createDoubleBuffer((int)16);
        doubles = new double[16];
        camera = new Matrix4d();
    }

    public static enum RotationOrder {
        XYZ,
        XZY,
        YXZ,
        YZX,
        ZXY,
        ZYX;

        public final int firstIndex;
        public final int secondIndex;
        public final int thirdIndex;

        private RotationOrder() {
            String order = this.name().toUpperCase();
            this.firstIndex = order.charAt(0) - 88;
            this.secondIndex = order.charAt(1) - 88;
            this.thirdIndex = order.charAt(2) - 88;
        }

        public Float doTest(int index, Matrix3f test) {
            float[] buffer = new float[3];
            buffer[index == this.firstIndex ? this.secondIndex : this.firstIndex] = 1.0f;
            Vector3f in = new Vector3f(buffer);
            Vector3f out = new Vector3f();
            test.transform((Tuple3f)in, (Tuple3f)out);
            out.get(buffer);
            buffer[index] = 0.0f;
            out.set(buffer);
            if ((double)out.length() < 1.0E-7) {
                return null;
            }
            out.normalize();
            float cos = in.dot(out);
            out.cross(in, out);
            out.get(buffer);
            float sin = out.length() * Math.signum(buffer[index]);
            return Float.valueOf((float)Math.toDegrees(Math.atan2(sin, cos)));
        }
    }

    public static enum MatrixMajor {
        ROW,
        COLUMN;

    }

    public static class Transformation {
        public Matrix4f translation = new Matrix4f();
        public Matrix4f rotation = new Matrix4f();
        public Matrix4f scale = new Matrix4f();
        private Exception creationException = null;

        public Transformation(Matrix4f translation, Matrix4f rotation, Matrix4f scale) {
            this.translation.set(translation);
            this.rotation.set(rotation);
            this.scale.set(scale);
        }

        public Transformation() {
            this.translation.setIdentity();
            this.rotation.setIdentity();
            this.scale.setIdentity();
        }

        public Matrix3f getScale3f() {
            Matrix3f scale3f = new Matrix3f();
            scale3f.setIdentity();
            scale3f.m00 = this.scale.m00;
            scale3f.m11 = this.scale.m11;
            scale3f.m22 = this.scale.m22;
            return scale3f;
        }

        public Vector3f getTranslation3f() {
            Vector3f translation3f = new Vector3f();
            translation3f.set(this.translation.m03, this.translation.m13, this.translation.m23);
            return translation3f;
        }

        public Matrix3f getRotation3f() {
            Matrix3f rotation3f = new Matrix3f();
            rotation3f.setIdentity();
            rotation3f.setRow(0, this.rotation.m00, this.rotation.m01, this.rotation.m02);
            rotation3f.setRow(1, this.rotation.m10, this.rotation.m11, this.rotation.m12);
            rotation3f.setRow(2, this.rotation.m20, this.rotation.m21, this.rotation.m22);
            return rotation3f;
        }

        public Exception getCreationException() {
            return this.creationException;
        }

        public Vector3f getRotation(RotationOrder order) {
            return this.getRotation(order, null);
        }

        public Vector3f getRotation(RotationOrder order, Vector3f ref) {
            return this.getRotation(order, ref, 0);
        }

        public Vector3f getRotation(RotationOrder order, int invAxis) {
            return this.getRotation(order, null, invAxis);
        }

        public Vector3f getRotation(RotationOrder order, Vector3f ref, int invAxis) {
            Float angle;
            Matrix3f mat = this.getRotation3f();
            float[] rotation = new float[3];
            float[] refFloats = null;
            if (ref != null) {
                refFloats = new float[3];
                ref.get(refFloats);
            }
            Vector3f x = new Vector3f(mat.m00, mat.m10, mat.m20);
            Vector3f y = new Vector3f(mat.m01, mat.m11, mat.m21);
            Vector3f z = new Vector3f(mat.m02, mat.m12, mat.m22);
            Vector3f crossY = new Vector3f();
            Vector3f originalY = new Vector3f();
            originalY.normalize(y);
            crossY.cross(z, x);
            crossY.normalize();
            if (crossY.dot(originalY) < 0.0f) {
                mat.mul(Transformation.getInvertAxisMatrix(invAxis));
            }
            if ((angle = order.doTest(order.thirdIndex, mat)) != null) {
                if (refFloats != null) {
                    angle = Float.valueOf(refFloats[order.thirdIndex] + MathHelper.wrapDegrees(2.0f * (angle.floatValue() - refFloats[order.thirdIndex])) / 2.0f);
                }
                rotation[order.thirdIndex] = angle.floatValue();
                mat.mul(Transformation.getRotationMatrix(order.thirdIndex, -angle.floatValue()), mat);
            } else if (refFloats != null) {
                angle = Float.valueOf(refFloats[order.thirdIndex]);
                rotation[order.thirdIndex] = angle.floatValue();
                mat.mul(Transformation.getRotationMatrix(order.thirdIndex, -angle.floatValue()), mat);
            }
            angle = order.doTest(order.secondIndex, mat);
            if (angle == null) {
                return null;
            }
            if (refFloats != null) {
                angle = Float.valueOf(refFloats[order.secondIndex] + MathHelper.wrapDegrees(angle.floatValue() - refFloats[order.secondIndex]));
            }
            rotation[order.secondIndex] = angle.floatValue();
            mat.mul(Transformation.getRotationMatrix(order.secondIndex, -angle.floatValue()), mat);
            angle = order.doTest(order.firstIndex, mat);
            if (angle == null) {
                return null;
            }
            if (refFloats != null) {
                angle = Float.valueOf(refFloats[order.firstIndex] + MathHelper.wrapDegrees(angle.floatValue() - refFloats[order.firstIndex]));
            }
            rotation[order.firstIndex] = angle.floatValue();
            return new Vector3f(rotation);
        }

        public Vector3f getScale() {
            return this.getScale(0);
        }

        public Vector3f getScale(int invAxis) {
            Vector3f scale = new Vector3f(this.scale.m00, this.scale.m11, this.scale.m22);
            Vector3f x = new Vector3f(this.rotation.m00, this.rotation.m10, this.rotation.m20);
            Vector3f y = new Vector3f(this.rotation.m01, this.rotation.m11, this.rotation.m21);
            Vector3f z = new Vector3f(this.rotation.m02, this.rotation.m12, this.rotation.m22);
            Vector3f crossY = new Vector3f();
            Vector3f originalY = new Vector3f();
            originalY.normalize(y);
            crossY.cross(z, x);
            crossY.normalize();
            if (crossY.dot(originalY) < 0.0f) {
                Transformation.getInvertAxisMatrix(invAxis).transform((Tuple3f)scale);
            }
            return scale;
        }

        public static Matrix3f getRotationMatrix(int axis, double degrees) {
            Matrix3f mat = new Matrix3f();
            switch (axis) {
                case 0: {
                    mat.rotX((float)Math.toRadians(degrees));
                    break;
                }
                case 1: {
                    mat.rotY((float)Math.toRadians(degrees));
                    break;
                }
                case 2: {
                    mat.rotZ((float)Math.toRadians(degrees));
                }
            }
            return mat;
        }

        public static Matrix3f getInvertAxisMatrix(int axis) {
            Matrix3f mat = new Matrix3f();
            mat.setIdentity();
            switch (axis) {
                case 0: {
                    mat.m00 = -1.0f;
                    break;
                }
                case 1: {
                    mat.m11 = -1.0f;
                    break;
                }
                case 2: {
                    mat.m22 = -1.0f;
                }
            }
            return mat;
        }
    }
}

