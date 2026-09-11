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
import kamkeel.npcs.controllers.data.ability.conditions.ConditionFilter;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.builder.FieldDef;
import noppes.npcs.entity.EntityNPCInterface;

public class ConditionHitCount
extends AbilityCondition {
    private int requiredHits = 3;
    private int withinTicks = 60;

    public ConditionHitCount() {
        this.typeId = "condition.cnpc.hit_count";
        this.name = "condition.hit_count";
        this.conditionFilter = ConditionFilter.CASTER;
    }

    @Override
    protected boolean checkEntity(EntityLivingBase entity) {
        return false;
    }

    @Override
    public boolean check(EntityLivingBase caster, EntityLivingBase target) {
        if (caster instanceof EntityNPCInterface) {
            EntityNPCInterface npc = (EntityNPCInterface)caster;
            if (npc.abilities != null) {
                return npc.abilities.getRecentHitCount(this.withinTicks) >= this.requiredHits;
            }
        }
        return false;
    }

    public int getRequiredHits() {
        return this.requiredHits;
    }

    public void setRequiredHits(int requiredHits) {
        this.requiredHits = Math.max(0, requiredHits);
    }

    public int getWithinTicks() {
        return this.withinTicks;
    }

    public void setWithinTicks(int withinTicks) {
        this.withinTicks = Math.max(0, withinTicks);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getConditionDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.intField("condition.required_hits", this::getRequiredHits, this::setRequiredHits).min(1.0f));
        defs.add(FieldDef.intField("condition.within_ticks", this::getWithinTicks, this::setWithinTicks).min(0.0f));
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getConditionSummary() {
        return "[Caster] Hit " + this.requiredHits + " times in " + this.withinTicks + " ticks";
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        nbt.func_74768_a("requiredHits", this.requiredHits);
        nbt.func_74768_a("withinTicks", this.withinTicks);
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        this.requiredHits = Math.max(0, nbt.func_74762_e("requiredHits"));
        this.withinTicks = Math.max(0, nbt.func_74762_e("withinTicks"));
    }
}

