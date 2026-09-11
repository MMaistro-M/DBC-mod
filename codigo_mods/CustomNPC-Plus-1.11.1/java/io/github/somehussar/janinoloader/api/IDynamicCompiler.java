/*
 * Decompiled with CFR 0.152.
 */
package io.github.somehussar.janinoloader.api;

import io.github.somehussar.janinoloader.api.IClassReloadListener;
import java.io.IOException;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.util.resource.Resource;

public interface IDynamicCompiler {
    public void compileClass(Resource ... var1) throws CompileException, IOException;

    public void recompileClass(Resource ... var1) throws CompileException, IOException;

    public void removeClass(String ... var1);

    public void addReloadListener(IClassReloadListener ... var1);

    public void removeListener(IClassReloadListener ... var1);

    public ClassLoader getClassLoader();
}

