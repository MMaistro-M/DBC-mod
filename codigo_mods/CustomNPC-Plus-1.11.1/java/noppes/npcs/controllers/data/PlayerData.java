/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IExtendedEntityProperties
 */
package noppes.npcs.controllers.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.UUID;
import kamkeel.npcs.addon.DBCAddon;
import kamkeel.npcs.controllers.ProfileController;
import kamkeel.npcs.controllers.data.profile.Profile;
import kamkeel.npcs.network.packets.data.AchievementPacket;
import kamkeel.npcs.network.packets.data.ChatAlertPacket;
import kamkeel.npcs.network.packets.request.party.PartyInvitePacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;
import noppes.npcs.api.ability.IPlayerAbilityData;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.handler.IPlayerBankData;
import noppes.npcs.api.handler.IPlayerData;
import noppes.npcs.api.handler.IPlayerDialogData;
import noppes.npcs.api.handler.IPlayerFactionData;
import noppes.npcs.api.handler.IPlayerItemGiverData;
import noppes.npcs.api.handler.IPlayerMailData;
import noppes.npcs.api.handler.IPlayerQuestData;
import noppes.npcs.api.handler.IPlayerTradeData;
import noppes.npcs.api.handler.IPlayerTransportData;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.constants.EnumRoleType;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.PartyController;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.controllers.data.AnimationData;
import noppes.npcs.controllers.data.MagicData;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerAbilityData;
import noppes.npcs.controllers.data.PlayerAbilityHotbarData;
import noppes.npcs.controllers.data.PlayerBankData;
import noppes.npcs.controllers.data.PlayerDataScript;
import noppes.npcs.controllers.data.PlayerDialogData;
import noppes.npcs.controllers.data.PlayerEffectData;
import noppes.npcs.controllers.data.PlayerFactionData;
import noppes.npcs.controllers.data.PlayerItemGiverData;
import noppes.npcs.controllers.data.PlayerMailData;
import noppes.npcs.controllers.data.PlayerQuestData;
import noppes.npcs.controllers.data.PlayerTradeData;
import noppes.npcs.controllers.data.PlayerTransportData;
import noppes.npcs.controllers.data.action.ActionManager;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.entity.data.DataSkinOverlays;
import noppes.npcs.entity.data.DataTimers;
import noppes.npcs.roles.RoleCompanion;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.ScreenSize;
import noppes.npcs.util.CustomNPCsThreader;
import noppes.npcs.util.NBTJsonUtil;

