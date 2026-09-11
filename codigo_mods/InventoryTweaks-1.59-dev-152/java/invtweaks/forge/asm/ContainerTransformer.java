/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper
 *  cpw.mods.fml.relauncher.FMLRelaunchLog
 *  net.minecraft.launchwrapper.IClassTransformer
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.AnnotationNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package invtweaks.forge.asm;

import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import cpw.mods.fml.relauncher.FMLRelaunchLog;
import invtweaks.forge.asm.ASMHelper;
import invtweaks.forge.asm.FMLPlugin;
import invtweaks.forge.asm.compatibility.CompatibilityConfigLoader;
import invtweaks.forge.asm.compatibility.ContainerInfo;
import invtweaks.forge.asm.compatibility.MethodInfo;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ContainerTransformer
implements IClassTransformer {
    public static final String VALID_INVENTORY_METHOD = "invtweaks$validInventory";
    public static final String VALID_CHEST_METHOD = "invtweaks$validChest";
    public static final String LARGE_CHEST_METHOD = "invtweaks$largeChest";
    public static final String SHOW_BUTTONS_METHOD = "invtweaks$showButtons";
    public static final String ROW_SIZE_METHOD = "invtweaks$rowSize";
    public static final String SLOT_MAP_METHOD = "invtweaks$slotMap";
    public static final String CONTAINER_CLASS_INTERNAL = "net/minecraft/inventory/Container";
    public static final String SLOT_MAPS_VANILLA_CLASS = "invtweaks/containers/VanillaSlotMaps";
    public static final String SLOT_MAPS_MODCOMPAT_CLASS = "invtweaks/containers/CompatibilitySlotMaps";
    public static final String ANNOTATION_CHEST_CONTAINER = "Linvtweaks/api/container/ChestContainer;";
    public static final String ANNOTATION_CHEST_CONTAINER_ROW_CALLBACK = "Linvtweaks/api/container/ChestContainer$RowSizeCallback;";
    public static final String ANNOTATION_CHEST_CONTAINER_LARGE_CALLBACK = "Linvtweaks/api/container/ChestContainer$IsLargeCallback;";
    public static final String ANNOTATION_INVENTORY_CONTAINER = "Linvtweaks/api/container/InventoryContainer;";
    public static final String ANNOTATION_IGNORE_CONTAINER = "Linvtweaks/api/container/IgnoreContainer;";
    public static final String ANNOTATION_CONTAINER_SECTION_CALLBACK = "Linvtweaks/api/container/ContainerSectionCallback;";
    private static Map<String, ContainerInfo> standardClasses = new HashMap<String, ContainerInfo>();
    private static Map<String, ContainerInfo> compatibilityClasses = new HashMap<String, ContainerInfo>();
    private static Map<String, ContainerInfo> configClasses = new HashMap<String, ContainerInfo>();
    private static String containerClassName;

    private void lateInit() {
        standardClasses.put("net.minecraft.inventory.ContainerPlayer", new ContainerInfo(true, true, false, ContainerTransformer.getVanillaSlotMapInfo("containerPlayerSlots")));
        standardClasses.put("net.minecraft.inventory.ContainerMerchant", new ContainerInfo(true, true, false));
        standardClasses.put("net.minecraft.inventory.ContainerRepair", new ContainerInfo(true, true, false, ContainerTransformer.getVanillaSlotMapInfo("containerPlayerSlots")));
        standardClasses.put("net.minecraft.inventory.ContainerHopper", new ContainerInfo(true, true, false));
        standardClasses.put("net.minecraft.inventory.ContainerBeacon", new ContainerInfo(true, true, false));
        standardClasses.put("net.minecraft.inventory.ContainerBrewingStand", new ContainerInfo(true, true, false, ContainerTransformer.getVanillaSlotMapInfo("containerBrewingSlots")));
        standardClasses.put("net.minecraft.inventory.ContainerWorkbench", new ContainerInfo(true, true, false, ContainerTransformer.getVanillaSlotMapInfo("containerWorkbenchSlots")));
        standardClasses.put("net.minecraft.inventory.ContainerEnchantment", new ContainerInfo(true, true, false, ContainerTransformer.getVanillaSlotMapInfo("containerEnchantmentSlots")));
        standardClasses.put("net.minecraft.inventory.ContainerFurnace", new ContainerInfo(true, true, false, ContainerTransformer.getVanillaSlotMapInfo("containerFurnaceSlots")));
        standardClasses.put("net.minecraft.inventory.ContainerDispenser", new ContainerInfo(true, false, true, 3, ContainerTransformer.getVanillaSlotMapInfo("containerChestDispenserSlots")));
        standardClasses.put("net.minecraft.inventory.ContainerChest", new ContainerInfo(true, false, true, ContainerTransformer.getVanillaSlotMapInfo("containerChestDispenserSlots")));
        compatibilityClasses.put("com.pahimar.ee3.inventory.ContainerAlchemicalBag", new ContainerInfo(true, false, true, true, 13));
        compatibilityClasses.put("com.pahimar.ee3.inventory.ContainerAlchemicalChest", new ContainerInfo(true, false, true, true, 13));
        compatibilityClasses.put("com.pahimar.ee3.inventory.ContainerPortableCrafting", new ContainerInfo(true, true, false, ContainerTransformer.getCompatiblitySlotMapInfo("ee3PortableCraftingSlots")));
        compatibilityClasses.put("codechicken.enderstorage.storage.item.ContainerEnderItemStorage", new ContainerInfo(true, false, true));
        compatibilityClasses.put("micdoodle8.mods.galacticraft.core.inventory.GCCoreContainerPlayer", new ContainerInfo(true, true, false, ContainerTransformer.getCompatiblitySlotMapInfo("galacticraftPlayerSlots")));
        try {
            configClasses = CompatibilityConfigLoader.load("config/InvTweaksCompatibility.xml");
        }
        catch (FileNotFoundException ex) {
            configClasses = new HashMap<String, ContainerInfo>();
        }
        catch (Exception ex) {
            configClasses = new HashMap<String, ContainerInfo>();
            ex.printStackTrace();
        }
    }

    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (containerClassName == null) {
            containerClassName = FMLPlugin.runtimeDeobfEnabled ? FMLDeobfuscatingRemapper.INSTANCE.unmap(CONTAINER_CLASS_INTERNAL) : CONTAINER_CLASS_INTERNAL;
            this.lateInit();
        }
        if (bytes == null || bytes.length == 0) {
            return bytes;
        }
        ClassReader cr = new ClassReader(bytes);
        ClassNode cn = new ClassNode(262144);
        ClassWriter cw = new ClassWriter(3);
        cr.accept((ClassVisitor)cn, 0);
        if ("net.minecraft.inventory.Container".equals(transformedName)) {
            FMLRelaunchLog.info((String)"InvTweaks: %s", (Object[])new Object[]{transformedName});
            ContainerTransformer.transformBaseContainer(cn);
            cn.accept((ClassVisitor)cw);
            return cw.toByteArray();
        }
        if ("net.minecraft.client.gui.inventory.ContainerCreative".equals(transformedName)) {
            FMLRelaunchLog.info((String)"InvTweaks: %s", (Object[])new Object[]{transformedName});
            ContainerTransformer.transformCreativeContainer(cn);
            cn.accept((ClassVisitor)cw);
            return cw.toByteArray();
        }
        ContainerInfo info = standardClasses.get(transformedName);
        if (info != null) {
            FMLRelaunchLog.info((String)"InvTweaks: %s", (Object[])new Object[]{transformedName});
            ContainerTransformer.transformContainer(cn, info);
            cn.accept((ClassVisitor)cw);
            return cw.toByteArray();
        }
        if ("invtweaks.InvTweaksObfuscation".equals(transformedName)) {
            FMLRelaunchLog.info((String)"InvTweaks: %s", (Object[])new Object[]{transformedName});
            Type containertype = Type.getObjectType((String)containerClassName);
            for (MethodNode method : cn.methods) {
                if ("isValidChest".equals(method.name)) {
                    ASMHelper.replaceSelfForwardingMethod(method, VALID_CHEST_METHOD, containertype);
                    continue;
                }
                if ("isValidInventory".equals(method.name)) {
                    ASMHelper.replaceSelfForwardingMethod(method, VALID_INVENTORY_METHOD, containertype);
                    continue;
                }
                if ("showButtons".equals(method.name)) {
                    ASMHelper.replaceSelfForwardingMethod(method, SHOW_BUTTONS_METHOD, containertype);
                    continue;
                }
                if ("getSpecialChestRowSize".equals(method.name)) {
                    ASMHelper.replaceSelfForwardingMethod(method, ROW_SIZE_METHOD, containertype);
                    continue;
                }
                if ("getContainerSlotMap".equals(method.name)) {
                    ASMHelper.replaceSelfForwardingMethod(method, SLOT_MAP_METHOD, containertype);
                    continue;
                }
                if (!"isLargeChest".equals(method.name)) continue;
                ASMHelper.replaceSelfForwardingMethod(method, LARGE_CHEST_METHOD, containertype);
            }
            cn.accept((ClassVisitor)cw);
            return cw.toByteArray();
        }
        info = configClasses.get(transformedName);
        if (info != null) {
            FMLRelaunchLog.info((String)"InvTweaks: %s", (Object[])new Object[]{transformedName});
            ContainerTransformer.transformContainer(cn, info);
            cn.accept((ClassVisitor)cw);
            return cw.toByteArray();
        }
        if (cn.visibleAnnotations != null) {
            for (AnnotationNode annotation : cn.visibleAnnotations) {
                if (annotation == null) continue;
                ContainerInfo apiInfo = null;
                if (ANNOTATION_CHEST_CONTAINER.equals(annotation.desc)) {
                    MethodNode large_method;
                    short rowSize = 9;
                    boolean isLargeChest = false;
                    boolean showButtons = true;
                    if (annotation.values != null) {
                        for (int i = 0; i < annotation.values.size(); i += 2) {
                            String valueName = (String)annotation.values.get(i);
                            Object value = annotation.values.get(i + 1);
                            if ("rowSize".equals(valueName)) {
                                rowSize = (short)((Integer)value).intValue();
                                continue;
                            }
                            if ("isLargeChest".equals(valueName)) {
                                isLargeChest = (Boolean)value;
                                continue;
                            }
                            if (!"showButtons".equals(valueName)) continue;
                            showButtons = (Boolean)value;
                        }
                    }
                    apiInfo = new ContainerInfo(showButtons, false, true, isLargeChest, rowSize);
                    MethodNode row_method = this.findAnnotatedMethod(cn, ANNOTATION_CHEST_CONTAINER_ROW_CALLBACK);
                    if (row_method != null) {
                        apiInfo.rowSizeMethod = new MethodInfo(Type.getMethodType((String)row_method.desc), Type.getObjectType((String)cn.name), row_method.name);
                    }
                    if ((large_method = this.findAnnotatedMethod(cn, ANNOTATION_CHEST_CONTAINER_LARGE_CALLBACK)) != null) {
                        apiInfo.largeChestMethod = new MethodInfo(Type.getMethodType((String)large_method.desc), Type.getObjectType((String)cn.name), large_method.name);
                    }
                } else if (ANNOTATION_INVENTORY_CONTAINER.equals(annotation.desc)) {
                    boolean showOptions = true;
                    if (annotation.values != null) {
                        for (int i = 0; i < annotation.values.size(); i += 2) {
                            String valueName = (String)annotation.values.get(i);
                            Object value = annotation.values.get(i + 1);
                            if (!"showOptions".equals(valueName)) continue;
                            showOptions = (Boolean)value;
                        }
                    }
                    apiInfo = new ContainerInfo(showOptions, true, false);
                } else if (ANNOTATION_IGNORE_CONTAINER.equals(annotation.desc)) {
                    ContainerTransformer.transformBaseContainer(cn);
                    cn.accept((ClassVisitor)cw);
                    return cw.toByteArray();
                }
                if (apiInfo == null) continue;
                MethodNode method = this.findAnnotatedMethod(cn, ANNOTATION_CONTAINER_SECTION_CALLBACK);
                if (method != null) {
                    apiInfo.slotMapMethod = new MethodInfo(Type.getMethodType((String)method.desc), Type.getObjectType((String)cn.name), method.name);
                }
                ContainerTransformer.transformContainer(cn, apiInfo);
                cn.accept((ClassVisitor)cw);
                return cw.toByteArray();
            }
        }
        if ((info = compatibilityClasses.get(transformedName)) != null) {
            FMLRelaunchLog.info((String)"InvTweaks: %s", (Object[])new Object[]{transformedName});
            ContainerTransformer.transformContainer(cn, info);
            cn.accept((ClassVisitor)cw);
            return cw.toByteArray();
        }
        if ("net.minecraft.client.gui.GuiTextField".equals(transformedName)) {
            FMLRelaunchLog.info((String)"InvTweaks: %s", (Object[])new Object[]{transformedName});
            ContainerTransformer.transformTextField(cn);
            cn.accept((ClassVisitor)cw);
            return cw.toByteArray();
        }
        return bytes;
    }

    private MethodNode findAnnotatedMethod(ClassNode cn, String annotationDesc) {
        for (MethodNode method : cn.methods) {
            if (method.visibleAnnotations == null) continue;
            for (AnnotationNode methodAnnotation : method.visibleAnnotations) {
                if (!annotationDesc.equals(methodAnnotation.desc)) continue;
                return method;
            }
        }
        return null;
    }

    public static void transformContainer(ClassNode clazz, ContainerInfo info) {
        ASMHelper.generateBooleanMethodConst(clazz, SHOW_BUTTONS_METHOD, info.showButtons);
        ASMHelper.generateBooleanMethodConst(clazz, VALID_INVENTORY_METHOD, info.validInventory);
        ASMHelper.generateBooleanMethodConst(clazz, VALID_CHEST_METHOD, info.validChest);
        if (info.largeChestMethod != null) {
            if (info.largeChestMethod.isStatic) {
                ASMHelper.generateForwardingToStaticMethod(clazz, LARGE_CHEST_METHOD, info.largeChestMethod.methodName, info.largeChestMethod.methodType.getReturnType(), info.largeChestMethod.methodClass, info.largeChestMethod.methodType.getArgumentTypes()[0]);
            } else {
                ASMHelper.generateSelfForwardingMethod(clazz, LARGE_CHEST_METHOD, info.largeChestMethod.methodName, info.largeChestMethod.methodType.getReturnType());
            }
        } else {
            ASMHelper.generateBooleanMethodConst(clazz, LARGE_CHEST_METHOD, info.largeChest);
        }
        if (info.rowSizeMethod != null) {
            if (info.rowSizeMethod.isStatic) {
                ASMHelper.generateForwardingToStaticMethod(clazz, ROW_SIZE_METHOD, info.rowSizeMethod.methodName, info.rowSizeMethod.methodType.getReturnType(), info.rowSizeMethod.methodClass, info.rowSizeMethod.methodType.getArgumentTypes()[0]);
            } else {
                ASMHelper.generateSelfForwardingMethod(clazz, ROW_SIZE_METHOD, info.rowSizeMethod.methodName, info.rowSizeMethod.methodType.getReturnType());
            }
        } else {
            ASMHelper.generateIntegerMethodConst(clazz, ROW_SIZE_METHOD, info.rowSize);
        }
        if (info.slotMapMethod.isStatic) {
            ASMHelper.generateForwardingToStaticMethod(clazz, SLOT_MAP_METHOD, info.slotMapMethod.methodName, info.slotMapMethod.methodType.getReturnType(), info.slotMapMethod.methodClass, info.slotMapMethod.methodType.getArgumentTypes()[0]);
        } else {
            ASMHelper.generateSelfForwardingMethod(clazz, SLOT_MAP_METHOD, info.slotMapMethod.methodName, info.slotMapMethod.methodType.getReturnType());
        }
    }

    public static void transformBaseContainer(ClassNode clazz) {
        ASMHelper.generateBooleanMethodConst(clazz, SHOW_BUTTONS_METHOD, false);
        ASMHelper.generateBooleanMethodConst(clazz, VALID_INVENTORY_METHOD, false);
        ASMHelper.generateBooleanMethodConst(clazz, VALID_CHEST_METHOD, false);
        ASMHelper.generateBooleanMethodConst(clazz, LARGE_CHEST_METHOD, false);
        ASMHelper.generateIntegerMethodConst(clazz, ROW_SIZE_METHOD, (short)9);
        ASMHelper.generateForwardingToStaticMethod(clazz, SLOT_MAP_METHOD, "unknownContainerSlots", Type.getObjectType((String)"java/util/Map"), Type.getObjectType((String)SLOT_MAPS_VANILLA_CLASS), Type.getObjectType((String)CONTAINER_CLASS_INTERNAL));
    }

    public static void transformCreativeContainer(ClassNode clazz) {
    }

    private static void transformTextField(ClassNode clazz) {
        for (MethodNode method : clazz.methods) {
            String unmappedName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(clazz.name, method.name, method.desc);
            String unmappedDesc = FMLDeobfuscatingRemapper.INSTANCE.mapMethodDesc(method.desc);
            if (!"func_146195_b".equals(unmappedName) || !"(Z)V".equals(unmappedDesc)) continue;
            InsnList code = method.instructions;
            AbstractInsnNode returnNode = null;
            for (AbstractInsnNode insn : code) {
                if (insn.getOpcode() != 177) continue;
                returnNode = insn;
                break;
            }
            if (returnNode != null) {
                code.insertBefore(returnNode, (AbstractInsnNode)new VarInsnNode(21, 1));
                code.insertBefore(returnNode, (AbstractInsnNode)new MethodInsnNode(184, "invtweaks/forge/InvTweaksMod", "setTextboxModeStatic", "(Z)V"));
                FMLRelaunchLog.info((String)"InvTweaks: successfully transformed setFocused/func_146195_b", (Object[])new Object[0]);
                continue;
            }
            FMLRelaunchLog.severe((String)"InvTweaks: unable to find return in setFocused/func_146195_b", (Object[])new Object[0]);
        }
    }

    public static MethodInfo getCompatiblitySlotMapInfo(String name) {
        return ContainerTransformer.getSlotMapInfo(Type.getObjectType((String)SLOT_MAPS_MODCOMPAT_CLASS), name, true);
    }

    public static MethodInfo getVanillaSlotMapInfo(String name) {
        return ContainerTransformer.getSlotMapInfo(Type.getObjectType((String)SLOT_MAPS_VANILLA_CLASS), name, true);
    }

    public static MethodInfo getSlotMapInfo(Type mClass, String name, boolean isStatic) {
        return new MethodInfo(Type.getMethodType((Type)Type.getObjectType((String)"java/util/Map"), (Type[])new Type[]{Type.getObjectType((String)containerClassName)}), mClass, name, isStatic);
    }
}

