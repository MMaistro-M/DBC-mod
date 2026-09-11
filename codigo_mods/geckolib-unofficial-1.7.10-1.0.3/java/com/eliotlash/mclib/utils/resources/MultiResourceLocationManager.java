/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package com.eliotlash.mclib.utils.resources;

import com.eliotlash.mclib.utils.resources.MultiResourceLocation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class MultiResourceLocationManager {
    private static int id = 0;
    private static Map<ResourceLocation, List<Pair>> map = new HashMap<ResourceLocation, List<Pair>>();

    public static int getId(MultiResourceLocation location) {
        if (location.children.isEmpty()) {
            return -1;
        }
        ResourceLocation keyRL = location.children.get((int)0).path;
        List<Pair> pairs = map.get(keyRL);
        if (pairs == null) {
            pairs = new ArrayList<Pair>();
            map.put(keyRL, pairs);
        }
        for (Pair pair : pairs) {
            if (!pair.mrl.equals(location)) continue;
            return pair.id;
        }
        int newId = id++;
        pairs.add(new Pair(newId, location.copy()));
        return newId;
    }

    private static class Pair {
        public int id;
        public MultiResourceLocation mrl;

        public Pair(int id, MultiResourceLocation mrl) {
            this.id = id;
            this.mrl = mrl;
        }
    }
}

