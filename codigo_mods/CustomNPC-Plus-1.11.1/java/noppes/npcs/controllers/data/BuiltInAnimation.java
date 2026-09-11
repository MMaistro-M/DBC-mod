/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers.data;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.api.handler.data.IAnimation;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.controllers.data.Frame;

public class BuiltInAnimation
extends Animation {
    public BuiltInAnimation() {
        this.id = -1;
    }

    public BuiltInAnimation(String name) {
        this.name = name;
        this.id = -1;
    }

    @Override
    public IAnimation save() {
        return this;
    }

    @Override
    public int getID() {
        return -1;
    }

    @Override
    public void setID(int newID) {
    }

    public boolean isBuiltIn() {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        String preservedName = this.name;
        this.speed = compound.func_74760_g("Speed");
        this.smooth = compound.func_74771_c("Smooth");
        this.loop = compound.func_74762_e("Loop");
        this.frames.clear();
        NBTTagList list = compound.func_150295_c("Frames", 10);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound item = list.func_150305_b(i);
            Frame frame = new Frame();
            frame.parent = this;
            frame.readFromNBT(item);
            this.frames.add(frame);
        }
        this.whileStanding = compound.func_74767_n("WhileStanding");
        this.whileMoving = compound.func_74767_n("WhileWalking");
        this.whileAttacking = compound.func_74767_n("WhileAttacking");
        this.currentFrame = compound.func_74762_e("CurrentFrame");
        this.currentFrameTime = compound.func_74762_e("CurrentFrameTime");
        this.name = preservedName;
        this.id = -1;
    }
}

