/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package kamkeel.npcs.controllers;

import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.controllers.data.energy.IEnergyExtender;
import kamkeel.npcs.entity.EntityAbilityBeam;
import kamkeel.npcs.entity.EntityAbilityDisc;
import kamkeel.npcs.entity.EntityAbilityLaser;
import kamkeel.npcs.entity.EntityAbilityOrb;
import kamkeel.npcs.entity.EntityAbilityZone;
import kamkeel.npcs.entity.EntityEnergyExplosion;
import kamkeel.npcs.entity.EntityEnergyPanel;
import kamkeel.npcs.entity.EntityEnergySweeper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import noppes.npcs.api.IEnergyHandler;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEnergyBeam;
import noppes.npcs.api.entity.IEnergyDisc;
import noppes.npcs.api.entity.IEnergyExplosion;
import noppes.npcs.api.entity.IEnergyLaser;
import noppes.npcs.api.entity.IEnergyOrb;
import noppes.npcs.api.entity.IEnergyPanel;
import noppes.npcs.api.entity.IEnergySweeper;
import noppes.npcs.api.entity.IEnergyZone;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.entity.ScriptEnergyExplosion;

public class EnergyController
implements IEnergyHandler {
    public static EnergyController Instance = new EnergyController();
    private final List<IEnergyExtender> extenders = new ArrayList<IEnergyExtender>();

    public void registerExtender(IEnergyExtender handler) {
        this.extenders.add(handler);
    }

    public List<IEnergyExtender> getExtenders() {
        return this.extenders;
    }

    public boolean fireOnEnergyDamage(Entity energyEntity, EntityLivingBase owner, EntityLivingBase target, float damage, float knockback, float knockbackUp, double kbDirX, double kbDirZ, float damageMultiplier, NBTTagCompound damageData) {
        for (IEnergyExtender handler : this.extenders) {
            if (!handler.onEnergyDamage(energyEntity, owner, target, damage, knockback, knockbackUp, kbDirX, kbDirZ, damageMultiplier, damageData)) continue;
            return true;
        }
        return false;
    }

    public float fireModifyEnergyDamage(Entity energyEntity, EntityLivingBase owner, float baseDamage, NBTTagCompound damageData) {
        float damage = baseDamage;
        for (IEnergyExtender handler : this.extenders) {
            damage = handler.modifyEnergyDamage(energyEntity, owner, damage, damageData);
        }
        return damage;
    }

    public EntityAbilityOrb createOrbInternal(World world, Entity owner, double x, double y, double z, float size) {
        EntityAbilityOrb entity = new EntityAbilityOrb(world);
        entity.func_70107_b(x, y, z);
        entity.setStartPosition(x, y, z);
        entity.setOwnerEntityId(owner.func_145782_y());
        entity.setProjectileSize(size);
        return entity;
    }

    public EntityAbilityBeam createBeamInternal(World world, Entity owner, double x, double y, double z, float beamWidth, float headSize) {
        EntityAbilityBeam entity = new EntityAbilityBeam(world);
        entity.func_70107_b(x, y, z);
        entity.setStartPosition(x, y, z);
        entity.setOwnerEntityId(owner.func_145782_y());
        entity.setBeamWidth(beamWidth);
        entity.setHeadSize(headSize);
        return entity;
    }

    public EntityAbilityDisc createDiscInternal(World world, Entity owner, double x, double y, double z, float radius, float thickness) {
        EntityAbilityDisc entity = new EntityAbilityDisc(world);
        entity.func_70107_b(x, y, z);
        entity.setStartPosition(x, y, z);
        entity.setOwnerEntityId(owner.func_145782_y());
        entity.setDiscRadius(radius);
        entity.setDiscThickness(thickness);
        return entity;
    }

    public EntityAbilityLaser createLaserInternal(World world, Entity owner, double x, double y, double z, float laserWidth) {
        EntityAbilityLaser entity = new EntityAbilityLaser(world);
        entity.func_70107_b(x, y, z);
        entity.setStartPosition(x, y, z);
        entity.setOwnerEntityId(owner.func_145782_y());
        entity.setLaserWidth(laserWidth);
        return entity;
    }

    public EntityAbilityZone createHazardInternal(World world, Entity owner, double x, double y, double z) {
        EntityAbilityZone entity = new EntityAbilityZone(world);
        entity.initAsHazard(owner, x, y, z);
        return entity;
    }

    public EntityAbilityZone createTrapInternal(World world, Entity owner, double x, double y, double z) {
        EntityAbilityZone entity = new EntityAbilityZone(world);
        entity.initAsTrap(owner, x, y, z);
        return entity;
    }

    public EntityEnergySweeper createSweeperInternal(World world, Entity owner, double x, double y, double z) {
        EntityEnergySweeper entity = new EntityEnergySweeper(world);
        entity.func_70107_b(x, y, z);
        entity.setOwnerEntityId(owner.func_145782_y());
        return entity;
    }

    public EntityEnergyPanel createPanelInternal(World world, Entity owner, double x, double y, double z) {
        EntityEnergyPanel entity = new EntityEnergyPanel(world);
        entity.func_70107_b(x, y, z);
        entity.setOwnerEntityId(owner.func_145782_y());
        return entity;
    }

    public EntityEnergyExplosion createExplosionInternal(World world, Entity owner, double x, double y, double z, float radius) {
        return new EntityEnergyExplosion(world, owner, x, y, z, radius);
    }

    @Override
    public IEnergyOrb createOrb(IWorld world, IEntity owner, double x, double y, double z, float size) {
        EntityAbilityOrb entity = this.createOrbInternal((World)world.getMCWorld(), (Entity)owner.getMCEntity(), x, y, z, size);
        return (IEnergyOrb)NpcAPI.Instance().getIEntity(entity);
    }

    @Override
    public IEnergyBeam createBeam(IWorld world, IEntity owner, double x, double y, double z, float beamWidth, float headSize) {
        EntityAbilityBeam entity = this.createBeamInternal((World)world.getMCWorld(), (Entity)owner.getMCEntity(), x, y, z, beamWidth, headSize);
        return (IEnergyBeam)NpcAPI.Instance().getIEntity(entity);
    }

    @Override
    public IEnergyDisc createDisc(IWorld world, IEntity owner, double x, double y, double z, float radius, float thickness) {
        EntityAbilityDisc entity = this.createDiscInternal((World)world.getMCWorld(), (Entity)owner.getMCEntity(), x, y, z, radius, thickness);
        return (IEnergyDisc)NpcAPI.Instance().getIEntity(entity);
    }

    @Override
    public IEnergyLaser createLaser(IWorld world, IEntity owner, double x, double y, double z, float laserWidth) {
        EntityAbilityLaser entity = this.createLaserInternal((World)world.getMCWorld(), (Entity)owner.getMCEntity(), x, y, z, laserWidth);
        return (IEnergyLaser)NpcAPI.Instance().getIEntity(entity);
    }

    @Override
    public IEnergyZone createHazard(IWorld world, IEntity owner, double x, double y, double z) {
        EntityAbilityZone entity = this.createHazardInternal((World)world.getMCWorld(), (Entity)owner.getMCEntity(), x, y, z);
        return (IEnergyZone)NpcAPI.Instance().getIEntity(entity);
    }

    @Override
    public IEnergyZone createTrap(IWorld world, IEntity owner, double x, double y, double z) {
        EntityAbilityZone entity = this.createTrapInternal((World)world.getMCWorld(), (Entity)owner.getMCEntity(), x, y, z);
        return (IEnergyZone)NpcAPI.Instance().getIEntity(entity);
    }

    @Override
    public IEnergySweeper createSweeper(IWorld world, IEntity owner, double x, double y, double z) {
        EntityEnergySweeper entity = this.createSweeperInternal((World)world.getMCWorld(), (Entity)owner.getMCEntity(), x, y, z);
        return (IEnergySweeper)NpcAPI.Instance().getIEntity(entity);
    }

    @Override
    public IEnergyPanel createPanel(IWorld world, IEntity owner, double x, double y, double z) {
        EntityEnergyPanel entity = this.createPanelInternal((World)world.getMCWorld(), (Entity)owner.getMCEntity(), x, y, z);
        return (IEnergyPanel)NpcAPI.Instance().getIEntity(entity);
    }

    @Override
    public IEnergyExplosion createExplosion(IWorld world, IEntity owner, double x, double y, double z, float radius) {
        EntityEnergyExplosion entity = this.createExplosionInternal((World)world.getMCWorld(), (Entity)owner.getMCEntity(), x, y, z, radius);
        return new ScriptEnergyExplosion<EntityEnergyExplosion>(entity);
    }
}

