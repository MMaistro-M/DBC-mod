/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedInEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedOutEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$ServerTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$WorldTickEvent
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.world.WorldServer
 */
package noppes.npcs;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import kamkeel.npcs.controllers.ProfileController;
import kamkeel.npcs.controllers.SyncController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import noppes.npcs.NPCSpawning;
import noppes.npcs.controllers.AuctionController;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.action.ActionManager;
import noppes.npcs.entity.EntityNPCInterface;

public class ServerTickHandler {
    private String serverName = null;

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            ActionManager.GLOBAL.tick();
            if (AuctionController.Instance != null) {
                AuctionController.Instance.onServerTick();
            }
        }
    }

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            NPCSpawning.findChunksForSpawning((WorldServer)event.world);
        }
    }

    @SubscribeEvent
    public void playerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayerMP player = (EntityPlayerMP)event.player;
        PlayerData playerData = PlayerData.get(event.player);
        if (playerData != null) {
            playerData.onLogin();
        }
        ProfileController.Instance.login((EntityPlayer)player);
        SyncController.beginLogin(player);
        SyncController.syncEffects(player);
        ScriptController.Instance.syncClientScripts(player);
        SyncController.syncAbilities(player);
        if (AuctionController.Instance != null) {
            AuctionController.Instance.onPlayerLogin((EntityPlayer)player);
        }
    }

    @SubscribeEvent
    public void playerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        PlayerData playerData;
        if (event.player.field_70154_o instanceof EntityNPCInterface) {
            event.player.func_70078_a(null);
        }
        if ((playerData = PlayerData.get(event.player)) != null) {
            playerData.onLogout();
        }
        if (AuctionController.Instance != null) {
            AuctionController.Instance.onPlayerLogout(event.player.func_110124_au());
        }
        ProfileController.Instance.logout(event.player);
    }
}

