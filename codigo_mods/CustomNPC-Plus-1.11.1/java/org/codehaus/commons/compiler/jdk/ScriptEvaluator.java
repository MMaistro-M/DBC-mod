/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.commons.compiler.jdk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.ErrorHandler;
import org.codehaus.commons.compiler.IExpressionEvaluator;
import org.codehaus.commons.compiler.IScriptEvaluator;
import org.codehaus.commons.compiler.MultiCookable;
import org.codehaus.commons.compiler.WarningHandler;
import org.codehaus.commons.compiler.io.Readers;
import org.codehaus.commons.compiler.jdk.ClassBodyEvaluator;
import org.codehaus.commons.nullanalysis.Nullable;

public class ScriptEvaluator
extends MultiCookable
implements IScriptEvaluator {
    final ClassBodyEvaluator cbe = new ClassBodyEvaluator();
    @Nullable
    private Script[] scripts;
    private Class<?> defaultReturnType = IScriptEvaluator.DEFAULT_RETURN_TYPE;
    @Nullable
    private Method[] result;

    public ScriptEvaluator(String script) throws CompileException {
        this.cook(script);
    }

    public ScriptEvaluator(String script, Class<?> returnType) throws CompileException {
        this.setReturnType(returnType);
        this.cook(script);
    }

    public ScriptEvaluator(String script, Class<?> returnType, String[] parameterNames, Class<?>[] parameterTypes) throws CompileException {
        this.setReturnType(returnType);
        this.setParameters(parameterNames, parameterTypes);
        this.cook(script);
    }

    public ScriptEvaluator(String script, Class<?> returnType, String[] parameterNames, Class<?>[] parameterTypes, Class<?>[] thrownExceptions) throws CompileException {
        this.setReturnType(returnType);
        this.setParameters(parameterNames, parameterTypes);
        this.setThrownExceptions(thrownExceptions);
        this.cook(script);
    }

    public ScriptEvaluator(@Nullable String fileName, InputStream is, Class<?> returnType, String[] parameterNames, Class<?>[] parameterTypes, Class<?>[] thrownExceptions, @Nullable ClassLoader parentClassLoader) throws CompileException, IOException {
        this.setReturnType(returnType);
        this.setParameters(parameterNames, parameterTypes);
        this.setThrownExceptions(thrownExceptions);
        this.setParentClassLoader(parentClassLoader);
        this.cook(fileName, is);
    }

    public ScriptEvaluator(@Nullable String fileName, Reader reader, Class<?> returnType, String[] parameterNames, Class<?>[] parameterTypes, Class<?>[] thrownExceptions, @Nullable ClassLoader parentClassLoader) throws CompileException, IOException {
        this.setReturnType(returnType);
        this.setParameters(parameterNames, parameterTypes);
        this.setThrownExceptions(thrownExceptions);
        this.setParentClassLoader(parentClassLoader);
        this.cook(fileName, reader);
    }

    public ScriptEvaluator() {
    }

    @Override
    public void setParentClassLoader(@Nullable ClassLoader parentClassLoader) {
        this.cbe.setParentClassLoader(parentClassLoader);
    }

    @Override
    public void setDebuggingInformation(boolean debugSource, boolean debugLines, boolean debugVars) {
        this.cbe.setDebuggingInformation(debugSource, debugLines, debugVars);
    }

    @Override
    public void setSourceVersion(int version) {
        this.cbe.setSourceVersion(version);
    }

    @Override
    public void setTargetVersion(int version) {
        this.cbe.setTargetVersion(version);
    }

    @Override
    public void setCompileErrorHandler(@Nullable ErrorHandler compileErrorHandler) {
        this.cbe.setCompileErrorHandler(compileErrorHandler);
    }

    @Override
    public void setWarningHandler(@Nullable WarningHandler warningHandler) {
        this.cbe.setWarningHandler(warningHandler);
    }

    public void setScriptCount(int count) {
        Script[] ss = this.scripts;
        if (ss == null) {
            this.scripts = ss = new Script[count];
            for (int i = 0; i < count; ++i) {
                ss[i] = new Script();
            }
        } else if (count != ss.length) {
            throw new IllegalArgumentException("Inconsistent script count; previously " + ss.length + ", now " + count);
        }
    }

    private Script getScript(int index) {
        if (this.scripts != null) {
            return this.scripts[index];
        }
        throw new IllegalStateException("\"getScript()\" invoked before \"setScriptCount()\"");
    }

    @Override
    public void setClassName(String className) {
        this.cbe.setClassName(className);
    }

    @Override
    public void setImplementedInterfaces(Class<?>[] implementedInterfaces) {
        this.cbe.setImplementedInterfaces(implementedInterfaces);
    }

    @Override
    public void setExtendedClass(@Nullable Class<?> extendedClass) {
        this.cbe.setExtendedClass(extendedClass);
    }

    @Override
    public void setDefaultReturnType(Class<?> defaultReturnType) {
        this.defaultReturnType = defaultReturnType;
    }

    @Override
    public Class<?> getDefaultReturnType() {
        return this.defaultReturnType;
    }

    @Override
    public void setDefaultImports(String ... defaultImports) {
        this.cbe.setDefaultImports(defaultImports);
    }

    @Override
    public String[] getDefaultImports() {
        return this.cbe.getDefaultImports();
    }

    @Override
    public void setOverrideMethod(boolean overrideMethod) {
        this.setOverrideMethod(new boolean[]{overrideMethod});
    }

    @Override
    public void setStaticMethod(boolean staticMethod) {
        this.setStaticMethod(new boolean[]{staticMethod});
    }

    @Override
    public void setReturnType(Class<?> returnType) {
        this.setReturnTypes(new Class[]{returnType});
    }

    @Override
    public void setMethodName(@Nullable String methodName) {
        this.setMethodNames(new String[]{methodName});
    }

    @Override
    public void setParameters(String[] names, Class<?>[] types) {
        this.setParameters(new String[][]{names}, new Class[][]{types});
    }

    @Override
    public void setThrownExceptions(Class<?>[] thrownExceptions) {
        this.setThrownExceptions(new Class[][]{thrownExceptions});
    }

    @Override
    @Nullable
    public Object evaluate() throws InvocationTargetException {
        return this.evaluate(new Object[0]);
    }

    @Override
    @Nullable
    public Object evaluate(@Nullable Object[] arguments) throws InvocationTargetException {
        return this.evaluate(0, arguments);
    }

    @Override
    public Method getMethod() {
        return this.getMethod(0);
    }

    @Override
    public void setOverrideMethod(boolean[] overrideMethod) {
        this.setScriptCount(overrideMethod.length);
        for (int i = 0; i < overrideMethod.length; ++i) {
            this.getScript((int)i).overrideMethod = overrideMethod[i];
        }
    }

    @Override
    public void setStaticMethod(boolean[] staticMethod) {
        this.setScriptCount(staticMethod.length);
        for (int i = 0; i < staticMethod.length; ++i) {
            this.getScript((int)i).staticMethod = staticMethod[i];
        }
    }

    @Override
    public void setReturnTypes(Class<?>[] returnTypes) {
        this.setScriptCount(returnTypes.length);
        for (int i = 0; i < returnTypes.length; ++i) {
            this.getScript(i).returnType = returnTypes[i];
        }
    }

    @Override
    public void setMethodNames(String[] methodNames) {
        this.setScriptCount(methodNames.length);
        for (int i = 0; i < methodNames.length; ++i) {
            this.getScript(i).methodName = methodNames[i];
        }
    }

    @Override
    public void setParameters(String[][] parameterNames, Class<?>[][] parameterTypes) {
        this.setScriptCount(parameterNames.length);
        this.setScriptCount(parameterTypes.length);
        for (int i = 0; i < parameterNames.length; ++i) {
            Script script = this.getScript(i);
            Script.access$302(script, (String[])parameterNames[i].clone());
            Script.access$402(script, (Class[])parameterTypes[i].clone());
        }
    }

    @Override
    public void setThrownExceptions(Class<?>[][] thrownExceptions) {
        this.setScriptCount(thrownExceptions.length);
        for (int i = 0; i < thrownExceptions.length; ++i) {
            Script.access$502(this.getScript(i), thrownExceptions[i]);
        }
    }

    @Override
    public void cook(@Nullable String fileName, Reader reader) throws CompileException, IOException {
        if (!reader.markSupported()) {
            reader = new BufferedReader(reader);
        }
        String[] imports = ClassBodyEvaluator.parseImportDeclarations(reader);
        this.cook(new String[]{fileName}, new Reader[]{reader}, imports);
    }

    @Override
    public void cook(String[] fileNames, Reader[] readers) throws CompileException, IOException {
        if (readers.length == 1) {
            Reader r = readers[0];
            if (!r.markSupported()) {
                r = new BufferedReader(r);
            }
            this.cook(fileNames, new Reader[]{r}, ClassBodyEvaluator.parseImportDeclarations(r));
        } else {
            this.cook(fileNames, readers, new String[0]);
        }
    }

    /*
     * WARNING - void declaration
     */
    protected final void cook(String[] fileNames, Reader[] readers, String[] imports) throws CompileException, IOException {
        this.setScriptCount(fileNames.length);
        this.setScriptCount(readers.length);
        int count = readers.length;
        ArrayList<Reader> classBody = new ArrayList<Reader>();
        for (int idx = 0; idx < count; ++idx) {
            int j;
            Script s = this.getScript(idx);
            boolean overrideMethod = s.overrideMethod;
            boolean staticMethod = s.staticMethod;
            Class<?> clazz = s.returnType != null ? s.returnType : this.getDefaultReturnType();
            String methodName = this.getMethodName(idx);
            String[] parameterNames = s.parameterNames;
            Class[] parameterTypes = s.parameterTypes;
            Class[] thrownExceptions = s.thrownExceptions;
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            if (overrideMethod) {
                pw.print("@Override ");
            }
            pw.print("public ");
            if (staticMethod) {
                pw.print("static ");
            }
            pw.print(clazz.getCanonicalName());
            pw.print(" ");
            pw.print(methodName);
            pw.print("(");
            for (j = 0; j < parameterNames.length; ++j) {
                if (j > 0) {
                    pw.print(", ");
                }
                pw.print(parameterTypes[j].getName());
                pw.print(" ");
                pw.print(parameterNames[j]);
            }
            pw.print(")");
            for (j = 0; j < thrownExceptions.length; ++j) {
                pw.print(j == 0 ? " throws " : ", ");
                pw.print(thrownExceptions[j].getName());
            }
            pw.println(" {");
            pw.close();
            classBody.add(new StringReader(sw.toString()));
            classBody.add(this.cbe.newFileName((String)(fileNames[idx] != null ? fileNames[idx] : (idx == 0 ? null : "[" + idx + "]")), readers[idx]));
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            pw.println("}");
            pw.close();
            classBody.add(new StringReader(sw.toString()));
        }
        this.cbe.cook(fileNames[0], imports, Readers.concat(classBody));
        Class<?> c = this.getClazz();
        this.result = new Method[count];
        Method[] methods = this.result;
        if (count <= 10) {
            for (int idx = 0; idx < count; ++idx) {
                Script sript = this.getScript(idx);
                String string = this.getMethodName(idx);
                try {
                    methods[idx] = c.getDeclaredMethod(string, sript.parameterTypes);
                    continue;
                }
                catch (NoSuchMethodException nsme) {
                    throw new RuntimeException("SNO: Loaded class does not declare method \"" + string + "\"", nsme);
                }
            }
        } else {
            void var10_19;
            Method[] ma = c.getDeclaredMethods();
            class MethodWrapper {
                private final String name;
                private final Class<?>[] parameterTypes;

                MethodWrapper(String name, Class<?>[] parameterTypes) {
                    this.name = name;
                    this.parameterTypes = parameterTypes;
                }

                public boolean equals(@Nullable Object o) {
                    if (!(o instanceof MethodWrapper)) {
                        return false;
                    }
                    MethodWrapper that = (MethodWrapper)o;
                    return this.name.equals(that.name) && Arrays.equals(this.parameterTypes, that.parameterTypes);
                }

                public int hashCode() {
                    return this.name.hashCode() ^ Arrays.hashCode(this.parameterTypes);
                }
            }
            HashMap<MethodWrapper, Method> dms = new HashMap<MethodWrapper, Method>(2 * count);
            for (Method m : ma) {
                dms.put(new MethodWrapper(m.getName(), m.getParameterTypes()), m);
            }
            boolean bl = false;
            while (var10_19 < count) {
                Class[] parameterTypes;
                Method m;
                String methodName = this.getMethodName((int)var10_19);
                m = (Method)dms.get(new MethodWrapper(methodName, parameterTypes = this.getScript((int)var10_19).parameterTypes));
                if (m == null) {
                    throw new RuntimeException("SNO: Loaded class does not declare method \"" + methodName + "\"");
                }
                methods[var10_19] = m;
                ++var10_19;
            }
        }
    }

    private String getMethodName(int idx) {
        String result = this.getScript(idx).methodName;
        if (result == null) {
            result = "eval*".replace("*", Integer.toString(idx));
        }
        return result;
    }

    protected final Class<?> getReturnType(int i) {
        Class<?> returnType = this.getScript(i).returnType;
        return returnType != null ? returnType : this.getDefaultReturnType();
    }

    @Override
    public <T> T createFastEvaluator(String script, Class<T> interfaceToImplement, String[] parameterNames) throws CompileException {
        try {
            return this.createFastEvaluator(new StringReader(script), interfaceToImplement, parameterNames);
        }
        catch (IOException ioe) {
            throw new RuntimeException("SNO: IOException despite StringReader", ioe);
        }
    }

    @Override
    public <T> T createFastEvaluator(Reader r, Class<T> interfaceToImplement, String[] parameterNames) throws CompileException, IOException {
        if (!interfaceToImplement.isInterface()) {
            throw new RuntimeException("\"" + interfaceToImplement + "\" is not an interface");
        }
        this.setImplementedInterfaces(new Class[]{interfaceToImplement});
        this.setStaticMethod(false);
        Method[] methods = interfaceToImplement.getDeclaredMethods();
        if (methods.length != 1) {
            throw new RuntimeException("Interface \"" + interfaceToImplement + "\" must declare exactly one method");
        }
        Method methodToImplement = methods[0];
        if (this instanceof IExpressionEvaluator) {
            ((IExpressionEvaluator)((Object)this)).setExpressionType(methodToImplement.getReturnType());
        } else {
            this.setReturnType(methodToImplement.getReturnType());
        }
        this.setMethodName(methodToImplement.getName());
        this.setParameters(parameterNames, methodToImplement.getParameterTypes());
        this.setThrownExceptions(methodToImplement.getExceptionTypes());
        this.cook(r);
        Class<?> c = this.getMethod().getDeclaringClass();
        try {
            Object instance = c.getConstructor(new Class[0]).newInstance(new Object[0]);
            return (T)instance;
        }
        catch (InstantiationException e) {
            throw new RuntimeException("SNO - Declared class is always non-abstract", e);
        }
        catch (Exception e) {
            throw new CompileException("Instantiating \"" + c.getName() + "\"", null, e);
        }
    }

    @Override
    @Nullable
    public Object evaluate(int idx, @Nullable Object[] arguments) throws InvocationTargetException {
        try {
            return this.getMethods()[idx].invoke(null, arguments);
        }
        catch (IllegalAccessException ex) {
            throw new RuntimeException(ex.toString(), ex);
        }
    }

    @Override
    public Method[] getResult() {
        return this.getMethods();
    }

    @Override
    public Method getMethod(int idx) {
        return this.getMethods()[idx];
    }

    protected Method[] getMethods() {
        if (this.result != null) {
            return this.result;
        }
        throw new IllegalStateException("\"cook()\" has not yet been called");
    }

    @Override
    public Class<?> getClazz() {
        return this.cbe.getClazz();
    }

    @Override
    public Map<String, byte[]> getBytecodes() {
        return this.cbe.getBytecodes();
    }

    private static class Script {
        protected boolean overrideMethod;
        protected boolean staticMethod = true;
        @Nullable
        private Class<?> returnType;
        @Nullable
        private String methodName;
        private String[] parameterNames = new String[0];
        private Class<?>[] parameterTypes = new Class[0];
        private Class<?>[] thrownExceptions = new Class[0];

        private Script() {
        }

        static /* synthetic */ String[] access$302(Script x0, String[] x1) {
            x0.parameterNames = x1;
            return x1;
        }

        static /* synthetic */ Class[] access$402(Script x0, Class[] x1) {
            x0.parameterTypes = x1;
            return x1;
        }

        static /* synthetic */ Class[] access$502(Script x0, Class[] x1) {
            x0.thrownExceptions = x1;
            return x1;
        }
    }
}

