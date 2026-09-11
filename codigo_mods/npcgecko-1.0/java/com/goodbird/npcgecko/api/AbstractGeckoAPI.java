/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package com.goodbird.npcgecko.api;

import cpw.mods.fml.common.Loader;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.entity.EntityNPCInterface;
import software.bernie.geckolib3.core.builder.AnimationBuilder;

public abstract class AbstractGeckoAPI {
    private static AbstractGeckoAPI instance = null;

    public static boolean IsAvailable() {
        return Loader.isModLoaded((String)"geckolib3");
    }

    public static AbstractGeckoAPI Instance() {
        if (instance != null) {
            return instance;
        }
        if (!AbstractGeckoAPI.IsAvailable()) {
            return null;
        }
        try {
            Class<?> c = Class.forName("com.goodbird.npcgecko.api.GeckoAPI");
            instance = (AbstractGeckoAPI)c.getMethod("Instance", new Class[0]).invoke(null, new Object[0]);
        }
        catch (Exception exception) {
            // empty catch block
        }
        return instance;
    }

    public abstract AnimationBuilder createAnimationBuilder();

    public abstract void syncAnimForPlayer(ICustomNpc<EntityNPCInterface> var1, AnimationBuilder var2, IPlayer<EntityPlayerMP> var3);

    public abstract void syncAnimForAll(ICustomNpc<EntityNPCInterface> var1, AnimationBuilder var2);
}

