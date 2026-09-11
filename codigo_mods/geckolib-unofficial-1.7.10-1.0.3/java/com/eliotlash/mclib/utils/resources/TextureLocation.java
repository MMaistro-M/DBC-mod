/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package com.eliotlash.mclib.utils.resources;

import java.lang.reflect.Field;
import net.minecraft.util.ResourceLocation;

public class TextureLocation
extends ResourceLocation {
    public TextureLocation(String domain, String path) {
        super(domain, path);
        this.set(domain, path);
    }

    public TextureLocation(String string) {
        super(string);
        this.set(string);
    }

    public void set(String location) {
        String[] split = location.split(":");
        String domain = split.length > 0 ? split[0] : "minecraft";
        String path = split.length > 1 ? split[1] : "";
        this.set(domain, path);
    }

    public void set(String domain, String path) {
        Field[] fields;
        for (Field field : fields = ResourceLocation.class.getDeclaredFields()) {
            try {
                this.unlockField(field);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            fields[0].set((Object)this, domain);
            fields[1].set((Object)this, path);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void unlockField(Field field) throws Exception {
        field.setAccessible(true);
        Field modifiers = Field.class.getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(field, field.getModifiers() & 0xFFFFFFEF);
    }
}

