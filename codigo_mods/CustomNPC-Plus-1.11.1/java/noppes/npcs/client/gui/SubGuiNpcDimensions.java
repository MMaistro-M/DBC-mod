/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.DimensionsGetPacket;
import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.data.SpawnData;

public class SubGuiNpcDimensions
extends SubGuiInterface
implements IScrollData {
    private final SpawnData data;
    private GuiCustomScroll allDimensions;
    private HashMap<String, Integer> dimensionMap = new HashMap();
    private GuiCustomScroll dataDimensions;

    public SubGuiNpcDimensions(SpawnData data) {
        this.data = data;
        this.setBackground("menubg.png");
        this.xSize = 346;
        this.ySize = 216;
        this.closeOnEsc = true;
        PacketClient.sendClient(new DimensionsGetPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        if (this.allDimensions == null) {
            this.allDimensions = new GuiCustomScroll(this, 0);
            this.allDimensions.setSize(140, 180);
        }
        this.allDimensions.guiLeft = this.guiLeft + 4;
        this.allDimensions.guiTop = this.guiTop + 14;
        this.addScroll(this.allDimensions);
        this.addLabel(new GuiNpcLabel(1, "spawning.availableDimensions", this.guiLeft + 4, this.guiTop + 4));
        if (this.dataDimensions == null) {
            this.dataDimensions = new GuiCustomScroll(this, 1);
            this.dataDimensions.setSize(140, 180);
        }
        this.dataDimensions.guiLeft = this.guiLeft + 200;
        this.dataDimensions.guiTop = this.guiTop + 14;
        this.addScroll(this.dataDimensions);
        this.addLabel(new GuiNpcLabel(2, "spawning.spawningDimensions", this.guiLeft + 200, this.guiTop + 4));
        ArrayList<String> dimensions = new ArrayList<String>();
        Set<Map.Entry<String, Integer>> entrySet = this.dimensionMap.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            if (!this.data.dimensions.contains(entry.getValue())) continue;
            dimensions.add(entry.getKey());
        }
        this.dataDimensions.setList(dimensions);
        this.addButton(new GuiNpcButton(1, this.guiLeft + 145, this.guiTop + 40, 55, 20, ">"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 145, this.guiTop + 62, 55, 20, "<"));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 145, this.guiTop + 90, 55, 20, ">>"));
        this.addButton(new GuiNpcButton(4, this.guiLeft + 145, this.guiTop + 112, 55, 20, "<<"));
        this.addButton(new GuiNpcButton(66, this.guiLeft + 260, this.guiTop + 194, 60, 20, "gui.done"));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 1 && this.allDimensions.hasSelected()) {
            this.data.dimensions.add(this.dimensionMap.get(this.allDimensions.getSelected()));
            this.allDimensions.selected = -1;
            this.func_73866_w_();
        }
        if (button.field_146127_k == 2 && this.dataDimensions.hasSelected()) {
            this.data.dimensions.remove(this.dimensionMap.get(this.dataDimensions.getSelected()));
            this.dataDimensions.selected = -1;
            this.func_73866_w_();
        }
        if (button.field_146127_k == 3) {
            this.data.dimensions.clear();
            this.data.dimensions.addAll(this.dimensionMap.values());
            this.allDimensions.selected = -1;
            this.func_73866_w_();
        }
        if (button.field_146127_k == 4) {
            this.data.dimensions.clear();
            this.allDimensions.selected = -1;
            this.func_73866_w_();
        }
        if (button.field_146127_k == 66) {
            this.close();
        }
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        this.allDimensions.setList(list);
        this.dimensionMap = data;
        ArrayList<String> dimensions = new ArrayList<String>();
        Set<Map.Entry<String, Integer>> entrySet = this.dimensionMap.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            if (!this.data.dimensions.contains(entry.getValue())) continue;
            dimensions.add(entry.getKey());
        }
        this.dataDimensions.setList(dimensions);
        this.func_73866_w_();
    }

    @Override
    public void setSelected(String selected) {
    }
}

