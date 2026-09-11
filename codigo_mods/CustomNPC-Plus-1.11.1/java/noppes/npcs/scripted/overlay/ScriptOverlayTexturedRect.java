/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.scripted.overlay;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.overlay.IOverlayTexturedRect;
import noppes.npcs.scripted.overlay.ScriptOverlayComponent;

public class ScriptOverlayTexturedRect
extends ScriptOverlayComponent
implements IOverlayTexturedRect {
    int width;
    int height;
    int textureX;
    int textureY = -1;
    float scale = 1.0f;
    String texture;

    public ScriptOverlayTexturedRect() {
    }

    public ScriptOverlayTexturedRect(int id, String texture, int x, int y, int width, int height) {
        this.setID(id);
        this.setTexture(texture);
        this.setPos(x, y);
        this.setSize(width, height);
    }

    public ScriptOverlayTexturedRect(int id, String texture, int x, int y, int width, int height, int textureX, int textureY) {
        this(id, texture, x, y, width, height);
        this.setTextureOffset(textureX, textureY);
    }

    @Override
    public String getTexture() {
        return this.texture;
    }

    @Override
    public IOverlayTexturedRect setTexture(String texture) {
        this.texture = texture;
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
    public IOverlayTexturedRect setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    @Override
    public float getScale() {
        return this.scale;
    }

    @Override
    public IOverlayTexturedRect setScale(float scale) {
        this.scale = scale;
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
    public IOverlayTexturedRect setTextureOffset(int offsetX, int offsetY) {
        this.textureX = offsetX;
        this.textureY = offsetY;
        return this;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public NBTTagCompound toNBT(NBTTagCompound nbt) {
        super.toNBT(nbt);
        nbt.func_74783_a("size", new int[]{this.width, this.height});
        nbt.func_74776_a("scale", this.scale);
        nbt.func_74778_a("texture", this.texture);
        if (this.textureX >= 0 && this.textureY >= 0) {
            nbt.func_74783_a("texPos", new int[]{this.textureX, this.textureY});
        }
        return nbt;
    }

    @Override
    public ScriptOverlayComponent fromNBT(NBTTagCompound nbt) {
        super.fromNBT(nbt);
        this.setSize(nbt.func_74759_k("size")[0], nbt.func_74759_k("size")[1]);
        this.setScale(nbt.func_74760_g("scale"));
        this.setTexture(nbt.func_74779_i("texture"));
        if (nbt.func_74764_b("texPos")) {
            this.setTextureOffset(nbt.func_74759_k("texPos")[0], nbt.func_74759_k("texPos")[1]);
        }
        return this;
    }
}

