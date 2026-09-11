/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.io.Files
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagByte
 *  net.minecraft.nbt.NBTTagByteArray
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagDouble
 *  net.minecraft.nbt.NBTTagFloat
 *  net.minecraft.nbt.NBTTagInt
 *  net.minecraft.nbt.NBTTagIntArray
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagLong
 *  net.minecraft.nbt.NBTTagShort
 *  net.minecraft.nbt.NBTTagString
 *  org.apache.commons.io.Charsets
 */
package noppes.npcs.util;

import com.google.common.io.Files;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;
import noppes.npcs.LogWriter;
import noppes.npcs.util.JsonException;
import org.apache.commons.io.Charsets;

public class NBTJsonUtil {
    public static String Convert(NBTTagCompound compound) {
        ArrayList<JsonLine> list = new ArrayList<JsonLine>();
        JsonLine line = NBTJsonUtil.ReadTag("", (NBTBase)compound, list);
        line.removeComma();
        return NBTJsonUtil.ConvertList(list);
    }

    public static NBTTagCompound Convert(String json) throws JsonException {
        json = json.trim();
        JsonFile file = new JsonFile(json);
        if (!json.startsWith("{") || !json.endsWith("}")) {
            throw new JsonException("Not properly incapsulated between { }", file);
        }
        NBTTagCompound compound = new NBTTagCompound();
        NBTJsonUtil.FillCompound(compound, file);
        return compound;
    }

    public static void FillCompound(NBTTagCompound compound, JsonFile json) throws JsonException {
        if (json.startsWith("{") || json.startsWith(",")) {
            json.cut(1);
        }
        if (json.startsWith("}")) {
            return;
        }
        int index = json.indexOf(":");
        if (index < 1) {
            throw new JsonException("Expected key after ,", json);
        }
        String key = json.substring(0, index);
        json.cut(index + 1);
        NBTBase base = NBTJsonUtil.ReadValue(json);
        if (base == null) {
            base = new NBTTagString();
        }
        if (key.startsWith("\"")) {
            key = key.substring(1);
        }
        if (key.endsWith("\"")) {
            key = key.substring(0, key.length() - 1);
        }
        compound.func_74782_a(key, base);
        if (json.startsWith(",")) {
            NBTJsonUtil.FillCompound(compound, json);
        }
    }

    public static NBTBase ReadValue(JsonFile json) throws JsonException {
        if (json.startsWith("{")) {
            NBTTagCompound compound = new NBTTagCompound();
            NBTJsonUtil.FillCompound(compound, json);
            if (!json.startsWith("}")) {
                throw new JsonException("Expected }", json);
            }
            json.cut(1);
            return compound;
        }
        if (json.startsWith("[")) {
            json.cut(1);
            NBTTagList list = new NBTTagList();
            NBTBase value = NBTJsonUtil.ReadValue(json);
            while (value != null) {
                list.func_74742_a(value);
                if (!json.startsWith(",")) break;
                json.cut(1);
                value = NBTJsonUtil.ReadValue(json);
            }
            if (!json.startsWith("]")) {
                throw new JsonException("Expected ]", json);
            }
            json.cut(1);
            if (list.func_150303_d() == 3) {
                int[] arr = new int[list.func_74745_c()];
                int i = 0;
                while (list.func_74745_c() > 0) {
                    arr[i] = ((NBTTagInt)list.func_74744_a(0)).func_150287_d();
                    ++i;
                }
                return new NBTTagIntArray(arr);
            }
            if (list.func_150303_d() == 1) {
                byte[] arr = new byte[list.func_74745_c()];
                int i = 0;
                while (list.func_74745_c() > 0) {
                    arr[i] = ((NBTTagByte)list.func_74744_a(0)).func_150290_f();
                    ++i;
                }
                return new NBTTagByteArray(arr);
            }
            return list;
        }
        if (json.startsWith("\"")) {
            json.cut(1);
            String s = "";
            boolean ignore = false;
            while (!json.startsWith("\"") || ignore) {
                String cut = json.cutDirty(1);
                ignore = cut.equals("\\");
                s = s + cut;
            }
            json.cut(1);
            return new NBTTagString(s.replace("\\\"", "\""));
        }
        String s = "";
        while (!json.startsWith(",", "]", "}")) {
            s = s + json.cut(1);
        }
        if ((s = s.trim().toLowerCase()).isEmpty() || s.contains("bytes]")) {
            return null;
        }
        try {
            if (s.endsWith("d")) {
                return new NBTTagDouble(Double.parseDouble(s.substring(0, s.length() - 1)));
            }
            if (s.endsWith("f")) {
                return new NBTTagFloat(Float.parseFloat(s.substring(0, s.length() - 1)));
            }
            if (s.endsWith("b")) {
                return new NBTTagByte(Byte.parseByte(s.substring(0, s.length() - 1)));
            }
            if (s.endsWith("s")) {
                return new NBTTagShort(Short.parseShort(s.substring(0, s.length() - 1)));
            }
            if (s.endsWith("l")) {
                return new NBTTagLong(Long.parseLong(s.substring(0, s.length() - 1)));
            }
            if (s.contains(".")) {
                return new NBTTagDouble(Double.parseDouble(s));
            }
            return new NBTTagInt(Integer.parseInt(s));
        }
        catch (NumberFormatException ex) {
            throw new JsonException("Unable to convert: " + s + " to a number", json);
        }
    }

