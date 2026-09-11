/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.reflect.ClassPath
 *  com.google.common.reflect.ClassPath$ClassInfo
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$RenderTickEvent
 *  cpw.mods.fml.common.network.FMLNetworkEvent$ClientCustomPacketEvent
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.event.entity.EntityEvent$EntityConstructing
 *  net.minecraftforge.event.entity.player.ItemTooltipEvent
 *  net.minecraftforge.event.world.ChunkDataEvent
 *  net.minecraftforge.event.world.ChunkEvent
 *  net.minecraftforge.event.world.ChunkWatchEvent
 *  net.minecraftforge.event.world.WorldEvent$PotentialSpawns
 */
package noppes.npcs.controllers.data;

import com.google.common.reflect.ClassPath;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import kamkeel.npcs.network.packets.request.script.ForgeScriptPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.ChunkWatchEvent;
import net.minecraftforge.event.world.WorldEvent;
import noppes.npcs.CustomNpcs;
import noppes.npcs.EventHooks;
import noppes.npcs.constants.ScriptContext;
import noppes.npcs.controllers.ScriptContainer;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.ScriptHookController;
import noppes.npcs.controllers.data.IScriptUnit;
import noppes.npcs.controllers.data.MultiScriptHandler;
import noppes.npcs.janino.EventJaninoScript;
import org.apache.commons.lang3.StringUtils;

public class ForgeDataScript
extends MultiScriptHandler {
    private static final Object HOOK_LOCK = new Object();
    private static List<String> cachedHooks;
    private long lastForgeUpdate = -1L;
    private final HashSet<String> globalUnknownEvents = new HashSet();

    public boolean isEnabled() {
        return this.enabled && CustomNpcs.proxy.isGlobalForgeScripts() && ScriptController.HasStart && this.scripts.size() > 0;
    }

    @Override
    protected boolean canRunScripts() {
        return this.isEnabled();
    }

    @Override
    public ScriptContext getContext() {
        return ScriptContext.FORGE;
    }

    @Override
    protected boolean needsReInit() {
        return ScriptController.Instance.lastLoaded > this.lastInited || ScriptController.Instance.lastForgeUpdate > this.lastForgeUpdate;
    }

    @Override
    protected void reInitScripts() {
        this.lastInited = ScriptController.Instance.lastLoaded;
        this.lastForgeUpdate = ScriptController.Instance.lastForgeUpdate;
        this.globalUnknownEvents.clear();
        for (IScriptUnit script : this.scripts) {
            if (!(script instanceof ScriptContainer)) continue;
            ((ScriptContainer)script).errored = false;
        }
    }

    @Override
    public void callScript(String type, Event event) {
        if (!this.canRunScripts()) {
            return;
        }
        if (this.needsReInit()) {
            this.reInitScripts();
            if (!type.equals("init")) {
                EventHooks.onForgeInit(this);
            }
        }
        if (this.globalUnknownEvents.contains(type)) {
            return;
        }
        boolean anyHandled = false;
        for (IScriptUnit script : this.scripts) {
            if (script == null || script.hasErrored() || !script.hasCode()) continue;
            boolean wasUnknown = script.isUnknownFunction(type);
            script.run(type, (Object)event);
            if (wasUnknown && script.isUnknownFunction(type)) continue;
            anyHandled = true;
        }
        if (!anyHandled) {
            this.globalUnknownEvents.add(type);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getHooks() {
        if (cachedHooks != null) {
            return new ArrayList<String>(cachedHooks);
        }
        Object object = HOOK_LOCK;
        synchronized (object) {
            if (cachedHooks != null) {
                return new ArrayList<String>(cachedHooks);
            }
            ArrayList<String> hookList = new ArrayList<String>(ScriptHookController.Instance.getAllHooks(ScriptContext.FORGE.hookContext));
            ArrayList list = new ArrayList();
            try {
                list.addAll(ClassPath.from((ClassLoader)this.getClass().getClassLoader()).getTopLevelClassesRecursive("cpw.mods.fml.common.gameevent"));
                list.addAll(ClassPath.from((ClassLoader)this.getClass().getClassLoader()).getTopLevelClassesRecursive("net.minecraftforge.event"));
                list.removeAll((Collection<?>)ClassPath.from((ClassLoader)this.getClass().getClassLoader()).getTopLevelClassesRecursive("net.minecraftforge.event.terraingen"));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (ClassPath.ClassInfo classInfo : list) {
                Class infoClass = classInfo.load();
                ArrayList classes = new ArrayList(Arrays.asList(infoClass.getDeclaredClasses()));
                if (classes.isEmpty()) {
                    classes.add(infoClass);
                }
                for (Class clazz : classes) {
                    if (!this.isValidForgeEvent(clazz)) continue;
                    String eventName = clazz.getName();
                    int lastDot = eventName.lastIndexOf(".");
                    if (hookList.contains(eventName = StringUtils.uncapitalize(eventName.substring(lastDot + 1).replace("$", "")))) continue;
                    hookList.add(eventName);
                }
            }
            cachedHooks = hookList;
        }
        return new ArrayList<String>(cachedHooks);
    }

    private boolean isValidForgeEvent(Class<?> eventClass) {
        return Event.class.isAssignableFrom(eventClass) && Modifier.isPublic(eventClass.getModifiers()) && !Modifier.isAbstract(eventClass.getModifiers()) && !EntityEvent.EntityConstructing.class.isAssignableFrom(eventClass) && !WorldEvent.PotentialSpawns.class.isAssignableFrom(eventClass) && !TickEvent.RenderTickEvent.class.isAssignableFrom(eventClass) && !TickEvent.ClientTickEvent.class.isAssignableFrom(eventClass) && !FMLNetworkEvent.ClientCustomPacketEvent.class.isAssignableFrom(eventClass) && !ItemTooltipEvent.class.isAssignableFrom(eventClass) && !ChunkEvent.class.isAssignableFrom(eventClass) && !ChunkWatchEvent.class.isAssignableFrom(eventClass) && !ChunkDataEvent.class.isAssignableFrom(eventClass);
    }

    @Override
    public void requestData() {
        ForgeScriptPacket.Get();
    }

    @Override
    public void sendSavePacket(int index, int totalCount, NBTTagCompound nbt) {
        ForgeScriptPacket.Save(index, totalCount, nbt);
    }

    @Override
    public IScriptUnit createJaninoScriptUnit() {
        return new EventJaninoScript(ScriptContext.FORGE);
    }

    @Override
    public boolean isClient() {
        return false;
    }
}

