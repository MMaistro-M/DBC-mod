/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import noppes.npcs.client.gui.util.script.PackageFinder;

public class ClassPathFinder {
    private final Map<String, ClassInfo> classCache = new HashMap<String, ClassInfo>();
    private final Set<String> validPackages = new HashSet<String>();
    public static final Set<String> JAVA_LANG_CLASSES = new HashSet<String>(Arrays.asList("Object", "String", "Class", "System", "Math", "Integer", "Double", "Float", "Long", "Short", "Byte", "Character", "Boolean", "Number", "Void", "Thread", "Runnable", "Exception", "RuntimeException", "Error", "Throwable", "StringBuilder", "StringBuffer", "Enum", "Comparable", "Iterable", "CharSequence", "Cloneable", "Process", "ProcessBuilder", "Runtime", "SecurityManager", "ClassLoader", "Package", "ArithmeticException", "ArrayIndexOutOfBoundsException", "ClassCastException", "IllegalArgumentException", "IllegalStateException", "IndexOutOfBoundsException", "NullPointerException", "NumberFormatException", "UnsupportedOperationException", "AssertionError", "OutOfMemoryError", "StackOverflowError"));

    public void clearCache() {
        this.classCache.clear();
        this.validPackages.clear();
    }

    public ResolveResult resolve(String importPath) {
        String pathToResolve;
        if (importPath == null || importPath.isEmpty()) {
            return ResolveResult.notFound("", "", -1, 0);
        }
        String normalizedPath = importPath.replaceAll("\\s*\\.\\s*", ".").trim();
        boolean trailingDot = normalizedPath.endsWith(".");
        String string = pathToResolve = trailingDot ? normalizedPath.substring(0, normalizedPath.length() - 1) : normalizedPath;
        if (pathToResolve.isEmpty()) {
            return ResolveResult.notFound("", "", -1, 0);
        }
        String[] segments = pathToResolve.split("\\.");
        int n = segments.length;
        int firstUpperIndex = this.findFirstUppercaseSegment(segments);
        if (firstUpperIndex == n) {
            int longestValid = this.findLongestValidPackagePrefixIndex(segments);
            if (longestValid == n) {
                return ResolveResult.notFound(pathToResolve, "", -1, n);
            }
            if (longestValid > 0) {
                String validPrefix = this.buildPackagePortion(segments, longestValid);
                String invalidRemainder = this.buildClassPortion(segments, longestValid, n);
                int invalidOffset = validPrefix.length() + 1;
                return ResolveResult.notFound(validPrefix, invalidRemainder, invalidOffset, longestValid);
            }
            return ResolveResult.notFound("", pathToResolve, 0, 0);
        }
        for (int classStartIdx = firstUpperIndex; classStartIdx < n; ++classStartIdx) {
            for (int classEndIdx = n; classEndIdx > classStartIdx; --classEndIdx) {
                String candidate = this.buildClassName(segments, classStartIdx, classEndIdx);
                ClassInfo info = this.tryResolveClass(candidate);
                if (info == null) continue;
                String packagePortion = this.buildPackagePortion(segments, classStartIdx);
                String validClassPortion = this.buildClassPortion(segments, classStartIdx, classEndIdx);
                if (!packagePortion.isEmpty()) {
                    this.registerValidPackage(packagePortion);
                }
                if (classEndIdx < n) {
                    List<ClassSegment> classSegments = this.buildClassSegments(segments, classStartIdx, classEndIdx);
                    String fullClassPortion = this.buildClassPortion(segments, classStartIdx, n);
                    int validEndOffset = packagePortion.length() + (packagePortion.isEmpty() ? 0 : 1) + validClassPortion.length() + 1;
                    return new ResolveResult(false, info, packagePortion, fullClassPortion, classSegments, validEndOffset, classStartIdx);
                }
                List<ClassSegment> classSegments = this.buildClassSegments(segments, classStartIdx, n);
                return ResolveResult.found(new ClassInfo(info.resolvedName, info.type, packagePortion.length()), packagePortion, validClassPortion, classSegments, classStartIdx);
            }
        }
        if (firstUpperIndex > 0 && firstUpperIndex < n) {
            String packagePortion = this.buildPackagePortion(segments, firstUpperIndex);
            String classPortion = this.buildClassPortion(segments, firstUpperIndex, n);
            int typoIndex = this.findPackageTypoIndex(segments, firstUpperIndex);
            if (typoIndex >= 0 && typoIndex < firstUpperIndex) {
                String validPrefix = this.buildPackagePortion(segments, typoIndex);
                int invalidOffset = validPrefix.isEmpty() ? 0 : validPrefix.length() + 1;
                return ResolveResult.notFound(validPrefix, this.buildClassPortion(segments, typoIndex, n), invalidOffset, typoIndex);
            }
            int invalidOffset = packagePortion.length() + 1;
            return ResolveResult.notFound(packagePortion, classPortion, invalidOffset, firstUpperIndex);
        }
        if (firstUpperIndex == 0) {
            return ResolveResult.notFound("", pathToResolve, 0, 0);
        }
        return ResolveResult.notFound(pathToResolve, "", -1, n);
    }

