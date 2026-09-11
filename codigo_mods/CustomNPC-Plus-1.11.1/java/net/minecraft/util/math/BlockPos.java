/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.AbstractIterator
 *  com.google.common.collect.Lists
 *  javax.annotation.concurrent.Immutable
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.MathHelper
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.util.math;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.concurrent.Immutable;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3i;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Immutable
public class BlockPos
extends Vec3i {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final BlockPos ORIGIN = new BlockPos(0, 0, 0);
    private static final int NUM_X_BITS = 26;
    private static final int NUM_Z_BITS = 26;
    private static final int NUM_Y_BITS = 12;
    private static final int Y_SHIFT = 26;
    private static final int X_SHIFT = 38;
    private static final long X_MASK = 0x3FFFFFFL;
    private static final long Y_MASK = 4095L;
    private static final long Z_MASK = 0x3FFFFFFL;

    public BlockPos(int x, int y, int z) {
        super(x, y, z);
    }

    public BlockPos(double x, double y, double z) {
        super(x, y, z);
    }

    public BlockPos(Entity source) {
        this(source.field_70165_t, source.field_70163_u, source.field_70161_v);
    }

    public BlockPos(Vec3i source) {
        this(source.getXD(), source.getYD(), source.getZD());
    }

    public BlockPos add(double x, double y, double z) {
        return x == 0.0 && y == 0.0 && z == 0.0 ? this : new BlockPos(this.getXD() + x, this.getYD() + y, this.getZD() + z);
    }

    public BlockPos add(int x, int y, int z) {
        return x == 0 && y == 0 && z == 0 ? this : new BlockPos(this.getXD() + (double)x, this.getYD() + (double)y, this.getZD() + (double)z);
    }

    public BlockPos add(Vec3i vec) {
        return vec.getXD() == 0.0 && vec.getYD() == 0.0 && vec.getZD() == 0.0 ? this : new BlockPos(this.getXD() + vec.getXD(), this.getYD() + vec.getYD(), this.getZD() + vec.getZD());
    }

    public BlockPos up() {
        return this.up(1.0);
    }

    public BlockPos up(double n) {
        return this.offset(EnumFacing.UP, n);
    }

    public BlockPos down() {
        return this.down(1.0);
    }

    public BlockPos down(double n) {
        return this.offset(EnumFacing.DOWN, n);
    }

    public BlockPos north() {
        return this.north(1.0);
    }

    public BlockPos north(double n) {
        return this.offset(EnumFacing.NORTH, n);
    }

    public BlockPos south() {
        return this.south(1.0);
    }

    public BlockPos south(double n) {
        return this.offset(EnumFacing.SOUTH, n);
    }

    public BlockPos west() {
        return this.west(1.0);
    }

    public BlockPos west(double n) {
        return this.offset(EnumFacing.WEST, n);
    }

    public BlockPos east() {
        return this.east(1.0);
    }

    public BlockPos east(double n) {
        return this.offset(EnumFacing.EAST, n);
    }

    public BlockPos offset(EnumFacing facing) {
        return this.offset(facing, 1.0);
    }

    public BlockPos offset(EnumFacing facing, double n) {
        return n == 0.0 ? this : new BlockPos(this.getXD() + (double)facing.func_82601_c() * n, this.getYD() + (double)facing.func_96559_d() * n, this.getZD() + (double)facing.func_82599_e() * n);
    }

    @Override
    public BlockPos crossProduct(Vec3i vec) {
        return new BlockPos(this.getYD() * vec.getZD() - this.getZD() * vec.getYD(), this.getZD() * vec.getXD() - this.getXD() * vec.getZD(), this.getXD() * vec.getYD() - this.getYD() * vec.getXD());
    }

    public long toLong() {
        return ((long)this.getX() & 0x3FFFFFFL) << 38 | ((long)this.getY() & 0xFFFL) << 26 | ((long)this.getZ() & 0x3FFFFFFL) << 0;
    }

    public static BlockPos fromLong(long serialized) {
        int i = (int)(serialized << 0 >> 38);
        int j = (int)(serialized << 26 >> 52);
        int k = (int)(serialized << 38 >> 38);
        return new BlockPos(i, j, k);
    }

    public static Iterable<BlockPos> getAllInBox(BlockPos from, BlockPos to) {
        final BlockPos blockpos = new BlockPos(Math.min(from.getX(), to.getX()), Math.min(from.getY(), to.getY()), Math.min(from.getZ(), to.getZ()));
        final BlockPos blockpos1 = new BlockPos(Math.max(from.getX(), to.getX()), Math.max(from.getY(), to.getY()), Math.max(from.getZ(), to.getZ()));
        return new Iterable<BlockPos>(){

            @Override
            public Iterator<BlockPos> iterator() {
                return new AbstractIterator<BlockPos>(){
                    private BlockPos lastReturned = null;

                    protected BlockPos computeNext() {
                        if (this.lastReturned == null) {
                            this.lastReturned = blockpos;
                            return this.lastReturned;
                        }
                        if (this.lastReturned.equals(blockpos1)) {
                            return (BlockPos)this.endOfData();
                        }
                        int i = this.lastReturned.getX();
                        int j = this.lastReturned.getY();
                        int k = this.lastReturned.getZ();
                        if (i < blockpos1.getX()) {
                            ++i;
                        } else if (j < blockpos1.getY()) {
                            i = blockpos.getX();
                            ++j;
                        } else if (k < blockpos1.getZ()) {
                            i = blockpos.getX();
                            j = blockpos.getY();
                            ++k;
                        }
                        this.lastReturned = new BlockPos(i, j, k);
                        return this.lastReturned;
                    }
                };
            }
        };
    }

    public static final class PooledMutableBlockPos
    extends MutableBlockPos {
        private boolean released;
        private static final List<PooledMutableBlockPos> POOL = Lists.newArrayList();

        private PooledMutableBlockPos(int xIn, int yIn, int zIn) {
            super(xIn, yIn, zIn);
        }

        public static PooledMutableBlockPos retain() {
            return PooledMutableBlockPos.retain(0, 0, 0);
        }

        public static PooledMutableBlockPos retain(double xIn, double yIn, double zIn) {
            return PooledMutableBlockPos.retain(MathHelper.func_76128_c((double)xIn), MathHelper.func_76128_c((double)yIn), MathHelper.func_76128_c((double)zIn));
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public static PooledMutableBlockPos retain(int xIn, int yIn, int zIn) {
            List<PooledMutableBlockPos> list = POOL;
            synchronized (list) {
                PooledMutableBlockPos blockpos$pooledmutableblockpos;
                if (!POOL.isEmpty() && (blockpos$pooledmutableblockpos = POOL.remove(POOL.size() - 1)) != null && blockpos$pooledmutableblockpos.released) {
                    blockpos$pooledmutableblockpos.released = false;
                    blockpos$pooledmutableblockpos.set(xIn, yIn, zIn);
                    return blockpos$pooledmutableblockpos;
                }
            }
            return new PooledMutableBlockPos(xIn, yIn, zIn);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public void release() {
            List<PooledMutableBlockPos> list = POOL;
            synchronized (list) {
                if (POOL.size() < 100) {
                    POOL.add(this);
                }
                this.released = true;
            }
        }

        public PooledMutableBlockPos set(int xIn, int yIn, int zIn) {
            if (this.released) {
                LOGGER.error("PooledMutableBlockPosition modified after it was released.", new Throwable());
                this.released = false;
            }
            return (PooledMutableBlockPos)super.setPos(xIn, yIn, zIn);
        }

        public PooledMutableBlockPos set(double xIn, double yIn, double zIn) {
            return (PooledMutableBlockPos)super.setPos(xIn, yIn, zIn);
        }

        public PooledMutableBlockPos set(Vec3i vec) {
            return (PooledMutableBlockPos)super.setPos(vec);
        }

        public PooledMutableBlockPos offsetMutable(EnumFacing facing) {
            return (PooledMutableBlockPos)super.move(facing);
        }

        public PooledMutableBlockPos movePos(EnumFacing p_189538_1_, int p_189538_2_) {
            return (PooledMutableBlockPos)super.move(p_189538_1_, p_189538_2_);
        }
    }

    public static class MutableBlockPos
    extends BlockPos {
        protected int x;
        protected int y;
        protected int z;

        public MutableBlockPos() {
            this(0, 0, 0);
        }

        public MutableBlockPos(BlockPos pos) {
            this(pos.getX(), pos.getY(), pos.getZ());
        }

        public MutableBlockPos(int x_, int y_, int z_) {
            super(0, 0, 0);
            this.x = x_;
            this.y = y_;
            this.z = z_;
        }

        @Override
        public int getX() {
            return this.x;
        }

        @Override
        public int getY() {
            return this.y;
        }

        @Override
        public int getZ() {
            return this.z;
        }

        public MutableBlockPos setPos(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }

        public MutableBlockPos setPos(double p_189532_1_, double p_189532_3_, double p_189532_5_) {
            return this.setPos(MathHelper.func_76128_c((double)p_189532_1_), MathHelper.func_76128_c((double)p_189532_3_), MathHelper.func_76128_c((double)p_189532_5_));
        }

        public MutableBlockPos setPos(Vec3i p_189533_1_) {
            return this.setPos(p_189533_1_.getX(), p_189533_1_.getY(), p_189533_1_.getZ());
        }

        public MutableBlockPos move(EnumFacing p_189536_1_) {
            return this.move(p_189536_1_, 1);
        }

        public MutableBlockPos move(EnumFacing p_189534_1_, int p_189534_2_) {
            return this.setPos(this.x + p_189534_1_.func_82601_c() * p_189534_2_, this.y + p_189534_1_.func_96559_d() * p_189534_2_, this.z + p_189534_1_.func_82599_e() * p_189534_2_);
        }

        public void setY(int yIn) {
            this.y = yIn;
        }

        public BlockPos toImmutable() {
            return new BlockPos(this);
        }
    }
}

