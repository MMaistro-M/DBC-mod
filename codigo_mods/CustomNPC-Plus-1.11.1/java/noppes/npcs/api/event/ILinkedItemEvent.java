/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.event;

import noppes.npcs.api.event.IItemEvent;

public interface ILinkedItemEvent
extends IItemEvent {

    public static interface BuildEvent
    extends IItemEvent {
    }

    public static interface VersionChangeEvent
    extends IItemEvent {
        public int getVersion();

        public int getPreviousVersion();
    }
}

