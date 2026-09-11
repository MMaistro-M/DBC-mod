/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.NBTTagCompound
 */
package riskyken.armourersWorkshop.common.data;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import riskyken.armourersWorkshop.api.common.painting.IPantable;
import riskyken.armourersWorkshop.api.common.skin.cubes.ICubeColour;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.skin.cubes.CubeColour;
import riskyken.armourersWorkshop.common.skin.cubes.CubeRegistry;
import riskyken.armourersWorkshop.common.skin.cubes.ICube;

public class MiniCube
implements IPantable {
    private ICube type;
    private byte x;
    private byte y;
    private byte z;
    private CubeColour cc = new CubeColour();

    public MiniCube(ICube type) {
        this.type = type;
    }

    public MiniCube(ByteBuf buf) {
        this.type = CubeRegistry.INSTANCE.getCubeFormId(buf.readByte());
        this.readFromBuf(buf);
    }

    @Override
    public void setColour(int colour) {
        this.cc.setColour(colour);
    }

    public ICubeColour getCubeColour() {
        return this.cc;
    }

    public boolean isGlowing() {
        return false;
    }

    public int getZ() {
        return this.z;
    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

    public void setY(byte b) {
        this.y = b;
    }

    public void setX(byte b) {
        this.x = b;
    }

    public void setZ(byte b) {
        this.z = b;
    }

    @Override
    public void setColour(int colour, int side) {
        this.cc.setColour(colour, side);
    }

    @Override
    public void setColour(byte[] rgb, int side) {
        this.cc.setRed(rgb[0], side);
        this.cc.setGreen(rgb[1], side);
        this.cc.setBlue(rgb[2], side);
    }

    @Override
    public void setColour(ICubeColour cubeColour) {
        this.cc = new CubeColour(cubeColour);
    }

    @Override
    public int getColour(int side) {
        return this.cc.getColour(side);
    }

    @Override
    public void setPaintType(PaintType paintType, int side) {
        this.cc.setPaintType((byte)paintType.getKey(), side);
    }

    @Override
    public PaintType getPaintType(int side) {
        return PaintType.getPaintTypeFormSKey(this.cc.getPaintType(side));
    }

    @Override
    public ICubeColour getColour() {
        return this.cc;
    }

    public void writeToBuf(ByteBuf buf) {
        buf.writeByte((int)this.x);
        buf.writeByte((int)this.y);
        buf.writeByte((int)this.z);
        NBTTagCompound compound = new NBTTagCompound();
        this.cc.writeToNBT(compound);
        ByteBufUtils.writeTag((ByteBuf)buf, (NBTTagCompound)compound);
    }

    private void readFromBuf(ByteBuf buf) {
        this.x = buf.readByte();
        this.y = buf.readByte();
        this.z = buf.readByte();
        NBTTagCompound compound = ByteBufUtils.readTag((ByteBuf)buf);
        this.cc.readFromNBT(compound);
    }
}

