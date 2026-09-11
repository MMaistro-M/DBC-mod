/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.controllers.data.ability.enums;

public enum TargetingMode {
    AGGRO_TARGET,
    SELF,
    AOE_SELF,
    AOE_TARGET;


    public String toString() {
        switch (this) {
            case AGGRO_TARGET: {
                return "ability.target.aggro_target";
            }
            case SELF: {
                return "ability.target.self";
            }
            case AOE_SELF: {
                return "ability.target.aoe_self";
            }
            case AOE_TARGET: {
                return "ability.target.aoe_target";
            }
        }
        return this.name();
    }
}

