/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerRespawnEvent
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.village.MerchantRecipeList
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.event.entity.living.LivingAttackEvent
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.player.EntityInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$Clone
 *  net.minecraftforge.event.entity.player.PlayerEvent$SaveToFile
 *  net.minecraftforge.event.entity.player.PlayerEvent$StartTracking
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 *  net.minecraftforge.event.terraingen.PopulateChunkEvent$Post
 */
package noppes.npcs;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kamkeel.npcs.controllers.SyncController;
import kamkeel.npcs.controllers.data.energycharge.EnergyChargeTracker;
import kamkeel.npcs.entity.EntityEnergyBarrier;
import kamkeel.npcs.entity.EntityEnergyDome;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumSoundOperation;
import kamkeel.npcs.network.enums.EnumSyncAction;
import kamkeel.npcs.network.enums.EnumSyncType;
import kamkeel.npcs.network.packets.data.ClonerPacket;
import kamkeel.npcs.network.packets.data.MarkDataPacket;
import kamkeel.npcs.network.packets.data.SoundManagementPacket;
import kamkeel.npcs.network.packets.data.VillagerListPacket;
import kamkeel.npcs.network.packets.data.gui.GuiOpenPacket;
import kamkeel.npcs.network.packets.data.large.SyncPacket;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.EventHooks;
import noppes.npcs.LogWriter;
import noppes.npcs.NPCSpawning;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.blocks.tiles.ITileIcon;
import noppes.npcs.config.ConfigDebug;
import noppes.npcs.config.ConfigEnergy;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.constants.EnumPartyObjectives;
import noppes.npcs.constants.EnumQuestType;
import noppes.npcs.constants.EnumRoleType;
import noppes.npcs.controllers.PartyController;
import noppes.npcs.controllers.ServerCloneController;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.controllers.data.AnimationData;
import noppes.npcs.controllers.data.Line;
import noppes.npcs.controllers.data.MarkData;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerQuestData;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.controllers.data.QuestData;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.items.ItemExcalibur;
import noppes.npcs.items.ItemShield;
import noppes.npcs.items.ItemSoulstoneEmpty;
import noppes.npcs.quests.QuestKill;
import noppes.npcs.roles.RoleFollower;

public class ServerEventsHandler {
    public static EntityVillager Merchant;
    public static Entity mounted;

