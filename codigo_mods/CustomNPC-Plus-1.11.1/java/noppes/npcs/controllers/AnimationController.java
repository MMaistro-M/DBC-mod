/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;
import kamkeel.npcs.util.Register;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;
import noppes.npcs.api.handler.IAnimationHandler;
import noppes.npcs.api.handler.data.IAnimation;
import noppes.npcs.controllers.CategoryManager;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.controllers.data.BuiltInAnimation;
import noppes.npcs.controllers.data.Category;
import noppes.npcs.util.NBTJsonUtil;

public class AnimationController
implements IAnimationHandler {
    public HashMap<Integer, Animation> animations;
    private HashMap<Integer, String> bootOrder;
    public HashMap<String, BuiltInAnimation> builtInAnimations = new HashMap();
    private static final String BUILTIN_ANIMATIONS_PATH = "/assets/customnpcs/cnpc_animations";
    private static final String BUILTIN_ANIMATIONS_RESOURCE = "assets/customnpcs/cnpc_animations";
    public static AnimationController Instance = new AnimationController();
    private int lastUsedID = 0;
    public CategoryManager categoryManager = new CategoryManager();
    public LinkedHashMap<String, Register.Animations> registeredAnimations = new LinkedHashMap();

    public AnimationController() {
        Instance = this;
        this.bootOrder = new HashMap();
        this.animations = new HashMap();
    }

    public static AnimationController getInstance() {
        return Instance;
    }

    public void load() {
        this.bootOrder = new HashMap();
        this.animations = new HashMap();
        this.builtInAnimations = new HashMap();
        LogWriter.info("Loading animations...");
        this.loadBuiltInAnimations();
        this.loadRegisteredBuiltIns();
        this.readAnimationMap();
        this.loadAnimations();
        LogWriter.info("Done loading animations.");
    }

    private void loadBuiltInAnimations() {
        try {
            Path animationsPath;
            URL resourceUrl = CustomNpcs.class.getResource(BUILTIN_ANIMATIONS_PATH);
            if (resourceUrl == null) {
                LogWriter.info("Built-in animations folder not found: /assets/customnpcs/cnpc_animations");
                return;
            }
            URI uri = resourceUrl.toURI();
            if (uri.getScheme().equals("jar")) {
                FileSystem fileSystem = null;
                try {
                    fileSystem = FileSystems.getFileSystem(uri);
                }
                catch (Exception e) {
                    fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
                }
                animationsPath = fileSystem.getPath(BUILTIN_ANIMATIONS_PATH, new String[0]);
            } else {
                animationsPath = Paths.get(uri);
            }
            try (Stream<Path> paths = Files.walk(animationsPath, 1, new FileVisitOption[0]);){
                paths.filter(path -> path.toString().endsWith(".json")).forEach(path -> {
                    String fileName = path.getFileName().toString();
                    String animName = fileName.substring(0, fileName.length() - 5);
                    try {
                        this.loadBuiltInAnimation(animName);
                    }
                    catch (Exception e) {
                        LogWriter.error("Error loading built-in animation: " + animName, e);
                    }
                });
            }
            LogWriter.info("Loaded " + this.builtInAnimations.size() + " built-in animations.");
        }
        catch (Exception e) {
            LogWriter.error("Error scanning built-in animations folder", e);
        }
    }

    public void loadBuiltInAnimation(Class<?> modClass, String path, String animName) throws Exception {
        String resourcePath = path + "/" + animName + ".json";
        try (InputStream stream = modClass.getResourceAsStream(resourcePath);){
            String line;
            if (stream == null) {
                LogWriter.error("Animation not found: " + animName.toLowerCase());
                return;
            }
            StringBuilder content = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();
            NBTTagCompound nbt = NBTJsonUtil.Convert(content.toString());
            BuiltInAnimation animation = new BuiltInAnimation(animName);
            animation.readFromNBT(nbt);
            this.builtInAnimations.put(animName.toLowerCase(), animation);
            LogWriter.info("Registered animation: " + animName.toLowerCase());
        }
    }

    private void loadBuiltInAnimation(String animName) throws Exception {
        this.loadBuiltInAnimation(CustomNpcs.class, BUILTIN_ANIMATIONS_PATH, animName);
    }

    private void loadAnimations() {
        this.animations.clear();
        File dir = this.getDir();
        if (!dir.exists()) {
            dir.mkdir();
        }
        this.categoryManager.loadCategories(dir);
        this.loadAnimationsFromDir(dir, 0);
        for (Map.Entry<Integer, Category> entry : this.categoryManager.getCategories().entrySet()) {
            File catDir = this.categoryManager.getCategoryDir(entry.getKey());
            this.loadAnimationsFromDir(catDir, entry.getKey());
        }
        this.saveAnimationMap();
    }

    private void loadAnimationsFromDir(File dir, int catId) {
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (!file.isFile() || !file.getName().endsWith(".json")) continue;
            try {
                Animation animation = new Animation();
                animation.readFromNBT(NBTJsonUtil.LoadFile(file));
                animation.name = file.getName().substring(0, file.getName().length() - 5);
                if (animation.id == -1) {
                    animation.id = this.getUnusedId();
                }
                int originalID = animation.id;
                int setID = animation.id;
                while (!(!this.bootOrder.containsKey(setID) && !this.animations.containsKey(setID) || this.bootOrder.containsKey(setID) && this.bootOrder.get(setID).equals(animation.name))) {
                    ++setID;
                }
                animation.id = setID;
                if (originalID != setID) {
                    LogWriter.info("Found Animation ID Mismatch: " + animation.name + ", New ID: " + setID);
                    animation.save();
                }
                this.animations.put(animation.id, animation);
                this.categoryManager.registerItem(animation.id, catId);
            }
            catch (Exception e) {
                LogWriter.error("Error loading: " + file.getAbsolutePath(), e);
            }
        }
    }

    public void addAnimationRegister(String namespace, Register.Animations register) {
        this.registeredAnimations.put(namespace, register);
    }

    private void loadRegisteredBuiltIns() {
        for (Register.Animations register : this.registeredAnimations.values()) {
            register.register();
        }
    }

    public void loadClientBuiltIns() {
        this.loadBuiltInAnimations();
        this.loadRegisteredBuiltIns();
    }

    private File getDir() {
        return new File(CustomNpcs.getWorldSaveDirectory(), "animations");
    }

    public int getUnusedId() {
        if (this.lastUsedID == 0) {
            for (int catid : this.animations.keySet()) {
                if (catid <= this.lastUsedID) continue;
                this.lastUsedID = catid;
            }
        }
        ++this.lastUsedID;
        return this.lastUsedID;
    }

    @Override
    public IAnimation saveAnimation(IAnimation animation) {
        if (animation instanceof BuiltInAnimation) {
            LogWriter.info("Cannot save built-in animation: " + animation.getName());
            return animation;
        }
        if (animation.getID() < 0) {
            animation.setID(this.getUnusedId());
            while (this.hasName(animation.getName())) {
                animation.setName(animation.getName() + "_");
            }
        } else {
            Animation existing = this.animations.get(animation.getID());
            if (existing != null && !existing.name.equals(animation.getName())) {
                while (this.hasName(animation.getName())) {
                    animation.setName(animation.getName() + "_");
                }
            }
        }
        this.animations.remove(animation.getID());
        this.animations.put(animation.getID(), (Animation)animation);
        this.saveAnimationMap();
        File dir = this.categoryManager.getItemDir(animation.getID());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, animation.getName() + ".json_new");
        File file2 = new File(dir, animation.getName() + ".json");
        try {
            NBTJsonUtil.SaveFile(file, ((Animation)animation).writeToNBT());
            if (file2.exists()) {
                file2.delete();
            }
            file.renameTo(file2);
        }
        catch (Exception e) {
            LogWriter.except(e);
        }
        return this.animations.get(animation.getID());
    }

    public boolean hasName(String newName) {
        if (newName.trim().isEmpty()) {
            return true;
        }
        for (Animation animation : this.animations.values()) {
            if (!animation.name.equals(newName)) continue;
            return true;
        }
        return false;
    }

    public Animation cloneAnimation(int originalId) {
        Animation original = this.animations.get(originalId);
        if (original == null || original instanceof BuiltInAnimation) {
            return null;
        }
        int originalCatId = this.categoryManager.getItemCategory(originalId);
        Animation clone = new Animation();
        clone.readFromNBT(original.writeToNBT());
        clone.id = this.getUnusedId();
        String name = clone.name;
        while (this.hasName(name)) {
            name = name + "_";
        }
        clone.name = name;
        if (originalCatId > 0) {
            this.categoryManager.registerItem(clone.id, originalCatId);
        }
        this.saveAnimation(clone);
        return clone;
    }

    @Override
    public void delete(String name) {
        Animation delete = this.getAnimationFromName(name);
        if (delete != null) {
            this.delete(delete.getID());
        }
    }

    @Override
    public void delete(int id) {
        if (id < 0 || !this.animations.containsKey(id)) {
            return;
        }
        Animation anim = this.animations.get(id);
        if (anim instanceof BuiltInAnimation) {
            LogWriter.info("Cannot delete built-in animation: " + anim.name);
            return;
        }
        Animation foundAnimation = this.animations.remove(id);
        if (foundAnimation != null && foundAnimation.name != null) {
            File dir = this.categoryManager.getItemDir(id);
            File file = new File(dir, foundAnimation.name + ".json");
            if (file.exists()) {
                file.delete();
            }
            this.categoryManager.removeItem(id);
            this.saveAnimationMap();
        }
    }

    @Override
    public boolean has(String name) {
        return this.getAnimationFromName(name) != null;
    }

    @Override
    public IAnimation get(String name) {
        return this.get(name, false);
    }

    public IAnimation get(String name, boolean builtIn) {
        BuiltInAnimation builtInAnim;
        for (Map.Entry<Integer, Animation> entry : this.animations.entrySet()) {
            if (!entry.getValue().name.equalsIgnoreCase(name)) continue;
            return entry.getValue();
        }
        if (builtIn && (builtInAnim = this.builtInAnimations.get(name.toLowerCase())) != null) {
            return builtInAnim;
        }
        return null;
    }

    @Override
    public IAnimation get(int id) {
        if (id < 0) {
            return null;
        }
        return this.animations.get(id);
    }

    public BuiltInAnimation getBuiltInAnimation(String name) {
        return this.builtInAnimations.get(name.toLowerCase());
    }

    @Override
    public IAnimation[] getAnimations() {
        ArrayList<Animation> animations = new ArrayList<Animation>(this.animations.values());
        return animations.toArray(new IAnimation[0]);
    }

    @Override
    public IAnimation[] getBuiltInAnimations() {
        return this.builtInAnimations.values().toArray(new IAnimation[0]);
    }

    @Override
    public IAnimation[] getAllAnimations() {
        ArrayList<Animation> all = new ArrayList<Animation>();
        all.addAll(this.builtInAnimations.values());
        all.addAll(this.animations.values());
        return all.toArray(new IAnimation[0]);
    }

    @Override
    public boolean isBuiltIn(String name) {
        return this.builtInAnimations.containsKey(name.toLowerCase());
    }

    @Deprecated
    public boolean isBuiltIn(int id) {
        return false;
    }

    public Animation getAnimationFromName(String animation) {
        for (Map.Entry<Integer, Animation> entryAnimation : this.animations.entrySet()) {
            if (!entryAnimation.getValue().name.equalsIgnoreCase(animation)) continue;
            return entryAnimation.getValue();
        }
        BuiltInAnimation builtIn = this.builtInAnimations.get(animation.toLowerCase());
        if (builtIn != null) {
            return builtIn;
        }
        return null;
    }

    @Override
    public String[] getBuiltInAnimationNames() {
        return this.builtInAnimations.keySet().toArray(new String[0]);
    }

    public String[] getNames() {
        String[] names = new String[this.animations.size()];
        int i = 0;
        for (Animation animation : this.animations.values()) {
            names[i] = animation.name.toLowerCase();
            ++i;
        }
        return names;
    }

    public File getMapDir() {
        File dir = CustomNpcs.getWorldSaveDirectory();
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    public void readAnimationMap() {
        this.bootOrder.clear();
        try {
            File file = new File(this.getMapDir(), "animations.dat");
            if (file.exists()) {
                this.loadAnimationMapFile(file);
            }
        }
        catch (Exception e) {
            try {
                File file = new File(this.getMapDir(), "animations.dat_old");
                if (file.exists()) {
                    this.loadAnimationMapFile(file);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public NBTTagCompound writeMapNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagList animationList = new NBTTagList();
        for (Integer key : this.animations.keySet()) {
            Animation animation = this.animations.get(key);
            if (animation.getName().isEmpty()) continue;
            NBTTagCompound animationCompound = new NBTTagCompound();
            animationCompound.func_74778_a("Name", animation.getName());
            animationCompound.func_74768_a("ID", key.intValue());
            animationList.func_74742_a((NBTBase)animationCompound);
        }
        nbt.func_74782_a("Animations", (NBTBase)animationList);
        return nbt;
    }

    public void readMapNBT(NBTTagCompound compound) {
        NBTTagList list = compound.func_150295_c("Animations", 10);
        if (list != null) {
            for (int i = 0; i < list.func_74745_c(); ++i) {
                NBTTagCompound nbttagcompound = list.func_150305_b(i);
                String animationName = nbttagcompound.func_74779_i("Name");
                Integer key = nbttagcompound.func_74762_e("ID");
                this.bootOrder.put(key, animationName);
            }
        }
    }

    private void loadAnimationMapFile(File file) throws IOException {
        DataInputStream var1 = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(file))));
        this.readAnimationMap(var1);
        var1.close();
    }

    public void readAnimationMap(DataInputStream stream) throws IOException {
        NBTTagCompound nbtCompound = CompressedStreamTools.func_74794_a((DataInputStream)stream);
        this.readMapNBT(nbtCompound);
    }

    public void saveAnimationMap() {
        try {
            File saveDir = this.getMapDir();
            File file = new File(saveDir, "animations.dat_new");
            File file1 = new File(saveDir, "animations.dat_old");
            File file2 = new File(saveDir, "animations.dat");
            CompressedStreamTools.func_74799_a((NBTTagCompound)this.writeMapNBT(), (OutputStream)new FileOutputStream(file));
            if (file1.exists()) {
                file1.delete();
            }
            file2.renameTo(file1);
            if (file2.exists()) {
                file2.delete();
            }
            file.renameTo(file2);
            if (file.exists()) {
                file.delete();
            }
        }
        catch (Exception e) {
            LogWriter.except(e);
        }
    }

    public Map<String, Integer> getCategoryScrollData() {
        return this.categoryManager.getCategoryScrollData();
    }

    public void moveItemToCategory(int itemId, int catId) {
        Animation animation = this.animations.get(itemId);
        if (animation == null) {
            return;
        }
        this.categoryManager.moveItem(itemId, animation.name + ".json", catId);
        this.saveAnimationMap();
    }

    public Map<String, Integer> getItemsByCategoryScrollData(int catId) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        List<Integer> itemIds = this.categoryManager.getItemsInCategory(catId, this.animations.keySet());
        for (int itemId : itemIds) {
            Animation animation = this.animations.get(itemId);
            if (animation == null) continue;
            map.put(animation.name, animation.id);
        }
        return map;
    }
}

