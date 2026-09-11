/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.roles;

import java.util.List;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobInterface;

public class JobFollower
extends JobInterface {
    public EntityNPCInterface following = null;
    private int ticks = 40;
    private int range = 20;
    public String name = "";

    public JobFollower(EntityNPCInterface npc) {
        super(npc);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74778_a("FollowingEntityName", this.name);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.name = compound.func_74779_i("FollowingEntityName");
    }

    @Override
    public boolean aiShouldExecute() {
        if (this.npc.isAttacking()) {
            return false;
        }
        --this.ticks;
        if (this.ticks > 0) {
            return false;
        }
        this.ticks = 10;
        this.following = null;
        List list = this.npc.field_70170_p.func_72872_a(EntityNPCInterface.class, this.npc.field_70121_D.func_72314_b((double)this.getRange(), (double)this.getRange(), (double)this.getRange()));
        for (EntityNPCInterface entity : list) {
            if (entity == this.npc || entity.isKilled() || !entity.display.name.equalsIgnoreCase(this.name)) continue;
            this.following = entity;
            break;
        }
        return false;
    }

    private int getRange() {
        if (this.range > ConfigMain.NpcNavRange) {
            return ConfigMain.NpcNavRange;
        }
        return this.range;
    }

    public boolean isFollowing() {
        return this.following != null;
    }

    @Override
    public void reset() {
    }

    @Override
    public void resetTask() {
        this.following = null;
    }

    public boolean hasOwner() {
        return !this.name.isEmpty();
    }
}

