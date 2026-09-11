/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package riskyken.armourersWorkshop.common.skin;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import riskyken.armourersWorkshop.api.common.IPoint3D;
import riskyken.armourersWorkshop.api.common.IRectangle3D;
import riskyken.armourersWorkshop.api.common.skin.cubes.ICubeColour;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.blocks.BlockLocation;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.exception.SkinSaveException;
import riskyken.armourersWorkshop.common.skin.cubes.CubeColour;
import riskyken.armourersWorkshop.common.skin.cubes.CubeMarkerData;
import riskyken.armourersWorkshop.common.skin.cubes.CubeRegistry;
import riskyken.armourersWorkshop.common.skin.cubes.ICube;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinCubeData;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.skin.type.block.SkinBlock;
import riskyken.armourersWorkshop.common.tileentities.TileEntityBoundingBox;
import riskyken.armourersWorkshop.common.tileentities.TileEntityColourable;
import riskyken.armourersWorkshop.utils.BlockUtils;

public final class ArmourerWorldHelper {
    public static Skin saveSkinFromWorld(World world, SkinProperties skinProps, ISkinType skinType, int[] paintData, int xCoord, int yCoord, int zCoord, ForgeDirection direction) throws SkinSaveException {
        SkinPart testPart;
        Skin skin;
        ArrayList<SkinPart> parts = new ArrayList<SkinPart>();
        if (skinType == SkinTypeRegistry.skinBlock) {
            SkinPart skinPart;
            ISkinPartType partType = ((SkinBlock)SkinTypeRegistry.skinBlock).partBase;
            if (SkinProperties.PROP_BLOCK_MULTIBLOCK.getValue(skinProps).booleanValue()) {
                partType = ((SkinBlock)SkinTypeRegistry.skinBlock).partMultiblock;
            }
            if ((skinPart = ArmourerWorldHelper.saveArmourPart(world, partType, xCoord, yCoord, zCoord, direction, true)) != null) {
                parts.add(skinPart);
            }
        } else {
            for (int i = 0; i < skinType.getSkinParts().size(); ++i) {
                ISkinPartType partType = skinType.getSkinParts().get(i);
                SkinPart skinPart = ArmourerWorldHelper.saveArmourPart(world, partType, xCoord, yCoord, zCoord, direction, true);
                if (skinPart == null) continue;
                parts.add(skinPart);
            }
        }
        if (paintData != null) {
            paintData = (int[])paintData.clone();
        }
        if ((skin = new Skin(skinProps, skinType, paintData, parts)).getParts().size() == 0 && !skin.hasPaintData()) {
            throw new SkinSaveException("Nothing to save.", SkinSaveException.SkinSaveExceptionType.NO_DATA);
        }
        for (int i = 0; i < skinType.getSkinParts().size(); ++i) {
            ISkinPartType partType = skinType.getSkinParts().get(i);
            if (!partType.isPartRequired()) continue;
            boolean havePart = false;
            for (int j = 0; j < skin.getPartCount(); ++j) {
                if (partType != skin.getParts().get(j).getPartType()) continue;
                havePart = true;
                break;
            }
            if (havePart) continue;
            throw new SkinSaveException("Skin is missing part " + partType.getPartName(), SkinSaveException.SkinSaveExceptionType.MISSING_PARTS);
        }
        if (SkinProperties.PROP_BLOCK_BED.getValue(skinProps) & SkinProperties.PROP_BLOCK_SEAT.getValue(skinProps)) {
            throw new SkinSaveException("Skin can not be a bed and a seat.", SkinSaveException.SkinSaveExceptionType.BED_AND_SEAT);
        }
        if (skinType == SkinTypeRegistry.skinBlock & SkinProperties.PROP_BLOCK_MULTIBLOCK.getValue(skinProps) && (testPart = ArmourerWorldHelper.saveArmourPart(world, ((SkinBlock)SkinTypeRegistry.skinBlock).partBase, xCoord, yCoord, zCoord, direction, true)) == null) {
            throw new SkinSaveException("Multiblock has no blocks in the yellow area.", SkinSaveException.SkinSaveExceptionType.INVALID_MULTIBLOCK);
        }
        return skin;
    }

