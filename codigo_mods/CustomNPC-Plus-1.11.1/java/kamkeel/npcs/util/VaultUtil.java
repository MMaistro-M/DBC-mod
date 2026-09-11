/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package kamkeel.npcs.util;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import kamkeel.npcs.util.BukkitUtil;
import net.minecraft.entity.player.EntityPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VaultUtil {
    private static final Logger logger = LogManager.getLogger(VaultUtil.class);
    private static boolean initialized = false;
    private static boolean vaultEnabled = false;
    private static Class<?> economyClass;
    private static Class<?> economyResponseClass;
    private static Method isEnabled;
    private static Method getName;
    private static Method hasAccount;
    private static Method hasAccountOffline;
    private static Method getBalance;
    private static Method getBalanceOffline;
    private static Method has;
    private static Method hasOffline;
    private static Method withdrawPlayer;
    private static Method withdrawPlayerOffline;
    private static Method depositPlayer;
    private static Method depositPlayerOffline;
    private static Method format;
    private static Method currencyNamePlural;
    private static Method currencyNameSingular;
    private static Method createPlayerAccount;
    private static Method createPlayerAccountOffline;
    private static Method hasBankSupport;
    private static Method getBanks;
    private static Method bankBalance;
    private static Method bankHas;
    private static Method bankWithdraw;
    private static Method bankDeposit;
    private static Method transactionSuccess;
    private static Object economyInstance;

    public static void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        if (!BukkitUtil.isEnabled()) {
            logger.debug("Bukkit not available, Vault integration disabled");
            return;
        }
        try {
            Class<?> offlinePlayerClass = BukkitUtil.getOfflinePlayerClass();
            if (offlinePlayerClass == null) {
                logger.error("Could not get OfflinePlayer class from BukkitUtil");
                return;
            }
            Object vault = BukkitUtil.getPlugin("Vault");
            if (vault == null) {
                return;
            }
            ClassLoader vaultLoader = vault.getClass().getClassLoader();
            economyClass = vaultLoader.loadClass("net.milkbowl.vault.economy.Economy");
            economyResponseClass = vaultLoader.loadClass("net.milkbowl.vault.economy.EconomyResponse");
            isEnabled = economyClass.getMethod("isEnabled", new Class[0]);
            getName = economyClass.getMethod("getName", new Class[0]);
            hasAccount = economyClass.getMethod("hasAccount", String.class);
            hasAccountOffline = economyClass.getMethod("hasAccount", offlinePlayerClass);
            getBalance = economyClass.getMethod("getBalance", String.class);
            getBalanceOffline = economyClass.getMethod("getBalance", offlinePlayerClass);
            has = economyClass.getMethod("has", String.class, Double.TYPE);
            hasOffline = economyClass.getMethod("has", offlinePlayerClass, Double.TYPE);
            withdrawPlayer = economyClass.getMethod("withdrawPlayer", String.class, Double.TYPE);
            withdrawPlayerOffline = economyClass.getMethod("withdrawPlayer", offlinePlayerClass, Double.TYPE);
            depositPlayer = economyClass.getMethod("depositPlayer", String.class, Double.TYPE);
            depositPlayerOffline = economyClass.getMethod("depositPlayer", offlinePlayerClass, Double.TYPE);
            format = economyClass.getMethod("format", Double.TYPE);
            currencyNamePlural = economyClass.getMethod("currencyNamePlural", new Class[0]);
            currencyNameSingular = economyClass.getMethod("currencyNameSingular", new Class[0]);
            createPlayerAccount = economyClass.getMethod("createPlayerAccount", String.class);
            createPlayerAccountOffline = economyClass.getMethod("createPlayerAccount", offlinePlayerClass);
            hasBankSupport = economyClass.getMethod("hasBankSupport", new Class[0]);
            getBanks = economyClass.getMethod("getBanks", new Class[0]);
            bankBalance = economyClass.getMethod("bankBalance", String.class);
            bankHas = economyClass.getMethod("bankHas", String.class, Double.TYPE);
            bankWithdraw = economyClass.getMethod("bankWithdraw", String.class, Double.TYPE);
            bankDeposit = economyClass.getMethod("bankDeposit", String.class, Double.TYPE);
            transactionSuccess = economyResponseClass.getMethod("transactionSuccess", new Class[0]);
            economyInstance = BukkitUtil.getServiceProvider(economyClass);
            if (economyInstance != null) {
                vaultEnabled = true;
                logger.info("Vault Economy integration enabled - Provider: " + VaultUtil.getEconomyName());
            } else {
                logger.info("Vault found but no Economy provider registered");
            }
        }
        catch (ClassNotFoundException e) {
            logger.debug("Vault not found, economy integration disabled");
        }
        catch (NoSuchMethodException e) {
            logger.error("Vault API method not found, economy integration disabled", (Throwable)e);
        }
        catch (Exception e) {
            logger.error("Error initializing Vault integration", (Throwable)e);
        }
    }

    public static void refreshEconomyProvider() {
        if (!initialized || !BukkitUtil.isEnabled()) {
            return;
        }
        try {
            economyInstance = BukkitUtil.getServiceProvider(economyClass);
            if (economyInstance != null) {
                vaultEnabled = true;
                logger.info("Vault Economy provider refreshed: " + VaultUtil.getEconomyName());
            }
        }
        catch (Exception e) {
            logger.error("Error refreshing Vault economy provider", (Throwable)e);
        }
    }

    public static boolean isEnabled() {
        if (!vaultEnabled || economyInstance == null) {
            return false;
        }
        try {
            return (Boolean)isEnabled.invoke(economyInstance, new Object[0]);
        }
        catch (Exception e) {
            logger.error("Error checking if economy is enabled", (Throwable)e);
            return false;
        }
    }

    public static String getEconomyName() {
        if (!vaultEnabled || economyInstance == null) {
            return null;
        }
        try {
            return (String)getName.invoke(economyInstance, new Object[0]);
        }
        catch (Exception e) {
            logger.error("Error getting economy name", (Throwable)e);
            return null;
        }
    }

    public static boolean hasAccount(EntityPlayer player) {
        return VaultUtil.hasAccount(player.func_70005_c_());
    }

    public static boolean hasAccount(String playerName) {
        if (!vaultEnabled || economyInstance == null) {
            return false;
        }
        try {
            Object offlinePlayer = BukkitUtil.getOfflinePlayer(playerName);
            if (offlinePlayer != null) {
                return (Boolean)hasAccountOffline.invoke(economyInstance, offlinePlayer);
            }
            return (Boolean)hasAccount.invoke(economyInstance, playerName);
        }
        catch (Exception e) {
            logger.error("Error checking if player has account: " + playerName, (Throwable)e);
            return false;
        }
    }

    public static double getBalance(EntityPlayer player) {
        return VaultUtil.getBalance(player.func_70005_c_());
    }

    public static double getBalance(String playerName) {
        if (!vaultEnabled || economyInstance == null) {
            return 0.0;
        }
        try {
            Object offlinePlayer = BukkitUtil.getOfflinePlayer(playerName);
            if (offlinePlayer != null) {
                return (Double)getBalanceOffline.invoke(economyInstance, offlinePlayer);
            }
            return (Double)getBalance.invoke(economyInstance, playerName);
        }
        catch (Exception e) {
            logger.error("Error getting balance for player: " + playerName, (Throwable)e);
            return 0.0;
        }
    }

    public static boolean has(EntityPlayer player, double amount) {
        return VaultUtil.has(player.func_70005_c_(), amount);
    }

    public static boolean has(String playerName, double amount) {
        if (!vaultEnabled || economyInstance == null) {
            return false;
        }
        try {
            Object offlinePlayer = BukkitUtil.getOfflinePlayer(playerName);
            if (offlinePlayer != null) {
                return (Boolean)hasOffline.invoke(economyInstance, offlinePlayer, amount);
            }
            return (Boolean)has.invoke(economyInstance, playerName, amount);
        }
        catch (Exception e) {
            logger.error("Error checking if player has amount: " + playerName, (Throwable)e);
            return false;
        }
    }

    public static boolean withdrawMoney(EntityPlayer player, double amount) {
        return VaultUtil.withdrawMoney(player.func_70005_c_(), amount);
    }

    public static boolean withdrawMoney(String playerName, double amount) {
        if (!vaultEnabled || economyInstance == null) {
            return false;
        }
        try {
            Object offlinePlayer = BukkitUtil.getOfflinePlayer(playerName);
            Object response = offlinePlayer != null ? withdrawPlayerOffline.invoke(economyInstance, offlinePlayer, amount) : withdrawPlayer.invoke(economyInstance, playerName, amount);
            return (Boolean)transactionSuccess.invoke(response, new Object[0]);
        }
        catch (Exception e) {
            logger.error("Error withdrawing money from player: " + playerName, (Throwable)e);
            return false;
        }
    }

    public static boolean addMoney(EntityPlayer player, double amount) {
        return VaultUtil.addMoney(player.func_70005_c_(), amount);
    }

    public static boolean addMoney(String playerName, double amount) {
        if (!vaultEnabled || economyInstance == null) {
            return false;
        }
        try {
            Object offlinePlayer = BukkitUtil.getOfflinePlayer(playerName);
            Object response = offlinePlayer != null ? depositPlayerOffline.invoke(economyInstance, offlinePlayer, amount) : depositPlayer.invoke(economyInstance, playerName, amount);
            return (Boolean)transactionSuccess.invoke(response, new Object[0]);
        }
        catch (Exception e) {
            logger.error("Error adding money to player: " + playerName, (Throwable)e);
            return false;
        }
    }

    public static boolean setBalance(EntityPlayer player, double amount) {
        return VaultUtil.setBalance(player.func_70005_c_(), amount);
    }

    public static boolean setBalance(String playerName, double amount) {
        if (!vaultEnabled || economyInstance == null) {
            return false;
        }
        double currentBalance = VaultUtil.getBalance(playerName);
        if (currentBalance > 0.0 && !VaultUtil.withdrawMoney(playerName, currentBalance)) {
            return false;
        }
        if (amount > 0.0) {
            return VaultUtil.addMoney(playerName, amount);
        }
        return true;
    }

    public static String format(double amount) {
        if (!vaultEnabled || economyInstance == null) {
            return String.valueOf(amount);
        }
        try {
            return (String)format.invoke(economyInstance, amount);
        }
        catch (Exception e) {
            logger.error("Error formatting amount", (Throwable)e);
            return String.valueOf(amount);
        }
    }

    public static String getCurrencyNamePlural() {
        if (!vaultEnabled || economyInstance == null) {
            return "coins";
        }
        try {
            return (String)currencyNamePlural.invoke(economyInstance, new Object[0]);
        }
        catch (Exception e) {
            logger.error("Error getting currency name plural", (Throwable)e);
            return "coins";
        }
    }

    public static String getCurrencyNameSingular() {
        if (!vaultEnabled || economyInstance == null) {
            return "coin";
        }
        try {
            return (String)currencyNameSingular.invoke(economyInstance, new Object[0]);
        }
        catch (Exception e) {
            logger.error("Error getting currency name singular", (Throwable)e);
            return "coin";
        }
    }

    public static boolean createAccount(EntityPlayer player) {
        return VaultUtil.createAccount(player.func_70005_c_());
    }

    public static boolean createAccount(String playerName) {
        if (!vaultEnabled || economyInstance == null) {
            return false;
        }
        if (VaultUtil.hasAccount(playerName)) {
            return true;
        }
        try {
            Object offlinePlayer = BukkitUtil.getOfflinePlayer(playerName);
            if (offlinePlayer != null) {
                return (Boolean)createPlayerAccountOffline.invoke(economyInstance, offlinePlayer);
            }
            return (Boolean)createPlayerAccount.invoke(economyInstance, playerName);
        }
        catch (Exception e) {
            logger.error("Error creating account for player: " + playerName, (Throwable)e);
            return false;
        }
    }

    public static boolean hasBankSupport() {
        if (!vaultEnabled || economyInstance == null) {
            return false;
        }
        try {
            return (Boolean)hasBankSupport.invoke(economyInstance, new Object[0]);
        }
        catch (Exception e) {
            logger.error("Error checking bank support", (Throwable)e);
            return false;
        }
    }

    public static List<String> getBanks() {
        if (!vaultEnabled || economyInstance == null) {
            return Collections.emptyList();
        }
        try {
            return (List)getBanks.invoke(economyInstance, new Object[0]);
        }
        catch (Exception e) {
            logger.error("Error getting banks", (Throwable)e);
            return Collections.emptyList();
        }
    }

    public static double getBankBalance(String bankName) {
        if (!vaultEnabled || economyInstance == null) {
            return 0.0;
        }
        try {
            Object response = bankBalance.invoke(economyInstance, bankName);
            return economyResponseClass.getField("balance").getDouble(response);
        }
        catch (Exception e) {
            logger.error("Error getting bank balance: " + bankName, (Throwable)e);
            return 0.0;
        }
    }

    public static boolean bankHas(String bankName, double amount) {
        if (!vaultEnabled || economyInstance == null) {
            return false;
        }
        try {
            Object response = bankHas.invoke(economyInstance, bankName, amount);
            return (Boolean)transactionSuccess.invoke(response, new Object[0]);
        }
        catch (Exception e) {
            logger.error("Error checking bank balance: " + bankName, (Throwable)e);
            return false;
        }
    }

    public static boolean bankWithdraw(String bankName, double amount) {
        if (!vaultEnabled || economyInstance == null) {
            return false;
        }
        try {
            Object response = bankWithdraw.invoke(economyInstance, bankName, amount);
            return (Boolean)transactionSuccess.invoke(response, new Object[0]);
        }
        catch (Exception e) {
            logger.error("Error withdrawing from bank: " + bankName, (Throwable)e);
            return false;
        }
    }

    public static boolean bankDeposit(String bankName, double amount) {
        if (!vaultEnabled || economyInstance == null) {
            return false;
        }
        try {
            Object response = bankDeposit.invoke(economyInstance, bankName, amount);
            return (Boolean)transactionSuccess.invoke(response, new Object[0]);
        }
        catch (Exception e) {
            logger.error("Error depositing to bank: " + bankName, (Throwable)e);
            return false;
        }
    }

    public static boolean transfer(EntityPlayer from, EntityPlayer to, double amount) {
        return VaultUtil.transfer(from.func_70005_c_(), to.func_70005_c_(), amount);
    }

    public static boolean transfer(String fromPlayerName, String toPlayerName, double amount) {
        if (!VaultUtil.has(fromPlayerName, amount)) {
            return false;
        }
        if (!VaultUtil.withdrawMoney(fromPlayerName, amount)) {
            return false;
        }
        if (!VaultUtil.addMoney(toPlayerName, amount)) {
            VaultUtil.addMoney(fromPlayerName, amount);
            return false;
        }
        return true;
    }
}

