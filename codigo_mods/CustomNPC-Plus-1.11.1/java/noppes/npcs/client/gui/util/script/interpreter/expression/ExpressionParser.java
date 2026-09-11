/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.expression;

import java.util.ArrayList;
import java.util.List;
import noppes.npcs.client.gui.util.script.interpreter.expression.ExpressionNode;
import noppes.npcs.client.gui.util.script.interpreter.expression.ExpressionToken;
import noppes.npcs.client.gui.util.script.interpreter.expression.OperatorType;

public class ExpressionParser {
    private final List<ExpressionToken> tokens;
    private int pos;

    public ExpressionParser(List<ExpressionToken> tokens) {
        this.tokens = tokens;
        this.pos = 0;
    }

    public ExpressionNode parse() {
        if (this.tokens.isEmpty()) {
            return null;
        }
        return this.parseLambdaOrExpression();
    }

    private ExpressionNode parseLambdaOrExpression() {
        int checkpoint = this.pos;
        ArrayList<String> paramNames = null;
        int lambdaStart = this.current().getStart();
        boolean isJSArrow = false;
        if (this.check(ExpressionToken.TokenKind.IDENTIFIER)) {
            String paramName = this.current().getText();
            this.advance();
            if (this.check(ExpressionToken.TokenKind.LAMBDA_ARROW)) {
                paramNames = new ArrayList();
                paramNames.add(paramName);
            } else if (this.check(ExpressionToken.TokenKind.JS_ARROW)) {
                paramNames = new ArrayList();
                paramNames.add(paramName);
                isJSArrow = true;
            } else {
                this.pos = checkpoint;
            }
        } else if (this.check(ExpressionToken.TokenKind.LEFT_PAREN)) {
            block27: {
                this.advance();
                paramNames = new ArrayList<String>();
                if (!this.check(ExpressionToken.TokenKind.RIGHT_PAREN)) {
                    while (true) {
                        if (!this.check(ExpressionToken.TokenKind.IDENTIFIER)) {
                            this.pos = checkpoint;
                            paramNames = null;
                            break block27;
                        }
                        paramNames.add(this.current().getText());
                        this.advance();
                        if (!this.check(ExpressionToken.TokenKind.COMMA)) break;
                        this.advance();
                    }
                    if (!this.check(ExpressionToken.TokenKind.RIGHT_PAREN)) {
                        this.pos = checkpoint;
                        paramNames = null;
                    }
                }
            }
            if (paramNames != null) {
                if (this.check(ExpressionToken.TokenKind.RIGHT_PAREN)) {
                    this.advance();
                    if (!this.check(ExpressionToken.TokenKind.LAMBDA_ARROW)) {
                        if (this.check(ExpressionToken.TokenKind.JS_ARROW)) {
                            isJSArrow = true;
                        } else {
                            this.pos = checkpoint;
                            paramNames = null;
                        }
                    }
                } else {
                    this.pos = checkpoint;
                    paramNames = null;
                }
            }
        }
        if (paramNames != null) {
            ExpressionNode body;
            int bodyEnd;
            this.advance();
            boolean isBlock = false;
            if (this.check(ExpressionToken.TokenKind.LEFT_BRACE)) {
                isBlock = true;
                int braceStart = this.current().getStart();
                this.advance();
                int depth = 1;
                while (depth > 0 && this.pos < this.tokens.size() && !this.check(ExpressionToken.TokenKind.EOF)) {
                    if (this.check(ExpressionToken.TokenKind.LEFT_BRACE)) {
                        ++depth;
                    } else if (this.check(ExpressionToken.TokenKind.RIGHT_BRACE)) {
                        --depth;
                    }
                    if (depth <= 0) continue;
                    this.advance();
                }
                bodyEnd = this.current().getEnd();
                if (this.check(ExpressionToken.TokenKind.RIGHT_BRACE)) {
                    this.advance();
                }
                body = new ExpressionNode.StringLiteralNode("<block>", braceStart, bodyEnd);
            } else {
                body = this.parseExpressionInternal(0);
                if (body == null) {
                    return null;
                }
                bodyEnd = body.getEnd();
            }
            if (isJSArrow) {
                return new ExpressionNode.JSArrowNode(paramNames, body, isBlock, lambdaStart, bodyEnd);
            }
            return new ExpressionNode.LambdaNode(paramNames, body, isBlock, lambdaStart, bodyEnd);
        }
        return this.parseExpressionInternal(0);
    }