    @SubscribeEvent
    public void invoke(EntityInteractEvent event) {
        ItemStack item = event.entityPlayer.func_71045_bC();
        if (item == null) {
            return;
        }
        boolean isRemote = event.entityPlayer.field_70170_p.field_72995_K;
        boolean npcInteracted = event.target instanceof EntityNPCInterface;
        if (!isRemote && ConfigMain.OpsOnly && !MinecraftServer.func_71276_C().func_71203_ab().func_152596_g(event.entityPlayer.func_146103_bH())) {
            return;
        }
        if (!isRemote && item.func_77973_b() == CustomItems.soulstoneEmpty && event.target instanceof EntityLivingBase) {
            ((ItemSoulstoneEmpty)item.func_77973_b()).store((EntityLivingBase)event.target, item, event.entityPlayer);
            if (ConfigDebug.PlayerLogging && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
                LogWriter.script(String.format("[%s] (Player) %s PICKED ENTITY %s", "SOULSTONE", event.entityPlayer.func_70005_c_(), event.target));
            }
        }
        if (item.func_77973_b() == CustomItems.wand && npcInteracted && !isRemote) {
            if (!CustomNpcsPermissions.hasPermission(event.entityPlayer, CustomNpcsPermissions.NPC_GUI)) {
                return;
            }
            event.setCanceled(true);
            NoppesUtilServer.sendOpenGui(event.entityPlayer, EnumGuiType.MainMenuDisplay, (EntityNPCInterface)event.target);
            if (ConfigDebug.PlayerLogging && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
                LogWriter.script(String.format("[%s] (Player) %s OPEN NPC %s (%s, %s, %s) [%s]", "WAND", event.entityPlayer.func_70005_c_(), ((EntityNPCInterface)event.target).display.getName(), (int)event.target.field_70165_t, (int)event.target.field_70163_u, (int)event.target.field_70161_v, event.target.field_70170_p.func_72912_H().func_76065_j()));
            }
        } else if (item.func_77973_b() == CustomItems.cloner && !isRemote && !(event.target instanceof EntityPlayer)) {
            if (!CustomNpcsPermissions.hasPermission(event.entityPlayer, CustomNpcsPermissions.TOOL_CLONER)) {
                return;
            }
            NBTTagCompound compound = new NBTTagCompound();
            if (!event.target.func_70039_c(compound)) {
                return;
            }
            PlayerData data = PlayerData.get(event.entityPlayer);
            ServerCloneController.Instance.cleanTags(compound);
            PacketHandler.Instance.sendToPlayer(new ClonerPacket(compound), (EntityPlayerMP)event.entityPlayer);
            data.cloned = compound;
            if (event.target instanceof EntityNPCInterface) {
                NoppesUtilServer.setEditingNpc(event.entityPlayer, (EntityNPCInterface)event.target);
            }
            event.setCanceled(true);
        } else if (item.func_77973_b() == CustomItems.scripter && !isRemote && npcInteracted) {
            if (!CustomNpcsPermissions.hasPermission(event.entityPlayer, CustomNpcsPermissions.TOOL_SCRIPTER)) {
                return;
            }
            NoppesUtilServer.setEditingNpc(event.entityPlayer, (EntityNPCInterface)event.target);
            event.setCanceled(true);
            GuiOpenPacket.openGUI((EntityPlayerMP)event.entityPlayer, EnumGuiType.Script, 0, 0, 0);
            if (ConfigDebug.PlayerLogging && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
                LogWriter.script(String.format("[%s] (Player) %s OPEN NPC %s (%s, %s, %s) [%s]", "SCRIPTER", event.entityPlayer.func_70005_c_(), ((EntityNPCInterface)event.target).display.getName(), (int)event.target.field_70165_t, (int)event.target.field_70163_u, (int)event.target.field_70161_v, event.target.field_70170_p.func_72912_H().func_76065_j()));
            }
        } else if (item.func_77973_b() == CustomItems.mount) {
            if (!CustomNpcsPermissions.hasPermission(event.entityPlayer, CustomNpcsPermissions.TOOL_MOUNTER)) {
                return;
            }
            event.setCanceled(true);
            mounted = event.target;
            if (isRemote) {
                CustomNpcs.proxy.openGui(MathHelper.func_76128_c((double)ServerEventsHandler.mounted.field_70165_t), MathHelper.func_76128_c((double)ServerEventsHandler.mounted.field_70163_u), MathHelper.func_76128_c((double)ServerEventsHandler.mounted.field_70161_v), EnumGuiType.MobSpawnerMounter, event.entityPlayer);
            }
        } else if (item.func_77973_b() == CustomItems.wand && !isRemote && event.target instanceof EntityVillager) {
            if (!CustomNpcsPermissions.hasPermission(event.entityPlayer, CustomNpcsPermissions.EDIT_VILLAGER)) {
                return;
            }
            event.setCanceled(true);
            Merchant = (EntityVillager)event.target;
            if (!isRemote) {
                EntityPlayerMP player = (EntityPlayerMP)event.entityPlayer;
                player.openGui((Object)CustomNpcs.instance, EnumGuiType.MerchantAdd.ordinal(), player.field_70170_p, 0, 0, 0);
                MerchantRecipeList merchantrecipelist = Merchant.func_70934_b((EntityPlayer)player);
                if (merchantrecipelist != null) {
                    PacketHandler.Instance.sendToPlayer(new VillagerListPacket(merchantrecipelist), player);
                }
            }
        }
    }

