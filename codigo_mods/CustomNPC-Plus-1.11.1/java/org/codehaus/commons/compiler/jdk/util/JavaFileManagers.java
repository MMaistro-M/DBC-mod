/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.commons.compiler.jdk.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import org.codehaus.commons.compiler.jdk.util.JavaFileObjects;
import org.codehaus.commons.compiler.util.reflect.ApiLog;
import org.codehaus.commons.compiler.util.resource.ListableResourceFinder;
import org.codehaus.commons.compiler.util.resource.Resource;
import org.codehaus.commons.compiler.util.resource.ResourceCreator;
import org.codehaus.commons.compiler.util.resource.ResourceFinder;
import org.codehaus.commons.nullanalysis.NotNullByDefault;

public final class JavaFileManagers {
    private JavaFileManagers() {
    }

    public static <M extends JavaFileManager> ForwardingJavaFileManager<M> fromResourceFinder(final M delegate, final JavaFileManager.Location location, final JavaFileObject.Kind kind, final ResourceFinder resourceFinder, final Charset charset) {
        class ResourceFinderInputJavaFileManager
        extends ForwardingJavaFileManager<M> {
            ResourceFinderInputJavaFileManager() {
                super(javaFileManager);
            }

            @Override
            @NotNullByDefault(value=false)
            public String inferBinaryName(JavaFileManager.Location location2, JavaFileObject jfo) {
                if (!(jfo instanceof JavaFileObjects.ResourceJavaFileObject)) {
                    String result = super.inferBinaryName(location2, jfo);
                    assert (result != null);
                    return result;
                }
                String bn = jfo.getName();
                if (bn.startsWith("/")) {
                    bn = bn.substring(1);
                }
                if (!bn.endsWith(jfo.getKind().extension)) {
                    throw new AssertionError((Object)("Name \"" + jfo.getName() + "\" does not match kind \"" + (Object)((Object)jfo.getKind()) + "\""));
                }
                bn = bn.substring(0, bn.length() - jfo.getKind().extension.length());
                bn = bn.replace('/', '.');
                return bn;
            }

            @Override
            @NotNullByDefault(value=false)
            public boolean hasLocation(JavaFileManager.Location location2) {
                return location2 == location || super.hasLocation(location2);
            }

            @Override
            @NotNullByDefault(value=false)
            public Iterable<JavaFileObject> list(JavaFileManager.Location location2, String packageName, Set<JavaFileObject.Kind> kinds, boolean recurse) throws IOException {
                Iterable<JavaFileObject> delegatesJfos = super.list(location2, packageName, kinds, recurse);
                if (location2 == location && kinds.contains((Object)kind)) {
                    assert (resourceFinder instanceof ListableResourceFinder) : resourceFinder;
                    ListableResourceFinder lrf = (ListableResourceFinder)resourceFinder;
                    Iterable<Resource> resources = lrf.list(packageName.replace('.', '/') + "/", recurse);
                    if (resources != null) {
                        ArrayList<JavaFileObject> result = new ArrayList<JavaFileObject>();
                        for (Resource r : resources) {
                            String className = r.getFileName();
                            if (!className.endsWith(kind.extension)) continue;
                            className = className.substring(0, className.length() - kind.extension.length());
                            className = className.replace(File.separatorChar, '.');
                            className = className.replace('/', '.');
                            int idx = className.lastIndexOf(packageName + ".");
                            assert (idx != -1) : className + "//" + packageName;
                            JavaFileObject jfo = this.getJavaFileForInput(location2, className = className.substring(idx), kind);
                            if (jfo == null) continue;
                            result.add(jfo);
                        }
                        for (JavaFileObject jfo : delegatesJfos) {
                            result.add(jfo);
                        }
                        return result;
                    }
                }
                return delegatesJfos;
            }

            @Override
            @NotNullByDefault(value=false)
            public JavaFileObject getJavaFileForInput(JavaFileManager.Location location2, String className, JavaFileObject.Kind kind2) throws IOException {
                assert (location2 != null);
                assert (className != null);
                assert (kind2 != null);
                if (location2 == location && kind2 == kind) {
                    Resource resource = resourceFinder.findResource(className.replace('.', '/') + kind2.extension);
                    if (resource == null) {
                        return null;
                    }
                    JavaFileObject result = JavaFileObjects.fromResource(resource, className, kind, charset);
                    result = (JavaFileObject)ApiLog.logMethodInvocations(result);
                    return result;
                }
                return super.getJavaFileForInput(location2, className, kind2);
            }

            @Override
            @NotNullByDefault(value=false)
            public boolean isSameFile(FileObject a, FileObject b) {
                if (a instanceof JavaFileObjects.ResourceJavaFileObject && b instanceof JavaFileObjects.ResourceJavaFileObject) {
                    return a.getName().contentEquals(b.getName());
                }
                return super.isSameFile(a, b);
            }
        }
        return new ResourceFinderInputJavaFileManager();
    }

