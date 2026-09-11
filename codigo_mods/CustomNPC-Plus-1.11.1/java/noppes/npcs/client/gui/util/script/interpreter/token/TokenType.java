/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.token;

import noppes.npcs.client.gui.util.script.interpreter.token.ScriptColorScheme;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public enum TokenType {
    COMMENT(-8947849, 140),
    STRING(-15219037, 130),
    JSDOC_TAG(-7438224, 125, true, true),
    JSDOC_TYPE(-6648708, 124),
    UNUSED_IMPORT(-10066330, 119),
    KEYWORD(-43691, 100, true, false),
    INTERFACE_DECL(-8326410, 85),
    ENUM_DECL(-43521, 85),
    ENUM_CONSTANT(-11141121, 84, true, true),
    CLASS_DECL(-16733526, 85),
    IMPORTED_CLASS(-16733526, 75),
    GENERIC_TYPE_PARAM(-16713062, 76),
    TYPE_DECL(-16733526, 70),
    METHOD_DECL(-16733696, 60),
    METHOD_CALL(-11141291, 50),
    UNDEFINED_VAR(-5636096, 20),
    PARAMETER(-11184641, 36),
    GLOBAL_FIELD(-11141121, 35),
    LOCAL_FIELD(-171, 25),
    STATIC_FINAL_FIELD(-43521, 36, true, true),
    LITERAL(-8797953, 40),
    VARIABLE(-1, 30),
    DEFAULT(-1, 0);

    private final int defaultHexColor;
    private final int priority;
    private final boolean defaultBold;
    private final boolean defaultItalic;

    private TokenType(int hexColor, int priority) {
        this(hexColor, priority, false, false);
    }

    private TokenType(int hexColor, int priority, boolean bold, boolean italic) {
        this.defaultHexColor = hexColor;
        this.priority = priority;
        this.defaultBold = bold;
        this.defaultItalic = italic;
    }

    public int getHexColor() {
        return ScriptColorScheme.styles[this.ordinal()].hexColor;
    }

    public int getPriority() {
        return this.priority;
    }

    public boolean isBold() {
        return ScriptColorScheme.styles[this.ordinal()].bold;
    }

    public boolean isItalic() {
        return ScriptColorScheme.styles[this.ordinal()].italic;
    }

    int getDefaultHexColor() {
        return this.defaultHexColor;
    }

    boolean getDefaultBold() {
        return this.defaultBold;
    }

    boolean getDefaultItalic() {
        return this.defaultItalic;
    }

    public static TokenType getByType(TypeInfo typeInfo) {
        if (typeInfo == null || !typeInfo.isResolved()) {
            return UNDEFINED_VAR;
        }
        if ("any".equals(typeInfo.getFullName())) {
            return KEYWORD;
        }
        return typeInfo.getTokenType();
    }

    public static int getColor(TypeInfo typeInfo) {
        return TokenType.getByType(typeInfo).getHexColor();
    }

    public static int getPackageColor() {
        return IMPORTED_CLASS.getHexColor();
    }

    public char toColorCode() {
        switch (this) {
            case COMMENT: 
            case LITERAL: 
            case UNUSED_IMPORT: {
                return '7';
            }
            case STRING: {
                return '5';
            }
            case JSDOC_TAG: {
                return '6';
            }
            case JSDOC_TYPE: {
                return '3';
            }
            case KEYWORD: {
                return 'c';
            }
            case ENUM_DECL: 
            case STATIC_FINAL_FIELD: {
                return 'd';
            }
            case ENUM_CONSTANT: {
                return '9';
            }
            case INTERFACE_DECL: 
            case GLOBAL_FIELD: {
                return 'b';
            }
            case GENERIC_TYPE_PARAM: {
                return 'a';
            }
            case CLASS_DECL: 
            case IMPORTED_CLASS: 
            case TYPE_DECL: {
                return '3';
            }
            case METHOD_DECL: {
                return '2';
            }
            case METHOD_CALL: {
                return 'a';
            }
            case LOCAL_FIELD: {
                return 'e';
            }
            case PARAMETER: {
                return '9';
            }
            case UNDEFINED_VAR: {
                return '4';
            }
        }
        return 'f';
    }
}

