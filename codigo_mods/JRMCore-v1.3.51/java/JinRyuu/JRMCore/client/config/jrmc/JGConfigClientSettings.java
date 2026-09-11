/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 */
package JinRyuu.JRMCore.client.config.jrmc;

import JinRyuu.JRMCore.client.notification.JGNotificationGUI;
import JinRyuu.JRMCore.client.notification.JGNotificationHandler;
import JinRyuu.JRMCore.server.config.JGConfigBase;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class JGConfigClientSettings
extends JGConfigBase {
    public static final String CATEGORY_NAME_CLIENTSIDED = "Client Settings";
    public static final String CATEGORY_NAME_NOTIFICATIONS = "Notifications";
    public static final String CLIENT_hud2_3 = "0,0,154,55,0,background,;45,12,104,8,0,0,55,12,2,health,30,150,30,#amount/#max,;49,23,85,11,1,0,55,25,2,energy,0,0,0,#amount/#max,;44,37,59,7,0,0,50,35,2,stamina,255,200,0,#empty,;1,8,10,39,0,2,0,22,2,transform,255,0,255,#empty,;6,6,43,43,0,2,21,22,2,release,255,0,0,#amount,;50,50,2,0,150,50,50,;75,0,2,25,150,255,;";
    public static boolean configsChanged = false;
    public static boolean tipNotificationsOn = true;
    public static boolean errorNotificationsOn = true;
    public static int CLIENT_hud0x;
    public static int CLIENT_hud0y;
    public static int CLIENT_hud0;
    public static int CLIENT_lockon;
    public static int CLIENT_hud1x;
    public static int CLIENT_hud1y;
    public static int CLIENT_hud1;
    public static boolean CLIENT_GR0;
    public static boolean CLIENT_GR1;
    public static boolean CLIENT_GR2;
    public static boolean CLIENT_GR3;
    public static boolean CLIENT_GR4;
    public static boolean CLIENT_GR5;
    public static boolean CLIENT_GR6;
    public static boolean CLIENT_GR7;
    public static boolean CLIENT_GR8;
    public static boolean CLIENT_GR9;
    public static boolean CLIENT_GR10;
    public static boolean CLIENT_GR11;
    public static boolean CLIENT_GR12;
    public static boolean CLIENT_GR13;
    public static int CLIENT_DA1;
    public static int CLIENT_DA2;
    public static int CLIENT_DA3;
    public static boolean CLIENT_DA4;
    public static boolean CLIENT_DA5;
    public static boolean CLIENT_DA6;
    public static boolean CLIENT_DA7;
    public static boolean CLIENT_DA8;
    public static boolean CLIENT_DA9;
    public static boolean CLIENT_DA10;
    public static boolean CLIENT_DA11;
    public static boolean CLIENT_DA12;
    public static boolean CLIENT_DA13;
    public static boolean CLIENT_DA14;
    public static boolean CLIENT_DA15;
    public static boolean CLIENT_DA16;
    public static boolean CLIENT_hud2;
    public static String CLIENT_hud2_2;
    public static boolean CLIENT_DA17;
    public static boolean CLIENT_DA18;
    public static boolean CLIENT_DA19;
    public static boolean CLIENT_DA20;
    public static int CLIENT_DA21;
    public static boolean CLIENT_DA22;
    public static byte CLIENT_Ki_Visibility;
    public static int CLIENT_Ki_Scale;
    public static byte CLIENT_Ki_Charge_Visibility;
    public static int CLIENT_Ki_Charge_Scale;
    public static byte CLIENT_Jutsu_Visibility;
    public static int CLIENT_Jutsu_Scale;
    public static boolean[] CLIENT_Ki_3d;
    public static boolean[] CLIENT_Ki_Charge_3d;
    public static boolean[] CLIENT_Jutsu_3d;
    public static boolean renderEnergyOutsideView;
    public static int addMore;
    public static boolean concentrationAnimatedTexturesOn;
    public static boolean concentrationAnimatedColorOn;
    public static boolean airboxing3DCharacterOn;
    public static boolean airboxing3DAuraOn;
    public static boolean airboxingAnimatedColorOn;
    public static int renderdistanceMultiplierBarrierBlock;
    public static int renderdistanceMultiplierParticles;
    public static int renderdistanceMultiplierKiAttackCharge;
    public static int renderdistanceMultiplierAuras;
    public static boolean kiAttackChargePercentageOn;
    public static boolean dbcFastFusionSpectatorCameraFollowOn;
    public static boolean safeZoneUIModeOn;
    public static boolean instantTransmissionParticles;
    public static boolean instantTransmissionFirstPerson;
    public static final short[] BENS_CONFIG_HUD_SIZES_X;
    public static final short[] BENS_CONFIG_HUD_SIZES_Y;
    public static final short[] BENS_CONFIG_HUD_SIZES_POS;
    public static final short[] BENS_CONFIG_HUD_SIZES_extra_POS_X;
    public static final short[] BENS_CONFIG_HUD_SIZES_extra_POS_Y;
    public static final short[] BENS_CONFIG_HUD_SIZES_extra_height;
    public static final short BENS_CONFIG_HUD_LOCKON = 6;
    public static final short[] BENS_CONFIG_HUD_1_SIZES_POS;
    public static final short[] BENS_CONFIG_HUD_1_SIZES_POS_STAM;
    public static final short[] BENS_CONFIG_HUD_1_SIZES_HEALTH_WIDTH;
    public static final short[] BENS_CONFIG_HUD_1_SIZES_STAMINA_WIDTH;
    public static final short[] BENS_CONFIG_HUD_1_SIZES_X;
    public static final short[] BENS_CONFIG_HUD_1_SIZES_Y;

    public static int get_da1() {
        return 1 + CLIENT_DA1;
    }

    public static float get_da2() {
        return 1.0f + (float)CLIENT_DA2 * 0.01f;
    }

    public static void init(Configuration config) {
        config.load();
        JGConfigClientSettings.init_client(config, true, false);
    }

    public static void initNotifications(Configuration config) {
        config.load();
        JGConfigClientSettings.init_notifications(config, true, false);
    }

    public static short get_hud_x() {
        return BENS_CONFIG_HUD_SIZES_X[CLIENT_hud0];
    }

    public static int get_hud_start_x() {
        return BENS_CONFIG_HUD_SIZES_extra_POS_X[CLIENT_hud0];
    }

    public static int get_hud_start_y() {
        return BENS_CONFIG_HUD_SIZES_extra_POS_Y[CLIENT_hud0];
    }

    public static int get_hud_height(int i) {
        int id = BENS_CONFIG_HUD_SIZES_extra_height[CLIENT_hud0] * i;
        return id;
    }

    public static int get_hud_y() {
        return BENS_CONFIG_HUD_SIZES_Y[CLIENT_hud0];
    }

    public static int get_hud_pos(int i) {
        int id = i * BENS_CONFIG_HUD_SIZES_Y[CLIENT_hud0];
        return BENS_CONFIG_HUD_SIZES_POS[CLIENT_hud0] + id;
    }

    public static int get_hud_lockon() {
        return CLIENT_lockon + 1;
    }

    public static void hud_lockon_add() {
        if (CLIENT_lockon + 1 < 6) {
            ++CLIENT_lockon;
        }
    }

    public static void hud_lockon_take() {
        if (CLIENT_lockon > 0) {
            --CLIENT_lockon;
        }
    }

    public static short get_hud_1_pos(int n) {
        int num = 0;
        if (CLIENT_hud1 > 0) {
            num = BENS_CONFIG_HUD_1_SIZES_POS[CLIENT_hud1];
        }
        return (short)(num += n * JGConfigClientSettings.get_hud_1_height());
    }

    public static short get_hud_1_pos_stam() {
        return BENS_CONFIG_HUD_1_SIZES_POS_STAM[CLIENT_hud1];
    }

    public static short get_hud_1_width_hea() {
        return BENS_CONFIG_HUD_1_SIZES_HEALTH_WIDTH[CLIENT_hud1];
    }

    public static short get_hud_1_width_st() {
        return BENS_CONFIG_HUD_1_SIZES_STAMINA_WIDTH[CLIENT_hud1];
    }

    public static short get_hud_1_width() {
        return BENS_CONFIG_HUD_1_SIZES_X[CLIENT_hud1];
    }

    public static short get_hud_1_height() {
        return BENS_CONFIG_HUD_1_SIZES_Y[CLIENT_hud1];
    }

    public static void settings_addmore() {
        addMore = addMore < 1000 ? (addMore *= 10) : 1;
    }

    public static void init_client(Configuration config, boolean firstRun, boolean reset) {
        int j;
        configsChanged = !firstRun;
        String name = "";
        String CATEGORY = CATEGORY_NAME_CLIENTSIDED;
        String client = "Client Sided!";
        int min = -10000;
        int max = 10000000;
        Property property = config.get(CATEGORY, name + " Player Energy (Ki/Chakra) HUD X Position", 0);
        property.comment = "Client Sided! " + name + " Player Energy (Ki/Chakra) HUD X Position (from left to right)" + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            CLIENT_hud0x = JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(0);
            CLIENT_hud0x = 0;
        } else {
            property.setValue(CLIENT_hud0x);
        }
        min = -10000;
        max = 10000000;
        property = config.get(CATEGORY, name + " Player Energy (Ki/Chakra) HUD Y Position", 0);
        property.comment = "Client Sided! " + name + " Player Energy (Ki/Chakra) HUD Y Position (from top to bottom)" + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            CLIENT_hud0y = JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(0);
            CLIENT_hud0y = 0;
        } else {
            property.setValue(CLIENT_hud0y);
        }
        min = 0;
        max = 2;
        property = config.get(CATEGORY, name + " Player Energy (Ki/Chakra) HUD Style", 0);
        property.comment = "Client Sided! " + name + " Player Energy (Ki/Chakra) HUD Style" + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            CLIENT_hud0 = JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(0);
            CLIENT_hud0 = 0;
        } else {
            property.setValue(CLIENT_hud0);
        }
        min = 0;
        max = 5;
        property = config.get(CATEGORY, name + " Player Lock on Style", 0);
        property.comment = "Client Sided! " + name + " Player Lock on Style" + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            CLIENT_lockon = JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(0);
            CLIENT_lockon = 0;
        } else {
            property.setValue(CLIENT_lockon);
        }
        min = -10000;
        max = 10000000;
        property = config.get(CATEGORY, name + " Player Health and Action HUD X Position", 0);
        property.comment = "Client Sided! " + name + " Player Health and Action HUD X Position (from left to right)" + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            CLIENT_hud1x = JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(0);
            CLIENT_hud1x = 0;
        } else {
            property.setValue(CLIENT_hud1x);
        }
        min = -10000;
        max = 10000000;
        property = config.get(CATEGORY, name + " Player Health and Action HUD Y Position", 0);
        property.comment = "Client Sided! " + name + " Player Health and Action HUD Y Position (from top to bottom)" + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            CLIENT_hud1y = JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(0);
            CLIENT_hud1y = 0;
        } else {
            property.setValue(CLIENT_hud1y);
        }
        min = 0;
        max = 2;
        property = config.get(CATEGORY, name + " Player Health and Action HUD Style", 0);
        property.comment = "Client Sided! " + name + " Player Health and Action HUD Style" + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            CLIENT_hud1 = JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(0);
            CLIENT_hud1 = 0;
        } else {
            property.setValue(CLIENT_hud1);
        }
        property = config.get(CATEGORY, name + " God Aura Particles On", true);
        property.comment = "Client Sided! " + name + " God Aura Particles On";
        if (firstRun) {
            CLIENT_GR0 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_GR0 = true;
        } else {
            property.setValue(CLIENT_GR0);
        }
        property = config.get(CATEGORY, name + " Stone Aura Particles On", true);
        property.comment = "Client Sided! " + name + " Stone Aura Particles On";
        if (firstRun) {
            CLIENT_GR1 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_GR1 = true;
        } else {
            property.setValue(CLIENT_GR1);
        }
        property = config.get(CATEGORY, name + " Swoop image effect On", true);
        property.comment = "Client Sided! " + name + " Swoop image effect On";
        if (firstRun) {
            CLIENT_GR2 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_GR2 = true;
        } else {
            property.setValue(CLIENT_GR2);
        }
        property = config.get(CATEGORY, name + " Fireball Particles On", true);
        property.comment = "Client Sided! " + name + " Fireball Particles On";
        if (firstRun) {
            CLIENT_GR3 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_GR3 = true;
        } else {
            property.setValue(CLIENT_GR3);
        }
        property = config.get(CATEGORY, name + " Explosion Particles On", true);
        property.comment = "Client Sided! " + name + " Explosion Particles On";
        if (firstRun) {
            CLIENT_GR4 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_GR4 = true;
        } else {
            property.setValue(CLIENT_GR4);
        }
        property = config.get(CATEGORY, name + " Percentage Text for Health bars", false);
        property.comment = "Client Sided! " + name + " Percentage Text for Health bars";
        if (firstRun) {
            CLIENT_GR5 = property.getBoolean();
        } else if (reset) {
            property.setValue(false);
            CLIENT_GR5 = false;
        } else {
            property.setValue(CLIENT_GR5);
        }
        property = config.get(CATEGORY, name + " Rotated Health Bar On", false);
        property.comment = "Client Sided! " + name + " Rotated Health Bar On";
        if (firstRun) {
            CLIENT_GR6 = property.getBoolean();
        } else if (reset) {
            property.setValue(false);
            CLIENT_GR6 = false;
        } else {
            property.setValue(CLIENT_GR6);
        }
        property = config.get(CATEGORY, name + " Ultra Instinct Aura Particles On", true);
        property.comment = "Client Sided! " + name + " Ultra Instinct Aura Particles On";
        if (firstRun) {
            CLIENT_GR7 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_GR7 = true;
        } else {
            property.setValue(CLIENT_GR7);
        }
        property = config.get(CATEGORY, name + " Grass Aura Particles On", true);
        property.comment = "Client Sided! " + name + " Grass Aura Particles On";
        if (firstRun) {
            CLIENT_GR8 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_GR8 = true;
        } else {
            property.setValue(CLIENT_GR8);
        }
        property = config.get(CATEGORY, name + " Dust Aura Particles On", true);
        property.comment = "Client Sided! " + name + " Dust Aura Particles On";
        if (firstRun) {
            CLIENT_GR9 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_GR9 = true;
        } else {
            property.setValue(CLIENT_GR9);
        }
        property = config.get(CATEGORY, name + " 3D Stone Aura Particles On", false);
        property.comment = "Client Sided! " + name + " 3D Stone Aura Particles On";
        if (firstRun) {
            CLIENT_GR10 = property.getBoolean();
        } else if (reset) {
            property.setValue(false);
            CLIENT_GR10 = false;
        } else {
            property.setValue(CLIENT_GR10);
        }
        property = config.get(CATEGORY, name + " 3D Grass Aura Particles On", false);
        property.comment = "Client Sided! " + name + " 3D Grass Aura Particles On";
        if (firstRun) {
            CLIENT_GR11 = property.getBoolean();
        } else if (reset) {
            property.setValue(false);
            CLIENT_GR11 = false;
        } else {
            property.setValue(CLIENT_GR11);
        }
        min = -1;
        max = 1000;
        property = config.get(CATEGORY, name + "Particles amount multiplier", 0);
        property.comment = "Client Sided! " + name + " Particles amount multiplier" + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            CLIENT_DA1 = JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(0);
            CLIENT_DA1 = 0;
        } else {
            property.setValue(CLIENT_DA1);
        }
        min = 0;
        max = 10000;
        property = config.get(CATEGORY, name + "Particles speed multiplier", 0);
        property.comment = "Client Sided! " + name + " Particles speed multiplier" + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            CLIENT_DA2 = JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(0);
            CLIENT_DA2 = 0;
        } else {
            property.setValue(CLIENT_DA2);
        }
        min = 0;
        max = 10;
        property = config.get(CATEGORY, name + "Particle invisible from first person", 2);
        property.comment = "Client Sided! " + name + " Particle invisible from first person" + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            CLIENT_DA3 = JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(2);
            CLIENT_DA3 = 2;
        } else {
            property.setValue(CLIENT_DA3);
        }
        property = config.get(CATEGORY, name + "New first person animations On", true);
        property.comment = "Client Sided! " + name + " New first person animations On";
        if (firstRun) {
            CLIENT_DA4 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_DA4 = true;
        } else {
            property.setValue(CLIENT_DA4);
        }
        property = config.get(CATEGORY, name + "New 3D Explosion On", true);
        property.comment = "Client Sided! " + name + " New 3D Explosion On";
        if (firstRun) {
            CLIENT_DA5 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_DA5 = true;
        } else {
            property.setValue(CLIENT_DA5);
        }
        property = config.get(CATEGORY, name + "New 2D Explosion On", true);
        property.comment = "Client Sided! " + name + " New 2D Explosion On";
        if (firstRun) {
            CLIENT_DA6 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_DA6 = true;
        } else {
            property.setValue(CLIENT_DA6);
        }
        property = config.get(CATEGORY, name + "Explosion 3D On", true);
        property.comment = "Client Sided! " + name + " Explosion 3D On";
        if (firstRun) {
            CLIENT_DA7 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_DA7 = true;
        } else {
            property.setValue(CLIENT_DA7);
        }
        property = config.get(CATEGORY, name + "NPC aura On", true);
        property.comment = "Client Sided! " + name + " NPC aura On";
        if (firstRun) {
            CLIENT_DA8 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_DA8 = true;
        } else {
            property.setValue(CLIENT_DA8);
        }
        property = config.get(CATEGORY, name + "Double ki blast color On", true);
        property.comment = "CLIENT_DA9" + name + " Double ki blast color On";
        if (firstRun) {
            CLIENT_DA9 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_DA9 = true;
        } else {
            property.setValue(CLIENT_DA9);
        }
        property = config.get(CATEGORY, name + "Ki blast charge animation On", true);
        property.comment = "Client Sided! " + name + " Ki blast charge animation On";
        if (firstRun) {
            CLIENT_DA10 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_DA10 = true;
        } else {
            property.setValue(CLIENT_DA10);
        }
        property = config.get(CATEGORY, name + "Flying trail On", false);
        property.comment = "Client Sided! " + name + " Flying trail On";
        if (firstRun) {
            CLIENT_DA11 = property.getBoolean();
        } else if (reset) {
            property.setValue(false);
            CLIENT_DA11 = false;
        } else {
            property.setValue(CLIENT_DA11);
        }
        property = config.get(CATEGORY, name + "Aura lightning On", true);
        property.comment = "Client Sided! " + name + " Aura lightning On";
        if (firstRun) {
            CLIENT_DA12 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_DA12 = true;
        } else {
            property.setValue(CLIENT_DA12);
        }
        property = config.get(CATEGORY, name + "2D player aura animation On", true);
        property.comment = "Client Sided! " + name + " 2D player aura animation On";
        if (firstRun) {
            CLIENT_DA13 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_DA13 = true;
        } else {
            property.setValue(CLIENT_DA13);
        }
        property = config.get(CATEGORY, name + "3D player aura animation On", true);
        property.comment = "Client Sided! " + name + " 3D player aura animation On";
        if (firstRun) {
            CLIENT_DA14 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_DA14 = true;
        } else {
            property.setValue(CLIENT_DA14);
        }
        property = config.get(CATEGORY, name + "Hitting clash effect On", true);
        property.comment = "Client Sided! " + name + " Hitting clash effect On";
        if (firstRun) {
            CLIENT_DA15 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_DA15 = true;
        } else {
            property.setValue(CLIENT_DA15);
        }
        property = config.get(CATEGORY, name + "Spawn Other particles On", true);
        property.comment = "Client Sided! " + name + " Spawn Other particles On";
        if (firstRun) {
            CLIENT_DA16 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_DA16 = true;
        } else {
            property.setValue(CLIENT_DA16);
        }
        property = config.get(CATEGORY, name + "Custom HUD On", false);
        property.comment = "Client Sided! " + name + " Custom HUD On";
        if (firstRun) {
            CLIENT_hud2 = property.getBoolean();
        } else if (reset) {
            property.setValue(false);
            CLIENT_hud2 = false;
        } else {
            property.setValue(CLIENT_hud2);
        }
        property = config.get(CATEGORY, name + "Custom HUD Properties", CLIENT_hud2_3);
        property.comment = "Client Sided! " + name + " Custom HUD Properties" + JGConfigClientSettings.getDefault(CLIENT_hud2_3);
        if (firstRun) {
            CLIENT_hud2_2 = property.getString();
        } else if (reset) {
            property.setValue(CLIENT_hud2_3);
            CLIENT_hud2_2 = CLIENT_hud2_3;
        } else {
            property.setValue(CLIENT_hud2_2);
        }
        property = config.get(CATEGORY, name + "April fools mode On", false);
        property.comment = "Client Sided! " + name + " April fools mode On";
        if (firstRun) {
            CLIENT_DA17 = property.getBoolean();
        } else if (reset) {
            property.setValue(false);
            CLIENT_DA17 = false;
        } else {
            property.setValue(CLIENT_DA17);
        }
        property = config.get(CATEGORY, name + "First Person Blocking animation On", false);
        property.comment = "Client Sided! " + name + " First Person Blocking animation On";
        if (firstRun) {
            CLIENT_DA18 = property.getBoolean();
        } else if (reset) {
            property.setValue(false);
            CLIENT_DA18 = false;
        } else {
            property.setValue(CLIENT_DA18);
        }
        property = config.get(CATEGORY, name + "Player Bruises On", true);
        property.comment = "Client Sided! " + name + " Player Bruises On";
        if (firstRun) {
            CLIENT_DA19 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_DA19 = true;
        } else {
            property.setValue(CLIENT_DA19);
        }
        property = config.get(CATEGORY, name + "3D Aura, 2D God On", true);
        property.comment = "Client Sided! " + name + " 3D Aura, 2D God On";
        if (firstRun) {
            CLIENT_DA20 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_DA20 = true;
        } else {
            property.setValue(CLIENT_DA20);
        }
        min = 0;
        max = 10;
        property = config.get(CATEGORY, name + " 3D Aura Invisibility", 10);
        property.comment = "Client Sided! " + name + " 3D Aura Invisibility" + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            CLIENT_DA21 = JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(10);
            CLIENT_DA21 = 10;
        } else {
            property.setValue(CLIENT_DA21);
        }
        property = config.get(CATEGORY, name + "Action Menu Text Type On", false);
        property.comment = "Client Sided! " + name + " Action Menu Text Type On";
        if (firstRun) {
            CLIENT_GR12 = property.getBoolean();
        } else if (reset) {
            property.setValue(false);
            CLIENT_GR12 = false;
        } else {
            property.setValue(CLIENT_GR12);
        }
        property = config.get(CATEGORY, name + "Action Menu Text White On", false);
        property.comment = "Client Sided! " + name + " Action Menu Text White On";
        if (firstRun) {
            CLIENT_GR13 = property.getBoolean();
        } else if (reset) {
            property.setValue(false);
            CLIENT_GR13 = false;
        } else {
            property.setValue(CLIENT_GR13);
        }
        property = config.get(CATEGORY, name + "Enma Desk On", true);
        property.comment = "Client Sided! " + name + " Enma Desk On";
        if (firstRun) {
            CLIENT_DA22 = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            CLIENT_DA22 = true;
        } else {
            property.setValue(CLIENT_DA22);
        }
        min = 0;
        max = 10;
        property = config.get(CATEGORY, name + " Ki Attack Visibility", 10);
        property.comment = "Client Sided! " + name + " Ki Attack Visibility" + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            CLIENT_Ki_Visibility = (byte)JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(10);
            CLIENT_Ki_Visibility = (byte)10;
        } else {
            property.setValue((int)CLIENT_Ki_Visibility);
        }
        min = 0;
        max = 100000;
        property = config.get(CATEGORY, name + " Ki Attack Scale", 10);
        property.comment = "Client Sided! " + name + " Ki Attack Scale" + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            CLIENT_Ki_Scale = JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(10);
            CLIENT_Ki_Scale = 10;
        } else {
            property.setValue(CLIENT_Ki_Scale);
        }
        min = 0;
        max = 10;
        property = config.get(CATEGORY, name + " Ki Charge Visibility", 10);
        property.comment = "Client Sided! " + name + " Ki Charge Visibility" + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            CLIENT_Ki_Charge_Visibility = (byte)JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(10);
            CLIENT_Ki_Charge_Visibility = (byte)10;
        } else {
            property.setValue((int)CLIENT_Ki_Charge_Visibility);
        }
        min = 0;
        max = 100000;
        property = config.get(CATEGORY, name + " Ki Charge Scale", 10);
        property.comment = "Client Sided! " + name + " Ki Charge Scale" + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            CLIENT_Ki_Charge_Scale = JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(10);
            CLIENT_Ki_Charge_Scale = 10;
        } else {
            property.setValue(CLIENT_Ki_Charge_Scale);
        }
        min = 0;
        max = 10;
        property = config.get(CATEGORY, name + " Jutsu Visibility", 10);
        property.comment = "Client Sided! " + name + " Jutsu Visibility" + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            CLIENT_Jutsu_Visibility = (byte)JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(10);
            CLIENT_Jutsu_Visibility = (byte)10;
        } else {
            property.setValue((int)CLIENT_Jutsu_Visibility);
        }
        min = 0;
        max = 100000;
        property = config.get(CATEGORY, name + " Jutsu Scale", 10);
        property.comment = "Client Sided! " + name + " Jutsu Scale" + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            CLIENT_Jutsu_Scale = JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(10);
            CLIENT_Jutsu_Scale = 10;
        } else {
            property.setValue(CLIENT_Jutsu_Scale);
        }
        String[] names2 = new String[]{"Wave", "Blast", "Disk", "Laser", "Spiral", "BigBlast", "Barrage", "Shield", "Explosion"};
        for (j = 0; j < names2.length; ++j) {
            property = config.get(CATEGORY, name + " 3D Ki Attack Effect [" + names2[j] + "] On", false);
            property.comment = "Client Sided! " + name + " 3D Ki Attack Effect [" + names2[j] + "] On";
            if (firstRun) {
                JGConfigClientSettings.CLIENT_Ki_3d[j] = property.getBoolean();
                continue;
            }
            if (reset) {
                property.setValue(false);
                JGConfigClientSettings.CLIENT_Ki_3d[j] = false;
                continue;
            }
            property.setValue(CLIENT_Ki_3d[j]);
        }
        for (j = 0; j < names2.length; ++j) {
            property = config.get(CATEGORY, name + " 3D Ki Attack Charge Effect [" + names2[j] + "] On", false);
            property.comment = "Client Sided! " + name + " 3D Ki Attack Charge Effect [" + names2[j] + "] On";
            if (firstRun) {
                JGConfigClientSettings.CLIENT_Ki_Charge_3d[j] = property.getBoolean();
                continue;
            }
            if (reset) {
                property.setValue(false);
                JGConfigClientSettings.CLIENT_Ki_Charge_3d[j] = false;
                continue;
            }
            property.setValue(CLIENT_Ki_Charge_3d[j]);
        }
        for (j = 0; j < 3; ++j) {
            property = config.get(CATEGORY, name + " 3D Jutsu Attack Effect [" + names2[j] + "] On", false);
            property.comment = "Client Sided! " + name + " 3D Jutsu Attack Effect [" + names2[j] + "] On";
            if (firstRun) {
                JGConfigClientSettings.CLIENT_Jutsu_3d[j] = property.getBoolean();
                continue;
            }
            if (reset) {
                property.setValue(false);
                JGConfigClientSettings.CLIENT_Jutsu_3d[j] = false;
                continue;
            }
            property.setValue(CLIENT_Jutsu_3d[j]);
        }
        property = config.get(CATEGORY, name + "Render Energy Outside View", true);
        property.comment = "Client Sided! " + name + " Render Energy Outside View";
        if (firstRun) {
            renderEnergyOutsideView = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            renderEnergyOutsideView = true;
        } else {
            property.setValue(renderEnergyOutsideView);
        }
        name = "Minigame Concentration Animated Textures On";
        property = config.get(CATEGORY, name, true);
        property.comment = "Client Sided! " + name;
        if (firstRun) {
            concentrationAnimatedTexturesOn = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            concentrationAnimatedTexturesOn = true;
        } else {
            property.setValue(concentrationAnimatedTexturesOn);
        }
        name = "Minigame Concentration Animated Color On";
        property = config.get(CATEGORY, name, true);
        property.comment = "Client Sided! " + name;
        if (firstRun) {
            concentrationAnimatedColorOn = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            concentrationAnimatedColorOn = true;
        } else {
            property.setValue(concentrationAnimatedColorOn);
        }
        name = "Minigame Airboxing 3D Character On";
        property = config.get(CATEGORY, name, true);
        property.comment = "Client Sided! " + name;
        if (firstRun) {
            airboxing3DCharacterOn = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            airboxing3DCharacterOn = true;
        } else {
            property.setValue(airboxing3DCharacterOn);
        }
        name = "Minigame Airboxing 3D Aura On";
        property = config.get(CATEGORY, name, true);
        property.comment = "Client Sided! " + name;
        if (firstRun) {
            airboxing3DAuraOn = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            airboxing3DAuraOn = true;
        } else {
            property.setValue(airboxing3DAuraOn);
        }
        name = "Minigame Airboxing Animated Color On";
        property = config.get(CATEGORY, name, true);
        property.comment = "Client Sided! " + name;
        if (firstRun) {
            airboxingAnimatedColorOn = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            airboxingAnimatedColorOn = true;
        } else {
            property.setValue(airboxingAnimatedColorOn);
        }
        min = 0;
        max = 100;
        name = "Render Distance Multiplier - Barrier Block";
        property = config.get(CATEGORY, name, 100);
        property.comment = "Client Sided! " + name + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            renderdistanceMultiplierBarrierBlock = JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(100);
            renderdistanceMultiplierBarrierBlock = 100;
        } else {
            property.setValue(renderdistanceMultiplierBarrierBlock);
        }
        min = 0;
        max = 10000;
        name = "Render Distance Multiplier - Particles";
        property = config.get(CATEGORY, name, 100);
        property.comment = "Client Sided! " + name + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            renderdistanceMultiplierParticles = JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(100);
            renderdistanceMultiplierParticles = 100;
        } else {
            property.setValue(renderdistanceMultiplierParticles);
        }
        min = 0;
        max = 10000;
        name = "Render Distance Multiplier - Auras";
        property = config.get(CATEGORY, name, 100);
        property.comment = "Client Sided! " + name + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            renderdistanceMultiplierAuras = JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(100);
            renderdistanceMultiplierAuras = 100;
        } else {
            property.setValue(renderdistanceMultiplierAuras);
        }
        min = 0;
        max = 10000;
        name = "Render Distance Multiplier - Ki Attack Charge";
        property = config.get(CATEGORY, name, 100);
        property.comment = "Client Sided! " + name + JGConfigClientSettings.getDefault("" + min, "" + max);
        if (firstRun) {
            renderdistanceMultiplierKiAttackCharge = JGConfigClientSettings.checkValue(property.getInt(), min, max);
        } else if (reset) {
            property.setValue(100);
            renderdistanceMultiplierKiAttackCharge = 100;
        } else {
            property.setValue(renderdistanceMultiplierKiAttackCharge);
        }
        name = "Ki Attack Charge Percentage On";
        property = config.get(CATEGORY, name, true);
        property.comment = "Client Sided! " + name;
        if (firstRun) {
            kiAttackChargePercentageOn = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            kiAttackChargePercentageOn = true;
        } else {
            property.setValue(kiAttackChargePercentageOn);
        }
        name = "Tip Notifications On";
        property = config.get(CATEGORY, name, true);
        property.comment = "Client Sided! " + name;
        if (firstRun) {
            tipNotificationsOn = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            tipNotificationsOn = true;
        } else {
            property.setValue(tipNotificationsOn);
        }
        name = "Error Notifications On";
        property = config.get(CATEGORY, name, true);
        property.comment = "Client Sided! " + name;
        if (firstRun) {
            errorNotificationsOn = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            errorNotificationsOn = true;
        } else {
            property.setValue(errorNotificationsOn);
        }
        name = "DBCFast Fusion Spectator Camera Follow";
        property = config.get(CATEGORY, name, true);
        property.comment = "Client Sided! " + name;
        if (firstRun) {
            dbcFastFusionSpectatorCameraFollowOn = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            dbcFastFusionSpectatorCameraFollowOn = true;
        } else {
            property.setValue(dbcFastFusionSpectatorCameraFollowOn);
        }
        name = "Safe Zone UI Mode On";
        property = config.get(CATEGORY, name, true);
        property.comment = "Client Sided! " + name;
        if (firstRun) {
            safeZoneUIModeOn = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            safeZoneUIModeOn = true;
        } else {
            property.setValue(safeZoneUIModeOn);
        }
        name = "Instant Transmission First Person On";
        property = config.get(CATEGORY, name, true);
        property.comment = "Client Sided! " + name;
        if (firstRun) {
            instantTransmissionFirstPerson = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            instantTransmissionFirstPerson = true;
        } else {
            property.setValue(instantTransmissionFirstPerson);
        }
        name = "Instant Transmission Particles On";
        property = config.get(CATEGORY, name, true);
        property.comment = "Client Sided! " + name;
        if (firstRun) {
            instantTransmissionParticles = property.getBoolean();
        } else if (reset) {
            property.setValue(true);
            instantTransmissionParticles = true;
        } else {
            property.setValue(instantTransmissionParticles);
        }
        config.save();
    }

    public static void init_notifications(Configuration config, boolean firstRun, boolean reset) {
        String name = "";
        String CATEGORY = CATEGORY_NAME_NOTIFICATIONS;
        String client = "Client Sided!";
        for (int i = 0; i < JGNotificationGUI.categoryState.length; ++i) {
            int min = 0;
            int max = 2;
            name = "Category State " + JGNotificationHandler.CATEGORY_TEXTS_ALL[i];
            Property property = config.get(CATEGORY, name, 0);
            property.comment = "Client Sided! " + name + JGConfigClientSettings.getDefault("" + min, "" + max);
            if (firstRun) {
                JGNotificationGUI.categoryState[i] = JGConfigClientSettings.checkValue(property.getInt(), min, max);
                continue;
            }
            if (reset) {
                property.setValue(0);
                JGNotificationGUI.categoryState[i] = 0;
                continue;
            }
            property.setValue(JGNotificationGUI.categoryState[i]);
        }
        config.save();
    }

    static {
        CLIENT_GR0 = true;
        CLIENT_GR1 = true;
        CLIENT_GR2 = true;
        CLIENT_GR3 = true;
        CLIENT_GR4 = true;
        CLIENT_GR5 = false;
        CLIENT_GR6 = false;
        CLIENT_GR7 = false;
        CLIENT_GR8 = false;
        CLIENT_GR9 = false;
        CLIENT_GR10 = false;
        CLIENT_GR11 = false;
        CLIENT_GR12 = false;
        CLIENT_GR13 = false;
        CLIENT_DA3 = 2;
        CLIENT_DA4 = true;
        CLIENT_DA5 = true;
        CLIENT_DA6 = true;
        CLIENT_DA7 = true;
        CLIENT_DA8 = true;
        CLIENT_DA9 = true;
        CLIENT_DA10 = true;
        CLIENT_DA11 = false;
        CLIENT_DA12 = true;
        CLIENT_DA13 = true;
        CLIENT_DA14 = true;
        CLIENT_DA15 = true;
        CLIENT_DA16 = true;
        CLIENT_hud2 = false;
        CLIENT_hud2_2 = CLIENT_hud2_3;
        CLIENT_DA17 = false;
        CLIENT_DA18 = false;
        CLIENT_DA19 = true;
        CLIENT_DA20 = true;
        CLIENT_DA21 = 10;
        CLIENT_DA22 = true;
        CLIENT_Ki_Visibility = (byte)10;
        CLIENT_Ki_Scale = 10;
        CLIENT_Ki_Charge_Visibility = (byte)10;
        CLIENT_Ki_Charge_Scale = 10;
        CLIENT_Jutsu_Visibility = (byte)10;
        CLIENT_Jutsu_Scale = 10;
        CLIENT_Ki_3d = new boolean[9];
        CLIENT_Ki_Charge_3d = new boolean[9];
        CLIENT_Jutsu_3d = new boolean[3];
        renderEnergyOutsideView = true;
        addMore = 1;
        concentrationAnimatedTexturesOn = true;
        concentrationAnimatedColorOn = true;
        airboxing3DCharacterOn = true;
        airboxing3DAuraOn = true;
        airboxingAnimatedColorOn = true;
        renderdistanceMultiplierBarrierBlock = 1;
        renderdistanceMultiplierParticles = 1;
        renderdistanceMultiplierKiAttackCharge = 1;
        renderdistanceMultiplierAuras = 1;
        kiAttackChargePercentageOn = true;
        dbcFastFusionSpectatorCameraFollowOn = true;
        safeZoneUIModeOn = true;
        instantTransmissionParticles = true;
        instantTransmissionFirstPerson = true;
        BENS_CONFIG_HUD_SIZES_X = new short[]{182, 242, 143};
        BENS_CONFIG_HUD_SIZES_Y = new short[]{10, 25, 16};
        BENS_CONFIG_HUD_SIZES_POS = new short[]{0, 30, 105};
        BENS_CONFIG_HUD_SIZES_extra_POS_X = new short[]{0, 0, 51};
        BENS_CONFIG_HUD_SIZES_extra_POS_Y = new short[]{0, 0, 36};
        BENS_CONFIG_HUD_SIZES_extra_height = new short[]{0, 0, 50};
        BENS_CONFIG_HUD_1_SIZES_POS = new short[]{0, 33, 78, 117};
        BENS_CONFIG_HUD_1_SIZES_POS_STAM = new short[]{17, 56, 98, 137};
        BENS_CONFIG_HUD_1_SIZES_HEALTH_WIDTH = new short[]{6, 7, 6, 6};
        BENS_CONFIG_HUD_1_SIZES_STAMINA_WIDTH = new short[]{4, 6, 5, 5};
        BENS_CONFIG_HUD_1_SIZES_X = new short[]{41, 133, 105, 105};
        BENS_CONFIG_HUD_1_SIZES_Y = new short[]{11, 15, 13, 13};
    }
}

