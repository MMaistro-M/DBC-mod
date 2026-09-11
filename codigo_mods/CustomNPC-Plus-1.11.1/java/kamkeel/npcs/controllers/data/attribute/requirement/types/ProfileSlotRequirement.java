/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.attribute.requirement.types;

import kamkeel.npcs.controllers.data.attribute.requirement.IRequirementChecker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.controllers.data.PlayerData;

public class ProfileSlotRequirement
implements IRequirementChecker {
    @Override
    public String getKey() {
        return "cnpc_profile_slot";
    }

    @Override
    public String getTranslation() {
        return "profile.slot";
    }

    @Override
    public String getTooltipValue(NBTTagCompound nbt) {
        if (nbt.func_74764_b(this.getKey())) {
            int profileSlot = nbt.func_74762_e(this.getKey());
            return String.valueOf(profileSlot);
        }
        return "null";
    }

    @Override
    public void apply(NBTTagCompound nbt, Object value) {
        if (value instanceof Integer) {
            nbt.func_74768_a(this.getKey(), ((Integer)value).intValue());
        }
    }

    @Override
    public Object getValue(NBTTagCompound nbt) {
        if (nbt.func_74764_b(this.getKey())) {
            return nbt.func_74762_e(this.getKey());
        }
        return null;
    }

    @Override
    public boolean check(EntityPlayer player, NBTTagCompound nbt) {
        if (nbt.func_74764_b(this.getKey())) {
            int profileSlot = nbt.func_74762_e(this.getKey());
            return PlayerData.get((EntityPlayer)player).profileSlot == profileSlot;
        }
        return true;
    }
}

