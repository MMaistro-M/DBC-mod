/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package riskyken.armourersWorkshop.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.common.creativetab.ISortOrder;

public abstract class AbstractModItem
extends Item
implements ISortOrder {
    private int sortPriority = 0;

    public AbstractModItem(String name) {
        this(name, true);
    }

    public AbstractModItem(String name, boolean addCreativeTab) {
        if (addCreativeTab) {
            this.func_77637_a(ArmourersWorkshop.tabArmorersWorkshop);
        }
        this.func_77655_b(name);
        this.func_77627_a(false);
        this.func_77625_d(1);
        this.setNoRepair();
    }

    @SideOnly(value=Side.CLIENT)
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

    public Item func_77655_b(String name) {
        GameRegistry.registerItem((Item)this, (String)name);
        return super.func_77655_b(name);
    }

    public String func_77658_a() {
        return this.getModdedUnlocalizedName(super.func_77658_a());
    }

    public String func_77667_c(ItemStack itemStack) {
        return this.getModdedUnlocalizedName(super.func_77667_c(itemStack), itemStack);
    }

    protected String getModdedUnlocalizedName(String unlocalizedName) {
        String name = unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
        if (this.field_77787_bX) {
            return "item." + "armourersWorkshop".toLowerCase() + ":" + name + ".0";
        }
        return "item." + "armourersWorkshop".toLowerCase() + ":" + name;
    }

    protected String getModdedUnlocalizedName(String unlocalizedName, ItemStack stack) {
        String name = unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
        if (this.field_77787_bX) {
            return "item." + "armourersWorkshop".toLowerCase() + ":" + name + "." + stack.func_77960_j();
        }
        return "item." + "armourersWorkshop".toLowerCase() + ":" + name;
    }

    public AbstractModItem setSortPriority(int sortPriority) {
        this.sortPriority = sortPriority;
        return this;
    }

    @Override
    public int getSortPriority() {
        return this.sortPriority;
    }
}

