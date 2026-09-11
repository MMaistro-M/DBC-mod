/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.key.impl;

import noppes.npcs.client.key.KeyPreset;
import noppes.npcs.client.key.KeyPresetManager;

public class ScriptEditorKeys
extends KeyPresetManager {
    public final KeyPreset COPY = this.add("Copy").setDefaultState(46, true, false, false);
    public final KeyPreset PASTE = this.add("Paste").setDefaultState(47, true, false, false);
    public final KeyPreset CUT = this.add("Cut").setDefaultState(45, true, false, false);
    public final KeyPreset DUPLICATE = this.add("Duplicate").setDefaultState(32, true, false, false);
    public final KeyPreset UNDO = this.add("Undo").setDefaultState(44, true, false, false);
    public final KeyPreset REDO = this.add("Redo").setDefaultState(21, true, false, false);
    public final KeyPreset FORMAT = this.add("Format Code").setDefaultState(33, false, true, false);
    public final KeyPreset TOGGLE_COMMENT = this.add("Toggle Comment").setDefaultState(53, true, false, false);
    public final KeyPreset DELETE_LINE = this.add("Delete Line").setDefaultState(211, true, false, false);
    public final KeyPreset MOVE_LINE_UP = this.add("Move Line Up").setDefaultState(200, false, true, false);
    public final KeyPreset MOVE_LINE_DOWN = this.add("Move Line Down").setDefaultState(208, false, true, false);
    public final KeyPreset SEARCH = this.add("Search").setDefaultState(33, true, false, false);
    public final KeyPreset SEARCH_REPLACE = this.add("Replace").setDefaultState(33, true, false, true);
    public final KeyPreset GO_TO_LINE = this.add("Go to Line").setDefaultState(34, true, false, false);
    public final KeyPreset RENAME = this.add("Rename").setDefaultState(19, true, false, false);
    public final KeyPreset FULLSCREEN = this.add("Toggle Fullscreen").setDefaultState(87, false, false, false);
    public final KeyPreset AUTOCOMPLETE = this.add("Autocomplete").setDefaultState(57, true, false, false);

    public ScriptEditorKeys() {
        super("script_editor");
        this.load();
    }
}

