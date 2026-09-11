/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParser
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.utils;

import com.google.common.base.Charsets;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.utils.ModLogger;

public final class SerializeHelper {
    private SerializeHelper() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String readFile(File file, Charset encoding) {
        FileInputStream inputStream = null;
        String text = null;
        try {
            inputStream = new FileInputStream(file);
            char[] data = IOUtils.toCharArray((InputStream)inputStream, (Charset)encoding);
            text = new String(data);
        }
        catch (Exception e) {
            try {
                e.printStackTrace();
            }
            catch (Throwable throwable) {
                IOUtils.closeQuietly(inputStream);
                throw throwable;
            }
            IOUtils.closeQuietly((InputStream)inputStream);
        }
        IOUtils.closeQuietly((InputStream)inputStream);
        return text;
    }

    public static String readFile(InputStream inputStream, Charset encoding) throws IOException {
        char[] data = IOUtils.toCharArray((InputStream)inputStream, (Charset)encoding);
        return new String(data);
    }

    public static JsonElement readJsonFile(File file) {
        return SerializeHelper.readJsonFile(file, Charsets.UTF_8);
    }

    public static JsonElement readJsonFile(File file, Charset encoding) {
        return SerializeHelper.stringToJson(SerializeHelper.readFile(file, encoding));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void writeFile(File file, Charset encoding, String text) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file, false);
            byte[] data = text.getBytes(encoding);
            ((OutputStream)outputStream).write(data);
            outputStream.flush();
        }
        catch (Exception e) {
            try {
                e.printStackTrace();
            }
            catch (Throwable throwable) {
                IOUtils.closeQuietly(outputStream);
                throw throwable;
            }
            IOUtils.closeQuietly((OutputStream)outputStream);
        }
        IOUtils.closeQuietly((OutputStream)outputStream);
    }

    public static void writeJsonFile(File file, Charset encoding, JsonElement json) {
        SerializeHelper.writeFile(file, encoding, json.toString());
    }

    public static void writeJsonFile(JsonElement json, File file) {
        SerializeHelper.writeFile(file, Charsets.UTF_8, json.toString());
    }

    public static JsonElement stringToJson(String jsonString) {
        try {
            JsonParser parser = new JsonParser();
            return parser.parse(jsonString);
        }
        catch (Exception e) {
            ModLogger.log(Level.ERROR, "Error parsing json.");
            ModLogger.log(Level.ERROR, e.getMessage());
            return null;
        }
    }
}

