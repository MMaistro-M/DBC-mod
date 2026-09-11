package JinRyuu.JRMCore.i;

import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHDBC;
import JinRyuu.JRMCore.JRMCoreM;
import JinRyuu.JRMCore.JRMCoreMsn;
import JinRyuu.JRMCore.JRMCoreMsnBundle;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.entity.EntityEng;
import com.google.common.collect.Lists;
import cpw.mods.fml.common.FMLCommonHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import org.apache.commons.lang3.math.NumberUtils;

public class ExtendedPlayer implements IExtendedEntityProperties {
   public static final String EXT_PROP_NAME = "JRMCEP";
   private final String extra_data_base = "0;0;0f;0;0;0;0;0;0;0;0;0;";
   public static final int BLOCKING = 20;
   public static final int OTHERCODE = 21;
   public static final int HAIRCODE = 22;
   public static final int KISHOTNOW = 0;
   public static final int KISHOTCOL = 1;
   public static final int KISHOTSI = 2;
   public static final int KISHOTPART = 3;
   public static final int HANDEFFECT = 4;
   public static final int HANDEFFECT2 = 5;
   public static final int HANDEFFECT3 = 6;
   public static final int UIANIMATION = 7;
   public static final int UIANIMATION_ID = 9;
   public static final int KISHOOTANIM = 8;
   public static final int KISHOOTANIM_ON = 10;
   public static final int GOD_OF_DESTRUCTION_ON = 11;
   private final EntityPlayer player;
   private int saga = -1;
   private int side = -1;
   public final InventoryCustomPlayer inventory = new InventoryCustomPlayer();
   private int blocking;
   private String haircode;
   private String extracode;
   private final int extra_data_sum = 12;
   private final byte HEALTH = 0;
   private final byte ENERGY = 1;
   private final byte STAMINA = 2;
   private int hairCheckDim = -1;
   private boolean MRC = true;

   public ExtendedPlayer(EntityPlayer player) {
      this.player = player;
      this.blocking = 0;
      this.haircode = "";
      this.extracode = "0;0;0f;0;0;0;0;0;0;0;0;0;";
      this.player.func_70096_w().func_75682_a(JRMCoreConfig.ExtendedPlayerBlockID, this.blocking);
      this.player.func_70096_w().func_75682_a(JRMCoreConfig.ExtendedPlayerOtherID, this.extracode);
      this.player.func_70096_w().func_75682_a(JRMCoreConfig.ExtendedPlayerHairID, this.haircode);
   }

   public static final void register(EntityPlayer player) {
      player.registerExtendedProperties("JRMCEP", new ExtendedPlayer(player));
   }

   public static final ExtendedPlayer get(EntityPlayer player) {
      return (ExtendedPlayer)player.getExtendedProperties("JRMCEP");
   }

   public void copy(ExtendedPlayer props) {
      this.inventory.copy(props.inventory);
      this.blocking = props.blocking;
      this.haircode = "";
      this.extracode = "0;0;0f;0;0;0;0;0;0;0;0;0;";
   }

   public final void saveNBTData(NBTTagCompound compound) {
      NBTTagCompound properties = new NBTTagCompound();
      this.inventory.writeToNBT(properties);
      properties.func_74768_a("blocking", this.player.func_70096_w().func_75679_c(JRMCoreConfig.ExtendedPlayerBlockID));
      properties.func_74778_a("haircode", this.player.func_70096_w().func_75681_e(JRMCoreConfig.ExtendedPlayerHairID));
      properties.func_74778_a("extracode", this.player.func_70096_w().func_75681_e(JRMCoreConfig.ExtendedPlayerOtherID));
      compound.func_74782_a("JRMCEP", properties);
   }

   public final void loadNBTData(NBTTagCompound compound) {
      NBTTagCompound properties = (NBTTagCompound)compound.func_74781_a("JRMCEP");
      this.inventory.readFromNBT(properties);
      this.player.func_70096_w().func_75692_b(JRMCoreConfig.ExtendedPlayerBlockID, properties.func_74762_e("blocking"));
      this.player.func_70096_w().func_75692_b(JRMCoreConfig.ExtendedPlayerHairID, properties.func_74779_i("haircode"));
      if (properties.func_74779_i("extracode").split(";").length == 12) {
         this.player.func_70096_w().func_75692_b(JRMCoreConfig.ExtendedPlayerOtherID, properties.func_74779_i("extracode"));
      } else {
         this.player.func_70096_w().func_75692_b(JRMCoreConfig.ExtendedPlayerOtherID, "0;0;0f;0;0;0;0;0;0;0;0;0;");
      }
   }

