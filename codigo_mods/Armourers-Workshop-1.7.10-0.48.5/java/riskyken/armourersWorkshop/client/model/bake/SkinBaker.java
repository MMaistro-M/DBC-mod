/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.entity.Entity
 *  net.minecraftforge.common.util.ForgeDirection
 */
package riskyken.armourersWorkshop.client.model.bake;

import java.util.ArrayList;
import java.util.HashSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.util.ForgeDirection;
import riskyken.armourersWorkshop.api.common.skin.Rectangle3D;
import riskyken.armourersWorkshop.client.model.bake.ColouredFace;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;
import riskyken.armourersWorkshop.common.skin.cubes.CubeRegistry;
import riskyken.armourersWorkshop.common.skin.cubes.ICube;
import riskyken.armourersWorkshop.common.skin.data.SkinCubeData;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.proxies.ClientProxy;

public final class SkinBaker {
    public static boolean withinMaxRenderDistance(Entity entity) {
        return SkinBaker.withinMaxRenderDistance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
    }

    public static boolean withinMaxRenderDistance(double x, double y, double z) {
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        return !(player.func_70011_f(x, y, z) > (double)ConfigHandlerClient.maxSkinRenderDistance);
    }

    public static int[][][] cullFacesOnEquipmentPart(SkinPart skinPart) {
        SkinCubeData cubeData = skinPart.getCubeData();
        cubeData.setupFaceFlags();
        skinPart.getClientSkinPartData().totalCubesInPart = new int[CubeRegistry.INSTANCE.getTotalCubes()];
        Rectangle3D pb = skinPart.getPartBounds();
        int[][][] cubeArray = new int[pb.getWidth()][pb.getHeight()][pb.getDepth()];
        for (int i = 0; i < cubeData.getCubeCount(); ++i) {
            byte cubeId = cubeData.getCubeId(i);
            byte[] cubeLoc = cubeData.getCubeLocation(i);
            byte by = cubeId;
            skinPart.getClientSkinPartData().totalCubesInPart[by] = skinPart.getClientSkinPartData().totalCubesInPart[by] + 1;
            int x = cubeLoc[0] - pb.getX();
            int y = cubeLoc[1] - pb.getY();
            int z = cubeLoc[2] - pb.getZ();
            cubeArray[x][y][z] = i + 1;
        }
        ArrayList<CubeLocation> openList = new ArrayList<CubeLocation>();
        HashSet<Integer> closedSet = new HashSet<Integer>();
        CubeLocation startCube = new CubeLocation(-1, -1, -1);
        openList.add(startCube);
        closedSet.add(startCube.hashCode());
        while (openList.size() > 0) {
            CubeLocation cl = (CubeLocation)openList.get(openList.size() - 1);
            openList.remove(openList.size() - 1);
            ArrayList<CubeLocation> foundLocations = SkinBaker.checkCubesAroundLocation(cubeData, cl, pb, cubeArray);
            for (int i = 0; i < foundLocations.size(); ++i) {
                CubeLocation foundLocation = foundLocations.get(i);
                if (closedSet.contains(foundLocation.hashCode())) continue;
                closedSet.add(foundLocation.hashCode());
                if (!SkinBaker.isCubeInSearchArea(foundLocation, pb)) continue;
                openList.add(foundLocation);
            }
        }
        return cubeArray;
    }

