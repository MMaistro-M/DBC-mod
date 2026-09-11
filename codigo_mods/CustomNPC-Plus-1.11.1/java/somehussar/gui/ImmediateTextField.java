/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 */
package somehussar.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import somehussar.gui.annotationHandling.field.EditableField;

public class ImmediateTextField
extends GuiNpcTextField {
    private final EditableField editableField;

    public ImmediateTextField(int id, EditableField field, int xPos, int yPos) {
        this(id, field, Minecraft.func_71410_x().field_71466_p, xPos, yPos);
    }

    public ImmediateTextField(int id, EditableField field, FontRenderer fontRenderer, int xPos, int yPos) {
        super(id, null, fontRenderer, xPos, yPos, 0, 0, null);
        this.editableField = field;
    }

    @Override
    public void drawTextBox(int mousX, int mousY) {
    }

    @Override
    public void func_146192_a(int xPos, int yPos, int k) {
        xPos -= this.field_146209_f;
        yPos -= this.field_146210_g;
    }
}

