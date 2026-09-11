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
import software.bernie.geckolib3.file.GeoModelLoader;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.network.NetworkHandler;
import software.bernie.geckolib3.network.PacketRemoveModel;
import software.bernie.geckolib3.network.PacketSendModel;
import software.bernie.geckolib3.watchers.ModelDirWatcher;

public class ModelLibrary {
    private HashMap<ResourceLocation, GeoModel> folderModels = new HashMap();
    public File folder;
    public ModelDirWatcher updateController;
    public static ModelLibrary instance;

    public ModelLibrary(File folder) {
        instance = this;
        this.folder = folder;
        this.folder.mkdirs();
        this.updateController = new ModelDirWatcher(folder);
        this.updateController.start();
    }

    public void reload(boolean sync) {
        this.folderModels.clear();
        this.recursiveWalk(this.folder);
        if (sync) {
            this.syncAll();
        }
    }

    public void syncPlayer(EntityPlayer player) {
        for (Map.Entry<ResourceLocation, GeoModel> entry : this.folderModels.entrySet()) {
            NetworkHandler.sendToPlayer(new PacketSendModel(entry.getValue(), entry.getKey().func_110623_a()), player);
        }
    }

    public void syncAll() {
        if (MinecraftServer.func_71276_C() != null) {
            for (Map.Entry<ResourceLocation, GeoModel> entry : this.folderModels.entrySet()) {
                NetworkHandler.sendToAll(new PacketSendModel(entry.getValue(), entry.getKey().func_110623_a()));
            }
        }
    }

    public void syncAdd(ResourceLocation location) {
        if (MinecraftServer.func_71276_C() != null) {
            NetworkHandler.sendToAll(new PacketSendModel(this.folderModels.get(location), location.func_110623_a()));
        }
    }

    public void syncRemove(ResourceLocation location) {
        if (MinecraftServer.func_71276_C() != null) {
            NetworkHandler.sendToAll(new PacketRemoveModel(location.func_110623_a()));
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

    public GeoModel get(ResourceLocation identifier) {
        return this.folderModels.get(identifier);
    }

    public void remove(File file) {
        ResourceLocation location = new ResourceLocation("custom", this.getNameFromFile(file));
        this.folderModels.remove(location);
        this.syncRemove(location);
    }

    public String getNameFromFile(File file) {
        String folderPath = this.folder.getAbsolutePath();
        return file.getAbsolutePath().substring(folderPath.length() + 1).replace('\\', '/');
    }

    public void storeModel(File file) {
        ResourceLocation location = new ResourceLocation("custom", this.getNameFromFile(file));
        GeoModel model = this.loadModel(file);
        if (model != null) {
            this.folderModels.put(location, model);
            this.syncAdd(location);
        }
    }

    public GeoModel loadModel(File file) {
        ResourceLocation location = new ResourceLocation("custom", this.getNameFromFile(file));
        try {
            return GeoModelLoader.getInstance().loadModelFromFile(file, location);
        }
        catch (Exception e) {
            return null;
        }
    }
}

