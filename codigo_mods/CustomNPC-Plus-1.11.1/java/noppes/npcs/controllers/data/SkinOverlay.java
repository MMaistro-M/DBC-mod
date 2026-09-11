/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.ISkinOverlay;
import noppes.npcs.entity.data.DataSkinOverlays;

public class SkinOverlay
implements ISkinOverlay {
    public DataSkinOverlays parent;
    public String texture;
    public boolean glow = true;
    public boolean blend = true;
    public float alpha = 1.0f;
    public float size = 1.0f;
    public int color = 0xFFFFFF;
    public float speedX = 0.0f;
    public float speedY = 0.0f;
    public float scaleX = 1.0f;
    public float scaleY = 1.0f;
    public float offsetX = 0.0f;
    public float offsetY = 0.0f;
    public float offsetZ = 0.0f;
    public long ticks = 0L;

    public SkinOverlay() {
    }

    public SkinOverlay(String texture) {
        this.texture = texture;
    }

    @Override
    public void setTexture(String texture) {
        this.texture = texture;
        this.updateClient();
    }

    @Override
    public String getTexture() {
        return this.texture;
    }

    @Override
    public void setGlow(boolean glow) {
        this.glow = glow;
        this.updateClient();
    }

    @Override
    public boolean getGlow() {
        return this.glow;
    }

    @Override
    public void setBlend(boolean blend) {
        this.blend = blend;
        this.updateClient();
    }

    @Override
    public boolean getBlend() {
        return this.blend;
    }

    @Override
    public void setAlpha(float alpha) {
        this.alpha = alpha;
        this.updateClient();
    }

    @Override
    public float getAlpha() {
        return this.alpha;
    }

    @Override
    public void setSize(float size) {
        this.size = size;
        this.updateClient();
    }

    @Override
    public float getSize() {
        return this.size;
    }

    @Override
    public void setColor(int color) {
        this.color = Math.max(0, color);
        this.updateClient();
    }

    @Override
    public int getColor() {
        return this.color;
    }

    @Override
    public void setTextureScale(float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.updateClient();
    }

    @Override
    public float getTextureScaleX() {
        return this.scaleX;
    }

    @Override
    public float getTextureScaleY() {
        return this.scaleY;
    }

    @Override
    public void setSpeed(float speedX, float speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
        this.updateClient();
    }

    @Override
    public float getSpeedX() {
        return this.speedX;
    }

    @Override
    public float getSpeedY() {
        return this.speedY;
    }

    @Override
    public void setOffset(float offsetX, float offsetY, float offsetZ) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.updateClient();
    }

    @Override
    public float getOffsetX() {
        return this.offsetX;
    }

    @Override
    public float getOffsetY() {
        return this.offsetY;
    }

    @Override
    public float getOffsetZ() {
        return this.offsetZ;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.texture = compound.func_74779_i("SkinOverlayTexture");
        this.glow = compound.func_74767_n("SkinOverlayGlow");
        this.blend = compound.func_74767_n("SkinOverlayBlend");
        this.alpha = compound.func_74760_g("SkinOverlayAlpha");
        this.size = compound.func_74760_g("SkinOverlaySize");
        this.color = compound.func_74764_b("SkinOverlayColor") ? compound.func_74762_e("SkinOverlayColor") : 0xFFFFFF;
        this.speedX = compound.func_74760_g("SkinOverlaySpeedX");
        this.speedY = compound.func_74760_g("SkinOverlaySpeedY");
        this.scaleX = compound.func_74760_g("SkinOverlayScaleX");
        this.scaleY = compound.func_74760_g("SkinOverlayScaleY");
        this.offsetX = compound.func_74760_g("SkinOverlayOffsetX");
        this.offsetY = compound.func_74760_g("SkinOverlayOffsetY");
        this.offsetZ = compound.func_74760_g("SkinOverlayOffsetZ");
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74778_a("SkinOverlayTexture", this.getTexture());
        compound.func_74757_a("SkinOverlayGlow", this.getGlow());
        compound.func_74757_a("SkinOverlayBlend", this.getBlend());
        compound.func_74776_a("SkinOverlayAlpha", this.getAlpha());
        compound.func_74776_a("SkinOverlaySize", this.getSize());
        compound.func_74768_a("SkinOverlayColor", this.getColor());
        compound.func_74776_a("SkinOverlaySpeedX", this.getSpeedX());
        compound.func_74776_a("SkinOverlaySpeedY", this.getSpeedY());
        compound.func_74776_a("SkinOverlayScaleX", this.getTextureScaleX());
        compound.func_74776_a("SkinOverlayScaleY", this.getTextureScaleY());
        compound.func_74776_a("SkinOverlayOffsetX", this.getOffsetX());
        compound.func_74776_a("SkinOverlayOffsetY", this.getOffsetY());
        compound.func_74776_a("SkinOverlayOffsetZ", this.getOffsetZ());
        return compound;
    }

    public static ISkinOverlay overlayFromNBT(NBTTagCompound compound) {
        SkinOverlay overlay = new SkinOverlay();
        overlay.readFromNBT(compound);
        return overlay;
    }

    private void updateClient() {
        if (this.parent != null) {
            this.parent.updateClient();
            this.parent.save();
        }
    }
}

