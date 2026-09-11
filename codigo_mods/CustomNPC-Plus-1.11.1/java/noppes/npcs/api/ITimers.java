/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api;

public interface ITimers {
    public int[] timerIds();

    public void start(int var1, int var2, boolean var3);

    public void forceStart(int var1, int var2, boolean var3);

    public boolean has(int var1);

    public boolean stop(int var1);

    public void reset(int var1);

    public void clear();

    public int ticks(int var1);

    public void setTicks(int var1, int var2);

    public int maxTicks(int var1);

    public void setMaxTicks(int var1, int var2);

    public boolean repeats(int var1);

    public void setRepeats(int var1, boolean var2);

    public int size();
}

