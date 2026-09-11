package JinRyuu.JRMCore.p;

import JinRyuu.FamilyC.EntityNPC;
import JinRyuu.FamilyC.FamilyCCharGui;
import JinRyuu.FamilyC.FamilyCConfig;
import JinRyuu.JRMCore.FamilyCH;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class FamilyCP implements IMessage {
   public static final int FORM_FAMILY = 0;
   public static final int PROPOSE_INVITE = 1;
   public static final int ADOPT_INVITE = 2;
   public static final int ACCEPT_PROPOSE = 3;
   public static final int ACCEPT_ADOPT = 4;
   public static final int DECLINE_BOTH_ADOPT_AND_PROPOSE = 5;
   public static final int LEAVE_DIVORCE = 6;
   public static final int DISINHERIT_FORCE_DIVORCE_UNADOPT_CHILD = 7;
   public static final int ACCEPT_PROCREATION = 8;
   public static final int DECLINE_PROCREATION = 9;
   public static final int OFFER_PROCREATION = 10;
   public static final int NPC_CHANGE_DATA = 20;
   public static final int PLAYER_PARENT_AND_CHILD_DATA_WHEN_GUI_OPENED = 21;
   public static final int NPC_CHANGE_NAME = 22;
   public static final int NPC_CHANGE_DNS = 23;
   int id;
   String txt;

   public FamilyCP() {
   }

   public FamilyCP(int id, String txt) {
      this.id = id;
      this.txt = txt;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeInt(this.id);
      ByteBufUtils.writeUTF8String(buffer, this.txt);
   }

   public void fromBytes(ByteBuf buffer) {
      this.id = buffer.readInt();
      this.txt = ByteBufUtils.readUTF8String(buffer);
   }

   public static class Handler extends BAmh<FamilyCP> {
      private static int dnsRaceSlcted;
      private static int dnsGenderSlcted;
      private static int dnsHairSlcted;
      private static int dnsHair2Slcted;
      private static int dnsColorSlcted;
      private static int dnsBreastSizeSlcted;
      private static int dnsBodyTypeSlcted;
      private static int dnsBodyColMainSlcted;
      private static int dnsBodyColSub1Slcted;
      private static int dnsBodyColSub2Slcted;
      private static int dnsBodyColSub3Slcted;
      private static int dnsFaceNoseSlcted;
      private static int dnsFaceMouthSlcted;
      private static int dnsEyesSlcted;
      private static int dnsEyeCol1Slcted;
      private static int dnsEyeCol2Slcted;
      private static String dns;

      public IMessage handleClientMessage(EntityPlayer p, FamilyCP msg, MessageContext ctx) {
         int id = msg.id;
         String txt = msg.txt;
         if (id == 20) {
            String[] dat = txt.split(":");
            int eid = Integer.parseInt(dat[0]);
            String follow = dat[1];
            String aggro = dat[2];
            String fid = dat[3];
            String d = dat[4];
            String m = dat[5];
            int cn = Integer.parseInt(dat[6]);
            Entity pl = p.field_70170_p.func_73045_a(eid);
            if (pl instanceof EntityNPC && pl != null) {
               FamilyCCharGui.dtcf = follow;
               FamilyCCharGui.dtca = aggro;
               FamilyCCharGui.dtcft = fid;
               FamilyCCharGui.dtcdad = d;
               FamilyCCharGui.dtcmom = m;
               FamilyCCharGui.inv = cn == 1 ? -1 : 0;
            }
         }

         if (id == 21) {
            FamilyCCharGui.children = txt;
         }

         if (id == 23) {
            int n = Integer.parseInt(txt);
            Entity pl = p.field_70170_p.func_73045_a(n);
            if (pl != null && pl instanceof EntityNPC) {
               EntityNPC npl = (EntityNPC)pl;
               npl.setNamUpdt(true);
            }
         }

         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, FamilyCP m, MessageContext ctx) {
         ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
         int id = m.id;
         String txt = m.txt;
         MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
         String y = JRMCoreH.cly;
         String g = JRMCoreH.clgd;
         String dns = JRMCoreH.getString(p, "jrmcDNS");
         if (id == 0) {
            String[] s = txt.split(",");
            String rid = s[0] + ",0";
            String d = txt + "!" + p.func_70005_c_() + ",e!0";
            String fid = JRMCoreH.getString(p, FamilyCH.FID);
            if (FamilyCH.rfi(server, s[0] + ",0").equals("0") && fid.length() < 2) {
               FamilyCH.wfi(server, d, rid, false);
               JRMCoreH.setString(rid, p, FamilyCH.FID);
               p.func_145747_a(new ChatComponentText(y + g + s[0] + y + " Family has been created!").func_150255_a(color));
            } else {
               p.func_145747_a(new ChatComponentText(y + "Family already exists, can't create another family!").func_150255_a(color));
            }
         }

         if (id == 1) {
            String fid = JRMCoreH.getString(p, FamilyCH.FID);
            String n = p.func_70005_c_();
            String[] s = fid.split(",");
            String fn = s[0];
            EntityPlayerMP pi = JRMCoreH.getPlayerForUsername(server, txt);
            if (pi != null) {
               String pfid = JRMCoreH.getString(pi, FamilyCH.FID);
               if (pfid.length() < 2) {
                  JRMCoreH.setString("", pi, FamilyCH.FIDa);
                  JRMCoreH.setString(p.func_70005_c_(), pi, FamilyCH.FIDi);
                  pi.func_145747_a(
                     new ChatComponentText(y + "" + g + n + y + " sent you a proposal" + (fn.length() > 1 ? " from the " + g + fn + y + " family" : "") + "!")
                        .func_150255_a(color)
                  );
               }
            }
         }

         if (id == 2) {
            String fid = JRMCoreH.getString(p, FamilyCH.FID);
            String n = p.func_70005_c_();
            String[] s = fid.split(",");
            String fn = s[0];
            EntityPlayerMP pi = JRMCoreH.getPlayerForUsername(server, txt);
            if (pi != null) {
               String pfid = JRMCoreH.getString(pi, FamilyCH.FID);
               if (pfid.length() < 2) {
                  JRMCoreH.setString("", pi, FamilyCH.FIDi);
                  JRMCoreH.setString(p.func_70005_c_(), pi, FamilyCH.FIDa);
                  String[] fns = fn.split(",");
                  pi.func_145747_a(
                     new ChatComponentText(
                           y
                              + ""
                              + g
                              + p.func_70005_c_()
                              + y
                              + " wants to adopt you"
                              + (fn.length() > 1 ? ". So you'd be apart of the " + g + fns[0] + y + " family" : "")
                              + "!"
                        )
                        .func_150255_a(color)
                  );
               }
            }
         }

         if (id == 3) {
            String prf = JRMCoreH.getString(p, FamilyCH.FIDi);
            EntityPlayerMP prfn = JRMCoreH.getPlayerForUsername(server, prf);
            String fidp = JRMCoreH.getString(p, FamilyCH.FID);
            if (prfn != null && fidp.length() < 2) {
               String fid = JRMCoreH.getString(prfn, FamilyCH.FID);
               String[] fida = fid.split(",");
               int ffd = Integer.parseInt(fida[1]);
               String fd = FamilyCH.rfi(server, fid);
               String[] famD = fd.split("!");
               String fn = ffd == 0 ? famD[0] : "";
               String[] fm = ffd == 0 ? famD[1].split(",") : famD[0].split(",");
               int fc = ffd == 0 ? Integer.parseInt(famD[2]) : 0;
               String nf = "";
               boolean nnf = false;
               String cd = "";

               for (int i = 0; i < fm.length; i++) {
                  String fh = fm[i];
                  String[] fh2 = fh.split(":");
                  if (i > 1 && fh2[0].equalsIgnoreCase(prf)) {
                     nnf = true;
                     cd = fh2[1];
                  }
               }

               if (nnf) {
                  String fD = prf + "," + p.func_70005_c_();
                  String ff = fida[0] + "," + cd;
                  JRMCoreH.setString(fid, prfn, FamilyCH.FIDo);
                  JRMCoreH.setString(ff, prfn, FamilyCH.FID);
                  JRMCoreH.setString(ff, p, FamilyCH.FID);
                  FamilyCH.wfi(server, fD, ff, false);
               } else {
                  for (int i = 0; i < fm.length; i++) {
                     String fh = fm[i];
                     if (fh.length() < 2 && !fh.equalsIgnoreCase(prf) && i < 2) {
                        nf = nf + "," + p.func_70005_c_();
                        JRMCoreH.setString(fid, p, FamilyCH.FID);
                        JRMCoreH.setString("", p, FamilyCH.FIDi);
                        JRMCoreH.setString("", p, FamilyCH.FIDa);
                     } else {
                        nf = nf + "," + fh;
                     }
                  }

                  nf = nf.substring(1);
                  String fD = ffd == 0 ? fn + "!" + nf + "!" + fc : nf;
                  FamilyCH.wfi(server, fD, fid, false);
               }

               String[] fns = fn.split(",");
               prfn.func_145747_a(
                  new ChatComponentText(
                        y
                           + "Proposal was accepted! You were married to "
                           + g
                           + p.func_70005_c_()
                           + y
                           + " and "
                           + y
                           + "joined the "
                           + g
                           + fns[0]
                           + y
                           + " family!"
                     )
                     .func_150255_a(color)
               );
               p.func_145747_a(
                  new ChatComponentText(y + "You have married " + g + prfn.func_70005_c_() + y + " and so you joined the " + g + fns[0] + y + " family!")
                     .func_150255_a(color)
               );
            } else {
               p.func_145747_a(new ChatComponentText(y + "Marriage failed because " + g + prf + y + " was not found!").func_150255_a(color));
            }

            JRMCoreH.setString("", p, FamilyCH.FIDi);
            JRMCoreH.setString("", p, FamilyCH.FIDa);
         }

         if (id == 4) {
            String arf = JRMCoreH.getString(p, FamilyCH.FIDa);
            EntityPlayerMP arfn = JRMCoreH.getPlayerForUsername(server, arf);
            String fidp = JRMCoreH.getString(p, FamilyCH.FID);
            if (arfn != null && fidp.length() < 2) {
               String fid = JRMCoreH.getString(arfn, FamilyCH.FID);
               String[] fida = fid.split(",");
               int ffd = Integer.parseInt(fida[1]);
               String fd = FamilyCH.rfi(server, fid);
               String[] famD = fd.split("!");
               String fn = ffd == 0 ? famD[0] : "";
               String[] fm = ffd == 0 ? famD[1].split(",") : famD[0].split(",");
               if (ffd == 0) {
                  Integer.parseInt(famD[2]);
               } else {
                  int fc = 0;
               }

               String nf = "";
               boolean nnf = false;
               String cd = "";

               for (int i = 0; i < fm.length; i++) {
                  String fh = fm[i];
                  String[] fh2 = fh.split(":");
                  if (i > 1 && fh2[0].equalsIgnoreCase(arf)) {
                     nnf = true;
                     cd = fh2[1];
                  }
               }

               String fid2 = fida[0] + ",0";
               String fd2 = FamilyCH.rfi(server, fid2);
               String[] famD2 = fd2.split("!");
               int var175 = Integer.parseInt(famD2[2]);
               var175++;
               if (nnf) {
                  nf = arf + ",e," + p.func_70005_c_() + ":" + var175;
                  fid2 = fida[0] + "," + cd;
                  JRMCoreH.setString(fid, arfn, FamilyCH.FIDo);
                  JRMCoreH.setString(fid2, arfn, FamilyCH.FID);
                  JRMCoreH.setString(fid2, p, FamilyCH.FID);
                  fd2 = nf;
                  FamilyCH.wfi(server, fd2, fid2, false);
               } else {
                  fid2 = ffd == 0 ? famD[1] : famD[0];
                  nf = fid2 + "," + p.func_70005_c_() + ":" + var175;
                  fd2 = ffd == 0 ? fn + "!" + nf + "!" + var175 : nf;
                  JRMCoreH.setString(fid, p, FamilyCH.FID);
                  FamilyCH.wfi(server, fd2, fid, false);
               }

               if (ffd != 0) {
                  fid = fida[0] + ",0";
                  fd = FamilyCH.rfi(server, fid);
                  famD = fd.split("!");
                  fn = famD[0];
                  fid2 = famD[1];
                  fd2 = fn + "!" + fid2 + "!" + var175;
                  FamilyCH.wfi(server, fd2, fid, false);
               }

               arfn.func_145747_a(
                  new ChatComponentText(y + "Adoption offer was accepted! " + g + p.func_70005_c_() + y + " is now part of the " + g + fida[0] + y + " family!")
                     .func_150255_a(color)
               );
               p.func_145747_a(
                  new ChatComponentText(
                        y + "You have been adopted by " + g + arfn.func_70005_c_() + y + " and you are now part of the " + g + fida[0] + y + " family!"
                     )
                     .func_150255_a(color)
               );
            } else {
               p.func_145747_a(new ChatComponentText(y + "Adoption failed because " + g + arf + y + " was not found!").func_150255_a(color));
            }

            JRMCoreH.setString("", p, FamilyCH.FIDa);
            JRMCoreH.setString("", p, FamilyCH.FIDi);
         }

         if (id == 5) {
            JRMCoreH.setString("", p, FamilyCH.FIDi);
            JRMCoreH.setString("", p, FamilyCH.FIDa);
         }

         if (id == 6) {
            String fid = JRMCoreH.getString(p, FamilyCH.FID);
            String n = p.func_70005_c_();
            if (fid.length() > 2) {
               String[] fida = fid.split(",");
               int ffd = Integer.parseInt(fida[1]);
               String fd = FamilyCH.rfi(server, fid);
               String[] famD = fd.split("!");
               String fn = ffd == 0 ? famD[0] : "";
               String[] fm = ffd == 0 ? famD[1].split(",") : famD[0].split(",");
               int fc = ffd == 0 ? Integer.parseInt(famD[2]) : 0;
               String nf = "";

               for (int i = 0; i < fm.length; i++) {
                  String fh = fm[i];
                  String[] fh2 = fh.split(":");
                  if (fh2[0].equalsIgnoreCase(p.func_70005_c_())) {
                     nf = nf + (i < 2 ? ",l" : "");
                  } else {
                     nf = nf + "," + fh;
                  }
               }

               nf = nf.substring(1);
               String fD = ffd == 0 ? fn + "!" + nf + "!" + fc : nf;
               FamilyCH.wfi(server, fD, fid, false);
               String fido = JRMCoreH.getString(p, FamilyCH.FIDo);
               if (fido.length() > 2) {
                  fida = fido.split(",");
                  ffd = Integer.parseInt(fida[1]);
                  String fdo = FamilyCH.rfi(server, fido);
                  famD = fdo.split("!");
                  fn = ffd == 0 ? famD[0] : "";
                  fm = ffd == 0 ? famD[1].split(",") : famD[0].split(",");
                  fc = ffd == 0 ? Integer.parseInt(famD[2]) : 0;
                  nf = "";

                  for (int i = 0; i < fm.length; i++) {
                     String fh = fm[i];
                     String[] fh2 = fh.split(":");
                     if (fh2[0].equalsIgnoreCase(p.func_70005_c_())) {
                        nf = nf + (i < 2 ? ",l" : "");
                        JRMCoreH.setString("0", p, FamilyCH.FIDo);
                     } else {
                        nf = nf + "," + fh;
                     }
                  }

                  nf = nf.substring(1);
                  fD = ffd == 0 ? fn + "!" + nf + "!" + fc : nf;
                  FamilyCH.wfi(server, fD, fido, false);
               }

               String[] fns = fn.split(",");
               p.func_145747_a(new ChatComponentText(y + "You have left the " + g + fns[0] + y + " family!").func_150255_a(color));
            }
         }

         if (id == 7) {
            String fid = JRMCoreH.getString(p, FamilyCH.FID);
            String n = txt;
            EntityPlayerMP pud = JRMCoreH.getPlayerForUsername(server, n);
            if (fid.length() > 2 && n.length() > 1) {
               String[] fida = fid.split(",");
               String fnam = fida[0];
               int ffd = Integer.parseInt(fida[1]);
               String mfd = FamilyCH.rfi(server, fnam + ",0");
               if (mfd.contains("!")) {
                  String[] mfD = mfd.split("!");
                  int mfDi = Integer.parseInt(mfD[2]);

                  for (int i = 0; i <= ffd; i++) {
                     String fid2 = fnam + "," + i;
                     String afm = FamilyCH.rfi(server, fid2);
                     if (afm.length() > 3) {
                        String[] famDa = afm.split("!");
                        String fn = i == 0 ? famDa[0] : "";
                        String[] fma = i == 0 ? famDa[1].split(",") : famDa[0].split(",");
                        int fc = i == 0 ? Integer.parseInt(famDa[2]) : 0;
                        String nf = "";

                        for (int i2 = 0; i2 < fma.length; i2++) {
                           String fh = fma[i2];
                           String[] fh2 = fh.split(":");
                           if (fh2[0].equalsIgnoreCase(n)) {
                              nf = nf + (i2 < 2 ? ",k" : "");
                              FamilyCH.wfmd(server, "1", n, false);
                           } else {
                              nf = nf + "," + fh;
                           }
                        }

                        nf = nf.substring(1);
                        String fD = i == 0 ? fn + "!" + nf + "!" + fc : nf;
                        FamilyCH.wfi(server, fD, fid2, false);
                     }
                  }
               }

               if (pud != null) {
                  pud.func_145747_a(
                     new ChatComponentText(y + "You have been removed from the " + g + fnam + y + " family by " + g + p.func_70005_c_() + y + "!")
                        .func_150255_a(color)
                  );
               }

               p.func_145747_a(new ChatComponentText(y + "You have removed " + g + n + y + " from the " + g + fnam + y + " family!").func_150255_a(color));
            } else {
               p.func_145747_a(new ChatComponentText(y + "Removing " + g + n + y + " failed!").func_150255_a(color));
            }
         }

         if (id == 8) {
            String prid = JRMCoreH.getString(p, FamilyCH.prID);
            EntityPlayerMP pt = JRMCoreH.getPlayerForUsername(server, prid.length() > 2 && !prid.contains(";") ? prid : "");
            if (pt != null && p != null) {
               String dnsTar = JRMCoreH.getString(pt, "jrmcDNS");
               int pa = gb(p, "jrmcAccept");
               int pta = gb(pt, "jrmcAccept");
               int pg = JRMCoreH.dnsGender(dns);
               int ptg = JRMCoreH.dnsGender(dnsTar);
               if ((pg == 0 && ptg == 1 || pg == 1 && ptg == 0) && pa == 1 && pta == 1) {
                  byte R1 = gb(p, "jrmcRace");
                  int H1B = JRMCoreH.dnsHairB(dns);
                  int H1F = JRMCoreH.dnsHairF(dns);
                  int H1C = JRMCoreH.dnsHairC(dns);
                  int B1 = JRMCoreH.dnsBreast(dns);
                  int S1T = JRMCoreH.dnsSkinT(dns);
                  int B1T = JRMCoreH.dnsBodyT(dns);
                  int B1CM = JRMCoreH.dnsBodyCM(dns);
                  int B1C1 = JRMCoreH.dnsBodyC1(dns);
                  int B1C2 = JRMCoreH.dnsBodyC2(dns);
                  int B1C3 = JRMCoreH.dnsBodyC3(dns);
                  int F1N = JRMCoreH.dnsFaceN(dns);
                  int F1M = JRMCoreH.dnsFaceM(dns);
                  int E1 = JRMCoreH.dnsEyes(dns);
                  int E1C1 = JRMCoreH.dnsEyeC1(dns);
                  int E1C2 = JRMCoreH.dnsEyeC2(dns);
                  String dnsH = JRMCoreH.getString(p, "jrmcDNSH");
                  byte R2 = gb(pt, "jrmcRace");
                  int H2B = JRMCoreH.dnsHairB(dnsTar);
                  int H2F = JRMCoreH.dnsHairF(dnsTar);
                  int H2C = JRMCoreH.dnsHairC(dnsTar);
                  int B2 = JRMCoreH.dnsBreast(dnsTar);
                  int S2T = JRMCoreH.dnsSkinT(dnsTar);
                  int B2T = JRMCoreH.dnsBodyT(dnsTar);
                  int B2CM = JRMCoreH.dnsBodyCM(dnsTar);
                  int B2C1 = JRMCoreH.dnsBodyC1(dnsTar);
                  int B2C2 = JRMCoreH.dnsBodyC2(dnsTar);
                  int B2C3 = JRMCoreH.dnsBodyC3(dnsTar);
                  int F2N = JRMCoreH.dnsFaceN(dnsTar);
                  int F2M = JRMCoreH.dnsFaceM(dnsTar);
                  int E2 = JRMCoreH.dnsEyes(dnsTar);
                  int E2C1 = JRMCoreH.dnsEyeC1(dnsTar);
                  int E2C2 = JRMCoreH.dnsEyeC2(dnsTar);
                  String dnsH2 = JRMCoreH.getString(pt, "jrmcDNSH");
                  if (FamilyCH.procWith(R1, R2)) {
                     byte r = (byte)FamilyCH.procTR(R1, R2);
                     Random ran = new Random();
                     dnsRaceSlcted = r;
                     dnsGenderSlcted = ran.nextInt(2);
                     int rid = ran.nextInt(3);
                     dnsHairSlcted = rid == 0 ? H1B : (rid == 1 ? H2B : ran.nextInt(JRMCoreH.Hairs.length));
                     dnsHair2Slcted = H1F;
                     rid = ran.nextInt(3);
                     dnsColorSlcted = rid == 0 ? H1C : (rid == 1 ? H2C : ran.nextInt(16777000));
                     dnsBreastSizeSlcted = ran.nextInt(9);
                     boolean p1p2 = false;
                     boolean p1 = false;
                     boolean p2 = false;
                     rid = ran.nextInt(3);
                     if (S1T == 1 && S2T == 1) {
                        p1p2 = true;
                        rid = rid == 0 ? B1T : (rid == 1 ? B2T : ran.nextInt(JRMCoreH.customSknLimits[r][0]));
                     } else if (S1T == 1) {
                        p1 = true;
                        rid = rid != 0 && rid != 1 ? ran.nextInt(JRMCoreH.customSknLimits[r][0]) : B1T;
                     } else if (S2T != 1) {
                        rid = ran.nextInt(JRMCoreH.customSknLimits[r][0]);
                     } else {
                        p2 = true;
                        rid = rid != 0 && rid != 1 ? ran.nextInt(JRMCoreH.customSknLimits[r][0]) : B2T;
                     }

                     dnsBodyTypeSlcted = rid;
                     rid = ran.nextInt(JRMCoreH.customSknLimitsBCP[r]);
                     int cls = JRMCoreH.defbodycols[rid][r].length;
                     rid = ran.nextInt(4);
                     dnsBodyColMainSlcted = cls < 1
                        ? 0
                        : (
                           rid != 0 || !p1p2 && !p1
                              ? (
                                 rid != 1 && (rid != 0 || p1) || !p1p2 && !p2
                                    ? (
                                       rid != 2 && (rid >= 2 || p1p2 || p1 || p2)
                                          ? ran.nextInt(16777000)
                                          : JRMCoreH.defbodycols[ran.nextInt(JRMCoreH.customSknLimitsBCP[r])][r][0]
                                    )
                                    : B2CM
                              )
                              : B1CM
                        );
                     rid = ran.nextInt(4);
                     dnsBodyColSub1Slcted = cls < 2
                        ? 0
                        : (
                           rid != 0 || !p1p2 && !p1
                              ? (
                                 rid != 1 && (rid != 0 || p1) || !p1p2 && !p2
                                    ? (
                                       rid != 2 && (rid >= 2 || p1p2 || p1 || p2)
                                          ? ran.nextInt(16777000)
                                          : JRMCoreH.defbodycols[ran.nextInt(JRMCoreH.customSknLimitsBCP[r])][r][1]
                                    )
                                    : B2C1
                              )
                              : B1C1
                        );
                     rid = ran.nextInt(4);
                     dnsBodyColSub2Slcted = cls < 3
                        ? 0
                        : (
                           rid != 0 || !p1p2 && !p1
                              ? (
                                 rid != 1 && (rid != 0 || p1) || !p1p2 && !p2
                                    ? (
                                       rid != 2 && (rid >= 2 || p1p2 || p1 || p2)
                                          ? ran.nextInt(16777000)
                                          : JRMCoreH.defbodycols[ran.nextInt(JRMCoreH.customSknLimitsBCP[r])][r][2]
                                    )
                                    : B2C2
                              )
                              : B1C2
                        );
                     rid = ran.nextInt(4);
                     dnsBodyColSub3Slcted = cls < 4
                        ? 0
                        : (
                           rid != 0 || !p1p2 && !p1
                              ? (
                                 rid != 1 && (rid != 0 || p1) || !p1p2 && !p2
                                    ? (
                                       rid != 2 && (rid >= 2 || p1p2 || p1 || p2)
                                          ? ran.nextInt(16777000)
                                          : JRMCoreH.defbodycols[ran.nextInt(JRMCoreH.customSknLimitsBCP[r])][r][3]
                                    )
                                    : B2C3
                              )
                              : B1C3
                        );
                     rid = ran.nextInt(3);
                     dnsFaceNoseSlcted = rid != 0 || !p1p2 && !p1
                        ? (rid != 1 && (rid != 0 || p1) || !p1p2 && !p2 ? ran.nextInt(JRMCoreH.customSknLimits[r][2]) : F2N)
                        : F1N;
                     rid = ran.nextInt(3);
                     dnsFaceMouthSlcted = rid != 0 || !p1p2 && !p1
                        ? (rid != 1 && (rid != 0 || p1) || !p1p2 && !p2 ? ran.nextInt(JRMCoreH.customSknLimits[r][3]) : F2M)
                        : F1M;
                     rid = ran.nextInt(3);
                     dnsEyesSlcted = rid != 0 || !p1p2 && !p1
                        ? (rid != 1 && (rid != 0 || p1) || !p1p2 && !p2 ? ran.nextInt(JRMCoreH.customSknLimits[r][4]) : E2)
                        : E1;
                     rid = ran.nextInt(4);
                     int rid2 = ran.nextInt(5);
                     dnsEyeCol1Slcted = rid != 0 || !p1p2 && !p1
                        ? (
                           rid != 1 && (rid != 0 || p1) || !p1p2 && !p2
                              ? (
                                 rid != 2 && (rid >= 2 || p1p2 || p1 || p2)
                                    ? ran.nextInt(16777000)
                                    : JRMCoreH.defeyecols[ran.nextInt(JRMCoreH.defeyecols.length)][r]
                              )
                              : E2C1
                        )
                        : E1C1;
                     rid = ran.nextInt(4);
                     dnsEyeCol2Slcted = rid2 != 0
                        ? dnsEyeCol1Slcted
                        : (
                           rid != 0 || !p1p2 && !p1
                              ? (
                                 rid != 1 && (rid != 0 || p1) || !p1p2 && !p2
                                    ? (
                                       rid != 2 && (rid >= 2 || p1p2 || p1 || p2)
                                          ? ran.nextInt(16777000)
                                          : JRMCoreH.defeyecols[ran.nextInt(JRMCoreH.defeyecols.length)][r]
                                    )
                                    : E2C1
                              )
                              : E1C1
                        );
                     setdns();
                     String dnsHdef = JRMCoreH.defHairPrsts[ran.nextInt(JRMCoreH.defHairPrsts.length)];
                     rid = ran.nextInt(2);
                     String dnsHc = dnsHairSlcted != 12
                        ? "0"
                        : (H1B == 12 && H2B == 12 ? (rid == 0 ? dnsH : dnsH2) : (H1B == 12 ? dnsH : (H2B == 12 ? dnsH2 : dnsHdef)));
                     EntityPlayer e = (EntityPlayer)(ptg == 1 ? pt : (pg == 1 ? p : p));
                     EntityPlayer e2 = (EntityPlayer)(ptg == 0 ? pt : (pg == 0 ? p : pt));
                     JRMCoreH.setString(
                        FamilyCP.Handler.dns + ";" + e.func_70005_c_() + ";" + e2.func_70005_c_() + ";" + txt + ";" + FamilyCConfig.pt * 120 + ";" + dnsHc,
                        e,
                        FamilyCH.prID
                     );
                     JRMCoreH.setString("f", e2, FamilyCH.prID);
                     e2.func_145747_a(new ChatComponentText(y + "" + g + e.func_70005_c_() + y + " is now pregnant, thanks to you!").func_150255_a(color));
                     e.func_145747_a(
                        new ChatComponentText(y + "You've become pregnant! The father is " + g + e2.func_70005_c_() + y + ".").func_150255_a(color)
                     );
                  }
               }
            } else {
               JRMCoreH.setString("n", p, FamilyCH.prID);
            }
         }

         if (id == 9) {
            String prid = JRMCoreH.getString(p, FamilyCH.prID);
            EntityPlayerMP po = JRMCoreH.getPlayerForUsername(server, prid);
            if (po != null) {
               po.func_145747_a(new ChatComponentText(y + "" + p.func_70005_c_() + " has declined your procreation offer!").func_150255_a(color));
            }

            JRMCoreH.setString("d", p, FamilyCH.prID);
         }

         if (id == 10) {
            EntityPlayerMP po = JRMCoreH.getPlayerForUsername(server, txt);
            String dnsTar = JRMCoreH.getString(po, "jrmcDNS");
            int pg = JRMCoreH.dnsGender(dns);
            int pog = JRMCoreH.dnsGender(dnsTar);
            byte R1 = gb(p, "jrmcRace");
            byte R2 = gb(po, "jrmcRace");
            if (FamilyCConfig.mc == 0 || FamilyCConfig.dcr) {
               p.func_145747_a(new ChatComponentText(y + "Procreation is disabled by the server!").func_150255_a(color));
            } else if (FamilyCH.procWith(R1, R2) && po != null && (pg == 0 && pog == 1 || pg == 1 && pog == 0)) {
               boolean allow = true;
               String p1 = FamilyCH.rpfd(server, txt);
               if (p1.contains(";")) {
                  String[] p1d = p1.split(";");
                  if (p1d.length >= FamilyCConfig.mc) {
                     p.func_145747_a(new ChatComponentText(y + "" + g + txt + y + " has already " + FamilyCConfig.mc + " children!").func_150255_a(color));
                     allow = false;
                  } else {
                     String p2 = FamilyCH.rpfd(server, p.func_70005_c_());
                     if (p2.contains(";")) {
                        String[] p2d = p2.split(";");
                        if (p2d.length >= FamilyCConfig.mc) {
                           p.func_145747_a(
                              new ChatComponentText(y + "" + g + p.func_70005_c_() + y + " has already " + FamilyCConfig.mc + " children!")
                                 .func_150255_a(color)
                           );
                           allow = false;
                        }
                     }
                  }
               }

               if (allow) {
                  String prid = JRMCoreH.getString((EntityPlayer)(pg == 1 ? p : po), FamilyCH.prID);
                  if (prid.contains(";") && !prid.equals("") && prid != "") {
                     p.func_145747_a(new ChatComponentText(y + (pg == 1 ? "You are" : g + txt + y + " is") + " already pregnant!").func_150255_a(color));
                  } else {
                     JRMCoreH.setString(p.func_70005_c_(), po, FamilyCH.prID);
                     p.func_145747_a(new ChatComponentText(y + "A procreation offer has been sent!").func_150255_a(color));
                     po.func_145747_a(
                        new ChatComponentText(y + "You've recived a procreation offer from " + g + p.func_70005_c_() + y + "!").func_150255_a(color)
                     );
                  }
               }
            }
         }

         if (id == 20) {
            String[] dat = txt.split(":");
            int eid = Integer.parseInt(dat[0]);
            int follow = Integer.parseInt(dat[1]);
            boolean aggro = Integer.parseInt(dat[2]) == 1;
            int fid = Integer.parseInt(dat[3]);
            int drp = Integer.parseInt(dat[4]);
            Entity pl = p.field_70170_p.func_73045_a(eid);
            if (pl != null && pl instanceof EntityNPC) {
               EntityNPC npl = (EntityNPC)pl;
               if (p.func_70005_c_().equalsIgnoreCase(npl.getDad()) || p.func_70005_c_().equalsIgnoreCase(npl.getMom())) {
                  npl.setFollow(follow);
                  npl.setAggr(aggro);
                  npl.setFollowTarget(fid);
                  if (drp == 1) {
                     ((EntityNPC)pl).func_82160_b(true, 0);
                  }
               }
            }
         }

         if (id == 21) {
            boolean allow = true;
            String p1 = FamilyCH.rpfd(server, p.func_70005_c_());
            String[] p1d = p1.split(";");
            String c = "";

            for (int i = 0; i < p1d.length; i++) {
               String[] d = p1d[i].split(":");
               c = c + ";" + FamilyCH.rcfd(server, d[0]) + ":" + FamilyCH.rcpd(server, d[0]);
            }

            c = c.substring(1);
            if (c.length() > 2) {
               FamilyCH.jfcd(21, c, p);
            }
         }

         if (id == 22) {
            String[] dat = txt.split(":");
            int eid = Integer.parseInt(dat[0]);
            String n = dat[1];
            Entity pl = p.field_70170_p.func_73045_a(eid);
            if (pl != null && pl instanceof EntityNPC) {
               EntityNPC entityNPC = (EntityNPC)pl;
               if (p.func_70005_c_().equalsIgnoreCase(entityNPC.getDad()) || p.func_70005_c_().equalsIgnoreCase(entityNPC.getMom())) {
                  entityNPC.setCnam((byte)0);
                  entityNPC.setNam(n);
                  entityNPC.setNamUpdt(true);
                  String d = FamilyCH.rcfd(server, entityNPC.getCid() + "");
                  String[] prt = d.split(":");
                  FamilyCH.wcfd(server, prt[1] + ":" + prt[2] + ":" + n, entityNPC.getCid(), false);
                  FamilyCH.jfcd(23, entityNPC.func_145782_y() + "", p);
               }
            }
         }

         if (id == 23) {
            String[] dat = txt.split(":");
            int eid = Integer.parseInt(dat[0]);
            String type = dat[1];
            String dnsNPC = dat[2];
            Entity entity = p.field_70170_p.func_73045_a(eid);
            if (entity != null && entity instanceof EntityNPC) {
               EntityNPC entityNPC = (EntityNPC)entity;
               if (p.func_70005_c_().equalsIgnoreCase(entityNPC.getDad()) || p.func_70005_c_().equalsIgnoreCase(entityNPC.getMom())) {
                  if (type.equals("dns")) {
                     entityNPC.setDNS(dnsNPC);
                  }

                  if (type.equals("dnsH")) {
                     entityNPC.setDNSH(dnsNPC);
                  }
               }
            }
         }

         return null;
      }

      private static String ntl(int i) {
         return JRMCoreH.numToLet(i);
      }

      private static String ntl5(int i) {
         return JRMCoreH.numToLet5(i);
      }

      private static byte gb(EntityPlayer p, String s) {
         return JRMCoreH.getByte(p, s);
      }

      private static int gi(EntityPlayer p, String s) {
         return JRMCoreH.getInt(p, s);
      }

      public static void setdns() {
         String R = ntl(dnsRaceSlcted);
         String G = dnsGenderSlcted + "";
         String H1 = ntl(dnsHairSlcted);
         String H2 = ntl(dnsHair2Slcted);
         String HC = ntl5(dnsColorSlcted);
         String BS = dnsBreastSizeSlcted + "";
         String ST = "1";
         String BT = ntl(dnsBodyTypeSlcted);
         String BCM = ntl5(dnsBodyColMainSlcted);
         String BC1 = ntl5(dnsBodyColSub1Slcted);
         String BC2 = ntl5(dnsBodyColSub2Slcted);
         String BC3 = ntl5(dnsBodyColSub3Slcted);
         String FN = ntl(dnsFaceNoseSlcted);
         String FM = ntl(dnsFaceMouthSlcted);
         String ET = ntl(dnsEyesSlcted);
         String EC1 = ntl5(dnsEyeCol1Slcted);
         String EC2 = ntl5(dnsEyeCol2Slcted);
         dns = R + G + H1 + H2 + HC + BS + ST + BT + BCM + BC1 + BC2 + BC3 + FN + FM + ET + EC1 + EC2;
      }
   }
}
