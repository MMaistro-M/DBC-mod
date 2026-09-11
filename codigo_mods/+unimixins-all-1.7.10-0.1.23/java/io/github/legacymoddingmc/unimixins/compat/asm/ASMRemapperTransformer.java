/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.primitives.Bytes
 *  net.minecraft.launchwrapper.IClassTransformer
 *  net.minecraft.launchwrapper.Launch
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.commons.Remapper
 *  org.objectweb.asm.commons.RemappingClassAdapter
 *  org.objectweb.asm.tree.AnnotationNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.MethodNode
 */
package io.github.legacymoddingmc.unimixins.compat.asm;

import com.google.common.primitives.Bytes;
import io.github.legacymoddingmc.unimixins.compat.CompatCore;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import org.apache.commons.lang3.StringUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.commons.RemappingClassAdapter;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class ASMRemapperTransformer
implements IClassTransformer {
    private static final String ASM_PACKAGE_UNSHADED = "org/objectweb/asm/";
    private static final String ASM_PACKAGE_LEGACY = "org/spongepowered/asm/lib/";
    private static final String ASM_PACKAGE_MBL = "org/spongepowered/libraries/org/objectweb/asm/";
    private static final List<String> ASM_PACKAGE_PREFIXES = Arrays.asList("org/objectweb/asm/", "org/spongepowered/asm/lib/", "org/spongepowered/libraries/org/objectweb/asm/");
    private static final List<byte[]> SHADED_ASM_PACKAGE_PREFIXES_RAW = Arrays.asList("org/spongepowered/asm/lib/".getBytes(StandardCharsets.UTF_8), "org/spongepowered/libraries/org/objectweb/asm/".getBytes(StandardCharsets.UTF_8));
    private static String realASMPackagePrefix;
    private static List<byte[]> wrongASMPackagePrefixesRaw;
    private static List<String> wrongASMPackagePrefixes;

    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (basicClass == null) {
            return null;
        }
        if (transformedName.startsWith("io.github.legacymoddingmc.unimixins.compat.asm.") || transformedName.startsWith("com.google.") || transformedName.startsWith("org.apache.") || transformedName.startsWith("org.objectweb.asm.")) {
            return basicClass;
        }
        boolean foundWrongAsm = ASMRemapperTransformer.containsAnyPattern(basicClass, ASMRemapperTransformer.getWrongASMPackagePrefixesRaw());
        if (!foundWrongAsm) {
            return basicClass;
        }
        boolean foundShadedAsm = ASMRemapperTransformer.containsAnyPattern(basicClass, SHADED_ASM_PACKAGE_PREFIXES_RAW);
        boolean doRemap = foundShadedAsm;
        if (!doRemap) {
            ClassNode classNode = new ClassNode();
            ClassReader classReaderForNode = new ClassReader(basicClass);
            classReaderForNode.accept((ClassVisitor)classNode, 0);
            if (classNode.interfaces != null) {
                for (String itf : classNode.interfaces) {
                    if (!itf.equals("org/spongepowered/asm/mixin/extensibility/IMixinConfigPlugin")) continue;
                    doRemap = true;
                    break;
                }
            }
            if (!doRemap && classNode.visibleAnnotations != null) {
                for (AnnotationNode ann : classNode.visibleAnnotations) {
                    if (!ann.desc.equals("Lio/github/legacymoddingmc/unimixins/compat/api/RemapASMForMixin;")) continue;
                    doRemap = true;
                    break;
                }
            }
        }
        if (doRemap) {
            ClassReader classReader = new ClassReader(basicClass);
            CompatCore.LOGGER.info("Transforming class " + transformedName + " to fit current mixin environment.");
            ClassWriter classWriter = new ClassWriter(1);
            SpongepoweredASMRemappingAdapter remapAdapter = new SpongepoweredASMRemappingAdapter(classWriter);
            classReader.accept((ClassVisitor)remapAdapter, 8);
            basicClass = classWriter.toByteArray();
        }
        return basicClass;
    }

    private static boolean containsAnyPattern(byte[] array, List<byte[]> patterns) {
        if (array == null) {
            return false;
        }
        for (byte[] pattern : patterns) {
            if (Bytes.indexOf((byte[])array, (byte[])pattern) == -1) continue;
            return true;
        }
        return false;
    }

    private static String getRealASMPackagePrefix() {
        if (realASMPackagePrefix == null) {
            try {
                ClassReader cr = new ClassReader(Launch.classLoader.getClassBytes("org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin"));
                ClassNode cn = new ClassNode();
                cr.accept((ClassVisitor)cn, 0);
                for (MethodNode m : cn.methods) {
                    if (!m.name.equals("preApply")) continue;
                    int classNodeDescStart = StringUtils.ordinalIndexOf(m.desc, "L", 2);
                    int classNodeDescEnd = StringUtils.ordinalIndexOf(m.desc, "L", 3);
                    String classNodeName = m.desc.substring(classNodeDescStart + 1, classNodeDescEnd - 1);
                    realASMPackagePrefix = classNodeName.substring(0, classNodeName.indexOf("tree/ClassNode"));
                    break;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to determine real package name Mixin's shaded ASM.");
            }
            finally {
                if (realASMPackagePrefix == null) {
                    realASMPackagePrefix = "UNKNOWN";
                }
                CompatCore.LOGGER.debug("Resolved real package prefix to: " + realASMPackagePrefix);
            }
        }
        return realASMPackagePrefix;
    }

    private static List<String> getWrongASMPackagePrefixes() {
        if (wrongASMPackagePrefixes == null) {
            wrongASMPackagePrefixes = ASM_PACKAGE_PREFIXES.stream().filter(x -> !x.equals(ASMRemapperTransformer.getRealASMPackagePrefix())).collect(Collectors.toList());
        }
        return wrongASMPackagePrefixes;
    }

    private static List<byte[]> getWrongASMPackagePrefixesRaw() {
        if (wrongASMPackagePrefixesRaw == null) {
            wrongASMPackagePrefixesRaw = ASMRemapperTransformer.getWrongASMPackagePrefixes().stream().map(x -> x.getBytes(StandardCharsets.UTF_8)).collect(Collectors.toList());
        }
        return wrongASMPackagePrefixesRaw;
    }

    private static class SpongepoweredASMRemapper
    extends Remapper {
        public static final Remapper INSTANCE = new SpongepoweredASMRemapper();

        private SpongepoweredASMRemapper() {
        }

        public String map(String typeName) {
            for (String s : ASM_PACKAGE_PREFIXES) {
                if (!typeName.startsWith(s)) continue;
                String newName = ASMRemapperTransformer.getRealASMPackagePrefix() + typeName.substring(s.length());
                return newName;
            }
            return super.map(typeName);
        }
    }

    private static class SpongepoweredASMRemappingAdapter
    extends RemappingClassAdapter {
        public SpongepoweredASMRemappingAdapter(ClassWriter classWriter) {
            super((ClassVisitor)classWriter, SpongepoweredASMRemapper.INSTANCE);
        }
    }
}

