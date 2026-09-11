/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.settings.KeyBinding
 */
package com.tobiasmjc.dbcadditions;

import com.tobiasmjc.dbcadditions.packets.DBUPacketOpenGui;
import com.tobiasmjc.dbcadditions.packets.DBUPackets;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;

public class DBCAKeys {
    public static KeyBinding CUSTOM_INVENTORY = new KeyBinding("DBC Additions Inventory", 21, "Dragon Block C Additions");

    public static void register() {
        ClientRegistry.registerKeyBinding((KeyBinding)CUSTOM_INVENTORY);
    }

    public static void update() {
        if (CUSTOM_INVENTORY.func_151468_f()) {
            EntityClientPlayerMP p = Minecraft.func_71410_x().field_71439_g;
            p.openGui((Object)"dbcadditions", 2, p.field_70170_p, (int)p.field_70165_t, (int)p.field_70163_u, (int)p.field_70161_v);
            DBUPackets.sendToServer(new DBUPacketOpenGui(2));
        }
    }
}

