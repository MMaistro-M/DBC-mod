/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.world.World
 */
package kamkeel.npcs.controllers;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import kamkeel.npcs.controllers.data.telegraph.Telegraph;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import kamkeel.npcs.network.packets.data.telegraph.TelegraphRemovePacket;
import kamkeel.npcs.network.packets.data.telegraph.TelegraphSpawnPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;
import noppes.npcs.api.ITelegraph;
import noppes.npcs.api.ITelegraphInstance;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.handler.ITelegraphHandler;
import noppes.npcs.scripted.ScriptTelegraph;
import noppes.npcs.scripted.ScriptTelegraphInstance;
import noppes.npcs.util.NBTJsonUtil;

public class TelegraphController
implements ITelegraphHandler {
    public static TelegraphController Instance;
    private HashMap<String, Telegraph> savedTelegraphs = new HashMap();
    private ConcurrentHashMap<String, TelegraphInstance> activeInstances = new ConcurrentHashMap();

    public static void init() {
        Instance = new TelegraphController();
        Instance.load();
    }

    public TelegraphInstance spawn(Telegraph telegraph, World world, double x, double y, double z, float yaw) {
        TelegraphInstance instance = new TelegraphInstance(telegraph, x, y, z, yaw);
        instance.setWorld(world);
        this.activeInstances.put(instance.getInstanceId(), instance);
        TelegraphSpawnPacket.sendToDimension(instance, world.field_73011_w.field_76574_g);
        return instance;
    }

    public TelegraphInstance spawn(Telegraph telegraph, World world, double x, double y, double z) {
        return this.spawn(telegraph, world, x, y, z, 0.0f);
    }

    public TelegraphInstance spawn(Telegraph telegraph, Entity entity) {
        return this.spawn(telegraph, entity, 0.0f);
    }

    public TelegraphInstance spawn(Telegraph telegraph, Entity entity, float yaw) {
        TelegraphInstance instance = new TelegraphInstance(telegraph, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, yaw);
        instance.setWorld(entity.field_70170_p);
        instance.setEntityIdToFollow(entity.func_145782_y());
        this.activeInstances.put(instance.getInstanceId(), instance);
        TelegraphSpawnPacket.sendToTracking(instance, entity);
        return instance;
    }

    public TelegraphInstance spawnToPlayer(Telegraph telegraph, EntityPlayerMP player, double x, double y, double z, float yaw) {
        TelegraphInstance instance = new TelegraphInstance(telegraph, x, y, z, yaw);
        instance.setWorld(player.field_70170_p);
        this.activeInstances.put(instance.getInstanceId(), instance);
        TelegraphSpawnPacket.send(instance, player);
        return instance;
    }

    public TelegraphInstance spawnToAll(Telegraph telegraph, World world, double x, double y, double z, float yaw) {
        TelegraphInstance instance = new TelegraphInstance(telegraph, x, y, z, yaw);
        instance.setWorld(world);
        this.activeInstances.put(instance.getInstanceId(), instance);
        TelegraphSpawnPacket.sendToAll(instance);
        return instance;
    }

    public TelegraphInstance spawnToTracking(Telegraph telegraph, Entity entity, double x, double y, double z, float yaw) {
        TelegraphInstance instance = new TelegraphInstance(telegraph, x, y, z, yaw);
        instance.setWorld(entity.field_70170_p);
        this.activeInstances.put(instance.getInstanceId(), instance);
        TelegraphSpawnPacket.sendToTracking(instance, entity);
        return instance;
    }

    @Override
    public void remove(String instanceId) {
        TelegraphInstance instance = this.activeInstances.remove(instanceId);
        if (instance != null && instance.getWorld() != null) {
            TelegraphRemovePacket.sendToDimension(instanceId, instance.getDimensionId());
        }
    }

    public void remove(TelegraphInstance instance) {
        if (instance != null) {
            this.remove(instance.getInstanceId());
        }
    }

    public void removeFromPlayer(String instanceId, EntityPlayerMP player) {
        this.activeInstances.remove(instanceId);
        TelegraphRemovePacket.send(instanceId, player);
    }

    public TelegraphInstance getActiveInstance(String instanceId) {
        return this.activeInstances.get(instanceId);
    }

    public void saveInternal(String name, Telegraph telegraph) {
        if (name == null || name.isEmpty() || telegraph == null) {
            return;
        }
        telegraph.setId(name);
        this.savedTelegraphs.put(name, telegraph);
        this.saveTelegraphFile(name, telegraph);
    }

    public Telegraph getInternal(String name) {
        return this.savedTelegraphs.get(name);
    }

    @Override
    public void delete(String name) {
        File file;
        Telegraph removed = this.savedTelegraphs.remove(name);
        if (removed != null && (file = new File(this.getDir(), name + ".json")).exists()) {
            file.delete();
        }
    }

    @Override
    public boolean has(String name) {
        return this.savedTelegraphs.containsKey(name);
    }

    @Override
    public String[] getSavedNames() {
        return this.savedTelegraphs.keySet().toArray(new String[0]);
    }

    private File getDir() {
        return new File(CustomNpcs.getWorldSaveDirectory(), "telegraphs");
    }

    public void load() {
        this.savedTelegraphs.clear();
        LogWriter.info("Loading telegraph presets...");
        File dir = this.getDir();
        if (!dir.exists()) {
            dir.mkdirs();
            return;
        }
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (!file.isFile() || !file.getName().endsWith(".json")) continue;
            try {
                Telegraph telegraph = new Telegraph();
                telegraph.readNBT(NBTJsonUtil.LoadFile(file));
                String name = file.getName().substring(0, file.getName().length() - 5);
                telegraph.setId(name);
                this.savedTelegraphs.put(name, telegraph);
            }
            catch (Exception e) {
                LogWriter.error("Error loading telegraph preset: " + file.getName(), e);
            }
        }
        LogWriter.info("Loaded " + this.savedTelegraphs.size() + " telegraph presets.");
    }

    private void saveTelegraphFile(String name, Telegraph telegraph) {
        try {
            File dir = this.getDir();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, name + ".json_new");
            File file2 = new File(dir, name + ".json");
            NBTJsonUtil.SaveFile(file, telegraph.writeNBT());
            if (file2.exists()) {
                file2.delete();
            }
            file.renameTo(file2);
        }
        catch (Exception e) {
            LogWriter.error("Error saving telegraph preset: " + name, e);
        }
    }

    @Override
    public ITelegraph createCircle(float radius) {
        return new ScriptTelegraph(Telegraph.circle(radius));
    }

    @Override
    public ITelegraph createRing(float outerRadius, float innerRadius) {
        return new ScriptTelegraph(Telegraph.ring(outerRadius, innerRadius));
    }

    @Override
    public ITelegraph createLine(float length, float width) {
        return new ScriptTelegraph(Telegraph.line(length, width));
    }

    @Override
    public ITelegraph createCone(float length, float angle) {
        return new ScriptTelegraph(Telegraph.cone(length, angle));
    }

    @Override
    public ITelegraph createSquare(float radius) {
        return new ScriptTelegraph(Telegraph.square(radius));
    }

    @Override
    public ITelegraph createPoint() {
        return new ScriptTelegraph(Telegraph.point());
    }

    @Override
    public ITelegraph create(String type) {
        if (type == null) {
            return null;
        }
        try {
            Telegraph telegraph;
            TelegraphType telegraphType = TelegraphType.valueOf(type.toUpperCase());
            switch (telegraphType) {
                case CIRCLE: {
                    telegraph = Telegraph.circle(3.0f);
                    break;
                }
                case RING: {
                    telegraph = Telegraph.ring(5.0f, 2.0f);
                    break;
                }
                case LINE: {
                    telegraph = Telegraph.line(5.0f, 2.0f);
                    break;
                }
                case CONE: {
                    telegraph = Telegraph.cone(5.0f, 45.0f);
                    break;
                }
                case SQUARE: {
                    telegraph = Telegraph.square(3.0f);
                    break;
                }
                case POINT: {
                    telegraph = Telegraph.point();
                    break;
                }
                default: {
                    telegraph = Telegraph.circle(3.0f);
                }
            }
            return new ScriptTelegraph(telegraph);
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public ITelegraphInstance spawn(ITelegraph telegraph, IWorld world, double x, double y, double z) {
        return this.spawn(telegraph, world, x, y, z, 0.0f);
    }

    @Override
    public ITelegraphInstance spawn(ITelegraph telegraph, IWorld world, double x, double y, double z, float yaw) {
        if (telegraph == null || world == null) {
            return null;
        }
        Telegraph internal = ((ScriptTelegraph)telegraph).getMCTelegraph();
        TelegraphInstance instance = this.spawn(internal, (World)world.getMCWorld(), x, y, z, yaw);
        return new ScriptTelegraphInstance(instance);
    }

    @Override
    public ITelegraphInstance spawn(ITelegraph telegraph, IEntity entity) {
        return this.spawn(telegraph, entity, 0.0f);
    }

    @Override
    public ITelegraphInstance spawn(ITelegraph telegraph, IEntity entity, float yaw) {
        if (telegraph == null || entity == null) {
            return null;
        }
        Telegraph internal = ((ScriptTelegraph)telegraph).getMCTelegraph();
        TelegraphInstance instance = this.spawn(internal, (Entity)entity.getMCEntity(), yaw);
        return new ScriptTelegraphInstance(instance);
    }

    @Override
    public ITelegraph get(String name) {
        Telegraph telegraph = this.savedTelegraphs.get(name);
        return telegraph != null ? new ScriptTelegraph(new Telegraph(telegraph)) : null;
    }

    @Override
    public void save(String name, ITelegraph telegraph) {
        if (name == null || name.isEmpty() || telegraph == null) {
            return;
        }
        Telegraph internal = ((ScriptTelegraph)telegraph).getMCTelegraph();
        this.saveInternal(name, internal);
    }

    @Override
    public void remove(ITelegraphInstance instance) {
        if (instance != null) {
            this.remove(instance.getInstanceId());
        }
    }
}

