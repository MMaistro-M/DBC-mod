/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 */
package noppes.npcs.controllers.data;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.ArrayList;
import java.util.List;
import noppes.npcs.CustomNpcs;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.data.IScriptHandler;
import noppes.npcs.controllers.data.IScriptUnit;

public abstract class ScriptHandler
implements IScriptHandler {
    public List<IScriptUnit> scripts = new ArrayList<IScriptUnit>();
    protected String scriptLanguage = "ECMAScript";
    protected boolean enabled = false;
    protected long lastInited = -1L;

    protected boolean canRunScripts() {
        return this.enabled && ScriptController.HasStart && CustomNpcs.proxy.isScriptingEnabled() && this.scripts != null && !this.scripts.isEmpty();
    }

    @Override
    public void callScript(String hookName, Event event) {
        if (!this.canRunScripts()) {
            return;
        }
        if (ScriptController.Instance.lastLoaded > this.lastInited) {
            this.lastInited = ScriptController.Instance.lastLoaded;
            for (IScriptUnit script : this.scripts) {
                if (script == null) continue;
                script.setErrored(false);
            }
        }
        for (IScriptUnit script : this.scripts) {
            if (script == null || script.hasErrored() || !script.hasCode()) continue;
            script.run(hookName, (Object)event);
        }
    }

    @Override
    public boolean getEnabled() {
        return this.enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String getLanguage() {
        return this.scriptLanguage;
    }

    @Override
    public void setLanguage(String language) {
        this.scriptLanguage = language;
    }

    @Override
    public void setScripts(List<IScriptUnit> list) {
        this.scripts = list;
    }

    @Override
    public List<IScriptUnit> getScripts() {
        return this.scripts;
    }

    public void clear() {
        this.scripts = new ArrayList<IScriptUnit>();
    }

    public void resetLastInited() {
        this.lastInited = -1L;
    }
}

