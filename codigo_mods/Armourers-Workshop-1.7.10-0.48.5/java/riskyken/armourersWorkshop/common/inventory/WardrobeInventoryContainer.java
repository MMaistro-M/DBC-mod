/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package riskyken.armourersWorkshop.common.inventory;

import java.util.HashMap;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.inventory.IInventorySlotUpdate;
import riskyken.armourersWorkshop.common.inventory.WardrobeInventory;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.utils.NBTHelper;

public class WardrobeInventoryContainer {
    private static final String TAG_WARDROBE_CONTAINER = "wardrobeContainer";
    private static final String TAG_LEGACY_ITEMS = "items";
    private final HashMap<ISkinType, WardrobeInventory> skinInventorys = new HashMap();

    public WardrobeInventoryContainer(IInventorySlotUpdate callback, ISkinType[] validSkins) {
        for (int i = 0; i < validSkins.length; ++i) {
            this.skinInventorys.put(validSkins[i], new WardrobeInventory(callback, validSkins[i]));
        }
    }

    public WardrobeInventory getInventoryForSkinType(ISkinType skinType) {
        return this.skinInventorys.get(skinType);
    }

    public void writeToNBT(NBTTagCompound compound) {
        NBTTagCompound containerCompound = new NBTTagCompound();
        Set<ISkinType> skinTypes = this.skinInventorys.keySet();
        for (int i = 0; i < this.skinInventorys.size(); ++i) {
            ISkinType skinType = (ISkinType)this.skinInventorys.keySet().toArray()[i];
            this.skinInventorys.get(skinType).writeItemsToNBT(containerCompound);
        }
        compound.func_74782_a(TAG_WARDROBE_CONTAINER, (NBTBase)containerCompound);
    }

    public void readFromNBT(NBTTagCompound compound) {
        if (compound.func_150297_b(TAG_WARDROBE_CONTAINER, 10)) {
            NBTTagCompound containerCompound = compound.func_74775_l(TAG_WARDROBE_CONTAINER);
            Set<ISkinType> skinTypes = this.skinInventorys.keySet();
            for (int i = 0; i < this.skinInventorys.size(); ++i) {
                ISkinType skinType = (ISkinType)this.skinInventorys.keySet().toArray()[i];
                this.skinInventorys.get(skinType).readItemsFromNBT(containerCompound);
            }
        } else if (compound.func_74764_b(TAG_LEGACY_ITEMS)) {
            ItemStack[] legacyItems = new ItemStack[7];
            NBTHelper.readStackArrayFromNBT(compound, TAG_LEGACY_ITEMS, legacyItems);
            this.getInventoryForSkinType(SkinTypeRegistry.skinHead).func_70299_a(0, legacyItems[0]);
            this.getInventoryForSkinType(SkinTypeRegistry.skinChest).func_70299_a(0, legacyItems[1]);
            this.getInventoryForSkinType(SkinTypeRegistry.skinLegs).func_70299_a(0, legacyItems[2]);
            this.getInventoryForSkinType(SkinTypeRegistry.skinFeet).func_70299_a(0, legacyItems[3]);
            this.getInventoryForSkinType(SkinTypeRegistry.skinSword).func_70299_a(0, legacyItems[4]);
            this.getInventoryForSkinType(SkinTypeRegistry.skinBow).func_70299_a(0, legacyItems[5]);
            this.getInventoryForSkinType(SkinTypeRegistry.oldSkinArrow).func_70299_a(0, legacyItems[6]);
        }
    }

    public void dropItems(EntityPlayer player) {
        Set<ISkinType> skinTypes = this.skinInventorys.keySet();
        for (int i = 0; i < this.skinInventorys.size(); ++i) {
            ISkinType skinType = (ISkinType)this.skinInventorys.keySet().toArray()[i];
            this.skinInventorys.get(skinType).dropItems(player);
        }
    }
}

