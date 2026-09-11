/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.crash.CrashReport
 *  net.minecraft.crash.CrashReportCategory
 *  net.minecraft.launchwrapper.IClassTransformer
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package io.github.legacymoddingmc.unimixins.compat.asm;

import io.github.legacymoddingmc.unimixins.compat.CompatCore;
import io.github.legacymoddingmc.unimixins.compat.CrashReportEnhancer;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class EnhanceCrashReportsTransformer
implements IClassTransformer {
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (basicClass == null) {
            return null;
        }
        if (name.equals("cpw.mods.fml.common.FMLCommonHandler")) {
            return EnhanceCrashReportsTransformer.transformFMLCommonHandler(basicClass);
        }
        return basicClass;
    }

    private static byte[] transformFMLCommonHandler(byte[] bytes) {
        CompatCore.LOGGER.info("Transforming FMLCommonHandler to add hook for enhancing crash reports");
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept((ClassVisitor)classNode, 0);
        for (MethodNode m : classNode.methods) {
            if (!m.name.equals("enhanceCrashReport")) continue;
            InsnNode injectionTarget = null;
            for (int i = 0; i < m.instructions.size(); ++i) {
                InsnNode in;
                AbstractInsnNode ain = m.instructions.get(i);
                if (!(ain instanceof InsnNode) || (in = (InsnNode)ain).getOpcode() != 177) continue;
                injectionTarget = in;
                break;
            }
            if (injectionTarget == null) continue;
            InsnList inject = new InsnList();
            inject.add((AbstractInsnNode)new VarInsnNode(25, 1));
            inject.add((AbstractInsnNode)new VarInsnNode(25, 2));
            inject.add((AbstractInsnNode)new MethodInsnNode(184, "io/github/legacymoddingmc/unimixins/compat/asm/EnhanceCrashReportsTransformer$Hooks", "postEnhanceCrashReport", "(Lnet/minecraft/crash/CrashReport;Lnet/minecraft/crash/CrashReportCategory;)V", false));
            m.instructions.insertBefore((AbstractInsnNode)injectionTarget, inject);
        }
        ClassWriter writer = new ClassWriter(3);
        classNode.accept((ClassVisitor)writer);
        return writer.toByteArray();
    }

    public static class Hooks {
        public static void postEnhanceCrashReport(CrashReport report, CrashReportCategory category) {
            CrashReportEnhancer.addMixinsToCrashReport(report, category);
        }
    }
}

