/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.commons.compiler.jdk;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import org.codehaus.commons.compiler.AbstractCompiler;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.ErrorHandler;
import org.codehaus.commons.compiler.Location;
import org.codehaus.commons.compiler.WarningHandler;
import org.codehaus.commons.compiler.jdk.util.JavaFileManagers;
import org.codehaus.commons.compiler.jdk.util.JavaFileObjects;
import org.codehaus.commons.compiler.util.reflect.ApiLog;
import org.codehaus.commons.compiler.util.resource.Resource;
import org.codehaus.commons.compiler.util.resource.ResourceCreator;
import org.codehaus.commons.compiler.util.resource.ResourceFinder;
import org.codehaus.commons.nullanalysis.Nullable;

public class Compiler
extends AbstractCompiler {
    private Collection<String> compilerOptions = new ArrayList<String>();
    private final JavaCompiler compiler;

    public Compiler() {
        JavaCompiler c = ToolProvider.getSystemJavaCompiler();
        if (c == null) {
            throw new RuntimeException("JDK Java compiler not available - probably you're running a JRE, not a JDK", null);
        }
        this.compiler = c;
    }

    public Compiler(JavaCompiler compiler) {
        this.compiler = compiler;
    }

    @Override
    public void setVerbose(boolean verbose) {
    }

    public void setCompilerOptions(String[] compilerOptions) {
        this.compilerOptions = Arrays.asList(compilerOptions);
    }

    @Override
    public void compile(Resource[] sourceResources) throws CompileException, IOException {
        this.compile(sourceResources, null);
    }

    public void compile(Resource[] sourceResources, @Nullable SortedSet<Location> offsets) throws CompileException, IOException {
        File[] bcp;
        ArrayList<String> options = new ArrayList<String>(this.compilerOptions);
        ArrayList<String> l = new ArrayList<String>();
        if (this.debugLines) {
            l.add("lines");
        }
        if (this.debugSource) {
            l.add("source");
        }
        if (this.debugVars) {
            l.add("vars");
        }
        if (l.isEmpty()) {
            l.add("none");
        }
        Iterator it = l.iterator();
        String o = "-g:" + (String)it.next();
        while (it.hasNext()) {
            o = o + "," + (String)it.next();
        }
        options.add(o);
        if (this.sourceVersion != -1) {
            options.add("-source");
            options.add(Integer.toString(this.sourceVersion));
        }
        if (this.targetVersion != -1) {
            options.add("-target");
            options.add(Integer.toString(this.targetVersion));
        }
        if ((bcp = this.bootClassPath) != null) {
            options.add("-bootclasspath");
            options.add(Compiler.filesToPath(bcp));
        }
        options.add("-classpath");
        options.add(Compiler.filesToPath(this.classPath));
        Compiler.compile(this.compiler, options, this.sourceFinder, this.sourceCharset, this.classFileFinder, this.classFileCreator, sourceResources, this.compileErrorHandler, this.warningHandler, offsets);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static void compile(JavaCompiler compiler, List<String> options, ResourceFinder sourceFinder, Charset sourceFileCharset, ResourceFinder classFileFinder, ResourceCreator classFileCreator, Resource[] sourceFiles, @Nullable ErrorHandler compileErrorHandler, @Nullable WarningHandler warningHandler, @Nullable SortedSet<Location> offsets) throws CompileException, IOException {
        ArrayList<JavaFileObject> sourceFileObjects = new ArrayList<JavaFileObject>();
        for (int i = 0; i < sourceFiles.length; ++i) {
            Resource sourceResource = sourceFiles[i];
            String fn = sourceResource.getFileName();
            String className = fn.substring(fn.lastIndexOf(File.separatorChar) + 1).replace('/', '.');
            if (className.endsWith(".java")) {
                className = className.substring(0, className.length() - 5);
            }
            sourceFileObjects.add(JavaFileObjects.fromResource(sourceResource, className, JavaFileObject.Kind.SOURCE, sourceFileCharset));
        }
        try (JavaFileManager fileManager = Compiler.getJavaFileManager(compiler, sourceFinder, sourceFileCharset, classFileFinder, classFileCreator);){
            Compiler.compile(compiler, options, sourceFileObjects, fileManager, compileErrorHandler, warningHandler, offsets);
        }
    }

    private static JavaFileManager getJavaFileManager(JavaCompiler compiler, ResourceFinder sourceFileFinder, Charset sourceFileCharset, ResourceFinder classFileFinder, ResourceCreator classFileCreator) {
        JavaFileManager jfm = compiler.getStandardFileManager(null, null, null);
        jfm = JavaFileManagers.fromResourceCreator(jfm, StandardLocation.CLASS_OUTPUT, JavaFileObject.Kind.CLASS, classFileCreator, Charset.defaultCharset());
        jfm = JavaFileManagers.fromResourceFinder(jfm, StandardLocation.CLASS_PATH, JavaFileObject.Kind.CLASS, classFileFinder, Charset.defaultCharset());
        jfm = JavaFileManagers.fromResourceFinder(jfm, StandardLocation.SOURCE_PATH, JavaFileObject.Kind.SOURCE, sourceFileFinder, sourceFileCharset);
        return jfm;
    }

    static void compile(JavaCompiler compiler, List<String> options, Collection<JavaFileObject> sourceFileObjects, JavaFileManager fileManager, final @Nullable ErrorHandler compileErrorHandler, final @Nullable WarningHandler warningHandler, final @Nullable SortedSet<Location> offsets) throws CompileException, IOException {
        fileManager = (JavaFileManager)ApiLog.logMethodInvocations(fileManager);
        final int[] compileErrorCount = new int[1];
        DiagnosticListener<JavaFileObject> dl = new DiagnosticListener<JavaFileObject>(){

            @Override
            public void report(@Nullable Diagnostic<? extends JavaFileObject> diagnostic) {
                SortedSet<Location> hs;
                assert (diagnostic != null);
                JavaFileObject source = diagnostic.getSource();
                Location loc = new Location(source == null ? null : (source instanceof JavaFileObjects.ResourceJavaFileObject ? ((JavaFileObjects.ResourceJavaFileObject)source).getResourceFileName() : source.getName()), (short)diagnostic.getLineNumber(), (short)diagnostic.getColumnNumber());
                if (offsets != null && !(hs = offsets.headSet(loc)).isEmpty()) {
                    Location co = hs.last();
                    loc = new Location(co.getFileName(), loc.getLineNumber() - co.getLineNumber() + 1, loc.getLineNumber() == co.getLineNumber() ? loc.getColumnNumber() - co.getColumnNumber() + 1 : loc.getColumnNumber());
                }
                String message = diagnostic.getMessage(null) + " (" + diagnostic.getCode() + ")";
                try {
                    switch (diagnostic.getKind()) {
                        case ERROR: {
                            compileErrorCount[0] = compileErrorCount[0] + 1;
                            if (compileErrorHandler == null) {
                                throw new CompileException(message, loc);
                            }
                            compileErrorHandler.handleError(diagnostic.toString(), loc);
                            break;
                        }
                        case MANDATORY_WARNING: 
                        case WARNING: {
                            if (warningHandler == null) break;
                            warningHandler.handleWarning(null, message, loc);
                            break;
                        }
                    }
                }
                catch (CompileException ce) {
                    throw new RuntimeException(ce);
                }
            }
        };
        try {
            if (!compiler.getTask(null, fileManager, (DiagnosticListener<? super JavaFileObject>)dl, options, null, sourceFileObjects).call().booleanValue() && compileErrorCount[0] == 0) {
                throw new CompileException("Compilation failed for an unknown reason", null);
            }
        }
        catch (RuntimeException rte) {
            for (Throwable t = rte.getCause(); t != null; t = t.getCause()) {
                if (t instanceof CompileException) {
                    throw (CompileException)t;
                }
                if (!(t instanceof IOException)) continue;
                throw (IOException)t;
            }
            throw rte;
        }
        if (compileErrorCount[0] > 0) {
            throw new CompileException("Compilation failed with " + compileErrorCount[0] + " errors", null);
        }
    }

    private static String filesToPath(File[] files) {
        StringBuilder sb = new StringBuilder();
        for (File cpe : files) {
            if (sb.length() > 0) {
                sb.append(File.pathSeparatorChar);
            }
            sb.append(cpe.getPath());
        }
        return sb.toString();
    }
}

