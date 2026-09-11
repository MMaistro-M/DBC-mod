/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 */
package JinRyuu.DragonBC.common.Render;

import JinRyuu.JRMCore.JRMCoreH;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class ArtGravDevTileEntity_bkp
extends TileEntity {
    private float gravity = 1.0f;
    public int gravityDevCB;

    public boolean canUpdate() {
        return true;
    }

    private void writeSyncableDataToNBT(NBTTagCompound syncData) {
        syncData.func_74776_a("gravity", this.gravity);
    }

    private void readSyncableDataFromNBT(NBTTagCompound par1) {
        this.gravity = par1.func_74760_g("gravity");
    }

    public float getGravity() {
        return this.gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (this.gravity > 1.0f) {
            --this.gravityDevCB;
            if (this.gravityDevCB <= 0) {
                int n;
                AxisAlignedBB aabb;
                List list;
                this.gravityDevCB = 20;
                if (!this.field_145850_b.field_72995_K && !(list = this.field_145850_b.func_72872_a(EntityPlayer.class, aabb = AxisAlignedBB.func_72330_a((double)((double)this.field_145851_c - (double)(n = 10)), (double)((double)this.field_145848_d - (double)n), (double)((double)this.field_145849_e - (double)n), (double)((double)this.field_145851_c + (double)n), (double)((double)this.field_145848_d + (double)n), (double)((double)this.field_145849_e + (double)n)))).isEmpty()) {
                    for (int i = 0; i < list.size(); ++i) {
                        EntityPlayer player = (EntityPlayer)list.get(i);
                        int epoch = (int)(System.currentTimeMillis() / 1000L) + 1;
                        JRMCoreH.setInt(epoch, player, "jrmcGravZoneLast");
                        float G = JRMCoreH.getFloat(player, "jrmcGravForce");
                        if (!(this.gravity > G)) continue;
                        JRMCoreH.setFloat(this.gravity, player, "jrmcGravForce");
                    }
                }
            }
        }
    }

    public void func_145841_b(NBTTagCompound par1) {
        super.func_145841_b(par1);
        this.writeSyncableDataToNBT(par1);
    }

    public void func_145839_a(NBTTagCompound par1) {
        super.func_145839_a(par1);
        this.readSyncableDataFromNBT(par1);
    }

    public Packet func_145844_m() {
        NBTTagCompound syncData = new NBTTagCompound();
        this.writeSyncableDataToNBT(syncData);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, syncData);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.readSyncableDataFromNBT(pkt.func_148857_g());
    }
}

