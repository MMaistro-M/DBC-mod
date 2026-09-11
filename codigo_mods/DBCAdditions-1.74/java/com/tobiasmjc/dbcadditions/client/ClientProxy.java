/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.tobiasmjc.dbcadditions.client;

import com.tobiasmjc.dbcadditions.DBCAKeys;
import com.tobiasmjc.dbcadditions.client.gui.DBAGuiActions;
import com.tobiasmjc.dbcadditions.client.gui.DBAGuiFormWheel;
import com.tobiasmjc.dbcadditions.entities.EntitiesDBCA;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;

@SideOnly(value=Side.CLIENT)
public class ClientProxy {
    public static SimpleNetworkWrapper network;
    public static DBAGuiActions ACTION_MENU;
    public static DBAGuiFormWheel FORM_MENU;
    public static EntityPlayer CurrentPlayerColor;
    public static boolean isRenderingKiBar;

    public static void registerRender() {
        EntitiesDBCA.registerRenderers();
        DBCAKeys.register();
    }

    static {
        ACTION_MENU = new DBAGuiActions();
        FORM_MENU = new DBAGuiFormWheel();
        CurrentPlayerColor = null;
    }
}

