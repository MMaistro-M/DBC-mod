/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package kamkeel.npcs.controllers.data.energycharge;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import net.minecraft.world.World;

public class EnergyChargePreviewManager {
    private static final int MAX_PREVIEW_AGE = 1200;
    public static EnergyChargePreviewManager ClientInstance;
    private final ConcurrentHashMap<String, EntityEnergyProjectile> previews = new ConcurrentHashMap();

    public static void initClient() {
        ClientInstance = new EnergyChargePreviewManager();
    }

    public void addPreview(String instanceId, EntityEnergyProjectile entity) {
        if (instanceId == null || entity == null) {
            return;
        }
        this.previews.put(instanceId, entity);
    }

    public void removePreview(String instanceId) {
        if (instanceId == null) {
            return;
        }
        this.previews.remove(instanceId);
    }

    public Collection<EntityEnergyProjectile> getPreviews() {
        return this.previews.values();
    }

    public boolean hasPreviews() {
        return !this.previews.isEmpty();
    }

    public void clear() {
        this.previews.clear();
    }

    public void tick(World world) {
        if (world == null) {
            this.clear();
            return;
        }
        Iterator<EntityEnergyProjectile> iterator = this.previews.values().iterator();
        while (iterator.hasNext()) {
            EntityEnergyProjectile entity = iterator.next();
            if (entity == null || entity.field_70128_L || entity.field_70170_p != world) {
                iterator.remove();
                continue;
            }
            if (entity.field_70173_aa > 1200) {
                entity.func_70106_y();
                iterator.remove();
                continue;
            }
            entity.func_70071_h_();
            if (!entity.field_70128_L) continue;
            iterator.remove();
        }
    }
}