    private ExpressionToken current() {
        if (this.pos >= this.tokens.size()) {
            return this.tokens.get(this.tokens.size() - 1);
        }
        return this.tokens.get(this.pos);
    }

    private ExpressionToken advance() {
        ExpressionToken tok = this.current();
        if (this.pos < this.tokens.size()) {
            ++this.pos;
        }
        return tok;
    }

    private boolean check(ExpressionToken.TokenKind kind) {
        return this.current().getKind() == kind;
    }

    private boolean match(ExpressionToken.TokenKind kind) {
        if (this.check(kind)) {
            this.advance();
            return true;
        }
        return false;
    }

    private ExpressionNode parseExpression(int minPrecedence) {
        if (minPrecedence == 0) {
            return this.parseLambdaOrExpression();
        }
        return this.parseExpressionInternal(minPrecedence);
    }

    private ExpressionNode parseExpressionInternal(int minPrecedence) {
        ExpressionNode left = this.parsePrefixExpression();
        if (left == null) {
            return null;
        }
        while (true) {
            int precedence;
            if (this.check(ExpressionToken.TokenKind.QUESTION) && minPrecedence <= 2) {
                left = this.parseTernary(left);
                continue;
            }
            OperatorType op = this.getCurrentBinaryOperator();
            if (op == null || (precedence = op.getPrecedence()) < minPrecedence) break;
            this.advance();
            int nextMinPrecedence = op.getAssociativity() == OperatorType.Associativity.LEFT ? precedence + 1 : precedence;
            ExpressionNode right = this.parseExpression(nextMinPrecedence);
            if (right == null) break;
            if (op == OperatorType.INSTANCEOF) {
                String typeName = this.extractTypeName(right);
                left = new ExpressionNode.InstanceofNode(left, typeName, left.getStart(), right.getEnd());
                continue;
            }
            if (op.isAssignment()) {
                left = new ExpressionNode.AssignmentNode(left, op, right, left.getStart(), right.getEnd());
                continue;
            }
            left = new ExpressionNode.BinaryOpNode(left, op, right, left.getStart(), right.getEnd());
        }
        return this.parsePostfixExpression(left);
    }

    private String extractTypeName(ExpressionNode node) {
        if (node instanceof ExpressionNode.IdentifierNode) {
            return ((ExpressionNode.IdentifierNode)node).getName();
        }
        if (node instanceof ExpressionNode.MemberAccessNode) {
            ExpressionNode.MemberAccessNode ma = (ExpressionNode.MemberAccessNode)node;
            return this.extractTypeName(ma.getTarget()) + "." + ma.getMemberName();
        }
        return "Object";
    }

    private ExpressionNode parseTernary(ExpressionNode condition) {
        int start = condition.getStart();
        this.advance();
        ExpressionNode thenExpr = this.parseExpression(0);
        if (thenExpr == null) {
            return condition;
        }
        if (!this.match(ExpressionToken.TokenKind.COLON)) {
            return condition;
        }
        ExpressionNode elseExpr = this.parseExpression(2);
        if (elseExpr == null) {
            return condition;
        }
        return new ExpressionNode.TernaryNode(condition, thenExpr, elseExpr, start, elseExpr.getEnd());
    }

    private OperatorType getCurrentBinaryOperator() {
        OperatorType op;
        ExpressionToken tok = this.current();
        if (tok.getKind() == ExpressionToken.TokenKind.OPERATOR && (op = tok.getOperatorType()) != null && (op.isBinary() || op.isAssignment())) {
            return op;
        }
        if (tok.getKind() == ExpressionToken.TokenKind.INSTANCEOF) {
            return OperatorType.INSTANCEOF;
        }
        return null;
    }