   public void init(Entity entity, World world) {
   }

   public void onUpdate() {
      if (this.getUIAnim() > 0) {
         this.setUIAnim(this.getUIAnim() - 1);
      } else if (this.getUIAnim() < 0) {
         this.setUIAnim(this.getUIAnim() + 1);
      }

      if (this.getUIAnim() == 0) {
         this.setUIAnimID(0);
      }

      if (!this.player.field_70170_p.field_72995_K) {
         if (JRMCoreH.updateEveryXTick(this.player.field_70173_aa, 10)) {
            String ex = "";
            if (this.hairCheckDim != this.player.field_71093_bK) {
               this.hairCheckDim = this.player.field_71093_bK;
               ex = "0";
            }

            this.haircode = JRMCoreH.getString(this.player, "jrmcDNSH") + ex;
            this.setHairCode(this.haircode);
         }

         if (JRMCoreH.updateEveryXTick(this.player.field_70173_aa, JRMCoreConfig.plUpd)) {
            NBTTagCompound nbt = JRMCoreH.nbt(this.player, "pres");
            String msd = nbt.func_74779_i("JRMCmissionSync");
            String msdV = nbt.func_74779_i("JRMCmissionSyncVers");
            int syncDaam = 0;
            if (msd.length() > 3 && JRMCoreM.missions != null) {
               String msdO = msd;
               String msdVO = msdV;
               new HashMap();
               int pw = nbt.func_74771_c("jrmcPwrtyp");
               int rc = nbt.func_74771_c("jrmcRace");
               int cl = nbt.func_74771_c("jrmcClass");
               int st = nbt.func_74771_c("jrmcState");
               ArrayList<EntityPlayer> ps = JRMCoreM.prog(this.player, true);
               int g = ps.size();
               syncDaam = JRMCoreM.getSydaAmount(msd);

               for (int i = 0; i < syncDaam; i++) {
                  String sid = JRMCoreM.getMda_Series(msd, i);
                  int msnToSendID = JRMCoreM.getMda_Mission(msd, i);
                  ps = JRMCoreM.prog(this.player, true, sid, msnToSendID);
                  String seriesID = sid;
                  JRMCoreMsnBundle M = JRMCoreM.missions.get(seriesID);
                  if (M != null) {
                     List<JRMCoreMsn> msnl = M.getMissions();
                     String[] sydaV = JRMCoreM.getMda(msdV, seriesID);
                     int rp = Integer.parseInt(JRMCoreM.getSydaV(sydaV, 2));
                     rp = rp > 0 ? rp - 1 : rp;
                     String cc = JRMCoreM.getSydaV(sydaV, 3);
                     String ccv = JRMCoreM.getSydaV(sydaV, 4);
                     msdV = JRMCoreM.setSydaV(msdV, seriesID, M.getVersion(), "" + rp, cc, ccv);
                     if (rp >= 0) {
                        boolean reset = false;
                        if (this.MRC) {
                           sydaV = JRMCoreM.getMda(msdV, seriesID);
                           if (sydaV.length > 1) {
                              reset = !M.getVersion().equalsIgnoreCase(sydaV[1]);
                           }

                           this.MRC = false;
                        }

                        for (int j = 0; j < msnl.size(); j++) {
                           JRMCoreMsn msn = msnl.get(j);
                           if ((
                                 !reset
                                    || !JRMCoreM.resetMsnBndl(true, reset, msn, msnToSendID, pw, rc, cl, msd, msdV, msdO, msdVO, seriesID, nbt, M, this.player)
                              )
                              && msn.getId() == msnToSendID) {
                              ArrayList<String> o = msn.getObjectives(pw, rc, cl);
                              int size = o.size();
                              boolean comp = JRMCoreM.getMda_ObjComp_all(o, JRMCoreM.getMda(msd, seriesID), g);
                              ArrayList<Boolean> arl = Lists.newArrayList();
                              boolean kl = false;

                              for (int k = 0; k < o.size(); k++) {
                                 String os = o.get(k);
                                 if (os != null && os.length() > 2) {
                                    String t = JRMCoreM.getMCo_type(os);
                                    String d1 = JRMCoreM.getMCo_data(os, "N");
                                    String dt = JRMCoreM.getMCo_data(os, "T");
                                    if (comp || !t.equalsIgnoreCase("kill") && !t.equalsIgnoreCase("killsame")) {
                                       if (!t.equalsIgnoreCase("biome") && !t.equalsIgnoreCase("biome2")) {
                                          if (!t.equalsIgnoreCase("dim") && !t.equalsIgnoreCase("dim2")) {
                                             if (t.equalsIgnoreCase("item")) {
                                                String[] d2 = d1.split("::");
                                                Item im = JRMCoreH.getItemByText(d2.length > 1 ? d2[0] : d1);
                                                ItemStack is = im != null ? new ItemStack(im, 1, d2.length > 1 ? Integer.parseInt(d2[1]) : 0) : null;
                                                String en = is != null ? is.func_77977_a() : "ERROR";
                                                int c = 0;

                                                for (EntityPlayer player : ps) {
                                                   for (int l = 0; l < player.field_71071_by.field_70462_a.length; l++) {
                                                      if (player.field_71071_by.field_70462_a[l] != null
                                                         && player.field_71071_by.field_70462_a[l].func_77977_a().equalsIgnoreCase(en)) {
                                                         c += player.field_71071_by.field_70462_a[l].field_77994_a;
                                                      }
                                                   }
                                                }

                                                msd = JRMCoreM.setSyda(msd, seriesID, msnToSendID, size, k, "" + c);
                                             } else if (t.equalsIgnoreCase("state")) {
                                                msd = JRMCoreM.setSyda(msd, seriesID, msnToSendID, size, k, JRMCoreH.TransNmsM[rc][st]);
                                             } else if (t.equalsIgnoreCase("lvl")) {
                                                int b = 0;

                                                for (EntityPlayer player : ps) {
                                                   int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(player);
                                                   int a = JRMCoreH.getPlayerLevel(PlyrAttrbts);
                                                   b = b != 0 && a >= b ? b : a;
                                                }

                                                msd = JRMCoreM.setSyda(msd, seriesID, msnToSendID, size, k, "" + b);
                                             }
                                          } else {
                                             boolean b = false;

                                             for (EntityPlayer player : ps) {
                                                b = player.field_70170_p.field_73011_w.func_80007_l().equalsIgnoreCase(d1)
                                                   || NumberUtils.isNumber(d1) && player.field_71093_bK == Integer.parseInt(d1);
                                                if (!b) {
                                                   break;
                                                }
                                             }

                                             msd = JRMCoreM.setSyda(msd, seriesID, msnToSendID, size, k, b ? "1" : "0");
                                          }
                                       } else {
                                          boolean b = false;

                                          for (EntityPlayer player : ps) {
                                             b = player.field_70170_p
                                                .func_72807_a((int)player.field_70165_t, (int)player.field_70161_v)
                                                .field_76791_y
                                                .equalsIgnoreCase(d1);
                                             if (!b) {
                                                break;
                                             }
                                          }

                                          msd = JRMCoreM.setSyda(msd, seriesID, msnToSendID, size, k, b ? "1" : "0");
                                       }
                                    } else {
                                       int n = 128;

                                       for (EntityPlayer player : ps) {
                                          EntityPlayer p = player;
                                          AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(
                                             p.field_70165_t - n, 0.0, p.field_70161_v - n, p.field_70165_t + n, 255.0, p.field_70161_v + n
                                          );
                                          if (EntityList.field_75625_b.get(d1) != null) {
                                             List l = p.field_70170_p.func_72872_a((Class)EntityList.field_75625_b.get(d1), aabb);
                                             boolean nokl = l.isEmpty();
                                             if (dt.length() > 1 && nokl) {
                                                String[] ar = JRMCoreM.getMCo_TranSplit(dt);

                                                for (int m = 0; m < ar.length; m++) {
                                                   String[] aamt = ar[m].split("\\|");
                                                   aabb = AxisAlignedBB.func_72330_a(
                                                      p.field_70165_t - n, 0.0, p.field_70161_v - n, p.field_70165_t + n, 255.0, p.field_70161_v + n
                                                   );
                                                   if (EntityList.field_75625_b.get(aamt[0]) != null) {
                                                      l = p.field_70170_p.func_72872_a((Class)EntityList.field_75625_b.get(aamt[0]), aabb);
                                                      nokl = l.isEmpty();
                                                      if (!nokl) {
                                                         break;
                                                      }
                                                   }
                                                }
                                             }

                                             arl.add(nokl);
                                          }
                                       }
                                    }
                                 }
                              }

                              if (!arl.isEmpty()) {
                                 boolean strt = true;

                                 for (int l = 0; l < arl.size(); l++) {
                                    boolean b = arl.get(l);
                                    if (!b) {
                                       strt = false;
                                       break;
                                    }
                                 }

                                 if (strt) {
                                    msd = JRMCoreM.setSyda(msd, seriesID, msnToSendID, size, JRMCoreM.SYNC_COND_data_REV(2), "0");
                                 }
                              }

                              if (!msdO.equalsIgnoreCase(msd)) {
                                 nbt.func_74778_a("JRMCmissionSync", msd);
                              }
                              break;
                           }
                        }

                        if (!msdVO.equalsIgnoreCase(msdV)) {
                           nbt.func_74778_a("JRMCmissionSyncVers", msdV);
                        }
                     }
                  }
               }
            }
         }

         if (JRMCoreH.DBC()) {
            long worldTime = FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(0).func_72820_D() % 24000L;
            if (worldTime == 1L) {
               JRMCoreH.setInt(0, this.player, "jrmcTPlimit");
               JRMCoreH.setInt(0, this.player, "jrmcTPlimit2");
               JRMCoreH.setInt(0, this.player, "DBCSenzu");
               byte tm = JRMCoreH.getByte(this.player, "jrmcTlmd");
               if (tm == 4) {
                  Random ran = new Random();
                  int r = ran.nextInt(3);
                  if (r == 0) {
                     JRMCoreH.setByte((byte)0, this.player, "jrmcTlmd");
                  }
               }
            }

            if (this.player.field_70170_p.func_147439_a((int)this.player.field_70165_t, (int)this.player.field_70163_u, (int)this.player.field_70161_v)
                  == JRMCoreHDBC.getMedBlock()
               && JRMCoreH.updateEveryXTick(this.player.field_70173_aa, JRMCoreHDBC.DBCgetConfighPodUpd())) {
               int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(this.player);
               this.player.func_70050_g(300);
               byte pwr = JRMCoreH.getByte(this.player, "jrmcPwrtyp");
               byte rc = JRMCoreH.getByte(this.player, "jrmcRace");
               byte cls = JRMCoreH.getByte(this.player, "jrmcClass");
               int maxBody = JRMCoreH.stat(this.player, 2, pwr, 2, PlyrAttrbts[2], rc, cls, 0.0F);
               int curBody = JRMCoreH.getInt(this.player, "jrmcBdy");
               int maxEnergy = JRMCoreH.stat(this.player, 5, pwr, 5, PlyrAttrbts[5], rc, cls, JRMCoreH.SklLvl_KiBs(this.player, pwr));
               int curEnergy = JRMCoreH.getInt(this.player, "jrmcEnrgy");
               int maxStam = JRMCoreH.stat(this.player, 2, pwr, 3, PlyrAttrbts[2], rc, cls, 0.0F);
               int curStam = JRMCoreH.getInt(this.player, "jrmcStamina");
               float damage = 20.0F - this.player.func_110143_aJ();
               if (curBody - damage > 0.0F) {
                  this.player.func_70606_j(this.player.func_110143_aJ() + damage);
               }

               if (curBody < maxBody) {
                  float body;
                  if (JRMCoreHDBC.DBCgetConfighPodPerc(0)) {
                     body = curBody + JRMCoreHDBC.DBCgetConfighPodRate(0) / 100.0F * maxBody;
                  } else {
                     body = curBody + JRMCoreHDBC.DBCgetConfighPodRate(0) * 3;
                  }

                  JRMCoreH.setInt(body > maxBody ? maxBody : body, this.player, "jrmcBdy");
               }

               if (curEnergy < maxEnergy) {
                  float energy;
                  if (JRMCoreHDBC.DBCgetConfighPodPerc(1)) {
                     energy = curEnergy + JRMCoreHDBC.DBCgetConfighPodRate(1) / 100.0F * maxEnergy;
                  } else {
                     energy = curEnergy + JRMCoreHDBC.DBCgetConfighPodRate(1) * 3;
                  }

                  JRMCoreH.setInt(energy > maxEnergy ? maxEnergy : energy, this.player, "jrmcEnrgy");
               }

               if (curStam < maxStam) {
                  float stam;
                  if (JRMCoreHDBC.DBCgetConfighPodPerc(2)) {
                     stam = curStam + JRMCoreHDBC.DBCgetConfighPodRate(2) / 100.0F * maxStam;
                  } else {
                     stam = curStam + JRMCoreHDBC.DBCgetConfighPodRate(2) * 3;
                  }

                  JRMCoreH.setInt(stam > maxStam ? maxStam : stam, this.player, "jrmcStamina");
               }

               if (this.player.func_71024_bL().func_75121_c()) {
                  this.player.func_71024_bL().func_75122_a(1, 0.5F);
               }
            }

            if (this.player.field_71093_bK == 0) {
               JRMCoreHDBC.DBSpawn(this.player, worldTime);
            }

            if (this.player.field_71093_bK == 20) {
               JRMCoreHDBC.DBSpawn(this.player, worldTime);
            }

            if (JRMCoreH.updateEveryXTick(this.player.field_70173_aa, JRMCoreConfig.plUpd)
               && this.player.field_71093_bK == 0
               && this.player.field_70170_p.func_147439_a((int)this.player.field_70165_t, (int)this.player.field_70163_u, (int)this.player.field_70161_v)
                  != JRMCoreHDBC.DBCgetBlockTCPort()) {
               short s1 = JRMCoreH.getByte(this.player, "jrmcMsg");
               if (s1 != 0) {
                  JRMCoreH.setByte(0, this.player, "jrmcMsg");
               }
            }
         }
      } else if (JRMCoreH.DBC()) {
         if (this.getAnimKiShoot() != 0) {
            if (this.getAnimKiShotNow() == 0 && this.player != null && this.getAnimKiShoot() != 0 && JGConfigClientSettings.CLIENT_DA10) {
               boolean spawn = true;
               List list = this.checkForEntitiesInside();

               for (int id = 0; id < list.size(); id++) {
                  Entity entity = (Entity)list.get(id);
                  if (entity instanceof EntityEng && ((EntityEng)entity).user == this.player) {
                     spawn = false;
                  }
               }

               if (spawn) {
                  EntityEng kiCharge = new EntityEng(
                     this.player.field_70170_p,
                     this.player.field_70165_t,
                     this.player.field_70163_u,
                     this.player.field_70161_v,
                     this.player.func_70005_c_(),
                     this.getAnimKiShoot(),
                     this.getKiShotCol(),
                     this.getKiShotSiz(),
                     this.getKiShotPart()
                  );
                  kiCharge.destroyer = this.getGoDOn() == 1;
                  this.player.field_70170_p.func_72838_d(kiCharge);
               }
            }

            this.setAnimKiShotNow(1);
         } else {
            this.setAnimKiShotNow(0);
         }
      }

      if (this.getEffect_used() > 0) {
         this.setEffect_used(this.getEffect_used() + 1);
      }
   }

