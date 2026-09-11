/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.nbt.NBTTagCompound
 */
package riskyken.armourersWorkshop.common.data;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.nbt.NBTTagCompound;

public class BipedRotations {
    private static final String TAG_HEAD = "head";
    private static final String TAG_CHEST = "chest";
    private static final String TAG_LEFT_ARM = "leftArm";
    private static final String TAG_RIGHT_ARM = "rightArm";
    private static final String TAG_LEFT_LEG = "LeftLeg";
    private static final String TAG_RIGHT_LEG = "RightLeg";
    private static final String TAG_IS_CHILD = "isChild";
    public BipedPart head = new BipedPart("head");
    public BipedPart chest = new BipedPart("chest");
    public BipedPart leftArm = new BipedPart("leftArm");
    public BipedPart rightArm = new BipedPart("rightArm");
    public BipedPart leftLeg = new BipedPart("LeftLeg");
    public BipedPart rightLeg = new BipedPart("RightLeg");
    public boolean isChild;
    public boolean hasCustomHead;

    public BipedRotations() {
        this.resetRotations();
    }

    public BipedPart getPartForIndex(int index) {
        switch (index) {
            case 0: {
                return this.head;
            }
            case 1: {
                return this.chest;
            }
            case 2: {
                return this.leftArm;
            }
            case 3: {
                return this.rightArm;
            }
            case 4: {
                return this.leftLeg;
            }
            case 5: {
                return this.rightLeg;
            }
        }
        return null;
    }

    public void resetRotations() {
        this.head.setRotationsDegrees(0.0f, 0.0f, 0.0f);
        this.chest.setRotationsDegrees(0.0f, 0.0f, 0.0f);
        this.leftArm.setRotationsDegrees(0.0f, -1.0f, -10.0f);
        this.rightArm.setRotationsDegrees(0.0f, 1.0f, 10.0f);
        this.leftLeg.setRotationsDegrees(0.0f, 0.0f, 0.0f);
        this.rightLeg.setRotationsDegrees(0.0f, 0.0f, 0.0f);
    }

    public void applyRotationsToBiped(ModelBiped modelBiped) {
        this.head.applyRotationsToBipedPart(modelBiped.field_78116_c);
        this.head.applyRotationsToBipedPart(modelBiped.field_78114_d);
        this.chest.applyRotationsToBipedPart(modelBiped.field_78115_e);
        this.leftArm.applyRotationsToBipedPart(modelBiped.field_78113_g);
        this.rightArm.applyRotationsToBipedPart(modelBiped.field_78112_f);
        this.leftLeg.applyRotationsToBipedPart(modelBiped.field_78124_i);
        this.rightLeg.applyRotationsToBipedPart(modelBiped.field_78123_h);
        modelBiped.field_78091_s = this.isChild;
    }

    public void loadNBTData(NBTTagCompound compound) {
        this.head.loadNBTData(compound);
        this.chest.loadNBTData(compound);
        this.leftArm.loadNBTData(compound);
        this.rightArm.loadNBTData(compound);
        this.leftLeg.loadNBTData(compound);
        this.rightLeg.loadNBTData(compound);
        this.isChild = compound.func_74767_n(TAG_IS_CHILD);
    }

    public void saveNBTData(NBTTagCompound compound) {
        this.head.saveNBTData(compound);
        this.chest.saveNBTData(compound);
        this.leftArm.saveNBTData(compound);
        this.rightArm.saveNBTData(compound);
        this.leftLeg.saveNBTData(compound);
        this.rightLeg.saveNBTData(compound);
        compound.func_74757_a(TAG_IS_CHILD, this.isChild);
    }

    public void readFromBuf(ByteBuf buf) {
        this.head.readFromBuf(buf);
        this.chest.readFromBuf(buf);
        this.leftArm.readFromBuf(buf);
        this.rightArm.readFromBuf(buf);
        this.leftLeg.readFromBuf(buf);
        this.rightLeg.readFromBuf(buf);
        this.isChild = buf.readBoolean();
    }