    private ExpressionNode parsePrefixExpression() {
        OperatorType op;
        ExpressionToken tok = this.current();
        int start = tok.getStart();
        if (tok.getKind() == ExpressionToken.TokenKind.FUNCTION) {
            return this.parseFunctionExpression();
        }
        if (tok.getKind() == ExpressionToken.TokenKind.OPERATOR && (op = tok.getOperatorType()) != null && this.isUnaryOperator(op)) {
            this.advance();
            ExpressionNode operand = this.parsePrefixExpression();
            if (operand == null) {
                return null;
            }
            OperatorType unaryOp = this.toUnaryOperator(op);
            return new ExpressionNode.UnaryOpNode(unaryOp, operand, true, start, operand.getEnd());
        }
        if (tok.getKind() == ExpressionToken.TokenKind.LEFT_PAREN) {
            return this.parseCastOrParenthesized();
        }
        return this.parsePrimaryExpression();
    }

    private ExpressionNode parseFunctionExpression() {
        int start = this.current().getStart();
        this.advance();
        String functionName = null;
        if (this.check(ExpressionToken.TokenKind.IDENTIFIER)) {
            functionName = this.current().getText();
            this.advance();
        }
        if (!this.check(ExpressionToken.TokenKind.LEFT_PAREN)) {
            return null;
        }
        this.advance();
        ArrayList<String> params = new ArrayList<String>();
        while (!this.check(ExpressionToken.TokenKind.RIGHT_PAREN) && !this.check(ExpressionToken.TokenKind.EOF)) {
            if (!this.check(ExpressionToken.TokenKind.IDENTIFIER)) {
                return null;
            }
            params.add(this.current().getText());
            this.advance();
            if (this.check(ExpressionToken.TokenKind.COMMA)) {
                this.advance();
                continue;
            }
            if (this.check(ExpressionToken.TokenKind.RIGHT_PAREN)) continue;
            return null;
        }
        if (!this.check(ExpressionToken.TokenKind.RIGHT_PAREN)) {
            return null;
        }
        this.advance();
        if (!this.check(ExpressionToken.TokenKind.LEFT_BRACE)) {
            return null;
        }
        this.advance();
        StringBuilder bodyBuilder = new StringBuilder();
        int depth = 1;
        while (depth > 0 && !this.check(ExpressionToken.TokenKind.EOF)) {
            ExpressionToken bodyToken = this.current();
            if (bodyToken.getKind() == ExpressionToken.TokenKind.LEFT_BRACE) {
                ++depth;
            } else if (bodyToken.getKind() == ExpressionToken.TokenKind.RIGHT_BRACE && --depth == 0) break;
            bodyBuilder.append(bodyToken.getText()).append(" ");
            this.advance();
        }
        String bodyText = bodyBuilder.toString().trim();
        int end = this.current().getEnd();
        if (!this.check(ExpressionToken.TokenKind.RIGHT_BRACE)) {
            return null;
        }
        this.advance();
        return new ExpressionNode.JSFunctionNode(functionName, params, bodyText, start, end);
    }

    private boolean isUnaryOperator(OperatorType op) {
        switch (op) {
            case ADD: 
            case SUBTRACT: 
            case LOGICAL_NOT: 
            case BITWISE_NOT: 
            case PRE_INCREMENT: 
            case PRE_DECREMENT: {
                return true;
            }
        }
        return false;
    }

    private OperatorType toUnaryOperator(OperatorType op) {
        switch (op) {
            case ADD: {
                return OperatorType.UNARY_PLUS;
            }
            case SUBTRACT: {
                return OperatorType.UNARY_MINUS;
            }
        }
        return op;
    }

