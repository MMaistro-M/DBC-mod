/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.roles;

import java.util.HashMap;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import noppes.npcs.NBTTags;
import noppes.npcs.NoppesStringUtils;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.NpcMiscInventory;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.constants.EnumJobType;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleInterface;

public class RoleFollower
extends RoleInterface {
    private String ownerUUID;
    public boolean isFollowing = true;
    public HashMap<Integer, Integer> rates;
    public NpcMiscInventory inventory;
    public String dialogHire = StatCollector.func_74838_a((String)"follower.hireText") + " {days} " + StatCollector.func_74838_a((String)"follower.days");
    public String dialogFarewell = StatCollector.func_74838_a((String)"follower.farewellText") + " {player}";
    public int daysHired;
    public long hiredTime;
    public boolean disableGui = false;
    public boolean infiniteDays = false;
    public boolean refuseSoulStone = false;
    public EntityPlayer owner = null;

    public RoleFollower(EntityNPCInterface npc) {
        super(npc);
        this.inventory = new NpcMiscInventory(3);
        this.rates = new HashMap();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74768_a("MercenaryDaysHired", this.daysHired);
        nbttagcompound.func_74772_a("MercenaryHiredTime", this.hiredTime);
        nbttagcompound.func_74778_a("MercenaryDialogHired", this.dialogHire);
        nbttagcompound.func_74778_a("MercenaryDialogFarewell", this.dialogFarewell);
        if (this.hasOwner()) {
            nbttagcompound.func_74778_a("MercenaryOwner", this.ownerUUID);
        }
        nbttagcompound.func_74782_a("MercenaryDayRates", (NBTBase)NBTTags.nbtIntegerIntegerMap(this.rates));
        nbttagcompound.func_74782_a("MercenaryInv", (NBTBase)this.inventory.getToNBT());
        nbttagcompound.func_74757_a("MercenaryIsFollowing", this.isFollowing);
        nbttagcompound.func_74757_a("MercenaryDisableGui", this.disableGui);
        nbttagcompound.func_74757_a("MercenaryInfiniteDays", this.infiniteDays);
        nbttagcompound.func_74757_a("MercenaryRefuseSoulstone", this.refuseSoulStone);
        return nbttagcompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        this.ownerUUID = nbttagcompound.func_74779_i("MercenaryOwner");
        this.daysHired = nbttagcompound.func_74762_e("MercenaryDaysHired");
        this.hiredTime = nbttagcompound.func_74763_f("MercenaryHiredTime");
        this.dialogHire = nbttagcompound.func_74779_i("MercenaryDialogHired");
        this.dialogFarewell = nbttagcompound.func_74779_i("MercenaryDialogFarewell");
        this.rates = NBTTags.getIntegerIntegerMap(nbttagcompound.func_150295_c("MercenaryDayRates", 10));
        this.inventory.setFromNBT(nbttagcompound.func_74775_l("MercenaryInv"));
        this.isFollowing = nbttagcompound.func_74767_n("MercenaryIsFollowing");
        this.disableGui = nbttagcompound.func_74767_n("MercenaryDisableGui");
        this.infiniteDays = nbttagcompound.func_74767_n("MercenaryInfiniteDays");
        this.refuseSoulStone = nbttagcompound.func_74767_n("MercenaryRefuseSoulstone");
    }

    @Override
    public boolean aiShouldExecute() {
        this.owner = this.getOwner();
        if (!this.infiniteDays && this.owner != null && this.getDaysLeft() <= 0) {
            this.owner.func_145747_a((IChatComponent)new ChatComponentTranslation(NoppesStringUtils.formatText(this.dialogFarewell, new Object[]{this.owner, this.npc}), new Object[0]));
            this.killed();
        }
        return false;
    }

    public EntityPlayer getOwner() {
        if (this.ownerUUID == null || this.ownerUUID.isEmpty()) {
            return null;
        }
        try {
            UUID uuid = UUID.fromString(this.ownerUUID);
            if (uuid != null) {
                return this.npc.field_70170_p.func_152378_a(uuid);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        return this.npc.field_70170_p.func_72924_a(this.ownerUUID);
    }

    public boolean hasOwner() {
        if (!this.infiniteDays && this.daysHired <= 0) {
            return false;
        }
        return this.ownerUUID != null && !this.ownerUUID.isEmpty();
    }

    @Override
    public void killed() {
        this.ownerUUID = null;
        this.daysHired = 0;
        this.hiredTime = 0L;
        this.isFollowing = true;
        this.npc.stats.canDespawn = this.npc.stats.playerSetCanDespawn;
    }

    public int getDaysLeft() {
        if (this.infiniteDays) {
            return 100;
        }
        if (this.daysHired <= 0) {
            return 0;
        }
        int days = (int)((this.npc.field_70170_p.func_82737_E() - this.hiredTime) / 24000L);
        return this.daysHired - days;
    }

    public void addDays(int days) {
        this.daysHired = days + this.getDaysLeft();
        this.hiredTime = this.npc.field_70170_p.func_82737_E();
    }

    @Override
    public void interact(EntityPlayer player) {
        if (this.ownerUUID == null || this.ownerUUID.isEmpty()) {
            this.npc.say(player, this.npc.advanced.getInteractLine());
            NoppesUtilServer.sendOpenGui(player, EnumGuiType.PlayerFollowerHire, this.npc);
        } else if (player == this.owner && !this.disableGui) {
            NoppesUtilServer.sendOpenGui(player, EnumGuiType.PlayerFollower, this.npc);
        }
    }

    @Override
    public boolean defendOwner() {
        return this.isFollowing() && this.npc.advanced.job == EnumJobType.Guard;
    }

    @Override
    public void delete() {
    }

    public boolean isFollowing() {
        return this.owner != null && this.isFollowing && this.getDaysLeft() > 0;
    }

    public void setOwner(EntityPlayer player) {
        UUID id = player.func_110124_au();
        if (this.ownerUUID == null || id == null || !this.ownerUUID.equals(id)) {
            this.killed();
        }
        this.ownerUUID = id.toString();
        this.npc.stats.canDespawn = false;
    }

    public void setRate(int index, int amount) {
        if (index > 2 || index < 0) {
            return;
        }
        if (amount < 1) {
            return;
        }
        this.rates.put(index, amount);
    }

    public int getRate(int index) {
        if (index > 2 || index < 0) {
            return -1;
        }
        if (!this.rates.containsKey(index)) {
            return -1;
        }
        return this.rates.get(index);
    }

    public void setDialogHire(String dialogHire) {
        this.dialogHire = dialogHire;
    }

    public String getDialogHire() {
        return this.dialogHire;
    }

    public void setDialogFarewell(String dialogFarewell) {
        this.dialogFarewell = dialogFarewell;
    }

    public String getDialogFarewell() {
        return this.dialogFarewell;
    }
}

