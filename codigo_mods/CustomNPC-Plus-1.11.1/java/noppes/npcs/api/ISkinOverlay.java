/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api;

public interface ISkinOverlay {
    public void setTexture(String var1);

    public String getTexture();

    public void setGlow(boolean var1);

    public boolean getGlow();

    public void setBlend(boolean var1);

    public boolean getBlend();

    public void setAlpha(float var1);

    public float getAlpha();

    public void setSize(float var1);

    public float getSize();

    public void setColor(int var1);

    public int getColor();

    public void setTextureScale(float var1, float var2);

    public float getTextureScaleX();

    public float getTextureScaleY();

    public void setSpeed(float var1, float var2);

    public float getSpeedX();

    public float getSpeedY();

    public void setOffset(float var1, float var2, float var3);

    public float getOffsetX();

    public float getOffsetY();

    public float getOffsetZ();
}

