/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.addon;

import kamkeel.npcs.addon.DBCAddon;
import kamkeel.npcs.addon.GeckoAddon;
import kamkeel.npcs.addon.client.DBCClient;
import kamkeel.npcs.addon.client.GeckoAddonClient;

public class AddonManager {
    public static AddonManager Instance;

    public AddonManager() {
        Instance = this;
        this.load();
    }

    public void load() {
        new GeckoAddon();
        new GeckoAddonClient();
        new DBCAddon();
        new DBCClient();
    }
}

