/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import cpw.mods.fml.common.FMLCommonHandler;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.network.packets.data.ability.AbilityHotbarSyncPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.controllers.data.AbilityHotbarData;

public class PlayerAbilityHotbarData {
    public AbilityHotbarData[] slots = new AbilityHotbarData[12];

    public PlayerAbilityHotbarData() {
        for (int i = 0; i < this.slots.length; ++i) {
            this.slots[i] = new AbilityHotbarData(i);
        }
    }

    public AbilityHotbarData getSlot(int index) {
        if (index < 0 || index >= this.slots.length) {
            return null;
        }
        return this.slots[index];
    }

    public void setSlot(int index, String abilityKey) {
        if (index < 0 || index >= this.slots.length) {
            return;
        }
        this.slots[index].abilityKey = abilityKey != null ? abilityKey : "";
    }

    public void clearSlot(int index) {
        if (index < 0 || index >= this.slots.length) {
            return;
        }
        this.slots[index].reset();
    }

    public boolean hasAnyAbilities() {
        for (AbilityHotbarData slot : this.slots) {
            if (slot.isEmpty()) continue;
            return true;
        }
        return false;
    }

    public void writeToNBT(NBTTagCompound compound) {
        for (int i = 0; i < this.slots.length; ++i) {
            this.slots[i].writeToNBT(compound);
        }
    }

    public void readFromNBT(NBTTagCompound compound) {
        for (int i = 0; i < this.slots.length; ++i) {
            NBTTagCompound slotTag = compound.func_74775_l("AbilityHotbar" + i);
            this.slots[i].readFromNBT(slotTag);
        }
        this.validateSlots();
    }

    public void validateSlots() {
        if (AbilityController.Instance == null) {
            return;
        }
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            return;
        }
        for (AbilityHotbarData slot : this.slots) {
            boolean valid;
            if (slot.isEmpty()) continue;
            if (slot.isChainKey()) {
                valid = AbilityController.Instance.canResolveChainedAbility(slot.getResolveKey());
            } else {
                Ability ability;
                valid = AbilityController.Instance.canResolveAbility(slot.abilityKey);
                if (valid && (ability = AbilityController.Instance.resolveAbility(slot.abilityKey)) != null && !ability.getAllowedBy().allowsPlayer()) {
                    valid = false;
                }
            }
            if (valid) continue;
            slot.reset();
        }
    }

    public void syncToClient(EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            AbilityHotbarSyncPacket.sendToPlayer((EntityPlayerMP)player);
        }
    }
}

