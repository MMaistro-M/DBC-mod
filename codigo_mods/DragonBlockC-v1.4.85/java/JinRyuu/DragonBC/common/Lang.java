/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.StatCollector
 */
package JinRyuu.DragonBC.common;

import net.minecraft.util.StatCollector;

public class Lang {
    public String BigBangName = StatCollector.func_74838_a((String)"dbc.KABigBang.name");
    public String BlastName = StatCollector.func_74838_a((String)"dbc.KABlast.name");
    public String BurningAttName = StatCollector.func_74838_a((String)"dbc.KABurningAtt.name");
    public String DeathBeamName = StatCollector.func_74838_a((String)"dbc.KADeathBeam.name");
    public String DodonName = StatCollector.func_74838_a((String)"dbc.KADodon.name");
    public String EnergyDiskName = StatCollector.func_74838_a((String)"dbc.KAEnergyDisk.name");
    public String FinalFlashName = StatCollector.func_74838_a((String)"dbc.KAFinalFlash.name");
    public String FingerLeserName = StatCollector.func_74838_a((String)"dbc.KAFingerLeser.name");
    public String GalicGunName = StatCollector.func_74838_a((String)"dbc.KAGalicGun.name");
    public String HameName = StatCollector.func_74838_a((String)"dbc.KAHame.name");
    public String Hame10xName = StatCollector.func_74838_a((String)"dbc.KAHame10x.name");
    public String MakankoName = StatCollector.func_74838_a((String)"dbc.KAMakanko.name");
    public String MasenkoName = StatCollector.func_74838_a((String)"dbc.KAMasenko.name");
    public String PlanetDestName = StatCollector.func_74838_a((String)"dbc.KAPlanetDest.name");
    public String SpiritbombName = StatCollector.func_74838_a((String)"dbc.KASpiritbomb.name");

    public static void init() {
    }

    public String KAName(int par1) {
        String ret = "";
        int selct = par1;
        if (selct == 1) {
            ret = this.BigBangName;
        }
        if (selct == 2) {
            ret = this.BlastName;
        }
        if (selct == 3) {
            ret = this.BurningAttName;
        }
        if (selct == 4) {
            ret = this.DeathBeamName;
        }
        if (selct == 5) {
            ret = this.DodonName;
        }
        if (selct == 6) {
            ret = this.EnergyDiskName;
        }
        if (selct == 7) {
            ret = this.FinalFlashName;
        }
        if (selct == 8) {
            ret = this.FingerLeserName;
        }
        if (selct == 9) {
            ret = this.GalicGunName;
        }
        if (selct == 10) {
            ret = this.HameName;
        }
        if (selct == 11) {
            ret = this.Hame10xName;
        }
        if (selct == 12) {
            ret = this.MakankoName;
        }
        if (selct == 13) {
            ret = this.MasenkoName;
        }
        if (selct == 14) {
            ret = this.PlanetDestName;
        }
        if (selct == 15) {
            ret = this.SpiritbombName;
        }
        return ret;
    }
}

