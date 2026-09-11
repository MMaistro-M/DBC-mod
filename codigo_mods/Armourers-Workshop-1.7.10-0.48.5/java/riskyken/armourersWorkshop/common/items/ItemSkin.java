/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.dispenser.BehaviorDefaultDispenseItem
 *  net.minecraft.dispenser.IBehaviorDispenseItem
 *  net.minecraft.dispenser.IBlockSource
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.input.Keyboard
 */
package riskyken.armourersWorkshop.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.input.Keyboard;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.client.settings.Keybindings;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.blocks.BlockLocation;
import riskyken.armourersWorkshop.common.blocks.BlockSkinnable;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;
import riskyken.armourersWorkshop.common.items.AbstractModItem;
import riskyken.armourersWorkshop.common.items.AbstractModItemArmour;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.skin.cubes.CubeRegistry;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinnable;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinnableChild;
import riskyken.armourersWorkshop.common.wardrobe.ExPropsPlayerSkinData;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;
import riskyken.armourersWorkshop.utils.SkinUtils;
import riskyken.armourersWorkshop.utils.TranslateUtils;
import riskyken.armourersWorkshop.utils.UtilPlayer;

public class ItemSkin
extends AbstractModItem {
    @SideOnly(value=Side.CLIENT)
    private IIcon loadingIcon;
    private static final IBehaviorDispenseItem dispenserBehavior = new BehaviorDefaultDispenseItem(){

        protected ItemStack func_82487_b(IBlockSource blockSource, ItemStack itemStack) {
            if (!SkinNBTHelper.stackHasSkinData(itemStack)) {
                return super.func_82487_b(blockSource, itemStack);
            }
            EnumFacing enumfacing = BlockDispenser.func_149937_b((int)blockSource.func_82620_h());
            int x = blockSource.func_82623_d() + enumfacing.func_82601_c();
            int y = blockSource.func_82622_e() + enumfacing.func_96559_d();
            int z = blockSource.func_82621_f() + enumfacing.func_82599_e();
            AxisAlignedBB axisalignedbb = AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)(x + 1), (double)(y + 1), (double)(z + 1));
            List list = blockSource.func_82618_k().func_72872_a(EntityLivingBase.class, axisalignedbb);
            for (int i = 0; i < list.size(); ++i) {
                EntityPlayer player;
                ExPropsPlayerSkinData equipmentData;
                EntityLivingBase entitylivingbase = (EntityLivingBase)list.get(i);
                if (!(entitylivingbase instanceof EntityPlayer) || !(equipmentData = ExPropsPlayerSkinData.get(player = (EntityPlayer)entitylivingbase)).setStackInNextFreeSlot(itemStack.func_77946_l())) continue;
                --itemStack.field_77994_a;
                return itemStack;
            }
            return super.func_82487_b(blockSource, itemStack);
        }
    };

    public ItemSkin() {
        super("equipmentSkin", false);
        BlockDispenser.field_149943_a.func_82595_a((Object)this, (Object)dispenserBehavior);
    }

    public ISkinType getSkinType(ItemStack stack) {
        return SkinNBTHelper.getSkinTypeFromStack(stack);
    }

    public String func_77653_i(ItemStack stack) {
        Skin skin = SkinUtils.getSkinDetectSide(stack, true, false);
        if (skin != null && !skin.getCustomName().trim().isEmpty()) {
            return skin.getCustomName();
        }
        return super.func_77653_i(stack);
    }

    public static void addTooltipToSkinItem(ItemStack stack, EntityPlayer player, List tooltip, boolean showAdvancedItemTooltips) {
        String cRed = EnumChatFormatting.RED.toString();
        boolean isEquipmentSkin = stack.func_77973_b() == ModItems.equipmentSkin;
        boolean isEquipmentContainer = stack.func_77973_b() instanceof AbstractModItemArmour;
        if (SkinNBTHelper.stackHasSkinData(stack)) {
            SkinPointer skinData = SkinNBTHelper.getSkinPointerFromStack(stack);
            SkinIdentifier identifier = skinData.getIdentifier();
            if (!isEquipmentSkin & !skinData.lockSkin & !isEquipmentContainer) {
                return;
            }
            if (!isEquipmentSkin) {
                tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.hasSkin"));
            }
            if (ClientSkinCache.INSTANCE.isSkinInCache(skinData)) {
                Skin data = ClientSkinCache.INSTANCE.getSkin(skinData);
                if (stack.func_77973_b() != ModItems.equipmentSkin & !data.getCustomName().trim().isEmpty()) {
                    tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinName", data.getCustomName()));
                }
                if (!data.getAuthorName().trim().isEmpty()) {
                    tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinAuthor", data.getAuthorName()));
                }
                if (skinData.getIdentifier().getSkinType() != null) {
                    String localSkinName = SkinTypeRegistry.INSTANCE.getLocalizedSkinTypeName(skinData.getIdentifier().getSkinType());
                    tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinType", localSkinName));
                }
                if (ConfigHandlerClient.showSkinTooltipDebugInfo) {
                    if (GuiScreen.func_146272_n()) {
                        tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinIdentifier"));
                        if (identifier.hasLocalId()) {
                            tooltip.add("  " + TranslateUtils.translate("item.armourersworkshop:rollover.skinId", identifier.getSkinLocalId()));
                        }
                        if (identifier.hasLibraryFile()) {
                            tooltip.add("  " + TranslateUtils.translate("item.armourersworkshop:rollover.skinLibraryFile", identifier.getSkinLibraryFile().getFullName()));
                        }
                        if (identifier.hasGlobalId()) {
                            tooltip.add("  " + TranslateUtils.translate("item.armourersworkshop:rollover.skinGlobalId", identifier.getSkinGlobalId()));
                        }
                        tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinTotalCubes", data.getTotalCubes()));
                        tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinNumCubes", data.getTotalOfCubeType(CubeRegistry.INSTANCE.getCubeFormId((byte)0))));
                        tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinNumCubesGlowing", data.getTotalOfCubeType(CubeRegistry.INSTANCE.getCubeFormId((byte)1))));
                        tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinNumCubesGlass", data.getTotalOfCubeType(CubeRegistry.INSTANCE.getCubeFormId((byte)2))));
                        tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinNumCubesGlassGlowing", data.getTotalOfCubeType(CubeRegistry.INSTANCE.getCubeFormId((byte)3))));
                        tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinPaintData", data.hasPaintData()));
                        tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinMarkerCount", data.getMarkerCount()));
                        tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinDyeCount", skinData.getSkinDye().getNumberOfDyes()));
                        tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinProperties"));
                        ArrayList<String> props = data.getProperties().getPropertiesList();
                        for (int i = 0; i < props.size(); ++i) {
                            tooltip.add("  " + props.get(i));
                        }
                    } else {
                        tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinHoldShiftForInfo"));
                    }
                }
                if (identifier.hasLocalId() && identifier.getSkinLocalId() != data.lightHash()) {
                    tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinIdError1"));
                    tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinIdError2"));
                    tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinIdError3", data.requestId, data.lightHash()));
                }
            } else {
                tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skindownloading", identifier.toString()));
                if (identifier.hasLocalId()) {
                    tooltip.add("  " + TranslateUtils.translate("item.armourersworkshop:rollover.skinId", identifier.getSkinLocalId()));
                }
                if (identifier.hasLibraryFile()) {
                    tooltip.add("  " + TranslateUtils.translate("item.armourersworkshop:rollover.skinLibraryFile", identifier.getSkinLibraryFile().getFullName()));
                }
                if (identifier.hasGlobalId()) {
                    tooltip.add("  " + TranslateUtils.translate("item.armourersworkshop:rollover.skinGlobalId", identifier.getSkinGlobalId()));
                }
            }
            String keyName = Keyboard.getKeyName((int)Keybindings.openCustomArmourGui.func_151463_i());
            if (isEquipmentSkin) {
                tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinOpenWardrobe", keyName));
            }
        } else if (SkinNBTHelper.stackHasLegacySkinData(stack)) {
            tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinOldType"));
        } else if (isEquipmentSkin) {
            tooltip.add(TranslateUtils.translate("item.armourersworkshop:rollover.skinInvalidItem"));
        }
    }

    public int getRenderPasses(int metadata) {
        return 2;
    }

    public boolean func_77623_v() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister register) {
        this.field_77791_bV = register.func_94245_a(LibItemResources.TEMPLATE_BLANK);
        this.loadingIcon = register.func_94245_a(LibItemResources.TEMPLATE_LOADING);
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        SkinPointer skinData;
        if (pass == 1) {
            return this.loadingIcon;
        }
        if (SkinNBTHelper.stackHasSkinData(stack) && (skinData = SkinNBTHelper.getSkinPointerFromStack(stack)).getIdentifier().getSkinType() != null && skinData.getIdentifier().getSkinType().getIcon() != null) {
            return skinData.getIdentifier().getSkinType().getIcon();
        }
        return this.field_77791_bV;
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Skin skin;
        Block block = world.func_147439_a(x, y, z);
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stack);
        if (skinPointer != null && skinPointer.getIdentifier().getSkinType() == SkinTypeRegistry.skinBlock && (skin = SkinUtils.getSkinDetectSide(skinPointer, false, true)) != null) {
            ForgeDirection dir = ForgeDirection.getOrientation((int)side);
            Block replaceBlock = world.func_147439_a(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
            if (replaceBlock.isReplaceable((IBlockAccess)world, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ)) {
                this.placeSkinAtLocation(world, player, side, stack, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, skinPointer);
                return true;
            }
        }
        return false;
    }

    public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(itemStack);
        if (!world.field_72995_K && skinPointer != null && this.equipSkin(player, itemStack.func_77946_l())) {
            --itemStack.field_77994_a;
        }
        return itemStack;
    }

    private boolean equipSkin(EntityPlayer player, ItemStack itemStack) {
        ExPropsPlayerSkinData equipmentData = ExPropsPlayerSkinData.get(player);
        return equipmentData.setStackInNextFreeSlot(itemStack);
    }

    private boolean canPlaceSkinAtLocation(World world, EntityPlayer player, int side, ItemStack stack, int x, int y, int z, SkinPointer skinPointer) {
        if (!player.func_82247_a(x, y, z, side, stack)) {
            return false;
        }
        if (stack.field_77994_a == 0) {
            return false;
        }
        if (y == 255) {
            return false;
        }
        return world.func_147472_a(world.func_147439_a(x, y, z), x, y, z, false, side, null, stack);
    }

    private boolean canPlaceChildren(World world, EntityPlayer player, int side, ItemStack stack, int x, int y, int z, Skin skin, SkinPointer skinPointer, ArrayList<BlockLocation> relatedBlocks) {
        ForgeDirection dir = UtilPlayer.getDirectionSide(player).getOpposite();
        for (int ix = 0; ix < 3; ++ix) {
            for (int iy = 0; iy < 3; ++iy) {
                for (int iz = 0; iz < 3; ++iz) {
                    float[] bounds = TileEntitySkinnable.getBlockBounds(skin, -ix + 2, iy, iz, dir);
                    if (bounds == null) continue;
                    int childX = x;
                    int childY = y;
                    int childZ = z;
                    relatedBlocks.add(new BlockLocation(childX += ix - 1 - dir.offsetX * 1, childY += iy, childZ += iz - 1 - dir.offsetZ * 1));
                    Block replaceBlock = world.func_147439_a(childX, childY, childZ);
                    if (replaceBlock.isReplaceable((IBlockAccess)world, childX, childY, childZ)) continue;
                    return false;
                }
            }
        }
        return true;
    }

    private boolean placeSkinAtLocation(World world, EntityPlayer player, int side, ItemStack stack, int x, int y, int z, SkinPointer skinPointer) {
        if (!this.canPlaceSkinAtLocation(world, player, side, stack, x, y, z, skinPointer)) {
            return false;
        }
        ForgeDirection dir = UtilPlayer.getDirectionSide(player).getOpposite();
        Skin skin = SkinUtils.getSkinDetectSide(stack, false, true);
        if (skin == null) {
            return false;
        }
        BlockSkinnable targetBlock = (BlockSkinnable)ModBlocks.skinnable;
        if (SkinProperties.PROP_BLOCK_GLOWING.getValue(skin.getProperties()).booleanValue()) {
            targetBlock = (BlockSkinnable)ModBlocks.skinnableGlowing;
        }
        int meta = targetBlock.convertDirectionToMetadata(dir);
        boolean multiblock = SkinProperties.PROP_BLOCK_MULTIBLOCK.getValue(skin.getProperties());
        ArrayList<BlockLocation> relatedBlocks = new ArrayList<BlockLocation>();
        if (multiblock) {
            if (!this.canPlaceChildren(world, player, side, stack, x, y, z, skin, skinPointer, relatedBlocks)) {
                return false;
            }
            this.placeChildren(world, player, side, x, y, z, skin, skinPointer, relatedBlocks);
        }
        world.func_147465_d(x, y, z, (Block)targetBlock, meta, 2);
        world.func_147455_a(x, y, z, targetBlock.func_149915_a(world, 0));
        TileEntitySkinnable te = (TileEntitySkinnable)world.func_147438_o(x, y, z);
        te.setSkinPointer(skin, skinPointer);
        targetBlock.func_149689_a(world, x, y, z, (EntityLivingBase)player, stack);
        targetBlock.func_149714_e(world, x, y, z, meta);
        te.setRelatedBlocks(relatedBlocks);
        --stack.field_77994_a;
        world.func_72908_a((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), "dig.stone", 1.0f, 1.0f);
        return true;
    }

    private void placeChildren(World world, EntityPlayer player, int side, int x, int y, int z, Skin skin, SkinPointer skinPointer, ArrayList<BlockLocation> relatedBlocks) {
        for (int ix = 0; ix < 3; ++ix) {
            for (int iy = 0; iy < 3; ++iy) {
                for (int iz = 0; iz < 3; ++iz) {
                    this.placeChild(world, player, side, x, y, z, ix, iy, iz, skin, skinPointer, relatedBlocks);
                }
            }
        }
    }

    private void placeChild(World world, EntityPlayer player, int side, int x, int y, int z, int ix, int iy, int iz, Skin skin, SkinPointer skinPointer, ArrayList<BlockLocation> relatedBlocks) {
        float[] bounds;
        ForgeDirection dir = UtilPlayer.getDirectionSide(player).getOpposite();
        BlockSkinnable targetBlock = (BlockSkinnable)ModBlocks.skinnableChild;
        int meta = targetBlock.convertDirectionToMetadata(dir);
        if (SkinProperties.PROP_BLOCK_GLOWING.getValue(skin.getProperties()).booleanValue()) {
            targetBlock = (BlockSkinnable)ModBlocks.skinnableChildGlowing;
        }
        if ((bounds = TileEntitySkinnable.getBlockBounds(skin, -ix + 2, iy, iz, dir)) != null) {
            int childX = x;
            int childY = y;
            int childZ = z;
            world.func_147465_d(childX += ix - 1 - dir.offsetX * 1, childY += iy, childZ += iz - 1 - dir.offsetZ * 1, (Block)targetBlock, meta, 2);
            world.func_147455_a(childX, childY, childZ, targetBlock.createTileEntity(world, meta));
            TileEntitySkinnableChild te = (TileEntitySkinnableChild)world.func_147438_o(childX, childY, childZ);
            te.setSkinPointer(skin, skinPointer);
            te.setParentLocation(x, y, z);
            te.setRelatedBlocks(relatedBlocks);
        }
    }
}

