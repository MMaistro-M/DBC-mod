/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.tileentity.TileEntity
 */
package noppes.npcs.api;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import noppes.npcs.api.IContainer;
import noppes.npcs.api.INbt;
import noppes.npcs.api.IPos;
import noppes.npcs.api.ITileEntity;
import noppes.npcs.api.IWorld;

public interface IBlock {
    public int getX();

    public int getY();

    public int getZ();

    public IPos getPosition();

    public boolean setPosition(IPos var1, IWorld var2);

    public boolean setPosition(IPos var1);

    public boolean setPosition(int var1, int var2, int var3, IWorld var4);

    public boolean setPosition(int var1, int var2, int var3);

    public String getName();

    public void remove();

    public boolean isAir();

    public IBlock setBlock(String var1);

    public IBlock setBlock(IBlock var1);

    public boolean isContainer();

    public IContainer getContainer();

    public IWorld getWorld();

    public boolean hasTileEntity();

    public ITileEntity getTileEntity();

    public void setTileEntity(ITileEntity var1);

    public TileEntity getMCTileEntity();

    public Block getMCBlock();

    public String getDisplayName();

    public INbt getTileEntityNBT();

    public boolean canCollide(double var1);

    public boolean canCollide();

    public void setBounds(float var1, float var2, float var3, float var4, float var5, float var6);

    public double getBlockBoundsMinX();

    public double getBlockBoundsMinY();

    public double getBlockBoundsMinZ();

    public double getBlockBoundsMaxX();

    public double getBlockBoundsMaxY();

    public double getBlockBoundsMaxZ();
}

