/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package riskyken.armourersWorkshop.common.wardrobe;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import java.util.BitSet;
import java.util.HashMap;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.wardrobe.ExPropsPlayerSkinData;
import riskyken.armourersWorkshop.common.wardrobe.ExtraColours;

public class EquipmentWardrobeData {
    private static final String TAG_EXTRA_COLOUR = "extra-colour-";
    private static final String TAG_ARMOUR_OVERRIDE = "armourOverride";
    private static final String TAG_SLOTS_UNLOCKED = "slotsUnlocked";
    private static final String TAG_SLOT_KEY = "slotKey";
    private static final String TAG_SLOT_VALUE = "slotValue";
    private ExtraColours extraColours;
    public BitSet armourOverride;
    public HashMap<String, Integer> slotsUnlocked;

    public EquipmentWardrobeData() {
        this.extraColours = new ExtraColours();
        this.armourOverride = new BitSet(4);
        this.slotsUnlocked = new HashMap();
        ISkinType[] validSkins = ExPropsPlayerSkinData.validSkins;
        for (int i = 0; i < validSkins.length; ++i) {
            ISkinType skinType = validSkins[i];
            this.slotsUnlocked.put(skinType.getRegistryName(), this.getUnlockedSlotsForSkinType(skinType));
        }
    }

    public int getUnlockedSlotsForSkinType(ISkinType skinType) {
        if (skinType == SkinTypeRegistry.skinBow) {
            return 1;
        }
        if (skinType == SkinTypeRegistry.skinSword) {
            return 5;
        }
        if (this.slotsUnlocked.containsKey(skinType.getRegistryName())) {
            return this.slotsUnlocked.get(skinType.getRegistryName());
        }
        return ConfigHandler.startingWardrobeSlots;
    }

    public void setUnlockedSlotsForSkinType(ISkinType skinType, int value) {
        this.slotsUnlocked.put(skinType.getRegistryName(), value);
    }

    public EquipmentWardrobeData(EquipmentWardrobeData ewd) {
        this.extraColours = new ExtraColours(ewd.getExtraColours());
        this.armourOverride = (BitSet)ewd.armourOverride.clone();
        this.slotsUnlocked = ewd.slotsUnlocked;
    }

    public ExtraColours getExtraColours() {
        return this.extraColours;
    }

    public void saveNBTData(NBTTagCompound compound) {
        int i;
        for (i = 0; i < ExtraColours.ExtraColourType.values().length; ++i) {
            ExtraColours.ExtraColourType type = ExtraColours.ExtraColourType.values()[i];
            compound.func_74768_a(TAG_EXTRA_COLOUR + type.toString().toLowerCase(), this.extraColours.getColour(type));
        }
        for (i = 0; i < 4; ++i) {
            compound.func_74757_a(TAG_ARMOUR_OVERRIDE + i, this.armourOverride.get(i));
        }
        NBTTagList slotsList = new NBTTagList();
        ISkinType[] validSkins = ExPropsPlayerSkinData.validSkins;
        for (int i2 = 0; i2 < validSkins.length; ++i2) {
            ISkinType skinType = validSkins[i2];
            NBTTagCompound slotCount = new NBTTagCompound();
            slotCount.func_74778_a(TAG_SLOT_KEY, skinType.getRegistryName());
            slotCount.func_74768_a(TAG_SLOT_VALUE, this.getUnlockedSlotsForSkinType(skinType));
            slotsList.func_74742_a((NBTBase)slotCount);
        }
        compound.func_74782_a(TAG_SLOTS_UNLOCKED, (NBTBase)slotsList);
    }

    public void loadNBTData(NBTTagCompound compound) {
        int i;
        for (i = 0; i < ExtraColours.ExtraColourType.values().length; ++i) {
            ExtraColours.ExtraColourType type = ExtraColours.ExtraColourType.values()[i];
            if (!compound.func_150297_b(TAG_EXTRA_COLOUR + type.toString().toLowerCase(), 3)) continue;
            this.extraColours.setColour(type, compound.func_74762_e(TAG_EXTRA_COLOUR + type.toString().toLowerCase()));
        }
        for (i = 0; i < 4; ++i) {
            this.armourOverride.set(i, compound.func_74767_n(TAG_ARMOUR_OVERRIDE + i));
        }
        if (compound.func_150297_b(TAG_SLOTS_UNLOCKED, 9)) {
            NBTTagList slotsList = compound.func_150295_c(TAG_SLOTS_UNLOCKED, 10);
            this.slotsUnlocked.clear();
            for (int i2 = 0; i2 < slotsList.func_74745_c(); ++i2) {
                NBTTagCompound slotCount = slotsList.func_150305_b(i2);
                if (!(slotCount.func_150297_b(TAG_SLOT_KEY, 8) & slotCount.func_150297_b(TAG_SLOT_VALUE, 3))) continue;
                String key = slotCount.func_74779_i(TAG_SLOT_KEY);
                int value = slotCount.func_74762_e(TAG_SLOT_VALUE);
                this.slotsUnlocked.put(key, value);
            }
        }
    }

    public void fromBytes(ByteBuf buf) {
        NBTTagCompound compound = ByteBufUtils.readTag((ByteBuf)buf);
        this.loadNBTData(compound);
    }

    public void toBytes(ByteBuf buf) {
        NBTTagCompound compound = new NBTTagCompound();
        this.saveNBTData(compound);
        ByteBufUtils.writeTag((ByteBuf)buf, (NBTTagCompound)compound);
    }
}

