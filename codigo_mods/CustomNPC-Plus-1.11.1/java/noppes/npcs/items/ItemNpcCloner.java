/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package noppes.npcs.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.NoppesUtilServer;

public class ItemNpcCloner
extends Item {
    public ItemNpcCloner() {
        this.field_77777_bU = 1;
        this.func_77637_a(CustomItems.tab);
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        block3: {
            block2: {
                if (par3World.field_72995_K) break block2;
                if (CustomNpcsPermissions.hasPermission(par2EntityPlayer, CustomNpcsPermissions.TOOL_CLONER)) break block3;
            }
            return false;
        }
        NoppesUtilServer.setClonerGui((EntityPlayerMP)par2EntityPlayer, par4, par5, par6);
        return true;
    }

    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        return 9127187;
    }

    public boolean func_77623_v() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = Items.field_151036_c.func_77617_a(0);
    }

    public Item func_77655_b(String name) {
        GameRegistry.registerItem((Item)this, (String)name);
        return super.func_77655_b(name);
    }
}

