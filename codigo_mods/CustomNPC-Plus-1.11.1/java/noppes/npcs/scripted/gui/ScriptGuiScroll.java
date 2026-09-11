/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 */
package noppes.npcs.scripted.gui;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import noppes.npcs.api.gui.IScroll;
import noppes.npcs.scripted.gui.ScriptGuiComponent;

public class ScriptGuiScroll
extends ScriptGuiComponent
implements IScroll {
    int width;
    int height;
    int defaultSelection = -1;
    String[] list;
    boolean multiSelect = false;

    public ScriptGuiScroll() {
    }

    public ScriptGuiScroll(int id, int x, int y, int width, int height, String[] list) {
        this.setID(id);
        this.setPos(x, y);
        this.setSize(width, height);
        this.setList(list);
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
    public IScroll setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    @Override
    public String[] getList() {
        return this.list;
    }

    @Override
    public IScroll setList(String[] list) {
        this.list = list;
        return this;
    }

    @Override
    public int getDefaultSelection() {
        return this.defaultSelection;
    }

    @Override
    public IScroll setDefaultSelection(int defaultSelection) {
        this.defaultSelection = defaultSelection;
        return this;
    }

    @Override
    public boolean isMultiSelect() {
        return this.multiSelect;
    }

    @Override
    public IScroll setMultiSelect(boolean multiSelect) {
        this.multiSelect = multiSelect;
        return this;
    }

    @Override
    public int getType() {
        return 4;
    }

    @Override
    public NBTTagCompound toNBT(NBTTagCompound nbt) {
        super.toNBT(nbt);
        nbt.func_74783_a("size", new int[]{this.width, this.height});
        if (this.defaultSelection >= 0) {
            nbt.func_74768_a("default", this.defaultSelection);
        }
        NBTTagList list = new NBTTagList();
        for (String s : this.list) {
            list.func_74742_a((NBTBase)new NBTTagString(s));
        }
        nbt.func_74782_a("list", (NBTBase)list);
        nbt.func_74757_a("multiSelect", this.multiSelect);
        return nbt;
    }

    @Override
    public ScriptGuiComponent fromNBT(NBTTagCompound nbt) {
        super.fromNBT(nbt);
        this.setSize(nbt.func_74759_k("size")[0], nbt.func_74759_k("size")[1]);
        if (nbt.func_74764_b("default")) {
            this.setDefaultSelection(nbt.func_74762_e("default"));
        }
        NBTTagList tagList = nbt.func_150295_c("list", 8);
        String[] list = new String[tagList.func_74745_c()];
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            list[i] = tagList.func_150307_f(i);
        }
        this.setList(list);
        this.setMultiSelect(nbt.func_74767_n("multiSelect"));
        return this;
    }
}

