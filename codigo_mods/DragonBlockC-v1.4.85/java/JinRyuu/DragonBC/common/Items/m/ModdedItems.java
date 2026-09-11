/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraftforge.common.util.EnumHelper
 */
package JinRyuu.DragonBC.common.Items.m;

import JinRyuu.DragonBC.common.Items.m.MyAxes;
import JinRyuu.DragonBC.common.Items.m.MyHoes;
import JinRyuu.DragonBC.common.Items.m.MyPickaxes;
import JinRyuu.DragonBC.common.Items.m.MyShovels;
import JinRyuu.DragonBC.common.Items.m.MySwords;
import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public final class ModdedItems {
    public static Item SakuraStick;
    public static Item MahagonyStick;
    public static Item MapleStick;
    public static Item SakuraBranch;
    public static Item MahagonyBranch;
    public static MySwords SakuraHammer;
    public static MyPickaxes SakuraPickaxe;
    public static MyAxes SakuraAxe;
    public static MySwords SakuraSword;
    public static MyHoes SakuraHoe;
    public static MyShovels SakuraShovel;
    public static MyPickaxes MahagonyPickaxe;
    public static MyAxes MahagonyAxe;
    public static MySwords MahagonySword;
    public static MyHoes MahagonyHoe;
    public static MyShovels MahagonyShovel;
    public static MyPickaxes MaplePickaxe;
    public static MyAxes MapleAxe;
    public static MySwords MapleSword;
    public static MyHoes MapleHoe;
    public static MyShovels MapleShovel;
    public static Item.ToolMaterial Sakura;
    public static Item.ToolMaterial Mahagony;
    public static Item.ToolMaterial Maple;

    public static final void init() {
        SakuraStick = new Item().func_77655_b("Sakura_Stick").func_77637_a(mod_DragonBC.DragonBlockC).func_111206_d(JRMCoreH.tjdbcAssts + ":Sakura_Stick");
        MahagonyStick = new Item().func_77655_b("Mahagony_Stick").func_77637_a(mod_DragonBC.DragonBlockC).func_111206_d(JRMCoreH.tjdbcAssts + ":Mahagony_Stick");
        MapleStick = new Item().func_77655_b("Maple_Stick").func_77637_a(mod_DragonBC.DragonBlockC).func_111206_d(JRMCoreH.tjdbcAssts + ":Maple_Stick");
        SakuraBranch = new Item().func_77655_b("Sakura_Branch").func_77637_a(mod_DragonBC.DragonBlockC).func_111206_d(JRMCoreH.tjdbcAssts + ":Sakura_Branch");
        MahagonyBranch = new Item().func_77655_b("Mahagony_Branch").func_77637_a(mod_DragonBC.DragonBlockC).func_111206_d(JRMCoreH.tjdbcAssts + ":Mahagony_Branch");
        SakuraPickaxe = new MyPickaxes("Sakura_Pickaxe", Sakura);
        SakuraAxe = new MyAxes("Sakura_Axe", Sakura);
        SakuraSword = new MySwords("Sakura_Sword", Sakura);
        SakuraAxe = new MyAxes("Sakura_Axe", Sakura);
        SakuraShovel = new MyShovels("Sakura_Shovel", Sakura);
        SakuraHoe = new MyHoes("Sakura_Hoe", Sakura);
        SakuraHammer = new MySwords("Sakura_Hammer", Sakura);
        MahagonyPickaxe = new MyPickaxes("Mahagony_Pickaxe", Mahagony);
        MahagonyAxe = new MyAxes("Mahagony_Axe", Mahagony);
        MahagonySword = new MySwords("Mahagony_Sword", Mahagony);
        MahagonyAxe = new MyAxes("Mahagony_Axe", Mahagony);
        MahagonyShovel = new MyShovels("Mahagony_Shovel", Mahagony);
        MahagonyHoe = new MyHoes("Mahagony_Hoe", Mahagony);
        MaplePickaxe = new MyPickaxes("Maple_Pickaxe", Maple);
        MapleAxe = new MyAxes("Maple_Axe", Maple);
        MapleSword = new MySwords("Maple_Sword", Maple);
        MapleAxe = new MyAxes("Maple_Axe", Maple);
        MapleShovel = new MyShovels("Maple_Shovel", Maple);
        MapleHoe = new MyHoes("Maple_Hoe", Maple);
        GameRegistry.registerItem((Item)SakuraStick, (String)"Sakura_Stick");
        GameRegistry.registerItem((Item)MahagonyStick, (String)"Mahagony_Stick");
        GameRegistry.registerItem((Item)MapleStick, (String)"Maple_Stick");
        GameRegistry.registerItem((Item)SakuraBranch, (String)"Sakura_Branch");
        GameRegistry.registerItem((Item)MahagonyBranch, (String)"Mahagony_Branch");
        GameRegistry.registerItem((Item)SakuraPickaxe, (String)"Sakura_Pickaxe");
        GameRegistry.registerItem((Item)SakuraAxe, (String)"Sakura_Axe");
        GameRegistry.registerItem((Item)SakuraShovel, (String)"Sakura_Shovel");
        GameRegistry.registerItem((Item)SakuraHoe, (String)"Sakura_Hoe");
        GameRegistry.registerItem((Item)SakuraSword, (String)"Sakura_Sword");
        GameRegistry.registerItem((Item)SakuraHammer, (String)"Sakura_Hammer");
        GameRegistry.registerItem((Item)MahagonyPickaxe, (String)"Mahagony_Pickaxe");
        GameRegistry.registerItem((Item)MahagonyAxe, (String)"Mahagony_Axe");
        GameRegistry.registerItem((Item)MahagonyShovel, (String)"Mahagony_Shovel");
        GameRegistry.registerItem((Item)MahagonyHoe, (String)"Mahagony_Hoe");
        GameRegistry.registerItem((Item)MahagonySword, (String)"Mahagony_Sword");
        GameRegistry.registerItem((Item)MaplePickaxe, (String)"Maple_Pickaxe");
        GameRegistry.registerItem((Item)MapleAxe, (String)"Maple_Axe");
        GameRegistry.registerItem((Item)MapleShovel, (String)"Maple_Shovel");
        GameRegistry.registerItem((Item)MapleHoe, (String)"Maple_Hoe");
        GameRegistry.registerItem((Item)MapleSword, (String)"Maple_Sword");
    }

    static {
        Sakura = EnumHelper.addToolMaterial((String)"Sakura", (int)1, (int)175, (float)5.0f, (float)1.5f, (int)7);
        Mahagony = EnumHelper.addToolMaterial((String)"Mahagony", (int)2, (int)230, (float)7.0f, (float)2.5f, (int)10);
        Maple = EnumHelper.addToolMaterial((String)"Maple", (int)2, (int)450, (float)8.0f, (float)3.5f, (int)14);
    }
}