    private static SkinPart saveArmourPart(World world, ISkinPartType skinPart, int xCoord, int yCoord, int zCoord, ForgeDirection direction, boolean markerCheck) throws SkinSaveException {
        int cubeCount = ArmourerWorldHelper.getNumberOfCubesInPart(world, xCoord, yCoord, zCoord, skinPart);
        if (cubeCount < 1) {
            return null;
        }
        SkinCubeData cubeData = new SkinCubeData();
        cubeData.setCubeCount(cubeCount);
        ArrayList<CubeMarkerData> markerBlocks = new ArrayList<CubeMarkerData>();
        IRectangle3D buildSpace = skinPart.getBuildingSpace();
        IPoint3D offset = skinPart.getOffset();
        int i = 0;
        for (int ix = 0; ix < buildSpace.getWidth(); ++ix) {
            for (int iy = 0; iy < buildSpace.getHeight(); ++iy) {
                for (int iz = 0; iz < buildSpace.getDepth(); ++iz) {
                    Block block;
                    int x = xCoord + ix + -offset.getX() + buildSpace.getX();
                    int y = yCoord + iy + -offset.getY();
                    int z = zCoord + iz + offset.getZ() + buildSpace.getZ();
                    int xOrigin = -ix + -buildSpace.getX();
                    int yOrigin = -iy + -buildSpace.getY();
                    int zOrigin = -iz + -buildSpace.getZ();
                    if (world.func_147437_c(x, y, z) || !CubeRegistry.INSTANCE.isBuildingBlock(block = world.func_147439_a(x, y, z))) continue;
                    ArmourerWorldHelper.saveArmourBlockToList(world, x, y, z, xOrigin - 1, yOrigin - 1, -zOrigin, cubeData, i, markerBlocks, direction);
                    ++i;
                }
            }
        }
        if (markerCheck) {
            if (skinPart.getMinimumMarkersNeeded() > markerBlocks.size()) {
                throw new SkinSaveException("Missing marker for part " + skinPart.getPartName(), SkinSaveException.SkinSaveExceptionType.MARKER_ERROR);
            }
            if (markerBlocks.size() > skinPart.getMaximumMarkersNeeded()) {
                throw new SkinSaveException("Too many markers for part " + skinPart.getPartName(), SkinSaveException.SkinSaveExceptionType.MARKER_ERROR);
            }
        }
        return new SkinPart(cubeData, skinPart, markerBlocks);
    }

    private static void saveArmourBlockToList(World world, int x, int y, int z, int ix, int iy, int iz, SkinCubeData cubeData, int index, ArrayList<CubeMarkerData> markerBlocks, ForgeDirection direction) {
        Block block = world.func_147439_a(x, y, z);
        if (!CubeRegistry.INSTANCE.isBuildingBlock(block)) {
            return;
        }
        int meta = world.func_72805_g(x, y, z);
        ICubeColour c = BlockUtils.getColourFromTileEntity(world, x, y, z);
        byte cubeType = CubeRegistry.INSTANCE.getCubeFromBlock(block).getId();
        cubeData.setCubeId(index, cubeType);
        cubeData.setCubeLocation(index, (byte)ix, (byte)iy, (byte)iz);
        for (int i = 0; i < 6; ++i) {
            cubeData.setCubeColour(index, i, c.getRed(i), c.getGreen(i), c.getBlue(i));
            cubeData.setCubePaintType(index, i, c.getPaintType(i));
        }
        if (meta > 0) {
            markerBlocks.add(new CubeMarkerData((byte)ix, (byte)iy, (byte)iz, (byte)meta));
        }
    }

