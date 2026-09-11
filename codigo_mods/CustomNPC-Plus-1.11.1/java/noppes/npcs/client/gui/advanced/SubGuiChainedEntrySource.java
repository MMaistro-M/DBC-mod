/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui.advanced;

import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.advanced.SubGuiSimpleChoice;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;

public class SubGuiChainedEntrySource
extends SubGuiSimpleChoice {
    public static final int SOURCE_NONE = -1;
    public static final int SOURCE_NPC_SLOTS = 0;
    public static final int SOURCE_LOAD_PRESET = 1;
    public static final int SOURCE_CREATE_NEW = 2;
    public static final int SOURCE_BUILT_IN = 3;
    private final boolean showNpcOption;

    public SubGuiChainedEntrySource() {
        this(true);
    }

    public SubGuiChainedEntrySource(boolean showNpcOption) {
        this.showNpcOption = showNpcOption;
        this.setBackground("menubg.png");
        this.xSize = 200;
        this.ySize = showNpcOption ? 132 : 110;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 10;
        this.addLabel(new GuiNpcLabel(0, "ability.entrySource", this.guiLeft + 10, y));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 10, y += 20, 180, 20, "ability.byReference"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 10, y += 22, 180, 20, "ability.createNew"));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 10, y += 22, 180, 20, "gui.builtin"));
        if (this.showNpcOption) {
            this.addButton(new GuiNpcButton(0, this.guiLeft + 10, y += 22, 180, 20, "ability.fromNpc"));
        }
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id >= 0 && id <= 3) {
            this.setResult(id);
            this.close();
        }
    }
}

