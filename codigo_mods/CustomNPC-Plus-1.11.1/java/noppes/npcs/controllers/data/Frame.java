/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers.data;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.api.handler.data.IFrame;
import noppes.npcs.api.handler.data.IFramePart;
import noppes.npcs.constants.EnumAnimationPart;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.controllers.data.FramePart;

public class Frame
implements IFrame {
    public Animation parent;
    public HashMap<EnumAnimationPart, FramePart> frameParts = new HashMap();
    public int duration = 0;
    boolean customized = false;
    public float speed = 1.0f;
    public byte smooth = 0;
    private int colorMarker = 0xFFFFFF;
    private String comment = "";

    public Frame() {
    }

    public Frame(int duration) {
        this.duration = duration;
    }

    public Frame(int duration, float speed, byte smooth) {
        this.duration = duration;
        this.speed = speed;
        this.smooth = smooth;
        this.customized = true;
    }

    public HashMap<EnumAnimationPart, FramePart> getFrameMap() {
        return this.frameParts;
    }

    @Override
    public IFramePart[] getParts() {
        return this.frameParts.values().toArray(new IFramePart[0]);
    }

    @Override
    public IFrame addPart(IFramePart partConfig) {
        this.frameParts.put(((FramePart)partConfig).getPart(), (FramePart)partConfig);
        return this;
    }

    @Override
    public IFrame removePart(String partName) {
        try {
            this.frameParts.remove((Object)EnumAnimationPart.valueOf(partName));
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        return this;
    }

    @Override
    public IFrame removePart(int partId) {
        for (EnumAnimationPart part : EnumAnimationPart.values()) {
            this.frameParts.remove((Object)part);
        }
        return this;
    }

    @Override
    public IFrame clearParts() {
        this.frameParts.clear();
        return this;
    }

    @Override
    public int getDuration() {
        return this.duration;
    }

    @Override
    public IFrame setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public boolean isCustomized() {
        return this.customized;
    }

    @Override
    public IFrame setCustomized(boolean customized) {
        this.customized = customized;
        return this;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public IFrame setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    @Override
    public byte smoothType() {
        return this.smooth;
    }

    @Override
    public IFrame setSmooth(byte smooth) {
        this.smooth = smooth;
        return this;
    }

    public int getColorMarker() {
        return this.colorMarker;
    }

    public void setColorMarker(int color) {
        this.colorMarker = color;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.duration = compound.func_74762_e("Duration");
        if (compound.func_74764_b("ColorMarker")) {
            this.setColorMarker(compound.func_74762_e("ColorMarker"));
        }
        if (compound.func_74764_b("Comment")) {
            this.comment = compound.func_74779_i("Comment");
        }
        if (compound.func_74764_b("Speed")) {
            this.customized = true;
            this.speed = compound.func_74760_g("Speed");
        }
        if (compound.func_74764_b("Smooth")) {
            this.customized = true;
            this.smooth = compound.func_74771_c("Smooth");
        }
        if (!this.customized && this.parent != null) {
            this.speed = this.parent.speed;
            this.smooth = this.parent.smooth;
        }
        HashMap<EnumAnimationPart, FramePart> frameParts = new HashMap<EnumAnimationPart, FramePart>();
        NBTTagList list = compound.func_150295_c("FrameParts", 10);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound item = list.func_150305_b(i);
            FramePart framePart = new FramePart();
            framePart.readFromNBT(item);
            if (!framePart.customized) {
                framePart.smooth = this.smooth;
                framePart.speed = this.speed;
            }
            frameParts.put(framePart.part, framePart);
        }
        this.frameParts = frameParts;
    }

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74768_a("Duration", this.duration);
        compound.func_74768_a("ColorMarker", this.colorMarker);
        compound.func_74778_a("Comment", this.comment);
        if (this.customized) {
            compound.func_74776_a("Speed", this.speed);
            compound.func_74774_a("Smooth", this.smooth);
        }
        NBTTagList list = new NBTTagList();
        for (FramePart framePart : this.frameParts.values()) {
            NBTTagCompound item = framePart.writeToNBT();
            list.func_74742_a((NBTBase)item);
        }
        compound.func_74782_a("FrameParts", (NBTBase)list);
        return compound;
    }

    public Frame copy() {
        Frame frame = new Frame(this.duration);
        HashMap<EnumAnimationPart, FramePart> frameParts = this.frameParts;
        for (Map.Entry<EnumAnimationPart, FramePart> entry : frameParts.entrySet()) {
            frame.frameParts.put(entry.getKey(), entry.getValue().copy());
        }
        frame.parent = this.parent;
        frame.duration = this.duration;
        frame.customized = this.customized;
        frame.speed = this.speed;
        frame.smooth = this.smooth;
        frame.colorMarker = this.colorMarker;
        return frame;
    }
}

