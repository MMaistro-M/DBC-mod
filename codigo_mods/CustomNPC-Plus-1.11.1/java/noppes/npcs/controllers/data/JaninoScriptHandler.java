/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.controllers.data.IScriptUnit;
import noppes.npcs.controllers.data.ScriptHandler;
import noppes.npcs.janino.JaninoScript;

public class JaninoScriptHandler<S extends JaninoScript<?>>
extends ScriptHandler {
    protected S script;
    private final Supplier<S> factory;
    private final Class<S> scriptClass;

    public JaninoScriptHandler(Supplier<S> factory, Class<S> scriptClass) {
        this.factory = factory;
        this.scriptClass = scriptClass;
        this.scriptLanguage = "Java";
    }

    public boolean hasScript() {
        return this.script != null;
    }

    public S getScript() {
        return this.script;
    }

    public JaninoScript createScript() {
        if (this.script == null) {
            this.script = (JaninoScript)this.factory.get();
        }
        return this.script;
    }

    public void deleteScript() {
        this.script = null;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.script = JaninoScript.readFromNBT(compound, this.script, this.factory);
    }

    public void writeToNBT(NBTTagCompound compound) {
        JaninoScript.writeToNBT(compound, this.script);
    }

    @Override
    public void callScript(String hookName, Event event) {
    }

    @Override
    public void callScript(EnumScriptType type, Event event) {
    }

    @Override
    public void setScripts(List<IScriptUnit> list) {
        if (list == null || list.isEmpty() || list.get(0) == null) {
            this.deleteScript();
            return;
        }
        IScriptUnit unit = list.get(0);
        if (this.scriptClass.isInstance(unit)) {
            this.script = (JaninoScript)this.scriptClass.cast(unit);
            return;
        }
        JaninoScript target = (JaninoScript)this.scriptClass.cast(this.createScript());
        target.setScript(unit.getScript());
        target.setExternalScripts(unit.getExternalScripts());
    }

    @Override
    public List<IScriptUnit> getScripts() {
        if (this.script == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(this.script);
    }

    @Override
    public void addScriptUnit(IScriptUnit unit) {
        this.setScripts(Collections.singletonList(unit));
    }

    @Override
    public void replaceScriptUnit(int index, IScriptUnit unit) {
        this.setScripts(Collections.singletonList(unit));
    }

    @Override
    public void removeScriptUnit(int index) {
        this.deleteScript();
    }

    @Override
    public List<String> getHooks() {
        if (this.script != null) {
            return ((JaninoScript)this.script).getHookList();
        }
        return Collections.emptyList();
    }

    @Override
    public IScriptUnit createJaninoScriptUnit() {
        return (IScriptUnit)this.factory.get();
    }

    @Override
    public boolean supportsJanino() {
        return true;
    }
}

