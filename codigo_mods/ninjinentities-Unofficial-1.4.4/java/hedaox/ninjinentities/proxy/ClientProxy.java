/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 */
package hedaox.ninjinentities.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import hedaox.ninjinentities.proxy.CommonProxy;
import hedaox.ninjinentities.rendering.entities.NinjinRenderEntities;

public class ClientProxy
extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent $e) {
        super.preInit($e);
        NinjinRenderEntities.init();
    }

    @Override
    public void init(FMLInitializationEvent $e) {
        super.init($e);
    }
}