    public static void loadSkinIntoWorld(World world, int x, int y, int z, Skin skin, ForgeDirection direction) {
        ArrayList<SkinPart> parts = skin.getParts();
        for (int i = 0; i < parts.size(); ++i) {
            ArmourerWorldHelper.loadSkinPartIntoWorld(world, parts.get(i), x, y, z, direction, false);
        }
    }

    private static void loadSkinPartIntoWorld(World world, SkinPart partData, int xCoord, int yCoord, int zCoord, ForgeDirection direction, boolean mirror) {
        ISkinPartType skinPart = partData.getPartType();
        IRectangle3D buildSpace = skinPart.getBuildingSpace();
        IPoint3D offset = skinPart.getOffset();
        SkinCubeData cubeData = partData.getCubeData();
        for (int i = 0; i < cubeData.getCubeCount(); ++i) {
            ICube blockData = cubeData.getCube(i);
            int meta = 0;
            for (int j = 0; j < partData.getMarkerBlocks().size(); ++j) {
                CubeMarkerData cmd = partData.getMarkerBlocks().get(j);
                byte[] loc = cubeData.getCubeLocation(i);
                if (!(cmd.x == loc[0] & cmd.y == loc[1] & cmd.z == loc[2])) continue;
                meta = cmd.meta;
                break;
            }
            int xOrigin = -offset.getX();
            int yOrigin = -offset.getY() + -buildSpace.getY();
            int zOrigin = offset.getZ();
            ArmourerWorldHelper.loadSkinBlockIntoWorld(world, xCoord, yCoord, zCoord, xOrigin, yOrigin, zOrigin, blockData, direction, meta, cubeData, i, mirror);
        }
    }

    private static void loadSkinBlockIntoWorld(World world, int x, int y, int z, int xOrigin, int yOrigin, int zOrigin, ICube blockData, ForgeDirection direction, int meta, SkinCubeData cubeData, int index, boolean mirror) {
        int targetZ;
        int targetY;
        int targetX;
        byte[] loc = cubeData.getCubeLocation(index);
        int shiftX = -loc[0] - 1;
        int shiftY = loc[1] + 1;
        byte shiftZ = loc[2];
        if (mirror) {
            shiftX = loc[0];
        }
        if (world.func_147439_a(targetX = x + shiftX + xOrigin, targetY = y + yOrigin - shiftY, targetZ = z + shiftZ + zOrigin) == ModBlocks.boundingBox) {
            world.func_147468_f(targetX, targetY, targetZ);
            world.func_147475_p(targetX, targetY, targetZ);
        }
        if (world.func_147437_c(targetX, targetY, targetZ)) {
            Block targetBlock = blockData.getMinecraftBlock();
            world.func_147449_b(targetX, targetY, targetZ, targetBlock);
            world.func_72921_c(targetX, targetY, targetZ, meta, 2);
            TileEntity te = world.func_147438_o(targetX, targetY, targetZ);
            if (te != null && te instanceof TileEntityColourable) {
                CubeColour cc = new CubeColour();
                for (int i = 0; i < 6; ++i) {
                    byte[] c = cubeData.getCubeColour(index, i);
                    byte paintType = cubeData.getCubePaintType(index, i);
                    if (mirror) {
                        if (i == 4) {
                            c = cubeData.getCubeColour(index, 5);
                            paintType = cubeData.getCubePaintType(index, 5);
                        }
                        if (i == 5) {
                            c = cubeData.getCubeColour(index, 4);
                            paintType = cubeData.getCubePaintType(index, 4);
                        }
                    }
                    cc.setRed(c[0], i);
                    cc.setGreen(c[1], i);
                    cc.setBlue(c[2], i);
                    cc.setPaintType(paintType, i);
                }
                ((TileEntityColourable)te).setColour(cc);
            }
        }
    }

