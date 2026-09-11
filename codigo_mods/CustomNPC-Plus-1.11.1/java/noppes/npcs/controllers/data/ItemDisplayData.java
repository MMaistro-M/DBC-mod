/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import net.minecraft.nbt.NBTTagCompound;

public class ItemDisplayData {
    public String texture = "minecraft:textures/items/iron_pickaxe.png";
    public Float translateX = Float.valueOf(0.0f);
    public Float translateY = Float.valueOf(0.0f);
    public Float translateZ = Float.valueOf(0.0f);
    public Integer itemColor = 9127187;
    public Float scaleX = Float.valueOf(1.0f);
    public Float scaleY = Float.valueOf(1.0f);
    public Float scaleZ = Float.valueOf(1.0f);
    public Float rotationX = Float.valueOf(0.0f);
    public Float rotationY = Float.valueOf(0.0f);
    public Float rotationZ = Float.valueOf(0.0f);
    public Float rotationXRate = Float.valueOf(0.0f);
    public Float rotationYRate = Float.valueOf(0.0f);
    public Float rotationZRate = Float.valueOf(0.0f);
    public Boolean durabilityShow = false;
    public Integer durabilityColor = -1;
    public Boolean animated = false;
    public Integer frameCount = 1;
    public Integer frametime = 2;

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        this.writeToNBT(compound);
        return compound;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        this.setBoolean(compound, "DurabilityShow", this.durabilityShow);
        this.setInteger(compound, "DurabilityColor", this.durabilityColor);
        this.setInteger(compound, "ItemColor", this.itemColor);
        if (this.texture != null) {
            compound.func_74778_a("ItemTexture", this.texture);
        }
        this.setFloat(compound, "RotationX", this.rotationX);
        this.setFloat(compound, "RotationY", this.rotationY);
        this.setFloat(compound, "RotationZ", this.rotationZ);
        this.setFloat(compound, "RotationXRate", this.rotationXRate);
        this.setFloat(compound, "RotationYRate", this.rotationYRate);
        this.setFloat(compound, "RotationZRate", this.rotationZRate);
        this.setFloat(compound, "ScaleX", this.scaleX);
        this.setFloat(compound, "ScaleY", this.scaleY);
        this.setFloat(compound, "ScaleZ", this.scaleZ);
        this.setFloat(compound, "TranslateX", this.translateX);
        this.setFloat(compound, "TranslateY", this.translateY);
        this.setFloat(compound, "TranslateZ", this.translateZ);
        this.setBoolean(compound, "Animated", this.animated);
        this.setInteger(compound, "FrameCount", this.frameCount);
        this.setInteger(compound, "FrameTime", this.frametime);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        if (compound.func_74764_b("DurabilityShow")) {
            this.durabilityShow = compound.func_74767_n("DurabilityShow");
        }
        if (compound.func_74764_b("DurabilityColor")) {
            this.durabilityColor = compound.func_74762_e("DurabilityColor");
        }
        if (compound.func_74764_b("ItemColor")) {
            this.itemColor = compound.func_74762_e("ItemColor");
        }
        if (compound.func_74764_b("ItemTexture")) {
            this.texture = compound.func_74779_i("ItemTexture");
        }
        if (compound.func_74764_b("RotationX")) {
            this.rotationX = Float.valueOf(compound.func_74760_g("RotationX"));
        }
        if (compound.func_74764_b("RotationY")) {
            this.rotationY = Float.valueOf(compound.func_74760_g("RotationY"));
        }
        if (compound.func_74764_b("RotationZ")) {
            this.rotationZ = Float.valueOf(compound.func_74760_g("RotationZ"));
        }
        if (compound.func_74764_b("RotationXRate")) {
            this.rotationXRate = Float.valueOf(compound.func_74760_g("RotationXRate"));
        }
        if (compound.func_74764_b("RotationYRate")) {
            this.rotationYRate = Float.valueOf(compound.func_74760_g("RotationYRate"));
        }
        if (compound.func_74764_b("RotationZRate")) {
            this.rotationZRate = Float.valueOf(compound.func_74760_g("RotationZRate"));
        }
        if (compound.func_74764_b("ScaleX")) {
            this.scaleX = Float.valueOf(compound.func_74760_g("ScaleX"));
        }
        if (compound.func_74764_b("ScaleY")) {
            this.scaleY = Float.valueOf(compound.func_74760_g("ScaleY"));
        }
        if (compound.func_74764_b("ScaleZ")) {
            this.scaleZ = Float.valueOf(compound.func_74760_g("ScaleZ"));
        }
        if (compound.func_74764_b("TranslateX")) {
            this.translateX = Float.valueOf(compound.func_74760_g("TranslateX"));
        }
        if (compound.func_74764_b("TranslateY")) {
            this.translateY = Float.valueOf(compound.func_74760_g("TranslateY"));
        }
        if (compound.func_74764_b("TranslateZ")) {
            this.translateZ = Float.valueOf(compound.func_74760_g("TranslateZ"));
        }
        if (compound.func_74764_b("Animated")) {
            this.animated = compound.func_74767_n("Animated");
        }
        if (compound.func_74764_b("FrameCount")) {
            this.frameCount = Math.max(1, compound.func_74762_e("FrameCount"));
        }
        if (compound.func_74764_b("FrameTime")) {
            this.frametime = Math.max(1, compound.func_74762_e("FrameTime"));
        }
    }

    private void setBoolean(NBTTagCompound compound, String key, Boolean value) {
        if (value != null) {
            compound.func_74757_a(key, value.booleanValue());
        }
    }

    private void setInteger(NBTTagCompound compound, String key, Integer value) {
        if (value != null) {
            compound.func_74768_a(key, value.intValue());
        }
    }

    private void setFloat(NBTTagCompound compound, String key, Float value) {
        if (value != null) {
            compound.func_74776_a(key, value.floatValue());
        }
    }
}

