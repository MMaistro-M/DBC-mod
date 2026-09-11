/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package riskyken.armourersWorkshop.api.common.skin;

import java.io.InputStream;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;

public interface ISkinDataHandler {
    public void setSkinOnPlayer(EntityPlayer var1, ItemStack var2, int var3);

    @Deprecated
    public boolean setSkinOnPlayer(EntityPlayer var1, ItemStack var2);

    public ItemStack getSkinFormPlayer(EntityPlayer var1, ISkinType var2, int var3);

    @Deprecated
    public ItemStack getSkinFormPlayer(EntityPlayer var1, ISkinType var2);

    public void removeSkinFromPlayer(EntityPlayer var1, ISkinType var2, int var3);

    @Deprecated
    public void removeSkinFromPlayer(EntityPlayer var1, ISkinType var2);

    public boolean isValidEquipmentSkin(ItemStack var1);

    public boolean stackHasSkinPointer(ItemStack var1);

    public ISkinPointer getSkinPointerFromStack(ItemStack var1);

    public void saveSkinPointerOnStack(ISkinPointer var1, ItemStack var2);

    public boolean compoundHasSkinPointer(NBTTagCompound var1);

    public ISkinPointer readSkinPointerFromCompound(NBTTagCompound var1);

    public void writeSkinPointerToCompound(ISkinPointer var1, NBTTagCompound var2);

    public ISkinPointer addSkinToCache(InputStream var1);

    public boolean isArmourRenderOverridden(EntityPlayer var1, int var2);

    public void setItemAsSkinnable(Item var1);
}

