/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.scripted;

import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.IPixelmonPlayerData;
import noppes.npcs.compat.PixelmonHelper;
import noppes.npcs.scripted.entity.ScriptPixelmon;

public class ScriptPixelmonPlayerData
implements IPixelmonPlayerData {
    private EntityPlayerMP player;

    public ScriptPixelmonPlayerData(EntityPlayerMP player) {
        this.player = player;
    }

    @Override
    public ScriptPixelmon getPartySlot(int slot) {
        NBTTagCompound compound = PixelmonHelper.getPartySlot(slot, (EntityPlayer)this.player);
        if (compound == null) {
            return null;
        }
        EntityTameable pixelmon = PixelmonHelper.pixelmonFromNBT(compound, (EntityPlayer)this.player);
        return new ScriptPixelmon<EntityTameable>(pixelmon, compound);
    }

    @Override
    public int countPCPixelmon() {
        return PixelmonHelper.countPCPixelmon(this.player);
    }
}

