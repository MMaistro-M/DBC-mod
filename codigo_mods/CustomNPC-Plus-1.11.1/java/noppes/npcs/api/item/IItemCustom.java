/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.item;

import noppes.npcs.api.item.IItemCustomizable;

public interface IItemCustom
extends IItemCustomizable {
    public boolean getEnabled();

    public void setEnabled(boolean var1);

    public void setArmorType(int var1);

    public void setIsTool(boolean var1);

    public void setIsNormalItem(boolean var1);

    public void setDigSpeed(int var1);

    public void setMaxStackSize(int var1);

    public void setDurabilityValue(float var1);

    public void setMaxItemUseDuration(int var1);

    public void setItemUseAction(int var1);

    public void setAttackSpeed(int var1);

    public void setEnchantability(int var1);
}