    public static void createBoundingBoxes(World world, int x, int y, int z, int parentX, int parentY, int parentZ, ISkinType skinType, SkinProperties skinProps) {
        for (int i = 0; i < skinType.getSkinParts().size(); ++i) {
            ISkinPartType skinPart = skinType.getSkinParts().get(i);
            ArmourerWorldHelper.createBoundingBoxesForSkinPart(world, x, y, z, parentX, parentY, parentZ, skinPart, skinProps);
        }
    }

    private static void createBoundingBoxesForSkinPart(World world, int x, int y, int z, int parentX, int parentY, int parentZ, ISkinPartType skinPart, SkinProperties skinProps) {
        if (skinPart.isModelOverridden(skinProps)) {
            return;
        }
        IRectangle3D buildSpace = skinPart.getBuildingSpace();
        IRectangle3D guideSpace = skinPart.getGuideSpace();
        IPoint3D offset = skinPart.getOffset();
        if (guideSpace == null) {
            return;
        }
        for (int ix = 0; ix < guideSpace.getWidth(); ++ix) {
            for (int iy = 0; iy < guideSpace.getHeight(); ++iy) {
                for (int iz = 0; iz < guideSpace.getDepth(); ++iz) {
                    int xTar = x + ix + -offset.getX() + guideSpace.getX();
                    int yTar = y + iy + -offset.getY() + guideSpace.getY() - buildSpace.getY();
                    int zTar = z + iz + offset.getZ() + guideSpace.getZ();
                    ISkinPartType guidePart = skinPart;
                    byte guideX = (byte)ix;
                    byte guideY = (byte)iy;
                    byte guideZ = (byte)iz;
                    if (!world.func_147437_c(xTar, yTar, zTar)) continue;
                    world.func_147449_b(xTar, yTar, zTar, ModBlocks.boundingBox);
                    TileEntity te = null;
                    te = world.func_147438_o(xTar, yTar, zTar);
                    if (te != null && te instanceof TileEntityBoundingBox) {
                        ((TileEntityBoundingBox)te).setParent(parentX, parentY, parentZ, guideX, guideY, guideZ, guidePart);
                        continue;
                    }
                    te = new TileEntityBoundingBox(parentX, parentY, parentZ, guideX, guideY, guideZ, guidePart);
                    world.func_147455_a(xTar, yTar, zTar, te);
                }
            }
        }
    }

    public static void removeBoundingBoxes(World world, int x, int y, int z, ISkinType skinType) {
        for (int i = 0; i < skinType.getSkinParts().size(); ++i) {
            ISkinPartType skinPart = skinType.getSkinParts().get(i);
            ArmourerWorldHelper.removeBoundingBoxesForSkinPart(world, x, y, z, skinPart);
        }
    }

    private static void removeBoundingBoxesForSkinPart(World world, int x, int y, int z, ISkinPartType skinPart) {
        IRectangle3D buildSpace = skinPart.getBuildingSpace();
        IRectangle3D guideSpace = skinPart.getGuideSpace();
        IPoint3D offset = skinPart.getOffset();
        if (guideSpace == null) {
            return;
        }
        for (int ix = 0; ix < guideSpace.getWidth(); ++ix) {
            for (int iy = 0; iy < guideSpace.getHeight(); ++iy) {
                for (int iz = 0; iz < guideSpace.getDepth(); ++iz) {
                    int zTar;
                    int yTar;
                    int xTar = x + ix + -offset.getX() + guideSpace.getX();
                    if (!world.func_72899_e(xTar, yTar = y + iy + -offset.getY() + guideSpace.getY() - buildSpace.getY(), zTar = z + iz + offset.getZ() + guideSpace.getZ()) || world.func_147439_a(xTar, yTar, zTar) != ModBlocks.boundingBox) continue;
                    world.func_147468_f(xTar, yTar, zTar);
                }
            }
        }
    }

    public static void copySkinCubes(World world, int x, int y, int z, ISkinPartType srcPart, ISkinPartType desPart, boolean mirror) throws SkinSaveException {
        ArrayList blList = new ArrayList();
        SkinPart skinPart = ArmourerWorldHelper.saveArmourPart(world, srcPart, x, y, z, ForgeDirection.UNKNOWN, false);
        if (skinPart != null) {
            skinPart.setSkinPart(desPart);
            ArmourerWorldHelper.loadSkinPartIntoWorld(world, skinPart, x, y, z, ForgeDirection.UNKNOWN, mirror);
        }
    }