    private static ArrayList<CubeLocation> checkCubesAroundLocation(SkinCubeData cubeData, CubeLocation cubeLocation, Rectangle3D partBounds, int[][][] cubeArray) {
        ArrayList<CubeLocation> openList = new ArrayList<CubeLocation>();
        ForgeDirection[] dirs = new ForgeDirection[]{ForgeDirection.DOWN, ForgeDirection.UP, ForgeDirection.SOUTH, ForgeDirection.NORTH, ForgeDirection.WEST, ForgeDirection.EAST};
        int index = SkinBaker.getIndexForLocation(cubeLocation, partBounds, cubeArray);
        boolean isGlass = false;
        if (index > 0) {
            ICube cube = cubeData.getCube(index - 1);
            isGlass = cube.needsPostRender();
        }
        for (int i = 0; i < dirs.length; ++i) {
            ForgeDirection dir = dirs[i];
            int x = cubeLocation.x + dir.offsetX;
            int y = cubeLocation.y + dir.offsetY;
            int z = cubeLocation.z + dir.offsetZ;
            int tarIndex = SkinBaker.getIndexForLocation(x, y, z, partBounds, cubeArray);
            if (tarIndex < 1) {
                openList.add(new CubeLocation(x, y, z));
            } else if (cubeData.getCube(tarIndex - 1).needsPostRender()) {
                openList.add(new CubeLocation(x, y, z));
            }
            if (tarIndex <= 0) continue;
            SkinBaker.flagCubeFace(x, y, z, i, partBounds, cubeArray, cubeData, isGlass);
        }
        return openList;
    }

    private static int getIndexForLocation(CubeLocation cubeLocation, Rectangle3D partBounds, int[][][] cubeArray) {
        return SkinBaker.getIndexForLocation(cubeLocation.x, cubeLocation.y, cubeLocation.z, partBounds, cubeArray);
    }

    private static int getIndexForLocation(int x, int y, int z, Rectangle3D partBounds, int[][][] cubeArray) {
        if (x >= 0 & x < partBounds.getWidth() && y >= 0 & y < partBounds.getHeight() && z >= 0 & z < partBounds.getDepth()) {
            return cubeArray[x][y][z];
        }
        return 0;
    }

    private static void flagCubeFace(int x, int y, int z, int face, Rectangle3D partBounds, int[][][] cubeArray, SkinCubeData cubeData, boolean isGlass) {
        int checkIndex = SkinBaker.getIndexForLocation(x, y, z, partBounds, cubeArray);
        if (!isGlass) {
            cubeData.getFaceFlags(checkIndex - 1).set(face, true);
        } else {
            ICube cube = cubeData.getCube(checkIndex - 1);
            cubeData.getFaceFlags(checkIndex - 1).set(face, cube.needsPostRender() != isGlass);
        }
    }

    private static boolean isCubeInSearchArea(CubeLocation cubeLocation, Rectangle3D partBounds) {
        return cubeLocation.x > -2 & cubeLocation.x < partBounds.getWidth() + 1 && cubeLocation.y > -2 & cubeLocation.y < partBounds.getHeight() + 1 && cubeLocation.z > -2 & cubeLocation.z < partBounds.getDepth() + 1;
    }

