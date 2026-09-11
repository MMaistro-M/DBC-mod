/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.event.entity.EntityEvent$EntityConstructing
 *  net.minecraftforge.event.entity.player.PlayerEvent$Clone
 */
package com.tobiasmjc.dbcadditions.event;

import com.tobiasmjc.dbcadditions.data.DBCAPlayerEProperties;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class DBCAPlayerEvents {
    @SubscribeEvent
    public void onPlayerConstructing(EntityEvent.EntityConstructing event) {
        EntityPlayer player;
        if (event.entity instanceof EntityPlayer && DBCAPlayerEProperties.get(player = (EntityPlayer)event.entity) == null) {
            DBCAPlayerEProperties.register(player);
            NBTTagCompound compound = player.getEntityData();
            DBCAPlayerEProperties.get(player).loadNBTData(compound);
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        DBCAPlayerEProperties.get(event.entityPlayer).copy(DBCAPlayerEProperties.get(event.original));
    }
}