    private static List<NBTBase> getListData(NBTTagList list) {
        return (List)ObfuscationReflectionHelper.getPrivateValue(NBTTagList.class, (Object)list, (int)0);
    }

    private static JsonLine ReadTag(String name, NBTBase base, List<JsonLine> list) {
        JsonLine line;
        if (!name.isEmpty()) {
            name = "\"" + name + "\": ";
        }
        if (base.func_74732_a() == 8) {
            String data = ((NBTTagString)base).func_150285_a_();
            data = data.replace("\"", "\\\"");
            list.add(new JsonLine(name + "\"" + data + "\""));
        } else {
            JsonLine line2;
            if (base.func_74732_a() == 7) {
                JsonLine line3;
                byte[] arr = ((NBTTagByteArray)base).func_150292_c();
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                for (int i = 0; i < arr.length; ++i) {
                    sb.append(arr[i]).append("b");
                    if (i >= arr.length - 1) continue;
                    sb.append(",");
                }
                sb.append("]");
                list.add(new JsonLine(name + sb.toString()));
                JsonLine jsonLine = line3 = list.get(list.size() - 1);
                jsonLine.line = jsonLine.line + ",";
                return line3;
            }
            if (base.func_74732_a() == 9) {
                list.add(new JsonLine(name + "["));
                NBTTagList tags = (NBTTagList)base;
                line2 = null;
                List<NBTBase> data = NBTJsonUtil.getListData(tags);
                for (NBTBase b : data) {
                    line2 = NBTJsonUtil.ReadTag("", b, list);
                }
                if (line2 != null) {
                    line2.removeComma();
                }
                list.add(new JsonLine("]"));
            } else if (base.func_74732_a() == 10) {
                list.add(new JsonLine(name + "{"));
                NBTTagCompound compound = (NBTTagCompound)base;
                line2 = null;
                for (Object key : compound.func_150296_c()) {
                    line2 = NBTJsonUtil.ReadTag(key.toString(), compound.func_74781_a(key.toString()), list);
                }
                if (line2 != null) {
                    line2.removeComma();
                }
                list.add(new JsonLine("}"));
            } else if (base.func_74732_a() == 11) {
                list.add(new JsonLine(name + base.toString().replaceFirst(",]", "]")));
            } else {
                list.add(new JsonLine(name + base));
            }
        }
        JsonLine jsonLine = line = list.get(list.size() - 1);
        jsonLine.line = jsonLine.line + ",";
        return line;
    }

