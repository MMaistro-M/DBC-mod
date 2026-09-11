/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.addon;

import cpw.mods.fml.common.Loader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.entity.EntityNPCInterface;

public class DBCAddon {
    public static DBCAddon instance;
    public boolean supportEnabled = true;

    public DBCAddon() {
        instance = this;
    }

    public static boolean IsAvailable() {
        return Loader.isModLoaded((String)"npcdbc");
    }

    public void dbcCopyData(EntityLivingBase copied, EntityLivingBase entity) {
    }

    public boolean canDBCAttack(EntityNPCInterface npc, float attackStrength, Entity receiver) {
        return false;
    }

    public void doDBCDamage(EntityNPCInterface npc, float attackStrength, Entity receiver) {
    }

    public float getAttackerDBCDamage(float vanillaDamage) {
        return vanillaDamage;
    }

    public boolean isKO(EntityNPCInterface npc, EntityPlayer player) {
        return false;
    }

    public void writeToNBT(PlayerData playerData, NBTTagCompound nbtTagCompound) {
    }

    public void readFromNBT(PlayerData playerData, NBTTagCompound nbtTagCompound) {
    }

    public void syncPlayer(EntityPlayerMP playerMP) {
    }
}

