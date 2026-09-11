/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.scripted.event.player;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.item.ItemStack;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.event.ICustomGuiEvent;
import noppes.npcs.api.gui.ICustomGui;
import noppes.npcs.api.gui.IItemSlot;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.event.player.PlayerEvent;

public class CustomGuiEvent
extends PlayerEvent
implements ICustomGuiEvent {
    public final ICustomGui gui;

    public CustomGuiEvent(IPlayer player, ICustomGui gui) {
        super(player);
        this.gui = gui;
    }

    @Override
    public String getHookName() {
        return EnumScriptType.CUSTOM_GUI_EVENT.function;
    }

    @Override
    public ICustomGui getGui() {
        return this.gui;
    }

    @Override
    public int getId() {
        return -1;
    }

    @Cancelable
    public static class SlotClickEvent
    extends CustomGuiEvent
    implements ICustomGuiEvent.SlotClickEvent {
        public final int slotId;
        public final IItemStack stack;
        public final int dragType;
        public final IItemSlot slot;
        public final int clickType;

        public SlotClickEvent(IPlayer player, ICustomGui gui, int slotId, IItemSlot slot, IItemStack stack, int dragType, int clickType) {
            super(player, gui);
            this.slotId = slotId;
            this.stack = stack;
            this.dragType = dragType;
            this.clickType = clickType;
            this.slot = slot;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.CUSTOM_GUI_SLOT_CLICKED.function;
        }

        @Override
        public int getId() {
            return this.slotId;
        }

        @Override
        public IItemStack getStack() {
            return this.stack;
        }

        @Override
        public int getDragType() {
            return this.dragType;
        }
    }

    public static class SlotEvent
    extends CustomGuiEvent
    implements ICustomGuiEvent.SlotEvent {
        public final int slotId;
        public final IItemStack stack;
        public final IItemSlot slot;

        public SlotEvent(IPlayer player, ICustomGui gui, int slotId, ItemStack stack, IItemSlot slot) {
            super(player, gui);
            this.slotId = slotId;
            this.stack = NpcAPI.Instance().getIItemStack(stack);
            this.slot = slot;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.CUSTOM_GUI_SLOT.function;
        }

        @Override
        public int getId() {
            return this.slotId;
        }

        @Override
        public IItemStack getStack() {
            return this.stack;
        }
    }

    public static class ScrollEvent
    extends CustomGuiEvent
    implements ICustomGuiEvent.ScrollEvent {
        public final int scrollId;
        public final String[] selection;
        public final boolean doubleClick;
        public final int scrollIndex;

        public ScrollEvent(IPlayer player, ICustomGui gui, int scrollId, int scrollIndex, String[] selection, boolean doubleClick) {
            super(player, gui);
            this.scrollId = scrollId;
            this.selection = selection;
            this.doubleClick = doubleClick;
            this.scrollIndex = scrollIndex;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.CUSTOM_GUI_SCROLL.function;
        }

        @Override
        public int getId() {
            return this.scrollId;
        }

        @Override
        public String[] getSelection() {
            return this.selection;
        }

        @Override
        public boolean doubleClick() {
            return this.doubleClick;
        }

        @Override
        public int getScrollIndex() {
            return this.scrollIndex;
        }
    }

    public static class CloseEvent
    extends CustomGuiEvent
    implements ICustomGuiEvent.CloseEvent {
        public CloseEvent(IPlayer player, ICustomGui gui) {
            super(player, gui);
        }

        @Override
        public String getHookName() {
            return EnumScriptType.CUSTOM_GUI_CLOSED.function;
        }
    }

    public static class UnfocusedEvent
    extends CustomGuiEvent
    implements ICustomGuiEvent.UnfocusedEvent {
        public final int textfieldId;

        public UnfocusedEvent(IPlayer player, ICustomGui gui, int textfieldId) {
            super(player, gui);
            this.textfieldId = textfieldId;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.CUSTOM_GUI_TEXTFIELD.function;
        }

        @Override
        public int getId() {
            return this.textfieldId;
        }
    }

    public static class ButtonEvent
    extends CustomGuiEvent
    implements ICustomGuiEvent.ButtonEvent {
        public final int buttonId;

        public ButtonEvent(IPlayer player, ICustomGui gui, int buttonId) {
            super(player, gui);
            this.buttonId = buttonId;
        }

        @Override
        public String getHookName() {
            return EnumScriptType.CUSTOM_GUI_BUTTON.function;
        }

        @Override
        public int getId() {
            return this.buttonId;
        }
    }
}