    private static String ConvertList(List<JsonLine> list) {
        String json = "";
        int tab = 0;
        for (JsonLine tag : list) {
            if (tag.reduceTab()) {
                --tab;
            }
            for (int i = 0; i < tab; ++i) {
                json = json + "    ";
            }
            json = json + tag + "\n";
            if (!tag.increaseTab()) continue;
            ++tab;
        }
        return json;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static NBTTagCompound loadNBTData(File file) {
        try (FileInputStream fis = new FileInputStream(file);){
            NBTTagCompound nBTTagCompound = CompressedStreamTools.func_74796_a((InputStream)fis);
            return nBTTagCompound;
        }
        catch (Exception e) {
            LogWriter.error("Error loading: " + file.getName(), e);
            return new NBTTagCompound();
        }
    }

    public static NBTTagCompound LoadFile(File file) throws IOException, JsonException {
        return NBTJsonUtil.Convert(Files.toString((File)file, (Charset)Charsets.UTF_8));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void SaveFile(File file, NBTTagCompound compound) throws IOException, JsonException {
        String json = NBTJsonUtil.Convert(compound);
        try (OutputStreamWriter writer = null;){
            writer = new OutputStreamWriter((OutputStream)new FileOutputStream(file), Charsets.UTF_8);
            writer.write(json);
        }
    }

    public static void main(String[] args) {
        try {
            NBTTagCompound comp = new NBTTagCompound();
            NBTTagCompound comp2 = new NBTTagCompound();
            comp2.func_74773_a("test", new byte[]{0, 0, 1, 1, 0});
            comp2.func_74783_a("intArray", new int[]{0, 0, 1, 1, 0});
            comp2.func_74774_a("byte", (byte)7);
            comp2.func_74777_a("short", (short)123);
            comp2.func_74768_a("int", 456);
            comp2.func_74772_a("long", 789L);
            comp2.func_74776_a("float", 3.14f);
            comp2.func_74780_a("double", 2.71828);
            comp2.func_74778_a("string", "Testing");
            NBTTagList list = new NBTTagList();
            list.func_74742_a((NBTBase)new NBTTagString("jim"));
            list.func_74742_a((NBTBase)new NBTTagString("foo"));
            comp2.func_74782_a("list", (NBTBase)list);
            NBTTagCompound innerComp = new NBTTagCompound();
            innerComp.func_74778_a("innerString", "innerValue");
            innerComp.func_74768_a("innerInt", 10);
            innerComp.func_74774_a("innerByte", (byte)11);
            comp2.func_74782_a("innerComp", (NBTBase)innerComp);
            comp.func_74782_a("comp", (NBTBase)comp2);
            String json = NBTJsonUtil.Convert(comp);
            System.out.println("Generated JSON:");
            System.out.println(json);
            NBTTagCompound compFromJson = NBTJsonUtil.Convert(json);
            String jsonAfterRead = NBTJsonUtil.Convert(compFromJson);
            System.out.println("\nRe-converted JSON from read NBT:");
            System.out.println(jsonAfterRead);
            if (json.equals(jsonAfterRead)) {
                System.out.println("\nSUCCESS: Read and write operations are consistent for all types.");
            } else {
                System.out.println("\nWARNING: There is a mismatch in read and write results for some types.");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class JsonFile {
        private String original;
        private String text;

        public JsonFile(String text) {
            this.text = text;
            this.original = text;
        }

        public String cutDirty(int i) {
            String s = this.text.substring(0, i);
            this.text = this.text.substring(i);
            return s;
        }

        public String cut(int i) {
            String s = this.text.substring(0, i);
            this.text = this.text.substring(i).trim();
            return s;
        }

        public String substring(int beginIndex, int endIndex) {
            return this.text.substring(beginIndex, endIndex);
        }

        public int indexOf(String s) {
            return this.text.indexOf(s);
        }

        public String getCurrentPos() {
            int lengthOr = this.original.length();
            int lengthCur = this.text.length();
            int currentPos = lengthOr - lengthCur;
            String done = this.original.substring(0, currentPos);
            String[] lines = done.split("\r\n|\r|\n");
            int pos = 0;
            String line = "";
            if (lines.length > 0) {
                pos = lines[lines.length - 1].length();
                line = this.original.split("\r\n|\r|\n")[lines.length - 1].trim();
            }
            return "Line: " + lines.length + ", Pos: " + pos + ", Text: " + line;
        }

        public boolean startsWith(String ... ss) {
            for (String s : ss) {
                if (!this.text.startsWith(s)) continue;
                return true;
            }
            return false;
        }

        public boolean endsWith(String s) {
            return this.text.endsWith(s);
        }
    }

    static class JsonLine {
        private String line;

        public JsonLine(String line) {
            this.line = line;
        }

        public void removeComma() {
            if (this.line.endsWith(",")) {
                this.line = this.line.substring(0, this.line.length() - 1);
            }
        }

        public boolean reduceTab() {
            int length = this.line.length();
            return length == 1 && (this.line.endsWith("}") || this.line.endsWith("]")) || length == 2 && (this.line.endsWith("},") || this.line.endsWith("],"));
        }

        public boolean increaseTab() {
            return this.line.endsWith("{") || this.line.endsWith("[");
        }

        public String toString() {
            return this.line;
        }
    }
}

