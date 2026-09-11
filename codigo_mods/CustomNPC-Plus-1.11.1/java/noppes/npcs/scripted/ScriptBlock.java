/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package noppes.npcs.scripted;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import noppes.npcs.api.IBlock;
import noppes.npcs.api.IContainer;
import noppes.npcs.api.INbt;
import noppes.npcs.api.IPos;
import noppes.npcs.api.ITileEntity;
import noppes.npcs.api.IWorld;
import noppes.npcs.scripted.CustomNPCsException;
import noppes.npcs.scripted.NpcAPI;

public class ScriptBlock
implements IBlock {
    protected IWorld world;
    protected Block block;
    protected BlockPos pos;
    protected IPos bPos;
    protected ITileEntity tile;

    public ScriptBlock(World world, Block block, BlockPos pos) {
        this.world = NpcAPI.Instance().getIWorld(world);
        this.block = block;
        this.pos = pos;
        this.bPos = NpcAPI.Instance().getIPos(pos);
        this.tile = NpcAPI.Instance().getITileEntity(world.func_147438_o(pos.getX(), pos.getY(), pos.getZ()));
    }

    @Override
    public IPos getPosition() {
        return this.bPos;
    }

    public IPos getPos() {
        return this.bPos;
    }

    @Override
    public boolean setPosition(IPos pos, IWorld world) {
        if (pos == null || world == null || !world.setBlock(pos, this)) {
            return false;
        }
        this.world.removeBlock(this.getPos());
        this.world = world;
        this.block = world.getBlock(pos).getMCBlock();
        this.pos = pos.getMCPos();
        this.bPos = pos;
        this.tile = NpcAPI.Instance().getITileEntity(world.getMCWorld().func_147438_o(pos.getX(), pos.getY(), pos.getZ()));
        return true;
    }

    @Override
    public boolean setPosition(IPos pos) {
        return this.setPosition(pos, this.world);
    }

    public boolean setPos(IPos pos, IWorld world) {
        return this.setPosition(pos, world);
    }

    public boolean setPos(IPos pos) {
        return this.setPosition(pos);
    }

    @Override
    public boolean setPosition(int x, int y, int z, IWorld world) {
        return this.setPos(NpcAPI.Instance().getIPos(x, y, z), world);
    }

    @Override
    public boolean setPosition(int x, int y, int z) {
        return this.setPos(NpcAPI.Instance().getIPos(x, y, z));
    }

    public boolean setPos(int x, int y, int z, IWorld world) {
        return this.setPosition(x, y, z, world);
    }

    public boolean setPos(int x, int y, int z) {
        return this.setPosition(x, y, z);
    }

    @Override
    public int getX() {
        return this.pos.getX();
    }

    @Override
    public int getY() {
        return this.pos.getY();
    }

    @Override
    public int getZ() {
        return this.pos.getZ();
    }

    @Override
    public void remove() {
        this.world.getMCWorld().func_147468_f(this.getX(), this.getY(), this.getZ());
    }

    @Override
    public boolean isAir() {
        return this.block.isAir((IBlockAccess)this.world.getMCWorld(), this.getX(), this.getY(), this.getZ());
    }

    @Override
    public IBlock setBlock(String blockName) {
        Block block = (Block)Block.field_149771_c.func_82594_a((Object)new ResourceLocation(blockName));
        if (block == null) {
            return this;
        }
        this.world.getMCWorld().func_147449_b(this.getX(), this.getY(), this.getZ(), block);
        return NpcAPI.Instance().getIBlock(this.world, this.getX(), this.getY(), this.getZ());
    }

    @Override
    public IBlock setBlock(IBlock block) {
        this.world.getMCWorld().func_147449_b(this.getX(), this.getY(), this.getZ(), block.getMCBlock());
        return NpcAPI.Instance().getIBlock(this.world, this.getX(), this.getY(), this.getZ());
    }

    @Override
    public boolean isContainer() {
        return this.tile != null && this.tile.getMCTileEntity() != null && this.tile.getMCTileEntity() instanceof IInventory && ((IInventory)this.tile.getMCTileEntity()).func_70302_i_() > 0;
    }

    @Override
    public IContainer getContainer() {
        if (!this.isContainer()) {
            throw new CustomNPCsException("This block is not a container", new Object[0]);
        }
        return NpcAPI.Instance().getIContainer((IInventory)this.tile.getMCTileEntity());
    }

    @Override
    public String getName() {
        return Block.field_149771_c.func_148750_c((Object)this.block) + "";
    }

    @Override
    public String getDisplayName() {
        return this.tile == null || this.tile.getMCTileEntity() == null ? this.getName() : this.tile.getMCTileEntity().field_145854_h.func_149702_O();
    }

    @Override
    public IWorld getWorld() {
        return this.world;
    }

    @Override
    public Block getMCBlock() {
        return this.block;
    }

    @Override
    public boolean hasTileEntity() {
        return this.tile != null;
    }

    @Override
    public ITileEntity getTileEntity() {
        return this.tile;
    }

    @Override
    public void setTileEntity(ITileEntity tileEntity) {
        this.world.setTileEntity(this.pos.getX(), this.pos.getY(), this.pos.getZ(), tileEntity);
        this.tile = tileEntity;
    }

    @Override
    public TileEntity getMCTileEntity() {
        if (this.tile == null) {
            return null;
        }
        return this.tile.getMCTileEntity();
    }

    @Override
    public INbt getTileEntityNBT() {
        if (this.tile == null || this.tile.getMCTileEntity() == null) {
            return null;
        }
        NBTTagCompound compound = new NBTTagCompound();
        this.tile.getMCTileEntity().func_145841_b(compound);
        return NpcAPI.Instance().getINbt(compound);
    }

    @Override
    public boolean canCollide(double maxVolume) {
        AxisAlignedBB alignedBB = this.block.func_149668_a((World)this.world.getMCWorld(), this.getX(), this.getY(), this.getZ());
        if (alignedBB == null) {
            return false;
        }
        double xEdge = alignedBB.field_72336_d - alignedBB.field_72340_a;
        double yEdge = alignedBB.field_72337_e - alignedBB.field_72338_b;
        double zEdge = alignedBB.field_72334_f - alignedBB.field_72339_c;
        return Math.abs(xEdge * yEdge * zEdge) > maxVolume;
    }

    @Override
    public boolean canCollide() {
        return this.canCollide(0.0);
    }

    @Override
    public void setBounds(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        this.block.func_149676_a(minX, minY, minZ, maxX, maxY, maxZ);
    }

    @Override
    public double getBlockBoundsMinX() {
        return this.block.func_149704_x();
    }

    @Override
    public double getBlockBoundsMinY() {
        return this.block.func_149665_z();
    }

    @Override
    public double getBlockBoundsMinZ() {
        return this.block.func_149706_B();
    }

    @Override
    public double getBlockBoundsMaxX() {
        return this.block.func_149753_y();
    }

    @Override
    public double getBlockBoundsMaxY() {
        return this.block.func_149669_A();
    }

    @Override
    public double getBlockBoundsMaxZ() {
        return this.block.func_149693_C();
    }

    public String toString() {
        return this.getName() + " @" + this.getPos() + (this.world == null ? "" : " in DIM" + this.world.getDimensionID());
    }

    public boolean equals(Object obj) {
        return obj instanceof IBlock && ((ScriptBlock)obj).getName().equals(this.getName()) && ((ScriptBlock)obj).getWorld() == this.getWorld() && ((ScriptBlock)obj).getPos().equals(this.getPos());
    }
}

