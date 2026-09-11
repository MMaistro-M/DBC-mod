/*
 * Decompiled with CFR 0.152.
 */
package io.github.somehussar.janinoloader.api.script;

import io.github.somehussar.janinoloader.api.IDynamicCompiler;
import io.github.somehussar.janinoloader.api.script.IScriptClassBody;
import io.github.somehussar.janinoloader.script.SafeScriptClassBuilder;

public interface IScriptBodyBuilder<DesiredType> {
    public static <T> IScriptBodyBuilder<T> getBuilder(Class<T> targetClass, IDynamicCompiler compiler) {
        return new SafeScriptClassBuilder<T>(targetClass, compiler);
    }

    public IScriptBodyBuilder<DesiredType> setDefaultImports(String ... var1);

    public IScriptBodyBuilder<DesiredType> setImplementedTypes(Class<?> ... var1);

    public IScriptBodyBuilder<DesiredType> setScript(String var1);

    public IScriptBodyBuilder<DesiredType> setInstanceDelegate(IScriptClassBody.InstanceDelegate<DesiredType> var1);

    public IScriptClassBody<DesiredType> build();

    public IScriptBodyBuilder<DesiredType> setReloadDelegate(IScriptClassBody.ReloadDelegate<DesiredType> var1);
}

