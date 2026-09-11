/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.type;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Deque;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassIndex {
    private static ClassIndex instance;
    private final Map<String, List<Class<?>>> simpleNameToClasses = new LinkedHashMap();
    private final Set<Class<?>> registeredClasses = new HashSet();

    private ClassIndex() {
        this.initializeCommonClasses();
    }

    public static ClassIndex getInstance() {
        if (instance == null) {
            instance = new ClassIndex();
        }
        return instance;
    }

    public static void init() {
        ClassIndex.getInstance();
    }

    public void addClass(Class<?> clazz) {
        if (clazz == null) {
            return;
        }
        if (this.registeredClasses.contains(clazz)) {
            return;
        }
        this.registeredClasses.add(clazz);
        String simpleName = clazz.getSimpleName();
        if (!simpleName.isEmpty() && !simpleName.contains("$")) {
            this.simpleNameToClasses.computeIfAbsent(simpleName, k -> new ArrayList()).add(clazz);
        }
    }

    public void addPackage(String packageName) {
        this.addPackage(packageName, new String[0]);
    }

    public void addPackage(String packageName, String ... excludedPackages) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = packageName.replace('.', '/');
            Enumeration<URL> resources = classLoader.getResources(path);
            HashSet<String> classNames = new HashSet<String>();
            HashSet<String> excludedPrefixes = new HashSet<String>();
            for (String excluded : excludedPackages) {
                if (excluded == null || excluded.isEmpty()) continue;
                excludedPrefixes.add(excluded + ".");
            }
            while (resources.hasMoreElements()) {
                int separatorIndex;
                URL resource = resources.nextElement();
                if (resource.getProtocol().equals("file")) {
                    File directory = new File(resource.getFile());
                    this.scanDirectory(directory, packageName, classNames, excludedPrefixes);
                    continue;
                }
                if (!resource.getProtocol().equals("jar")) continue;
                String jarPath = resource.getPath();
                if (jarPath.startsWith("file:")) {
                    jarPath = jarPath.substring(5);
                }
                if ((separatorIndex = jarPath.indexOf("!")) != -1) {
                    jarPath = jarPath.substring(0, separatorIndex);
                }
                this.scanJar(jarPath, packageName, classNames, excludedPrefixes);
            }
            for (String className : classNames) {
                try {
                    Class<?> clazz = Class.forName(className, false, classLoader);
                    this.addClass(clazz);
                }
                catch (ClassNotFoundException | ExceptionInInitializerError | NoClassDefFoundError throwable) {}
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void scanDirectory(File directory, String packageName, Set<String> classNames) {
        this.scanDirectory(directory, packageName, classNames, new HashSet<String>());
    }

    private void scanDirectory(File directory, String packageName, Set<String> classNames, Set<String> excludedPrefixes) {
        if (!directory.exists() || !directory.isDirectory()) {
            return;
        }
        for (String excludedPrefix : excludedPrefixes) {
            if (!(packageName + ".").startsWith(excludedPrefix) && !packageName.equals(excludedPrefix.substring(0, excludedPrefix.length() - 1))) continue;
            return;
        }
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            String fileName = file.getName();
            if (file.isDirectory()) {
                this.scanDirectory(file, packageName + "." + fileName, classNames, excludedPrefixes);
                continue;
            }
            if (!fileName.endsWith(".class")) continue;
            String className = packageName + "." + fileName.substring(0, fileName.length() - 6);
            classNames.add(className);
        }
    }

    private void scanJar(String jarPath, String packageName, Set<String> classNames) {
        this.scanJar(jarPath, packageName, classNames, new HashSet<String>());
    }

    private void scanJar(String jarPath, String packageName, Set<String> classNames, Set<String> excludedPrefixes) {
        try {
            JarFile jarFile = new JarFile(jarPath);
            Enumeration<JarEntry> entries = jarFile.entries();
            String packagePath = packageName.replace('.', '/');
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();
                if (!entryName.startsWith(packagePath) || !entryName.endsWith(".class")) continue;
                String className = entryName.substring(0, entryName.length() - 6).replace('/', '.');
                boolean excluded = false;
                for (String excludedPrefix : excludedPrefixes) {
                    if (!(className + ".").startsWith(excludedPrefix) && !className.startsWith(excludedPrefix.substring(0, excludedPrefix.length() - 1) + ".")) continue;
                    excluded = true;
                    break;
                }
                if (excluded) continue;
                classNames.add(className);
            }
            jarFile.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public List<String> findByPrefix(String prefix, int maxResults) {
        ArrayList<String> results = new ArrayList<String>();
        String lowerPrefix = prefix.toLowerCase();
        for (Map.Entry<String, List<Class<?>>> entry : this.simpleNameToClasses.entrySet()) {
            if (!entry.getKey().toLowerCase().startsWith(lowerPrefix)) continue;
            for (Class<?> clazz : entry.getValue()) {
                results.add(clazz.getName());
                if (maxResults == -1 || results.size() < maxResults) continue;
                return results;
            }
        }
        return results;
    }

    private void initializeCommonClasses() {
        this.addClass(String.class);
        this.addClass(Integer.class);
        this.addClass(Double.class);
        this.addClass(Float.class);
        this.addClass(Long.class);
        this.addClass(Boolean.class);
        this.addClass(Character.class);
        this.addClass(Byte.class);
        this.addClass(Short.class);
        this.addClass(Object.class);
        this.addClass(Math.class);
        this.addClass(System.class);
        this.addClass(Thread.class);
        this.addClass(Runnable.class);
        this.addClass(Exception.class);
        this.addClass(RuntimeException.class);
        this.addClass(Error.class);
        this.addClass(Throwable.class);
        this.addClass(List.class);
        this.addClass(ArrayList.class);
        this.addClass(LinkedList.class);
        this.addClass(Set.class);
        this.addClass(HashSet.class);
        this.addClass(LinkedHashSet.class);
        this.addClass(TreeSet.class);
        this.addClass(Map.class);
        this.addClass(HashMap.class);
        this.addClass(LinkedHashMap.class);
        this.addClass(TreeMap.class);
        this.addClass(Collection.class);
        this.addClass(Iterator.class);
        this.addClass(Comparator.class);
        this.addClass(Collections.class);
        this.addClass(Arrays.class);
        this.addClass(Queue.class);
        this.addClass(Deque.class);
        this.addClass(Stack.class);
        this.addClass(Vector.class);
        this.addClass(Hashtable.class);
        this.addClass(File.class);
        this.addClass(InputStream.class);
        this.addClass(OutputStream.class);
        this.addClass(Reader.class);
        this.addClass(Writer.class);
        this.addClass(BufferedReader.class);
        this.addClass(BufferedWriter.class);
        this.addClass(FileReader.class);
        this.addClass(FileWriter.class);
        this.addClass(IOException.class);
        this.addClass(Random.class);
        this.addClass(Date.class);
        this.addClass(Calendar.class);
        this.addClass(UUID.class);
        this.addClass(Pattern.class);
        this.addClass(Matcher.class);
        this.addPackage("java.util.function");
        this.addPackage("net.minecraft.entity");
        this.addPackage("net.minecraft.item");
        this.addPackage("net.minecraft.block");
        this.addPackage("net.minecraft.world");
        this.addPackage("net.minecraft.util");
        this.addPackage("net.minecraft.nbt");
        this.addPackage("net.minecraft.potion");
        this.addPackage("net.minecraft.enchantment");
        this.addPackage("net.minecraft.inventory");
        this.addPackage("net.minecraft.tileentity");
        this.addPackage("net.minecraft.command");
        this.addPackage("net.minecraft.client");
        this.addPackage("net.minecraftforge.common");
        this.addPackage("net.minecraftforge.event");
        this.addPackage("net.minecraftforge.fml.common");
        this.addPackage("noppes.npcs");
        try {
            this.addPackage("JinRyuu.JRMCore");
            this.addPackage("JinRyuu.DragonBC");
            this.addPackage("kamkeel.npcdbc");
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

