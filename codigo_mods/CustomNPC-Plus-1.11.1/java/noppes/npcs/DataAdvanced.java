/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 */
package noppes.npcs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import noppes.npcs.constants.EnumJobType;
import noppes.npcs.constants.EnumRoleType;
import noppes.npcs.controllers.data.DialogOption;
import noppes.npcs.controllers.data.FactionOptions;
import noppes.npcs.controllers.data.Line;
import noppes.npcs.controllers.data.Lines;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobBard;
import noppes.npcs.roles.JobChunkLoader;
import noppes.npcs.roles.JobConversation;
import noppes.npcs.roles.JobFollower;
import noppes.npcs.roles.JobGuard;
import noppes.npcs.roles.JobHealer;
import noppes.npcs.roles.JobItemGiver;
import noppes.npcs.roles.JobSpawner;
import noppes.npcs.roles.RoleAuctioneer;
import noppes.npcs.roles.RoleBank;
import noppes.npcs.roles.RoleCompanion;
import noppes.npcs.roles.RoleFollower;
import noppes.npcs.roles.RoleMount;
import noppes.npcs.roles.RolePostman;
import noppes.npcs.roles.RoleTrader;
import noppes.npcs.roles.RoleTransporter;

public class DataAdvanced {
    public Lines interactLines = new Lines();
    public Lines worldLines = new Lines();
    public Lines attackLines = new Lines();
    public Lines killedLines = new Lines();
    public Lines killLines = new Lines();
    public boolean orderedLines = false;
    public String idleSound = "";
    public String angrySound = "";
    public String hurtSound = "minecraft:game.player.hurt";
    public String deathSound = "minecraft:game.player.hurt";
    public String stepSound = "";
    private final EntityNPCInterface npc;
    public FactionOptions factions = new FactionOptions();
    public HashSet<UUID> tagUUIDs = new HashSet();
    public EnumRoleType role = EnumRoleType.None;
    public EnumJobType job = EnumJobType.None;
    public boolean attackOtherFactions = false;
    public boolean defendFaction = false;
    public boolean disablePitch = false;
    public String soulStonePlayerName = "";
    public boolean refuseSoulStone = false;
    public boolean soulStoneInit = false;
    public int minFactionPointsToSoulStone = -1;

    public DataAdvanced(EntityNPCInterface npc) {
        this.npc = npc;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74782_a("NpcLines", (NBTBase)this.worldLines.writeToNBT());
        compound.func_74782_a("NpcKilledLines", (NBTBase)this.killedLines.writeToNBT());
        compound.func_74782_a("NpcInteractLines", (NBTBase)this.interactLines.writeToNBT());
        compound.func_74782_a("NpcAttackLines", (NBTBase)this.attackLines.writeToNBT());
        compound.func_74782_a("NpcKillLines", (NBTBase)this.killLines.writeToNBT());
        compound.func_74778_a("NpcIdleSound", this.idleSound);
        compound.func_74778_a("NpcAngrySound", this.angrySound);
        compound.func_74778_a("NpcHurtSound", this.hurtSound);
        compound.func_74778_a("NpcDeathSound", this.deathSound);
        compound.func_74778_a("NpcStepSound", this.stepSound);
        compound.func_74757_a("OrderedLines", this.orderedLines);
        compound.func_74768_a("FactionID", this.npc.getFaction().id);
        compound.func_74757_a("AttackOtherFactions", this.attackOtherFactions);
        compound.func_74757_a("DefendFaction", this.defendFaction);
        compound.func_74757_a("DisablePitch", this.disablePitch);
        compound.func_74768_a("Role", this.role.ordinal());
        compound.func_74768_a("NpcJob", this.job.ordinal());
        compound.func_74782_a("FactionPoints", (NBTBase)this.factions.writeToNBT(new NBTTagCompound()));
        compound.func_74782_a("NPCDialogOptions", (NBTBase)this.nbtDialogs(this.npc.dialogs));
        compound.func_74757_a("RefuseSoulStone", this.refuseSoulStone);
        compound.func_74778_a("SoulStonePlayerName", this.soulStonePlayerName);
        compound.func_74768_a("MinFactionPointsToSoulStone", this.minFactionPointsToSoulStone);
        if (!this.tagUUIDs.isEmpty()) {
            NBTTagList nbtTagList = new NBTTagList();
            for (UUID uuid : this.tagUUIDs) {
                nbtTagList.func_74742_a((NBTBase)new NBTTagString(uuid.toString()));
            }
            compound.func_74782_a("TagUUIDs", (NBTBase)nbtTagList);
        }
        return compound;
    }

