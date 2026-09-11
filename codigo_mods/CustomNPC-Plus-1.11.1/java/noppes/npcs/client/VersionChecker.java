/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.event.ClickEvent
 *  net.minecraft.event.ClickEvent$Action
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 */
package noppes.npcs.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class VersionChecker
extends Thread {
    private int revision = 15;

    @Override
    public void run() {
        EntityClientPlayerMP player;
        String name = "\u00a7cCustomNPC+\u00a7f";
        String link = "\u00a79\u00a7nClick here";
        String text = name + " installed. For more info " + link;
        try {
            player = Minecraft.func_71410_x().field_71439_g;
        }
        catch (NoSuchMethodError e) {
            return;
        }
        while ((player = Minecraft.func_71410_x().field_71439_g) == null) {
            try {
                Thread.sleep(2000L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ChatComponentTranslation message = new ChatComponentTranslation(text, new Object[0]);
        message.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/customnpc-plus"));
        player.func_145747_a((IChatComponent)message);
    }
}

