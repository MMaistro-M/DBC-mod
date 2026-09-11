/*
 * Decompiled with CFR 0.152.
 */
package io.github.tox1cozz.mixinextras.utils;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;

public class CompatibilityHelper {
    private static final Constructor<InvalidInjectionException> INVALID_INJECTION_EXCEPTION_CONSTRUCTOR = Arrays.stream(InvalidInjectionException.class.getConstructors()).filter(it -> {
        Class<?>[] parameters = it.getParameterTypes();
        return parameters.length == 2 && parameters[0].isAssignableFrom(InjectionInfo.class) && parameters[1] == String.class;
    }).findAny().orElse(null);

    public static RuntimeException makeInvalidInjectionException(InjectionInfo info, String message) {
        try {
            return INVALID_INJECTION_EXCEPTION_CONSTRUCTOR.newInstance(info, message);
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