    public void readToNBT(NBTTagCompound compound) {
        this.interactLines.readNBT(compound.func_74775_l("NpcInteractLines"));
        this.worldLines.readNBT(compound.func_74775_l("NpcLines"));
        this.attackLines.readNBT(compound.func_74775_l("NpcAttackLines"));
        this.killedLines.readNBT(compound.func_74775_l("NpcKilledLines"));
        this.killLines.readNBT(compound.func_74775_l("NpcKillLines"));
        this.idleSound = compound.func_74779_i("NpcIdleSound");
        this.angrySound = compound.func_74779_i("NpcAngrySound");
        this.hurtSound = compound.func_74779_i("NpcHurtSound");
        this.deathSound = compound.func_74779_i("NpcDeathSound");
        this.stepSound = compound.func_74779_i("NpcStepSound");
        this.orderedLines = compound.func_74767_n("OrderedLines");
        this.npc.setFaction(compound.func_74762_e("FactionID"));
        this.npc.faction = this.npc.getFaction();
        this.attackOtherFactions = compound.func_74767_n("AttackOtherFactions");
        this.defendFaction = compound.func_74767_n("DefendFaction");
        this.disablePitch = compound.func_74767_n("DisablePitch");
        this.setRole(compound.func_74762_e("Role"));
        this.setJob(compound.func_74762_e("NpcJob"));
        this.factions.readFromNBT(compound.func_74775_l("FactionPoints"));
        this.npc.dialogs = this.getDialogs(compound.func_150295_c("NPCDialogOptions", 10));
        this.refuseSoulStone = compound.func_74767_n("RefuseSoulStone");
        this.soulStonePlayerName = compound.func_74779_i("SoulStonePlayerName");
        this.minFactionPointsToSoulStone = compound.func_74762_e("MinFactionPointsToSoulStone");
        if (compound.func_74764_b("TagUUIDs")) {
            NBTTagList nbtTagList = compound.func_150295_c("TagUUIDs", 8);
            for (int i = 0; i < nbtTagList.func_74745_c(); ++i) {
                this.tagUUIDs.add(UUID.fromString(nbtTagList.func_150307_f(i)));
            }
        }
    }

