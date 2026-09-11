/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 */
package JinRyuu.DragonBC.common.Render;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class ppTileEntity
extends TileEntity {
    private boolean f = false;
    private float gravity = 1.0f;

    public boolean getF() {
        return this.f;
    }

    public void setF() {
        this.f = true;
    }

    public boolean canUpdate() {
        return true;
    }

    private void writeSyncableDataToNBT(NBTTagCompound syncData) {
        syncData.func_74757_a("f", this.f);
    }

    private void readSyncableDataFromNBT(NBTTagCompound par1) {
        this.f = par1.func_74767_n("f");
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.readSyncableDataFromNBT(pkt.func_148857_g());
    }

    public void func_145839_a(NBTTagCompound p_145839_1_) {
        super.func_145839_a(p_145839_1_);
        this.readSyncableDataFromNBT(p_145839_1_);
    }

    public void func_145841_b(NBTTagCompound p_145841_1_) {
        super.func_145841_b(p_145841_1_);
        this.writeSyncableDataToNBT(p_145841_1_);
    }

    public void func_145845_h() {
        boolean flag1 = false;
        if (flag1) {
            this.func_70296_d();
        }
    }
}

