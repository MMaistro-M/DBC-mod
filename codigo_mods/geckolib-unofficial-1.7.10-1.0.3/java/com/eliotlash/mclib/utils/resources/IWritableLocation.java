/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  net.minecraft.nbt.NBTBase
 */
package com.eliotlash.mclib.utils.resources;

import com.eliotlash.mclib.utils.ICopy;
import com.google.gson.JsonElement;
import net.minecraft.nbt.NBTBase;

public interface IWritableLocation<T>
extends ICopy<T> {
    public void fromNbt(NBTBase var1) throws Exception;

    public void fromJson(JsonElement var1) throws Exception;

    public NBTBase writeNbt();

    public JsonElement writeJson();
}

