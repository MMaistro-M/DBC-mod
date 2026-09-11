/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.item;

import noppes.npcs.api.item.IItemStack;

public interface IItemCustomizable
extends IItemStack {
    public Object getScriptHandler();

    @Override
    public int getMaxStackSize();

    public int getArmorType();

    public boolean isTool();

    public boolean isNormalItem();

    public int getDigSpeed();

    public double getDurabilityValue();

    public int getMaxItemUseDuration();

    public int getItemUseAction();

    public int getEnchantability();

    public int getAttackSpeed();

    public String getTexture();

    public void setTexture(String var1);

    public Boolean getDurabilityShow();

    public void setDurabilityShow(Boolean var1);

    public Integer getDurabilityColor();

    public void setDurabilityColor(Integer var1);

    public Integer getColor();

    public void setColor(Integer var1);

    public void setRotation(Float var1, Float var2, Float var3);

    public void setRotationRate(Float var1, Float var2, Float var3);

    public void setScale(Float var1, Float var2, Float var3);

    public void setTranslate(Float var1, Float var2, Float var3);

    public Float getRotationX();

    public Float getRotationY();

    public Float getRotationZ();

    public Float getRotationXRate();

    public Float getRotationYRate();

    public Float getRotationZRate();

    public Float getScaleX();

    public Float getScaleY();

    public Float getScaleZ();

    public Float getTranslateX();

    public Float getTranslateY();

    public Float getTranslateZ();

    public Boolean isTextureAnimated();

    public void setTextureAnimated(Boolean var1);

    public Integer getFrameCount();

    public void setFrameCount(Integer var1);

    public Integer getFrameTime();

    public void setFrameTime(Integer var1);
}

