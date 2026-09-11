/*
 * Decompiled with CFR 0.152.
 */
package io.github.tox1cozz.mixinextras.injector;

import io.github.tox1cozz.mixinextras.injector.WrapWithCondition;
import io.github.tox1cozz.mixinextras.injector.WrapWithConditionInjector;
import java.util.List;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;

@InjectionInfo.AnnotationType(value=WrapWithCondition.class)
@InjectionInfo.HandlerPrefix(value="wrapWithCondition")
public class WrapWithConditionInjectionInfo
extends InjectionInfo {
    static final String POPPED_OPERATION_DECORATOR = "mixinextras_operationIsImmediatelyPopped";

    public WrapWithConditionInjectionInfo(MixinTargetContext mixin, MethodNode method, AnnotationNode annotation) {
        super(mixin, method, annotation);
    }

    @Override
    protected Injector parseInjector(AnnotationNode injectAnnotation) {
        return new WrapWithConditionInjector(this);
    }

    @Override
    public void prepare() {
        super.prepare();
        for (List nodeList : this.targetNodes.values()) {
            for (InjectionNodes.InjectionNode node : nodeList) {
                Type returnType;
                AbstractInsnNode currentTarget = node.getCurrentTarget();
                if (!(currentTarget instanceof MethodInsnNode) || !this.isTypePoppedByInstruction(returnType = Type.getReturnType(((MethodInsnNode)currentTarget).desc), currentTarget.getNext())) continue;
                node.decorate(POPPED_OPERATION_DECORATOR, true);
            }
        }
    }

    private boolean isTypePoppedByInstruction(Type type, AbstractInsnNode insn) {
        switch (type.getSize()) {
            case 2: {
                return insn.getOpcode() == 88;
            }
            case 1: {
                return insn.getOpcode() == 87;
            }
        }
        return false;
    }
}

