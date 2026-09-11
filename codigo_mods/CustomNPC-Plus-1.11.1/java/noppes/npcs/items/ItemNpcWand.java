/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package noppes.npcs.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.entity.EntityCustomNpc;

public class ItemNpcWand
extends Item {
    public ItemNpcWand() {
        this.field_77777_bU = 1;
        this.func_77637_a(CustomItems.tab);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer player) {
        if (!par2World.field_72995_K) {
            return par1ItemStack;
        }
        if (CustomNpcsPermissions.hasPermission(player, CustomNpcsPermissions.NPC_GUI)) {
            CustomNpcs.proxy.openGui(0, 0, 0, EnumGuiType.NpcRemote, player);
        } else {
            player.func_145747_a((IChatComponent)new ChatComponentTranslation("availability.permission", new Object[0]));
        }
        return par1ItemStack;
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer player, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par3World.field_72995_K) {
            return false;
        }
        if (ConfigMain.OpsOnly && !MinecraftServer.func_71276_C().func_71203_ab().func_152596_g(player.func_146103_bH())) {
            player.func_145747_a((IChatComponent)new ChatComponentTranslation("availability.permission", new Object[0]));
            return false;
        }
        if (CustomNpcsPermissions.hasPermission(player, CustomNpcsPermissions.NPC_CREATE)) {
            EntityCustomNpc npc = new EntityCustomNpc(par3World);
            npc.ais.startPos = new int[]{par4, par5, par6};
            npc.func_70012_b((float)par4 + 0.5f, npc.getStartYPos(), (float)par6 + 0.5f, player.field_70177_z, 0.0f);
            par3World.func_72838_d((Entity)npc);
            npc.func_70606_j(npc.func_110138_aP());
            NoppesUtilServer.sendOpenGuiNoDelay(player, EnumGuiType.MainMenuDisplay, npc);
            return true;
        }
        player.func_145747_a((IChatComponent)new ChatComponentTranslation("availability.permission", new Object[0]));
        return false;
    }

    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        return 9127187;
    }

    public boolean func_77623_v() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = Items.field_151019_K.func_77617_a(0);
    }

    public Item func_77655_b(String name) {
        GameRegistry.registerItem((Item)this, (String)name);
        return super.func_77655_b(name);
    }
}

