/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui;

import java.util.HashMap;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.mainmenu.MainmenuAdvancedGetPacket;
import kamkeel.npcs.network.packets.request.mainmenu.MainmenuAdvancedSavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.select.GuiSoundSelection;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.data.Line;
import noppes.npcs.controllers.data.Lines;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNPCLinesEdit
extends GuiNPCInterface2
implements IGuiData,
ISubGuiListener,
ITextfieldListener {
    private Lines lines;
    private int selectedId = -1;
    private GuiSoundSelection gui;

    public GuiNPCLinesEdit(EntityNPCInterface npc, Lines lines) {
        super(npc);
        this.lines = lines;
        PacketClient.sendClient(new MainmenuAdvancedGetPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        for (int i = 0; i < 8; ++i) {
            String text = "";
            String sound = "";
            if (this.lines.lines.containsKey(i)) {
                Line line = this.lines.lines.get(i);
                text = line.getText();
                sound = line.getSound();
            }
            this.addTextField(new GuiNpcTextField(i, this, this.field_146289_q, this.guiLeft + 4, this.guiTop + 4 + i * 24, 200, 20, text));
            this.addTextField(new GuiNpcTextField(i + 8, this, this.field_146289_q, this.guiLeft + 208, this.guiTop + 4 + i * 24, 146, 20, sound));
            this.addButton(new GuiNpcButton(i, this.guiLeft + 358, this.guiTop + 4 + i * 24, 60, 20, "gui.select"));
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        this.selectedId = button.field_146127_k + 8;
        this.setSubGui(new GuiSoundSelection(this.getTextField(this.selectedId).func_146179_b()));
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.npc.advanced.readToNBT(compound);
        this.func_73866_w_();
    }

    private void saveLines() {
        HashMap<Integer, Line> lines = new HashMap<Integer, Line>();
        for (int i = 0; i < 8; ++i) {
            GuiNpcTextField tf = this.getTextField(i);
            GuiNpcTextField tf2 = this.getTextField(i + 8);
            if (tf.isEmpty() && tf2.isEmpty()) continue;
            Line line = new Line();
            line.setText(tf.func_146179_b());
            line.setSound(tf2.func_146179_b());
            lines.put(i, line);
        }
        this.lines.lines = lines;
    }

    @Override
    public void save() {
        this.saveLines();
        PacketClient.sendClient(new MainmenuAdvancedSavePacket(this.npc.advanced.writeToNBT(new NBTTagCompound())));
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        GuiSoundSelection gss = (GuiSoundSelection)subgui;
        if (gss.selectedResource != null) {
            this.getTextField(this.selectedId).func_146180_a(gss.selectedResource.toString());
            this.saveLines();
            this.func_73866_w_();
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        this.saveLines();
        this.func_73866_w_();
    }
}

