/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.constants;

import noppes.npcs.constants.EnumAnimation;

public enum EnumCompanionStage {
    BABY(0, EnumAnimation.CRAWLING, "companion.baby"),
    CHILD(72000, EnumAnimation.NONE, "companion.child"),
    TEEN(180000, EnumAnimation.NONE, "companion.teenager"),
    ADULT(324000, EnumAnimation.NONE, "companion.adult"),
    FULLGROWN(450000, EnumAnimation.NONE, "companion.fullgrown");

    public int matureAge;
    public EnumAnimation animation;
    public String name;

    private EnumCompanionStage(int age, EnumAnimation animation, String name) {
        this.matureAge = age;
        this.animation = animation;
        this.name = name;
    }
}

