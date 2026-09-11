/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.type;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.List;
import kamkeel.npcs.addon.DBCAddon;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import kamkeel.npcs.controllers.data.ability.gui.AbilityFieldDefs;
import kamkeel.npcs.controllers.data.ability.type.AbilityDefend;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.ability.type.IAbilityCounter;
import noppes.npcs.client.gui.builder.FieldDef;
import noppes.npcs.controllers.AnimationController;
import noppes.npcs.controllers.data.Animation;

public class AbilityCounter
extends AbilityDefend
implements IAbilityCounter {
    private CounterType counterType = CounterType.FLAT;
    private float counterValue = 6.0f;
    private int counterAnimationId = -1;
    private String counterAnimationName = "Ability_Guard_Counter";

    public AbilityCounter() {
        this.typeId = "ability.cnpc.counter";
        this.name = "Counter";
        this.cooldownTicks = 0;
        this.windUpTicks = 20;
        this.allowedBy = UserType.BOTH;
        this.activeSound = "random.anvil_land";
        this.activeAnimationName = "Ability_Guard_Active";
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/guard.png")};
    }

    @Override
    protected float performDefend(EntityLivingBase attacker, float amount) {
        float incomingDamage = amount;
        if (DBCAddon.IsAvailable()) {
            incomingDamage = DBCAddon.instance.getAttackerDBCDamage(amount);
        }
        float damage = this.counterType == CounterType.FLAT ? this.counterValue : incomingDamage * (this.counterValue / 100.0f);
        if (this.caster != null && attacker.func_70089_S() && damage > 0.0f) {
            this.applyAbilityDamage(this.caster, attacker, damage, 0.0f);
        }
        return 0.0f;
    }

    @Override
    public boolean hasDamage() {
        return true;
    }

    @Override
    protected Animation getDefendAnimation() {
        return this.getCounterAnimation();
    }

    public Animation getCounterAnimation() {
        if (AnimationController.Instance == null) {
            return null;
        }
        if (this.counterAnimationId >= 0) {
            return (Animation)AnimationController.Instance.get(this.counterAnimationId);
        }
        if (this.counterAnimationName != null && !this.counterAnimationName.isEmpty()) {
            return (Animation)AnimationController.Instance.get(this.counterAnimationName, true);
        }
        return null;
    }

    @Override
    protected void writeSubTypeNBT(NBTTagCompound nbt) {
        nbt.func_74778_a("counterType", this.counterType.name());
        nbt.func_74776_a("counterValue", this.counterValue);
        nbt.func_74768_a("counterAnimationId", this.counterAnimationId);
        nbt.func_74778_a("counterAnimationName", this.resolveAnimationName(this.counterAnimationId, this.counterAnimationName));
    }

    @Override
    protected void readSubTypeNBT(NBTTagCompound nbt) {
        try {
            this.counterType = CounterType.valueOf(nbt.func_74779_i("counterType"));
        }
        catch (Exception e) {
            this.counterType = CounterType.FLAT;
        }
        this.counterValue = nbt.func_74760_g("counterValue");
        this.counterAnimationId = nbt.func_74762_e("counterAnimationId");
        this.counterAnimationName = nbt.func_74779_i("counterAnimationName");
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    protected void getTypeDefinitions(List<FieldDef> defs) {
        defs.addAll(Arrays.asList(FieldDef.enumField("gui.type", CounterType.class, this::getCounterTypeEnum, this::setCounterTypeEnum).hover("ability.hover.counterType"), FieldDef.floatField("gui.value", this::getCounterValue, this::setCounterValue), AbilityFieldDefs.effectsListField("ability.effects", this::getEffects, this::setEffects)));
        FieldDef.insertAfter(defs, "ability.dazedAnimation", FieldDef.animSubGui("ability.counterAnimation", this::getCounterAnimationId, this::setCounterAnimationId, this::getCounterAnimationName, this::setCounterAnimationName).tab("Effects"));
    }

    public CounterType getCounterTypeEnum() {
        return this.counterType;
    }

    public void setCounterTypeEnum(CounterType counterType) {
        this.counterType = counterType;
    }

    @Override
    public int getCounterType() {
        return this.counterType.ordinal();
    }

    @Override
    public void setCounterType(int type) {
        CounterType[] values = CounterType.values();
        this.counterType = type >= 0 && type < values.length ? values[type] : CounterType.FLAT;
    }

    @Override
    public float getCounterValue() {
        return this.counterValue;
    }

    @Override
    public void setCounterValue(float counterValue) {
        this.counterValue = counterValue;
    }

    @Override
    public int getCounterAnimationId() {
        return this.counterAnimationId;
    }

    @Override
    public void setCounterAnimationId(int counterAnimationId) {
        this.counterAnimationId = counterAnimationId;
    }

    public String getCounterAnimationName() {
        return this.counterAnimationName;
    }

    public void setCounterAnimationName(String name) {
        this.counterAnimationName = name != null ? name : "";
    }

    public static enum CounterType {
        FLAT,
        PERCENT;


        public String toString() {
            switch (this) {
                case FLAT: {
                    return "ability.counter.flat";
                }
                case PERCENT: {
                    return "ability.counter.percent";
                }
            }
            return this.name();
        }
    }
}

