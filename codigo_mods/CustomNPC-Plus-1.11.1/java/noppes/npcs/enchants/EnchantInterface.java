/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.enchantment.EnumEnchantmentType
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.util.EnumHelper
 */
package noppes.npcs.enchants;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import noppes.npcs.LogWriter;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.enchants.EnchantConfusion;
import noppes.npcs.enchants.EnchantDamage;
import noppes.npcs.enchants.EnchantInfinite;
import noppes.npcs.enchants.EnchantPoison;

public abstract class EnchantInterface
extends Enchantment {
    private static EnumEnchantmentType CustomNpcsType;
    public static EnchantInterface Damage;
    public static EnchantInterface Poison;
    public static EnchantInterface Confusion;
    public static EnchantInterface Infinite;
    private Class[] classes;

    protected EnchantInterface(int par2, Class ... obs) {
        super(ConfigMain.EnchantStartId++, par2, CustomNpcsType);
        this.classes = obs;
    }

    public boolean func_92089_a(ItemStack par1ItemStack) {
        if (par1ItemStack.func_77973_b() == null) {
            return false;
        }
        for (Class cls : this.classes) {
            if (!cls.isInstance(par1ItemStack.func_77973_b())) continue;
            return true;
        }
        return false;
    }

    public static void load() {
        if (!ConfigMain.DisableEnchants) {
            CustomNpcsType = EnumHelper.addEnchantmentType((String)"customnpcs_enchants");
            try {
                Damage = new EnchantDamage();
                Poison = new EnchantPoison();
                Confusion = new EnchantConfusion();
                Infinite = new EnchantInfinite();
            }
            catch (Exception e) {
                LogWriter.except(e);
            }
        }
    }

    public static int getLevel(EnchantInterface enchant, ItemStack stack) {
        if (ConfigMain.DisableEnchants || enchant == null) {
            return 0;
        }
        return EnchantmentHelper.func_77506_a((int)enchant.field_77352_x, (ItemStack)stack);
    }
}

