/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.autocomplete;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import noppes.npcs.client.gui.util.script.autocomplete.AutocompleteItem;
import noppes.npcs.client.gui.util.script.autocomplete.AutocompleteProvider;
import noppes.npcs.client.gui.util.script.autocomplete.CompletionWeigherChains;
import noppes.npcs.client.gui.util.script.autocomplete.UsageTracker;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.ScoringContext;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.WeigherChain;
import noppes.npcs.client.gui.util.script.interpreter.ScriptDocument;
import noppes.npcs.client.gui.util.script.interpreter.field.EnumConstantInfo;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenType;
import noppes.npcs.client.gui.util.script.interpreter.type.ScriptTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeChecker;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeResolver;

public class JavaAutocompleteProvider
implements AutocompleteProvider {
    protected ScriptDocument document;

    public void setDocument(ScriptDocument document) {
        this.document = document;
    }

    @Override
    public boolean canProvide(AutocompleteProvider.Context context) {
        return this.document != null && !this.document.isJavaScript();
    }

    @Override
    public List<AutocompleteItem> getSuggestions(AutocompleteProvider.Context context) {
        TypeInfo receiverType;
        int resolvePos;
        ArrayList<AutocompleteItem> items = new ArrayList<AutocompleteItem>();
        if (this.document == null) {
            return items;
        }
        String ownerFullName = null;
        boolean isStaticContext = false;
        if (context.isMemberAccess && context.receiverExpression != null) {
            resolvePos = this.getMemberAccessResolvePosition(context);
            receiverType = this.document.resolveExpressionType(context.receiverExpression, resolvePos);
            if (receiverType != null && receiverType.isResolved()) {
                ownerFullName = receiverType.getFullName();
            }
            isStaticContext = this.isStaticAccess(context.receiverExpression, resolvePos);
        }
        if (context.isMemberAccess) {
            this.addMemberSuggestions(context, items);
        } else {
            this.addScopeSuggestions(context, items);
        }
        if (context.methodsOnly) {
            items.removeIf(item -> item.getKind() != AutocompleteItem.Kind.METHOD);
            if (context.receiverExpression != null && (receiverType = this.document.resolveExpressionType(context.receiverExpression, resolvePos = this.getMemberAccessResolvePosition(context))) != null && receiverType.isResolved() && isStaticContext) {
                items.add(new AutocompleteItem.Builder().name("new").insertText("new").kind(AutocompleteItem.Kind.KEYWORD).typeLabel("constructor").signature(receiverType.getSimpleName() + "::new").build());
            }
        }
        this.filterAndScore(items, context.prefix, context.isMemberAccess, isStaticContext, ownerFullName);
        return items;
    }

    protected void addMemberSuggestions(AutocompleteProvider.Context context, List<AutocompleteItem> items) {
        String receiverExpr = context.receiverExpression;
        if (receiverExpr == null || receiverExpr.isEmpty()) {
            return;
        }
        int resolvePos = this.getMemberAccessResolvePosition(context);
        TypeInfo receiverType = this.document.resolveExpressionType(receiverExpr, resolvePos);
        if (receiverType == null || !receiverType.isResolved()) {
            return;
        }
        boolean isStaticContext = this.isStaticAccess(receiverExpr, resolvePos);
        HashSet<String> addedMethods = new HashSet<String>();
        HashSet<String> addedFields = new HashSet<String>();
        for (MethodInfo method : receiverType.getAllMethods()) {
            String sig;
            if (isStaticContext && !Modifier.isStatic(method.getModifiers()) || !addedMethods.add(sig = method.getName() + "(" + method.getParameterCount() + ")")) continue;
            items.add(AutocompleteItem.fromMethod(method, context.methodsOnly));
        }
        if (!context.methodsOnly) {
            for (FieldInfo field : receiverType.getAllFields()) {
                if (isStaticContext && !Modifier.isStatic(field.getModifiers()) || !addedFields.add(field.getName())) continue;
                items.add(AutocompleteItem.fromField(field));
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
    }

    protected int getMemberAccessResolvePosition(AutocompleteProvider.Context context) {
        int i;
        if (context == null || context.text == null || context.text.isEmpty()) {
            return 0;
        }
        int pos = Math.max(0, Math.min(context.prefixStart, context.text.length()));
        for (i = Math.min(pos - 1, context.text.length() - 1); i >= 0 && Character.isWhitespace(context.text.charAt(i)); --i) {
        }
        if (i >= 0 && context.text.charAt(i) == '.') {
            return i;
        }
        return Math.max(0, Math.min(context.prefixStart, context.text.length()));
    }

    /*
     * WARNING - void declaration
     */
    protected void addScopeSuggestions(AutocompleteProvider.Context context, List<AutocompleteItem> items) {
        int pos = context.cursorPosition;
        MethodInfo containingMethod = this.document.findContainingMethod(pos);
        if (containingMethod != null) {
            List<FieldInfo> available = this.document.getAvailableVariablesAt(pos);
            HashSet<String> hashSet = new HashSet<String>();
            Iterator iterator = available.iterator();
            while (iterator.hasNext()) {
                FieldInfo fieldInfo = (FieldInfo)iterator.next();
                if (!fieldInfo.isLocal() && !fieldInfo.isParameter() || !hashSet.add(fieldInfo.getName())) continue;
                items.add(AutocompleteItem.fromField(fieldInfo));
            }
        }
        for (FieldInfo fieldInfo : this.document.getGlobalFields().values()) {
            if (!fieldInfo.isVisibleAt(pos)) continue;
            items.add(AutocompleteItem.fromField(fieldInfo));
        }
        ScriptTypeInfo enclosingType = this.findEnclosingType(pos);
        if (enclosingType != null) {
            void var6_13;
            for (FieldInfo fieldInfo : enclosingType.getFields().values()) {
                if (!fieldInfo.isVisibleAt(pos)) continue;
                items.add(AutocompleteItem.fromField(fieldInfo));
            }
            for (List<MethodInfo> list : enclosingType.getMethods().values()) {
                for (MethodInfo method : list) {
                    items.add(AutocompleteItem.fromMethod(method, context.methodsOnly));
                }
            }
            for (ScriptTypeInfo scriptTypeInfo : enclosingType.getInnerClasses()) {
                items.add(AutocompleteItem.fromType(scriptTypeInfo));
            }
            ScriptTypeInfo scriptTypeInfo = enclosingType.getOuterClass();
            while (var6_13 != null) {
                for (ScriptTypeInfo scriptTypeInfo2 : var6_13.getInnerClasses()) {
                    if (scriptTypeInfo2 == enclosingType) continue;
                    items.add(AutocompleteItem.fromType(scriptTypeInfo2));
                }
                for (FieldInfo fieldInfo : var6_13.getFields().values()) {
                    items.add(AutocompleteItem.fromField(fieldInfo));
                }
                for (List<MethodInfo> list : var6_13.getMethods().values()) {
                    for (MethodInfo method : list) {
                        items.add(AutocompleteItem.fromMethod(method, context.methodsOnly));
                    }
                }
                ScriptTypeInfo scriptTypeInfo3 = var6_13.getOuterClass();
            }
        }
        for (MethodInfo methodInfo : this.document.getAllMethods()) {
            items.add(AutocompleteItem.fromMethod(methodInfo, context.methodsOnly));
        }
        this.addLanguageUniqueSuggestions(context, items);
        this.addKeywords(items);
    }

    protected void addLanguageUniqueSuggestions(AutocompleteProvider.Context context, List<AutocompleteItem> items) {
        for (TypeInfo type : this.document.getImportedTypes()) {
            items.add(AutocompleteItem.fromType(type));
        }
        for (ScriptTypeInfo scriptType : this.document.getScriptTypesMap().values()) {
            items.add(AutocompleteItem.fromType(scriptType));
        }
        if (context.prefix != null && context.prefix.length() >= 2 && Character.isUpperCase(context.prefix.charAt(0))) {
            this.addUnimportedClassSuggestions(context.prefix, items);
        }
    }

    protected void addUnimportedClassSuggestions(String prefix, List<AutocompleteItem> items) {
        TypeResolver resolver = TypeResolver.getInstance();
        List<String> matchingClasses = resolver.findClassesByPrefix(prefix, -1);
        HashSet<String> importedFullNames = new HashSet<String>();
        for (TypeInfo imported : this.document.getImportedTypes()) {
            importedFullNames.add(imported.getFullName());
        }
        HashSet<String> existingSimpleNames = new HashSet<String>();
        for (AutocompleteItem item : items) {
            if (item.getKind() != AutocompleteItem.Kind.CLASS && item.getKind() != AutocompleteItem.Kind.ENUM) continue;
            existingSimpleNames.add(item.getName());
        }
        for (String fullName : matchingClasses) {
            TypeInfo type;
            if (importedFullNames.contains(fullName) || (type = resolver.resolveFullName(fullName)) == null || !type.isResolved() || existingSimpleNames.contains(type.getSimpleName())) continue;
            AutocompleteItem item = new AutocompleteItem.Builder().name(type.getSimpleName()).insertText(type.getSimpleName()).kind(type.getKind() == TypeInfo.Kind.ENUM ? AutocompleteItem.Kind.ENUM : AutocompleteItem.Kind.CLASS).typeLabel(type.getPackageName()).typeInfo(type).signature(type.getFullName()).sourceData(type).requiresImport(true).importPath(fullName).color(TokenType.UNUSED_IMPORT.getHexColor()).build();
            items.add(item);
            existingSimpleNames.add(type.getSimpleName());
        }
    }

    protected void addKeywords(List<AutocompleteItem> items) {
        for (String keyword : this.getKeywords()) {
            items.add(AutocompleteItem.keyword(keyword));
        }
    }

    public String[] getKeywords() {
        return TypeChecker.getJavaKeywords();
    }

    protected ScriptTypeInfo findEnclosingType(int position) {
        return this.document.findEnclosingScriptType(position);
    }

    protected boolean isStaticAccess(String receiverExpr, int position) {
        return TypeResolver.isStaticAccessExpression(receiverExpr, position, this.document);
    }

    protected UsageTracker getUsageTracker() {
        return UsageTracker.getJavaInstance();
    }

    protected WeigherChain getWeigherChain() {
        return CompletionWeigherChains.javaChain();
    }

    protected void filterAndScore(List<AutocompleteItem> items, String prefix, boolean isMemberAccess, boolean isStaticContext, String ownerFullName) {
        boolean requirePrefix = !isMemberAccess;
        Iterator<AutocompleteItem> iter = items.iterator();
        while (iter.hasNext()) {
            AutocompleteItem item = iter.next();
            int score = item.calculateMatchScore(prefix != null ? prefix : "", requirePrefix);
            if (score >= 0) continue;
            iter.remove();
        }
        ScoringContext context = new ScoringContext(prefix, isMemberAccess, isStaticContext, ownerFullName, this.getUsageTracker(), requirePrefix);
        WeigherChain weigherChain = this.getWeigherChain();
        Comparator<AutocompleteItem> comparator = weigherChain.buildComparator(context);
        items.sort(comparator);
    }
}

