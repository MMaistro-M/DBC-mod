/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.particles;

public enum BedrockCurveType {
    LINEAR("linear"),
    HERMITE("catmull_rom");

    public final String id;

    public static BedrockCurveType fromString(String type) {
        for (BedrockCurveType t : BedrockCurveType.values()) {
            if (!t.id.equals(type)) continue;
            return t;
        }
        return LINEAR;
    }

    private BedrockCurveType(String id) {
        this.id = id;
    }
}

