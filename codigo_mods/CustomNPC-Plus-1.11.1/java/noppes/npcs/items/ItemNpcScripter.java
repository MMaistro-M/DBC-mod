/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package noppes.npcs.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.config.ConfigScript;
import noppes.npcs.constants.EnumGuiType;

public class ItemNpcScripter
extends Item {
    public ItemNpcScripter() {
        this.field_77777_bU = 1;
        this.func_77637_a(CustomItems.tab);
    }

    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        return 9127187;
    }

    public boolean func_77623_v() {
        return true;
    }

    public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
        if (!world.field_72995_K) {
            if (!ConfigScript.canScript(player, CustomNpcsPermissions.TOOL_SCRIPTER)) {
                player.func_145747_a((IChatComponent)new ChatComponentTranslation("availability.permission", new Object[0]));
            }
            return itemStack;
        }
        CustomNpcs.proxy.openGui(0, 0, 0, EnumGuiType.ScriptEvent, player);
        return itemStack;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = Items.field_151037_a.func_77617_a(0);
    }

    public Item func_77655_b(String name) {
        GameRegistry.registerItem((Item)this, (String)name);
        return super.func_77655_b(name);
    }
}

