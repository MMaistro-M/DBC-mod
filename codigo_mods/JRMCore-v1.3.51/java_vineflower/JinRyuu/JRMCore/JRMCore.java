package JinRyuu.JRMCore;

import JinRyuu.JRMCore.entity.EntityCusPar;
import com.google.common.base.Strings;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import java.io.File;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringEscapeUtils;

public class JRMCore {
   public static JRMCorePacHanS phs = new JRMCorePacHanS();
   public static File configDir = null;
   public static File configDir_client = null;
   public static File configDir_clientNotifications = null;
   public static File configDir_minigame_concentration = null;
   public static File configDir_minigame_airBoxing = null;
   public static File configDir_form = null;
   public static File configDir_form_god = null;
   public static File configDir_skillsDBC = null;
   public static File configDir_skillsNC = null;
   public static File configDir_skills = null;
   public static File configDir_skillInstantTransmission = null;
   public static File[] configDir_formMastery = new File[JRMCoreH.Races.length];
   public static File[] configDir_races;
   public static File[] configDir_DBCAAiDifficulties;
   public static File configDir_formMasteryMain;

   public void initialize() {
      FMLCommonHandler.instance().bus().register(new JRMCoreComTickH());
   }

   public void postInit() {
      if (FMLCommonHandler.instance().getMinecraftServerInstance() != null && JRMCoreConfig.ssurl.contains("http://")) {
         String m = "";

         for (ModContainer mod : Loader.instance().getModList()) {
            if (mod.getMetadata() != null && mod.getMetadata().parentMod == null && !Strings.isNullOrEmpty(mod.getMetadata().parent)) {
               String parentMod = mod.getMetadata().parent;
               ModContainer parentContainer = (ModContainer)Loader.instance().getIndexedModList().get(parentMod);
               if (parentContainer != null) {
                  mod.getMetadata().parentMod = parentContainer;
                  parentContainer.getMetadata().childMods.add(mod);
                  continue;
               }
            } else if (mod.getMetadata() != null && mod.getMetadata().parentMod != null) {
               continue;
            }

            if (!mod.getModId().equals("mcp") && !mod.getModId().equals("FML") && !mod.getModId().equals("Forge")) {
               m = m + mod.getModId() + "@" + mod.getName() + "@" + mod.getVersion() + ";";
            }
         }

         m = StringEscapeUtils.escapeHtml4(m).replace(" ", "%20");
         String[] ssurl = JRMCoreConfig.ssurl.split(",");
         String var8 = JRMCorePacHanS.getUrlContents(ssurl[0] + ssurl[1] + ssurl[4] + ssurl[5] + m);
      }
   }

   public void registerRenderThings() {
   }

   public void registerTicks() {
   }

   public void registerKeys() {
   }

   public EntityPlayer getPlayerEntity(MessageContext ctx) {
      return ctx.getServerHandler().field_147369_b;
   }

   public EntityPlayer getPlayerEntity() {
      return null;
   }

   public EntityPlayer getPlayerClient() {
      return null;
   }

   public void generateDamIndParticles(double x, double y, double z, double amount, float timeleft) {
   }

   public void func_gcp(Entity entity, EntityCusPar e, double poX, double poY, double poZ, double moX, double moY, double moZ) {
   }

   public void func_gcp(
      Entity entity, EntityCusPar e, double poX, double poY, double poZ, double moX, double moY, double moZ, float size_min, float size_max, float size_speed
   ) {
   }

   public void func_gcp(
      Entity entity,
      EntityCusPar e,
      double poX,
      double poY,
      double poZ,
      double moX,
      double moY,
      double moZ,
      float size_min,
      float size_max,
      float size_speed,
      float motion
   ) {
   }

   public void func_gcp(
      Entity entity,
      EntityCusPar e,
      double poX,
      double poY,
      double poZ,
      double moX,
      double moY,
      double moZ,
      float size_min,
      float size_max,
      float size_speed,
      float motion,
      int death
   ) {
   }

   public void func_gcp(Entity entity, EntityCusPar e, double poX, double poY, double poZ, double moX, double moY, double moZ, int img_s) {
   }

   public void func_gcp(
      Entity entity,
      EntityCusPar e,
      double poX,
      double poY,
      double poZ,
      double sta_poX,
      double sta_poY,
      double sta_poZ,
      double moX,
      double moY,
      double moZ,
      int img_s
   ) {
   }

   public void func_gcp(
      String data3,
      World w,
      float box1,
      float box2,
      double poX,
      double poY,
      double poZ,
      double start_poX,
      double start_poY,
      double start_poZ,
      double moX,
      double moY,
      double moZ,
      float data29,
      int id,
      int id_min,
      int id_max,
      int data32,
      boolean rotate,
      float max_rotation_sp,
      boolean rotate2,
      float max_rotation_sp2,
      int data1,
      String rr,
      int data2,
      int data4,
      float data5,
      float data6,
      float data7,
      int data31,
      float data8,
      float data9,
      float data10,
      float data11,
      float data12,
      float data13,
      float data14,
      float data15,
      float data16,
      int data20,
      float data21,
      float data22,
      float data23,
      float data24,
      float data25,
      boolean data33,
      int data34,
      boolean data35,
      Entity ent
   ) {
   }

   public void func_gcp(
      String data3,
      World w,
      float box1,
      float box2,
      double poX,
      double poY,
      double poZ,
      double start_poX,
      double start_poY,
      double start_poZ,
      double moX,
      double moY,
      double moZ,
      float data29,
      int id,
      int id_min,
      int id_max,
      int data32,
      boolean rotate,
      float max_rotation_sp,
      boolean rotate2,
      float max_rotation_sp2,
      int data1,
      String rr,
      int data2,
      int data4,
      float data5,
      float data6,
      float data7,
      int data31,
      float data8,
      float data9,
      float data10,
      float data11,
      float data12,
      float data13,
      float data14,
      float data15,
      float data16,
      int data20,
      float data21,
      float data22,
      float data23,
      float data24,
      float data25,
      boolean data33,
      int data34,
      boolean data35
   ) {
   }
}
