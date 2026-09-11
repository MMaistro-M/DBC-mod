/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagDouble
 *  net.minecraft.nbt.NBTTagInt
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.controllers.data.IScriptHandler;
import noppes.npcs.controllers.data.IScriptUnit;

public class NBTTags {
    public static int TAG_End = 0;
    public static int TAG_Byte = 1;
    public static int TAG_Short = 2;
    public static int TAG_Int = 3;
    public static int TAG_Long = 4;
    public static int TAG_Float = 5;
    public static int TAG_Double = 6;
    public static int TAG_Byte_Array = 7;
    public static int TAG_String = 8;
    public static int TAG_List = 9;
    public static int TAG_Compound = 10;
    public static int TAG_Int_Array = 11;

    public static HashMap<Integer, ItemStack> getItemStackList(NBTTagList tagList) {
        HashMap<Integer, ItemStack> list = new HashMap<Integer, ItemStack>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            try {
                list.put(nbttagcompound.func_74771_c("Slot") & 0xFF, NoppesUtilServer.readItem(nbttagcompound));
                continue;
            }
            catch (ClassCastException e) {
                list.put(nbttagcompound.func_74762_e("Slot"), NoppesUtilServer.readItem(nbttagcompound));
            }
        }
        return list;
    }

    public static ItemStack[] getItemStackArray(NBTTagList tagList) {
        ItemStack[] list = new ItemStack[tagList.func_74745_c()];
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            list[nbttagcompound.func_74771_c((String)"Slot") & 0xFF] = NoppesUtilServer.readItem(nbttagcompound);
        }
        return list;
    }

    public static ArrayList<int[]> getIntegerArraySet(NBTTagList tagList) {
        ArrayList<int[]> set = new ArrayList<int[]>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound compound = tagList.func_150305_b(i);
            set.add(compound.func_74759_k("Array"));
        }
        return set;
    }

    public static HashMap<Integer, Boolean> getBooleanList(NBTTagList tagList) {
        HashMap<Integer, Boolean> list = new HashMap<Integer, Boolean>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            list.put(nbttagcompound.func_74762_e("Slot"), nbttagcompound.func_74767_n("Boolean"));
        }
        return list;
    }

    public static HashMap<Integer, Integer> getIntegerIntegerMap(NBTTagList tagList) {
        HashMap<Integer, Integer> list = new HashMap<Integer, Integer>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            list.put(nbttagcompound.func_74762_e("Slot"), nbttagcompound.func_74762_e("Integer"));
        }
        return list;
    }

    public static HashMap<Integer, Float> getIntegerFloatMap(NBTTagList tagList) {
        HashMap<Integer, Float> list = new HashMap<Integer, Float>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            list.put(nbttagcompound.func_74762_e("Slot"), Float.valueOf(nbttagcompound.func_74760_g("Float")));
        }
        return list;
    }

    public static HashMap<Integer, Double> getIntegerDoubleMap(NBTTagList tagList) {
        HashMap<Integer, Double> list = new HashMap<Integer, Double>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            list.put(nbttagcompound.func_74762_e("Slot"), nbttagcompound.func_74769_h("Double"));
        }
        return list;
    }

    public static HashMap<Integer, Long> getIntegerLongMap(NBTTagList tagList) {
        HashMap<Integer, Long> list = new HashMap<Integer, Long>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            list.put(nbttagcompound.func_74762_e("Slot"), nbttagcompound.func_74763_f("Long"));
        }
        return list;
    }

    public static HashSet<String> getStringSet(NBTTagList tagList) {
        HashSet<String> list = new HashSet<String>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            list.add(nbttagcompound.func_74779_i("String"));
        }
        return list;
    }

    public static HashMap<Integer, Byte> getIntegerByteMap(NBTTagList tagList) {
        HashMap<Integer, Byte> list = new HashMap<Integer, Byte>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            list.put(nbttagcompound.func_74762_e("Slot"), nbttagcompound.func_74771_c("Byte"));
        }
        return list;
    }

    public static NBTTagList nbtIntegerByteMap(Map<Integer, Byte> lines) {
        NBTTagList nbttaglist = new NBTTagList();
        if (lines == null) {
            return nbttaglist;
        }
        for (int slot : lines.keySet()) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74768_a("Slot", slot);
            nbttagcompound.func_74774_a("Byte", lines.get(slot).byteValue());
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public static NBTTagList nbtStringSet(HashSet<String> collection) {
        NBTTagList nbttaglist = new NBTTagList();
        if (collection == null) {
            return nbttaglist;
        }
        for (String slot : collection) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74778_a("String", slot);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public static HashSet<Integer> getIntegerSet(NBTTagList tagList) {
        HashSet<Integer> list = new HashSet<Integer>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            list.add(nbttagcompound.func_74762_e("Integer"));
        }
        return list;
    }

    public static NBTTagList nbtIntegerSet(HashSet<Integer> set) {
        NBTTagList nbttaglist = new NBTTagList();
        if (set == null) {
            return nbttaglist;
        }
        for (int slot : set) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74768_a("Integer", slot);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public static HashMap<String, String> getStringStringMap(NBTTagList tagList) {
        HashMap<String, String> list = new HashMap<String, String>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            list.put(nbttagcompound.func_74779_i("Slot"), nbttagcompound.func_74779_i("Value"));
        }
        return list;
    }

    public static HashMap<Integer, String> getIntegerStringMap(NBTTagList tagList) {
        HashMap<Integer, String> list = new HashMap<Integer, String>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            list.put(nbttagcompound.func_74762_e("Slot"), nbttagcompound.func_74779_i("Value"));
        }
        return list;
    }

    public static HashMap<String, Integer> getStringIntegerMap(NBTTagList tagList) {
        HashMap<String, Integer> list = new HashMap<String, Integer>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            list.put(nbttagcompound.func_74779_i("Slot"), nbttagcompound.func_74762_e("Value"));
        }
        return list;
    }

    public static HashMap<String, int[]> getStringIntegerArrayMap(NBTTagList tagList) {
        HashMap<String, int[]> list = new HashMap<String, int[]>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            list.put(nbttagcompound.func_74779_i("Slot"), nbttagcompound.func_74759_k("Value"));
        }
        return list;
    }

    public static HashMap<String, int[]> getStringIntegerArrayMap(NBTTagList tagList, int arrayLength) {
        HashMap<String, int[]> list = new HashMap<String, int[]>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            int[] a = nbttagcompound.func_74759_k("Value");
            if (a.length != arrayLength) {
                a = new int[arrayLength];
            }
            list.put(nbttagcompound.func_74779_i("Slot"), a);
        }
        return list;
    }

    public static HashMap<String, Vector<String>> getVectorMap(NBTTagList tagList) {
        HashMap<String, Vector<String>> map = new HashMap<String, Vector<String>>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            Vector<String> values = new Vector<String>();
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            NBTTagList list = nbttagcompound.func_150295_c("Values", 10);
            for (int j = 0; j < list.func_74745_c(); ++j) {
                NBTTagCompound value = list.func_150305_b(j);
                values.add(value.func_74779_i("Value"));
            }
            map.put(nbttagcompound.func_74779_i("Key"), values);
        }
        return map;
    }

    public static List<String> getStringList(NBTTagList tagList) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            String line = nbttagcompound.func_74779_i("Line");
            list.add(line);
        }
        return list;
    }

    public static String[] getStringArray(NBTTagList tagList, int size) {
        String[] arr = new String[size];
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            String line = nbttagcompound.func_74779_i("Value");
            int slot = nbttagcompound.func_74762_e("Slot");
            arr[slot] = line;
        }
        return arr;
    }

    public static NBTTagList nbtIntegerArraySet(List<int[]> set) {
        NBTTagList nbttaglist = new NBTTagList();
        if (set == null) {
            return nbttaglist;
        }
        for (int[] arr : set) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74783_a("Array", arr);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public static NBTTagList nbtItemStackList(HashMap<Integer, ItemStack> inventory) {
        NBTTagList nbttaglist = new NBTTagList();
        if (inventory == null) {
            return nbttaglist;
        }
        for (int slot : inventory.keySet()) {
            ItemStack item = inventory.get(slot);
            if (item == null) continue;
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74774_a("Slot", (byte)slot);
            NoppesUtilServer.writeItem(item, nbttagcompound);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public static NBTTagList nbtItemStackArray(ItemStack[] inventory) {
        NBTTagList nbttaglist = new NBTTagList();
        if (inventory == null) {
            return nbttaglist;
        }
        for (int slot = 0; slot < inventory.length; ++slot) {
            ItemStack item = inventory[slot];
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74774_a("Slot", (byte)slot);
            if (item != null) {
                NoppesUtilServer.writeItem(item, nbttagcompound);
            }
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public static NBTTagList nbtBooleanList(HashMap<Integer, Boolean> updatedSlots) {
        NBTTagList nbttaglist = new NBTTagList();
        if (updatedSlots == null) {
            return nbttaglist;
        }
        HashMap<Integer, Boolean> inventory2 = updatedSlots;
        for (Integer slot : inventory2.keySet()) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74768_a("Slot", slot.intValue());
            nbttagcompound.func_74757_a("Boolean", inventory2.get(slot).booleanValue());
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public static NBTTagList nbtIntegerIntegerMap(Map<Integer, Integer> lines) {
        NBTTagList nbttaglist = new NBTTagList();
        if (lines == null) {
            return nbttaglist;
        }
        for (int slot : lines.keySet()) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74768_a("Slot", slot);
            nbttagcompound.func_74768_a("Integer", lines.get(slot).intValue());
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public static NBTTagList nbtIntegerFloatMap(Map<Integer, Float> lines) {
        NBTTagList nbttaglist = new NBTTagList();
        if (lines == null) {
            return nbttaglist;
        }
        for (int slot : lines.keySet()) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74768_a("Slot", slot);
            nbttagcompound.func_74780_a("Float", (double)lines.get(slot).floatValue());
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public static NBTTagList nbtIntegerDoubleMap(Map<Integer, Double> lines) {
        NBTTagList nbttaglist = new NBTTagList();
        if (lines == null) {
            return nbttaglist;
        }
        for (int slot : lines.keySet()) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74768_a("Slot", slot);
            nbttagcompound.func_74780_a("Double", lines.get(slot).doubleValue());
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public static NBTTagList nbtIntegerLongMap(Map<Integer, Long> lines) {
        NBTTagList nbttaglist = new NBTTagList();
        if (lines == null) {
            return nbttaglist;
        }
        for (int slot : lines.keySet()) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74768_a("Slot", slot);
            nbttagcompound.func_74772_a("Long", lines.get(slot).longValue());
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public static NBTTagList nbtVectorMap(Map<String, Vector<String>> map) {
        NBTTagList list = new NBTTagList();
        if (map == null) {
            return list;
        }
        for (String key : map.keySet()) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.func_74778_a("Key", key);
            NBTTagList values = new NBTTagList();
            for (String value : map.get(key)) {
                NBTTagCompound comp = new NBTTagCompound();
                comp.func_74778_a("Value", value);
                values.func_74742_a((NBTBase)comp);
            }
            compound.func_74782_a("Values", (NBTBase)values);
            list.func_74742_a((NBTBase)compound);
        }
        return list;
    }

    public static NBTTagList nbtStringStringMap(Map<String, String> map) {
        NBTTagList nbttaglist = new NBTTagList();
        if (map == null) {
            return nbttaglist;
        }
        for (String slot : map.keySet()) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74778_a("Slot", slot);
            nbttagcompound.func_74778_a("Value", map.get(slot));
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public static NBTTagList nbtStringIntegerMap(Map<String, Integer> map) {
        NBTTagList nbttaglist = new NBTTagList();
        if (map == null) {
            return nbttaglist;
        }
        for (String slot : map.keySet()) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74778_a("Slot", slot);
            nbttagcompound.func_74768_a("Value", map.get(slot).intValue());
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public static NBTTagList nbtStringIntegerArrayMap(Map<String, int[]> map) {
        NBTTagList nbttaglist = new NBTTagList();
        if (map == null) {
            return nbttaglist;
        }
        for (String slot : map.keySet()) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74778_a("Slot", slot);
            nbttagcompound.func_74783_a("Value", map.get(slot));
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public static NBTBase nbtIntegerStringMap(HashMap<Integer, String> map) {
        NBTTagList nbttaglist = new NBTTagList();
        if (map == null) {
            return nbttaglist;
        }
        for (int slot : map.keySet()) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74768_a("Slot", slot);
            nbttagcompound.func_74778_a("Value", map.get(slot));
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public static NBTTagList nbtStringArray(String[] list) {
        NBTTagList nbttaglist = new NBTTagList();
        if (list == null) {
            return nbttaglist;
        }
        for (int i = 0; i < list.length; ++i) {
            if (list[i] == null) continue;
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74778_a("Value", list[i]);
            nbttagcompound.func_74768_a("Slot", i);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public static NBTTagList nbtStringList(List<String> list) {
        NBTTagList nbttaglist = new NBTTagList();
        for (String s : list) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74778_a("Line", s);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public static NBTTagList nbtDoubleList(double ... par1ArrayOfDouble) {
        NBTTagList nbttaglist = new NBTTagList();
        double[] adouble = par1ArrayOfDouble;
        int i = par1ArrayOfDouble.length;
        for (int j = 0; j < i; ++j) {
            double d1 = adouble[j];
            nbttaglist.func_74742_a((NBTBase)new NBTTagDouble(d1));
        }
        return nbttaglist;
    }

    public static NBTTagCompound NBTMerge(NBTTagCompound data, NBTTagCompound merge) {
        NBTTagCompound compound = (NBTTagCompound)data.func_74737_b();
        Set names = merge.func_150296_c();
        for (String name : names) {
            NBTBase base = merge.func_74781_a(name);
            if (base.func_74732_a() == 10) {
                base = NBTTags.NBTMerge(compound.func_74775_l(name), (NBTTagCompound)base);
            }
            compound.func_74782_a(name, base);
        }
        return compound;
    }

    public static TreeMap<Long, String> GetLongStringMap(NBTTagList tagList) {
        TreeMap<Long, String> list = new TreeMap<Long, String>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            list.put(nbttagcompound.func_74763_f("Long"), nbttagcompound.func_74779_i("String"));
        }
        return list;
    }

    public static NBTTagList NBTLongStringMap(Map<Long, String> map) {
        NBTTagList nbttaglist = new NBTTagList();
        if (map == null) {
            return nbttaglist;
        }
        for (long slot : map.keySet()) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74772_a("Long", slot);
            nbttagcompound.func_74778_a("String", map.get(slot));
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    public static List<IScriptUnit> GetScriptOld(NBTTagList list, IScriptHandler handler) {
        ArrayList<IScriptUnit> scripts = new ArrayList<IScriptUnit>();
        for (int i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound compoundd = list.func_150305_b(i);
            IScriptUnit script = IScriptUnit.createFromNBT(compoundd, handler);
            scripts.add(script);
        }
        return scripts;
    }

    public static List<IScriptUnit> GetScript(NBTTagCompound compound, IScriptHandler handler) {
        ArrayList<IScriptUnit> scripts = new ArrayList<IScriptUnit>();
        for (int i = 0; i < compound.func_74762_e("TotalScripts"); ++i) {
            NBTTagCompound containerCompound = compound.func_74775_l("Tab" + i);
            IScriptUnit script = IScriptUnit.createFromNBT(containerCompound, handler);
            scripts.add(script);
        }
        return scripts;
    }

    public static NBTTagList NBTScript(List<IScriptUnit> scripts) {
        NBTTagList list = new NBTTagList();
        for (IScriptUnit script : scripts) {
            NBTTagCompound compound = new NBTTagCompound();
            script.writeToNBT(compound);
            list.func_74742_a((NBTBase)compound);
        }
        return list;
    }

    public static int getIntAt(NBTTagList tagList, int index) {
        NBTTagCompound nbtbase;
        if (index >= 0 && index < tagList.func_74745_c() && (nbtbase = tagList.func_150305_b(index)).func_74732_a() == 3) {
            return ((NBTTagInt)nbtbase).func_150287_d();
        }
        return 0;
    }
}

