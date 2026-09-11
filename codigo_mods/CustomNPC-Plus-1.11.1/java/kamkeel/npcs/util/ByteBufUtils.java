/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTSizeTracker
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.village.MerchantRecipeList
 */
package kamkeel.npcs.util;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.village.MerchantRecipeList;

public class ByteBufUtils
extends cpw.mods.fml.common.network.ByteBufUtils {
    public static void fillBuffer(ByteBuf buffer, Object ... obs) throws IOException {
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
    }

    public static void writeIntArray(ByteBuf buffer, int[] arr) {
        buffer.writeInt(arr.length);
        for (int i : arr) {
            buffer.writeInt(i);
        }
    }

    public static int[] readIntArray(ByteBuf buffer) {
        int length = buffer.readInt();
        int[] arr = new int[length];
        for (int i = 0; i < length; ++i) {
            arr[i] = buffer.readInt();
        }
        return arr;
    }

    public static void writeNBT(ByteBuf buffer, NBTTagCompound compound) throws IOException {
        byte[] bytes = CompressedStreamTools.func_74798_a((NBTTagCompound)compound);
        buffer.writeShort((int)((short)bytes.length));
        buffer.writeBytes(bytes);
    }

    public static NBTTagCompound readNBT(ByteBuf buffer) throws IOException {
        byte[] bytes = new byte[buffer.readShort()];
        buffer.readBytes(bytes);
        return CompressedStreamTools.func_152457_a((byte[])bytes, (NBTSizeTracker)new NBTSizeTracker(0x200000L));
    }

    public static void writeBigNBT(ByteBuf buffer, NBTTagCompound compound) throws IOException {
        byte[] bytes = CompressedStreamTools.func_74798_a((NBTTagCompound)compound);
        buffer.writeInt(bytes.length);
        buffer.writeBytes(bytes);
    }

    public static NBTTagCompound readBigNBT(ByteBuf buffer) throws IOException {
        int length = buffer.readInt();
        byte[] bytes = new byte[length];
        buffer.readBytes(bytes);
        return CompressedStreamTools.func_152457_a((byte[])bytes, (NBTSizeTracker)new NBTSizeTracker(0x1E00000L));
    }

    public static void writeString(ByteBuf buffer, String s) {
        byte[] bytes = s.getBytes(Charsets.UTF_8);
        buffer.writeShort((int)((short)bytes.length));
        buffer.writeBytes(bytes);
    }

    public static String readString(ByteBuf buffer) {
        try {
            byte[] bytes = new byte[buffer.readShort()];
            buffer.readBytes(bytes);
            return new String(bytes, Charsets.UTF_8);
        }
        catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }
}

