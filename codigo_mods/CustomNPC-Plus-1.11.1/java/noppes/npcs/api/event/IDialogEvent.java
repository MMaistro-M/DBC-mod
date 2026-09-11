/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.event.IPlayerEvent;
import noppes.npcs.api.handler.data.IDialog;

public interface IDialogEvent
extends IPlayerEvent {
    public IDialog getDialog();

    public int getDialogId();

    public int getOptionId();

    public static interface DialogClosed
    extends IDialogEvent {
    }

    @Cancelable
    public static interface DialogOption
    extends IDialogEvent {
    }

    @Cancelable
    public static interface DialogOpen
    extends IDialogEvent {
    }
}

