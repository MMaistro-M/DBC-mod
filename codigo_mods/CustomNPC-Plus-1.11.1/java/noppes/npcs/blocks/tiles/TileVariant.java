/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 */
package noppes.npcs.blocks.tiles;

import kamkeel.npcs.util.ColorUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import noppes.npcs.blocks.tiles.TileColorable;
import noppes.npcs.items.ItemNpcTool;

public class TileVariant
extends TileEntity {
    public static int variantVersion = 1;
    int version = variantVersion;
    public int variant = 14;
    public int rotation;

    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.version = compound.func_74762_e("CNPCVersion");
        TileVariant.FixTileData(this.version, compound, this);
        this.variant = compound.func_74762_e("CNPCVariant");
        this.rotation = compound.func_74762_e("CNPCRotation");
    }

    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        compound.func_74768_a("CNPCVersion", this.version);
        compound.func_74768_a("CNPCVariant", this.variant);
        compound.func_74768_a("CNPCRotation", this.rotation);
    }

    public boolean canUpdate() {
        return false;
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound compound = pkt.func_148857_g();
        this.func_145839_a(compound);
    }

    public Packet func_145844_m() {
        NBTTagCompound compound = new NBTTagCompound();
        this.func_145841_b(compound);
        compound.func_82580_o("Items");
        S35PacketUpdateTileEntity packet = new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, compound);
        return packet;
    }

    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1));
    }

    public int powerProvided() {
        return 0;
    }

    public static void FixTileData(int version, NBTTagCompound compound, TileEntity tileEntity) {
        if (version == variantVersion) {
            return;
        }
        boolean fixMade = false;
        if (version < 1) {
            if (compound.func_74764_b("BannerColor")) {
                int bannerColor = compound.func_74762_e("BannerColor");
                if (TileVariant.isColorTile(tileEntity)) {
                    compound.func_74768_a(ItemNpcTool.BRUSH_COLOR_TAG, ColorUtil.colorTableInts[bannerColor]);
                }
                compound.func_74768_a("CNPCVariant", bannerColor);
                fixMade = true;
            }
            if (compound.func_74764_b("BannerRotation")) {
                compound.func_74768_a("CNPCRotation", compound.func_74762_e("BannerRotation"));
                fixMade = true;
            }
        }
        if (fixMade) {
            tileEntity.func_70296_d();
        }
    }

    public static boolean isColorTile(TileEntity tileEntity) {
        return tileEntity instanceof TileColorable;
    }
}

