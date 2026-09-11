/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package noppes.npcs.items;

import kamkeel.npcs.network.packets.data.ChatAlertPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.config.ConfigScript;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.items.ItemCustomizable;

public class ItemScripted
extends ItemCustomizable {
    public ItemScripted() {
        this.field_77777_bU = 1;
        this.func_77637_a(CustomItems.tab);
        CustomNpcs.proxy.registerItem(this);
        this.func_77627_a(true);
    }

    @Override
    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (!world.field_72995_K && player.func_70093_af() && player.field_71075_bZ.field_75098_d) {
            if (!ConfigScript.canScript(player, CustomNpcsPermissions.TOOL_SCRIPTED_ITEM)) {
                ChatAlertPacket.sendChatAlert((EntityPlayerMP)player, "availability.permission");
            } else {
                NoppesUtilServer.sendOpenGui(player, EnumGuiType.ScriptItem, null, 0, 0, 0);
            }
        }
        return super.func_77659_a(stack, world, player);
    }
}

