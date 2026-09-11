/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.undo;

import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.common.undo.PlayerUndoData;
import riskyken.armourersWorkshop.common.undo.UndoData;

public final class UndoManager {
    public static int maxUndos = 50;
    private static HashMap<String, PlayerUndoData> playerUndoData = new HashMap();

    public static void begin(EntityPlayer player) {
        if (!playerUndoData.containsKey(player.func_70005_c_())) {
            playerUndoData.put(player.func_70005_c_(), new PlayerUndoData(player));
        }
        PlayerUndoData playerData = playerUndoData.get(player.func_70005_c_());
        playerData.begin();
    }

    public static void end(EntityPlayer player) {
        if (!playerUndoData.containsKey(player.func_70005_c_())) {
            playerUndoData.put(player.func_70005_c_(), new PlayerUndoData(player));
        }
        PlayerUndoData playerData = playerUndoData.get(player.func_70005_c_());
        playerData.end();
    }

    @Deprecated
    public static void blockPainted(EntityPlayer player, World world, int x, int y, int z, int oldColour, byte oldPaintType, int side) {
        byte[] oldrgb = new byte[]{(byte)(oldColour >> 16 & 0xFF), (byte)(oldColour >> 8 & 0xFF), (byte)(oldColour & 0xFF)};
        UndoManager.blockPainted(player, world, x, y, z, oldrgb, oldPaintType, side);
    }

    public static void blockPainted(EntityPlayer player, World world, int x, int y, int z, byte[] oldrgb, byte oldPaintType, int side) {
        UndoData undoData = new UndoData(x, y, z, world.field_73011_w.field_76574_g, oldrgb, oldPaintType, side);
        if (!playerUndoData.containsKey(player.func_70005_c_())) {
            playerUndoData.put(player.func_70005_c_(), new PlayerUndoData(player));
        }
        PlayerUndoData playerData = playerUndoData.get(player.func_70005_c_());
        playerData.addUndoData(undoData);
    }

    public static void undoPressed(EntityPlayer player) {
        String key = player.func_70005_c_();
        if (!playerUndoData.containsKey(key)) {
            String outOfUndosText = StatCollector.func_74838_a((String)("chat." + "armourersWorkshop".toLowerCase() + ":undo.outOfUndos"));
            player.func_145747_a((IChatComponent)new ChatComponentText(outOfUndosText));
            return;
        }
        PlayerUndoData playerData = playerUndoData.get(key);
        World world = player.field_70170_p;
        String undoText = StatCollector.func_74838_a((String)("chat." + "armourersWorkshop".toLowerCase() + ":undo.undoing"));
        player.func_145747_a((IChatComponent)new ChatComponentText(undoText));
        playerData.playerPressedUndo(world);
        if (playerData.getAvalableUndos() < 1) {
            playerUndoData.remove(key);
        }
    }
}

