/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import noppes.npcs.api.event.IAbilityEvent;
import noppes.npcs.api.event.IAnimationEvent;
import noppes.npcs.api.event.IAuctionEvent;
import noppes.npcs.api.event.IBlockEvent;
import noppes.npcs.api.event.IChainEvent;
import noppes.npcs.api.event.ICustomGuiEvent;
import noppes.npcs.api.event.ICustomNPCsEvent;
import noppes.npcs.api.event.IDialogEvent;
import noppes.npcs.api.event.IEnergyBarrierEvent;
import noppes.npcs.api.event.IEnergyProjectileEvent;
import noppes.npcs.api.event.IFactionEvent;
import noppes.npcs.api.event.IForgeEvent;
import noppes.npcs.api.event.IItemEvent;
import noppes.npcs.api.event.ILinkedItemEvent;
import noppes.npcs.api.event.INpcEvent;
import noppes.npcs.api.event.IPartyEvent;
import noppes.npcs.api.event.IPlayerEvent;
import noppes.npcs.api.event.IProjectileEvent;
import noppes.npcs.api.event.IQuestEvent;
import noppes.npcs.api.event.IRecipeEvent;
import noppes.npcs.api.handler.IHookDefinition;
import noppes.npcs.api.handler.IScriptHookHandler;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.constants.ScriptContext;
import noppes.npcs.controllers.HookDefinition;
import noppes.npcs.controllers.data.RecipeScript;

