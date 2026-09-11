/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.player.EntityInteractEvent
 */
package com.tobiasmjc.dbcadditions.event;

import JinRyuu.JRMCore.JRMCoreH;
import com.tobiasmjc.dbcadditions.data.DBCAPlayer;
import com.tobiasmjc.dbcadditions.data.DBCAPlayerEProperties;
import com.tobiasmjc.dbcadditions.data.ability.DBCAAbilities;
import com.tobiasmjc.dbcadditions.packets.DBUPacketAbsorb;
import com.tobiasmjc.dbcadditions.packets.DBUPackets;
import com.tobiasmjc.dbcadditions.utils.LivingUtils;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class DBCALivingEvents {
    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onLivingHurt(LivingHurtEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            if (!DBCAAbilities.isAbsorbing(player)) {
                return;
            }
            int received = JRMCoreH.getInt(player, "jrmcLastDamageReceived");
            player.field_70170_p.func_72956_a((Entity)player, "jinryuudragonbc:DBC4.block1", 0.5f, 0.9f / (player.field_70170_p.field_73012_v.nextFloat() * 0.6f + 0.9f));
            DBCAAbilities.AbsorbingMap.get((Object)player.func_70005_c_()).receivedDamage += (float)received;
        } else if (event.source.func_76346_g() instanceof EntityPlayer && !event.source.func_76355_l().equalsIgnoreCase("mob")) {
            EntityPlayer player = (EntityPlayer)event.source.func_76346_g();
            if (!DBCAAbilities.isAbsorbing(player)) {
                return;
            }
            if (event.entity != null && event.entity.func_145782_y() == DBCAAbilities.AbsorbingMap.get((Object)player.func_70005_c_()).target) {
                LivingUtils.knockback(event.entityLiving, (Entity)player, 6);
            }
            DBCAAbilities.stopAbsorbing(player);
            DBUPackets.sendToAll(new DBUPacketAbsorb(player.func_70005_c_(), event.entity.func_145782_y(), false));
        }
    }

    @SubscribeEvent
    public void onEntityInteract(EntityInteractEvent event) {
        if (event.target instanceof EntityPlayer) {
            EntityPlayer clickedPlayer = (EntityPlayer)event.target;
            EntityPlayer clickingPlayer = event.entityPlayer;
            DBCAPlayerEProperties prop1 = (DBCAPlayerEProperties)clickingPlayer.getExtendedProperties("DBCAInventory");
            DBCAPlayerEProperties prop2 = (DBCAPlayerEProperties)clickedPlayer.getExtendedProperties("DBCAInventory");
            ItemStack right1 = prop1.inventory.func_70301_a(1);
            ItemStack left1 = prop1.inventory.func_70301_a(0);
            ItemStack right2 = prop2.inventory.func_70301_a(1);
            ItemStack left2 = prop2.inventory.func_70301_a(0);
            if (right1 != null && left2 != null) {
                if (left1 == null && right2 == null && right1.func_77973_b() == left2.func_77973_b()) {
                    DBCAPlayer dbcaPlayer = DBCAPlayer.get(clickedPlayer);
                    dbcaPlayer.fuse(clickingPlayer, true);
                }
            } else if (left1 != null && right2 != null && right1 == null && left2 == null && left1.func_77973_b() == right2.func_77973_b()) {
                DBCAPlayer dbcaPlayer = DBCAPlayer.get(clickedPlayer);
                dbcaPlayer.fuse(clickingPlayer, true);
            }
        }
    }
}

