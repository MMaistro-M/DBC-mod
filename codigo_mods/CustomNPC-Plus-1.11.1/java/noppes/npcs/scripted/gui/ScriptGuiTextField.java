/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.scripted.gui;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.gui.ITextField;
import noppes.npcs.scripted.gui.ScriptGuiComponent;

public class ScriptGuiTextField
extends ScriptGuiComponent
implements ITextField {
    int width;
    int height;
    String defaultText;

    public ScriptGuiTextField() {
    }

    public ScriptGuiTextField(int id, int x, int y, int width, int height) {
        this.setID(id);
        this.setPos(x, y);
        this.setSize(width, height);
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
    public ITextField setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    @Override
    public String getText() {
        return this.defaultText;
    }

    @Override
    public ITextField setText(String defaultText) {
        this.defaultText = defaultText;
        return this;
    }

    @Override
    public int getType() {
        return 3;
    }

    @Override
    public NBTTagCompound toNBT(NBTTagCompound nbt) {
        super.toNBT(nbt);
        nbt.func_74783_a("size", new int[]{this.width, this.height});
        if (this.defaultText != null && !this.defaultText.isEmpty()) {
            nbt.func_74778_a("default", this.defaultText);
        }
        return nbt;
    }

    @Override
    public ScriptGuiComponent fromNBT(NBTTagCompound nbt) {
        super.fromNBT(nbt);
        this.setSize(nbt.func_74759_k("size")[0], nbt.func_74759_k("size")[1]);
        if (nbt.func_74764_b("default")) {
            this.setText(nbt.func_74779_i("default"));
        }
        return this;
    }
}