    public static int clearEquipmentCubes(World world, int x, int y, int z, ISkinType skinType, SkinProperties skinProps) {
        return ArmourerWorldHelper.clearEquipmentCubes(world, x, y, z, skinType, skinProps, null);
    }

    public static int clearMarkers(World world, int x, int y, int z, ISkinType skinType, SkinProperties skinProps, ISkinPartType partType) {
        int blockCount = 0;
        for (int i = 0; i < skinType.getSkinParts().size(); ++i) {
            ISkinPartType skinPart = skinType.getSkinParts().get(i);
            if (partType != null && partType != skinPart) continue;
            if (skinType == SkinTypeRegistry.skinBlock) {
                boolean multiblock;
                if (skinPart == ((SkinBlock)SkinTypeRegistry.skinBlock).partBase & !(multiblock = SkinProperties.PROP_BLOCK_MULTIBLOCK.getValue(skinProps).booleanValue())) {
                    blockCount += ArmourerWorldHelper.clearMarkersForSkinPart(world, x, y, z, skinPart);
                }
                if (!(skinPart == ((SkinBlock)SkinTypeRegistry.skinBlock).partMultiblock & multiblock)) continue;
                blockCount += ArmourerWorldHelper.clearMarkersForSkinPart(world, x, y, z, skinPart);
                continue;
            }
            blockCount += ArmourerWorldHelper.clearMarkersForSkinPart(world, x, y, z, skinPart);
        }
        return blockCount;
    }

    private static int clearMarkersForSkinPart(World world, int x, int y, int z, ISkinPartType skinPart) {
        IRectangle3D buildSpace = skinPart.getBuildingSpace();
        IPoint3D offset = skinPart.getOffset();
        int blockCount = 0;
        for (int ix = 0; ix < buildSpace.getWidth(); ++ix) {
            for (int iy = 0; iy < buildSpace.getHeight(); ++iy) {
                for (int iz = 0; iz < buildSpace.getDepth(); ++iz) {
                    Block block;
                    int zTar;
                    int yTar;
                    int xTar = x + ix + -offset.getX() + buildSpace.getX();
                    if (!world.func_72899_e(xTar, yTar = y + iy + -offset.getY(), zTar = z + iz + offset.getZ() + buildSpace.getZ()) || !CubeRegistry.INSTANCE.isBuildingBlock(block = world.func_147439_a(xTar, yTar, zTar)) || world.func_72805_g(xTar, yTar, zTar) == 0) continue;
                    world.func_72921_c(xTar, yTar, zTar, 0, 2);
                    ++blockCount;
                }
            }
        }
        return blockCount;
    }

    public static int clearEquipmentCubes(World world, int x, int y, int z, ISkinType skinType, SkinProperties skinProps, ISkinPartType partType) {
        int blockCount = 0;
        for (int i = 0; i < skinType.getSkinParts().size(); ++i) {
            ISkinPartType skinPart = skinType.getSkinParts().get(i);
            if (partType != null && partType != skinPart) continue;
            if (skinType == SkinTypeRegistry.skinBlock) {
                boolean multiblock;
                if (skinPart == ((SkinBlock)SkinTypeRegistry.skinBlock).partBase & !(multiblock = SkinProperties.PROP_BLOCK_MULTIBLOCK.getValue(skinProps).booleanValue())) {
                    blockCount += ArmourerWorldHelper.clearEquipmentCubesForSkinPart(world, x, y, z, skinPart);
                }
                if (!(skinPart == ((SkinBlock)SkinTypeRegistry.skinBlock).partMultiblock & multiblock)) continue;
                blockCount += ArmourerWorldHelper.clearEquipmentCubesForSkinPart(world, x, y, z, skinPart);
                continue;
            }
            blockCount += ArmourerWorldHelper.clearEquipmentCubesForSkinPart(world, x, y, z, skinPart);
        }
        return blockCount;
    }

