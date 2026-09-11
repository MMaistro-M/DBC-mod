/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 */
package riskyken.armourersWorkshop.common.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import riskyken.armourersWorkshop.api.common.painting.IPantable;
import riskyken.armourersWorkshop.api.common.skin.cubes.ICubeColour;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.skin.cubes.CubeColour;

public class TileEntityColourable
extends TileEntity
implements IPantable {
    private ICubeColour colour;

    public TileEntityColourable() {
        this.colour = new CubeColour();
    }

    public TileEntityColourable(int colour) {
        this.colour = new CubeColour(colour);
    }

    public boolean canUpdate() {
        return false;
    }

    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        if (compound.func_74764_b("colour")) {
            this.colour.setColour(compound.func_74762_e("colour"));
        } else {
            this.colour.readFromNBT(compound);
        }
    }

    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        this.colour.writeToNBT(compound);
    }

    public Packet func_145844_m() {
        NBTTagCompound compound = new NBTTagCompound();
        this.func_145841_b(compound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 5, compound);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        this.func_145839_a(packet.func_148857_g());
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    @Override
    public void setColour(int colour) {
        this.colour.setColour(colour);
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    @Override
    public void setColour(int colour, int side) {
        this.colour.setColour(colour, side);
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    @Override
    public void setColour(byte[] rgb, int side) {
        this.colour.setRed(rgb[0], side);
        this.colour.setGreen(rgb[1], side);
        this.colour.setBlue(rgb[2], side);
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    @Override
    public void setColour(ICubeColour colour) {
        this.colour = colour;
    }

    @Override
    public int getColour(int side) {
        Color saveColour = new Color(this.colour.getRed(side) & 0xFF, this.colour.getGreen(side) & 0xFF, this.colour.getBlue(side) & 0xFF);
        return saveColour.getRGB();
    }

    @Override
    public void setPaintType(PaintType paintType, int side) {
        this.colour.setPaintType((byte)paintType.getKey(), side);
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    @Override
    public PaintType getPaintType(int side) {
        return PaintType.getPaintTypeFormSKey(this.colour.getPaintType(side));
    }

    @Override
    public ICubeColour getColour() {
        return this.colour;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1));
    }

    @SideOnly(value=Side.CLIENT)
    public double func_145833_n() {
        return 2048.0;
    }
}

