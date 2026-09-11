/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDoor
 *  net.minecraft.block.BlockFence
 *  net.minecraft.block.BlockFenceGate
 *  net.minecraft.block.BlockRailBase
 *  net.minecraft.block.BlockWall
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.init.Blocks
 *  net.minecraft.pathfinding.PathFinder
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 */
package noppes.npcs.ai.pathfinder;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.util.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.IntHashMap;
import net.minecraft.world.IBlockAccess;
import noppes.npcs.ai.pathfinder.NPCPath;
import noppes.npcs.ai.pathfinder.NPCPathPoint;
import noppes.npcs.ai.pathfinder.PathHeap;
import noppes.npcs.ai.pathfinder.PathNodeType;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;

public class FlyPathFinder
extends PathFinder {
    private IBlockAccess worldMap;
    private PathHeap path = new PathHeap();
    protected final IntHashMap<NPCPathPoint> field_75867_c = new IntHashMap();
    private NPCPathPoint[] pathOptions = new NPCPathPoint[32];
    private Entity theEntity;
    private final Map<PathNodeType, Float> mapPathPriority = Maps.newEnumMap(PathNodeType.class);
    private boolean isPathingInWater;
    private boolean isMovementBlockAllowed;
    private boolean isWoodenDoorAllowed;
    private boolean canEntityDrown;
    private int drowningType;
    private boolean immuneToFire;

    public FlyPathFinder(IBlockAccess _worldMap, boolean doorsAllowed, boolean closedDoors, boolean canPathWater, boolean canDrown, Entity entityIn) {
        super(_worldMap, doorsAllowed, closedDoors, canPathWater, canDrown);
        this.theEntity = entityIn;
        this.worldMap = _worldMap;
        this.isWoodenDoorAllowed = doorsAllowed;
        this.isMovementBlockAllowed = closedDoors;
        this.isPathingInWater = canPathWater;
        this.canEntityDrown = canDrown;
    }

    public NPCPath createEntityPathTo(Entity entityFrom, Entity entityTo, float distance) {
        return this.createEntityPathTo(entityFrom, entityTo.field_70165_t, entityTo.field_70121_D.field_72338_b, entityTo.field_70161_v, distance);
    }

    public NPCPath createEntityPathTo(Entity entityFrom, int xTo, int yTo, int zTo, float distance) {
        return this.createEntityPathTo(entityFrom, (float)xTo + 0.5f, (float)yTo + 0.5f, (float)zTo + 0.5f, distance);
    }

    private NPCPath createEntityPathTo(Entity entityIn, double x, double y, double z, float distance) {
        this.path.clearPath();
        this.field_75867_c.clearMap();
        this.drowningType = ((EntityNPCInterface)this.theEntity).stats.drowningType;
        this.immuneToFire = ((EntityNPCInterface)this.theEntity).stats.immuneToFire;
        NPCPathPoint pathpoint = this.getStart();
        NPCPathPoint pathpoint1 = this.getPathPointToCoords(entityIn, x, y, z);
        NPCPathPoint pathPoint2 = new NPCPathPoint((int)Math.ceil(entityIn.field_70130_N), (int)Math.ceil(entityIn.field_70131_O), (int)Math.ceil(entityIn.field_70130_N));
        NPCPath pathentity = this.addToPath(entityIn, pathpoint, pathpoint1, pathPoint2, distance);
        return pathentity;
    }

    public NPCPathPoint getStart() {
        BlockPos blockpos1;
        PathNodeType pathnodetype1;
        int i;
        if (((EntityNPCInterface)this.theEntity).ais.canSwim && this.theEntity.func_70090_H()) {
            i = (int)this.theEntity.field_70121_D.field_72338_b;
            BlockPos.MutableBlockPos muteBlock = new BlockPos.MutableBlockPos(MathHelper.func_76128_c((double)this.theEntity.field_70165_t), i, MathHelper.func_76128_c((double)this.theEntity.field_70161_v));
            Block block = this.worldMap.func_147439_a(muteBlock.getX(), muteBlock.getY(), muteBlock.getZ());
            while (block == Blocks.field_150358_i || block == Blocks.field_150355_j) {
                muteBlock.setPos(MathHelper.func_76128_c((double)this.theEntity.field_70165_t), ++i, MathHelper.func_76128_c((double)this.theEntity.field_70161_v));
                block = this.worldMap.func_147439_a(muteBlock.getX(), muteBlock.getY(), muteBlock.getZ());
            }
        } else {
            i = MathHelper.func_76128_c((double)(this.theEntity.field_70121_D.field_72338_b + 0.5));
        }
        if (this.getPathPriority(pathnodetype1 = this.getPathNodeType((EntityLiving)this.theEntity, (blockpos1 = new BlockPos(this.theEntity)).getX(), i, blockpos1.getZ())) < 0.0f) {
            HashSet set = Sets.newHashSet();
            set.add(new BlockPos(this.theEntity.field_70121_D.field_72340_a, (double)i, this.theEntity.field_70121_D.field_72339_c));
            set.add(new BlockPos(this.theEntity.field_70121_D.field_72340_a, (double)i, this.theEntity.field_70121_D.field_72334_f));
            set.add(new BlockPos(this.theEntity.field_70121_D.field_72336_d, (double)i, this.theEntity.field_70121_D.field_72339_c));
            set.add(new BlockPos(this.theEntity.field_70121_D.field_72336_d, (double)i, this.theEntity.field_70121_D.field_72334_f));
            for (BlockPos blockpos : set) {
                PathNodeType pathnodetype = this.getPathNodeType((EntityLiving)this.theEntity, blockpos);
                if (!(this.getPathPriority(pathnodetype) >= 0.0f)) continue;
                return this.openPoint(blockpos.getX(), blockpos.getY(), blockpos.getZ());
            }
        }
        return this.openPoint(blockpos1.getX(), i, blockpos1.getZ());
    }

    public NPCPathPoint getPathPointToCoords(Entity entityIn, double x, double y, double z) {
        return this.openPoint(MathHelper.func_76128_c((double)x), MathHelper.func_76128_c((double)y), MathHelper.func_76128_c((double)z));
    }

    private NPCPath addToPath(Entity p_75861_1_, NPCPathPoint p_75861_2_, NPCPathPoint p_75861_3_, NPCPathPoint p_75861_4_, float p_75861_5_) {
        p_75861_2_.field_75836_e = 0.0f;
        p_75861_2_.field_75834_g = p_75861_2_.field_75833_f = p_75861_2_.distanceToSquared(p_75861_3_);
        this.path.clearPath();
        this.path.addPoint(p_75861_2_);
        NPCPathPoint pathpoint3 = p_75861_2_;
        int x = 0;
        while (!this.path.isPathEmpty() && ++x < 200) {
            NPCPathPoint pathpoint4 = this.path.dequeue();
            if (pathpoint4.equals((Object)p_75861_3_)) {
                return this.createEntityPath(p_75861_2_, p_75861_3_);
            }
            if (pathpoint4.distanceToSquared(p_75861_3_) < pathpoint3.distanceToSquared(p_75861_3_)) {
                pathpoint3 = pathpoint4;
            }
            pathpoint4.field_75842_i = true;
            int i = this.findPathOptions(p_75861_1_, pathpoint4, p_75861_4_, p_75861_3_, p_75861_5_);
            for (int j = 0; j < i; ++j) {
                NPCPathPoint pathpoint5 = this.pathOptions[j];
                float f1 = pathpoint4.field_75836_e + pathpoint4.distanceToSquared(pathpoint5);
                if (pathpoint5.func_75831_a() && !(f1 < pathpoint5.field_75836_e)) continue;
                pathpoint5.field_75841_h = pathpoint4;
                pathpoint5.field_75836_e = f1;
                pathpoint5.field_75833_f = pathpoint5.distanceToSquared(p_75861_3_);
                if (pathpoint5.func_75831_a()) {
                    this.path.changeDistance(pathpoint5, pathpoint5.field_75836_e + pathpoint5.field_75833_f);
                    continue;
                }
                pathpoint5.field_75834_g = pathpoint5.field_75836_e + pathpoint5.field_75833_f;
                this.path.addPoint(pathpoint5);
            }
        }
        if (pathpoint3 == p_75861_2_) {
            return null;
        }
        return this.createEntityPath(p_75861_2_, pathpoint3);
    }

    private int findPathOptions(Entity entity, NPCPathPoint currentPoint, NPCPathPoint p_75860_3_, NPCPathPoint targetPoint, float maxDistance) {
        NPCPathPoint pathpoint6;
        boolean flag5;
        int i = 0;
        int b0 = 0;
        if (this.func_75855_a(entity, currentPoint.field_75839_a, currentPoint.field_75837_b + 1, currentPoint.field_75838_c, p_75860_3_) == 1) {
            b0 = 1;
        }
        NPCPathPoint pathpoint0 = this.getSafePoint(entity, currentPoint.field_75839_a, currentPoint.field_75837_b, currentPoint.field_75838_c + 1, p_75860_3_, b0);
        NPCPathPoint pathpoint1 = this.getSafePoint(entity, currentPoint.field_75839_a - 1, currentPoint.field_75837_b, currentPoint.field_75838_c, p_75860_3_, b0);
        NPCPathPoint pathpoint2 = this.getSafePoint(entity, currentPoint.field_75839_a + 1, currentPoint.field_75837_b, currentPoint.field_75838_c, p_75860_3_, b0);
        NPCPathPoint pathpoint3 = this.getSafePoint(entity, currentPoint.field_75839_a, currentPoint.field_75837_b, currentPoint.field_75838_c - 1, p_75860_3_, b0);
        NPCPathPoint pathpoint4 = this.getSafePoint(entity, currentPoint.field_75839_a, currentPoint.field_75837_b + 1, currentPoint.field_75838_c, p_75860_3_, b0);
        NPCPathPoint pathpoint5 = this.getSafePoint(entity, currentPoint.field_75839_a, currentPoint.field_75837_b - 1, currentPoint.field_75838_c, p_75860_3_, b0);
        if (pathpoint0 != null && !pathpoint0.field_75842_i && pathpoint0.distanceTo(targetPoint) < maxDistance) {
            this.pathOptions[i++] = pathpoint0;
        }
        if (pathpoint1 != null && !pathpoint1.field_75842_i && pathpoint1.distanceTo(targetPoint) < maxDistance) {
            this.pathOptions[i++] = pathpoint1;
        }
        if (pathpoint2 != null && !pathpoint2.field_75842_i && pathpoint2.distanceTo(targetPoint) < maxDistance) {
            this.pathOptions[i++] = pathpoint2;
        }
        if (pathpoint3 != null && !pathpoint3.field_75842_i && pathpoint3.distanceTo(targetPoint) < maxDistance) {
            this.pathOptions[i++] = pathpoint3;
        }
        if (pathpoint4 != null && !pathpoint4.field_75842_i && pathpoint4.distanceTo(targetPoint) < maxDistance) {
            this.pathOptions[i++] = pathpoint4;
        }
        if (pathpoint5 != null && !pathpoint5.field_75842_i && pathpoint5.distanceTo(targetPoint) < maxDistance) {
            this.pathOptions[i++] = pathpoint5;
        }
        boolean flag = pathpoint3 != null && pathpoint3.costMalus != 1.0f && pathpoint3.costMalus != 2.0f;
        boolean flag1 = pathpoint0 != null && pathpoint0.costMalus != 1.0f && pathpoint0.costMalus != 2.0f;
        boolean flag2 = pathpoint2 != null && pathpoint2.costMalus != 1.0f && pathpoint2.costMalus != 2.0f;
        boolean flag3 = pathpoint1 != null && pathpoint1.costMalus != 1.0f && pathpoint1.costMalus != 2.0f;
        boolean flag4 = pathpoint4 != null && pathpoint4.costMalus != 1.0f && pathpoint4.costMalus != 2.0f;
        boolean bl = flag5 = pathpoint5 != null && pathpoint5.costMalus != 1.0f && pathpoint5.costMalus != 2.0f;
        if (flag && flag3 && (pathpoint6 = this.getSafePoint(entity, currentPoint.field_75839_a - 1, currentPoint.field_75837_b, currentPoint.field_75838_c - 1, p_75860_3_, b0)) != null && !pathpoint6.field_75842_i && pathpoint6.distanceTo(targetPoint) < maxDistance) {
            this.pathOptions[i++] = pathpoint6;
        }
        if (flag && flag2 && (pathpoint6 = this.getSafePoint(entity, currentPoint.field_75839_a + 1, currentPoint.field_75837_b, currentPoint.field_75838_c - 1, p_75860_3_, b0)) != null && !pathpoint6.field_75842_i && pathpoint6.distanceTo(targetPoint) < maxDistance) {
            this.pathOptions[i++] = pathpoint6;
        }
        if (flag1 && flag3 && (pathpoint6 = this.getSafePoint(entity, currentPoint.field_75839_a - 1, currentPoint.field_75837_b, currentPoint.field_75838_c + 1, p_75860_3_, b0)) != null && !pathpoint6.field_75842_i && pathpoint6.distanceTo(targetPoint) < maxDistance) {
            this.pathOptions[i++] = pathpoint6;
        }
        if (flag1 && flag2 && (pathpoint6 = this.getSafePoint(entity, currentPoint.field_75839_a + 1, currentPoint.field_75837_b, currentPoint.field_75838_c + 1, p_75860_3_, b0)) != null && !pathpoint6.field_75842_i && pathpoint6.distanceTo(targetPoint) < maxDistance) {
            this.pathOptions[i++] = pathpoint6;
        }
        if (flag && flag4 && (pathpoint6 = this.getSafePoint(entity, currentPoint.field_75839_a, currentPoint.field_75837_b + 1, currentPoint.field_75838_c - 1, p_75860_3_, b0)) != null && !pathpoint6.field_75842_i && pathpoint6.distanceTo(targetPoint) < maxDistance) {
            this.pathOptions[i++] = pathpoint6;
        }
        if (flag1 && flag4 && (pathpoint6 = this.getSafePoint(entity, currentPoint.field_75839_a, currentPoint.field_75837_b + 1, currentPoint.field_75838_c + 1, p_75860_3_, b0)) != null && !pathpoint6.field_75842_i && pathpoint6.distanceTo(targetPoint) < maxDistance) {
            this.pathOptions[i++] = pathpoint6;
        }
        if (flag2 && flag4 && (pathpoint6 = this.getSafePoint(entity, currentPoint.field_75839_a + 1, currentPoint.field_75837_b + 1, currentPoint.field_75838_c, p_75860_3_, b0)) != null && !pathpoint6.field_75842_i && pathpoint6.distanceTo(targetPoint) < maxDistance) {
            this.pathOptions[i++] = pathpoint6;
        }
        if (flag3 && flag4 && (pathpoint6 = this.getSafePoint(entity, currentPoint.field_75839_a - 1, currentPoint.field_75837_b + 1, currentPoint.field_75838_c, p_75860_3_, b0)) != null && !pathpoint6.field_75842_i && pathpoint6.distanceTo(targetPoint) < maxDistance) {
            this.pathOptions[i++] = pathpoint6;
        }
        if (flag && flag5 && (pathpoint6 = this.getSafePoint(entity, currentPoint.field_75839_a, currentPoint.field_75837_b - 1, currentPoint.field_75838_c - 1, p_75860_3_, b0)) != null && !pathpoint6.field_75842_i && pathpoint6.distanceTo(targetPoint) < maxDistance) {
            this.pathOptions[i++] = pathpoint6;
        }
        if (flag1 && flag5 && (pathpoint6 = this.getSafePoint(entity, currentPoint.field_75839_a, currentPoint.field_75837_b - 1, currentPoint.field_75838_c + 1, p_75860_3_, b0)) != null && !pathpoint6.field_75842_i && pathpoint6.distanceTo(targetPoint) < maxDistance) {
            this.pathOptions[i++] = pathpoint6;
        }
        if (flag2 && flag5 && (pathpoint6 = this.getSafePoint(entity, currentPoint.field_75839_a + 1, currentPoint.field_75837_b - 1, currentPoint.field_75838_c, p_75860_3_, b0)) != null && !pathpoint6.field_75842_i && pathpoint6.distanceTo(targetPoint) < maxDistance) {
            this.pathOptions[i++] = pathpoint6;
        }
        if (flag3 && flag5 && (pathpoint6 = this.getSafePoint(entity, currentPoint.field_75839_a - 1, currentPoint.field_75837_b - 1, currentPoint.field_75838_c, p_75860_3_, b0)) != null && !pathpoint6.field_75842_i && pathpoint6.distanceTo(targetPoint) < maxDistance) {
            this.pathOptions[i++] = pathpoint6;
        }
        return i;
    }

    private NPCPathPoint getSafePoint(Entity entity, int x, int y, int z, NPCPathPoint p_75858_5_, int p_75858_6_) {
        NPCPathPoint pathpoint1 = null;
        PathNodeType pathNodeType1 = this.getPathNodeType(entity, x, y, z);
        int i1 = this.nodeTypeToOffset(pathNodeType1);
        if (i1 == 2) {
            pathpoint1 = this.openPoint(x, y, z);
            pathpoint1.costMalus = i1;
            pathpoint1.nodeType = pathNodeType1;
            return pathpoint1;
        }
        if (i1 == 1) {
            pathpoint1 = this.openPoint(x, y, z);
            pathpoint1.costMalus = i1;
            pathpoint1.nodeType = pathNodeType1;
        }
        PathNodeType pathNodeType2 = this.getPathNodeType(entity, x, y + p_75858_6_, z);
        int vertOffset2 = this.nodeTypeToOffset(pathNodeType2);
        if (pathpoint1 == null && p_75858_6_ > 0 && i1 != -3 && i1 != -4 && vertOffset2 == 1) {
            pathpoint1 = this.openPoint(x, y + p_75858_6_, z);
            pathpoint1.costMalus = vertOffset2;
            pathpoint1.nodeType = pathNodeType2;
            y += p_75858_6_;
        }
        if (pathpoint1 != null) {
            int j1 = 0;
            int k1 = 0;
            while (y > 0) {
                PathNodeType pathNodeType3 = this.getPathNodeType(entity, x, y - 1, z);
                k1 = this.nodeTypeToOffset(pathNodeType3);
                if (this.isPathingInWater && k1 == -1) {
                    return null;
                }
                if (k1 != 1) break;
                if (j1++ >= entity.func_82143_as()) {
                    return null;
                }
                if (--y <= 0) continue;
                pathpoint1 = this.openPoint(x, y, z);
                pathpoint1.costMalus = k1;
                pathpoint1.nodeType = pathNodeType3;
            }
            if (k1 == -2) {
                return null;
            }
        }
        return pathpoint1;
    }

    private PathNodeType getPathNodeType(Entity entity, int x, int y, int z) {
        return this.getPathNodeType((EntityLiving)entity, new BlockPos(x, y, z));
    }

    public int nodeTypeToOffset(PathNodeType nodeType) {
        switch (nodeType) {
            case TRAPDOOR: {
                return -4;
            }
            case FENCE: {
                return -3;
            }
            case LAVA: 
            case DAMAGE_FIRE: 
            case DAMAGE_CACTUS: 
            case DAMAGE_OTHER: {
                return -2;
            }
            case WATER: 
            case DANGER_FIRE: 
            case DANGER_CACTUS: 
            case DANGER_OTHER: {
                return -1;
            }
            case BLOCKED: 
            case DOOR_WOOD_CLOSED: 
            case DOOR_IRON_CLOSED: {
                return 0;
            }
            case WALKABLE: 
            case DOOR_OPEN: {
                return 1;
            }
            case OPEN: 
            case RAIL: {
                return 2;
            }
        }
        return 1;
    }

    protected NPCPathPoint openPoint(int x, int y, int z) {
        int i = NPCPathPoint.makeHash(x, y, z);
        NPCPathPoint pathpoint = this.field_75867_c.lookup(i);
        if (pathpoint == null) {
            pathpoint = new NPCPathPoint(x, y, z);
            this.field_75867_c.addKey(i, pathpoint);
        }
        return pathpoint;
    }

    private NPCPath createEntityPath(NPCPathPoint pathFrom, NPCPathPoint pathTo) {
        int i = 1;
        NPCPathPoint pathpoint2 = pathTo;
        while (pathpoint2.field_75841_h != null) {
            ++i;
            pathpoint2 = pathpoint2.field_75841_h;
        }
        NPCPathPoint[] apathpoint = new NPCPathPoint[i];
        pathpoint2 = pathTo;
        apathpoint[--i] = pathTo;
        while (pathpoint2.field_75841_h != null) {
            pathpoint2 = pathpoint2.field_75841_h;
            apathpoint[--i] = pathpoint2;
        }
        return new NPCPath(apathpoint);
    }

    public PathNodeType getPathNodeType(IBlockAccess blockaccessIn, int x, int y, int z, EntityLiving entitylivingIn, int xSize, int ySize, int zSize, boolean canBreakDoorsIn, boolean canEnterDoorsIn) {
        EnumSet<PathNodeType> enumset = EnumSet.noneOf(PathNodeType.class);
        PathNodeType pathnodetype = PathNodeType.BLOCKED;
        BlockPos blockpos = new BlockPos((Entity)entitylivingIn);
        pathnodetype = this.getPathNodeType(blockaccessIn, x, y, z, xSize, ySize, zSize, canBreakDoorsIn, canEnterDoorsIn, enumset, pathnodetype, blockpos);
        if (enumset.contains((Object)PathNodeType.FENCE)) {
            return PathNodeType.FENCE;
        }
        PathNodeType pathnodetype1 = PathNodeType.BLOCKED;
        for (PathNodeType pathnodetype2 : enumset) {
            if (this.getPathPriority(pathnodetype2) < 0.0f) {
                return pathnodetype2;
            }
            if (!(this.getPathPriority(pathnodetype2) >= this.getPathPriority(pathnodetype1))) continue;
            pathnodetype1 = pathnodetype2;
        }
        if (pathnodetype == PathNodeType.OPEN && this.getPathPriority(pathnodetype1) == 0.0f) {
            return PathNodeType.OPEN;
        }
        return pathnodetype1;
    }

    public PathNodeType getPathNodeType(IBlockAccess blockaccessIn, int x, int y, int z) {
        PathNodeType pathnodetype = this.getPathNodeTypeRaw(blockaccessIn, x, y, z);
        if (pathnodetype == PathNodeType.OPEN && y >= 1) {
            PathNodeType pathnodetype1 = this.getPathNodeTypeRaw(blockaccessIn, x, y - 1, z);
            pathnodetype = pathnodetype1 != PathNodeType.DAMAGE_FIRE && pathnodetype1 != PathNodeType.LAVA ? (pathnodetype1 == PathNodeType.DAMAGE_CACTUS ? PathNodeType.DAMAGE_CACTUS : (pathnodetype1 == PathNodeType.DAMAGE_OTHER ? PathNodeType.DAMAGE_OTHER : (pathnodetype1 != PathNodeType.WALKABLE && pathnodetype1 != PathNodeType.OPEN && pathnodetype1 != PathNodeType.WATER ? PathNodeType.WALKABLE : PathNodeType.OPEN))) : PathNodeType.DAMAGE_FIRE;
        }
        pathnodetype = this.checkNeighborBlocks(blockaccessIn, x, y, z, pathnodetype);
        return pathnodetype;
    }

    public PathNodeType getPathNodeType(IBlockAccess blockAccess, int x, int y, int z, int xSize, int ySize, int zSize, boolean canOpenDoorsIn, boolean canEnterDoorsIn, EnumSet<PathNodeType> p_193577_10_, PathNodeType p_193577_11_, BlockPos p_193577_12_) {
        for (int i = 0; i < xSize; ++i) {
            for (int j = 0; j < ySize; ++j) {
                for (int k = 0; k < zSize; ++k) {
                    int l = i + x;
                    int i1 = j + y;
                    int j1 = k + z;
                    PathNodeType pathnodetype = this.getPathNodeType(blockAccess, l, i1, j1);
                    if (pathnodetype == PathNodeType.DOOR_WOOD_CLOSED && canOpenDoorsIn && canEnterDoorsIn) {
                        pathnodetype = PathNodeType.WALKABLE;
                    }
                    if (pathnodetype == PathNodeType.DOOR_OPEN && !canEnterDoorsIn) {
                        pathnodetype = PathNodeType.BLOCKED;
                    }
                    if (pathnodetype == PathNodeType.RAIL && !(blockAccess.func_147439_a(p_193577_12_.getX(), p_193577_12_.getY(), p_193577_12_.getZ()) instanceof BlockRailBase) && !(blockAccess.func_147439_a(p_193577_12_.down().getX(), p_193577_12_.down().getY(), p_193577_12_.down().getZ()) instanceof BlockRailBase)) {
                        pathnodetype = PathNodeType.FENCE;
                    }
                    if (i == 0 && j == 0 && k == 0) {
                        p_193577_11_ = pathnodetype;
                    }
                    p_193577_10_.add(pathnodetype);
                }
            }
        }
        return p_193577_11_;
    }

    private PathNodeType getPathNodeType(EntityLiving p_192559_1_, BlockPos p_192559_2_) {
        return this.getPathNodeType(p_192559_1_, p_192559_2_.getX(), p_192559_2_.getY(), p_192559_2_.getZ());
    }

    private PathNodeType getPathNodeType(EntityLiving p_192558_1_, int p_192558_2_, int p_192558_3_, int p_192558_4_) {
        int entitySizeX = MathHelper.func_76128_c((double)(this.theEntity.field_70130_N + 1.0f));
        int entitySizeY = MathHelper.func_76128_c((double)(this.theEntity.field_70131_O + 1.0f));
        int entitySizeZ = MathHelper.func_76128_c((double)(this.theEntity.field_70130_N + 1.0f));
        return this.getPathNodeType(this.worldMap, p_192558_2_, p_192558_3_, p_192558_4_, p_192558_1_, entitySizeX, entitySizeY, entitySizeZ, ((EntityCustomNpc)this.theEntity).ais.doorInteract == 0, ((EntityCustomNpc)this.theEntity).ais.doorInteract == 1);
    }

    public PathNodeType checkNeighborBlocks(IBlockAccess worldmap, int x, int y, int z, PathNodeType type) {
        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();
        if (type == PathNodeType.WALKABLE) {
            for (int i = -1; i <= 1; ++i) {
                for (int j = -1; j <= 1; ++j) {
                    if (i == 0 && j == 0) continue;
                    blockpos$pooledmutableblockpos.setPos(i + x, y, j + z);
                    Block block = worldmap.func_147439_a(blockpos$pooledmutableblockpos.getX(), blockpos$pooledmutableblockpos.getY(), blockpos$pooledmutableblockpos.getZ());
                    if (block == Blocks.field_150434_aF) {
                        type = PathNodeType.DANGER_CACTUS;
                        continue;
                    }
                    if (block != Blocks.field_150480_ab || this.immuneToFire) continue;
                    type = PathNodeType.DANGER_FIRE;
                }
            }
        }
        blockpos$pooledmutableblockpos.release();
        return type;
    }

    protected PathNodeType getPathNodeTypeRaw(IBlockAccess worldmap, int p_189553_2_, int p_189553_3_, int p_189553_4_) {
        BlockPos blockpos = new BlockPos(p_189553_2_, p_189553_3_, p_189553_4_);
        Block block = worldmap.func_147439_a(blockpos.getX(), blockpos.getY(), blockpos.getZ());
        Material material = block.func_149688_o();
        if (material == Material.field_151579_a) {
            return PathNodeType.OPEN;
        }
        if (block != Blocks.field_150415_aT && block != Blocks.field_150392_bi) {
            if (block == Blocks.field_150480_ab) {
                return this.immuneToFire ? PathNodeType.OPEN : PathNodeType.DAMAGE_FIRE;
            }
            if (block == Blocks.field_150434_aF) {
                return PathNodeType.DAMAGE_CACTUS;
            }
            if (block instanceof BlockDoor && material == Material.field_151575_d && !((BlockDoor)block).func_149655_b(this.worldMap, p_189553_2_, p_189553_3_, p_189553_4_)) {
                return PathNodeType.DOOR_WOOD_CLOSED;
            }
            if (block instanceof BlockDoor && material == Material.field_151575_d && !((BlockDoor)block).func_149655_b(this.worldMap, p_189553_2_, p_189553_3_, p_189553_4_)) {
                return PathNodeType.DOOR_IRON_CLOSED;
            }
            if (block instanceof BlockDoor && ((BlockDoor)block).func_149655_b(this.worldMap, p_189553_2_, p_189553_3_, p_189553_4_)) {
                return PathNodeType.DOOR_OPEN;
            }
            if (block instanceof BlockRailBase) {
                return PathNodeType.RAIL;
            }
            if (!(block instanceof BlockFence || block instanceof BlockWall || block instanceof BlockFenceGate)) {
                if (material == Material.field_151586_h) {
                    return ((EntityNPCInterface)this.theEntity).ais.canSwim ? PathNodeType.OPEN : PathNodeType.WATER;
                }
                if (material == Material.field_151587_i) {
                    return this.immuneToFire ? PathNodeType.OPEN : PathNodeType.LAVA;
                }
                return block.func_149655_b(worldmap, blockpos.getX(), blockpos.getY(), blockpos.getZ()) ? PathNodeType.OPEN : PathNodeType.BLOCKED;
            }
            return PathNodeType.FENCE;
        }
        return PathNodeType.TRAPDOOR;
    }

    public float getPathPriority(PathNodeType nodeType) {
        Float f = this.mapPathPriority.get((Object)nodeType);
        return f == null ? nodeType.getPriority() : f.floatValue();
    }
}

