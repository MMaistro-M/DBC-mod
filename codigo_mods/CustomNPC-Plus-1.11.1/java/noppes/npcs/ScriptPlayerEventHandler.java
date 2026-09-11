/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$ItemPickupEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerChangedDimensionEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedInEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedOutEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerRespawnEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.ContainerPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.common.util.FakePlayer
 *  net.minecraftforge.event.ServerChatEvent
 *  net.minecraftforge.event.entity.EntityStruckByLightningEvent
 *  net.minecraftforge.event.entity.PlaySoundAtEntityEvent
 *  net.minecraftforge.event.entity.item.ItemTossEvent
 *  net.minecraftforge.event.entity.living.LivingAttackEvent
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingJumpEvent
 *  net.minecraftforge.event.entity.living.LivingFallEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.player.AchievementEvent
 *  net.minecraftforge.event.entity.player.ArrowLooseEvent
 *  net.minecraftforge.event.entity.player.ArrowNockEvent
 *  net.minecraftforge.event.entity.player.BonemealEvent
 *  net.minecraftforge.event.entity.player.EntityInteractEvent
 *  net.minecraftforge.event.entity.player.FillBucketEvent
 *  net.minecraftforge.event.entity.player.PlayerDropsEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 *  net.minecraftforge.event.entity.player.PlayerOpenContainerEvent
 *  net.minecraftforge.event.entity.player.PlayerPickupXpEvent
 *  net.minecraftforge.event.entity.player.PlayerSleepInBedEvent
 *  net.minecraftforge.event.entity.player.PlayerUseItemEvent$Finish
 *  net.minecraftforge.event.entity.player.PlayerUseItemEvent$Start
 *  net.minecraftforge.event.entity.player.PlayerUseItemEvent$Stop
 *  net.minecraftforge.event.entity.player.PlayerUseItemEvent$Tick
 *  net.minecraftforge.event.entity.player.PlayerWakeUpEvent
 *  net.minecraftforge.event.entity.player.UseHoeEvent
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 */
package noppes.npcs;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import kamkeel.npcs.addon.DBCAddon;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.AttributeController;
import kamkeel.npcs.controllers.SyncController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.enums.AbilityPhase;
import kamkeel.npcs.controllers.data.ability.type.AbilityCounter;
import kamkeel.npcs.controllers.data.ability.type.AbilityDefend;
import kamkeel.npcs.controllers.data.ability.type.AbilityDodge;
import kamkeel.npcs.controllers.data.ability.type.AbilityGuard;
import kamkeel.npcs.controllers.data.attribute.tracker.PlayerAttributeTracker;
import kamkeel.npcs.network.packets.data.ability.PlayerAbilitySyncPacket;
import kamkeel.npcs.util.AttributeAttackUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AchievementEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent;
import noppes.npcs.CustomNpcs;
import noppes.npcs.EventHooks;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.IBlock;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.ability.IAbility;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.item.IItemCustomizable;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.config.ConfigScript;
import noppes.npcs.constants.EnumQuestType;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.PartyController;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerDataScript;
import noppes.npcs.controllers.data.PlayerQuestData;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.items.ItemNpcTool;
import noppes.npcs.quests.QuestItem;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.event.player.PlayerEvent;
import noppes.npcs.scripted.item.ScriptCustomizableItem;

