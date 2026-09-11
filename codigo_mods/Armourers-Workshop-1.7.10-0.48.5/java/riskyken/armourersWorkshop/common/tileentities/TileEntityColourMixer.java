/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 */
package riskyken.armourersWorkshop.common.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import riskyken.armourersWorkshop.api.common.painting.IPaintingTool;
import riskyken.armourersWorkshop.api.common.painting.IPantable;
import riskyken.armourersWorkshop.api.common.skin.cubes.ICubeColour;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.items.paintingtool.ItemColourPicker;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.skin.cubes.CubeColour;
import riskyken.armourersWorkshop.common.tileentities.AbstractTileEntityInventory;
import riskyken.armourersWorkshop.utils.UtilColour;

public class TileEntityColourMixer
extends AbstractTileEntityInventory
implements IPantable {
    private static final String TAG_ITEM_UPDATE = "itemUpdate";
    private static final String TAG_COLOUR_FAMILY = "colourFamily";
    private static final String TAG_PAINT_TYPE = "paintType";
    private static final int INVENTORY_SIZE = 2;
    public int colour = 0xFFFFFF;
    private PaintType paintType = PaintType.NORMAL;
    private UtilColour.ColourFamily colourFamily = UtilColour.ColourFamily.MINECRAFT_WOOL;
    private boolean itemUpdate;
    private boolean colourUpdate = false;

    public TileEntityColourMixer() {
        super(2);
    }

    public boolean isSpecial() {
        int meta = this.func_145832_p();
        return meta == 1;
    }

    public boolean canUpdate() {
        return false;
    }

    @Override
    public void func_70299_a(int i, ItemStack itemstack) {
        super.func_70299_a(i, itemstack);
        this.checkForPaintBrush();
    }

    private void checkForPaintBrush() {
        ItemStack stackInput = this.func_70301_a(0);
        ItemStack stackOutput = this.func_70301_a(1);
        if (stackInput != null && stackInput.func_77973_b() instanceof IPaintingTool) {
            if (stackOutput != null) {
                return;
            }
            this.func_70299_a(0, null);
            this.func_70299_a(1, stackInput);
            if (stackInput.func_77973_b() instanceof IPaintingTool && stackInput.func_77973_b() != ModItems.colourPicker) {
                IPaintingTool paintingTool = (IPaintingTool)stackInput.func_77973_b();
                paintingTool.setToolColour(stackInput, this.colour);
                paintingTool.setToolPaintType(stackInput, this.getPaintType(0));
            }
            if (stackInput.func_77973_b() == ModItems.colourPicker) {
                this.setPaintType(((ItemColourPicker)stackInput.func_77973_b()).getToolPaintType(stackInput), 0);
                this.setColour(((ItemColourPicker)stackInput.func_77973_b()).getToolColour(stackInput), true);
            }
            this.func_70296_d();
        }
    }

    public void setColourFamily(UtilColour.ColourFamily colourFamily) {
        this.colourFamily = colourFamily;
        this.func_70296_d();
    }

    public UtilColour.ColourFamily getColourFamily() {
        return this.colourFamily;
    }

    public String func_145825_b() {
        return "colourMixer";
    }

    public void receiveColourUpdateMessage(int colour, boolean item, PaintType paintType) {
        this.setColour(colour, item);
        this.setPaintType(paintType, 0);
    }

    public void setColour(int colour, boolean item) {
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        if (item) {
            this.itemUpdate = true;
        }
        this.colour = colour;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    @Override
    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.colour = compound.func_74762_e("colour");
        this.colourFamily = UtilColour.ColourFamily.values()[compound.func_74762_e(TAG_COLOUR_FAMILY)];
        this.paintType = compound.func_74764_b(TAG_PAINT_TYPE) ? PaintType.getPaintTypeFromUKey(compound.func_74762_e(TAG_PAINT_TYPE)) : PaintType.NORMAL;
    }

    @Override
    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        compound.func_74768_a("colour", this.colour);
        compound.func_74768_a(TAG_COLOUR_FAMILY, this.colourFamily.ordinal());
        compound.func_74768_a(TAG_PAINT_TYPE, this.paintType.getKey());
    }

    public Packet func_145844_m() {
        NBTTagCompound compound = new NBTTagCompound();
        this.writeBaseToNBT(compound);
        compound.func_74768_a("colour", this.colour);
        compound.func_74768_a(TAG_PAINT_TYPE, this.paintType.getKey());
        compound.func_74757_a(TAG_ITEM_UPDATE, this.itemUpdate);
        if (this.itemUpdate) {
            this.itemUpdate = false;
        }
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 3, compound);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        NBTTagCompound compound = packet.func_148857_g();
        this.readBaseFromNBT(compound);
        this.colour = compound.func_74762_e("colour");
        this.paintType = PaintType.getPaintTypeFromUKey(compound.func_74762_e(TAG_PAINT_TYPE));
        this.itemUpdate = compound.func_74767_n(TAG_ITEM_UPDATE);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        this.colourUpdate = true;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean getHasItemUpdateAndReset() {
        if (this.itemUpdate) {
            this.itemUpdate = false;
            return true;
        }
        return false;
    }

    @Override
    public int getColour(int side) {
        return this.colour;
    }

    @Override
    public ICubeColour getColour() {
        return new CubeColour(this.colour);
    }

    @Override
    @Deprecated
    public void setColour(int colour) {
        this.setColour(colour, false);
    }

    @Override
    public void setColour(byte[] rgb, int side) {
        this.setColour(new Color(rgb[0] & 0xFF, rgb[1] & 0xFF, rgb[2] & 0xFF).getRGB(), false);
    }

    @Override
    @Deprecated
    public void setColour(int colour, int side) {
        this.setColour(colour, false);
    }

    @Override
    public void setColour(ICubeColour colour) {
    }

    @Override
    public void setPaintType(PaintType paintType, int side) {
        this.paintType = paintType;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    @Override
    public PaintType getPaintType(int side) {
        return this.paintType;
    }
}

