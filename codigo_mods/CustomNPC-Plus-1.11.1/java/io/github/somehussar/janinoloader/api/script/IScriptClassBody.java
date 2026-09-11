/*
 * Decompiled with CFR 0.152.
 */
package io.github.somehussar.janinoloader.api.script;

import io.github.somehussar.janinoloader.api.IClassReloadListener;
import java.io.IOException;
import org.codehaus.commons.compiler.CompileException;

public interface IScriptClassBody<DesiredType>
extends IClassReloadListener {
    public DesiredType get();

    public void setScript(String var1) throws CompileException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    public void assertCompiled() throws CompileException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    public void prepareToUnload();

    @FunctionalInterface
    public static interface ReloadDelegate<R> {
        public R apply(R var1, R var2, ClassLoader var3);
    }

    @FunctionalInterface
    public static interface InstanceDelegate<R> {
        public R apply(Class<? extends R> var1);
    }
}

