/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package noppes.npcs.scripted.roles;

import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.IAvailability;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.api.jobs.IJobItemGiver;
import noppes.npcs.controllers.data.Availability;
import noppes.npcs.controllers.data.PlayerItemGiverData;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobItemGiver;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.roles.ScriptJobInterface;

public class ScriptJobItemGiver
extends ScriptJobInterface
implements IJobItemGiver {
    private JobItemGiver job;

    public ScriptJobItemGiver(JobItemGiver job) {
        super(job);
        this.job = job;
    }

    public ScriptJobItemGiver(EntityNPCInterface npc) {
        super(npc);
        this.job = (JobItemGiver)npc.jobInterface;
    }

    @Override
    public int getType() {
        return 5;
    }

    @Override
    public void setCooldown(int cooldown) {
        if (cooldown < 0) {
            cooldown = 0;
        }
        this.job.cooldown = cooldown;
    }

    @Override
    public void setCooldownType(int type) {
        if (type < 0) {
            type = 0;
        } else if (type > 2) {
            type = 2;
        }
        this.job.cooldownType = type;
    }

    @Override
    public int getCooldownType() {
        return this.job.cooldownType;
    }

    @Override
    public void setGivingMethod(int method) {
        if (method < 0) {
            method = 0;
        } else if (method > 4) {
            method = 4;
        }
        this.job.givingMethod = method;
    }

    @Override
    public int getGivingMethod() {
        return this.job.givingMethod;
    }

    @Override
    public void setLines(String[] lines) {
        this.job.lines = new ArrayList<String>(Arrays.asList(lines));
    }

    @Override
    public String[] getLines() {
        return this.job.lines.toArray(new String[0]);
    }

    @Override
    public void setAvailability(IAvailability availability) {
        this.job.availability = (Availability)availability;
    }

    @Override
    public IAvailability getAvailability() {
        return this.job.availability;
    }

    @Override
    public void setItem(int slot, IItemStack item) {
        if (slot < 0) {
            slot = 0;
        } else if (slot > 8) {
            slot = 8;
        }
        this.job.inventory.items.put(slot, item.getMCItemStack());
    }

    @Override
    public IItemStack[] getItems() {
        ArrayList<IItemStack> items = new ArrayList<IItemStack>();
        for (int i = 0; i < this.job.inventory.func_70302_i_(); ++i) {
            items.add(NpcAPI.Instance().getIItemStack(this.job.inventory.func_70301_a(i)));
        }
        return items.toArray(new IItemStack[0]);
    }

    @Override
    public boolean giveItems(IPlayer player) {
        return this.job.giveItems((EntityPlayer)player.getMCEntity());
    }

    @Override
    public boolean canPlayerInteract(IPlayer player) {
        return this.job.canPlayerInteract((PlayerItemGiverData)player.getData().getItemGiverData());
    }
}

