/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.ai.pathfinder;

import noppes.npcs.ai.pathfinder.NPCPathPoint;

public class PathHeap {
    private NPCPathPoint[] pathPoints = new NPCPathPoint[128];
    private int count;

    public NPCPathPoint addPoint(NPCPathPoint pathPoint) {
        if (pathPoint.field_75835_d >= 0) {
            throw new IllegalStateException("OW KNOWS!");
        }
        if (this.count == this.pathPoints.length) {
            NPCPathPoint[] apathpoint = new NPCPathPoint[this.count << 1];
            System.arraycopy(this.pathPoints, 0, apathpoint, 0, this.count);
            this.pathPoints = apathpoint;
        }
        this.pathPoints[this.count] = pathPoint;
        pathPoint.field_75835_d = this.count;
        this.sortBack(this.count++);
        return pathPoint;
    }

    public void clearPath() {
        this.count = 0;
    }

    public NPCPathPoint dequeue() {
        NPCPathPoint pathpoint = this.pathPoints[0];
        this.pathPoints[0] = this.pathPoints[--this.count];
        this.pathPoints[this.count] = null;
        if (this.count > 0) {
            this.sortForward(0);
        }
        pathpoint.field_75835_d = -1;
        return pathpoint;
    }

    public void changeDistance(NPCPathPoint point, float distance) {
        float f1 = point.field_75834_g;
        point.field_75834_g = distance;
        if (distance < f1) {
            this.sortBack(point.field_75835_d);
        } else {
            this.sortForward(point.field_75835_d);
        }
    }

    private void sortBack(int index) {
        NPCPathPoint pathpoint = this.pathPoints[index];
        float f = pathpoint.field_75834_g;
        while (index > 0) {
            int j = index - 1 >> 1;
            NPCPathPoint pathpoint1 = this.pathPoints[j];
            if (f >= pathpoint1.field_75834_g) break;
            this.pathPoints[index] = pathpoint1;
            pathpoint1.field_75835_d = index;
            index = j;
        }
        this.pathPoints[index] = pathpoint;
        pathpoint.field_75835_d = index;
    }

    private void sortForward(int index) {
        NPCPathPoint pathpoint = this.pathPoints[index];
        float f = pathpoint.field_75834_g;
        while (true) {
            float f2;
            NPCPathPoint pathpoint2;
            int j = 1 + (index << 1);
            int k = j + 1;
            if (j >= this.count) break;
            NPCPathPoint pathpoint1 = this.pathPoints[j];
            float f1 = pathpoint1.field_75834_g;
            if (k >= this.count) {
                pathpoint2 = null;
                f2 = Float.POSITIVE_INFINITY;
            } else {
                pathpoint2 = this.pathPoints[k];
                f2 = pathpoint2.field_75834_g;
            }
            if (f1 < f2) {
                if (f1 >= f) break;
                this.pathPoints[index] = pathpoint1;
                pathpoint1.field_75835_d = index;
                index = j;
                continue;
            }
            if (f2 >= f) break;
            this.pathPoints[index] = pathpoint2;
            pathpoint2.field_75835_d = index;
            index = k;
        }
        this.pathPoints[index] = pathpoint;
        pathpoint.field_75835_d = index;
    }

    public boolean isPathEmpty() {
        return this.count == 0;
    }
}

