/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 */
package noppes.npcs.blocks.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import noppes.npcs.TextBlock;

public class TileBigSign
extends TileEntity {
    public int rotation;
    public boolean canEdit = true;
    public boolean hasChanged = true;
    private String signText = "";
    public TextBlock block;

    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.rotation = compound.func_74762_e("SignRotation");
        this.setText(compound.func_74779_i("SignText"));
    }

    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        compound.func_74768_a("SignRotation", this.rotation);
        compound.func_74778_a("SignText", this.signText);
    }

    public boolean canUpdate() {
        return false;
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound compound = pkt.func_148857_g();
        this.func_145839_a(compound);
    }

    public void setText(String text) {
        this.signText = text;
        this.hasChanged = true;
    }

    public String getText() {
        return this.signText;
    }

    public Packet func_145844_m() {
        NBTTagCompound compound = new NBTTagCompound();
        this.func_145841_b(compound);
        S35PacketUpdateTileEntity packet = new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, compound);
        return packet;
    }
}

