/*
 * Decompiled with CFR 0.152.
 */
package invtweaks;

import java.util.Comparator;

public abstract class TickScheduledTask {
    private final long scheduledTickTime;

    public TickScheduledTask(long time) {
        this.scheduledTickTime = time;
    }

    public final long getScheduledTickTime() {
        return this.scheduledTickTime;
    }

    abstract void run();

    public static final class TaskComparator
    implements Comparator<TickScheduledTask> {
        @Override
        public int compare(TickScheduledTask o1, TickScheduledTask o2) {
            return Long.valueOf(o1.scheduledTickTime).compareTo(o2.scheduledTickTime);
        }
    }
}

