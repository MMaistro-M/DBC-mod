/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import noppes.npcs.client.gui.util.script.interpreter.ScriptDocument;
import noppes.npcs.client.gui.util.script.interpreter.ScriptLine;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldAccessInfo;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenErrorMessage;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenType;
import noppes.npcs.client.gui.util.script.interpreter.type.ScriptTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeResolver;

public class FieldChainMarker {
    private final ScriptDocument document;
    private final String text;

    public FieldChainMarker(ScriptDocument document, String text) {
        this.document = document;
        this.text = text;
    }

    public void markChainedFieldAccesses(List<ScriptLine.Mark> marks) {
        Pattern chainPattern = Pattern.compile("\\b(this|super|[a-zA-Z_][a-zA-Z0-9_]*)\\s*\\.\\s*([a-zA-Z_][a-zA-Z0-9_]*)");
        Matcher m = chainPattern.matcher(this.text);
        while (m.find()) {
            ChainData chain;
            int chainStart = m.start(1);
            if (this.document.isExcluded(chainStart) || this.document.isInImportOrPackage(chainStart) || this.document.isFollowedByParen(m.end(2)) || (chain = this.buildChain(m)) == null) continue;
            ChainContext ctx = this.resolveChainStart(chain, chainStart);
            for (int i = ctx.startIndex; i < chain.segments.size(); ++i) {
                ctx.currentIndex = i;
                int[] pos = chain.positions.get(i);
                if (this.document.isExcluded(pos[0])) continue;
                MarkResult result = this.resolveSegmentMark(ctx, chainStart);
                if (result.mark != null) {
                    marks.add(result.mark);
                }
                ctx.currentType = result.nextType;
            }
        }
        Pattern dotIdent = Pattern.compile("\\.\\s*([a-zA-Z_][a-zA-Z0-9_]*)");
        Matcher md = dotIdent.matcher(this.text);
        while (md.find()) {
            String receiverExpr;
            int[] bounds;
            Character precedingChar;
            int identStart = md.start(1);
            int identEnd = md.end(1);
            int dotPos = md.start();
            if (this.document.isExcluded(identStart) || this.document.isInImportOrPackage(identStart) || (precedingChar = this.getNonWhitespaceBefore(dotPos)) == null || precedingChar.charValue() != ')' && precedingChar.charValue() != ']' || (bounds = this.document.findReceiverBoundsBefore(dotPos)) == null || this.document.isExcluded(bounds[0]) || this.document.isInImportOrPackage(bounds[0]) || (receiverExpr = this.text.substring(bounds[0], bounds[1]).trim()).isEmpty()) continue;
            TypeInfo receiverType = this.document.resolveExpressionType(receiverExpr, bounds[0]);
            this.markReceiverChainSegments(marks, md.group(1), identStart, identEnd, receiverType);
        }
    }

    private ChainData buildChain(Matcher m) {
        ChainData chain = new ChainData();
        chain.segments.add(m.group(1));
        chain.positions.add(new int[]{m.start(1), m.end(1)});
        chain.segments.add(m.group(2));
        chain.positions.add(new int[]{m.start(2), m.end(2)});
        int pos = m.end(2);
        while (pos < this.text.length() && (pos = this.document.skipWhitespace(pos)) < this.text.length() && !this.document.isExcluded(pos) && this.text.charAt(pos) == '.') {
            ++pos;
            if ((pos = this.document.skipWhitespace(pos)) >= this.text.length() || this.document.isExcluded(pos) || !Character.isJavaIdentifierStart(this.text.charAt(pos))) break;
            int identStart = pos;
            while (pos < this.text.length() && Character.isJavaIdentifierPart(this.text.charAt(pos))) {
                ++pos;
            }
            int identEnd = pos;
            if (this.document.isFollowedByParen(identEnd)) break;
            chain.segments.add(this.text.substring(identStart, identEnd));
            chain.positions.add(new int[]{identStart, identEnd});
        }
        return chain;
    }

