/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import java.util.ArrayList;
import java.util.HashSet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.NBTTags;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.IPlayerFactionData;
import noppes.npcs.api.handler.data.IFaction;
import noppes.npcs.controllers.FactionController;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerFactionData;
import noppes.npcs.entity.EntityNPCInterface;

public class Faction
implements IFaction {
    public String name = "";
    public int color = Integer.parseInt("FF00", 16);
    public HashSet<Integer> attackFactions;
    public int id = -1;
    public int neutralPoints = 500;
    public int friendlyPoints = 1500;
    public int defaultPoints = 1000;
    public boolean hideFaction = false;
    public boolean getsAttacked = false;
    public boolean isPassive = false;

    public Faction() {
        this.attackFactions = new HashSet();
    }

    public Faction(int id, String name, int color, int defaultPoints) {
        this.name = name;
        this.color = color;
        this.defaultPoints = defaultPoints;
        this.id = id;
        this.attackFactions = new HashSet();
    }

    public static String formatName(String name) {
        name = name.toLowerCase().trim();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public void readNBT(NBTTagCompound compound) {
        this.name = compound.func_74779_i("Name");
        this.color = compound.func_74762_e("Color");
        this.id = compound.func_74762_e("Slot");
        this.neutralPoints = compound.func_74762_e("NeutralPoints");
        this.friendlyPoints = compound.func_74762_e("FriendlyPoints");
        this.defaultPoints = compound.func_74762_e("DefaultPoints");
        this.hideFaction = compound.func_74767_n("HideFaction");
        this.getsAttacked = compound.func_74767_n("GetsAttacked");
        this.isPassive = compound.func_74767_n("IsPassive");
        this.attackFactions = NBTTags.getIntegerSet(compound.func_150295_c("AttackFactions", 10));
    }

    public void writeNBT(NBTTagCompound compound) {
        compound.func_74768_a("Slot", this.id);
        compound.func_74778_a("Name", this.name);
        compound.func_74768_a("Color", this.color);
        compound.func_74768_a("NeutralPoints", this.neutralPoints);
        compound.func_74768_a("FriendlyPoints", this.friendlyPoints);
        compound.func_74768_a("DefaultPoints", this.defaultPoints);
        compound.func_74757_a("HideFaction", this.hideFaction);
        compound.func_74757_a("GetsAttacked", this.getsAttacked);
        compound.func_74757_a("IsPassive", this.isPassive);
        compound.func_74782_a("AttackFactions", (NBTBase)NBTTags.nbtIntegerSet(this.attackFactions));
    }

    public boolean isFriendlyToPlayer(EntityPlayer player) {
        PlayerFactionData data = PlayerData.get((EntityPlayer)player).factionData;
        return data.getFactionPoints(this.id) >= this.friendlyPoints;
    }

    public boolean isAggressiveToPlayer(EntityPlayer player) {
        PlayerFactionData data = PlayerData.get((EntityPlayer)player).factionData;
        return data.getFactionPoints(this.id) < this.neutralPoints;
    }

    public boolean isNeutralToPlayer(EntityPlayer player) {
        PlayerFactionData data = PlayerData.get((EntityPlayer)player).factionData;
        int points = data.getFactionPoints(this.id);
        return points >= this.neutralPoints && points < this.friendlyPoints;
    }

    public boolean isAggressiveToNpc(EntityNPCInterface entity) {
        return this.attackFactions.contains(entity.faction.id);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDefaultPoints(int points) {
        this.defaultPoints = points;
    }

    @Override
    public int getDefaultPoints() {
        return this.defaultPoints;
    }

    @Override
    public void setFriendlyPoints(int points) {
        this.friendlyPoints = points;
    }

    @Override
    public int getFriendlyPoints() {
        return this.friendlyPoints;
    }

    @Override
    public void setNeutralPoints(int points) {
        this.neutralPoints = points;
    }

    @Override
    public int getNeutralPoints() {
        return this.neutralPoints;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getColor() {
        return this.color;
    }

    @Override
    public int playerStatus(IPlayer player) {
        IPlayerFactionData data = player.getData().getFactionData();
        int points = data.getPoints(this.id);
        if (points >= this.friendlyPoints) {
            return 1;
        }
        return points < this.neutralPoints ? -1 : 0;
    }

    @Override
    public boolean isAggressiveToNpc(ICustomNpc npc) {
        return this.attackFactions.contains(npc.getFaction().getId());
    }

    @Override
    public boolean isEnemyFaction(IFaction faction) {
        return this.attackFactions.contains(faction.getId());
    }

    @Override
    public IFaction[] getEnemyFactions() {
        ArrayList<Faction> enemyFactions = new ArrayList<Faction>();
        for (int id : this.attackFactions) {
            enemyFactions.add(FactionController.getInstance().get(id));
        }
        return enemyFactions.toArray(new IFaction[0]);
    }

    @Override
    public void addEnemyFaction(IFaction faction) {
        this.attackFactions.add(faction.getId());
    }

    @Override
    public void removeEnemyFaction(IFaction faction) {
        this.attackFactions.remove(faction.getId());
    }

    @Override
    public boolean getIsHidden() {
        return this.hideFaction;
    }

    @Override
    public void setIsHidden(boolean bo) {
        this.hideFaction = bo;
    }

    @Override
    public boolean isPassive() {
        return this.isPassive;
    }

    @Override
    public void setIsPassive(boolean passive) {
        this.isPassive = passive;
    }

    @Override
    public boolean attackedByMobs() {
        return this.getsAttacked;
    }

    @Override
    public void setAttackedByMobs(boolean bo) {
        this.getsAttacked = bo;
    }

    @Override
    public void save() {
        FactionController.getInstance().saveFaction(this);
    }

    public boolean isFriendlyToPlayer(IPlayer player) {
        return this.isFriendlyToPlayer((EntityPlayer)player.getMCEntity());
    }

    public boolean isNeutralToPlayer(IPlayer player) {
        return this.isNeutralToPlayer((EntityPlayer)player.getMCEntity());
    }

    public boolean isAggressiveToPlayer(IPlayer player) {
        return this.isAggressiveToPlayer((EntityPlayer)player.getMCEntity());
    }
}

