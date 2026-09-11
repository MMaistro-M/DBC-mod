/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.attribute.requirement;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public interface IRequirementChecker {
    public String getKey();

    public String getTranslation();

    public String getTooltipValue(NBTTagCompound var1);

    public Object getValue(NBTTagCompound var1);

    public void apply(NBTTagCompound var1, Object var2);

    public boolean check(EntityPlayer var1, NBTTagCompound var2);
}

