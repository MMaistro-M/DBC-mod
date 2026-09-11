/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Multimap
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemEditableBook
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.item.ItemWritableBook
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTBase$NBTPrimitive
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraftforge.common.IPlantable
 */
package noppes.npcs.scripted.item;

import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import kamkeel.npcs.controllers.data.attribute.requirement.IRequirementChecker;
import kamkeel.npcs.controllers.data.attribute.requirement.RequirementCheckerRegistry;
import kamkeel.npcs.util.AttributeItemUtil;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.IPlantable;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.api.INbt;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.items.ItemLinked;
import noppes.npcs.items.ItemScripted;
import noppes.npcs.scripted.CustomNPCsException;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.ScriptNbt;
import noppes.npcs.scripted.item.ScriptCustomItem;
import noppes.npcs.scripted.item.ScriptItemArmor;
import noppes.npcs.scripted.item.ScriptItemBlock;
import noppes.npcs.scripted.item.ScriptItemBook;
import noppes.npcs.scripted.item.ScriptLinkedItem;

public class ScriptItemStack
implements IItemStack {
    public ItemStack item;

    public ScriptItemStack(ItemStack item) {
        this.item = item;
    }

    public int getType() {
        if (this.item.func_77973_b() instanceof IPlantable) {
            return 5;
        }
        return this.item.func_77973_b() instanceof ItemSword ? 4 : 0;
    }

    @Override
    public String getName() {
        return Item.field_150901_e.func_148750_c((Object)this.item.func_77973_b());
    }

    @Override
    public int getStackSize() {
        return this.item.field_77994_a;
    }

    @Override
    public boolean hasCustomName() {
        return this.item.func_82837_s();
    }

    @Override
    public void setCustomName(String name) {
        this.item.func_151001_c(name);
    }

    @Override
    public String getDisplayName() {
        return this.item.func_82833_r();
    }

    @Override
    public String getItemName() {
        return this.item.func_77973_b().func_77653_i(this.item);
    }

    @Override
    public int getMaxStackSize() {
        return this.item.func_77976_d();
    }

    @Override
    public void setStackSize(int size) {
        if (size < 0) {
            size = 1;
        } else if (size > 64) {
            size = 64;
        }
        this.item.field_77994_a = size;
    }

    @Override
    public int getItemDamage() {
        return this.item.func_77960_j();
    }

    @Override
    public void setItemDamage(int value) {
        this.item.func_77964_b(value);
    }

    @Override
    public void setTag(String key, Object value) {
        if (value instanceof Number) {
            this.getTag().func_74780_a(key, ((Number)value).doubleValue());
        } else if (value instanceof String) {
            this.getTag().func_74778_a(key, (String)value);
        }
    }

    @Override
    public boolean hasTag(String key) {
        if (this.item.field_77990_d == null) {
            return false;
        }
        return this.getTag().func_74764_b(key);
    }

    @Override
    public Object getTag(String key) {
        if (this.item.field_77990_d == null) {
            return null;
        }
        NBTBase tag = this.getTag().func_74781_a(key);
        if (tag == null) {
            return null;
        }
        if (tag instanceof NBTBase.NBTPrimitive) {
            return ((NBTBase.NBTPrimitive)tag).func_150286_g();
        }
        if (tag instanceof NBTTagString) {
            return ((NBTTagString)tag).func_150285_a_();
        }
        return tag;
    }

    @Override
    public INbt removeTags() {
        ScriptNbt nbt = (ScriptNbt)NpcAPI.Instance().getINbt(this.item.field_77990_d);
        this.item.field_77990_d = null;
        return nbt;
    }

    @Override
    public boolean isEnchanted() {
        return this.item.func_77948_v();
    }

    @Override
    public boolean hasEnchant(int id) {
        if (!this.isEnchanted()) {
            return false;
        }
        NBTTagList list = this.item.func_77986_q();
        for (int i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound compound = list.func_150305_b(i);
            if (compound.func_74765_d("id") != id) continue;
            return true;
        }
        return false;
    }

    @Override
    public void addEnchant(int id, int strength) {
        Enchantment ench = Enchantment.field_77331_b[id];
        if (ench == null) {
            throw new CustomNPCsException("Unknown enchant id:" + id, new Object[0]);
        }
        this.item.func_77966_a(ench, strength);
    }

    @Override
    public void setAttribute(String name, double value) {
        NBTTagCompound nbttagcompound;
        NBTTagCompound compound = this.item.func_77978_p();
        if (compound == null) {
            compound = new NBTTagCompound();
            this.item.func_77982_d(compound);
        }
        NBTTagList nbttaglist = compound.func_150295_c("AttributeModifiers", 10);
        NBTTagList newList = new NBTTagList();
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            nbttagcompound = nbttaglist.func_150305_b(i);
            if (nbttagcompound.func_74779_i("AttributeName").equals(name)) continue;
            newList.func_74742_a((NBTBase)nbttagcompound);
        }
        if (value != 0.0) {
            AttributeModifier attributeModifier = new AttributeModifier(name, value, 0);
            nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74778_a("Name", attributeModifier.func_111166_b());
            nbttagcompound.func_74780_a("Amount", attributeModifier.func_111164_d());
            nbttagcompound.func_74768_a("Operation", attributeModifier.func_111169_c());
            nbttagcompound.func_74772_a("UUIDMost", attributeModifier.func_111167_a().getMostSignificantBits());
            nbttagcompound.func_74772_a("UUIDLeast", attributeModifier.func_111167_a().getLeastSignificantBits());
            nbttagcompound.func_74778_a("AttributeName", name);
            newList.func_74742_a((NBTBase)nbttagcompound);
        }
        compound.func_74782_a("AttributeModifiers", (NBTBase)newList);
    }

    @Override
    public double getAttribute(String name) {
        NBTTagCompound compound = this.item.func_77978_p();
        if (compound == null) {
            return 0.0;
        }
        Multimap map = this.item.func_111283_C();
        for (Map.Entry entry : map.entries()) {
            if (!((String)entry.getKey()).equals(name)) continue;
            AttributeModifier mod = (AttributeModifier)entry.getValue();
            return mod.func_111164_d();
        }
        return 0.0;
    }

    @Override
    public String[] getLore() {
        NBTTagList nbttaglist;
        NBTTagCompound compound;
        if (this.item.func_77978_p() != null && (compound = this.item.func_77978_p().func_74775_l("display")) != null && compound.func_150299_b("Lore") == 9 && (nbttaglist = compound.func_150295_c("Lore", 8)).func_74745_c() > 0) {
            ArrayList<String> lore = new ArrayList<String>();
            for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
                lore.add(nbttaglist.func_150307_f(i));
            }
            return lore.toArray(new String[lore.size()]);
        }
        return new String[0];
    }

    @Override
    public boolean hasLore() {
        NBTTagCompound compound;
        if (this.item.func_77978_p() != null && (compound = this.item.func_77978_p().func_74775_l("display")) != null && compound.func_150299_b("Lore") == 9) {
            NBTTagList nbttaglist = compound.func_150295_c("Lore", 8);
            return nbttaglist.func_74745_c() > 0;
        }
        return false;
    }

    @Override
    public void setLore(String[] lore) {
        NBTTagCompound compound = this.item.func_77978_p();
        if (compound == null) {
            compound = new NBTTagCompound();
            this.item.func_77982_d(compound);
        }
        NBTTagCompound display = compound.func_74775_l("display");
        if (lore == null || lore.length == 0) {
            display.func_82580_o("Lore");
            return;
        }
        NBTTagList nbtlist = new NBTTagList();
        for (String s : lore) {
            nbtlist.func_74742_a((NBTBase)new NBTTagString(s));
        }
        display.func_74782_a("Lore", (NBTBase)nbtlist);
        compound.func_74782_a("display", (NBTBase)display);
    }

    @Override
    public boolean hasAttribute(String name) {
        NBTTagCompound compound = this.item.func_77978_p();
        if (compound == null) {
            return false;
        }
        NBTTagList nbttaglist = compound.func_150295_c("AttributeModifiers", 10);
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound c = nbttaglist.func_150305_b(i);
            if (!c.func_74779_i("AttributeName").equals(name)) continue;
            return true;
        }
        return false;
    }

    private static ScriptItemStack createNew(ItemStack item) {
        if (item == null) {
            return null;
        }
        if (item.func_77973_b() instanceof ItemLinked) {
            return new ScriptLinkedItem(item);
        }
        if (item.func_77973_b() instanceof ItemScripted) {
            return new ScriptCustomItem(item);
        }
        if (item.func_77973_b() == Items.field_151164_bB || item.func_77973_b() == Items.field_151099_bA || item.func_77973_b() instanceof ItemWritableBook || item.func_77973_b() instanceof ItemEditableBook) {
            return new ScriptItemBook(item);
        }
        if (item.func_77973_b() instanceof ItemArmor) {
            return new ScriptItemArmor(item);
        }
        if (item.func_77973_b() instanceof ItemBlock) {
            return new ScriptItemBlock(item);
        }
        return new ScriptItemStack(item);
    }

    @Override
    public IItemStack copy() {
        return ScriptItemStack.createNew(this.item.func_77946_l());
    }

    @Override
    public int getMaxItemDamage() {
        return this.item.func_77958_k();
    }

    @Override
    public boolean isWrittenBook() {
        return this.item.func_77973_b() == Items.field_151164_bB || this.item.func_77973_b() == Items.field_151099_bA;
    }

    @Override
    public String getBookTitle() {
        return this.item.func_77978_p().func_74779_i("title");
    }

    @Override
    public String getBookAuthor() {
        return this.item.func_77978_p().func_74779_i("author");
    }

    @Override
    public String[] getBookText() {
        if (!this.isWrittenBook()) {
            return null;
        }
        ArrayList<String> list = new ArrayList<String>();
        NBTTagList pages = this.item.func_77978_p().func_150295_c("pages", 8);
        for (int i = 0; i < pages.func_74745_c(); ++i) {
            list.add(pages.func_150307_f(i));
        }
        return list.toArray(new String[list.size()]);
    }

    private NBTTagCompound getTag() {
        if (this.item.field_77990_d == null) {
            this.item.field_77990_d = new NBTTagCompound();
        }
        return this.item.field_77990_d;
    }

    @Override
    public boolean isBlock() {
        Block block = Block.func_149634_a((Item)this.item.func_77973_b());
        return block != null && block != Blocks.field_150350_a;
    }

    public boolean isFood() {
        return this.item.func_77973_b() instanceof ItemFood;
    }

    public int getFoodPoints() {
        if (this.isFood()) {
            return ((ItemFood)this.item.func_77973_b()).func_150905_g(this.item);
        }
        return -1;
    }

    @Override
    public INbt getNbt() {
        NBTTagCompound compound = this.item.func_77978_p();
        if (compound == null) {
            compound = new NBTTagCompound();
            this.item.func_77982_d(compound);
        }
        return NpcAPI.Instance().getINbt(compound);
    }

    @Override
    public INbt getItemNbt() {
        NBTTagCompound compound = new NBTTagCompound();
        this.item.func_77955_b(compound);
        return NpcAPI.Instance().getINbt(compound);
    }

    @Override
    public NBTTagCompound getMCNbt() {
        NBTTagCompound compound = new NBTTagCompound();
        this.item.func_77955_b(compound);
        return compound;
    }

    @Override
    public void setMCNbt(NBTTagCompound compound) {
    }

    @Override
    public ItemStack getMCItemStack() {
        return this.item;
    }

    @Override
    public int itemHash() {
        return this.item.hashCode();
    }

    public boolean equals(Object object) {
        return object instanceof ScriptItemStack && ((ScriptItemStack)object).getMCItemStack().equals(this.getMCItemStack());
    }

    @Override
    public boolean compare(IItemStack item, boolean ignoreNBT) {
        return NoppesUtilPlayer.compareItems(this.getMCItemStack(), item.getMCItemStack(), false, ignoreNBT);
    }

    @Override
    public boolean compare(IItemStack item, boolean ignoreDamage, boolean ignoreNBT) {
        return NoppesUtilPlayer.compareItems(this.getMCItemStack(), item.getMCItemStack(), ignoreDamage, ignoreNBT);
    }

    @Override
    public void setCustomAttribute(String key, double value) {
        AttributeItemUtil.applyAttribute(this.item, key, (float)value);
    }

    @Override
    public boolean hasCustomAttribute(String key) {
        return AttributeItemUtil.readAttributes(this.item).containsKey(key);
    }

    @Override
    public float getCustomAttribute(String key) {
        return this.hasCustomAttribute(key) ? AttributeItemUtil.readAttributes(this.item).get(key).floatValue() : 0.0f;
    }

    @Override
    public void removeCustomAttribute(String key) {
        AttributeItemUtil.removeAttribute(this.item, key);
    }

    @Override
    public void setMagicAttribute(String key, int magicId, double value) {
        AttributeItemUtil.writeMagicAttribute(this.item, key, magicId, (float)value);
    }

    @Override
    public float getMagicAttribute(String key, int magicId) {
        Map<Integer, Float> magicMap = AttributeItemUtil.readMagicAttributeMap(this.item, key);
        return magicMap.getOrDefault(magicId, Float.valueOf(0.0f)).floatValue();
    }

    @Override
    public boolean hasMagicAttribute(String key, int magicId) {
        Map<Integer, Float> magicMap = AttributeItemUtil.readMagicAttributeMap(this.item, key);
        return magicMap.containsKey(magicId);
    }

    @Override
    public void removeMagicAttribute(String key, int magicId) {
        AttributeItemUtil.removeMagicAttribute(this.item, key, magicId);
    }

    @Override
    public void setRequirement(String reqKey, Object value) {
        AttributeItemUtil.applyRequirement(this.item, reqKey, value);
    }

    @Override
    public boolean hasRequirement(String reqKey) {
        if (this.item == null || this.item.field_77990_d == null) {
            return false;
        }
        NBTTagCompound root = this.item.field_77990_d;
        if (!root.func_74764_b("RPGCore")) {
            return false;
        }
        NBTTagCompound rpgCore = root.func_74775_l("RPGCore");
        if (!rpgCore.func_74764_b("Requirements")) {
            return false;
        }
        NBTTagCompound reqTag = rpgCore.func_74775_l("Requirements");
        return reqTag.func_74764_b(reqKey);
    }

    @Override
    public Object getRequirement(String reqKey) {
        if (this.item == null || this.item.field_77990_d == null) {
            return null;
        }
        NBTTagCompound root = this.item.field_77990_d;
        if (!root.func_74764_b("RPGCore")) {
            return null;
        }
        NBTTagCompound rpgCore = root.func_74775_l("RPGCore");
        if (!rpgCore.func_74764_b("Requirements")) {
            return null;
        }
        NBTTagCompound reqTag = rpgCore.func_74775_l("Requirements");
        if (reqTag.func_74764_b(reqKey)) {
            IRequirementChecker checker = RequirementCheckerRegistry.getChecker(reqKey);
            if (checker != null) {
                return checker.getValue(reqTag);
            }
            NBTBase tag = reqTag.func_74781_a(reqKey);
            if (tag instanceof NBTBase.NBTPrimitive) {
                return ((NBTBase.NBTPrimitive)tag).func_150286_g();
            }
            if (tag instanceof NBTTagString) {
                return ((NBTTagString)tag).func_150285_a_();
            }
            return tag;
        }
        return null;
    }

    @Override
    public void removeRequirement(String reqKey) {
        AttributeItemUtil.removeRequirement(this.item, reqKey);
    }

    @Override
    public String[] getCustomAttributeKeys() {
        Map<String, Float> attrs = AttributeItemUtil.readAttributes(this.item);
        return attrs.keySet().toArray(new String[attrs.size()]);
    }

    @Override
    public String[] getMagicAttributeKeys(String key) {
        Map<Integer, Float> magicMap = AttributeItemUtil.readMagicAttributeMap(this.item, key);
        String[] keys = new String[magicMap.size()];
        int i = 0;
        for (Integer magicId : magicMap.keySet()) {
            keys[i++] = magicId.toString();
        }
        return keys;
    }

    @Override
    public String[] getRequirementKeys() {
        if (this.item == null || this.item.field_77990_d == null) {
            return new String[0];
        }
        NBTTagCompound root = this.item.field_77990_d;
        if (!root.func_74764_b("RPGCore")) {
            return new String[0];
        }
        NBTTagCompound rpgCore = root.func_74775_l("RPGCore");
        if (!rpgCore.func_74764_b("Requirements")) {
            return new String[0];
        }
        NBTTagCompound reqTag = rpgCore.func_74775_l("Requirements");
        Set keys = reqTag.func_150296_c();
        return keys.toArray(new String[keys.size()]);
    }
}