   private List checkForEntitiesInside() {
      AxisAlignedBB aabb = this.player.field_70121_D.func_72329_c();
      return this.player.field_70170_p.func_72839_b(this.player, aabb);
   }

   public final int getBlocking() {
      return this.player.func_70096_w().func_75679_c(JRMCoreConfig.ExtendedPlayerBlockID);
   }

   public final String getHairCode() {
      return this.player.func_70096_w().func_75681_e(JRMCoreConfig.ExtendedPlayerHairID);
   }

   public final int getAnimKiShotNow() {
      String data = this.getOtherCode(0);
      return Integer.parseInt(data);
   }

   public final int getKiShotCol() {
      String data = this.getOtherCode(1);
      return Integer.parseInt(data);
   }

   public final float getKiShotSiz() {
      String data = this.getOtherCode(2);
      return Float.parseFloat(data);
   }

   public final int getKiShotPart() {
      String data = this.getOtherCode(3);
      return Integer.parseInt(data);
   }

   public final int getHandEffect() {
      String data = this.getOtherCode(4);
      return Integer.parseInt(data);
   }

   public final int getEffect_used() {
      String data = this.getOtherCode(5);
      return Integer.parseInt(data);
   }

   public final int getEffect_used2() {
      String data = this.getOtherCode(6);
      return Integer.parseInt(data);
   }