    public static void buildPartDisplayListArray(SkinPart partData, int[][] dyeColour, int[] dyeUseCount, int[][][] cubeArray) {
        boolean multipassSkinRendering = ClientProxy.useMultipassSkinRendering();
        int lodLevels = ConfigHandlerClient.maxLodLevels;
        ArrayList[] renderLists = new ArrayList[ClientProxy.getNumberOfRenderLayers() * (lodLevels + 1)];
        for (int i = 0; i < renderLists.length; ++i) {
            renderLists[i] = new ArrayList();
        }
        SkinCubeData cubeData = partData.getCubeData();
        Rectangle3D pb = partData.getPartBounds();
        for (int ix = 0; ix < pb.getWidth(); ++ix) {
            for (int iy = 0; iy < pb.getHeight(); ++iy) {
                for (int iz = 0; iz < pb.getDepth(); ++iz) {
                    int i = SkinBaker.getIndexForLocation(ix, iy, iz, pb, cubeArray) - 1;
                    if (i != -1) {
                        byte[] loc = cubeData.getCubeLocation(i);
                        byte[] paintType = cubeData.getCubePaintType(i);
                        ICube cube = partData.getCubeData().getCube(i);
                        byte a = -1;
                        if (cube.needsPostRender()) {
                            a = 127;
                        }
                        byte[] r = cubeData.getCubeColourR(i);
                        byte[] g = cubeData.getCubeColourG(i);
                        byte[] b = cubeData.getCubeColourB(i);
                        for (int j = 0; j < 6; ++j) {
                            int paint = paintType[j] & 0xFF;
                            if (paint >= 1 && paint <= 8 && cubeData.getFaceFlags(i).get(j)) {
                                int n = paint - 1;
                                dyeUseCount[n] = dyeUseCount[n] + 1;
                                int[] nArray = dyeColour[0];
                                int n2 = paint - 1;
                                nArray[n2] = nArray[n2] + (r[j] & 0xFF);
                                int[] nArray2 = dyeColour[1];
                                int n3 = paint - 1;
                                nArray2[n3] = nArray2[n3] + (g[j] & 0xFF);
                                int[] nArray3 = dyeColour[2];
                                int n4 = paint - 1;
                                nArray3[n4] = nArray3[n4] + (b[j] & 0xFF);
                            }
                            if (paint == 253) {
                                dyeUseCount[8] = dyeUseCount[8] + 1;
                                int[] nArray = dyeColour[0];
                                nArray[8] = nArray[8] + (r[j] & 0xFF);
                                int[] nArray4 = dyeColour[1];
                                nArray4[8] = nArray4[8] + (g[j] & 0xFF);
                                int[] nArray5 = dyeColour[2];
                                nArray5[8] = nArray5[8] + (b[j] & 0xFF);
                            }
                            if (paint != 254) continue;
                            dyeUseCount[9] = dyeUseCount[9] + 1;
                            int[] nArray = dyeColour[0];
                            nArray[9] = nArray[9] + (r[j] & 0xFF);
                            int[] nArray6 = dyeColour[1];
                            nArray6[9] = nArray6[9] + (g[j] & 0xFF);
                            int[] nArray7 = dyeColour[2];
                            nArray7[9] = nArray7[9] + (b[j] & 0xFF);
                        }
                        int listIndex = 0;
                        if (multipassSkinRendering) {
                            if (cube.isGlowing() && !cube.needsPostRender()) {
                                listIndex = 1;
                            }
                            if (cube.needsPostRender() && !cube.isGlowing()) {
                                listIndex = 2;
                            }
                            if (cube.isGlowing() && cube.needsPostRender()) {
                                listIndex = 3;
                            }
                        } else if (cube.isGlowing()) {
                            listIndex = 1;
                        }
                        for (int j = 0; j < 6; ++j) {
                            if (!cubeData.getFaceFlags(i).get(j)) continue;
                            ColouredFace ver = new ColouredFace(loc[0], loc[1], loc[2], r[j], g[j], b[j], a, paintType[j], (byte)j, 1);
                            renderLists[listIndex].add(ver);
                        }
                    }
                    for (int lod = 1; lod < lodLevels + 1; ++lod) {
                        byte lodLevel = (byte)Math.pow(2.0, lod);
                        if (!(ix % lodLevel == 0 & iy % lodLevel == 0 & iz % lodLevel == 0)) continue;
                        for (int j = 0; j < 6; ++j) {
                            boolean showFace = SkinBaker.getAverageFaceFlags(ix, iy, iz, lodLevel, cubeArray, cubeData, pb, j);
                            if (!showFace) continue;
                            byte[] avegC = SkinBaker.getAverageRGBAT(ix, iy, iz, lodLevel, cubeArray, cubeData, pb, j);
                            ICube cube = CubeRegistry.INSTANCE.getCubeFormId(avegC[5]);
                            int listIndex = 0;
                            if (multipassSkinRendering) {
                                if (cube.isGlowing() && !cube.needsPostRender()) {
                                    listIndex = 1;
                                }
                                if (cube.needsPostRender() && !cube.isGlowing()) {
                                    listIndex = 2;
                                }
                                if (cube.isGlowing() && cube.needsPostRender()) {
                                    listIndex = 3;
                                }
                            } else if (cube.isGlowing()) {
                                listIndex = 1;
                            }
                            int lodIndex = lod * ClientProxy.getNumberOfRenderLayers() + listIndex;
                            ColouredFace ver = new ColouredFace((byte)(ix + pb.getX()), (byte)(iy + pb.getY()), (byte)(iz + pb.getZ()), avegC[0], avegC[1], avegC[2], avegC[3], avegC[4], (byte)j, lodLevel);
                            renderLists[lodIndex].add(ver);
                        }
                    }
                }
            }
        }
        partData.getClientSkinPartData().setVertexLists(renderLists);
    }

