/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.data.effect;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

public interface IEffectAction {
    public String getId();

    public String getDisplayName();

    public void apply(EntityLivingBase var1, EntityLivingBase var2, NBTTagCompound var3);

    default public NBTTagCompound createDefaultConfig() {
        return new NBTTagCompound();
    }
}

