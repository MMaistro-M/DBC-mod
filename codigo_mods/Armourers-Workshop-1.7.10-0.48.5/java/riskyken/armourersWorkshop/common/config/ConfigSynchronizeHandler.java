/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 */
package riskyken.armourersWorkshop.common.config;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.server.MessageServerSyncConfig;

public final class ConfigSynchronizeHandler {
    public ConfigSynchronizeHandler() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (!event.entity.field_70170_p.field_72995_K && event.entity instanceof EntityPlayerMP) {
            MessageServerSyncConfig message = new MessageServerSyncConfig((EntityPlayer)event.entity);
            PacketHandler.networkWrapper.sendTo((IMessage)message, (EntityPlayerMP)event.entity);
        }
    }

    public static void resyncConfigs() {
        MessageServerSyncConfig message = new MessageServerSyncConfig();
        PacketHandler.networkWrapper.sendToAll((IMessage)message);
    }
}

