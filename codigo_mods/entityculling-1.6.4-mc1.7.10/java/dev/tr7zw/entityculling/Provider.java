/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 */
package dev.tr7zw.entityculling;

import dev.tr7zw.entityculling.shadow.com.logisticscraft.occlusionculling.DataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;

public class Provider
implements DataProvider {
    private final Minecraft client = Minecraft.func_71410_x();
    private WorldClient world = null;

    @Override
    public boolean prepareChunk(int chunkX, int chunkZ) {
        this.world = this.client.field_71441_e;
        return this.world != null;
    }

    @Override
    public boolean isOpaqueFullCube(int x, int y, int z) {
        return this.world.func_147439_a(x, y, z).func_149662_c();
    }

    @Override
    public void cleanup() {
        this.world = null;
    }
}

