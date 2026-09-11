/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.discovery.ModCandidate
 *  cpw.mods.fml.common.discovery.ModDiscoverer
 *  net.minecraft.launchwrapper.IClassTransformer
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package io.github.legacymoddingmc.unimixins.compat.asm;

import cpw.mods.fml.common.discovery.ModCandidate;
import cpw.mods.fml.common.discovery.ModDiscoverer;
import io.github.legacymoddingmc.unimixins.compat.CompatCore;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class IgnoreDuplicateJarsTransformer
implements IClassTransformer {
    private static Set<File> uniMixinsJavaAgentJars;

    public static boolean wantsToRun() {
        return !IgnoreDuplicateJarsTransformer.getUniMixinsJavaAgentJars().isEmpty();
    }

    private static Set<File> getUniMixinsJavaAgentJars() {
        if (uniMixinsJavaAgentJars == null) {
            uniMixinsJavaAgentJars = new HashSet<File>();
            try {
                for (String arg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
                    String agent;
                    File agentFile;
                    String name;
                    if (!arg.startsWith("-javaagent:") || !(name = (agentFile = new File(agent = arg.substring("-javaagent:".length()))).getName()).toLowerCase().contains("unimixins")) continue;
                    uniMixinsJavaAgentJars.add(agentFile);
                }
            }
            catch (Exception e) {
                CompatCore.LOGGER.error("Failed to enumerate command line java agents");
                e.printStackTrace();
            }
        }
        return uniMixinsJavaAgentJars;
    }

    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (basicClass == null) {
            return null;
        }
        if (transformedName.equals("cpw.mods.fml.common.discovery.ModDiscoverer")) {
            basicClass = this.doTransformModDiscoverer(basicClass);
        }
        return basicClass;
    }

    private byte[] doTransformModDiscoverer(byte[] bytes) {
        CompatCore.LOGGER.info("IgnoreDuplicateJarsTransformer: Transforming ModDiscoverer#identifyMods to ignore duplicate UniMixins jars coming from Java agents.");
        try {
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(bytes);
            classReader.accept((ClassVisitor)classNode, 0);
            for (MethodNode m : classNode.methods) {
                if (!m.name.equals("identifyMods")) continue;
                InsnList patch = new InsnList();
                patch.add((AbstractInsnNode)new VarInsnNode(25, 0));
                patch.add((AbstractInsnNode)new MethodInsnNode(184, "io/github/legacymoddingmc/unimixins/compat/asm/IgnoreDuplicateJarsTransformer$Hooks", "preIdentifyMods", "(Lcpw/mods/fml/common/discovery/ModDiscoverer;)V"));
                m.instructions.insert(patch);
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
        public static void preIdentifyMods(ModDiscoverer dis) {
            try {
                List<ModCandidate> candidates = Hooks.getModCandidates(dis);
                HashMap<File, List> modCandidatesByFile = new HashMap<File, List>();
                for (ModCandidate modCandidate : candidates) {
                    modCandidatesByFile.computeIfAbsent(Hooks.getModContainer(modCandidate), x -> new ArrayList()).add(modCandidate);
                }
                for (Map.Entry entry : modCandidatesByFile.entrySet()) {
                    File file = (File)entry.getKey();
                    List mcs = (List)entry.getValue();
                    if (!IgnoreDuplicateJarsTransformer.getUniMixinsJavaAgentJars().contains(file)) continue;
                    Iterator it = mcs.iterator();
                    while (mcs.size() > 1 && it.hasNext()) {
                        ModCandidate mc = (ModCandidate)it.next();
                        if (!mc.isClasspath()) continue;
                        CompatCore.LOGGER.info("Removing duplicate Java agent mod candidate: " + file.getName());
                        candidates.remove(mc);
                        mcs.remove(mc);
                    }
                }
            }
            catch (Exception e) {
                CompatCore.LOGGER.error("Failed to remove duplicate mod candidates, the Mixin Java agent will cause a duplicate mod error. Add the UniMix jar as the java agent instead.");
                e.printStackTrace();
            }
        }

        private static List<ModCandidate> getModCandidates(ModDiscoverer dis) throws Exception {
            Field f = dis.getClass().getDeclaredField("candidates");
            f.setAccessible(true);
            return (List)f.get(dis);
        }

        private static File getModContainer(ModCandidate mc) throws Exception {
            Field f = ModCandidate.class.getDeclaredField("modContainer");
            f.setAccessible(true);
            return (File)f.get(mc);
        }
    }
}

