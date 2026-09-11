/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.event.entity.EntityEvent
 *  net.minecraftforge.event.world.WorldEvent
 */
package noppes.npcs;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.ArrayList;
import java.util.HashMap;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.ChainedAbility;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import kamkeel.npcs.network.packets.data.AchievementPacket;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.world.WorldEvent;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.IBlock;
import noppes.npcs.api.IPos;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IAnimatable;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.entity.IEnergyBarrier;
import noppes.npcs.api.entity.IEnergyProjectile;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.entity.IProjectile;
import noppes.npcs.api.event.IAbilityEvent;
import noppes.npcs.api.event.IAnimationEvent;
import noppes.npcs.api.event.IChainEvent;
import noppes.npcs.api.gui.ICustomGui;
import noppes.npcs.api.gui.IItemSlot;
import noppes.npcs.api.handler.data.IAnimation;
import noppes.npcs.api.handler.data.IAnvilRecipe;
import noppes.npcs.api.handler.data.IFrame;
import noppes.npcs.api.handler.data.IPlayerEffect;
import noppes.npcs.api.handler.data.IProfile;
import noppes.npcs.api.item.IItemCustomizable;
import noppes.npcs.api.item.IItemLinked;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.controllers.CustomGuiController;
import noppes.npcs.controllers.ScriptContainer;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.data.AbilityScript;
import noppes.npcs.controllers.data.ChainedAbilityScript;
import noppes.npcs.controllers.data.Dialog;
import noppes.npcs.controllers.data.ForgeDataScript;
import noppes.npcs.controllers.data.IScriptBlockHandler;
import noppes.npcs.controllers.data.IScriptHandler;
import noppes.npcs.controllers.data.IScriptHandlerPacket;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerDataScript;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.controllers.data.RecipeScript;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.entity.EntityProjectile;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.event.AbilityEvent;
import noppes.npcs.scripted.event.AnimationEvent;
import noppes.npcs.scripted.event.BlockEvent;
import noppes.npcs.scripted.event.ChainEvent;
import noppes.npcs.scripted.event.CustomNPCsEvent;
import noppes.npcs.scripted.event.EnergyBarrierEvent;
import noppes.npcs.scripted.event.EnergyProjectileEvent;
import noppes.npcs.scripted.event.ForgeEvent;
import noppes.npcs.scripted.event.ItemEvent;
import noppes.npcs.scripted.event.LinkedItemEvent;
import noppes.npcs.scripted.event.NpcEvent;
import noppes.npcs.scripted.event.PartyEvent;
import noppes.npcs.scripted.event.ProjectileEvent;
import noppes.npcs.scripted.event.RecipeScriptEvent;
import noppes.npcs.scripted.event.player.AuctionEvent;
import noppes.npcs.scripted.event.player.CustomGuiEvent;
import noppes.npcs.scripted.event.player.DialogEvent;
import noppes.npcs.scripted.event.player.FactionEvent;
import noppes.npcs.scripted.event.player.PlayerEvent;
import noppes.npcs.scripted.event.player.QuestEvent;
import noppes.npcs.scripted.item.ScriptItemStack;
import org.apache.commons.lang3.StringUtils;

public class EventHooks {
    private static final HashMap<Class<?>, String> forgeEventNameCache = new HashMap();

    public static void onScriptItemInit(IItemCustomizable item) {
        IScriptHandler handler = (IScriptHandler)item.getScriptHandler();
        if (handler != null && !handler.isClient()) {
            ItemEvent.InitEvent event = new ItemEvent.InitEvent(item);
            handler.callScript(EnumScriptType.INIT, (Event)event);
            NpcAPI.EVENT_BUS.post((Event)event);
        }
    }

    public static void onScriptItemUpdate(IItemCustomizable item, EntityLivingBase player) {
        IScriptHandler handler = (IScriptHandler)item.getScriptHandler();
        if (handler != null && !handler.isClient()) {
            ItemEvent.UpdateEvent event = new ItemEvent.UpdateEvent(item, NpcAPI.Instance().getIEntity((Entity)player));
            handler.callScript(EnumScriptType.TICK, (Event)event);
            NpcAPI.EVENT_BUS.post((Event)event);
        }
    }

