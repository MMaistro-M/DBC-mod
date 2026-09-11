/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.world.World
 */
package com.tobiasmjc.dbcadditions.packets;

import com.tobiasmjc.dbcadditions.packets.DBUPacketAbsorb;
import com.tobiasmjc.dbcadditions.packets.DBUPacketLearnSkill;
import com.tobiasmjc.dbcadditions.packets.DBUPacketOpenGui;
import com.tobiasmjc.dbcadditions.packets.DBUPacketRemoveSkill;
import com.tobiasmjc.dbcadditions.packets.DBUPacketResetData;
import com.tobiasmjc.dbcadditions.packets.DBUPacketSelectForm;
import com.tobiasmjc.dbcadditions.packets.DBUPacketSync;
import com.tobiasmjc.dbcadditions.packets.DBUPacketTransform;
import com.tobiasmjc.dbcadditions.packets.DBUPacketUpgradeSkill;
import com.tobiasmjc.dbcadditions.packets.DBUPacketWish;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class DBUPackets {
    private static final SimpleNetworkWrapper dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel("dbcadditions");

    public static void registerPackets() {
        dispatcher.registerMessage(DBUPacketWish.Handler.class, DBUPacketWish.class, 0, Side.SERVER);
        dispatcher.registerMessage(DBUPacketSelectForm.Handler.class, DBUPacketSelectForm.class, 1, Side.SERVER);
        dispatcher.registerMessage(DBUPacketTransform.Handler.class, DBUPacketTransform.class, 2, Side.SERVER);
        dispatcher.registerMessage(DBUPacketTransform.Handler.class, DBUPacketTransform.class, 2, Side.CLIENT);
        dispatcher.registerMessage(DBUPacketLearnSkill.Handler.class, DBUPacketLearnSkill.class, 3, Side.SERVER);
        dispatcher.registerMessage(DBUPacketRemoveSkill.Handler.class, DBUPacketRemoveSkill.class, 4, Side.SERVER);
        dispatcher.registerMessage(DBUPacketUpgradeSkill.Handler.class, DBUPacketUpgradeSkill.class, 5, Side.SERVER);
        dispatcher.registerMessage(DBUPacketAbsorb.Handler.class, DBUPacketAbsorb.class, 6, Side.CLIENT);
        dispatcher.registerMessage(DBUPacketAbsorb.Handler.class, DBUPacketAbsorb.class, 6, Side.SERVER);
        dispatcher.registerMessage(DBUPacketResetData.Handler.class, DBUPacketResetData.class, 7, Side.CLIENT);
        dispatcher.registerMessage(DBUPacketSync.Handler.class, DBUPacketSync.class, 8, Side.CLIENT);
        dispatcher.registerMessage(DBUPacketOpenGui.Handler.class, DBUPacketOpenGui.class, 9, Side.SERVER);
    }

    public static void sendToServer(IMessage message) {
        dispatcher.sendToServer(message);
    }

    public static void sendToClient(IMessage message, EntityPlayerMP player) {
        dispatcher.sendTo(message, player);
    }

    public static void sendToAll(IMessage message, World world) {
        for (Object p1 : world.field_73010_i) {
            if (!(p1 instanceof EntityPlayerMP)) continue;
            DBUPackets.sendToClient(message, (EntityPlayerMP)p1);
        }
    }

    public static void sendToAll(IMessage message) {
        dispatcher.sendToAll(message);
    }
}

