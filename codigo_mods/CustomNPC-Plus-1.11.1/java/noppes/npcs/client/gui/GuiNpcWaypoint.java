/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui;

import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.TileEntitySavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.blocks.tiles.TileWaypoint;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;

public class GuiNpcWaypoint
extends GuiNPCInterface {
    private TileWaypoint tile;

    public GuiNpcWaypoint(int x, int y, int z) {
        this.tile = (TileWaypoint)this.player.field_70170_p.func_147438_o(x, y, z);
        this.xSize = 265;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(0, "gui.name", this.guiLeft + 1, this.guiTop + 76, 0xFFFFFF));
        this.addTextField(new GuiNpcTextField(0, this, this.field_146289_q, this.guiLeft + 60, this.guiTop + 71, 200, 20, this.tile.name));
        this.addLabel(new GuiNpcLabel(1, "gui.range", this.guiLeft + 1, this.guiTop + 97, 0xFFFFFF));
        this.addTextField(new GuiNpcTextField(1, this, this.field_146289_q, this.guiLeft + 60, this.guiTop + 92, 200, 20, this.tile.range + ""));
        this.getTextField((int)1).integersOnly = true;
        this.getTextField(1).setMinMaxDefault(2, 60, 10);
        this.addButton(new GuiNpcButton(0, this.guiLeft + 40, this.guiTop + 190, 120, 20, "Done"));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 0) {
            this.close();
        }
    }

    @Override
    public void save() {
        this.tile.name = this.getTextField(0).func_146179_b();
        this.tile.range = this.getTextField(1).getInteger();
        NBTTagCompound compound = new NBTTagCompound();
        this.tile.func_145841_b(compound);
        PacketClient.sendClient(new TileEntitySavePacket(compound));
    }
}

