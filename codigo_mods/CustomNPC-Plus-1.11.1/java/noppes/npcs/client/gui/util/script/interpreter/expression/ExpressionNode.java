/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.expression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import noppes.npcs.client.gui.util.script.interpreter.InnerCallableScope;
import noppes.npcs.client.gui.util.script.interpreter.expression.OperatorType;
import noppes.npcs.client.gui.util.script.interpreter.expression.TypeRules;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public abstract class ExpressionNode {
    protected final int start;
    protected final int end;

    protected ExpressionNode(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public abstract TypeInfo resolveType(TypeResolverContext var1);

    public static interface TypeResolverContext {
        public TypeInfo resolveIdentifier(String var1);

        public TypeInfo resolveMemberAccess(TypeInfo var1, String var2);

        public TypeInfo resolveMethodCall(TypeInfo var1, String var2, TypeInfo[] var3);

        public TypeInfo resolveArrayAccess(TypeInfo var1);

        public TypeInfo resolveTypeName(String var1);
    }

    public static class MethodReferenceNode
    extends ExpressionNode {
        private final ExpressionNode target;
        private final String methodName;
        private final boolean isStatic;

        public MethodReferenceNode(ExpressionNode target, String methodName, boolean isStatic, int start, int end) {
            super(start, end);
            this.target = target;
            this.methodName = methodName;
            this.isStatic = isStatic;
        }

        public ExpressionNode getTarget() {
            return this.target;
        }

        public String getMethodName() {
            return this.methodName;
        }

        public boolean isStatic() {
            return this.isStatic;
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            return TypeInfo.fromClass(Object.class);
        }

        public String toString() {
            return this.target + "::" + this.methodName;
        }
    }

    public static class JSArrowNode
    extends ExpressionNode {
        private final List<String> parameterNames;
        private final ExpressionNode body;
        private final boolean isBlock;
        private InnerCallableScope scopeRef;

        public JSArrowNode(List<String> parameterNames, ExpressionNode body, boolean isBlock, int start, int end) {
            super(start, end);
            this.parameterNames = parameterNames != null ? new ArrayList<String>(parameterNames) : new ArrayList();
            this.body = body;
            this.isBlock = isBlock;
        }

        public List<String> getParameterNames() {
            return Collections.unmodifiableList(this.parameterNames);
        }

        public ExpressionNode getBody() {
            return this.body;
        }

        public boolean isBlock() {
            return this.isBlock;
        }

        public InnerCallableScope getScopeRef() {
            return this.scopeRef;
        }

        public void setScopeRef(InnerCallableScope scope) {
            this.scopeRef = scope;
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            return TypeInfo.fromClass(Object.class);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append(String.join((CharSequence)", ", this.parameterNames));
            sb.append(") => ");
            if (this.isBlock) {
                sb.append("{ ... }");
            } else if (this.body != null) {
                sb.append(this.body);
            }
            return sb.toString();
        }
    }

    public static class JSFunctionNode
    extends ExpressionNode {
        private final String name;
        private final List<String> parameterNames;
        private final String bodyText;
        private InnerCallableScope scopeRef;

        public JSFunctionNode(String name, List<String> parameterNames, String bodyText, int start, int end) {
            super(start, end);
            this.name = name;
            this.parameterNames = parameterNames != null ? new ArrayList<String>(parameterNames) : new ArrayList();
            this.bodyText = bodyText;
            this.scopeRef = null;
        }

        public String getName() {
            return this.name;
        }

        public List<String> getParameterNames() {
            return Collections.unmodifiableList(this.parameterNames);
        }

        public String getBodyText() {
            return this.bodyText;
        }

        public InnerCallableScope getScopeRef() {
            return this.scopeRef;
        }

        public void setScopeRef(InnerCallableScope scope) {
            this.scopeRef = scope;
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            return TypeInfo.fromClass(Object.class);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("function");
            if (this.name != null && !this.name.isEmpty()) {
                sb.append(" ").append(this.name);
            }
            sb.append("(");
            sb.append(String.join((CharSequence)", ", this.parameterNames));
            sb.append(") { ");
            if (this.bodyText.length() > 30) {
                sb.append(this.bodyText.substring(0, 30)).append("...");
            } else {
                sb.append(this.bodyText);
            }
            sb.append(" }");
            return sb.toString();
        }
    }

    public static class LambdaNode
    extends ExpressionNode {
        private final List<String> parameterNames;
        private final ExpressionNode body;
        private final boolean isBlock;
        private InnerCallableScope scopeRef;

        public LambdaNode(List<String> parameterNames, ExpressionNode body, boolean isBlock, int start, int end) {
            super(start, end);
            this.parameterNames = parameterNames != null ? new ArrayList<String>(parameterNames) : new ArrayList();
            this.body = body;
            this.isBlock = isBlock;
            this.scopeRef = null;
        }

        public List<String> getParameterNames() {
            return Collections.unmodifiableList(this.parameterNames);
        }

        public ExpressionNode getBody() {
            return this.body;
        }

        public boolean isBlock() {
            return this.isBlock;
        }

        public InnerCallableScope getScopeRef() {
            return this.scopeRef;
        }

        public void setScopeRef(InnerCallableScope scope) {
            this.scopeRef = scope;
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            return TypeInfo.fromClass(Object.class);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.parameterNames.size() == 1 && !this.isBlock) {
                sb.append(this.parameterNames.get(0));
            } else {
                sb.append("(");
                sb.append(String.join((CharSequence)", ", this.parameterNames));
                sb.append(")");
            }
            sb.append(" -> ");
            if (this.isBlock) {
                sb.append("{ ... }");
            } else {
                sb.append(this.body);
            }
            return sb.toString();
        }
    }

    public static class ParenthesizedNode
    extends ExpressionNode {
        private final ExpressionNode inner;

        public ParenthesizedNode(ExpressionNode inner, int start, int end) {
            super(start, end);
            this.inner = inner;
        }

        public ExpressionNode getInner() {
            return this.inner;
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            return this.inner.resolveType(resolver);
        }
    }

    public static class AssignmentNode
    extends ExpressionNode {
        private final ExpressionNode target;
        private final OperatorType operator;
        private final ExpressionNode value;

        public AssignmentNode(ExpressionNode target, OperatorType operator, ExpressionNode value, int start, int end) {
            super(start, end);
            this.target = target;
            this.operator = operator;
            this.value = value;
        }

        public ExpressionNode getTarget() {
            return this.target;
        }

        public OperatorType getOperator() {
            return this.operator;
        }

        public ExpressionNode getValue() {
            return this.value;
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            return this.target.resolveType(resolver);
        }
    }

    public static class CastNode
    extends ExpressionNode {
        private final String typeName;
        private final ExpressionNode expression;

        public CastNode(String typeName, ExpressionNode expression, int start, int end) {
            super(start, end);
            this.typeName = typeName;
            this.expression = expression;
        }

        public String getTypeName() {
            return this.typeName;
        }

        public ExpressionNode getExpression() {
            return this.expression;
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            return resolver.resolveTypeName(this.typeName);
        }
    }

    public static class InstanceofNode
    extends ExpressionNode {
        private final ExpressionNode expression;
        private final String typeName;

        public InstanceofNode(ExpressionNode expression, String typeName, int start, int end) {
            super(start, end);
            this.expression = expression;
            this.typeName = typeName;
        }

        public ExpressionNode getExpression() {
            return this.expression;
        }

        public String getTypeName() {
            return this.typeName;
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            return TypeInfo.fromPrimitive("boolean");
        }
    }

    public static class TernaryNode
    extends ExpressionNode {
        private final ExpressionNode condition;
        private final ExpressionNode thenExpr;
        private final ExpressionNode elseExpr;

        public TernaryNode(ExpressionNode condition, ExpressionNode thenExpr, ExpressionNode elseExpr, int start, int end) {
            super(start, end);
            this.condition = condition;
            this.thenExpr = thenExpr;
            this.elseExpr = elseExpr;
        }

        public ExpressionNode getCondition() {
            return this.condition;
        }

        public ExpressionNode getThenExpr() {
            return this.thenExpr;
        }

        public ExpressionNode getElseExpr() {
            return this.elseExpr;
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            TypeInfo thenType = this.thenExpr.resolveType(resolver);
            TypeInfo elseType = this.elseExpr.resolveType(resolver);
            return TypeRules.resolveTernaryType(thenType, elseType);
        }
    }

    public static class UnaryOpNode
    extends ExpressionNode {
        private final OperatorType operator;
        private final ExpressionNode operand;
        private final boolean prefix;

        public UnaryOpNode(OperatorType operator, ExpressionNode operand, boolean prefix, int start, int end) {
            super(start, end);
            this.operator = operator;
            this.operand = operand;
            this.prefix = prefix;
        }

        public OperatorType getOperator() {
            return this.operator;
        }

        public ExpressionNode getOperand() {
            return this.operand;
        }

        public boolean isPrefix() {
            return this.prefix;
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            TypeInfo operandType = this.operand.resolveType(resolver);
            return TypeRules.resolveUnaryOperatorType(this.operator, operandType);
        }
    }

    public static class BinaryOpNode
    extends ExpressionNode {
        private final ExpressionNode left;
        private final OperatorType operator;
        private final ExpressionNode right;

        public BinaryOpNode(ExpressionNode left, OperatorType operator, ExpressionNode right, int start, int end) {
            super(start, end);
            this.left = left;
            this.operator = operator;
            this.right = right;
        }

        public ExpressionNode getLeft() {
            return this.left;
        }

        public OperatorType getOperator() {
            return this.operator;
        }

        public ExpressionNode getRight() {
            return this.right;
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            TypeInfo leftType = this.left.resolveType(resolver);
            TypeInfo rightType = this.right.resolveType(resolver);
            return TypeRules.resolveBinaryOperatorType(this.operator, leftType, rightType);
        }
    }

    public static class NewNode
    extends ExpressionNode {
        private final String typeName;
        private final List<ExpressionNode> arguments;

        public NewNode(String typeName, List<ExpressionNode> arguments, int start, int end) {
            super(start, end);
            this.typeName = typeName;
            this.arguments = arguments != null ? new ArrayList<ExpressionNode>(arguments) : new ArrayList();
        }

        public String getTypeName() {
            return this.typeName;
        }

        public List<ExpressionNode> getArguments() {
            return Collections.unmodifiableList(this.arguments);
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            return resolver.resolveTypeName(this.typeName);
        }
    }

    public static class ArrayAccessNode
    extends ExpressionNode {
        private final ExpressionNode array;
        private final ExpressionNode index;

        public ArrayAccessNode(ExpressionNode array, ExpressionNode index, int start, int end) {
            super(start, end);
            this.array = array;
            this.index = index;
        }

        public ExpressionNode getArray() {
            return this.array;
        }

        public ExpressionNode getIndex() {
            return this.index;
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            TypeInfo arrayType = this.array.resolveType(resolver);
            if (arrayType == null || !arrayType.isResolved()) {
                return null;
            }
            return resolver.resolveArrayAccess(arrayType);
        }
    }

    public static class MethodCallNode
    extends ExpressionNode {
        private final ExpressionNode target;
        private final String methodName;
        private final List<ExpressionNode> arguments;

        public MethodCallNode(ExpressionNode target, String methodName, List<ExpressionNode> arguments, int start, int end) {
            super(start, end);
            this.target = target;
            this.methodName = methodName;
            this.arguments = arguments != null ? new ArrayList<ExpressionNode>(arguments) : new ArrayList();
        }

        public ExpressionNode getTarget() {
            return this.target;
        }

        public String getMethodName() {
            return this.methodName;
        }

        public List<ExpressionNode> getArguments() {
            return Collections.unmodifiableList(this.arguments);
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            TypeInfo targetType = this.target != null ? this.target.resolveType(resolver) : null;
            TypeInfo[] argTypes = new TypeInfo[this.arguments.size()];
            for (int i = 0; i < this.arguments.size(); ++i) {
                argTypes[i] = this.arguments.get(i).resolveType(resolver);
            }
            return resolver.resolveMethodCall(targetType, this.methodName, argTypes);
        }
    }

    public static class MemberAccessNode
    extends ExpressionNode {
        private final ExpressionNode target;
        private final String memberName;

        public MemberAccessNode(ExpressionNode target, String memberName, int start, int end) {
            super(start, end);
            this.target = target;
            this.memberName = memberName;
        }

        public ExpressionNode getTarget() {
            return this.target;
        }

        public String getMemberName() {
            return this.memberName;
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            TypeInfo targetType = this.target.resolveType(resolver);
            if (targetType == null || !targetType.isResolved()) {
                return null;
            }
            return resolver.resolveMemberAccess(targetType, this.memberName);
        }
    }

    public static class IdentifierNode
    extends ExpressionNode {
        private final String name;

        public IdentifierNode(String name, int start, int end) {
            super(start, end);
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            return resolver.resolveIdentifier(this.name);
        }
    }

    public static class NullLiteralNode
    extends ExpressionNode {
        public NullLiteralNode(int start, int end) {
            super(start, end);
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            return TypeInfo.unresolved("null", "<null>");
        }
    }

    public static class StringLiteralNode
    extends ExpressionNode {
        private final String value;

        public StringLiteralNode(String value, int start, int end) {
            super(start, end);
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            return TypeInfo.string();
        }
    }

    public static class CharLiteralNode
    extends ExpressionNode {
        public CharLiteralNode(String value, int start, int end) {
            super(start, end);
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            return TypeInfo.fromPrimitive("char");
        }
    }

    public static class BooleanLiteralNode
    extends ExpressionNode {
        public BooleanLiteralNode(boolean value, int start, int end) {
            super(start, end);
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            return TypeInfo.fromPrimitive("boolean");
        }
    }

    public static class DoubleLiteralNode
    extends ExpressionNode {
        public DoubleLiteralNode(String value, int start, int end) {
            super(start, end);
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            return TypeInfo.fromPrimitive("double");
        }
    }

    public static class FloatLiteralNode
    extends ExpressionNode {
        public FloatLiteralNode(String value, int start, int end) {
            super(start, end);
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            return TypeInfo.fromPrimitive("float");
        }
    }

    public static class LongLiteralNode
    extends ExpressionNode {
        public LongLiteralNode(String value, int start, int end) {
            super(start, end);
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            return TypeInfo.fromPrimitive("long");
        }
    }

    public static class IntLiteralNode
    extends ExpressionNode {
        private final String value;

        public IntLiteralNode(String value, int start, int end) {
            super(start, end);
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        @Override
        public TypeInfo resolveType(TypeResolverContext resolver) {
            return TypeInfo.fromPrimitive("int");
        }
    }
}

