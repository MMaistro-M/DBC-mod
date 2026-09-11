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
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.conditions.ConditionFilter;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import noppes.npcs.LogWriter;
import noppes.npcs.client.gui.builder.FieldDef;

public abstract class AbilityCondition {
    public static final int MAX_CONDITIONS = 5;
    protected String typeId = "";
    protected String name = "";
    protected UserType userType = UserType.BOTH;
    protected ConditionFilter conditionFilter = ConditionFilter.CASTER;

    public boolean check(EntityLivingBase caster, EntityLivingBase target) {
        switch (this.getFilter()) {
            case CASTER: {
                return this.checkEntity(caster);
            }
            case TARGET: {
                return target != null && this.checkEntity(target);
            }
            case BOTH: {
                return this.checkEntity(caster) && target != null && this.checkEntity(target);
            }
        }
        return this.checkEntity(caster);
    }

    protected abstract boolean checkEntity(EntityLivingBase var1);

    public boolean requiresTarget() {
        return false;
    }

    public boolean isConfigured() {
        return true;
    }

    public UserType getUserType() {
        return this.userType;
    }

    public String getTypeId() {
        return this.typeId;
    }

    public String getName() {
        return this.name;
    }

    public ConditionFilter getFilter() {
        return this.conditionFilter;
    }

    public void setFilter(ConditionFilter filter) {
        this.conditionFilter = filter;
    }

    @SideOnly(value=Side.CLIENT)
    public abstract void getConditionDefinitions(List<FieldDef> var1);

    @SideOnly(value=Side.CLIENT)
    public abstract String getConditionSummary();

    @SideOnly(value=Side.CLIENT)
    public final List<FieldDef> getAllDefinitions() {
        ArrayList<FieldDef> defs = new ArrayList<FieldDef>();
        defs.add(FieldDef.labelField("ability.validFor", () -> "\u00a7e" + StatCollector.func_74838_a((String)("ability.userType." + this.getUserType().name()))));
        defs.add(FieldDef.enumField("condition.filter", ConditionFilter.class, this::getFilter, this::setFilter));
        this.getConditionDefinitions(defs);
        return defs;
    }

    public final NBTTagCompound writeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74778_a("typeId", this.getTypeId());
        nbt.func_74778_a("name", this.getName());
        nbt.func_74768_a("userType", this.getUserType().ordinal());
        nbt.func_74768_a("filter", this.getFilter().ordinal());
        this.writeTypeNBT(nbt);
        return nbt;
    }

    public abstract void writeTypeNBT(NBTTagCompound var1);

    public abstract void readTypeNBT(NBTTagCompound var1);

    public final void readNBT(NBTTagCompound nbt) {
        this.typeId = nbt.func_74779_i("typeId");
        this.name = nbt.func_74779_i("name");
        this.userType = UserType.fromOrdinal(nbt.func_74762_e("userType"));
        this.conditionFilter = ConditionFilter.fromOrdinal(nbt.func_74762_e("filter"));
        this.readTypeNBT(nbt);
    }

    public static AbilityCondition fromNBT(NBTTagCompound nbt) {
        String typeId = nbt.func_74779_i("typeId");
        Supplier<AbilityCondition> factory = AbilityController.Instance.getConditionType(typeId);
        if (factory == null) {
            LogWriter.info("AbilityController: Unknown condition type: " + typeId);
            return null;
        }
        AbilityCondition condition = factory.get();
        condition.readNBT(nbt);
        return condition;
    }
}

