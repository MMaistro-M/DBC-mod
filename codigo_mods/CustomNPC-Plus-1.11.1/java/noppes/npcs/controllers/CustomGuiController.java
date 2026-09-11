/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.world.World
 */
package noppes.npcs.controllers;

import cpw.mods.fml.common.eventhandler.Event;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import kamkeel.npcs.network.packets.data.script.ScriptOverlayDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import noppes.npcs.CustomNpcs;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.containers.ContainerCustomGui;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.data.PlayerDataScript;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.event.player.CustomGuiEvent;
import noppes.npcs.scripted.gui.ScriptGui;
import noppes.npcs.scripted.overlay.ScriptOverlay;

public class CustomGuiController {
    public static void openGui(IPlayer player, ScriptGui gui) {
        EntityPlayerMP entityPlayerMP = (EntityPlayerMP)player.getMCEntity();
        entityPlayerMP.openGui((Object)CustomNpcs.instance, EnumGuiType.CustomGui.ordinal(), (World)player.getWorld().getMCWorld(), gui.getSlots().size(), 0, 0);
        ((ContainerCustomGui)((EntityPlayerMP)player.getMCEntity()).field_71070_bA).setGui(gui, (EntityPlayer)player.getMCEntity());
        GuiDataPacket.sendGuiData((EntityPlayerMP)player.getMCEntity(), gui.toNBT());
    }

    public static boolean updateGui(IPlayer player, ScriptGui gui) {
        if (((EntityPlayerMP)player.getMCEntity()).field_71070_bA instanceof ContainerCustomGui) {
            ((ContainerCustomGui)((EntityPlayerMP)player.getMCEntity()).field_71070_bA).setGui(gui, (EntityPlayer)player.getMCEntity());
            GuiDataPacket.sendGuiData((EntityPlayerMP)player.getMCEntity(), gui.toNBT());
            return true;
        }
        return false;
    }

    public static void openOverlay(IPlayer player, ScriptOverlay gui) {
        PacketHandler.Instance.sendToPlayer(new ScriptOverlayDataPacket(gui.toNBT()), (EntityPlayerMP)player.getMCEntity());
    }

    public static boolean updateOverlay(IPlayer player, ScriptOverlay gui) {
        PacketHandler.Instance.sendToPlayer(new ScriptOverlayDataPacket(gui.toNBT()), (EntityPlayerMP)player.getMCEntity());
        return true;
    }

    static boolean checkGui(CustomGuiEvent event) {
        EntityPlayer player = (EntityPlayer)event.player.getMCEntity();
        if (!(player.field_71070_bA instanceof ContainerCustomGui)) {
            return false;
        }
        return ((ContainerCustomGui)player.field_71070_bA).customGui.getID() == event.gui.getID();
    }

    public static IItemStack[] getSlotContents(EntityPlayer player) {
        IItemStack[] slotContents = new IItemStack[]{};
        if (player.field_71070_bA instanceof ContainerCustomGui) {
            ContainerCustomGui container = (ContainerCustomGui)player.field_71070_bA;
            slotContents = new IItemStack[container.guiInventory.func_70302_i_()];
            for (int i = 0; i < container.guiInventory.func_70302_i_(); ++i) {
                slotContents[i] = NpcAPI.Instance().getIItemStack(container.guiInventory.func_70301_a(i));
            }
        }
        return slotContents;
    }

    public static void onButton(CustomGuiEvent.ButtonEvent event) {
        EntityPlayer player = (EntityPlayer)event.player.getMCEntity();
        if (player != null) {
            ScriptGui gui = CustomGuiController.getOpenGui(player);
            if (CustomGuiController.checkGui(event) && gui != null) {
                gui.getScriptHandler(player).callScript(EnumScriptType.CUSTOM_GUI_BUTTON, (Event)event);
            }
        }
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onSlotChange(CustomGuiEvent.SlotEvent event) {
        EntityPlayer player = (EntityPlayer)event.player.getMCEntity();
        if (player != null) {
            ScriptGui gui = CustomGuiController.getOpenGui(player);
            if (CustomGuiController.checkGui(event) && gui != null) {
                gui.getScriptHandler(player).callScript(EnumScriptType.CUSTOM_GUI_SLOT, (Event)event);
            }
        }
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static boolean onSlotClick(CustomGuiEvent.SlotClickEvent event) {
        EntityPlayer player = (EntityPlayer)event.player.getMCEntity();
        if (player != null) {
            ScriptGui gui = CustomGuiController.getOpenGui(player);
            if (CustomGuiController.checkGui(event) && gui != null) {
                gui.getScriptHandler(player).callScript(EnumScriptType.CUSTOM_GUI_SLOT_CLICKED, (Event)event);
            }
        }
        return NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onCustomGuiUnfocused(CustomGuiEvent.UnfocusedEvent event) {
        EntityPlayer player = (EntityPlayer)event.player.getMCEntity();
        if (player != null) {
            ScriptGui gui = CustomGuiController.getOpenGui(player);
            if (CustomGuiController.checkGui(event) && gui != null) {
                gui.getScriptHandler(player).callScript(EnumScriptType.CUSTOM_GUI_TEXTFIELD, (Event)event);
            }
        }
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onScrollClick(CustomGuiEvent.ScrollEvent event) {
        EntityPlayer player = (EntityPlayer)event.player.getMCEntity();
        if (player != null) {
            ScriptGui gui = CustomGuiController.getOpenGui(player);
            if (CustomGuiController.checkGui(event) && gui != null) {
                gui.getScriptHandler(player).callScript(EnumScriptType.CUSTOM_GUI_SCROLL, (Event)event);
            }
        }
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static void onClose(CustomGuiEvent.CloseEvent event) {
        EntityPlayer player = (EntityPlayer)event.player.getMCEntity();
        if (player != null) {
            PlayerDataScript handler = ScriptController.Instance.getPlayerScripts(player);
            handler.callScript(EnumScriptType.CUSTOM_GUI_CLOSED, (Event)event);
        }
        NpcAPI.EVENT_BUS.post((Event)event);
    }

    public static ScriptGui getOpenGui(EntityPlayer player) {
        return player.field_71070_bA instanceof ContainerCustomGui ? ((ContainerCustomGui)player.field_71070_bA).customGui : null;
    }

    public static String[] readScrollSelection(ByteBuf buffer) {
        try {
            NBTTagList list = ByteBufUtils.readNBT(buffer).func_150295_c("selection", 8);
            String[] selection = new String[list.func_74745_c()];
            for (int i = 0; i < list.func_74745_c(); ++i) {
                selection[i] = list.func_150307_f(i);
            }
            return selection;
        }
        catch (IOException var4) {
            var4.printStackTrace();
            return null;
        }
    }
}

