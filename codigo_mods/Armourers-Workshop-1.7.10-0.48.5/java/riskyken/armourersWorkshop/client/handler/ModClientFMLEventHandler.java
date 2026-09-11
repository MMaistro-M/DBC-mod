/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.event.ConfigChangedEvent$OnConfigChangedEvent
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.InputEvent$KeyInputEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$RenderTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Type
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.event.ClickEvent
 *  net.minecraft.event.ClickEvent$Action
 *  net.minecraft.event.HoverEvent
 *  net.minecraft.event.HoverEvent$Action
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 */
package riskyken.armourersWorkshop.client.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import riskyken.armourersWorkshop.client.settings.Keybindings;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientKeyPress;
import riskyken.armourersWorkshop.common.update.UpdateCheck;
import riskyken.armourersWorkshop.utils.TranslateUtils;

public class ModClientFMLEventHandler {
    private boolean shownUpdateInfo = false;
    private boolean showmDevWarning;
    public static float renderTickTime;
    public static int skinRendersThisTick;
    public static int skinRenderLastTick;

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if (eventArgs.modID.equals("armourersWorkshop")) {
            ConfigHandler.loadConfigFile();
            ConfigHandlerClient.loadConfigFile();
        }
    }

    public void onPlayerTickEndEvent() {
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        if (!this.shownUpdateInfo && UpdateCheck.updateFound) {
            this.shownUpdateInfo = true;
            ChatComponentText updateMessage = new ChatComponentText(TranslateUtils.translate("chat.armourersworkshop:updateAvailable", UpdateCheck.remoteModVersion) + " ");
            ChatComponentText updateURL = new ChatComponentText(TranslateUtils.translate("chat.armourersworkshop:updateDownload"));
            updateURL.func_150256_b().func_150228_d(Boolean.valueOf(true));
            updateURL.func_150256_b().func_150238_a(EnumChatFormatting.BLUE);
            updateURL.func_150256_b().func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText(TranslateUtils.translate("chat.armourersworkshop:updateDownloadRollover"))));
            updateURL.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://minecraft.curseforge.com/projects/armourers-workshop/files"));
            updateMessage.func_150257_a((IChatComponent)updateURL);
            player.func_145747_a((IChatComponent)updateMessage);
        }
        if (!this.showmDevWarning) {
            // empty if block
        }
    }

    @SubscribeEvent
    public void onKeyInputEvent(InputEvent.KeyInputEvent event) {
        if (Keybindings.openCustomArmourGui.func_151468_f() & ConfigHandler.allowEquipmentWardrobe) {
            PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientKeyPress(0));
        }
        if (Keybindings.undo.func_151468_f()) {
            PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientKeyPress(1));
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == Side.CLIENT && event.type == TickEvent.Type.PLAYER && event.phase == TickEvent.Phase.END) {
            this.onPlayerTickEndEvent();
        }
    }

    @SubscribeEvent
    public void onRenderTickEvent(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            renderTickTime = event.renderTickTime;
            skinRenderLastTick = skinRendersThisTick;
            skinRendersThisTick = 0;
        }
    }

    static {
        skinRendersThisTick = 0;
        skinRenderLastTick = 0;
    }
}

