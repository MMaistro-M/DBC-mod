/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.stream.JsonReader
 *  javax.annotation.Nullable
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.util.ResourceLocation
 *  org.apache.commons.io.IOUtils
 */
package software.bernie.geckolib3.file;

import com.eliotlash.molang.MolangParser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.core.builder.Animation;
import software.bernie.geckolib3.file.AnimationFile;
import software.bernie.geckolib3.file.GeckoJsonException;
import software.bernie.geckolib3.util.json.JsonAnimationUtils;

public class AnimationFileLoader {
    private static AnimationFileLoader instance;

    public static AnimationFileLoader getInstance() {
        if (instance == null) {
            instance = new AnimationFileLoader();
        }
        return instance;
    }

    public AnimationFile loadAllAnimations(MolangParser parser, ResourceLocation location, IResourceManager manager) {
        AnimationFile animationFile = new AnimationFile();
        JsonObject jsonRepresentation = this.loadFile(location, manager);
        Set<Map.Entry<String, JsonElement>> entrySet = JsonAnimationUtils.getAnimations(jsonRepresentation);
        for (Map.Entry<String, JsonElement> entry : entrySet) {
            String animationName = entry.getKey();
            try {
                Animation animation = JsonAnimationUtils.deserializeJsonToAnimation(JsonAnimationUtils.getAnimation(jsonRepresentation, animationName), parser);
                animationFile.putAnimation(animationName, animation);
            }
            catch (GeckoJsonException e) {
                GeckoLib.LOGGER.error("Could not load animation: {}", new Object[]{animationName, e});
                throw new RuntimeException(e);
            }
        }
        return animationFile;
    }

    public AnimationFile loadAllAnimations(MolangParser parser, File file, ResourceLocation location) {
        AnimationFile animationFile = new AnimationFile();
        JsonObject jsonRepresentation = this.loadFile(file, location);
        Set<Map.Entry<String, JsonElement>> entrySet = JsonAnimationUtils.getAnimations(jsonRepresentation);
        for (Map.Entry<String, JsonElement> entry : entrySet) {
            String animationName = entry.getKey();
            try {
                Animation animation = JsonAnimationUtils.deserializeJsonToAnimation(JsonAnimationUtils.getAnimation(jsonRepresentation, animationName), parser);
                animationFile.putAnimation(animationName, animation);
            }
            catch (GeckoJsonException e) {
                GeckoLib.LOGGER.error("Could not load animation: {}", new Object[]{animationName, e});
                throw new RuntimeException(e);
            }
        }
        return animationFile;
    }

    private JsonObject loadFile(ResourceLocation location, IResourceManager manager) {
        String content = AnimationFileLoader.getResourceAsString(location, manager);
        Gson GSON = new Gson();
        return AnimationFileLoader.gsonDeserialize(GSON, new StringReader(content), JsonObject.class, false);
    }

    private JsonObject loadFile(File file, ResourceLocation location) {
        String content = AnimationFileLoader.getResourceAsString(file, location);
        Gson GSON = new Gson();
        return AnimationFileLoader.gsonDeserialize(GSON, new StringReader(content), JsonObject.class, false);
    }

    @Nullable
    public static <T> T gsonDeserialize(Gson gsonIn, Reader readerIn, Class<T> adapter, boolean lenient) {
        try {
            JsonReader jsonreader = new JsonReader(readerIn);
            jsonreader.setLenient(lenient);
            return (T)gsonIn.getAdapter(adapter).read(jsonreader);
        }
        catch (IOException ioexception) {
            throw new JsonParseException((Throwable)ioexception);
        }
    }

    public static String getResourceAsString(ResourceLocation location, IResourceManager manager) {
        String string;
        block8: {
            InputStream inputStream = manager.func_110536_a(location).func_110527_b();
            try {
                string = IOUtils.toString((InputStream)inputStream, (Charset)Charset.defaultCharset());
                if (inputStream == null) break block8;
            }
            catch (Throwable throwable) {
                try {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                catch (Exception e) {
                    String message = "Couldn't load " + location;
                    GeckoLib.LOGGER.error(message, (Throwable)e);
                    throw new RuntimeException(new FileNotFoundException(location.toString()));
                }
            }
            inputStream.close();
        }
        return string;
    }

    public static String getResourceAsString(File file, ResourceLocation location) {
        String string;
        block8: {
            InputStream inputStream = file.toURL().openStream();
            try {
                string = IOUtils.toString((InputStream)inputStream, (Charset)Charset.defaultCharset());
                if (inputStream == null) break block8;
            }
            catch (Throwable throwable) {
                try {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                catch (Exception e) {
                    String message = "Couldn't load " + location;
                    GeckoLib.LOGGER.error(message, (Throwable)e);
                    throw new RuntimeException(new FileNotFoundException(location.toString()));
                }
            }
            inputStream.close();
        }
        return string;
    }
}

