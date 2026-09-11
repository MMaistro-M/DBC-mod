/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

public interface IDialogImage {
    public int getId();

    public void setTexture(String var1);

    public String getTexture();

    public void setPosition(int var1, int var2);

    public int getX();

    public int getY();

    public void setWidthHeight(int var1, int var2);

    public int getWidth();

    public int getHeight();

    public void setTextureOffset(int var1, int var2);

    public int getTextureX();

    public int getTextureY();

    public void setColor(int var1);

    public int getColor();

    public void setSelectedColor(int var1);

    public int getSelectedColor();

    public void setScale(float var1);

    public float getScale();

    public void setAlpha(float var1);

    public float getAlpha();

    public void setRotation(float var1);

    public float getRotation();

    public void setImageType(int var1);

    public int getImageType();

    public void setAlignment(int var1);

    public int getAlignment();
}

