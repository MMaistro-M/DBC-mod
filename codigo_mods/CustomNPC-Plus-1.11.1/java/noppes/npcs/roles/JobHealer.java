/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.roles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobInterface;

public class JobHealer
extends JobInterface {
    private long healTicks = 0L;
    public int range = 5;
    public int speed = 5;
    private List<EntityLivingBase> toHeal = new ArrayList<EntityLivingBase>();

    public JobHealer(EntityNPCInterface npc) {
        super(npc);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74768_a("HealerRange", this.range);
        nbttagcompound.func_74768_a("HealerSpeed", this.speed);
        return nbttagcompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        this.range = nbttagcompound.func_74762_e("HealerRange");
        this.speed = nbttagcompound.func_74762_e("HealerSpeed");
    }

    @Override
    public boolean aiShouldExecute() {
        ++this.healTicks;
        if (this.healTicks < (long)(this.speed * 10)) {
            return false;
        }
        for (Object plObj : this.npc.field_70170_p.func_72872_a(EntityLivingBase.class, this.npc.field_70121_D.func_72314_b((double)this.range, (double)this.range / 2.0, (double)this.range))) {
            EntityNPCInterface npc;
            EntityPlayer player;
            EntityLivingBase entity = (EntityLivingBase)plObj;
            if (entity instanceof EntityPlayer && (player = (EntityPlayer)entity).func_110143_aJ() < player.func_110138_aP() && !this.npc.faction.isAggressiveToPlayer(player)) {
                this.toHeal.add((EntityLivingBase)player);
            }
            if (!(entity instanceof EntityNPCInterface) || !((npc = (EntityNPCInterface)entity).func_110143_aJ() < npc.func_110138_aP()) || this.npc.faction.isAggressiveToNpc(npc)) continue;
            this.toHeal.add((EntityLivingBase)npc);
        }
        this.healTicks = 0L;
        return !this.toHeal.isEmpty();
    }

    @Override
    public void aiStartExecuting() {
        Iterator<EntityLivingBase> iterator = this.toHeal.iterator();
        while (iterator.hasNext()) {
            EntityLivingBase entity;
            float heal = (entity = iterator.next()).func_110138_aP() / 20.0f;
            this.heal(entity, heal > 0.0f ? heal : 1.0f);
        }
        this.toHeal.clear();
    }

    public void heal(EntityLivingBase entity, float amount) {
        entity.func_70691_i(amount);
        NoppesUtilServer.spawnParticle((Entity)entity, "heal", entity.field_71093_bK);
    }
}

