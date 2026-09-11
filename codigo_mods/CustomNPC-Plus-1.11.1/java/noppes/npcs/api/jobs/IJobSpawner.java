/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package noppes.npcs.api.jobs;

import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.api.IPos;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.jobs.IJob;

public interface IJobSpawner
extends IJob {
    public IEntityLivingBase<?> spawnEntity(int var1);

    public IEntityLivingBase<?> getEntity(int var1, int var2, int var3, int var4, IWorld var5);

    public IEntityLivingBase<?> getEntity(int var1, IPos var2, IWorld var3);

    public void setEntity(int var1, IEntityLivingBase<?> var2);

    public void removeAllSpawned();

    public IEntityLivingBase<?>[] getNearbySpawned();

    public boolean hasPixelmon();

    public boolean isEmpty();

    public boolean isOnCooldown(IPlayer<EntityPlayerMP> var1);
}

