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
import kamkeel.npcs.controllers.data.ability.type.energy.AbilityBarrier;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import kamkeel.npcs.entity.EntityEnergyBarrier;
import kamkeel.npcs.entity.EntityEnergyPanel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.builder.FieldDef;

public class AbilityWall
extends AbilityBarrier {
    private final EnergyPanelData panelData = new EnergyPanelData(4.0f, 3.0f, 0.0f);

    public AbilityWall() {
        super(new EnergyDisplayData(0x44FFCC, 0x22CCAA, true, 0.3f, 0.4f, 0.5f, 0.0f), new EnergyBarrierData(80.0f, true, 160, true));
        this.typeId = "ability.cnpc.wall";
        this.name = "Wall";
        this.targetingMode = TargetingMode.AGGRO_TARGET;
        this.maxRange = 15.0f;
        this.minRange = 2.0f;
        this.cooldownTicks = 80;
        this.windUpTicks = 20;
        this.lockMovement = LockMode.WINDUP;
        this.telegraphType = TelegraphType.LINE;
        this.showTelegraph = true;
        this.windUpAnimationName = "";
        this.activeAnimationName = "";
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/wall.png"), new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/wall_overlay.png", () -> this.isOuterColorEnabled() ? this.getOuterColor() : this.getInnerColor())};
    }

    @Override
    public boolean hasDamage() {
        return this.panelData.launching;
    }

    @Override
    protected EntityEnergyBarrier createBarrierEntity(EntityLivingBase caster, EntityLivingBase target) {
        float yaw;
        double placeZ;
        double placeY;
        double placeX;
        if (target != null) {
            double dx = target.field_70165_t - caster.field_70165_t;
            double dz = target.field_70161_v - caster.field_70161_v;
            double dist = Math.sqrt(dx * dx + dz * dz);
            if (dist < 0.01) {
                float yawRad = (float)Math.toRadians(caster.field_70177_z);
                placeX = caster.field_70165_t + -Math.sin(yawRad) * 3.0;
                placeY = caster.field_70163_u;
                placeZ = caster.field_70161_v + Math.cos(yawRad) * 3.0;
                yaw = caster.field_70177_z;
            } else {
                double placeDist = Math.min(dist * 0.5, 5.0);
                placeX = caster.field_70165_t + dx / dist * placeDist;
                placeY = caster.field_70163_u;
                placeZ = caster.field_70161_v + dz / dist * placeDist;
                yaw = (float)(Math.atan2(-dx, dz) * 180.0 / Math.PI);
            }
        } else {
            float yawRad = (float)Math.toRadians(caster.field_70177_z);
            placeX = caster.field_70165_t + -Math.sin(yawRad) * 3.0;
            placeY = caster.field_70163_u;
            placeZ = caster.field_70161_v + Math.cos(yawRad) * 3.0;
            yaw = caster.field_70177_z;
        }
        EntityEnergyPanel.PanelMode mode = this.panelData.launching ? EntityEnergyPanel.PanelMode.LAUNCHED : EntityEnergyPanel.PanelMode.PLACED;
        EnergyPanelData data = this.panelData.copy();
        data.heightOffset = 0.0f;
        EntityEnergyPanel panel = new EntityEnergyPanel(caster.field_70170_p, caster, placeX += (double)this.offsetX, placeY += (double)this.offsetY, placeZ += (double)this.offsetZ, yaw, mode, this.displayData.copy(), this.lightningData.copy(), this.barrierData.copy(), data);
        panel.setSourceAbility(this);
        return panel;
    }

    @Override
    public float getTelegraphRadius() {
        return Math.max(this.panelData.panelWidth, this.panelData.panelHeight) * 0.5f;
    }

    @Override
    public List<AbilityVariant> getVariants() {
        return Arrays.asList(new AbilityVariant("ability.variant.static", a -> a.setName("Static Wall")), new AbilityVariant("ability.variant.launched", a -> {
            AbilityWall wall = (AbilityWall)a;
            a.setName("Launched Wall");
            wall.panelData.launching = true;
            wall.panelData.launchSpeed = 0.6f;
            wall.panelData.launchDamage = 10.0f;
            wall.panelData.launchKnockback = 3.0f;
        }));
    }

    @Override
    protected void writeBarrierTypeNBT(NBTTagCompound nbt) {
        this.panelData.writeNBT(nbt);
    }

    @Override
    protected void readBarrierTypeNBT(NBTTagCompound nbt) {
        this.panelData.readNBT(nbt);
        if (!nbt.func_74764_b("barrierOffsetY") && this.panelData.heightOffset != 0.0f) {
            this.offsetY = this.panelData.heightOffset;
            this.panelData.heightOffset = 0.0f;
        }
    }

    public EnergyPanelData getPanelData() {
        return this.panelData;
    }

    public float getPanelWidth() {
        return this.panelData.panelWidth;
    }

    public void setPanelWidth(float width) {
        this.panelData.setPanelWidth(width);
    }

    public float getPanelHeight() {
        return this.panelData.panelHeight;
    }

    public void setPanelHeight(float height) {
        this.panelData.setPanelHeight(height);
    }

    public boolean isLaunching() {
        return this.panelData.launching;
    }

    public void setLaunching(boolean launching) {
        this.panelData.launching = launching;
    }

    public float getLaunchSpeed() {
        return this.panelData.launchSpeed;
    }

    public void setLaunchSpeed(float speed) {
        this.panelData.setLaunchSpeed(speed);
    }

    public float getLaunchDamage() {
        return this.panelData.launchDamage;
    }

    public void setLaunchDamage(float damage) {
        this.panelData.setLaunchDamage(damage);
    }

    @Override
    public float getDisplayDamage() {
        return this.panelData.launching ? this.panelData.launchDamage : 0.0f;
    }

    public float getLaunchKnockback() {
        return this.panelData.launchKnockback;
    }

    public void setLaunchKnockback(float kb) {
        this.panelData.setLaunchKnockback(kb);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    protected void addBarrierTypeDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.section("ability.section.panel"));
        defs.add(FieldDef.row(FieldDef.floatField("gui.width", this::getPanelWidth, this::setPanelWidth).range(0.5f, 100.0f), FieldDef.floatField("gui.height", this::getPanelHeight, this::setPanelHeight).range(0.5f, 100.0f)));
        defs.add(FieldDef.section("ability.section.launch"));
        defs.add(FieldDef.boolField("gui.enabled", this::isLaunching, this::setLaunching).hover("ability.hover.launching"));
        defs.add(FieldDef.row(FieldDef.floatField("stats.speed", this::getLaunchSpeed, this::setLaunchSpeed).visibleWhen(this::isLaunching), FieldDef.floatField("enchantment.damage", this::getLaunchDamage, this::setLaunchDamage).visibleWhen(this::isLaunching)));
        defs.add(FieldDef.floatField("ability.knockback", this::getLaunchKnockback, this::setLaunchKnockback).visibleWhen(this::isLaunching));
    }
}

