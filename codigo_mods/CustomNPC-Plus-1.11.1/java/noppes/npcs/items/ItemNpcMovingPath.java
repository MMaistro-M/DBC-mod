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
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package noppes.npcs.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.entity.EntityNPCInterface;

public class ItemNpcMovingPath
extends Item {
    public ItemNpcMovingPath() {
        this.field_77777_bU = 1;
        this.func_77637_a(CustomItems.tab);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        block5: {
            block4: {
                if (par2World.field_72995_K) break block4;
                if (CustomNpcsPermissions.hasPermission(par3EntityPlayer, CustomNpcsPermissions.TOOL_MOUNTER)) break block5;
            }
            return par1ItemStack;
        }
        EntityNPCInterface npc = this.getNpc(par1ItemStack, par2World);
        if (npc != null) {
            NoppesUtilServer.sendOpenGui(par3EntityPlayer, EnumGuiType.MovingPath, npc);
        }
        return par1ItemStack;
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer player, World par3World, int x, int y, int z, int par7, float par8, float par9, float par10) {
        block6: {
            block5: {
                if (par3World.field_72995_K) break block5;
                if (CustomNpcsPermissions.hasPermission(player, CustomNpcsPermissions.TOOL_MOUNTER)) break block6;
            }
            return false;
        }
        EntityNPCInterface npc = this.getNpc(par1ItemStack, par3World);
        if (npc == null) {
            return false;
        }
        List<int[]> list = npc.ais.getMovingPath();
        int[] pos = list.get(list.size() - 1);
        list.add(new int[]{x, y, z});
        double d3 = x - pos[0];
        double d4 = y - pos[1];
        double d5 = z - pos[2];
        double distance = MathHelper.func_76133_a((double)(d3 * d3 + d4 * d4 + d5 * d5));
        player.func_145747_a((IChatComponent)new ChatComponentText("Added point x:" + x + " y:" + y + " z:" + z + " to npc " + npc.func_70005_c_()));
        if (distance > (double)ConfigMain.NpcNavRange) {
            player.func_145747_a((IChatComponent)new ChatComponentText("Warning: point is too far away from previous point. Max block walk distance = " + ConfigMain.NpcNavRange));
        }
        return true;
    }

    private EntityNPCInterface getNpc(ItemStack item, World world) {
        if (world.field_72995_K || item.field_77990_d == null) {
            return null;
        }
        Entity entity = world.func_73045_a(item.field_77990_d.func_74762_e("NPCID"));
        if (entity == null || !(entity instanceof EntityNPCInterface)) {
            return null;
        }
        return (EntityNPCInterface)entity;
    }

    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        return 9127187;
    }

    public boolean func_77623_v() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = Items.field_151040_l.func_77617_a(0);
    }

    public Item func_77655_b(String name) {
        GameRegistry.registerItem((Item)this, (String)name);
        return super.func_77655_b(name);
    }
}