    public static <M extends JavaFileManager> ForwardingJavaFileManager<M> fromResourceCreator(M delegate, final JavaFileManager.Location location, final JavaFileObject.Kind kind, final ResourceCreator resourceCreator, final Charset charset) {
        return new ForwardingJavaFileManager<M>(delegate){

            @Override
            @NotNullByDefault(value=false)
            public JavaFileObject getJavaFileForOutput(JavaFileManager.Location location2, String className, JavaFileObject.Kind kind2, FileObject sibling) throws IOException {
                if (kind2 == kind && location2 == location) {
                    String resourceName = className.replace('.', '/') + ".class";
                    return JavaFileObjects.fromResourceCreator(resourceCreator, resourceName, kind, charset);
                }
                return super.getJavaFileForOutput(location2, className, kind2, sibling);
            }
        };
    }

    public static <M extends JavaFileManager> ForwardingJavaFileManager<M> inMemory(M delegate, final Charset charset) {
        return new ForwardingJavaFileManager<M>(delegate){
            private final Map<JavaFileManager.Location, Map<JavaFileObject.Kind, Map<String, JavaFileObject>>> javaFiles;
            {
                super(x0);
                this.javaFiles = new HashMap<JavaFileManager.Location, Map<JavaFileObject.Kind, Map<String, JavaFileObject>>>();
            }

            @Override
            @NotNullByDefault(value=false)
            public FileObject getFileForInput(JavaFileManager.Location location, String packageName, String relativeName) {
                throw new UnsupportedOperationException("getFileForInput");
            }

            @Override
            @NotNullByDefault(value=false)
            public FileObject getFileForOutput(JavaFileManager.Location location, String packageName, String relativeName, FileObject sibling) {
                throw new UnsupportedOperationException("getFileForOutput");
            }

            @Override
            @NotNullByDefault(value=false)
            public JavaFileObject getJavaFileForInput(JavaFileManager.Location location, String className, JavaFileObject.Kind kind) throws IOException {
                Map<String, JavaFileObject> kindJavaFiles;
                Map<JavaFileObject.Kind, Map<String, JavaFileObject>> locationJavaFiles = this.javaFiles.get(location);
                if (locationJavaFiles != null && (kindJavaFiles = locationJavaFiles.get((Object)kind)) != null) {
                    return kindJavaFiles.get(className);
                }
                return super.getJavaFileForInput(location, className, kind);
            }

            @Override
            @NotNullByDefault(value=false)
            public JavaFileObject getJavaFileForOutput(JavaFileManager.Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
                Map<String, JavaFileObject> kindJavaFiles;
                Map<JavaFileObject.Kind, Map<String, JavaFileObject>> locationJavaFiles = this.javaFiles.get(location);
                if (locationJavaFiles == null) {
                    locationJavaFiles = new HashMap<JavaFileObject.Kind, Map<String, JavaFileObject>>();
                    this.javaFiles.put(location, locationJavaFiles);
                }
                if ((kindJavaFiles = locationJavaFiles.get((Object)kind)) == null) {
                    kindJavaFiles = new HashMap<String, JavaFileObject>();
                    locationJavaFiles.put(kind, kindJavaFiles);
                }
                JavaFileObjects.ByteArrayJavaFileObject fileObject = JavaFileObjects.inMemory(className, kind, charset);
                kindJavaFiles.put(className, fileObject);
                return fileObject;
            }

            @Override
            @NotNullByDefault(value=false)
            public Iterable<JavaFileObject> list(JavaFileManager.Location location, String packageName, Set<JavaFileObject.Kind> kinds, boolean recurse) throws IOException {
                Map<JavaFileObject.Kind, Map<String, JavaFileObject>> locationFiles = this.javaFiles.get(location);
                if (locationFiles == null) {
                    return super.list(location, packageName, kinds, recurse);
                }
                String prefix = packageName.isEmpty() ? "" : packageName + ".";
                int pl = prefix.length();
                ArrayList<JavaFileObject> result = new ArrayList<JavaFileObject>();
                for (JavaFileObject.Kind kind : kinds) {
                    Map<String, JavaFileObject> kindFiles = locationFiles.get((Object)kind);
                    if (kindFiles == null) continue;
                    for (Map.Entry<String, JavaFileObject> e : kindFiles.entrySet()) {
                        String className = e.getKey();
                        JavaFileObject javaFileObject = e.getValue();
                        if (!className.startsWith(prefix) || !recurse && className.indexOf(46, pl) != -1) continue;
                        result.add(javaFileObject);
                    }
                }
                return result;
            }
        };
    }
}