    private ChainContext resolveChainStart(ChainData chain, int chainStart) {
        ChainContext ctx = new ChainContext();
        ctx.chain = chain;
        ctx.currentIndex = 0;
        String first = chain.segments.get(0);
        ctx.firstIsThis = first.equals("this");
        ctx.firstIsSuper = first.equals("super");
        ctx.firstIsPrecededByDot = this.document.isPrecededByDot(chainStart);
        ctx.startIndex = ctx.firstIsPrecededByDot ? 0 : 1;
        ctx.currentType = null;
        ctx.enclosingType = this.document.findEnclosingScriptType(chainStart);
        if (ctx.firstIsThis) {
            ctx.currentType = this.document.resolveThisType(chainStart);
            if (ctx.currentType == null) {
                ctx.currentType = ctx.enclosingType;
            }
        } else if (ctx.firstIsSuper) {
            if (ctx.enclosingType != null && ctx.enclosingType.hasSuperClass()) {
                ctx.currentType = ctx.enclosingType.getSuperClass();
            }
        } else if (ctx.firstIsPrecededByDot) {
            TypeInfo receiverType = this.document.resolveReceiverChain(chainStart);
            if (receiverType != null && receiverType.hasField(first)) {
                FieldInfo varInfo = receiverType.getFieldInfo(first);
                ctx.currentType = varInfo != null ? varInfo.getTypeInfo() : null;
            }
        } else {
            FieldInfo varInfo;
            TypeInfo typeCheck = this.document.resolveType(first);
            ctx.currentType = typeCheck != null && typeCheck.isResolved() ? typeCheck : ((varInfo = this.document.resolveVariable(first, chainStart)) != null ? varInfo.getTypeInfo() : null);
        }
        return ctx;
    }

    private MarkResult resolveSegmentMark(ChainContext ctx, int chainStart) {
        int index = ctx.currentIndex;
        String segment = ctx.chain.segments.get(index);
        int[] pos = ctx.chain.positions.get(index);
        if (index == 0 && ctx.firstIsPrecededByDot) {
            return this.resolveReceiverFieldMark(ctx, chainStart);
        }
        if (index == 1 && ctx.firstIsThis) {
            return this.resolveThisFieldMark(ctx);
        }
        if (index == 1 && ctx.firstIsSuper) {
            return this.resolveSuperFieldMark(ctx);
        }
        if (ctx.currentType != null && ctx.currentType.isResolved()) {
            return this.resolveTypedFieldMark(ctx);
        }
        return new MarkResult(new ScriptLine.Mark(pos[0], pos[1], TokenType.UNDEFINED_VAR), null);
    }

    private MarkResult resolveReceiverFieldMark(ChainContext ctx, int chainStart) {
        int index = ctx.currentIndex;
        String segment = ctx.chain.segments.get(index);
        int[] pos = ctx.chain.positions.get(index);
        boolean isLast = index == ctx.chain.segments.size() - 1;
        boolean isStatic = this.isStaticContext(ctx);
        TypeInfo receiverType = this.document.resolveReceiverChain(chainStart);
        if (receiverType != null && receiverType.hasField(segment)) {
            FieldInfo fieldInfo = receiverType.getFieldInfo(segment);
            FieldAccessInfo accessInfo = this.document.createFieldAccessInfo(segment, pos[0], pos[1], receiverType, fieldInfo, isLast, isStatic);
            return new MarkResult(new ScriptLine.Mark(pos[0], pos[1], this.getFieldTokenType(fieldInfo), accessInfo), fieldInfo != null ? fieldInfo.getTypeInfo() : null);
        }
        return new MarkResult(new ScriptLine.Mark(pos[0], pos[1], TokenType.UNDEFINED_VAR), null);
    }

    private MarkResult resolveThisFieldMark(ChainContext ctx) {
        TypeInfo thisType;
        int index = ctx.currentIndex;
        String segment = ctx.chain.segments.get(index);
        int[] pos = ctx.chain.positions.get(index);
        boolean isLast = index == ctx.chain.segments.size() - 1;
        boolean found = false;
        FieldInfo fieldInfo = null;
        TypeInfo typeInfo = thisType = ctx.currentType != null ? ctx.currentType : ctx.enclosingType;
        if (thisType != null && thisType.hasField(segment)) {
            found = true;
            fieldInfo = thisType.getFieldInfo(segment);
        } else if (this.document.getGlobalFields().containsKey(segment)) {
            found = true;
            fieldInfo = this.document.getGlobalFields().get(segment);
        }
        if (found) {
            FieldAccessInfo accessInfo = this.document.createFieldAccessInfo(segment, pos[0], pos[1], thisType, fieldInfo, isLast, false);
            return new MarkResult(new ScriptLine.Mark(pos[0], pos[1], this.getFieldTokenType(fieldInfo), accessInfo), fieldInfo != null ? fieldInfo.getTypeInfo() : null);
        }
        return new MarkResult(new ScriptLine.Mark(pos[0], pos[1], TokenType.UNDEFINED_VAR), null);
    }

