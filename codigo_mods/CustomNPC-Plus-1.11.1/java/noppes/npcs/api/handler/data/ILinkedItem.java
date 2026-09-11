/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import noppes.npcs.api.item.IItemStack;

public interface ILinkedItem {
    public ILinkedItem save();

    public IItemStack createStack();

    public int getId();

    public void setId(int var1);

    public int getVersion();

    public void setVersion(int var1);

    public String getName();

    public void setName(String var1);

    public double getDurabilityValue();

    public void setDurabilityValue(double var1);

    public int getStackSize();

    public void setStackSize(int var1);

    public int getMaxItemUseDuration();

    public void setMaxItemUseDuration(int var1);

    public int getItemUseAction();

    public void setItemUseAction(int var1);

    public boolean isNormalItem();

    public void setNormalItem(boolean var1);

    public boolean isTool();

    public void setTool(boolean var1);

    public int getDigSpeed();

    public void setDigSpeed(int var1);

    public int getArmorType();

    public void setArmorType(int var1);

    public int getEnchantability();

    public void setEnchantability(int var1);

    public int getAttackSpeed();

    public void setAttackSpeed(int var1);
}

