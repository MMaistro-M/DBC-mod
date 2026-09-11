/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script;

import java.util.ArrayList;
import java.util.List;

public class UndoManager {
    private final List<UndoState> undoStack = new ArrayList<UndoState>();
    private final List<UndoState> redoStack = new ArrayList<UndoState>();
    private boolean isUndoing = false;
    private static final int MAX_STACK_SIZE = 100;

    public void recordState(String text, int cursorPosition) {
        if (this.isUndoing) {
            return;
        }
        this.undoStack.add(new UndoState(text, cursorPosition));
        this.redoStack.clear();
        while (this.undoStack.size() > 100) {
            this.undoStack.remove(0);
        }
    }

    public UndoState undo(String currentText, int currentCursor) {
        if (this.undoStack.isEmpty()) {
            return null;
        }
        this.isUndoing = true;
        this.redoStack.add(new UndoState(currentText, currentCursor));
        UndoState state = this.undoStack.remove(this.undoStack.size() - 1);
        this.isUndoing = false;
        return state;
    }

    public UndoState redo(String currentText, int currentCursor) {
        if (this.redoStack.isEmpty()) {
            return null;
        }
        this.isUndoing = true;
        this.undoStack.add(new UndoState(currentText, currentCursor));
        UndoState state = this.redoStack.remove(this.redoStack.size() - 1);
        this.isUndoing = false;
        return state;
    }

    public boolean canUndo() {
        return !this.undoStack.isEmpty();
    }

    public boolean canRedo() {
        return !this.redoStack.isEmpty();
    }

    public void clear() {
        this.undoStack.clear();
        this.redoStack.clear();
    }

    public boolean isUndoing() {
        return this.isUndoing;
    }

    public void setUndoing(boolean undoing) {
        this.isUndoing = undoing;
    }

    public List<UndoState> getUndoList() {
        return this.undoStack;
    }

    public List<UndoState> getRedoList() {
        return this.redoStack;
    }

    public static class UndoState {
        public final String text;
        public final int cursorPosition;

        public UndoState(String text, int cursorPosition) {
            this.text = text;
            this.cursorPosition = cursorPosition;
        }
    }
}