    private MarkResult resolveSuperFieldMark(ChainContext ctx) {
        int index = ctx.currentIndex;
        String segment = ctx.chain.segments.get(index);
        int[] pos = ctx.chain.positions.get(index);
        boolean isLast = index == ctx.chain.segments.size() - 1;
        TypeInfo superType = ctx.currentType;
        if (superType == null) {
            return new MarkResult(new ScriptLine.Mark(pos[0], pos[1], TokenType.UNDEFINED_VAR, TokenErrorMessage.from("Cannot resolve field '" + segment + "'").clearOtherErrors()), null);
        }
        boolean found = false;
        FieldInfo fieldInfo = null;
        if (superType instanceof ScriptTypeInfo) {
            ScriptTypeInfo scriptSuper = (ScriptTypeInfo)superType;
            found = scriptSuper.hasFieldInHierarchy(segment);
            if (found) {
                fieldInfo = scriptSuper.getFieldInfoInHierarchy(segment);
            }
        } else {
            found = superType.hasField(segment);
            if (found) {
                fieldInfo = superType.getFieldInfo(segment);
            }
        }
        if (found) {
            FieldAccessInfo accessInfo = this.document.createFieldAccessInfo(segment, pos[0], pos[1], superType, fieldInfo, isLast, false);
            return new MarkResult(new ScriptLine.Mark(pos[0], pos[1], this.getFieldTokenType(fieldInfo), accessInfo), fieldInfo != null ? fieldInfo.getTypeInfo() : null);
        }
        String errorMsg = "Field '" + segment + "' not found in parent class hierarchy starting from '" + superType.getSimpleName() + "'";
        return new MarkResult(new ScriptLine.Mark(pos[0], pos[1], TokenType.UNDEFINED_VAR, TokenErrorMessage.from(errorMsg).clearOtherErrors()), null);
    }

    private MarkResult resolveTypedFieldMark(ChainContext ctx) {
        int index = ctx.currentIndex;
        String segment = ctx.chain.segments.get(index);
        int[] pos = ctx.chain.positions.get(index);
        boolean isLast = index == ctx.chain.segments.size() - 1;
        boolean isStatic = this.isStaticContext(ctx);
        TypeInfo currentType = ctx.currentType;
        if (!currentType.hasField(segment)) {
            Class<?>[] innerClass2;
            TypeInfo rawType = currentType.getRawType();
            if (rawType instanceof ScriptTypeInfo && (innerClass2 = ((ScriptTypeInfo)rawType).getInnerClass(segment)) != null) {
                return new MarkResult(new ScriptLine.Mark(pos[0], pos[1], innerClass2.getTokenType(), innerClass2), (TypeInfo)innerClass2);
            }
            if (currentType.getJavaClass() != null) {
                try {
                    for (Class<?> nested : currentType.getJavaClass().getDeclaredClasses()) {
                        if (!Modifier.isPublic(nested.getModifiers()) || !nested.getSimpleName().equals(segment)) continue;
                        TypeInfo nestedType = TypeInfo.fromClass(nested);
                        return new MarkResult(new ScriptLine.Mark(pos[0], pos[1], nestedType.getTokenType(), nestedType), nestedType);
                    }
                }
                catch (SecurityException innerClass2) {
                    // empty catch block
                }
            }
            return new MarkResult(new ScriptLine.Mark(pos[0], pos[1], TokenType.UNDEFINED_VAR), null);
        }
        FieldInfo fieldInfo = currentType.getFieldInfo(segment);
        if (isStatic && fieldInfo != null && !fieldInfo.isStatic()) {
            TokenErrorMessage errorMsg = TokenErrorMessage.from("Cannot access non-static field '" + segment + "' from static context '" + currentType.getSimpleName() + "'").clearOtherErrors();
            return new MarkResult(new ScriptLine.Mark(pos[0], pos[1], TokenType.UNDEFINED_VAR, errorMsg), null);
        }
        FieldAccessInfo accessInfo = this.document.createFieldAccessInfo(segment, pos[0], pos[1], currentType, fieldInfo, isLast, isStatic);
        return new MarkResult(new ScriptLine.Mark(pos[0], pos[1], this.getFieldTokenType(fieldInfo), accessInfo), fieldInfo != null ? fieldInfo.getTypeInfo() : null);
    }

