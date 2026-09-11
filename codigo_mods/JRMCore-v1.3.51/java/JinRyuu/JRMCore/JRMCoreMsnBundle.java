package JinRyuu.JRMCore;

import com.google.common.collect.Lists;
import java.util.ArrayList;

public class JRMCoreMsnBundle {
   public String name;
   public String desc;
   public String authors;
   public String version;
   public String mods;
   public JRMCoreMsnBundle.Stngs settings;
   public ArrayList<JRMCoreMsn> missions = Lists.newArrayList();

   public JRMCoreMsnBundle() {
      this.settings = new JRMCoreMsnBundle.Stngs();
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDesc() {
      return this.desc;
   }

   public void setDesc(String desc) {
      this.desc = desc;
   }

   public ArrayList<JRMCoreMsn> getMissions() {
      return this.missions;
   }

   public void setMissions(ArrayList<JRMCoreMsn> data) {
      this.missions = data;
   }

   public String getVersion() {
      return this.version;
   }

   public void setVersion(String version) {
      this.version = version;
   }

   public String getMods() {
      return this.mods;
   }

   public void setMods(String mods) {
      this.mods = mods;
   }

   public String getAuthor() {
      return this.authors;
   }

   public void setAuthor(String author) {
      this.authors = author;
   }

   public JRMCoreMsnBundle.Stngs getSettings() {
      return this.settings;
   }

   public void setSettings(JRMCoreMsnBundle.Stngs settings) {
      this.settings = settings;
   }

   public class Stngs {
      public String repeat;
      public String unlock;
      public String vars;

      public String getRepeat() {
         return this.repeat;
      }

      public void setRepeat(String repeat) {
         this.repeat = repeat;
      }

      public String getUnlock() {
         return this.unlock;
      }

      public void setUnlock(String unlock) {
         this.unlock = unlock;
      }

      public String getVars() {
         return this.vars;
      }

      public void setVars(String vars) {
         this.vars = vars;
      }
   }
}
