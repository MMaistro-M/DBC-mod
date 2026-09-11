/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 */
package JinRyuu.FamilyC;

import JinRyuu.FamilyC.FamilyCComTickH;
import JinRyuu.JRMCore.p.FamilyCP;
import cpw.mods.fml.common.FMLCommonHandler;

public class FamilyC {
    public static Class[] registerPackets = new Class[]{FamilyCP.class};

    public void registerRenderThings() {
    }

    public void registerTicks() {
        FMLCommonHandler.instance().bus().register((Object)new FamilyCComTickH());
    }

    public void postInit() {
    }

    public void registerKeys() {
    }
}

