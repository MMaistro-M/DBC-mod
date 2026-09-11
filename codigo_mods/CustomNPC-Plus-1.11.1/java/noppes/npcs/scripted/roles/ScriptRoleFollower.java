/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 */
package noppes.npcs.scripted.roles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.roles.IRoleFollower;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleFollower;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.roles.ScriptRoleInterface;

public class ScriptRoleFollower
extends ScriptRoleInterface
implements IRoleFollower {
    private RoleFollower role;

    public ScriptRoleFollower(EntityNPCInterface npc) {
        super(npc);
        this.role = (RoleFollower)npc.roleInterface;
    }

    @Override
    public void setOwner(IPlayer player) {
        if (player == null || player.getMCEntity() == null) {
            this.role.setOwner(null);
            return;
        }
        EntityPlayer mcplayer = (EntityPlayer)player.getMCEntity();
        this.role.setOwner(mcplayer);
    }

    @Override
    public IPlayer getOwner() {
        if (this.role.owner == null) {
            return null;
        }
        return (IPlayer)NpcAPI.Instance().getIEntity((Entity)this.role.owner);
    }

    @Override
    public boolean hasOwner() {
        return this.role.owner != null;
    }

    @Override
    public boolean isFollowing() {
        return this.role.isFollowing();
    }

    @Override
    public void setIsFollowing(boolean following) {
        if (this.role.owner != null && this.role.getDaysLeft() > 0) {
            this.role.isFollowing = following;
        }
    }

    @Override
    public int getDaysLeft() {
        return this.role.getDaysLeft();
    }

    @Override
    public void addDaysLeft(int days) {
        this.role.addDays(days);
    }

    @Override
    public boolean getInfiniteDays() {
        return this.role.infiniteDays;
    }

    @Override
    public void setInfiniteDays(boolean infinite) {
        this.role.infiniteDays = infinite;
    }

    @Override
    public boolean getGuiDisabled() {
        return this.role.disableGui;
    }

    @Override
    public void setGuiDisabled(boolean disabled) {
        this.role.disableGui = disabled;
    }

    @Override
    public void setRate(int index, int amount) {
        this.role.setRate(index, amount);
    }

    @Override
    public int getRate(int index) {
        return this.role.getRate(index);
    }

    @Override
    public void setDialogHire(String dialogHire) {
        this.role.setDialogHire(dialogHire);
    }

    @Override
    public String getDialogHire() {
        return this.role.getDialogHire();
    }

    @Override
    public void setDialogFarewell(String dialogFarewell) {
        this.role.setDialogFarewell(dialogFarewell);
    }

    @Override
    public String getDialogFarewell() {
        return this.role.getDialogFarewell();
    }

    @Override
    public int getType() {
        return 2;
    }
}

