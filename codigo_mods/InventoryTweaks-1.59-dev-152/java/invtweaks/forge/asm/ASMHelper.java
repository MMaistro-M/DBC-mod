/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.IntInsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package invtweaks.forge.asm;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ASMHelper {
    public static void generateBooleanMethodConst(ClassNode clazz, String name, boolean retval) {
        MethodNode method = new MethodNode(262144, 4097, name, "()Z", null, null);
        InsnList code = method.instructions;
        code.add((AbstractInsnNode)new InsnNode(retval ? 4 : 3));
        code.add((AbstractInsnNode)new InsnNode(172));
        clazz.methods.add(method);
    }

    public static void generateIntegerMethodConst(ClassNode clazz, String name, short retval) {
        MethodNode method = new MethodNode(262144, 4097, name, "()I", null, null);
        InsnList code = method.instructions;
        if (retval >= -128 && retval <= 127) {
            code.add((AbstractInsnNode)new IntInsnNode(16, (int)retval));
        } else {
            code.add((AbstractInsnNode)new IntInsnNode(17, (int)retval));
        }
        code.add((AbstractInsnNode)new InsnNode(172));
        clazz.methods.add(method);
    }

    public static void generateSelfForwardingMethod(ClassNode clazz, String name, String forwardname, Type rettype) {
        MethodNode method = new MethodNode(262144, 4097, name, "()" + rettype.getDescriptor(), null, null);
        ASMHelper.populateSelfForwardingMethod(method, forwardname, rettype, Type.getObjectType((String)clazz.name));
        clazz.methods.add(method);
    }

    public static void generateStaticForwardingMethod(ClassNode clazz, String name, String forwardname, Type rettype, Type argtype) {
        MethodNode method = new MethodNode(262144, 4105, name, "()" + rettype.getDescriptor(), null, null);
        ASMHelper.populateSelfForwardingMethod(method, forwardname, rettype, argtype);
        clazz.methods.add(method);
    }

    public static void generateForwardingToStaticMethod(ClassNode clazz, String name, String forwardname, Type rettype, Type fowardtype) {
        MethodNode method = new MethodNode(262144, 4097, name, "()" + rettype.getDescriptor(), null, null);
        ASMHelper.populateForwardingToStaticMethod(method, forwardname, rettype, Type.getObjectType((String)clazz.name), fowardtype);
        clazz.methods.add(method);
    }

    public static void generateForwardingToStaticMethod(ClassNode clazz, String name, String forwardname, Type rettype, Type fowardtype, Type thistype) {
        MethodNode method = new MethodNode(262144, 4097, name, "()" + rettype.getDescriptor(), null, null);
        ASMHelper.populateForwardingToStaticMethod(method, forwardname, rettype, thistype, fowardtype);
        clazz.methods.add(method);
    }

    public static void replaceSelfForwardingMethod(MethodNode method, String forwardname, Type thistype) {
        Type methodType = Type.getMethodType((String)method.desc);
        method.instructions.clear();
        ASMHelper.populateSelfForwardingMethod(method, forwardname, methodType.getReturnType(), thistype);
    }

    public static void generateForwardingMethod(ClassNode clazz, String name, String forwardname, Type rettype, Type argtype) {
        MethodNode method = new MethodNode(262144, 4097, name, "()" + rettype.getDescriptor(), null, null);
        ASMHelper.populateForwardingMethod(method, forwardname, rettype, argtype, Type.getObjectType((String)clazz.name));
        clazz.methods.add(method);
    }

    public static void replaceForwardingMethod(MethodNode method, String forwardname, Type thistype) {
        Type methodType = Type.getMethodType((String)method.desc);
        method.instructions.clear();
        ASMHelper.populateForwardingMethod(method, forwardname, methodType.getReturnType(), methodType.getArgumentTypes()[0], thistype);
    }

    public static void populateForwardingToStaticMethod(MethodNode method, String forwardname, Type rettype, Type thistype, Type forwardtype) {
        InsnList code = method.instructions;
        code.add((AbstractInsnNode)new VarInsnNode(thistype.getOpcode(21), 0));
        code.add((AbstractInsnNode)new MethodInsnNode(184, forwardtype.getInternalName(), forwardname, Type.getMethodDescriptor((Type)rettype, (Type[])new Type[]{thistype})));
        code.add((AbstractInsnNode)new InsnNode(rettype.getOpcode(172)));
    }

    public static void populateSelfForwardingMethod(MethodNode method, String forwardname, Type rettype, Type thistype) {
        InsnList code = method.instructions;
        code.add((AbstractInsnNode)new VarInsnNode(thistype.getOpcode(21), 0));
        code.add((AbstractInsnNode)new MethodInsnNode(182, thistype.getInternalName(), forwardname, "()" + rettype.getDescriptor()));
        code.add((AbstractInsnNode)new InsnNode(rettype.getOpcode(172)));
    }

    public static void populateForwardingMethod(MethodNode method, String forwardname, Type rettype, Type argtype, Type thistype) {
        InsnList code = method.instructions;
        code.add((AbstractInsnNode)new VarInsnNode(argtype.getOpcode(21), 1));
        code.add((AbstractInsnNode)new MethodInsnNode(182, argtype.getInternalName(), forwardname, "()" + rettype.getDescriptor()));
        code.add((AbstractInsnNode)new InsnNode(rettype.getOpcode(172)));
    }
}

