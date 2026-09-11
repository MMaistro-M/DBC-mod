/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.event.IPlayerEvent;
import noppes.npcs.api.gui.ICustomGui;
import noppes.npcs.api.item.IItemStack;

public interface ICustomGuiEvent
extends IPlayerEvent {
    public ICustomGui getGui();

    public int getId();

    @Cancelable
    public static interface SlotClickEvent
    extends ICustomGuiEvent {
        public IItemStack getStack();

        public int getDragType();
    }

    public static interface SlotEvent
    extends ICustomGuiEvent {
        public IItemStack getStack();
    }

    public static interface ScrollEvent
    extends ICustomGuiEvent {
        public String[] getSelection();

        public boolean doubleClick();

        public int getScrollIndex();
    }

    public static interface CloseEvent
    extends ICustomGuiEvent {
    }

    public static interface UnfocusedEvent
    extends ICustomGuiEvent {
    }

    public static interface ButtonEvent
    extends ICustomGuiEvent {
    }
}

