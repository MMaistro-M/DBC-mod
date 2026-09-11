/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.Vec3
 */
package software.bernie.geckolib3.collision;

import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import software.bernie.geckolib3.collision.RotatableBB;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.util.MatrixStack;

public class ComplexBB
extends AxisAlignedBB {
    public ArrayList<AxisAlignedBB> boundingBoxes = new ArrayList();
    public static MatrixStack MATRIX_STACK = new MatrixStack();

    public ComplexBB(AxisAlignedBB[] bbs) {
        super(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        this.boundingBoxes.addAll(Arrays.asList(bbs));
        this.setupSize();
    }

    public ComplexBB(GeoModel model, double xOff, double yOff, double zOff) {
        super(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        MATRIX_STACK.push();
        MATRIX_STACK.translate((float)xOff, (float)yOff, (float)zOff);
        for (GeoBone group : model.topLevelBones) {
            this.walkBonesRecursively(group, xOff, yOff, zOff);
        }
        MATRIX_STACK.pop();
        this.setupSize();
    }

    public void setupSize() {
        if (this.boundingBoxes.size() == 0) {
            return;
        }
        this.field_72340_a = this.boundingBoxes.get((int)0).field_72340_a;
        this.field_72338_b = this.boundingBoxes.get((int)0).field_72338_b;
        this.field_72339_c = this.boundingBoxes.get((int)0).field_72339_c;
        this.field_72336_d = this.boundingBoxes.get((int)0).field_72336_d;
        this.field_72337_e = this.boundingBoxes.get((int)0).field_72337_e;
        this.field_72334_f = this.boundingBoxes.get((int)0).field_72334_f;
        for (AxisAlignedBB bb : this.boundingBoxes) {
            this.field_72340_a = Math.min(this.field_72340_a, bb.field_72340_a);
            this.field_72338_b = Math.min(this.field_72338_b, bb.field_72338_b);
            this.field_72339_c = Math.min(this.field_72339_c, bb.field_72339_c);
            this.field_72336_d = Math.max(this.field_72336_d, bb.field_72336_d);
            this.field_72337_e = Math.max(this.field_72337_e, bb.field_72337_e);
            this.field_72334_f = Math.max(this.field_72334_f, bb.field_72334_f);
        }
    }

    public void walkBonesRecursively(GeoBone bone, double xOff, double yOff, double zOff) {
        MATRIX_STACK.push();
        MATRIX_STACK.translate(bone);
        MATRIX_STACK.moveToPivot(bone);
        MATRIX_STACK.rotate(bone);
        MATRIX_STACK.scale(bone);
        MATRIX_STACK.moveBackFromPivot(bone);
        if (!bone.isHidden()) {
            for (GeoCube cube : bone.childCubes) {
                MATRIX_STACK.push();
                MATRIX_STACK.moveToPivot(cube);
                MATRIX_STACK.rotate(cube);
                MATRIX_STACK.moveBackFromPivot(cube);
                this.boundingBoxes.add(new RotatableBB(cube, MATRIX_STACK));
                MATRIX_STACK.pop();
            }
        }
        if (!bone.childBonesAreHiddenToo()) {
            for (GeoBone childBone : bone.childBones) {
                this.walkBonesRecursively(childBone, xOff, yOff, zOff);
            }
        }
        MATRIX_STACK.pop();
    }

    public ComplexBB getOffsetBoundingBox(double p_72325_1_, double p_72325_3_, double p_72325_5_) {
        ComplexBB copy = this.copy();
        copy.offset(p_72325_1_, p_72325_3_, p_72325_5_);
        return copy;
    }

    public ComplexBB expand(double p_72314_1_, double p_72314_3_, double p_72314_5_) {
        ComplexBB copy = this.copy();
        copy.field_72340_a -= p_72314_1_;
        copy.field_72338_b -= p_72314_3_;
        copy.field_72339_c -= p_72314_5_;
        copy.field_72336_d += p_72314_1_;
        copy.field_72337_e += p_72314_3_;
        copy.field_72334_f += p_72314_5_;
        for (AxisAlignedBB bb : this.boundingBoxes) {
            bb.func_72314_b(p_72314_1_, p_72314_3_, p_72314_5_);
        }
        return copy;
    }

    public ComplexBB contract(double p_72314_1_, double p_72314_3_, double p_72314_5_) {
        ComplexBB copy = this.copy();
        copy.field_72340_a += p_72314_1_;
        copy.field_72338_b += p_72314_3_;
        copy.field_72339_c += p_72314_5_;
        copy.field_72336_d -= p_72314_1_;
        copy.field_72337_e -= p_72314_3_;
        copy.field_72334_f -= p_72314_5_;
        for (AxisAlignedBB bb : this.boundingBoxes) {
            copy.contract(p_72314_1_, p_72314_3_, p_72314_5_);
        }
        return copy;
    }

    public boolean func_72326_a(AxisAlignedBB p_72326_1_) {
        if (p_72326_1_ == null) {
            return false;
        }
        boolean flag = false;
        for (AxisAlignedBB bb : this.boundingBoxes) {
            flag |= bb.func_72326_a(p_72326_1_);
        }
        return flag;
    }

    public ComplexBB offset(double p_72317_1_, double p_72317_3_, double p_72317_5_) {
        super.func_72317_d(p_72317_1_, p_72317_3_, p_72317_5_);
        for (AxisAlignedBB bb : this.boundingBoxes) {
            bb.func_72317_d(p_72317_1_, p_72317_3_, p_72317_5_);
        }
        return this;
    }

    public boolean func_72318_a(Vec3 p_72318_1_) {
        if (p_72318_1_ == null) {
            return false;
        }
        boolean flag = false;
        for (AxisAlignedBB bb : this.boundingBoxes) {
            flag |= bb.func_72318_a(p_72318_1_);
        }
        return flag;
    }

    public ComplexBB copy() {
        AxisAlignedBB[] bbs = new AxisAlignedBB[this.boundingBoxes.size()];
        for (int i = 0; i < this.boundingBoxes.size(); ++i) {
            bbs[i] = this.boundingBoxes.get(i).func_72329_c();
        }
        return new ComplexBB(bbs);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("complexbb{");
        for (AxisAlignedBB bb : this.boundingBoxes) {
            stringBuilder.append(bb.toString()).append(',');
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}

