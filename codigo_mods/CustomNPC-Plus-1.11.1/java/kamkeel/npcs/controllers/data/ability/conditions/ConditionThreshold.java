/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 */
package kamkeel.npcs.controllers.data.ability.conditions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.conditions.AbilityCondition;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.builder.FieldDef;

public abstract class ConditionThreshold
extends AbilityCondition {
    protected float thresholdFlat = 100.0f;
    protected float thresholdPercent = 0.5f;
    protected boolean percent = true;
    protected ThresholdType thresholdType = ThresholdType.ABOVE;

    protected abstract float getEntityValue(EntityLivingBase var1);

    protected abstract float getEntityMaxValue(EntityLivingBase var1);

    protected abstract String getStatName();

    @Override
    protected boolean checkEntity(EntityLivingBase entity) {
        float threshold;
        float value;
        if (this.percent) {
            float max = this.getEntityMaxValue(entity);
            value = max > 0.0f ? this.getEntityValue(entity) / max : 0.0f;
            threshold = this.thresholdPercent;
        } else {
            value = this.getEntityValue(entity);
            threshold = this.thresholdFlat;
        }
        return this.thresholdType.test(value, threshold);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getConditionDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.floatField("condition.threshold_flat", this::getThresholdFlat, this::setThresholdFlat).min(0.0f).visibleWhen(() -> !this.isPercent()));
        defs.add(FieldDef.floatField("condition.threshold_percent", this::getThresholdPercent, this::setThresholdPercent).min(0.0f).max(1.0f).visibleWhen(this::isPercent));
        defs.add(FieldDef.boolField("condition.percent", this::isPercent, this::setIsPercent));
        defs.add(FieldDef.enumField("condition.threshold_type", ThresholdType.class, this::getThresholdType, this::setThresholdType));
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("thresholdFlat", this.thresholdFlat);
        nbt.func_74776_a("thresholdPercent", this.thresholdPercent);
        nbt.func_74757_a("percent", this.percent);
        nbt.func_74768_a("thresholdType", this.thresholdType.ordinal());
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        this.thresholdFlat = nbt.func_74760_g("thresholdFlat");
        this.thresholdPercent = nbt.func_74760_g("thresholdPercent");
        this.percent = nbt.func_74767_n("percent");
        this.thresholdType = ThresholdType.fromOrdinal(nbt.func_74762_e("thresholdType"));
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getConditionSummary() {
        String filterLabel = StatCollector.func_74838_a((String)this.getFilter().toString());
        String typeName = this.getStatName();
        String typeLabel = StatCollector.func_74838_a((String)this.thresholdType.toString());
        String value = this.percent ? String.format("%.0f%%", Float.valueOf(this.thresholdPercent * 100.0f)) : String.format("%.0f", Float.valueOf(this.thresholdFlat));
        return "[" + filterLabel + "] " + typeName + " " + typeLabel + " " + value;
    }

    public float getThresholdFlat() {
        return this.thresholdFlat;
    }

    public void setThresholdFlat(float value) {
        this.thresholdFlat = Math.max(0.0f, value);
    }

    public float getThresholdPercent() {
        return this.thresholdPercent;
    }

    public void setThresholdPercent(float value) {
        this.thresholdPercent = Math.max(0.0f, Math.min(1.0f, value));
    }

    public boolean isPercent() {
        return this.percent;
    }

    public void setIsPercent(boolean percent) {
        this.percent = percent;
    }

    public ThresholdType getThresholdType() {
        return this.thresholdType;
    }

    public void setThresholdType(ThresholdType type) {
        this.thresholdType = type;
    }

    public static enum ThresholdType {
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
                return Math.abs(value - threshold) < 0.001f;
            }
        };


        public abstract boolean test(float var1, float var2);

        public static ThresholdType fromOrdinal(int ordinal) {
            ThresholdType[] values = ThresholdType.values();
            if (ordinal >= 0 && ordinal < values.length) {
                return values[ordinal];
            }
            return ABOVE;
        }

        public String toString() {
            switch (this) {
                case ABOVE: {
                    return "condition.above";
                }
                case BELOW: {
                    return "condition.below";
                }
                case EQUAL: {
                    return "condition.equal";
                }
            }
            return this.name();
        }
    }
}

