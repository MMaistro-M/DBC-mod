/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 *  org.apache.commons.io.FileUtils
 *  org.apache.commons.io.IOUtils
 */
package software.bernie.geckolib3.particles;

import com.eliotlash.mclib.utils.JsonUtils;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import software.bernie.geckolib3.particles.BedrockScheme;
import software.bernie.geckolib3.watchers.ParticleDirWatcher;

public class BedrockLibrary {
    public static long lastUpdate;
    public Map<String, BedrockScheme> presets = new HashMap<String, BedrockScheme>();
    public Map<String, BedrockScheme> factory = new HashMap<String, BedrockScheme>();
    public File folder;
    public ParticleDirWatcher updateController;
    public static BedrockLibrary instance;

    public BedrockLibrary(File folder) {
        instance = this;
        this.folder = folder;
        this.folder.mkdirs();
        this.updateController = new ParticleDirWatcher(folder);
        this.updateController.start();
    }

    public File file(String name) {
        return new File(this.folder, name + ".json");
    }

    public boolean hasEffect(String name) {
        return this.file(name).isFile();
    }

    public void reload() {
        this.presets.clear();
        this.presets.putAll(this.factory);
        this.recursiveWalk(this.folder);
    }

    public void recursiveWalk(File folder) {
        for (File file : folder.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".json")) {
                this.storeScheme(file);
            }
            if (!file.isDirectory()) continue;
            this.recursiveWalk(file);
        }
    }

    public BedrockScheme get(String identifier) {
        for (BedrockScheme scheme : this.presets.values()) {
            if (!scheme.identifier.equals(identifier)) continue;
            return scheme;
        }
        return null;
    }

    public void remove(String name) {
        if (this.presets.containsKey(name = name.substring(0, name.indexOf(".json")))) {
            this.presets.remove(name);
        }
    }

    public void storeScheme(File file) {
        BedrockScheme scheme = this.loadScheme(file);
        if (scheme != null) {
            String name = file.getName();
            String schemeName = name.substring(0, name.indexOf(".json"));
            if (this.presets.containsKey(schemeName)) {
                this.presets.get((Object)schemeName).toReload = true;
            }
            scheme.name = schemeName;
            this.presets.put(schemeName, scheme);
        }
    }

    public BedrockScheme loadScheme(File file) {
        if (!file.exists()) {
            return null;
        }
        try {
            String contents = FileUtils.readFileToString((File)file, (Charset)StandardCharsets.UTF_8);
            if (contents.isEmpty()) {
                return null;
            }
            return BedrockScheme.parse(contents);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void storeFactory(ResourceLocation name) {
        BedrockScheme scheme = this.loadFactory(name);
        if (scheme != null) {
            scheme.name = this.getName(name);
            this.factory.put(this.getName(name), scheme);
        }
    }

    public BedrockScheme loadFactory(ResourceLocation resLoc) {
        try {
            return BedrockScheme.parse(IOUtils.toString((InputStream)this.getClass().getClassLoader().getResourceAsStream("assets/" + resLoc.func_110624_b() + "/" + resLoc.func_110623_a()), (Charset)StandardCharsets.UTF_8)).factory(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getName(ResourceLocation resLoc) {
        String[] parts = resLoc.func_110623_a().split("/");
        String name = parts[parts.length - 1];
        return name.substring(0, name.indexOf(".json"));
    }

    public void save(String filename, BedrockScheme scheme) {
        String json = JsonUtils.jsonToPretty(BedrockScheme.toJson(scheme));
        File file = this.file(filename);
        try {
            FileUtils.writeStringToFile((File)file, (String)json, (Charset)StandardCharsets.UTF_8);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.storeScheme(file);
        lastUpdate = System.currentTimeMillis();
    }
}

