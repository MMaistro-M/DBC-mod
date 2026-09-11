/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Tuple4f
 *  javax.vecmath.Vector4f
 *  net.minecraft.util.AxisAlignedBB
 */
package software.bernie.geckolib3.collision;

import javax.vecmath.Tuple4f;
import javax.vecmath.Vector4f;
import net.minecraft.util.AxisAlignedBB;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoVertex;
import software.bernie.geckolib3.util.MatrixStack;

public class RotatableBB
extends AxisAlignedBB {
    public RotatableBB(GeoCube cube, MatrixStack stack) {
        super(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        Vector4f vector4f;
        int i = 0;
        double[][] vertices = new double[8][];
        for (GeoVertex vertex : cube.quads[4].vertices) {
            vector4f = new Vector4f(vertex.position.x, vertex.position.y, vertex.position.z, 1.0f);
            stack.getModelMatrix().transform((Tuple4f)vector4f);
            vertices[i] = new double[]{vector4f.x, vector4f.y, vector4f.z};
            ++i;
        }
        for (GeoVertex vertex : cube.quads[5].vertices) {
            vector4f = new Vector4f(vertex.position.x, vertex.position.y, vertex.position.z, 1.0f);
            stack.getModelMatrix().transform((Tuple4f)vector4f);
            vertices[i] = new double[]{vector4f.x, vector4f.y, vector4f.z};
            ++i;
        }
        this.setupVertices(vertices);
    }

    public RotatableBB(double[][] vertices) {
        super(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        this.setupVertices(vertices);
    }

    public void setupVertices(double[][] vertices) {
        if (vertices.length != 8) {
            return;
        }
        this.field_72340_a = this.findMin(vertices, 0);
        this.field_72338_b = this.findMin(vertices, 1);
        this.field_72339_c = this.findMin(vertices, 2);
        this.field_72336_d = this.findMax(vertices, 0);
        this.field_72337_e = this.findMax(vertices, 1);
        this.field_72334_f = this.findMax(vertices, 2);
    }

    public double findMax(double[][] vertices, int secondIndex) {
        double max = vertices[0][secondIndex];
        for (int i = 0; i < vertices.length; ++i) {
            max = Math.max(max, vertices[i][secondIndex]);
        }
        return max;
    }

    public double findMin(double[][] vertices, int secondIndex) {
        double min = vertices[0][secondIndex];
        for (int i = 0; i < vertices.length; ++i) {
            min = Math.min(min, vertices[i][secondIndex]);
        }
        return min;
    }
}

