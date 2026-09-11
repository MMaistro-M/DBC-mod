/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Util
 *  net.minecraft.util.Util$EnumOS
 *  org.lwjgl.Sys
 */
package noppes.npcs.client;

import io.netty.buffer.ByteBuf;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.GuiRequestPacket;
import kamkeel.npcs.network.packets.request.IsGuiOpenInform;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import noppes.npcs.CustomNpcs;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.gui.player.GuiDialogInteract;
import noppes.npcs.client.gui.player.GuiQuestCompletion;
import noppes.npcs.client.gui.player.modern.GuiModernDialogInteract;
import noppes.npcs.client.gui.player.modern.GuiModernQuestDialog;
import noppes.npcs.client.gui.util.GuiContainerNPCInterface;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.IPlayerDataInfo;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.config.ConfigExperimental;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.data.Dialog;
import noppes.npcs.controllers.data.MagicData;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.controllers.data.SkinOverlay;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.Sys;

public class NoppesUtil {
    private static EntityNPCInterface lastNpc;
    private static HashMap<String, Integer> data;

    public static void requestOpenGUI(EnumGuiType gui) {
        NoppesUtil.requestOpenGUI(gui, 0, 0, 0);
    }

    public static void requestOpenGUI(EnumGuiType gui, int i, int j, int k) {
        PacketClient.sendClient(new GuiRequestPacket(gui.ordinal(), i, j, k));
    }

    public static void updateSkinOverlayData(EntityPlayer player, NBTTagCompound compound) {
        HashMap<Integer, SkinOverlay> skinOverlays = new HashMap<Integer, SkinOverlay>();
        HashMap<Object, Object> oldOverlays = new HashMap();
        NBTTagList skinOverlayList = compound.func_150295_c("SkinOverlayData", 10);
        if (ClientCacheHandler.skinOverlays.containsKey(player.func_110124_au())) {
            oldOverlays = ClientCacheHandler.skinOverlays.get(player.func_110124_au());
        }
        for (int i = 0; i < skinOverlayList.func_74745_c(); ++i) {
            int tagID = skinOverlayList.func_150305_b(i).func_74762_e("SkinOverlayID");
            SkinOverlay overlay = (SkinOverlay)SkinOverlay.overlayFromNBT(skinOverlayList.func_150305_b(i));
            if (oldOverlays.containsKey(tagID)) {
                overlay.ticks = ((SkinOverlay)oldOverlays.get((Object)Integer.valueOf((int)tagID))).ticks;
            }
            skinOverlays.put(tagID, overlay);
        }
        ClientCacheHandler.skinOverlays.put(player.func_110124_au(), skinOverlays);
    }

