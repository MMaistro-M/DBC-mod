/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.init.Blocks
 *  net.minecraft.pathfinding.PathEntity
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.pathfinding.PathPoint
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.ChunkCache
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package noppes.npcs.ai.pathfinder;

import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import noppes.npcs.ai.pathfinder.FlyPathFinder;
import noppes.npcs.ai.pathfinder.NPCPath;
import noppes.npcs.ai.pathfinder.NPCPathPoint;
import noppes.npcs.entity.EntityNPCInterface;

public class PathNavigateFlying
extends PathNavigate {
    private EntityNPCInterface theEntity;
    private World worldObj;
    protected NPCPath field_75514_c;
    private double speed;
    private IAttributeInstance pathSearchRange;
    protected float maxDistanceToWaypoint = 0.5f;
    private boolean noSunPathfind;
    private int totalTicks;
    private int ticksAtLastPos;
    private Vec3 lastPosCheck = Vec3.func_72443_a((double)0.0, (double)0.0, (double)0.0);
    private Vec3 timeoutCachedNode = Vec3.func_72443_a((double)0.0, (double)0.0, (double)0.0);
    private long timeoutTimer;
    private long lastTimeoutCheck;
    private double timeoutLimit;
    private boolean canPassOpenWoodenDoors = true;
    private boolean canPassClosedWoodenDoors;
    private boolean avoidsWater;
    private boolean canSwim;
    public BlockPos targetPos;
    protected boolean tryUpdatePath;
    protected long lastTimeUpdated;
    public FlyPathFinder pathFinder;

    public PathNavigateFlying(EntityLiving entityIN, World worldIn) {
        super(entityIN, worldIn);
        this.theEntity = (EntityNPCInterface)entityIN;
        this.worldObj = worldIn;
        this.pathSearchRange = entityIN.func_110148_a(SharedMonsterAttributes.field_111265_b);
    }

    public void updatePath() {
        if (this.worldObj.func_82737_E() - this.lastTimeUpdated > 20L) {
            if (this.targetPos != null) {
                this.field_75514_c = null;
                this.field_75514_c = this.getPathToXYZ(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ());
                this.lastTimeUpdated = this.worldObj.func_82737_E();
                this.tryUpdatePath = false;
            }
        } else {
            this.tryUpdatePath = true;
        }
    }

    public void func_75491_a(boolean p_75491_1_) {
        this.avoidsWater = p_75491_1_;
    }

    public boolean func_75486_a() {
        return this.avoidsWater;
    }

    public void func_75498_b(boolean p_75498_1_) {
        this.canPassClosedWoodenDoors = p_75498_1_;
    }

    public void func_75490_c(boolean p_75490_1_) {
        this.canPassOpenWoodenDoors = p_75490_1_;
    }

    public boolean func_75507_c() {
        return this.canPassClosedWoodenDoors;
    }

    public void func_75504_d(boolean p_75504_1_) {
        this.noSunPathfind = p_75504_1_;
    }

    public void func_75489_a(double p_75489_1_) {
        this.speed = p_75489_1_;
    }

    public void func_75495_e(boolean p_75495_1_) {
        this.canSwim = p_75495_1_;
    }

    public float func_111269_d() {
        return (float)this.pathSearchRange.func_111126_e();
    }

    public NPCPath getPathToXYZ(double x, double y, double z) {
        BlockPos pos = new BlockPos(x, y, z);
        if (!this.canNavigate()) {
            return null;
        }
        if (this.field_75514_c != null && !this.field_75514_c.func_75879_b() && pos.equals(this.targetPos)) {
            return this.field_75514_c;
        }
        this.targetPos = pos;
        return this.getEntityPathToXYZ((Entity)this.theEntity, MathHelper.func_76128_c((double)x), (int)y, MathHelper.func_76128_c((double)z), this.func_111269_d(), this.canPassOpenWoodenDoors, this.canPassClosedWoodenDoors, this.avoidsWater, this.canSwim);
    }

    public boolean func_75492_a(double p_75492_1_, double p_75492_3_, double p_75492_5_, double p_75492_7_) {
        NPCPath pathentity = this.getPathToXYZ(MathHelper.func_76128_c((double)p_75492_1_), (int)p_75492_3_, MathHelper.func_76128_c((double)p_75492_5_));
        return this.func_75484_a(pathentity, p_75492_7_);
    }

    public NPCPath getPathToEntityLiving(Entity entity) {
        if (!this.canNavigate()) {
            return null;
        }
        BlockPos blockpos = new BlockPos(entity);
        if (this.field_75514_c != null && !this.field_75514_c.func_75879_b() && blockpos.equals(this.targetPos)) {
            return this.field_75514_c;
        }
        this.targetPos = blockpos;
        return this.getPathEntityToEntity((Entity)this.theEntity, entity, this.func_111269_d(), this.canPassOpenWoodenDoors, this.canPassClosedWoodenDoors, this.avoidsWater, this.canSwim);
    }

    public boolean func_75497_a(Entity p_75497_1_, double p_75497_2_) {
        NPCPath pathentity = this.getPathToEntityLiving(p_75497_1_);
        return pathentity != null && this.func_75484_a(pathentity, p_75497_2_);
    }

    public boolean func_75484_a(@Nullable PathEntity pathEntity, double speed) {
        NPCPath NPCPath2 = (NPCPath)pathEntity;
        if (NPCPath2 == null) {
            this.field_75514_c = null;
            return false;
        }
        if (!NPCPath2.func_75876_a(this.field_75514_c)) {
            this.field_75514_c = NPCPath2;
        }
        if (this.noSunPathfind) {
            this.removeSunnyPath();
        }
        if (this.field_75514_c.func_75874_d() == 0) {
            return false;
        }
        this.speed = speed;
        Vec3 vec3 = this.getEntityPosition();
        this.ticksAtLastPos = this.totalTicks;
        this.lastPosCheck = vec3;
        return true;
    }

    public PathEntity func_75505_d() {
        return this.field_75514_c;
    }

    private boolean isInLiquid() {
        return this.theEntity.func_70090_H() || this.theEntity.func_70058_J();
    }

    private boolean canNavigate() {
        return !this.isInLiquid() || this.canSwim && this.isInLiquid();
    }

    public void func_75501_e() {
        ++this.totalTicks;
        if (this.tryUpdatePath) {
            this.updatePath();
        }
        if (!this.func_75500_f()) {
            Vec3 vec3;
            if (this.canNavigate()) {
                if (this.theEntity.display.modelSize >= 5) {
                    this.pathFollowBig();
                } else {
                    this.pathFollowSmall();
                }
            } else if (this.field_75514_c != null && this.field_75514_c.func_75873_e() < this.field_75514_c.func_75874_d() && !this.func_75500_f()) {
                Vec3 vec3d = this.field_75514_c.func_75881_a((Entity)this.theEntity, this.field_75514_c.func_75873_e());
                Vec3 vec32 = this.field_75514_c.func_75878_a((Entity)this.theEntity);
                if (MathHelper.func_76128_c((double)this.theEntity.field_70165_t) == MathHelper.func_76128_c((double)vec3d.field_72450_a) && MathHelper.func_76128_c((double)this.theEntity.field_70163_u) == MathHelper.func_76128_c((double)vec3d.field_72448_b) && MathHelper.func_76128_c((double)this.theEntity.field_70161_v) == MathHelper.func_76128_c((double)vec3d.field_72449_c) && vec32 != null) {
                    this.field_75514_c.func_75872_c(this.field_75514_c.func_75873_e() + 1);
                    this.theEntity.func_70605_aq().func_75642_a(vec32.field_72450_a, vec32.field_72448_b, vec32.field_72449_c, this.speed);
                }
            }
            if (!this.func_75500_f() && (vec3 = this.field_75514_c.func_75878_a((Entity)this.theEntity)) != null) {
                this.theEntity.func_70605_aq().func_75642_a(vec3.field_72450_a, vec3.field_72448_b, vec3.field_72449_c, this.speed);
            }
        }
    }

    private void pathFollowBig() {
        int k;
        Vec3 vec3 = this.getEntityPosition();
        int i = this.field_75514_c.func_75874_d();
        float f = this.theEntity.field_70130_N * this.theEntity.field_70130_N;
        for (k = this.field_75514_c.func_75873_e(); k < i; ++k) {
            if (!(vec3.func_72436_e(this.field_75514_c.func_75881_a((Entity)this.theEntity, k)) < (double)f)) continue;
            this.field_75514_c.func_75872_c(k + 1);
        }
        k = MathHelper.func_76128_c((double)(this.theEntity.field_70130_N + 1.0f));
        int l = MathHelper.func_76128_c((double)(this.theEntity.field_70131_O + 1.0f));
        this.trimPath(vec3, i, k, l);
        this.checkForStuck(vec3);
    }

    private void pathFollowSmall() {
        Vec3 vec3 = this.getEntityPosition();
        int i = this.field_75514_c.func_75874_d();
        for (int j = this.field_75514_c.func_75873_e(); j < this.field_75514_c.func_75874_d(); ++j) {
            if ((double)this.field_75514_c.getPathPointFromIndex((int)j).field_75837_b == Math.floor(vec3.field_72448_b)) continue;
            i = j;
            break;
        }
        this.maxDistanceToWaypoint = this.theEntity.field_70130_N > 0.75f ? this.theEntity.field_70130_N / 2.0f : 0.75f - this.theEntity.field_70130_N / 2.0f;
        Vec3 vec3d1 = this.field_75514_c.getCurrentPos();
        if (MathHelper.func_76135_e((float)((float)(this.theEntity.field_70165_t - (vec3d1.field_72450_a + 0.5)))) < this.maxDistanceToWaypoint && MathHelper.func_76135_e((float)((float)(this.theEntity.field_70161_v - (vec3d1.field_72449_c + 0.5)))) < this.maxDistanceToWaypoint && Math.abs(this.theEntity.field_70163_u - vec3d1.field_72448_b) < 1.0) {
            this.field_75514_c.func_75872_c(this.field_75514_c.func_75873_e() + 1);
        }
        int k = MathHelper.func_76143_f((double)this.theEntity.field_70130_N);
        int l = MathHelper.func_76143_f((double)(this.theEntity.field_70131_O + 1.0f));
        this.trimPath(vec3, i, k, l);
        this.checkForStuck(vec3);
    }

    public void trimPath(Vec3 position, int pathLength, int width, int height) {
        for (int j1 = pathLength - 1; j1 >= this.field_75514_c.func_75873_e(); --j1) {
            Vec3 pathPoint = this.field_75514_c.func_75881_a((Entity)this.theEntity, j1);
            if (position.func_72438_d(pathPoint) > 6.0 || !this.isDirectPathBetweenPoints(position, pathPoint, width, height)) continue;
            this.field_75514_c.func_75872_c(j1);
            break;
        }
    }

    public boolean func_75500_f() {
        return this.field_75514_c == null || this.field_75514_c.func_75879_b();
    }

    public void func_75499_g() {
        this.field_75514_c = null;
    }

    private Vec3 getEntityPosition() {
        return Vec3.func_72443_a((double)this.theEntity.field_70165_t, (double)this.getPathableYPos(), (double)this.theEntity.field_70161_v);
    }

    private double getPathableYPos() {
        return this.theEntity.field_70121_D.field_72338_b + 0.05;
    }

    private void removeSunnyPath() {
        if (!this.worldObj.func_72937_j(MathHelper.func_76128_c((double)this.theEntity.field_70165_t), (int)(this.theEntity.field_70121_D.field_72338_b + 0.5), MathHelper.func_76128_c((double)this.theEntity.field_70161_v))) {
            for (int i = 0; i < this.field_75514_c.func_75874_d(); ++i) {
                NPCPathPoint pathpoint = this.field_75514_c.getPathPointFromIndex(i);
                if (!this.worldObj.func_72937_j(((PathPoint)pathpoint).field_75839_a, ((PathPoint)pathpoint).field_75837_b, ((PathPoint)pathpoint).field_75838_c)) continue;
                this.field_75514_c.func_75871_b(i - 1);
                return;
            }
        }
    }

    private boolean isDirectPathBetweenPoints(Vec3 startPos, Vec3 endPos, int width, int height) {
        Vec3 pos18;
        Vec3 pos17 = Vec3.func_72443_a((double)startPos.field_72450_a, (double)startPos.field_72448_b, (double)startPos.field_72449_c);
        return !this.rayTraceBlocks(pos17, pos18 = Vec3.func_72443_a((double)endPos.field_72450_a, (double)endPos.field_72448_b, (double)endPos.field_72449_c), true, false, false, width, height);
    }

    public boolean rayTraceBlocks(Vec3 p_147447_1_, Vec3 p_147447_2_, boolean p_147447_3_, boolean p_147447_4_, boolean p_147447_5_, int width, int height) {
        if (!(Double.isNaN(p_147447_1_.field_72450_a) || Double.isNaN(p_147447_1_.field_72448_b) || Double.isNaN(p_147447_1_.field_72449_c))) {
            if (!(Double.isNaN(p_147447_2_.field_72450_a) || Double.isNaN(p_147447_2_.field_72448_b) || Double.isNaN(p_147447_2_.field_72449_c))) {
                MovingObjectPosition movingobjectposition;
                int i = MathHelper.func_76128_c((double)p_147447_2_.field_72450_a);
                int j = MathHelper.func_76128_c((double)p_147447_2_.field_72448_b);
                int k = MathHelper.func_76128_c((double)p_147447_2_.field_72449_c);
                int l = MathHelper.func_76128_c((double)p_147447_1_.field_72450_a);
                int i1 = MathHelper.func_76128_c((double)p_147447_1_.field_72448_b);
                int j1 = MathHelper.func_76128_c((double)p_147447_1_.field_72449_c);
                Block block = this.worldObj.func_147439_a(l, i1, j1);
                int k1 = this.worldObj.func_72805_g(l, i1, j1);
                if ((!p_147447_4_ || block.func_149668_a(this.worldObj, l, i1, j1) != null) && block.func_149678_a(k1, p_147447_3_) && (movingobjectposition = block.func_149731_a(this.worldObj, l, i1, j1, p_147447_1_, p_147447_2_)) != null && movingobjectposition.field_72313_a != MovingObjectPosition.MovingObjectType.MISS) {
                    return true;
                }
                MovingObjectPosition movingobjectposition2 = null;
                k1 = 200;
                while (k1-- >= 0) {
                    int b0;
                    if (Double.isNaN(p_147447_1_.field_72450_a) || Double.isNaN(p_147447_1_.field_72448_b) || Double.isNaN(p_147447_1_.field_72449_c)) {
                        return false;
                    }
                    if (l == i && i1 == j && j1 == k) {
                        if (p_147447_5_) {
                            return movingobjectposition2 != null && movingobjectposition2.field_72313_a != MovingObjectPosition.MovingObjectType.MISS;
                        }
                        return false;
                    }
                    boolean flag6 = true;
                    boolean flag3 = true;
                    boolean flag4 = true;
                    double d0 = 999.0;
                    double d1 = 999.0;
                    double d2 = 999.0;
                    if (i > l) {
                        d0 = (double)l + 1.0;
                    } else if (i < l) {
                        d0 = (double)l + 0.0;
                    } else {
                        flag6 = false;
                    }
                    if (j > i1) {
                        d1 = (double)i1 + 1.0;
                    } else if (j < i1) {
                        d1 = (double)i1 + 0.0;
                    } else {
                        flag3 = false;
                    }
                    if (k > j1) {
                        d2 = (double)j1 + 1.0;
                    } else if (k < j1) {
                        d2 = (double)j1 + 0.0;
                    } else {
                        flag4 = false;
                    }
                    double d3 = 999.0;
                    double d4 = 999.0;
                    double d5 = 999.0;
                    double d6 = p_147447_2_.field_72450_a - p_147447_1_.field_72450_a;
                    double d7 = p_147447_2_.field_72448_b - p_147447_1_.field_72448_b;
                    double d8 = p_147447_2_.field_72449_c - p_147447_1_.field_72449_c;
                    if (flag6) {
                        d3 = (d0 - p_147447_1_.field_72450_a) / d6;
                    }
                    if (flag3) {
                        d4 = (d1 - p_147447_1_.field_72448_b) / d7;
                    }
                    if (flag4) {
                        d5 = (d2 - p_147447_1_.field_72449_c) / d8;
                    }
                    boolean flag5 = false;
                    if (d3 < d4 && d3 < d5) {
                        b0 = i > l ? 4 : 5;
                        p_147447_1_.field_72450_a = d0;
                        p_147447_1_.field_72448_b += d7 * d3;
                        p_147447_1_.field_72449_c += d8 * d3;
                    } else if (d4 < d5) {
                        b0 = j > i1 ? 0 : 1;
                        p_147447_1_.field_72450_a += d6 * d4;
                        p_147447_1_.field_72448_b = d1;
                        p_147447_1_.field_72449_c += d8 * d4;
                    } else {
                        b0 = k > j1 ? 2 : 3;
                        p_147447_1_.field_72450_a += d6 * d5;
                        p_147447_1_.field_72448_b += d7 * d5;
                        p_147447_1_.field_72449_c = d2;
                    }
                    Vec3 vec32 = Vec3.func_72443_a((double)p_147447_1_.field_72450_a, (double)p_147447_1_.field_72448_b, (double)p_147447_1_.field_72449_c);
                    vec32.field_72450_a = MathHelper.func_76128_c((double)p_147447_1_.field_72450_a);
                    l = (int)vec32.field_72450_a;
                    if (b0 == 5) {
                        --l;
                        vec32.field_72450_a += 1.0;
                    }
                    vec32.field_72448_b = MathHelper.func_76128_c((double)p_147447_1_.field_72448_b);
                    i1 = (int)vec32.field_72448_b;
                    if (b0 == 1) {
                        --i1;
                        vec32.field_72448_b += 1.0;
                    }
                    vec32.field_72449_c = MathHelper.func_76128_c((double)p_147447_1_.field_72449_c);
                    j1 = (int)vec32.field_72449_c;
                    if (b0 == 3) {
                        --j1;
                        vec32.field_72449_c += 1.0;
                    }
                    boolean value = false;
                    for (int x = -width; x <= width && !value; ++x) {
                        block2: for (int y = 0; y <= height && !value; ++y) {
                            for (int z = -width; z <= width; ++z) {
                                boolean noCollisionBlock;
                                if (Math.abs(x) == Math.abs(z)) continue;
                                Block block2 = this.worldObj.func_147439_a(l += x, i1 += y, j1 += z);
                                int meta2 = this.worldObj.func_72805_g(l, i1, j1);
                                boolean collideCheck = (!p_147447_4_ || block2.func_149668_a(this.worldObj, l, i1, j1) != null) && block2.func_149678_a(meta2, p_147447_3_);
                                boolean bl = noCollisionBlock = block2 == Blocks.field_150350_a || block2.func_149688_o() == Material.field_151587_i && this.theEntity.func_70045_F() || block2.func_149688_o() == Material.field_151586_h && this.theEntity.stats.drowningType == 2;
                                if (collideCheck && !noCollisionBlock) {
                                    if (!block2.func_149655_b((IBlockAccess)this.worldObj, l, i1, j1)) {
                                        value = true;
                                        l -= x;
                                        i1 -= y;
                                        j1 -= z;
                                        continue block2;
                                    }
                                    MovingObjectPosition movingobjectposition3 = block2.func_149731_a(this.worldObj, l, i1, j1, p_147447_1_, p_147447_2_);
                                    if (movingobjectposition3 != null && movingobjectposition3.field_72313_a != MovingObjectPosition.MovingObjectType.MISS) {
                                        value = true;
                                        l -= x;
                                        i1 -= y;
                                        j1 -= z;
                                        continue block2;
                                    }
                                    movingobjectposition2 = new MovingObjectPosition(l, i1, j1, b0, p_147447_1_, false);
                                }
                                l -= x;
                                i1 -= y;
                                j1 -= z;
                            }
                        }
                    }
                    if (!value) continue;
                    return true;
                }
                if (p_147447_5_) {
                    return movingobjectposition2 != null && movingobjectposition2.field_72313_a != MovingObjectPosition.MovingObjectType.MISS;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    protected void checkForStuck(Vec3 positionVec3) {
        if (this.totalTicks - this.ticksAtLastPos > 100) {
            if (positionVec3.func_72436_e(this.lastPosCheck) < 2.25) {
                this.func_75499_g();
            }
            this.ticksAtLastPos = this.totalTicks;
            this.lastPosCheck = positionVec3;
        }
        if (this.field_75514_c != null && !this.field_75514_c.func_75879_b()) {
            Vec3 vec3d = this.field_75514_c.getCurrentPos();
            if (vec3d.equals(this.timeoutCachedNode)) {
                this.timeoutTimer += System.currentTimeMillis() - this.lastTimeoutCheck;
            } else {
                this.timeoutCachedNode = vec3d;
                double d0 = positionVec3.func_72438_d(this.timeoutCachedNode);
                double d = this.timeoutLimit = this.theEntity.func_70689_ay() > 0.0f ? d0 / (double)this.theEntity.func_70689_ay() * 1000.0 : 0.0;
            }
            if (this.timeoutLimit > 0.0 && (double)this.timeoutTimer > this.timeoutLimit * 3.0) {
                this.timeoutCachedNode = Vec3.func_72443_a((double)0.0, (double)0.0, (double)0.0);
                this.timeoutTimer = 0L;
                this.timeoutLimit = 0.0;
                this.func_75499_g();
            }
            this.lastTimeoutCheck = System.currentTimeMillis();
        }
    }

    public ChunkCache createChunkCache(World world, BlockPos blockFrom, BlockPos blockTo) {
        return new ChunkCache(world, blockFrom.getX(), blockFrom.getY(), blockFrom.getZ(), blockTo.getX(), blockTo.getY(), blockTo.getZ(), 0);
    }

    public NPCPath getPathEntityToEntity(Entity entityFrom, Entity entityTo, float distance, boolean openDoors, boolean breakDoors, boolean pathWater, boolean canSwim) {
        float f = this.func_111269_d();
        this.theEntity.field_70170_p.field_72984_F.func_76320_a("pathfind");
        BlockPos blockpos1 = new BlockPos((Entity)this.theEntity).up();
        int i = (int)(f + 16.0f);
        ChunkCache chunkcache = this.createChunkCache(this.worldObj, blockpos1.add(-i, -i, -i), blockpos1.add(i, i, i));
        this.pathFinder = new FlyPathFinder((IBlockAccess)chunkcache, openDoors, breakDoors, pathWater, canSwim, (Entity)this.theEntity);
        NPCPath pathentity = this.pathFinder.createEntityPathTo(entityFrom, entityTo, distance);
        this.theEntity.field_70170_p.field_72984_F.func_76319_b();
        return pathentity;
    }

    public NPCPath getEntityPathToXYZ(Entity entityFrom, int x, int y, int z, float distance, boolean openDoors, boolean breakDoors, boolean pathWater, boolean canSwim) {
        float f = this.func_111269_d();
        this.theEntity.field_70170_p.field_72984_F.func_76320_a("pathfind");
        BlockPos blockpos1 = new BlockPos((Entity)this.theEntity).up();
        int i = (int)(f + 16.0f);
        ChunkCache chunkcache = this.createChunkCache(this.worldObj, blockpos1.add(-i, -i, -i), blockpos1.add(i, i, i));
        this.pathFinder = new FlyPathFinder((IBlockAccess)chunkcache, openDoors, breakDoors, pathWater, canSwim, (Entity)this.theEntity);
        NPCPath pathentity = this.pathFinder.createEntityPathTo(entityFrom, this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ(), f);
        this.theEntity.field_70170_p.field_72984_F.func_76319_b();
        return pathentity;
    }
}

