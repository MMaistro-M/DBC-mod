/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 */
package riskyken.armourersWorkshop.common.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import riskyken.armourersWorkshop.common.tileentities.AbstractTileEntityInventory;

public class TileEntityOutfitMaker
extends AbstractTileEntityInventory {
    private static final String TAG_OUTFIT_NAME = "outfitName";
    private static final String TAG_OUTFIT_FLAVOUR = "outfitFlavour";
    public static final int OUTFIT_SKINS = 5;
    public static final int OUTFIT_ROWS = 4;
    private static final int INVENTORY_SIZE = 22;
    private String outfitName = "";
    private String outfitFlavour = "";

    public TileEntityOutfitMaker() {
        super(22);
    }

    public String getOutfitName() {
        return this.outfitName;
    }

    public void setOutfitName(String outfitName) {
        this.outfitName = outfitName;
        this.dirtySync();
    }

    public String getOutfitFlavour() {
        return this.outfitFlavour;
    }

    public void setOutfitFlavour(String outfitFlavour) {
        this.outfitFlavour = outfitFlavour;
        this.dirtySync();
    }

    public String func_145825_b() {
        return "outfit_maker";
    }

    public boolean canUpdate() {
        return false;
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.func_145839_a(pkt.func_148857_g());
    }

    public Packet func_145844_m() {
        NBTTagCompound compound = new NBTTagCompound();
        this.func_145841_b(compound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 5, compound);
    }

    @Override
    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        compound.func_74778_a(TAG_OUTFIT_NAME, this.outfitName);
        compound.func_74778_a(TAG_OUTFIT_FLAVOUR, this.outfitFlavour);
    }

    @Override
    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        if (compound.func_150297_b(TAG_OUTFIT_NAME, 8)) {
            this.outfitName = compound.func_74779_i(TAG_OUTFIT_NAME);
        }
        if (compound.func_150297_b(TAG_OUTFIT_FLAVOUR, 8)) {
            this.outfitFlavour = compound.func_74779_i(TAG_OUTFIT_FLAVOUR);
        }
    }
}

