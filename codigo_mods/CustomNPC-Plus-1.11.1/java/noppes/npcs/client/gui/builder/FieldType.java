/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 */
package noppes.npcs.client.gui.builder;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public enum FieldType {
    FLOAT,
    INT,
    BOOLEAN,
    ENUM,
    STRING_ENUM,
    SUB_GUI,
    STRING,
    SECTION_HEADER,
    LABEL,
    EFFECTS_LIST,
    CUSTOM_EFFECTS_LIST,
    EFFECT_ACTIONS_LIST,
    ROW,
    STRING_BROWSE;

}

