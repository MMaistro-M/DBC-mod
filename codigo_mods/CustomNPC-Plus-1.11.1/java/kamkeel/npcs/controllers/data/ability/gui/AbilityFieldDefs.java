/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 */
package kamkeel.npcs.controllers.data.ability.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import kamkeel.npcs.controllers.data.ability.data.effect.AbilityCustomEffect;
import kamkeel.npcs.controllers.data.ability.data.effect.AbilityPotionEffect;
import kamkeel.npcs.controllers.data.ability.data.entry.AbilityEffectActionEntry;
import noppes.npcs.client.gui.builder.FieldDef;
import noppes.npcs.client.gui.builder.FieldType;

@SideOnly(value=Side.CLIENT)
public class AbilityFieldDefs {
    public static FieldDef effectsListField(String label, Supplier<List<AbilityPotionEffect>> getter, Consumer<List<AbilityPotionEffect>> setter) {
        return FieldDef.custom(label, FieldType.EFFECTS_LIST, () -> (List)getter.get(), v -> setter.accept((List)v));
    }

    public static FieldDef customEffectsListField(String label, Supplier<List<AbilityCustomEffect>> getter, Consumer<List<AbilityCustomEffect>> setter) {
        return FieldDef.custom(label, FieldType.CUSTOM_EFFECTS_LIST, () -> (List)getter.get(), v -> setter.accept((List)v));
    }

    public static FieldDef effectActionsListField(String label, Supplier<List<AbilityEffectActionEntry>> getter, Consumer<List<AbilityEffectActionEntry>> setter) {
        return FieldDef.custom(label, FieldType.EFFECT_ACTIONS_LIST, () -> (List)getter.get(), v -> setter.accept((List)v));
    }
}

