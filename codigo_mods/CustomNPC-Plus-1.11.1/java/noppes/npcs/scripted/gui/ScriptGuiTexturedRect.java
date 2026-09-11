/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.scripted.gui;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.gui.ITexturedRect;
import noppes.npcs.scripted.gui.ScriptGuiComponent;

public class ScriptGuiTexturedRect
extends ScriptGuiComponent
implements ITexturedRect {
    int width;
    int height;
    int textureX;
    int textureY = -1;
    float scale = 1.0f;
    String texture;
    boolean animated = false;
    int frameCount = 1;
    int frametime = 2;

    public ScriptGuiTexturedRect() {
    }

    public ScriptGuiTexturedRect(int id, String texture, int x, int y, int width, int height) {
        this.setID(id);
        this.setTexture(texture);
        this.setPos(x, y);
        this.setSize(width, height);
    }

    public ScriptGuiTexturedRect(int id, String texture, int x, int y, int width, int height, int textureX, int textureY) {
        this(id, texture, x, y, width, height);
        this.setTextureOffset(textureX, textureY);
    }

    @Override
    public String getTexture() {
        return this.texture;
    }

    @Override
    public ITexturedRect setTexture(String texture) {
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
    public ITexturedRect setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    @Override
    public float getScale() {
        return this.scale;
    }

    @Override
    public ITexturedRect setScale(float scale) {
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
    public ITexturedRect setTextureOffset(int offsetX, int offsetY) {
        this.textureX = offsetX;
        this.textureY = offsetY;
        return this;
    }

    @Override
    public boolean isAnimated() {
        return this.animated && this.frameCount > 1;
    }

    @Override
    public int getFrameCount() {
        return this.frameCount;
    }

    @Override
    public int getFrameTime() {
        return this.frametime;
    }

    @Override
    public ITexturedRect setAnimation(int frameCount, int frametime) {
        this.animated = frameCount > 1;
        this.frameCount = Math.max(1, frameCount);
        this.frametime = Math.max(1, frametime);
        return this;
    }

    @Override
    public int getType() {
        return 2;
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
        if (this.animated) {
            nbt.func_74757_a("animated", true);
            nbt.func_74768_a("frameCount", this.frameCount);
            nbt.func_74768_a("frameTime", this.frametime);
        }
        return nbt;
    }

    @Override
    public ScriptGuiComponent fromNBT(NBTTagCompound nbt) {
        super.fromNBT(nbt);
        this.setSize(nbt.func_74759_k("size")[0], nbt.func_74759_k("size")[1]);
        this.setScale(nbt.func_74760_g("scale"));
        this.setTexture(nbt.func_74779_i("texture"));
        if (nbt.func_74764_b("texPos")) {
            this.setTextureOffset(nbt.func_74759_k("texPos")[0], nbt.func_74759_k("texPos")[1]);
        }
        if (nbt.func_74764_b("animated") && nbt.func_74767_n("animated")) {
            this.setAnimation(nbt.func_74764_b("frameCount") ? Math.max(1, nbt.func_74762_e("frameCount")) : 1, nbt.func_74764_b("frameTime") ? Math.max(1, nbt.func_74762_e("frameTime")) : 2);
        }
        return this;
    }
}

