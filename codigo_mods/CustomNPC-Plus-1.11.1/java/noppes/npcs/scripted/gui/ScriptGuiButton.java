/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.scripted.gui;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.gui.IButton;
import noppes.npcs.api.gui.ICustomGuiComponent;
import noppes.npcs.scripted.gui.ScriptGuiComponent;

public class ScriptGuiButton
extends ScriptGuiComponent
implements IButton {
    int width;
    int height = -1;
    String label;
    String texture;
    int textureX;
    int textureY = -1;
    float scale = 1.0f;
    boolean enabled = true;

    public ScriptGuiButton() {
    }

    public ScriptGuiButton(int id, String label, int x, int y) {
        this.setID(id);
        this.setLabel(label);
        this.setPos(x, y);
    }

    public ScriptGuiButton(int id, String label, int x, int y, int width, int height) {
        this(id, label, x, y);
        this.setSize(width, height);
    }

    public ScriptGuiButton(int id, String label, int x, int y, int width, int height, String texture) {
        this(id, label, x, y, width, height);
        this.setTexture(texture);
    }

    public ScriptGuiButton(int id, String label, int x, int y, int width, int height, String texture, int textureX, int textureY) {
        this(id, label, x, y, width, height, texture);
        this.setTextureOffset(textureX, textureY);
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
    public IButton setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public IButton setLabel(String label) {
        this.label = label;
        return this;
    }

    @Override
    public String getTexture() {
        return this.texture;
    }

    @Override
    public boolean hasTexture() {
        return this.texture != null;
    }

    @Override
    public IButton setTexture(String texture) {
        this.texture = texture;
        return this;
    }

    @Override
    public int getTextureX() {
        return this.textureX;
    }

    @Override
    public int getTextureY() {
        return this.textureY;
    }

    @Override
    public IButton setTextureOffset(int textureX, int textureY) {
        this.textureX = textureX;
        this.textureY = textureY;
        return this;
    }

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
    public void setScale(float scale) {
        this.scale = scale;
    }

    @Override
    public float getScale() {
        return this.scale;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public NBTTagCompound toNBT(NBTTagCompound nbt) {
        super.toNBT(nbt);
        if (this.width > 0 && this.height > 0) {
            nbt.func_74783_a("size", new int[]{this.width, this.height});
        }
        nbt.func_74776_a("scale", this.scale);
        nbt.func_74757_a("enabled", this.enabled);
        nbt.func_74778_a("label", this.label);
        if (this.hasTexture()) {
            nbt.func_74778_a("texture", this.texture);
        }
        if (this.textureX >= 0 && this.textureY >= 0) {
            nbt.func_74783_a("texPos", new int[]{this.textureX, this.textureY});
        }
        return nbt;
    }

    @Override
    public ScriptGuiComponent fromNBT(NBTTagCompound nbt) {
        super.fromNBT(nbt);
        if (nbt.func_74764_b("size")) {
            this.setSize(nbt.func_74759_k("size")[0], nbt.func_74759_k("size")[1]);
        }
        this.setScale(nbt.func_74760_g("scale"));
        this.setEnabled(nbt.func_74767_n("enabled"));
        this.setLabel(nbt.func_74779_i("label"));
        if (nbt.func_74764_b("texture")) {
            this.setTexture(nbt.func_74779_i("texture"));
        }
        if (nbt.func_74764_b("texPos")) {
            this.setTextureOffset(nbt.func_74759_k("texPos")[0], nbt.func_74759_k("texPos")[1]);
        }
        return this;
    }
}

