/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.geckolib3.resource;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.file.AnimationFile;
import software.bernie.geckolib3.file.AnimationFileLoader;
import software.bernie.geckolib3.molang.MolangRegistrar;
import software.bernie.geckolib3.network.NetworkHandler;
import software.bernie.geckolib3.network.PacketRemoveAnimation;
import software.bernie.geckolib3.network.PacketSendAnimation;
import software.bernie.geckolib3.watchers.AnimationDirWatcher;

public class AnimationLibrary {
    private HashMap<ResourceLocation, AnimationFile> folderAnimations = new HashMap();
    public File folder;
    public AnimationDirWatcher updateController;
    public static AnimationLibrary instance;

    public AnimationLibrary(File folder) {
        instance = this;
        this.folder = folder;
        this.folder.mkdirs();
        this.updateController = new AnimationDirWatcher(folder);
        this.updateController.start();
    }

    public void reload(boolean sync) {
        this.folderAnimations.clear();
        this.recursiveWalk(this.folder);
        if (sync) {
            this.syncAll();
        }
    }

    public void syncPlayer(EntityPlayer player) {
        for (Map.Entry<ResourceLocation, AnimationFile> entry : this.folderAnimations.entrySet()) {
            NetworkHandler.sendToPlayer(new PacketSendAnimation(entry.getValue(), entry.getKey().func_110623_a()), player);
        }
    }

    public void syncAll() {
        if (MinecraftServer.func_71276_C() != null) {
            for (Map.Entry<ResourceLocation, AnimationFile> entry : this.folderAnimations.entrySet()) {
                NetworkHandler.sendToAll(new PacketSendAnimation(entry.getValue(), entry.getKey().func_110623_a()));
            }
        }
    }

    public void syncAdd(ResourceLocation location) {
        if (MinecraftServer.func_71276_C() != null) {
            NetworkHandler.sendToAll(new PacketSendAnimation(this.folderAnimations.get(location), location.func_110623_a()));
        }
    }

    public void syncRemove(ResourceLocation location) {
        if (MinecraftServer.func_71276_C() != null) {
            NetworkHandler.sendToAll(new PacketRemoveAnimation(location.func_110623_a()));
        }
    }

    public void recursiveWalk(File folder) {
        for (File file : folder.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".json")) {
                this.storeModel(file);
            }
            if (!file.isDirectory()) continue;
            this.recursiveWalk(file);
        }
    }

    public AnimationFile get(ResourceLocation identifier) {
        return this.folderAnimations.get(identifier);
    }

    public void remove(File file) {
        ResourceLocation location = new ResourceLocation("custom", this.getNameFromFile(file));
        this.folderAnimations.remove(location);
        this.syncRemove(location);
    }

    public String getNameFromFile(File file) {
        String folderPath = this.folder.getAbsolutePath();
        return file.getAbsolutePath().substring(folderPath.length() + 1).replace('\\', '/');
    }

    public void storeModel(File file) {
        ResourceLocation location = new ResourceLocation("custom", this.getNameFromFile(file));
        AnimationFile model = this.loadModel(file);
        if (model != null) {
            this.folderAnimations.put(location, model);
            this.syncAdd(location);
        }
    }

    public AnimationFile loadModel(File file) {
        ResourceLocation location = new ResourceLocation("custom", this.getNameFromFile(file));
        try {
            return AnimationFileLoader.getInstance().loadAllAnimations(MolangRegistrar.getParser(), file, location);
        }
        catch (Exception e) {
            return null;
        }
    }
}

