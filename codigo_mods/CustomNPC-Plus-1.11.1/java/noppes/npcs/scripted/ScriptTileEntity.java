/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package noppes.npcs.scripted;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import noppes.npcs.api.IBlock;
import noppes.npcs.api.INbt;
import noppes.npcs.api.IPos;
import noppes.npcs.api.ITileEntity;
import noppes.npcs.api.IWorld;
import noppes.npcs.scripted.NpcAPI;

public class ScriptTileEntity<T extends TileEntity>
implements ITileEntity {
    protected T tileEntity;
    protected IWorld world;

    public ScriptTileEntity(T tileEntity) {
        this.tileEntity = tileEntity;
        this.world = NpcAPI.Instance().getIWorld(tileEntity.func_145831_w());
    }

    @Override
    public int getBlockMetadata() {
        return this.tileEntity.func_145832_p();
    }

    @Override
    public IWorld getWorld() {
        return this.world;
    }

    @Override
    public void setWorld(IWorld world) {
        this.tileEntity.func_145834_a((World)world.getMCWorld());
        this.world = world;
    }

    @Override
    public TileEntity getMCTileEntity() {
        return this.tileEntity;
    }

    @Override
    public void markDirty() {
        this.tileEntity.func_70296_d();
    }

    @Override
    public void readFromNBT(INbt nbt) {
        this.tileEntity.func_145839_a(nbt.getMCNBT());
    }

    @Override
    public double getDistanceFrom(double x, double y, double z) {
        return this.tileEntity.func_145835_a(x, y, z);
    }

    @Override
    public double getDistanceFrom(IPos pos) {
        return this.getDistanceFrom(pos.getXD(), pos.getYD(), pos.getZD());
    }

    @Override
    public IBlock getBlockType() {
        return NpcAPI.Instance().getIBlock(this.world, ((TileEntity)this.tileEntity).field_145851_c, ((TileEntity)this.tileEntity).field_145848_d, ((TileEntity)this.tileEntity).field_145849_e);
    }

    @Override
    public boolean isInvalid() {
        return this.tileEntity.func_145837_r();
    }

    @Override
    public void invalidate() {
        this.tileEntity.func_145843_s();
    }

    @Override
    public void validate() {
        this.tileEntity.func_145829_t();
    }

    @Override
    public void updateContainingBlockInfo() {
        this.tileEntity.func_145836_u();
    }

    @Override
    public INbt getNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        this.tileEntity.func_145841_b(compound);
        return NpcAPI.Instance().getINbt(compound);
    }
}

