/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.scripted.event.player;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.event.IDialogEvent;
import noppes.npcs.api.handler.data.IDialog;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.scripted.event.player.PlayerEvent;

public class DialogEvent
extends PlayerEvent
implements IDialogEvent {
    public final IDialog dialog;
    public final int dialogId;
    public final int optionId;

    public DialogEvent(IPlayer player, int id, int optionId, IDialog dialog) {
        super(player);
        this.dialog = dialog;
        this.dialogId = id;
        this.optionId = optionId;
    }

    @Override
    public IDialog getDialog() {
        return this.dialog;
    }

    @Override
    public int getDialogId() {
        return this.dialogId;
    }

    @Override
    public int getOptionId() {
        return this.optionId;
    }

    @Override
    public String getHookName() {
        return EnumScriptType.DIALOG_EVENT.function;
    }

    public static class DialogClosed
    extends DialogEvent
    implements IDialogEvent.DialogClosed {
        public DialogClosed(IPlayer player, int id, int optionId, IDialog dialog) {
            super(player, id, optionId, dialog);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.DIALOG_CLOSE.function;
        }
    }

    @Cancelable
    public static class DialogOption
    extends DialogEvent
    implements IDialogEvent.DialogOption {
        public DialogOption(IPlayer player, int id, int optionId, IDialog dialog) {
            super(player, id, optionId, dialog);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.DIALOG_OPTION.function;
        }
    }

    @Cancelable
    public static class DialogOpen
    extends DialogEvent
    implements IDialogEvent.DialogOpen {
        public DialogOpen(IPlayer player, int id, int optionId, IDialog dialog) {
            super(player, id, optionId, dialog);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.DIALOG_OPEN.function;
        }
    }
}