    private static boolean getAverageFaceFlags(int x, int y, int z, byte lodLevel, int[][][] cubeArray, SkinCubeData cubeData, Rectangle3D partBounds, int face) {
        for (int ix = 0; ix < lodLevel; ++ix) {
            for (int iy = 0; iy < lodLevel; ++iy) {
                for (int iz = 0; iz < lodLevel; ++iz) {
                    int index = SkinBaker.getIndexForLocation(ix + x, iy + y, iz + z, partBounds, cubeArray) - 1;
                    if (index == -1 || !cubeData.getFaceFlags(index).get(face)) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private static byte[] getAverageRGBAT(int x, int y, int z, byte lodLevel, int[][][] cubeArray, SkinCubeData cubeData, Rectangle3D partBounds, int face) {
        int count = 0;
        int r = 0;
        int g = 0;
        int b = 0;
        int a = 0;
        int[] paintTypes = new int[256];
        int[] cubeTypes = new int[256];
        for (int ix = 0; ix < lodLevel; ++ix) {
            for (int iy = 0; iy < lodLevel; ++iy) {
                for (int iz = 0; iz < lodLevel; ++iz) {
                    int index = SkinBaker.getIndexForLocation(ix + x, iy + y, iz + z, partBounds, cubeArray) - 1;
                    if (index == -1 || !cubeData.getFaceFlags(index).get(face)) continue;
                    ++count;
                    r += cubeData.getCubeColourR(index)[face] & 0xFF;
                    g += cubeData.getCubeColourG(index)[face] & 0xFF;
                    b += cubeData.getCubeColourB(index)[face] & 0xFF;
                    a = cubeData.getCube(index).needsPostRender() ? (a += 127) : (a += 255);
                    int n = cubeData.getCubePaintType(index)[face] & 0xFF;
                    paintTypes[n] = paintTypes[n] + 1;
                    int n2 = cubeData.getCubeId(index) & 0xFF;
                    cubeTypes[n2] = cubeTypes[n2] + 1;
                }
            }
        }
        byte[] rgbat = new byte[6];
        if (count != 0) {
            rgbat[0] = (byte)(r / count);
            rgbat[1] = (byte)(g / count);
            rgbat[2] = (byte)(b / count);
            rgbat[3] = (byte)(a / count);
            int commonPaintTypeIndex = 0;
            int mostPaintTypes = 0;
            for (int i = 0; i < paintTypes.length; ++i) {
                if (paintTypes[i] <= mostPaintTypes) continue;
                mostPaintTypes = paintTypes[i];
                commonPaintTypeIndex = i;
            }
            rgbat[4] = (byte)commonPaintTypeIndex;
            int commonCubeTypesIndex = 0;
            int mostCubeTypes = 0;
            for (int i = 0; i < cubeTypes.length; ++i) {
                if (cubeTypes[i] <= mostCubeTypes) continue;
                mostCubeTypes = cubeTypes[i];
                commonCubeTypesIndex = i;
            }
            rgbat[5] = (byte)commonCubeTypesIndex;
        }
        return rgbat;
    }

    private static class CubeLocation {
        public final int x;
        public final int y;
        public final int z;

        public CubeLocation(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int hashCode() {
            return this.toString().hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            CubeLocation other = (CubeLocation)obj;
            if (this.x != other.x) {
                return false;
            }
            if (this.y != other.y) {
                return false;
            }
            return this.z == other.z;
        }

        public String toString() {
            return "CubeLocation [x=" + this.x + ", y=" + this.y + ", z=" + this.z + "]";
        }
    }
}

