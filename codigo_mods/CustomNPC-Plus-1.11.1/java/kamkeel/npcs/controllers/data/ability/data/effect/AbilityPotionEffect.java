/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 */
package kamkeel.npcs.controllers.data.ability.data.effect;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import noppes.npcs.constants.EnumPotionType;

public class AbilityPotionEffect {
    private EnumPotionType type = EnumPotionType.None;
    private int manualPotionId = 0;
    private int durationTicks = 60;
    private int amplifier = 0;

    public AbilityPotionEffect() {
    }

    public AbilityPotionEffect(EnumPotionType type, int durationTicks, int amplifier) {
        this.type = type;
        this.durationTicks = Math.max(1, durationTicks);
        this.amplifier = Math.max(0, Math.min(255, amplifier));
    }

    public AbilityPotionEffect copy() {
        AbilityPotionEffect copy = new AbilityPotionEffect(this.type, this.durationTicks, this.amplifier);
        copy.manualPotionId = this.manualPotionId;
        return copy;
    }

    public void apply(EntityLivingBase entity) {
        if (entity == null || this.type == EnumPotionType.None) {
            return;
        }
        if (this.type == EnumPotionType.Fire) {
            entity.func_70015_d(Math.max(1, this.durationTicks / 20));
            return;
        }
        int potionId = this.type.getResolvedPotionId(this.manualPotionId);
        if (EnumPotionType.isValidPotionId(potionId)) {
            entity.func_70690_d(new PotionEffect(potionId, this.durationTicks, this.amplifier));
        }
    }

    public NBTTagCompound writeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74768_a("potionType", this.type.ordinal());
        nbt.func_74768_a("duration", this.durationTicks);
        nbt.func_74768_a("amplifier", this.amplifier);
        if (this.type == EnumPotionType.Manual) {
            nbt.func_74768_a("manualPotionId", this.manualPotionId);
        }
        return nbt;
    }

    public void readNBT(NBTTagCompound nbt) {
        this.type = EnumPotionType.fromOrdinal(nbt.func_74762_e("potionType"));
        this.durationTicks = nbt.func_74762_e("duration");
        this.amplifier = nbt.func_74762_e("amplifier");
        if (this.type == EnumPotionType.Manual) {
            this.manualPotionId = nbt.func_74762_e("manualPotionId");
        }
    }

    public static AbilityPotionEffect fromNBT(NBTTagCompound nbt) {
        AbilityPotionEffect effect = new AbilityPotionEffect();
        effect.readNBT(nbt);
        return effect;
    }

    public EnumPotionType getType() {
        return this.type;
    }

    public void setType(EnumPotionType type) {
        this.type = type != null ? type : EnumPotionType.None;
    }

    public int getManualPotionId() {
        return this.manualPotionId;
    }

    public void setManualPotionId(int manualPotionId) {
        this.manualPotionId = Math.max(0, manualPotionId);
    }

    public int getDurationTicks() {
        return this.durationTicks;
    }

    public void setDurationTicks(int durationTicks) {
        this.durationTicks = Math.max(1, durationTicks);
    }

    public int getAmplifier() {
        return this.amplifier;
    }

    public void setAmplifier(int amplifier) {
        this.amplifier = Math.max(0, Math.min(255, amplifier));
    }

    public boolean isValid() {
        return this.type != EnumPotionType.None;
    }
}

