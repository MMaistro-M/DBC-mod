package JinRyuu.JRMCore.p.DBC;

import JinRyuu.DragonBC.common.DBCClientTickHandler;
import JinRyuu.DragonBC.common.DBCKeyHandler;
import JinRyuu.DragonBC.common.DBCKiTech;
import JinRyuu.DragonBC.common.Gui.DBCWishGui;
import JinRyuu.DragonBC.common.Npcs.EntityAura;
import JinRyuu.DragonBC.common.Npcs.EntityAuraRing;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCInstantTransmission;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DBCPacketHandlerClient {
   public static int getDBCPlayerBlockMode() {
      boolean instantTransmissionON = DBCClientTickHandler.instantTransmissionOn || DBCKeyHandler.thirdFn.func_151470_d();
      if (!instantTransmissionON) {
         return 1;
      } else {
         return JRMCoreH.isPowerTypeKi() && JRMCoreH.curRelease != 0 && JRMCoreH.SklLvl(17, JRMCoreH.Pwrtyp) > 0 ? 2 : 0;
      }
   }

   public static void selectGroupTeleport(int id) {
      if (JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_ENABLED[1]) {
         DBCKiTech.EnAt(DBCPacketHandlerServer.INSTANT_TRANSMISSION_GROUP, (byte)id);
      }
   }

   public void handleDBCdri(int dri, EntityPlayer p) {
      JRMCoreH.DSpeed = dri;
      if (dri >= 99) {
         JRMCoreH.SagaProg = dri;
      }
   }

   public void handleDBCwish(int id, String s, EntityPlayer p) {
      if (id == 3) {
         DBCWishGui.playerlist = s.split(";");
      }
   }

   public void handleDBCtick(int c, EntityPlayer Player) {
      if (c >= 0 && c <= 3) {
         if (c == 0 || c == 1) {
            JRMCoreH.Senzu = c;
         }

         if (c == 2 || c == 3) {
            JRMCoreH.Master = c - 2;
         }
      }
   }

   public void handleDBCspacepod1(int c, EntityPlayer p) {
      if (c >= 0 && c <= 3) {
         if (c == 0 || c == 1) {
            JRMCoreH.Senzu = c;
         }

         if (c == 2 || c == 3) {
            JRMCoreH.Master = c - 2;
         }
      } else {
         DBCClientTickHandler.mountHelper = c;
      }
   }

   public void handleDBCchargepart(byte dbcchargepart, String dbcCharger, EntityPlayer p) {
      Entity other = p.field_70170_p.func_72924_a(dbcCharger);
      Random rand = new Random();
      Entity aura = null;
      float state = 0.0F;
      float state2 = 0.0F;
      int cr = 0;
      if (JRMCoreH.plyrs != null
         && JRMCoreH.plyrs.length > 0
         && JRMCoreH.dnn(2)
         && JRMCoreH.plyrs.length <= JRMCoreH.data2.length
         && JRMCoreH.dnn(10)
         && JRMCoreH.plyrs.length <= JRMCoreH.dat10.length) {
         for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
            if (JRMCoreH.plyrs[pl].equals(dbcCharger)) {
               String[] states = JRMCoreH.data2[pl].split(";");
               state = Integer.parseInt(states[0]);
               state2 = Integer.parseInt(states[1]);
               cr = Integer.parseInt(JRMCoreH.dat10[pl].split(";")[0]);
            }
         }
      }

      if (dbcchargepart == 1) {
         aura = new EntityAura(p.field_70170_p, dbcCharger, state2 > 0.0F ? 16646144 : 11075583, state, state2, cr);
      }

      if (dbcchargepart == 2) {
         aura = new EntityAura(p.field_70170_p, dbcCharger, state2 > 0.0F ? 16646144 : 16701952, state, state2, cr);
      }

      if (dbcchargepart == 3) {
         aura = new EntityAura(p.field_70170_p, dbcCharger, state2 > 0.0F ? 16646144 : 14526719, state, state2, cr);
      }

      if (dbcchargepart == 4) {
         aura = new EntityAura(p.field_70170_p, dbcCharger, state2 > 0.0F ? 16646144 : 16646144, state, state2, cr);
      }

      if (dbcchargepart == 5) {
         aura = new EntityAuraRing(p.field_70170_p, dbcCharger, state2 > 0.0F ? 16646144 : 11075583, state, state2, cr);
      }

      if (dbcchargepart == 6) {
         aura = new EntityAuraRing(p.field_70170_p, dbcCharger, state2 > 0.0F ? 16646144 : 16701952, state, state2, cr);
      }

      if (dbcchargepart == 7) {
         aura = new EntityAuraRing(p.field_70170_p, dbcCharger, state2 > 0.0F ? 16646144 : 14526719, state, state2, cr);
      }

      if (dbcchargepart == 8) {
         aura = new EntityAuraRing(p.field_70170_p, dbcCharger, state2 > 0.0F ? 16646144 : 16646144, state, state2, cr);
      }

      if (dbcchargepart == 9) {
         aura = new EntityAura(p.field_70170_p, dbcCharger, state2 > 0.0F ? 16646144 : 11075583, state, state2, cr);
      }

      if (aura != null && other != null) {
         aura.func_70012_b(other.field_70165_t - 0.0, other.field_70163_u - 1.6 + rand.nextInt(5) * 0.03, other.field_70161_v - 0.0, rand.nextFloat(), 0.0F);
         p.field_70170_p.func_72838_d(aura);
      }
   }

   public void handleDBCascend(byte dbcascend, EntityPlayer p) {
   }

   public void handleDBCdescend(byte dbcdescend, EntityPlayer p) {
   }

   public void handleDBCchargesound(int c, String s, EntityPlayer p) {
      this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(c), s);
   }

   public void handleDBCjumpsound(int dbcjumpsound, int dbcp, EntityPlayer p) {
      if (dbcjumpsound == -2) {
         JRMCoreH.wishes = dbcp;
      } else if (dbcjumpsound == -1) {
         JRMCoreH.revTmr = dbcp * 5;
      }

      if (p.field_70170_p.func_73045_a(dbcp) instanceof EntityPlayer) {
         switch (dbcjumpsound) {
            case 1:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC.jump");
               break;
            case 2:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC3.cbigbang");
               break;
            case 3:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "");
               break;
            case 4:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC3.cburning");
               break;
            case 5:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "");
               break;
            case 6:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "");
               break;
            case 7:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC3.ckidisc");
               break;
            case 8:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC2.finalflash_charge");
               break;
            case 9:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "");
               break;
            case 10:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC3.cgallitgun");
               break;
            case 11:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC.hame");
               break;
            case 12:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC.hame");
               break;
            case 13:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC3.cspecialbeamcannon");
               break;
            case 14:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC3.cmasenko");
               break;
            case 15:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC2.deathball_charge");
               break;
            case 16:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC3.cspiritbomb");
               break;
            case 102:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC2.bigbang_fire");
               break;
            case 103:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC2.blast");
               break;
            case 104:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC3.fburning");
               break;
            case 105:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC2.basicbeam_fire");
               break;
            case 106:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC2.kiball_release");
               break;
            case 107:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC2.disc_fire");
               break;
            case 108:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC3.ffinalflash");
               break;
            case 109:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC3.fingerleser");
               break;
            case 110:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC3.fgallitgun");
               break;
            case 111:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC.ha");
               break;
            case 112:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC.ha10x");
               break;
            case 113:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC3.fspecialbeamcannon");
               break;
            case 114:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC3.fmasenko");
               break;
            case 115:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC2.deathball_fire");
               break;
            case 116:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC3.fspiritbomb");
               break;
            case 300:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC3.force");
               break;
            case 1000:
               this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(dbcp), "jinryuudragonbc:DBC2.tp");
         }
      }
   }

   public void handleDBCascendsound(int c, EntityPlayer p) {
      this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(c), "jinryuudragonbc:DBC.ascend");
   }

   public void handleDBCdescendsound(int c, EntityPlayer p) {
      this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(c), "jinryuudragonbc:DBC.descend");
   }

   public void handleDBCscouter1(int c, EntityPlayer p) {
      this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(c), "jinryuudragonbc:scouter.scouteron");
   }

   public void handleDBCscouter2(int c, EntityPlayer p) {
      this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(c), "jinryuudragonbc:scouter.count");
   }

   public void handleDBCscouter3(int c, EntityPlayer p) {
      this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(c), "jinryuudragonbc:scouter.warn");
   }

   public void handleDBCscouter4(int c, EntityPlayer p) {
      this.soundPowerUp((EntityPlayer)p.field_70170_p.func_73045_a(c), "jinryuudragonbc:scouter.startcount");
   }

   public void soundPowerUp(EntityPlayer var4, String var2) {
      if (var4 != null) {
         var4.func_85030_a(var2, 0.15F, 1.0F);
      }
   }

   public void closeInventoryChange(EntityPlayer p) {
      p.field_71071_by.field_70459_e = false;
   }

   public void closeInventoryChange(EntityPlayerMP player) {
      player.field_71071_by.field_70459_e = false;
   }
}
