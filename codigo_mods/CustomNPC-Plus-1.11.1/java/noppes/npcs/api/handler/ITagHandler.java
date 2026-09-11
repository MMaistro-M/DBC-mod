/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import java.util.List;
import noppes.npcs.api.handler.data.ITag;

public interface ITagHandler {
    public List<ITag> list();

    public ITag delete(int var1);

    public ITag create(String var1, int var2);

    public ITag get(int var1);
}

