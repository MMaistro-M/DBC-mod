/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package kamkeel.npcs.controllers.data.ability.conditions;

import kamkeel.npcs.controllers.data.ability.conditions.ConditionThreshold;
import net.minecraft.entity.EntityLivingBase;

public class ConditionHPThreshold
extends ConditionThreshold {
    public ConditionHPThreshold() {
        this.typeId = "condition.cnpc.hp_threshold";
        this.name = "condition.hp_threshold";
    }

    @Override
    protected float getEntityValue(EntityLivingBase entity) {
        return entity.func_110143_aJ();
    }

    @Override
    protected float getEntityMaxValue(EntityLivingBase entity) {
        return entity.func_110138_aP();
    }

    @Override
    protected String getStatName() {
        return "HP";
    }
}

