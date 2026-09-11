/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util;

import java.util.HashMap;
import java.util.UUID;
import noppes.npcs.controllers.data.Tag;
import noppes.npcs.controllers.data.TagMap;

public interface IClonerGui {
    public int getShowingClones();

    public HashMap<UUID, Tag> getTags();

    public TagMap getTagMap();
}