    private void markReceiverChainSegments(List<ScriptLine.Mark> marks, String firstField, int identStart, int identEnd, TypeInfo receiverType) {
        FieldInfo fInfo = null;
        TypeInfo currentType = null;
        if (receiverType == null || !receiverType.hasField(firstField)) {
            marks.add(new ScriptLine.Mark(identStart, identEnd, TokenType.UNDEFINED_VAR));
            return;
        }
        fInfo = receiverType.getFieldInfo(firstField);
        boolean hasMore = this.document.isFollowedByDot(identEnd);
        boolean isStatic = false;
        FieldAccessInfo accessInfo = this.document.createFieldAccessInfo(firstField, identStart, identEnd, receiverType, fInfo, !hasMore, isStatic);
        marks.add(new ScriptLine.Mark(identStart, identEnd, this.getFieldTokenType(fInfo), accessInfo));
        currentType = fInfo != null ? fInfo.getTypeInfo() : null;
        int pos = identEnd;
        while (pos < this.text.length() && (pos = this.document.skipWhitespace(pos)) < this.text.length() && !this.document.isExcluded(pos) && this.text.charAt(pos) == '.') {
            boolean isLast;
            ++pos;
            if ((pos = this.document.skipWhitespace(pos)) >= this.text.length() || this.document.isExcluded(pos) || !Character.isJavaIdentifierStart(this.text.charAt(pos))) break;
            int nStart = pos;
            while (pos < this.text.length() && Character.isJavaIdentifierPart(this.text.charAt(pos))) {
                ++pos;
            }
            int nEnd = pos;
            String seg = this.text.substring(nStart, nEnd);
            if (this.document.isFollowedByParen(nEnd) || this.document.isExcluded(nStart)) break;
            boolean bl = isLast = !this.document.isFollowedByDot(nEnd);
            if (currentType != null && currentType.isResolved() && currentType.hasField(seg)) {
                FieldInfo segInfo = currentType.getFieldInfo(seg);
                boolean isStatic2 = false;
                FieldAccessInfo accessInfo2 = this.document.createFieldAccessInfo(seg, nStart, nEnd, currentType, segInfo, isLast, isStatic2);
                marks.add(new ScriptLine.Mark(nStart, nEnd, this.getFieldTokenType(segInfo), accessInfo2));
                currentType = segInfo != null ? segInfo.getTypeInfo() : null;
                continue;
            }
            marks.add(new ScriptLine.Mark(nStart, nEnd, TokenType.UNDEFINED_VAR));
            break;
        }
    }

    private TokenType getFieldTokenType(FieldInfo fieldInfo) {
        if (fieldInfo != null && fieldInfo.isEnumConstant()) {
            return TokenType.ENUM_CONSTANT;
        }
        return TokenType.GLOBAL_FIELD;
    }

    private Character getNonWhitespaceBefore(int pos) {
        int before;
        for (before = pos - 1; before >= 0 && Character.isWhitespace(this.text.charAt(before)); --before) {
        }
        return before >= 0 ? Character.valueOf(this.text.charAt(before)) : null;
    }

    private boolean isStaticContext(ChainContext ctx) {
        if (ctx.currentIndex <= 0) {
            return false;
        }
        String previousSegment = ctx.chain.segments.get(ctx.currentIndex - 1);
        int prevPos = ctx.chain.positions.get(ctx.currentIndex - 1)[0];
        return TypeResolver.isStaticAccessExpression(previousSegment, prevPos, this.document);
    }

    private static class MarkResult {
        final ScriptLine.Mark mark;
        final TypeInfo nextType;

        MarkResult(ScriptLine.Mark mark, TypeInfo nextType) {
            this.mark = mark;
            this.nextType = nextType;
        }
    }

    private static class ChainContext {
        ChainData chain;
        int currentIndex;
        TypeInfo currentType;
        ScriptTypeInfo enclosingType;
        int startIndex;
        boolean firstIsThis;
        boolean firstIsSuper;
        boolean firstIsPrecededByDot;

        private ChainContext() {
        }
    }

    private static class ChainData {
        final List<String> segments = new ArrayList<String>();
        final List<int[]> positions = new ArrayList<int[]>();

        private ChainData() {
        }
    }
}

