/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package com.goodbird.npcgecko.api;

import com.goodbird.npcgecko.api.AbstractGeckoAPI;
import com.goodbird.npcgecko.network.CPacketSyncManualAnim;
import com.goodbird.npcgecko.network.NetworkHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.entity.EntityNPCInterface;
import software.bernie.geckolib3.core.builder.AnimationBuilder;

public class GeckoAPI
extends AbstractGeckoAPI {
    public static Side side = FMLCommonHandler.instance().getEffectiveSide();
    private static AbstractGeckoAPI Instance;

    private GeckoAPI() {
    }

    public static AbstractGeckoAPI Instance() {
        if (Instance == null) {
            Instance = new GeckoAPI();
        }
        return Instance;
    }

    @Override
    public AnimationBuilder createAnimationBuilder() {
        return new AnimationBuilder();
    }

    @Override
    public void syncAnimForPlayer(ICustomNpc<EntityNPCInterface> npc, AnimationBuilder builder, IPlayer<EntityPlayerMP> player) {
        NetworkHandler.sendToPlayer(new CPacketSyncManualAnim((EntityNPCInterface)((Object)npc.getMCEntity()), builder), (EntityPlayer)player.getMCEntity());
    }

    @Override
    public void syncAnimForAll(ICustomNpc<EntityNPCInterface> npc, AnimationBuilder builder) {
        NetworkHandler.sendToAll(new CPacketSyncManualAnim((EntityNPCInterface)((Object)npc.getMCEntity()), builder));
    }
}

