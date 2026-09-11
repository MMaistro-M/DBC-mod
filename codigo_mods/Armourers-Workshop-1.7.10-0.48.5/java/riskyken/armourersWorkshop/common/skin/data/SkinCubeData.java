/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 */
package riskyken.armourersWorkshop.common.skin.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.BitSet;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.common.exception.InvalidCubeTypeException;
import riskyken.armourersWorkshop.common.skin.cubes.CubeRegistry;
import riskyken.armourersWorkshop.common.skin.cubes.ICube;
import riskyken.armourersWorkshop.common.skin.data.serialize.LegacyCubeHelper;

public class SkinCubeData {
    private byte[] cubeId;
    private byte[] cubeLocX;
    private byte[] cubeLocY;
    private byte[] cubeLocZ;
    private byte[][] cubeColourR;
    private byte[][] cubeColourG;
    private byte[][] cubeColourB;
    private byte[][] cubePaintType;
    @SideOnly(value=Side.CLIENT)
    private BitSet[] faceFlags;

    @SideOnly(value=Side.CLIENT)
    public void setFaceFlags(int index, BitSet faceFlags) {
        this.faceFlags[index] = faceFlags;
    }

    @SideOnly(value=Side.CLIENT)
    public BitSet getFaceFlags(int index) {
        return this.faceFlags[index];
    }

    public void setupFaceFlags() {
        this.faceFlags = new BitSet[this.getCubeCount()];
        for (int i = 0; i < this.getCubeCount(); ++i) {
            this.faceFlags[i] = new BitSet(6);
        }
    }

    public void setCubeCount(int count) {
        this.cubeId = new byte[count];
        this.cubeLocX = new byte[count];
        this.cubeLocY = new byte[count];
        this.cubeLocZ = new byte[count];
        this.cubeColourR = new byte[count][6];
        this.cubeColourG = new byte[count][6];
        this.cubeColourB = new byte[count][6];
        this.cubePaintType = new byte[count][6];
    }

    public int getCubeCount() {
        return this.cubeId.length;
    }

    public void setCubeId(int index, byte id) {
        this.cubeId[index] = id;
    }

    public byte getCubeId(int index) {
        return this.cubeId[index];
    }

    public ICube getCube(int index) {
        return CubeRegistry.INSTANCE.getCubeFormId(this.cubeId[index]);
    }

    public void setCubeColour(int index, int side, byte r, byte g, byte b) {
        this.cubeColourR[index][side] = r;
        this.cubeColourG[index][side] = g;
        this.cubeColourB[index][side] = b;
    }

    public byte[] getCubeColour(int index, int side) {
        return new byte[]{this.cubeColourR[index][side], this.cubeColourG[index][side], this.cubeColourB[index][side]};
    }

    public byte[] getCubeColourR(int index) {
        return new byte[]{this.cubeColourR[index][0], this.cubeColourR[index][1], this.cubeColourR[index][2], this.cubeColourR[index][3], this.cubeColourR[index][4], this.cubeColourR[index][5]};
    }

    public byte[] getCubeColourG(int index) {
        return new byte[]{this.cubeColourG[index][0], this.cubeColourG[index][1], this.cubeColourG[index][2], this.cubeColourG[index][3], this.cubeColourG[index][4], this.cubeColourG[index][5]};
    }

    public byte[] getCubeColourB(int index) {
        return new byte[]{this.cubeColourB[index][0], this.cubeColourB[index][1], this.cubeColourB[index][2], this.cubeColourB[index][3], this.cubeColourB[index][4], this.cubeColourB[index][5]};
    }

    public void setCubeLocation(int index, byte x, byte y, byte z) {
        this.cubeLocX[index] = x;
        this.cubeLocY[index] = y;
        this.cubeLocZ[index] = z;
    }

    public byte[] getCubeLocation(int index) {
        return new byte[]{this.cubeLocX[index], this.cubeLocY[index], this.cubeLocZ[index]};
    }

    public void setCubePaintType(int index, int side, byte paintType) {
        this.cubePaintType[index][side] = paintType;
    }

    public byte getCubePaintType(int index, int side) {
        return this.cubePaintType[index][side];
    }

    public byte[] getCubePaintType(int index) {
        return new byte[]{this.cubePaintType[index][0], this.cubePaintType[index][1], this.cubePaintType[index][2], this.cubePaintType[index][3], this.cubePaintType[index][4], this.cubePaintType[index][5]};
    }

    public void writeToStream(DataOutputStream stream) throws IOException {
        stream.writeInt(this.cubeId.length);
        for (int i = 0; i < this.getCubeCount(); ++i) {
            stream.writeByte(this.cubeId[i]);
            stream.writeByte(this.cubeLocX[i]);
            stream.writeByte(this.cubeLocY[i]);
            stream.writeByte(this.cubeLocZ[i]);
            for (int side = 0; side < 6; ++side) {
                stream.writeByte(this.cubeColourR[i][side]);
                stream.writeByte(this.cubeColourG[i][side]);
                stream.writeByte(this.cubeColourB[i][side]);
                stream.writeByte(this.cubePaintType[i][side]);
            }
        }
    }

    public void readFromStream(DataInputStream stream, int version, ISkinPartType skinPart) throws IOException, InvalidCubeTypeException {
        int size = stream.readInt();
        this.setCubeCount(size);
        for (int i = 0; i < this.getCubeCount(); ++i) {
            int side;
            if (version < 10) {
                LegacyCubeHelper.loadLegacyCubeData(this, i, stream, version, skinPart);
                for (side = 0; side < 6; ++side) {
                    this.cubePaintType[i][side] = -1;
                }
            } else {
                this.cubeId[i] = stream.readByte();
                this.cubeLocX[i] = stream.readByte();
                this.cubeLocY[i] = stream.readByte();
                this.cubeLocZ[i] = stream.readByte();
                for (side = 0; side < 6; ++side) {
                    this.cubeColourR[i][side] = stream.readByte();
                    this.cubeColourG[i][side] = stream.readByte();
                    this.cubeColourB[i][side] = stream.readByte();
                    this.cubePaintType[i][side] = stream.readByte();
                }
            }
            if (version >= 11) continue;
            for (side = 0; side < 6; ++side) {
                this.cubePaintType[i][side] = -1;
            }
        }
    }

    public String toString() {
        return "SkinCubeData [cubeId=" + Arrays.toString(this.cubeId) + ", cubeLocX=" + Arrays.toString(this.cubeLocX) + ", cubeLocY=" + Arrays.toString(this.cubeLocY) + ", cubeLocZ=" + Arrays.toString(this.cubeLocZ) + ", cubeColourR=" + Arrays.deepToString((Object[])this.cubeColourR) + ", cubeColourG=" + Arrays.deepToString((Object[])this.cubeColourG) + ", cubeColourB=" + Arrays.deepToString((Object[])this.cubeColourB) + ", cubePaintType=" + Arrays.deepToString((Object[])this.cubePaintType) + "]";
    }
}

