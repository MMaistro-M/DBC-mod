/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.autocomplete;

import java.lang.reflect.Method;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSFieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSMethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticField;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticMethod;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticParameter;

public class AutocompleteItem
implements Comparable<AutocompleteItem> {
    private final String name;
    private final String searchName;
    private final String insertText;
    private final Kind kind;
    private final String typeLabel;
    private final TypeInfo typeInfo;
    private final String signature;
    private final String documentation;
    private final Object sourceData;
    private final boolean deprecated;
    private final boolean requiresImport;
    private final String importPath;
    private final int inheritanceDepth;
    private int matchScore = 0;
    private int[] matchIndices;
    private int color = -1;

    private AutocompleteItem(String name, String searchName, String insertText, Kind kind, String typeLabel, TypeInfo typeLabelTypeInfo, String signature, String documentation, Object sourceData, boolean deprecated, boolean requiresImport, String importPath, int inheritanceDepth) {
        this.name = name;
        this.searchName = searchName != null ? searchName : name;
        this.insertText = insertText;
        this.kind = kind;
        this.typeLabel = typeLabel;
        this.typeInfo = typeLabelTypeInfo;
        this.signature = signature;
        this.documentation = documentation;
        this.sourceData = sourceData;
        this.deprecated = deprecated;
        this.requiresImport = requiresImport;
        this.importPath = importPath;
        this.inheritanceDepth = inheritanceDepth;
    }

    public static AutocompleteItem fromMethod(MethodInfo method) {
        return AutocompleteItem.fromMethod(method, false);
    }

    public static AutocompleteItem fromMethod(MethodInfo method, boolean forMethodReference) {
        String name = method.getName();
        StringBuilder insertText = new StringBuilder(name);
        if (!forMethodReference) {
            insertText.append("(");
            if (method.getParameterCount() > 0) {
                // empty if block
            }
            insertText.append(")");
        }
        String returnType = method.getReturnType() != null ? AutocompleteItem.getName(method.getReturnType()) : "void";
        String signature = AutocompleteItem.buildMethodSignature(method);
        StringBuilder displayName = new StringBuilder(name);
        displayName.append("(");
        for (int i = 0; i < method.getParameterCount(); ++i) {
            FieldInfo param;
            if (i > 0) {
                displayName.append(", ");
            }
            String paramType = (param = method.getParameters().get(i)).getTypeInfo() != null ? AutocompleteItem.getName(param.getTypeInfo()) : "?";
            displayName.append(paramType);
            if (param.isVarArg()) {
                displayName.append("...");
            }
            if (param.getName() == null || param.getName().isEmpty()) continue;
            displayName.append(" ").append(param.getName());
        }
        displayName.append(")");
        return new AutocompleteItem(displayName.toString(), name, insertText.toString(), Kind.METHOD, returnType, method.getReturnType(), signature, method.getDocumentation(), method, method.getJavaMethod() != null && method.getJavaMethod().isAnnotationPresent(Deprecated.class), false, null, -1);
    }

    public static AutocompleteItem fromField(FieldInfo field) {
        Kind kind;
        String typeLabel = field.getTypeInfo() != null ? AutocompleteItem.getName(field.getTypeInfo()) : "?";
        switch (field.getScope()) {
            case PARAMETER: {
                kind = Kind.PARAMETER;
                break;
            }
            case LOCAL: {
                kind = Kind.VARIABLE;
                break;
            }
            case ENUM_CONSTANT: {
                kind = Kind.ENUM_CONSTANT;
                break;
            }
            default: {
                kind = Kind.FIELD;
            }
        }
        return new AutocompleteItem(field.getName(), field.getName(), field.getName(), kind, typeLabel, field.getTypeInfo(), typeLabel + " " + field.getName(), null, field, false, false, null, -1);
    }

    public static AutocompleteItem fromType(TypeInfo type) {
        Kind kind;
        switch (type.getKind()) {
            case INTERFACE: {
                kind = Kind.CLASS;
                break;
            }
            case ENUM: {
                kind = Kind.ENUM;
                break;
            }
            default: {
                kind = Kind.CLASS;
            }
        }
        String name = AutocompleteItem.getName(type);
        return new AutocompleteItem(name, name, name, kind, type.getPackageName(), type, type.getFullName(), null, type, false, false, null, -1);
    }

    public static AutocompleteItem fromJSMethod(JSMethodInfo method) {
        return AutocompleteItem.fromJSMethod(method, null, 0, false);
    }

    public static AutocompleteItem fromJSMethod(JSMethodInfo method, int inheritanceDepth) {
        return AutocompleteItem.fromJSMethod(method, null, inheritanceDepth, false);
    }

    public static AutocompleteItem fromJSMethod(JSMethodInfo method, TypeInfo contextType, int inheritanceDepth) {
        return AutocompleteItem.fromJSMethod(method, contextType, inheritanceDepth, false);
    }

    public static AutocompleteItem fromJSMethod(JSMethodInfo method, TypeInfo contextType, int inheritanceDepth, boolean forMethodReference) {
        String name = method.getName();
        StringBuilder insertText = new StringBuilder(name);
        if (!forMethodReference) {
            insertText.append("(");
            insertText.append(")");
        }
        StringBuilder displayName = new StringBuilder(name);
        displayName.append("(");
        for (int i = 0; i < method.getParameters().size(); ++i) {
            JSMethodInfo.JSParameterInfo param;
            TypeInfo paramType;
            String paramTypeName;
            if (i > 0) {
                displayName.append(", ");
            }
            String string = paramTypeName = (paramType = (param = method.getParameters().get(i)).getResolvedType(contextType)).isResolved() ? paramType.getDisplayName() : param.getType();
            if (param.isVarArg()) {
                paramTypeName = paramTypeName + "...";
            }
            displayName.append(paramTypeName).append(" ").append(param.getName());
        }
        displayName.append(")");
        TypeInfo returnTypeInfo = method.getResolvedReturnType(contextType);
        String returnType = returnTypeInfo.isResolved() ? returnTypeInfo.getDisplayName() : method.getReturnType();
        return new AutocompleteItem(displayName.toString(), name, insertText.toString(), Kind.METHOD, returnType, returnTypeInfo, method.getSignature(), method.getDocumentation(), method, false, false, null, inheritanceDepth);
    }

    public static AutocompleteItem fromJSField(JSFieldInfo field) {
        return AutocompleteItem.fromJSField(field, null, 0);
    }

    public static AutocompleteItem fromJSField(JSFieldInfo field, int inheritanceDepth) {
        return AutocompleteItem.fromJSField(field, null, inheritanceDepth);
    }

    public static AutocompleteItem fromJSField(JSFieldInfo field, TypeInfo contextType, int inheritanceDepth) {
        TypeInfo fieldTypeInfo = field.getResolvedType(contextType);
        String fieldType = fieldTypeInfo.isResolved() ? fieldTypeInfo.getDisplayName() : field.getType();
        return new AutocompleteItem(field.getName(), field.getName(), field.getName(), Kind.FIELD, fieldType, fieldTypeInfo, field.toString(), field.getDocumentation(), field, false, false, null, inheritanceDepth);
    }

    public static AutocompleteItem keyword(String keyword) {
        return new AutocompleteItem(keyword, keyword, keyword, Kind.KEYWORD, "keyword", null, null, null, null, false, false, null, -1);
    }

    public static AutocompleteItem fromSyntheticMethod(SyntheticMethod method, TypeInfo containingType) {
        return AutocompleteItem.fromSyntheticMethod(method, containingType, false);
    }

    public static AutocompleteItem fromSyntheticMethod(SyntheticMethod method, TypeInfo containingType, boolean forMethodReference) {
        String name = method.name;
        StringBuilder insertText = new StringBuilder(name);
        if (!forMethodReference) {
            insertText.append("(");
            insertText.append(")");
        }
        StringBuilder displayName = new StringBuilder(name);
        displayName.append("(");
        for (int i = 0; i < method.parameters.size(); ++i) {
            if (i > 0) {
                displayName.append(", ");
            }
            SyntheticParameter param = method.parameters.get(i);
            String simpleTypeName = AutocompleteItem.getSimpleTypeName(param.typeName);
            displayName.append(simpleTypeName).append(" ").append(param.name);
        }
        displayName.append(")");
        String simpleReturnType = AutocompleteItem.getSimpleTypeName(method.returnType);
        return new AutocompleteItem(displayName.toString(), name, insertText.toString(), Kind.METHOD, simpleReturnType, method.getReturnTypeInfo(), method.getSignature(), method.documentation, method, false, false, null, 0);
    }

    private static String getSimpleTypeName(String typeName) {
        if (typeName == null) {
            return "void";
        }
        int lastDot = typeName.lastIndexOf(46);
        return lastDot >= 0 ? typeName.substring(lastDot + 1) : typeName;
    }

    public static AutocompleteItem fromSyntheticField(SyntheticField field) {
        return new AutocompleteItem(field.name, field.name, field.name, Kind.FIELD, field.typeName, field.getTypeInfo(), field.typeName + " " + field.name, field.documentation, field, false, false, null, 0);
    }

    private static String buildMethodSignature(MethodInfo method) {
        StringBuilder sb = new StringBuilder();
        String returnType = method.getReturnType() != null ? AutocompleteItem.getName(method.getReturnType()) : "void";
        sb.append(returnType).append(" ").append(method.getName()).append("(");
        for (int i = 0; i < method.getParameterCount(); ++i) {
            FieldInfo param;
            if (i > 0) {
                sb.append(", ");
            }
            String paramType = (param = method.getParameters().get(i)).getTypeInfo() != null ? AutocompleteItem.getName(param.getTypeInfo()) : "?";
            sb.append(paramType);
            if (param.isVarArg()) {
                sb.append("...");
            }
            sb.append(" ").append(param.getName());
        }
        sb.append(")");
        return sb.toString();
    }

    public int calculateMatchScore(String query, boolean requirePrefix) {
        String lowerQuery;
        if (query == null || query.isEmpty()) {
            this.matchScore = 100;
            this.matchIndices = new int[0];
            return this.matchScore;
        }
        String lowerName = this.searchName.toLowerCase();
        if (lowerName.startsWith(lowerQuery = query.toLowerCase())) {
            this.matchScore = 1000 - query.length();
            this.matchIndices = new int[query.length()];
            for (int i = 0; i < query.length(); ++i) {
                this.matchIndices[i] = i;
            }
            return this.matchScore;
        }
        if (requirePrefix) {
            this.matchScore = -1;
            this.matchIndices = new int[0];
            return this.matchScore;
        }
        int subIndex = lowerName.indexOf(lowerQuery);
        if (subIndex >= 0) {
            this.matchScore = 500 - subIndex;
            this.matchIndices = new int[query.length()];
            for (int i = 0; i < query.length(); ++i) {
                this.matchIndices[i] = subIndex + i;
            }
            return this.matchScore;
        }
        int[] indices = new int[query.length()];
        int queryIdx = 0;
        int gaps = 0;
        int consecutiveBonus = 0;
        int lastMatchIdx = -2;
        for (int nameIdx = 0; queryIdx < query.length() && nameIdx < this.searchName.length(); ++nameIdx) {
            if (Character.toLowerCase(this.searchName.charAt(nameIdx)) == lowerQuery.charAt(queryIdx)) {
                indices[queryIdx] = nameIdx;
                if (nameIdx == lastMatchIdx + 1) {
                    consecutiveBonus += 10;
                }
                if (nameIdx == 0 || !Character.isLetterOrDigit(this.searchName.charAt(nameIdx - 1)) || Character.isUpperCase(this.searchName.charAt(nameIdx)) && nameIdx > 0 && Character.isLowerCase(this.searchName.charAt(nameIdx - 1))) {
                    consecutiveBonus += 20;
                }
                lastMatchIdx = nameIdx;
                ++queryIdx;
                continue;
            }
            ++gaps;
        }
        if (queryIdx < query.length()) {
            this.matchScore = -1;
            this.matchIndices = null;
            return -1;
        }
        this.matchScore = 100 + consecutiveBonus - gaps;
        this.matchIndices = indices;
        return this.matchScore;
    }

    public int calculateMatchScore(String query) {
        return this.calculateMatchScore(query, false);
    }

    @Deprecated
    public void addScoreBoost(int boost) {
        this.matchScore += boost;
    }

    public String getName() {
        return this.name;
    }

    public String getSearchName() {
        return this.searchName;
    }

    public String getInsertText() {
        return this.insertText;
    }

    public Kind getKind() {
        return this.kind;
    }

    public String getTypeLabel() {
        return this.typeLabel;
    }

    public TypeInfo getTypeInfo() {
        return this.typeInfo;
    }

    public String getSignature() {
        return this.signature;
    }

    public String getDocumentation() {
        return this.documentation;
    }

    public Object getSourceData() {
        return this.sourceData;
    }

    public boolean isDeprecated() {
        return this.deprecated;
    }

    public boolean requiresImport() {
        return this.requiresImport;
    }

    public String getImportPath() {
        return this.importPath;
    }

    public int getMatchScore() {
        return this.matchScore;
    }

    public int[] getMatchIndices() {
        return this.matchIndices;
    }

    public int getInheritanceDepth() {
        return this.inheritanceDepth;
    }

    public int getColor() {
        return this.color;
    }

    public AutocompleteItem setColor(int col) {
        this.color = col;
        return this;
    }

    public static String getName(TypeInfo type) {
        return type.getDisplayName();
    }

    public int getParameterCount() {
        if (this.sourceData instanceof MethodInfo) {
            return ((MethodInfo)this.sourceData).getParameterCount();
        }
        if (this.sourceData instanceof JSMethodInfo) {
            return ((JSMethodInfo)this.sourceData).getParameterCount();
        }
        if (this.sourceData instanceof SyntheticMethod) {
            return ((SyntheticMethod)this.sourceData).parameters.size();
        }
        return 0;
    }

    public boolean isStatic() {
        if (this.sourceData instanceof MethodInfo) {
            return ((MethodInfo)this.sourceData).isStatic();
        }
        if (this.sourceData instanceof FieldInfo) {
            return ((FieldInfo)this.sourceData).isStatic();
        }
        if (this.sourceData instanceof JSMethodInfo) {
            return ((JSMethodInfo)this.sourceData).isStatic();
        }
        if (this.sourceData instanceof JSFieldInfo) {
            return ((JSFieldInfo)this.sourceData).isStatic();
        }
        return false;
    }

    public boolean isFinal() {
        if (this.sourceData instanceof MethodInfo) {
            return ((MethodInfo)this.sourceData).isFinal();
        }
        if (this.sourceData instanceof FieldInfo) {
            return ((FieldInfo)this.sourceData).isFinal();
        }
        if (this.sourceData instanceof JSFieldInfo) {
            return ((JSFieldInfo)this.sourceData).isReadonly();
        }
        return false;
    }

    public boolean isInheritedObjectMethod() {
        if (this.kind != Kind.METHOD) {
            return false;
        }
        if (this.sourceData instanceof MethodInfo) {
            MethodInfo methodInfo = (MethodInfo)this.sourceData;
            Method javaMethod = methodInfo.getJavaMethod();
            if (javaMethod != null) {
                return Object.class.equals(javaMethod.getDeclaringClass());
            }
            TypeInfo containingType = methodInfo.getContainingType();
            return containingType != null && containingType.getFullName().equals("java.lang.Object") && !methodInfo.isStatic() && !methodInfo.isOverride();
        }
        if (this.sourceData instanceof JSMethodInfo) {
            JSMethodInfo jsMethod = (JSMethodInfo)this.sourceData;
            JSTypeInfo definingType = jsMethod.getContainingType();
            return definingType != null && "Object".equals(definingType.getSimpleName()) && !jsMethod.isStatic() && this.inheritanceDepth > 0;
        }
        return false;
    }

    public String getIconId() {
        switch (this.kind) {
            case METHOD: {
                return "m";
            }
            case FIELD: {
                return "f";
            }
            case VARIABLE: {
                return "v";
            }
            case PARAMETER: {
                return "p";
            }
            case CLASS: {
                return "C";
            }
            case ENUM: {
                return "E";
            }
            case ENUM_CONSTANT: {
                return "e";
            }
            case KEYWORD: {
                return "k";
            }
            case SNIPPET: {
                return "s";
            }
        }
        return "?";
    }

    public int getIconColor() {
        switch (this.kind) {
            case METHOD: {
                return -4687909;
            }
            case FIELD: {
                return -8797953;
            }
            case VARIABLE: {
                return -8460409;
            }
            case PARAMETER: {
                return -65281;
            }
            case CLASS: {
                return -22953;
            }
            case ENUM: {
                return -10138;
            }
            case ENUM_CONSTANT: {
                return -10138;
            }
            case KEYWORD: {
                return -33934;
            }
            case SNIPPET: {
                return -3355444;
            }
        }
        return -3355444;
    }

    @Override
    public int compareTo(AutocompleteItem other) {
        if (this.matchScore != other.matchScore) {
            return other.matchScore - this.matchScore;
        }
        if (this.kind.getPriority() != other.kind.getPriority()) {
            return this.kind.getPriority() - other.kind.getPriority();
        }
        if (this.inheritanceDepth >= 0 && other.inheritanceDepth >= 0 && this.inheritanceDepth != other.inheritanceDepth) {
            return this.inheritanceDepth - other.inheritanceDepth;
        }
        return this.name.compareToIgnoreCase(other.name);
    }

    public String toString() {
        return this.name + " (" + (Object)((Object)this.kind) + ")";
    }

    public static class Builder {
        private String name;
        private String searchName;
        private String insertText;
        private Kind kind = Kind.FIELD;
        private String typeLabel = "";
        private TypeInfo typeInfo;
        private String signature;
        private String documentation;
        private Object sourceData;
        private boolean deprecated = false;
        private boolean requiresImport = false;
        private String importPath = null;
        private int color = -1;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder searchName(String searchName) {
            this.searchName = searchName;
            return this;
        }

        public Builder insertText(String insertText) {
            this.insertText = insertText;
            return this;
        }

        public Builder kind(Kind kind) {
            this.kind = kind;
            return this;
        }

        public Builder typeLabel(String typeLabel) {
            this.typeLabel = typeLabel;
            return this;
        }

        public Builder typeInfo(TypeInfo typeInfo) {
            this.typeInfo = typeInfo;
            return this;
        }

        public Builder signature(String signature) {
            this.signature = signature;
            return this;
        }

        public Builder documentation(String documentation) {
            this.documentation = documentation;
            return this;
        }

        public Builder sourceData(Object sourceData) {
            this.sourceData = sourceData;
            return this;
        }

        public Builder deprecated(boolean deprecated) {
            this.deprecated = deprecated;
            return this;
        }

        public Builder requiresImport(boolean requiresImport) {
            this.requiresImport = requiresImport;
            return this;
        }

        public Builder importPath(String importPath) {
            this.importPath = importPath;
            return this;
        }

        public Builder color(int color) {
            this.color = color;
            return this;
        }

        public AutocompleteItem build() {
            if (this.insertText == null) {
                this.insertText = this.name;
            }
            return new AutocompleteItem(this.name, this.searchName, this.insertText, this.kind, this.typeLabel, this.typeInfo, this.signature, this.documentation, this.sourceData, this.deprecated, this.requiresImport, this.importPath, -1).setColor(this.color);
        }
    }

    public static enum Kind {
        ENUM_CONSTANT(-1),
        METHOD(0),
        FIELD(10),
        PARAMETER(19),
        VARIABLE(20),
        CLASS(30),
        ENUM(40),
        KEYWORD(60),
        SNIPPET(70);

        private final int priority;

        private Kind(int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return this.priority;
        }
    }
}

