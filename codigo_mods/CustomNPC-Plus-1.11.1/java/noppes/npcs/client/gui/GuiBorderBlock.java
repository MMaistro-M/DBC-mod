/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui;

import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.TileEntityGetPacket;
import kamkeel.npcs.network.packets.request.TileEntitySavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.blocks.tiles.TileBorder;
import noppes.npcs.client.gui.SubGuiNpcAvailability;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.IGuiData;

public class GuiBorderBlock
extends GuiNPCInterface
implements IGuiData {
    private TileBorder tile;

    public GuiBorderBlock(int x, int y, int z) {
        this.tile = (TileBorder)this.player.field_70170_p.func_147438_o(x, y, z);
        PacketClient.sendClient(new TileEntityGetPacket(x, y, z));
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addButton(new GuiNpcButton(4, this.guiLeft + 40, this.guiTop + 40, 120, 20, "Availability Options"));
        this.addLabel(new GuiNpcLabel(0, "Height", this.guiLeft + 1, this.guiTop + 76, 0xFFFFFF));
        this.addTextField(new GuiNpcTextField(0, this, this.field_146289_q, this.guiLeft + 60, this.guiTop + 71, 40, 20, this.tile.height + ""));
        this.getTextField((int)0).integersOnly = true;
        this.getTextField(0).setMinMaxDefault(0, 500, 6);
        this.addLabel(new GuiNpcLabel(1, "Message", this.guiLeft + 1, this.guiTop + 100, 0xFFFFFF));
        this.addTextField(new GuiNpcTextField(1, this, this.field_146289_q, this.guiLeft + 60, this.guiTop + 95, 200, 20, this.tile.message));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 40, this.guiTop + 190, 120, 20, "Done"));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 0) {
            this.close();
        }
        if (id == 4) {
            this.save();
            this.setSubGui(new SubGuiNpcAvailability(this.tile.availability));
        }
    }

    @Override
    public void save() {
        if (this.tile == null) {
            return;
        }
        this.tile.height = this.getTextField(0).getInteger();
        this.tile.message = this.getTextField(1).func_146179_b();
        NBTTagCompound compound = new NBTTagCompound();
        this.tile.func_145841_b(compound);
        PacketClient.sendClient(new TileEntitySavePacket(compound));
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.tile.func_145839_a(compound);
    }
}

