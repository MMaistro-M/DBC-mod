/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.reflect.ClassPath
 *  com.google.common.reflect.ClassPath$ClassInfo
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.ModContainer
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent
 *  cpw.mods.fml.common.gameevent.TickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$RenderTickEvent
 *  cpw.mods.fml.common.network.FMLNetworkEvent$ClientCustomPacketEvent
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.event.entity.EntityEvent
 *  net.minecraftforge.event.entity.EntityEvent$EntityConstructing
 *  net.minecraftforge.event.entity.player.ItemTooltipEvent
 *  net.minecraftforge.event.world.ChunkDataEvent
 *  net.minecraftforge.event.world.ChunkEvent
 *  net.minecraftforge.event.world.ChunkWatchEvent
 *  net.minecraftforge.event.world.WorldEvent
 *  net.minecraftforge.event.world.WorldEvent$PotentialSpawns
 */
package noppes.npcs;

import com.google.common.reflect.ClassPath;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.relauncher.Side;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Vector;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.ChunkWatchEvent;
import net.minecraftforge.event.world.WorldEvent;
import noppes.npcs.CustomNpcs;
import noppes.npcs.EventHooks;
import noppes.npcs.compat.PixelmonHelper;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.scripted.event.ForgeEvent;

public class ScriptForgeEventHandler {
    @SubscribeEvent
    public void forgeEntity(Event event) {
        if (CustomNpcs.getServer() != null && ScriptController.Instance.forgeScripts.isEnabled()) {
            if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
                return;
            }
            if (event instanceof TickEvent && (((TickEvent)event).side != Side.SERVER || ((TickEvent)event).phase != TickEvent.Phase.START)) {
                return;
            }
            if (event instanceof EntityEvent) {
                EntityEvent ev2 = (EntityEvent)event;
                if (ev2.entity != null && ev2.entity.field_70170_p instanceof WorldServer) {
                    EventHooks.onForgeEntityEvent(ev2);
                }
            } else if (event instanceof WorldEvent) {
                WorldEvent ev1 = (WorldEvent)event;
                if (ev1.world instanceof WorldServer) {
                    EventHooks.onForgeWorldEvent(ev1);
                }
            } else if (!(event instanceof TickEvent) || ((TickEvent)event).side != Side.CLIENT) {
                if (event instanceof PlayerEvent) {
                    PlayerEvent ev = (PlayerEvent)event;
                    if (ev.player == null || !(ev.player.field_70170_p instanceof WorldServer)) {
                        return;
                    }
                }
                EventHooks.onForgeEvent(new ForgeEvent(event), event);
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    public ScriptForgeEventHandler registerForgeEvents() {
        try {
            e = this.getClass().getMethod("forgeEntity", new Class[]{Event.class});
            register = FMLCommonHandler.instance().bus().getClass().getDeclaredMethod("register", new Class[]{Class.class, Object.class, Method.class, ModContainer.class});
            register.setAccessible(true);
            list = new ArrayList<E>();
            list.addAll(ClassPath.from((ClassLoader)this.getClass().getClassLoader()).getTopLevelClassesRecursive("cpw.mods.fml.common.gameevent"));
            list.addAll(ClassPath.from((ClassLoader)this.getClass().getClassLoader()).getTopLevelClassesRecursive("net.minecraftforge.event"));
            list.removeAll((Collection<?>)ClassPath.from((ClassLoader)this.getClass().getClassLoader()).getTopLevelClassesRecursive("net.minecraftforge.event.terraingen"));
            e1 = list.iterator();
            block4: while (true) {
                if (!e1.hasNext()) {
                    if (PixelmonHelper.Enabled) {
                        try {
                            e2 = ClassLoader.class.getDeclaredField("classes");
                            e2.setAccessible(true);
                            classLoader1 = Thread.currentThread().getContextClassLoader();
                            classes1 = new ArrayList<E>((Vector)e2.get(classLoader1));
                            for (Class var11_13 : classes1) {
                            }
                        }
                        catch (Exception var12) {
                            var12.printStackTrace();
                        }
                        return this;
                    }
                    return this;
                }
                classLoader = (ClassPath.ClassInfo)e1.next();
                classes = classLoader.getName();
                if (classes.startsWith("net.minecraftforge.event.terraingen")) continue;
                infoClass = classLoader.load();
                c = new ArrayList<Class<?>>(Arrays.asList(infoClass.getDeclaredClasses()));
                if (c.isEmpty()) {
                    c.add(infoClass);
                }
                var10 = c.iterator();
                while (true) {
                    if (var10.hasNext()) ** break;
                    continue block4;
                    c1 = var10.next();
                    if (EntityEvent.EntityConstructing.class.isAssignableFrom(c1) || WorldEvent.PotentialSpawns.class.isAssignableFrom(c1) || TickEvent.RenderTickEvent.class.isAssignableFrom(c1) || TickEvent.ClientTickEvent.class.isAssignableFrom(c1) || FMLNetworkEvent.ClientCustomPacketEvent.class.isAssignableFrom(c1) || ItemTooltipEvent.class.isAssignableFrom(c1) || !Event.class.isAssignableFrom(c1) || Modifier.isAbstract(c1.getModifiers()) || !Modifier.isPublic(c1.getModifiers()) || ChunkEvent.class.isAssignableFrom(c1) || ChunkWatchEvent.class.isAssignableFrom(c1) || ChunkDataEvent.class.isAssignableFrom(c1)) continue;
                    register.invoke((Object)FMLCommonHandler.instance().bus(), new Object[]{c1, this, e, Loader.instance().activeModContainer()});
                }
                break;
            }
        }
        catch (Exception var13) {
            var13.printStackTrace();
            return this;
        }
    }
}

