/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.boss.EntityDragon
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.roles;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.NBTTags;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobInterface;

public class JobGuard
extends JobInterface {
    public boolean attacksAnimals = false;
    public boolean attackHostileMobs = true;
    public boolean attackCreepers = false;
    public List<String> targets = new ArrayList<String>();
    public boolean specific = false;

    public JobGuard(EntityNPCInterface npc) {
        super(npc);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74757_a("GuardAttackAnimals", this.attacksAnimals);
        nbttagcompound.func_74757_a("GuardAttackMobs", this.attackHostileMobs);
        nbttagcompound.func_74757_a("GuardAttackCreepers", this.attackCreepers);
        nbttagcompound.func_74757_a("GuardSpecific", this.specific);
        nbttagcompound.func_74782_a("GuardTargets", (NBTBase)NBTTags.nbtStringList(this.targets));
        return nbttagcompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        this.attacksAnimals = nbttagcompound.func_74767_n("GuardAttackAnimals");
        this.attackHostileMobs = nbttagcompound.func_74767_n("GuardAttackMobs");
        this.attackCreepers = nbttagcompound.func_74767_n("GuardAttackCreepers");
        this.specific = nbttagcompound.func_74767_n("GuardSpecific");
        this.targets = NBTTags.getStringList(nbttagcompound.func_150295_c("GuardTargets", 10));
    }

    public boolean isEntityApplicable(Entity entity) {
        if (entity instanceof EntityPlayer || entity instanceof EntityNPCInterface) {
            return false;
        }
        if (this.specific && this.targets.contains("entity." + EntityList.func_75621_b((Entity)entity) + ".name")) {
            return true;
        }
        if (entity instanceof EntityAnimal) {
            return this.attacksAnimals && (!(entity instanceof EntityTameable) || ((EntityTameable)entity).func_70902_q() == null);
        }
        if (entity instanceof EntityCreeper) {
            return this.attackCreepers;
        }
        if (entity instanceof IMob || entity instanceof EntityDragon) {
            return this.attackHostileMobs;
        }
        return false;
    }
}

