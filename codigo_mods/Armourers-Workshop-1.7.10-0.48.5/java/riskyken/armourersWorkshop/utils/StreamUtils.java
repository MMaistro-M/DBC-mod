/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.Charsets
 */
package riskyken.armourersWorkshop.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.io.Charsets;

public final class StreamUtils {
    private StreamUtils() {
    }

    public static void writeString(DataOutputStream stream, Charset charset, String string) throws IOException {
        byte[] bytes = string.getBytes(charset);
        int size = bytes.length;
        StreamUtils.writeUnsignedShort(stream, size);
        stream.write(bytes);
    }

    public static String readString(DataInputStream stream, Charset charset) throws IOException {
        int size = StreamUtils.readUnsignedShort(stream);
        byte[] bytes = new byte[size];
        stream.read(bytes, 0, size);
        return new String(bytes, charset);
    }

    public static void writeStringUtf8(DataOutputStream stream, String string) throws IOException {
        StreamUtils.writeString(stream, Charsets.UTF_8, string);
    }

    public static void writeStringAscii(DataOutputStream stream, String string) throws IOException {
        StreamUtils.writeString(stream, Charsets.US_ASCII, string);
    }

    public static String readStringUtf8(DataInputStream stream) throws IOException {
        return StreamUtils.readString(stream, Charsets.UTF_8);
    }

    public static String readStringAscii(DataInputStream stream) throws IOException {
        return StreamUtils.readString(stream, Charsets.US_ASCII);
    }

    private static void writeUnsignedShort(DataOutputStream stream, int value) throws IOException {
        if (value > 65535) {
            throw new IOException("String is over the max length allowed.");
        }
        stream.writeShort((short)value);
    }

    private static int readUnsignedShort(DataInputStream stream) throws IOException {
        return stream.readShort() & 0xFFFF;
    }
}

