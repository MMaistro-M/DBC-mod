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
import noppes.npcs.api.item.IItemLinked;
import noppes.npcs.controllers.LinkedItemController;
import noppes.npcs.controllers.data.IScriptHandler;
import noppes.npcs.controllers.data.LinkedItem;
import noppes.npcs.scripted.item.ScriptCustomizableItem;

public class ScriptLinkedItem
extends ScriptCustomizableItem
implements IItemLinked {
    public LinkedItem linkedItem = new LinkedItem();
    public double durabilityValue = 1.0;
    public int linkedVersion = 1;

    public ScriptLinkedItem(ItemStack item) {
        super(item);
        this.setDefaults();
        this.loadItemData();
    }

    public ScriptLinkedItem(ItemStack item, LinkedItem linkedItem) {
        super(item);
        this.linkedItem = linkedItem.clone();
        this.linkedVersion = this.linkedItem.version;
        this.setDefaults();
        this.saveItemData();
    }

    public void setDefaults() {
        this.itemDisplay.texture = null;
        this.itemDisplay.translateX = null;
        this.itemDisplay.translateY = null;
        this.itemDisplay.translateZ = null;
        this.itemDisplay.itemColor = null;
        this.itemDisplay.scaleX = null;
        this.itemDisplay.scaleY = null;
        this.itemDisplay.scaleZ = null;
        this.itemDisplay.rotationX = null;
        this.itemDisplay.rotationY = null;
        this.itemDisplay.rotationZ = null;
        this.itemDisplay.rotationXRate = null;
        this.itemDisplay.rotationYRate = null;
        this.itemDisplay.rotationZRate = null;
        this.itemDisplay.durabilityShow = null;
        this.itemDisplay.durabilityColor = null;
    }

    @Override
    public LinkedItem getLinkedItem() {
        return LinkedItemController.getInstance().get(this.linkedItem.getId());
    }

    @Override
    public IScriptHandler getScriptHandler() {
        if (this.getLinkedItem() == null) {
            return null;
        }
        return this.getLinkedItem().getScriptHandler();
    }

    @Override
    public int getMaxStackSize() {
        return this.linkedItem.stackSize;
    }

    @Override
    public int getArmorType() {
        return this.linkedItem.armorType;
    }

    @Override
    public boolean isTool() {
        return this.linkedItem.isTool;
    }

    @Override
    public boolean isNormalItem() {
        return this.linkedItem.isNormalItem;
    }

    @Override
    public int getDigSpeed() {
        return this.linkedItem.digSpeed;
    }

    @Override
    public double getDurabilityValue() {
        return this.durabilityValue;
    }

    @Override
    public void setDurabilityValue(float value) {
        this.durabilityValue = value;
        this.saveItemData();
    }

    @Override
    public int getMaxItemUseDuration() {
        return this.linkedItem.maxItemUseDuration;
    }

    @Override
    public int getItemUseAction() {
        return this.linkedItem.itemUseAction;
    }

    @Override
    public int getEnchantability() {
        return this.linkedItem.enchantability;
    }

    @Override
    public int getAttackSpeed() {
        return this.linkedItem.attackSpeed;
    }

    @Override
    public String getTexture() {
        return this.itemDisplay.texture == null ? this.linkedItem.display.texture : this.itemDisplay.texture;
    }

    @Override
    public Boolean getDurabilityShow() {
        return this.itemDisplay.durabilityShow != null ? this.itemDisplay.durabilityShow : this.linkedItem.display.durabilityShow;
    }

    @Override
    public Integer getDurabilityColor() {
        return this.itemDisplay.durabilityColor != null ? this.itemDisplay.durabilityColor : this.linkedItem.display.durabilityColor;
    }

    @Override
    public Integer getColor() {
        return this.itemDisplay.itemColor != null ? this.itemDisplay.itemColor : this.linkedItem.display.itemColor;
    }

    @Override
    public Float getRotationX() {
        return this.itemDisplay.rotationX != null ? this.itemDisplay.rotationX : this.linkedItem.display.rotationX;
    }

    @Override
    public Float getRotationY() {
        return this.itemDisplay.rotationY != null ? this.itemDisplay.rotationY : this.linkedItem.display.rotationY;
    }

    @Override
    public Float getRotationZ() {
        return this.itemDisplay.rotationZ != null ? this.itemDisplay.rotationZ : this.linkedItem.display.rotationZ;
    }

    @Override
    public Float getRotationXRate() {
        return this.itemDisplay.rotationXRate != null ? this.itemDisplay.rotationXRate : this.linkedItem.display.rotationXRate;
    }

    @Override
    public Float getRotationYRate() {
        return this.itemDisplay.rotationYRate != null ? this.itemDisplay.rotationYRate : this.linkedItem.display.rotationYRate;
    }

    @Override
    public Float getRotationZRate() {
        return this.itemDisplay.rotationZRate != null ? this.itemDisplay.rotationZRate : this.linkedItem.display.rotationZRate;
    }

    @Override
    public Float getScaleX() {
        return this.itemDisplay.scaleX != null ? this.itemDisplay.scaleX : this.linkedItem.display.scaleX;
    }

    @Override
    public Float getScaleY() {
        return this.itemDisplay.scaleY != null ? this.itemDisplay.scaleY : this.linkedItem.display.scaleY;
    }

    @Override
    public Float getScaleZ() {
        return this.itemDisplay.scaleZ != null ? this.itemDisplay.scaleZ : this.linkedItem.display.scaleZ;
    }

    @Override
    public Float getTranslateX() {
        return this.itemDisplay.translateX != null ? this.itemDisplay.translateX : this.linkedItem.display.translateX;
    }

    @Override
    public Float getTranslateY() {
        return this.itemDisplay.translateY != null ? this.itemDisplay.translateY : this.linkedItem.display.translateY;
    }

    @Override
    public Float getTranslateZ() {
        return this.itemDisplay.translateZ != null ? this.itemDisplay.translateZ : this.linkedItem.display.translateZ;
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

    @Override
    public void saveItemData() {
        NBTTagCompound c = this.item.func_77978_p();
        if (c == null) {
            c = new NBTTagCompound();
            this.item.func_77982_d(c);
        }
        c.func_74782_a("ItemData", (NBTBase)this.getItemNBT(new NBTTagCompound()));
    }

    @Override
    public void loadItemData() {
        NBTTagCompound c = this.item.func_77978_p();
        if (c != null && !c.func_74775_l("ItemData").func_82582_d()) {
            this.setItemNBT(c.func_74775_l("ItemData"));
        }
    }

    @Override
    public NBTTagCompound getItemNBT(NBTTagCompound compound) {
        this.itemDisplay.writeToNBT(compound);
        compound.func_74782_a("LinkedData", (NBTBase)this.linkedItem.writeToNBT(false));
        compound.func_74768_a("LinkedVersion", this.linkedVersion);
        compound.func_74780_a("DurabilityValue", this.durabilityValue);
        return compound;
    }

    @Override
    public void setItemNBT(NBTTagCompound compound) {
        this.itemDisplay.readFromNBT(compound);
        this.linkedItem.readFromNBT(compound.func_74775_l("LinkedData"));
        this.linkedVersion = compound.func_74762_e("LinkedVersion");
        if (compound.func_74764_b("DurabilityValue")) {
            this.durabilityValue = compound.func_74769_h("DurabilityValue");
        }
    }
}

