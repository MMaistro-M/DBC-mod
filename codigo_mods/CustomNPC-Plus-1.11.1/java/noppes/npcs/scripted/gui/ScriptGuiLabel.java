/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.scripted.gui;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.gui.ILabel;
import noppes.npcs.scripted.gui.ScriptGuiComponent;

public class ScriptGuiLabel
extends ScriptGuiComponent
implements ILabel {
    String label;
    int width;
    int height;
    float scale = 1.0f;
    boolean shadow;

    public ScriptGuiLabel() {
    }

    public ScriptGuiLabel(int id, String label, int x, int y, int width, int height) {
        this.setID(id);
        this.setText(label);
        this.setPos(x, y);
        this.setSize(width, height);
        this.setShadow(false);
    }

    public ScriptGuiLabel(int id, String label, int x, int y, int width, int height, int color) {
        this(id, label, x, y, width, height);
        this.setColor(color);
    }

    @Override
    public String getText() {
        return this.label;
    }

    @Override
    public ILabel setText(String label) {
        this.label = label;
        return this;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public ILabel setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    @Override
    public float getScale() {
        return this.scale;
    }

    @Override
    public ILabel setScale(float scale) {
        this.scale = scale;
        return this;
    }

    @Override
    public boolean getShadow() {
        return this.shadow;
    }

    @Override
    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public NBTTagCompound toNBT(NBTTagCompound nbt) {
        super.toNBT(nbt);
        nbt.func_74778_a("label", this.label);
        nbt.func_74783_a("size", new int[]{this.width, this.height});
        nbt.func_74776_a("scale", this.scale);
        nbt.func_74757_a("shadow", this.shadow);
        return nbt;
    }

    @Override
    public ScriptGuiComponent fromNBT(NBTTagCompound nbt) {
        super.fromNBT(nbt);
        this.setText(nbt.func_74779_i("label"));
        this.setSize(nbt.func_74759_k("size")[0], nbt.func_74759_k("size")[1]);
        this.setScale(nbt.func_74760_g("scale"));
        this.setShadow(nbt.func_74767_n("shadow"));
        return this;
    }
}

