/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.internal.FMLNetworkHandler
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayer$EnumStatus
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.blocks;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.api.common.skin.Point3D;
import riskyken.armourersWorkshop.client.lib.LibBlockResources;
import riskyken.armourersWorkshop.common.blocks.AbstractModBlockContainer;
import riskyken.armourersWorkshop.common.blocks.BlockLocation;
import riskyken.armourersWorkshop.common.items.ItemDebugTool;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.items.block.ModItemBlock;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinnable;
import riskyken.armourersWorkshop.utils.BlockUtils;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;
import riskyken.armourersWorkshop.utils.SkinUtils;
import riskyken.armourersWorkshop.utils.UtilItems;

public class BlockSkinnable
extends AbstractModBlockContainer
implements ItemDebugTool.IDebug {
    @SideOnly(value=Side.CLIENT)
    private IIcon cubeIcon;

    public BlockSkinnable() {
        this("skinnable");
    }

    public BlockSkinnable(String name) {
        super(name, Material.field_151573_f, field_149777_j, false);
    }

    public Block func_149663_c(String name) {
        GameRegistry.registerBlock((Block)this, ModItemBlock.class, (String)("block." + name));
        return super.func_149663_c(name);
    }

    public boolean func_149659_a(Explosion explosion) {
        return false;
    }

    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
        if (!world.field_72995_K) {
            TileEntitySkinnable te = this.getTileEntity((IBlockAccess)world, x, y, z);
            if (te != null && te.getInventory() != null) {
                BlockUtils.dropInventoryBlocks(world, te.getInventory(), x, y, z);
            }
            this.dropSkin(world, x, y, z, false);
        }
        world.func_147475_p(x, y, z);
        super.onBlockExploded(world, x, y, z, explosion);
    }

    public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
        TileEntitySkinnable te = this.getTileEntity((IBlockAccess)world, x, y, z);
        if (te != null && te.getInventory() != null) {
            BlockUtils.dropInventoryBlocks(world, te.getInventory(), x, y, z);
        }
        super.func_149749_a(world, x, y, z, block, meta);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        Skin skin = this.getSkin((IBlockAccess)world, x, y, z);
        TileEntitySkinnable te = this.getTileEntity((IBlockAccess)world, x, y, z);
        if (te == null) {
            return false;
        }
        TileEntitySkinnable parentTe = te.getParent();
        if (parentTe == null) {
            return false;
        }
        if (parentTe.hasLinkedBlock()) {
            BlockLocation loc = parentTe.getLinkedBlock();
            Block block = world.func_147439_a(loc.x, loc.y, loc.z);
            if (!(block instanceof BlockSkinnable)) {
                return block.func_149727_a(world, loc.x, loc.y, loc.z, player, side, xHit, yHit, zHit);
            }
        }
        if (skin == null) {
            return false;
        }
        if (SkinProperties.PROP_BLOCK_SEAT.getValue(skin.getProperties()).booleanValue()) {
            return this.sitOnSeat(world, parentTe.field_145851_c, parentTe.field_145848_d, parentTe.field_145849_e, player, skin);
        }
        if (SkinProperties.PROP_BLOCK_BED.getValue(skin.getProperties()).booleanValue()) {
            // empty if block
        }
        if (SkinProperties.PROP_BLOCK_INVENTORY.getValue(skin.getProperties()) | SkinProperties.PROP_BLOCK_ENDER_INVENTORY.getValue(skin.getProperties())) {
            if (!world.field_72995_K) {
                FMLNetworkHandler.openGui((EntityPlayer)player, (Object)ArmourersWorkshop.instance, (int)15, (World)world, (int)parentTe.field_145851_c, (int)parentTe.field_145848_d, (int)parentTe.field_145849_e);
            }
            return true;
        }
        return false;
    }

    private boolean sitOnSeat(World world, int x, int y, int z, EntityPlayer player, Skin skin) {
        List seats = world.func_72872_a(Seat.class, AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)(x + 1), (double)(y + 1), (double)(z + 1)));
        if (seats.size() == 0) {
            Point3D point = null;
            point = skin.getParts().get(0).getMarkerCount() > 0 ? skin.getParts().get(0).getMarker(0) : new Point3D(0, 0, 0);
            int rotation = world.func_72805_g(x, y, z);
            skin.getParts().get(0).getMarker(0);
            Seat seat = new Seat(world, x, y, z, point, rotation);
            world.func_72838_d((Entity)seat);
            player.func_70078_a((Entity)seat);
            return true;
        }
        return false;
    }

    private boolean sleepInBed(World world, int x, int y, int z, EntityPlayer player, Skin skin, ForgeDirection direction, TileEntitySkinnable tileEntity) {
        if (world.field_72995_K) {
            return true;
        }
        Point3D point = null;
        point = skin.getParts().get(0).getMarkerCount() > 0 ? skin.getParts().get(0).getMarker(0) : new Point3D(0, 0, 16);
        int xBlockOffset = MathHelper.func_76128_c((double)(((double)point.getX() + 8.0) / 16.0));
        int zBlockOffset = MathHelper.func_76128_c((double)(((double)point.getZ() + 8.0) / 16.0));
        int xOffset = point.getX() + 8 - x * 16;
        int zOffset = point.getY() + 8 - y * 16;
        float scale = 0.0625f;
        EntityPlayer.EnumStatus enumstatus = player.func_71018_a(x -= xBlockOffset * direction.offsetZ + zBlockOffset * direction.offsetX, y, z -= zBlockOffset * direction.offsetZ + xBlockOffset * direction.offsetX);
        if (enumstatus == EntityPlayer.EnumStatus.OK) {
            tileEntity.setBedOccupied(true);
            ModLogger.log("sleeping!");
            player.field_71079_bU = 0.0f;
            player.field_71089_bV = 0.0f;
            player.func_70107_b((double)(x + 10), (double)((float)y - 0.5f), (double)(z + 1));
            player.field_71081_bT = new ChunkCoordinates(x, y, z);
            world.func_72854_c();
            return true;
        }
        if (enumstatus == EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW) {
            player.func_146105_b((IChatComponent)new ChatComponentTranslation("tile.bed.noSleep", new Object[0]));
        } else if (enumstatus == EntityPlayer.EnumStatus.NOT_SAFE) {
            player.func_146105_b((IChatComponent)new ChatComponentTranslation("tile.bed.notSafe", new Object[0]));
        }
        return true;
    }

    public boolean isBed(IBlockAccess world, int x, int y, int z, EntityLivingBase player) {
        Skin skin = this.getSkin(world, x, y, z);
        if (skin != null) {
            return SkinProperties.PROP_BLOCK_BED.getValue(skin.getProperties());
        }
        return false;
    }

    public void setBedOccupied(IBlockAccess world, int x, int y, int z, EntityPlayer player, boolean occupied) {
        TileEntitySkinnable te = this.getTileEntity(world, x, y, z);
        if (te != null) {
            te.setBedOccupied(occupied);
        }
    }

    public int getBedDirection(IBlockAccess world, int x, int y, int z) {
        TileEntitySkinnable te = this.getTileEntity(world, x, y, z);
        if (te != null) {
            switch (te.getRotation()) {
                case NORTH: {
                    return 0;
                }
                case EAST: {
                    return 1;
                }
                case SOUTH: {
                    return 2;
                }
                case WEST: {
                    return 3;
                }
            }
        }
        return 0;
    }

    public boolean isBedFoot(IBlockAccess world, int x, int y, int z) {
        Skin skin = this.getSkin(world, x, y, z);
        if (skin != null) {
            for (int i = 0; i < skin.getPartCount(); ++i) {
                SkinPart part = skin.getParts().get(i);
                part.getMarkerCount();
            }
        }
        return false;
    }

    public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity) {
        if (entity instanceof Seat) {
            return;
        }
        if (entity != null && entity instanceof EntityPlayer && ((EntityPlayer)entity).func_70608_bn()) {
            Skin skin = this.getSkin((IBlockAccess)world, x, y, z);
            if (skin != null) {
                Point3D point = null;
                point = skin.getParts().get(0).getMarkerCount() > 0 ? skin.getParts().get(0).getMarker(0) : new Point3D(0, 0, 16);
                float f = 0.0625f;
            }
            list.add(AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)((float)x + 1.0f), (double)((float)y + 0.5f), (double)((float)z + 1.0f)));
            return;
        }
        super.func_149743_a(world, x, y, z, mask, list, entity);
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        Skin skin = this.getSkin((IBlockAccess)world, x, y, z);
        if (skin != null && SkinProperties.PROP_BLOCK_NO_COLLISION.getValue(skin.getProperties()).booleanValue()) {
            return null;
        }
        this.func_149719_a((IBlockAccess)world, x, y, z);
        return super.func_149668_a(world, x, y, z);
    }

    public boolean func_149678_a(int meta, boolean holdingBoat) {
        if (!ArmourersWorkshop.isDedicated()) {
            return !this.checkCameraCollide();
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean checkCameraCollide() {
        if (Minecraft.func_71410_x().field_71439_g != null && !Minecraft.func_71410_x().field_71439_g.func_70115_ae()) {
            return false;
        }
        int renderCount = 0;
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stack.length; ++i) {
            StackTraceElement element = stack[i];
            if (element.getClassName().equals(EntityRenderer.class.getName())) {
                ++renderCount;
            }
            if (renderCount != 4) continue;
            return true;
        }
        return false;
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        TileEntitySkinnable te = this.getTileEntity(world, x, y, z);
        if (te != null) {
            ForgeDirection dir = this.getFacingDirection(world, x, y, z);
            if (dir == ForgeDirection.NORTH) {
                te.setBoundsOnBlock((Block)this, 1, 0, 0);
                return;
            }
            if (dir == ForgeDirection.EAST) {
                te.setBoundsOnBlock((Block)this, 0, 0, 1);
                return;
            }
            if (dir == ForgeDirection.SOUTH) {
                te.setBoundsOnBlock((Block)this, 1, 0, 2);
                return;
            }
            if (dir == ForgeDirection.WEST) {
                te.setBoundsOnBlock((Block)this, 2, 0, 1);
                return;
            }
        }
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister register) {
        this.field_149761_L = register.func_94245_a(LibBlockResources.SKINNABLE);
        this.cubeIcon = register.func_94245_a(LibBlockResources.CUBE);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        if (meta == 4) {
            return this.cubeIcon;
        }
        return this.field_149761_L;
    }

    private TileEntitySkinnable getTileEntity(IBlockAccess world, int x, int y, int z) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileEntitySkinnable) {
            return (TileEntitySkinnable)te;
        }
        ModLogger.log(Level.WARN, String.format("Block skin at x:%d y:%d z:%d has no tile entity.", x, y, z));
        return null;
    }

    private SkinPointer getSkinPointer(IBlockAccess world, int x, int y, int z) {
        TileEntitySkinnable te = this.getTileEntity(world, x, y, z);
        if (te != null) {
            return te.getSkinPointer();
        }
        ModLogger.log(Level.WARN, String.format("Block skin at x:%d y:%d z:%d has no skin data.", x, y, z));
        return null;
    }

    private Skin getSkin(IBlockAccess world, int x, int y, int z) {
        SkinPointer skinPointer = this.getSkinPointer(world, x, y, z);
        if (skinPointer != null) {
            return SkinUtils.getSkinDetectSide(skinPointer, true, true);
        }
        return null;
    }

    public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
        Skin skin = this.getSkin(world, x, y, z);
        if (skin != null) {
            return SkinProperties.PROP_BLOCK_LADDER.getValue(skin.getProperties());
        }
        return false;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        SkinPointer skinPointer = this.getSkinPointer((IBlockAccess)world, x, y, z);
        if (skinPointer != null) {
            ItemStack returnStack = new ItemStack(ModItems.equipmentSkin, 1);
            SkinNBTHelper.addSkinDataToStack(returnStack, skinPointer);
            return returnStack;
        }
        return null;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return new ArrayList<ItemStack>();
    }

    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
        this.dropSkin(world, x, y, z, player.field_71075_bZ.field_75098_d);
        return super.removedByPlayer(world, player, x, y, z, willHarvest);
    }

    private void dropSkin(World world, int x, int y, int z, boolean isCreativeMode) {
        TileEntitySkinnable te = this.getTileEntity((IBlockAccess)world, x, y, z);
        if (te != null) {
            SkinPointer skinPointer = te.getSkinPointer();
            if (skinPointer != null) {
                if (!isCreativeMode) {
                    ItemStack skinStack = new ItemStack(ModItems.equipmentSkin, 1);
                    SkinNBTHelper.addSkinDataToStack(skinStack, skinPointer);
                    UtilItems.spawnItemInWorld(world, x, y, z, skinStack);
                }
                te.killChildren(world);
            } else {
                ModLogger.log(Level.WARN, String.format("Block skin at x:%d y:%d z:%d had no skin data.", x, y, z));
            }
        }
    }

    public ForgeDirection getFacingDirection(IBlockAccess world, int x, int y, int z) {
        return this.getFacingDirection(world.func_72805_g(x, y, z));
    }

    public ForgeDirection getFacingDirection(int metadata) {
        return this.convertMetadataToDirection(metadata);
    }

    public void setFacingDirection(World world, int x, int y, int z, ForgeDirection direction) {
        this.setFacingDirection(world, x, y, z, direction.ordinal());
    }

    public void setFacingDirection(World world, int x, int y, int z, int metadata) {
        world.func_72921_c(x, y, z, metadata, 2);
    }

    public int convertDirectionToMetadata(ForgeDirection direction) {
        int meta = direction.ordinal();
        return meta == 2 ? 4 : (meta == 3 ? 2 : (meta == 4 ? 3 : (meta == 5 ? 5 : 2)));
    }

    public ForgeDirection convertMetadataToDirection(int metadata) {
        if (metadata == 5) {
            return ForgeDirection.EAST;
        }
        if (metadata == 4) {
            return ForgeDirection.NORTH;
        }
        if (metadata == 3) {
            return ForgeDirection.WEST;
        }
        if (metadata == 2) {
            return ForgeDirection.SOUTH;
        }
        return ForgeDirection.EAST;
    }

    public TileEntity func_149915_a(World world, int p_149915_2_) {
        return new TileEntitySkinnable();
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149721_r() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return -1;
    }

    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
        if (world.field_72995_K) {
            return false;
        }
        Skin skin = this.getSkin((IBlockAccess)world, x, y, z);
        if (skin == null) {
            return false;
        }
        if (SkinProperties.PROP_BLOCK_MULTIBLOCK.getValue(skin.getProperties()).booleanValue()) {
            return false;
        }
        int rotation = world.func_72805_g(x, y, z);
        if (++rotation > 3) {
            rotation = 0;
        }
        world.func_72921_c(x, y, z, rotation, 2);
        return true;
    }

    @Override
    public void getDebugHoverText(World world, int x, int y, int z, ArrayList<String> textLines) {
        textLines.add("Direction: " + this.getFacingDirection((IBlockAccess)world, x, y, z));
    }

    public static class Seat
    extends Entity
    implements IEntityAdditionalSpawnData {
        private int noRiderTime = 0;
        private Point3D offset;
        private int rotation;

        public Seat(World world, int x, int y, int z, Point3D offset, int rotation) {
            super(world);
            this.func_70107_b(x, y, z);
            this.func_70105_a(0.0f, 0.0f);
            this.offset = offset;
            this.rotation = rotation;
        }

        public Seat(World world) {
            super(world);
            this.func_70105_a(0.0f, 0.0f);
            this.offset = new Point3D(0, 0, 0);
        }

        protected void func_70088_a() {
        }

        public void func_70043_V() {
            if (this.field_70153_n != null) {
                ForgeDirection[] rotMatrix = new ForgeDirection[]{ForgeDirection.SOUTH, ForgeDirection.WEST, ForgeDirection.NORTH, ForgeDirection.EAST};
                float scale = 0.0625f;
                ForgeDirection dir = rotMatrix[this.rotation & 3];
                float offsetX = (float)this.offset.getX() * scale * (float)dir.offsetZ + (float)(-this.offset.getZ()) * scale * (float)dir.offsetX;
                float offsetY = (float)this.offset.getY() * scale;
                float offsetZ = (float)(-this.offset.getZ()) * scale * (float)dir.offsetZ + (float)(-this.offset.getX()) * scale * (float)dir.offsetX;
                this.field_70153_n.func_70107_b(this.field_70165_t + 0.5 - (double)offsetX, this.field_70163_u + this.field_70153_n.func_70033_W() + 0.5 - (double)offsetY, this.field_70161_v + 0.5 - (double)offsetZ);
            }
        }

        public void func_70071_h_() {
            super.func_70071_h_();
            if (!(this.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v) instanceof BlockSkinnable)) {
                this.func_70106_y();
                return;
            }
            if (this.field_70153_n == null) {
                ++this.noRiderTime;
                if (this.noRiderTime > 1) {
                    this.func_70106_y();
                }
            } else if (this.field_70153_n.func_70093_af()) {
                this.field_70153_n.func_70107_b(this.field_70165_t + 0.5, this.field_70163_u + 2.0, this.field_70161_v + 0.5);
                this.func_70106_y();
            }
        }

        public void func_70100_b_(EntityPlayer player) {
            if (player.field_70154_o != this) {
                super.func_70100_b_(player);
            }
        }

        public boolean shouldRenderInPass(int pass) {
            return false;
        }

        protected void func_70037_a(NBTTagCompound compound) {
        }

        protected void func_70014_b(NBTTagCompound compound) {
        }

        public void writeSpawnData(ByteBuf buf) {
            buf.writeInt(this.offset.getX());
            buf.writeInt(this.offset.getY());
            buf.writeInt(this.offset.getZ());
            buf.writeInt(this.rotation);
        }

        public void readSpawnData(ByteBuf buf) {
            this.offset = new Point3D(buf.readInt(), buf.readInt(), buf.readInt());
            this.rotation = buf.readInt();
        }
    }
}

