/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import java.util.List;
import noppes.npcs.api.handler.IHookDefinition;

public interface IScriptHookHandler {
    public void registerHookDefinition(String var1, IHookDefinition var2);

    public IHookDefinition getHookDefinition(String var1, String var2);

    public List<IHookDefinition> getAllHookDefinitions(String var1);

    public List<String> getAllHooks(String var1);

    public boolean hasHook(String var1, String var2);

    public int getHookRevision();

    public String[] getContexts();
}

