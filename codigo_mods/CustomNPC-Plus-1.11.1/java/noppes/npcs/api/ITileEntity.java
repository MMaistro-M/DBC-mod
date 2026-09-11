/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 */
package noppes.npcs.api;

import net.minecraft.tileentity.TileEntity;
import noppes.npcs.api.IBlock;
import noppes.npcs.api.INbt;
import noppes.npcs.api.IPos;
import noppes.npcs.api.IWorld;

public interface ITileEntity {
    public int getBlockMetadata();

    public IWorld getWorld();

    public void setWorld(IWorld var1);

    public TileEntity getMCTileEntity();

    public void markDirty();

    public void readFromNBT(INbt var1);

    public double getDistanceFrom(double var1, double var3, double var5);

    public double getDistanceFrom(IPos var1);

    public IBlock getBlockType();

    public boolean isInvalid();

    public void invalidate();

    public void validate();

    public void updateContainingBlockInfo();

    public INbt getNBT();
}