    private int findPackageTypoIndex(String[] segments, int classStartIdx) {
        StringBuilder sb = new StringBuilder();
        int lastValidIndex = -1;
        for (int i = 0; i < classStartIdx; ++i) {
            String parentPath;
            if (i > 0) {
                sb.append(".");
            }
            sb.append(segments[i]);
            String currentPath = sb.toString();
            if (this.validPackages.contains(currentPath)) {
                lastValidIndex = i + 1;
                continue;
            }
            if (i <= 0 || !this.validPackages.contains(parentPath = this.buildPackagePortion(segments, i))) continue;
            boolean hasAlternative = false;
            for (String cached : this.validPackages) {
                if (!cached.startsWith(parentPath + ".") || cached.length() <= parentPath.length() + 1) continue;
                hasAlternative = true;
                break;
            }
            if (!hasAlternative) continue;
            return i;
        }
        if (lastValidIndex > 0 && lastValidIndex < classStartIdx) {
            String validPrefix = this.buildPackagePortion(segments, lastValidIndex);
            for (String cached : this.validPackages) {
                String cachedRemainder;
                String nextCached;
                String nextTyped;
                if (!cached.startsWith(validPrefix + ".") || (nextTyped = segments[lastValidIndex]).equals(nextCached = (cachedRemainder = cached.substring(validPrefix.length() + 1)).contains(".") ? cachedRemainder.substring(0, cachedRemainder.indexOf(46)) : cachedRemainder)) continue;
                return lastValidIndex;
            }
        }
        return -1;
    }

    public boolean isValidPackage(String packagePath) {
        String[] testClasses;
        if (packagePath == null || packagePath.isEmpty()) {
            return false;
        }
        if (this.validPackages.contains(packagePath)) {
            return true;
        }
        if (PackageFinder.find(packagePath)) {
            this.registerValidPackage(packagePath);
            return true;
        }
        for (String testClass : testClasses = this.getTestClassesForPackage(packagePath)) {
            try {
                Class.forName(testClass);
                this.registerValidPackage(packagePath);
                return true;
            }
            catch (ClassNotFoundException | LinkageError throwable) {
            }
        }
        return false;
    }

    public ClassInfo resolveSimpleName(String simpleName, Map<String, String> importedClasses) {
        return this.resolveSimpleName(simpleName, importedClasses, Collections.emptySet());
    }

    public ClassInfo resolveSimpleName(String simpleName, Map<String, String> importedClasses, Set<String> importedPackages) {
        ClassInfo info;
        if (simpleName == null || simpleName.isEmpty()) {
            return null;
        }
        String fullName = importedClasses.get(simpleName);
        if (fullName != null && (info = this.tryResolveClass(fullName)) != null) {
            return info;
        }
        if (JAVA_LANG_CLASSES.contains(simpleName) && (info = this.tryResolveClass("java.lang." + simpleName)) != null) {
            return info;
        }
        if (importedPackages != null && !importedPackages.isEmpty()) {
            for (String p : importedPackages) {
                ClassInfo info2;
                boolean lastIsClass;
                if (p == null || p.isEmpty()) continue;
                String[] segs = p.split("\\.");
                String last = segs[segs.length - 1];
                boolean bl = lastIsClass = last.length() > 0 && this.tryResolveClass(p) != null;
                if (!lastIsClass) {
                    String candidate = p + "." + simpleName;
                    info2 = this.tryResolveClass(candidate);
                    if (info2 == null) continue;
                    return info2;
                }
                String candidateInner = p + "$" + simpleName;
                info2 = this.tryResolveClass(candidateInner);
                if (info2 != null) {
                    return info2;
                }
                String candidatePkg = p + "." + simpleName;
                info2 = this.tryResolveClass(candidatePkg);
                if (info2 == null) continue;
                return info2;
            }
        }
        return null;
    }