   public final int getUIAnim() {
      String data = this.getOtherCode(7);
      return Integer.parseInt(data);
   }

   public final int getUIAnimID() {
      String data = this.getOtherCode(9);
      return Integer.parseInt(data);
   }

   public final int getAnimKiShoot() {
      String data = this.getOtherCode(8);
      return Integer.parseInt(data);
   }

   public final int getAnimKiShootOn() {
      String data = this.getOtherCode(10);
      return Integer.parseInt(data);
   }

   public final int getGoDOn() {
      String data = this.getOtherCode(11);
      return Integer.parseInt(data);
   }

   public String getOtherCode(int id) {
      String data;
      if (this.player.func_70096_w().func_75681_e(JRMCoreConfig.ExtendedPlayerOtherID).split(";").length == 12) {
         data = this.player.func_70096_w().func_75681_e(JRMCoreConfig.ExtendedPlayerOtherID).split(";")[id];
      } else {
         data = "0;0;0f;0;0;0;0;0;0;0;0;0;".split(";")[id];
      }

      return data;
   }

   public final void setBlocking(int amount) {
      this.player.func_70096_w().func_75692_b(JRMCoreConfig.ExtendedPlayerBlockID, amount);
   }

   public final void setHairCode(String v) {
      this.player.func_70096_w().func_75692_b(JRMCoreConfig.ExtendedPlayerHairID, v);
   }

