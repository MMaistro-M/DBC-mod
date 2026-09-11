/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package riskyken.armourersWorkshop.common.skin.cubes;

import java.awt.Color;
import java.util.Arrays;
import net.minecraft.nbt.NBTTagCompound;
import riskyken.armourersWorkshop.api.common.skin.cubes.ICubeColour;

public class CubeColour
implements ICubeColour {
    private static final String TAG_RED = "r";
    private static final String TAG_GREEN = "g";
    private static final String TAG_BLUE = "b";
    private static final String TAG_TYPE = "t";
    private byte[] r;
    private byte[] g;
    private byte[] b;
    private byte[] t;

    public CubeColour() {
        this.initArray();
    }

    public CubeColour(ICubeColour cubeColour) {
        this.r = (byte[])cubeColour.getRed().clone();
        this.g = (byte[])cubeColour.getGreen().clone();
        this.b = (byte[])cubeColour.getBlue().clone();
        this.t = (byte[])cubeColour.getPaintType().clone();
    }

    public CubeColour(int colour) {
        this.t = new byte[6];
        this.r = new byte[6];
        this.g = new byte[6];
        this.b = new byte[6];
        for (int i = 0; i < 6; ++i) {
            this.t[i] = -1;
            this.r[i] = (byte)(colour >> 16 & 0xFF);
            this.g[i] = (byte)(colour >> 8 & 0xFF);
            this.b[i] = (byte)(colour & 0xFF);
        }
    }

    private void initArray() {
        this.t = new byte[6];
        this.r = new byte[6];
        this.g = new byte[6];
        this.b = new byte[6];
        for (int i = 0; i < 6; ++i) {
            this.t[i] = -1;
            this.r[i] = -1;
            this.g[i] = -1;
            this.b[i] = -1;
        }
    }

    @Override
    public byte getRed(int side) {
        return this.r[side];
    }

    @Override
    public byte getGreen(int side) {
        return this.g[side];
    }

    @Override
    public byte getBlue(int side) {
        return this.b[side];
    }

    @Override
    public byte getPaintType(int side) {
        return this.t[side];
    }

    @Override
    public byte[] getRed() {
        return this.r;
    }

    @Override
    public byte[] getGreen() {
        return this.g;
    }

    @Override
    public byte[] getBlue() {
        return this.b;
    }

    @Override
    public byte[] getPaintType() {
        return this.t;
    }

    @Override
    public void setColour(int colour, int side) {
        this.r[side] = (byte)(colour >> 16 & 0xFF);
        this.g[side] = (byte)(colour >> 8 & 0xFF);
        this.b[side] = (byte)(colour & 0xFF);
    }

    public int getColour(int side) {
        Color c = new Color(this.r[side] & 0xFF, this.g[side] & 0xFF, this.b[side] & 0xFF);
        return c.getRGB();
    }

    @Override
    @Deprecated
    public void setColour(int colour) {
        for (int i = 0; i < 6; ++i) {
            this.r[i] = (byte)(colour >> 16 & 0xFF);
            this.g[i] = (byte)(colour >> 8 & 0xFF);
            this.b[i] = (byte)(colour & 0xFF);
        }
    }

    @Override
    public void setRed(byte red, int side) {
        this.r[side] = red;
    }

    @Override
    public void setGreen(byte green, int side) {
        this.g[side] = green;
    }

    @Override
    public void setBlue(byte blue, int side) {
        this.b[side] = blue;
    }

    @Override
    public void setPaintType(byte type, int side) {
        this.t[side] = type;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        for (int i = 0; i < 6; ++i) {
            this.r[i] = compound.func_74771_c(TAG_RED + i);
            this.g[i] = compound.func_74771_c(TAG_GREEN + i);
            this.b[i] = compound.func_74771_c(TAG_BLUE + i);
            this.t[i] = compound.func_74764_b(TAG_TYPE + i) ? compound.func_74771_c(TAG_TYPE + i) : (byte)-1;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        for (int i = 0; i < 6; ++i) {
            compound.func_74774_a(TAG_RED + i, this.r[i]);
            compound.func_74774_a(TAG_GREEN + i, this.g[i]);
            compound.func_74774_a(TAG_BLUE + i, this.b[i]);
            compound.func_74774_a(TAG_TYPE + i, this.t[i]);
        }
    }

    public String toString() {
        return "CubeColour [r=" + Arrays.toString(this.r) + ", g=" + Arrays.toString(this.g) + ", b=" + Arrays.toString(this.b) + ", t=" + Arrays.toString(this.t) + "]";
    }
}

