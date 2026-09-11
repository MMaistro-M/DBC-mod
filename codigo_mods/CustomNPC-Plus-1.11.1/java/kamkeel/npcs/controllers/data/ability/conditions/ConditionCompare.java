/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.conditions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.conditions.AbilityCondition;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.builder.FieldDef;

public abstract class ConditionCompare
extends AbilityCondition {
    protected CompareType compareType = CompareType.ABOVE;

    protected abstract float getEntityValue(EntityLivingBase var1);

    protected abstract float getThreshold();

    @SideOnly(value=Side.CLIENT)
    protected abstract void getExtraDefinitions(List<FieldDef> var1);

    protected abstract void writeExtraNBT(NBTTagCompound var1);

    protected abstract void readExtraNBT(NBTTagCompound var1);

    @Override
    protected boolean checkEntity(EntityLivingBase entity) {
        float value = this.getEntityValue(entity);
        if (value < 0.0f) {
            return false;
        }
        return this.compareType.test(value, this.getThreshold());
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getConditionDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.enumField("condition.compare_type", CompareType.class, this::getCompareType, this::setCompareType));
        this.getExtraDefinitions(defs);
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        nbt.func_74768_a("compareType", this.compareType.ordinal());
        this.writeExtraNBT(nbt);
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        this.compareType = CompareType.fromOrdinal(nbt.func_74762_e("compareType"));
        this.readExtraNBT(nbt);
    }

    public CompareType getCompareType() {
        return this.compareType;
    }

    public void setCompareType(CompareType compareType) {
        this.compareType = compareType;
    }

    public static enum CompareType {
        ABOVE{

            @Override
            public boolean test(float value, float threshold) {
                return value >= threshold;
            }
        }
        ,
        BELOW{

            @Override
            public boolean test(float value, float threshold) {
                return value <= threshold;
            }
        }
        ,
        EQUAL{

            @Override
            public boolean test(float value, float threshold) {
                return value == threshold;
            }
        };


        public abstract boolean test(float var1, float var2);

        public static CompareType fromOrdinal(int ordinal) {
            CompareType[] values = CompareType.values();
            return ordinal >= 0 && ordinal < values.length ? values[ordinal] : ABOVE;
        }

        public String toString() {
            return "condition." + this.name().toLowerCase();
        }
    }
}