    private static int clearEquipmentCubesForSkinPart(World world, int x, int y, int z, ISkinPartType skinPart) {
        IRectangle3D buildSpace = skinPart.getBuildingSpace();
        IPoint3D offset = skinPart.getOffset();
        int blockCount = 0;
        for (int ix = 0; ix < buildSpace.getWidth(); ++ix) {
            for (int iy = 0; iy < buildSpace.getHeight(); ++iy) {
                for (int iz = 0; iz < buildSpace.getDepth(); ++iz) {
                    Block block;
                    int zTar;
                    int yTar;
                    int xTar = x + ix + -offset.getX() + buildSpace.getX();
                    if (!world.func_72899_e(xTar, yTar = y + iy + -offset.getY(), zTar = z + iz + offset.getZ() + buildSpace.getZ()) || !CubeRegistry.INSTANCE.isBuildingBlock(block = world.func_147439_a(xTar, yTar, zTar))) continue;
                    world.func_147468_f(xTar, yTar, zTar);
                    world.func_147475_p(xTar, yTar, zTar);
                    ++blockCount;
                }
            }
        }
        return blockCount;
    }

    public static ArrayList<BlockLocation> getListOfPaintableCubes(World world, int x, int y, int z, ISkinType skinType) {
        ArrayList<BlockLocation> blList = new ArrayList<BlockLocation>();
        for (int i = 0; i < skinType.getSkinParts().size(); ++i) {
            ISkinPartType skinPart = skinType.getSkinParts().get(i);
            ArmourerWorldHelper.getBuildingCubesForPart(world, x, y, z, skinPart, blList);
        }
        return blList;
    }

    private static void getBuildingCubesForPart(World world, int x, int y, int z, ISkinPartType skinPart, ArrayList<BlockLocation> blList) {
        IRectangle3D buildSpace = skinPart.getBuildingSpace();
        IPoint3D offset = skinPart.getOffset();
        for (int ix = 0; ix < buildSpace.getWidth(); ++ix) {
            for (int iy = 0; iy < buildSpace.getHeight(); ++iy) {
                for (int iz = 0; iz < buildSpace.getDepth(); ++iz) {
                    Block block;
                    int zTar;
                    int yTar;
                    int xTar = x + ix + -offset.getX() + buildSpace.getX();
                    if (!world.func_72899_e(xTar, yTar = y + iy + -offset.getY(), zTar = z + iz + offset.getZ() + buildSpace.getZ()) || !CubeRegistry.INSTANCE.isBuildingBlock(block = world.func_147439_a(xTar, yTar, zTar))) continue;
                    blList.add(new BlockLocation(xTar, yTar, zTar));
                }
            }
        }
    }

    private static int getNumberOfCubesInPart(World world, int x, int y, int z, ISkinPartType skinPart) {
        IRectangle3D buildSpace = skinPart.getBuildingSpace();
        IPoint3D offset = skinPart.getOffset();
        int cubeCount = 0;
        for (int ix = 0; ix < buildSpace.getWidth(); ++ix) {
            for (int iy = 0; iy < buildSpace.getHeight(); ++iy) {
                for (int iz = 0; iz < buildSpace.getDepth(); ++iz) {
                    Block block;
                    int zTar;
                    int yTar;
                    int xTar = x + ix + -offset.getX() + buildSpace.getX();
                    if (!world.func_72899_e(xTar, yTar = y + iy + -offset.getY(), zTar = z + iz + offset.getZ() + buildSpace.getZ()) || !CubeRegistry.INSTANCE.isBuildingBlock(block = world.func_147439_a(xTar, yTar, zTar))) continue;
                    ++cubeCount;
                }
            }
        }
        return cubeCount;
    }
}

