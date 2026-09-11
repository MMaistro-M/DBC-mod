/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.commons.compiler.jdk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.Buffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.Cookable;
import org.codehaus.commons.compiler.ErrorHandler;
import org.codehaus.commons.compiler.IClassBodyEvaluator;
import org.codehaus.commons.compiler.WarningHandler;
import org.codehaus.commons.compiler.io.Readers;
import org.codehaus.commons.compiler.jdk.SimpleCompiler;
import org.codehaus.commons.nullanalysis.Nullable;

public class ClassBodyEvaluator
extends Cookable
implements IClassBodyEvaluator {
    private final SimpleCompiler sc = new SimpleCompiler();
    private String[] defaultImports = new String[0];
    private String className = "SC";
    @Nullable
    private Class<?> extendedType;
    private Class<?>[] implementedTypes = new Class[0];
    @Nullable
    private Class<?> result;
    private static final Pattern IMPORT_STATEMENT_PATTERN = Pattern.compile("\\bimport\\s+((?:static\\s+)?[\\p{javaLowerCase}\\p{javaUpperCase}_\\$][\\p{javaLowerCase}\\p{javaUpperCase}\\d_\\$]*(?:\\.[\\p{javaLowerCase}\\p{javaUpperCase}_\\$][\\p{javaLowerCase}\\p{javaUpperCase}\\d_\\$]*)*(?:\\.\\*)?);");

    @Override
    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public void setDefaultImports(String ... defaultImports) {
        this.defaultImports = (String[])defaultImports.clone();
    }

    @Override
    public String[] getDefaultImports() {
        return (String[])this.defaultImports.clone();
    }

    @Override
    public void setExtendedClass(@Nullable Class<?> extendedType) {
        this.extendedType = extendedType;
    }

    @Override
    @Deprecated
    public void setExtendedType(@Nullable Class<?> extendedClass) {
        this.setExtendedClass(extendedClass);
    }

    @Override
    public void setImplementedInterfaces(Class<?>[] implementedTypes) {
        this.implementedTypes = implementedTypes;
    }

    @Override
    @Deprecated
    public void setImplementedTypes(Class<?>[] implementedInterfaces) {
        this.setImplementedInterfaces(implementedInterfaces);
    }

    @Override
    public void setParentClassLoader(@Nullable ClassLoader parentClassLoader) {
        this.sc.setParentClassLoader(parentClassLoader);
    }

    @Override
    public void setDebuggingInformation(boolean debugSource, boolean debugLines, boolean debugVars) {
        this.sc.setDebuggingInformation(debugSource, debugLines, debugVars);
    }

    @Override
    public void setSourceVersion(int version) {
        this.sc.setSourceVersion(version);
    }

    @Override
    public void setTargetVersion(int version) {
        this.sc.setTargetVersion(version);
    }

    @Override
    public void setCompileErrorHandler(@Nullable ErrorHandler compileErrorHandler) {
        this.sc.setCompileErrorHandler(compileErrorHandler);
    }

    @Override
    public void setWarningHandler(@Nullable WarningHandler warningHandler) {
        this.sc.setWarningHandler(warningHandler);
    }

    @Override
    public void cook(@Nullable String fileName, Reader r) throws CompileException, IOException {
        if (!r.markSupported()) {
            r = new BufferedReader(r);
        }
        this.cook(fileName, ClassBodyEvaluator.parseImportDeclarations(r), r);
    }

    protected void cook(@Nullable String fileName, String[] imports, Reader r) throws CompileException, IOException {
        String simpleClassName;
        String packageName;
        StringWriter sw1 = new StringWriter();
        PrintWriter pw = new PrintWriter(sw1);
        int idx = this.className.lastIndexOf(46);
        if (idx == -1) {
            packageName = "";
            simpleClassName = this.className;
        } else {
            packageName = this.className.substring(0, idx);
            simpleClassName = this.className.substring(idx + 1);
        }
        if (!packageName.isEmpty()) {
            pw.print("package ");
            pw.print(packageName);
            pw.println(";");
        }
        for (String defaultImport : this.defaultImports) {
            pw.print("import ");
            pw.print(defaultImport);
            pw.println(";");
        }
        if (!r.markSupported()) {
            r = new BufferedReader(r);
        }
        for (String imporT : imports) {
            pw.print("import ");
            pw.print(imporT);
            pw.println(";");
        }
        pw.print("public class ");
        pw.print(simpleClassName);
        Class<?> oet = this.extendedType;
        if (oet != null) {
            pw.print(" extends ");
            pw.print(oet.getCanonicalName());
        }
        if (this.implementedTypes.length > 0) {
            pw.print(" implements ");
            pw.print(this.implementedTypes[0].getCanonicalName());
            for (int i = 1; i < this.implementedTypes.length; ++i) {
                pw.print(", ");
                pw.print(this.implementedTypes[i].getCanonicalName());
            }
        }
        pw.println(" {");
        pw.close();
        StringWriter sw2 = new StringWriter();
        PrintWriter pw2 = new PrintWriter(sw2);
        pw2.println("}");
        pw2.close();
        r = Readers.concat(new StringReader(sw1.toString()), this.newFileName(fileName, r), new StringReader(sw2.toString()));
        this.sc.cook(fileName, r);
        try {
            this.result = this.sc.getClassLoader().loadClass(this.className);
        }
        catch (ClassNotFoundException cnfe) {
            throw new IOException(cnfe);
        }
    }

    @Override
    public Class<?> getClazz() {
        assert (this.result != null);
        return this.result;
    }

    @Override
    public Map<String, byte[]> getBytecodes() {
        return this.sc.getBytecodes();
    }

    protected Reader newFileName(final @Nullable String fileName, Reader reader) {
        return Readers.onFirstChar(reader, new Runnable(){

            @Override
            public void run() {
                ClassBodyEvaluator.this.sc.addOffset(fileName);
            }
        });
    }

    protected static String[] parseImportDeclarations(Reader r) throws IOException {
        CharBuffer cb = CharBuffer.allocate(10000);
        r.mark(cb.limit());
        r.read(cb);
        ((Buffer)cb).rewind();
        ArrayList<String> imports = new ArrayList<String>();
        int afterLastImport = 0;
        Matcher matcher = IMPORT_STATEMENT_PATTERN.matcher(cb);
        while (matcher.find()) {
            imports.add(matcher.group(1));
            afterLastImport = matcher.end();
        }
        r.reset();
        r.skip(afterLastImport);
        return imports.toArray(new String[imports.size()]);
    }

    @Override
    public Object createInstance(Reader reader) throws CompileException, IOException {
        this.cook(reader);
        try {
            return this.getClazz().getConstructor(new Class[0]).newInstance(new Object[0]);
        }
        catch (InstantiationException ie) {
            throw new CompileException("Class is abstract, an interface, an array class, a primitive type, or void; or has no zero-parameter constructor", null, ie);
        }
        catch (Exception e) {
            throw new CompileException("Instantiating \"" + this.getClazz().getCanonicalName() + "\"", null, e);
        }
    }
}

