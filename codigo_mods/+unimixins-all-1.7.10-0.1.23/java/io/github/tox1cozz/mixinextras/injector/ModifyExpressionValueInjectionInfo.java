/*
 * Decompiled with CFR 0.152.
 */
package io.github.tox1cozz.mixinextras.injector;

import io.github.tox1cozz.mixinextras.injector.ModifyExpressionValue;
import io.github.tox1cozz.mixinextras.injector.ModifyExpressionValueInjector;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;

@InjectionInfo.AnnotationType(value=ModifyExpressionValue.class)
@InjectionInfo.HandlerPrefix(value="modifyExpressionValue")
public class ModifyExpressionValueInjectionInfo
extends InjectionInfo {
    public ModifyExpressionValueInjectionInfo(MixinTargetContext mixin, MethodNode method, AnnotationNode annotation) {
        super(mixin, method, annotation);
    }

    @Override
    protected Injector parseInjector(AnnotationNode injectAnnotation) {
        return new ModifyExpressionValueInjector(this);
    }
}

