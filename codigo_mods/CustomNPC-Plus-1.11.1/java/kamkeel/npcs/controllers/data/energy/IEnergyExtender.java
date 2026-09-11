/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.energy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

public interface IEnergyExtender {
    public boolean onEnergyDamage(Entity var1, EntityLivingBase var2, EntityLivingBase var3, float var4, float var5, float var6, double var7, double var9, float var11, NBTTagCompound var12);

    public float modifyEnergyDamage(Entity var1, EntityLivingBase var2, float var3, NBTTagCompound var4);
}

