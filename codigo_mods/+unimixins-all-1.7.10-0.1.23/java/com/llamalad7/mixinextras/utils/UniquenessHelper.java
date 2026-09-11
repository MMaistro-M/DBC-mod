/*
 * Decompiled with CFR 0.152.
 */
package com.llamalad7.mixinextras.utils;

import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.MethodNode;

public class UniquenessHelper {
    public static String getUniqueMethodName(ClassNode classNode, String name) {
        int counter = classNode.methods.size();
        while (true) {
            String candidate = name + '$' + counter;
            boolean isValid = true;
            for (MethodNode methodNode : classNode.methods) {
                if (!methodNode.name.equals(candidate)) continue;
                isValid = false;
                break;
            }
            if (isValid) {
                return candidate;
            }
            ++counter;
        }
    }
}

