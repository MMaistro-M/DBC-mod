/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.village.MerchantRecipeList
 */
package noppes.npcs;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.village.MerchantRecipeList;
import noppes.npcs.LogWriter;

public class Server {
    public static boolean fillBuffer(ByteBuf buffer, Enum enu, Object ... obs) throws IOException {
        buffer.writeInt(enu.ordinal());
        for (Object ob : obs) {
            if (ob == null) continue;
            if (ob instanceof Map) {
                Map map = (Map)ob;
                buffer.writeInt(map.size());
                for (String key : map.keySet()) {
                    int value = (Integer)map.get(key);
                    buffer.writeInt(value);
                    ByteBufUtils.writeString(buffer, key);
                }
                continue;
            }
            if (ob instanceof MerchantRecipeList) {
                ((MerchantRecipeList)ob).func_151391_a(new PacketBuffer(buffer));
                continue;
            }
            if (ob instanceof List) {
                List list = (List)ob;
                buffer.writeInt(list.size());
                for (String s : list) {
                    ByteBufUtils.writeString(buffer, s);
                }
                continue;
            }
            if (ob instanceof Enum) {
                buffer.writeInt(((Enum)ob).ordinal());
                continue;
            }
            if (ob instanceof Integer) {
                buffer.writeInt(((Integer)ob).intValue());
                continue;
            }
            if (ob instanceof Boolean) {
                buffer.writeBoolean(((Boolean)ob).booleanValue());
                continue;
            }
            if (ob instanceof String) {
                ByteBufUtils.writeString(buffer, (String)ob);
                continue;
            }
            if (ob instanceof Float) {
                buffer.writeFloat(((Float)ob).floatValue());
                continue;
            }
            if (ob instanceof Long) {
                buffer.writeLong(((Long)ob).longValue());
                continue;
            }
            if (ob instanceof Double) {
                buffer.writeDouble(((Double)ob).doubleValue());
                continue;
            }
            if (ob instanceof byte[]) {
                byte[] byteArray = (byte[])ob;
                buffer.writeShort((int)((short)byteArray.length));
                buffer.writeBytes(byteArray);
                continue;
            }
            if (!(ob instanceof NBTTagCompound)) continue;
            ByteBufUtils.writeNBT(buffer, (NBTTagCompound)ob);
        }
        if (buffer.array().length >= Short.MAX_VALUE) {
            LogWriter.error("Packet " + enu + " was too big to be send");
            LogWriter.script("Issue occurred with the following Packet - " + enu + ":\n" + Arrays.toString(obs));
            return false;
        }
        return true;
    }
}

