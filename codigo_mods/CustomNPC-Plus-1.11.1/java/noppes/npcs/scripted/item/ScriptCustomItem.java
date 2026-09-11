/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.scripted.item;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import kamkeel.npcs.network.packets.request.script.item.ItemScriptErrorPacket;
import kamkeel.npcs.network.packets.request.script.item.ItemScriptPacket;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.EventHooks;
import noppes.npcs.NBTTags;
import noppes.npcs.api.item.IItemCustom;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.constants.ScriptContext;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.data.IScriptHandler;
import noppes.npcs.controllers.data.IScriptHandlerPacket;
import noppes.npcs.controllers.data.IScriptUnit;
import noppes.npcs.scripted.CustomNPCsException;
import noppes.npcs.scripted.item.ScriptCustomizableItem;

public class ScriptCustomItem
extends ScriptCustomizableItem
implements IItemCustom,
IScriptHandlerPacket {
    public List<IScriptUnit> scripts = new ArrayList<IScriptUnit>();
    public List<Integer> errored = new ArrayList<Integer>();
    public String scriptLanguage = "ECMAScript";
    public boolean enabled = false;
    public boolean loaded = false;
    private Map<Long, String> clientConsoleText = new TreeMap<Long, String>();
    public double durabilityValue = 1.0;
    public int stackSize = 64;
    public int maxItemUseDuration = 20;
    public int itemUseAction = 0;
    public boolean isNormalItem = false;
    public boolean isTool = false;
    public int digSpeed = 1;
    public int armorType = -2;
    public int enchantability;
    public int attackSpeed = 10;
    public long lastInited = -1L;

    public ScriptCustomItem(ItemStack item) {
        super(item);
        this.loadItemData();
    }

    public NBTTagCompound getScriptNBT(NBTTagCompound compound) {
        compound.func_74782_a("Scripts", (NBTBase)NBTTags.NBTScript(this.scripts));
        compound.func_74778_a("ScriptLanguage", this.scriptLanguage);
        compound.func_74757_a("ScriptEnabled", this.enabled);
        return compound;
    }

    public void setScriptNBT(NBTTagCompound compound) {
        if (compound.func_74764_b("Scripts")) {
            this.scripts = NBTTags.GetScriptOld(compound.func_150295_c("Scripts", 10), this);
            this.scriptLanguage = compound.func_74779_i("ScriptLanguage");
            this.enabled = compound.func_74767_n("ScriptEnabled");
        }
    }

    @Override
    public ScriptContext getContext() {
        return ScriptContext.ITEM;
    }

    @Override
    public void requestData() {
        ItemScriptErrorPacket.Get();
        ItemScriptPacket.Get();
    }

    @Override
    public void sendSavePacket(int index, int totalCount, NBTTagCompound nbt) {
        ItemScriptPacket.Save(nbt);
    }

    @Override
    public IScriptHandlerPacket.GuiDataResult setGuiData(NBTTagCompound compound) {
        if (compound.func_74764_b("LoadComplete")) {
            return new IScriptHandlerPacket.GuiDataResult(IScriptHandlerPacket.GuiDataKind.LOAD_COMPLETE, -1);
        }
        if (compound.func_74764_b("ItemScriptConsole")) {
            this.clientConsoleText = NBTTags.GetLongStringMap(compound.func_150295_c("ItemScriptConsole", 10));
            return new IScriptHandlerPacket.GuiDataResult(IScriptHandlerPacket.GuiDataKind.TAB, -1);
        }
        this.setMCNbt(compound);
        this.loadScriptData();
        return new IScriptHandlerPacket.GuiDataResult(IScriptHandlerPacket.GuiDataKind.METADATA, -1);
    }

    @Override
    public void sync() {
        this.sendSavePacket(-1, this.getScripts().size(), this.getMCNbt());
    }

    @Override
    public int getType() {
        return 6;
    }

    private boolean isEnabled() {
        return this.enabled && ScriptController.HasStart;
    }

    @Override
    public IScriptHandler getScriptHandler() {
        return this;
    }

    @Override
    public void callScript(EnumScriptType type, Event event) {
        this.callScript(type.function, event);
    }

    @Override
    public void callScript(String hookName, Event event) {
        if (!this.loaded) {
            this.loadScriptData();
            this.loaded = true;
        }
        if (!this.isEnabled()) {
            return;
        }
        if (ScriptController.Instance.lastLoaded > this.lastInited) {
            this.lastInited = ScriptController.Instance.lastLoaded;
            if (!Objects.equals(hookName, EnumScriptType.INIT.function)) {
                EventHooks.onScriptItemInit(this);
            }
        }
        for (int i = 0; i < this.scripts.size(); ++i) {
            IScriptUnit script = this.scripts.get(i);
            if (this.errored.contains(i) || script == null || script.hasErrored() || !script.hasCode()) continue;
            script.run(hookName, (Object)event);
            if (!script.hasErrored()) continue;
            this.errored.add(i);
        }
    }

    @Override
    public boolean isClient() {
        return false;
    }

    @Override
    public boolean getEnabled() {
        return this.enabled;
    }

    @Override
    public void setEnabled(boolean enable) {
        this.enabled = enable;
    }

    @Override
    public String getLanguage() {
        return this.scriptLanguage;
    }

    @Override
    public void setLanguage(String lang) {
        this.scriptLanguage = lang;
    }

    @Override
    public void setScripts(List<IScriptUnit> list) {
        this.scripts = list;
    }

    @Override
    public List<IScriptUnit> getScripts() {
        return this.scripts;
    }

    @Override
    public String noticeString() {
        return "ScriptedItem";
    }

    @Override
    public Map<Long, String> getConsoleText() {
        return this.clientConsoleText;
    }

    @Override
    public void clearConsole() {
        this.clientConsoleText.clear();
        ItemScriptErrorPacket.Clear();
    }

    @Override
    public int getMaxStackSize() {
        return this.stackSize;
    }

    @Override
    public void setArmorType(int armorType) {
        this.armorType = armorType;
        this.saveItemData();
    }

    @Override
    public int getArmorType() {
        return this.armorType;
    }

    @Override
    public void setIsTool(boolean isTool) {
        this.isTool = isTool;
        this.saveItemData();
    }

    @Override
    public boolean isTool() {
        return this.isTool;
    }

    @Override
    public void setIsNormalItem(boolean normalItem) {
        this.isNormalItem = normalItem;
        this.saveItemData();
    }

    @Override
    public boolean isNormalItem() {
        return this.isNormalItem;
    }

    @Override
    public void setDigSpeed(int digSpeed) {
        this.digSpeed = digSpeed;
        this.saveItemData();
    }

    @Override
    public int getDigSpeed() {
        return this.digSpeed;
    }

    @Override
    public void setMaxStackSize(int size) {
        if (size < 1 || size > 127) {
            throw new CustomNPCsException("Stacksize has to be between 1 and 127", new Object[0]);
        }
        this.stackSize = size;
        this.saveItemData();
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
        return this.maxItemUseDuration;
    }

    @Override
    public void setMaxItemUseDuration(int duration) {
        this.maxItemUseDuration = duration;
        this.saveItemData();
    }

    @Override
    public void setItemUseAction(int action) {
        this.itemUseAction = action;
        this.saveItemData();
    }

    @Override
    public int getItemUseAction() {
        return this.itemUseAction;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public int getAttackSpeed() {
        return this.attackSpeed;
    }

    @Override
    public void setAttackSpeed(int speed) {
        if (speed <= 0) {
            speed = 10;
        }
        this.attackSpeed = speed;
        this.saveItemData();
    }

    @Override
    public void setEnchantability(int enchantability) {
        this.enchantability = enchantability;
        this.saveItemData();
    }

    public void saveScriptData() {
        NBTTagCompound c = this.item.func_77978_p();
        if (c == null) {
            c = new NBTTagCompound();
            this.item.func_77982_d(c);
        }
        c.func_74782_a("ScriptedData", (NBTBase)this.getScriptNBT(new NBTTagCompound()));
    }

    public void loadScriptData() {
        NBTTagCompound c = this.item.func_77978_p();
        if (c != null) {
            this.setScriptNBT(c.func_74775_l("ScriptedData"));
        }
    }

    @Override
    public NBTTagCompound getMCNbt() {
        NBTTagCompound compound = super.getMCNbt();
        compound.func_74782_a("ScriptedData", (NBTBase)this.getScriptNBT(new NBTTagCompound()));
        return compound;
    }

    @Override
    public void setMCNbt(NBTTagCompound compound) {
        super.setMCNbt(compound);
        this.setScriptNBT(compound.func_74775_l("ScriptedData"));
    }

    @Override
    public NBTTagCompound getItemNBT(NBTTagCompound compound) {
        super.getItemNBT(compound);
        compound.func_74780_a("DurabilityValue", this.durabilityValue);
        compound.func_74768_a("MaxStackSize", this.stackSize);
        compound.func_74757_a("IsTool", this.isTool);
        compound.func_74757_a("IsNormalItem", this.isNormalItem);
        compound.func_74768_a("DigSpeed", this.digSpeed);
        compound.func_74768_a("ArmorType", this.armorType);
        compound.func_74768_a("Enchantability", this.enchantability);
        compound.func_74768_a("AttackSpeed", this.attackSpeed);
        compound.func_74768_a("MaxItemUseDuration", this.maxItemUseDuration);
        compound.func_74768_a("ItemUseAction", this.itemUseAction);
        return compound;
    }

    @Override
    public void setItemNBT(NBTTagCompound compound) {
        super.setItemNBT(compound);
        this.durabilityValue = compound.func_74769_h("DurabilityValue");
        this.stackSize = compound.func_74762_e("MaxStackSize");
        this.isTool = compound.func_74767_n("IsTool");
        this.isNormalItem = compound.func_74767_n("IsNormalItem");
        this.digSpeed = compound.func_74762_e("DigSpeed");
        this.armorType = compound.func_74762_e("ArmorType");
        this.enchantability = compound.func_74762_e("Enchantability");
        this.setAttackSpeed(compound.func_74762_e("AttackSpeed"));
        this.maxItemUseDuration = compound.func_74762_e("MaxItemUseDuration");
        this.itemUseAction = compound.func_74762_e("ItemUseAction");
    }
}