    private ExpressionNode parseCastOrParenthesized() {
        ExpressionNode inner;
        int start = this.current().getStart();
        this.advance();
        if (this.check(ExpressionToken.TokenKind.IDENTIFIER)) {
            String possibleType = this.current().getText();
            int savedPos = this.pos;
            this.advance();
            while (this.check(ExpressionToken.TokenKind.DOT)) {
                this.advance();
                if (!this.check(ExpressionToken.TokenKind.IDENTIFIER)) continue;
                possibleType = possibleType + "." + this.current().getText();
                this.advance();
            }
            while (this.check(ExpressionToken.TokenKind.LEFT_BRACKET)) {
                this.advance();
                if (!this.match(ExpressionToken.TokenKind.RIGHT_BRACKET)) {
                    this.pos = savedPos;
                    break;
                }
                possibleType = possibleType + "[]";
            }
            if (this.check(ExpressionToken.TokenKind.RIGHT_PAREN)) {
                ExpressionNode expr;
                this.advance();
                if (this.canStartExpression() && (expr = this.parsePrefixExpression()) != null) {
                    return new ExpressionNode.CastNode(possibleType, expr, start, expr.getEnd());
                }
            }
            this.pos = savedPos;
        }
        if ((inner = this.parseExpression(0)) != null && this.match(ExpressionToken.TokenKind.RIGHT_PAREN)) {
            ExpressionNode.ParenthesizedNode paren = new ExpressionNode.ParenthesizedNode(inner, start, this.current().getStart());
            return this.parseAccessChain(paren);
        }
        return inner;
    }

    private boolean canStartExpression() {
        ExpressionToken.TokenKind kind = this.current().getKind();
        switch (kind) {
            case IDENTIFIER: 
            case INT_LITERAL: 
            case LONG_LITERAL: 
            case FLOAT_LITERAL: 
            case DOUBLE_LITERAL: 
            case BOOLEAN_LITERAL: 
            case CHAR_LITERAL: 
            case STRING_LITERAL: 
            case NULL_LITERAL: 
            case NEW: 
            case LEFT_PAREN: 
            case OPERATOR: {
                return true;
            }
        }
        return false;
    }

    private ExpressionNode parsePrimaryExpression() {
        ExpressionToken tok = this.current();
        int start = tok.getStart();
        switch (tok.getKind()) {
            case INT_LITERAL: {
                this.advance();
                return new ExpressionNode.IntLiteralNode(tok.getText(), start, tok.getEnd());
            }
            case LONG_LITERAL: {
                this.advance();
                return new ExpressionNode.LongLiteralNode(tok.getText(), start, tok.getEnd());
            }
            case FLOAT_LITERAL: {
                this.advance();
                return new ExpressionNode.FloatLiteralNode(tok.getText(), start, tok.getEnd());
            }
            case DOUBLE_LITERAL: {
                this.advance();
                return new ExpressionNode.DoubleLiteralNode(tok.getText(), start, tok.getEnd());
            }
            case BOOLEAN_LITERAL: {
                this.advance();
                return new ExpressionNode.BooleanLiteralNode("true".equals(tok.getText()), start, tok.getEnd());
            }
            case CHAR_LITERAL: {
                this.advance();
                return new ExpressionNode.CharLiteralNode(tok.getText(), start, tok.getEnd());
            }
            case STRING_LITERAL: {
                this.advance();
                return new ExpressionNode.StringLiteralNode(tok.getText(), start, tok.getEnd());
            }
            case NULL_LITERAL: {
                this.advance();
                return new ExpressionNode.NullLiteralNode(start, tok.getEnd());
            }
            case NEW: {
                return this.parseNewExpression();
            }
            case IDENTIFIER: {
                return this.parseIdentifierOrMethodCall();
            }
            case LEFT_PAREN: {
                return this.parseCastOrParenthesized();
            }
        }
        return null;
    }

    private ExpressionNode parseNewExpression() {
        int start = this.current().getStart();
        this.advance();
        if (!this.check(ExpressionToken.TokenKind.IDENTIFIER)) {
            return null;
        }
        StringBuilder typeName = new StringBuilder(this.current().getText());
        this.advance();
        while (this.check(ExpressionToken.TokenKind.DOT)) {
            this.advance();
            if (!this.check(ExpressionToken.TokenKind.IDENTIFIER)) continue;
            typeName.append(".").append(this.current().getText());
            this.advance();
        }
        ArrayList<ExpressionNode> args = new ArrayList();
        if (this.match(ExpressionToken.TokenKind.LEFT_PAREN)) {
            args = this.parseArgumentList();
            this.match(ExpressionToken.TokenKind.RIGHT_PAREN);
        }
        return new ExpressionNode.NewNode(typeName.toString(), args, start, this.current().getStart());
    }

