package JinRyuu.JRMCore.p.NC;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.entity.EntityEnergyAttJ;
import JinRyuu.JRMCore.entity.EntityEnergyAttJ2;
import JinRyuu.JRMCore.entity.EntityEnergyAttJ3;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import JinRyuu.JRMCore.p.PD;
import JinRyuu.NarutoC.common.NCJutsus;
import JinRyuu.NarutoC.common.Entitys.hgTaiKaiEntity;
import JinRyuu.NarutoC.common.Npcs.EntityNCjutsuBunshin;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.Vec3;

public class NCPacketHandlerServer {
   public void handleJRNC(int c, String d, EntityPlayer Player) {
      if (d.equalsIgnoreCase("copy")) {
         MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
         Entity tr = server.func_130014_f_().func_73045_a(c);
         EntityPlayerMP targ = tr instanceof EntityPlayerMP ? (EntityPlayerMP)tr : null;
         if (targ != null) {
            PD.sendTo(new NCPData2(Player.func_145782_y(), d), targ);
         }
      } else {
         MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
         EntityPlayerMP targ = (EntityPlayerMP)server.func_130014_f_().func_73045_a(c);
         String[] ds = d.split(";");
         int s = Integer.parseInt(ds[0]);
         if (s != 0 && s != 9) {
            d = JRMCoreH.getString(Player, JRMCoreH.techNbt[s - 1]);
            if (d.length() < 3) {
               s = Integer.parseInt(d);
               d = "";

               for (int i = 0; i < JRMCoreH.pmj[s].length; i++) {
                  d = d + JRMCoreH.pmj[s][i] + ";";
               }

               d = d + s;
            }

            JRMCoreH.setString(d, targ, "jrmcTechC");
         }

         if (targ != null) {
            PD.sendTo(new NCPData2(Player.func_145782_y(), d), targ);
         }
      }
   }

