package JinRyuu.JRMCore;

import JinRyuu.DragonBC.common.Npcs.EntityDBCEvil;
import JinRyuu.DragonBC.common.Npcs.EntityDBCKami;
import JinRyuu.JRMCore.entity.EntityEnergyAtt;
import JinRyuu.JRMCore.entity.EntityPunch;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.WorldServer;

public class JRMCoreModDBC {
   public static void safeOut(MinecraftServer server) {
      WorldServer dim0 = server.func_71218_a(0);
      AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(56.0, 10.0, 20.0, 116.0, 240.0, 80.0);
      if (JRMCoreH.DBC() && JRMCoreConfig.sfzns) {
         List enma = dim0.func_72872_a(EntityDBCKami.class, aabb);
         if (enma.isEmpty()) {
         }

         if (enma.size() > 1) {
            for (int i = 1; enma.size() > i; i++) {
               Entity m = (Entity)enma.get(i);
               m.func_70106_y();
            }
         }

         List mobs = dim0.func_72872_a(EntityMob.class, aabb);
         if (mobs.size() > 0) {
            for (int i = 0; mobs.size() > i; i++) {
               Entity m = (Entity)mobs.get(i);
               m.func_70106_y();
            }
         }

         List dem = dim0.func_72872_a(EntityDBCEvil.class, aabb);
         if (dem.size() > 0) {
            for (int i = 0; dem.size() > i; i++) {
               Entity m = (Entity)dem.get(i);
               m.func_70106_y();
            }
         }

         List eea = dim0.func_72872_a(EntityEnergyAtt.class, aabb);
         if (eea.size() > 0) {
            for (int i = 0; eea.size() > i; i++) {
               Entity m = (Entity)eea.get(i);
               m.func_70106_y();
            }
         }

         List ep = dim0.func_72872_a(EntityPunch.class, aabb);
         if (ep.size() > 0) {
            for (int i = 0; ep.size() > i; i++) {
               Entity m = (Entity)ep.get(i);
               m.func_70106_y();
            }
         }
      }
   }
}
