/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 */
package somehussar.gui;

import net.minecraft.client.gui.GuiScreen;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.SubGuiInterface;
import somehussar.gui.annotationHandling.GuiFieldHandler;
import somehussar.gui.annotationHandling.field.EditableField;

public class AnnotatedGui<T>
extends SubGuiInterface {
    private final GuiFieldHandler.ClassMetadata metadata;

    public AnnotatedGui(T object, String[] groups, GuiScreen parent) {
        this(object, groups, parent, 256, 216);
    }

    public AnnotatedGui(T object, String[] groups, GuiScreen parent, int xSize, int ySize) {
        this.metadata = object != null ? GuiFieldHandler.getMetadata(object.getClass()) : null;
        this.setBackground("menubg.png");
        this.xSize = xSize;
        this.ySize = ySize;
        this.closeOnEsc = true;
        this.parent = parent;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        if (this.metadata == null) {
            return;
        }
        int i = 0;
        for (EditableField field : this.metadata.getParent().getDeclaredFields()) {
            this.addLabel(new GuiNpcLabel(i++, field.getName(), this.guiLeft + 8, this.guiTop + i * 15 + 5));
        }
    }

    @Override
    public void save() {
    }
}

