/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import java.util.HashSet;
import java.util.UUID;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomItems;
import noppes.npcs.EventHooks;
import noppes.npcs.api.handler.data.ILinkedItem;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.controllers.LinkedItemController;
import noppes.npcs.controllers.TagController;
import noppes.npcs.controllers.data.ItemDisplayData;
import noppes.npcs.controllers.data.LinkedItemScript;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.item.ScriptLinkedItem;

public class LinkedItem
implements ILinkedItem {
    public static final String LINKED_VERSION_VERSION_TAG = "LinkedVersion";
    public static final String LINKED_DATA_NBT_TAG = "LinkedData";
    public int id = -1;
    public int version = 0;
    public String name;
    public final ItemDisplayData display = new ItemDisplayData();
    public double durabilityValue = 1.0;
    public int stackSize = 64;
    public int maxItemUseDuration = 20;
    public int itemUseAction = 0;
    public boolean isNormalItem = false;
    public boolean isTool = false;
    public int digSpeed = 1;
    public int attackSpeed = 10;
    public int armorType = -2;
    public int enchantability;
    public HashSet<UUID> tagUUIDs = new HashSet();

    public LinkedItem() {
        this("New");
    }

    public LinkedItem(String name) {
        this.name = name;
    }

    public LinkedItemScript getScriptHandler() {
        return (LinkedItemScript)LinkedItemController.getInstance().getScriptHandler(this.getId());
    }

    public NBTTagCompound writeToNBT(boolean saveScripts) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74768_a("Id", this.id);
        compound.func_74768_a("Version", this.version);
        compound.func_74778_a("Name", this.name);
        compound.func_74782_a("Display", (NBTBase)this.display.writeToNBT());
        compound.func_74780_a("DurabilityValue", this.durabilityValue);
        compound.func_74768_a("MaxStackSize", this.stackSize);
        compound.func_74757_a("IsTool", this.isTool);
        compound.func_74757_a("IsNormalItem", this.isNormalItem);
        compound.func_74768_a("DigSpeed", this.digSpeed);
        compound.func_74768_a("AttackSpeed", this.attackSpeed);
        compound.func_74768_a("ArmorType", this.armorType);
        compound.func_74768_a("Enchantability", this.enchantability);
        compound.func_74768_a("MaxItemUseDuration", this.maxItemUseDuration);
        compound.func_74768_a("ItemUseAction", this.itemUseAction);
        TagController.writeTagUUIDs(compound, "TagUUIDs", this.tagUUIDs);
        if (saveScripts) {
            NBTTagCompound scriptData = new NBTTagCompound();
            LinkedItemScript handler = this.getScriptHandler();
            if (handler != null) {
                handler.writeToNBT(scriptData);
            }
            compound.func_74782_a("ScriptData", (NBTBase)scriptData);
        }
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.id = compound.func_74764_b("Id") ? compound.func_74762_e("Id") : LinkedItemController.getInstance().getUnusedId();
        this.name = compound.func_74779_i("Name");
        this.version = compound.func_74762_e("Version");
        this.display.readFromNBT(compound.func_74775_l("Display"));
        this.durabilityValue = compound.func_74769_h("DurabilityValue");
        this.stackSize = compound.func_74762_e("MaxStackSize");
        this.isTool = compound.func_74767_n("IsTool");
        this.isNormalItem = compound.func_74767_n("IsNormalItem");
        this.digSpeed = compound.func_74762_e("DigSpeed");
        this.setAttackSpeed(compound.func_74762_e("AttackSpeed"));
        this.armorType = compound.func_74762_e("ArmorType");
        this.enchantability = compound.func_74762_e("Enchantability");
        this.maxItemUseDuration = compound.func_74762_e("MaxItemUseDuration");
        this.itemUseAction = compound.func_74762_e("ItemUseAction");
        this.tagUUIDs = TagController.readTagUUIDs(compound, "TagUUIDs");
        if (compound.func_150297_b("ScriptData", 10)) {
            LinkedItemScript handler = new LinkedItemScript();
            handler.readFromNBT(compound.func_74775_l("ScriptData"));
            this.setScriptHandler(handler);
        }
    }

    @Override
    public ILinkedItem save() {
        return LinkedItemController.getInstance().saveLinkedItem(this);
    }

    public void setScriptHandler(LinkedItemScript handler) {
        LinkedItemController.getInstance().linkedItemsScripts.put(this.id, handler);
    }

    @Override
    public IItemStack createStack() {
        LinkedItem copy = this.clone();
        ItemStack stack = new ItemStack(CustomItems.linked_item, 1);
        ScriptLinkedItem scriptLinkedItem = new ScriptLinkedItem(stack, copy);
        EventHooks.onLinkedItemBuild(scriptLinkedItem);
        return NpcAPI.Instance().getIItemStack(scriptLinkedItem.item);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getVersion() {
        return this.version;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getDurabilityValue() {
        return this.durabilityValue;
    }

    @Override
    public void setDurabilityValue(double durabilityValue) {
        this.durabilityValue = durabilityValue;
    }

    @Override
    public int getStackSize() {
        return this.stackSize;
    }

    @Override
    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }

    @Override
    public int getMaxItemUseDuration() {
        return this.maxItemUseDuration;
    }

    @Override
    public void setMaxItemUseDuration(int maxItemUseDuration) {
        this.maxItemUseDuration = maxItemUseDuration;
    }

    @Override
    public int getItemUseAction() {
        return this.itemUseAction;
    }

    @Override
    public void setItemUseAction(int itemUseAction) {
        this.itemUseAction = itemUseAction;
    }

    @Override
    public boolean isNormalItem() {
        return this.isNormalItem;
    }

    @Override
    public void setNormalItem(boolean normalItem) {
        this.isNormalItem = normalItem;
    }

    @Override
    public boolean isTool() {
        return this.isTool;
    }

    @Override
    public void setTool(boolean tool) {
        this.isTool = tool;
    }

    @Override
    public int getDigSpeed() {
        return this.digSpeed;
    }

    @Override
    public void setDigSpeed(int digSpeed) {
        this.digSpeed = digSpeed;
    }

    @Override
    public int getArmorType() {
        return this.armorType;
    }

    @Override
    public void setArmorType(int armorType) {
        this.armorType = armorType;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public void setEnchantability(int enchantability) {
        this.enchantability = enchantability;
    }

    @Override
    public int getAttackSpeed() {
        return this.attackSpeed;
    }

    @Override
    public void setAttackSpeed(int time) {
        if (time <= 0) {
            time = 10;
        }
        this.attackSpeed = time;
    }

    public LinkedItem clone() {
        NBTTagCompound nbt = this.writeToNBT(true);
        LinkedItem clone = new LinkedItem(this.name);
        clone.readFromNBT(nbt);
        return clone;
    }

    public LinkedItemScript getOrCreateScriptHandler() {
        LinkedItemScript data = this.getScriptHandler();
        if (data == null) {
            data = new LinkedItemScript();
            this.setScriptHandler(data);
        }
        return data;
    }
}

