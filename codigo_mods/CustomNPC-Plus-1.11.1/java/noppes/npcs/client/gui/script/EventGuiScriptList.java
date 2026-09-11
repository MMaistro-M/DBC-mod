/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui.script;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.data.IScriptUnit;

public class EventGuiScriptList
extends SubGuiInterface {
    private GuiCustomScroll scroll1;
    private GuiCustomScroll scroll2;
    private IScriptUnit container;
    private List<String> scripts;

    public EventGuiScriptList(List<String> scripts, IScriptUnit container) {
        this.container = container;
        this.setBackground("menubg.png");
        this.xSize = 346;
        this.ySize = 216;
        if (scripts == null) {
            scripts = new ArrayList<String>();
        }
        this.scripts = scripts;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        if (this.scroll1 == null) {
            this.scroll1 = new GuiCustomScroll(this, 0);
            this.scroll1.setSize(140, 180);
        }
        this.scroll1.guiLeft = this.guiLeft + 4;
        this.scroll1.guiTop = this.guiTop + 14;
        this.addScroll(this.scroll1);
        this.addLabel(new GuiNpcLabel(1, "script.availableScripts", this.guiLeft + 4, this.guiTop + 4));
        if (this.scroll2 == null) {
            this.scroll2 = new GuiCustomScroll(this, 1);
            this.scroll2.setSize(140, 180);
        }
        this.scroll2.guiLeft = this.guiLeft + 200;
        this.scroll2.guiTop = this.guiTop + 14;
        this.addScroll(this.scroll2);
        this.addLabel(new GuiNpcLabel(2, "script.loadedScripts", this.guiLeft + 200, this.guiTop + 4));
        ArrayList<String> temp = new ArrayList<String>(this.scripts);
        temp.removeAll(this.getScripts());
        this.scroll1.setList(temp);
        this.scroll2.setList(this.getScripts());
        this.addButton(new GuiNpcButton(1, this.guiLeft + 145, this.guiTop + 40, 55, 20, ">"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 145, this.guiTop + 62, 55, 20, "<"));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 145, this.guiTop + 90, 55, 20, ">>"));
        this.addButton(new GuiNpcButton(4, this.guiLeft + 145, this.guiTop + 112, 55, 20, "<<"));
        this.addButton(new GuiNpcButton(66, this.guiLeft + 260, this.guiTop + 194, 60, 20, "gui.done"));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 1 && this.scroll1.hasSelected()) {
            this.getScripts().add(this.scroll1.getSelected());
            this.scroll1.selected = -1;
            this.scroll2.selected = -1;
            this.func_73866_w_();
        }
        if (button.field_146127_k == 2 && this.scroll2.hasSelected()) {
            this.getScripts().remove(this.scroll2.getSelected());
            this.scroll2.selected = -1;
            this.func_73866_w_();
        }
        if (button.field_146127_k == 3) {
            this.getScripts().clear();
            for (String script : this.scripts) {
                this.getScripts().add(script);
            }
            this.scroll1.selected = -1;
            this.scroll2.selected = -1;
            this.func_73866_w_();
        }
        if (button.field_146127_k == 4) {
            this.getScripts().clear();
            this.scroll1.selected = -1;
            this.scroll2.selected = -1;
            this.func_73866_w_();
        }
        if (button.field_146127_k == 66) {
            this.close();
        }
    }

    public List<String> getScripts() {
        return this.container.getExternalScripts();
    }

    @Override
    public void save() {
    }
}

