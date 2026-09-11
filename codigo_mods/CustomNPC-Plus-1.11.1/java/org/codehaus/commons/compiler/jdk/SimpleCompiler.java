/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.commons.compiler.jdk;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.tools.JavaCompiler;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.Cookable;
import org.codehaus.commons.compiler.ErrorHandler;
import org.codehaus.commons.compiler.ISimpleCompiler;
import org.codehaus.commons.compiler.Location;
import org.codehaus.commons.compiler.WarningHandler;
import org.codehaus.commons.compiler.io.Readers;
import org.codehaus.commons.compiler.jdk.Compiler;
import org.codehaus.commons.compiler.util.LineAndColumnTracker;
import org.codehaus.commons.compiler.util.reflect.ByteArrayClassLoader;
import org.codehaus.commons.compiler.util.resource.MapResourceCreator;
import org.codehaus.commons.compiler.util.resource.MapResourceFinder;
import org.codehaus.commons.compiler.util.resource.MultiResourceFinder;
import org.codehaus.commons.compiler.util.resource.Resource;
import org.codehaus.commons.compiler.util.resource.ResourceFinders;
import org.codehaus.commons.compiler.util.resource.StringResource;
import org.codehaus.commons.nullanalysis.NotNullByDefault;
import org.codehaus.commons.nullanalysis.Nullable;

public class SimpleCompiler
extends Cookable
implements ISimpleCompiler {
    private ClassLoader parentClassLoader = Thread.currentThread().getContextClassLoader();
    private final Compiler compiler;
    @Nullable
    private Map<String, byte[]> bytecodes;
    private final LineAndColumnTracker tracker = LineAndColumnTracker.create();
    private final SortedSet<Location> offsets = new TreeSet<Location>(new Comparator<Location>(){

        @Override
        @NotNullByDefault(value=false)
        public int compare(Location l1, Location l2) {
            return l1.getLineNumber() < l2.getLineNumber() ? -1 : (l1.getLineNumber() > l2.getLineNumber() ? 1 : l1.getColumnNumber() - l2.getColumnNumber());
        }
    });
    @Nullable
    private ClassLoader getClassLoaderCache;

    public SimpleCompiler() {
        this.compiler = new Compiler();
    }

    public SimpleCompiler(JavaCompiler javaCompiler) {
        this.compiler = new Compiler(javaCompiler);
    }

    @Override
    public void setSourceVersion(int version) {
        this.compiler.setSourceVersion(version);
    }

    @Override
    public void setTargetVersion(int version) {
        this.compiler.setTargetVersion(version);
    }

    @Override
    public Map<String, byte[]> getBytecodes() {
        return this.assertCooked();
    }

    private Map<String, byte[]> assertCooked() {
        Map<String, byte[]> result = this.bytecodes;
        if (result == null) {
            throw new IllegalStateException("Must only be called after \"cook()\"");
        }
        return result;
    }

    @Override
    public ClassLoader getClassLoader() {
        ClassLoader result = this.getClassLoaderCache;
        if (result != null) {
            return result;
        }
        final Map<String, byte[]> bytecode = this.getBytecodes();
        this.getClassLoaderCache = result = AccessController.doPrivileged(new PrivilegedAction<ClassLoader>(){

            @Override
            public ClassLoader run() {
                return new ByteArrayClassLoader(bytecode, SimpleCompiler.this.parentClassLoader);
            }
        });
        return this.getClassLoaderCache;
    }

    @Override
    public void cook(@Nullable String fileName, Reader r) throws CompileException, IOException {
        this.tracker.reset();
        this.offsets.clear();
        r = Readers.trackLineAndColumn(r, this.tracker);
        String text = Readers.readAll(r);
        StringResource compilationUnit = new StringResource(fileName == null ? "simplecompiler" : fileName, text);
        this.compiler.setClassPath(new File[0]);
        this.bytecodes = new HashMap<String, byte[]>();
        HashMap<String, byte[]> bcs = this.bytecodes;
        this.compiler.setClassFileFinder(new MultiResourceFinder(ResourceFinders.fromClassLoader(this.parentClassLoader), new MapResourceFinder(bcs)));
        this.compiler.setClassFileCreator(new MapResourceCreator(bcs));
        this.compiler.compile(new Resource[]{compilationUnit}, this.offsets);
    }

    @Override
    public void setDebuggingInformation(boolean debugSource, boolean debugLines, boolean debugVars) {
        this.compiler.setDebugSource(debugSource);
        this.compiler.setDebugLines(debugLines);
        this.compiler.setDebugVars(debugVars);
    }

    @Override
    public void setParentClassLoader(@Nullable ClassLoader parentClassLoader) {
        this.parentClassLoader = parentClassLoader != null ? parentClassLoader : Thread.currentThread().getContextClassLoader();
    }

    @Deprecated
    public void setParentClassLoader(@Nullable ClassLoader parentClassLoader, Class<?>[] auxiliaryClasses) {
        this.setParentClassLoader(parentClassLoader);
    }

    @Override
    public void setCompileErrorHandler(@Nullable ErrorHandler compileErrorHandler) {
        this.compiler.setCompileErrorHandler(compileErrorHandler);
    }

    @Override
    public void setWarningHandler(@Nullable WarningHandler warningHandler) {
        this.compiler.setWarningHandler(warningHandler);
    }

    protected void addOffset(@Nullable String fileName) {
        LineAndColumnTracker t = this.tracker;
        assert (t != null);
        this.offsets.add(new Location(fileName, t.getLineNumber(), t.getColumnNumber()));
    }
}

