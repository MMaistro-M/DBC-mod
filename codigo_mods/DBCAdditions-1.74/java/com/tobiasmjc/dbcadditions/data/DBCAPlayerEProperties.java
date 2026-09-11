/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IExtendedEntityProperties
 */
package com.tobiasmjc.dbcadditions.data;

import com.tobiasmjc.dbcadditions.inventory.InventoryDBCAPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class DBCAPlayerEProperties
implements IExtendedEntityProperties {
    public static final String EXT_PROP_NAME = "DBCAInventory";
    public final InventoryDBCAPlayer inventory = new InventoryDBCAPlayer(2);

    public static void register(EntityPlayer player) {
        player.registerExtendedProperties(EXT_PROP_NAME, (IExtendedEntityProperties)new DBCAPlayerEProperties());
    }

    public static InventoryDBCAPlayer getInventory(EntityPlayer player) {
        return ((DBCAPlayerEProperties)player.getExtendedProperties((String)EXT_PROP_NAME)).inventory;
    }

    public static final DBCAPlayerEProperties get(EntityPlayer player) {
        return (DBCAPlayerEProperties)player.getExtendedProperties(EXT_PROP_NAME);
    }

    public void copy(DBCAPlayerEProperties props) {
        this.inventory.copy(props.inventory);
    }

    public void saveNBTData(NBTTagCompound compound) {
        NBTTagCompound invTag = new NBTTagCompound();
        this.inventory.writeToNBT(invTag);
        compound.func_74782_a(EXT_PROP_NAME, (NBTBase)invTag);
    }

    public void loadNBTData(NBTTagCompound compound) {
        if (compound.func_74764_b(EXT_PROP_NAME)) {
            this.inventory.readFromNBT(compound.func_74775_l(EXT_PROP_NAME));
        }
    }

    public void init(Entity entity, World world) {
    }
}

