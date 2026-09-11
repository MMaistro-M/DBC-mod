/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.scripted.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.gui.IButton;
import noppes.npcs.api.gui.ICustomGui;
import noppes.npcs.api.gui.ICustomGuiComponent;
import noppes.npcs.api.gui.IItemSlot;
import noppes.npcs.api.gui.ILabel;
import noppes.npcs.api.gui.ILine;
import noppes.npcs.api.gui.IScroll;
import noppes.npcs.api.gui.ITextField;
import noppes.npcs.api.gui.ITexturedRect;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.controllers.CustomGuiController;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.data.PlayerDataScript;
import noppes.npcs.scripted.gui.ScriptGuiButton;
import noppes.npcs.scripted.gui.ScriptGuiComponent;
import noppes.npcs.scripted.gui.ScriptGuiItemSlot;
import noppes.npcs.scripted.gui.ScriptGuiLabel;
import noppes.npcs.scripted.gui.ScriptGuiLine;
import noppes.npcs.scripted.gui.ScriptGuiScroll;
import noppes.npcs.scripted.gui.ScriptGuiTextField;
import noppes.npcs.scripted.gui.ScriptGuiTexturedRect;

public class ScriptGui
implements ICustomGui {
    int id;
    int width;
    int height;
    int playerInvX;
    int playerInvY;
    boolean pauseGame;
    boolean showPlayerInv;
    boolean closeOnEsc = true;
    String backgroundTexture = "";
    HashMap<Integer, ICustomGuiComponent> components = new HashMap();
    List<IItemSlot> slots = new ArrayList<IItemSlot>();

    public ScriptGui() {
    }

    public ScriptGui(int id, int width, int height, boolean pauseGame) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.pauseGame = pauseGame;
    }

    @Override
    public int getID() {
        return this.id;
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
    public List<ICustomGuiComponent> getComponents() {
        return new ArrayList<ICustomGuiComponent>(this.components.values());
    }

    @Override
    public void clear() {
        this.components.clear();
    }

    @Override
    public List<IItemSlot> getSlots() {
        return this.slots;
    }

    public PlayerDataScript getScriptHandler(EntityPlayer player) {
        return ScriptController.Instance.getPlayerScripts(player);
    }

    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setCloseOnEscape(boolean close) {
        this.closeOnEsc = close;
    }

    public boolean doesCloseOnEscape() {
        return this.closeOnEsc;
    }

    @Override
    public void setDoesPauseGame(boolean pauseGame) {
        this.pauseGame = pauseGame;
    }

    @Override
    public boolean doesPauseGame() {
        return this.pauseGame;
    }

    @Override
    public void setBackgroundTexture(String resourceLocation) {
        this.backgroundTexture = resourceLocation;
    }

    @Override
    public String getBackgroundTexture() {
        return this.backgroundTexture;
    }

    @Override
    public IButton addButton(int id, String label, int x, int y) {
        ScriptGuiButton component = new ScriptGuiButton(id, label, x, y);
        this.updateComponent(component);
        return (IButton)this.getComponent(id);
    }

    @Override
    public IButton addButton(int id, String label, int x, int y, int width, int height) {
        ScriptGuiButton component = new ScriptGuiButton(id, label, x, y, width, height);
        this.updateComponent(component);
        return (IButton)this.getComponent(id);
    }

    @Override
    public IButton addTexturedButton(int id, String label, int x, int y, int width, int height, String texture) {
        ScriptGuiButton component = new ScriptGuiButton(id, label, x, y, width, height, texture);
        this.updateComponent(component);
        return (IButton)this.getComponent(id);
    }

    @Override
    public IButton addTexturedButton(int id, String label, int x, int y, int width, int height, String texture, int textureX, int textureY) {
        ScriptGuiButton component = new ScriptGuiButton(id, label, x, y, width, height, texture, textureX, textureY);
        this.updateComponent(component);
        return (IButton)this.getComponent(id);
    }

    @Override
    public ILabel addLabel(int id, String label, int x, int y, int width, int height) {
        ScriptGuiLabel component = new ScriptGuiLabel(id, label, x, y, width, height);
        this.updateComponent(component);
        return (ILabel)this.getComponent(id);
    }

    @Override
    public ILabel addLabel(int id, String label, int x, int y, int width, int height, int color) {
        ScriptGuiLabel component = new ScriptGuiLabel(id, label, x, y, width, height, color);
        this.updateComponent(component);
        return (ILabel)this.getComponent(id);
    }

    @Override
    public ITextField addTextField(int id, int x, int y, int width, int height) {
        ScriptGuiTextField component = new ScriptGuiTextField(id, x, y, width, height);
        this.updateComponent(component);
        return (ITextField)this.getComponent(id);
    }

    @Override
    public ITexturedRect addTexturedRect(int id, String texture, int x, int y, int width, int height) {
        ScriptGuiTexturedRect component = new ScriptGuiTexturedRect(id, texture, x, y, width, height);
        this.updateComponent(component);
        return (ITexturedRect)this.getComponent(id);
    }

    @Override
    public ITexturedRect addTexturedRect(int id, String texture, int x, int y, int width, int height, int textureX, int textureY) {
        ScriptGuiTexturedRect component = new ScriptGuiTexturedRect(id, texture, x, y, width, height, textureX, textureY);
        this.updateComponent(component);
        return (ITexturedRect)this.getComponent(id);
    }

    @Override
    public IItemSlot addItemSlot(int id, int x, int y) {
        ScriptGuiItemSlot slot = new ScriptGuiItemSlot(id, x, y);
        this.updateComponent(slot);
        return (IItemSlot)this.getComponent(id);
    }

    @Override
    public IItemSlot addItemSlot(int id, int x, int y, IItemStack stack) {
        ScriptGuiItemSlot slot = new ScriptGuiItemSlot(id, x, y, stack);
        this.updateComponent(slot);
        return (IItemSlot)this.getComponent(id);
    }

    @Override
    public IItemSlot addItemSlot(int x, int y) {
        return this.addItemSlot(-1, x, y);
    }

    @Override
    public IItemSlot addItemSlot(int x, int y, IItemStack stack) {
        return this.addItemSlot(-1, x, y, stack);
    }

    @Override
    public IScroll addScroll(int id, int x, int y, int width, int height, String[] list) {
        ScriptGuiScroll component = new ScriptGuiScroll(id, x, y, width, height, list);
        this.updateComponent(component);
        return (IScroll)this.getComponent(id);
    }

    @Override
    public ILine addLine(int id, int x1, int y1, int x2, int y2, int color, int thickness) {
        ScriptGuiLine component = new ScriptGuiLine(id, x1, y1, x2, y2, color, thickness);
        this.updateComponent(component);
        return (ILine)this.getComponent(id);
    }

    @Override
    public ILine addLine(int id, int x1, int y1, int x2, int y2) {
        ScriptGuiLine component = new ScriptGuiLine(id, x1, y1, x2, y2);
        this.updateComponent(component);
        return (ILine)this.getComponent(id);
    }

    @Override
    public void showPlayerInventory(int x, int y) {
        this.showPlayerInv = true;
        this.playerInvX = x;
        this.playerInvY = y;
    }

    @Override
    public ICustomGuiComponent getComponent(int componentID) {
        return this.components.get(componentID);
    }

    @Override
    public void removeComponent(int componentID) {
        if (this.components.containsKey(componentID)) {
            ICustomGuiComponent component = this.getComponent(componentID);
            if (component instanceof IItemSlot) {
                for (IItemSlot slot : this.slots) {
                    if (slot.getID() != componentID) continue;
                    this.slots.remove(slot);
                    break;
                }
            }
            this.components.remove(componentID);
        }
    }

    @Override
    public void updateComponent(ICustomGuiComponent component) {
        if (component != null) {
            this.removeComponent(component.getID());
            this.components.put(component.getID(), component);
            if (component instanceof IItemSlot) {
                this.slots.add((IItemSlot)component);
            }
        }
    }

    @Override
    public void update(IPlayer player) {
        CustomGuiController.updateGui(player, this);
    }

    @Override
    public boolean getShowPlayerInv() {
        return this.showPlayerInv;
    }

    @Override
    public int getPlayerInvX() {
        return this.playerInvX;
    }

    @Override
    public int getPlayerInvY() {
        return this.playerInvY;
    }

    private int getMaxId() {
        int max = 0;
        for (ICustomGuiComponent component : this.getComponents()) {
            if (component.getID() < max) continue;
            max = component.getID();
        }
        return max;
    }

    @Override
    public ICustomGui fromNBT(NBTTagCompound tag) {
        this.id = tag.func_74762_e("id");
        this.width = tag.func_74759_k("size")[0];
        this.height = tag.func_74759_k("size")[1];
        this.pauseGame = tag.func_74767_n("pause");
        this.backgroundTexture = tag.func_74779_i("bgTexture");
        this.closeOnEsc = tag.func_74767_n("closeOnEsc");
        NBTTagList list = tag.func_150295_c("components", 10);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound b = list.func_150305_b(i);
            ScriptGuiComponent component = ScriptGuiComponent.createFromNBT(b);
            if (component == null || component instanceof ScriptGuiItemSlot) continue;
            this.components.put(component.getID(), component);
        }
        ArrayList<IItemSlot> slots = new ArrayList<IItemSlot>();
        list = tag.func_150295_c("slots", 10);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound slotNbt = list.func_150305_b(i);
            int id = slotNbt.func_74762_e("id");
            if (!this.components.containsKey(id)) {
                this.components.put(id, ScriptGuiComponent.createFromNBT(slotNbt));
            } else {
                this.components.get(id).fromNBT(slotNbt);
            }
            ScriptGuiItemSlot slot = (ScriptGuiItemSlot)this.components.get(id);
            slots.add(slot);
        }
        this.slots = slots;
        this.showPlayerInv = tag.func_74767_n("showPlayerInv");
        if (this.showPlayerInv) {
            this.playerInvX = tag.func_74759_k("pInvPos")[0];
            this.playerInvY = tag.func_74759_k("pInvPos")[1];
        }
        return this;
    }

    @Override
    public NBTTagCompound toNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.func_74768_a("id", this.id);
        tag.func_74783_a("size", new int[]{this.width, this.height});
        tag.func_74757_a("pause", this.pauseGame);
        tag.func_74778_a("bgTexture", this.backgroundTexture);
        tag.func_74757_a("closeOnEsc", this.closeOnEsc);
        NBTTagList list = new NBTTagList();
        for (ICustomGuiComponent iCustomGuiComponent : this.components.values()) {
            list.func_74742_a((NBTBase)iCustomGuiComponent.toNBT(new NBTTagCompound()));
        }
        tag.func_74782_a("components", (NBTBase)list);
        list = new NBTTagList();
        for (ICustomGuiComponent iCustomGuiComponent : this.slots) {
            if (iCustomGuiComponent.getID() == -1) {
                iCustomGuiComponent.setID(this.getMaxId() + 1);
            }
            list.func_74742_a((NBTBase)iCustomGuiComponent.toNBT(new NBTTagCompound()));
        }
        tag.func_74782_a("slots", (NBTBase)list);
        tag.func_74757_a("showPlayerInv", this.showPlayerInv);
        if (this.showPlayerInv) {
            tag.func_74783_a("pInvPos", new int[]{this.playerInvX, this.playerInvY});
        }
        return tag;
    }
}

