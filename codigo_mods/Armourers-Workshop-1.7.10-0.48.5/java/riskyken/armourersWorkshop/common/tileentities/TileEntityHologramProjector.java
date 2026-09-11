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
 *  net.minecraftforge.common.util.ForgeDirection
 */
package riskyken.armourersWorkshop.common.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import riskyken.armourersWorkshop.common.tileentities.AbstractTileEntityInventory;

public class TileEntityHologramProjector
extends AbstractTileEntityInventory {
    private static final int INVENTORY_SIZE = 1;
    private static final String TAG_OFFSET_X = "offsetX";
    private static final String TAG_OFFSET_Y = "offsetY";
    private static final String TAG_OFFSET_Z = "offsetZ";
    private static final String TAG_ANGLE_X = "angleX";
    private static final String TAG_ANGLE_Y = "angleY";
    private static final String TAG_ANGLE_Z = "angleZ";
    private static final String TAG_ROTATION_OFFSET_X = "rotationOffsetX";
    private static final String TAG_ROTATION_OFFSET_Y = "rotationOffsetY";
    private static final String TAG_ROTATION_OFFSET_Z = "rotationOffsetZ";
    private static final String TAG_ROTATION_SPEED_X = "rotationSpeedX";
    private static final String TAG_ROTATION_SPEED_Y = "rotationSpeedY";
    private static final String TAG_ROTATION_SPEED_Z = "rotationSpeedZ";
    private static final String TAG_GLOWING = "glowing";
    private static final String TAG_POWER_MODE = "powerMode";
    private static final String TAG_POWERED = "powered";
    private int offsetX = 0;
    private int offsetY = 16;
    private int offsetZ = 0;
    private int angleX = 0;
    private int angleY = 0;
    private int angleZ = 0;
    private int rotationOffsetX = 0;
    private int rotationOffsetY = 0;
    private int rotationOffsetZ = 0;
    private int rotationSpeedX = 0;
    private int rotationSpeedY = 0;
    private int rotationSpeedZ = 0;
    private boolean glowing = true;
    private PowerMode powerMode = PowerMode.IGNORED;
    private boolean powered = false;
    private static boolean showRotationPoint;

    public TileEntityHologramProjector() {
        super(1);
    }

    public String func_145825_b() {
        return "hologramProjector";
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

    public void setOffset(int x, int y, int z) {
        this.offsetX = x;
        this.offsetY = y;
        this.offsetZ = z;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void setAngle(int x, int y, int z) {
        this.angleX = x;
        this.angleY = y;
        this.angleZ = z;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void setRotationOffset(int x, int y, int z) {
        this.rotationOffsetX = x;
        this.rotationOffsetY = y;
        this.rotationOffsetZ = z;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void setRotationSpeed(int x, int y, int z) {
        this.rotationSpeedX = x;
        this.rotationSpeedY = y;
        this.rotationSpeedZ = z;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void setShowRotationPoint(boolean showRotationPoint) {
        TileEntityHologramProjector.showRotationPoint = showRotationPoint;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public int getOffsetX() {
        return this.offsetX;
    }

    public int getOffsetY() {
        return this.offsetY;
    }

    public int getOffsetZ() {
        return this.offsetZ;
    }

    public int getAngleX() {
        return this.angleX;
    }

    public int getAngleY() {
        return this.angleY;
    }

    public int getAngleZ() {
        return this.angleZ;
    }

    public int getRotationOffsetX() {
        return this.rotationOffsetX;
    }

    public int getRotationOffsetY() {
        return this.rotationOffsetY;
    }

    public int getRotationOffsetZ() {
        return this.rotationOffsetZ;
    }

    public int getRotationSpeedX() {
        return this.rotationSpeedX;
    }

    public int getRotationSpeedY() {
        return this.rotationSpeedY;
    }

    public int getRotationSpeedZ() {
        return this.rotationSpeedZ;
    }

    public boolean isShowRotationPoint() {
        return showRotationPoint;
    }

    public boolean isGlowing() {
        return this.glowing;
    }

    public void setGlowing(boolean glowing) {
        this.glowing = glowing;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public PowerMode getPowerMode() {
        return this.powerMode;
    }

    public void setPowerMode(PowerMode powerMode) {
        this.powerMode = powerMode;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    private int readIntFromCompound(NBTTagCompound compound, String key, int defaultValue) {
        if (compound.func_150297_b(key, 3)) {
            return compound.func_74762_e(key);
        }
        return defaultValue;
    }

    private boolean readBoolFromCompound(NBTTagCompound compound, String key, Boolean defaultValue) {
        if (compound.func_150297_b(key, 1)) {
            return compound.func_74767_n(key);
        }
        return defaultValue;
    }

    public void updatePoweredState() {
        if (this.field_145850_b != null) {
            this.setPoweredState(this.field_145850_b.func_94572_D(this.field_145851_c, this.field_145848_d, this.field_145849_e) > 0);
        }
    }

    public void setPoweredState(boolean powered) {
        if (this.powered != powered) {
            this.powered = powered;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    public boolean isPowered() {
        return this.powered;
    }

    @Override
    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        compound.func_74768_a(TAG_OFFSET_X, this.offsetX);
        compound.func_74768_a(TAG_OFFSET_Y, this.offsetY);
        compound.func_74768_a(TAG_OFFSET_Z, this.offsetZ);
        compound.func_74768_a(TAG_ANGLE_X, this.angleX);
        compound.func_74768_a(TAG_ANGLE_Y, this.angleY);
        compound.func_74768_a(TAG_ANGLE_Z, this.angleZ);
        compound.func_74768_a(TAG_ROTATION_OFFSET_X, this.rotationOffsetX);
        compound.func_74768_a(TAG_ROTATION_OFFSET_Y, this.rotationOffsetY);
        compound.func_74768_a(TAG_ROTATION_OFFSET_Z, this.rotationOffsetZ);
        compound.func_74768_a(TAG_ROTATION_SPEED_X, this.rotationSpeedX);
        compound.func_74768_a(TAG_ROTATION_SPEED_Y, this.rotationSpeedY);
        compound.func_74768_a(TAG_ROTATION_SPEED_Z, this.rotationSpeedZ);
        compound.func_74757_a(TAG_GLOWING, this.glowing);
        compound.func_74774_a(TAG_POWER_MODE, (byte)this.powerMode.ordinal());
        compound.func_74757_a(TAG_POWERED, this.powered);
    }

    @Override
    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.offsetX = this.readIntFromCompound(compound, TAG_OFFSET_X, 0);
        this.offsetY = this.readIntFromCompound(compound, TAG_OFFSET_Y, 16);
        this.offsetZ = this.readIntFromCompound(compound, TAG_OFFSET_Z, 0);
        this.angleX = this.readIntFromCompound(compound, TAG_ANGLE_X, 0);
        this.angleY = this.readIntFromCompound(compound, TAG_ANGLE_Y, 0);
        this.angleZ = this.readIntFromCompound(compound, TAG_ANGLE_Z, 0);
        this.rotationOffsetX = this.readIntFromCompound(compound, TAG_ROTATION_OFFSET_X, 0);
        this.rotationOffsetY = this.readIntFromCompound(compound, TAG_ROTATION_OFFSET_Y, 0);
        this.rotationOffsetZ = this.readIntFromCompound(compound, TAG_ROTATION_OFFSET_Z, 0);
        this.rotationSpeedX = this.readIntFromCompound(compound, TAG_ROTATION_SPEED_X, 0);
        this.rotationSpeedY = this.readIntFromCompound(compound, TAG_ROTATION_SPEED_Y, 0);
        this.rotationSpeedZ = this.readIntFromCompound(compound, TAG_ROTATION_SPEED_Z, 0);
        this.glowing = this.readBoolFromCompound(compound, TAG_GLOWING, true);
        if (compound.func_150297_b(TAG_POWER_MODE, 1)) {
            byte powerByte = compound.func_74771_c(TAG_POWER_MODE);
            if (powerByte >= 0 & powerByte < PowerMode.values().length) {
                this.powerMode = PowerMode.values()[powerByte];
            }
        } else {
            this.powerMode = PowerMode.IGNORED;
        }
        this.powered = this.readBoolFromCompound(compound, TAG_POWERED, false);
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)-2.0, (double)-2.0, (double)-2.0, (double)3.0, (double)3.0, (double)3.0);
        ForgeDirection dir = ForgeDirection.getOrientation((int)this.func_145832_p());
        bb.func_72317_d((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e);
        float scale = 0.0625f;
        switch (dir) {
            case UP: {
                bb.func_72317_d((double)((float)this.offsetX * scale), (double)((float)this.offsetY * scale), (double)((float)this.offsetZ * scale));
                break;
            }
            case DOWN: {
                bb.func_72317_d((double)((float)(-this.offsetX) * scale), (double)((float)(-this.offsetY) * scale), (double)((float)this.offsetZ * scale));
                break;
            }
            case EAST: {
                bb.func_72317_d((double)((float)this.offsetY * scale), (double)((float)(-this.offsetX) * scale), (double)((float)this.offsetZ * scale));
                break;
            }
            case WEST: {
                bb.func_72317_d((double)((float)(-this.offsetY) * scale), (double)((float)this.offsetX * scale), (double)((float)this.offsetZ * scale));
                break;
            }
            case NORTH: {
                bb.func_72317_d((double)((float)(-this.offsetX) * scale), (double)((float)(-this.offsetZ) * scale), (double)((float)(-this.offsetY) * scale));
                break;
            }
            case SOUTH: {
                bb.func_72317_d((double)((float)(-this.offsetX) * scale), (double)((float)this.offsetZ * scale), (double)((float)this.offsetY * scale));
                break;
            }
        }
        return bb;
    }

    public static enum PowerMode {
        IGNORED,
        HIGH,
        LOW;

    }
}

