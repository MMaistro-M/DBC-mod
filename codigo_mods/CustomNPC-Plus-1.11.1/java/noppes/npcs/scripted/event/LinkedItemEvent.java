/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.scripted.event;

import noppes.npcs.api.event.ILinkedItemEvent;
import noppes.npcs.api.item.IItemLinked;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.scripted.event.ItemEvent;

public class LinkedItemEvent
extends ItemEvent
implements ILinkedItemEvent {
    public LinkedItemEvent(IItemLinked item) {
        super(item);
    }

    public static class BuildEvent
    extends ItemEvent
    implements ILinkedItemEvent.BuildEvent {
        public BuildEvent(IItemLinked item) {
            super(item);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.LINKED_ITEM_BUILD.function;
        }
    }

    public static class VersionChangeEvent
    extends ItemEvent
    implements ILinkedItemEvent.VersionChangeEvent {
        public final int version;
        public final int prevVersion;

        public VersionChangeEvent(IItemLinked item, int version, int prevVersion) {
            super(item);
            this.version = version;
            this.prevVersion = prevVersion;
        }

        @Override
        public int getVersion() {
            return this.version;
        }

        @Override
        public int getPreviousVersion() {
            return this.prevVersion;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.LINKED_ITEM_VERSION.function;
        }
    }
}

