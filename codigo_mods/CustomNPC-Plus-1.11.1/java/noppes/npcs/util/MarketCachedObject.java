/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.util;

import foxz.utils.Market;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.util.CacheHashMap;

public class MarketCachedObject
extends CacheHashMap.CachedObject<NBTTagCompound> {
    public MarketCachedObject(NBTTagCompound object) {
        super(object);
    }

    @Override
    public void save() {
        Market.saveFile((NBTTagCompound)this.getObject());
    }
}

