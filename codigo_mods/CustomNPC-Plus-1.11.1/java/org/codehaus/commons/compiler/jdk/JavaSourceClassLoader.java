/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.commons.compiler.jdk;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import org.codehaus.commons.compiler.AbstractJavaSourceClassLoader;
import org.codehaus.commons.compiler.jdk.util.JavaFileManagers;
import org.codehaus.commons.compiler.jdk.util.JavaFileObjects;
import org.codehaus.commons.compiler.util.Disassembler;
import org.codehaus.commons.compiler.util.resource.DirectoryResourceFinder;
import org.codehaus.commons.compiler.util.resource.PathResourceFinder;
import org.codehaus.commons.compiler.util.resource.ResourceFinder;
import org.codehaus.commons.nullanalysis.NotNullByDefault;
import org.codehaus.commons.nullanalysis.Nullable;

public class JavaSourceClassLoader
extends AbstractJavaSourceClassLoader {
    private static final JavaCompiler SYSTEM_JAVA_COMPILER = JavaSourceClassLoader.getSystemJavaCompiler();
    private ResourceFinder sourceFinder = new DirectoryResourceFinder(new File("."));
    private Charset sourceCharset = Charset.defaultCharset();
    private boolean debuggingInfoLines;
    private boolean debuggingInfoVars;
    private boolean debuggingInfoSource;
    private Collection<String> compilerOptions = new ArrayList<String>();
    @Nullable
    private JavaFileManager fileManager;

    public JavaSourceClassLoader() {
        this.init();
    }

    private static JavaCompiler getSystemJavaCompiler() {
        JavaCompiler c = ToolProvider.getSystemJavaCompiler();
        if (c == null) {
            throw new UnsupportedOperationException("JDK Java compiler not available - probably you're running a JRE, not a JDK");
        }
        return c;
    }

    public JavaSourceClassLoader(ClassLoader parentClassLoader) {
        super(parentClassLoader);
        this.init();
    }

    private void init() {
    }

    private JavaFileManager getJavaFileManager() {
        if (this.fileManager != null) {
            return this.fileManager;
        }
        JavaFileManager jfm = SYSTEM_JAVA_COMPILER.getStandardFileManager(null, null, null);
        jfm = JavaFileManagers.inMemory(jfm, Charset.defaultCharset());
        jfm = JavaFileManagers.fromResourceFinder(jfm, StandardLocation.SOURCE_PATH, JavaFileObject.Kind.SOURCE, this.sourceFinder, this.sourceCharset);
        this.fileManager = jfm;
        return this.fileManager;
    }

    @Override
    public void setSourcePath(File[] sourcePath) {
        this.setSourceFinder(new PathResourceFinder(sourcePath));
    }

    @Override
    public void setSourceFinder(ResourceFinder sourceFinder) {
        this.sourceFinder = sourceFinder;
    }

    @Override
    public void setSourceCharset(Charset charset) {
        this.sourceCharset = charset;
    }

    @Override
    public void setDebuggingInfo(boolean lines, boolean vars, boolean source) {
        this.debuggingInfoLines = lines;
        this.debuggingInfoVars = vars;
        this.debuggingInfoSource = source;
    }

    public void setCompilerOptions(String[] compilerOptions) {
        this.compilerOptions = Arrays.asList(compilerOptions);
    }

    @Override
    @NotNullByDefault(value=false)
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            return this.findClass2(className);
        }
        catch (IOException ioe) {
            throw new DiagnosticException(ioe);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private Class<?> findClass2(String className) throws IOException {
        int size;
        byte[] ba;
        JavaFileObject classFileObject = this.findClassFile(className);
        if (classFileObject instanceof JavaFileObjects.ByteArrayJavaFileObject) {
            ba = ((JavaFileObjects.ByteArrayJavaFileObject)classFileObject).toByteArray();
            size = ba.length;
        } else {
            ba = new byte[4096];
            size = 0;
            try (InputStream is = classFileObject.openInputStream();){
                int res;
                while ((res = is.read(ba, size, ba.length - size)) != -1) {
                    if ((size += res) != ba.length) continue;
                    byte[] tmp = new byte[2 * size];
                    System.arraycopy(ba, 0, tmp, 0, size);
                    ba = tmp;
                }
                is.close();
            }
        }
        if (Boolean.getBoolean("disasm")) {
            Disassembler.disassembleToStdout(ba);
        }
        return this.defineClass(className, ba, 0, size, this.protectionDomainFactory != null ? this.protectionDomainFactory.getProtectionDomain(JavaSourceClassLoader.getSourceResourceName(className)) : null);
    }

    private JavaFileObject findClassFile(String className) throws IOException {
        JavaFileObject classFileObject = this.getJavaFileManager().getJavaFileForInput(StandardLocation.CLASS_OUTPUT, className, JavaFileObject.Kind.CLASS);
        if (classFileObject != null) {
            return classFileObject;
        }
        int idx = className.indexOf(36);
        String topLevelClassName = idx == -1 ? className : className.substring(0, idx);
        JavaFileObject sourceFileObject = this.getJavaFileManager().getJavaFileForInput(StandardLocation.SOURCE_PATH, topLevelClassName, JavaFileObject.Kind.SOURCE);
        if (sourceFileObject == null) {
            throw new DiagnosticException("Source for '" + className + "' not found");
        }
        ArrayList<String> options = new ArrayList<String>(this.compilerOptions);
        ArrayList<String> l = new ArrayList<String>();
        if (this.debuggingInfoLines) {
            l.add("lines");
        }
        if (this.debuggingInfoSource) {
            l.add("source");
        }
        if (this.debuggingInfoVars) {
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
        if (!SYSTEM_JAVA_COMPILER.getTask(null, this.getJavaFileManager(), (DiagnosticListener<? super JavaFileObject>)new DiagnosticListener<JavaFileObject>(){

            @Override
            public void report(@Nullable Diagnostic<? extends JavaFileObject> diagnostic) {
                assert (diagnostic != null);
                if (diagnostic.getKind() == Diagnostic.Kind.ERROR) {
                    throw new DiagnosticException(diagnostic);
                }
            }
        }, options, null, Collections.singleton(sourceFileObject)).call().booleanValue()) {
            throw new DiagnosticException(className + ": Compilation failed");
        }
        classFileObject = this.getJavaFileManager().getJavaFileForInput(StandardLocation.CLASS_OUTPUT, className, JavaFileObject.Kind.CLASS);
        if (classFileObject == null) {
            throw new DiagnosticException(className + ": Class file not created by compilation");
        }
        return classFileObject;
    }

    private static String getSourceResourceName(String className) {
        int idx = className.lastIndexOf(46) + 1;
        if ((idx = className.indexOf(36, idx)) != -1) {
            className = className.substring(0, idx);
        }
        return className.replace('.', '/') + ".java";
    }

    public static class DiagnosticException
    extends RuntimeException {
        private static final long serialVersionUID = 5589635876875819926L;

        DiagnosticException(String message) {
            super(message);
        }

        DiagnosticException(Throwable cause) {
            super(cause);
        }

        DiagnosticException(Diagnostic<? extends JavaFileObject> diagnostic) {
            super(diagnostic.toString());
        }
    }
}

