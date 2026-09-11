/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.skin.data.serialize;

import java.io.DataInputStream;
import java.io.IOException;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.common.exception.InvalidCubeTypeException;
import riskyken.armourersWorkshop.common.skin.data.SkinCubeData;

public final class LegacyCubeHelper {
    public static void loadLegacyCubeData(SkinCubeData cubeData, int index, DataInputStream input, int version, ISkinPartType skinPart) throws IOException, InvalidCubeTypeException {
        if (version < 3) {
            LegacyCubeHelper.loadlegacyCube(cubeData, index, input, version, skinPart);
        } else {
            int i;
            byte id = input.readByte();
            byte x = input.readByte();
            byte y = input.readByte();
            byte z = input.readByte();
            byte[] r = new byte[6];
            byte[] g = new byte[6];
            byte[] b = new byte[6];
            if (version < 7) {
                int colour = input.readInt();
                for (int i2 = 0; i2 < 6; ++i2) {
                    r[i2] = (byte)(colour >> 16 & 0xFF);
                    g[i2] = (byte)(colour >> 8 & 0xFF);
                    b[i2] = (byte)(colour & 0xFF);
                }
            } else {
                for (i = 0; i < 6; ++i) {
                    r[i] = input.readByte();
                    g[i] = input.readByte();
                    b[i] = input.readByte();
                }
            }
            cubeData.setCubeId(index, id);
            cubeData.setCubeLocation(index, x, y, z);
            for (i = 0; i < 6; ++i) {
                cubeData.setCubeColour(index, i, r[i], g[i], b[i]);
            }
        }
    }

    public static void loadlegacyCube(SkinCubeData cubeData, int index, DataInputStream stream, int version, ISkinPartType skinPart) throws IOException, InvalidCubeTypeException {
        byte x = stream.readByte();
        byte y = stream.readByte();
        byte z = stream.readByte();
        int colour = stream.readInt();
        byte blockType = stream.readByte();
        if (version < 2) {
            String partName = skinPart.getRegistryName();
            if (partName.equals("armourers:sword.base")) {
                y = (byte)(y - 1);
            } else if (partName.equals("armourers:legs.skirt")) {
                y = (byte)(y - 1);
            } else if (partName.equals("armourers:legs.leftLeg")) {
                y = (byte)(y - 1);
            } else if (partName.equals("armourers:legs.rightLeg")) {
                y = (byte)(y - 1);
            } else if (partName.equals("armourers:feet.leftFoot")) {
                y = (byte)(y - 1);
            } else if (partName.equals("armourers:feet.rightFoot")) {
                y = (byte)(y - 1);
            }
        }
        cubeData.setCubeId(index, blockType);
        cubeData.setCubeLocation(index, x, y, z);
        byte r = (byte)(colour >> 16 & 0xFF);
        byte g = (byte)(colour >> 8 & 0xFF);
        byte b = (byte)(colour & 0xFF);
        for (int i = 0; i < 6; ++i) {
            cubeData.setCubeColour(index, i, r, g, b);
        }
    }
}

