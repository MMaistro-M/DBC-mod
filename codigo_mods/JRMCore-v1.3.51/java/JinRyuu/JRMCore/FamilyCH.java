/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.FamilyCDataThread;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.p.FamilyCP;
import JinRyuu.JRMCore.p.PD;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class FamilyCH {
    public static int lastUpdateTick = 0;
    public static NBTTagCompound familyCNBT = null;
    public static FamilyCDataThread familyCDataThread = null;
    static String fif = "JFamilyC_f.jfc";
    static String fmd = "JFamilyC_d.jfc";
    static String cfd = "JFamilyC_c.jfc";
    static String pfd = "JFamilyC_p.jfc";
    static String cpd = "JFamilyC_cp.jfc";
    public static String FID = "JRMCFID";
    public static String FIDo = "JRMCFIDo";
    public static String FIDi = "JRMCFIDi";
    public static String FIDa = "JRMCFIDa";
    public static String prID = "JRMCproc";
    public static String[] famNams;
    public static String[] famMem;
    public static String prop;
    public static String adop;
    public static int[] pregTime;
    public static int FamID;
    public static String FamP;
    public static String[] jnam0;
    public static String[] jnam1;
    public static String[] jnam2;
    public static String[] nam1;
    public static String[] nam2;

    public static void wfmd(MinecraftServer server, String d, String rid, boolean b) {
        String fd = "/data";
        JRMCoreH.wd(server, d, rid + "", "/data", fmd, b);
    }

    public static String rfmd(MinecraftServer server, String id) {
        String fd = "/data";
        return JRMCoreH.rd(server, id + "", "/data", fmd);
    }

    public static void wfi(MinecraftServer server, String d, String rid, boolean b) {
        String fd = "/data";
        JRMCoreH.wd(server, d, rid + "", "/data", fif, b);
    }

    public static String rfi(MinecraftServer server, String id) {
        String fd = "/data";
        return JRMCoreH.rd(server, id + "", "/data", fif);
    }

    public static void wcfdD(MinecraftServer server) {
        String fd = "/data";
        JRMCoreH.wd(server, "/data", cfd);
    }

    public static void wcfd(MinecraftServer server, String d, int id, boolean b) {
        String fd = "/data";
        JRMCoreH.wd(server, d, id + "", "/data", cfd, b);
    }

    public static String rcfd(MinecraftServer server, String id) {
        String fd = "/data";
        return JRMCoreH.rd(server, id, "/data", cfd);
    }

    public static void wpfdD(MinecraftServer server) {
        String fd = "/data";
        JRMCoreH.wd(server, "/data", pfd);
    }

    public static void wpfd(MinecraftServer server, String d, String id, boolean b) {
        String fd = "/data";
        JRMCoreH.wd(server, d, id, "/data", pfd, b);
    }

    public static String rpfd(MinecraftServer server, String id) {
        String fd = "/data";
        return JRMCoreH.rd(server, id, "/data", pfd);
    }

    public static void wcpd(MinecraftServer server, String d, String id, boolean b) {
        String fd = "/data";
        JRMCoreH.wd(server, d, id, "/data", cpd, b);
    }

    public static String rcpd(MinecraftServer server, String id) {
        String fd = "/data";
        return JRMCoreH.rd(server, id, "/data", cpd);
    }

    public static NBTTagCompound readFamilyInfoFromNBT(MinecraftServer server) {
        String fd = "/data";
        return JRMCoreH.readNBTFile(server, "/data", fif);
    }

    public static int d(String s, int i) {
        return i == 3 ? FamilyCH.ltn5(FamilyCH.sa(s, 6), FamilyCH.sa(s, 7), FamilyCH.sa(s, 8), FamilyCH.sa(s, 9), FamilyCH.sa(s, 10)) : FamilyCH.ltn(FamilyCH.sa(s, i == 0 ? 0 : (i == 1 ? 2 : (i == 2 ? 4 : 11))), FamilyCH.sa(s, i == 0 ? 1 : (i == 1 ? 3 : (i == 2 ? 5 : 12))));
    }

    public static String sa(String s1, int s2) {
        return s1.charAt(s2) + "";
    }

    public static int ltn(String s1, String s2) {
        return JRMCoreH.letToNum(s1, s2);
    }

    public static int ltn5(String s1, String s2, String s3, String s4, String s5) {
        return JRMCoreH.letToNum5(s1, s2, s3, s4, s5);
    }

    public static String namGen() {
        Random r1 = new Random();
        int i0 = r1.nextInt(2);
        String s = "";
        if (i0 == 1) {
            r1 = new Random();
            int i1 = r1.nextInt(nam1.length - 1);
            r1 = new Random();
            int i2 = r1.nextInt(nam2.length - 1);
            s = nam1[i1] + nam2[i2];
        } else {
            r1 = new Random();
            int i1 = r1.nextInt(10);
            String s1 = i1 < jnam0.length ? jnam0[i1] : "";
            r1 = new Random();
            i1 = r1.nextInt(3);
            String s2 = "";
            for (int i = 0; i < i1 + 1; ++i) {
                r1 = new Random();
                int i2 = r1.nextInt(jnam1.length);
                s2 = s2 + jnam1[i2];
            }
            r1 = new Random();
            i1 = r1.nextInt(4);
            String s3 = i1 < jnam2.length ? jnam0[i1] : "";
            String n = s1 + s2 + s3;
            s = n.substring(0, 1).toUpperCase() + n.substring(1);
        }
        return s != null ? s : "Null";
    }

    public static void jfcd(int id, String txt) {
        PD.sendToServer(new FamilyCP(id, txt));
    }

    public static void jfcd(int id, String txt, EntityPlayer p) {
        PD.sendTo(new FamilyCP(id, txt), (EntityPlayerMP)p);
    }

    public static boolean procWith(int ar, int tr) {
        return ar != tr ? ar >= 0 && ar <= 2 && tr >= 0 && tr <= 2 : true;
    }

    public static int procTR(int ar, int tr) {
        return ar != tr ? (ar >= 0 && ar <= 2 && tr >= 0 && tr <= 2 ? 2 : 0) : ar;
    }

    static {
        FamID = 0;
        jnam0 = new String[]{"a", "i", "u", "e", "o"};
        jnam1 = new String[]{"ka", "ki", "ki", "ku", "ku", "ke", "ke", "ko", "ko", "sa", "sa", "sa", "shi", "shi", "shi", "su", "su", "se", "so", "ta", "ta", "chi", "chi", "tsu", "te", "to", "na", "ni", "ni", "nu", "nu", "ne", "no", "no", "ha", "hi", "fu", "fu", "he", "ho", "ma", "ma", "ma", "mi", "mi", "mi", "mu", "mu", "mu", "mu", "me", "mo", "mo", "mo", "ya", "yu", "yu", "yu", "yo", "ra", "ra", "ra", "ri", "ru", "ru", "ru", "re", "ro", "ro", "ro", "wa", "wa", "wa", "wa", "wo", "wo"};
        jnam2 = new String[]{"n"};
        nam1 = new String[]{"Kami", "Kame", "Ko", "Leto", "Le", "La", "Lao", "Mu", "Mute", "Na", "Nap", "Ni", "Omeni", "Ome", "Oo", "Pic", "Pik", "Pi", "Pui", "Poi", "Po", "Pu", "Pa", "Pud", "Pu", "Para", "Puru", "Pora", "Poru", "Rai", "Rei", "Re", "Reno", "Ra", "Roshi", "Ro", "Sei", "Sa", "She", "Tru", "Turu", "Tu", "Tia", "Ti", "Tur", "Ve", "Vel", "Vege", "Veji", "Vi", "Vide", "Ya", "Yam", "Yamu", "Yako", "Zarbo", "Za", "Ze", "Bi", "Ba", "Be", "Buu", "Baba", "Bibi", "Beji", "Bu", "Bul", "Buru", "Ba", "Bardo", "Bro", "Broli", "Chi", "Chao", "Choa", "Ce", "Dodo", "Do", "Duru", "Dabu", "Da", "Du", "Furi", "Frei", "Free", "Frie", "Fu", "Fuze", "Gero", "Ge", "Giyu", "Gin", "Gi", "Gig", "Gaji", "Ga", "Gul", "Gu", "Ger", "Go", "Jei", "Jie", "Ji", "Je", "Jin", "Kai", "Ki", "Ka", "Kril", "Kri", "Kuru", "Kuri", "Ku", "Kururi", "Ka"};
        nam2 = new String[]{"nku", "nkuu", "tai", "kai", "ken", "aio", "san", "drick", "nix", "golo", "gollo", "gelo", "galo", "gello", "ucha", "'ken", "'kan", "sen", "zar", "bon", "don", "kou", "han", "sin", "-Chi", "nate", "rne", "jitsu", "jiit", "jit", "u", "ut", "be", "long", "razu", "tle", "rin", "rrin", "tenks", "tunks", "zedd", "edd", "er", "bidi", "bi", "di", "dd", "d", "badi", "ba", "ta", "ter", "rter", "ma", "po", "bo", "ck", "tsu", "tzu", "ll", "ria", "ra", "ri", "za", "zer", "yu", "do", "o", "ku", "kuu", "ce", "ke", "lin", "rin", "me", "sennin", "shinhan", "ten", "en", "mat", "t", "tien", "pa", "colo", "kon", "kolo", "con", "-pui", "-poi", "ditz", "dditz", "dittz", "shi", "shu", "sho", "sha", "she", "les", "nks", "geta", "jita", "gita", "jeta", "tto", "to", "cha", "chi", "n"};
    }
}

