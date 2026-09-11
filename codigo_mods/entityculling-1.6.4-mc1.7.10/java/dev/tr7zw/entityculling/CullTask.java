/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.Vec3
 */
package dev.tr7zw.entityculling;

import dev.tr7zw.entityculling.EntityCullingModBase;
import dev.tr7zw.entityculling.access.Cullable;
import dev.tr7zw.entityculling.shadow.com.logisticscraft.occlusionculling.OcclusionCullingInstance;
import dev.tr7zw.entityculling.shadow.com.logisticscraft.occlusionculling.util.Vec3d;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class CullTask
implements Runnable {
    public boolean requestCull = false;
    private final OcclusionCullingInstance culling;
    private final Minecraft client = Minecraft.func_71410_x();
    private final int sleepDelay;
    private final int hitboxLimit;
    private final Set<String> unCullable;
    public long lastTime;
    private Vec3d lastPos;
    private Vec3d aabbMin;
    private Vec3d aabbMax;

    public CullTask(OcclusionCullingInstance culling, Set<String> unCullable) {
        this.sleepDelay = EntityCullingModBase.instance.config.sleepDelay;
        this.hitboxLimit = EntityCullingModBase.instance.config.hitboxLimit;
        this.lastTime = 0L;
        this.lastPos = new Vec3d(0.0, 0.0, 0.0);
        this.aabbMin = new Vec3d(0.0, 0.0, 0.0);
        this.aabbMax = new Vec3d(0.0, 0.0, 0.0);
        this.culling = culling;
        this.unCullable = unCullable;
    }

    @Override
    public void run() {
        while (this.client != null) {
            try {
                Thread.sleep(this.sleepDelay);
                if (!EntityCullingModBase.enabled || this.client.field_71441_e == null || this.client.field_71439_g == null || this.client.field_71439_g.field_70173_aa <= 10 || this.client.field_71451_h == null) continue;
                Vec3 cameraMC = null;
                cameraMC = EntityCullingModBase.instance.config.debugMode ? this.client.field_71439_g.func_70666_h(0.0f).func_72441_c(0.0, (double)this.client.field_71439_g.func_70047_e(), 0.0) : this.getCameraPos();
                if (!this.requestCull && cameraMC.field_72450_a == this.lastPos.x && cameraMC.field_72448_b == this.lastPos.y && cameraMC.field_72449_c == this.lastPos.z) continue;
                long start = System.currentTimeMillis();
                this.requestCull = false;
                this.lastPos.set(cameraMC.field_72450_a, cameraMC.field_72448_b, cameraMC.field_72449_c);
                Vec3d camera = this.lastPos;
                this.culling.resetCache();
                boolean noCulling = this.client.field_71474_y.field_74320_O != 0;
                Iterator iterator = this.client.field_71441_e.field_147482_g.iterator();
                while (iterator.hasNext()) {
                    Cullable cullable;
                    TileEntity entry;
                    try {
                        entry = (TileEntity)iterator.next();
                    }
                    catch (NullPointerException | ConcurrentModificationException ex) {
                        break;
                    }
                    if (this.unCullable.contains(entry.func_145838_q().func_149739_a()) || (cullable = (Cullable)entry).isForcedVisible()) continue;
                    if (noCulling) {
                        cullable.setCulled(false);
                        continue;
                    }
                    if (!(this.distanceSq(entry.field_145851_c, entry.field_145848_d, entry.field_145849_e, cameraMC.field_72450_a, cameraMC.field_72448_b, cameraMC.field_72449_c) < 4096.0)) continue;
                    this.aabbMin.set(entry.field_145851_c, entry.field_145848_d, entry.field_145849_e);
                    this.aabbMax.set(entry.field_145851_c + 1, entry.field_145848_d + 1, entry.field_145849_e + 1);
                    boolean visible = this.culling.isAABBVisible(this.aabbMin, this.aabbMax, camera);
                    cullable.setCulled(!visible);
                }
                Entity entity = null;
                Iterator iterable = this.client.field_71441_e.func_72910_y().iterator();
                while (iterable.hasNext()) {
                    Cullable cullable;
                    try {
                        entity = (Entity)iterable.next();
                    }
                    catch (NullPointerException | ConcurrentModificationException ex) {
                        break;
                    }
                    if (entity == null || !(entity instanceof Cullable) || (cullable = (Cullable)entity).isForcedVisible()) continue;
                    if (noCulling) {
                        cullable.setCulled(false);
                        continue;
                    }
                    if (this.distanceSq(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, cameraMC.field_72450_a, cameraMC.field_72448_b, cameraMC.field_72449_c) > (double)(EntityCullingModBase.instance.config.tracingDistance * EntityCullingModBase.instance.config.tracingDistance)) {
                        cullable.setCulled(false);
                        continue;
                    }
                    AxisAlignedBB boundingBox = entity.field_70121_D;
                    if (boundingBox.field_72336_d - boundingBox.field_72340_a > (double)this.hitboxLimit || boundingBox.field_72337_e - boundingBox.field_72338_b > (double)this.hitboxLimit || boundingBox.field_72334_f - boundingBox.field_72339_c > (double)this.hitboxLimit) {
                        cullable.setCulled(false);
                        continue;
                    }
                    this.aabbMin.set(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72339_c);
                    this.aabbMax.set(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72334_f);
                    boolean visible = this.culling.isAABBVisible(this.aabbMin, this.aabbMax, camera);
                    cullable.setCulled(!visible);
                }
                this.lastTime = System.currentTimeMillis() - start;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Shutting down culling task!");
    }

    private Vec3 getCameraPos() {
        if (this.client.field_71474_y.field_74320_O == 0) {
            return this.client.field_71451_h.func_70666_h(0.0f).func_72441_c(0.0, (double)this.client.field_71439_g.func_70047_e(), 0.0);
        }
        return this.client.field_71451_h.func_70666_h(0.0f).func_72441_c(0.0, (double)this.client.field_71439_g.func_70047_e(), 0.0);
    }

    private double distanceSq(double x1, double y1, double z1, double x2, double y2, double z2) {
        double d3 = x1 - x2;
        double d4 = y1 - y2;
        double d5 = z1 - z2;
        return d3 * d3 + d4 * d4 + d5 * d5;
    }
}