    public static void clickSound() {
        Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a((ResourceLocation)new ResourceLocation("gui.button.press"), (float)1.0f));
    }

    public static EntityNPCInterface getLastNpc() {
        return lastNpc;
    }

    public static void setLastNpc(EntityNPCInterface npc) {
        lastNpc = npc;
    }

    public static void openGUI(EntityPlayer player, Object guiscreen) {
        CustomNpcs.proxy.openGui(player, guiscreen);
    }

    public static void openFolder(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory()) {
            return;
        }
        String s = dir.getAbsolutePath();
        if (Util.func_110647_a() == Util.EnumOS.OSX) {
            try {
                Runtime.getRuntime().exec(new String[]{"/usr/bin/open", s});
                return;
            }
            catch (IOException iOException) {}
        } else if (Util.func_110647_a() == Util.EnumOS.WINDOWS) {
            String s1 = String.format("cmd.exe /C start \"Open file\" \"%s\"", s);
            try {
                Runtime.getRuntime().exec(s1);
                return;
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        boolean flag = false;
        try {
            Class<?> oclass = Class.forName("java.awt.Desktop");
            Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
            oclass.getMethod("browse", URI.class).invoke(object, dir.toURI());
        }
        catch (Throwable throwable) {
            flag = true;
        }
        if (flag) {
            Sys.openURL((String)("file://" + s));
        }
    }

    public static void setScrollList(ByteBuf buffer) {
        GuiScreen gui = Minecraft.func_71410_x().field_71462_r;
        if (gui instanceof GuiNPCInterface && ((GuiNPCInterface)gui).hasSubGui()) {
            gui = ((GuiNPCInterface)gui).getSubGui();
        }
        if (gui == null || !(gui instanceof IScrollData)) {
            return;
        }
        Vector<String> data = new Vector<String>();
        EnumScrollData dataType = EnumScrollData.values()[buffer.readInt()];
        try {
            int size = buffer.readInt();
            for (int i = 0; i < size; ++i) {
                data.add(ByteBufUtils.readString(buffer));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        ((IScrollData)gui).setData(data, null, dataType);
    }

    public static void setScrollData(ByteBuf buffer) {
        GuiScreen gui = Minecraft.func_71410_x().field_71462_r;
        if (gui == null) {
            return;
        }
        EnumScrollData dataType = EnumScrollData.values()[buffer.readInt()];
        try {
            int size = buffer.readInt();
            for (int i = 0; i < size; ++i) {
                int id = buffer.readInt();
                String name = ByteBufUtils.readString(buffer);
                data.put(name, id);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        if (gui instanceof GuiNPCInterface && ((GuiNPCInterface)gui).hasSubGui()) {
            gui = ((GuiNPCInterface)gui).getSubGui();
        }
        if (gui instanceof GuiContainerNPCInterface && ((GuiContainerNPCInterface)gui).hasSubGui()) {
            gui = ((GuiContainerNPCInterface)gui).getSubGui();
        }
        if (gui instanceof IScrollData) {
            ((IScrollData)gui).setData(new Vector<String>(data.keySet()), data, dataType);
        }
        data = new HashMap();
    }

    public static void guiQuestCompletion(EntityPlayer player, NBTTagCompound read) {
        Quest quest = new Quest();
        quest.readNBT(read);
        if (!quest.completeText.isEmpty()) {
            NoppesUtil.openGUI(player, new GuiQuestCompletion(quest));
        }
    }

    public static void openDialog(NBTTagCompound compound, EntityNPCInterface npc, EntityPlayer player) {
        Dialog dialog = new Dialog();
        dialog.readNBT(compound);
        GuiScreen gui = Minecraft.func_71410_x().field_71462_r;
        if (!(gui instanceof GuiDialogInteract)) {
            if (ConfigExperimental.ModernGuiSystem) {
                if (dialog.hasQuest()) {
                    CustomNpcs.proxy.openGui(player, new GuiModernQuestDialog(npc, dialog.getQuest(), dialog, -2));
                } else {
                    CustomNpcs.proxy.openGui(player, new GuiModernDialogInteract(npc, dialog));
                }
            } else {
                CustomNpcs.proxy.openGui(player, new GuiDialogInteract(npc, dialog));
            }
        } else {
            GuiDialogInteract dia = (GuiDialogInteract)gui;
            dia.appendDialog(dialog);
        }
    }

    public static void saveRedstoneBlock(EntityPlayer player, NBTTagCompound compound) {
        int x = compound.func_74762_e("x");
        int y = compound.func_74762_e("y");
        int z = compound.func_74762_e("z");
        TileEntity tile = player.field_70170_p.func_147438_o(x, y, z);
        tile.func_145839_a(compound);
        CustomNpcs.proxy.openGui(x, y, z, EnumGuiType.RedstoneBlock, player);
    }

    public static void saveWayPointBlock(EntityPlayer player, NBTTagCompound compound) {
        int x = compound.func_74762_e("x");
        int y = compound.func_74762_e("y");
        int z = compound.func_74762_e("z");
        TileEntity tile = player.field_70170_p.func_147438_o(x, y, z);
        tile.func_145839_a(compound);
        CustomNpcs.proxy.openGui(x, y, z, EnumGuiType.Waypoint, player);
    }

    public static void isGUIOpen(boolean isGUIOpen) {
        PacketClient.sendClient(new IsGuiOpenInform(isGUIOpen));
    }

    public static void handlePlayerData(ByteBuf data, EntityPlayer player) throws IOException {
        String playerName = ByteBufUtils.readString(data);
        Map<String, Integer> questCategories = NoppesUtil.readMap(data);
        Map<String, Integer> questActive = NoppesUtil.readMap(data);
        Map<String, Integer> questFinished = NoppesUtil.readMap(data);
        Map<String, Integer> dialogCategories = NoppesUtil.readMap(data);
        Map<String, Integer> dialogRead = NoppesUtil.readMap(data);
        Map<String, Integer> transportCategories = NoppesUtil.readMap(data);
        Map<String, Integer> transportLocations = NoppesUtil.readMap(data);
        Map<String, Integer> bankData = NoppesUtil.readMap(data);
        Map<String, Integer> factionData = NoppesUtil.readMap(data);
        MagicData magicData = new MagicData();
        magicData.readToNBT(ByteBufUtils.readNBT(data));
        GuiScreen gui = Minecraft.func_71410_x().field_71462_r;
        if (gui == null) {
            return;
        }
        if (gui instanceof GuiNPCInterface && ((GuiNPCInterface)gui).hasSubGui()) {
            gui = ((GuiNPCInterface)gui).getSubGui();
        }
        if (gui instanceof GuiContainerNPCInterface && ((GuiContainerNPCInterface)gui).hasSubGui()) {
            gui = ((GuiContainerNPCInterface)gui).getSubGui();
        }
        if (gui instanceof IPlayerDataInfo) {
            IPlayerDataInfo info = (IPlayerDataInfo)gui;
            info.setQuestData(questCategories, questActive, questFinished);
            info.setDialogData(dialogCategories, dialogRead);
            info.setTransportData(transportCategories, transportLocations);
            info.setBankData(bankData);
            info.setFactionData(factionData);
            info.setMagicData(magicData);
        }
    }

    private static Map<String, Integer> readMap(ByteBuf data) {
        int size = data.readInt();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < size; ++i) {
            String key = ByteBufUtils.readString(data);
            int value = data.readInt();
            map.put(key, value);
        }
        return map;
    }

    static {
        data = new HashMap();
    }
}

