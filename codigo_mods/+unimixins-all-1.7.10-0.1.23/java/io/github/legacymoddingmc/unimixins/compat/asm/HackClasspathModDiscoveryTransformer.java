/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.IClassTransformer
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 */
package io.github.legacymoddingmc.unimixins.compat.asm;

import io.github.legacymoddingmc.unimixins.compat.CompatCore;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class HackClasspathModDiscoveryTransformer
implements IClassTransformer {
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (basicClass == null) {
            return null;
        }
        if (transformedName.startsWith("org.objectweb.asm.")) {
            return basicClass;
        }
        if (transformedName.equals("cpw.mods.fml.common.discovery.ModDiscoverer")) {
            basicClass = this.doTransformModDiscoverer(basicClass);
        }
        return basicClass;
    }

    private byte[] doTransformModDiscoverer(byte[] bytes) {
        CompatCore.LOGGER.info("HackClasspathModDiscoveryTransformer: Transforming ModDiscoverer#findClasspathMods to ignore reparseable coremods.");
        try {
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(bytes);
            classReader.accept((ClassVisitor)classNode, 0);
            block2: for (MethodNode m : classNode.methods) {
                if (!m.name.equals("findClasspathMods")) continue;
                ListIterator it = m.instructions.iterator();
                while (it.hasNext()) {
                    AbstractInsnNode i = (AbstractInsnNode)it.next();
                    if (i.getOpcode() != 184) continue;
                    MethodInsnNode mi = (MethodInsnNode)i;
                    if (!mi.owner.equals("cpw/mods/fml/relauncher/CoreModManager") || !mi.name.equals("getReparseableCoremods") || !mi.desc.equals("()Ljava/util/List;")) continue;
                    m.instructions.insertBefore((AbstractInsnNode)mi, (AbstractInsnNode)new MethodInsnNode(184, "io/github/legacymoddingmc/unimixins/compat/asm/HackClasspathModDiscoveryTransformer$Hooks", "redirectGetReparseableCoremods", mi.desc));
                    it.remove();
                    continue block2;
                }
            }
            ClassWriter writer = new ClassWriter(3);
            classNode.accept((ClassVisitor)writer);
            return writer.toByteArray();
        }
        catch (Exception e) {
            e.printStackTrace();
            return bytes;
        }
    }

    public static class Hooks {
        public static List<String> redirectGetReparseableCoremods() {
            return Arrays.asList(new String[0]);
        }
    }
}