   public void handleJRNC(byte dt, byte d1, EntityPlayer Player) {
      if (dt == 0) {
         if (d1 == 10) {
            JRMCoreH.setByte(1, Player, "jrmcFrng");
         } else if (d1 == 11) {
            JRMCoreH.setByte(0, Player, "jrmcFrng");
         } else if (d1 == 12) {
            JRMCoreH.setByte(1, Player, "jrmcdj");
         } else if (d1 == 13) {
            JRMCoreH.setByte(2, Player, "jrmcdj");
         } else {
            NBTTagCompound nbt = JRMCoreH.nbt(Player);
            int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(Player);
            String PlyrSkillX = JRMCoreH.getString(Player, "jrmcSSltX");
            String PlyrSkillY = JRMCoreH.getString(Player, "jrmcSSltY");
            String[] PlyrSkills = JRMCoreH.getString(Player, "jrmcSSlts").split(",");
            byte rc = JRMCoreH.getByte(Player, "jrmcRace");
            byte pwr = JRMCoreH.getByte(Player, "jrmcPwrtyp");
            byte cls = JRMCoreH.getByte(Player, "jrmcClass");
            int maxEnergy = JRMCoreH.stat(Player, 5, pwr, 5, PlyrAttrbts[5], rc, cls, 0.0F);
            int curEn = JRMCoreH.getInt(Player, "jrmcEnrgy");
            int curBo = JRMCoreH.getInt(Player, "jrmcBdy");
            byte align = JRMCoreH.getByte(Player, "jrmcAlign");
            byte state = JRMCoreH.getByte(Player, "jrmcState");
            byte trans = (byte)(state + 1);
            int ma = JRMCoreH.SklLvlY(2, PlyrSkillY);
            int tj = JRMCoreH.SklLvl(0, 2, PlyrSkills);
            int ni = JRMCoreH.SklLvl(1, 2, PlyrSkills);
            int ge = JRMCoreH.SklLvl(2, 2, PlyrSkills);
            int md = JRMCoreH.SklLvl(9, 2, PlyrSkills);
            int cc = JRMCoreH.SklLvl(3, 2, PlyrSkills);
            int afi = JRMCoreH.SklLvl(4, 2, PlyrSkills);
            int awi = JRMCoreH.SklLvl(5, 2, PlyrSkills);
            int ali = JRMCoreH.SklLvl(6, 2, PlyrSkills);
            int aea = JRMCoreH.SklLvl(7, 2, PlyrSkills);
            int awa = JRMCoreH.SklLvl(8, 2, PlyrSkills);
            boolean may = false;
            Entity var8 = null;
            String s = d1 == 100 ? JRMCoreH.getString(Player, "jrmcTechC") : JRMCoreH.getString(Player, JRMCoreH.techNbt[d1]);
            if ((d1 < 0 || d1 >= 4 || s == null || s.length() <= 0) && (d1 != 100 || s == null || s.split(";").length != 15)) {
               if ((d1 < 4 || d1 >= 8 || s == null || s.length() <= 0) && (d1 != 100 || s == null || s.split(";").length != 16)) {
                  if (d1 == 8) {
                     MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                     String gi = NCJutsus.rgi(server, Player.func_70005_c_());
                     if (gi.contains(";")) {
                        String[] a = gi.split(";");
                        String m = a[0];
                        String[] j = JRMCoreH.pmj[Integer.parseInt(a[1])];
                        int dmg = Integer.parseInt(a[2]);
                        int t = Integer.parseInt(a[3]);
                        int l = Integer.parseInt(a[4]);
                        int cst = dmg / t * l;
                        cst = cst < 1 ? 1 : cst;
                        byte cl = JRMCoreH.getByte(Player, "jrmcClass");
                        String doujutsu = JRMCoreH.ncCSkls[cl];
                        String s2 = JRMCoreH.getString(Player, "jrmcSSltY");
                        int lvl = 0;
                        if (s2 != null && s2.length() > 2 && cl != 0 && doujutsu.equals("SG")) {
                           lvl = Integer.parseInt(s2.replaceAll(doujutsu, "")) + 1;
                        }

                        cst = (int)(cst * (0.5F + (0.5F - 0.05F * lvl)));
                        if (curEn >= cst && ge > 0) {
                           byte P = JRMCoreH.getByte(Player, "jrmcPtch");
                           float ptch = P == 0 ? 1.0F : 0.8F + P * 0.006F;
                           Player.field_70170_p.func_72956_a(Player, "jinryuunarutoc:NC2." + JRMCoreH.techNCSndIncSpec[0], 1.0F, ptch);
                           NCJutsus.wgi(server, " ", Player.func_70005_c_(), true);
                           if (!JRMCoreH.isInCreativeMode(Player)) {
                              JRMCoreH.setInt(curEn - cst, Player, "jrmcEnrgy");
                           }
                        } else if (cst > curEn) {
                           Player.func_145747_a(new ChatComponentText(JRMCoreH.cly + " Not Enough Chakra!"));
                        } else if (ge == 0) {
                           Player.func_145747_a(
                              new ChatComponentText(
                                 JRMCoreH.cly
                                    + "You need to learn the "
                                    + JRMCoreH.clgd
                                    + JRMCoreH.trl("nc", JRMCoreH.NCSkillNames[2])
                                    + JRMCoreH.cly
                                    + " Skill!"
                              )
                           );
                        }
                     }

                     String StE = nbt.func_74779_i("jrmcStatusEff");
                     StE = JRMCoreH.StusEfcts(16, StE, nbt, false);
                  }
               } else {
                  if (d1 == 100) {
                     s = s.split(";")[15];
                  }

                  int tn = Integer.parseInt(s);
                  byte curRel = JRMCoreH.getByte(Player, "jrmcRelease");
                  String[] tech = JRMCoreH.pmj[tn];
                  if (tech != null && tech.length > 9) {
                     byte type = Byte.parseByte(tech[3]);
                     byte speed = Byte.parseByte(tech[4]);
                     int dam = Integer.parseInt(tech[5]);
                     byte effect = Byte.parseByte(tech[6]);
                     int cost = Integer.parseInt(tech[7]);
                     byte color = Byte.parseByte(tech[10]);
                     byte density = Byte.parseByte(tech[11]);
                     byte sincantation = 0;
                     byte sfire = 0;
                     byte smove = 0;
                     if (tech.length > 12) {
                        sincantation = Byte.parseByte(tech[12]);
                        sfire = Byte.parseByte(tech[13]);
                        smove = Byte.parseByte(tech[14]);
                     }

                     int typ = type;
                     int sped = speed;
                     int effe = effect;
                     double costP = JRMCoreH.round(JRMCoreH.techNCCostP[cost] * (curRel * 0.01), 1);
                     int cst = (int)(costP * 0.01 * maxEnergy);
                     cst = cst < 1 ? 1 : cst;
                     int dmg1 = (int)(cst - cst * 0.25 * sped + (typ == 0 ? cst * 0.2 : 0.0));
                     int dmg = dmg1;
                     int af = 0;
                     if (effe == 0 && afi > 0) {
                        may = true;
                        af = afi;
                     } else if (effe == 1 && awi > 0) {
                        may = true;
                        af = awi;
                     } else if (effe == 2 && ali > 0) {
                        may = true;
                        af = ali;
                     } else if (effe == 3 && aea > 0) {
                        may = true;
                        af = aea;
                     } else if (effe == 4 && awa > 0) {
                        may = true;
                        af = awa;
                     } else {
                        may = true;
                        af = ni;
                     }

                     String snd = "";
                     String snd2 = "";
                     if (density == 1) {
                        snd = "NC1." + JRMCoreH.techNCSndIncPM[sincantation];
                     }

                     if (density == 2 && JRMCoreH.techNCSndIncCls.length > density) {
                        snd2 = "NC2." + JRMCoreH.techNCSndIncCls[density];
                     }

                     if (density == 4) {
                        if (JRMCoreH.techNCSndIncAff.length > effe) {
                           snd = "NC2." + JRMCoreH.techNCSndIncAff[effe];
                        }

                        if (sfire == 7) {
                           snd2 = "NC2." + JRMCoreH.techNCSndIncSpec[sfire];
                        } else if (JRMCoreH.techNCSndIncCls.length > density) {
                           snd2 = "NC2." + JRMCoreH.techNCSndIncCls[density];
                        }
                     }

                     if (density == 5) {
                        this.soundPowerUp(Player, "jinryuunarutoc:NC1.Chakra_Focus");
                     }

                     if (cst <= curEn && may) {
                        boolean wcbu = true;
                        if (density != 1) {
                           if (density == 2) {
                              if (ge > 0) {
                                 byte cl = JRMCoreH.getByte(Player, "jrmcClass");
                                 String doujutsu = JRMCoreH.ncCSkls[cl];
                                 String s2 = JRMCoreH.getString(Player, "jrmcSSltY");
                                 int t = 0;
                                 if (s2 != null && s2.length() > 2 && cl != 0 && doujutsu.equals("SG")) {
                                    t = Integer.parseInt(s2.replaceAll(doujutsu, "") + 1);
                                 }

                                 double d = dmg / 15.0;
                                 dmg = (int)(d * 5.0 + d * ge);
                                 dmg = dmg < 1 ? 1 : dmg;
                                 dmg = (int)(dmg * (1.0 + t * 0.1));
                                 var8 = new EntityEnergyAttJ(
                                    Player, type, speed, dmg, effect, color, density, sincantation, sfire, smove, (byte)tn, dmg1, cst, cost
                                 );
                              } else {
                                 Player.func_145747_a(
                                    new ChatComponentText(
                                       JRMCoreH.cly
                                          + "You need to learn the "
                                          + JRMCoreH.clgd
                                          + JRMCoreH.trl("nc", JRMCoreH.NCSkillNames[2])
                                          + JRMCoreH.cly
                                          + " Skill!"
                                    )
                                 );
                              }
                           } else if (density == 4) {
                              if (ni > 0) {
                                 Player.field_70170_p.func_72956_a(Player, "jinryuunarutoc:NC1." + JRMCoreH.techNCSndFirePM[sincantation], 0.25F, 1.0F);
                                 double d = dmg / 50.0;
                                 dmg = (int)(d * 20.0 + d * (ni * 2.0 + af));
                                 dmg = (int)(dmg * (effe == 7 ? 1.0 : 0.5));
                                 dmg = dmg < 1 ? 1 : dmg;
                                 var8 = new EntityNCjutsuBunshin(Player.field_70170_p, Player, tn + "", dmg, null);
                                 var8.func_70012_b(
                                    Player.field_70165_t - 0.0,
                                    Player.field_70163_u + 1.5,
                                    Player.field_70161_v - 0.0,
                                    Player.field_70177_z,
                                    Player.field_70125_A
                                 );
                              } else {
                                 Player.func_145747_a(
                                    new ChatComponentText(
                                       JRMCoreH.cly
                                          + "You need to learn the "
                                          + JRMCoreH.clgd
                                          + JRMCoreH.trl("nc", JRMCoreH.NCSkillNames[1])
                                          + JRMCoreH.cly
                                          + " Skill!"
                                    )
                                 );
                              }
                           } else if (density == 5) {
                              if (cc > 0 && md > 0) {
                                 Player.field_70170_p.func_72956_a(Player, "jinryuunarutoc:NC1." + JRMCoreH.techNCSndFirePM[sincantation], 0.25F, 1.0F);
                                 double d = dmg / 50.0F;
                                 dmg = (int)(d * 20.0 + d * (md * 2.0 + cc));
                                 dmg = dmg < 1 ? 1 : dmg;
                                 int a = 2;
                                 AxisAlignedBB ab = AxisAlignedBB.func_72330_a(
                                    Player.field_70165_t - a,
                                    Player.field_70163_u - a,
                                    Player.field_70161_v - a,
                                    Player.field_70165_t + a,
                                    Player.field_70163_u + a,
                                    Player.field_70161_v + a
                                 );
                                 List list = Player.field_70170_p.func_72872_a(EntityPlayer.class, ab);
                                 if (list.size() == 1) {
                                    int maxBody = JRMCoreH.stat(Player, 2, pwr, 2, PlyrAttrbts[2], rc, cls, 0.0F);
                                    int curBody = JRMCoreH.getInt(Player, "jrmcBdy");
                                    int all = curBody + dmg;
                                    JRMCoreH.setInt(all > maxBody ? maxBody : all, Player, "jrmcBdy");
                                 } else {
                                    for (int i = 0; i < list.size(); i++) {
                                       EntityPlayer ent = (EntityPlayer)list.get(i);
                                       if (ent != Player) {
                                          int[] entPlyrAttrbts = JRMCoreH.PlyrAttrbts(ent);
                                          byte entrc = JRMCoreH.getByte(ent, "jrmcRace");
                                          byte entpwr = JRMCoreH.getByte(ent, "jrmcPwrtyp");
                                          byte entcls = JRMCoreH.getByte(ent, "jrmcClass");
                                          int entmaxBody = JRMCoreH.stat(Player, 2, entpwr, 2, entPlyrAttrbts[2], entrc, entcls, 0.0F);
                                          int entcurBody = JRMCoreH.getInt(ent, "jrmcBdy");
                                          int all = entcurBody + dmg / (list.size() - 1);
                                          JRMCoreH.setInt(all > entmaxBody ? entmaxBody : all, ent, "jrmcBdy");
                                       }
                                    }
                                 }
                              } else if (cc == 0) {
                                 Player.func_145747_a(
                                    new ChatComponentText(
                                       JRMCoreH.cly
                                          + "You need to learn the "
                                          + JRMCoreH.clgd
                                          + JRMCoreH.trl("nc", JRMCoreH.NCSkillNames[3])
                                          + JRMCoreH.cly
                                          + " Skill!"
                                    )
                                 );
                              } else {
                                 Player.func_145747_a(
                                    new ChatComponentText(
                                       JRMCoreH.cly
                                          + "You need to learn the "
                                          + JRMCoreH.clgd
                                          + JRMCoreH.trl("nc", JRMCoreH.NCSkillNames[9])
                                          + JRMCoreH.cly
                                          + " Skill!"
                                    )
                                 );
                              }
                           } else if (density == 6) {
                              int tm = nbt.func_74762_e("nccdt") + 10;
                              int tc = (int)(System.currentTimeMillis() / 1000L);
                              if (tc > tm && ni > 0 && ma > 0 && cls == 2) {
                                 nbt.func_74768_a("nccdt", (int)(System.currentTimeMillis() / 1000L));
                                 String StE = nbt.func_74779_i("jrmcStatusEff");
                                 StE = JRMCoreH.StusEfcts(16, StE, nbt, true);
                                 Player.field_70170_p.func_72956_a(Player, "jinryuunarutoc:NC1." + JRMCoreH.techNCSndFirePM[sincantation], 0.25F, 1.0F);
                              } else if (cls != 2) {
                                 Player.func_145747_a(
                                    new ChatComponentText(
                                          "You need to be from Clan " + JRMCoreH.clgd + JRMCoreH.trl("jrmc", JRMCoreH.cl(pwr)[2]) + " to use this Jutsu!"
                                       )
                                       .func_150255_a(JRMCoreH.CHAT_STYLE_YELLOW)
                                 );
                              } else if (tj == 0) {
                                 Player.func_145747_a(
                                    new ChatComponentText("You need to learn the Skill " + JRMCoreH.clgd + JRMCoreH.trl("nc", JRMCoreH.NCSkillNames[1]) + "!")
                                       .func_150255_a(JRMCoreH.CHAT_STYLE_YELLOW)
                                 );
                              } else if (ma == 0) {
                                 Player.func_145747_a(
                                    new ChatComponentText(
                                          "You need to upgrade Main Skill " + JRMCoreH.clgd + JRMCoreH.trl("nc", JRMCoreH.NCRacialSkillAbilityNames[2]) + "!"
                                       )
                                       .func_150255_a(JRMCoreH.CHAT_STYLE_YELLOW)
                                 );
                              } else if (tm >= tc) {
                                 Player.func_145747_a(
                                    new ChatComponentText("You need to wait " + (tm - tc + 1) + " seconds to use it again!")
                                       .func_150255_a(JRMCoreH.CHAT_STYLE_YELLOW)
                                 );
                              }
                           } else if (density == 7) {
                              int tm = nbt.func_74762_e("nccdt") + 10;
                              int tc = (int)(System.currentTimeMillis() / 1000L);
                              if (tc > tm && tj > 0 && ma > 0 && cls == 1) {
                                 nbt.func_74768_a("nccdt", (int)(System.currentTimeMillis() / 1000L));
                                 double d = dmg / 50.0F;
                                 dmg = (int)(d * 20.0 + d * (ni * 2.0 + af));
                                 var8 = new hgTaiKaiEntity(Player.field_70170_p, Player.func_70005_c_(), 56573, 0.0F, 0.0F, 100, false, 0.9F, dmg);
                              } else if (cls != 1) {
                                 Player.func_145747_a(
                                    new ChatComponentText(
                                          "You need to be from Clan " + JRMCoreH.clgd + JRMCoreH.trl("jrmc", JRMCoreH.cl(pwr)[1]) + " to use this Jutsu!"
                                       )
                                       .func_150255_a(JRMCoreH.CHAT_STYLE_YELLOW)
                                 );
                              } else if (tj == 0) {
                                 Player.func_145747_a(
                                    new ChatComponentText("You need to learn the Skill " + JRMCoreH.clgd + JRMCoreH.trl("nc", JRMCoreH.NCSkillNames[1]) + "!")
                                       .func_150255_a(JRMCoreH.CHAT_STYLE_YELLOW)
                                 );
                              } else if (ma == 0) {
                                 Player.func_145747_a(
                                    new ChatComponentText(
                                          "You need to upgrade Main Skill " + JRMCoreH.clgd + JRMCoreH.trl("nc", JRMCoreH.NCRacialSkillAbilityNames[1]) + "!"
                                       )
                                       .func_150255_a(JRMCoreH.CHAT_STYLE_YELLOW)
                                 );
                              } else if (tm >= tc) {
                                 Player.func_145747_a(
                                    new ChatComponentText("You need to wait " + (tm - tc + 1) + " seconds to use it again!")
                                       .func_150255_a(JRMCoreH.CHAT_STYLE_YELLOW)
                                 );
                              }
                           }
                        } else if (ni > 0 && af > 0) {
                           double d = dmg / 50.0F;
                           dmg = (int)(d * 20.0 + d * (ni * 2.0 + af));
                           byte dat = 0;
                           if (snd.contains("Rasengan")) {
                              dat = 1;
                           } else if (snd.contains("Chidori")) {
                              dat = 2;
                           } else if (snd.contains("Katon_Hous")) {
                              dat = 3;
                           } else if (snd.contains("Earth_Wall")) {
                              dat = 4;
                           } else if (snd.contains("Mud_Wall")) {
                              dat = 5;
                           } else if (snd.contains("Water_Pistol")) {
                              dat = 6;
                           }

                           if (dat == 4 || dat == 5) {
                              double d8 = Player.field_70130_N + 1.0F;
                              double d9 = Player.field_70131_O;
                              Vec3 vec3 = Player.func_70676_i(1.0F);
                              double x = Player.field_70165_t + vec3.field_72450_a * d8;
                              double y = Player.field_70163_u + vec3.field_72448_b * d8 + Player.field_70131_O * 0.55F;
                              double z = Player.field_70161_v + vec3.field_72449_c * d8;
                              int spot = -1;
                              int checked = 0;

                              for (int i = (int)y; spot == -1 && checked < 3; i--) {
                                 if (!Player.field_70170_p.func_147439_a((int)x, i, (int)z).func_149739_a().toLowerCase().contains("air")) {
                                    spot = i + 1;
                                    y = spot;
                                    break;
                                 }

                                 checked++;
                              }

                              if (spot != -1) {
                                 var8 = new EntityEnergyAttJ3(dat, Player, type, dmg, (byte)tn, dmg1, cst, cost);
                              } else {
                                 wcbu = false;
                                 Player.func_145747_a(new ChatComponentText(JRMCoreH.cly + "Failed to create Wall!" + JRMCoreH.cly));
                              }
                           } else if (dat > 0) {
                              if (dat < 3) {
                                 ExtendedPlayer.get(Player).setHandEffect(dat);
                                 ExtendedPlayer.get(Player).setEffect_used(1);
                                 ExtendedPlayer.get(Player).setEffect_used2(dmg);
                              } else {
                                 float spd = speed * (1.0F + af * 0.05F);
                                 var8 = new EntityEnergyAttJ2(
                                    dat, Player, type, spd, dmg, effect, color, density, sincantation, sfire, smove, (byte)tn, dmg1, cst, cost
                                 );
                              }
                           } else {
                              var8 = new EntityEnergyAttJ(
                                 Player, type, speed, dmg, effect, color, density, sincantation, sfire, smove, (byte)tn, dmg1, cst, cost
                              );
                           }
                        } else {
                           Player.func_145747_a(
                              new ChatComponentText(
                                 JRMCoreH.cly
                                    + "You need to learn the "
                                    + JRMCoreH.clgd
                                    + JRMCoreH.trl("nc", JRMCoreH.NCSkillNames[1])
                                    + JRMCoreH.cly
                                    + " Skill!"
                              )
                           );
                        }

                        if (wcbu) {
                           byte P = JRMCoreH.getByte(Player, "jrmcPtch");
                           float ptch = P == 0 ? 1.0F : 0.8F + P * 0.006F;
                           Player.field_70170_p.func_72956_a(Player, "jinryuunarutoc:" + snd, 1.0F, density == 1 ? 1.0F : ptch);
                           Player.field_70170_p.func_72956_a(Player, "jinryuunarutoc:" + snd2, 1.0F, ptch);
                           if (!JRMCoreH.isInCreativeMode(Player)) {
                              JRMCoreH.setInt(curEn - cst, Player, "jrmcEnrgy");
                           }
                        }

                        if (var8 != null) {
                           Player.field_70170_p.func_72838_d(var8);
                        }
                     } else if (cst > curEn) {
                        Player.func_145747_a(new ChatComponentText(" Not Enough Chakra!").func_150255_a(JRMCoreH.CHAT_STYLE_YELLOW));
                     } else if (!may) {
                        Player.func_145747_a(
                           new ChatComponentText("You need to learn the " + JRMCoreH.clgd + JRMCoreH.trl("nc", JRMCoreH.techNCEffects[effe]) + " Affinity!")
                              .func_150255_a(JRMCoreH.CHAT_STYLE_YELLOW)
                        );
                     }
                  }
               }
            } else {
               byte curRel = JRMCoreH.getByte(Player, "jrmcRelease");
               if (curRel > 0) {
                  String[] tech = (d1 == 100 ? s : s).toString().split(";");
                  if (tech != null && tech.length > 9) {
                     String name = tech[0];
                     byte type = Byte.parseByte(tech[3]);
                     byte speed = Byte.parseByte(tech[4]);
                     int dam = Integer.parseInt(tech[5]);
                     byte effect = Byte.parseByte(tech[6]);
                     int cost = Integer.parseInt(tech[7]);
                     byte color = Byte.parseByte(tech[10]);
                     byte density = Byte.parseByte(tech[11]);
                     byte sincantation = 0;
                     byte sfire = 0;
                     byte smove = 0;
                     if (tech.length > 12) {
                        sincantation = Byte.parseByte(tech[12]);
                        sfire = Byte.parseByte(tech[13]);
                        smove = Byte.parseByte(tech[14]);
                     }

                     int typ = type;
                     int sped = speed;
                     int effe = effect;
                     double costP = JRMCoreH.round(JRMCoreH.techNCCostP[cost] * (curRel * 0.01F), 1);
                     int cst = (int)(costP * 0.01F * maxEnergy);
                     cst = cst < 1 ? 1 : cst;
                     int dmg1 = (int)(cst - cst * 0.25F * sped + (typ == 0 ? cst * 0.2F : 0.0F));
                     int dmg = dmg1;
                     int af = 0;
                     if (effe == 0 && afi > 0) {
                        may = true;
                        af = afi;
                     } else if (effe == 1 && awi > 0) {
                        may = true;
                        af = awi;
                     } else if (effe == 2 && ali > 0) {
                        may = true;
                        af = ali;
                     } else if (effe == 3 && aea > 0) {
                        may = true;
                        af = aea;
                     } else if (effe == 4 && awa > 0) {
                        may = true;
                        af = awa;
                     } else if (effe > 4) {
                        may = true;
                        af = ni;
                     }

                     if (cst < curEn && may) {
                        if (!JRMCoreH.isInCreativeMode(Player)) {
                           JRMCoreH.setInt(curEn - cst, Player, "jrmcEnrgy");
                        }

                        String snd = "";
                        String snd2 = "";
                        if (density == 1) {
                           if (JRMCoreH.techNCSndIncAff.length > effe) {
                              snd = JRMCoreH.techNCSndIncAff[effe];
                           }

                           if (JRMCoreH.techNCSndIncTyp.length > type) {
                              snd2 = JRMCoreH.techNCSndIncTyp[type];
                           }
                        }

                        if (density == 2 && JRMCoreH.techNCSndIncCls.length > density) {
                           snd2 = JRMCoreH.techNCSndIncCls[density];
                        }

                        if (density == 4) {
                           if (JRMCoreH.techNCSndIncAff.length > effe) {
                              snd = JRMCoreH.techNCSndIncAff[effe];
                           }

                           if (JRMCoreH.techNCSndIncCls.length > density) {
                              snd2 = JRMCoreH.techNCSndIncCls[density];
                           }
                        }

                        byte P = JRMCoreH.getByte(Player, "jrmcPtch");
                        float ptch = P == 0 ? 1.0F : 0.8F + P * 0.006F;
                        Player.field_70170_p.func_72956_a(Player, "jinryuunarutoc:NC2." + snd, 1.0F, ptch);
                        Player.field_70170_p.func_72956_a(Player, "jinryuunarutoc:NC2." + snd2, 1.0F, ptch);
                        if (density == 1) {
                           if (ni > 0) {
                              double d = dmg / 50.0;
                              dmg = (int)(d * 20.0 + d * (ni * 2.0 + af));
                              dmg = dmg < 1 ? 1 : dmg;
                              var8 = new EntityEnergyAttJ(
                                 Player, type, speed, dmg, effect, color, density, sincantation, sfire, smove, (byte)-1, dmg1, cst, cost
                              );
                              ((EntityEnergyAttJ)var8).setJutsuName(name);
                              Player.field_70170_p.func_72838_d(var8);
                           } else {
                              Player.func_145747_a(
                                 new ChatComponentText(
                                    JRMCoreH.cly
                                       + "You need to learn the "
                                       + JRMCoreH.clgd
                                       + JRMCoreH.trl("nc", JRMCoreH.NCSkillNames[1])
                                       + JRMCoreH.cly
                                       + " Skill!"
                                 )
                              );
                           }
                        }
                     } else if (cst > curEn) {
                        Player.func_145747_a(new ChatComponentText(JRMCoreH.cly + "Not Enough Chakra!"));
                     } else if (!may) {
                        Player.func_145747_a(
                           new ChatComponentText(
                              JRMCoreH.cly
                                 + "You need to learn the "
                                 + JRMCoreH.clgd
                                 + JRMCoreH.trl("nc", JRMCoreH.techNCEffects[effe])
                                 + JRMCoreH.cly
                                 + " Affinity Skill!"
                           )
                        );
                     }
                  }
               }
            }
         }
      }
   }

