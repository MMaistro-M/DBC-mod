/*
 * Decompiled with CFR 0.152.
 */
package com.llamalad7.mixinextras.expression.impl.ast.expressions;

import com.llamalad7.mixinextras.expression.impl.ExpressionSource;
import com.llamalad7.mixinextras.expression.impl.ast.expressions.Expression;
import com.llamalad7.mixinextras.expression.impl.ast.expressions.SimpleExpression;
import com.llamalad7.mixinextras.expression.impl.ast.identifiers.TypeIdentifier;
import com.llamalad7.mixinextras.expression.impl.flow.FlowValue;
import com.llamalad7.mixinextras.expression.impl.point.ExpressionContext;
import com.llamalad7.mixinextras.expression.impl.utils.ExpressionASMUtils;
import java.util.List;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.IntInsnNode;
import org.spongepowered.asm.lib.tree.MultiANewArrayInsnNode;
import org.spongepowered.asm.lib.tree.TypeInsnNode;

public class NewArrayExpression
extends SimpleExpression {
    public final TypeIdentifier innerType;
    public final List<Expression> dims;
    public final int blankDims;

    public NewArrayExpression(ExpressionSource src, TypeIdentifier innerType, List<Expression> dims, int blankDims) {
        super(src);
        this.innerType = innerType;
        this.dims = dims;
        this.blankDims = blankDims;
    }

    @Override
    protected boolean matchesImpl(FlowValue node, ExpressionContext ctx) {
        if (node.hasDecoration("mixinextras_persistent_arrayCreationInfo")) {
            return false;
        }
        Type newInnerType = this.getInnerType(node.getInsn());
        if (newInnerType == null) {
            return false;
        }
        int newBlankDims = this.getBlankDims(node.getInsn());
        if (newBlankDims + node.inputCount() < this.blankDims + this.dims.size()) {
            return false;
        }
        if (!this.innerType.matches(ctx.pool, newInnerType)) {
            return false;
        }
        return this.inputsMatch(node, ctx, ctx.allowIncompleteListInputs, this.dims.toArray(new Expression[0]));
    }

    private Type getInnerType(AbstractInsnNode insn) {
        switch (insn.getOpcode()) {
            case 189: {
                Type elementType = Type.getObjectType(((TypeInsnNode)insn).desc);
                return elementType.getSort() == 9 ? elementType.getElementType() : elementType;
            }
            case 188: {
                return ExpressionASMUtils.getNewArrayType((IntInsnNode)insn);
            }
            case 197: {
                return Type.getType(((MultiANewArrayInsnNode)insn).desc).getElementType();
            }
        }
        return null;
    }

    private int getBlankDims(AbstractInsnNode insn) {
        switch (insn.getOpcode()) {
            case 189: {
                Type elementType = Type.getObjectType(((TypeInsnNode)insn).desc);
                return elementType.getSort() == 9 ? elementType.getDimensions() : 0;
            }
            case 197: {
                MultiANewArrayInsnNode newArray = (MultiANewArrayInsnNode)insn;
                return Type.getType(newArray.desc).getDimensions() - newArray.dims;
            }
        }
        return 0;
    }
}

