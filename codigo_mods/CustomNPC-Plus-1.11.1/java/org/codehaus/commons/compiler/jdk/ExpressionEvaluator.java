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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.ErrorHandler;
import org.codehaus.commons.compiler.IExpressionEvaluator;
import org.codehaus.commons.compiler.InternalCompilerException;
import org.codehaus.commons.compiler.MultiCookable;
import org.codehaus.commons.compiler.WarningHandler;
import org.codehaus.commons.compiler.io.Readers;
import org.codehaus.commons.compiler.jdk.ClassBodyEvaluator;
import org.codehaus.commons.compiler.jdk.ScriptEvaluator;
import org.codehaus.commons.nullanalysis.Nullable;

public class ExpressionEvaluator
extends MultiCookable
implements IExpressionEvaluator {
    private final ScriptEvaluator se = new ScriptEvaluator();

    public ExpressionEvaluator(String expression, Class<?> expressionType, String[] parameterNames, Class<?>[] parameterTypes) throws CompileException {
        this.se.setClassName("SC");
        this.se.setDefaultReturnType(IExpressionEvaluator.DEFAULT_EXPRESSION_TYPE);
        this.setExpressionType(expressionType);
        this.setParameters(parameterNames, parameterTypes);
        this.cook(expression);
    }

    public ExpressionEvaluator(String expression, Class<?> expressionType, String[] parameterNames, Class<?>[] parameterTypes, Class<?>[] thrownExceptions, @Nullable ClassLoader parentClassLoader) throws CompileException {
        this.se.setClassName("SC");
        this.se.setDefaultReturnType(IExpressionEvaluator.DEFAULT_EXPRESSION_TYPE);
        this.setExpressionType(expressionType);
        this.setParameters(parameterNames, parameterTypes);
        this.setThrownExceptions(thrownExceptions);
        this.setParentClassLoader(parentClassLoader);
        this.cook(expression);
    }

    public ExpressionEvaluator(String expression, Class<?> expressionType, String[] parameterNames, Class<?>[] parameterTypes, Class<?>[] thrownExceptions, @Nullable Class<?> extendedType, Class<?>[] implementedTypes, @Nullable ClassLoader parentClassLoader) throws CompileException {
        this.se.setClassName("SC");
        this.se.setDefaultReturnType(IExpressionEvaluator.DEFAULT_EXPRESSION_TYPE);
        this.setExpressionType(expressionType);
        this.setParameters(parameterNames, parameterTypes);
        this.setThrownExceptions(thrownExceptions);
        this.setExtendedClass(extendedType);
        this.setImplementedInterfaces(implementedTypes);
        this.setParentClassLoader(parentClassLoader);
        this.cook(expression);
    }

    public ExpressionEvaluator() {
        this.se.setClassName("SC");
        this.se.setDefaultReturnType(IExpressionEvaluator.DEFAULT_EXPRESSION_TYPE);
    }

    @Override
    public void setParentClassLoader(@Nullable ClassLoader parentClassLoader) {
        this.se.setParentClassLoader(parentClassLoader);
    }

    @Override
    public void setDebuggingInformation(boolean debugSource, boolean debugLines, boolean debugVars) {
        this.se.setDebuggingInformation(debugSource, debugLines, debugVars);
    }

    @Override
    public void setSourceVersion(int version) {
        this.se.setSourceVersion(version);
    }

    @Override
    public void setTargetVersion(int version) {
        this.se.setTargetVersion(version);
    }

    @Override
    public void setCompileErrorHandler(@Nullable ErrorHandler compileErrorHandler) {
        this.se.setCompileErrorHandler(compileErrorHandler);
    }

    @Override
    public void setWarningHandler(@Nullable WarningHandler warningHandler) {
        this.se.setWarningHandler(warningHandler);
    }

    @Override
    public void setDefaultImports(String ... defaultImports) {
        this.se.setDefaultImports(defaultImports);
    }

    @Override
    public String[] getDefaultImports() {
        return this.se.getDefaultImports();
    }

    @Override
    public void setDefaultExpressionType(Class<?> defaultExpressionType) {
        this.se.setDefaultReturnType(defaultExpressionType);
    }

    @Override
    public Class<?> getDefaultExpressionType() {
        return this.se.getDefaultReturnType();
    }

    @Override
    public void setImplementedInterfaces(Class<?>[] implementedTypes) {
        this.se.setImplementedInterfaces(implementedTypes);
    }

    @Override
    public void setReturnType(Class<?> returnType) {
        this.se.setReturnType(returnType);
    }

    @Override
    public void setExpressionType(Class<?> expressionType) {
        this.se.setReturnType(expressionType);
    }

    @Override
    public void setExpressionTypes(Class<?>[] expressionTypes) {
        this.se.setReturnTypes(expressionTypes);
    }

    @Override
    public void setOverrideMethod(boolean overrideMethod) {
        this.se.setOverrideMethod(overrideMethod);
    }

    @Override
    public void setOverrideMethod(boolean[] overrideMethod) {
        this.se.setOverrideMethod(overrideMethod);
    }

    @Override
    public void setParameters(String[] parameterNames, Class<?>[] parameterTypes) {
        this.se.setParameters(parameterNames, parameterTypes);
    }

    @Override
    public void setParameters(String[][] parameterNames, Class<?>[][] parameterTypes) {
        this.se.setParameters(parameterNames, parameterTypes);
    }

    @Override
    public void setClassName(String className) {
        this.se.setClassName(className);
    }

    @Override
    public void setExtendedClass(@Nullable Class<?> extendedType) {
        this.se.setExtendedClass(extendedType);
    }

    @Override
    public void setStaticMethod(boolean staticMethod) {
        this.se.setStaticMethod(staticMethod);
    }

    @Override
    public void setStaticMethod(boolean[] staticMethod) {
        this.se.setStaticMethod(staticMethod);
    }

    @Override
    public void setMethodName(String methodName) {
        this.se.setMethodName(methodName);
    }

    @Override
    public void setMethodNames(String[] methodNames) {
        this.se.setMethodNames(methodNames);
    }

    @Override
    public void setThrownExceptions(Class<?>[] thrownExceptions) {
        this.se.setThrownExceptions(thrownExceptions);
    }

    @Override
    public void setThrownExceptions(Class<?>[][] thrownExceptions) {
        this.se.setThrownExceptions(thrownExceptions);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void cook(@Nullable String fileName, Reader reader) throws CompileException, IOException {
        this.se.setScriptCount(1);
        if (!reader.markSupported()) {
            reader = new BufferedReader(reader);
        }
        String[] imports = ClassBodyEvaluator.parseImportDeclarations(reader);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            Class<?> returnType = this.se.getReturnType(0);
            if (returnType != Void.TYPE && returnType != Void.class) {
                pw.print("return ");
            }
            Readers.copy(reader, pw);
            pw.println(";");
            pw.close();
        }
        finally {
            try {
                pw.close();
            }
            catch (Exception exception) {}
        }
        reader = new StringReader(sw.toString());
        this.se.cook(new String[]{fileName}, new Reader[]{reader}, imports);
    }

    @Override
    public void cook(String[] fileNames, Reader[] readers) throws CompileException, IOException {
        String[] imports;
        if ((readers = (Reader[])readers.clone()).length == 1) {
            if (!readers[0].markSupported()) {
                readers[0] = new BufferedReader(readers[0]);
            }
            imports = ClassBodyEvaluator.parseImportDeclarations(readers[0]);
        } else {
            imports = new String[]{};
        }
        Class[] returnTypes = new Class[readers.length];
        for (int i = 0; i < readers.length; ++i) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            returnTypes[i] = this.se.getReturnType(i);
            if (returnTypes[i] != Void.TYPE && returnTypes[i] != Void.class) {
                pw.print("return ");
            }
            Readers.copy(readers[i], pw);
            pw.println(";");
            pw.close();
            readers[i] = new StringReader(sw.toString());
        }
        this.se.cook(fileNames, readers, imports);
    }

    @Override
    @Nullable
    public Object evaluate() throws InvocationTargetException {
        return this.evaluate(new Object[0]);
    }

    @Override
    @Nullable
    public Object evaluate(@Nullable Object[] arguments) throws InvocationTargetException {
        return this.se.evaluate(arguments);
    }

    @Override
    @Nullable
    public Object evaluate(int idx, @Nullable Object[] arguments) throws InvocationTargetException {
        return this.se.evaluate(idx, arguments);
    }

    @Override
    public Method getMethod() {
        return this.se.getMethod();
    }

    @Override
    public Method getMethod(int idx) {
        return this.se.getMethod(idx);
    }

    @Override
    public Class<?> getClazz() {
        return this.se.getClazz();
    }

    @Override
    public Method[] getResult() {
        return this.se.getResult();
    }

    @Override
    public Map<String, byte[]> getBytecodes() {
        return this.se.getBytecodes();
    }

    @Override
    public <T> T createFastEvaluator(String expression, Class<? extends T> interfaceToImplement, String ... parameterNames) throws CompileException {
        try {
            return this.createFastEvaluator(new StringReader(expression), interfaceToImplement, parameterNames);
        }
        catch (IOException ex) {
            throw new InternalCompilerException("IOException despite StringReader", ex);
        }
    }

    @Override
    public <T> T createFastEvaluator(Reader reader, Class<? extends T> interfaceToImplement, String ... parameterNames) throws CompileException, IOException {
        if (!interfaceToImplement.isInterface()) {
            throw new InternalCompilerException("\"" + interfaceToImplement + "\" is not an interface");
        }
        Method[] methods = interfaceToImplement.getDeclaredMethods();
        if (methods.length != 1) {
            throw new InternalCompilerException("Interface \"" + interfaceToImplement + "\" must declare exactly one method");
        }
        Method methodToImplement = methods[0];
        this.setImplementedInterfaces(new Class[]{interfaceToImplement});
        this.setOverrideMethod(true);
        this.setStaticMethod(false);
        this.setExpressionType(methodToImplement.getReturnType());
        this.setMethodName(methodToImplement.getName());
        this.setParameters(parameterNames, methodToImplement.getParameterTypes());
        this.setThrownExceptions(methodToImplement.getExceptionTypes());
        this.cook(reader);
        Class<?> actualClass = this.getMethod().getDeclaringClass();
        try {
            return (T)actualClass.newInstance();
        }
        catch (InstantiationException e) {
            throw new InternalCompilerException(e.toString(), e);
        }
        catch (IllegalAccessException e) {
            throw new InternalCompilerException(e.toString(), e);
        }
    }
}

