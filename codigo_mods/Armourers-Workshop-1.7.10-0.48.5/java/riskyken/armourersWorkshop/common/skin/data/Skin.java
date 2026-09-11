/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.common.skin.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.IRectangle3D;
import riskyken.armourersWorkshop.api.common.skin.Rectangle3D;
import riskyken.armourersWorkshop.api.common.skin.data.ISkin;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPart;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.skin.SkinModelTexture;
import riskyken.armourersWorkshop.common.skin.cubes.CubeRegistry;
import riskyken.armourersWorkshop.common.skin.cubes.ICube;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;

public class Skin
implements ISkin {
    public static final int FILE_VERSION = 13;
    public static final String KEY_AUTHOR_NAME = "authorName";
    public static final String KEY_AUTHOR_UUID = "authorUUID";
    public static final String KEY_CUSTOM_NAME = "customName";
    public static final String KEY_FILE_NAME = "fileName";
    private SkinProperties properties;
    private ISkinType equipmentSkinType;
    private int[] paintData;
    private ArrayList<SkinPart> parts;
    private int lightHash = 0;
    public SkinIdentifier requestId;
    public int serverId = -1;
    @SideOnly(value=Side.CLIENT)
    public SkinModelTexture skinModelTexture;
    @SideOnly(value=Side.CLIENT)
    public int paintTextureId;
    private int[] averageR = new int[10];
    private int[] averageG = new int[10];
    private int[] averageB = new int[10];

    public void setAverageDyeValues(int[] r, int[] g, int[] b) {
        this.averageR = r;
        this.averageG = g;
        this.averageB = b;
    }

    @SideOnly(value=Side.CLIENT)
    public Rectangle3D getSkinBounds() {
        int i;
        int x = 0;
        int y = 0;
        int z = 0;
        int width = 1;
        int height = 1;
        int depth = 1;
        for (i = 0; i < this.getPartCount(); ++i) {
            if (this.getSkinType() == SkinTypeRegistry.skinBow && i > 0) continue;
            SkinPart skinPart = this.getParts().get(i);
            Rectangle3D bounds = skinPart.getPartBounds();
            width = Math.max(width, bounds.getWidth());
            height = Math.max(height, bounds.getHeight());
            depth = Math.max(depth, bounds.getDepth());
            x = bounds.getX();
            y = bounds.getY();
            z = bounds.getZ();
            if (!this.hasPaintData()) continue;
            IRectangle3D skinRec = skinPart.getPartType().getGuideSpace();
            width = Math.max(width, skinRec.getWidth());
            height = Math.max(height, skinRec.getHeight());
            depth = Math.max(depth, skinRec.getDepth());
            x = Math.max(x, skinRec.getX());
            y = Math.max(y, skinRec.getY());
            z = Math.max(z, skinRec.getZ());
        }
        if (this.getPartCount() == 0) {
            for (i = 0; i < this.getSkinType().getSkinParts().size(); ++i) {
                ISkinPartType part = this.getSkinType().getSkinParts().get(i);
                IRectangle3D skinRec = part.getGuideSpace();
                width = Math.max(width, skinRec.getWidth());
                height = Math.max(height, skinRec.getHeight());
                depth = Math.max(depth, skinRec.getDepth());
                x = Math.min(x, skinRec.getX());
                y = Math.max(y, skinRec.getY());
                z = Math.min(z, skinRec.getZ());
            }
        }
        return new Rectangle3D(x, y, z, width, height, depth);
    }

    public int[] getAverageDyeColour(int dyeNumber) {
        return new int[]{this.averageR[dyeNumber], this.averageG[dyeNumber], this.averageB[dyeNumber]};
    }

    public SkinProperties getProperties() {
        return this.properties;
    }

    public Skin(SkinProperties properties, ISkinType equipmentSkinType, int[] paintData, ArrayList<SkinPart> equipmentSkinParts) {
        this.properties = properties;
        this.equipmentSkinType = equipmentSkinType;
        this.paintData = null;
        if (paintData != null) {
            boolean validPaintData = false;
            for (int i = 0; i < 2048; ++i) {
                if (paintData[i] >>> 16 == 255) continue;
                validPaintData = true;
                break;
            }
            if (validPaintData) {
                this.paintData = paintData;
            }
        }
        this.parts = equipmentSkinParts;
    }

    @SideOnly(value=Side.CLIENT)
    public void cleanUpDisplayLists() {
        for (int i = 0; i < this.parts.size(); ++i) {
            this.parts.get(i).getClientSkinPartData().cleanUpDisplayLists();
        }
        if (this.hasPaintData()) {
            this.skinModelTexture.func_147631_c();
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void blindPaintTexture() {
        if (this.hasPaintData()) {
            GL11.glBindTexture((int)3553, (int)this.paintTextureId);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public int getModelCount() {
        int count = 0;
        for (int i = 0; i < this.parts.size(); ++i) {
            count += this.parts.get(i).getModelCount();
        }
        return count;
    }

    public int getPartCount() {
        return this.parts.size();
    }

    public int lightHash() {
        if (this.lightHash == 0) {
            this.lightHash = this.hashCode();
        }
        return this.lightHash;
    }

    @Override
    public ISkinType getSkinType() {
        return this.equipmentSkinType;
    }

    public boolean hasPaintData() {
        return this.paintData != null;
    }

    public int[] getPaintData() {
        return this.paintData;
    }

    public ArrayList<SkinPart> getParts() {
        return this.parts;
    }

    public SkinPart getSkinPartFromType(ISkinPartType skinPartType) {
        for (int i = 0; i < this.parts.size(); ++i) {
            if (this.parts.get(i).getPartType() != skinPartType) continue;
            return this.parts.get(i);
        }
        return null;
    }

    @Override
    public ArrayList<ISkinPart> getSubParts() {
        ArrayList<ISkinPart> partList = new ArrayList<ISkinPart>();
        for (int i = 0; i < this.parts.size(); ++i) {
            partList.add(this.parts.get(i));
        }
        return partList;
    }

    public boolean hasPart(String partRegistryName) {
        for (int i = 0; i < this.parts.size(); ++i) {
            if (!this.parts.get(i).getPartType().getRegistryName().equals(partRegistryName)) continue;
            return true;
        }
        return false;
    }

    public SkinPart getPart(String partRegistryName) {
        for (int i = 0; i < this.parts.size(); ++i) {
            if (!this.parts.get(i).getPartType().getRegistryName().equals(partRegistryName)) continue;
            return this.parts.get(i);
        }
        return null;
    }

    public String getCustomName() {
        return this.properties.getPropertyString(KEY_CUSTOM_NAME, "");
    }

    public String getAuthorName() {
        return this.properties.getPropertyString(KEY_AUTHOR_NAME, "");
    }

    public int getTotalCubes() {
        int totalCubes = 0;
        for (int i = 0; i < CubeRegistry.INSTANCE.getTotalCubes(); ++i) {
            ICube cube = CubeRegistry.INSTANCE.getCubeFormId((byte)i);
            totalCubes += this.getTotalOfCubeType(cube);
        }
        return totalCubes;
    }

    public int getTotalOfCubeType(ICube cube) {
        int totalOfCube = 0;
        byte cubeId = cube.getId();
        for (int i = 0; i < this.parts.size(); ++i) {
            totalOfCube += this.parts.get((int)i).getClientSkinPartData().totalCubesInPart[cubeId];
        }
        return totalOfCube;
    }

    public int hashCode() {
        if (this.lightHash == 0) {
            String result = this.toString();
            for (int i = 0; i < this.parts.size(); ++i) {
                result = result + this.parts.get(i).toString();
            }
            this.lightHash = result.hashCode();
        }
        return this.lightHash;
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
        Skin other = (Skin)obj;
        return other.lightHash == this.lightHash;
    }

    public String toString() {
        String returnString = "Skin [properties=" + this.properties + ", type=" + this.equipmentSkinType.getName().toUpperCase();
        if (this.paintData != null) {
            returnString = returnString + ", paintData=" + Arrays.hashCode(this.paintData);
        }
        returnString = returnString + "]";
        return returnString;
    }

    public int getMarkerCount() {
        int count = 0;
        for (int i = 0; i < this.parts.size(); ++i) {
            count += this.parts.get(i).getMarkerBlocks().size();
        }
        return count;
    }
}

