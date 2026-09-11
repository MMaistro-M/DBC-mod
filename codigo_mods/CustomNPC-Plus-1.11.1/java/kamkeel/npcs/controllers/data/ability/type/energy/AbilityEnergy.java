/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.type.energy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyLightningData;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.builder.FieldDef;

public abstract class AbilityEnergy
extends Ability {
    protected EnergyDisplayData displayData;
    protected EnergyLightningData lightningData;

    protected AbilityEnergy(EnergyDisplayData displayData) {
        this.displayData = displayData;
        this.lightningData = new EnergyLightningData();
    }

    public int getInnerColor() {
        return this.displayData.innerColor;
    }

    public void setInnerColor(int color) {
        this.displayData.innerColor = color;
    }

    public float getInnerAlpha() {
        return this.displayData.innerAlpha;
    }

    public void setInnerAlpha(float alpha) {
        this.displayData.innerAlpha = alpha;
    }

    public int getOuterColor() {
        return this.displayData.outerColor;
    }

    public void setOuterColor(int color) {
        this.displayData.outerColor = color;
    }

    public boolean isOuterColorEnabled() {
        return this.displayData.outerColorEnabled;
    }

    public void setOuterColorEnabled(boolean enabled) {
        this.displayData.outerColorEnabled = enabled;
    }

    public float getOuterColorWidth() {
        return this.displayData.outerColorWidth;
    }

    public void setOuterColorWidth(float width) {
        this.displayData.outerColorWidth = width;
    }

    public float getOuterColorAlpha() {
        return this.displayData.outerColorAlpha;
    }

    public void setOuterColorAlpha(float alpha) {
        this.displayData.outerColorAlpha = alpha;
    }

    public float getRotationSpeed() {
        return this.displayData.rotationSpeed;
    }

    public void setRotationSpeed(float speed) {
        this.displayData.rotationSpeed = speed;
    }

    public boolean hasLightningEffect() {
        return this.lightningData.lightningEffect;
    }

    public void setLightningEffect(boolean enabled) {
        this.lightningData.lightningEffect = enabled;
    }

    public float getLightningDensity() {
        return this.lightningData.lightningDensity;
    }

    public void setLightningDensity(float density) {
        this.lightningData.lightningDensity = density;
    }

    public float getLightningRadius() {
        return this.lightningData.lightningRadius;
    }

    public void setLightningRadius(float radius) {
        this.lightningData.lightningRadius = radius;
    }

    public int getLightningFadeTime() {
        return this.lightningData.lightningFadeTime;
    }

    public void setLightningFadeTime(int fadeTime) {
        this.lightningData.lightningFadeTime = fadeTime;
    }

    protected void writeEnergyNBT(NBTTagCompound nbt) {
        this.displayData.writeNBT(nbt);
        this.lightningData.writeNBT(nbt);
    }

    protected void readEnergyNBT(NBTTagCompound nbt) {
        this.displayData.readNBT(nbt);
        this.lightningData.readNBT(nbt);
    }

    @SideOnly(value=Side.CLIENT)
    protected void addEnergyVisualDefinitions(List<FieldDef> defs) {
        this.addEnergyColorDefinitions(defs);
        this.addEnergyEffectDefinitions(defs);
    }

    @SideOnly(value=Side.CLIENT)
    protected void addEnergyColorDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.section("ability.section.colors").tab("ability.tab.visual"));
        defs.add(FieldDef.row(FieldDef.colorSubGui("ability.innerColor", this::getInnerColor, this::setInnerColor), FieldDef.floatField("ability.innerAlpha", this::getInnerAlpha, this::setInnerAlpha).range(0.0f, 1.0f)).tab("ability.tab.visual"));
        defs.add(FieldDef.boolField("ability.outerEnabled", this::isOuterColorEnabled, this::setOuterColorEnabled).tab("ability.tab.visual"));
        defs.add(FieldDef.colorSubGui("ability.outerColor", this::getOuterColor, this::setOuterColor).tab("ability.tab.visual").visibleWhen(this::isOuterColorEnabled));
        defs.add(FieldDef.row(FieldDef.floatField("ability.outerWidth", this::getOuterColorWidth, this::setOuterColorWidth).visibleWhen(this::isOuterColorEnabled), FieldDef.floatField("ability.outerAlpha", this::getOuterColorAlpha, this::setOuterColorAlpha).range(0.0f, 1.0f).visibleWhen(this::isOuterColorEnabled)).tab("ability.tab.visual"));
    }

    @SideOnly(value=Side.CLIENT)
    protected void addEnergyEffectDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.section("ability.section.effects").tab("ability.tab.visual"));
        this.addEnergyLightningDefinitions(defs);
    }

    @SideOnly(value=Side.CLIENT)
    protected void addEnergyLightningDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.boolField("ability.lightning", this::hasLightningEffect, this::setLightningEffect).tab("ability.tab.visual"));
        defs.add(FieldDef.row(FieldDef.floatField("gui.density", this::getLightningDensity, this::setLightningDensity).visibleWhen(this::hasLightningEffect).range(0.01f, 100.0f), FieldDef.floatField("gui.radius", this::getLightningRadius, this::setLightningRadius).range(0.1f, 100.0f).visibleWhen(this::hasLightningEffect)).tab("ability.tab.visual"));
    }
}