    private ClassInfo tryResolveClass(String className) {
        if (className == null || className.isEmpty()) {
            return null;
        }
        if (this.classCache.containsKey(className)) {
            return this.classCache.get(className);
        }
        ClassInfo result = null;
        try {
            Class<?> clazz = Class.forName(className);
            result = this.createClassInfo(className, clazz);
            this.classCache.put(className, result);
            int lastDot = className.lastIndexOf(".");
            if (lastDot > 0) {
                this.registerValidPackage(className.substring(0, lastDot));
            }
        }
        catch (ClassNotFoundException | NoClassDefFoundError throwable) {
        }
        catch (LinkageError linkageError) {
            // empty catch block
        }
        return result;
    }

    public ClassInfo tryResolveClassName(String className) {
        return this.tryResolveClass(className);
    }

    private ClassInfo createClassInfo(String resolvedName, Class<?> clazz) {
        ClassType type = clazz.isInterface() ? ClassType.INTERFACE : (clazz.isEnum() ? ClassType.ENUM : ClassType.CLASS);
        int pkgEnd = resolvedName.lastIndexOf(46);
        if (pkgEnd < 0) {
            pkgEnd = 0;
        }
        return new ClassInfo(resolvedName, type, pkgEnd);
    }

    private String buildClassName(String[] segments, int classStartIdx, int classEndIdx) {
        int i;
        StringBuilder sb = new StringBuilder();
        for (i = 0; i < classStartIdx; ++i) {
            if (i > 0) {
                sb.append('.');
            }
            sb.append(segments[i]);
        }
        for (i = classStartIdx; i < classEndIdx; ++i) {
            if (i == classStartIdx) {
                if (sb.length() > 0) {
                    sb.append('.');
                }
            } else {
                sb.append('$');
            }
            sb.append(segments[i]);
        }
        return sb.toString();
    }

    private String buildPackagePortion(String[] segments, int classStartIdx) {
        if (classStartIdx <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < classStartIdx; ++i) {
            if (i > 0) {
                sb.append('.');
            }
            sb.append(segments[i]);
        }
        return sb.toString();
    }

    private String buildClassPortion(String[] segments, int classStartIdx, int endIdx) {
        StringBuilder sb = new StringBuilder();
        for (int i = classStartIdx; i < endIdx; ++i) {
            if (i > classStartIdx) {
                sb.append('.');
            }
            sb.append(segments[i]);
        }
        return sb.toString();
    }

    private List<ClassSegment> buildClassSegments(String[] segments, int classStartIdx, int endIdx) {
        int i;
        ArrayList<ClassSegment> result = new ArrayList<ClassSegment>();
        StringBuilder fullName = new StringBuilder();
        for (i = 0; i < classStartIdx; ++i) {
            if (i > 0) {
                fullName.append('.');
            }
            fullName.append(segments[i]);
        }
        for (i = classStartIdx; i < endIdx; ++i) {
            if (i == classStartIdx) {
                if (fullName.length() > 0) {
                    fullName.append('.');
                }
            } else {
                fullName.append('$');
            }
            fullName.append(segments[i]);
            ClassInfo info = this.tryResolveClass(fullName.toString());
            ClassType type = info != null ? info.type : ClassType.CLASS;
            result.add(new ClassSegment(segments[i], type));
        }
        return result;
    }

    private int findFirstUppercaseSegment(String[] segments) {
        for (int i = 0; i < segments.length; ++i) {
            if (segments[i].length() <= 0 || !Character.isUpperCase(segments[i].charAt(0))) continue;
            return i;
        }
        return segments.length;
    }

    private int findLongestValidPackagePrefixIndex(String[] segments) {
        String pkg;
        int longestValid = 0;
        int i = 1;
        while (i <= segments.length && this.isValidPackage(pkg = this.buildPackagePortion(segments, i))) {
            longestValid = i++;
        }
        return longestValid;
    }

    private void registerValidPackage(String packagePath) {
        int lastDot;
        this.validPackages.add(packagePath);
        String current = packagePath;
        while ((lastDot = current.lastIndexOf(46)) > 0) {
            current = current.substring(0, lastDot);
            this.validPackages.add(current);
        }
    }

    private String[] getTestClassesForPackage(String packagePath) {
        switch (packagePath) {
            case "java": {
                return new String[]{"java.lang.Object"};
            }
            case "java.util": {
                return new String[]{"java.util.List", "java.util.Map", "java.util.Set"};
            }
            case "java.io": {
                return new String[]{"java.io.File", "java.io.InputStream"};
            }
            case "java.net": {
                return new String[]{"java.net.URL", "java.net.Socket"};
            }
            case "java.lang": {
                return new String[]{"java.lang.Object", "java.lang.String"};
            }
        }
        return new String[]{packagePath + ".package-info"};
    }

