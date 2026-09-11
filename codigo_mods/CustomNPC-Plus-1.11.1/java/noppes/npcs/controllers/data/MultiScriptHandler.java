/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.ArrayList;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.NBTTags;
import noppes.npcs.controllers.ScriptContainer;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.data.IScriptHandlerPacket;
import noppes.npcs.controllers.data.IScriptUnit;
import noppes.npcs.controllers.data.ScriptHandler;

public abstract class MultiScriptHandler
extends ScriptHandler
implements IScriptHandlerPacket {
    public void readFromNBT(NBTTagCompound compound) {
        this.scripts = compound.func_74764_b("Scripts") ? new ArrayList<IScriptUnit>(NBTTags.GetScriptOld(compound.func_150295_c("Scripts", 10), this)) : new ArrayList<IScriptUnit>(NBTTags.GetScript(compound, this));
        this.scriptLanguage = compound.func_74779_i("ScriptLanguage");
        this.normalizeLanguage();
        this.enabled = compound.func_74767_n("ScriptEnabled");
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74768_a("TotalScripts", this.scripts.size());
        for (int i = 0; i < this.scripts.size(); ++i) {
            compound.func_74782_a("Tab" + i, (NBTBase)((IScriptUnit)this.scripts.get(i)).writeToNBT(new NBTTagCompound()));
        }
        compound.func_74778_a("ScriptLanguage", this.scriptLanguage);
        compound.func_74757_a("ScriptEnabled", this.enabled);
        return compound;
    }

    protected void normalizeLanguage() {
        if (this.scriptLanguage == null || this.scriptLanguage.isEmpty()) {
            this.scriptLanguage = !ScriptController.Instance.languages.isEmpty() ? (String)ScriptController.Instance.languages.keySet().toArray()[0] : "ECMAScript";
        }
    }

    protected boolean needsReInit() {
        return ScriptController.Instance.lastLoaded > this.lastInited;
    }

    protected void reInitScripts() {
        this.lastInited = ScriptController.Instance.lastLoaded;
        for (IScriptUnit script : this.scripts) {
            if (!(script instanceof ScriptContainer)) continue;
            ((ScriptContainer)script).errored = false;
        }
    }

    @Override
    public void callScript(String hookName, Event event) {
        if (!this.canRunScripts()) {
            return;
        }
        if (this.needsReInit()) {
            this.reInitScripts();
        }
        for (IScriptUnit script : this.scripts) {
            if (script == null || script.hasErrored() || !script.hasCode()) continue;
            script.run(hookName, (Object)event);
        }
    }
}

