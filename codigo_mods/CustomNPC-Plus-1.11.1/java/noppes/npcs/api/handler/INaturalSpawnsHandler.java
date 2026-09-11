/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.handler.data.INaturalSpawn;

public interface INaturalSpawnsHandler {
    public void save();

    public INaturalSpawn[] getSpawns();

    public INaturalSpawn[] getSpawns(String var1);

    public void addSpawn(INaturalSpawn var1);

    public void removeSpawn(INaturalSpawn var1);

    public INaturalSpawn createSpawn();
}

