/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PackageFinder {
    private static volatile PackageFinder INSTANCE = null;
    private final Set<String> packages = new HashSet<String>();
    private final ClassLoader classLoader;

    private PackageFinder(ClassLoader cl) throws IOException {
        this.classLoader = cl == null ? Thread.currentThread().getContextClassLoader() : cl;
        this.buildIndex();
    }

    private void buildIndex() throws IOException {
        this.packages.clear();
        ClassLoader cl = this.classLoader;
        if (cl instanceof URLClassLoader) {
            URL[] urls;
            for (URL url : urls = ((URLClassLoader)cl).getURLs()) {
                try {
                    File jf;
                    String jarPath;
                    String protocol = url.getProtocol();
                    if ("file".equals(protocol)) {
                        File f;
                        try {
                            URI u = url.toURI();
                            f = new File(u);
                        }
                        catch (Exception ex) {
                            f = new File(url.getPath());
                        }
                        if (!f.exists()) continue;
                        if (f.isDirectory()) {
                            this.scanDirectory(f);
                            continue;
                        }
                        if (!f.isFile() || !f.getName().endsWith(".jar")) continue;
                        this.scanJar(f);
                        continue;
                    }
                    if (!"jar".equals(protocol)) continue;
                    String s = url.getPath();
                    int excl = s.indexOf(33);
                    String string = jarPath = excl >= 0 ? s.substring(0, excl) : s;
                    if (jarPath.startsWith("file:")) {
                        jarPath = jarPath.substring(5);
                    }
                    if (!(jf = new File(jarPath)).exists()) continue;
                    this.scanJar(jf);
                }
                catch (IOException protocol) {
                    // empty catch block
                }
            }
        } else {
            String[] entries;
            String cp = System.getProperty("java.class.path", "");
            for (String e : entries = cp.split(File.pathSeparator)) {
                File f;
                if (e == null || e.isEmpty() || !(f = new File(e)).exists()) continue;
                if (f.isDirectory()) {
                    this.scanDirectory(f);
                    continue;
                }
                if (!f.isFile() || !f.getName().endsWith(".jar")) continue;
                this.scanJar(f);
            }
        }
    }

    private void scanJar(File jarFile) throws IOException {
        try (JarFile jf = new JarFile(jarFile);){
            Enumeration<JarEntry> en = jf.entries();
            while (en.hasMoreElements()) {
                int idx;
                JarEntry je = en.nextElement();
                String name = je.getName();
                if (!name.endsWith(".class") || (idx = name.lastIndexOf(47)) <= 0) continue;
                String pkg = name.substring(0, idx).replace('/', '.');
                this.addPackageHierarchy(pkg);
            }
        }
    }

    private void scanDirectory(File dir) throws IOException {
        Path root = dir.toPath();
        if (!Files.exists(root, new LinkOption[0])) {
            return;
        }
        Files.walk(root, new FileVisitOption[0]).forEach(p -> {
            try {
                if (Files.isRegularFile(p, new LinkOption[0]) && p.toString().endsWith(".class")) {
                    Path parent = p.getParent();
                    if (parent == null) {
                        return;
                    }
                    Path rel = root.relativize(parent);
                    String rp = rel.toString();
                    if (rp.isEmpty()) {
                        return;
                    }
                    String pkg = rp.replace(File.separatorChar, '.');
                    this.addPackageHierarchy(pkg);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        });
    }

    private void addPackageHierarchy(String pkg) {
        if (pkg == null || pkg.isEmpty()) {
            return;
        }
        String cur = pkg;
        while (true) {
            int idx;
            if (!cur.isEmpty()) {
                this.packages.add(cur);
            }
            if ((idx = cur.lastIndexOf(46)) < 0) break;
            cur = cur.substring(0, idx);
        }
    }

    public boolean contains(String pkg) {
        if (pkg == null || pkg.isEmpty()) {
            return false;
        }
        return this.packages.contains(pkg);
    }

    public static boolean find(String pkg) {
        PackageFinder pf = PackageFinder.getInstance();
        if (pf != null) {
            return pf.contains(pkg);
        }
        return false;
    }

    public static synchronized void init(ClassLoader cl) throws IOException {
        if (INSTANCE == null) {
            INSTANCE = new PackageFinder(cl);
        } else if (cl != null) {
            INSTANCE = new PackageFinder(cl);
        } else {
            INSTANCE.buildIndex();
        }
    }

    public static PackageFinder getInstance() {
        if (INSTANCE == null) {
            try {
                INSTANCE = PackageFinder.fromContext();
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        return INSTANCE;
    }

    public static PackageFinder fromContext() throws IOException {
        return new PackageFinder(Thread.currentThread().getContextClassLoader());
    }

    public synchronized void reload() throws IOException {
        this.buildIndex();
    }
}

