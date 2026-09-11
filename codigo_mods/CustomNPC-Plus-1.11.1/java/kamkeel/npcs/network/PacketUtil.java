/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import kamkeel.npcs.network.enums.EnumItemPacketType;
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomItems;
import noppes.npcs.LogWriter;
import noppes.npcs.NBTTags;
import noppes.npcs.controllers.ScriptContainer;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.data.IScriptHandler;
import noppes.npcs.controllers.data.IScriptUnit;
import noppes.npcs.items.ItemNpcTool;

public class PacketUtil {
    public static boolean verifyItemPacket(String name, EnumItemPacketType type, EntityPlayer player) {
        if (player == null) {
            return false;
        }
        ItemStack item = player.field_71071_by.func_70448_g();
        if (item == null) {
            LogWriter.error(String.format("%s attempted to utilize a %s %s Packet without an item, they could be a hacker", new Object[]{player.func_70005_c_(), type, name}));
            return false;
        }
        switch (type) {
            case WAND: {
                if (item.func_77973_b() == CustomItems.wand) break;
                LogWriter.error(String.format("%s attempted to utilize a %s Packet without a Wand, they could be a hacker", new Object[]{player.func_70005_c_(), type, name}));
                return false;
            }
            case MOUNTER: {
                if (item.func_77973_b() == CustomItems.mount) break;
                LogWriter.error(String.format("%s attempted to utilize a %s Packet without a Mounter, they could be a hacker", new Object[]{player.func_70005_c_(), type, name}));
                return false;
            }
            case CLONER: {
                if (item.func_77973_b() == CustomItems.cloner) break;
                LogWriter.error(String.format("%s attempted to utilize a %s %s Packet without a Cloner, they could be a hacker", new Object[]{player.func_70005_c_(), type, name}));
                return false;
            }
            case TELEPORTER: {
                if (item.func_77973_b() == CustomItems.teleporter) break;
                LogWriter.error(String.format("%s attempted to utilize a %s %s Packet without a Teleporter, they could be a hacker", new Object[]{player.func_70005_c_(), type, name}));
                return false;
            }
            case SCRIPTER: {
                if (item.func_77973_b() == CustomItems.scripter) break;
                LogWriter.error(String.format("%s attempted to utilize a %s %s Packet without a Scripter, they could be a hacker", new Object[]{player.func_70005_c_(), type, name}));
                return false;
            }
            case PATHER: {
                if (item.func_77973_b() == CustomItems.moving) break;
                LogWriter.error(String.format("%s attempted to utilize a %s %s Packet without a Pather, they could be a hacker", new Object[]{player.func_70005_c_(), type, name}));
                return false;
            }
            case BLOCK: {
                if (item.func_77973_b() != Item.func_150898_a((Block)CustomItems.waypoint) && item.func_77973_b() != Item.func_150898_a((Block)CustomItems.border) && item.func_77973_b() != Item.func_150898_a((Block)CustomItems.redstoneBlock)) break;
                LogWriter.error(String.format("%s attempted to utilize a %s %s Packet without a valid Block, they could be a hacker", new Object[]{player.func_70005_c_(), type, name}));
                return false;
            }
            case BRUSH: {
                if (item.func_77973_b() == CustomItems.tool && item.func_77960_j() == 1) break;
                LogWriter.error(String.format("%s attempted to utilize a %s %s Packet without a Pather, they could be a hacker", new Object[]{player.func_70005_c_(), type, name}));
                return false;
            }
            case HAMMER: {
                if (item.func_77973_b() == CustomItems.tool && item.func_77960_j() == 0) break;
                LogWriter.error(String.format("%s attempted to utilize a %s %s Packet without a Hammer, they could be a hacker", new Object[]{player.func_70005_c_(), type, name}));
                return false;
            }
            case MAGIC_BOOK: {
                if (item.func_77973_b() == CustomItems.tool && item.func_77960_j() == 2) break;
                LogWriter.error(String.format("%s attempted to utilize a %s %s Packet without a Magic Book, they could be a hacker", new Object[]{player.func_70005_c_(), type, name}));
                return false;
            }
        }
        return true;
    }

    public static boolean verifyItemPacket(String name, EntityPlayer player, EnumItemPacketType ... types) {
        if (player == null) {
            return false;
        }
        ItemStack item = player.field_71071_by.func_70448_g();
        if (item == null) {
            LogWriter.error(String.format("%s attempted to utilize a %s without an item. Expected one of: %s, they could be a hacker", player.func_70005_c_(), name, PacketUtil.getExpectedItemNames(types)));
            return false;
        }
        for (EnumItemPacketType type : types) {
            if (!PacketUtil.isValidItemForType(item, type)) continue;
            return true;
        }
        LogWriter.error(String.format("%s attempted to utilize a %s without a valid item. Expected one of: %s, they could be a hacker", player.func_70005_c_(), name, PacketUtil.getExpectedItemNames(types)));
        return false;
    }

