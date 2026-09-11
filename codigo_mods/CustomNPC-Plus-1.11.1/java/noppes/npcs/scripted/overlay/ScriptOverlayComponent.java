/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.scripted.overlay;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.overlay.ICustomOverlayComponent;
import noppes.npcs.scripted.overlay.ScriptOverlayLabel;
import noppes.npcs.scripted.overlay.ScriptOverlayLine;
import noppes.npcs.scripted.overlay.ScriptOverlayTexturedRect;

public abstract class ScriptOverlayComponent
implements ICustomOverlayComponent {
    int id;
    int posX;
    int posY;
    int alignment = 0;
    int color = 0xFFFFFF;
    float alpha = 1.0f;
    float rotation = 0.0f;

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public ICustomOverlayComponent setID(int id) {
        this.id = id;
        return this;
    }

    @Override
    public int getPosX() {
        return this.posX;
    }

    @Override
    public int getPosY() {
        return this.posY;
    }

    @Override
    public ICustomOverlayComponent setPos(int x, int y) {
        this.posX = x;
        this.posY = y;
        return this;
    }

    @Override
    public int getAlignment() {
        return this.alignment;
    }

    @Override
    public void setAlignment(int alignment) {
        this.alignment = alignment;
    }

    @Override
    public int getColor() {
        return this.color;
    }

    @Override
    public ICustomOverlayComponent setColor(int color) {
        this.color = color;
        return this;
    }

    @Override
    public float getAlpha() {
        return this.alpha;
    }

    @Override
    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    @Override
    public float getRotation() {
        return this.rotation;
    }

    @Override
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public abstract int getType();

    @Override
    public NBTTagCompound toNBT(NBTTagCompound nbt) {
        nbt.func_74768_a("id", this.id);
        nbt.func_74783_a("pos", new int[]{this.posX, this.posY});
        nbt.func_74768_a("alignment", this.alignment);
        nbt.func_74768_a("color", this.color);
        nbt.func_74776_a("alpha", this.alpha);
        nbt.func_74776_a("rotation", this.rotation);
        nbt.func_74768_a("type", this.getType());
        return nbt;
    }

    @Override
    public ScriptOverlayComponent fromNBT(NBTTagCompound nbt) {
        this.setID(nbt.func_74762_e("id"));
        this.setPos(nbt.func_74759_k("pos")[0], nbt.func_74759_k("pos")[1]);
        this.setAlignment(nbt.func_74762_e("alignment"));
        this.setColor(nbt.func_74762_e("color"));
        this.setAlpha(nbt.func_74760_g("alpha"));
        this.setRotation(nbt.func_74762_e("rotation"));
        return this;
    }

    public static ScriptOverlayComponent createFromNBT(NBTTagCompound nbt) {
        switch (nbt.func_74762_e("type")) {
            case 0: {
                return new ScriptOverlayTexturedRect().fromNBT(nbt);
            }
            case 1: {
                return new ScriptOverlayLabel().fromNBT(nbt);
            }
            case 2: {
                return new ScriptOverlayLine().fromNBT(nbt);
            }
        }
        return null;
    }
}

