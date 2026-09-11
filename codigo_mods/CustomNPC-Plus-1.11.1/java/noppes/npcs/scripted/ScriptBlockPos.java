/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.EnumFacing
 */
package noppes.npcs.scripted;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraft.util.math.BlockPos;
import noppes.npcs.api.IPos;
import noppes.npcs.scripted.NpcAPI;

public class ScriptBlockPos
implements IPos {
    public BlockPos blockPos;

    public ScriptBlockPos(BlockPos pos) {
        this.blockPos = pos;
    }

    @Override
    public int getX() {
        return this.blockPos.getX();
    }

    @Override
    public int getY() {
        return this.blockPos.getY();
    }

    @Override
    public int getZ() {
        return this.blockPos.getZ();
    }

    @Override
    public double getXD() {
        return this.blockPos.getXD();
    }

    @Override
    public double getYD() {
        return this.blockPos.getYD();
    }

    @Override
    public double getZD() {
        return this.blockPos.getZD();
    }

    @Override
    public IPos up() {
        return NpcAPI.Instance().getIPos(this.blockPos.up());
    }

    @Override
    public IPos up(double n) {
        return NpcAPI.Instance().getIPos(this.blockPos.up(n));
    }

    @Override
    public IPos down() {
        return NpcAPI.Instance().getIPos(this.blockPos.down());
    }

    @Override
    public IPos down(double n) {
        return NpcAPI.Instance().getIPos(this.blockPos.down(n));
    }

    @Override
    public IPos north() {
        return NpcAPI.Instance().getIPos(this.blockPos.north());
    }

    @Override
    public IPos north(double n) {
        return NpcAPI.Instance().getIPos(this.blockPos.north(n));
    }

    @Override
    public IPos east() {
        return NpcAPI.Instance().getIPos(this.blockPos.east());
    }

    @Override
    public IPos east(double n) {
        return NpcAPI.Instance().getIPos(this.blockPos.east(n));
    }

    @Override
    public IPos south() {
        return NpcAPI.Instance().getIPos(this.blockPos.south());
    }

    @Override
    public IPos south(double n) {
        return NpcAPI.Instance().getIPos(this.blockPos.south(n));
    }

    @Override
    public IPos west() {
        return NpcAPI.Instance().getIPos(this.blockPos.west());
    }

    @Override
    public IPos west(double n) {
        return NpcAPI.Instance().getIPos(this.blockPos.west(n));
    }

    @Override
    public IPos add(double x, double y, double z) {
        return NpcAPI.Instance().getIPos(this.blockPos.add(x, y, z));
    }

    @Override
    public IPos add(IPos pos) {
        return NpcAPI.Instance().getIPos(this.blockPos.add(pos.getXD(), pos.getYD(), pos.getZD()));
    }

    @Override
    public IPos subtract(double x, double y, double z) {
        return NpcAPI.Instance().getIPos(this.blockPos.add(-x, -y, -z));
    }

    @Override
    public IPos subtract(IPos pos) {
        return NpcAPI.Instance().getIPos(this.blockPos.add(-pos.getXD(), -pos.getYD(), -pos.getZD()));
    }

    @Override
    public IPos offset(int direction) {
        return NpcAPI.Instance().getIPos(this.blockPos.offset(EnumFacing.values()[direction]));
    }

    @Override
    public IPos offset(int direction, double n) {
        return NpcAPI.Instance().getIPos(this.blockPos.offset(EnumFacing.values()[direction], n));
    }

    @Override
    public IPos crossProduct(double x, double y, double z) {
        return this.crossProduct(NpcAPI.Instance().getIPos(x, y, z));
    }

    @Override
    public IPos crossProduct(IPos pos) {
        return NpcAPI.Instance().getIPos(this.blockPos.crossProduct(new Vec3i(-pos.getXD(), -pos.getYD(), -pos.getZD())));
    }

    @Override
    public IPos divide(double scalar) {
        return NpcAPI.Instance().getIPos(this.getXD() / scalar, this.getYD() / scalar, this.getZD() / scalar);
    }

    @Override
    public long toLong() {
        return this.blockPos.toLong();
    }

    @Override
    public IPos fromLong(long serialized) {
        this.blockPos = BlockPos.fromLong(serialized);
        return this;
    }

    @Override
    public IPos normalize() {
        double d = Math.sqrt(this.blockPos.getXD() * this.blockPos.getXD() + this.blockPos.getYD() * this.blockPos.getYD() + this.blockPos.getZD() * this.blockPos.getZD());
        return NpcAPI.Instance().getIPos(this.getXD() / d, this.getYD() / d, this.getZD() / d);
    }

    @Override
    public double[] normalizeDouble() {
        double d = Math.sqrt(this.blockPos.getXD() * this.blockPos.getXD() + this.blockPos.getYD() * this.blockPos.getYD() + this.blockPos.getZD() * this.blockPos.getZD());
        return new double[]{this.getXD() / d, this.getYD() / d, this.getZD() / d};
    }

    @Override
    public double distanceTo(IPos pos) {
        double d0 = this.getXD() - pos.getXD();
        double d1 = this.getYD() - pos.getYD();
        double d2 = this.getZD() - pos.getZD();
        return Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
    }

    @Override
    public double distanceTo(double x, double y, double z) {
        double d0 = this.getXD() - x;
        double d1 = this.getYD() - y;
        double d2 = this.getZD() - z;
        return Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
    }

    @Override
    public BlockPos getMCPos() {
        return this.blockPos;
    }

    public String toString() {
        double xd = Math.floor(this.getXD() * 1000.0) / 1000.0;
        double yd = Math.floor(this.getYD() * 1000.0) / 1000.0;
        double zd = Math.floor(this.getZD() * 1000.0) / 1000.0;
        return "(" + xd + ", " + yd + ", " + zd + ")";
    }

    public boolean equals(Object object) {
        return object instanceof IPos && ((IPos)object).toLong() == this.toLong();
    }
}