    @SubscribeEvent
    public void barrierAbsorbDamage(LivingAttackEvent event) {
        if (event.entityLiving == null || event.entityLiving.field_70170_p == null || event.entityLiving.field_70170_p.field_72995_K) {
            return;
        }
        EntityEnergyBarrier barrier = EntityEnergyBarrier.getAbsorbingBarrier((Entity)event.entityLiving);
        if (barrier != null) {
            barrier.absorbDamage(event.ammount);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void domeItemBlacklist(PlayerInteractEvent event) {
        if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
            return;
        }
        EntityPlayer player = event.entityPlayer;
        if (player == null || player.field_70170_p == null || player.field_70170_p.field_72995_K) {
            return;
        }
        ItemStack held = player.func_70694_bm();
        if (held == null) {
            return;
        }
        String itemId = Item.field_150901_e.func_148750_c((Object)held.func_77973_b());
        if (itemId == null) {
            return;
        }
        String[] blacklist = ConfigEnergy.DomeItemBlacklist;
        if (blacklist == null) {
            return;
        }
        boolean blacklisted = false;
        for (String banned : blacklist) {
            if (!banned.equals(itemId)) continue;
            blacklisted = true;
            break;
        }
        if (!blacklisted) {
            return;
        }
        List<EntityEnergyBarrier> barriers = EntityEnergyBarrier.getActiveBarriers(player.field_70170_p);
        for (EntityEnergyBarrier barrier : barriers) {
            if (!(barrier instanceof EntityEnergyDome) || !barrier.isEntityInside((Entity)player)) continue;
            event.setCanceled(true);
            return;
        }
    }

    @SubscribeEvent
    public void partyDamagedEvent(LivingAttackEvent event) {
        if (event.source == null || !(event.source.func_76346_g() instanceof EntityPlayer) || !(event.entityLiving instanceof EntityPlayer) || FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            return;
        }
        if (ConfigMain.PartyFriendlyFireEnabled) {
            Party party;
            EntityPlayer sourcePlayer = (EntityPlayer)event.source.func_76346_g();
            PlayerData playerData = PlayerData.get(sourcePlayer);
            PlayerData targetData = PlayerData.get((EntityPlayer)event.entityLiving);
            if (playerData.partyUUID != null && playerData.partyUUID.equals(targetData.partyUUID) && (party = PartyController.Instance().getParty(playerData.partyUUID)) != null && !party.friendlyFire()) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void invoke(LivingHurtEvent event) {
        if (!(event.entityLiving instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer)event.entityLiving;
        if (event.source.func_76363_c() || event.source.func_76347_k()) {
            return;
        }
        if (!player.func_70632_aY()) {
            return;
        }
        ItemStack item = player.func_71045_bC();
        if (item == null || !(item.func_77973_b() instanceof ItemShield)) {
            return;
        }
        if (((ItemShield)item.func_77973_b()).material.getDamageVsEntity() < player.func_70681_au().nextInt(9)) {
            return;
        }
        float damage = (float)item.func_77960_j() + event.ammount;
        item.func_77972_a((int)event.ammount, (EntityLivingBase)player);
        if (damage > (float)item.func_77958_k()) {
            event.ammount = damage - (float)item.func_77958_k();
        } else {
            event.ammount = 0.0f;
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void invoke(PlayerInteractEvent event) {
        EntityPlayer player = event.entityPlayer;
        Block block = player.field_70170_p.func_147439_a(event.x, event.y, event.z);
        if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK && player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() == CustomItems.teleporter) {
            event.setCanceled(true);
        }
        if (block == Blocks.field_150462_ai && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && !player.field_70170_p.field_72995_K) {
            PacketHandler.Instance.sendToPlayer(new SyncPacket(EnumSyncType.WORKBENCH_RECIPES, EnumSyncAction.RELOAD, -1, SyncController.getCurrentRevision(EnumSyncType.WORKBENCH_RECIPES), SyncController.workbenchNBT()), (EntityPlayerMP)player);
        }
        if (block == CustomItems.carpentyBench && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && !player.field_70170_p.field_72995_K) {
            PacketHandler.Instance.sendToPlayer(new SyncPacket(EnumSyncType.CARPENTRY_RECIPES, EnumSyncAction.RELOAD, -1, SyncController.getCurrentRevision(EnumSyncType.CARPENTRY_RECIPES), SyncController.carpentryNBT()), (EntityPlayerMP)player);
        }
        if ((block == CustomItems.banner || block == CustomItems.wallBanner || block == CustomItems.sign) && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            ITileIcon tile;
            ItemStack item = player.field_71071_by.func_70448_g();
            if (item == null || item.func_77973_b() == null) {
                return;
            }
            int y = event.y;
            int meta = player.field_70170_p.func_72805_g(event.x, event.y, event.z);
            if (meta >= 7) {
                --y;
            }
            if (!(tile = (ITileIcon)player.field_70170_p.func_147438_o(event.x, y, event.z)).canEdit()) {
                if (item.func_77973_b() == CustomItems.wand && CustomNpcsPermissions.hasPermission(player, CustomNpcsPermissions.EDIT_BLOCKS)) {
                    tile.setTime(System.currentTimeMillis());
                    if (player.field_70170_p.field_72995_K) {
                        player.func_146105_b((IChatComponent)new ChatComponentTranslation("availability.editIcon", new Object[0]));
                    }
                }
                return;
            }
            if (!player.field_70170_p.field_72995_K) {
                tile.setIcon(item.func_77946_l());
                player.field_70170_p.func_147471_g(event.x, y, event.z);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void invoke(LivingDeathEvent event) {
        if (event.entityLiving.field_70170_p.field_72995_K) {
            return;
        }
        EnergyChargeTracker.Instance.removeAllForCaster(event.entityLiving.func_145782_y());
        if (event.source.func_76346_g() != null) {
            if (event.source.func_76346_g() instanceof EntityPlayer) {
                this.doExcalibur((EntityPlayer)event.source.func_76346_g(), event.entityLiving);
            }
            if (event.source.func_76346_g() instanceof EntityNPCInterface) {
                EntityNPCInterface npc = (EntityNPCInterface)event.source.func_76346_g();
                Line line = npc.advanced.getKillLine();
                if (line != null) {
                    npc.saySurrounding(line.formatTarget(event.entityLiving));
                }
                EventHooks.onNPCKilledEntity(npc, event.entityLiving);
            }
            EntityPlayer player = null;
            if (event.source.func_76346_g() instanceof EntityPlayer) {
                player = (EntityPlayer)event.source.func_76346_g();
            } else if (event.source.func_76346_g() instanceof EntityNPCInterface && ((EntityNPCInterface)event.source.func_76346_g()).advanced.role == EnumRoleType.Follower) {
                player = ((RoleFollower)((EntityNPCInterface)event.source.func_76346_g()).roleInterface).owner;
            }
            if (player != null) {
                this.doQuest(player, event.entityLiving, true);
                if (event.entityLiving instanceof EntityNPCInterface) {
                    this.doFactionPoints(player, (EntityNPCInterface)event.entityLiving);
                }
            }
        }
        if (event.entityLiving instanceof EntityPlayer) {
            PlayerData data = PlayerData.get((EntityPlayer)event.entityLiving);
            data.save();
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        if (event.entity.field_70170_p.field_72995_K) {
            return;
        }
        NBTTagCompound storedData = event.original.getEntityData().func_74775_l("CNPCStoredData");
        if (!storedData.func_82582_d()) {
            event.entityPlayer.getEntityData().func_74782_a("CNPCStoredData", (NBTBase)storedData);
        }
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.player == null || event.player.field_70170_p == null || event.player.field_70170_p.field_72995_K) {
            return;
        }
        PlayerData data = PlayerData.get(event.player);
        data.setGUIOpen(false);
        data.editingNpc = null;
    }

    private void doExcalibur(EntityPlayer player, EntityLivingBase entity) {
        ItemStack item = player.func_71045_bC();
        if (item == null || item.func_77973_b() != CustomItems.excalibur) {
            return;
        }
        PacketHandler.Instance.sendToPlayer(new SoundManagementPacket(EnumSoundOperation.PLAY_MUSIC, "customnpcs:songs.excalibur"), (EntityPlayerMP)player);
        player.func_145747_a((IChatComponent)new ChatComponentTranslation("<" + StatCollector.func_74838_a((String)(item.func_77973_b().func_77658_a() + ".name")) + "> " + ItemExcalibur.quotes[player.func_70681_au().nextInt(ItemExcalibur.quotes.length)], new Object[0]));
    }

    private void doFactionPoints(EntityPlayer player, EntityNPCInterface npc) {
        npc.advanced.factions.addPoints(player);
    }

    private void doQuest(EntityPlayer player, EntityLivingBase entity, boolean all) {
        PlayerData playerData = PlayerData.get(player);
        if (playerData == null) {
            return;
        }
        PlayerQuestData questData = playerData.questData;
        if (questData == null) {
            return;
        }
        boolean checkCompletion = false;
        String entityName = EntityList.func_75621_b((Entity)entity);
        if (entity instanceof EntityPlayer) {
            entityName = "Player";
        }
        Party party = playerData.getPlayerParty();
        Quest partyQuest = null;
        if (party != null && party.getQuestData() != null) {
            partyQuest = party.getQuestData().quest;
            if (partyQuest != null && (partyQuest.type == EnumQuestType.Kill || partyQuest.type == EnumQuestType.AreaKill)) {
                this.doPartyQuest(player, party, entity);
            } else {
                partyQuest = null;
            }
        }
        ArrayList<QuestData> activeQuestValues = new ArrayList<QuestData>(questData.activeQuests.values());
        for (QuestData data : activeQuestValues) {
            if (data.quest == null || data.quest.type != EnumQuestType.Kill && data.quest.type != EnumQuestType.AreaKill || partyQuest != null && partyQuest.getId() == data.quest.getId() || data.quest.partyOptions.allowParty && data.quest.partyOptions.onlyParty) continue;
            if (data.quest.type == EnumQuestType.AreaKill && all) {
                List list = player.field_70170_p.func_72872_a(EntityPlayer.class, entity.field_70121_D.func_72314_b(10.0, 10.0, 10.0));
                for (EntityPlayer pl : list) {
                    if (pl == player) continue;
                    this.doQuest(pl, entity, false);
                }
            }
            String name = entityName;
            QuestKill quest = (QuestKill)data.quest.questInterface;
            Class entityType = EntityNPCInterface.class;
            if (quest.targetType == 2) {
                try {
                    entityType = Class.forName(quest.customTargetType);
                }
                catch (ClassNotFoundException notFoundException) {
                    continue;
                }
            }
            if (quest.targetType > 0 && !entityType.isInstance(entity)) continue;
            if (entity.func_70005_c_() != null && quest.targets != null && quest.targets.containsKey(entity.func_70005_c_())) {
                name = entity.func_70005_c_();
            } else if (entity.func_70005_c_() == null || quest.targets == null || !quest.targets.containsKey(name)) continue;
            checkCompletion = true;
            HashMap<String, Integer> killed = quest.getKilled(data);
            if (!killed.containsKey(name)) {
                killed.put(name, 1);
            } else if (killed.get(name) < quest.targets.get(name)) {
                int amount = killed.get(name);
                killed.put(name, amount + 1);
            }
            quest.setKilled(data, killed);
            playerData.updateClient = true;
        }
        if (!checkCompletion) {
            return;
        }
        questData.checkQuestCompletion(playerData, EnumQuestType.Kill);
    }

    private void doPartyQuest(EntityPlayer player, Party party, EntityLivingBase entity) {
        PlayerData pdata = PlayerData.get(player);
        QuestData data = party.getQuestData();
        if (data == null) {
            return;
        }
        if (pdata == null) {
            return;
        }
        if (data.quest.type != EnumQuestType.Kill && data.quest.type != EnumQuestType.AreaKill) {
            return;
        }
        String name = EntityList.func_75621_b((Entity)entity);
        if (entity instanceof EntityPlayer) {
            name = "Player";
        }
        QuestKill quest = (QuestKill)data.quest.questInterface;
        if (data.quest.partyOptions.objectiveRequirement == EnumPartyObjectives.Leader && !party.getLeaderUUID().equals(player.func_110124_au())) {
            return;
        }
        Class entityType = EntityNPCInterface.class;
        if (quest.targetType == 2) {
            try {
                entityType = Class.forName(quest.customTargetType);
            }
            catch (ClassNotFoundException notFoundException) {
                return;
            }
        }
        if (quest.targetType > 0 && !entityType.isInstance(entity)) {
            return;
        }
        if (quest.targets.containsKey(entity.func_70005_c_())) {
            name = entity.func_70005_c_();
        } else if (!quest.targets.containsKey(name)) {
            return;
        }
        if (data.quest.partyOptions.objectiveRequirement == EnumPartyObjectives.All) {
            HashMap<String, Integer> killed = quest.getPlayerKilled(data, player.func_70005_c_());
            if (!killed.containsKey(name)) {
                killed.put(name, 1);
            } else if (killed.get(name) < quest.targets.get(name)) {
                int amount = killed.get(name);
                killed.put(name, amount + 1);
            }
            quest.setPlayerKilled(data, killed, player.func_70005_c_());
        } else {
            HashMap<String, Integer> killed = quest.getKilled(data);
            if (!killed.containsKey(name)) {
                killed.put(name, 1);
            } else if (killed.get(name) < quest.targets.get(name)) {
                int amount = killed.get(name);
                killed.put(name, amount + 1);
            }
            quest.setKilled(data, killed);
            pdata.updateClient = true;
        }
        PartyController.Instance().pingPartyQuestObjectiveUpdate(party);
        PartyController.Instance().checkQuestCompletion(party, EnumQuestType.Kill);
    }

    @SubscribeEvent
    public void world(PlayerEvent.SaveToFile event) {
        PlayerData data = PlayerData.get((EntityPlayer)event.entity);
        data.save();
    }

    @SubscribeEvent
    public void world(EntityJoinWorldEvent event) {
        if (event.world.field_72995_K || !(event.entity instanceof EntityPlayer)) {
            return;
        }
        PlayerData data = PlayerData.get((EntityPlayer)event.entity);
        data.updateCompanion(event.world);
    }

    @SubscribeEvent
    public void populateChunk(PopulateChunkEvent.Post event) {
        NPCSpawning.performWorldGenSpawning(event.world, event.chunkX << 4, event.chunkZ << 4, event.rand);
    }

    @SubscribeEvent
    public void playerTracking(PlayerEvent.StartTracking event) {
        AnimationData playerAnimData;
        if (event.target.field_70170_p.field_72995_K) {
            return;
        }
        if (event.target instanceof EntityLivingBase && event.entityPlayer instanceof EntityPlayerMP) {
            EnergyChargeTracker.Instance.sendToPlayer(event.target.func_145782_y(), (EntityPlayerMP)event.entityPlayer, (int)event.target.field_70170_p.func_82737_E());
        }
        if (!(event.target instanceof EntityPlayerMP) && !(event.target instanceof EntityNPCInterface)) {
            return;
        }
        AnimationData animationData = AnimationData.getData(event.target);
        if (animationData != null && animationData.isClientAnimating() && (playerAnimData = AnimationData.getData((Entity)event.entityPlayer)) != null) {
            Animation currentAnimation = animationData.currentClientAnimation;
            NBTTagCompound compound = currentAnimation.writeToNBT();
            playerAnimData.viewAnimation(currentAnimation, animationData, compound, animationData.isClientAnimating(), currentAnimation.currentFrame, currentAnimation.currentFrameTime);
        }
        if (event.target instanceof EntityNPCInterface) {
            MarkData data = MarkData.get((EntityNPCInterface)event.target);
            if (data.marks.isEmpty()) {
                return;
            }
            PacketHandler.Instance.sendToPlayer(new MarkDataPacket(event.target.func_145782_y(), data.getNBT()), (EntityPlayerMP)event.entityPlayer);
        }
    }

    @SubscribeEvent
    public void entityTick(LivingEvent.LivingUpdateEvent event) {
        AnimationData data = AnimationData.getData((Entity)event.entityLiving);
        if (data != null) {
            data.increaseTime();
        }
    }
}

