/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.skin.cubes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CubeMarkerData {
    public byte x;
    public byte y;
    public byte z;
    public byte meta;

    public CubeMarkerData(byte x, byte y, byte z, byte meta) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.meta = meta;
    }

    public CubeMarkerData(DataInputStream stream, int version) throws IOException {
        this.readFromStream(stream, version);
    }

    public void writeToStream(DataOutputStream stream) throws IOException {
        stream.writeByte(this.x);
        stream.writeByte(this.y);
        stream.writeByte(this.z);
        stream.writeByte(this.meta);
    }

    private void readFromStream(DataInputStream stream, int version) throws IOException {
        this.x = stream.readByte();
        this.y = stream.readByte();
        this.z = stream.readByte();
        this.meta = stream.readByte();
    }

    public String toString() {
        return "CubeMarkerData [x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", meta=" + this.meta + "]";
    }
}

