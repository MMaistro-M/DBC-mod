/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package kamkeel.npcs.controllers.data.telegraph;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import kamkeel.npcs.controllers.data.telegraph.Telegraph;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import net.minecraft.world.World;

public class TelegraphManager {
    public static TelegraphManager ClientInstance;
    private final ConcurrentHashMap<String, TelegraphInstance> telegraphs = new ConcurrentHashMap();

    public static void initClient() {
        ClientInstance = new TelegraphManager();
    }

    public void addTelegraph(TelegraphInstance instance) {
        this.telegraphs.put(instance.getInstanceId(), instance);
    }

    public void removeTelegraph(String instanceId) {
        this.telegraphs.remove(instanceId);
    }

    public TelegraphInstance getTelegraph(String instanceId) {
        return this.telegraphs.get(instanceId);
    }

    public Collection<TelegraphInstance> getTelegraphs() {
        return this.telegraphs.values();
    }

    public void clear() {
        this.telegraphs.clear();
    }

    public boolean hasTelegraphs() {
        return !this.telegraphs.isEmpty();
    }

    public void tick(World world) {
        Iterator<TelegraphInstance> iterator = this.telegraphs.values().iterator();
        while (iterator.hasNext()) {
            TelegraphInstance instance = iterator.next();
            if (instance.tick(world)) continue;
            iterator.remove();
        }
    }

    public TelegraphInstance spawnCircle(double x, double y, double z, float radius, int durationTicks, int color) {
        Telegraph telegraph = Telegraph.circle(radius);
        telegraph.setDurationTicks(durationTicks);
        telegraph.setColor(color);
        telegraph.setWarningColor(color & 0xFFFFFF | 0xC0000000);
        TelegraphInstance instance = new TelegraphInstance(telegraph, x, y, z, 0.0f);
        this.addTelegraph(instance);
        return instance;
    }

    public TelegraphInstance spawnLine(double x, double y, double z, float yaw, float length, float width, int durationTicks, int color) {
        Telegraph telegraph = Telegraph.line(length, width);
        telegraph.setDurationTicks(durationTicks);
        telegraph.setColor(color);
        telegraph.setWarningColor(color & 0xFFFFFF | 0xC0000000);
        TelegraphInstance instance = new TelegraphInstance(telegraph, x, y, z, yaw);
        this.addTelegraph(instance);
        return instance;
    }

    public TelegraphInstance spawnCone(double x, double y, double z, float yaw, float length, float angle, int durationTicks, int color) {
        Telegraph telegraph = Telegraph.cone(length, angle);
        telegraph.setDurationTicks(durationTicks);
        telegraph.setColor(color);
        telegraph.setWarningColor(color & 0xFFFFFF | 0xC0000000);
        TelegraphInstance instance = new TelegraphInstance(telegraph, x, y, z, yaw);
        this.addTelegraph(instance);
        return instance;
    }
}