public class ScriptPlayerEventHandler {
    @SubscribeEvent
    public void onServerTick(TickEvent.PlayerTickEvent event) {
        if (event.player == null || event.player.field_70170_p == null) {
            return;
        }
        if (event.side == Side.SERVER && event.phase == TickEvent.Phase.START) {
            EntityPlayer player = event.player;
            if (player.field_70173_aa % 10 == 0) {
                PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(player);
                IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)player);
                EventHooks.onPlayerTick(handler, scriptPlayer);
                for (int i = 0; i < player.field_71071_by.func_70302_i_(); ++i) {
                    ItemStack item = player.field_71071_by.func_70301_a(i);
                    if (item == null || !NoppesUtilServer.isScriptableItem(item.func_77973_b())) continue;
                    IItemCustomizable isw = (IItemCustomizable)NpcAPI.Instance().getIItemStack(item);
                    EventHooks.onScriptItemUpdate(isw, (EntityLivingBase)player);
                }
                if (ConfigMain.AttributesEnabled) {
                    AttributeController.getTracker(player).updateIfChanged(player);
                }
            }
            if (PlayerDataController.Instance != null) {
                PlayerQuestData questData;
                boolean trackingPartyQuest;
                IAbility current;
                PlayerData playerData = PlayerData.get(player);
                playerData.actionManager.tick();
                playerData.abilityData.tick(player);
                if (playerData.abilityData.isMovementLocked() && ((current = playerData.abilityData.getCurrentAbility()) == null || !((Ability)current).hasAbilityMovement() || ((Ability)current).getPhase() != AbilityPhase.ACTIVE)) {
                    boolean sFlying = playerData.abilityData.wasFlyingAtLock() || AbilityController.Instance.isPlayerFlying(player);
                    player.field_70159_w = 0.0;
                    player.field_70181_x = !sFlying ? Math.min(player.field_70181_x, 0.0) : 0.0;
                    player.field_70179_y = 0.0;
                    player.field_70133_I = true;
                }
                if ((current = playerData.abilityData.getCurrentAbility()) != null && ((Ability)current).hasAbilityMovement() && ((Ability)current).getPhase() == AbilityPhase.ACTIVE) {
                    player.field_70701_bs = 0.0f;
                    player.field_70702_br = 0.0f;
                }
                if (playerData.updateClient) {
                    SyncController.syncPlayerData((EntityPlayerMP)player, true);
                    playerData.updateClient = false;
                }
                if (playerData.timers.size() > 0) {
                    playerData.timers.update();
                }
                if (player.field_70173_aa % 10 == 0) {
                    EntityPlayerMP mp = (EntityPlayerMP)player;
                    SyncController.syncEffects(mp);
                    SyncController.syncAbilityCooldowns(mp);
                    PlayerAbilitySyncPacket.sendToPlayer(mp);
                    playerData.abilityData.ensureLockStateClear();
                }
                if (player.field_70173_aa % 10 == 0) {
                    CustomEffectController.Instance.runEffects(player);
                }
                if (player.field_70173_aa % 20 == 0) {
                    CustomEffectController.Instance.decrementEffects(player);
                }
                Party party = playerData.getPlayerParty();
                boolean bl = trackingPartyQuest = playerData.questData.getTrackedQuest() != null && party != null && party.getQuest() != null && party.getQuest().getId() == playerData.questData.getTrackedQuest().getId();
                if (playerData.questData.getTrackedQuest() != null && !playerData.questData.activeQuests.containsKey(playerData.questData.getTrackedQuest().getId()) && !trackingPartyQuest) {
                    PlayerData.get((EntityPlayer)player).questData.untrackQuest();
                }
                if (player.field_70173_aa % 20 == 0 && !PlayerData.get((EntityPlayer)player).skinOverlays.overlayList.isEmpty()) {
                    PlayerData.get((EntityPlayer)player).skinOverlays.updateClient();
                }
                if (player.field_70173_aa % (ConfigMain.TrackedQuestUpdateFrequency * 20) == 0 && (questData = playerData.questData) != null && questData.getTrackedQuest() != null && ((Quest)questData.getTrackedQuest()).type == EnumQuestType.Item) {
                    if (trackingPartyQuest) {
                        NoppesUtilPlayer.sendPartyTrackedQuestData((EntityPlayerMP)event.player, party);
                    } else {
                        NoppesUtilPlayer.sendTrackedQuestData((EntityPlayerMP)event.player);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void invoke(EntityInteractEvent event) {
        if (event.entityPlayer == null || event.entityPlayer.field_70170_p == null) {
            return;
        }
        if (!event.entityPlayer.field_70170_p.field_72995_K && event.entityPlayer.field_70170_p instanceof WorldServer && event.entityPlayer instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.entityPlayer);
            IPlayer ip = NoppesUtilServer.getIPlayer(event.entityPlayer);
            PlayerEvent.RightClickEvent rightClickEvent = new PlayerEvent.RightClickEvent(ip, 1, NpcAPI.Instance().getIEntity(event.target));
            boolean rightClick = EventHooks.onPlayerRightClick(handler, rightClickEvent);
            PlayerEvent.InteractEvent ev = new PlayerEvent.InteractEvent(ip, 1, NpcAPI.Instance().getIEntity(event.target));
            boolean interact = EventHooks.onPlayerInteract(handler, ev);
            event.setCanceled(rightClick || interact);
        }
    }

    @SubscribeEvent
    public void invoke(PlayerInteractEvent event) {
        if (event.entityPlayer == null || event.entityPlayer.field_70170_p == null || event.entityPlayer.field_70170_p.field_72995_K || !(event.entityPlayer instanceof EntityPlayerMP)) {
            return;
        }
        if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
            return;
        }
        IPlayer ip = NoppesUtilServer.getIPlayer(event.entityPlayer);
        PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.entityPlayer);
        PlayerData pd = PlayerData.get(event.entityPlayer);
        if (pd == null) {
            return;
        }
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
            if (pd.hadInteract) {
                pd.hadInteract = false;
                return;
            }
            PlayerEvent.RightClickEvent rightClickEvent = new PlayerEvent.RightClickEvent(ip, 0, null);
            event.setCanceled(EventHooks.onPlayerRightClick(handler, rightClickEvent));
        } else if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            pd.hadInteract = true;
            IWorld iw = NpcAPI.Instance().getIWorld(event.world);
            IBlock blockCtx = NpcAPI.Instance().getIBlock(iw, event.x, event.y, event.z);
            PlayerEvent.RightClickEvent rightClickEvent = new PlayerEvent.RightClickEvent(ip, 2, blockCtx);
            event.setCanceled(EventHooks.onPlayerRightClick(handler, rightClickEvent));
        }
    }

    @SubscribeEvent
    public void invoke(ArrowNockEvent event) {
        if (event.entityPlayer == null || event.entityPlayer.field_70170_p == null) {
            return;
        }
        if (!event.entityPlayer.field_70170_p.field_72995_K && event.entityPlayer.field_70170_p instanceof WorldServer && event.entityPlayer instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.entityPlayer);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.entityPlayer);
            PlayerEvent.RangedChargeEvent ev = new PlayerEvent.RangedChargeEvent(scriptPlayer);
            EventHooks.onPlayerBowCharge(handler, ev);
        }
    }

    @SubscribeEvent
    public void invoke(ArrowLooseEvent event) {
        if (event.entityPlayer == null || event.entityPlayer.field_70170_p == null) {
            return;
        }
        if (!event.entityPlayer.field_70170_p.field_72995_K && event.entityPlayer.field_70170_p instanceof WorldServer && event.entityPlayer instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.entityPlayer);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.entityPlayer);
            PlayerEvent.RangedLaunchedEvent ev = new PlayerEvent.RangedLaunchedEvent(scriptPlayer, event.bow, event.charge);
            EventHooks.onPlayerRanged(handler, ev);
        }
    }

    @SubscribeEvent
    public void invoke(BlockEvent.BreakEvent event) {
        if (event.getPlayer() == null || event.getPlayer().field_70170_p == null) {
            return;
        }
        if (event.getPlayer().func_70694_bm() != null && event.getPlayer().func_70694_bm().func_77973_b() instanceof ItemNpcTool) {
            event.setCanceled(true);
            return;
        }
        if (!event.getPlayer().field_70170_p.field_72995_K && event.world instanceof WorldServer && event.getPlayer() instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.getPlayer());
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.getPlayer());
            PlayerEvent.BreakEvent ev = new PlayerEvent.BreakEvent(scriptPlayer, NpcAPI.Instance().getIBlock(NpcAPI.Instance().getIWorld(event.world), NpcAPI.Instance().getIPos(event.x, event.y, event.z)), event.getExpToDrop());
            event.setCanceled(EventHooks.onPlayerBreak(handler, ev));
            event.setExpToDrop(ev.exp);
        }
    }

    @SubscribeEvent
    public void invoke(PlayerUseItemEvent.Start event) {
        if (event.entityPlayer == null || event.entityPlayer.field_70170_p == null) {
            return;
        }
        if (event.entityPlayer.field_70170_p instanceof WorldServer && event.entityPlayer instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.entityPlayer);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.entityPlayer);
            event.setCanceled(EventHooks.onStartUsingItem(handler, scriptPlayer, event.duration, event.item));
        }
    }

    @SubscribeEvent
    public void invoke(PlayerUseItemEvent.Tick event) {
        if (event.entityPlayer == null || event.entityPlayer.field_70170_p == null) {
            return;
        }
        if (event.entityPlayer.field_70170_p instanceof WorldServer && event.entityPlayer instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.entityPlayer);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.entityPlayer);
            event.setCanceled(EventHooks.onUsingItem(handler, scriptPlayer, event.duration, event.item));
        }
    }

    @SubscribeEvent
    public void invoke(PlayerUseItemEvent.Stop event) {
        if (event.entityPlayer == null || event.entityPlayer.field_70170_p == null) {
            return;
        }
        if (event.entityPlayer.field_70170_p instanceof WorldServer && event.entityPlayer instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.entityPlayer);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.entityPlayer);
            event.setCanceled(EventHooks.onStopUsingItem(handler, scriptPlayer, event.duration, event.item));
        }
    }

    @SubscribeEvent
    public void invoke(PlayerUseItemEvent.Finish event) {
        if (event.entityPlayer == null || event.entityPlayer.field_70170_p == null) {
            return;
        }
        if (event.entityPlayer.field_70170_p instanceof WorldServer && event.entityPlayer instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.entityPlayer);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.entityPlayer);
            EventHooks.onFinishUsingItem(handler, scriptPlayer, event.duration, event.item);
        }
    }

    @SubscribeEvent
    public void invoke(PlayerDropsEvent event) {
        if (event.entityPlayer == null || event.entityPlayer.field_70170_p == null) {
            return;
        }
        if (event.entityPlayer.field_70170_p instanceof WorldServer && event.entityPlayer instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.entityPlayer);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.entityPlayer);
            event.setCanceled(EventHooks.onPlayerDropItems(handler, scriptPlayer, event.drops));
        }
    }

    @SubscribeEvent
    public void invoke(PlayerPickupXpEvent event) {
        if (event.entityPlayer == null || event.entityPlayer.field_70170_p == null) {
            return;
        }
        if (event.entityPlayer.field_70170_p instanceof WorldServer && event.entityPlayer instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.entityPlayer);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.entityPlayer);
            EventHooks.onPlayerPickupXP(handler, scriptPlayer, event.orb);
        }
    }

    @SubscribeEvent
    public void invoke(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.player == null || event.player.field_70170_p == null) {
            return;
        }
        if (event.player.field_70170_p instanceof WorldServer && event.player instanceof EntityPlayerMP) {
            PlayerData playerData = PlayerData.get(event.player);
            if (playerData != null) {
                playerData.abilityData.resetOnDimensionChange();
            }
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.player);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.player);
            EventHooks.onPlayerChangeDim(handler, scriptPlayer, event.fromDim, event.toDim);
        }
    }

    @SubscribeEvent
    public void invoke(PlayerEvent.ItemPickupEvent event) {
        if (event.player == null || event.player.field_70170_p == null) {
            return;
        }
        if (!event.player.field_70170_p.field_72995_K && !(event.player instanceof FakePlayer)) {
            PlayerData playerData = PlayerData.get(event.player);
            PlayerQuestData questData = playerData.questData;
            QuestItem.pickedUp = event.pickedUp.func_92059_d();
            Party playerParty = playerData.getPlayerParty();
            if (playerParty != null) {
                QuestItem.pickedUpParty = event.pickedUp.func_92059_d();
                QuestItem.pickedUpPlayer = event.player;
                PartyController.Instance().checkQuestCompletion(playerParty, EnumQuestType.Item);
            }
            questData.checkQuestCompletion(playerData, EnumQuestType.Item);
        }
        if (event.player.field_70170_p instanceof WorldServer && event.player instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.player);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.player);
            EventHooks.onPlayerPickUp(handler, scriptPlayer, event.pickedUp);
        }
    }

    @SubscribeEvent
    public void invoke(PlayerOpenContainerEvent event) {
        if (event.entityPlayer == null || event.entityPlayer.field_70170_p == null) {
            return;
        }
        if (event.entityPlayer.field_70170_p instanceof WorldServer && !(event.entityPlayer.field_71070_bA instanceof ContainerPlayer) && event.entityPlayer instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.entityPlayer);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.entityPlayer);
            EventHooks.onPlayerContainerOpen(handler, scriptPlayer, event.entityPlayer.field_71070_bA);
        }
    }

    @SubscribeEvent
    public void invoke(UseHoeEvent event) {
        if (event.entityPlayer == null || event.entityPlayer.field_70170_p == null) {
            return;
        }
        if (event.entityPlayer.field_70170_p instanceof WorldServer && event.entityPlayer instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.entityPlayer);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.entityPlayer);
            EventHooks.onPlayerUseHoe(handler, scriptPlayer, event.current, event.x, event.y, event.z);
        }
    }

    @SubscribeEvent
    public void invoke(PlayerSleepInBedEvent event) {
        if (event.entityPlayer == null || event.entityPlayer.field_70170_p == null) {
            return;
        }
        if (event.entityPlayer.field_70170_p instanceof WorldServer && event.entityPlayer instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.entityPlayer);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.entityPlayer);
            EventHooks.onPlayerSleep(handler, scriptPlayer, event.x, event.y, event.z);
        }
    }

    @SubscribeEvent
    public void invoke(PlayerWakeUpEvent event) {
        if (event.entityPlayer == null || event.entityPlayer.field_70170_p == null) {
            return;
        }
        if (event.entityPlayer.field_70170_p instanceof WorldServer && event.entityPlayer instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.entityPlayer);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.entityPlayer);
            EventHooks.onPlayerWakeUp(handler, scriptPlayer, event.setSpawn);
        }
    }

    @SubscribeEvent
    public void invoke(FillBucketEvent event) {
        if (event.entityPlayer == null || event.entityPlayer.field_70170_p == null) {
            return;
        }
        if (event.entityPlayer.field_70170_p instanceof WorldServer && event.entityPlayer instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.entityPlayer);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.entityPlayer);
            EventHooks.onPlayerFillBucket(handler, scriptPlayer, event.current, event.result);
        }
    }

    @SubscribeEvent
    public void invoke(BonemealEvent event) {
        if (event.entityPlayer == null || event.entityPlayer.field_70170_p == null) {
            return;
        }
        if (event.entityPlayer.field_70170_p instanceof WorldServer && event.entityPlayer instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.entityPlayer);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.entityPlayer);
            EventHooks.onPlayerBonemeal(handler, scriptPlayer, event.x, event.y, event.z, event.world);
        }
    }

    @SubscribeEvent
    public void invoke(AchievementEvent event) {
        if (event.entityPlayer == null || event.entityPlayer.field_70170_p == null) {
            return;
        }
        if (event.entityPlayer.field_70170_p instanceof WorldServer && event.entityPlayer instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.entityPlayer);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.entityPlayer);
            EventHooks.onPlayerAchievement(handler, scriptPlayer, CustomNpcs.proxy.getAchievementDesc(event.achievement));
        }
    }

    @SubscribeEvent
    public void invoke(ItemTossEvent event) {
        if (event.player == null || event.player.field_70170_p == null) {
            return;
        }
        if (event.player.field_70170_p instanceof WorldServer && event.player instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.player);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.player);
            event.setCanceled(EventHooks.onPlayerToss(handler, scriptPlayer, event.entityItem));
        }
    }

    @SubscribeEvent
    public void invoke(LivingFallEvent event) {
        if (event.entityLiving == null || event.entityLiving.field_70170_p == null) {
            return;
        }
        if (event.entityLiving.field_70170_p instanceof WorldServer && event.entityLiving instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts((EntityPlayer)event.entityLiving);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.entityLiving);
            event.setCanceled(EventHooks.onPlayerFall(handler, scriptPlayer, event.distance));
        }
    }

    @SubscribeEvent
    public void invoke(LivingEvent.LivingJumpEvent event) {
        if (event.entityLiving == null || event.entityLiving.field_70170_p == null) {
            return;
        }
        if (event.entityLiving.field_70170_p instanceof WorldServer && event.entityLiving instanceof EntityPlayerMP) {
            PlayerData playerData = PlayerData.get((EntityPlayer)event.entityLiving);
            if (playerData != null && playerData.abilityData.isMovementLocked()) {
                event.entityLiving.field_70181_x = 0.0;
                event.entityLiving.field_70133_I = true;
            }
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts((EntityPlayer)event.entityLiving);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.entityLiving);
            EventHooks.onPlayerJump(handler, scriptPlayer);
        }
    }

    @SubscribeEvent
    public void invoke(EntityStruckByLightningEvent event) {
        if (event.entity == null || event.entity.field_70170_p == null) {
            return;
        }
        if (event.entity.field_70170_p instanceof WorldServer && event.entity instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts((EntityPlayer)event.entity);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity(event.entity);
            EventHooks.onPlayerLightning(handler, scriptPlayer);
        }
    }

    @SubscribeEvent
    public void invoke(PlaySoundAtEntityEvent event) {
        if (event.entity == null || event.entity.field_70170_p == null) {
            return;
        }
        if (event.entity.field_70170_p instanceof WorldServer && event.entity instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts((EntityPlayer)event.entity);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity(event.entity);
            EventHooks.onPlayerSound(handler, scriptPlayer, event.name, event.pitch, event.volume);
        }
    }

    @SubscribeEvent
    public void invoke(LivingDeathEvent event) {
        if (event.entityLiving == null || event.entityLiving.field_70170_p == null) {
            return;
        }
        if (event.entityLiving.field_70170_p instanceof WorldServer) {
            IPlayer scriptPlayer2;
            PlayerDataScript handler;
            Entity source = NoppesUtilServer.GetDamageSource(event.source);
            if (event.entityLiving instanceof EntityPlayerMP) {
                handler = ScriptController.Instance.getPlayerScripts((EntityPlayer)event.entityLiving);
                try {
                    CustomEffectController.getInstance().killEffects((EntityPlayer)event.entityLiving);
                    scriptPlayer2 = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.entityLiving);
                    EventHooks.onPlayerDeath(handler, scriptPlayer2, event.source, source);
                }
                catch (Exception scriptPlayer2) {
                    // empty catch block
                }
                EntityPlayer player = (EntityPlayer)event.entityLiving;
                PlayerData playerData = PlayerData.get(player);
                playerData.abilityData.interruptCurrentAbility();
                if (ConfigScript.ClearActionsOnDeath) {
                    playerData.actionManager.clear();
                }
            }
            if (event.source.func_76346_g() instanceof EntityPlayerMP) {
                handler = ScriptController.Instance.getPlayerScripts((EntityPlayer)event.source.func_76346_g());
                scriptPlayer2 = (IPlayer)NpcAPI.Instance().getIEntity(event.source.func_76346_g());
                EventHooks.onPlayerKills(handler, scriptPlayer2, event.entityLiving);
            }
        }
    }

    @SubscribeEvent
    public void invoke(LivingAttackEvent event) {
        if (event.entityLiving == null || event.entityLiving.field_70170_p == null) {
            return;
        }
        if (event.entityLiving.field_70170_p instanceof WorldServer) {
            EntityLivingBase attacker;
            float result;
            AbilityDefend defend;
            PlayerData pData;
            boolean cancel = event.isCanceled();
            Entity source = NoppesUtilServer.GetDamageSource(event.source);
            if (event.entityLiving instanceof EntityPlayerMP) {
                PlayerDataScript handler = ScriptController.Instance.getPlayerScripts((EntityPlayer)event.entityLiving);
                PlayerEvent.AttackedEvent pevent = new PlayerEvent.AttackedEvent((IPlayer)NpcAPI.Instance().getIEntity((Entity)((EntityPlayer)event.entityLiving)), source, event.ammount, event.source);
                cancel = EventHooks.onPlayerAttacked(handler, pevent);
            }
            if (event.source.func_76346_g() instanceof EntityPlayerMP) {
                ScriptCustomizableItem sci;
                int attackSpeed;
                IItemStack itemStack;
                EntityPlayerMP playerMP = (EntityPlayerMP)event.source.func_76346_g();
                if (playerMP.func_70694_bm() != null && (itemStack = NpcAPI.Instance().getIItemStack(playerMP.func_70694_bm())) instanceof ScriptCustomizableItem && event.entityLiving.field_70172_ad <= event.entityLiving.field_70771_an - (attackSpeed = (sci = (ScriptCustomizableItem)itemStack).getAttackSpeed())) {
                    event.entityLiving.field_70172_ad = 0;
                }
                PlayerDataScript handler = ScriptController.Instance.getPlayerScripts((EntityPlayer)event.source.func_76346_g());
                float attackAmount = event.ammount;
                AttributeAttackUtil.lastAttackCritted = null;
                if (ConfigMain.AttributesEnabled && !DBCAddon.IsAvailable()) {
                    EntityPlayer attackPlayer = (EntityPlayer)event.source.func_76346_g();
                    attackAmount = AttributeAttackUtil.calculateOutgoing(attackPlayer, attackAmount);
                    if (!(event.entityLiving instanceof EntityPlayerMP) && !(event.entityLiving instanceof EntityNPCInterface)) {
                        PlayerAttributeTracker tracker = AttributeController.getTracker(attackPlayer);
                        if (AttributeAttackUtil.rollCrit(tracker)) {
                            AttributeAttackUtil.lastAttackCritted = true;
                            attackAmount = AttributeAttackUtil.applyCritDamage(attackAmount, tracker);
                        } else {
                            AttributeAttackUtil.lastAttackCritted = false;
                        }
                    }
                }
                PlayerEvent.AttackEvent pevent1 = new PlayerEvent.AttackEvent((IPlayer)NpcAPI.Instance().getIEntity((Entity)((EntityPlayer)event.source.func_76346_g())), (Entity)event.entityLiving, attackAmount, event.source);
                boolean bl = cancel = cancel || EventHooks.onPlayerAttack(handler, pevent1);
            }
            if (!cancel && event.entityLiving instanceof EntityPlayerMP && (pData = PlayerData.get((EntityPlayer)event.entityLiving)) != null && pData.abilityData != null && ((defend = pData.abilityData.getActiveDefend()) instanceof AbilityDodge || defend instanceof AbilityCounter) && (result = defend.onDefend(attacker = source instanceof EntityLivingBase ? (EntityLivingBase)source : null, event.source, event.ammount)) != event.ammount) {
                cancel = true;
            }
            event.setCanceled(cancel);
        }
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void invoke(LivingHurtEvent event) {
        if (event.entityLiving == null || event.entityLiving.field_70170_p == null) {
            return;
        }
        if (event.entityLiving.field_70170_p instanceof WorldServer) {
            boolean cancel = event.isCanceled();
            Entity source = NoppesUtilServer.GetDamageSource(event.source);
            if (ConfigMain.AttributesEnabled && !DBCAddon.IsAvailable()) {
                if (event.entityLiving instanceof EntityPlayerMP && source instanceof EntityPlayerMP) {
                    event.ammount = AttributeAttackUtil.calculateDamagePlayerToPlayer((EntityPlayer)source, (EntityPlayer)event.entityLiving, event.ammount);
                } else if (!(event.entityLiving instanceof EntityNPCInterface) && source instanceof EntityPlayer) {
                    event.ammount = AttributeAttackUtil.calculateOutgoing((EntityPlayer)source, event.ammount);
                    if (AttributeAttackUtil.lastAttackCritted != null) {
                        if (AttributeAttackUtil.lastAttackCritted.booleanValue()) {
                            event.ammount = AttributeAttackUtil.applyCritDamage(event.ammount, AttributeController.getTracker((EntityPlayer)source));
                        }
                        AttributeAttackUtil.lastAttackCritted = null;
                    } else {
                        event.ammount = AttributeAttackUtil.applyCrit(event.ammount, AttributeController.getTracker((EntityPlayer)source));
                    }
                }
            }
            if (event.entityLiving instanceof EntityPlayerMP) {
                PlayerData pData = PlayerData.get((EntityPlayer)event.entityLiving);
                if (pData != null && pData.abilityData != null && pData.abilityData.isExecutingAbility()) {
                    if (pData.abilityData.isCurrentAbilityInvulnerable()) {
                        event.ammount = 0.0f;
                        cancel = true;
                    } else {
                        float modified;
                        AbilityDefend defend = pData.abilityData.getActiveDefend();
                        if (defend instanceof AbilityGuard) {
                            EntityLivingBase attacker = source instanceof EntityLivingBase ? (EntityLivingBase)source : null;
                            event.ammount = defend.onDefend(attacker, event.source, event.ammount);
                        }
                        event.ammount = modified = pData.abilityData.onDamage(event.source, event.ammount);
                    }
                }
                PlayerDataScript handler = ScriptController.Instance.getPlayerScripts((EntityPlayer)event.entityLiving);
                PlayerEvent.DamagedEvent pevent = new PlayerEvent.DamagedEvent((IPlayer)NpcAPI.Instance().getIEntity((Entity)((EntityPlayer)event.entityLiving)), source, event.ammount, event.source);
                cancel = cancel || EventHooks.onPlayerDamaged(handler, pevent);
                event.ammount = pevent.damage;
            }
            if (source instanceof EntityPlayerMP) {
                PlayerDataScript handler = ScriptController.Instance.getPlayerScripts((EntityPlayer)source);
                PlayerEvent.DamagedEntityEvent pevent1 = new PlayerEvent.DamagedEntityEvent((IPlayer)NpcAPI.Instance().getIEntity(source), (Entity)event.entityLiving, event.ammount, event.source);
                cancel = cancel || EventHooks.onPlayerDamagedEntity(handler, pevent1);
                event.ammount = pevent1.damage;
            }
            event.setCanceled(cancel);
        }
    }

    @SubscribeEvent
    public void invoke(PlayerEvent.PlayerRespawnEvent event) {
        if (event.player == null || event.player.field_70170_p == null) {
            return;
        }
        if (event.player.field_70170_p instanceof WorldServer && event.player instanceof EntityPlayerMP) {
            PlayerData playerData = PlayerData.get(event.player);
            if (playerData != null) {
                playerData.abilityData.resetOnRespawn();
            }
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.player);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.player);
            EventHooks.onPlayerRespawn(handler, scriptPlayer);
        }
    }

    @SubscribeEvent
    public void invoke(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.player == null || event.player.field_70170_p == null) {
            return;
        }
        if (event.player.field_70170_p instanceof WorldServer && event.player instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.player);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.player);
            EventHooks.onPlayerLogin(handler, scriptPlayer);
            PlayerData playerData = PlayerData.get(event.player);
            Quest quest = (Quest)playerData.questData.getTrackedQuest();
            if (quest != null) {
                Party party = playerData.getPlayerParty();
                if (party != null && party.getQuest() != null && party.getQuest().getId() == quest.getId()) {
                    NoppesUtilPlayer.sendPartyTrackedQuestData((EntityPlayerMP)event.player, party);
                } else {
                    NoppesUtilPlayer.sendTrackedQuestData((EntityPlayerMP)event.player);
                }
            }
            PlayerData.get((EntityPlayer)event.player).skinOverlays.updateClient();
        }
    }

    @SubscribeEvent
    public void invoke(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.player == null || event.player.field_70170_p == null) {
            return;
        }
        if (event.player.field_70170_p instanceof WorldServer && event.player instanceof EntityPlayerMP) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(event.player);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.player);
            AttributeController.removeTracker(event.player.func_110124_au());
            EventHooks.onPlayerLogout(handler, scriptPlayer);
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void invoke(ServerChatEvent event) {
        if (event.player == null || event.player.field_70170_p == null) {
            return;
        }
        if (event.player.field_70170_p instanceof WorldServer && !event.player.equals((Object)EntityNPCInterface.chateventPlayer)) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts((EntityPlayer)event.player);
            IPlayer scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)event.player);
            String message = event.message;
            PlayerEvent.ChatEvent ev = new PlayerEvent.ChatEvent(scriptPlayer, event.message);
            EventHooks.onPlayerChat(handler, ev);
            event.setCanceled(ev.isCanceled());
            if (!message.equals(ev.message)) {
                ChatComponentTranslation chat = new ChatComponentTranslation("", new Object[0]);
                chat.func_150257_a(ForgeHooks.newChatWithLinks((String)ev.message));
                event.component = chat;
            }
        }
    }
}

