/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraftforge.common.util.EnumHelper
 */
package JinRyuu.JRMCore.items;

import JinRyuu.JRMCore.items.ItemAnyBase;
import JinRyuu.JRMCore.items.ItemBarberSnC;
import JinRyuu.JRMCore.items.VanityColor;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ItemsJRMC {
    public static Item ItemBarberSnC;
    public static ItemArmor.ArmorMaterial GI2;
    public static Item Custom_fabric;
    public static Item Costume_creeper;
    public static Item Costume_ender;
    public static final int[] ItemsVanityNum;
    public static final int[] ItemVanity3;
    public static Item[] ItemsVanity;

    public static void init() {
        ItemBarberSnC = GameRegistry.registerItem((Item)new ItemBarberSnC(20, 10.0f, true).func_77655_b("ItemBarberSnC"), (String)"ItemBarberSnC", null);
        Custom_fabric = GameRegistry.registerItem((Item)new ItemAnyBase().func_77655_b("ItemFabric"), (String)"ItemFabric", null);
        int id = 0;
        Costume_creeper = GameRegistry.registerItem((Item)new VanityColor(7064135, GI2, 5, "jrmc_vanity_" + id).func_77655_b("jrmc_vanity_" + id), (String)("jrmc_vanity_" + id), null);
        Costume_ender = GameRegistry.registerItem((Item)new VanityColor(12686847, GI2, 5, "jrmc_vanity_" + ++id).func_77655_b("jrmc_vanity_" + id), (String)("jrmc_vanity_" + id), null);
        ++id;
        for (int i = 0; i < ItemsVanityNum.length; ++i) {
            int j = i + id;
            ItemsJRMC.ItemsVanity[i] = GameRegistry.registerItem((Item)new VanityColor(0xFFFFFF, GI2, ItemsVanityNum[i], "jrmc_vanity_" + j, ItemVanity3[i]).func_77655_b("jrmc_vanity_" + j), (String)("jrmc_vanity_" + j), null);
        }
    }

    static {
        GI2 = EnumHelper.addArmorMaterial((String)"GI", (int)250, (int[])new int[]{3, 5, 5, 5}, (int)20);
        ItemsVanityNum = new int[]{5, 1, 2, 2, 3};
        ItemVanity3 = new int[]{0, 1, 2, 3, 4};
        ItemsVanity = new Item[ItemsVanityNum.length];
    }
}

