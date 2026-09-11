/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.profile;

import java.util.List;
import kamkeel.npcs.controllers.data.profile.ProfileInfoEntry;
import kamkeel.npcs.controllers.data.profile.ProfileOperation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public interface IProfileData {
    public String getTagName();

    public NBTTagCompound getCurrentNBT(EntityPlayer var1);

    public void save(EntityPlayer var1);

    public void setNBT(EntityPlayer var1, NBTTagCompound var2);

    public int getSwitchPriority();

    public ProfileOperation verifySwitch(EntityPlayer var1);

    public List<ProfileInfoEntry> getInfo(EntityPlayer var1, NBTTagCompound var2);
}

