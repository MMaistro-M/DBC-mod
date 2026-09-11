/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemBlockWithMetadata
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package riskyken.armourersWorkshop.common.items.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import riskyken.armourersWorkshop.common.creativetab.ISortOrder;

public class ModItemBlockWithMetadata
extends ItemBlockWithMetadata
implements ISortOrder {
    public ModItemBlockWithMetadata(Block block) {
        super(block, block);
    }

    public String func_77657_g(ItemStack par1ItemStack) {
        return super.func_77657_g(par1ItemStack);
    }

    public String func_77667_c(ItemStack itemstack) {
        return this.field_150939_a.func_149739_a() + itemstack.func_77960_j();
    }

    public void func_77624_a(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        String localized;
        String unlocalized = itemStack.func_77977_a() + ".flavour";
        if (!unlocalized.equals(localized = StatCollector.func_74838_a((String)unlocalized))) {
            if (localized.contains("%n")) {
                String[] split = localized.split("%n");
                for (int i = 0; i < split.length; ++i) {
                    list.add(split[i]);
                }
            } else {
                list.add(localized);
            }
        }
        super.func_77624_a(itemStack, player, list, par4);
    }

    @Override
    public int getSortPriority() {
        if (this.field_150939_a instanceof ISortOrder) {
            return ((ISortOrder)this.field_150939_a).getSortPriority();
        }
        return 100;
    }
}