    public List<TypeOccurrence> parseGenericTypes(String genericContent, Map<String, String> importedClasses) {
        return this.parseGenericTypes(genericContent, importedClasses, Collections.emptySet());
    }

    public List<TypeOccurrence> parseGenericTypes(String genericContent, Map<String, String> importedClasses, Set<String> importedPackages) {
        ArrayList<TypeOccurrence> results = new ArrayList<TypeOccurrence>();
        this.parseGenericTypesRecursive(genericContent, 0, importedClasses, importedPackages, results);
        return results;
    }

    private void parseGenericTypesRecursive(String content, int baseOffset, Map<String, String> importedClasses, Set<String> importedPackages, List<TypeOccurrence> results) {
        if (content == null || content.isEmpty()) {
            return;
        }
        int i = 0;
        while (i < content.length()) {
            char c = content.charAt(i);
            if (!Character.isJavaIdentifierStart(c)) {
                ++i;
                continue;
            }
            int start = i;
            while (i < content.length() && Character.isJavaIdentifierPart(content.charAt(i))) {
                ++i;
            }
            String typeName = content.substring(start, i);
            ClassInfo info = this.resolveSimpleName(typeName, importedClasses, importedPackages);
            if (info != null) {
                ClassType classType = info != null ? info.type : ClassType.CLASS;
                results.add(new TypeOccurrence(baseOffset + start, baseOffset + i, typeName, classType));
            }
            while (i < content.length() && Character.isWhitespace(content.charAt(i))) {
                ++i;
            }
            if (i >= content.length() || content.charAt(i) != '<') continue;
            int nestedStart = i + 1;
            int depth = 1;
            ++i;
            while (i < content.length() && depth > 0) {
                if (content.charAt(i) == '<') {
                    ++depth;
                } else if (content.charAt(i) == '>') {
                    --depth;
                }
                ++i;
            }
            if (nestedStart >= i - 1) continue;
            String nestedContent = content.substring(nestedStart, i - 1);
            this.parseGenericTypesRecursive(nestedContent, baseOffset + nestedStart, importedClasses, importedPackages, results);
        }
    }

    public static class TypeOccurrence {
        public final int startOffset;
        public final int endOffset;
        public final String typeName;
        public final ClassType type;

        public TypeOccurrence(int startOffset, int endOffset, String typeName, ClassType type) {
            this.startOffset = startOffset;
            this.endOffset = endOffset;
            this.typeName = typeName;
            this.type = type;
        }
    }

    public static class ClassSegment {
        public final String name;
        public final ClassType type;

        public ClassSegment(String name, ClassType type) {
            this.name = name;
            this.type = type;
        }
    }

    public static class ResolveResult {
        public final boolean found;
        public final ClassInfo classInfo;
        public final String packagePortion;
        public final String classPortion;
        public final List<ClassSegment> classSegments;
        public final int invalidStartOffset;
        public final int packageSegmentCount;

        private ResolveResult(boolean found, ClassInfo classInfo, String packagePortion, String classPortion, List<ClassSegment> classSegments, int invalidStartOffset, int packageSegmentCount) {
            this.found = found;
            this.classInfo = classInfo;
            this.packagePortion = packagePortion;
            this.classPortion = classPortion;
            this.classSegments = classSegments != null ? classSegments : Collections.emptyList();
            this.invalidStartOffset = invalidStartOffset;
            this.packageSegmentCount = packageSegmentCount;
        }

        public static ResolveResult notFound(String packagePortion, String classPortion, int invalidStartOffset, int packageSegmentCount) {
            return new ResolveResult(false, null, packagePortion, classPortion, null, invalidStartOffset, packageSegmentCount);
        }

        public static ResolveResult found(ClassInfo info, String packagePortion, String classPortion, List<ClassSegment> segments, int packageSegmentCount) {
            return new ResolveResult(true, info, packagePortion, classPortion, segments, -1, packageSegmentCount);
        }
    }

    public static enum ClassType {
        INTERFACE,
        ENUM,
        CLASS;

    }

    public static class ClassInfo {
        public final String resolvedName;
        public final ClassType type;
        public final int packageEndIndex;

        public ClassInfo(String resolvedName, ClassType type, int packageEndIndex) {
            this.resolvedName = resolvedName;
            this.type = type;
            this.packageEndIndex = packageEndIndex;
        }
    }
}