public class ScriptHookController
implements IScriptHookHandler {
    public static ScriptHookController Instance;
    private final Map<String, Map<String, HookDefinition>> hookDefinitions = new HashMap<String, Map<String, HookDefinition>>();
    private int hookRevision = 0;

    public ScriptHookController() {
        Instance = this;
        this.initializeBuiltInHooks();
    }

    private void initializeBuiltInHooks() {
        for (ScriptContext context : ScriptContext.values()) {
            if (context.hookContext.isEmpty()) continue;
            this.hookDefinitions.put(context.hookContext, new LinkedHashMap());
        }
        this.initializeNpcHooks();
        this.initializePlayerHooks();
        this.initializeBlockHooks();
        this.initializeItemHooks();
        this.initializeLinkedItemHooks();
        this.initializeRecipeHooks();
        this.initializeEffectHooks();
        this.initializeAbilityHooks();
        this.initializeForgeHooks();
    }

    private void initializeNpcHooks() {
        this.hook(ScriptContext.NPC, EnumScriptType.INIT, INpcEvent.InitEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.TICK, INpcEvent.UpdateEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.TIMER, INpcEvent.TimerEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.INTERACT, INpcEvent.InteractEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.DIALOG, INpcEvent.DialogEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.DIALOG_CLOSE, INpcEvent.DialogClosedEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.COLLIDE, INpcEvent.CollideEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.DAMAGED, INpcEvent.DamagedEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.KILLED, INpcEvent.DiedEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.KILLS, INpcEvent.KilledEntityEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ATTACK_MELEE, INpcEvent.MeleeAttackEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ATTACK_SWING, INpcEvent.SwingEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.RANGED_LAUNCHED, INpcEvent.RangedLaunchedEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.TARGET, INpcEvent.TargetEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.TARGET_LOST, INpcEvent.TargetLostEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.PROJECTILE_TICK, IProjectileEvent.UpdateEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.PROJECTILE_IMPACT, IProjectileEvent.ImpactEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ENERGY_PROJECTILE_FIRED, IEnergyProjectileEvent.FiredEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ENERGY_PROJECTILE_TICK, IEnergyProjectileEvent.UpdateEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ENERGY_PROJECTILE_ENTITY_IMPACT, IEnergyProjectileEvent.EntityImpactEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ENERGY_PROJECTILE_BLOCK_IMPACT, IEnergyProjectileEvent.BlockImpactEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ENERGY_PROJECTILE_EXPIRED, IEnergyProjectileEvent.ExpiredEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ABILITY_START, IAbilityEvent.StartEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ABILITY_EXECUTE, IAbilityEvent.ExecuteEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ABILITY_HIT, IAbilityEvent.HitEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ABILITY_TICK, IAbilityEvent.TickEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ABILITY_INTERRUPT, IAbilityEvent.InterruptEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ABILITY_COMPLETE, IAbilityEvent.CompleteEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ABILITY_TOGGLE, IAbilityEvent.ToggleEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ABILITY_TOGGLE_TICK, IAbilityEvent.ToggleUpdateEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.CHAIN_START, IChainEvent.StartEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.CHAIN_NEXT, IChainEvent.NextEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.CHAIN_COMPLETE, IChainEvent.CompleteEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.CHAIN_INTERRUPT, IChainEvent.InterruptEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ENERGY_BARRIER_SPAWNED, IEnergyBarrierEvent.SpawnedEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ENERGY_BARRIER_TICK, IEnergyBarrierEvent.UpdateEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ENERGY_BARRIER_HIT, IEnergyBarrierEvent.HitEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ENERGY_BARRIER_DESTROYED, IEnergyBarrierEvent.DestroyedEvent.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ANIMATION_START, IAnimationEvent.Started.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ANIMATION_END, IAnimationEvent.Ended.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ANIMATION_FRAME_ENTER, IAnimationEvent.IFrameEvent.Entered.class);
        this.hook(ScriptContext.NPC, EnumScriptType.ANIMATION_FRAME_EXIT, IAnimationEvent.IFrameEvent.Exited.class);
    }

    private void initializePlayerHooks() {
        this.hook(ScriptContext.PLAYER, EnumScriptType.INIT, IPlayerEvent.InitEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.TICK, IPlayerEvent.UpdateEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.TIMER, IPlayerEvent.TimerEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ATTACK, IPlayerEvent.AttackEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ATTACKED, IPlayerEvent.AttackedEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.DAMAGED, IPlayerEvent.DamagedEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.DAMAGED_ENTITY, IPlayerEvent.DamagedEntityEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.KILLS, IPlayerEvent.KilledEntityEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.KILLED, IPlayerEvent.DiedEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.INTERACT, IPlayerEvent.InteractEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.RIGHT_CLICK, IPlayerEvent.RightClickEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.BREAK_BLOCK, IPlayerEvent.BreakEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.CHAT, IPlayerEvent.ChatEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.LOGIN, IPlayerEvent.LoginEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.LOGOUT, IPlayerEvent.LogoutEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.RESPAWN, IPlayerEvent.RespawnEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.KEY_PRESSED, IPlayerEvent.KeyPressedEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.MOUSE_CLICKED, IPlayerEvent.MouseClickedEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.PICKUP, IPlayerEvent.PickUpEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.TOSS, IPlayerEvent.TossEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.DROP, IPlayerEvent.DropEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.PICKUP_XP, IPlayerEvent.PickupXPEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.START_USING_ITEM, IPlayerEvent.StartUsingItem.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.USING_ITEM, IPlayerEvent.UsingItem.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.STOP_USING_ITEM, IPlayerEvent.StopUsingItem.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.FINISH_USING_ITEM, IPlayerEvent.FinishUsingItem.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.CONTAINER_OPEN, IPlayerEvent.ContainerOpen.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.JUMP, IPlayerEvent.JumpEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.FALL, IPlayerEvent.FallEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.CHANGED_DIM, IPlayerEvent.ChangedDimension.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.RANGED_CHARGE, IPlayerEvent.RangedChargeEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.RANGED_LAUNCHED, IPlayerEvent.RangedLaunchedEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.USE_HOE, IPlayerEvent.UseHoeEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.BONEMEAL, IPlayerEvent.BonemealEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.FILL_BUCKET, IPlayerEvent.FillBucketEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.WAKE_UP, IPlayerEvent.WakeUpEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.SLEEP, IPlayerEvent.SleepEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.PLAYSOUND, IPlayerEvent.SoundEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.LIGHTNING, IPlayerEvent.LightningEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.SCRIPT_COMMAND, ICustomNPCsEvent.ScriptedCommandEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.QUEST_START, IQuestEvent.QuestStartEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.QUEST_COMPLETED, IQuestEvent.QuestCompletedEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.QUEST_TURNIN, IQuestEvent.QuestTurnedInEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.DIALOG_OPEN, IDialogEvent.DialogOpen.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.DIALOG_OPTION, IDialogEvent.DialogOption.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.DIALOG_CLOSE, IDialogEvent.DialogClosed.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.FACTION_POINTS, IFactionEvent.FactionPoints.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.CUSTOM_GUI_CLOSED, ICustomGuiEvent.CloseEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.CUSTOM_GUI_BUTTON, ICustomGuiEvent.ButtonEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.CUSTOM_GUI_SLOT, ICustomGuiEvent.SlotEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.CUSTOM_GUI_SLOT_CLICKED, ICustomGuiEvent.SlotClickEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.CUSTOM_GUI_SCROLL, ICustomGuiEvent.ScrollEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.CUSTOM_GUI_TEXTFIELD, ICustomGuiEvent.UnfocusedEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.PARTY_QUEST_COMPLETED, IPartyEvent.PartyQuestCompletedEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.PARTY_QUEST_SET, IPartyEvent.PartyQuestSetEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.PARTY_QUEST_TURNED_IN, IPartyEvent.PartyQuestTurnedInEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.PARTY_INVITE, IPartyEvent.PartyInviteEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.PARTY_KICK, IPartyEvent.PartyKickEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.PARTY_LEAVE, IPartyEvent.PartyLeaveEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.PARTY_DISBAND, IPartyEvent.PartyDisbandEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ANIMATION_START, IAnimationEvent.Started.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ANIMATION_END, IAnimationEvent.Ended.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ANIMATION_FRAME_ENTER, IAnimationEvent.IFrameEvent.Entered.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ANIMATION_FRAME_EXIT, IAnimationEvent.IFrameEvent.Exited.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ENERGY_PROJECTILE_FIRED, IEnergyProjectileEvent.FiredEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ENERGY_PROJECTILE_TICK, IEnergyProjectileEvent.UpdateEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ENERGY_PROJECTILE_ENTITY_IMPACT, IEnergyProjectileEvent.EntityImpactEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ENERGY_PROJECTILE_BLOCK_IMPACT, IEnergyProjectileEvent.BlockImpactEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ENERGY_PROJECTILE_EXPIRED, IEnergyProjectileEvent.ExpiredEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ENERGY_BARRIER_SPAWNED, IEnergyBarrierEvent.SpawnedEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ENERGY_BARRIER_TICK, IEnergyBarrierEvent.UpdateEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ENERGY_BARRIER_HIT, IEnergyBarrierEvent.HitEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ENERGY_BARRIER_DESTROYED, IEnergyBarrierEvent.DestroyedEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ABILITY_START, IAbilityEvent.StartEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ABILITY_EXECUTE, IAbilityEvent.ExecuteEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ABILITY_HIT, IAbilityEvent.HitEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ABILITY_TICK, IAbilityEvent.TickEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ABILITY_INTERRUPT, IAbilityEvent.InterruptEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ABILITY_COMPLETE, IAbilityEvent.CompleteEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ABILITY_TOGGLE, IAbilityEvent.ToggleEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ABILITY_TOGGLE_TICK, IAbilityEvent.ToggleUpdateEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.CHAIN_START, IChainEvent.StartEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.CHAIN_NEXT, IChainEvent.NextEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.CHAIN_COMPLETE, IChainEvent.CompleteEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.CHAIN_INTERRUPT, IChainEvent.InterruptEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.AUCTION_CREATE, IAuctionEvent.CreateEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.AUCTION_BID, IAuctionEvent.BidEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.AUCTION_BUYOUT, IAuctionEvent.BuyoutEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.AUCTION_CANCEL, IAuctionEvent.CancelEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.AUCTION_CLAIM, IAuctionEvent.ClaimEvent.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.PROFILE_CHANGE, IPlayerEvent.ProfileEvent.Changed.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.PROFILE_REMOVE, IPlayerEvent.ProfileEvent.Removed.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.PROFILE_CREATE, IPlayerEvent.ProfileEvent.Create.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ON_EFFECT_ADD, IPlayerEvent.EffectEvent.Added.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ON_EFFECT_TICK, IPlayerEvent.EffectEvent.Ticked.class);
        this.hook(ScriptContext.PLAYER, EnumScriptType.ON_EFFECT_REMOVE, IPlayerEvent.EffectEvent.Removed.class);
    }

    private void initializeBlockHooks() {
        this.hook(ScriptContext.BLOCK, EnumScriptType.INIT, IBlockEvent.InitEvent.class);
        this.hook(ScriptContext.BLOCK, EnumScriptType.TICK, IBlockEvent.UpdateEvent.class);
        this.hook(ScriptContext.BLOCK, EnumScriptType.INTERACT, IBlockEvent.InteractEvent.class);
        this.hook(ScriptContext.BLOCK, EnumScriptType.FALLEN_UPON, IBlockEvent.EntityFallenUponEvent.class);
        this.hook(ScriptContext.BLOCK, EnumScriptType.REDSTONE, IBlockEvent.RedstoneEvent.class);
        this.hook(ScriptContext.BLOCK, EnumScriptType.BROKEN, IBlockEvent.BreakEvent.class);
        this.hook(ScriptContext.BLOCK, EnumScriptType.EXPLODED, IBlockEvent.ExplodedEvent.class);
        this.hook(ScriptContext.BLOCK, EnumScriptType.RAIN_FILLED, IBlockEvent.RainFillEvent.class);
        this.hook(ScriptContext.BLOCK, EnumScriptType.NEIGHBOR_CHANGED, IBlockEvent.NeighborChangedEvent.class);
        this.hook(ScriptContext.BLOCK, EnumScriptType.CLICKED, IBlockEvent.ClickedEvent.class);
        this.hook(ScriptContext.BLOCK, EnumScriptType.HARVESTED, IBlockEvent.HarvestedEvent.class);
        this.hook(ScriptContext.BLOCK, EnumScriptType.COLLIDE, IBlockEvent.CollidedEvent.class);
        this.hook(ScriptContext.BLOCK, EnumScriptType.TIMER, IBlockEvent.TimerEvent.class);
    }

    private void initializeItemHooks() {
        this.hook(ScriptContext.ITEM, EnumScriptType.INIT, IItemEvent.InitEvent.class);
        this.hook(ScriptContext.ITEM, EnumScriptType.TICK, IItemEvent.UpdateEvent.class);
        this.hook(ScriptContext.ITEM, EnumScriptType.TOSSED, IItemEvent.TossedEvent.class);
        this.hook(ScriptContext.ITEM, EnumScriptType.PICKEDUP, IItemEvent.PickedUpEvent.class);
        this.hook(ScriptContext.ITEM, EnumScriptType.SPAWN, IItemEvent.SpawnEvent.class);
        this.hook(ScriptContext.ITEM, EnumScriptType.INTERACT, IItemEvent.InteractEvent.class);
        this.hook(ScriptContext.ITEM, EnumScriptType.RIGHT_CLICK, IItemEvent.RightClickEvent.class);
        this.hook(ScriptContext.ITEM, EnumScriptType.ATTACK, IItemEvent.AttackEvent.class);
        this.hook(ScriptContext.ITEM, EnumScriptType.START_USING_ITEM, IItemEvent.StartUsingItem.class);
        this.hook(ScriptContext.ITEM, EnumScriptType.USING_ITEM, IItemEvent.UsingItem.class);
        this.hook(ScriptContext.ITEM, EnumScriptType.STOP_USING_ITEM, IItemEvent.StopUsingItem.class);
        this.hook(ScriptContext.ITEM, EnumScriptType.FINISH_USING_ITEM, IItemEvent.FinishUsingItem.class);
    }

    private void initializeLinkedItemHooks() {
        this.hook(ScriptContext.LINKED_ITEM, EnumScriptType.LINKED_ITEM_BUILD, ILinkedItemEvent.BuildEvent.class);
        this.hook(ScriptContext.LINKED_ITEM, EnumScriptType.LINKED_ITEM_VERSION, ILinkedItemEvent.VersionChangeEvent.class);
        this.hook(ScriptContext.LINKED_ITEM, EnumScriptType.INIT, IItemEvent.InitEvent.class);
        this.hook(ScriptContext.LINKED_ITEM, EnumScriptType.TICK, IItemEvent.UpdateEvent.class);
        this.hook(ScriptContext.LINKED_ITEM, EnumScriptType.TOSSED, IItemEvent.TossedEvent.class);
        this.hook(ScriptContext.LINKED_ITEM, EnumScriptType.PICKEDUP, IItemEvent.PickedUpEvent.class);
        this.hook(ScriptContext.LINKED_ITEM, EnumScriptType.SPAWN, IItemEvent.SpawnEvent.class);
        this.hook(ScriptContext.LINKED_ITEM, EnumScriptType.INTERACT, IItemEvent.InteractEvent.class);
        this.hook(ScriptContext.LINKED_ITEM, EnumScriptType.RIGHT_CLICK, IItemEvent.RightClickEvent.class);
        this.hook(ScriptContext.LINKED_ITEM, EnumScriptType.ATTACK, IItemEvent.AttackEvent.class);
        this.hook(ScriptContext.LINKED_ITEM, EnumScriptType.START_USING_ITEM, IItemEvent.StartUsingItem.class);
        this.hook(ScriptContext.LINKED_ITEM, EnumScriptType.USING_ITEM, IItemEvent.UsingItem.class);
        this.hook(ScriptContext.LINKED_ITEM, EnumScriptType.STOP_USING_ITEM, IItemEvent.StopUsingItem.class);
        this.hook(ScriptContext.LINKED_ITEM, EnumScriptType.FINISH_USING_ITEM, IItemEvent.FinishUsingItem.class);
    }

    private void initializeRecipeHooks() {
        this.hook(ScriptContext.RECIPE, RecipeScript.ScriptType.PRE.function, IRecipeEvent.Pre.class);
        this.hook(ScriptContext.RECIPE, RecipeScript.ScriptType.POST.function, IRecipeEvent.Post.class);
    }

    private void initializeEffectHooks() {
        this.hook(ScriptContext.EFFECT, EnumScriptType.ON_EFFECT_ADD, IPlayerEvent.EffectEvent.Added.class);
        this.hook(ScriptContext.EFFECT, EnumScriptType.ON_EFFECT_TICK, IPlayerEvent.EffectEvent.Ticked.class);
        this.hook(ScriptContext.EFFECT, EnumScriptType.ON_EFFECT_REMOVE, IPlayerEvent.EffectEvent.Removed.class);
    }

    private void initializeAbilityHooks() {
        this.hook(ScriptContext.ABILITY, EnumScriptType.ABILITY_START, IAbilityEvent.StartEvent.class);
        this.hook(ScriptContext.ABILITY, EnumScriptType.ABILITY_EXECUTE, IAbilityEvent.ExecuteEvent.class);
        this.hook(ScriptContext.ABILITY, EnumScriptType.ABILITY_HIT, IAbilityEvent.HitEvent.class);
        this.hook(ScriptContext.ABILITY, EnumScriptType.ABILITY_TICK, IAbilityEvent.TickEvent.class);
        this.hook(ScriptContext.ABILITY, EnumScriptType.ABILITY_INTERRUPT, IAbilityEvent.InterruptEvent.class);
        this.hook(ScriptContext.ABILITY, EnumScriptType.ABILITY_COMPLETE, IAbilityEvent.CompleteEvent.class);
        this.hook(ScriptContext.ABILITY, EnumScriptType.ABILITY_TOGGLE, IAbilityEvent.ToggleEvent.class);
        this.hook(ScriptContext.ABILITY, EnumScriptType.ABILITY_TOGGLE_TICK, IAbilityEvent.ToggleUpdateEvent.class);
        this.hook(ScriptContext.CHAINED_ABILITY, EnumScriptType.CHAIN_START, IChainEvent.StartEvent.class);
        this.hook(ScriptContext.CHAINED_ABILITY, EnumScriptType.CHAIN_NEXT, IChainEvent.NextEvent.class);
        this.hook(ScriptContext.CHAINED_ABILITY, EnumScriptType.CHAIN_COMPLETE, IChainEvent.CompleteEvent.class);
        this.hook(ScriptContext.CHAINED_ABILITY, EnumScriptType.CHAIN_INTERRUPT, IChainEvent.InterruptEvent.class);
    }

    private void initializeForgeHooks() {
        this.hook(ScriptContext.FORGE, EnumScriptType.INIT, IForgeEvent.InitEvent.class);
        this.hook(ScriptContext.FORGE, EnumScriptType.FORGE_WORLD, IForgeEvent.WorldEvent.class);
        this.hook(ScriptContext.FORGE, EnumScriptType.FORGE_ENTITY, IForgeEvent.EntityEvent.class);
        this.hook(ScriptContext.FORGE, EnumScriptType.CNPC_NATURAL_SPAWN, ICustomNPCsEvent.CNPCNaturalSpawnEvent.class);
    }

    private void hook(ScriptContext context, EnumScriptType type, Class<?> eventClass) {
        this.hookDefinitions.get(context.hookContext).put(type.function, HookDefinition.of(type.function, eventClass));
    }

    private void hook(ScriptContext context, EnumScriptType type) {
        this.hookDefinitions.get(context.hookContext).put(type.function, HookDefinition.simple(type.function));
    }

    private void hook(ScriptContext context, String hookName) {
        this.hookDefinitions.get(context.hookContext).put(hookName, HookDefinition.simple(hookName));
    }

    private void hook(ScriptContext context, String hookName, Class<?> eventClass) {
        this.hookDefinitions.get(context.hookContext).put(hookName, HookDefinition.of(hookName, eventClass));
    }

    public void registerHook(String context, String hookName, Class<?> eventClass) {
        if (context == null || hookName == null) {
            return;
        }
        Map<String, HookDefinition> contextDefs = this.hookDefinitions.get(context);
        if (contextDefs == null) {
            contextDefs = new LinkedHashMap<String, HookDefinition>();
            this.hookDefinitions.put(context, contextDefs);
        }
        contextDefs.put(hookName, HookDefinition.of(hookName, eventClass));
        ++this.hookRevision;
    }

    public void registerHook(ScriptContext context, String hookName, Class<?> eventClass) {
        this.registerHook(context.hookContext, hookName, eventClass);
    }

    public void registerHook(String context, String hookName) {
        if (context == null || hookName == null) {
            return;
        }
        Map<String, HookDefinition> contextDefs = this.hookDefinitions.get(context);
        if (contextDefs == null) {
            contextDefs = new LinkedHashMap<String, HookDefinition>();
            this.hookDefinitions.put(context, contextDefs);
        }
        if (!contextDefs.containsKey(hookName)) {
            contextDefs.put(hookName, HookDefinition.simple(hookName));
            ++this.hookRevision;
        }
    }

    public void registerHook(ScriptContext context, String hookName) {
        this.registerHook(context.hookContext, hookName);
    }

    public void registerHook(String context, HookDefinition definition) {
        if (context == null || definition == null) {
            return;
        }
        Map<String, HookDefinition> contextDefs = this.hookDefinitions.get(context);
        if (contextDefs == null) {
            contextDefs = new LinkedHashMap<String, HookDefinition>();
            this.hookDefinitions.put(context, contextDefs);
        }
        contextDefs.put(definition.hookName(), definition);
        ++this.hookRevision;
    }

    public void registerHook(ScriptContext context, HookDefinition definition) {
        this.registerHook(context.hookContext, definition);
    }

    @Override
    public void registerHookDefinition(String context, IHookDefinition definition) {
        if (definition instanceof HookDefinition) {
            this.registerHook(context, (HookDefinition)definition);
        } else if (definition != null) {
            this.registerHook(context, HookDefinition.builder(definition.hookName()).eventClass(definition.eventClassName()).paramNames(definition.paramNames()).requiredImports(definition.requiredImports()).cancelable(definition.isCancelable()).build());
        }
    }

    @Override
    public IHookDefinition getHookDefinition(String context, String hookName) {
        if (context == null || hookName == null) {
            return null;
        }
        Map<String, HookDefinition> contextDefs = this.hookDefinitions.get(context);
        return contextDefs != null ? (IHookDefinition)contextDefs.get(hookName) : null;
    }

    @Override
    public List<IHookDefinition> getAllHookDefinitions(String context) {
        if (context == null) {
            return Collections.emptyList();
        }
        Map<String, HookDefinition> contextDefs = this.hookDefinitions.get(context);
        if (contextDefs == null || contextDefs.isEmpty()) {
            return Collections.emptyList();
        }
        return new ArrayList<IHookDefinition>(contextDefs.values());
    }

    @Override
    public List<String> getAllHooks(String context) {
        if (context == null) {
            return Collections.emptyList();
        }
        Map<String, HookDefinition> contextDefs = this.hookDefinitions.get(context);
        if (contextDefs == null || contextDefs.isEmpty()) {
            return Collections.emptyList();
        }
        return new ArrayList<String>(contextDefs.keySet());
    }

    @Override
    public boolean hasHook(String context, String hookName) {
        if (context == null || hookName == null) {
            return false;
        }
        Map<String, HookDefinition> contextDefs = this.hookDefinitions.get(context);
        return contextDefs != null && contextDefs.containsKey(hookName);
    }

    @Override
    public int getHookRevision() {
        return this.hookRevision;
    }

    @Override
    public String[] getContexts() {
        return this.hookDefinitions.keySet().toArray(new String[0]);
    }
}

