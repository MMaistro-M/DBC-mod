/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.Teleporter
 */
package com.tobiasmjc.dbcadditions.packets;

import com.tobiasmjc.dbcadditions.DBCAConfig;
import com.tobiasmjc.dbcadditions.data.DBCAPlayer;
import com.tobiasmjc.dbcadditions.dimensions.TeleporterDBUtils;
import com.tobiasmjc.dbcadditions.items.ItemsDBCUtils;
import com.tobiasmjc.dbcadditions.packets.DBUMessageHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

public class DBUPacketWish
implements IMessage {
    byte i;

    public DBUPacketWish() {
    }

    public DBUPacketWish(byte i) {
        this.i = i;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeByte((int)this.i);
    }

    public void fromBytes(ByteBuf buffer) {
        this.i = buffer.readByte();
    }

    public static class Handler
    extends DBUMessageHandler<DBUPacketWish> {
        @Override
        public void onServerSide(EntityPlayerMP p, DBUPacketWish packet) {
            byte id = packet.i;
            MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
            if (id >= 4 && id <= 9) {
                DBCAPlayer player = DBCAPlayer.get((EntityPlayer)p);
                player.PotaraCooldown = DBCAConfig.PotaraCooldown * 2;
                player.saveNBTData();
            }
            switch (id) {
                case 0: {
                    TeleporterDBUtils.transferPlayer(server, p, 98);
                    break;
                }
                case 1: {
                    if (TeleporterDBUtils.transferPlayer(server, p, 99)) {
                        p.playerNetServerHandler.setPlayerLocation(-10.0, 70.0, 0.0, 0.0f, 0.0f);
                    }
                    break;
                }
                case 2: {
                    if (TeleporterDBUtils.transferPlayer(server, p, 0)) {
                        p.playerNetServerHandler.setPlayerLocation(80.0, 220.0, 60.0, 0.0f, 0.0f);
                    }
                    break;
                }
                case 3: {
                    if (TeleporterDBUtils.transferPlayer(server, p, 100)) {
                        p.playerNetServerHandler.setPlayerLocation(0.0, 74.0, -39.0, 0.0f, 0.0f);
                    }
                    break;
                }
                case 4: {
                    p.inventory.addItemStackToInventory(new ItemStack((Item)ItemsDBCUtils.potara_yellow, DBCAConfig.PotaraAmount));
                    break;
                }
                case 5: {
                    p.inventory.addItemStackToInventory(new ItemStack((Item)ItemsDBCUtils.potara_green, DBCAConfig.PotaraAmount));
                    break;
                }
                case 6: {
                    p.inventory.addItemStackToInventory(new ItemStack((Item)ItemsDBCUtils.potara_blue, DBCAConfig.PotaraAmount));
                    break;
                }
                case 7: {
                    p.inventory.addItemStackToInventory(new ItemStack((Item)ItemsDBCUtils.potara_red, DBCAConfig.PotaraAmount));
                    break;
                }
                case 8: {
                    p.inventory.addItemStackToInventory(new ItemStack((Item)ItemsDBCUtils.potara_pink, DBCAConfig.PotaraAmount));
                    break;
                }
                case 9: {
                    p.inventory.addItemStackToInventory(new ItemStack((Item)ItemsDBCUtils.potara_white, DBCAConfig.PotaraAmount));
                }
            }
        }
    }
}
