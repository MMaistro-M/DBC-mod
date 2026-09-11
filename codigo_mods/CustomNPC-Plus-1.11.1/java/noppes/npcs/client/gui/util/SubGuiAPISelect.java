/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.SubGuiConfirmLink;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.APIRegistry;

public class SubGuiAPISelect
extends SubGuiInterface
implements ISubGuiListener {
    private final List<String> names;
    private final List<String> urls;

    public SubGuiAPISelect() {
        LinkedHashMap<String, String> entries = APIRegistry.Instance.getEntries();
        this.names = new ArrayList<String>(entries.keySet());
        this.urls = new ArrayList<String>(entries.values());
        this.xSize = 180;
        this.ySize = 40 + this.names.size() * 24;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(0, "gui.api", this.guiLeft + 10, this.guiTop + 10, 0xFFFFFF));
        for (int i = 0; i < this.names.size(); ++i) {
            this.addButton(new GuiNpcButton(i, this.guiLeft + 10, this.guiTop + 28 + i * 24, 160, 20, this.names.get(i)));
        }
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        this.func_73733_a(this.guiLeft, this.guiTop, this.guiLeft + this.xSize, this.guiTop + this.ySize, -1072689136, -1072689136);
        super.func_73863_a(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id >= 0 && id < this.urls.size()) {
            this.setSubGui(new SubGuiConfirmLink(this.urls.get(id)));
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiConfirmLink && ((SubGuiConfirmLink)subgui).confirmed) {
            this.close();
        }
    }

    @Override
    public void save() {
    }
}