    public static boolean onScriptItemTossed(IItemCustomizable item, EntityPlayer player, EntityItem entity) {
        IScriptHandler handler = (IScriptHandler)item.getScriptHandler();
        ItemEvent.TossedEvent event = new ItemEvent.TossedEvent(item, (IPlayer)NpcAPI.Instance().getIEntity((Entity)player), NpcAPI.Instance().getIEntity((Entity)entity));
        if (handler != null) {
            handler.callScript(EnumScriptType.TOSSED, (Event)event);
        }
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onScriptItemPickedUp(IItemCustomizable item, EntityPlayer player) {
        IScriptHandler handler = (IScriptHandler)item.getScriptHandler();
        ItemEvent.PickedUpEvent event = new ItemEvent.PickedUpEvent(item, (IPlayer)NpcAPI.Instance().getIEntity((Entity)player));
        if (handler != null) {
            handler.callScript(EnumScriptType.PICKEDUP, (Event)event);
        }
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onScriptItemSpawn(IItemCustomizable item, EntityItem entity) {
        IScriptHandler handler = (IScriptHandler)item.getScriptHandler();
        ItemEvent.SpawnEvent event = new ItemEvent.SpawnEvent(item, NpcAPI.Instance().getIEntity((Entity)entity));
        if (handler != null) {
            handler.callScript(EnumScriptType.SPAWN, (Event)event);
        }
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onScriptItemInteract(IItemCustomizable item, ItemEvent.InteractEvent event) {
        IScriptHandler handler = (IScriptHandler)item.getScriptHandler();
        if (handler != null) {
            handler.callScript(EnumScriptType.INTERACT, (Event)event);
        }
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onScriptItemRightClick(IItemCustomizable item, ItemEvent.RightClickEvent event) {
        IScriptHandler handler = (IScriptHandler)item.getScriptHandler();
        if (handler != null) {
            handler.callScript(EnumScriptType.RIGHT_CLICK, (Event)event);
        }
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onScriptItemAttack(IItemCustomizable item, ItemEvent.AttackEvent event) {
        IScriptHandler handler = (IScriptHandler)item.getScriptHandler();
        if (handler != null) {
            handler.callScript(EnumScriptType.ATTACK, (Event)event);
        }
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onStartUsingCustomItem(IItemCustomizable item, IPlayer player, int duration) {
        IScriptHandler handler = (IScriptHandler)item.getScriptHandler();
        ItemEvent.StartUsingItem event = new ItemEvent.StartUsingItem(item, player, duration);
        if (handler != null) {
            handler.callScript(EnumScriptType.START_USING_ITEM, (Event)event);
        }
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onUsingCustomItem(IItemCustomizable item, IPlayer player, int duration) {
        IScriptHandler handler = (IScriptHandler)item.getScriptHandler();
        ItemEvent.UsingItem event = new ItemEvent.UsingItem(item, player, duration);
        if (handler != null) {
            handler.callScript(EnumScriptType.USING_ITEM, (Event)event);
        }
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onStopUsingCustomItem(IItemCustomizable item, IPlayer player, int duration) {
        IScriptHandler handler = (IScriptHandler)item.getScriptHandler();
        ItemEvent.StopUsingItem event = new ItemEvent.StopUsingItem(item, player, duration);
        if (handler != null) {
            handler.callScript(EnumScriptType.STOP_USING_ITEM, (Event)event);
        }
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onFinishUsingCustomItem(IItemCustomizable item, IPlayer player, int duration) {
        IScriptHandler handler = (IScriptHandler)item.getScriptHandler();
        ItemEvent.FinishUsingItem event = new ItemEvent.FinishUsingItem(item, player, duration);
        if (handler != null) {
            handler.callScript(EnumScriptType.FINISH_USING_ITEM, (Event)event);
        }
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onRepairCustomItem(IItemCustomizable item, IPlayer player, IItemStack left, IItemStack right, float anvilBreakChance) {
        IScriptHandler handler = (IScriptHandler)item.getScriptHandler();
        ItemEvent.RepairItem event = new ItemEvent.RepairItem(item, player, left, right, anvilBreakChance);
        if (handler != null) {
            handler.callScript(EnumScriptType.REPAIR_ITEM, (Event)event);
        }
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onBreakCustomItem(IItemCustomizable item, IPlayer player) {
        IScriptHandler handler = (IScriptHandler)item.getScriptHandler();
        ItemEvent.BreakItem event = new ItemEvent.BreakItem(item, player);
        if (handler != null) {
            handler.callScript(EnumScriptType.BREAK_ITEM, (Event)event);
        }
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static RecipeScriptEvent.Pre onRecipeScriptPre(EntityPlayer player, RecipeScript script, Object recipe, ItemStack[] items) {
        String msg;
        IItemStack[] iitems = new IItemStack[items.length];
        for (int i = 0; i < items.length; ++i) {
            iitems[i] = items[i] == null ? null : NpcAPI.Instance().getIItemStack(items[i]);
        }
        RecipeScriptEvent.Pre event = new RecipeScriptEvent.Pre(NoppesUtilServer.getIPlayer(player), recipe, recipe instanceof IAnvilRecipe, iitems);
        if (script != null) {
            script.callScript(RecipeScript.ScriptType.PRE.function, (Event)event);
        }
        NpcAPI.EVENT_BUS.post((Event)event);
        if (!player.field_70170_p.field_72995_K && (msg = event.getMessage()) != null && !msg.isEmpty()) {
            AchievementPacket.sendAchievement((EntityPlayerMP)player, false, "", msg);
        }
        return event;
    }

    public static ItemStack onRecipeScriptPost(EntityPlayer player, RecipeScript script, Object recipe, ItemStack[] items, ItemStack result) {
        IItemStack[] iitems = new IItemStack[items.length];
        for (int i = 0; i < items.length; ++i) {
            iitems[i] = items[i] == null ? null : NpcAPI.Instance().getIItemStack(items[i]);
        }
        RecipeScriptEvent.Post event = new RecipeScriptEvent.Post(NoppesUtilServer.getIPlayer(player), recipe, recipe instanceof IAnvilRecipe, iitems, NpcAPI.Instance().getIItemStack(result));
        if (script != null) {
            script.callScript(RecipeScript.ScriptType.POST.function, (Event)event);
        }
        NpcAPI.EVENT_BUS.post((Event)event);
        return event.getResult() == null ? null : ((ScriptItemStack)event.getCraft()).getMCItemStack();
    }

    public static void onLinkedItemVersionChange(IItemLinked item, int version, int prevVersion) {
        IScriptHandler handler = (IScriptHandler)item.getScriptHandler();
        LinkedItemEvent.VersionChangeEvent event = new LinkedItemEvent.VersionChangeEvent(item, version, prevVersion);
        if (handler != null) {
            handler.callScript(EnumScriptType.LINKED_ITEM_VERSION, (Event)event);
        }
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onLinkedItemBuild(IItemLinked item) {
        IScriptHandler handler = (IScriptHandler)item.getScriptHandler();
        LinkedItemEvent.BuildEvent event = new LinkedItemEvent.BuildEvent(item);
        if (handler != null) {
            handler.callScript(EnumScriptType.LINKED_ITEM_BUILD, (Event)event);
        }
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onNPCInit(EntityNPCInterface npc) {
        if (npc == null || npc.wrappedNPC == null) {
            return;
        }
        NpcEvent.InitEvent event = new NpcEvent.InitEvent(npc.wrappedNPC);
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.INIT, (Event)event);
        npc.script.callScript(EnumScriptType.INIT, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
        npc.advanced.soulStoneInit = false;
    }

    public static void onNPCUpdate(EntityNPCInterface npc) {
        if (npc == null || npc.wrappedNPC == null) {
            return;
        }
        NpcEvent.UpdateEvent event = new NpcEvent.UpdateEvent(npc.wrappedNPC);
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.TICK, (Event)event);
        npc.script.callScript(EnumScriptType.TICK, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onNPCDialog(EntityNPCInterface npc, EntityPlayer player, int dialogId, int optionId, Dialog dialog) {
        if (npc == null || npc.wrappedNPC == null) {
            return false;
        }
        NpcEvent.DialogEvent event = new NpcEvent.DialogEvent(npc.wrappedNPC, player, dialogId, optionId, dialog);
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.DIALOG, (Event)event);
        npc.script.callScript(EnumScriptType.DIALOG, event, "player", event.getPlayer(), "dialog", event.getDialogId(), "option", event.getOptionId(), "dialogObj", event.getDialog());
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onNPCDialogClosed(EntityNPCInterface npc, EntityPlayer player, int dialogId, int optionId, Dialog dialog) {
        if (npc == null || npc.wrappedNPC == null) {
            return;
        }
        NpcEvent.DialogClosedEvent event = new NpcEvent.DialogClosedEvent(npc.wrappedNPC, player, dialogId, optionId, dialog);
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.DIALOG_CLOSE, (Event)event);
        npc.script.callScript(EnumScriptType.DIALOG_CLOSE, event, "player", event.getPlayer(), "dialog", event.getDialogId(), "option", event.getOptionId(), "dialogObj", event.getDialog());
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onNPCInteract(EntityNPCInterface npc, EntityPlayer player) {
        if (npc == null || npc.wrappedNPC == null) {
            return false;
        }
        NpcEvent.InteractEvent event = new NpcEvent.InteractEvent(npc.wrappedNPC, player);
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.INTERACT, (Event)event);
        boolean result = npc.script.callScript(EnumScriptType.INTERACT, event, "player", player);
        NpcAPI.EVENT_BUS.post((Event)event);
        return result;
    }

    public static boolean onNPCMeleeAttack(EntityNPCInterface npc, NpcEvent.MeleeAttackEvent event) {
        if (npc == null || npc.wrappedNPC == null) {
            return false;
        }
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.ATTACK_MELEE, (Event)event);
        npc.script.callScript(EnumScriptType.ATTACK_MELEE, event, "target", event.target);
        npc.script.callScript(EnumScriptType.ATTACK, event, "target", event.target);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onNPCMeleeSwing(EntityNPCInterface npc, NpcEvent.SwingEvent event) {
        if (npc == null || npc.wrappedNPC == null) {
            return false;
        }
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.ATTACK_SWING, (Event)event);
        npc.script.callScript(EnumScriptType.ATTACK_SWING, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onNPCRangedAttack(EntityNPCInterface npc, NpcEvent.RangedLaunchedEvent event) {
        if (npc == null || npc.wrappedNPC == null) {
            return false;
        }
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.RANGED_LAUNCHED, (Event)event);
        npc.script.callScript(EnumScriptType.RANGED_LAUNCHED, event, "target", event.target);
        npc.script.callScript(EnumScriptType.ATTACK, event, "target", event.target);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onNPCKilledEntity(EntityNPCInterface npc, EntityLivingBase entity) {
        if (npc == null || npc.wrappedNPC == null) {
            return;
        }
        NpcEvent.KilledEntityEvent event = new NpcEvent.KilledEntityEvent(npc.wrappedNPC, entity);
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.KILLS, (Event)event);
        npc.script.callScript(EnumScriptType.KILLS, event, "target", entity);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onNPCTarget(EntityNPCInterface npc, NpcEvent.TargetEvent event) {
        if (npc == null || npc.wrappedNPC == null) {
            return false;
        }
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.TARGET, (Event)event);
        npc.script.callScript(EnumScriptType.TARGET, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onNPCTargetLost(EntityNPCInterface npc, EntityLivingBase prevtarget, EntityLivingBase newTarget) {
        if (npc.script.isClient()) {
            return false;
        }
        NpcEvent.TargetLostEvent event = new NpcEvent.TargetLostEvent(npc.wrappedNPC, prevtarget, newTarget);
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.TARGET_LOST, (Event)event);
        npc.script.callScript(EnumScriptType.TARGET_LOST, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onNPCCollide(EntityNPCInterface npc, Entity entity) {
        if (npc == null || npc.wrappedNPC == null) {
            return;
        }
        NpcEvent.CollideEvent event = new NpcEvent.CollideEvent(npc.wrappedNPC, entity);
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.COLLIDE, (Event)event);
        npc.script.callScript(EnumScriptType.COLLIDE, event, "entity", entity);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onNPCDamaged(EntityNPCInterface npc, NpcEvent.DamagedEvent event) {
        if (npc == null || npc.wrappedNPC == null) {
            return false;
        }
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.DAMAGED, (Event)event);
        npc.script.callScript(EnumScriptType.DAMAGED, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onNPCKilled(EntityNPCInterface npc, NpcEvent.DiedEvent event) {
        if (npc == null || npc.wrappedNPC == null) {
            return false;
        }
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.KILLED, (Event)event);
        npc.script.callScript(EnumScriptType.KILLED, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onNPCTimer(EntityNPCInterface npc, int id) {
        if (npc == null || npc.wrappedNPC == null) {
            return;
        }
        NpcEvent.TimerEvent event = new NpcEvent.TimerEvent(npc.wrappedNPC, id);
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.TIMER, (Event)event);
        npc.script.callScript(EnumScriptType.TIMER, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onProjectileTick(EntityProjectile projectile) {
        ProjectileEvent.UpdateEvent event = new ProjectileEvent.UpdateEvent((IProjectile)((Object)NpcAPI.Instance().getIEntity((Entity)projectile)));
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.PROJECTILE_TICK, (Event)event);
        for (ScriptContainer script : projectile.scripts) {
            if (!script.isValid()) continue;
            script.run(EnumScriptType.PROJECTILE_TICK, event);
        }
        EntityLivingBase thrower = projectile.func_85052_h();
        if (thrower != null && thrower instanceof EntityNPCInterface) {
            EntityNPCInterface npc = (EntityNPCInterface)thrower;
            npc.script.callScript(EnumScriptType.PROJECTILE_TICK, (Event)event);
        }
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onProjectileImpact(EntityProjectile projectile, ProjectileEvent.ImpactEvent event) {
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.PROJECTILE_IMPACT, (Event)event);
        for (ScriptContainer script : projectile.scripts) {
            if (!script.isValid()) continue;
            script.run(EnumScriptType.PROJECTILE_IMPACT, event);
        }
        EntityLivingBase thrower = projectile.func_85052_h();
        if (thrower != null && thrower instanceof EntityNPCInterface) {
            EntityNPCInterface npc = (EntityNPCInterface)thrower;
            npc.script.callScript(EnumScriptType.PROJECTILE_IMPACT, (Event)event);
        }
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onEnergyProjectileFired(EntityEnergyProjectile projectile) {
        PlayerDataScript handler;
        IEnergyProjectile wrapped = (IEnergyProjectile)NpcAPI.Instance().getIEntity(projectile);
        EnergyProjectileEvent.FiredEvent event = new EnergyProjectileEvent.FiredEvent(wrapped);
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.ENERGY_PROJECTILE_FIRED, (Event)event);
        Entity owner = projectile.field_70170_p.func_73045_a(projectile.getOwnerEntityId());
        if (owner instanceof EntityNPCInterface) {
            ((EntityNPCInterface)owner).script.callScript(EnumScriptType.ENERGY_PROJECTILE_FIRED, (Event)event);
        } else if (owner instanceof EntityPlayer && (handler = ScriptController.Instance.getPlayerScripts((EntityPlayer)owner)) != null) {
            handler.callScript(EnumScriptType.ENERGY_PROJECTILE_FIRED, (Event)event);
        }
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onEnergyProjectileTick(EntityEnergyProjectile projectile, int tick) {
        PlayerDataScript handler;
        IEnergyProjectile wrapped = (IEnergyProjectile)NpcAPI.Instance().getIEntity(projectile);
        EnergyProjectileEvent.UpdateEvent event = new EnergyProjectileEvent.UpdateEvent(wrapped, tick);
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.ENERGY_PROJECTILE_TICK, (Event)event);
        Entity owner = projectile.field_70170_p.func_73045_a(projectile.getOwnerEntityId());
        if (owner instanceof EntityNPCInterface) {
            ((EntityNPCInterface)owner).script.callScript(EnumScriptType.ENERGY_PROJECTILE_TICK, (Event)event);
        } else if (owner instanceof EntityPlayer && (handler = ScriptController.Instance.getPlayerScripts((EntityPlayer)owner)) != null) {
            handler.callScript(EnumScriptType.ENERGY_PROJECTILE_TICK, (Event)event);
        }
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static float onEnergyProjectileEntityImpact(EntityEnergyProjectile projectile, EntityLivingBase target, float damage) {
        PlayerDataScript handler;
        IEnergyProjectile wrapped = (IEnergyProjectile)NpcAPI.Instance().getIEntity(projectile);
        IEntity<?> wrappedTarget = NpcAPI.Instance().getIEntity((Entity)target);
        EnergyProjectileEvent.EntityImpactEvent event = new EnergyProjectileEvent.EntityImpactEvent(wrapped, wrappedTarget, damage);
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.ENERGY_PROJECTILE_ENTITY_IMPACT, (Event)event);
        Entity owner = projectile.field_70170_p.func_73045_a(projectile.getOwnerEntityId());
        if (owner instanceof EntityNPCInterface) {
            ((EntityNPCInterface)owner).script.callScript(EnumScriptType.ENERGY_PROJECTILE_ENTITY_IMPACT, (Event)event);
        } else if (owner instanceof EntityPlayer && (handler = ScriptController.Instance.getPlayerScripts((EntityPlayer)owner)) != null) {
            handler.callScript(EnumScriptType.ENERGY_PROJECTILE_ENTITY_IMPACT, (Event)event);
        }
        NpcAPI.EVENT_BUS.post((Event)event);
        if (event.isCanceled()) {
            return -1.0f;
        }
        return event.getDamage();
    }

    public static void onEnergyProjectileBlockImpact(EntityEnergyProjectile projectile, int blockX, int blockY, int blockZ) {
        PlayerDataScript handler;
        IEnergyProjectile wrapped = (IEnergyProjectile)NpcAPI.Instance().getIEntity(projectile);
        IPos pos = NpcAPI.Instance().getIPos(blockX, blockY, blockZ);
        IWorld world = NpcAPI.Instance().getIWorld(projectile.field_70170_p);
        IBlock block = NpcAPI.Instance().getIBlock(world, pos);
        EnergyProjectileEvent.BlockImpactEvent event = new EnergyProjectileEvent.BlockImpactEvent(wrapped, block);
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.ENERGY_PROJECTILE_BLOCK_IMPACT, (Event)event);
        Entity owner = projectile.field_70170_p.func_73045_a(projectile.getOwnerEntityId());
        if (owner instanceof EntityNPCInterface) {
            ((EntityNPCInterface)owner).script.callScript(EnumScriptType.ENERGY_PROJECTILE_BLOCK_IMPACT, (Event)event);
        } else if (owner instanceof EntityPlayer && (handler = ScriptController.Instance.getPlayerScripts((EntityPlayer)owner)) != null) {
            handler.callScript(EnumScriptType.ENERGY_PROJECTILE_BLOCK_IMPACT, (Event)event);
        }
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onEnergyProjectileExpired(EntityEnergyProjectile projectile) {
        PlayerDataScript handler;
        IEnergyProjectile wrapped = (IEnergyProjectile)NpcAPI.Instance().getIEntity(projectile);
        EnergyProjectileEvent.ExpiredEvent event = new EnergyProjectileEvent.ExpiredEvent(wrapped);
        ScriptController.Instance.globalNpcScripts.callScript(EnumScriptType.ENERGY_PROJECTILE_EXPIRED, (Event)event);
        Entity owner = projectile.field_70170_p.func_73045_a(projectile.getOwnerEntityId());
        if (owner instanceof EntityNPCInterface) {
            ((EntityNPCInterface)owner).script.callScript(EnumScriptType.ENERGY_PROJECTILE_EXPIRED, (Event)event);
        } else if (owner instanceof EntityPlayer && (handler = ScriptController.Instance.getPlayerScripts((EntityPlayer)owner)) != null) {
            handler.callScript(EnumScriptType.ENERGY_PROJECTILE_EXPIRED, (Event)event);
        }
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    private static void dispatchBarrierEvent(Entity barrierEntity, int ownerEntityId, EnergyBarrierEvent event, EnumScriptType type) {
        PlayerDataScript handler;
        ScriptController.Instance.globalNpcScripts.callScript(type, (Event)event);
        Entity owner = barrierEntity.field_70170_p.func_73045_a(ownerEntityId);
        if (owner instanceof EntityNPCInterface) {
            ((EntityNPCInterface)owner).script.callScript(type, (Event)event);
        } else if (owner instanceof EntityPlayer && (handler = ScriptController.Instance.getPlayerScripts((EntityPlayer)owner)) != null) {
            handler.callScript(type, (Event)event);
        }
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onEnergyBarrierSpawned(Entity barrierEntity, int ownerEntityId) {
        IEnergyBarrier wrapped = (IEnergyBarrier)NpcAPI.Instance().getIEntity(barrierEntity);
        EnergyBarrierEvent.SpawnedEvent event = new EnergyBarrierEvent.SpawnedEvent(wrapped);
        EventHooks.dispatchBarrierEvent(barrierEntity, ownerEntityId, event, EnumScriptType.ENERGY_BARRIER_SPAWNED);
    }

    public static void onEnergyBarrierTick(Entity barrierEntity, int ownerEntityId) {
        IEnergyBarrier wrapped = (IEnergyBarrier)NpcAPI.Instance().getIEntity(barrierEntity);
        EnergyBarrierEvent.UpdateEvent event = new EnergyBarrierEvent.UpdateEvent(wrapped);
        EventHooks.dispatchBarrierEvent(barrierEntity, ownerEntityId, event, EnumScriptType.ENERGY_BARRIER_TICK);
    }

    public static float onEnergyBarrierHit(Entity barrierEntity, int ownerEntityId, EntityEnergyProjectile projectile, float damage) {
        IEnergyBarrier wrappedBarrier = (IEnergyBarrier)NpcAPI.Instance().getIEntity(barrierEntity);
        IEnergyProjectile wrappedProjectile = projectile != null ? (IEnergyProjectile)NpcAPI.Instance().getIEntity(projectile) : null;
        EnergyBarrierEvent.HitEvent event = new EnergyBarrierEvent.HitEvent(wrappedBarrier, wrappedProjectile, damage);
        EventHooks.dispatchBarrierEvent(barrierEntity, ownerEntityId, event, EnumScriptType.ENERGY_BARRIER_HIT);
        if (event.isCanceled()) {
            return -1.0f;
        }
        return event.getDamage();
    }

    public static void onEnergyBarrierDestroyed(Entity barrierEntity, int ownerEntityId) {
        IEnergyBarrier wrapped = (IEnergyBarrier)NpcAPI.Instance().getIEntity(barrierEntity);
        EnergyBarrierEvent.DestroyedEvent event = new EnergyBarrierEvent.DestroyedEvent(wrapped);
        EventHooks.dispatchBarrierEvent(barrierEntity, ownerEntityId, event, EnumScriptType.ENERGY_BARRIER_DESTROYED);
    }

    public static void onPlayerInit(PlayerDataScript handler, IPlayer player) {
        PlayerEvent.InitEvent event = new PlayerEvent.InitEvent(player);
        handler.callScript(EnumScriptType.INIT, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPlayerTick(PlayerDataScript handler, IPlayer player) {
        PlayerEvent.UpdateEvent event = new PlayerEvent.UpdateEvent(player);
        handler.callScript(EnumScriptType.TICK, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onPlayerInteract(PlayerDataScript handler, PlayerEvent.InteractEvent event) {
        handler.callScript(EnumScriptType.INTERACT, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onPlayerRightClick(PlayerDataScript handler, PlayerEvent.RightClickEvent event) {
        handler.callScript(EnumScriptType.RIGHT_CLICK, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onProfileChange(PlayerDataScript handler, IPlayer player, IProfile profile, int newSlot, int prevSlot, boolean post) {
        PlayerEvent.ProfileEvent.Changed event = new PlayerEvent.ProfileEvent.Changed(player, profile, newSlot, prevSlot, post);
        handler.callScript(EnumScriptType.PROFILE_CHANGE, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onProfileRemove(PlayerDataScript handler, IPlayer player, IProfile profile, int slot, boolean post) {
        PlayerEvent.ProfileEvent.Removed event = new PlayerEvent.ProfileEvent.Removed(player, profile, slot, post);
        handler.callScript(EnumScriptType.PROFILE_REMOVE, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onProfileCreate(PlayerDataScript handler, IPlayer player, IProfile profile, int slot, boolean post) {
        PlayerEvent.ProfileEvent.Create event = new PlayerEvent.ProfileEvent.Create(player, profile, slot, post);
        handler.callScript(EnumScriptType.PROFILE_CREATE, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onEffectAdded(IPlayer player, IPlayerEffect effect) {
        PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(player);
        PlayerEvent.EffectEvent.Added event = new PlayerEvent.EffectEvent.Added(player, effect);
        handler.callScript(EnumScriptType.ON_EFFECT_ADD.function, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onEffectTick(IPlayer player, IPlayerEffect effect) {
        PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(player);
        PlayerEvent.EffectEvent.Ticked event = new PlayerEvent.EffectEvent.Ticked(player, effect);
        handler.callScript(EnumScriptType.ON_EFFECT_TICK.function, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onEffectRemove(IPlayer player, IPlayerEffect effect, PlayerEvent.EffectEvent.ExpirationType type) {
        PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(player);
        PlayerEvent.EffectEvent.Removed event = new PlayerEvent.EffectEvent.Removed(player, effect, type);
        handler.callScript(EnumScriptType.ON_EFFECT_REMOVE.function, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onStartUsingItem(PlayerDataScript handler, IPlayer player, int duration, ItemStack item) {
        PlayerEvent.StartUsingItem event = new PlayerEvent.StartUsingItem(player, item, duration);
        handler.callScript(EnumScriptType.START_USING_ITEM, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onUsingItem(PlayerDataScript handler, IPlayer player, int duration, ItemStack item) {
        PlayerEvent.UsingItem event = new PlayerEvent.UsingItem(player, item, duration);
        handler.callScript(EnumScriptType.USING_ITEM, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onStopUsingItem(PlayerDataScript handler, IPlayer player, int duration, ItemStack item) {
        PlayerEvent.StopUsingItem event = new PlayerEvent.StopUsingItem(player, item, duration);
        handler.callScript(EnumScriptType.STOP_USING_ITEM, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onFinishUsingItem(PlayerDataScript handler, IPlayer player, int duration, ItemStack item) {
        PlayerEvent.FinishUsingItem event = new PlayerEvent.FinishUsingItem(player, item, duration);
        handler.callScript(EnumScriptType.FINISH_USING_ITEM, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onPlayerDropItems(PlayerDataScript handler, IPlayer player, ArrayList<EntityItem> entityItems) {
        IItemStack[] items = new IItemStack[entityItems.size()];
        for (int i = 0; i < entityItems.size(); ++i) {
            items[i] = NpcAPI.Instance().getIItemStack(entityItems.get(i).func_92059_d());
        }
        PlayerEvent.DropEvent event = new PlayerEvent.DropEvent(player, items);
        handler.callScript(EnumScriptType.DROP, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPlayerPickupXP(PlayerDataScript handler, IPlayer player, EntityXPOrb orb) {
        PlayerEvent.PickupXPEvent event = new PlayerEvent.PickupXPEvent(player, orb);
        handler.callScript(EnumScriptType.PICKUP_XP, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onPlayerToss(PlayerDataScript handler, IPlayer player, EntityItem entityItem) {
        PlayerEvent.TossEvent event = new PlayerEvent.TossEvent(player, NpcAPI.Instance().getIItemStack(entityItem.func_92059_d()));
        handler.callScript(EnumScriptType.TOSS, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onPlayerPickUp(PlayerDataScript handler, IPlayer player, EntityItem entityItem) {
        PlayerEvent.PickUpEvent event = new PlayerEvent.PickUpEvent(player, NpcAPI.Instance().getIItemStack(entityItem.func_92059_d()));
        handler.callScript(EnumScriptType.PICKUP, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPlayerContainerOpen(PlayerDataScript handler, IPlayer player, Container container) {
        PlayerEvent.ContainerOpen event = new PlayerEvent.ContainerOpen(player, NpcAPI.Instance().getIContainer(container));
        handler.callScript(EnumScriptType.CONTAINER_OPEN, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPlayerUseHoe(PlayerDataScript handler, IPlayer player, ItemStack hoe, int x, int y, int z) {
        PlayerEvent.UseHoe event = new PlayerEvent.UseHoe(player, hoe, x, y, z);
        handler.callScript(EnumScriptType.USE_HOE, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPlayerSleep(PlayerDataScript handler, IPlayer player, int x, int y, int z) {
        PlayerEvent.Sleep event = new PlayerEvent.Sleep(player, x, y, z);
        handler.callScript(EnumScriptType.SLEEP, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPlayerWakeUp(PlayerDataScript handler, IPlayer player, boolean setSpawn) {
        PlayerEvent.WakeUp event = new PlayerEvent.WakeUp(player, setSpawn);
        handler.callScript(EnumScriptType.WAKE_UP, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPlayerDeath(PlayerDataScript handler, IPlayer player, DamageSource source, Entity entity) {
        PlayerEvent.DiedEvent event = new PlayerEvent.DiedEvent(player, source, entity);
        handler.callScript(EnumScriptType.KILLED, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPlayerKills(PlayerDataScript handler, IPlayer player, EntityLivingBase entityLiving) {
        PlayerEvent.KilledEntityEvent event = new PlayerEvent.KilledEntityEvent(player, entityLiving);
        handler.callScript(EnumScriptType.KILLS, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onPlayerAttacked(PlayerDataScript handler, PlayerEvent.AttackedEvent event) {
        handler.callScript(EnumScriptType.ATTACKED, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onPlayerDamaged(PlayerDataScript handler, PlayerEvent.DamagedEvent event) {
        handler.callScript(EnumScriptType.DAMAGED, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onPlayerLightning(PlayerDataScript handler, IPlayer player) {
        PlayerEvent.LightningEvent event = new PlayerEvent.LightningEvent(player);
        handler.callScript(EnumScriptType.LIGHTNING, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onPlayerSound(PlayerDataScript handler, IPlayer player, String name, float pitch, float volume) {
        PlayerEvent.SoundEvent event = new PlayerEvent.SoundEvent(player, name, pitch, volume);
        handler.callScript(EnumScriptType.PLAYSOUND, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onPlayerFall(PlayerDataScript handler, IPlayer player, float distance) {
        PlayerEvent.FallEvent event = new PlayerEvent.FallEvent(player, distance);
        handler.callScript(EnumScriptType.FALL, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPlayerJump(PlayerDataScript handler, IPlayer player) {
        PlayerEvent.JumpEvent event = new PlayerEvent.JumpEvent(player);
        handler.callScript(EnumScriptType.JUMP, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPlayerLogin(PlayerDataScript handler, IPlayer player) {
        PlayerEvent.LoginEvent event = new PlayerEvent.LoginEvent(player);
        handler.callScript(EnumScriptType.LOGIN, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPlayerAchievement(PlayerDataScript handler, IPlayer player, String description) {
        PlayerEvent.Achievement event = new PlayerEvent.Achievement(player, description);
        handler.callScript(EnumScriptType.ACHIEVEMENT, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPlayerFillBucket(PlayerDataScript handler, IPlayer player, ItemStack current, ItemStack result) {
        PlayerEvent.FillBucket event = new PlayerEvent.FillBucket(player, current, result);
        handler.callScript(EnumScriptType.FILL_BUCKET, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPlayerBonemeal(PlayerDataScript handler, IPlayer player, int x, int y, int z, World world) {
        PlayerEvent.Bonemeal event = new PlayerEvent.Bonemeal(player, x, y, z, world);
        handler.callScript(EnumScriptType.BONEMEAL, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPlayerRespawn(PlayerDataScript handler, IPlayer player) {
        PlayerEvent.RespawnEvent event = new PlayerEvent.RespawnEvent(player);
        handler.callScript(EnumScriptType.RESPAWN, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPlayerLogout(PlayerDataScript handler, IPlayer player) {
        PlayerEvent.LogoutEvent event = new PlayerEvent.LogoutEvent(player);
        handler.callScript(EnumScriptType.LOGOUT, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPlayerChat(PlayerDataScript handler, PlayerEvent.ChatEvent event) {
        handler.callScript(EnumScriptType.CHAT, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onPlayerBowCharge(PlayerDataScript handler, PlayerEvent.RangedChargeEvent event) {
        handler.callScript(EnumScriptType.RANGED_CHARGE, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onPlayerRanged(PlayerDataScript handler, PlayerEvent.RangedLaunchedEvent event) {
        handler.callScript(EnumScriptType.RANGED_LAUNCHED, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onPlayerAttack(PlayerDataScript handler, PlayerEvent.AttackEvent event) {
        handler.callScript(EnumScriptType.ATTACK, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onPlayerDamagedEntity(PlayerDataScript handler, PlayerEvent.DamagedEntityEvent event) {
        handler.callScript(EnumScriptType.DAMAGED_ENTITY, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    private static IScriptHandler getEntityScriptHandler(IEntityLivingBase entity) {
        if (entity instanceof ICustomNpc) {
            return ((EntityNPCInterface)((Object)((ICustomNpc)entity).getMCEntity())).script;
        }
        if (entity instanceof IPlayer && ScriptController.Instance != null) {
            return ScriptController.Instance.getPlayerScripts((EntityPlayer)((IPlayer)entity).getMCEntity());
        }
        return null;
    }

    private static boolean postAbilityEvent(Ability ability, Event event) {
        IAbilityEvent abilityEvent = (IAbilityEvent)event;
        AbilityScript abScript = ability.getOrCreateInstanceScript();
        if (abScript != null) {
            abScript.callScript(abilityEvent.getHookName(), event);
        }
        if (event.isCancelable() && event.isCanceled()) {
            return true;
        }
        IScriptHandler handler = EventHooks.getEntityScriptHandler(abilityEvent.getEntity());
        if (handler != null && !handler.isClient()) {
            handler.callScript(abilityEvent.getHookName(), event);
        }
        if (event.isCancelable() && event.isCanceled()) {
            return true;
        }
        return NpcAPI.EVENT_BUS.post(event);
    }

    private static boolean postChainEvent(ChainedAbility chain, Event event) {
        IChainEvent chainEvent = (IChainEvent)event;
        ChainedAbilityScript chainScript = chain.getOrCreateInstanceScript();
        if (chainScript != null) {
            chainScript.callScript(chainEvent.getHookName(), event);
        }
        if (event.isCancelable() && event.isCanceled()) {
            return true;
        }
        IScriptHandler handler = EventHooks.getEntityScriptHandler(chainEvent.getEntity());
        if (handler != null && !handler.isClient()) {
            handler.callScript(chainEvent.getHookName(), event);
        }
        if (event.isCancelable() && event.isCanceled()) {
            return true;
        }
        return NpcAPI.EVENT_BUS.post(event);
    }

    public static boolean onAbilityStart(Ability ability, EntityLivingBase entity, EntityLivingBase target) {
        if (ability == null || entity == null) {
            return false;
        }
        return EventHooks.postAbilityEvent(ability, new AbilityEvent.StartEvent(entity, ability, target));
    }

    public static boolean onAbilityExecute(Ability ability, EntityLivingBase entity, EntityLivingBase target) {
        if (ability == null || entity == null) {
            return false;
        }
        return EventHooks.postAbilityEvent(ability, new AbilityEvent.ExecuteEvent(entity, ability, target));
    }

    public static boolean onAbilityTick(Ability ability, EntityLivingBase entity, EntityLivingBase target, int phase, int tick) {
        if (ability == null || entity == null) {
            return false;
        }
        return EventHooks.postAbilityEvent(ability, new AbilityEvent.TickEvent(entity, ability, target, phase, tick));
    }

    public static boolean onAbilityComplete(Ability ability, EntityLivingBase entity, EntityLivingBase target) {
        if (ability == null || entity == null) {
            return false;
        }
        return EventHooks.postAbilityEvent(ability, new AbilityEvent.CompleteEvent(entity, ability, target));
    }

    public static boolean onAbilityInterrupt(Ability ability, EntityLivingBase entity, EntityLivingBase target, DamageSource source, float damage) {
        if (ability == null || entity == null) {
            return false;
        }
        return EventHooks.postAbilityEvent(ability, new AbilityEvent.InterruptEvent(entity, ability, target, source, damage));
    }

    public static boolean onAbilityHit(Ability ability, AbilityEvent.HitEvent event) {
        if (ability == null || event == null) {
            return false;
        }
        return EventHooks.postAbilityEvent(ability, event);
    }

    public static boolean onAbilityToggle(Ability ability, EntityLivingBase entity, int oldState, int newState) {
        if (ability == null || entity == null) {
            return false;
        }
        return EventHooks.postAbilityEvent(ability, new AbilityEvent.ToggleEvent(entity, ability, oldState, newState));
    }

    public static boolean onAbilityToggleUpdate(Ability ability, AbilityEvent.ToggleUpdateEvent event) {
        if (ability == null || event == null) {
            return false;
        }
        return EventHooks.postAbilityEvent(ability, event);
    }

    public static void onChainStart(ChainedAbility chain, EntityLivingBase entity, int entryIndex, EntityLivingBase target) {
        EventHooks.postChainEvent(chain, new ChainEvent.StartEvent(entity, chain, entryIndex, target));
    }

    public static void onChainNext(ChainedAbility chain, EntityLivingBase entity, int entryIndex, EntityLivingBase target) {
        EventHooks.postChainEvent(chain, new ChainEvent.NextEvent(entity, chain, entryIndex, target));
    }

    public static void onChainComplete(ChainedAbility chain, EntityLivingBase entity, int entryIndex, EntityLivingBase target) {
        EventHooks.postChainEvent(chain, new ChainEvent.CompleteEvent(entity, chain, entryIndex, target));
    }

    public static void onChainInterrupt(ChainedAbility chain, EntityLivingBase entity, int entryIndex, EntityLivingBase target, DamageSource source, float damage) {
        EventHooks.postChainEvent(chain, new ChainEvent.InterruptEvent(entity, chain, entryIndex, target, source, damage));
    }

    public static void onPlayerChangeDim(PlayerDataScript handler, IPlayer player, int fromDim, int toDim) {
        PlayerEvent.ChangedDimension event = new PlayerEvent.ChangedDimension(player, fromDim, toDim);
        handler.callScript(EnumScriptType.CHANGED_DIM, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPlayerMouseClicked(EntityPlayerMP player, int button, int mouseWheel, boolean buttonDown, boolean isCtrlPressed, boolean isShiftPressed, boolean isAltPressed, boolean isMetaPressed, int[] heldKeys) {
        PlayerDataScript handler = ScriptController.Instance.getPlayerScripts((EntityPlayer)player);
        PlayerEvent.MouseClickedEvent event = new PlayerEvent.MouseClickedEvent((IPlayer)NpcAPI.Instance().getIEntity((Entity)player), button, mouseWheel, buttonDown, isCtrlPressed, isAltPressed, isShiftPressed, isMetaPressed, heldKeys);
        handler.callScript(EnumScriptType.MOUSE_CLICKED, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPlayerKeyPressed(EntityPlayerMP player, int button, boolean isCtrlPressed, boolean isShiftPressed, boolean isAltPressed, boolean isMetaPressed, boolean buttonDown, int[] heldKeys) {
        PlayerDataScript handler = ScriptController.Instance.getPlayerScripts((EntityPlayer)player);
        PlayerEvent.KeyPressedEvent event = new PlayerEvent.KeyPressedEvent((IPlayer)NpcAPI.Instance().getIEntity((Entity)player), button, isCtrlPressed, isAltPressed, isShiftPressed, isMetaPressed, buttonDown, heldKeys);
        handler.callScript(EnumScriptType.KEY_PRESSED, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPlayerTimer(PlayerData data, int id) {
        PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(data.player);
        PlayerEvent.TimerEvent event = new PlayerEvent.TimerEvent((IPlayer)NpcAPI.Instance().getIEntity((Entity)data.player), id);
        handler.callScript(EnumScriptType.TIMER, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onPlayerBreak(PlayerDataScript handler, PlayerEvent.BreakEvent event) {
        handler.callScript(EnumScriptType.BREAK_BLOCK, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onForgeEntityEvent(EntityEvent event) {
        IEntity<?> e = NpcAPI.Instance().getIEntity(event.entity);
        EventHooks.onForgeEvent(new ForgeEvent.EntityEvent(event, e), (Event)event);
    }

    public static void onForgeWorldEvent(WorldEvent event) {
        if (ScriptController.Instance.forgeScripts.isEnabled()) {
            IWorld e = NpcAPI.Instance().getIWorld((World)((WorldServer)event.world));
            EventHooks.onForgeEvent(new ForgeEvent.WorldEvent(event, e), (Event)event);
        }
    }

    public static void onForgeInit(ForgeDataScript handler) {
        ForgeEvent.InitEvent event = new ForgeEvent.InitEvent();
        handler.callScript("init", (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    private static String getForgeEventName(Event event) {
        Class<?> clazz = event.getClass();
        String cached = forgeEventNameCache.get(clazz);
        if (cached != null) {
            return cached;
        }
        String eventName = clazz.getName();
        int i = eventName.lastIndexOf(".");
        eventName = StringUtils.uncapitalize(eventName.substring(i + 1).replace("$", ""));
        forgeEventNameCache.put(clazz, eventName);
        return eventName;
    }

    public static void onForgeEvent(ForgeEvent ev, Event event) {
        ForgeDataScript handler = ScriptController.Instance.forgeScripts;
        if (handler.isEnabled()) {
            String eventName = EventHooks.getForgeEventName(event);
            if (event.isCancelable()) {
                ev.setCanceled(event.isCanceled());
            }
            handler.callScript(eventName, event);
            NpcAPI.EVENT_BUS.post((Event)ev);
            if (event.isCancelable()) {
                event.setCanceled(ev.isCanceled());
            }
        }
    }

    public static boolean onCNPCNaturalSpawn(CustomNPCsEvent.CNPCNaturalSpawnEvent event) {
        ForgeDataScript handler = ScriptController.Instance.forgeScripts;
        if (handler.isEnabled()) {
            handler.callScript(EnumScriptType.CNPC_NATURAL_SPAWN, (Event)event);
            return NpcAPI.EVENT_BUS.post((Event)event);
        }
        return false;
    }

    public static boolean onScriptedCommand(ICommandSender sender, CustomNPCsEvent.ScriptedCommandEvent event) {
        if (sender instanceof EntityPlayer) {
            ScriptController.Instance.getPlayerScripts((EntityPlayer)sender).callScript(EnumScriptType.SCRIPT_COMMAND, (Event)event);
        } else {
            ScriptController.Instance.playerScripts.callScript(EnumScriptType.SCRIPT_COMMAND, (Event)event);
        }
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onCustomGuiButton(IPlayer player, ICustomGui gui, int buttonId) {
        CustomGuiEvent.ButtonEvent event = new CustomGuiEvent.ButtonEvent(player, gui, buttonId);
        CustomGuiController.onButton(event);
    }

    public static void onCustomGuiSlot(IPlayer player, ICustomGui gui, int slotId, ItemStack stack, IItemSlot slot) {
        CustomGuiEvent.SlotEvent event = new CustomGuiEvent.SlotEvent(player, gui, slotId, stack, slot);
        CustomGuiController.onSlotChange(event);
    }

    public static boolean onCustomGuiSlotClicked(IPlayer player, ICustomGui gui, int slotId, IItemSlot slot, int dragType, int clickType) {
        CustomGuiEvent.SlotClickEvent event = new CustomGuiEvent.SlotClickEvent(player, gui, slotId, slot, player.getOpenContainer().getSlot(slotId), dragType, clickType);
        return CustomGuiController.onSlotClick(event);
    }

    public static void onCustomGuiUnfocused(IPlayer player, ICustomGui gui, int textfieldId) {
        CustomGuiEvent.UnfocusedEvent event = new CustomGuiEvent.UnfocusedEvent(player, gui, textfieldId);
        CustomGuiController.onCustomGuiUnfocused(event);
    }

    public static void onCustomGuiScrollClick(IPlayer player, ICustomGui gui, int scrollId, int scrollIndex, String[] selection, boolean doubleClick) {
        CustomGuiEvent.ScrollEvent event = new CustomGuiEvent.ScrollEvent(player, gui, scrollId, scrollIndex, selection, doubleClick);
        CustomGuiController.onScrollClick(event);
    }

    public static void onCustomGuiClose(IPlayer player, ICustomGui gui) {
        CustomGuiEvent.CloseEvent event = new CustomGuiEvent.CloseEvent(player, gui);
        CustomGuiController.onClose(event);
    }

    public static void onQuestFinished(EntityPlayer player, Quest quest) {
        PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(player);
        QuestEvent.QuestCompletedEvent event = new QuestEvent.QuestCompletedEvent((IPlayer)NpcAPI.Instance().getIEntity((Entity)((EntityPlayerMP)player)), quest);
        handler.callScript(EnumScriptType.QUEST_COMPLETED, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onQuestStarted(EntityPlayer player, Quest quest) {
        PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(player);
        QuestEvent.QuestStartEvent event = new QuestEvent.QuestStartEvent((IPlayer)NpcAPI.Instance().getIEntity((Entity)((EntityPlayerMP)player)), quest);
        handler.callScript(EnumScriptType.QUEST_START, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onQuestTurnedIn(EntityPlayer player, QuestEvent.QuestTurnedInEvent event) {
        PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(player);
        handler.callScript(EnumScriptType.QUEST_TURNIN, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onFactionPoints(EntityPlayer player, FactionEvent.FactionPoints event) {
        PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(player);
        handler.callScript(EnumScriptType.FACTION_POINTS, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onDialogOpen(EntityPlayer player, DialogEvent.DialogOpen event) {
        PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(player);
        handler.callScript(EnumScriptType.DIALOG_OPEN, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onDialogOption(EntityPlayer player, DialogEvent.DialogOption event) {
        PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(player);
        handler.callScript(EnumScriptType.DIALOG_OPTION, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onDialogClosed(EntityPlayer player, DialogEvent.DialogClosed event) {
        PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(player);
        handler.callScript(EnumScriptType.DIALOG_CLOSE, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onScriptBlockInteract(IScriptBlockHandler handler, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (handler.isClient()) {
            return false;
        }
        BlockEvent.InteractEvent event = new BlockEvent.InteractEvent(handler.getBlock(), player, side, hitX, hitY, hitZ);
        handler.callScript(EnumScriptType.INTERACT, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onScriptBlockCollide(IScriptBlockHandler handler, Entity entityIn) {
        if (handler.isClient()) {
            return;
        }
        BlockEvent.CollidedEvent event = new BlockEvent.CollidedEvent(handler.getBlock(), entityIn);
        handler.callScript(EnumScriptType.COLLIDE, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onScriptBlockRainFill(IScriptBlockHandler handler) {
        if (handler.isClient()) {
            return;
        }
        BlockEvent.RainFillEvent event = new BlockEvent.RainFillEvent(handler.getBlock());
        handler.callScript(EnumScriptType.RAIN_FILLED, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static float onScriptBlockFallenUpon(IScriptBlockHandler handler, Entity entity, float distance) {
        if (handler.isClient()) {
            return distance;
        }
        BlockEvent.EntityFallenUponEvent event = new BlockEvent.EntityFallenUponEvent(handler.getBlock(), entity, distance);
        handler.callScript(EnumScriptType.FALLEN_UPON, (Event)event);
        if (NpcAPI.EVENT_BUS.post((Event)event)) {
            return 0.0f;
        }
        return event.distanceFallen;
    }

    public static void onScriptBlockClicked(IScriptBlockHandler handler, EntityPlayer player) {
        if (handler.isClient()) {
            return;
        }
        BlockEvent.ClickedEvent event = new BlockEvent.ClickedEvent(handler.getBlock(), player);
        handler.callScript(EnumScriptType.CLICKED, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onScriptBlockBreak(IScriptBlockHandler handler) {
        if (handler.isClient()) {
            return;
        }
        BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(handler.getBlock());
        handler.callScript(EnumScriptType.BROKEN, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onScriptBlockHarvest(IScriptBlockHandler handler, EntityPlayer player) {
        if (handler.isClient()) {
            return false;
        }
        BlockEvent.HarvestedEvent event = new BlockEvent.HarvestedEvent(handler.getBlock(), player);
        handler.callScript(EnumScriptType.HARVESTED, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onScriptBlockExploded(IScriptBlockHandler handler) {
        if (handler.isClient()) {
            return false;
        }
        BlockEvent.ExplodedEvent event = new BlockEvent.ExplodedEvent(handler.getBlock());
        handler.callScript(EnumScriptType.EXPLODED, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onScriptBlockNeighborChanged(IScriptBlockHandler handler, int x, int y, int z) {
        if (handler.isClient()) {
            return;
        }
        BlockEvent.NeighborChangedEvent event = new BlockEvent.NeighborChangedEvent(handler.getBlock(), NpcAPI.Instance().getIPos(x, y, z));
        handler.callScript(EnumScriptType.NEIGHBOR_CHANGED, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onScriptBlockRedstonePower(IScriptBlockHandler handler, int prevPower, int power) {
        if (handler.isClient()) {
            return;
        }
        BlockEvent.RedstoneEvent event = new BlockEvent.RedstoneEvent(handler.getBlock(), prevPower, power);
        handler.callScript(EnumScriptType.REDSTONE, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onScriptBlockInit(IScriptBlockHandler handler) {
        if (handler.isClient()) {
            return;
        }
        BlockEvent.InitEvent event = new BlockEvent.InitEvent(handler.getBlock());
        handler.callScript(EnumScriptType.INIT, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onScriptBlockUpdate(IScriptBlockHandler handler) {
        if (handler.isClient()) {
            return;
        }
        BlockEvent.UpdateEvent event = new BlockEvent.UpdateEvent(handler.getBlock());
        handler.callScript(EnumScriptType.TICK, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onScriptBlockTimer(IScriptBlockHandler handler, int id) {
        BlockEvent.TimerEvent event = new BlockEvent.TimerEvent(handler.getBlock(), id);
        handler.callScript(EnumScriptType.TIMER, (Event)event);
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onPartyFinished(Party party, Quest quest) {
        EntityPlayer player = party.getPartyLeader();
        if (player != null) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(player);
            PartyEvent.PartyQuestCompletedEvent event = new PartyEvent.PartyQuestCompletedEvent(party, quest);
            handler.callScript(EnumScriptType.PARTY_QUEST_COMPLETED, (Event)event);
            NpcAPI.EVENT_BUS.post((Event)event);
        }
    }

    public static void onPartyQuestSet(Party party, PartyEvent.PartyQuestSetEvent event) {
        EntityPlayer player = party.getPartyLeader();
        if (player != null) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(player);
            handler.callScript(EnumScriptType.PARTY_QUEST_SET, (Event)event);
            NpcAPI.EVENT_BUS.post((Event)event);
        }
    }

    public static void onPartyTurnIn(Party party, PartyEvent.PartyQuestTurnedInEvent event) {
        EntityPlayer player = party.getPartyLeader();
        if (player != null) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(player);
            handler.callScript(EnumScriptType.PARTY_QUEST_TURNED_IN, (Event)event);
            NpcAPI.EVENT_BUS.post((Event)event);
        }
    }

    public static void onPartyInvite(Party party, PartyEvent.PartyInviteEvent event) {
        EntityPlayer player = party.getPartyLeader();
        if (player != null) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(player);
            handler.callScript(EnumScriptType.PARTY_INVITE, (Event)event);
            NpcAPI.EVENT_BUS.post((Event)event);
        }
    }

    public static void onPartyKick(Party party, PartyEvent.PartyKickEvent event) {
        EntityPlayer player = party.getPartyLeader();
        if (player != null) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(player);
            handler.callScript(EnumScriptType.PARTY_KICK, (Event)event);
            NpcAPI.EVENT_BUS.post((Event)event);
        }
    }

    public static void onPartyLeave(Party party, PartyEvent.PartyLeaveEvent event) {
        EntityPlayer player = party.getPartyLeader();
        if (player != null) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(player);
            handler.callScript(EnumScriptType.PARTY_LEAVE, (Event)event);
            NpcAPI.EVENT_BUS.post((Event)event);
        }
    }

    public static void onPartyDisband(Party party, PartyEvent.PartyDisbandEvent event) {
        EntityPlayer player = party.getPartyLeader();
        if (player != null) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(player);
            handler.callScript(EnumScriptType.PARTY_DISBAND, (Event)event);
            NpcAPI.EVENT_BUS.post((Event)event);
        }
    }

    private static boolean postAnimationEvent(IAnimationEvent event) {
        IScriptHandlerPacket handler;
        IAnimatable animatable = event.getAnimation().getParent().getEntity();
        if (animatable instanceof ICustomNpc) {
            EntityNPCInterface npc = (EntityNPCInterface)((Object)((ICustomNpc)animatable).getMCEntity());
            handler = npc.script;
        } else {
            handler = ScriptController.Instance.getPlayerScripts((IPlayer)animatable);
        }
        if (handler.isClient()) {
            return false;
        }
        handler.callScript(event.getHookName(), (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onAnimationStarted(IAnimation animation) {
        if (animation.getParent() == null || animation.getParent().getEntity() == null) {
            return false;
        }
        return EventHooks.postAnimationEvent(new AnimationEvent.Started(animation));
    }

    public static void onAnimationEnded(IAnimation animation) {
        if (animation.getParent() == null || animation.getParent().getEntity() == null) {
            return;
        }
        EventHooks.postAnimationEvent(new AnimationEvent.Ended(animation));
    }

    public static void onAnimationFrameEntered(IAnimation animation, IFrame frame) {
        if (frame == null || animation.getParent() == null || animation.getParent().getEntity() == null) {
            return;
        }
        EventHooks.postAnimationEvent(new AnimationEvent.FrameEvent.Entered(animation, frame));
    }

    public static void onAnimationFrameExited(IAnimation animation, IFrame frame) {
        if (frame == null || animation.getParent() == null || animation.getParent().getEntity() == null) {
            return;
        }
        EventHooks.postAnimationEvent(new AnimationEvent.FrameEvent.Exited(animation, frame));
    }

    public static boolean onAuctionCreate(PlayerDataScript handler, AuctionEvent.CreateEvent event) {
        handler.callScript(EnumScriptType.AUCTION_CREATE, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onAuctionBid(PlayerDataScript handler, AuctionEvent.BidEvent event) {
        handler.callScript(EnumScriptType.AUCTION_BID, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onAuctionBuyout(PlayerDataScript handler, AuctionEvent.BuyoutEvent event) {
        handler.callScript(EnumScriptType.AUCTION_BUYOUT, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onAuctionCancel(PlayerDataScript handler, AuctionEvent.CancelEvent event) {
        handler.callScript(EnumScriptType.AUCTION_CANCEL, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onAuctionClaim(PlayerDataScript handler, AuctionEvent.ClaimEvent event) {
        handler.callScript(EnumScriptType.AUCTION_CLAIM, (Event)event);
        return NpcAPI.EVENT_BUS.post((Event)event);
    }
}

