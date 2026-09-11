/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.common.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.creativetab.ISortOrder;

public class CreativeTabArmourersWorkshop
extends CreativeTabs {
    public CreativeTabArmourersWorkshop(int id, String label) {
        super(id, label);
    }

    @SideOnly(value=Side.CLIENT)
    public Item func_78016_d() {
        return Item.func_150898_a((Block)ModBlocks.armourerBrain);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_78018_a(List list) {
        ArrayList items = new ArrayList();
        super.func_78018_a(items);
        Collections.sort(items, new ItemComparator());
        list.addAll(items);
    }

    private static class ItemComparator
    implements Comparator<ItemStack> {
        private ItemComparator() {
        }

        @Override
        public int compare(ItemStack stack1, ItemStack stack2) {
            if (stack1.func_77973_b() instanceof ISortOrder && stack2.func_77973_b() instanceof ISortOrder) {
                ISortOrder sort1 = (ISortOrder)stack1.func_77973_b();
                ISortOrder sort2 = (ISortOrder)stack2.func_77973_b();
                return sort2.getSortPriority() - sort1.getSortPriority();
            }
            return 0;
        }
    }
}