    private static boolean isValidItemForType(ItemStack item, EnumItemPacketType type) {
        if (item == null || item.func_77973_b() == null) {
            return false;
        }
        switch (type) {
            case WAND: {
                return item.func_77973_b() == CustomItems.wand;
            }
            case MOUNTER: {
                return item.func_77973_b() == CustomItems.mount;
            }
            case CLONER: {
                return item.func_77973_b() == CustomItems.cloner;
            }
            case TELEPORTER: {
                return item.func_77973_b() == CustomItems.teleporter;
            }
            case SCRIPTER: {
                return item.func_77973_b() == CustomItems.scripter;
            }
            case PATHER: {
                return item.func_77973_b() == CustomItems.moving;
            }
            case BLOCK: {
                return item.func_77973_b() != Item.func_150898_a((Block)CustomItems.waypoint) && item.func_77973_b() != Item.func_150898_a((Block)CustomItems.border) && item.func_77973_b() != Item.func_150898_a((Block)CustomItems.redstoneBlock);
            }
            case BRUSH: {
                return item.func_77973_b() instanceof ItemNpcTool && item.func_77960_j() == 1;
            }
            case HAMMER: {
                return item.func_77973_b() instanceof ItemNpcTool && item.func_77960_j() == 0;
            }
            case MAGIC_BOOK: {
                return item.func_77973_b() instanceof ItemNpcTool && item.func_77960_j() == 2;
            }
        }
        return false;
    }

    private static String getExpectedItemNames(EnumItemPacketType ... types) {
        LinkedHashSet<String> expectedNames = new LinkedHashSet<String>();
        for (EnumItemPacketType type : types) {
            expectedNames.add(PacketUtil.getExpectedItemName(type));
        }
        return String.join((CharSequence)", ", expectedNames);
    }

    private static String getExpectedItemName(EnumItemPacketType type) {
        switch (type) {
            case WAND: {
                return "Wand";
            }
            case MOUNTER: {
                return "Mounter";
            }
            case CLONER: {
                return "Cloner";
            }
            case TELEPORTER: {
                return "Teleporter";
            }
            case SCRIPTER: {
                return "Scripter";
            }
            case PATHER: {
                return "Pather";
            }
            case BLOCK: {
                return "valid Block";
            }
            case BRUSH: {
                return "Paintbrush";
            }
            case HAMMER: {
                return "Hammer";
            }
            case MAGIC_BOOK: {
                return "Magic Book";
            }
        }
        return type.toString();
    }

    public static void getScripts(IScriptHandler data, EntityPlayerMP player) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74757_a("ScriptEnabled", data.getEnabled());
        compound.func_74778_a("ScriptLanguage", data.getLanguage());
        compound.func_74782_a("Languages", (NBTBase)ScriptController.Instance.nbtLanguages());
        compound.func_74782_a("ScriptConsole", (NBTBase)NBTTags.NBTLongStringMap(data.getConsoleText()));
        GuiDataPacket.sendGuiData(player, compound);
        List<IScriptUnit> containers = data.getScripts();
        for (int i = 0; i < containers.size(); ++i) {
            IScriptUnit container = containers.get(i);
            NBTTagCompound tabCompound = new NBTTagCompound();
            tabCompound.func_74768_a("Tab", i);
            tabCompound.func_74782_a("Script", (NBTBase)container.writeToNBT(new NBTTagCompound()));
            tabCompound.func_74768_a("TotalScripts", containers.size());
            GuiDataPacket.sendGuiData(player, tabCompound);
        }
        NBTTagCompound loadComplete = new NBTTagCompound();
        loadComplete.func_74768_a("LoadComplete", 1);
        GuiDataPacket.sendGuiData(player, loadComplete);
    }

    public static void saveScripts(IScriptHandler data, ByteBuf buffer) throws IOException {
        int tab = buffer.readInt();
        int totalScripts = buffer.readInt();
        if (totalScripts == 0) {
            data.getScripts().clear();
        }
        if (tab >= 0) {
            if (data.getScripts().size() > totalScripts) {
                data.setScripts(data.getScripts().subList(0, totalScripts));
            } else {
                while (data.getScripts().size() < totalScripts) {
                    data.getScripts().add(new ScriptContainer(data));
                }
            }
            NBTTagCompound tabCompound = ByteBufUtils.readNBT(buffer);
            IScriptUnit script = IScriptUnit.createFromNBT(tabCompound, data);
            data.getScripts().set(tab, script);
        } else {
            NBTTagCompound compound = ByteBufUtils.readNBT(buffer);
            data.setLanguage(compound.func_74779_i("ScriptLanguage"));
            if (!ScriptController.Instance.languages.containsKey(data.getLanguage())) {
                if (!ScriptController.Instance.languages.isEmpty()) {
                    data.setLanguage((String)ScriptController.Instance.languages.keySet().toArray()[0]);
                } else {
                    data.setLanguage("ECMAScript");
                }
            }
            data.setEnabled(compound.func_74767_n("ScriptEnabled"));
        }
    }
}

