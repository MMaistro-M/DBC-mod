/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.commons.compiler;

import java.util.ArrayList;
import java.util.ServiceLoader;
import org.codehaus.commons.compiler.ICompilerFactory;
import org.codehaus.commons.nullanalysis.Nullable;

public final class CompilerFactoryFactory {
    @Nullable
    private static ICompilerFactory defaultCompilerFactory;

    private CompilerFactoryFactory() {
    }

    @Deprecated
    public static ICompilerFactory getDefaultCompilerFactory() throws Exception {
        return CompilerFactoryFactory.getDefaultCompilerFactory(Thread.currentThread().getContextClassLoader());
    }

    public static ICompilerFactory getDefaultCompilerFactory(ClassLoader classLoader) throws Exception {
        if (defaultCompilerFactory != null) {
            return defaultCompilerFactory;
        }
        ICompilerFactory[] allCompilerFactories = CompilerFactoryFactory.getAllCompilerFactories(classLoader);
        if (allCompilerFactories.length == 0) {
            throw new ClassNotFoundException("No implementation of org.codehaus.commons.compiler could be loaded. Typically, you'd have  \"janino.jar\", or \"commons-compiler-jdk.jar\", or both on the classpath, and use the \"ClassLoader.getSystemClassLoader\" to load them.");
        }
        ICompilerFactory cf = allCompilerFactories[0];
        defaultCompilerFactory = allCompilerFactories[0];
        return defaultCompilerFactory;
    }

    @Deprecated
    public static ICompilerFactory[] getAllCompilerFactories() throws Exception {
        return CompilerFactoryFactory.getAllCompilerFactories(Thread.currentThread().getContextClassLoader());
    }

    public static ICompilerFactory[] getAllCompilerFactories(ClassLoader classLoader) throws Exception {
        ArrayList<ICompilerFactory> factories = new ArrayList<ICompilerFactory>();
        for (ICompilerFactory cf : ServiceLoader.load(ICompilerFactory.class, classLoader)) {
            factories.add(cf);
        }
        return factories.toArray(new ICompilerFactory[factories.size()]);
    }

    @Deprecated
    public static ICompilerFactory getCompilerFactory(String compilerFactoryClassName) throws Exception {
        return CompilerFactoryFactory.getCompilerFactory(compilerFactoryClassName, Thread.currentThread().getContextClassLoader());
    }

    public static ICompilerFactory getCompilerFactory(String compilerFactoryClassName, ClassLoader classLoader) throws Exception {
        return (ICompilerFactory)classLoader.loadClass(compilerFactoryClassName).newInstance();
    }

    public static String getSpecificationVersion() {
        return CompilerFactoryFactory.class.getPackage().getSpecificationVersion();
    }
}

