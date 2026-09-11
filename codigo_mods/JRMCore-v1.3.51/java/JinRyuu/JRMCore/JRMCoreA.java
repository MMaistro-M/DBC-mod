/*
 * Decompiled with CFR 0.152.
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHDBC;
import JinRyuu.JRMCore.JRMCoreHNC;
import java.util.HashMap;
import java.util.Map;

public class JRMCoreA {
    public static Map<Integer, Object> actions = new HashMap<Integer, Object>();
    public static Map<Integer, Object> actionsDBC = new HashMap<Integer, Object>();
    public static Map<Integer, Object> actionsNC = new HashMap<Integer, Object>();

    public static void actionInit() {
        if (JRMCoreH.DBC()) {
            JRMCoreHDBC.actionInitDBC();
        }
        if (JRMCoreH.NC()) {
            JRMCoreHNC.actionInit();
        }
    }

    public static boolean pwr_usrs(int p) {
        return p == 1 || p == 2;
    }
}

