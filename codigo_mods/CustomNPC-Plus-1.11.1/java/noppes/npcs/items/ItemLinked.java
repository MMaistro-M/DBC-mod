/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S04PacketEntityEquipment
 *  net.minecraft.world.World
 */
package noppes.npcs.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.world.World;
import noppes.npcs.CustomNpcs;
import noppes.npcs.EventHooks;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.controllers.LinkedItemController;
import noppes.npcs.controllers.data.LinkedItem;
import noppes.npcs.items.ItemCustomizable;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.item.ScriptLinkedItem;

public class ItemLinked
extends ItemCustomizable {
    public ItemLinked() {
        this.field_77777_bU = 1;
        CustomNpcs.proxy.registerItem(this);
        this.func_77627_a(true);
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (world.field_72995_K) {
            return;
        }
        if (world.func_82737_E() % 10L != 0L) {
            return;
        }
        IItemStack itemStack = NpcAPI.Instance().getIItemStack(stack);
        if (itemStack instanceof ScriptLinkedItem) {
            ScriptLinkedItem scriptLinkedItem = (ScriptLinkedItem)itemStack;
            LinkedItem linkedItem = LinkedItemController.getInstance().get(scriptLinkedItem.linkedItem.getId());
            int prevVersion = scriptLinkedItem.linkedVersion;
            if (linkedItem != null && scriptLinkedItem.linkedVersion != linkedItem.version) {
                scriptLinkedItem.linkedItem = linkedItem.clone();
                scriptLinkedItem.linkedVersion = linkedItem.version;
                scriptLinkedItem.saveItemData();
                EventHooks.onLinkedItemVersionChange(scriptLinkedItem, linkedItem.version, prevVersion);
            } else if (linkedItem == null && entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)entity;
                player.field_71071_by.func_70299_a(itemSlot, null);
                player.field_71071_by.func_70296_d();
                if (player instanceof EntityPlayerMP) {
                    EntityPlayerMP playerMP = (EntityPlayerMP)player;
                    int equipmentSlot = -1;
                    if (itemSlot >= 36 && itemSlot < 40) {
                        equipmentSlot = itemSlot - 35;
                    }
                    if (equipmentSlot != -1) {
                        playerMP.field_71135_a.func_147359_a((Packet)new S04PacketEntityEquipment(player.func_145782_y(), equipmentSlot, null));
                    }
                    playerMP.func_71120_a(player.field_71069_bz);
                }
            }
        }
    }
}

