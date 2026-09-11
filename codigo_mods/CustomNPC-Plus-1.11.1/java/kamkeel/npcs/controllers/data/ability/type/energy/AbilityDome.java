/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.type.energy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.AbilityVariant;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyBarrierData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.enums.LockMode;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.ability.type.energy.AbilityBarrier;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import kamkeel.npcs.entity.EntityEnergyBarrier;
import kamkeel.npcs.entity.EntityEnergyDome;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.builder.FieldDef;

public class AbilityDome
extends AbilityBarrier {
    private float domeRadius = 5.0f;
    private boolean followCaster = false;

    public AbilityDome() {
        super(new EnergyDisplayData(0x44CCFF, 0x2288FF, true, 0.3f, 0.35f, 0.5f, 0.0f), new EnergyBarrierData(100.0f, true, 200, true));
        this.typeId = "ability.cnpc.dome";
        this.name = "Dome";
        this.targetingMode = TargetingMode.SELF;
        this.cooldownTicks = 100;
        this.windUpTicks = 30;
        this.lockMovement = LockMode.WINDUP;
        this.telegraphType = TelegraphType.RING;
        this.showTelegraph = true;
        this.windUpAnimationName = "";
        this.activeAnimationName = "";
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/dome.png", () -> this.isOuterColorEnabled() ? this.getOuterColor() : this.getInnerColor())};
    }

    @Override
    protected EntityEnergyBarrier createBarrierEntity(EntityLivingBase caster, EntityLivingBase target) {
        EntityEnergyDome dome = new EntityEnergyDome(caster.field_70170_p, caster, caster.field_70165_t + (double)this.offsetX, caster.field_70163_u + (double)this.offsetY, caster.field_70161_v + (double)this.offsetZ, this.domeRadius, this.displayData.copy(), this.lightningData.copy(), this.barrierData.copy());
        dome.setFollowCaster(this.followCaster);
        dome.setOffsets(this.offsetX, this.offsetY, this.offsetZ);
        dome.setSourceAbility(this);
        return dome;
    }

    @Override
    public boolean isTargetingModeLocked() {
        return true;
    }

    @Override
    public TargetingMode[] getAllowedTargetingModes() {
        return new TargetingMode[]{TargetingMode.SELF};
    }

    @Override
    public float getTelegraphRadius() {
        return this.domeRadius;
    }

    @Override
    public List<AbilityVariant> getVariants() {
        return Arrays.asList(new AbilityVariant("ability.variant.small", a -> {
            AbilityDome dome = (AbilityDome)a;
            a.setName("Small Dome");
            dome.setDomeRadius(3.0f);
            dome.setBarrierMaxHealth(60.0f);
        }), new AbilityVariant("ability.variant.large", a -> {
            AbilityDome dome = (AbilityDome)a;
            a.setName("Large Dome");
            dome.setDomeRadius(8.0f);
            dome.setBarrierMaxHealth(200.0f);
            dome.setBarrierDuration(300);
        }));
    }

    @Override
    protected void writeBarrierTypeNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("domeRadius", this.domeRadius);
        nbt.func_74757_a("followCaster", this.followCaster);
    }

    @Override
    protected void readBarrierTypeNBT(NBTTagCompound nbt) {
        this.domeRadius = nbt.func_74764_b("domeRadius") ? nbt.func_74760_g("domeRadius") : 5.0f;
        this.followCaster = nbt.func_74764_b("followCaster") && nbt.func_74767_n("followCaster");
    }

    public float getDomeRadius() {
        return this.domeRadius;
    }

    public void setDomeRadius(float radius) {
        this.domeRadius = Math.max(1.0f, radius);
    }

    public boolean isFollowCaster() {
        return this.followCaster;
    }

    public void setFollowCaster(boolean follow) {
        this.followCaster = follow;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    protected void addBarrierTypeDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.floatField("ability.domeRadius", this::getDomeRadius, this::setDomeRadius).range(1.0f, 50.0f));
        defs.add(FieldDef.boolField("ability.followCaster", this::isFollowCaster, this::setFollowCaster).hover("ability.hover.followCaster"));
    }
}

