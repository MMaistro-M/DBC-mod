/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.List;
import java.util.TreeMap;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.controllers.ScriptContainer;
import noppes.npcs.controllers.data.IScriptHandler;

public interface IScriptUnit {
    public static final String NBT_TYPE_KEY = "ScriptUnitType";
    public static final String TYPE_ECMASCRIPT = "ECMAScript";
    public static final String TYPE_JANINO = "Janino";

    public static IScriptUnit createFromNBT(NBTTagCompound compound, IScriptHandler handler) {
        IScriptUnit unit;
        String type = compound.func_74779_i(NBT_TYPE_KEY);
        if (TYPE_JANINO.equals(type)) {
            unit = handler.createJaninoScriptUnit();
            if (unit == null) {
                unit = new ScriptContainer(handler);
            }
        } else {
            unit = new ScriptContainer(handler);
        }
        unit.readFromNBT(compound);
        return unit;
    }

    public String getScript();

    public void setScript(String var1);

    public List<String> getExternalScripts();

    public void setExternalScripts(List<String> var1);

    public TreeMap<Long, String> getConsole();

    public void clearConsole();

    public void appendConsole(String var1);

    public String getLanguage();

    public void setLanguage(String var1);

    default public boolean isJanino() {
        return "Java".equals(this.getLanguage());
    }

    public String generateHookStub(String var1, Object var2);

    public NBTTagCompound writeToNBT(NBTTagCompound var1);

    public void readFromNBT(NBTTagCompound var1);

    public void ensureCompiled();

    public boolean hasCode();

    public boolean hasErrored();

    public void setErrored(boolean var1);

    public IScriptUnit createInstanceScope(IScriptHandler var1);

    default public boolean isUnknownFunction(String type) {
        return false;
    }

    public void run(EnumScriptType var1, Event var2);

    public void run(String var1, Object var2);

    default public Object callFunction(String hookName, Object ... args) {
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
}

