package JinRyuu.JRMCore.manager;

import JinRyuu.DragonBC.common.Npcs.EntityMasterBabidi;
import JinRyuu.DragonBC.common.Npcs.EntityMasterCell;
import JinRyuu.DragonBC.common.Npcs.EntityMasterEnma;
import JinRyuu.DragonBC.common.Npcs.EntityMasterFreeza;
import JinRyuu.DragonBC.common.Npcs.EntityMasterGohan;
import JinRyuu.DragonBC.common.Npcs.EntityMasterGoku;
import JinRyuu.DragonBC.common.Npcs.EntityMasterGuru;
import JinRyuu.DragonBC.common.Npcs.EntityMasterJin;
import JinRyuu.DragonBC.common.Npcs.EntityMasterKaio;
import JinRyuu.DragonBC.common.Npcs.EntityMasterKami;
import JinRyuu.DragonBC.common.Npcs.EntityMasterKarin;
import JinRyuu.DragonBC.common.Npcs.EntityMasterPiccolo;
import JinRyuu.DragonBC.common.Npcs.EntityMasterRoshi;
import JinRyuu.DragonBC.common.Npcs.EntityMasterTrunksFuture;
import JinRyuu.DragonBC.common.Npcs.EntityMasterVegeta;
import JinRyuu.DragonBC.common.Npcs.EntityMasterWhis;
import JinRyuu.JRMCore.entity.EntitySafeZone;
import net.minecraft.entity.player.EntityPlayerMP;

public class JRMCoreManagerDBC {
   public static String[] getMasterNames() {
      return new String[]{
         "Jin", "Babidi", "Cell", "Enma", "Freeza", "Gohan", "Goku", "Guru", "Kaio", "Kami", "Karin", "Piccolo", "Roshi", "Vegeta", "Trunks", "Whis"
      };
   }

   public static EntitySafeZone[] getMasters(EntityPlayerMP entityplayermp) {
      return new EntitySafeZone[]{
         new EntityMasterJin(entityplayermp.field_70170_p),
         new EntityMasterBabidi(entityplayermp.field_70170_p),
         new EntityMasterCell(entityplayermp.field_70170_p),
         new EntityMasterEnma(entityplayermp.field_70170_p),
         new EntityMasterFreeza(entityplayermp.field_70170_p),
         new EntityMasterGohan(entityplayermp.field_70170_p),
         new EntityMasterGoku(entityplayermp.field_70170_p),
         new EntityMasterGuru(entityplayermp.field_70170_p),
         new EntityMasterKaio(entityplayermp.field_70170_p),
         new EntityMasterKami(entityplayermp.field_70170_p),
         new EntityMasterKarin(entityplayermp.field_70170_p),
         new EntityMasterPiccolo(entityplayermp.field_70170_p),
         new EntityMasterRoshi(entityplayermp.field_70170_p),
         new EntityMasterVegeta(entityplayermp.field_70170_p),
         new EntityMasterTrunksFuture(entityplayermp.field_70170_p),
         new EntityMasterWhis(entityplayermp.field_70170_p)
      };
   }
}
