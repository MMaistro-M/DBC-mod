/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.util.AxisAlignedBB
 */
package riskyken.armourersWorkshop.common.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.data.MiniCube;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.tileentities.AbstractTileEntityInventory;

public class TileEntityMiniArmourer
extends AbstractTileEntityInventory {
    private static final String TAG_TYPE = "type";
    private static final int INVENTORY_SIZE = 2;
    @SideOnly(value=Side.CLIENT)
    public int red;
    @SideOnly(value=Side.CLIENT)
    public int green;
    @SideOnly(value=Side.CLIENT)
    public int blue;
    private ISkinType skinType;
    private ArrayList<SkinPart> skinParts = new ArrayList();

    public TileEntityMiniArmourer() {
        super(2);
        this.setSkinType(SkinTypeRegistry.skinHead, false);
    }

    public void cubeUpdateFromServer(ISkinPartType skinPartType, MiniCube cube, boolean remove) {
    }

    public ISkinType getSkinType() {
        return this.skinType;
    }

    public ArrayList<SkinPart> getSkinParts() {
        return this.skinParts;
    }

    public void setSkinParts(ArrayList<SkinPart> skinParts) {
        this.skinParts = skinParts;
    }

    public void setSkinType(ISkinType skinType) {
        if (skinType != this.skinType) {
            this.setSkinType(skinType, true);
        }
    }

    public void setSkinType(ISkinType skinType, boolean update) {
        this.skinType = skinType;
        this.skinParts.clear();
        if (this.skinType != null) {
            // empty if block
        }
        if (update) {
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        this.func_145839_a(packet.func_148857_g());
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public Packet func_145844_m() {
        NBTTagCompound compound = new NBTTagCompound();
        this.func_145841_b(compound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 5, compound);
    }

    @Override
    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.skinType = SkinTypeRegistry.INSTANCE.getSkinTypeFromRegistryName(compound.func_74779_i(TAG_TYPE));
        if (this.skinType != null) {
            this.setSkinType(this.skinType, false);
        }
    }

    @Override
    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        if (this.skinType != null) {
            compound.func_74778_a(TAG_TYPE, this.skinType.getRegistryName());
        }
    }

    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 2), (double)(this.field_145849_e + 1));
    }

    public String func_145825_b() {
        return "miniArmourer";
    }
}

