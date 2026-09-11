/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package noppes.npcs.scripted.roles;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.INbt;
import noppes.npcs.api.IPos;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.jobs.IJobSpawner;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobSpawner;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.roles.ScriptJobInterface;

public class ScriptJobSpawner
extends ScriptJobInterface
implements IJobSpawner {
    private final JobSpawner job;

    public ScriptJobSpawner(EntityNPCInterface npc) {
        super(npc);
        this.job = (JobSpawner)npc.jobInterface;
    }

    @Override
    public int getType() {
        return 6;
    }

    @Override
    public IEntityLivingBase<?> spawnEntity(int number) {
        EntityLivingBase base = this.job.spawnEntity(number);
        if (base == null) {
            return null;
        }
        return (IEntityLivingBase)NpcAPI.Instance().getIEntity((Entity)base);
    }

    @Override
    public IEntityLivingBase<?> getEntity(int number, int x, int y, int z, IWorld world) {
        NBTTagCompound compound = this.job.getCompound(number);
        if (compound == null || !compound.func_74764_b("id")) {
            return null;
        }
        Entity entity = NoppesUtilServer.getEntityFromNBT(compound, x, y, z, (World)world.getMCWorld());
        if (!(entity instanceof EntityLivingBase)) {
            return null;
        }
        return (IEntityLivingBase)NpcAPI.Instance().getIEntity(entity);
    }

    @Override
    public IEntityLivingBase<?> getEntity(int number, IPos pos, IWorld world) {
        return this.getEntity(number, pos.getX(), pos.getY(), pos.getZ(), world);
    }

    @Override
    public void setEntity(int number, IEntityLivingBase<?> entityLivingBase) {
        INbt nbt = entityLivingBase.getNbtOptional();
        if (nbt == null) {
            return;
        }
        this.job.setJobCompound(number, nbt.getMCNBT());
    }

    @Override
    public void removeAllSpawned() {
        for (EntityLivingBase entity : this.job.spawned) {
            entity.field_70128_L = true;
        }
        this.job.spawned = new ArrayList<EntityLivingBase>();
    }

    @Override
    public IEntityLivingBase<?>[] getNearbySpawned() {
        List<EntityLivingBase> nearbySpawned = this.job.getNearbySpawned();
        ArrayList<IEntityLivingBase> list = new ArrayList<IEntityLivingBase>();
        for (EntityLivingBase entity : nearbySpawned) {
            list.add((IEntityLivingBase)NpcAPI.Instance().getIEntity((Entity)entity));
        }
        return list.toArray(new IEntityLivingBase[0]);
    }

    @Override
    public boolean hasPixelmon() {
        return this.job.hasPixelmon();
    }

    @Override
    public boolean isEmpty() {
        return this.job.isEmpty();
    }

    @Override
    public boolean isOnCooldown(IPlayer<EntityPlayerMP> player) {
        return this.job.isOnCooldown(player.getName());
    }
}

