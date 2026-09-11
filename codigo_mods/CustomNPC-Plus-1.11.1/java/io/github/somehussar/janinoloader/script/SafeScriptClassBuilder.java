/*
 * Decompiled with CFR 0.152.
 */
package io.github.somehussar.janinoloader.script;

import io.github.somehussar.janinoloader.api.IDynamicCompiler;
import io.github.somehussar.janinoloader.api.script.IScriptBodyBuilder;
import io.github.somehussar.janinoloader.api.script.IScriptClassBody;
import io.github.somehussar.janinoloader.script.SafeScriptClassBody;

public class SafeScriptClassBuilder<DesiredType>
implements IScriptBodyBuilder<DesiredType> {
    private final Class<DesiredType> clazz;
    private final IDynamicCompiler compiler;
    private String[] importList = new String[0];
    private Class<?>[] implementedClasses = new Class[0];
    private String rawScript;
    private IScriptClassBody.InstanceDelegate<DesiredType> instanceDelegate;
    private IScriptClassBody.ReloadDelegate<DesiredType> reloadDelegate;

    public SafeScriptClassBuilder(Class<DesiredType> clazz, IDynamicCompiler compiler) {
        assert (clazz != null);
        assert (compiler != null);
        this.clazz = clazz;
        this.compiler = compiler;
    }

    @Override
    public IScriptBodyBuilder<DesiredType> setDefaultImports(String ... imports) {
        if (imports == null) {
            imports = new String[]{};
        }
        this.importList = imports;
        return this;
    }

    @Override
    public IScriptBodyBuilder<DesiredType> setImplementedTypes(Class<?> ... interfaces) {
        if (interfaces == null) {
            interfaces = new Class[]{};
        }
        this.implementedClasses = interfaces;
        return this;
    }

    @Override
    public IScriptBodyBuilder<DesiredType> setScript(String script) {
        assert (script != null);
        this.rawScript = script;
        return this;
    }

    @Override
    public IScriptBodyBuilder<DesiredType> setInstanceDelegate(IScriptClassBody.InstanceDelegate<DesiredType> delegate) {
        this.instanceDelegate = delegate;
        return this;
    }

    @Override
    public IScriptClassBody<DesiredType> build() {
        return new SafeScriptClassBody<DesiredType>(this.clazz, this.compiler, this.importList, this.implementedClasses, this.rawScript, this.instanceDelegate, this.reloadDelegate);
    }

    @Override
    public IScriptBodyBuilder<DesiredType> setReloadDelegate(IScriptClassBody.ReloadDelegate<DesiredType> delegate) {
        this.reloadDelegate = delegate;
        return this;
    }
}

