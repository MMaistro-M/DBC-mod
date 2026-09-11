/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.data;

import java.util.List;
import kamkeel.npcs.controllers.data.ability.conditions.AbilityCondition;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public interface IAbilityAction {
    public String getName();

    public boolean isEnabled();

    public void setEnabled(boolean var1);

    public int getWeight();

    public int getCooldownTicks();

    public float getMinRange();

    public float getMaxRange();

    public UserType getAllowedBy();

    public List<AbilityCondition> getConditions();

    public boolean checkConditions(EntityLivingBase var1, EntityLivingBase var2);

    public boolean checkConditionsForPlayer(EntityLivingBase var1);

    default public boolean isAvailableFor(EntityPlayer player) {
        return true;
    }

    public boolean isChain();

    public IAbilityAction deepCopyAction();

    public NBTTagCompound writeNBT(boolean var1);
}

