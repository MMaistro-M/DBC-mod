/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.config;

import java.io.File;
import noppes.npcs.config.ConfigClient;
import noppes.npcs.config.ConfigDebug;
import noppes.npcs.config.ConfigEnergy;
import noppes.npcs.config.ConfigExperimental;
import noppes.npcs.config.ConfigItem;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.config.ConfigMarket;
import noppes.npcs.config.ConfigScript;

public class LoadConfiguration {
    public static File mainConfigFile;
    public static File itemConfigFile;
    public static File scriptConfigFile;
    public static File debugConfigFile;
    public static File clientConfigFile;
    public static File experimentalConfigFile;
    public static File marketConfigFile;
    public static File energyConfigFile;

    public static void init(String configpath) {
        mainConfigFile = new File(configpath + "main.cfg");
        itemConfigFile = new File(configpath + "item.cfg");
        clientConfigFile = new File(configpath + "client.cfg");
        scriptConfigFile = new File(configpath + "script.cfg");
        debugConfigFile = new File(configpath + "debug.cfg");
        experimentalConfigFile = new File(configpath + "experimental.cfg");
        marketConfigFile = new File(configpath + "market.cfg");
        energyConfigFile = new File(configpath + "energy.cfg");
        ConfigMain.init(mainConfigFile);
        ConfigItem.init(itemConfigFile);
        ConfigClient.init(clientConfigFile);
        ConfigScript.init(scriptConfigFile);
        ConfigDebug.init(debugConfigFile);
        ConfigExperimental.init(experimentalConfigFile);
        ConfigMarket.init(marketConfigFile);
        ConfigEnergy.init(energyConfigFile);
    }
}

