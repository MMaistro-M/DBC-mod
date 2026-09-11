/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedInEvent
 *  cpw.mods.fml.common.network.FMLEmbeddedChannel
 *  cpw.mods.fml.common.network.FMLOutboundHandler
 *  cpw.mods.fml.common.network.FMLOutboundHandler$OutboundTarget
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.channel.ChannelHandler
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package invtweaks.forge;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import invtweaks.api.IItemTreeListener;
import invtweaks.api.InvTweaksAPI;
import invtweaks.api.SortingMethod;
import invtweaks.api.container.ContainerSection;
import invtweaks.network.ITMessageToMessageCodec;
import invtweaks.network.handlers.ClickMessageHandler;
import invtweaks.network.handlers.LoginMessageHandler;
import invtweaks.network.handlers.SortingCompleteMessageHandler;
import invtweaks.network.packets.ITPacketLogin;
import io.netty.channel.ChannelHandler;
import java.util.EnumMap;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CommonProxy
implements InvTweaksAPI {
    protected static EnumMap<Side, FMLEmbeddedChannel> invtweaksChannel;

    public void preInit(FMLPreInitializationEvent e) {
    }

    public void init(FMLInitializationEvent e) {
        invtweaksChannel = NetworkRegistry.INSTANCE.newChannel("InventoryTweaks", new ChannelHandler[]{new ITMessageToMessageCodec(), new ClickMessageHandler(), new LoginMessageHandler(), new SortingCompleteMessageHandler()});
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    public void setServerAssistEnabled(boolean enabled) {
    }

    public void setServerHasInvTweaks(boolean hasInvTweaks) {
    }

    @SideOnly(value=Side.CLIENT)
    public void slotClick(PlayerControllerMP playerController, int windowId, int slot, int data, int action, EntityPlayer player) {
    }

    public void sortComplete() {
    }

    @Override
    public void addOnLoadListener(IItemTreeListener listener) {
    }

    @Override
    public boolean removeOnLoadListener(IItemTreeListener listener) {
        return false;
    }

    @Override
    public void setSortKeyEnabled(boolean enabled) {
    }

    @Override
    public void setTextboxMode(boolean enabled) {
    }

    @Override
    public int compareItems(ItemStack i, ItemStack j) {
        return 0;
    }

    @Override
    public void sort(ContainerSection section, SortingMethod method) {
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent e) {
        FMLEmbeddedChannel channel = invtweaksChannel.get(Side.SERVER);
        channel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set((Object)FMLOutboundHandler.OutboundTarget.PLAYER);
        channel.attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set((Object)e.player);
        channel.writeOutbound(new Object[]{new ITPacketLogin()});
    }
}

