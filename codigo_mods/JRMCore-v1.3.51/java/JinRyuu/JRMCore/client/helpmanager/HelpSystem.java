/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.GameSettings
 */
package JinRyuu.JRMCore.client.helpmanager;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreKeyHandler;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import java.time.Duration;
import java.time.Instant;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;

public class HelpSystem {
    public static final int RED = 15602438;
    public static final int GREEN = 838686;
    public static final int YELLOW = 15445286;
    public static int helpTimerValue = 20;
    public static Instant helpTimer;
    public static boolean giveHelp;
    public static boolean createCharacter;
    public static boolean chargeKi;
    public static boolean extraInventory;
    public static boolean skillsDBC;
    public static boolean skillsNC;
    public static boolean gainTP;
    public static boolean fly;
    public static boolean checkedJBRA;
    public static boolean transformFail;

    public static void update() {
        if (helpTimer == null && !giveHelp) {
            helpTimer = Instant.now();
        } else if (helpTimer != null && Duration.between(helpTimer, Instant.now()).toMillis() / 1000L > (long)helpTimerValue) {
            if (helpTimerValue < 100) {
                helpTimerValue *= 6;
            }
            giveHelp = true;
            helpTimer = null;
        }
        boolean renderLocation = true;
        if (JGConfigClientSettings.errorNotificationsOn && !checkedJBRA) {
            checkedJBRA = true;
            if (!JRMCoreH.JBRA()) {
                JRMCoreClient.phc.handleNotification("JBRA Mod Missing", "JinGames JBRA Mod is Missing! Our mods require this mod to render important content for the mod!\nVisit our site https://dl.jingames.net to download JBRA.", (byte)2, (byte)6, (byte)1, 15602438);
            }
        }
        if (JGConfigClientSettings.errorNotificationsOn && !transformFail && JRMCoreH.Pwrtyp != 0 && JRMCoreKeyHandler.KiAscend.func_151468_f() && JRMCoreH.curRelease == 0) {
            transformFail = true;
            JRMCoreClient.phc.handleNotification("Unable to Transform", "Hold " + GameSettings.func_74298_c((int)JRMCoreKeyHandler.KiCharge.func_151463_i()) + " to charge your Energy, and increase your Release Level %, which can be found on the top-left side on your Energy bar.", (byte)2, (byte)6, (byte)1, 15602438);
        }
        if (JGConfigClientSettings.tipNotificationsOn && giveHelp) {
            if (!createCharacter && JRMCoreH.Pwrtyp == 0) {
                createCharacter = true;
                giveHelp = false;
                JRMCoreClient.phc.handleNotification("Press " + GameSettings.func_74298_c((int)JRMCoreKeyHandler.DS.func_151463_i()) + " to create a Character", "You must first create a character to fight!", (byte)4, (byte)21, (byte)1, 15445286);
            } else if (!chargeKi && JRMCoreH.Pwrtyp != 0 && JRMCoreH.curRelease == 0) {
                chargeKi = true;
                giveHelp = false;
                JRMCoreClient.phc.handleNotification("Hold " + GameSettings.func_74298_c((int)JRMCoreKeyHandler.KiCharge.func_151463_i()) + " to charge your Ki", "You must charge your Energy to use your powers!\nIncrease your Release Level %, which can be found on the top-left side on your Energy bar.", (byte)4, (byte)1, (byte)1, 15445286);
            } else if (!fly && JRMCoreH.isPowerTypeKi()) {
                boolean hasFly = false;
                if (JRMCoreH.PlyrSkills != null) {
                    for (int i = 0; i < JRMCoreH.PlyrSkills.length; ++i) {
                        String currentSkill = JRMCoreH.PlyrSkills[i];
                        if (JRMCoreH.PlyrSkills[i] == null || currentSkill.contains("pty") || currentSkill.length() <= 2 || !currentSkill.substring(0, 2).equals("FL")) continue;
                        hasFly = true;
                        break;
                    }
                }
                if (hasFly) {
                    fly = true;
                    giveHelp = false;
                    JRMCoreClient.phc.handleNotification("Press F to Fly", "You can use Flight by pressing: " + GameSettings.func_74298_c((int)JRMCoreKeyHandler.KiFlight.func_151463_i()), (byte)4, (byte)1, (byte)1, 15445286);
                }
            } else if (!extraInventory) {
                extraInventory = true;
                giveHelp = false;
                JRMCoreClient.phc.handleNotification("Extra Inventory", "Press 2nd Function Key (" + GameSettings.func_74298_c((int)JRMCoreKeyHandler.Fn.func_151463_i()) + ") + Inventory Key (" + GameSettings.func_74298_c((int)Minecraft.func_71410_x().field_71474_y.field_151445_Q.func_151463_i()) + ") to open up your Extra Inventory slots.\nThere you can place Scouters, weights, Vanity, and other armor items.", (byte)4, (byte)1, (byte)1, 15445286);
            } else if (!gainTP && JRMCoreH.Pwrtyp != 0) {
                gainTP = true;
                giveHelp = false;
                JRMCoreClient.phc.handleNotification("Punch to gain TP", "You can gain TP (Training Points) by punching creatures. Then you can use TP to level up your attribute points on your Data Sheet (" + GameSettings.func_74298_c((int)JRMCoreKeyHandler.DS.func_151463_i()) + ")", (byte)4, (byte)1, (byte)1, 15445286);
            } else if (!skillsDBC && JRMCoreH.isPowerTypeKi()) {
                skillsDBC = true;
                giveHelp = false;
                JRMCoreClient.phc.handleNotification("Learn Skills", "You can learn DBC skills from Masters all around the world to improve your abilities. Try visiting Kami at the coordinates X:0 Z:0!", (byte)4, (byte)1, (byte)1, 15445286);
            } else if (!skillsNC && JRMCoreH.isPowerTypeChakra()) {
                skillsNC = true;
                giveHelp = false;
                JRMCoreClient.phc.handleNotification("Learn Skills", "You can learn Naruto C skills by opening your Data Sheet (" + GameSettings.func_74298_c((int)JRMCoreKeyHandler.DS.func_151463_i()) + "), and click on Skills and Learn at the top right corner.", (byte)4, (byte)1, (byte)1, 15445286);
            }
        }
    }

    static {
        giveHelp = false;
        createCharacter = false;
        chargeKi = false;
        extraInventory = false;
        skillsDBC = false;
        skillsNC = false;
        gainTP = false;
        fly = false;
        checkedJBRA = false;
        transformFail = false;
    }
}

