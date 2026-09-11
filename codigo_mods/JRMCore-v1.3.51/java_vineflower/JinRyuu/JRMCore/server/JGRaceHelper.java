package JinRyuu.JRMCore.server;

import JinRyuu.JRMCore.JRMCoreH;
import net.minecraft.nbt.NBTTagCompound;

public class JGRaceHelper {
   public static int[][] RACIAL_SKILL_MAX = new int[][]{{0, 0, 0, 0, 0, 0, 0}, {5, 7, 7, 5, 6, 5, 5}, {9, 9, 9, 9, 9, 9, 9}, {0, 0, 0, 0, 0, 0, 0}};

   public static boolean isAboveMaxRacialSkill(byte current, boolean dbc, boolean nc, byte race) {
      return current > getMaxRacialSkillLevel(dbc, nc, race);
   }

   public static boolean isAboveMaxRacialSkill(NBTTagCompound nbt) {
      byte current = getRacialSkillLevel(nbt);
      byte max = getMaxRacialSkillLevel(nbt);
      return current > max;
   }

   public static byte getRacialSkillLevel(NBTTagCompound nbt) {
      boolean dbc = nbt.func_74771_c("jrmcPwrtyp") == 1;
      boolean nc = nbt.func_74771_c("jrmcPwrtyp") == 2;
      byte race = nbt.func_74771_c("jrmcRace");
      byte currentLevel = 0;
      String key = dbc ? "jrmcSSltX" : "jrmcSSltY";
      return !nbt.func_74764_b(key)
            || nbt.func_74779_i(key).contains("pty")
            || nbt.func_74779_i(key).length() <= 1
            || nc && (nbt.func_74779_i("jrmcSSltY").contains("Sai") || race == 1 || race == 2)
         ? -1
         : Byte.parseByte(nbt.func_74779_i(key).substring(2));
   }

   public static byte getMaxRacialSkillLevel(NBTTagCompound nbt) {
      boolean dbc = nbt.func_74771_c("jrmcPwrtyp") == 1;
      boolean nc = nbt.func_74771_c("jrmcPwrtyp") == 2;
      byte race = nbt.func_74771_c("jrmcRace");
      return getMaxRacialSkillLevel(dbc, nc, race);
   }

   public static byte getMaxRacialSkillLevel(boolean dbc, boolean nc, byte race) {
      if (dbc) {
         if (JRMCoreH.rSai(race)) {
            return 7;
         } else {
            return (byte)(JRMCoreH.isRaceArcosian(race) ? 6 : 5);
         }
      } else {
         return (byte)(nc ? 9 : 0);
      }
   }

   public static void setRacialSkillToMax(NBTTagCompound nbt) {
      boolean dbc = nbt.func_74771_c("jrmcPwrtyp") == 1;
      boolean nc = nbt.func_74771_c("jrmcPwrtyp") == 2;
      byte race = nbt.func_74771_c("jrmcRace");
      setRacialSkill(nbt, getMaxRacialSkillLevel(dbc, nc, race), dbc, nc, race);
   }

   public static void setRacialSkill(NBTTagCompound nbt, byte value) {
      boolean dbc = nbt.func_74771_c("jrmcPwrtyp") == 1;
      boolean nc = nbt.func_74771_c("jrmcPwrtyp") == 2;
      byte race = nbt.func_74771_c("jrmcRace");
      setRacialSkill(nbt, value, dbc, nc, race);
   }

   public static void setRacialSkill(NBTTagCompound nbt, byte value, boolean dbc, boolean nc, byte race) {
      if (dbc || nc) {
         String key = dbc ? "jrmcSSltX" : "jrmcSSltY";
         nbt.func_74778_a(key, (dbc ? JRMCoreH.vlblRSkls[0] : JRMCoreH.ncCSkls[nbt.func_74771_c("jrmcClass")]) + value);
      }
   }
}
