/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  net.minecraft.entity.player.EntityPlayer
 */
package JinRyuu.JYearsC;

import JinRyuu.JRMCore.p.YC.JYearsCP;
import JinRyuu.JYearsC.JYearsCComTickH;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;

public class JYearsC {
    public static Class[] registerPackets = new Class[]{JYearsCP.class};

    public void initialize() {
        FMLCommonHandler.instance().bus().register((Object)new JYearsCComTickH());
    }

    public void postInit() {
    }

    public void registerTickHandler() {
    }

    public void registerRenderThings() {
    }

    public void registerKeys() {
    }

    public EntityPlayer getPlayerEntity(MessageContext ctx) {
        return ctx.getServerHandler().field_147369_b;
    }
}

