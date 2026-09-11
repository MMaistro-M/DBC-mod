/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Type
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.event.ClickEvent
 *  net.minecraft.event.ClickEvent$Action
 *  net.minecraft.event.HoverEvent
 *  net.minecraft.event.HoverEvent$Action
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  org.apache.commons.io.Charsets
 */
package riskyken.armourersWorkshop.client.handler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.File;
import java.nio.charset.CharsetEncoder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import org.apache.commons.io.Charsets;

@SideOnly(value=Side.CLIENT)
public final class RehostedJarHandler {
    private static final String STOP_MOD_REPOSTS_URL = "http://stopmodreposts.org/";
    private boolean validJar = false;
    private long lastMessagePost = 0L;
    private long messagePostRate = 60000L;

    public RehostedJarHandler(File jarFile, String originalName) {
        this.checkIfJarIsValid(jarFile, originalName);
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    private void checkIfJarIsValid(File jarFile, String originalName) {
        if (jarFile == null) {
            return;
        }
        if (jarFile.getName().equals(originalName)) {
            this.validJar = true;
            return;
        }
        CharsetEncoder asciiEncoder = Charsets.US_ASCII.newEncoder();
        if (!asciiEncoder.canEncode(jarFile.getName())) {
            this.validJar = true;
            return;
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (this.validJar) {
            return;
        }
        if (event.side != Side.CLIENT) {
            return;
        }
        if (event.type != TickEvent.Type.PLAYER) {
            return;
        }
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        if (this.lastMessagePost + this.messagePostRate > System.currentTimeMillis()) {
            return;
        }
        this.lastMessagePost = System.currentTimeMillis();
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        ChatComponentTranslation downloadLink = new ChatComponentTranslation("chat.armourersworkshop:invalidJarDownload", new Object[]{null});
        downloadLink.func_150256_b().func_150228_d(Boolean.valueOf(true));
        downloadLink.func_150256_b().func_150238_a(EnumChatFormatting.BLUE);
        downloadLink.func_150256_b().func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentTranslation("chat.armourersworkshop:invalidJarDownloadTooltip", new Object[]{null})));
        downloadLink.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://minecraft.curseforge.com/projects/armourers-workshop/files"));
        ChatComponentTranslation stopModRepostsLink = new ChatComponentTranslation("chat.armourersworkshop:invalidJarStopModReposts", new Object[]{null});
        stopModRepostsLink.func_150256_b().func_150228_d(Boolean.valueOf(true));
        stopModRepostsLink.func_150256_b().func_150238_a(EnumChatFormatting.BLUE);
        stopModRepostsLink.func_150256_b().func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentTranslation("chat.armourersworkshop:invalidJarStopModRepostsTooltip", new Object[]{null})));
        stopModRepostsLink.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.OPEN_URL, STOP_MOD_REPOSTS_URL));
        ChatComponentTranslation updateMessage = new ChatComponentTranslation("chat.armourersworkshop:invalidJar", new Object[]{downloadLink, stopModRepostsLink});
        updateMessage.func_150256_b().func_150238_a(EnumChatFormatting.RED);
        player.func_145747_a((IChatComponent)updateMessage);
    }
}

