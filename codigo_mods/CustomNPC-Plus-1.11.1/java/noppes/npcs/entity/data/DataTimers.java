/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.entity.data;

import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.EventHooks;
import noppes.npcs.api.ITimers;
import noppes.npcs.blocks.tiles.TileScripted;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.scripted.CustomNPCsException;

public class DataTimers
implements ITimers {
    private final Object parent;
    private HashMap<Integer, Timer> timers = new HashMap();

    public DataTimers(Object parent) {
        this.parent = parent;
    }

    @Override
    public int[] timerIds() {
        Integer[] ids = this.timers.keySet().toArray(new Integer[0]);
        int[] intIds = new int[ids.length];
        for (int i = 0; i < ids.length; ++i) {
            intIds[i] = ids[i];
        }
        return intIds;
    }

    @Override
    public void start(int id, int ticks, boolean repeat) {
        if (this.timers.containsKey(id)) {
            throw new CustomNPCsException("There is already a timer with id: " + id, new Object[0]);
        }
        this.timers.put(id, new Timer(id, ticks, repeat));
    }

    @Override
    public void forceStart(int id, int ticks, boolean repeat) {
        this.timers.put(id, new Timer(id, ticks, repeat));
    }

    @Override
    public boolean has(int id) {
        return this.timers.containsKey(id);
    }

    @Override
    public boolean stop(int id) {
        return this.timers.remove(id) != null;
    }

    @Override
    public void reset(int id) {
        this.timerException(id);
        Timer timer = this.timers.get(id);
        timer.ticks = 0;
    }

    @Override
    public int ticks(int id) {
        this.timerException(id);
        return this.timers.get(id).ticks;
    }

    @Override
    public void setTicks(int id, int ticks) {
        this.timerException(id);
        Timer timer = this.timers.get(id);
        if (ticks < 0) {
            ticks = 0;
        }
        if (ticks > timer.timerTicks) {
            ticks = timer.timerTicks;
        }
        timer.ticks = ticks;
    }

    @Override
    public int maxTicks(int id) {
        this.timerException(id);
        return this.timers.get(id).timerTicks;
    }

    @Override
    public void setMaxTicks(int id, int maxTicks) {
        this.timerException(id);
        Timer timer = this.timers.get(id);
        if (maxTicks < 0) {
            maxTicks = 0;
        }
        if (timer.ticks > maxTicks) {
            timer.ticks = maxTicks;
        }
        timer.timerTicks = maxTicks;
    }

    @Override
    public boolean repeats(int id) {
        this.timerException(id);
        return this.timers.get(id).repeat;
    }

    @Override
    public void setRepeats(int id, boolean repeat) {
        this.timerException(id);
        this.timers.get(id).repeat = repeat;
    }

    public void writeToNBT(NBTTagCompound compound) {
        NBTTagList list = new NBTTagList();
        for (Timer timer : this.timers.values()) {
            NBTTagCompound c = new NBTTagCompound();
            c.func_74768_a("ID", timer.id);
            c.func_74768_a("TimerTicks", timer.timerTicks);
            c.func_74757_a("Repeat", timer.repeat);
            c.func_74768_a("Ticks", timer.ticks);
            list.func_74742_a((NBTBase)c);
        }
        compound.func_74782_a("NpcsTimers", (NBTBase)list);
    }

    private void timerException(int id) {
        if (!this.timers.containsKey(id)) {
            throw new CustomNPCsException("There is no timer with id: " + id, new Object[0]);
        }
    }

    public void readFromNBT(NBTTagCompound compound) {
        HashMap<Integer, Timer> timers = new HashMap<Integer, Timer>();
        NBTTagList list = compound.func_150295_c("NpcsTimers", 10);
        if (list != null) {
            for (int i = 0; i < list.func_74745_c(); ++i) {
                NBTTagCompound c = list.func_150305_b(i);
                Timer t = new Timer(c.func_74762_e("ID"), c.func_74762_e("TimerTicks"), c.func_74767_n("Repeat"));
                t.ticks = c.func_74762_e("Ticks");
                timers.put(t.id, t);
            }
        }
        this.timers = timers;
    }

    public void update() {
        for (Timer timer : new ArrayList<Timer>(this.timers.values())) {
            timer.update();
        }
    }

    @Override
    public void clear() {
        this.timers = new HashMap();
    }

    @Override
    public int size() {
        return this.timers.size();
    }

    class Timer {
        public int id;
        private boolean repeat;
        private int timerTicks;
        private int ticks;

        public Timer(int id, int ticks, boolean repeat) {
            this.id = id;
            this.repeat = repeat;
            this.timerTicks = ticks;
            this.ticks = ticks;
        }

        public void update() {
            if (this.ticks-- <= 0) {
                if (this.repeat) {
                    this.ticks = this.timerTicks;
                } else {
                    DataTimers.this.stop(this.id);
                }
                Object ob = DataTimers.this.parent;
                if (ob instanceof EntityNPCInterface) {
                    EventHooks.onNPCTimer((EntityNPCInterface)((Object)ob), this.id);
                } else if (ob instanceof PlayerData) {
                    EventHooks.onPlayerTimer((PlayerData)ob, this.id);
                } else if (ob instanceof TileScripted) {
                    EventHooks.onScriptBlockTimer((TileScripted)ob, this.id);
                }
            }
        }
    }
}