    private ExpressionNode parseIdentifierOrMethodCall() {
        int start = this.current().getStart();
        String name = this.current().getText();
        this.advance();
        ExpressionNode.IdentifierNode result = new ExpressionNode.IdentifierNode(name, start, this.current().getStart());
        return this.parseAccessChain(result);
    }

    private ExpressionNode parseAccessChain(ExpressionNode base) {
        while (true) {
            int end;
            if (this.check(ExpressionToken.TokenKind.METHOD_REFERENCE)) {
                this.advance();
                if (!this.check(ExpressionToken.TokenKind.IDENTIFIER)) break;
                String methodName = this.current().getText();
                end = this.current().getEnd();
                this.advance();
                base = new ExpressionNode.MethodReferenceNode(base, methodName, false, base.getStart(), end);
                break;
            }
            if (this.check(ExpressionToken.TokenKind.LEFT_PAREN)) {
                ExpressionNode target;
                String methodName;
                this.advance();
                List<ExpressionNode> args = this.parseArgumentList();
                end = this.current().getStart();
                this.match(ExpressionToken.TokenKind.RIGHT_PAREN);
                if (base instanceof ExpressionNode.IdentifierNode) {
                    methodName = ((ExpressionNode.IdentifierNode)base).getName();
                    target = null;
                } else if (base instanceof ExpressionNode.MemberAccessNode) {
                    ExpressionNode.MemberAccessNode ma = (ExpressionNode.MemberAccessNode)base;
                    methodName = ma.getMemberName();
                    target = ma.getTarget();
                } else {
                    methodName = "apply";
                    target = base;
                }
                base = new ExpressionNode.MethodCallNode(target, methodName, args, base.getStart(), end);
                continue;
            }
            if (this.check(ExpressionToken.TokenKind.DOT)) {
                this.advance();
                if (!this.check(ExpressionToken.TokenKind.IDENTIFIER)) break;
                String memberName = this.current().getText();
                end = this.current().getEnd();
                this.advance();
                base = new ExpressionNode.MemberAccessNode(base, memberName, base.getStart(), end);
                continue;
            }
            if (!this.check(ExpressionToken.TokenKind.LEFT_BRACKET)) break;
            this.advance();
            ExpressionNode index = this.parseExpression(0);
            end = this.current().getStart();
            this.match(ExpressionToken.TokenKind.RIGHT_BRACKET);
            if (index == null) continue;
            base = new ExpressionNode.ArrayAccessNode(base, index, base.getStart(), end);
        }
        return base;
    }

    private List<ExpressionNode> parseArgumentList() {
        ArrayList<ExpressionNode> args = new ArrayList<ExpressionNode>();
        if (this.check(ExpressionToken.TokenKind.RIGHT_PAREN)) {
            return args;
        }
        ExpressionNode first = this.parseExpression(0);
        if (first != null) {
            args.add(first);
        }
        while (this.match(ExpressionToken.TokenKind.COMMA)) {
            ExpressionNode arg = this.parseExpression(0);
            if (arg == null) continue;
            args.add(arg);
        }
        return args;
    }

    private ExpressionNode parsePostfixExpression(ExpressionNode expr) {
        ExpressionToken tok;
        if (expr == null) {
            return null;
        }
        while ((tok = this.current()).getKind() == ExpressionToken.TokenKind.OPERATOR) {
            OperatorType op = tok.getOperatorType();
            if (op == OperatorType.PRE_INCREMENT) {
                this.advance();
                expr = new ExpressionNode.UnaryOpNode(OperatorType.POST_INCREMENT, expr, false, expr.getStart(), tok.getEnd());
                continue;
            }
            if (op != OperatorType.PRE_DECREMENT) break;
            this.advance();
            expr = new ExpressionNode.UnaryOpNode(OperatorType.POST_DECREMENT, expr, false, expr.getStart(), tok.getEnd());
        }
        return expr;
    }
}

