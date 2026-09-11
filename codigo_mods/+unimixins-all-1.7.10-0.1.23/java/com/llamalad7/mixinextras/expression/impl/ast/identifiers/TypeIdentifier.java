/*
 * Decompiled with CFR 0.152.
 */
package com.llamalad7.mixinextras.expression.impl.ast.identifiers;

import com.llamalad7.mixinextras.expression.impl.pool.IdentifierPool;
import org.spongepowered.asm.lib.Type;

public interface TypeIdentifier {
    public boolean matches(IdentifierPool var1, Type var2);
}

