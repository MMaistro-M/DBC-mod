/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.autocomplete;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import noppes.npcs.client.gui.util.script.autocomplete.AutocompleteItem;
import noppes.npcs.client.gui.util.script.autocomplete.AutocompleteProvider;
import noppes.npcs.client.gui.util.script.autocomplete.CompletionWeigherChains;
import noppes.npcs.client.gui.util.script.autocomplete.JavaAutocompleteProvider;
import noppes.npcs.client.gui.util.script.autocomplete.UsageTracker;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.WeigherChain;
import noppes.npcs.client.gui.util.script.interpreter.field.EnumConstantInfo;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSFieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSMethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeRegistry;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.ScriptTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeChecker;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticField;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticMethod;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticType;
import noppes.npcs.config.ConfigScript;

public class JSAutocompleteProvider
extends JavaAutocompleteProvider {
    private final JSTypeRegistry registry = JSTypeRegistry.getInstance();

    public JSAutocompleteProvider() {
        if (!this.registry.isInitialized()) {
            this.registry.initializeFromResources();
        }
    }

    @Override
    public boolean canProvide(AutocompleteProvider.Context context) {
        return this.document != null && this.document.isJavaScript();
    }

    @Override
    protected void addMemberSuggestions(AutocompleteProvider.Context context, List<AutocompleteItem> items) {
        JSTypeInfo jsTypeInfo;
        JSTypeInfo objJSType;
        String receiverExpr = context.receiverExpression;
        if (receiverExpr == null || receiverExpr.isEmpty()) {
            return;
        }
        int resolvePos = this.getMemberAccessResolvePosition(context);
        TypeInfo receiverType = this.document.resolveExpressionType(receiverExpr, resolvePos);
        if (receiverType == null || !receiverType.isResolved()) {
            return;
        }
        SyntheticType syntheticType = this.document.getTypeResolver().getSyntheticType(receiverType.getSimpleName());
        if (syntheticType != null) {
            this.addSyntheticTypeSuggestions(syntheticType, items, context.methodsOnly);
            return;
        }
        boolean isStaticContext = this.isStaticAccess(receiverExpr, resolvePos);
        Class<?> javaClass = receiverType.getJavaClass();
        JSTypeInfo mergeRegistryType = javaClass != null && receiverType.getSyntheticMethods().isEmpty() && receiverType.getSyntheticFields().isEmpty() ? this.getMergeRegistryType(javaClass) : null;
        HashSet<String> addedMethods = new HashSet<String>();
        HashSet<String> addedFields = new HashSet<String>();
        for (MethodInfo method : receiverType.getAllMethods()) {
            String sig;
            if (isStaticContext && !Modifier.isStatic(method.getModifiers()) || !addedMethods.add(sig = method.getName() + "(" + method.getParameterCount() + ")")) continue;
            items.add(AutocompleteItem.fromMethod(method, context.methodsOnly));
        }
        if (mergeRegistryType != null) {
            this.addMethodsFromType(mergeRegistryType, receiverType, items, addedMethods, context.methodsOnly, isStaticContext);
        }
        if (!context.methodsOnly) {
            for (FieldInfo field : receiverType.getAllFields()) {
                if (isStaticContext && !Modifier.isStatic(field.getModifiers()) || !addedFields.add(field.getName())) continue;
                items.add(AutocompleteItem.fromField(field));
            }
            if (mergeRegistryType != null) {
                this.addFieldsFromType(mergeRegistryType, receiverType, items, addedFields, isStaticContext);
            }
        }
        if (isStaticContext && !context.methodsOnly) {
            for (TypeInfo nestedType : receiverType.getAllNestedTypes()) {
                items.add(AutocompleteItem.fromType(nestedType));
            }
        }
        if (!context.methodsOnly) {
            for (EnumConstantInfo enumConstant : receiverType.getEnumConstants().values()) {
                items.add(AutocompleteItem.fromField(enumConstant.getFieldInfo()));
            }
        }
        if (!(isStaticContext || javaClass != null && "java.lang.Object".equals(javaClass.getName()) || (objJSType = this.registry.getType("Object")) == null)) {
            for (JSMethodInfo m : objJSType.getMethods().values()) {
                String sig;
                if (m.isStatic() || !addedMethods.add(sig = m.getName() + "(" + m.getParameterCount() + ")")) continue;
                items.add(AutocompleteItem.fromJSMethod(m, receiverType, 1, context.methodsOnly));
            }
        }
        if (javaClass == null && !(receiverType instanceof ScriptTypeInfo) && (jsTypeInfo = receiverType.getJSTypeInfo()) != null) {
            this.addMethodsFromType(jsTypeInfo, receiverType, items, addedMethods, context.methodsOnly, isStaticContext);
            if (!context.methodsOnly && ConfigScript.ShowImplementationFieldsInAutocomplete) {
                this.addFieldsFromType(jsTypeInfo, receiverType, items, addedFields, isStaticContext);
            }
        }
    }

    private JSTypeInfo getMergeRegistryType(Class<?> javaClass) {
        return this.document.getTypeResolver().findJSTypeForJavaClass(javaClass);
    }

    private void addSyntheticTypeSuggestions(SyntheticType syntheticType, List<AutocompleteItem> items, boolean forMethodReference) {
        AutocompleteItem item;
        for (SyntheticMethod method : syntheticType.getMethods()) {
            item = AutocompleteItem.fromSyntheticMethod(method, syntheticType.getTypeInfo(), forMethodReference);
            items.add(item);
        }
        if (!forMethodReference) {
            for (SyntheticField field : syntheticType.getFields()) {
                item = AutocompleteItem.fromSyntheticField(field);
                items.add(item);
            }
        }
    }

    protected void addMethodsFromType(JSTypeInfo type, TypeInfo contextType, List<AutocompleteItem> items, Set<String> added) {
        this.addMethodsFromType(type, contextType, items, added, 0, false, false);
    }

    protected void addMethodsFromType(JSTypeInfo type, TypeInfo contextType, List<AutocompleteItem> items, Set<String> added, boolean forMethodReference) {
        this.addMethodsFromType(type, contextType, items, added, 0, forMethodReference, false);
    }

    protected void addMethodsFromType(JSTypeInfo type, TypeInfo contextType, List<AutocompleteItem> items, Set<String> added, boolean forMethodReference, boolean isStaticContext) {
        this.addMethodsFromType(type, contextType, items, added, 0, forMethodReference, isStaticContext);
    }

    private void addMethodsFromType(JSTypeInfo type, TypeInfo contextType, List<AutocompleteItem> items, Set<String> added, int depth) {
        this.addMethodsFromType(type, contextType, items, added, depth, false, false);
    }

    private void addMethodsFromType(JSTypeInfo type, TypeInfo contextType, List<AutocompleteItem> items, Set<String> added, int depth, boolean forMethodReference) {
        this.addMethodsFromType(type, contextType, items, added, depth, forMethodReference, false);
    }

    private void addMethodsFromType(JSTypeInfo type, TypeInfo contextType, List<AutocompleteItem> items, Set<String> added, int depth, boolean forMethodReference, boolean isStaticContext) {
        HashSet<String> baseNames = new HashSet<String>();
        for (String key : type.getMethods().keySet()) {
            String baseName = key.contains("$") ? key.substring(0, key.indexOf(36)) : key;
            if (added.contains(baseName)) continue;
            baseNames.add(baseName);
        }
        for (String baseName : baseNames) {
            added.add(baseName);
            ArrayList<JSMethodInfo> overloads = new ArrayList<JSMethodInfo>();
            if (type.getMethods().containsKey(baseName)) {
                overloads.add(type.getMethods().get(baseName));
            }
            int index = 1;
            while (type.getMethods().containsKey(baseName + "$" + index)) {
                overloads.add(type.getMethods().get(baseName + "$" + index));
                ++index;
            }
            for (JSMethodInfo method : overloads) {
                if (isStaticContext && !method.isStatic()) continue;
                items.add(AutocompleteItem.fromJSMethod(method, contextType, depth, forMethodReference));
            }
        }
        if (type.getResolvedParent() != null) {
            this.addMethodsFromType(type.getResolvedParent(), contextType, items, added, depth + 1, forMethodReference, isStaticContext);
        }
    }

    protected void addFieldsFromType(JSTypeInfo type, TypeInfo contextType, List<AutocompleteItem> items, Set<String> added) {
        this.addFieldsFromType(type, contextType, items, added, 0, false);
    }

    protected void addFieldsFromType(JSTypeInfo type, TypeInfo contextType, List<AutocompleteItem> items, Set<String> added, boolean isStaticContext) {
        this.addFieldsFromType(type, contextType, items, added, 0, isStaticContext);
    }

    private void addFieldsFromType(JSTypeInfo type, TypeInfo contextType, List<AutocompleteItem> items, Set<String> added, int depth) {
        this.addFieldsFromType(type, contextType, items, added, depth, false);
    }

    private void addFieldsFromType(JSTypeInfo type, TypeInfo contextType, List<AutocompleteItem> items, Set<String> added, int depth, boolean isStaticContext) {
        for (JSFieldInfo field : type.getFields().values()) {
            if (added.contains(field.getName()) || isStaticContext && !field.isStatic()) continue;
            added.add(field.getName());
            items.add(AutocompleteItem.fromJSField(field, contextType, depth));
        }
        if (type.getResolvedParent() != null) {
            this.addFieldsFromType(type.getResolvedParent(), contextType, items, added, depth + 1, isStaticContext);
        }
    }

    @Override
    protected void addUnimportedClassSuggestions(String prefix, List<AutocompleteItem> items) {
    }

    @Override
    protected void addLanguageUniqueSuggestions(AutocompleteProvider.Context context, List<AutocompleteItem> items) {
        super.addLanguageUniqueSuggestions(context, items);
        ArrayList<String> globalNames = new ArrayList<String>();
        globalNames.addAll(this.registry.getGlobalEngineObjects().keySet());
        globalNames.addAll(this.document.getEditorGlobals().keySet());
        for (String name : globalNames) {
            FieldInfo fieldInfo = this.document.resolveVariable(name, context.cursorPosition);
            if (fieldInfo == null || !fieldInfo.isResolved()) continue;
            items.add(AutocompleteItem.fromField(fieldInfo));
        }
        for (String importName : this.registry.getGlobalEngineImports().keySet()) {
            JSTypeInfo jsTypeInfo = this.registry.getGlobalImportType(importName);
            if (jsTypeInfo == null) continue;
            items.add(AutocompleteItem.fromType(TypeInfo.fromJSTypeInfo(jsTypeInfo)));
        }
        for (JSMethodInfo fn : this.registry.getGlobalEngineFunctions().values()) {
            items.add(AutocompleteItem.fromJSMethod(fn, 0));
        }
    }

    @Override
    protected UsageTracker getUsageTracker() {
        return UsageTracker.getJSInstance();
    }

    @Override
    protected WeigherChain getWeigherChain() {
        return CompletionWeigherChains.jsChain();
    }

    @Override
    public String[] getKeywords() {
        return TypeChecker.getJavaScriptKeywords();
    }
}

