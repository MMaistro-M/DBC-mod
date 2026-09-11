/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 */
package kamkeel.npcs.controllers.data.ability.conditions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.conditions.AbilityCondition;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.builder.FieldDef;
import noppes.npcs.entity.EntityNPCInterface;

public class ConditionItem
extends AbilityCondition {
    private UsageType usageType = UsageType.HOLDING;
    private String itemName = "";
    private ArmorSlot armorSlot = ArmorSlot.BOOTS;
    private boolean fullArmorSet = false;
    private int requiredCount = 1;

    public ConditionItem() {
        this.typeId = "condition.cnpc.item";
        this.name = "condition.item";
    }

    @Override
    protected boolean checkEntity(EntityLivingBase entity) {
        switch (this.usageType) {
            case HOLDING: {
                return this.checkHolding(entity);
            }
            case ARMOR: {
                return this.checkArmor(entity);
            }
            case COUNT: {
                return this.checkCount(entity);
            }
            case OFFHAND: {
                return this.checkOffhand(entity);
            }
        }
        return false;
    }

    private boolean checkHolding(EntityLivingBase entity) {
        ItemStack held = entity.func_70694_bm();
        return this.matchesItem(held);
    }

    private boolean checkArmor(EntityLivingBase entity) {
        int slot = this.armorSlot.ordinal() + 1;
        return this.matchesItem(entity.func_71124_b(slot));
    }

    private boolean checkCount(EntityLivingBase entity) {
        if (entity instanceof EntityPlayer) {
            return this.checkPlayerInventoryCount((EntityPlayer)entity);
        }
        if (entity instanceof EntityNPCInterface) {
            return this.checkNPCProjectile((EntityNPCInterface)entity);
        }
        return false;
    }

    private boolean checkOffhand(EntityLivingBase entity) {
        if (entity instanceof EntityPlayer) {
            return false;
        }
        if (!(entity instanceof EntityNPCInterface)) {
            return false;
        }
        ItemStack item = ((EntityNPCInterface)entity).getOffHand();
        return this.matchesItem(item);
    }

    private boolean checkPlayerInventoryCount(EntityPlayer player) {
        int count = 0;
        for (ItemStack stack : player.field_71071_by.field_70462_a) {
            if (!this.matchesItem(stack)) continue;
            count += stack.field_77994_a;
        }
        return count >= this.requiredCount;
    }

    private boolean checkNPCProjectile(EntityNPCInterface npc) {
        ItemStack projectile = npc.inventory.getProjectile();
        return this.matchesItem(projectile);
    }

    private boolean matchesItem(ItemStack stack) {
        if (stack == null || this.itemName == null || this.itemName.isEmpty()) {
            return false;
        }
        String registryName = Item.field_150901_e.func_148750_c((Object)stack.func_77973_b());
        return this.itemName.equals(registryName);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getConditionDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.enumField("condition.usage_type", UsageType.class, this::getUsageType, this::setUsageType));
        defs.add(FieldDef.stringField("condition.item_name", this::getItemName, this::setItemName).hover("condition.hover.item_name"));
        defs.add(FieldDef.enumField("condition.armor_slot", ArmorSlot.class, this::getArmorSlot, this::setArmorSlot).visibleWhen(() -> this.usageType == UsageType.ARMOR).hover("condition.hover.armor_slots"));
        defs.add(FieldDef.intField("condition.item_count", this::getRequiredCount, this::setRequiredCount).range(1.0f, 64.0f).visibleWhen(() -> this.usageType == UsageType.COUNT && this.userType.allowsPlayer()).hover("condition.hover.required_count"));
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getConditionSummary() {
        String filterLabel = StatCollector.func_74838_a((String)this.getFilter().toString());
        String usage = this.usageType.name();
        String item = this.itemName.isEmpty() ? "None" : this.itemName;
        return "[" + filterLabel + "] " + usage + ": " + item;
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        nbt.func_74778_a("itemName", this.itemName);
        nbt.func_74768_a("usageType", this.usageType.ordinal());
        nbt.func_74768_a("armorSlot", this.armorSlot.ordinal());
        nbt.func_74768_a("requiredCount", this.requiredCount);
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        this.itemName = nbt.func_74779_i("itemName");
        this.usageType = UsageType.fromOrdinal(nbt.func_74762_e("usageType"));
        this.armorSlot = ArmorSlot.fromOrdinal(nbt.func_74762_e("armorSlot"));
        this.requiredCount = Math.max(1, nbt.func_74762_e("requiredCount"));
    }

    @Override
    public boolean isConfigured() {
        return this.itemName != null && !this.itemName.isEmpty();
    }

    public UsageType getUsageType() {
        return this.usageType;
    }

    public void setUsageType(UsageType usageType) {
        this.usageType = usageType;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public ArmorSlot getArmorSlot() {
        return this.armorSlot;
    }

    public void setArmorSlot(ArmorSlot armorSlot) {
        this.armorSlot = armorSlot;
    }

    public boolean isFullArmorSet() {
        return this.fullArmorSet;
    }

    public void setFullArmorSet(boolean fullArmorSet) {
        this.fullArmorSet = fullArmorSet;
    }

    public int getRequiredCount() {
        return this.requiredCount;
    }

    public void setRequiredCount(int requiredCount) {
        this.requiredCount = Math.max(1, requiredCount);
    }

    public static enum ArmorSlot {
        BOOTS,
        LEGS,
        CHEST,
        HELMET;


        public static ArmorSlot fromOrdinal(int ordinal) {
            ArmorSlot[] values = ArmorSlot.values();
            return ordinal >= 0 && ordinal < values.length ? values[ordinal] : BOOTS;
        }
    }

    public static enum UsageType {
        ARMOR,
        HOLDING,
        COUNT,
        OFFHAND;


        public static UsageType fromOrdinal(int ordinal) {
            UsageType[] values = UsageType.values();
            return ordinal >= 0 && ordinal < values.length ? values[ordinal] : HOLDING;
        }
    }
}

