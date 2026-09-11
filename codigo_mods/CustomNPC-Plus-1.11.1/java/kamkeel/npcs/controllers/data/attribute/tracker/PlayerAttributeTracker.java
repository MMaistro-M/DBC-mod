/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.attribute.tracker;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import kamkeel.npcs.CustomAttributes;
import kamkeel.npcs.controllers.AttributeController;
import kamkeel.npcs.controllers.data.attribute.AttributeDefinition;
import kamkeel.npcs.controllers.data.attribute.PlayerAttribute;
import kamkeel.npcs.controllers.data.attribute.PlayerAttributeMap;
import kamkeel.npcs.controllers.data.attribute.requirement.RequirementCheckerRegistry;
import kamkeel.npcs.controllers.data.attribute.tracker.AttributeRecalcEvent;
import kamkeel.npcs.controllers.data.attribute.tracker.PlayerEquipmentTracker;
import kamkeel.npcs.util.AttributeAttackUtil;
import kamkeel.npcs.util.AttributeItemUtil;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.ICustomAttribute;
import noppes.npcs.api.handler.data.IPlayerAttributes;

public class PlayerAttributeTracker
implements IPlayerAttributes {
    public static UUID healthUUID = UUID.fromString("48a0ad75-2cf8-4838-ad2f-9a0aadc57dfe");
    public static UUID healthBoostUUID = UUID.fromString("93cfba41-294a-4e6b-a98b-bee76a9f813b");
    public static UUID movementSpeedUUID = UUID.fromString("e7e0dd10-8ed5-42fb-8167-e71f8de4ea0c");
    public static UUID knockbackResUUID = UUID.fromString("b2f261e8-34f0-4140-9828-b56c1f6e0ff2");
    private final UUID playerId;
    public PlayerAttributeMap playerAttributes = new PlayerAttributeMap();
    public float extraHealth = 0.0f;
    public float extraHealthBoost = 0.0f;
    public float movementSpeed = 0.0f;
    public float knockbackRes = 0.0f;
    public float gearOutput = 0.0f;
    public final Map<Integer, Float> magicDamage = new HashMap<Integer, Float>();
    public final Map<Integer, Float> magicBoost = new HashMap<Integer, Float>();
    public final Map<Integer, Float> magicDefense = new HashMap<Integer, Float>();
    public final Map<Integer, Float> magicResistance = new HashMap<Integer, Float>();
    private PlayerEquipmentTracker equipmentTracker = new PlayerEquipmentTracker();

    public PlayerAttributeTracker(UUID playerId) {
        this.playerId = playerId;
        this.recalcAttributes(null);
    }

    public void recalcAttributes(EntityPlayer player) {
        this.playerAttributes = new PlayerAttributeMap();
        this.magicDamage.clear();
        this.magicBoost.clear();
        this.magicDefense.clear();
        this.magicResistance.clear();
        if (player != null) {
            ItemStack[] equipment;
            AttributeRecalcEvent.pre(player, this);
            PlayerEquipmentTracker currentEquip = new PlayerEquipmentTracker();
            currentEquip.updateFrom(player);
            for (ItemStack piece : equipment = new ItemStack[]{currentEquip.heldItem, currentEquip.boots, currentEquip.leggings, currentEquip.chestplate, currentEquip.helmet}) {
                NBTTagCompound reqTag;
                NBTTagCompound rpgCore;
                if (piece == null || piece.field_77990_d != null && piece.field_77990_d.func_74764_b("RPGCore") && (rpgCore = piece.field_77990_d.func_74775_l("RPGCore")).func_74764_b("Requirements") && !RequirementCheckerRegistry.checkRequirements(player, reqTag = rpgCore.func_74775_l("Requirements"))) continue;
                rpgCore = AttributeItemUtil.readAttributes(piece).entrySet().iterator();
                while (rpgCore.hasNext()) {
                    Map.Entry entry2 = (Map.Entry)rpgCore.next();
                    String string = (String)entry2.getKey();
                    float value = ((Float)entry2.getValue()).floatValue();
                    AttributeDefinition def = AttributeController.getAttribute(string);
                    if (def == null) continue;
                    PlayerAttribute inst = this.playerAttributes.getAttributeInstance(def);
                    if (inst == null) {
                        inst = this.playerAttributes.registerAttribute(def, 0.0f);
                    }
                    inst.value = inst.getValue() + value;
                }
                Map<Integer, Float> magicFlat = AttributeItemUtil.readMagicAttributeMap(piece, "magic_damage");
                for (Map.Entry entry : magicFlat.entrySet()) {
                    int magicId = (Integer)entry.getKey();
                    float value = ((Float)entry.getValue()).floatValue();
                    this.magicDamage.put(magicId, Float.valueOf(this.magicDamage.getOrDefault(magicId, Float.valueOf(0.0f)).floatValue() + value));
                }
                Map<Integer, Float> magicPercent = AttributeItemUtil.readMagicAttributeMap(piece, "magic_boost");
                for (Map.Entry<Integer, Float> entry4 : magicPercent.entrySet()) {
                    int magicId = entry4.getKey();
                    float value = entry4.getValue().floatValue();
                    this.magicBoost.put(magicId, Float.valueOf(this.magicBoost.getOrDefault(magicId, Float.valueOf(0.0f)).floatValue() + value));
                }
                Map<Integer, Float> map = AttributeItemUtil.readMagicAttributeMap(piece, "magic_defense");
                for (Map.Entry<Integer, Float> entry5 : map.entrySet()) {
                    int magicId = entry5.getKey();
                    float value = entry5.getValue().floatValue();
                    this.magicDefense.put(magicId, Float.valueOf(this.magicDefense.getOrDefault(magicId, Float.valueOf(0.0f)).floatValue() + value));
                }
                Map<Integer, Float> magicResist = AttributeItemUtil.readMagicAttributeMap(piece, "magic_resistance");
                for (Map.Entry<Integer, Float> entry6 : magicResist.entrySet()) {
                    int magicId = entry6.getKey();
                    float value = entry6.getValue().floatValue();
                    this.magicResistance.put(magicId, Float.valueOf(this.magicResistance.getOrDefault(magicId, Float.valueOf(0.0f)).floatValue() + value));
                }
            }
            this.equipmentTracker = currentEquip;
            this.gearOutput = AttributeAttackUtil.calculateGearOutput(this);
            this.extraHealth = this.getAttributeValue(CustomAttributes.HEALTH);
            this.extraHealthBoost = this.getAttributeValue(CustomAttributes.HEALTH_BOOST);
            this.movementSpeed = this.getAttributeValue(CustomAttributes.MOVEMENT_SPEED);
            this.knockbackRes = this.getAttributeValue(CustomAttributes.KNOCKBACK_RES);
            this.updatePlayerMaxHealth(player);
            this.updatePlayerMovementSpeed(player);
            this.updatePlayerKnockbackRes(player);
            AttributeRecalcEvent.post(player, this);
        }
    }

    public void updatePlayerMaxHealth(EntityPlayer player) {
        float currentHealth;
        float bonusHealth = this.extraHealth;
        float healthBoostPercent = this.extraHealthBoost / 100.0f;
        IAttributeInstance maxHealthAttr = player.func_110140_aT().func_111151_a(SharedMonsterAttributes.field_111267_a);
        try {
            AttributeModifier oldBoost;
            AttributeModifier oldFlat = maxHealthAttr.func_111127_a(healthUUID);
            if (oldFlat != null) {
                maxHealthAttr.func_111124_b(oldFlat);
            }
            if ((oldBoost = maxHealthAttr.func_111127_a(healthBoostUUID)) != null) {
                maxHealthAttr.func_111124_b(oldBoost);
            }
        }
        catch (Exception oldFlat) {
            // empty catch block
        }
        if (bonusHealth != 0.0f) {
            maxHealthAttr.func_111121_a(new AttributeModifier(healthUUID, "RPGCoreHealthBonus", (double)bonusHealth, 0));
        }
        if (healthBoostPercent != 0.0f) {
            maxHealthAttr.func_111121_a(new AttributeModifier(healthBoostUUID, "RPGCoreHealthBoost", (double)healthBoostPercent, 1));
        }
        if ((double)(currentHealth = player.func_110143_aJ()) > maxHealthAttr.func_111126_e()) {
            player.func_70606_j((float)maxHealthAttr.func_111126_e());
        }
    }

    public void updatePlayerMovementSpeed(EntityPlayer player) {
        float extraSpeed = this.movementSpeed / 100.0f;
        IAttributeInstance speedAttr = player.func_110140_aT().func_111151_a(SharedMonsterAttributes.field_111263_d);
        try {
            AttributeModifier old = speedAttr.func_111127_a(movementSpeedUUID);
            if (old != null) {
                speedAttr.func_111124_b(old);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        if (extraSpeed != 0.0f) {
            speedAttr.func_111121_a(new AttributeModifier(movementSpeedUUID, "RPGCoreMovementSpeed", (double)extraSpeed, 1));
        }
    }

    public void updatePlayerKnockbackRes(EntityPlayer player) {
        float extraRes = this.knockbackRes / 100.0f;
        IAttributeInstance knockResAttr = player.func_110140_aT().func_111151_a(SharedMonsterAttributes.field_111266_c);
        try {
            AttributeModifier old = knockResAttr.func_111127_a(knockbackResUUID);
            if (old != null) {
                knockResAttr.func_111124_b(old);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        if (extraRes != 0.0f) {
            knockResAttr.func_111121_a(new AttributeModifier(knockbackResUUID, "RPGCoreKnockbackRes", (double)extraRes, 1));
        }
    }

    public void updateIfChanged(EntityPlayer player) {
        PlayerEquipmentTracker current = new PlayerEquipmentTracker();
        current.updateFrom(player);
        if (!this.equipmentTracker.equals(current)) {
            this.recalcAttributes(player);
            this.equipmentTracker = current;
        }
    }

    public float getAttributeValue(AttributeDefinition def) {
        PlayerAttribute inst = this.playerAttributes.getAttributeInstance(def);
        return inst != null ? inst.getValue() : 0.0f;
    }

    @Override
    public void recalculate(IPlayer player) {
        if (player == null || player.getMCEntity() == null) {
            return;
        }
        this.recalcAttributes((EntityPlayer)player.getMCEntity());
    }

    @Override
    public ICustomAttribute[] getAttributes() {
        return this.playerAttributes.map.values().toArray(new ICustomAttribute[this.playerAttributes.map.size()]);
    }

    @Override
    public float getAttributeValue(String key) {
        for (ICustomAttribute inst : this.getAttributes()) {
            if (!inst.getAttribute().getKey().equalsIgnoreCase(key)) continue;
            return inst.getValue();
        }
        return 0.0f;
    }

    @Override
    public boolean hasAttribute(String key) {
        for (ICustomAttribute inst : this.getAttributes()) {
            if (!inst.getAttribute().getKey().equalsIgnoreCase(key)) continue;
            return true;
        }
        return false;
    }

    @Override
    public ICustomAttribute getAttribute(String key) {
        for (ICustomAttribute inst : this.getAttributes()) {
            if (!inst.getAttribute().getKey().equalsIgnoreCase(key)) continue;
            return inst;
        }
        return null;
    }
}

