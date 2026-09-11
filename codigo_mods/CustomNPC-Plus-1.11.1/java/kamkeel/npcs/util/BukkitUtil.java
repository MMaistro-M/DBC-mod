/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package kamkeel.npcs.util;

import java.lang.reflect.Method;
import kamkeel.npcs.util.VaultUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BukkitUtil {
    private static final Logger logger = LogManager.getLogger(BukkitUtil.class);
    private static boolean initialized = false;
    private static boolean bukkitEnabled = false;
    private static Class<?> bukkitClass;
    private static Class<?> serverClass;
    private static Class<?> servicesManagerClass;
    private static Class<?> registeredServiceProviderClass;
    private static Class<?> offlinePlayerClass;
    private static Class<?> playerClass;
    private static Method getServer;
    private static Method getServicesManager;
    private static Method getRegistration;
    private static Method getProvider;
    private static Method getOfflinePlayer;
    private static Method getPlayer;
    private static Method getPluginManager;
    private static Method isPluginEnabled;
    private static Method getPlugin;

    public static void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        try {
            bukkitClass = Class.forName("org.bukkit.Bukkit");
            serverClass = Class.forName("org.bukkit.Server");
            servicesManagerClass = Class.forName("org.bukkit.plugin.ServicesManager");
            registeredServiceProviderClass = Class.forName("org.bukkit.plugin.RegisteredServiceProvider");
            offlinePlayerClass = Class.forName("org.bukkit.OfflinePlayer");
            playerClass = Class.forName("org.bukkit.entity.Player");
            getServer = bukkitClass.getMethod("getServer", new Class[0]);
            getServicesManager = bukkitClass.getMethod("getServicesManager", new Class[0]);
            getRegistration = servicesManagerClass.getMethod("getRegistration", Class.class);
            getProvider = registeredServiceProviderClass.getMethod("getProvider", new Class[0]);
            getOfflinePlayer = bukkitClass.getMethod("getOfflinePlayer", String.class);
            getPlayer = bukkitClass.getMethod("getPlayer", String.class);
            getPluginManager = bukkitClass.getMethod("getPluginManager", new Class[0]);
            Class<?> pluginManagerClass = Class.forName("org.bukkit.plugin.PluginManager");
            isPluginEnabled = pluginManagerClass.getMethod("isPluginEnabled", String.class);
            getPlugin = pluginManagerClass.getMethod("getPlugin", String.class);
            bukkitEnabled = true;
            logger.info("Bukkit integration enabled");
            VaultUtil.init();
        }
        catch (ClassNotFoundException e) {
            logger.debug("Bukkit not found, Bukkit integration disabled");
        }
        catch (NoSuchMethodException e) {
            logger.error("Bukkit API method not found", (Throwable)e);
        }
        catch (Exception e) {
            logger.error("Error initializing Bukkit integration", (Throwable)e);
        }
    }

    public static boolean isEnabled() {
        return bukkitEnabled;
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static boolean isPluginEnabled(String pluginName) {
        if (!bukkitEnabled) {
            return false;
        }
        try {
            Object pluginManager = getPluginManager.invoke(null, new Object[0]);
            return (Boolean)isPluginEnabled.invoke(pluginManager, pluginName);
        }
        catch (Exception e) {
            logger.error("Error checking if plugin is enabled: " + pluginName, (Throwable)e);
            return false;
        }
    }

    public static Object getOfflinePlayer(String playerName) {
        if (!bukkitEnabled) {
            return null;
        }
        try {
            return getOfflinePlayer.invoke(null, playerName);
        }
        catch (Exception e) {
            logger.error("Error getting OfflinePlayer: " + playerName, (Throwable)e);
            return null;
        }
    }

    public static Object getPlayer(String playerName) {
        if (!bukkitEnabled) {
            return null;
        }
        try {
            return getPlayer.invoke(null, playerName);
        }
        catch (Exception e) {
            logger.error("Error getting Player: " + playerName, (Throwable)e);
            return null;
        }
    }

    public static Object getServiceProvider(Class<?> serviceClass) {
        if (!bukkitEnabled) {
            return null;
        }
        try {
            Object servicesManager = getServicesManager.invoke(null, new Object[0]);
            Object registration = getRegistration.invoke(servicesManager, serviceClass);
            if (registration != null) {
                return getProvider.invoke(registration, new Object[0]);
            }
        }
        catch (Exception e) {
            logger.error("Error getting service provider: " + serviceClass.getName(), (Throwable)e);
        }
        return null;
    }

    public static Class<?> getBukkitClass() {
        return bukkitClass;
    }

    public static Class<?> getServicesManagerClass() {
        return servicesManagerClass;
    }

    public static Class<?> getRegisteredServiceProviderClass() {
        return registeredServiceProviderClass;
    }

    public static Class<?> getOfflinePlayerClass() {
        return offlinePlayerClass;
    }

    public static Class<?> getPlayerClass() {
        return playerClass;
    }

    public static Object getPlugin(String plugin) {
        try {
            return getPlugin.invoke(getPluginManager.invoke(null, new Object[0]), plugin);
        }
        catch (Throwable throwable) {
            return null;
        }
    }
}

