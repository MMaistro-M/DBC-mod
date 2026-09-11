/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.handler.data.IDialogImage;

public class DialogImage
implements IDialogImage {
    public int id;
    public String texture = "";
    public int x = 0;
    public int y = 0;
    public int width = 0;
    public int height = 0;
    public int textureX = 0;
    public int textureY = 0;
    public float scale = 1.0f;
    public int color = 0xFFFFFF;
    public int selectedColor = 0xFFFFFF;
    public float alpha = 1.0f;
    public float rotation = 0.0f;
    public int imageType = 1;
    public int alignment = 0;

    public DialogImage() {
    }

    public DialogImage(int id) {
        this.id = id;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74768_a("ID", this.id);
        compound.func_74778_a("Texture", this.texture);
        compound.func_74768_a("PosX", this.x);
        compound.func_74768_a("PosY", this.y);
        compound.func_74768_a("Width", this.width);
        compound.func_74768_a("Height", this.height);
        compound.func_74768_a("TextureX", this.textureX);
        compound.func_74768_a("TextureY", this.textureY);
        compound.func_74776_a("Scale", this.scale);
        compound.func_74768_a("Color", this.color);
        compound.func_74768_a("SelectedColor", this.selectedColor);
        compound.func_74776_a("Alpha", this.alpha);
        compound.func_74776_a("Rotation", this.rotation);
        compound.func_74768_a("ImageType", this.imageType);
        compound.func_74768_a("Alignment", this.alignment);
        return compound;
    }

    public void readNBT(NBTTagCompound compound) {
        this.id = compound.func_74762_e("ID");
        this.texture = compound.func_74779_i("Texture");
        this.x = compound.func_74762_e("PosX");
        this.y = compound.func_74762_e("PosY");
        this.width = compound.func_74762_e("Width");
        this.height = compound.func_74762_e("Height");
        this.textureX = compound.func_74762_e("TextureX");
        this.textureY = compound.func_74762_e("TextureY");
        this.scale = compound.func_74760_g("Scale");
        this.color = compound.func_74762_e("Color");
        this.selectedColor = compound.func_74762_e("SelectedColor");
        this.alpha = compound.func_74760_g("Alpha");
        this.rotation = compound.func_74760_g("Rotation");
        this.imageType = compound.func_74762_e("ImageType");
        this.alignment = compound.func_74762_e("Alignment");
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setTexture(String texture) {
        this.texture = texture;
    }

    @Override
    public String getTexture() {
        return this.texture;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setWidthHeight(int width, int height) {
        this.width = width;
        this.height = height;
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
    public void setTextureOffset(int offsetX, int offsetY) {
        this.textureX = offsetX;
        this.textureY = offsetY;
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
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getColor() {
        return this.color;
    }

    @Override
    public void setSelectedColor(int color) {
        this.selectedColor = color;
    }

    @Override
    public int getSelectedColor() {
        return this.selectedColor;
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
    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    @Override
    public float getAlpha() {
        return this.alpha;
    }

    @Override
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public float getRotation() {
        return this.rotation;
    }

    @Override
    public void setImageType(int imageType) {
        this.imageType = imageType;
    }

    @Override
    public int getImageType() {
        return this.imageType;
    }

    @Override
    public void setAlignment(int alignment) {
        this.alignment = alignment;
    }

    @Override
    public int getAlignment() {
        return this.alignment;
    }
}

