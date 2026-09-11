/*
 * Decompiled with CFR 0.152.
 */
package io.github.somehussar.janinoloader.script;

import io.github.somehussar.janinoloader.api.IDynamicCompiler;
import io.github.somehussar.janinoloader.api.script.IScriptClassBody;
import io.github.somehussar.janinoloader.script.ReloadingObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.util.reflect.ByteArrayClassLoader;
import org.codehaus.janino.ClassBodyEvaluator;

public class SafeScriptClassBody<DesiredType>
implements IScriptClassBody<DesiredType> {
    public static final IScriptClassBody.InstanceDelegate<?> DEFAULT_INSTANCE_DELEGATE = clazz -> {
        try {
            return clazz.newInstance();
        }
        catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    };
    public static final IScriptClassBody.ReloadDelegate<?> DEFAULT_RELOAD_DELEGATE = (oldInstance, newInstance, internalClassLoader) -> {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(oldInstance);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ReloadingObjectInputStream in = new ReloadingObjectInputStream(bis, internalClassLoader);
            return in.readObject();
        }
        catch (Throwable i) {
            return newInstance;
        }
    };
    private String rawScript;
    private DesiredType object;
    private boolean needToRecompile = true;
    private IDynamicCompiler compiler;
    private final String[] defaultImports;
    private final Class<?>[] interfaces;
    private final IScriptClassBody.InstanceDelegate<DesiredType> instanceDelegate;
    private final IScriptClassBody.ReloadDelegate<DesiredType> reloadDelegate;
    private ClassLoader internalClassLoader;
    private String compiledClassName;
    private final Class<DesiredType> clazz;
    private Map<String, byte[]> classBytes = new HashMap<String, byte[]>();

    SafeScriptClassBody(Class<DesiredType> outputClazz, IDynamicCompiler compiler, String[] defaultImports, Class<?>[] interfaces, String rawScript, IScriptClassBody.InstanceDelegate<DesiredType> instanceDelegate, IScriptClassBody.ReloadDelegate<DesiredType> reloadDelegate) {
        this.clazz = outputClazz;
        this.compiler = compiler;
        compiler.addReloadListener(this);
        this.defaultImports = defaultImports;
        this.instanceDelegate = instanceDelegate != null ? instanceDelegate : DEFAULT_INSTANCE_DELEGATE;
        IScriptClassBody.ReloadDelegate<Object> reloadDelegate2 = this.reloadDelegate = reloadDelegate != null ? reloadDelegate : DEFAULT_RELOAD_DELEGATE;
        if (this.clazz.isInterface()) {
            interfaces = Arrays.copyOf(interfaces, interfaces.length + 1);
            interfaces[interfaces.length - 1] = this.clazz;
        }
        this.interfaces = interfaces;
        this.rawScript = rawScript;
    }

    @Override
    public boolean handleClassLoaderReload(ClassLoader loader) {
        try {
            this.assertCompiled();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return false;
    }

    @Override
    public DesiredType get() {
        return this.object;
    }

    @Override
    public void setScript(String script) throws CompileException, IOException, ClassNotFoundException {
        this.needToRecompile = true;
        this.rawScript = script;
        this.assertCompiled();
    }

    @Override
    public void assertCompiled() throws CompileException, IOException, ClassNotFoundException {
        if (this.needToRecompile) {
            ClassBodyEvaluator se = new ClassBodyEvaluator();
            se.setParentClassLoader(this.compiler.getClassLoader());
            if (!this.clazz.isInterface()) {
                se.setExtendedClass(this.clazz);
            }
            se.setImplementedInterfaces(this.interfaces);
            if (this.defaultImports != null) {
                se.setDefaultImports(this.defaultImports);
            }
            se.cook(new StringReader(this.rawScript));
            this.classBytes = se.getBytecodes();
            Class<?> outputClazz = se.getClazz();
            this.internalClassLoader = outputClazz.getClassLoader();
            this.compiledClassName = outputClazz.getCanonicalName();
            this.object = this.instanceDelegate.apply(outputClazz);
            this.needToRecompile = false;
        } else {
            this.internalClassLoader = new ByteArrayClassLoader(this.classBytes, this.compiler.getClassLoader());
            Class<?> outputClazz = this.internalClassLoader.loadClass(this.compiledClassName);
            try {
                DesiredType newObject = this.instanceDelegate.apply(outputClazz);
                this.object = this.reloadDelegate.apply(this.object, newObject, this.internalClassLoader);
            }
            catch (Throwable e) {
                try {
                    this.object = this.instanceDelegate.apply(outputClazz);
                }
                catch (Throwable failedAgain) {
                    this.needToRecompile = true;
                    this.assertCompiled();
                }
            }
        }
    }

    @Override
    public void prepareToUnload() {
        if (this.compiler != null) {
            this.compiler.removeListener(this);
        }
        this.compiler = null;
        this.internalClassLoader = null;
        this.object = null;
    }
}

