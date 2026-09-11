/*
 * Decompiled with CFR 0.152.
 */
package io.github.somehussar.janinoloader;

import io.github.somehussar.janinoloader.api.IClassReloadListener;
import io.github.somehussar.janinoloader.api.IDynamicCompiler;
import io.github.somehussar.janinoloader.api.delegates.LoadClassCondition;
import io.github.somehussar.janinoloader.classloader.MemoryClassLoader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.util.resource.MapResourceCreator;
import org.codehaus.commons.compiler.util.resource.Resource;
import org.codehaus.janino.ClassLoaderIClassLoader;
import org.codehaus.janino.Compiler;

public class JaninoCompiler
implements IDynamicCompiler {
    private final Map<String, byte[]> classes = new HashMap<String, byte[]>();
    private final Set<IClassReloadListener> listenerSet = new HashSet<IClassReloadListener>();
    private final LoadClassCondition classFilter;
    private final ClassLoader parent;
    private Compiler compiler;
    private ClassLoader secure;
    private boolean notify = false;

    JaninoCompiler(ClassLoader parent, LoadClassCondition classFilter) {
        this.classFilter = classFilter;
        this.parent = parent;
    }

    @Override
    public ClassLoader getClassLoader() {
        if (this.compiler == null) {
            this.resetClassloader();
        }
        return this.secure;
    }

    @Override
    public void compileClass(Resource ... resources) throws CompileException, IOException {
        if (this.compiler == null) {
            this.resetClassloader();
        }
        this.compiler.compile(resources);
        if (this.notify) {
            this.notifyListeners();
            this.notify = false;
        }
    }

    @Override
    public void recompileClass(Resource ... resources) throws CompileException, IOException {
        for (Resource resource : resources) {
            this.removeClass(resource.getFileName().replaceFirst("\\.java$", ""));
        }
        this.resetClassloader();
        this.compileClass(resources);
    }

    @Override
    public void removeClass(String ... names) {
        for (String name : names) {
            String key = name.replaceAll("\\.", "/") + (name.endsWith(".class") ? "" : ".class");
            this.classes.remove(key);
        }
        this.resetClassloader();
    }

    @Override
    public void addReloadListener(IClassReloadListener ... listeners) {
        this.listenerSet.addAll(Arrays.asList(listeners));
    }

    @Override
    public void removeListener(IClassReloadListener ... listeners) {
        Arrays.asList(listeners).forEach(this.listenerSet::remove);
    }

    private void notifyListeners() {
        this.listenerSet.removeIf(listener -> listener.handleClassLoaderReload(this.secure));
    }

    protected void resetClassloader() {
        this.notify = this.secure != null;
        this.secure = new MemoryClassLoader(this.parent, this.classFilter, this.classes);
        this.compiler = new Compiler();
        this.compiler.setTargetVersion(8);
        this.compiler.setIClassLoader(new ClassLoaderIClassLoader(this.secure));
        this.compiler.setClassFileCreator(new MapResourceCreator(this.classes));
    }
}

