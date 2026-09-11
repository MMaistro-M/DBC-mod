/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.IPos;
import noppes.npcs.api.handler.data.ITransportLocation;
import noppes.npcs.controllers.TransportController;
import noppes.npcs.controllers.data.TransportCategory;
import noppes.npcs.scripted.NpcAPI;

public class TransportLocation
implements ITransportLocation {
    public int id = -1;
    public String name = "default name";
    public double posX;
    public double posY;
    public double posZ;
    public int type = 0;
    public int dimension = 0;
    public TransportCategory category;

    public void readNBT(NBTTagCompound compound) {
        if (compound == null) {
            return;
        }
        this.id = compound.func_74762_e("Id");
        this.posX = compound.func_74769_h("PosX");
        this.posY = compound.func_74769_h("PosY");
        this.posZ = compound.func_74769_h("PosZ");
        this.type = compound.func_74762_e("Type");
        this.dimension = compound.func_74762_e("Dimension");
        this.name = compound.func_74779_i("Name");
    }

    public NBTTagCompound writeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74768_a("Id", this.id);
        compound.func_74780_a("PosX", this.posX);
        compound.func_74780_a("PosY", this.posY);
        compound.func_74780_a("PosZ", this.posZ);
        compound.func_74768_a("Type", this.type);
        compound.func_74768_a("Dimension", this.dimension);
        compound.func_74778_a("Name", this.name);
        return compound;
    }

    public boolean isDefault() {
        return this.type == 1;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    @Override
    public int getDimension() {
        return this.dimension;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public void setPosition(int x, int y, int z) {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
    }

    @Override
    public void setPosition(IPos pos) {
        this.posX = pos.getX();
        this.posY = pos.getY();
        this.posZ = pos.getZ();
    }

    public IPos getPos() {
        return NpcAPI.Instance().getIPos(this.posX, this.posY, this.posZ);
    }

    @Override
    public double getX() {
        return this.posX;
    }

    @Override
    public double getY() {
        return this.posY;
    }

    @Override
    public double getZ() {
        return this.posZ;
    }

    @Override
    public void save() {
        TransportController.getInstance().saveLocation(this.category.id, this);
    }
}

