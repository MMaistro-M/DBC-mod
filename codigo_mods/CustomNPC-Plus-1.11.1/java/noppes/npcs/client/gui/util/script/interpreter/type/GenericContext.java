/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.TypeParamInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.GenericTypeParser;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeResolver;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeStringNormalizer;

public final class GenericContext {
    private static final GenericContext EMPTY = new GenericContext(new HashMap<String, TypeInfo>(), new HashMap<String, TypeInfo>());
    private final Map<String, TypeInfo> appliedBindings;
    private final Map<String, TypeInfo> boundFallbacks;

    private GenericContext(Map<String, TypeInfo> appliedBindings, Map<String, TypeInfo> boundFallbacks) {
        this.appliedBindings = appliedBindings != null ? appliedBindings : new HashMap();
        this.boundFallbacks = boundFallbacks != null ? boundFallbacks : new HashMap();
    }

    public static GenericContext fromBindings(List<TypeParamInfo> declaredParams, List<TypeInfo> appliedArgs) {
        if (declaredParams == null || declaredParams.isEmpty()) {
            return EMPTY;
        }
        HashMap<String, TypeInfo> applied = new HashMap<String, TypeInfo>();
        if (appliedArgs != null) {
            int count = Math.min(declaredParams.size(), appliedArgs.size());
            for (int i = 0; i < count; ++i) {
                TypeParamInfo declared = declaredParams.get(i);
                TypeInfo arg = appliedArgs.get(i);
                if (declared == null || declared.getName() == null || declared.getName().isEmpty() || arg == null) continue;
                applied.put(declared.getName(), arg);
            }
        }
        HashMap<String, TypeInfo> bounds = new HashMap<String, TypeInfo>();
        for (TypeParamInfo declared : declaredParams) {
            String name;
            if (declared == null || (name = declared.getName()) == null || name.isEmpty()) continue;
            TypeInfo tp = TypeInfo.typeParameter(name, declared);
            TypeInfo effectiveBound = tp.getBoundType();
            bounds.put(name, effectiveBound != null ? effectiveBound : TypeInfo.OBJECT);
        }
        return new GenericContext(applied, bounds);
    }

    public static GenericContext forReceiver(TypeInfo receiverType) {
        if (receiverType != null && receiverType.isArray() && receiverType.getElementType() != null) {
            HashMap<String, TypeInfo> applied = new HashMap<String, TypeInfo>();
            applied.put("T", receiverType.getElementType());
            return new GenericContext(applied, new HashMap<String, TypeInfo>());
        }
        if (receiverType == null || !GenericContext.hasGenerics(receiverType)) {
            return EMPTY;
        }
        TypeInfo rawType = receiverType.getRawType();
        List<TypeParamInfo> declaredParams = rawType != null ? rawType.getTypeParams() : null;
        List<TypeInfo> appliedArgs = receiverType.getAppliedTypeArgs();
        HashMap<String, TypeInfo> applied = new HashMap<String, TypeInfo>();
        if (declaredParams != null && appliedArgs != null) {
            int count = Math.min(declaredParams.size(), appliedArgs.size());
            for (int i = 0; i < count; ++i) {
                TypeParamInfo declared = declaredParams.get(i);
                TypeInfo arg = appliedArgs.get(i);
                if (declared == null || declared.getName() == null || declared.getName().isEmpty() || arg == null) continue;
                applied.put(declared.getName(), arg);
            }
        }
        HashMap<String, TypeInfo> bounds = new HashMap<String, TypeInfo>();
        if (declaredParams != null) {
            for (TypeParamInfo declared : declaredParams) {
                String name;
                if (declared == null || (name = declared.getName()) == null || name.isEmpty()) continue;
                TypeInfo tp = TypeInfo.typeParameter(name, declared);
                TypeInfo effectiveBound = tp.getBoundType();
                bounds.put(name, effectiveBound != null ? effectiveBound : TypeInfo.OBJECT);
            }
        }
        return new GenericContext(applied, bounds);
    }

    public static boolean hasGenerics(TypeInfo type) {
        return type.isParameterized() || type.getRawType() != type || type.getTypeParams() != null && !type.getTypeParams().isEmpty();
    }

    public TypeInfo resolveTypeVariable(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        TypeInfo applied = this.appliedBindings.get(name);
        if (applied != null) {
            return applied;
        }
        return this.boundFallbacks.get(name);
    }

    public TypeInfo substitute(TypeInfo type) {
        String simple;
        String lookupKey;
        TypeInfo substitution;
        if (type == null) {
            return null;
        }
        if ((!type.isResolved() || type.isTypeParameter()) && (substitution = this.resolveTypeVariable(lookupKey = type.isTypeParameter() ? type.getTypeParameterName() : type.getSimpleName())) != null) {
            return substitution;
        }
        if (type.isParameterized()) {
            List<TypeInfo> originalArgs = type.getAppliedTypeArgs();
            ArrayList<TypeInfo> substitutedArgs = new ArrayList<TypeInfo>(originalArgs.size());
            boolean changed = false;
            for (TypeInfo arg : originalArgs) {
                TypeInfo substitutedArg = this.substitute(arg);
                substitutedArgs.add(substitutedArg);
                if (substitutedArg == arg) continue;
                changed = true;
            }
            if (changed) {
                return type.getRawType().parameterize(substitutedArgs);
            }
        }
        if ((simple = type.getSimpleName()) != null && simple.endsWith("[]")) {
            TypeStringNormalizer.ArraySplit split = TypeStringNormalizer.splitArraySuffixes(simple);
            String elementName = split.base;
            int dims = split.dimensions;
            TypeInfo elementSub = this.resolveTypeVariable(elementName);
            if (elementSub != null) {
                TypeInfo result = elementSub;
                for (int i = 0; i < dims; ++i) {
                    result = TypeInfo.arrayOf(result);
                }
                return result;
            }
        }
        return type;
    }

    public TypeInfo substituteString(String typeString, TypeResolver resolver) {
        if (typeString == null || typeString.trim().isEmpty()) {
            return null;
        }
        String normalized = TypeStringNormalizer.stripImportTypeSyntax(typeString);
        normalized = TypeStringNormalizer.pickPreferredUnionBranch(normalized);
        normalized = TypeStringNormalizer.stripNullableSuffix(normalized);
        TypeStringNormalizer.ArraySplit arraySplit = TypeStringNormalizer.splitArraySuffixes(normalized);
        String baseExpr = arraySplit.base;
        int dims = arraySplit.dimensions;
        String bareBase = GenericTypeParser.stripGenerics(baseExpr);
        TypeInfo direct = this.resolveTypeVariable(bareBase);
        if (direct != null) {
            TypeInfo result = direct;
            for (int i = 0; i < dims; ++i) {
                result = TypeInfo.arrayOf(result);
            }
            return result;
        }
        if (resolver == null) {
            return null;
        }
        TypeInfo resolved = resolver.resolveJSType(typeString);
        return this.substitute(resolved);
    }

    public TypeInfo substituteType(TypeInfo resolvedType, String rawTypeString, TypeResolver resolver) {
        TypeInfo fromString;
        if (resolvedType == null) {
            return null;
        }
        TypeInfo substituted = this.substitute(resolvedType);
        if (substituted != null && substituted.isResolved()) {
            return substituted;
        }
        if (rawTypeString != null && !rawTypeString.trim().isEmpty() && (fromString = this.substituteString(rawTypeString, resolver)) != null && fromString.isResolved()) {
            return fromString;
        }
        return substituted;
    }
}

