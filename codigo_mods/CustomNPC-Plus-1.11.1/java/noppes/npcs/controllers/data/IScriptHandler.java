/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.Event
 */
package noppes.npcs.controllers.data;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.constants.ScriptContext;
import noppes.npcs.controllers.ScriptHookController;
import noppes.npcs.controllers.data.IScriptUnit;

public interface IScriptHandler {
    default public void callScript(EnumScriptType type, Event event) {
        if (type != null) {
            this.callScript(type.function, event);
        }
    }

    public void callScript(String var1, Event var2);

    default public Object callFunction(String hookName, Object ... args) {
        if (hookName == null || hookName.isEmpty()) {
            return null;
        }
        if (!this.getEnabled()) {
            return null;
        }
        List<IScriptUnit> scripts = this.getScripts();
        if (scripts == null || scripts.isEmpty()) {
            return null;
        }
        for (IScriptUnit script : scripts) {
            Object result;
            if (script == null || script.hasErrored() || !script.hasCode() || (result = script.callFunction(hookName, args)) == null) continue;
            return result;
        }
        return null;
    }

    default public <S> S callFunction(String hookName, Class<S> returnType, Object ... args) {
        Object result = this.callFunction(hookName, args);
        if (result == null || returnType == null) {
            return null;
        }
        if (returnType.isInstance(result)) {
            return returnType.cast(result);
        }
        return null;
    }

    default public boolean isClient() {
        return FMLCommonHandler.instance().getEffectiveSide().isClient();
    }

    public boolean getEnabled();

    public void setEnabled(boolean var1);

    public String getLanguage();

    public void setLanguage(String var1);

    public void setScripts(List<IScriptUnit> var1);

    public List<IScriptUnit> getScripts();

    default public String noticeString() {
        return "";
    }

    default public Map<Long, String> getConsoleText() {
        TreeMap<Long, String> map = new TreeMap<Long, String>();
        int tab = 0;
        for (IScriptUnit script : this.getScripts()) {
            ++tab;
            for (Map.Entry<Long, String> entry : script.getConsole().entrySet()) {
                map.put(entry.getKey(), " tab " + tab + ":\n" + entry.getValue());
            }
        }
        return map;
    }

    default public void clearConsole() {
        for (IScriptUnit script : this.getScripts()) {
            script.clearConsole();
        }
    }

    default public IScriptUnit createJaninoScriptUnit() {
        return null;
    }

    default public boolean supportsJanino() {
        return this.createJaninoScriptUnit() != null;
    }

    default public ScriptContext getContext() {
        return ScriptContext.GLOBAL;
    }

    default public String getHookContext() {
        ScriptContext ctx = this.getContext();
        return ctx != null ? ctx.hookContext : "";
    }

    default public List<String> getHooks() {
        String context = this.getHookContext();
        if (context == null || context.isEmpty()) {
            return Collections.emptyList();
        }
        return ScriptHookController.Instance.getAllHooks(context);
    }

    default public boolean isSingleContainer() {
        return false;
    }

    default public IScriptUnit getSingleScript() {
        List<IScriptUnit> scripts = this.getScripts();
        return scripts != null && !scripts.isEmpty() ? scripts.get(0) : null;
    }

    default public void addScriptUnit(IScriptUnit unit) {
        if (unit != null) {
            this.getScripts().add(unit);
        }
    }

    default public void replaceScriptUnit(int index, IScriptUnit unit) {
        List<IScriptUnit> scripts = this.getScripts();
        if (index < 0 || index >= scripts.size()) {
            scripts.add(unit);
        } else {
            scripts.set(index, unit);
        }
    }

    default public void removeScriptUnit(int index) {
        List<IScriptUnit> scripts = this.getScripts();
        if (index >= 0 && index < scripts.size()) {
            scripts.remove(index);
        }
    }
}

