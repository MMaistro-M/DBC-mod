/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package com.goodbird.npcgecko.data;

import net.minecraft.nbt.NBTTagCompound;

public class CustomModelData {
    private String model = "geckolib3:geo/npc.geo.json";
    private String animFile = "custom:geo_npc.animation.json";
    private String idleAnim = "";
    private String walkAnim = "";
    private String meleeAttackAnim = "";
    private String hurtAnim = "";
    private String rangedAttackAnim = "";

    public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74778_a("Model", this.model);
        nbttagcompound.func_74778_a("AnimFile", this.animFile);
        nbttagcompound.func_74778_a("IdleAnim", this.idleAnim);
        nbttagcompound.func_74778_a("WalkAnim", this.walkAnim);
        nbttagcompound.func_74778_a("MeleeAttackAnim", this.meleeAttackAnim);
        nbttagcompound.func_74778_a("RangedAttackAnim", this.rangedAttackAnim);
        nbttagcompound.func_74778_a("HurtAnim", this.hurtAnim);
        return nbttagcompound;
    }

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        if (nbttagcompound.func_74764_b("Model")) {
            this.model = nbttagcompound.func_74779_i("Model");
            this.animFile = nbttagcompound.func_74779_i("AnimFile");
            this.idleAnim = nbttagcompound.func_74779_i("IdleAnim");
            this.walkAnim = nbttagcompound.func_74779_i("WalkAnim");
            this.hurtAnim = nbttagcompound.func_74779_i("HurtAnim");
            this.meleeAttackAnim = nbttagcompound.func_74779_i("MeleeAttackAnim");
            this.rangedAttackAnim = nbttagcompound.func_74779_i("RangedAttackAnim");
        }
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAnimFile() {
        return this.animFile;
    }

    public void setAnimFile(String animFile) {
        this.animFile = animFile;
    }

    public String getIdleAnim() {
        return this.idleAnim;
    }

    public void setIdleAnim(String idleAnim) {
        this.idleAnim = idleAnim;
    }

    public String getWalkAnim() {
        return this.walkAnim;
    }

    public void setWalkAnim(String walkAnim) {
        this.walkAnim = walkAnim;
    }

    public String getMeleeAttackAnim() {
        return this.meleeAttackAnim;
    }

    public void setMeleeAttackAnim(String meleeAttackAnim) {
        this.meleeAttackAnim = meleeAttackAnim;
    }

    public String getHurtAnim() {
        return this.hurtAnim;
    }

    public void setHurtAnim(String hurtAnim) {
        this.hurtAnim = hurtAnim;
    }

    public String getRangedAttackAnim() {
        return this.rangedAttackAnim;
    }

    public void setRangedAttackAnim(String rangedAttackAnim) {
        this.rangedAttackAnim = rangedAttackAnim;
    }
}

