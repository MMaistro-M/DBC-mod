/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonPrimitive
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.SimpleResource
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.ResourceLocation
 */
package com.eliotlash.mclib.utils.resources;

import com.eliotlash.mclib.utils.resources.IResourceTransformer;
import com.eliotlash.mclib.utils.resources.IWritableLocation;
import com.eliotlash.mclib.utils.resources.MultiResourceLocation;
import com.eliotlash.mclib.utils.resources.TextureLocation;
import com.eliotlash.mclib.utils.resources.TextureProcessor;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.SimpleResource;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;

public class RLUtils {
    private static List<IResourceTransformer> transformers = new ArrayList<IResourceTransformer>();
    private static ResourceLocation pixel = new ResourceLocation("mclib:textures/pixel.png");

    @SideOnly(value=Side.CLIENT)
    public static IResource getStreamForMultiskin(MultiResourceLocation multi) throws IOException {
        if (multi.children.isEmpty()) {
            throw new IOException("Multi-skin is empty!");
        }
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write((RenderedImage)TextureProcessor.postProcess(multi), "png", stream);
            return new SimpleResource((ResourceLocation)multi, (InputStream)new ByteArrayInputStream(stream.toByteArray()), null, null);
        }
        catch (IOException e) {
            throw e;
        }
        catch (Exception e) {
            throw new IOException(e);
        }
    }

    public static void register(IResourceTransformer transformer) {
        transformers.add(transformer);
    }

    public static ResourceLocation create(String path) {
        for (IResourceTransformer transformer : transformers) {
            path = transformer.transform(path);
        }
        return new TextureLocation(path);
    }

    public static ResourceLocation create(String domain, String path) {
        for (IResourceTransformer transformer : transformers) {
            String newDomain = transformer.transformDomain(domain, path);
            String newPath = transformer.transformPath(domain, path);
            domain = newDomain;
            path = newPath;
        }
        return new TextureLocation(domain, path);
    }

    public static ResourceLocation create(NBTBase base) {
        MultiResourceLocation location = MultiResourceLocation.from(base);
        if (location != null) {
            return location;
        }
        if (base instanceof NBTTagString) {
            return RLUtils.create(((NBTTagString)base).func_150285_a_());
        }
        return null;
    }

    public static ResourceLocation create(JsonElement element) {
        MultiResourceLocation location = MultiResourceLocation.from(element);
        if (location != null) {
            return location;
        }
        if (element.isJsonPrimitive()) {
            return RLUtils.create(element.getAsString());
        }
        return null;
    }

    public static NBTBase writeNbt(ResourceLocation location) {
        if (location instanceof IWritableLocation) {
            return ((IWritableLocation)location).writeNbt();
        }
        if (location != null) {
            return new NBTTagString(location.toString());
        }
        return null;
    }

    public static JsonElement writeJson(ResourceLocation location) {
        if (location instanceof IWritableLocation) {
            return ((IWritableLocation)location).writeJson();
        }
        if (location != null) {
            return new JsonPrimitive(location.toString());
        }
        return JsonNull.INSTANCE;
    }

    public static ResourceLocation clone(ResourceLocation location) {
        Object copy;
        if (location instanceof IWritableLocation && (copy = ((IWritableLocation)location).copy()) instanceof ResourceLocation) {
            return (ResourceLocation)copy;
        }
        if (location != null) {
            return RLUtils.create(location.toString());
        }
        return null;
    }
}

