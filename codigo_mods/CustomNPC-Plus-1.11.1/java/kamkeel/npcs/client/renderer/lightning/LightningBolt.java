/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package kamkeel.npcs.client.renderer.lightning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import kamkeel.npcs.client.renderer.lightning.Vec3d;
import net.minecraft.world.World;

public class LightningBolt {
    public static ConcurrentLinkedQueue<LightningBolt> boltList = new ConcurrentLinkedQueue();
    public ArrayList<Segment> segments = new ArrayList();
    public Vec3d start;
    public Vec3d end;
    public double length;
    public int numSegments0;
    private int numSplits;
    private boolean finalized;
    private Random rand;
    public long seed;
    public int particleAge;
    public int particleMaxAge;
    public boolean isDead;
    private World world;
    private HashMap<Integer, Integer> splitParents = new HashMap();
    public float speed = 1.5f;
    public static final int FADE_TIME = 4;
    public int colorOuter;
    public int colorInner;

    public LightningBolt(World world, Vec3d start, Vec3d end, float ticksPerMeter, long seed, int colorOuter, int colorInner) {
        this.world = world;
        this.seed = seed;
        this.rand = new Random(seed);
        this.start = start;
        this.end = end;
        this.speed = ticksPerMeter;
        this.colorOuter = colorOuter;
        this.colorInner = colorInner;
        this.numSegments0 = 1;
        this.length = end.copy().subtract(start).mag();
        this.particleMaxAge = Math.max(1, 4 + this.rand.nextInt(4) - 2);
        this.particleAge = -((int)(this.length * (double)this.speed));
        this.segments.add(new Segment(start, end));
    }

    public void defaultFractal() {
        this.fractal(2, this.length / 1.5, 0.7f, 0.7f, 45.0);
        this.fractal(2, this.length / 4.0, 0.5, 0.8f, 50.0);
        this.fractal(2, this.length / 15.0, 0.5, 0.9f, 55.0);
        this.fractal(2, this.length / 30.0, 0.5, 1.0, 60.0);
        this.fractal(2, this.length / 60.0, 0.0, 0.0, 0.0);
        this.fractal(2, this.length / 100.0, 0.0, 0.0, 0.0);
        this.fractal(2, this.length / 400.0, 0.0, 0.0, 0.0);
    }

    public void simpleFractal() {
        this.fractal(2, this.length / 2.0, 0.4f, 0.6f, 35.0);
        this.fractal(2, this.length / 6.0, 0.3f, 0.7f, 40.0);
        this.fractal(2, this.length / 20.0, 0.0, 0.0, 0.0);
    }

    public void fractal(int splits, double amount, double splitChance, double splitLength, double splitAngle) {
        if (this.finalized) {
            return;
        }
        ArrayList<Segment> oldSegments = this.segments;
        this.segments = new ArrayList();
        Segment prev = null;
        for (Segment segment : oldSegments) {
            int i;
            prev = segment.prev;
            Vec3d subSegment = segment.diff.copy().multiply(1.0 / (double)splits);
            BoltPoint[] newPoints = new BoltPoint[splits + 1];
            Vec3d startPoint = segment.startPoint.point;
            newPoints[0] = segment.startPoint;
            newPoints[splits] = segment.endPoint;
            for (i = 1; i < splits; ++i) {
                Vec3d randOff = segment.diff.copy().perpendicular().normalize().rotate(this.rand.nextFloat() * 360.0f, segment.diff);
                randOff.multiply((double)(this.rand.nextFloat() - 0.5f) * amount * 2.0);
                Vec3d basePoint = startPoint.copy().add(subSegment.copy().multiply(i));
                newPoints[i] = new BoltPoint(basePoint, randOff);
            }
            for (i = 0; i < splits; ++i) {
                Segment next = new Segment(newPoints[i], newPoints[i + 1], segment.light, segment.segmentNo * splits + i, segment.splitNo);
                next.prev = prev;
                if (prev != null) {
                    prev.next = next;
                }
                if (i != 0 && (double)this.rand.nextFloat() < splitChance) {
                    Vec3d splitRot = next.diff.copy().xCrossProduct().rotate(this.rand.nextFloat() * 360.0f, next.diff);
                    Vec3d diff = next.diff.copy().rotate((double)(this.rand.nextFloat() * 0.66f + 0.33f) * splitAngle, splitRot).multiply(splitLength);
                    ++this.numSplits;
                    this.splitParents.put(this.numSplits, next.splitNo);
                    Segment split = new Segment(newPoints[i], new BoltPoint(newPoints[i + 1].basePoint, newPoints[i + 1].offsetVec.copy().add(diff)), segment.light / 2.0f, next.segmentNo, this.numSplits);
                    split.prev = prev;
                    this.segments.add(split);
                }
                prev = next;
                this.segments.add(next);
            }
            if (segment.next == null) continue;
            segment.next.prev = prev;
        }
        this.numSegments0 *= splits;
    }

