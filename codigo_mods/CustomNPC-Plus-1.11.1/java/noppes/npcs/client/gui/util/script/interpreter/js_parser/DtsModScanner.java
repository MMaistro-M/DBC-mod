/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.ModContainer
 */
package noppes.npcs.client.gui.util.script.interpreter.js_parser;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class DtsModScanner {
    private static final Pattern MOD_DTS_PATH_PATTERN = Pattern.compile("^assets/([^/]+)/api/(.+\\.d\\.ts)$");
    private static final List<String> DOMAIN_PRIORITY = Arrays.asList("customnpcs", "npcdbc");

    private DtsModScanner() {
    }

    static List<DtsFileRef> collectDtsFilesFromMods() {
        ArrayList<DtsFileRef> dtsFiles = new ArrayList<DtsFileRef>();
        try {
            for (ModContainer mod : Loader.instance().getModList()) {
                File source = mod.getSource();
                if (source == null || !source.exists()) continue;
                if (source.isDirectory()) {
                    DtsModScanner.scanDirectoryForModDts(source, mod.getModId(), dtsFiles);
                    continue;
                }
                if (!source.getName().endsWith(".jar") && !source.getName().endsWith(".zip")) continue;
                DtsModScanner.scanJarForModDts(source, mod.getModId(), dtsFiles);
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return dtsFiles;
    }

    static void sortDtsFiles(List<DtsFileRef> dtsFiles) {
        Collections.sort(dtsFiles, new DtsFileRefComparator());
    }

    static void logSummary(List<DtsFileRef> dtsFiles) {
        LinkedHashMap<String, Integer> domainCounts = new LinkedHashMap<String, Integer>();
        for (DtsFileRef entry : dtsFiles) {
            domainCounts.put(entry.getDomain(), domainCounts.getOrDefault(entry.getDomain(), 0) + 1);
        }
        for (Map.Entry entry : domainCounts.entrySet()) {
        }
    }

    private static void scanDirectoryForModDts(File baseDir, String modId, List<DtsFileRef> dtsFiles) {
        DtsModScanner.scanDirectoryForModDts(baseDir, baseDir, modId, dtsFiles);
    }

    private static void scanDirectoryForModDts(File baseDir, File directory, String modId, List<DtsFileRef> dtsFiles) {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            String normalized;
            Matcher matcher;
            String relativePath;
            if (file.isDirectory()) {
                DtsModScanner.scanDirectoryForModDts(baseDir, file, modId, dtsFiles);
                continue;
            }
            if (!file.getName().endsWith(".d.ts") || (relativePath = baseDir.toURI().relativize(file.toURI()).getPath()) == null || !(matcher = MOD_DTS_PATH_PATTERN.matcher(normalized = relativePath.replace('\\', '/'))).matches()) continue;
            String domain = matcher.group(1);
            String apiPath = matcher.group(2);
            dtsFiles.add(DtsFileRef.forFile(modId, domain, apiPath, file));
        }
    }

    private static void scanJarForModDts(File jarFile, String modId, List<DtsFileRef> dtsFiles) {
        try {
            JarFile jar = new JarFile(jarFile);
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();
                Matcher matcher = MOD_DTS_PATH_PATTERN.matcher(entryName);
                if (!matcher.matches()) continue;
                String domain = matcher.group(1);
                String apiPath = matcher.group(2);
                dtsFiles.add(DtsFileRef.forJar(modId, domain, apiPath, jarFile, entryName));
            }
            jar.close();
        }
        catch (Exception e) {
            System.err.println("[JSTypeRegistry] Error scanning mod jar for .d.ts files: " + e.getMessage());
        }
    }

    private static class DtsFileRefComparator
    implements Comparator<DtsFileRef> {
        private DtsFileRefComparator() {
        }

        @Override
        public int compare(DtsFileRef a, DtsFileRef b) {
            int domainCompare = Integer.compare(this.getDomainRank(a.getDomain()), this.getDomainRank(b.getDomain()));
            if (domainCompare != 0) {
                return domainCompare;
            }
            int domainNameCompare = a.getDomain().compareTo(b.getDomain());
            if (domainNameCompare != 0) {
                return domainNameCompare;
            }
            int filePriorityCompare = Integer.compare(this.getFilePriority(a.getRelativePath()), this.getFilePriority(b.getRelativePath()));
            if (filePriorityCompare != 0) {
                return filePriorityCompare;
            }
            return a.getRelativePath().compareTo(b.getRelativePath());
        }

        private int getDomainRank(String domain) {
            int idx = DOMAIN_PRIORITY.indexOf(domain);
            return idx >= 0 ? idx : DOMAIN_PRIORITY.size();
        }

        private int getFilePriority(String relativePath) {
            if (relativePath.endsWith("hooks.d.ts")) {
                return 0;
            }
            if (relativePath.endsWith("index.d.ts")) {
                return 1;
            }
            return 2;
        }
    }

    static final class DtsFileRef {
        private final String modId;
        private final String domain;
        private final String relativePath;
        private final File file;
        private final File jarFile;
        private final String jarEntryName;

        private DtsFileRef(String modId, String domain, String relativePath, File file, File jarFile, String jarEntryName) {
            this.modId = modId;
            this.domain = domain;
            this.relativePath = relativePath;
            this.file = file;
            this.jarFile = jarFile;
            this.jarEntryName = jarEntryName;
        }

        static DtsFileRef forFile(String modId, String domain, String relativePath, File file) {
            return new DtsFileRef(modId, domain, relativePath, file, null, null);
        }

        static DtsFileRef forJar(String modId, String domain, String relativePath, File jarFile, String jarEntryName) {
            return new DtsFileRef(modId, domain, relativePath, null, jarFile, jarEntryName);
        }

        InputStream openStream() throws IOException {
            if (this.file != null) {
                return new FileInputStream(this.file);
            }
            if (this.jarFile != null && this.jarEntryName != null) {
                final JarFile jar = new JarFile(this.jarFile);
                JarEntry entry = jar.getJarEntry(this.jarEntryName);
                if (entry == null) {
                    jar.close();
                    return null;
                }
                InputStream is = jar.getInputStream(entry);
                return new FilterInputStream(is){

                    @Override
                    public void close() throws IOException {
                        super.close();
                        jar.close();
                    }
                };
            }
            return null;
        }

        String getDomain() {
            return this.domain;
        }

        String getRelativePath() {
            return this.relativePath;
        }

        String getOrigin() {
            return this.modId + ":" + this.domain + ":" + this.relativePath;
        }
    }
}

