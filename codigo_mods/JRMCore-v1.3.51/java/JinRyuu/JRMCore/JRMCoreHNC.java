package JinRyuu.JRMCore;

import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.entity.EntitySafeZone;
import JinRyuu.NarutoC.common.NCH;
import JinRyuu.NarutoC.common.mod_NarutoC;
import JinRyuu.NarutoC.common.Npcs.EntityNC;
import JinRyuu.NarutoC.common.Npcs.EntityNCKami;
import JinRyuu.NarutoC.common.Npcs.f.EntityKonohaFugaku;
import JinRyuu.NarutoC.common.Npcs.f.EntityKonohaHiashi;
import JinRyuu.NarutoC.common.Npcs.f.EntityKonohaSarutobi;
import JinRyuu.NarutoC.common.Villages.Konoha;
import JinRyuu.NarutoC.common.Villages.builds;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class JRMCoreHNC {
   public static final int Action_EightGates = 1;
   public static builds[] NCbuilds = new builds[]{new Konoha()};
   public static int[] NCbuildsdim = new int[]{0};
   public static boolean checked = false;
   public static boolean renderHyuuga;

   public static int getSpecJutsu(int k) {
      for (byte i = 0; i < 4; i++) {
         if (JRMCoreH.techsPM(i) >= 0) {
            String[] j = JRMCoreH.pmj[JRMCoreH.techsPM(i)];
            int den = Integer.parseInt(j[11]);
            switch (den) {
               case 6:
                  if (k == 3) {
                     return 4 + i + 1;
                  }
                  break;
               case 7:
                  if (k == 4) {
                     return 4 + i + 1;
                  }
            }
         }
      }

      return -1;
   }

   public static int iconInList() {
      ArrayList<Integer> a = iconList();
      return a.get(JRMCoreH.EnAtSlct < a.size() ? JRMCoreH.EnAtSlct : a.size() - 1);
   }

   public static ArrayList<Integer> iconList() {
      ArrayList<Integer> al = Lists.newArrayList();
      if (JRMCoreH.PlyrSettingsB(7)) {
         al.add(5);
         return al;
      }

      switch (JRMCoreH.Class) {
         case 0:
            al.add(0);
            break;
         case 1:
            al.add(1);
            break;
         case 2:
            al.add(2);
      }

      for (byte i = 0; i < 4; i++) {
         if (JRMCoreH.techsPM(i) >= 0 && JRMCoreH.pmj.length > JRMCoreH.techsPM(i)) {
            String[] j = JRMCoreH.pmj[JRMCoreH.techsPM(i)];
            int den = Integer.parseInt(j[11]);
            switch (den) {
               case 6:
                  al.add(3);
                  break;
               case 7:
                  al.add(4);
            }
         }
      }

      return al;
   }

   public static void actionInit() {
      JRMCoreA.actionsNC.put(0, 1);
   }

   public static String action(int d, boolean action, boolean black) {
      String opt1 = JGConfigClientSettings.CLIENT_GR12 ? "Enable" : "Off";
      String opt2 = JGConfigClientSettings.CLIENT_GR12 ? "Disable" : "On";
      String clr1 = "§4";
      String clr2 = "§2";
      if (JGConfigClientSettings.CLIENT_GR13) {
         black = true;
      }

      int race = JRMCoreH.Race;
      int pwr = JRMCoreH.Pwrtyp;
      int state = JRMCoreH.State;
      int align = JRMCoreH.align;
      boolean lf = JRMCoreH.StusEfctsMe(14);
      switch (d) {
         case 1:
            String nm = JRMCoreH.trl("nc", JRMCoreH.SklName(JRMCoreH.NCSkillIDs[12], JRMCoreH.NCSkillIDs, JRMCoreH.NCSkillNames));
            int skl = JRMCoreH.SklLvl(12, JRMCoreH.Pwrtyp);
            boolean ps = JRMCoreH.PlyrSettingsB(7);
            if (!lf && skl > 0) {
               if (!action) {
                  String enable = JRMCoreH.trl("jrmc", opt1);
                  String disable = JRMCoreH.trl("jrmc", opt2);
                  return nm + ": " + (ps ? (black ? "" : "§2") + disable : (black ? "" : "§4") + enable);
               } else if (ps) {
                  JRMCoreH.Skll((byte)6, (byte)7, (byte)1);
                  JRMCoreH.jrmct(3);
               } else {
                  JRMCoreH.Skll((byte)6, (byte)7, (byte)0);
                  JRMCoreH.jrmct(3);
               }
            }
         default:
            return "";
      }
   }

   public static void openGui(int id, EntityPlayer pl) {
      pl.openGui(mod_NarutoC.instance, id, pl.field_70170_p, (int)pl.field_70165_t, (int)pl.field_70163_u, (int)pl.field_70161_v);
   }

   public static boolean NCgetEntityNC(Entity shootingEntity) {
      return shootingEntity instanceof EntityNC;
   }

   public static void NCCommonTickHandlerNPCSpawnCheck(EntityPlayerMP player) {
      if (JRMCoreConfig.NPCSpawnCheck) {
         int[] Khvln1 = new int[3];
         if (NCH.genKnvl.contains(";")) {
            for (int i = 0; i < 3; i++) {
               Khvln1[i] = Integer.parseInt(NCH.genKnvl.split(";")[i]) + NCH.genKnvlN1[i];
            }
         }

         int[] Khvln2 = new int[3];
         if (NCH.genKnvl.contains(";")) {
            for (int i = 0; i < 3; i++) {
               Khvln2[i] = Integer.parseInt(NCH.genKnvl.split(";")[i]) + NCH.genKnvlN2[i];
            }
         }

         int[] Khvln3 = new int[3];
         if (NCH.genKnvl.contains(";")) {
            for (int i = 0; i < 3; i++) {
               Khvln3[i] = Integer.parseInt(NCH.genKnvl.split(";")[i]) + NCH.genKnvlN3[i];
            }
         }

         int[][] ps = new int[][]{Khvln1, Khvln2, Khvln3};
         EntityNCKami[] ent = new EntityNCKami[]{
            new EntityKonohaSarutobi(player.field_70170_p), new EntityKonohaHiashi(player.field_70170_p), new EntityKonohaFugaku(player.field_70170_p)
         };
         Class[] entclss = new Class[]{EntityKonohaSarutobi.class, EntityKonohaHiashi.class, EntityKonohaFugaku.class};
         int[] dims = new int[]{0, 0, 0};
         boolean[] spawn = new boolean[]{NCH.genKnvl.contains(";"), NCH.genKnvl.contains(";"), NCH.genKnvl.contains(";")};

         for (int i = 0; i < ps.length; i++) {
            int[] npc = ps[i];
            if (dims[i] == player.field_71093_bK && npc.length > 2) {
               int a = 2;
               AxisAlignedBB ab = AxisAlignedBB.func_72330_a(npc[0] - a, npc[1] - a, npc[2] - a, npc[0] + a, npc[1] + a, npc[2] + a);
               List enma = player.field_70170_p.func_72872_a(entclss[i], ab);
               if (enma.isEmpty() && spawn[i]) {
                  EntityNCKami en = ent[i];
                  en.func_70012_b(npc[0] + 0.5, npc[1], npc[2] + 0.5, 0.0F, 0.0F);
                  player.field_70170_p.func_72838_d(en);
               }
            }
         }
      }
   }

   public static String NCbuildsNams(int i) {
      return i == 0 ? "nc.com.loc.Konoha" : "";
   }

   public static void spawnBuilds(MinecraftServer server) {
      int[] Knvl = new int[3];
      if (NCH.genKnvl.contains(";")) {
         for (int i = 0; i < 3; i++) {
            Knvl[i] = Integer.parseInt(NCH.genKnvl.split(";")[i]);
         }
      }

      int[][] ps = new int[][]{Knvl};

      for (int i = 0; i < ps.length; i++) {
         int[] npc = ps[i];
         if (!server.func_71218_a(0).field_72995_K && npc.length > 2) {
            builds v = NCbuilds[i];
            World w = server.func_71218_a(NCbuildsdim[i]);
            v.setWorld(w);
            if ((npc[0] != 0 || npc[1] != 0 || npc[2] != 0) && !JRMCoreComTickH.bs.contains(NCbuildsNams(i))) {
               v.func_76484_a(w, w.field_73012_v, npc[0], npc[1], npc[2]);
            }
         }
      }
   }

   public static void NCCommonTickHandlerWorldGenBuildingsResetted(MinecraftServer server) {
      if (JRMCoreH.NCresetted) {
         JRMCoreH.NCresetted = false;
         WorldGenBuildingsSpawnCheck(server);
      }
   }

   public static void WorldGenBuildingsSpawnCheck(MinecraftServer server) {
      NCH.genKnvl = NCH.kvrwi(server);
   }

   public static void JRMCoreEHonPlayerInteract(PlayerInteractEvent event) {
      EntityPlayer p = event.entityPlayer;
      int x = event.x;
      int y = event.y;
      int z = event.z;
      int length = JRMCoreEH.allSafeZones.size();

      for (int i = length - 1; i >= 0; i--) {
         EntitySafeZone sz = (EntitySafeZone)JRMCoreEH.allSafeZones.get(i);
         if (sz == null || sz.field_70128_L) {
            JRMCoreEH.allSafeZones.remove(i);
         } else if (sz.field_71093_bK == p.field_71093_bK) {
            AxisAlignedBB ab = sz.createSafeZoneHitBox();
            if (ab.field_72340_a < x && x < ab.field_72336_d && ab.field_72338_b < y && y < ab.field_72337_e && ab.field_72339_c < z && z < ab.field_72334_f) {
               Block block = p.field_70170_p.func_147439_a(x, y, z);
               boolean door = false;
               if (JRMCoreConfig.sfzna != null) {
                  for (int j = 0; j < JRMCoreConfig.sfzna.length; j++) {
                     if (block == Block.func_149684_b(JRMCoreConfig.sfzna[j])) {
                        door = true;
                        break;
                     }
                  }
               }

               if (block != null && !door && event.isCancelable() || event.action == Action.LEFT_CLICK_BLOCK && block != null && door) {
                  event.setCanceled(true);
                  return;
               }
            }
         }
      }
   }
}
