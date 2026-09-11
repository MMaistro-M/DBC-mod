/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 */
package riskyken.armourersWorkshop.common.tileentities;

import java.awt.Point;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartTypeTextured;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.skin.SkinTextureHelper;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;

public class TileEntityBoundingBox
extends TileEntity {
    private static final String TAG_PARENT_X = "parentX";
    private static final String TAG_PARENT_Y = "parentY";
    private static final String TAG_PARENT_Z = "parentZ";
    private static final String TAG_GUIDE_X = "guideX";
    private static final String TAG_GUIDE_Y = "guideY";
    private static final String TAG_GUIDE_Z = "guideZ";
    private static final String TAG_SKIN_PART = "skinPart";
    private int parentX;
    private int parentY;
    private int parentZ;
    private byte guideX;
    private byte guideY;
    private byte guideZ;
    private ISkinPartType skinPart;

    public TileEntityBoundingBox() {
        this.setParent(0, 0, 0, (byte)0, (byte)0, (byte)0, null);
    }

    public TileEntityBoundingBox(int parentX, int parentY, int parentZ, byte guideX, byte guideY, byte guideZ, ISkinPartType skinPart) {
        this.setParent(parentX, parentY, parentZ, guideX, guideY, guideZ, skinPart);
    }

    public boolean canUpdate() {
        return false;
    }

    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.parentX = compound.func_74762_e(TAG_PARENT_X);
        this.parentY = compound.func_74762_e(TAG_PARENT_Y);
        this.parentZ = compound.func_74762_e(TAG_PARENT_Z);
        this.guideX = compound.func_74771_c(TAG_GUIDE_X);
        this.guideY = compound.func_74771_c(TAG_GUIDE_Y);
        this.guideZ = compound.func_74771_c(TAG_GUIDE_Z);
        this.skinPart = SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName(compound.func_74779_i(TAG_SKIN_PART));
    }

    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        compound.func_74768_a(TAG_PARENT_X, this.parentX);
        compound.func_74768_a(TAG_PARENT_Y, this.parentY);
        compound.func_74768_a(TAG_PARENT_Z, this.parentZ);
        compound.func_74774_a(TAG_GUIDE_X, this.guideX);
        compound.func_74774_a(TAG_GUIDE_Y, this.guideY);
        compound.func_74774_a(TAG_GUIDE_Z, this.guideZ);
        if (this.skinPart != null) {
            compound.func_74778_a(TAG_SKIN_PART, this.skinPart.getRegistryName());
        }
    }

    public Packet func_145844_m() {
        NBTTagCompound compound = new NBTTagCompound();
        this.func_145841_b(compound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 5, compound);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        this.func_145839_a(packet.func_148857_g());
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public TileEntityArmourer getParent() {
        TileEntity te = this.field_145850_b.func_147438_o(this.parentX, this.parentY, this.parentZ);
        if (te != null && te instanceof TileEntityArmourer) {
            return (TileEntityArmourer)te;
        }
        return null;
    }

    public boolean isParentValid() {
        TileEntity te = this.field_145850_b.func_147438_o(this.parentX, this.parentY, this.parentZ);
        return te != null && te instanceof TileEntityArmourer;
    }

    public ISkinPartType getSkinPart() {
        return this.skinPart;
    }

    public void setParent(int x, int y, int z, byte guideX, byte guideY, byte guideZ, ISkinPartType skinPart) {
        this.parentX = x;
        this.parentY = y;
        this.parentZ = z;
        this.guideX = guideX;
        this.guideY = guideY;
        this.guideZ = guideZ;
        this.skinPart = skinPart;
        this.func_70296_d();
    }

    public byte getGuideX() {
        return this.guideX;
    }

    public byte getGuideY() {
        return this.guideY;
    }

    public byte getGuideZ() {
        return this.guideZ;
    }

    public boolean isPaintableSide(int side) {
        ForgeDirection sideBlock = ForgeDirection.getOrientation((int)side);
        return this.field_145850_b.func_147439_a(this.field_145851_c + sideBlock.offsetX, this.field_145848_d + sideBlock.offsetY, this.field_145849_e + sideBlock.offsetZ) != this.func_145838_q();
    }

    public PaintType getPaintType(int side) {
        if (this.isParentValid() && this.skinPart instanceof ISkinPartTypeTextured) {
            Point texPoint = SkinTextureHelper.getTextureLocationFromWorldBlock(this, side);
            int colour = this.getParent().getPaintData(texPoint.x, texPoint.y);
            return PaintType.getPaintTypeFromColour(colour);
        }
        return PaintType.DYE_1;
    }
}

