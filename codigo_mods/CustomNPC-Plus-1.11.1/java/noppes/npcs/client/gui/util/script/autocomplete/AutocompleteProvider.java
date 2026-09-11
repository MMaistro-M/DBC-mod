/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.autocomplete;

import java.util.List;
import noppes.npcs.client.gui.util.script.autocomplete.AutocompleteItem;

public interface AutocompleteProvider {
    public List<AutocompleteItem> getSuggestions(Context var1);

    public boolean canProvide(Context var1);

    public static class Context {
        public final String text;
        public final int cursorPosition;
        public final int lineNumber;
        public final int columnPosition;
        public final String currentLine;
        public final String prefix;
        public final int prefixStart;
        public final boolean isMemberAccess;
        public final String receiverExpression;
        public final boolean explicitTrigger;
        public final boolean methodsOnly;

        public Context(String text, int cursorPosition, int lineNumber, int columnPosition, String currentLine, String prefix, int prefixStart, boolean isMemberAccess, String receiverExpression, boolean explicitTrigger) {
            this(text, cursorPosition, lineNumber, columnPosition, currentLine, prefix, prefixStart, isMemberAccess, receiverExpression, explicitTrigger, false);
        }

        public Context(String text, int cursorPosition, int lineNumber, int columnPosition, String currentLine, String prefix, int prefixStart, boolean isMemberAccess, String receiverExpression, boolean explicitTrigger, boolean methodsOnly) {
            this.text = text;
            this.cursorPosition = cursorPosition;
            this.lineNumber = lineNumber;
            this.columnPosition = columnPosition;
            this.currentLine = currentLine;
            this.prefix = prefix;
            this.prefixStart = prefixStart;
            this.isMemberAccess = isMemberAccess;
            this.receiverExpression = receiverExpression;
            this.explicitTrigger = explicitTrigger;
            this.methodsOnly = methodsOnly;
        }
    }
}

