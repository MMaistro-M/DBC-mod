/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$ItemPickupEvent
 *  cpw.mods.fml.common.network.FMLEmbeddedChannel
 *  cpw.mods.fml.common.network.FMLNetworkEvent$ClientConnectedToServerEvent
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.ItemTooltipEvent
 */
package invtweaks.forge;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.relauncher.Side;
import invtweaks.InvTweaks;
import invtweaks.InvTweaksHandlerSorting;
import invtweaks.InvTweaksItemTreeLoader;
import invtweaks.InvTweaksObfuscation;
import invtweaks.api.IItemTreeListener;
import invtweaks.api.SortingMethod;
import invtweaks.api.container.ContainerSection;
import invtweaks.forge.CommonProxy;
import invtweaks.forge.ForgeClientTick;
import invtweaks.network.packets.ITPacketClick;
import invtweaks.network.packets.ITPacketSortComplete;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class ClientProxy
extends CommonProxy {
    public static final KeyBinding KEYBINDING_SORT = new KeyBinding("invtweaks.key.sort", 19, "invtweaks.key.category");
    private InvTweaks instance;
    private ForgeClientTick clientTick;
    public boolean serverSupportEnabled = false;
    public boolean serverSupportDetected = false;

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        InvTweaks.log = e.getModLog();
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        Minecraft mc = FMLClientHandler.instance().getClient();
        this.instance = new InvTweaks(mc);
        this.clientTick = new ForgeClientTick(this.instance);
        FMLCommonHandler.instance().bus().register((Object)this.clientTick);
        MinecraftForge.EVENT_BUS.register((Object)this);
        ClientRegistry.registerKeyBinding((KeyBinding)KEYBINDING_SORT);
    }

    @SubscribeEvent
    public void notifyPickup(PlayerEvent.ItemPickupEvent e) {
        this.instance.setItemPickupPending(true);
    }

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent e) {
        if (e.showAdvancedItemTooltips) {
            e.toolTip.add(1, EnumChatFormatting.GRAY + Item.field_150901_e.func_148750_c((Object)e.itemStack.func_77973_b()));
        }
    }

    @Override
    public void setServerAssistEnabled(boolean enabled) {
        this.serverSupportEnabled = this.serverSupportDetected && enabled;
    }

    @Override
    public void setServerHasInvTweaks(boolean hasInvTweaks) {
        this.serverSupportDetected = hasInvTweaks;
        this.serverSupportEnabled = hasInvTweaks && !InvTweaks.getConfigManager().getConfig().getProperty("enableServerItemSwap").equals("false");
    }

    @Override
    public void slotClick(PlayerControllerMP playerController, int windowId, int slot, int data, int action, EntityPlayer player) {
        if (this.serverSupportEnabled) {
            player.field_71070_bA.func_75144_a(slot, data, action, player);
            ((FMLEmbeddedChannel)invtweaksChannel.get(Side.CLIENT)).writeOutbound(new Object[]{new ITPacketClick(slot, data, action)});
        } else {
            playerController.func_78753_a(windowId, slot, data, action, player);
        }
    }

    @Override
    public void sortComplete() {
        if (this.serverSupportEnabled) {
            ((FMLEmbeddedChannel)invtweaksChannel.get(Side.CLIENT)).writeOutbound(new Object[]{new ITPacketSortComplete()});
        }
    }

    @Override
    public void addOnLoadListener(IItemTreeListener listener) {
        InvTweaksItemTreeLoader.addOnLoadListener(listener);
    }

    @Override
    public boolean removeOnLoadListener(IItemTreeListener listener) {
        return InvTweaksItemTreeLoader.removeOnLoadListener(listener);
    }

    @Override
    public void setSortKeyEnabled(boolean enabled) {
        this.instance.setSortKeyEnabled(enabled);
    }

    @Override
    public void setTextboxMode(boolean enabled) {
        this.instance.setTextboxMode(enabled);
    }

    @Override
    public int compareItems(ItemStack i, ItemStack j) {
        return this.instance.compareItems(i, j);
    }

    @Override
    public void sort(ContainerSection section, SortingMethod method) {
        Minecraft mc = FMLClientHandler.instance().getClient();
        Container currentContainer = mc.field_71439_g.field_71069_bz;
        if (mc.field_71462_r instanceof GuiContainer) {
            currentContainer = ((GuiContainer)mc.field_71462_r).field_147002_h;
        }
        try {
            ClientProxy clientProxy = this;
            new InvTweaksHandlerSorting(mc, clientProxy.instance.getConfigManager().getConfig(), section, method, InvTweaksObfuscation.getSpecialChestRowSize(currentContainer)).sort();
        }
        catch (Exception e) {
            InvTweaks.logInGameErrorStatic("invtweaks.sort.chest.error", e);
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onConnectionToServer(FMLNetworkEvent.ClientConnectedToServerEvent e) {
        this.setServerHasInvTweaks(false);
    }
}

