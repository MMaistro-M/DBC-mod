/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api;

import net.minecraft.util.math.BlockPos;

public interface IPos {
    public int getX();

    public int getY();

    public int getZ();

    public double getXD();

    public double getYD();

    public double getZD();

    public IPos up();

    public IPos up(double var1);

    public IPos down();

    public IPos down(double var1);

    public IPos north();

    public IPos north(double var1);

    public IPos east();

    public IPos east(double var1);

    public IPos south();

    public IPos south(double var1);

    public IPos west();

    public IPos west(double var1);

    public IPos add(double var1, double var3, double var5);

    public IPos add(IPos var1);

    public IPos subtract(double var1, double var3, double var5);

    public IPos subtract(IPos var1);

    public IPos normalize();

    public double[] normalizeDouble();

    public IPos offset(int var1);

    public IPos offset(int var1, double var2);

    public IPos crossProduct(double var1, double var3, double var5);

    public IPos crossProduct(IPos var1);

    public IPos divide(double var1);

    public long toLong();

    public IPos fromLong(long var1);

    public double distanceTo(IPos var1);

    public double distanceTo(double var1, double var3, double var5);

    public BlockPos getMCPos();
}

