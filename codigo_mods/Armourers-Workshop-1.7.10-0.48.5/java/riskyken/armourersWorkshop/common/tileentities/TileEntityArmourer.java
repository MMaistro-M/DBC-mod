/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTUtil
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package riskyken.armourersWorkshop.common.tileentities;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import riskyken.armourersWorkshop.api.common.painting.IPantableBlock;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.texture.PlayerTexture;
import riskyken.armourersWorkshop.common.blocks.BlockLocation;
import riskyken.armourersWorkshop.common.data.TextureType;
import riskyken.armourersWorkshop.common.exception.SkinSaveException;
import riskyken.armourersWorkshop.common.items.ItemSkin;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.painting.IBlockPainter;
import riskyken.armourersWorkshop.common.skin.ArmourerWorldHelper;
import riskyken.armourersWorkshop.common.skin.ISkinHolder;
import riskyken.armourersWorkshop.common.skin.cache.CommonSkinCache;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.data.SkinProperty;
import riskyken.armourersWorkshop.common.skin.data.SkinTexture;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.tileentities.AbstractTileEntityInventory;
import riskyken.armourersWorkshop.common.undo.UndoManager;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class TileEntityArmourer
extends AbstractTileEntityInventory {
    private static final String TAG_DIRECTION = "direction";
    private static final String TAG_TYPE = "skinType";
    private static final String TAG_SHOW_GUIDES = "showGuides";
    private static final String TAG_SHOW_OVERLAY = "showOverlay";
    private static final String TAG_SHOW_HELPER = "showHelper";
    private static final String TAG_PAINT_DATA = "paintData";
    private static final String TAG_TEXTURE = "texture";
    private static final String TAG_TYPE_OLD = "type";
    private static final String TAG_OWNER_OLD = "owner";
    private static final int HEIGHT_OFFSET = 1;
    private static final int INVENTORY_SIZE = 2;
    private ForgeDirection direction;
    private ISkinType skinType;
    private boolean showGuides = true;
    private boolean showHelper = true;
    private SkinProperties skinProps;
    private int[] paintData;
    public boolean loadedArmourItem = false;
    private PlayerTexture texture = new PlayerTexture("", TextureType.USER);
    private PlayerTexture textureOld = new PlayerTexture("", TextureType.USER);
    @SideOnly(value=Side.CLIENT)
    public SkinTexture skinTexture;

    public TileEntityArmourer() {
        super(2);
        this.direction = ForgeDirection.NORTH;
        this.skinType = SkinTypeRegistry.INSTANCE.getSkinTypeFromRegistryName("armourers:head");
        this.skinProps = new SkinProperties();
        this.clearPaintData(false);
    }

    public boolean canUpdate() {
        return false;
    }

    public int[] getPaintData() {
        return this.paintData;
    }

    public void updatePaintData(int x, int y, int colour) {
        this.paintData[x + y * 64] = colour;
        this.func_70296_d();
        this.syncWithClients();
    }

    public int getPaintData(int x, int y) {
        return this.paintData[x + y * 64];
    }

    public void saveArmourItem(EntityPlayerMP player, String customName, String tags) {
        if (this.func_145831_w().field_72995_K) {
            return;
        }
        ItemStack stackInput = this.func_70301_a(0);
        ItemStack stackOutput = this.func_70301_a(1);
        if (!player.field_71075_bZ.field_75098_d && stackInput == null) {
            return;
        }
        if (stackOutput != null) {
            return;
        }
        ISkinHolder inputItem = null;
        if (!player.field_71075_bZ.field_75098_d) {
            if (!(stackInput.func_77973_b() instanceof ISkinHolder)) {
                return;
            }
            inputItem = (ISkinHolder)stackInput.func_77973_b();
        } else {
            inputItem = (ISkinHolder)ModItems.equipmentSkinTemplate;
        }
        Skin armourItemData = null;
        SkinProperties skinProps = new SkinProperties();
        skinProps.setProperty("authorName", player.func_70005_c_());
        if (player.func_146103_bH() != null && player.func_146103_bH().getId() != null) {
            skinProps.setProperty("authorUUID", player.func_146103_bH().getId().toString());
        }
        skinProps.setProperty("customName", customName);
        for (int i = 0; i < this.skinType.getProperties().size(); ++i) {
            SkinProperty skinProp = (SkinProperty)this.skinType.getProperties().get(i);
            skinProp.setValue(skinProps, skinProp.getValue(this.skinProps));
        }
        try {
            armourItemData = ArmourerWorldHelper.saveSkinFromWorld(this.field_145850_b, skinProps, this.skinType, this.paintData, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, this.direction);
        }
        catch (SkinSaveException e) {
            switch (e.getType()) {
                case NO_DATA: {
                    player.func_145747_a((IChatComponent)new ChatComponentText(e.getMessage()));
                    break;
                }
                case MARKER_ERROR: {
                    player.func_145747_a((IChatComponent)new ChatComponentText(e.getMessage()));
                    break;
                }
                case MISSING_PARTS: {
                    player.func_145747_a((IChatComponent)new ChatComponentText(e.getMessage()));
                    break;
                }
                case BED_AND_SEAT: {
                    player.func_145747_a((IChatComponent)new ChatComponentText(e.getMessage()));
                    break;
                }
                case INVALID_MULTIBLOCK: {
                    player.func_145747_a((IChatComponent)new ChatComponentText(e.getMessage()));
                }
            }
        }
        if (armourItemData == null) {
            return;
        }
        CommonSkinCache.INSTANCE.addEquipmentDataToCache(armourItemData, null);
        stackOutput = inputItem.makeStackForEquipment(armourItemData);
        if (stackOutput == null) {
            return;
        }
        if (!player.field_71075_bZ.field_75098_d) {
            this.func_70298_a(0, 1);
        }
        this.func_70299_a(1, stackOutput);
    }

    public void loadArmourItem(EntityPlayerMP player) {
        if (this.func_145831_w().field_72995_K) {
            return;
        }
        ItemStack stackInput = this.func_70301_a(0);
        ItemStack stackOuput = this.func_70301_a(1);
        if (stackInput == null) {
            return;
        }
        if (stackOuput != null) {
            return;
        }
        if (!(stackInput.func_77973_b() instanceof ItemSkin)) {
            return;
        }
        SkinPointer skinPointerInput = SkinNBTHelper.getSkinPointerFromStack(stackInput);
        if (skinPointerInput == null) {
            return;
        }
        if (this.skinType == null) {
            return;
        }
        if (this.skinType != skinPointerInput.getIdentifier().getSkinType() && (this.skinType != SkinTypeRegistry.skinLegs || skinPointerInput.getIdentifier().getSkinType() != SkinTypeRegistry.oldSkinSkirt)) {
            return;
        }
        Skin skin = CommonSkinCache.INSTANCE.getSkin(skinPointerInput);
        if (skin == null) {
            return;
        }
        this.setSkinProps(new SkinProperties(skin.getProperties()));
        ArmourerWorldHelper.loadSkinIntoWorld(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, skin, this.direction);
        if (skin.hasPaintData()) {
            this.paintData = (int[])skin.getPaintData().clone();
        } else {
            this.clearPaintData(true);
        }
        this.dirtySync();
        this.func_70299_a(0, null);
        this.func_70299_a(1, stackInput);
    }

    public void clearPaintData(boolean update) {
        this.paintData = new int[2048];
        for (int i = 0; i < 2048; ++i) {
            this.paintData[i] = 0xFFFFFF;
        }
        if (update) {
            this.dirtySync();
        }
    }

    public void toolUsedOnArmourer(IBlockPainter tool, World world, ItemStack stack, EntityPlayer player) {
        UndoManager.begin(player);
        this.applyToolToBlocks(tool, world, stack, player);
        UndoManager.end(player);
    }

    private void applyToolToBlocks(IBlockPainter tool, World world, ItemStack stack, EntityPlayer player) {
        if (this.skinType != null) {
            ArrayList<BlockLocation> paintableCubes = ArmourerWorldHelper.getListOfPaintableCubes(this.field_145850_b, this.field_145851_c, this.field_145848_d + this.getHeightOffset(), this.field_145849_e, this.skinType);
            for (int i = 0; i < paintableCubes.size(); ++i) {
                BlockLocation bl = paintableCubes.get(i);
                Block block = world.func_147439_a(bl.x, bl.y, bl.z);
                if (!(block instanceof IPantableBlock)) continue;
                for (int side = 0; side < 6; ++side) {
                    tool.usedOnBlockSide(stack, player, world, bl, block, side);
                }
            }
        }
    }

    public void onPlaced() {
        this.createBoundingBoxes();
    }

    public void preRemove() {
        this.removeBoundingBoxes();
    }

    public int getHeightOffset() {
        return 1;
    }

    public void copySkinCubes(EntityPlayerMP player, ISkinPartType srcPart, ISkinPartType desPart, boolean mirror) {
        try {
            ArmourerWorldHelper.copySkinCubes(this.field_145850_b, this.field_145851_c, this.field_145848_d + this.getHeightOffset(), this.field_145849_e, srcPart, desPart, mirror);
        }
        catch (SkinSaveException e) {
            player.func_145747_a((IChatComponent)new ChatComponentText(e.getMessage()));
        }
    }

    public void clearArmourCubes(ISkinPartType partType) {
        if (this.skinType != null) {
            ArmourerWorldHelper.clearEquipmentCubes(this.field_145850_b, this.field_145851_c, this.field_145848_d + this.getHeightOffset(), this.field_145849_e, this.skinType, this.skinProps, partType);
            SkinProperties newSkinProps = new SkinProperties();
            SkinProperties.PROP_BLOCK_MULTIBLOCK.setValue(newSkinProps, SkinProperties.PROP_BLOCK_MULTIBLOCK.getValue(this.skinProps));
            this.setSkinProps(newSkinProps);
            this.dirtySync();
        }
    }

    public void clearMarkers(ISkinPartType partType) {
        if (this.skinType != null) {
            ArmourerWorldHelper.clearMarkers(this.field_145850_b, this.field_145851_c, this.field_145848_d + this.getHeightOffset(), this.field_145849_e, this.skinType, this.skinProps, partType);
            SkinProperties newSkinProps = new SkinProperties();
            SkinProperties.PROP_BLOCK_MULTIBLOCK.setValue(newSkinProps, SkinProperties.PROP_BLOCK_MULTIBLOCK.getValue(this.skinProps));
            this.setSkinProps(newSkinProps);
            this.dirtySync();
        }
    }

    protected void removeBoundingBoxes() {
        if (this.skinType != null) {
            ArmourerWorldHelper.removeBoundingBoxes(this.field_145850_b, this.field_145851_c, this.field_145848_d + this.getHeightOffset(), this.field_145849_e, this.skinType);
        }
    }

    protected void createBoundingBoxes() {
        if (this.skinType != null) {
            ArmourerWorldHelper.createBoundingBoxes(this.field_145850_b, this.field_145851_c, this.field_145848_d + this.getHeightOffset(), this.field_145849_e, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.skinType, this.skinProps);
        }
    }

    public void setDirection(ForgeDirection direction) {
        this.direction = direction;
        this.dirtySync();
    }

    public ForgeDirection getDirection() {
        return this.direction;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        AxisAlignedBB bb = super.getRenderBoundingBox();
        bb = AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 15), (double)(this.field_145848_d - 10), (double)(this.field_145849_e - 46), (double)(this.field_145851_c + 30), (double)(this.field_145848_d + 40 + 23), (double)(this.field_145849_e + 35));
        return bb;
    }

    public ISkinType getSkinType() {
        return this.skinType;
    }

    public boolean isShowGuides() {
        return this.showGuides;
    }

    public boolean isShowHelper() {
        return this.showHelper;
    }

    public void setTexture(PlayerTexture texture) {
        this.textureOld = this.texture;
        this.texture = texture;
        this.dirtySync();
    }

    public PlayerTexture getTexture() {
        return this.texture;
    }

    public PlayerTexture getTextureOld() {
        return this.textureOld;
    }

    public void setSkinType(ISkinType skinType) {
        if (this.skinType == skinType) {
            return;
        }
        this.removeBoundingBoxes();
        this.skinType = skinType;
        this.skinProps = new SkinProperties();
        this.clearPaintData(true);
        this.createBoundingBoxes();
        this.dirtySync();
    }

    public void toggleGuides() {
        this.showGuides = !this.showGuides;
        this.dirtySync();
    }

    public void toggleHelper() {
        this.showHelper = !this.showHelper;
        this.dirtySync();
    }

    public SkinProperties getSkinProps() {
        return this.skinProps;
    }

    public void setSkinProps(SkinProperties skinProps) {
        boolean updateBounds = false;
        if (this.skinType != null && this.skinType.getVanillaArmourSlotId() != -1) {
            updateBounds = this.skinType.haveBoundsChanged(this.skinProps, skinProps);
        }
        this.skinProps = skinProps;
        if (updateBounds) {
            this.removeBoundingBoxes();
            this.createBoundingBoxes();
        }
        this.dirtySync();
    }

    public String func_145825_b() {
        return "armourerBrain";
    }

    public double func_145833_n() {
        return super.func_145833_n() * 10.0;
    }

    public Packet func_145844_m() {
        NBTTagCompound compound = new NBTTagCompound();
        this.writeBaseToNBT(compound);
        this.writeCommonToNBT(compound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 5, compound);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        NBTTagCompound compound = packet.func_148857_g();
        PlayerTexture playerTexture = this.texture;
        this.readBaseFromNBT(compound);
        this.readCommonFromNBT(compound);
        if (!this.texture.equals(playerTexture)) {
            this.textureOld = playerTexture;
        }
        this.syncWithClients();
        this.loadedArmourItem = true;
    }

    @Override
    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.readCommonFromNBT(compound);
    }

    @Override
    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        this.writeCommonToNBT(compound);
    }

    @Override
    public void readCommonFromNBT(NBTTagCompound compound) {
        super.readCommonFromNBT(compound);
        this.direction = ForgeDirection.getOrientation((int)compound.func_74771_c(TAG_DIRECTION));
        this.skinType = SkinTypeRegistry.INSTANCE.getSkinTypeFromRegistryName(compound.func_74779_i(TAG_TYPE));
        if (this.skinType == null && compound.func_74764_b(TAG_TYPE_OLD)) {
            this.skinType = SkinTypeRegistry.INSTANCE.getSkinTypeFromLegacyId(compound.func_74762_e(TAG_TYPE_OLD) - 1);
        }
        if (compound.func_150297_b(TAG_OWNER_OLD, 10)) {
            GameProfile gameProfile = NBTUtil.func_152459_a((NBTTagCompound)compound.func_74775_l(TAG_OWNER_OLD));
            this.texture = new PlayerTexture(gameProfile.getName(), TextureType.USER);
        }
        this.showGuides = compound.func_74767_n(TAG_SHOW_GUIDES);
        if (compound.func_74764_b(TAG_SHOW_HELPER)) {
            this.showHelper = compound.func_74767_n(TAG_SHOW_HELPER);
        }
        this.skinProps = new SkinProperties();
        this.skinProps.readFromNBT(compound);
        if (compound.func_150297_b(TAG_TEXTURE, 10)) {
            this.texture = PlayerTexture.fromNBT(compound.func_74775_l(TAG_TEXTURE));
        }
        if (compound.func_74764_b(TAG_PAINT_DATA)) {
            this.paintData = compound.func_74759_k(TAG_PAINT_DATA);
        }
    }

    @Override
    public void writeCommonToNBT(NBTTagCompound compound) {
        super.writeCommonToNBT(compound);
        compound.func_74774_a(TAG_DIRECTION, (byte)this.direction.ordinal());
        if (this.skinType != null) {
            compound.func_74778_a(TAG_TYPE, this.skinType.getRegistryName());
        }
        compound.func_74757_a(TAG_SHOW_GUIDES, this.showGuides);
        compound.func_74757_a(TAG_SHOW_HELPER, this.showHelper);
        this.skinProps.writeToNBT(compound);
        NBTTagCompound textureCompound = new NBTTagCompound();
        this.texture.writeToNBT(textureCompound);
        compound.func_74782_a(TAG_TEXTURE, (NBTBase)textureCompound);
        compound.func_74783_a(TAG_PAINT_DATA, this.paintData);
    }
}

