/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  io.netty.buffer.ByteBuf
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.serialize.SkinIdentifierSerializer;
import riskyken.armourersWorkshop.common.skin.data.serialize.SkinSerializer;
import riskyken.armourersWorkshop.utils.ModLogger;

public final class ByteBufHelper {
    public static void writeUUID(ByteBuf buf, UUID uuid) {
        buf.writeLong(uuid.getMostSignificantBits());
        buf.writeLong(uuid.getLeastSignificantBits());
    }

    public static UUID readUUID(ByteBuf buf) {
        long mostSigBits = buf.readLong();
        long leastSigBits = buf.readLong();
        return new UUID(mostSigBits, leastSigBits);
    }

    public static void writeStringArrayToBuf(ByteBuf buf, String[] strings) {
        buf.writeInt(strings.length);
        for (int i = 0; i < strings.length; ++i) {
            ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)strings[i]);
        }
    }

    public static String[] readStringArrayFromBuf(ByteBuf buf) {
        int size = buf.readInt();
        String[] strings = new String[size];
        for (int i = 0; i < size; ++i) {
            strings[i] = ByteBufUtils.readUTF8String((ByteBuf)buf);
        }
        return strings;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void writeSkinToByteBuf(ByteBuf buf, Skin skin) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(baos);
        boolean compress = ConfigHandler.serverCompressesSkins;
        buf.writeBoolean(compress);
        try {
            SkinSerializer.writeToStream(skin, dataOutputStream);
            SkinIdentifierSerializer.writeToStream(skin.requestId, dataOutputStream);
            dataOutputStream.flush();
            byte[] skinData = baos.toByteArray();
            if (compress) {
                skinData = ByteBufHelper.compressedByteArray(skinData);
            }
            if (skinData == null) {
                ModLogger.log(Level.ERROR, "Failed to compress skin data.");
                return;
            }
            ByteBufHelper.writeByteArrayToByteBuf(buf, skinData);
        }
        catch (IOException e2) {
            e2.printStackTrace();
            return;
        }
        finally {
            IOUtils.closeQuietly((OutputStream)dataOutputStream);
            IOUtils.closeQuietly((OutputStream)baos);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Skin readSkinFromByteBuf(ByteBuf buf) {
        boolean compressed = buf.readBoolean();
        byte[] skinData = ByteBufHelper.readByteArrayFromByteBuf(buf);
        if (compressed) {
            skinData = ByteBufHelper.decompressByteArray(skinData);
        }
        if (skinData == null) {
            ModLogger.log(Level.ERROR, "Failed to decompress skin data.");
            return null;
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(skinData);
        DataInputStream dataInputStream = new DataInputStream(bais);
        Skin skin = null;
        try {
            skin = SkinSerializer.readSkinFromStream(dataInputStream);
            skin.requestId = SkinIdentifierSerializer.readFromStream(dataInputStream);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly((InputStream)dataInputStream);
            IOUtils.closeQuietly((InputStream)bais);
        }
        return skin;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static byte[] convertSkinToByteArray(Skin skin) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(baos);
        byte[] skinData = null;
        try {
            SkinSerializer.writeToStream(skin, dataOutputStream);
            SkinIdentifierSerializer.writeToStream(skin.requestId, dataOutputStream);
            dataOutputStream.flush();
            skinData = baos.toByteArray();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly((OutputStream)dataOutputStream);
            IOUtils.closeQuietly((OutputStream)baos);
        }
        return skinData;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Skin convertByteArrayToSkin(byte[] data) {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream dataInputStream = new DataInputStream(bais);
        Skin skin = null;
        try {
            skin = SkinSerializer.readSkinFromStream(dataInputStream);
            skin.requestId = SkinIdentifierSerializer.readFromStream(dataInputStream);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly((InputStream)dataInputStream);
            IOUtils.closeQuietly((InputStream)bais);
        }
        return skin;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static byte[] compressedByteArray(byte[] data) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gzos = null;
        try {
            gzos = new GZIPOutputStream(baos);
            gzos.write(data);
            gzos.close();
        }
        catch (IOException e) {
            byte[] byArray;
            try {
                e.printStackTrace();
                IOUtils.closeQuietly((OutputStream)gzos);
                IOUtils.closeQuietly((OutputStream)baos);
                byArray = null;
            }
            catch (Throwable throwable) {
                IOUtils.closeQuietly(gzos);
                IOUtils.closeQuietly((OutputStream)baos);
                throw throwable;
            }
            IOUtils.closeQuietly((OutputStream)gzos);
            IOUtils.closeQuietly((OutputStream)baos);
            return byArray;
        }
        IOUtils.closeQuietly((OutputStream)gzos);
        IOUtils.closeQuietly((OutputStream)baos);
        byte[] compressedData = baos.toByteArray();
        return compressedData;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static byte[] decompressByteArray(byte[] compressedData) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream gzis = null;
        try {
            IOUtils.copy((InputStream)new GZIPInputStream(new ByteArrayInputStream(compressedData)), (OutputStream)baos);
        }
        catch (IOException e) {
            e.printStackTrace();
            Object data = null;
        }
        finally {
            IOUtils.closeQuietly(gzis);
            IOUtils.closeQuietly((OutputStream)baos);
        }
        return baos.toByteArray();
    }

    public static void writeByteArrayToByteBuf(ByteBuf buf, byte[] data) {
        buf.writeInt(data.length);
        buf.writeBytes(data);
    }

    public static byte[] readByteArrayFromByteBuf(ByteBuf buf) {
        int size = buf.readInt();
        byte[] data = new byte[size];
        buf.readBytes(data);
        return data;
    }

    private static void writeByteArrayToStream(DataOutputStream dataOutputStream, byte[] data) throws IOException {
        dataOutputStream.writeInt(data.length);
        dataOutputStream.write(data);
    }

    private static byte[] readByteArrayFromStream(DataInputStream dataInputStream) throws IOException {
        int size = dataInputStream.readInt();
        byte[] data = new byte[size];
        dataInputStream.read(data);
        return data;
    }
}

