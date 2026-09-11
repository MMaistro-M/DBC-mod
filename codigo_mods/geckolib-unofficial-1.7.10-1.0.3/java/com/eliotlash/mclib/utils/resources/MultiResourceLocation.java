/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Objects
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.ResourceLocation
 */
package com.eliotlash.mclib.utils.resources;

import com.eliotlash.mclib.utils.resources.FilteredResourceLocation;
import com.eliotlash.mclib.utils.resources.IWritableLocation;
import com.eliotlash.mclib.utils.resources.MultiResourceLocationManager;
import com.eliotlash.mclib.utils.resources.RLUtils;
import com.google.common.base.Objects;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;

public class MultiResourceLocation
extends ResourceLocation
implements IWritableLocation<MultiResourceLocation> {
    public List<FilteredResourceLocation> children = new ArrayList<FilteredResourceLocation>();
    private int id = -1;

    public MultiResourceLocation(String resourceName) {
        this();
        this.children.add(new FilteredResourceLocation(RLUtils.create(resourceName)));
    }

    public MultiResourceLocation(String resourceDomainIn, String resourcePathIn) {
        this();
        this.children.add(new FilteredResourceLocation(RLUtils.create(resourceDomainIn, resourcePathIn)));
    }

    public static MultiResourceLocation from(NBTBase nbt) {
        NBTTagList list;
        NBTTagList nBTTagList = list = nbt instanceof NBTTagList ? (NBTTagList)nbt : null;
        if (list == null || list.func_74745_c() == 0) {
            return null;
        }
        MultiResourceLocation multi = new MultiResourceLocation();
        try {
            multi.fromNbt(nbt);
            return multi;
        }
        catch (Exception exception) {
            return null;
        }
    }

    public static MultiResourceLocation from(JsonElement element) {
        JsonArray list;
        JsonArray jsonArray = list = element.isJsonArray() ? (JsonArray)element : null;
        if (list == null || list.size() == 0) {
            return null;
        }
        MultiResourceLocation multi = new MultiResourceLocation();
        try {
            multi.fromJson(element);
            return multi;
        }
        catch (Exception exception) {
            return null;
        }
    }

    public MultiResourceLocation() {
        super("it_would_be_very_ironic", "if_this_would_match_with_regular_rls");
    }

    public void recalculateId() {
        this.id = MultiResourceLocationManager.getId(this);
    }

    public String func_110624_b() {
        return this.children.isEmpty() ? "" : this.children.get((int)0).path.func_110624_b();
    }

    public String func_110623_a() {
        return this.children.isEmpty() ? "" : this.children.get((int)0).path.func_110623_a();
    }

    public String toString() {
        return this.func_110624_b() + ":" + this.func_110623_a();
    }

    public boolean equals(Object obj) {
        if (obj instanceof MultiResourceLocation) {
            MultiResourceLocation multi = (MultiResourceLocation)obj;
            return Objects.equal(this.children, multi.children);
        }
        return super.equals(obj);
    }

    public int hashCode() {
        if (this.id < 0) {
            this.recalculateId();
        }
        return this.id;
    }

    @Override
    public void fromNbt(NBTBase nbt) throws Exception {
        NBTTagList list = (NBTTagList)nbt;
        for (int i = 0; i < list.func_74745_c(); ++i) {
            FilteredResourceLocation location = FilteredResourceLocation.from((NBTBase)list.func_150305_b(i));
            if (location == null) continue;
            this.children.add(location);
        }
    }

    @Override
    public void fromJson(JsonElement element) throws Exception {
        JsonArray array = (JsonArray)element;
        for (int i = 0; i < array.size(); ++i) {
            FilteredResourceLocation location = FilteredResourceLocation.from(array.get(i));
            if (location == null) continue;
            this.children.add(location);
        }
    }

    @Override
    public NBTBase writeNbt() {
        NBTTagList list = new NBTTagList();
        for (FilteredResourceLocation child : this.children) {
            NBTBase tag = child.writeNbt();
            if (tag == null) continue;
            list.func_74742_a(tag);
        }
        return list;
    }

    @Override
    public JsonElement writeJson() {
        JsonArray array = new JsonArray();
        for (FilteredResourceLocation child : this.children) {
            JsonElement element = child.writeJson();
            if (element == null) continue;
            array.add(element);
        }
        return array;
    }

    @Override
    public MultiResourceLocation copy() {
        MultiResourceLocation newMulti = new MultiResourceLocation();
        for (FilteredResourceLocation child : this.children) {
            newMulti.children.add(child.copy());
        }
        return newMulti;
    }
}

