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
import noppes.npcs.api.gui.ICustomGuiComponent;
import noppes.npcs.scripted.gui.ScriptGuiButton;
import noppes.npcs.scripted.gui.ScriptGuiItemSlot;
import noppes.npcs.scripted.gui.ScriptGuiLabel;
import noppes.npcs.scripted.gui.ScriptGuiLine;
import noppes.npcs.scripted.gui.ScriptGuiScroll;
import noppes.npcs.scripted.gui.ScriptGuiTextField;
import noppes.npcs.scripted.gui.ScriptGuiTexturedRect;

public abstract class ScriptGuiComponent
implements ICustomGuiComponent {
    int id;
    int posX;
    int posY;
    String[] hoverText;
    int color = 0xFFFFFF;
    float alpha = 1.0f;
    float rotation = 0.0f;

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public ICustomGuiComponent setID(int id) {
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
    public ICustomGuiComponent setPos(int x, int y) {
        this.posX = x;
        this.posY = y;
        return this;
    }

    @Override
    public boolean hasHoverText() {
        return this.hoverText != null && this.hoverText.length > 0;
    }

    @Override
    public String[] getHoverText() {
        return this.hoverText;
    }

    @Override
    public ICustomGuiComponent setHoverText(String text) {
        this.hoverText = new String[]{text};
        return this;
    }

    @Override
    public ICustomGuiComponent setHoverText(String[] text) {
        this.hoverText = text;
        return this;
    }

    @Override
    public int getColor() {
        return this.color;
    }

    @Override
    public ICustomGuiComponent setColor(int color) {
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
        nbt.func_74768_a("color", this.color);
        nbt.func_74776_a("alpha", this.alpha);
        nbt.func_74776_a("rotation", this.rotation);
        if (this.hoverText != null) {
            NBTTagList list = new NBTTagList();
            for (String s : this.hoverText) {
                if (s == null || s.isEmpty()) continue;
                list.func_74742_a((NBTBase)new NBTTagString(s));
            }
            if (list.func_74745_c() > 0) {
                nbt.func_74782_a("hover", (NBTBase)list);
            }
        }
        nbt.func_74768_a("type", this.getType());
        return nbt;
    }

    @Override
    public ScriptGuiComponent fromNBT(NBTTagCompound nbt) {
        this.setID(nbt.func_74762_e("id"));
        this.setPos(nbt.func_74759_k("pos")[0], nbt.func_74759_k("pos")[1]);
        this.setColor(nbt.func_74762_e("color"));
        this.setAlpha(nbt.func_74760_g("alpha"));
        this.setRotation(nbt.func_74760_g("rotation"));
        if (nbt.func_74764_b("hover")) {
            NBTTagList list = nbt.func_150295_c("hover", 8);
            String[] hoverText = new String[list.func_74745_c()];
            for (int i = 0; i < list.func_74745_c(); ++i) {
                hoverText[i] = list.func_150307_f(i);
            }
            this.setHoverText(hoverText);
        }
        return this;
    }

    public static ScriptGuiComponent createFromNBT(NBTTagCompound nbt) {
        switch (nbt.func_74762_e("type")) {
            case 0: {
                return new ScriptGuiButton().fromNBT(nbt);
            }
            case 1: {
                return new ScriptGuiLabel().fromNBT(nbt);
            }
            case 2: {
                return new ScriptGuiTexturedRect().fromNBT(nbt);
            }
            case 3: {
                return new ScriptGuiTextField().fromNBT(nbt);
            }
            case 4: {
                return new ScriptGuiScroll().fromNBT(nbt);
            }
            case 5: {
                return new ScriptGuiItemSlot().fromNBT(nbt);
            }
            case 6: {
                return new ScriptGuiLine().fromNBT(nbt);
            }
        }
        return null;
    }
}

