/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.data.effect;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.util.ValueUtil;

public class AbilityCustomEffect {
    private int effectId = -1;
    private int durationTicks = 60;
    private byte level = 0;
    private int index = 0;

    public AbilityCustomEffect() {
    }

    public AbilityCustomEffect(int effectId, int durationTicks, byte level) {
        this.effectId = effectId;
        this.durationTicks = Math.max(1, durationTicks);
        this.level = (byte)Math.max(0, Math.min(10, level));
        this.index = 0;
    }

    public AbilityCustomEffect(int effectId, int durationTicks, byte level, int index) {
        this(effectId, durationTicks, level);
        this.index = index;
    }

    public AbilityCustomEffect copy() {
        return new AbilityCustomEffect(this.effectId, this.durationTicks, this.level, this.index);
    }

    public void apply(EntityLivingBase entity) {
        if (entity == null || this.effectId <= 0) {
            return;
        }
        if (entity instanceof EntityPlayer) {
            CustomEffectController.getInstance().applyEffect((EntityPlayer)entity, this.effectId, this.durationTicks, this.level, this.index);
        }
    }

    public boolean isValid() {
        return this.effectId > 0;
    }

    public NBTTagCompound writeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74768_a("effectId", this.effectId);
        nbt.func_74768_a("duration", this.durationTicks);
        nbt.func_74774_a("level", this.level);
        nbt.func_74768_a("index", this.index);
        return nbt;
    }

    public void readNBT(NBTTagCompound nbt) {
        this.effectId = nbt.func_74762_e("effectId");
        this.durationTicks = nbt.func_74762_e("duration");
        this.level = nbt.func_74771_c("level");
        this.index = nbt.func_74762_e("index");
    }

    public static AbilityCustomEffect fromNBT(NBTTagCompound nbt) {
        AbilityCustomEffect e = new AbilityCustomEffect();
        e.readNBT(nbt);
        return e;
    }

    public int getEffectId() {
        return this.effectId;
    }

    public void setEffectId(int effectId) {
        this.effectId = effectId;
    }

    public int getDurationTicks() {
        return this.durationTicks;
    }

    public void setDurationTicks(int durationTicks) {
        this.durationTicks = Math.max(1, durationTicks);
    }

    public byte getLevel() {
        return this.level;
    }

    public void setLevel(byte level) {
        this.level = (byte)ValueUtil.clamp((int)level, 0, 10);
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = Math.max(0, index);
    }
}

