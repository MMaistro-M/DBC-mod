/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package riskyken.armourersWorkshop.api.common.skin.data;

import io.netty.buffer.ByteBuf;

public interface ISkinDye {
    public byte[] getDyeColour(int var1);

    public String getDyeName(int var1);

    public boolean haveDyeInSlot(int var1);

    public boolean hasName(int var1);

    public void addDye(byte[] var1, String var2);

    public void addDye(byte[] var1);

    public void addDye(int var1, byte[] var2, String var3);

    public void addDye(int var1, byte[] var2);

    public void removeDye(int var1);

    public int getNumberOfDyes();

    public void writeToBuf(ByteBuf var1);

    public void readFromBuf(ByteBuf var1);
}

