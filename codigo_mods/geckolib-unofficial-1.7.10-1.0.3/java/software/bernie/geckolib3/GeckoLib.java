/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package software.bernie.geckolib3;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.resource.ResourceListener;

public class GeckoLib {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String ModID = "geckolib3";
    public static boolean hasInitialized;
    public static final String VERSION = "3.0.30";

    public static void initialize() {
        if (!hasInitialized) {
            GeckoLib.callFuture(new FutureTask<Object>(() -> {
                if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
                    GeckoLib.doOnlyOnClient();
                }
            }, null));
        }
        hasInitialized = true;
    }

    public static void callFuture(FutureTask<?> task) {
        try {
            task.run();
            task.get();
        }
        catch (InterruptedException | ExecutionException e) {
            FMLLog.getLogger().fatal("Exception caught executing FutureTask: {}", new Object[]{e.toString(), e});
        }
    }

    @SideOnly(value=Side.CLIENT)
    private static void doOnlyOnClient() {
        ResourceListener.registerReloadListener();
    }
}

