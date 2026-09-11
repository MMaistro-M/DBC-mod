/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.common.util.ForgeDirection
 */
package riskyken.armourersWorkshop.common.skin.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import riskyken.armourersWorkshop.api.common.skin.Point3D;
import riskyken.armourersWorkshop.api.common.skin.Rectangle3D;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPart;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.client.skin.ClientSkinPartData;
import riskyken.armourersWorkshop.common.blocks.BlockLocation;
import riskyken.armourersWorkshop.common.skin.cubes.CubeMarkerData;
import riskyken.armourersWorkshop.common.skin.data.SkinCubeData;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;

public class SkinPart
implements ISkinPart {
    private Rectangle3D partBounds;
    private Rectangle3D[][][] blockGrid;
    private SkinCubeData cubeData;
    private ArrayList<CubeMarkerData> markerBlocks;
    private ISkinPartType skinPart;
    @SideOnly(value=Side.CLIENT)
    private ClientSkinPartData clientSkinPartData;

    public SkinPart(SkinCubeData cubeData, ISkinPartType skinPart, ArrayList<CubeMarkerData> markerBlocks) {
        this.cubeData = cubeData;
        this.skinPart = skinPart;
        this.markerBlocks = markerBlocks;
        this.partBounds = this.setupPartBounds();
    }

    @SideOnly(value=Side.CLIENT)
    public void setClientSkinPartData(ClientSkinPartData clientSkinPartData) {
        this.clientSkinPartData = clientSkinPartData;
    }

    @SideOnly(value=Side.CLIENT)
    public ClientSkinPartData getClientSkinPartData() {
        return this.clientSkinPartData;
    }

    @SideOnly(value=Side.CLIENT)
    public int getModelCount() {
        return this.clientSkinPartData.getModelCount();
    }

    private Rectangle3D setupPartBounds() {
        if (this.skinPart == SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:block.base") | this.skinPart == SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:block.multiblock")) {
            this.setupBlockBounds();
        }
        int minX = 127;
        int maxX = -127;
        int minY = 127;
        int maxY = -127;
        int minZ = 127;
        int maxZ = -127;
        for (int i = 0; i < this.cubeData.getCubeCount(); ++i) {
            byte[] loc = this.cubeData.getCubeLocation(i);
            byte x = loc[0];
            byte y = loc[1];
            byte z = loc[2];
            minX = Math.min(x, minX);
            maxX = Math.max(x, maxX);
            minY = Math.min(y, minY);
            maxY = Math.max(y, maxY);
            minZ = Math.min(z, minZ);
            maxZ = Math.max(z, maxZ);
        }
        int xSize = maxX - minX;
        int ySize = maxY - minY;
        int zSize = maxZ - minZ;
        return new Rectangle3D(minX, minY, minZ, xSize + 1, ySize + 1, zSize + 1);
    }

    public void setSkinPart(ISkinPartType skinPart) {
        this.skinPart = skinPart;
        this.setupPartBounds();
    }

    public Rectangle3D getBlockBounds(int x, int y, int z) {
        return this.blockGrid[x][y][z];
    }

    public Rectangle3D[][][] getBlockGrid() {
        return this.blockGrid;
    }

    private void setupBlockBounds() {
        this.blockGrid = new Rectangle3D[3][3][3];
        for (int i = 0; i < this.cubeData.getCubeCount(); ++i) {
            byte[] loc = this.cubeData.getCubeLocation(i);
            int x = MathHelper.func_76141_d((float)((float)(loc[0] + 8) / 16.0f));
            int y = MathHelper.func_76141_d((float)((float)(loc[1] + 8) / 16.0f));
            int z = MathHelper.func_76141_d((float)((float)(loc[2] + 8) / 16.0f));
            this.setupBlockBounds(x, y, z, loc[0] - x * 16, loc[1] - y * 16, loc[2] - z * 16);
        }
        for (int ix = 0; ix < 3; ++ix) {
            for (int iy = 0; iy < 3; ++iy) {
                for (int iz = 0; iz < 3; ++iz) {
                    Rectangle3D rec = this.blockGrid[ix][iy][iz];
                    if (rec == null) continue;
                    rec.setWidth(rec.getWidth() - rec.getX() + 1);
                    rec.setHeight(rec.getHeight() - rec.getY() + 1);
                    rec.setDepth(rec.getDepth() - rec.getZ() + 1);
                }
            }
        }
    }

    private void setupBlockBounds(int blockX, int blockY, int blockZ, int x, int y, int z) {
        BlockLocation loc = new BlockLocation(blockX + 1, -blockY, blockZ);
        if (this.blockGrid[loc.x][loc.y][loc.z] == null) {
            this.blockGrid[loc.x][loc.y][loc.z] = new Rectangle3D(127, 127, 127, -127, -127, -127);
        }
        Rectangle3D rec = this.blockGrid[loc.x][loc.y][loc.z];
        rec.setX(Math.min(rec.getX(), x));
        rec.setY(Math.min(rec.getY(), y));
        rec.setZ(Math.min(rec.getZ(), z));
        rec.setWidth(Math.max(rec.getWidth(), x));
        rec.setHeight(Math.max(rec.getHeight(), y));
        rec.setDepth(Math.max(rec.getDepth(), z));
    }

    public SkinCubeData getCubeData() {
        return this.cubeData;
    }

    public void clearCubeData() {
        this.cubeData = null;
    }

    public Rectangle3D getPartBounds() {
        return this.partBounds;
    }

    @Override
    public ISkinPartType getPartType() {
        return this.skinPart;
    }

    public ArrayList<CubeMarkerData> getMarkerBlocks() {
        return this.markerBlocks;
    }

    @Override
    public int getMarkerCount() {
        return this.markerBlocks.size();
    }

    @Override
    public Point3D getMarker(int index) {
        if (index >= 0 & index < this.markerBlocks.size()) {
            CubeMarkerData cmd = this.markerBlocks.get(index);
            return new Point3D(cmd.x, cmd.y, cmd.z);
        }
        return null;
    }

    @Override
    public ForgeDirection getMarkerSide(int index) {
        if (index >= 0 & index < this.markerBlocks.size()) {
            CubeMarkerData cmd = this.markerBlocks.get(index);
            return ForgeDirection.getOrientation((int)(cmd.meta - 1));
        }
        return null;
    }

    public String toString() {
        return "SkinPart [cubeData=" + this.cubeData + ", markerBlocks=" + this.markerBlocks + ", skinPart=" + this.skinPart.getRegistryName() + "]";
    }
}