    public void finalizeBolt() {
        if (this.finalized) {
            return;
        }
        this.finalized = true;
        this.calculateEndDiffs();
        Collections.sort(this.segments, new SegmentLightSorter());
    }

    private void calculateEndDiffs() {
        Collections.sort(this.segments, new SegmentSorter());
        for (Segment segment : this.segments) {
            segment.calcEndDiffs();
        }
    }

    public void onUpdate() {
        ++this.particleAge;
        if (this.particleAge >= this.particleMaxAge) {
            this.isDead = true;
        }
    }

    public static void updateAll() {
        Iterator<LightningBolt> iterator = boltList.iterator();
        while (iterator.hasNext()) {
            LightningBolt bolt = iterator.next();
            bolt.onUpdate();
            if (!bolt.isDead) continue;
            iterator.remove();
        }
    }

    public class SegmentLightSorter
    implements Comparator<Segment> {
        @Override
        public int compare(Segment o1, Segment o2) {
            return Float.compare(o2.light, o1.light);
        }
    }

    public class SegmentSorter
    implements Comparator<Segment> {
        @Override
        public int compare(Segment o1, Segment o2) {
            int comp = Integer.compare(o1.splitNo, o2.splitNo);
            if (comp == 0) {
                return Integer.compare(o1.segmentNo, o2.segmentNo);
            }
            return comp;
        }
    }

    public class Segment {
        public BoltPoint startPoint;
        public BoltPoint endPoint;
        public Vec3d diff;
        public Segment prev;
        public Segment next;
        public Vec3d nextDiff;
        public Vec3d prevDiff;
        public float sinPrev;
        public float sinNext;
        public float light;
        public int segmentNo;
        public int splitNo;

        public Segment(BoltPoint start, BoltPoint end, float light, int segmentNo, int splitNo) {
            this.startPoint = start;
            this.endPoint = end;
            this.light = light;
            this.segmentNo = segmentNo;
            this.splitNo = splitNo;
            this.calcDiff();
        }

        public Segment(Vec3d start, Vec3d end) {
            this(this$0.new BoltPoint(start, new Vec3d(0.0, 0.0, 0.0)), this$0.new BoltPoint(end, new Vec3d(0.0, 0.0, 0.0)), 1.0f, 0, 0);
        }

        public void calcDiff() {
            this.diff = this.endPoint.point.copy().subtract(this.startPoint.point);
        }

        public void calcEndDiffs() {
            Vec3d thisDiffNorm;
            if (this.prev != null) {
                Vec3d prevDiffNorm = this.prev.diff.copy().normalize();
                thisDiffNorm = this.diff.copy().normalize();
                this.prevDiff = thisDiffNorm.copy().add(prevDiffNorm).normalize();
                this.sinPrev = (float)Math.sin(thisDiffNorm.angle(prevDiffNorm.multiply(-1.0)) / 2.0);
            } else {
                this.prevDiff = this.diff.copy().normalize();
                this.sinPrev = 1.0f;
            }
            if (this.next != null) {
                Vec3d nextDiffNorm = this.next.diff.copy().normalize();
                thisDiffNorm = this.diff.copy().normalize();
                this.nextDiff = thisDiffNorm.add(nextDiffNorm).normalize();
                this.sinNext = (float)Math.sin(thisDiffNorm.angle(nextDiffNorm.multiply(-1.0)) / 2.0);
            } else {
                this.nextDiff = this.diff.copy().normalize();
                this.sinNext = 1.0f;
            }
        }
    }

    public class BoltPoint {
        public Vec3d point;
        public Vec3d basePoint;
        public Vec3d offsetVec;

        public BoltPoint(Vec3d basePoint, Vec3d offsetVec) {
            this.point = basePoint.copy().add(offsetVec);
            this.basePoint = basePoint;
            this.offsetVec = offsetVec;
        }
    }
}

