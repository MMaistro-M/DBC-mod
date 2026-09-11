/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import kamkeel.npcs.network.packets.request.script.PlayerScriptPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import noppes.npcs.CustomNpcs;
import noppes.npcs.EventHooks;
import noppes.npcs.NBTTags;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.config.ConfigScript;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.constants.ScriptContext;
import noppes.npcs.controllers.ScriptContainer;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.data.IScriptUnit;
import noppes.npcs.controllers.data.MultiScriptHandler;
import noppes.npcs.janino.EventJaninoScript;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.event.player.PlayerEvent;
import noppes.npcs.util.ScriptToStringHelper;

public class PlayerDataScript
extends MultiScriptHandler {
    private EntityPlayer player;
    private IPlayer playerAPI;
    private long lastPlayerUpdate = 0L;
    private static Map<Long, String> staticConsole = new TreeMap<Long, String>();
    private static List<Integer> errored = new ArrayList<Integer>();

    public PlayerDataScript(EntityPlayer player) {
        if (player != null) {
            this.player = player;
        }
    }

    @Override
    public void clear() {
        super.clear();
        staticConsole = new TreeMap<Long, String>();
        errored = new ArrayList<Integer>();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        staticConsole = NBTTags.GetLongStringMap(compound.func_150295_c("ScriptConsole", 10));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.func_74782_a("ScriptConsole", (NBTBase)NBTTags.NBTLongStringMap(staticConsole));
        return compound;
    }

    public boolean isEnabled() {
        return CustomNpcs.proxy.isGlobalPlayerScripts() && ScriptController.Instance.playerScripts.enabled && ScriptController.HasStart && (this.player == null || !this.player.field_70170_p.field_72995_K);
    }

    @Override
    protected boolean canRunScripts() {
        return this.isEnabled();
    }

    @Override
    public ScriptContext getContext() {
        return ScriptContext.PLAYER;
    }

    @Override
    public void requestData() {
        PlayerScriptPacket.Get();
    }

    @Override
    public void sendSavePacket(int index, int totalCount, NBTTagCompound nbt) {
        PlayerScriptPacket.Save(index, totalCount, nbt);
    }

    @Override
    public void callScript(String hookName, Event event) {
        if (!this.isEnabled()) {
            return;
        }
        if (ConfigScript.IndividualPlayerScripts) {
            if (ScriptController.Instance.lastLoaded > this.lastInited || ScriptController.Instance.lastPlayerUpdate > this.lastPlayerUpdate) {
                this.lastInited = ScriptController.Instance.lastLoaded;
                errored.clear();
                if (this.player != null) {
                    this.scripts.clear();
                    for (IScriptUnit script : ScriptController.Instance.playerScripts.scripts) {
                        NBTTagCompound nbt = script.writeToNBT(new NBTTagCompound());
                        IScriptUnit cloned = IScriptUnit.createFromNBT(nbt, this);
                        this.scripts.add(cloned);
                    }
                }
                this.lastPlayerUpdate = ScriptController.Instance.lastPlayerUpdate;
                if (!Objects.equals(hookName, EnumScriptType.INIT.function) && event instanceof PlayerEvent) {
                    PlayerEvent playerEvent = (PlayerEvent)event;
                    EventHooks.onPlayerInit(this, playerEvent.player);
                }
            }
            for (int i = 0; i < this.scripts.size(); ++i) {
                IScriptUnit script;
                script = (IScriptUnit)this.scripts.get(i);
                if (errored.contains(i)) continue;
                script.run(hookName, (Object)event);
                if (script.hasErrored()) {
                    errored.add(i);
                }
                for (Map.Entry<Long, String> entry : script.getConsole().entrySet()) {
                    if (staticConsole.containsKey(entry.getKey())) continue;
                    staticConsole.put(entry.getKey(), " tab " + (i + 1) + ":\n" + entry.getValue());
                }
                script.clearConsole();
            }
        } else {
            if (ScriptController.Instance.lastLoaded > this.lastInited || ScriptController.Instance.lastPlayerUpdate > this.lastPlayerUpdate) {
                this.lastInited = ScriptController.Instance.lastLoaded;
                this.lastPlayerUpdate = ScriptController.Instance.lastPlayerUpdate;
                for (IScriptUnit script : this.scripts) {
                    if (!(script instanceof ScriptContainer)) continue;
                    ((ScriptContainer)script).errored = false;
                }
                if (!Objects.equals(hookName, EnumScriptType.INIT.function) && event instanceof PlayerEvent) {
                    PlayerEvent playerEvent = (PlayerEvent)event;
                    EventHooks.onPlayerInit(this, playerEvent.player);
                }
            }
            for (IScriptUnit script : this.scripts) {
                if (script == null || script.hasErrored() || !script.hasCode()) continue;
                script.run(hookName, (Object)event);
            }
        }
    }

    @Override
    public boolean isClient() {
        return this.player != null && this.player.func_70613_aW();
    }

    @Override
    public boolean getEnabled() {
        return ScriptController.Instance.playerScripts.enabled;
    }

    @Override
    public void setEnabled(boolean bo) {
        ScriptController.Instance.playerScripts.enabled = bo;
        this.enabled = bo;
    }

    @Override
    public String getLanguage() {
        return ScriptController.Instance.playerScripts.scriptLanguage;
    }

    @Override
    public void setLanguage(String lang) {
        ScriptController.Instance.playerScripts.scriptLanguage = lang;
        this.scriptLanguage = lang;
    }

    @Override
    public String noticeString() {
        if (this.player == null) {
            return "Global script";
        }
        BlockPos pos = new BlockPos((Entity)this.player);
        return ScriptToStringHelper.toStringHelper(this.player).add("x", pos.getX()).add("y", pos.getY()).add("z", pos.getZ()).toString();
    }

    public IPlayer getPlayer() {
        if (this.playerAPI == null) {
            this.playerAPI = (IPlayer)NpcAPI.Instance().getIEntity((Entity)this.player);
        }
        return this.playerAPI;
    }

    @Override
    public Map<Long, String> getConsoleText() {
        if (ConfigScript.IndividualPlayerScripts) {
            return staticConsole;
        }
        return super.getConsoleText();
    }

    @Override
    public void clearConsole() {
        if (ConfigScript.IndividualPlayerScripts) {
            staticConsole.clear();
            return;
        }
        super.clearConsole();
    }

    @Override
    public IScriptUnit createJaninoScriptUnit() {
        return new EventJaninoScript(ScriptContext.PLAYER);
    }
}