   public final void setAnimKiShotNow(int v) {
      this.setID(0, v);
   }

   public final void setKiShotCol(int v) {
      this.setID(1, v);
   }

   public final void setKiShotSiz(float v) {
      this.setIDf(2, v);
   }

   public final void setKiShotPart(int v) {
      this.setID(3, v);
   }

   public final void setHandEffect(int v) {
      this.setID(4, v);
   }

   public final void setEffect_used(int v) {
      this.setID(5, v);
   }

   public final void setEffect_used2(int v) {
      this.setID(6, v);
   }

   public final void setUIAnim(int v) {
      this.setID(7, v);
   }

   public final void setUIAnimID(int v) {
      this.setID(9, v);
   }

   public final void setAnimKiShoot(int v) {
      this.setID(8, v);
   }

   public final void setAnimKiShootOn(int v) {
      this.setID(10, v);
   }

   public final void setGoDOn(int v) {
      this.setID(11, v);
   }

   private void setID(int id, int v) {
      String data = "";

      for (int i = 0; i < 12; i++) {
         String dat = this.player.func_70096_w().func_75681_e(JRMCoreConfig.ExtendedPlayerOtherID).split(";")[i];
         if (i == id) {
            if (i == 2) {
               data = data + v;
               data = data + "f";
            } else {
               data = data + v;
            }
         } else if (i == 2) {
            data = data + dat;
         } else {
            data = data + dat;
         }

         data = data + ";";
      }

      this.player.func_70096_w().func_75692_b(JRMCoreConfig.ExtendedPlayerOtherID, data);
   }

   private void setIDf(int id, float v) {
      String data = "";

      for (int i = 0; i < 12; i++) {
         String dat = this.player.func_70096_w().func_75681_e(JRMCoreConfig.ExtendedPlayerOtherID).split(";")[i];
         if (i == id) {
            if (i == 2) {
               data = data + v;
               data = data + "f";
            } else {
               data = data + v;
            }
         } else if (i == 2) {
            data = data + dat;
         } else {
            data = data + dat;
         }

         data = data + ";";
      }

      this.player.func_70096_w().func_75692_b(JRMCoreConfig.ExtendedPlayerOtherID, data);
   }
}