    public void writeToBuf(ByteBuf buf) {
        this.head.writeToBuf(buf);
        this.chest.writeToBuf(buf);
        this.leftArm.writeToBuf(buf);
        this.rightArm.writeToBuf(buf);
        this.leftLeg.writeToBuf(buf);
        this.rightLeg.writeToBuf(buf);
        buf.writeBoolean(this.isChild);
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.chest == null ? 0 : this.chest.hashCode());
        result = 31 * result + (this.head == null ? 0 : this.head.hashCode());
        result = 31 * result + (this.isChild ? 1231 : 1237);
        result = 31 * result + (this.hasCustomHead ? 1231 : 1237);
        result = 31 * result + (this.leftArm == null ? 0 : this.leftArm.hashCode());
        result = 31 * result + (this.leftLeg == null ? 0 : this.leftLeg.hashCode());
        result = 31 * result + (this.rightArm == null ? 0 : this.rightArm.hashCode());
        result = 31 * result + (this.rightLeg == null ? 0 : this.rightLeg.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        BipedRotations other = (BipedRotations)obj;
        if (this.chest == null ? other.chest != null : !this.chest.equals(other.chest)) {
            return false;
        }
        if (this.head == null ? other.head != null : !this.head.equals(other.head)) {
            return false;
        }
        if (this.isChild != other.isChild) {
            return false;
        }
        if (this.leftArm == null ? other.leftArm != null : !this.leftArm.equals(other.leftArm)) {
            return false;
        }
        if (this.leftLeg == null ? other.leftLeg != null : !this.leftLeg.equals(other.leftLeg)) {
            return false;
        }
        if (this.rightArm == null ? other.rightArm != null : !this.rightArm.equals(other.rightArm)) {
            return false;
        }
        return !(this.rightLeg == null ? other.rightLeg != null : !this.rightLeg.equals(other.rightLeg));
    }

    public class BipedPart {
        private static final String TAG_ROTATION_X = "rotationX";
        private static final String TAG_ROTATION_Y = "rotationY";
        private static final String TAG_ROTATION_Z = "rotationZ";
        private final String partName;
        public float rotationX;
        public float rotationY;
        public float rotationZ;

        public BipedPart(String partName) {
            this.partName = partName;
        }

        public void applyRotationsToBipedPart(ModelRenderer modelRenderer) {
            modelRenderer.field_78795_f = this.rotationX;
            modelRenderer.field_78796_g = this.rotationY;
            modelRenderer.field_78808_h = this.rotationZ;
        }

        public void setRotations(float x, float y, float z) {
            this.rotationX = x;
            this.rotationY = y;
            this.rotationZ = z;
        }

        public void setRotationsDegrees(float x, float y, float z) {
            this.rotationX = (float)Math.toRadians(x);
            this.rotationY = (float)Math.toRadians(y);
            this.rotationZ = (float)Math.toRadians(z);
        }

        public void loadNBTData(NBTTagCompound compound) {
            this.rotationX = compound.func_74760_g(TAG_ROTATION_X + this.partName);
            this.rotationY = compound.func_74760_g(TAG_ROTATION_Y + this.partName);
            this.rotationZ = compound.func_74760_g(TAG_ROTATION_Z + this.partName);
        }

        public void saveNBTData(NBTTagCompound compound) {
            compound.func_74776_a(TAG_ROTATION_X + this.partName, this.rotationX);
            compound.func_74776_a(TAG_ROTATION_Y + this.partName, this.rotationY);
            compound.func_74776_a(TAG_ROTATION_Z + this.partName, this.rotationZ);
        }

        public void writeToBuf(ByteBuf buf) {
            buf.writeFloat(this.rotationX);
            buf.writeFloat(this.rotationY);
            buf.writeFloat(this.rotationZ);
        }

        public void readFromBuf(ByteBuf buf) {
            this.rotationX = buf.readFloat();
            this.rotationY = buf.readFloat();
            this.rotationZ = buf.readFloat();
        }

        public int hashCode() {
            int prime = 31;
            int result = 1;
            result = 31 * result + (this.partName == null ? 0 : this.partName.hashCode());
            result = 31 * result + Float.floatToIntBits(this.rotationX);
            result = 31 * result + Float.floatToIntBits(this.rotationY);
            result = 31 * result + Float.floatToIntBits(this.rotationZ);
            return result;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            BipedPart other = (BipedPart)obj;
            if (this.partName == null ? other.partName != null : !this.partName.equals(other.partName)) {
                return false;
            }
            if (Float.floatToIntBits(this.rotationX) != Float.floatToIntBits(other.rotationX)) {
                return false;
            }
            if (Float.floatToIntBits(this.rotationY) != Float.floatToIntBits(other.rotationY)) {
                return false;
            }
            return Float.floatToIntBits(this.rotationZ) == Float.floatToIntBits(other.rotationZ);
        }

        private BipedRotations getOuterType() {
            return BipedRotations.this;
        }
    }
}