public class PlayerData
implements IExtendedEntityProperties,
IPlayerData {
    public PlayerDialogData dialogData = new PlayerDialogData(this);
    public PlayerBankData bankData = new PlayerBankData(this);
    public PlayerQuestData questData = new PlayerQuestData(this);
    public PlayerTransportData transportData = new PlayerTransportData(this);
    public PlayerFactionData factionData = new PlayerFactionData(this);
    public PlayerItemGiverData itemgiverData = new PlayerItemGiverData(this);
    public PlayerMailData mailData = new PlayerMailData(this);
    public AnimationData animationData = new AnimationData(this);
    public PlayerEffectData effectData = new PlayerEffectData(this);
    public DataTimers timers = new DataTimers(this);
    public DataSkinOverlays skinOverlays = new DataSkinOverlays(this);
    public MagicData magicData = new MagicData();
    public PlayerTradeData tradeData = new PlayerTradeData(this);
    public PlayerAbilityData abilityData = new PlayerAbilityData(this);
    public PlayerAbilityHotbarData hotbarData = new PlayerAbilityHotbarData();
    public ActionManager actionManager = new ActionManager();
    public PlayerDataScript scriptData;
    public EntityNPCInterface editingNpc;
    public NBTTagCompound cloned;
    public UUID partyUUID = null;
    private final HashSet<UUID> partyInvites = new HashSet();
    public EntityPlayer player;
    public String playername = "";
    public String uuid = "";
    private EntityNPCInterface activeCompanion = null;
    public int companionID = 0;
    public boolean isGUIOpen = false;
    public boolean hadInteract = true;
    public boolean updateClient = false;
    public ScreenSize screenSize = new ScreenSize(-1, -1);
    public int profileSlot = 0;
    private boolean specialKeyDown = false;

    public void onLogin() {
        this.abilityData.resetOnLogin();
        AnimationData animationData = this.animationData;
        if (animationData != null && animationData.isClientAnimating()) {
            if (this.abilityData.isPlayingAbilityAnimation()) {
                this.abilityData.clearOrphanedAbilityAnimation();
            } else {
                Animation currentAnimation = animationData.currentClientAnimation;
                NBTTagCompound compound = currentAnimation.writeToNBT();
                animationData.viewAnimation(currentAnimation, animationData, compound, animationData.isClientAnimating(), currentAnimation.currentFrame, currentAnimation.currentFrameTime);
            }
        }
        CustomEffectController controller = CustomEffectController.getInstance();
        UUID playerID = this.player.getPersistentID();
        if (!controller.playerEffects.containsKey(playerID)) {
            controller.playerEffects.put(playerID, this.effectData.getEffects());
        }
    }

    public void onLogout() {
        this.abilityData.resetOnDisconnect();
        this.partyInvites.clear();
        this.actionManager.clear();
    }

    public void saveNBTData(NBTTagCompound nbtTagCompound) {
    }

    public void loadNBTData(NBTTagCompound compound) {
    }

    public void setNBT(NBTTagCompound data) {
        this.dialogData.loadNBTData(data);
        this.bankData.loadNBTData(data);
        this.questData.loadNBTData(data);
        this.transportData.loadNBTData(data);
        this.factionData.loadNBTData(data);
        this.itemgiverData.loadNBTData(data);
        this.mailData.loadNBTData(data);
        this.timers.readFromNBT(data);
        this.skinOverlays.readFromNBT(data);
        this.animationData.readFromNBT(data);
        this.effectData.readFromNBT(data);
        this.magicData.readToNBT(data);
        this.tradeData.readFromNBT(data);
        this.abilityData.readFromNBT(data);
        this.hotbarData.readFromNBT(data);
        if (this.player != null) {
            this.playername = this.player.func_70005_c_();
            this.uuid = this.player.getPersistentID().toString();
        } else {
            this.playername = data.func_74779_i("PlayerName");
            this.uuid = data.func_74779_i("UUID");
        }
        this.companionID = data.func_74762_e("PlayerCompanionId");
        this.profileSlot = data.func_74762_e("ProfileSlot");
        if (data.func_74764_b("PlayerCompanion") && !this.hasCompanion()) {
            EntityCustomNpc npc = new EntityCustomNpc(this.player.field_70170_p);
            npc.func_70037_a(data.func_74775_l("PlayerCompanion"));
            npc.func_70107_b(this.player.field_70165_t, this.player.field_70163_u, this.player.field_70161_v);
            if (npc.advanced.role == EnumRoleType.Companion) {
                this.setCompanion(npc);
                ((RoleCompanion)npc.roleInterface).setSitting(false);
                this.player.field_70170_p.func_72838_d((Entity)npc);
            }
        }
        this.isGUIOpen = data.func_74767_n("isGUIOpen");
        DBCAddon.instance.readFromNBT(this, data);
    }

    public NBTTagCompound getNBT() {
        NBTTagCompound nbt;
        if (this.player != null) {
            this.playername = this.player.func_70005_c_();
            this.uuid = this.player.getPersistentID().toString();
        }
        NBTTagCompound compound = new NBTTagCompound();
        this.dialogData.saveNBTData(compound);
        this.bankData.saveNBTData(compound);
        this.questData.saveNBTData(compound);
        this.transportData.saveNBTData(compound);
        this.factionData.saveNBTData(compound);
        this.itemgiverData.saveNBTData(compound);
        this.mailData.saveNBTData(compound);
        this.timers.writeToNBT(compound);
        this.skinOverlays.writeToNBT(compound);
        this.animationData.writeToNBT(compound);
        this.effectData.writeToNBT(compound);
        this.magicData.writeToNBT(compound);
        this.tradeData.writeToNBT(compound);
        this.abilityData.writeToNBT(compound);
        this.hotbarData.writeToNBT(compound);
        compound.func_74778_a("PlayerName", this.playername);
        compound.func_74778_a("UUID", this.uuid);
        compound.func_74768_a("PlayerCompanionId", this.companionID);
        compound.func_74757_a("isGUIOpen", this.isGUIOpen);
        compound.func_74768_a("ProfileSlot", this.profileSlot);
        if (this.hasCompanion() && this.activeCompanion.func_70039_c(nbt = new NBTTagCompound())) {
            compound.func_74782_a("PlayerCompanion", (NBTBase)nbt);
        }
        DBCAddon.instance.writeToNBT(this, compound);
        return compound;
    }

    public NBTTagCompound getSyncNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        this.dialogData.saveNBTData(compound);
        this.questData.saveNBTData(compound);
        this.factionData.saveNBTData(compound);
        return compound;
    }

    public void setSyncNBT(NBTTagCompound data) {
        this.dialogData.loadNBTData(data);
        this.questData.loadNBTData(data);
        this.factionData.loadNBTData(data);
    }

    public NBTTagCompound getPlayerEffects() {
        NBTTagCompound compound = new NBTTagCompound();
        this.effectData.writeToNBT(compound);
        return compound;
    }

    public void setPlayerEffects(NBTTagCompound data) {
        this.effectData.readFromNBT(data);
    }

    public NBTTagCompound getSyncNBTFull() {
        if (this.player != null) {
            this.playername = this.player.func_70005_c_();
            this.uuid = this.player.getPersistentID().toString();
        }
        NBTTagCompound compound = new NBTTagCompound();
        this.dialogData.saveNBTData(compound);
        this.bankData.saveNBTData(compound);
        this.questData.saveNBTData(compound);
        this.transportData.saveNBTData(compound);
        this.factionData.saveNBTData(compound);
        this.mailData.saveNBTData(compound);
        this.tradeData.writeToNBT(compound);
        this.abilityData.writeToNBT(compound);
        this.hotbarData.writeToNBT(compound);
        compound.func_74778_a("PlayerName", this.playername);
        compound.func_74778_a("UUID", this.uuid);
        DBCAddon.instance.writeToNBT(this, compound);
        return compound;
    }

    public void setSyncNBTFull(NBTTagCompound data) {
        this.dialogData.loadNBTData(data);
        this.bankData.loadNBTData(data);
        this.questData.loadNBTData(data);
        this.transportData.loadNBTData(data);
        this.factionData.loadNBTData(data);
        this.mailData.loadNBTData(data);
        this.tradeData.readFromNBT(data);
        this.abilityData.readFromNBT(data);
        this.hotbarData.readFromNBT(data);
        if (this.player != null) {
            this.playername = this.player.func_70005_c_();
            this.uuid = this.player.getPersistentID().toString();
        } else {
            this.playername = data.func_74779_i("PlayerName");
            this.uuid = data.func_74779_i("UUID");
        }
        DBCAddon.instance.readFromNBT(this, data);
    }

    public void getDBCSync(NBTTagCompound compound) {
        DBCAddon.instance.writeToNBT(this, compound);
    }

    public void setDBCSync(NBTTagCompound data) {
        DBCAddon.instance.readFromNBT(this, data);
    }

    public void init(Entity entity, World world) {
    }

    public void setGUIOpen(boolean bool) {
        this.isGUIOpen = bool;
    }

    public boolean getGUIOpen() {
        return this.isGUIOpen;
    }

    public boolean isSpecialKeyDown() {
        return this.specialKeyDown;
    }

    public void setSpecialKeyDown(boolean specialKeyDown) {
        this.specialKeyDown = specialKeyDown;
    }

    public ScreenSize getScreenSize() {
        return this.screenSize;
    }

    public void setScreenSize(ScreenSize size) {
        this.screenSize = size;
    }

    @Override
    public boolean hasCompanion() {
        return this.activeCompanion != null && !this.activeCompanion.field_70128_L;
    }

    public void setCompanion(EntityNPCInterface npc) {
        if (npc != null && npc.advanced.role != EnumRoleType.Companion) {
            return;
        }
        ++this.companionID;
        this.activeCompanion = npc;
        if (npc != null) {
            ((RoleCompanion)npc.roleInterface).companionID = this.companionID;
        }
        this.save();
    }

    public void updateCompanion(World world) {
        if (!this.hasCompanion() || world == this.activeCompanion.field_70170_p) {
            return;
        }
        RoleCompanion role = (RoleCompanion)this.activeCompanion.roleInterface;
        role.owner = this.player;
        if (!role.isFollowing()) {
            return;
        }
        NBTTagCompound nbt = new NBTTagCompound();
        this.activeCompanion.func_70039_c(nbt);
        this.activeCompanion.field_70128_L = true;
        EntityCustomNpc npc = new EntityCustomNpc(world);
        npc.func_70037_a(nbt);
        npc.func_70107_b(this.player.field_70165_t, this.player.field_70163_u, this.player.field_70161_v);
        this.setCompanion(npc);
        ((RoleCompanion)npc.roleInterface).setSitting(false);
        world.func_72838_d((Entity)npc);
    }

    public void inviteToParty(Party party) {
        if (party != null && this.partyUUID == null && !this.partyInvites.contains(party.getPartyUUID())) {
            this.partyInvites.add(party.getPartyUUID());
            AchievementPacket.sendAchievement((EntityPlayerMP)this.player, true, "party.inviteAlert", party.getPartyLeader().func_70005_c_());
            ChatAlertPacket.sendChatAlert((EntityPlayerMP)this.player, "\u00a7a", "party.inviteChat", " ", party.getPartyLeader().func_70005_c_(), "!");
        }
    }

    public void ignoreInvite(UUID uuid) {
        if (uuid != null) {
            this.partyInvites.remove(uuid);
            PartyInvitePacket.sendInviteData((EntityPlayerMP)this.player);
        }
    }

    public void acceptInvite(UUID uuid) {
        if (uuid != null) {
            this.partyInvites.remove(uuid);
            Party party = PartyController.Instance().getParty(uuid);
            if (party != null && !party.getIsLocked()) {
                party.addPlayer(this.player);
                PartyController.Instance().pingPartyUpdate(party);
            }
        }
    }

    public Party getPlayerParty() {
        if (this.partyUUID != null) {
            return PartyController.Instance().getParty(this.partyUUID);
        }
        return null;
    }

    public HashSet<UUID> getPartyInvites() {
        return (HashSet)this.partyInvites.clone();
    }

    @Override
    public void setCompanion(ICustomNpc npc) {
        this.setCompanion((EntityNPCInterface)((Object)npc.getMCEntity()));
    }

    @Override
    public ICustomNpc getCompanion() {
        return (ICustomNpc)NpcAPI.Instance().getIEntity((Entity)this.activeCompanion);
    }

    @Override
    public int getCompanionID() {
        return this.companionID;
    }

    @Override
    public IPlayerDialogData getDialogData() {
        return this.dialogData;
    }

    @Override
    public IPlayerBankData getBankData() {
        return this.bankData;
    }

    @Override
    public IPlayerQuestData getQuestData() {
        return this.questData;
    }

    @Override
    public IPlayerTransportData getTransportData() {
        return this.transportData;
    }

    @Override
    public IPlayerFactionData getFactionData() {
        return this.factionData;
    }

    @Override
    public IPlayerItemGiverData getItemGiverData() {
        return this.itemgiverData;
    }

    @Override
    public IPlayerMailData getMailData() {
        return this.mailData;
    }

    @Override
    public IPlayerTradeData getTradeData() {
        return this.tradeData;
    }

    @Override
    public IPlayerAbilityData getAbilityData() {
        return this.abilityData;
    }

    @Override
    public synchronized void save() {
        Profile profile = ProfileController.Instance.getProfile(UUID.fromString(this.uuid));
        if (profile != null && profile.currentSlotId != this.profileSlot) {
            ProfileController.Instance.saveOffline(profile, UUID.fromString(this.uuid));
            return;
        }
        NBTTagCompound compound = this.getNBT();
        String filename = ConfigMain.DatFormat ? this.uuid + ".dat" : this.uuid + ".json";
        PlayerDataController.Instance.putPlayerMap(this.playername, this.uuid);
        PlayerDataController.Instance.putPlayerDataCache(this.uuid, this);
        CustomNPCsThreader.customNPCThread.execute(() -> {
            try {
                File saveDir = PlayerDataController.Instance.getSaveDir();
                File file = new File(saveDir, filename + "_new");
                File file1 = new File(saveDir, filename);
                if (ConfigMain.DatFormat) {
                    CompressedStreamTools.func_74799_a((NBTTagCompound)compound, (OutputStream)new FileOutputStream(file));
                } else {
                    NBTJsonUtil.SaveFile(file, compound);
                }
                if (file1.exists()) {
                    file1.delete();
                }
                file.renameTo(file1);
            }
            catch (Exception e) {
                LogWriter.except(e);
            }
        });
    }

    public void load() {
        NBTTagCompound data = PlayerDataController.Instance.loadPlayerData(this.player.getPersistentID().toString());
        if (data.func_82582_d()) {
            data = PlayerDataController.Instance.loadPlayerDataOld(this.player.func_70005_c_());
        }
        if (data.func_82582_d()) {
            data = this.getNBT();
        }
        this.setNBT(data);
    }

    public static PlayerData get(EntityPlayer player) {
        if (player.field_70170_p.field_72995_K) {
            return CustomNpcs.proxy.getPlayerData(player);
        }
        return PlayerDataController.Instance.getPlayerData(player);
    }
}