    private HashMap<Integer, DialogOption> getDialogs(NBTTagList tagList) {
        HashMap<Integer, DialogOption> map = new HashMap<Integer, DialogOption>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            int slot = nbttagcompound.func_74762_e("DialogSlot");
            DialogOption option = new DialogOption();
            option.readNBT(nbttagcompound.func_74775_l("NPCDialog"));
            map.put(slot, option);
        }
        return map;
    }

    private NBTTagList nbtDialogs(HashMap<Integer, DialogOption> dialogs2) {
        NBTTagList nbttaglist = new NBTTagList();
        for (int slot : dialogs2.keySet()) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74768_a("DialogSlot", slot);
            nbttagcompound.func_74782_a("NPCDialog", (NBTBase)dialogs2.get(slot).writeNBT());
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public Line getInteractLine() {
        return (Line)this.interactLines.getLine(!this.orderedLines);
    }

    public Line getAttackLine() {
        return (Line)this.attackLines.getLine(!this.orderedLines);
    }

    public Line getKilledLine() {
        return (Line)this.killedLines.getLine(!this.orderedLines);
    }

    public Line getKillLine() {
        return (Line)this.killLines.getLine(!this.orderedLines);
    }

    public Line getWorldLine() {
        return (Line)this.worldLines.getLine(!this.orderedLines);
    }

    public void setRole(int i) {
        if (EnumRoleType.values().length <= i) {
            i -= 2;
        }
        EnumRoleType newRole = EnumRoleType.values()[i];
        if (this.npc.roleInterface instanceof RoleMount && newRole != EnumRoleType.Mount) {
            ((RoleMount)this.npc.roleInterface).onDisable();
        }
        this.role = newRole;
        if (this.role == EnumRoleType.None) {
            this.npc.roleInterface = null;
        } else if (this.role == EnumRoleType.Bank && !(this.npc.roleInterface instanceof RoleBank)) {
            this.npc.roleInterface = new RoleBank(this.npc);
        } else if (this.role == EnumRoleType.Follower && !(this.npc.roleInterface instanceof RoleFollower)) {
            this.npc.roleInterface = new RoleFollower(this.npc);
        } else if (this.role == EnumRoleType.Postman && !(this.npc.roleInterface instanceof RolePostman)) {
            this.npc.roleInterface = new RolePostman(this.npc);
        } else if (this.role == EnumRoleType.Trader && !(this.npc.roleInterface instanceof RoleTrader)) {
            this.npc.roleInterface = new RoleTrader(this.npc);
        } else if (this.role == EnumRoleType.Transporter && !(this.npc.roleInterface instanceof RoleTransporter)) {
            this.npc.roleInterface = new RoleTransporter(this.npc);
        } else if (this.role == EnumRoleType.Companion && !(this.npc.roleInterface instanceof RoleCompanion)) {
            this.npc.roleInterface = new RoleCompanion(this.npc);
        } else if (this.role == EnumRoleType.Mount && !(this.npc.roleInterface instanceof RoleMount)) {
            this.npc.roleInterface = new RoleMount(this.npc);
        } else if (this.role == EnumRoleType.Auctioneer && !(this.npc.roleInterface instanceof RoleAuctioneer)) {
            this.npc.roleInterface = new RoleAuctioneer(this.npc);
        }
    }

    public void setJob(int i) {
        if (this.npc.jobInterface != null && !this.npc.field_70170_p.field_72995_K) {
            this.npc.jobInterface.reset();
        }
        this.job = EnumJobType.values()[i % EnumJobType.values().length];
        if (this.job == EnumJobType.None) {
            this.npc.jobInterface = null;
        } else if (this.job == EnumJobType.Bard && !(this.npc.jobInterface instanceof JobBard)) {
            this.npc.jobInterface = new JobBard(this.npc);
        } else if (this.job == EnumJobType.Healer && !(this.npc.jobInterface instanceof JobHealer)) {
            this.npc.jobInterface = new JobHealer(this.npc);
        } else if (this.job == EnumJobType.Guard && !(this.npc.jobInterface instanceof JobGuard)) {
            this.npc.jobInterface = new JobGuard(this.npc);
        } else if (this.job == EnumJobType.ItemGiver && !(this.npc.jobInterface instanceof JobItemGiver)) {
            this.npc.jobInterface = new JobItemGiver(this.npc);
        } else if (this.job == EnumJobType.Follower && !(this.npc.jobInterface instanceof JobFollower)) {
            this.npc.jobInterface = new JobFollower(this.npc);
        } else if (this.job == EnumJobType.Spawner && !(this.npc.jobInterface instanceof JobSpawner)) {
            this.npc.jobInterface = new JobSpawner(this.npc);
        } else if (this.job == EnumJobType.Conversation && !(this.npc.jobInterface instanceof JobConversation)) {
            this.npc.jobInterface = new JobConversation(this.npc);
        } else if (this.job == EnumJobType.ChunkLoader && !(this.npc.jobInterface instanceof JobChunkLoader)) {
            this.npc.jobInterface = new JobChunkLoader(this.npc);
        }
    }

    public boolean hasWorldLines() {
        return !this.worldLines.isEmpty();
    }
}

