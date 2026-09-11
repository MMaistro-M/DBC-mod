/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import noppes.npcs.api.handler.data.IAnimationData;
import noppes.npcs.api.handler.data.IFrame;

public interface IAnimation {
    public IAnimationData getParent();

    public IFrame currentFrame();

    public IFrame[] getFrames();

    public IAnimation setFrames(IFrame[] var1);

    public IAnimation clearFrames();

    public IAnimation addFrame(IFrame var1);

    public IAnimation addFrame(int var1, IFrame var2);

    public IAnimation removeFrame(IFrame var1);

    public IAnimation setName(String var1);

    public String getName();

    public IAnimation setSpeed(float var1);

    public float getSpeed();

    public IAnimation setSmooth(byte var1);

    public byte isSmooth();

    public IAnimation doWhileStanding(boolean var1);

    public boolean doWhileStanding();

    public IAnimation doWhileMoving(boolean var1);

    public boolean doWhileMoving();

    public IAnimation doWhileAttacking(boolean var1);

    public boolean doWhileAttacking();

    public IAnimation setLoop(int var1);

    public int loop();

    public IAnimation save();

    public int getID();

    public void setID(int var1);

    public long getTotalTime();

    public boolean hasData(String var1);

    public Object getData(String var1);

    public IAnimation setData(String var1, Object var2);

    public IAnimation removeData(String var1);

    public IAnimation onStart(Consumer<IAnimation> var1);

    public IAnimation onFrame(BiConsumer<Integer, IAnimation> var1);

    public IAnimation onEnd(Consumer<IAnimation> var1);
}

