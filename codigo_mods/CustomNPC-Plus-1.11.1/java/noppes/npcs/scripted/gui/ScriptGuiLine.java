/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.scripted.gui;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.gui.ILine;
import noppes.npcs.scripted.gui.ScriptGuiComponent;

public class ScriptGuiLine
extends ScriptGuiComponent
implements ILine {
    int x1;
    int y1;
    int x2;
    int y2;
    int thickness = 2;

    public ScriptGuiLine() {
    }

    public ScriptGuiLine(int id, int x1, int y1, int x2, int y2) {
        this.setPos(x1, y1);
        this.setID(id);
        this.hoverText = new String[]{""};
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public ScriptGuiLine(int id, int x1, int y1, int x2, int y2, int color, int thickness) {
        this.setPos(x1, y1);
        this.setID(id);
        this.hoverText = new String[]{""};
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.thickness = thickness;
    }

    @Override
    public int getType() {
        return 6;
    }

    @Override
    public NBTTagCompound toNBT(NBTTagCompound nbt) {
        super.toNBT(nbt);
        nbt.func_74768_a("X1", this.x1);
        nbt.func_74768_a("Y1", this.y1);
        nbt.func_74768_a("X2", this.x2);
        nbt.func_74768_a("Y2", this.y2);
        nbt.func_74768_a("Thickness", this.thickness);
        return nbt;
    }

    @Override
    public ScriptGuiComponent fromNBT(NBTTagCompound nbt) {
        super.fromNBT(nbt);
        this.x1 = nbt.func_74762_e("X1");
        this.y1 = nbt.func_74762_e("Y1");
        this.x2 = nbt.func_74762_e("X2");
        this.y2 = nbt.func_74762_e("Y2");
        this.thickness = nbt.func_74762_e("Thickness");
        return this;
    }

    @Override
    public int getX1() {
        return this.x1;
    }

    @Override
    public int getY1() {
        return this.y1;
    }

    @Override
    public int getX2() {
        return this.x2;
    }

    @Override
    public int getY2() {
        return this.y2;
    }

    @Override
    public int getThickness() {
        return this.thickness;
    }

    @Override
    public void setX1(int x1) {
        this.x1 = x1;
    }

    @Override
    public void setY1(int y1) {
        this.y1 = y1;
    }

    @Override
    public void setX2(int x2) {
        this.x2 = x2;
    }

    @Override
    public void setY2(int y2) {
        this.y2 = y2;
    }

    @Override
    public void setThickness(int thickness) {
        this.thickness = thickness;
    }
}

