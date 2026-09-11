/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.miniarmourer;

import riskyken.armourersWorkshop.api.common.IRectangle3D;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;

public class SkinPartLayer {
    private byte[][][] cubeId;
    private byte[][][][] cubeColourR;
    private byte[][][][] cubeColourG;
    private byte[][][][] cubeColourB;
    private byte[][][][] cubePaintType;

    public SkinPartLayer(ISkinPartType partType) {
        IRectangle3D buildSpace = partType.getBuildingSpace();
        this.setCubeCount(buildSpace.getWidth(), buildSpace.getHeight(), buildSpace.getDepth());
    }

    public void setCubeCount(int width, int height, int depth) {
        this.cubeId = new byte[width][height][depth];
        this.cubeColourR = new byte[width][height][depth][6];
        this.cubeColourG = new byte[width][height][depth][6];
        this.cubeColourB = new byte[width][height][depth][6];
        this.cubePaintType = new byte[width][height][depth][6];
    }
}