   public void handleNCdou(byte b, EntityPlayer p) {
      Side side = FMLCommonHandler.instance().getEffectiveSide();
      if (side == Side.SERVER) {
         byte cl = JRMCoreH.getByte(p, "jrmcClass");
         byte P = JRMCoreH.getByte(p, "jrmcPtch");
         if (P == 0) {
            float ptch = 1.0F;
         } else {
            float ptch = 0.8F + P * 0.006F;
         }

         String doujutsu = JRMCoreH.ncCSkls[cl];
         NBTTagCompound nbt = this.nbt(p, "pres");
         String s2 = nbt.func_74779_i("jrmcSSltY");
         byte st = nbt.func_74771_c("jrmcState");
         int st2 = nbt.func_74771_c("jrmcState2");
         if (b == 0 && st2 > 0) {
            nbt.func_74774_a("jrmcState2", (byte)0);
         } else if (b == 2) {
            boolean psk = JRMCoreH.PlyrSettingsB(nbt, 7);
            int skk = JRMCoreH.SklLvl(12, p);
            if (psk && skk > 0 && skk > st2 && JRMCoreH.TransGtsDmg.length - 1 > st2) {
               nbt.func_74774_a("jrmcState2", (byte)(st2 + 1));
               p.func_145747_a(
                  new ChatComponentText(
                     JRMCoreH.clgd + JRMCoreH.trl("nc", JRMCoreH.NCSkillNames[12]) + ": " + JRMCoreH.trl("nc", JRMCoreH.TransGtsNms[st2 + 1])
                  )
               );
            }
         } else if (s2 != null && cl != 0 && !s2.equals("pty")) {
            int clanSkillLvl = Integer.parseInt(s2.replaceAll(doujutsu, ""));
            if (st == 0 && b == 1) {
               if (JRMCoreH.NCRacialSkillAbilityNames[cl].length() > 0) {
                  p.field_70170_p.func_72956_a(p, "jinryuunarutoc:NC2." + JRMCoreH.NCRacialSkillAbilityNames[cl].toLowerCase(), 0.5F, 1.0F);
               }

               nbt.func_74774_a("jrmcState", (byte)(clanSkillLvl + 1));
            } else if (st > 0) {
               nbt.func_74774_a("jrmcState", (byte)0);
            }
         }
      }
   }

