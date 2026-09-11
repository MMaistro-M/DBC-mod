/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$ItemPickupEvent
 *  net.minecraft.block.Block
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.util.FakePlayer
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.event.entity.item.ItemTossEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.event.entity.player.AnvilRepairEvent
 *  net.minecraftforge.event.entity.player.EntityInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerDestroyItemEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 *  net.minecraftforge.event.entity.player.PlayerUseItemEvent$Finish
 *  net.minecraftforge.event.entity.player.PlayerUseItemEvent$Start
 *  net.minecraftforge.event.entity.player.PlayerUseItemEvent$Stop
 *  net.minecraftforge.event.entity.player.PlayerUseItemEvent$Tick
 */
package noppes.npcs;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.EventHooks;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.IBlock;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.item.IItemCustomizable;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.blocks.BlockBanner;
import noppes.npcs.blocks.BlockTallLamp;
import noppes.npcs.blocks.tiles.TileColorable;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.items.ItemNpcTool;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.event.ItemEvent;

public class ScriptItemEventHandler {
    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving == null || event.entityLiving.field_70170_p == null || event.entityLiving instanceof EntityPlayer || event.entityLiving.field_70173_aa % 10 != 0) {
            return;
        }
        if (event.entityLiving instanceof EntityCustomNpc) {
            HashMap[] inventories;
            HashMap<Integer, ItemStack> armor = ((EntityCustomNpc)event.entityLiving).inventory.armor;
            HashMap<Integer, ItemStack> weapons = ((EntityCustomNpc)event.entityLiving).inventory.weapons;
            for (HashMap inventory : inventories = new HashMap[]{armor, weapons}) {
                for (ItemStack stack : inventory.values()) {
                    if (stack == null || !NoppesUtilServer.isScriptableItem(stack.func_77973_b())) continue;
                    IItemStack istack = NpcAPI.Instance().getIItemStack(stack);
                    EventHooks.onScriptItemUpdate((IItemCustomizable)istack, event.entityLiving);
                }
            }
        } else if (event.entityLiving.func_70694_bm() != null && NoppesUtilServer.isScriptableItem(event.entityLiving.func_70694_bm().func_77973_b()) && !event.isCanceled()) {
            IItemStack itemStack = NpcAPI.Instance().getIItemStack(event.entityLiving.func_70694_bm());
            EventHooks.onScriptItemUpdate((IItemCustomizable)itemStack, event.entityLiving);
        }
    }

    @SubscribeEvent
    public void onItemToss(ItemTossEvent e) {
        if (!this.isValidContext(e.player) || e.isCanceled()) {
            return;
        }
        if (e.entityItem == null) {
            return;
        }
        EntityItem entityItem = e.entityItem;
        IItemCustomizable c = this.getCustomizable(entityItem.func_92059_d());
        if (c != null) {
            e.setCanceled(EventHooks.onScriptItemTossed(c, e.player, entityItem));
        }
    }

    @SubscribeEvent
    public void onItemPickup(PlayerEvent.ItemPickupEvent e) {
        if (!this.isValidContext(e.player) || e.isCanceled()) {
            return;
        }
        IItemCustomizable c = this.getCustomizable(e.player.func_70694_bm());
        if (c != null) {
            EventHooks.onScriptItemPickedUp(c, e.player);
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent e) {
        if (e.world.field_72995_K || !(e.entity instanceof EntityItem)) {
            return;
        }
        EntityItem itemEnt = (EntityItem)e.entity;
        IItemCustomizable c = this.getCustomizable(itemEnt.func_92059_d());
        if (c != null) {
            e.setCanceled(EventHooks.onScriptItemSpawn(c, itemEnt));
        }
    }

    @SubscribeEvent
    public void onEntityInteract(EntityInteractEvent e) {
        EntityPlayer player = e.entityPlayer;
        if (!this.isValidContext(player) || e.isCanceled()) {
            return;
        }
        IItemCustomizable c = this.getCustomizable(player.func_70694_bm());
        IPlayer ip = NoppesUtilServer.getIPlayer(player);
        if (c != null && ip != null) {
            boolean cancelA = EventHooks.onScriptItemInteract(c, new ItemEvent.InteractEvent(c, ip, 2, NpcAPI.Instance().getIEntity(e.target)));
            boolean cancelB = EventHooks.onScriptItemRightClick(c, new ItemEvent.RightClickEvent(c, ip, 1, NpcAPI.Instance().getIEntity(e.target)));
            if (cancelA || cancelB) {
                e.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent e) {
        ItemStack held;
        EntityPlayer player = e.entityPlayer;
        if (player == null || e.action == null) {
            return;
        }
        if (!this.isValidContext(e.entityPlayer)) {
            return;
        }
        if (e.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK && ItemNpcTool.isPaintbrush(held = player.func_70694_bm()) && CustomNpcsPermissions.hasPermission(player, CustomNpcsPermissions.NPC_BUILD)) {
            TileEntity tile;
            int meta;
            int x = e.x;
            int y = e.y;
            int z = e.z;
            Block block = player.field_70170_p.func_147439_a(x, y, z);
            if ((block instanceof BlockTallLamp || block instanceof BlockBanner) && (meta = player.field_70170_p.func_72805_g(x, y, z)) >= 7) {
                --y;
            }
            if ((tile = player.field_70170_p.func_147438_o(x, y, z)) instanceof TileColorable) {
                int color = ((TileColorable)tile).color;
                if (!held.func_77942_o()) {
                    held.func_77982_d(new NBTTagCompound());
                }
                ItemNpcTool.setColor(held.func_77978_p(), color);
                e.setCanceled(true);
                return;
            }
        }
        if (PlayerDataController.Instance == null) {
            return;
        }
        PlayerData pd = PlayerData.get(player);
        if (pd == null) {
            return;
        }
        ItemStack held2 = player.func_70694_bm();
        IItemCustomizable c = this.getCustomizable(held2);
        IPlayer ip = NoppesUtilServer.getIPlayer(player);
        if (c == null || ip == null) {
            return;
        }
        if (e.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
            if (pd.hadInteract) {
                pd.hadInteract = false;
                return;
            }
            if (EventHooks.onScriptItemRightClick(c, new ItemEvent.RightClickEvent(c, ip, 0, null))) {
                e.setCanceled(true);
            }
        } else if (e.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            pd.hadInteract = true;
            IWorld iw = NpcAPI.Instance().getIWorld(e.world);
            IBlock blockCtx = NpcAPI.Instance().getIBlock(iw, e.x, e.y, e.z);
            if (EventHooks.onScriptItemRightClick(c, new ItemEvent.RightClickEvent(c, ip, 2, blockCtx))) {
                e.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onStartUseItem(PlayerUseItemEvent.Start e) {
        if (!this.isValidContext(e.entityPlayer)) {
            return;
        }
        IItemCustomizable c = this.getCustomizable(e.item);
        IPlayer ip = NoppesUtilServer.getIPlayer(e.entityPlayer);
        if (c != null && ip != null && EventHooks.onStartUsingCustomItem(c, ip, e.duration)) {
            e.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onUseItemTick(PlayerUseItemEvent.Tick e) {
        if (!this.isValidContext(e.entityPlayer)) {
            return;
        }
        IItemCustomizable c = this.getCustomizable(e.item);
        IPlayer ip = NoppesUtilServer.getIPlayer(e.entityPlayer);
        if (c != null && ip != null && EventHooks.onUsingCustomItem(c, ip, e.duration)) {
            e.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onStopUseItem(PlayerUseItemEvent.Stop e) {
        if (!this.isValidContext(e.entityPlayer)) {
            return;
        }
        IItemCustomizable c = this.getCustomizable(e.item);
        IPlayer ip = NoppesUtilServer.getIPlayer(e.entityPlayer);
        if (c != null && ip != null && EventHooks.onStopUsingCustomItem(c, ip, e.duration)) {
            e.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onFinishUseItem(PlayerUseItemEvent.Finish e) {
        if (!this.isValidContext(e.entityPlayer)) {
            return;
        }
        IItemCustomizable c = this.getCustomizable(e.item);
        IPlayer ip = NoppesUtilServer.getIPlayer(e.entityPlayer);
        if (c != null && ip != null) {
            EventHooks.onFinishUsingCustomItem(c, ip, e.duration);
        }
    }

    @SubscribeEvent
    public void onAnvilRepair(AnvilRepairEvent e) {
        if (!this.isValidContext(e.entityPlayer)) {
            return;
        }
        if (e.output == null) {
            return;
        }
        IItemStack out = NpcAPI.Instance().getIItemStack(e.output);
        if (out instanceof IItemCustomizable) {
            IPlayer ip = NoppesUtilServer.getIPlayer(e.entityPlayer);
            IItemStack left = NpcAPI.Instance().getIItemStack(e.left);
            IItemStack right = NpcAPI.Instance().getIItemStack(e.right);
            EventHooks.onRepairCustomItem((IItemCustomizable)out, ip, left, right, e.breakChance);
        }
    }

    @SubscribeEvent
    public void onDestroyItem(PlayerDestroyItemEvent e) {
        if (!this.isValidContext(e.entityPlayer)) {
            return;
        }
        if (e.original == null) {
            return;
        }
        IItemStack orig = NpcAPI.Instance().getIItemStack(e.original);
        if (orig instanceof IItemCustomizable) {
            IPlayer ip = NoppesUtilServer.getIPlayer(e.entityPlayer);
            EventHooks.onBreakCustomItem((IItemCustomizable)orig, ip);
        }
    }

    private boolean isValidContext(EntityPlayer player) {
        return player != null && player.field_70170_p instanceof WorldServer && !player.field_70170_p.field_72995_K && !(player instanceof FakePlayer);
    }

    private IItemCustomizable getCustomizable(ItemStack stack) {
        if (stack == null || stack.func_77973_b() == null || !NoppesUtilServer.isScriptableItem(stack.func_77973_b())) {
            return null;
        }
        IItemStack raw = NpcAPI.Instance().getIItemStack(stack);
        return raw instanceof IItemCustomizable ? (IItemCustomizable)raw : null;
    }
}

