/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.scripted.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.item.IItemCustomizable;
import noppes.npcs.controllers.data.ItemDisplayData;
import noppes.npcs.scripted.item.ScriptItemStack;

public abstract class ScriptCustomizableItem
extends ScriptItemStack
implements IItemCustomizable {
    public final ItemDisplayData itemDisplay = new ItemDisplayData();

    public ScriptCustomizableItem(ItemStack item) {
        super(item);
    }

    @Override
    public abstract int getMaxStackSize();

    @Override
    public abstract int getArmorType();

    @Override
    public abstract boolean isTool();

    @Override
    public abstract boolean isNormalItem();

    @Override
    public abstract int getDigSpeed();

    @Override
    public abstract double getDurabilityValue();

    @Override
    public abstract int getMaxItemUseDuration();

    @Override
    public abstract int getItemUseAction();

    @Override
    public abstract int getEnchantability();

    @Override
    public abstract int getAttackSpeed();

    @Override
    public String getTexture() {
        return this.itemDisplay.texture == null ? "" : this.itemDisplay.texture;
    }

    @Override
    public void setTexture(String texture) {
        if (texture == null) {
            texture = "";
        }
        this.itemDisplay.texture = texture;
        this.saveItemData();
    }

    @Override
    public Boolean getDurabilityShow() {
        return this.itemDisplay.durabilityShow != null ? this.itemDisplay.durabilityShow : false;
    }

    @Override
    public void setDurabilityShow(Boolean bo) {
        this.itemDisplay.durabilityShow = bo;
        this.saveItemData();
    }

    @Override
    public Integer getDurabilityColor() {
        return this.itemDisplay.durabilityColor != null ? this.itemDisplay.durabilityColor : -1;
    }

    @Override
    public void setDurabilityColor(Integer color) {
        this.itemDisplay.durabilityColor = color;
        this.saveItemData();
    }

    @Override
    public Integer getColor() {
        return this.itemDisplay.itemColor != null ? this.itemDisplay.itemColor : 9127187;
    }

    @Override
    public void setColor(Integer color) {
        this.itemDisplay.itemColor = color;
        this.saveItemData();
    }

    @Override
    public void setRotation(Float rotationX, Float rotationY, Float rotationZ) {
        this.itemDisplay.rotationX = rotationX;
        this.itemDisplay.rotationY = rotationY;
        this.itemDisplay.rotationZ = rotationZ;
        this.saveItemData();
    }

    @Override
    public void setRotationRate(Float rotationXRate, Float rotationYRate, Float rotationZRate) {
        this.itemDisplay.rotationXRate = rotationXRate;
        this.itemDisplay.rotationYRate = rotationYRate;
        this.itemDisplay.rotationZRate = rotationZRate;
        this.saveItemData();
    }

    @Override
    public void setScale(Float scaleX, Float scaleY, Float scaleZ) {
        this.itemDisplay.scaleX = scaleX;
        this.itemDisplay.scaleY = scaleY;
        this.itemDisplay.scaleZ = scaleZ;
        this.saveItemData();
    }

    @Override
    public void setTranslate(Float translateX, Float translateY, Float translateZ) {
        this.itemDisplay.translateX = translateX;
        this.itemDisplay.translateY = translateY;
        this.itemDisplay.translateZ = translateZ;
        this.saveItemData();
    }

    @Override
    public Float getRotationX() {
        return Float.valueOf(this.itemDisplay.rotationX != null ? this.itemDisplay.rotationX.floatValue() : 0.0f);
    }

    @Override
    public Float getRotationY() {
        return Float.valueOf(this.itemDisplay.rotationY != null ? this.itemDisplay.rotationY.floatValue() : 0.0f);
    }

    @Override
    public Float getRotationZ() {
        return Float.valueOf(this.itemDisplay.rotationZ != null ? this.itemDisplay.rotationZ.floatValue() : 0.0f);
    }

    @Override
    public Float getRotationXRate() {
        return Float.valueOf(this.itemDisplay.rotationXRate != null ? this.itemDisplay.rotationXRate.floatValue() : 0.0f);
    }

    @Override
    public Float getRotationYRate() {
        return Float.valueOf(this.itemDisplay.rotationYRate != null ? this.itemDisplay.rotationYRate.floatValue() : 0.0f);
    }

    @Override
    public Float getRotationZRate() {
        return Float.valueOf(this.itemDisplay.rotationZRate != null ? this.itemDisplay.rotationZRate.floatValue() : 0.0f);
    }

    @Override
    public Float getScaleX() {
        return Float.valueOf(this.itemDisplay.scaleX != null ? this.itemDisplay.scaleX.floatValue() : 0.0f);
    }

    @Override
    public Float getScaleY() {
        return Float.valueOf(this.itemDisplay.scaleY != null ? this.itemDisplay.scaleY.floatValue() : 0.0f);
    }

    @Override
    public Float getScaleZ() {
        return Float.valueOf(this.itemDisplay.scaleZ != null ? this.itemDisplay.scaleZ.floatValue() : 0.0f);
    }

    @Override
    public Float getTranslateX() {
        return Float.valueOf(this.itemDisplay.translateX != null ? this.itemDisplay.translateX.floatValue() : 0.0f);
    }

    @Override
    public Float getTranslateY() {
        return Float.valueOf(this.itemDisplay.translateY != null ? this.itemDisplay.translateY.floatValue() : 0.0f);
    }

    @Override
    public Float getTranslateZ() {
        return Float.valueOf(this.itemDisplay.translateZ != null ? this.itemDisplay.translateZ.floatValue() : 0.0f);
    }

    @Override
    public Boolean isTextureAnimated() {
        return this.itemDisplay.animated != null ? this.itemDisplay.animated : false;
    }

    @Override
    public void setTextureAnimated(Boolean animated) {
        this.itemDisplay.animated = animated;
        this.saveItemData();
    }

    @Override
    public Integer getFrameCount() {
        return this.itemDisplay.frameCount != null ? this.itemDisplay.frameCount : 1;
    }

    @Override
    public void setFrameCount(Integer frameCount) {
        this.itemDisplay.frameCount = frameCount != null ? Math.max(1, frameCount) : 1;
        this.saveItemData();
    }

    @Override
    public Integer getFrameTime() {
        return this.itemDisplay.frametime != null ? this.itemDisplay.frametime : 2;
    }

    @Override
    public void setFrameTime(Integer frametime) {
        this.itemDisplay.frametime = frametime != null ? Math.max(1, frametime) : 2;
        this.saveItemData();
    }

    @Override
    public NBTTagCompound getMCNbt() {
        NBTTagCompound compound = super.getMCNbt();
        compound.func_74782_a("ItemData", (NBTBase)this.getItemNBT(new NBTTagCompound()));
        return compound;
    }

    @Override
    public void setMCNbt(NBTTagCompound compound) {
        super.setMCNbt(compound);
        this.setItemNBT(compound.func_74775_l("ItemData"));
    }

    public void saveItemData() {
        NBTTagCompound c = this.item.func_77978_p();
        if (c == null) {
            c = new NBTTagCompound();
            this.item.func_77982_d(c);
        }
        c.func_74782_a("ItemData", (NBTBase)this.getItemNBT(new NBTTagCompound()));
    }

    public void loadItemData() {
        NBTTagCompound c = this.item.func_77978_p();
        if (c != null && !c.func_74775_l("ItemData").func_82582_d()) {
            this.setItemNBT(c.func_74775_l("ItemData"));
        }
    }

    public NBTTagCompound getItemNBT(NBTTagCompound compound) {
        this.itemDisplay.writeToNBT(compound);
        return compound;
    }

    public void setItemNBT(NBTTagCompound compound) {
        this.itemDisplay.readFromNBT(compound);
    }
}

