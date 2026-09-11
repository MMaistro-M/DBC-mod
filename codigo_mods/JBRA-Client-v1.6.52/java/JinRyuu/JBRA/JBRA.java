/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 */
package JinRyuu.JBRA;

import JinRyuu.JBRA.JBRAComTickH;
import cpw.mods.fml.common.FMLCommonHandler;

public class JBRA {
    public void registerRenderThings() {
    }

    public void registerTicks() {
        FMLCommonHandler.instance().bus().register((Object)new JBRAComTickH());
    }

    public void postInit() {
    }
}

