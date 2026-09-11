/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StringUtils
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringUtils;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.api.common.skin.Rectangle3D;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.blocks.BlockLocation;
import riskyken.armourersWorkshop.common.blocks.BlockSkinnable;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;
import riskyken.armourersWorkshop.common.inventory.ModInventory;
import riskyken.armourersWorkshop.common.skin.cache.CommonSkinCache;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.utils.ModLogger;

public class TileEntitySkinnable
extends TileEntity {
    private static final String TAG_HAS_SKIN = "hasSkin";
    private static final String TAG_RELATED_BLOCKS = "relatedBlocks";
    private static final String TAG_X = "x";
    private static final String TAG_Y = "y";
    private static final String TAG_Z = "z";
    private static final String TAG_BLOCK_INVENTORY = "blockInventory";
    private static final String TAG_BLOCK_INVENTORY_SIZE = "blockInventorySize";
    private static final String TAG_LINKED_BLOCK = "linkedBlock";
    private static final int NBT_VERSION = 1;
    private int nbtVersion;
    private SkinPointer skinPointer;
    private boolean haveBlockBounds = false;
    private ArrayList<BlockLocation> relatedBlocks;
    private boolean bedOccupied;
    private ModInventory inventory;
    private boolean blockInventory;
    private BlockLocation linkedBlock = null;
    @SideOnly(value=Side.CLIENT)
    private AxisAlignedBB renderBounds;
    public float minX;
    public float minY;
    public float minZ;
    public float maxX;
    public float maxY;
    public float maxZ;

    public boolean hasSkin() {
        return this.skinPointer != null;
    }

    public SkinPointer getSkinPointer() {
        return this.skinPointer;
    }

    public void setSkinPointer(Skin skin, SkinPointer skinPointer) {
        this.skinPointer = skinPointer;
        if (skin != null & this.isParent()) {
            SkinProperties skinProps = skin.getProperties();
            if (SkinProperties.PROP_BLOCK_INVENTORY.getValue(skin.getProperties()).booleanValue()) {
                this.blockInventory = true;
                int size = SkinProperties.PROP_BLOCK_INVENTORY_WIDTH.getValue(skin.getProperties()) * SkinProperties.PROP_BLOCK_INVENTORY_HEIGHT.getValue(skin.getProperties());
                this.inventory = new ModInventory("skinnable", size, this);
            }
        }
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public BlockLocation getLinkedBlock() {
        return this.linkedBlock;
    }

    public boolean hasLinkedBlock() {
        return this.linkedBlock != null;
    }

    public void setLinkedBlock(BlockLocation linkedBlock) {
        this.linkedBlock = linkedBlock;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void func_145836_u() {
        super.func_145836_u();
        this.haveBlockBounds = false;
    }

    public void setBoundsOnBlock(Block block, int xOffset, int yOffset, int zOffset) {
        if (this.haveBlockBounds) {
            // empty if block
        }
        if (block != null && !(block instanceof BlockSkinnable)) {
            ModLogger.log(Level.ERROR, String.format("Tile entity at X:%d Y:%d Z:%d has an invalid block.", xOffset, yOffset, zOffset));
            if (this.field_145850_b != null) {
                this.field_145850_b.func_147475_p(xOffset, yOffset, zOffset);
            }
            return;
        }
        if (this.hasSkin()) {
            BlockSkinnable blockSkinnable = (BlockSkinnable)block;
            Skin skin = null;
            skin = this.getSkin(this.skinPointer);
            if (skin != null) {
                ForgeDirection dir = blockSkinnable.getFacingDirection(this.func_145832_p());
                float[] bounds = TileEntitySkinnable.getBlockBounds(skin, xOffset, yOffset, zOffset, dir);
                if (bounds != null) {
                    this.minX = bounds[0];
                    this.minY = bounds[1];
                    this.minZ = bounds[2];
                    this.maxX = bounds[3];
                    this.maxY = bounds[4];
                    this.maxZ = bounds[5];
                    this.haveBlockBounds = true;
                    block.func_149676_a(this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ);
                }
                return;
            }
        }
        if (this.haveBlockBounds) {
            block.func_149676_a(this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ);
        } else {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    public static float[] getBlockBounds(Skin skin, int gridX, int gridY, int gridZ, ForgeDirection dir) {
        float[] bounds = new float[6];
        float scale = 0.0625f;
        SkinPart skinPart = skin.getParts().get(0);
        gridX = MathHelper.func_76125_a((int)gridX, (int)0, (int)2);
        gridY = MathHelper.func_76125_a((int)gridY, (int)0, (int)2);
        gridZ = MathHelper.func_76125_a((int)gridZ, (int)0, (int)2);
        Rectangle3D rec = skinPart.getBlockBounds(gridX, gridY, gridZ);
        switch (dir) {
            case NORTH: {
                break;
            }
            case EAST: {
                rec = skinPart.getBlockBounds(2 - gridZ, gridY, gridX);
                break;
            }
            case SOUTH: {
                rec = skinPart.getBlockBounds(2 - gridX, gridY, 2 - gridZ);
                break;
            }
            case WEST: {
                rec = skinPart.getBlockBounds(gridZ, gridY, 2 - gridX);
                break;
            }
        }
        if (rec == null) {
            return null;
        }
        int x = 8 + rec.getX();
        int y = 8 - rec.getHeight() - rec.getY();
        int z = 8 - rec.getDepth() - rec.getZ();
        bounds[0] = (float)x * scale;
        bounds[1] = (float)y * scale;
        bounds[2] = (float)z * scale;
        bounds[3] = (float)(x + rec.getWidth()) * scale;
        bounds[4] = (float)(y + rec.getHeight()) * scale;
        bounds[5] = (float)(z + rec.getDepth()) * scale;
        bounds = TileEntitySkinnable.rotateBlockBounds(bounds, dir);
        return bounds;
    }

    private static float[] rotateBlockBounds(float[] bounds, ForgeDirection dir) {
        float[] rotatedBounds = new float[6];
        for (int i = 0; i < bounds.length; ++i) {
            rotatedBounds[i] = bounds[i];
        }
        switch (dir) {
            case NORTH: {
                rotatedBounds[0] = 1.0f - bounds[3];
                rotatedBounds[2] = 1.0f - bounds[5];
                rotatedBounds[3] = 1.0f - bounds[0];
                rotatedBounds[5] = 1.0f - bounds[2];
                break;
            }
            case EAST: {
                rotatedBounds[0] = bounds[2];
                rotatedBounds[2] = 1.0f - bounds[3];
                rotatedBounds[3] = bounds[5];
                rotatedBounds[5] = 1.0f - bounds[0];
                break;
            }
            case WEST: {
                rotatedBounds[0] = 1.0f - bounds[5];
                rotatedBounds[2] = bounds[0];
                rotatedBounds[3] = 1.0f - bounds[2];
                rotatedBounds[5] = bounds[3];
                break;
            }
        }
        return rotatedBounds;
    }

    public boolean isBedOccupied() {
        return this.bedOccupied;
    }

    public void setBedOccupied(boolean bedOccupied) {
        this.bedOccupied = bedOccupied;
    }

    public Skin getSkin(ISkinPointer skinPointer) {
        if (this.func_145831_w().field_72995_K) {
            return this.getSkinClient(skinPointer);
        }
        return this.getSkinServer(skinPointer);
    }

    @SideOnly(value=Side.CLIENT)
    private Skin getSkinClient(ISkinPointer skinPointer) {
        return ClientSkinCache.INSTANCE.getSkin(skinPointer);
    }

    private Skin getSkinServer(ISkinPointer skinPointer) {
        return CommonSkinCache.INSTANCE.getSkin(skinPointer);
    }

    public void setRelatedBlocks(ArrayList<BlockLocation> relatedBlocks) {
        this.relatedBlocks = relatedBlocks;
    }

    public ArrayList<BlockLocation> getRelatedBlocks() {
        return this.relatedBlocks;
    }

    public TileEntitySkinnable getParent() {
        return this;
    }

    public boolean isParent() {
        return ((Object)((Object)this)).getClass() == TileEntitySkinnable.class;
    }

    public ModInventory getInventory() {
        return this.inventory;
    }

    public Packet func_145844_m() {
        NBTTagCompound compound = new NBTTagCompound();
        this.func_145841_b(compound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, compound);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        NBTTagCompound compound = packet.func_148857_g();
        this.func_145839_a(compound);
    }

    public boolean canUpdate() {
        return false;
    }

    public void setRotation(ForgeDirection rotation) {
        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, rotation.ordinal(), 2);
    }

    public ForgeDirection getRotation() {
        if (this.func_145838_q() instanceof BlockSkinnable) {
            return ((BlockSkinnable)this.func_145838_q()).getFacingDirection((IBlockAccess)this.func_145831_w(), this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        return ForgeDirection.UNKNOWN;
    }

    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        compound.func_74757_a(TAG_HAS_SKIN, this.hasSkin());
        compound.func_74768_a("nbtVersion", 1);
        if (this.hasLinkedBlock()) {
            compound.func_74783_a(TAG_LINKED_BLOCK, new int[]{this.linkedBlock.x, this.linkedBlock.y, this.linkedBlock.z});
        }
        if (this.hasSkin()) {
            this.skinPointer.writeToCompound(compound);
            if (this.relatedBlocks != null) {
                NBTTagList list = new NBTTagList();
                for (int i = 0; i < this.relatedBlocks.size(); ++i) {
                    NBTTagCompound blockCompound = new NBTTagCompound();
                    BlockLocation blockLoc = this.relatedBlocks.get(i);
                    blockCompound.func_74768_a(TAG_X, blockLoc.x);
                    blockCompound.func_74768_a(TAG_Y, blockLoc.y);
                    blockCompound.func_74768_a(TAG_Z, blockLoc.z);
                    list.func_74742_a((NBTBase)blockCompound);
                }
                compound.func_74782_a(TAG_RELATED_BLOCKS, (NBTBase)list);
            }
            if (this.isParent() & this.blockInventory & this.inventory != null) {
                compound.func_74757_a(TAG_BLOCK_INVENTORY, true);
                compound.func_74768_a(TAG_BLOCK_INVENTORY_SIZE, this.inventory.func_70302_i_());
                this.inventory.saveItemsToNBT(compound);
            } else {
                compound.func_74757_a(TAG_BLOCK_INVENTORY, false);
            }
        }
    }

    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        boolean hasSkin = compound.func_74767_n(TAG_HAS_SKIN);
        this.nbtVersion = 0;
        if (compound.func_150297_b("nbtVersion", 3)) {
            this.nbtVersion = compound.func_74762_e("nbtVersion");
        }
        if (compound.func_150297_b(TAG_LINKED_BLOCK, 11)) {
            int[] loc = compound.func_74759_k(TAG_LINKED_BLOCK);
            this.linkedBlock = new BlockLocation(loc[0], loc[1], loc[2]);
        } else {
            this.linkedBlock = null;
        }
        if (hasSkin) {
            this.skinPointer = new SkinPointer();
            this.skinPointer.readFromCompound(compound);
            if (compound.func_150297_b(TAG_RELATED_BLOCKS, 9)) {
                NBTTagList list = compound.func_150295_c(TAG_RELATED_BLOCKS, 10);
                this.relatedBlocks = new ArrayList();
                for (int i = 0; i < list.func_74745_c(); ++i) {
                    NBTTagCompound blockCompound = list.func_150305_b(i);
                    int x = blockCompound.func_74762_e(TAG_X);
                    int y = blockCompound.func_74762_e(TAG_Y);
                    int z = blockCompound.func_74762_e(TAG_Z);
                    BlockLocation blockLoc = new BlockLocation(x, y, z);
                    this.relatedBlocks.add(blockLoc);
                }
            } else {
                this.relatedBlocks = null;
            }
            this.blockInventory = false;
            this.inventory = null;
            if (this.isParent() && compound.func_150297_b(TAG_BLOCK_INVENTORY, 1) && compound.func_74767_n(TAG_BLOCK_INVENTORY)) {
                int size = 36;
                if (compound.func_150297_b(TAG_BLOCK_INVENTORY_SIZE, 3)) {
                    size = compound.func_74762_e(TAG_BLOCK_INVENTORY_SIZE);
                }
                this.blockInventory = true;
                this.inventory = new ModInventory("skinnable", size, this);
                this.inventory.loadItemsFromNBT(compound);
            }
        } else {
            this.skinPointer = null;
            this.relatedBlocks = null;
            ModLogger.log(Level.WARN, String.format("Skinnable tile at X:%d Y:%d Z:%d has no skin data.", this.field_145851_c, this.field_145848_d, this.field_145849_e));
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        if (this.renderBounds != null) return this.renderBounds;
        if (!this.hasSkin()) return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1));
        Skin skin = this.getSkin(this.getSkinPointer());
        if (skin == null) return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1));
        if (SkinProperties.PROP_BLOCK_MULTIBLOCK.getValue(skin.getProperties()).booleanValue()) {
            this.renderBounds = AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 1), (double)this.field_145848_d, (double)(this.field_145849_e - 1), (double)(this.field_145851_c + 2), (double)(this.field_145848_d + 3), (double)(this.field_145849_e + 2));
            ForgeDirection dir = this.getRotation().getOpposite();
            this.renderBounds.func_72317_d((double)dir.offsetX, 0.0, (double)dir.offsetZ);
            return this.renderBounds;
        } else {
            this.renderBounds = AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1));
        }
        return this.renderBounds;
    }

    @SideOnly(value=Side.CLIENT)
    public double func_145833_n() {
        return ConfigHandlerClient.blockSkinMaxRenderDistance;
    }

    public void killChildren(World world) {
        if (this.relatedBlocks != null) {
            for (int i = 0; i < this.relatedBlocks.size(); ++i) {
                BlockLocation loc = this.relatedBlocks.get(i);
                if (!(this.field_145851_c == loc.x & this.field_145848_d == loc.y & this.field_145849_e == loc.z)) {
                    ModLogger.log("Removing child: " + loc.toString());
                    world.func_147468_f(loc.x, loc.y, loc.z);
                    world.func_147475_p(loc.x, loc.y, loc.z);
                    continue;
                }
                ModLogger.log("Skipping child: " + loc.toString());
            }
        }
    }

    public boolean hasCustomName() {
        Skin skin;
        if (this.hasSkin() && (skin = this.getSkin(this.getSkinPointer())) != null) {
            return !StringUtils.func_151246_b((String)skin.getCustomName());
        }
        return false;
    }

    public String getCustomName() {
        Skin skin;
        if (this.hasSkin() && (skin = this.getSkin(this.getSkinPointer())) != null) {
            return skin.getCustomName();
        }
        return "";
    }
}

