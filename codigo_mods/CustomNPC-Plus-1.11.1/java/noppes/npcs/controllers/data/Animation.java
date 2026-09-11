/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.EventHooks;
import noppes.npcs.api.handler.data.IAnimation;
import noppes.npcs.api.handler.data.IAnimationData;
import noppes.npcs.api.handler.data.IFrame;
import noppes.npcs.constants.EnumAnimationPart;
import noppes.npcs.controllers.AnimationController;
import noppes.npcs.controllers.data.AnimationData;
import noppes.npcs.controllers.data.Frame;

public class Animation
implements IAnimation {
    public AnimationData parent;
    public int id = -1;
    public ArrayList<Frame> frames = new ArrayList();
    public int currentFrame = 0;
    public int currentFrameTime = 0;
    public String name = "";
    public float speed = 1.0f;
    public byte smooth = 0;
    public int loop = -1;
    public boolean whileStanding = true;
    public boolean whileAttacking = true;
    public boolean whileMoving = true;
    public boolean paused;
    protected final Map<String, Object> dataStore = new HashMap<String, Object>();
    public Consumer<IAnimation> onAnimationStart;
    public BiConsumer<Integer, IAnimation> onAnimationFrame;
    public Consumer<IAnimation> onAnimationEnd;

    public Animation() {
    }

    public Animation(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public Animation(int id, String name, float speed, byte smooth) {
        this.name = name;
        this.speed = speed;
        this.smooth = smooth;
        this.id = id;
    }

    @Override
    public IAnimationData getParent() {
        return this.parent;
    }

    @Override
    public IFrame currentFrame() {
        return this.currentFrame < this.frames.size() ? (IFrame)this.frames.get(this.currentFrame) : null;
    }

    @Override
    public IFrame[] getFrames() {
        return this.frames.toArray(new Frame[0]);
    }

    @Override
    public IAnimation setFrames(IFrame[] frames) {
        this.clearFrames();
        for (IFrame frame : frames) {
            this.frames.add((Frame)frame);
        }
        return this;
    }

    @Override
    public IAnimation clearFrames() {
        this.frames.clear();
        return this;
    }

    @Override
    public IAnimation addFrame(IFrame frame) {
        this.frames.add((Frame)frame);
        return this;
    }

    @Override
    public IAnimation addFrame(int index, IFrame frame) {
        this.frames.add(index, (Frame)frame);
        return this;
    }

    @Override
    public IAnimation removeFrame(IFrame frame) {
        this.frames.remove((Frame)frame);
        return this;
    }

    @Override
    public IAnimation setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public IAnimation setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public IAnimation setSmooth(byte smooth) {
        this.smooth = smooth;
        return this;
    }

    @Override
    public byte isSmooth() {
        return this.smooth;
    }

    @Override
    public IAnimation doWhileStanding(boolean whileStanding) {
        this.whileStanding = whileStanding;
        return this;
    }

    @Override
    public boolean doWhileStanding() {
        return this.whileStanding;
    }

    @Override
    public IAnimation doWhileMoving(boolean whileMoving) {
        this.whileMoving = whileMoving;
        return this;
    }

    @Override
    public boolean doWhileMoving() {
        return this.whileMoving;
    }

    @Override
    public IAnimation doWhileAttacking(boolean whileAttacking) {
        this.whileAttacking = whileAttacking;
        return this;
    }

    @Override
    public boolean doWhileAttacking() {
        return this.whileAttacking;
    }

    @Override
    public IAnimation setLoop(int loopAtFrame) {
        this.loop = loopAtFrame;
        return this;
    }

    @Override
    public int loop() {
        return this.loop;
    }

    @Override
    public IAnimation save() {
        return AnimationController.Instance.saveAnimation(this);
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setID(int newID) {
        this.id = newID;
    }

    @Override
    public long getTotalTime() {
        long time = 0L;
        for (Frame frame : this.frames) {
            time += (long)frame.duration;
        }
        return time;
    }

    @Override
    public boolean hasData(String key) {
        return this.dataStore.containsKey(key);
    }

    @Override
    public Object getData(String key) {
        return this.dataStore.get(key);
    }

    @Override
    public IAnimation setData(String key, Object v) {
        this.dataStore.put(key, v);
        return this;
    }

    @Override
    public IAnimation removeData(String key) {
        this.dataStore.remove(key);
        return this;
    }

    public void readFromNBT(NBTTagCompound compound) {
        if (compound.func_74764_b("ID")) {
            this.id = compound.func_74762_e("ID");
        } else if (AnimationController.Instance != null) {
            this.id = AnimationController.Instance.getUnusedId();
        }
        this.name = compound.func_74779_i("Name");
        this.speed = compound.func_74760_g("Speed");
        this.smooth = compound.func_74771_c("Smooth");
        this.loop = compound.func_74762_e("Loop");
        ArrayList<Frame> frames = new ArrayList<Frame>();
        NBTTagList list = compound.func_150295_c("Frames", 10);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound item = list.func_150305_b(i);
            Frame frame = new Frame();
            frame.parent = this;
            frame.readFromNBT(item);
            frames.add(frame);
        }
        this.frames = frames;
        this.whileStanding = compound.func_74767_n("WhileStanding");
        this.whileMoving = compound.func_74767_n("WhileWalking");
        this.whileAttacking = compound.func_74767_n("WhileAttacking");
        this.currentFrame = compound.func_74762_e("CurrentFrame");
        this.currentFrameTime = compound.func_74762_e("CurrentFrameTime");
    }

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74768_a("ID", this.id);
        compound.func_74778_a("Name", this.name);
        compound.func_74776_a("Speed", this.speed);
        compound.func_74774_a("Smooth", this.smooth);
        compound.func_74768_a("Loop", this.loop);
        NBTTagList list = new NBTTagList();
        for (Frame frame : this.frames) {
            NBTTagCompound item = frame.writeToNBT();
            list.func_74742_a((NBTBase)item);
        }
        compound.func_74782_a("Frames", (NBTBase)list);
        compound.func_74757_a("WhileStanding", this.whileStanding);
        compound.func_74757_a("WhileWalking", this.whileMoving);
        compound.func_74757_a("WhileAttacking", this.whileAttacking);
        compound.func_74768_a("CurrentFrame", this.currentFrame);
        compound.func_74768_a("CurrentFrameTime", this.currentFrameTime);
        return compound;
    }

    public boolean increaseTime() {
        if (this.paused) {
            return false;
        }
        if (this.parent != null && this.currentFrame < this.frames.size()) {
            this.parent.finishedFrame = this.currentFrame;
        }
        ++this.currentFrameTime;
        if (this.currentFrame() != null && this.currentFrameTime == this.currentFrame().getDuration()) {
            EventHooks.onAnimationFrameExited(this, this.currentFrame());
            Frame prevFrame = (Frame)this.currentFrame();
            Frame nextFrame = null;
            this.currentFrameTime = 0;
            ++this.currentFrame;
            if (this.currentFrame < this.frames.size()) {
                nextFrame = this.frames.get(this.currentFrame);
            } else if (this.loop >= 0 && this.loop < this.frames.size()) {
                this.currentFrame = this.loop;
            }
            if (this.currentFrame() != null) {
                EventHooks.onAnimationFrameEntered(this, this.currentFrame());
                this.fireFrameTask(this.currentFrame);
            } else {
                EventHooks.onAnimationEnded(this);
                this.fireEndTask();
            }
            if (nextFrame != null) {
                for (EnumAnimationPart part : EnumAnimationPart.values()) {
                    if (!prevFrame.frameParts.containsKey((Object)part) || !nextFrame.frameParts.containsKey((Object)part)) continue;
                    nextFrame.frameParts.get((Object)((Object)part)).prevRotations = prevFrame.frameParts.get((Object)((Object)part)).prevRotations;
                    nextFrame.frameParts.get((Object)((Object)part)).prevPivots = prevFrame.frameParts.get((Object)((Object)part)).prevPivots;
                }
            } else if (this.parent != null && this.currentFrame > this.loop) {
                this.parent.finishedTime = this.parent.getMCEntity().func_70654_ax();
            }
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void jumpToCurrentFrame() {
        this.jumpToFrameAtTime(this.currentFrame, 0);
    }

    @SideOnly(value=Side.CLIENT)
    public void jumpToFrameAtTime(int frameIndex, int time) {
        this.currentFrame = frameIndex;
        this.currentFrameTime = time;
        Frame frame = (Frame)this.currentFrame();
        if (frame != null) {
            for (EnumAnimationPart part : EnumAnimationPart.values()) {
                if (part == null || !frame.frameParts.containsKey((Object)part)) continue;
                frame.frameParts.get((Object)part).jumpToCurrentFrame();
            }
        }
    }

    @Override
    public IAnimation onStart(Consumer<IAnimation> task) {
        this.onAnimationStart = task;
        return this;
    }

    @Override
    public IAnimation onFrame(BiConsumer<Integer, IAnimation> task) {
        this.onAnimationFrame = task;
        return this;
    }

    @Override
    public IAnimation onEnd(Consumer<IAnimation> task) {
        this.onAnimationEnd = task;
        return this;
    }

    protected void fireStartTask() {
        if (this.onAnimationStart != null) {
            this.onAnimationStart.accept(this);
        }
    }

    protected void fireFrameTask(int frame) {
        if (this.onAnimationFrame != null) {
            this.onAnimationFrame.accept(frame, this);
        }
    }

    protected void fireEndTask() {
        if (this.onAnimationEnd != null) {
            this.onAnimationEnd.accept(this);
        }
    }

    public void moveFromGlobalToLocal(Animation animation) {
        this.onAnimationStart = animation.onAnimationStart;
        this.onAnimationFrame = animation.onAnimationFrame;
        this.onAnimationEnd = animation.onAnimationEnd;
        animation.onAnimationEnd = null;
        animation.onAnimationFrame = null;
        animation.onAnimationStart = null;
        this.dataStore.putAll(animation.dataStore);
        animation.dataStore.clear();
    }
}

