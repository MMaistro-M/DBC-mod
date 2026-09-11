/*
 * Decompiled with CFR 0.152.
 */
package io.github.tox1cozz.mixinextras.injector;

import io.github.tox1cozz.mixinextras.injector.ModifyReturnValue;
import io.github.tox1cozz.mixinextras.injector.ModifyReturnValueInjector;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;

@InjectionInfo.AnnotationType(value=ModifyReturnValue.class)
@InjectionInfo.HandlerPrefix(value="modifyReturnValue")
public class ModifyReturnValueInjectionInfo
extends InjectionInfo {
    public ModifyReturnValueInjectionInfo(MixinTargetContext mixin, MethodNode method, AnnotationNode annotation) {
        super(mixin, method, annotation);
    }

    @Override
    protected Injector parseInjector(AnnotationNode injectAnnotation) {
        return new ModifyReturnValueInjector(this);
    }
}

