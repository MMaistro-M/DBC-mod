/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.api.handler.data.ILine;
import noppes.npcs.api.handler.data.ILines;
import noppes.npcs.controllers.data.Line;

public class Lines
implements ILines {
    private static final Random random = new Random();
    private int lastLine = -1;
    public HashMap<Integer, Line> lines = new HashMap();

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        NBTTagList nbttaglist = new NBTTagList();
        for (int slot : this.lines.keySet()) {
            Line line = this.lines.get(slot);
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74768_a("Slot", slot);
            nbttagcompound.func_74778_a("Line", line.text);
            nbttagcompound.func_74778_a("Song", line.sound);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        compound.func_74782_a("Lines", (NBTBase)nbttaglist);
        return compound;
    }

    public void readNBT(NBTTagCompound compound) {
        NBTTagList nbttaglist = compound.func_150295_c("Lines", 10);
        HashMap<Integer, Line> map = new HashMap<Integer, Line>();
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
            Line line = new Line();
            line.text = nbttagcompound.func_74779_i("Line");
            line.sound = nbttagcompound.func_74779_i("Song");
            map.put(nbttagcompound.func_74762_e("Slot"), line);
        }
        this.lines = map;
    }

    @Override
    public ILine createLine(String text) {
        return new Line(text);
    }

    @Override
    public ILine getLine(boolean isRandom) {
        if (this.lines.isEmpty()) {
            return null;
        }
        if (isRandom) {
            ArrayList<Line> lines = new ArrayList<Line>(this.lines.values());
            return (ILine)lines.get(random.nextInt(lines.size()));
        }
        ++this.lastLine;
        while (true) {
            this.lastLine %= 8;
            Line line = this.lines.get(this.lastLine);
            if (line != null) {
                return line.copy();
            }
            ++this.lastLine;
        }
    }

    @Override
    public ILine getLine(int lineIndex) {
        return this.lines.get(lineIndex);
    }

    @Override
    public void setLine(int lineIndex, ILine line) {
        this.lines.put(lineIndex, (Line)line);
    }

    @Override
    public void removeLine(int lineIndex) {
        this.lines.remove(lineIndex);
    }

    @Override
    public void clear() {
        this.lines.clear();
    }

    @Override
    public boolean isEmpty() {
        return this.lines.isEmpty();
    }

    @Override
    public Integer[] getKeys() {
        return this.lines.keySet().toArray(new Integer[0]);
    }
}

