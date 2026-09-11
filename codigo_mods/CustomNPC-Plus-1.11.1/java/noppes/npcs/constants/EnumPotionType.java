/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.potion.Potion
 */
package noppes.npcs.constants;

import net.minecraft.potion.Potion;

public enum EnumPotionType {
    None("gui.none", -1),
    Fire("tile.fire.name", -1),
    Poison("potion.poison", Potion.field_76436_u.field_76415_H),
    Hunger("potion.hunger", Potion.field_76438_s.field_76415_H),
    Weakness("potion.weakness", Potion.field_76437_t.field_76415_H),
    Slowness("potion.moveSlowdown", Potion.field_76421_d.field_76415_H),
    Nausea("potion.confusion", Potion.field_76431_k.field_76415_H),
    Blindness("potion.blindness", Potion.field_76440_q.field_76415_H),
    Wither("potion.wither", Potion.field_82731_v.field_76415_H),
    MiningFatigue("potion.digSlowDown", Potion.field_76419_f.field_76415_H),
    Manual("effect.manual", -1);

    private final String langKey;
    private final int potionId;

    private EnumPotionType(String langKey, int potionId) {
        this.langKey = langKey;
        this.potionId = potionId;
    }

    public String getLangKey() {
        return this.langKey;
    }

    public int getPotionId() {
        return this.potionId;
    }

    public int getResolvedPotionId(int manualId) {
        if (this == Manual) {
            return manualId;
        }
        return this.potionId;
    }

    public static String[] getLangKeys() {
        EnumPotionType[] types = EnumPotionType.values();
        String[] keys = new String[types.length];
        for (int i = 0; i < types.length; ++i) {
            keys[i] = types[i].langKey;
        }
        return keys;
    }

    public static String[] getLangKeysNoNone() {
        EnumPotionType[] types = EnumPotionType.values();
        String[] keys = new String[types.length - 1];
        for (int i = 1; i < types.length; ++i) {
            keys[i - 1] = types[i].langKey;
        }
        return keys;
    }

    public static EnumPotionType fromIndexNoNone(int index) {
        return EnumPotionType.fromOrdinal(index + 1);
    }

    public static EnumPotionType fromOrdinal(int ordinal) {
        EnumPotionType[] values = EnumPotionType.values();
        if (ordinal >= 0 && ordinal < values.length) {
            return values[ordinal];
        }
        return None;
    }

    public static boolean isValidPotionId(int id) {
        return id >= 0 && id < Potion.field_76425_a.length && Potion.field_76425_a[id] != null;
    }
}

