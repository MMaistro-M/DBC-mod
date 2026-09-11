/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package riskyken.armourersWorkshop.common.inventory.slot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.inventory.slot.SlotHidable;
import riskyken.armourersWorkshop.common.items.ItemSkin;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class SlotSkin
extends SlotHidable {
    private ISkinType skinType;

    public SlotSkin(ISkinType skinType, IInventory inventory, int slotIndex, int xDisplayPosition, int yDisplayPosition) {
        super(inventory, slotIndex, xDisplayPosition, yDisplayPosition);
        this.skinType = skinType;
    }

    public boolean func_75214_a(ItemStack stack) {
        if (stack.func_77973_b() instanceof ItemSkin && SkinNBTHelper.stackHasSkinData(stack)) {
            SkinPointer skinData = SkinNBTHelper.getSkinPointerFromStack(stack);
            if (this.skinType != null && this.skinType == skinData.getIdentifier().getSkinType()) {
                return true;
            }
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_75212_b() {
        if (this.skinType != null) {
            return this.skinType.getEmptySlotIcon();
        }
        return super.func_75212_b();
    }
}

