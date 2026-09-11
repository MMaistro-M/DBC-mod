/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.scripted.overlay;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.overlay.ICustomOverlay;
import noppes.npcs.api.overlay.ICustomOverlayComponent;
import noppes.npcs.api.overlay.IOverlayLabel;
import noppes.npcs.api.overlay.IOverlayLine;
import noppes.npcs.api.overlay.IOverlayTexturedRect;
import noppes.npcs.controllers.CustomGuiController;
import noppes.npcs.scripted.overlay.ScriptOverlayComponent;
import noppes.npcs.scripted.overlay.ScriptOverlayLabel;
import noppes.npcs.scripted.overlay.ScriptOverlayLine;
import noppes.npcs.scripted.overlay.ScriptOverlayTexturedRect;

public class ScriptOverlay
implements ICustomOverlay {
    int id;
    int defaultAlignment = 0;
    List<ICustomOverlayComponent> components = new ArrayList<ICustomOverlayComponent>();

    public ScriptOverlay() {
    }

    public ScriptOverlay(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public List<ICustomOverlayComponent> getComponents() {
        return this.components;
    }

    @Override
    public int getDefaultAlignment() {
        return this.defaultAlignment;
    }

    @Override
    public void setDefaultAlignment(int defaultAlignment) {
        this.defaultAlignment = defaultAlignment;
    }

    @Override
    public IOverlayTexturedRect addTexturedRect(int id, String texture, int x, int y, int width, int height) {
        ScriptOverlayTexturedRect component = new ScriptOverlayTexturedRect(id, texture, x, y, width, height);
        this.components.add(component);
        return (IOverlayTexturedRect)this.components.get(this.components.size() - 1);
    }

    @Override
    public IOverlayTexturedRect addTexturedRect(int id, String texture, int x, int y, int width, int height, int textureX, int textureY) {
        ScriptOverlayTexturedRect component = new ScriptOverlayTexturedRect(id, texture, x, y, width, height, textureX, textureY);
        this.components.add(component);
        return (IOverlayTexturedRect)this.components.get(this.components.size() - 1);
    }

    @Override
    public IOverlayLabel addLabel(int id, String label, int x, int y, int width, int height) {
        ScriptOverlayLabel component = new ScriptOverlayLabel(id, label, x, y, width, height);
        this.components.add(component);
        return (IOverlayLabel)this.components.get(this.components.size() - 1);
    }

    @Override
    public IOverlayLabel addLabel(int id, String label, int x, int y, int width, int height, int color) {
        ScriptOverlayLabel component = new ScriptOverlayLabel(id, label, x, y, width, height, color);
        this.components.add(component);
        return (IOverlayLabel)this.components.get(this.components.size() - 1);
    }

    @Override
    public IOverlayLine addLine(int id, int x1, int y1, int x2, int y2, int color, int thickness) {
        ScriptOverlayLine line = new ScriptOverlayLine(id, x1, y1, x2, y2, color, thickness);
        this.components.add(line);
        return (IOverlayLine)this.components.get(this.components.size() - 1);
    }

    @Override
    public IOverlayLine addLine(int id, int x1, int y1, int x2, int y2) {
        ScriptOverlayLine line = new ScriptOverlayLine(id, x1, y1, x2, y2);
        this.components.add(line);
        return (IOverlayLine)this.components.get(this.components.size() - 1);
    }

    @Override
    public ICustomOverlayComponent getComponent(int componentID) {
        ICustomOverlayComponent component;
        Iterator<ICustomOverlayComponent> var2 = this.components.iterator();
        do {
            if (var2.hasNext()) continue;
            return null;
        } while ((component = var2.next()).getID() != componentID);
        return component;
    }

    @Override
    public void removeComponent(int componentID) {
        for (int i = 0; i < this.components.size(); ++i) {
            if (this.components.get(i).getID() != componentID) continue;
            this.components.remove(i);
            return;
        }
    }

    @Override
    public void updateComponent(ICustomOverlayComponent component) {
        for (int i = 0; i < this.components.size(); ++i) {
            ICustomOverlayComponent c = this.components.get(i);
            if (c.getID() != component.getID()) continue;
            this.components.set(i, component);
            return;
        }
    }

    @Override
    public void update(IPlayer player) {
        CustomGuiController.updateOverlay(player, this);
    }

    @Override
    public ICustomOverlay fromNBT(NBTTagCompound tag) {
        this.id = tag.func_74762_e("id");
        ArrayList<ICustomOverlayComponent> components = new ArrayList<ICustomOverlayComponent>();
        NBTTagList list = tag.func_150295_c("components", 10);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound b = list.func_150305_b(i);
            ScriptOverlayComponent component = ScriptOverlayComponent.createFromNBT(b);
            components.add(component);
        }
        this.components = components;
        return this;
    }

    @Override
    public NBTTagCompound toNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.func_74768_a("id", this.id);
        NBTTagList list = new NBTTagList();
        for (ICustomOverlayComponent c : this.components) {
            list.func_74742_a((NBTBase)((ScriptOverlayComponent)c).toNBT(new NBTTagCompound()));
        }
        tag.func_74782_a("components", (NBTBase)list);
        return tag;
    }
}

