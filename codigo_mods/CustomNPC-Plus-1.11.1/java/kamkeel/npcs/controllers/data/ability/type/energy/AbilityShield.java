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
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyPanelData;
import kamkeel.npcs.controllers.data.ability.enums.LockMode;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import kamkeel.npcs.controllers.data.ability.type.energy.AbilityBarrier;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import kamkeel.npcs.entity.EntityEnergyBarrier;
import kamkeel.npcs.entity.EntityEnergyPanel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.builder.FieldDef;

public class AbilityShield
extends AbilityBarrier {
    private float shieldWidth = 2.5f;
    private float shieldHeight = 2.5f;

    public AbilityShield() {
        super(new EnergyDisplayData(0xFFDD44, 0xFFAA00, true, 0.3f, 0.4f, 0.5f, 0.0f), new EnergyBarrierData(60.0f, true, 120, true));
        this.typeId = "ability.cnpc.shield";
        this.name = "Shield";
        this.targetingMode = TargetingMode.SELF;
        this.cooldownTicks = 60;
        this.windUpTicks = 10;
        this.lockMovement = LockMode.WINDUP_AND_ACTIVE;
        this.telegraphType = TelegraphType.NONE;
        this.showTelegraph = false;
        this.windUpAnimationName = "";
        this.activeAnimationName = "";
        this.allowedBy = UserType.BOTH;
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/shield.png", () -> this.isOuterColorEnabled() ? this.getOuterColor() : this.getInnerColor())};
    }

    @Override
    protected EntityEnergyBarrier createBarrierEntity(EntityLivingBase caster, EntityLivingBase target) {
        EnergyPanelData panelData = new EnergyPanelData(this.shieldWidth, this.shieldHeight, this.offsetY);
        panelData.offsetX = this.offsetX;
        panelData.offsetZ = this.offsetZ;
        float frontDist = 1.5f;
        float yawRad = (float)Math.toRadians(caster.field_70177_z);
        double spawnX = caster.field_70165_t + -Math.sin(yawRad) * (double)frontDist + (double)this.offsetX;
        double spawnY = caster.field_70163_u + (double)(caster.field_70131_O * 0.5f);
        double spawnZ = caster.field_70161_v + Math.cos(yawRad) * (double)frontDist + (double)this.offsetZ;
        EntityEnergyPanel panel = new EntityEnergyPanel(caster.field_70170_p, caster, spawnX, spawnY, spawnZ, caster.field_70177_z, EntityEnergyPanel.PanelMode.HELD, this.displayData.copy(), this.lightningData.copy(), this.barrierData.copy(), panelData);
        panel.setSourceAbility(this);
        return panel;
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
        return 0.0f;
    }

    @Override
    public List<AbilityVariant> getVariants() {
        return Arrays.asList(new AbilityVariant("ability.variant.small", a -> {
            AbilityShield shield = (AbilityShield)a;
            a.setName("Small Shield");
            shield.setShieldWidth(1.5f);
            shield.setShieldHeight(1.5f);
            shield.setBarrierMaxHealth(40.0f);
        }), new AbilityVariant("ability.variant.large", a -> {
            AbilityShield shield = (AbilityShield)a;
            a.setName("Large Shield");
            shield.setShieldWidth(4.0f);
            shield.setShieldHeight(4.0f);
            shield.setBarrierMaxHealth(120.0f);
            shield.setBarrierDuration(200);
        }));
    }

    @Override
    protected void writeBarrierTypeNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("shieldWidth", this.shieldWidth);
        nbt.func_74776_a("shieldHeight", this.shieldHeight);
    }

    @Override
    protected void readBarrierTypeNBT(NBTTagCompound nbt) {
        this.shieldWidth = nbt.func_74764_b("shieldWidth") ? nbt.func_74760_g("shieldWidth") : 2.5f;
        this.shieldHeight = nbt.func_74764_b("shieldHeight") ? nbt.func_74760_g("shieldHeight") : 2.5f;
    }

    public float getShieldWidth() {
        return this.shieldWidth;
    }

    public void setShieldWidth(float width) {
        this.shieldWidth = Math.max(0.5f, width);
    }

    public float getShieldHeight() {
        return this.shieldHeight;
    }

    public void setShieldHeight(float height) {
        this.shieldHeight = Math.max(0.5f, height);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    protected void addBarrierTypeDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.row(FieldDef.floatField("gui.width", this::getShieldWidth, this::setShieldWidth).range(0.5f, 100.0f), FieldDef.floatField("gui.height", this::getShieldHeight, this::setShieldHeight).range(0.5f, 100.0f)));
    }
}