   public void send(EntityPlayerMP Player) {
      Player.func_145747_a(new ChatComponentText("Not enough Training Points!"));
   }

   public void handleNCchargesound(int ncchargesound, EntityPlayer Player) {
      Side side = FMLCommonHandler.instance().getEffectiveSide();
      if (side == Side.SERVER && ncchargesound == 1) {
         this.soundPowerUp(Player, "jinryuunarutoc:NC1.Chakra_Focus");
      }
   }

   public void handleNCjumpsound(int ncjumpsound, EntityPlayer Player) {
      Side side = FMLCommonHandler.instance().getEffectiveSide();
      if (side == Side.SERVER && ncjumpsound == 1) {
         this.soundPowerUp(Player, "jinryuunarutoc:NC1.Jump");
      }

      if (ncjumpsound == 2) {
         this.soundPowerUp(Player, "jinryuunarutoc:NC1.Rasengan_Ready");
      }

      if (ncjumpsound == 4) {
         this.soundPowerUp(Player, "jinryuunarutoc:NC1.Katon_Hous");
      }

      if (ncjumpsound == 100) {
         this.soundPowerUp(Player, "jinryuunarutoc:NC1.Focus_Chakra");
      }

      if (ncjumpsound == 101) {
         this.soundPowerUp(Player, "jinryuunarutoc:NC1.Seal_a");
      }

      if (ncjumpsound == 102) {
         this.soundPowerUp(Player, "jinryuunarutoc:NC1.Seal_b");
      }

      if (ncjumpsound == 103) {
         this.soundPowerUp(Player, "jinryuunarutoc:NC1.Seal_c");
      }

      if (ncjumpsound == 104) {
         this.soundPowerUp(Player, "jinryuunarutoc:NC1.Jutsu_Enabled");
      }
   }

   public void soundPowerUp(EntityPlayer var4, String var2) {
      var4.field_70170_p.func_72956_a(var4, var2, 0.5F, 1.0F);
   }

   public NBTTagCompound nbt(EntityPlayer p, String s) {
      NBTTagCompound nbt;
      if (s.contains("pres")) {
         if (!p.getEntityData().func_74764_b("PlayerPersisted")) {
            nbt = new NBTTagCompound();
            p.getEntityData().func_74782_a("PlayerPersisted", nbt);
         } else {
            nbt = p.getEntityData().func_74775_l("PlayerPersisted");
         }
      } else {
         nbt = p.getEntityData();
      }

      return nbt;
   }

   public void closeInventoryChange(EntityPlayerMP player) {
      player.field_71071_by.field_70459_e = false;
   }
}
