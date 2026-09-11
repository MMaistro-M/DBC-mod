package JinRyuu.JRMCore.p.DBC;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.DBCH;
import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.DragonBC.common.Entitys.EntityEnergyFM;
import JinRyuu.DragonBC.common.Entitys.EntityInstantTransmission;
import JinRyuu.DragonBC.common.Entitys.EntityMajinAbsorption;
import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.DragonBC.common.Npcs.EntityDBCKami;
import JinRyuu.DragonBC.common.Npcs.EntityMasterGohan;
import JinRyuu.DragonBC.common.Npcs.EntityMasterGoku;
import JinRyuu.DragonBC.common.Npcs.EntityMasterTrunksFuture;
import JinRyuu.DragonBC.common.Npcs.EntityMasterVegeta;
import JinRyuu.DragonBC.common.Render.ArtGravDevTileEntity;
import JinRyuu.DragonBC.common.Worlds.WorldTeleporterDBCSpacePod01;
import JinRyuu.DragonBC.common.Worlds.WorldTeleporterDBCTelep;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHDBC;
import JinRyuu.JRMCore.entity.EntityEnergyAtt;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import JinRyuu.JRMCore.p.PD;
import JinRyuu.JRMCore.server.JGPlayerMP;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCFormMastery;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCGoD;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCInstantTransmission;
import JinRyuu.JRMCore.server.config.dbc.JGConfigRaces;
import JinRyuu.JRMCore.server.config.dbc.JGConfigUltraInstinct;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;

public class DBCPacketHandlerServer {
   public static final int SHENL = 0;
   public static final int PORUN = 1;
   public static final int DRAGON_WISH = 0;
   public static final int MASTER_ENMA_REVIVE = 1;
   public static final int GIVE_WEIGHT_ITEM = 2;
   public static final int PLAYER_DEAD_LIST = 3;
   public static final int CHANGE_GRAVITY_FOR_GRAV_DEVICE = 4;
   public static final int ASKING_FOR_MAJIN_STATUS_EFFECT = 5;
   public static final int WHIS_TELEPORT = 6;
   public static final int TOP_ARENA_RINGOUT = 7;
   public static final int KAIOKEN = 26;
   public static final int SCOUTER_EXPLOSION = -2;
   public static final int GRAVITY_TP_GAIN = -3;
   public static final int TIME_CHAMBER_KICK_TOO_LONG = -6;
   public static final int PLANET_NAMEK = 1;
   public static final int PLANET_VEGETA = 2;
   public static final int PLANET_EARTH = 3;
   public static final ChatStyle styleYellow = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
   public static final ChatStyle styleGold = new ChatStyle().func_150238_a(EnumChatFormatting.GOLD);
   public static final ChatStyle styleRed = new ChatStyle().func_150238_a(EnumChatFormatting.RED);
   public static byte WAVE_FIRING = 10;
   public static byte WAVE_STOP = 11;
   public static byte INSTANT_TRANSMISSION = 12;
   public static byte INSTANT_TRANSMISSION_GROUP = 13;
   public static byte MAJIN_ABSORPTION = 14;
   public double explosionX;
   public double explosionY;
   public double explosionZ;
   public float explosionSize;
   public List chunkPositionRecords;
   public float playerVelocityX;
   public float playerVelocityY;
   public float playerVelocityZ;
   public boolean expGriOff;
   public double expDam;
   public Entity origin;

   public void handleDBCenergy(byte b, byte p, EntityPlayer pl) {
      if (b == WAVE_FIRING) {
         JRMCoreH.setByte(1, pl, "jrmcFrng");
      } else if (b == WAVE_STOP) {
         JRMCoreH.setByte(0, pl, "jrmcFrng");
      } else if (b == INSTANT_TRANSMISSION || b == INSTANT_TRANSMISSION_GROUP) {
         boolean isShortRange = b == INSTANT_TRANSMISSION;
         int modeID = isShortRange ? 0 : 1;
         boolean isEnabled = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_ENABLED[modeID];
         if (isEnabled && pl != null && pl.field_70154_o == null && pl.field_70153_n == null) {
            JGPlayerMP jgPlayer = new JGPlayerMP(pl);
            NBTTagCompound nbt = this.nbt(pl, "pres");
            jgPlayer.setNBT(nbt);
            byte currentRelease = jgPlayer.getRelease();
            byte mode = p;
            if (currentRelease > 0) {
               byte skillLevel = (byte)JRMCoreH.SklLvl(17, pl);
               byte requiredLevel = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_UNLOCK_SKILL_LEVEL[modeID];
               if (skillLevel > 0) {
                  if (skillLevel < requiredLevel) {
                     String message = "Instant Transmission Failed! "
                        + JGConfigDBCInstantTransmission.TP_MODES[modeID]
                        + " Requires Instant Transmission Skill Level "
                        + requiredLevel
                        + " to be used!";
                     pl.func_145747_a(new ChatComponentText(message).func_150255_a(styleRed));
                     return;
                  }

                  byte kiSenseLevelRequirement = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_KI_SENSE_REQUIREMENT[modeID];
                  byte kiSenseSkillLevel = (byte)JRMCoreH.SklLvl(6, pl);
                  if (kiSenseLevelRequirement > kiSenseSkillLevel) {
                     String message = "Instant Transmission Failed! "
                        + JGConfigDBCInstantTransmission.TP_MODES[modeID]
                        + " Requires Ki Sense Level "
                        + kiSenseLevelRequirement
                        + " to be used!";
                     pl.func_145747_a(new ChatComponentText(message).func_150255_a(styleRed));
                     return;
                  }

                  int surroundMode = JRMCoreH.PlyrSettings(pl, 15);
                  if (!JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_ENABLED_SURROUND[surroundMode + 1]) {
                     String message = "Instant Transmission Failed! Surround Teleportation Mode "
                        + JGConfigDBCInstantTransmission.SURROUND_MODES[surroundMode + 1]
                        + " is Disabled!";
                     pl.func_145747_a(new ChatComponentText(message).func_150255_a(styleRed));
                     return;
                  }

                  if (nbt.func_74764_b("jrmcInstantTransmissionTimers")) {
                     String instantTransmissionTimers = nbt.func_74779_i("jrmcInstantTransmissionTimers");
                     String[] values = instantTransmissionTimers.split(";");
                     int cd = Integer.parseInt(values[modeID]);
                     if (cd > 0) {
                        String message = "Instant Transmission Failed! "
                           + JGConfigDBCInstantTransmission.TP_MODES[modeID]
                           + " teleportation is in Cooldown! "
                           + cd * 5
                           + "s Remaining!";
                        pl.func_145747_a(new ChatComponentText(message).func_150255_a(styleRed));
                        return;
                     }
                  }

                  if (isShortRange) {
                     int shortTPMode = JRMCoreH.PlyrSettings(pl, 14);
                     if (!JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_ENABLED_SHORT[shortTPMode + 1]) {
                        String message = "Instant Transmission Failed! Short Teleportation Mode "
                           + JGConfigDBCInstantTransmission.SHORT_MODES[shortTPMode + 1]
                           + " is Disabled!";
                        pl.func_145747_a(new ChatComponentText(message).func_150255_a(styleRed));
                        return;
                     }

                     Entity technique = new EntityInstantTransmission(pl);
                     ((EntityInstantTransmission)technique).setData(mode, skillLevel, shortTPMode, surroundMode);
                     pl.field_70170_p.func_72838_d(technique);
                  } else {
                     MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                     int groupID = JRMCoreH.getInt(pl, "JRMCGID");

                     for (int playerID = 0; playerID < server.func_71213_z().length; playerID++) {
                        int selectedMember = p;
                        if (playerID == selectedMember) {
                           EntityPlayerMP targetEntity = JRMCoreH.getPlayerForUsername(server, server.func_71213_z()[playerID]);
                           int egid = JRMCoreH.getInt(targetEntity, "JRMCGID");
                           if (JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_RELEASE_SENSE_REQUIRED_ENABLED[modeID]) {
                              JGPlayerMP targetMP = new JGPlayerMP(targetEntity);
                              NBTTagCompound nbt2 = this.nbt(targetEntity, "pres");
                              targetMP.setNBT(nbt2);
                              byte targetRelease = targetMP.getRelease();
                              boolean targetCanBeSensed = targetRelease > 0;
                              if (!targetCanBeSensed) {
                                 String message = "Instant Transmission Failed! Target can not be sensed at 0% Release Level.";
                                 pl.func_145747_a(new ChatComponentText(message).func_150255_a(styleRed));
                                 return;
                              }
                           }

                           if (egid == groupID && targetEntity != null && !targetEntity.equals(pl)) {
                              boolean isOneInOtherworld = pl.field_71093_bK == DBCConfig.otherWorld && targetEntity.field_71093_bK != DBCConfig.otherWorld
                                 || pl.field_71093_bK != DBCConfig.otherWorld && targetEntity.field_71093_bK == DBCConfig.otherWorld;
                              if (isOneInOtherworld) {
                                 String message = "Instant Transmission Failed! You can't "
                                    + (pl.field_71093_bK == DBCConfig.otherWorld && targetEntity.field_71093_bK != DBCConfig.otherWorld ? "Leave" : "Enter")
                                    + " the Otherworld!";
                                 pl.func_145747_a(new ChatComponentText(message).func_150255_a(styleRed));
                                 return;
                              }

                              boolean isFused = JRMCoreH.isFused(pl);
                              if (pl.field_71093_bK != targetEntity.field_71093_bK) {
                                 if (!JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_TP_ENABLED
                                    || JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_DIMENSION_BLACKLIST.containsKey(targetEntity.field_71093_bK)) {
                                    String message;
                                    if (JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_TP_ENABLED) {
                                       message = "Instant Transmission Failed! Dimensional Teleportation is Disabled!";
                                    } else {
                                       message = "Instant Transmission Failed! Target Dimension for Teleportation is Blacklisted!";
                                    }

                                    pl.func_145747_a(new ChatComponentText(message).func_150255_a(styleRed));
                                    return;
                                 }

                                 if (isFused) {
                                    String message = "Unable to dimensional teleport while fused.";
                                    pl.func_145747_a(new ChatComponentText(message).func_150255_a(styleRed));
                                    return;
                                 }
                              }

                              ArrayList<EntityPlayer> teleportedEntities = new ArrayList<>();
                              teleportedEntities.add(pl);
                              if (surroundMode != -1) {
                                 int surroundPlayerLimit = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_SURROUND_PLAYER_LIMIT_SKILL_LEVEL[modeID][skillLevel
                                    - 1];
                                 double r2 = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_SURROUND_TARGET_FINDER_RANGE;
                                 double r2Y = 1.0;
                                 AxisAlignedBB aabb2 = AxisAlignedBB.func_72330_a(
                                    pl.field_70165_t - r2,
                                    pl.field_70163_u - 1.0,
                                    pl.field_70161_v - r2,
                                    pl.field_70165_t + r2,
                                    pl.field_70163_u + 1.0,
                                    pl.field_70161_v + r2
                                 );
                                 List entityList2 = pl.field_70170_p.func_72839_b(pl, aabb2);

                                 for (int k = 0; k < entityList2.size(); k++) {
                                    Entity nearbyEntity = (Entity)entityList2.get(k);
                                    if (nearbyEntity != null
                                       && nearbyEntity instanceof EntityPlayer
                                       && !((EntityPlayer)nearbyEntity).equals(targetEntity)
                                       && !((EntityPlayer)nearbyEntity).equals(pl)
                                       && nearbyEntity.func_70089_S()) {
                                       if (surroundPlayerLimit != -1 && teleportedEntities.size() - 1 > surroundPlayerLimit) {
                                          break;
                                       }

                                       boolean groupOnly = surroundMode == 0;
                                       if (groupOnly) {
                                          int egid2 = JRMCoreH.getInt((EntityPlayer)nearbyEntity, "JRMCGID");
                                          if (egid2 == groupID && groupID != 0) {
                                             teleportedEntities.add((EntityPlayer)nearbyEntity);
                                          }
                                       } else {
                                          teleportedEntities.add((EntityPlayer)nearbyEntity);
                                       }
                                    }
                                 }
                              }

                              String ste = jgPlayer.getStatusEffects();
                              boolean divine = JRMCoreH.StusEfcts(17, ste);
                              boolean creativeMode = JRMCoreH.isInCreativeMode(pl);
                              if (!creativeMode) {
                                 int[] playerAttributes = jgPlayer.getAttributes();
                                 byte race = jgPlayer.getRace();
                                 byte classID = jgPlayer.getClassID();
                                 byte powerType = jgPlayer.getPowerType();
                                 int curEnergy = jgPlayer.getEnergy();
                                 int maxEnergy = jgPlayer.getEnergyMax(race, classID, powerType, playerAttributes, JRMCoreH.SklLvl_KiBs(pl, powerType));
                                 double FLAT_COST = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_COST[modeID][0][skillLevel - 1];
                                 double PERCENTAGE_COST = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_COST[modeID][1][skillLevel - 1];
                                 double costMulti = PERCENTAGE_COST / 100.0;
                                 double energyCost = maxEnergy * costMulti + FLAT_COST;
                                 if (pl.field_71093_bK != targetEntity.field_71093_bK) {
                                    double DIMENSIONAL_FLAT_COST = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_COST[0][skillLevel
                                       - 1];
                                    double DIMENSIONAL_PERCENTAGE_COST = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_COST[1][skillLevel
                                       - 1];
                                    double costMultiDim = DIMENSIONAL_PERCENTAGE_COST / 100.0;
                                    double energyCostDim = maxEnergy * costMultiDim + DIMENSIONAL_FLAT_COST;
                                    energyCost += energyCostDim;
                                 }

                                 if (teleportedEntities.size() > 1) {
                                    double costPerPlayerFlat = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_SURROUND_COST_PER_PLAYER[0][skillLevel
                                       - 1];
                                    double costPerPlayerPerc = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_SURROUND_COST_PER_PLAYER[1][skillLevel
                                       - 1];
                                    double costMultiPerPlayer = costPerPlayerPerc / 100.0;
                                    double energyCostDim = maxEnergy * costMultiPerPlayer + costPerPlayerFlat;
                                    energyCost += energyCostDim * (teleportedEntities.size() - 1);
                                 }

                                 if (curEnergy < energyCost) {
                                    String message = "Instant Transmission Failed! Not Enough Ki: " + (int)energyCost;
                                    pl.func_145747_a(new ChatComponentText(message).func_150255_a(styleRed));
                                    return;
                                 }

                                 int remainingEnergy = curEnergy - (int)energyCost;
                                 JRMCoreH.setInt(remainingEnergy, pl, "jrmcEnrgy");
                              }

                              pl.field_70170_p
                                 .func_72956_a(
                                    pl,
                                    divine ? "jinryuudragonbc:DBC4.blacktp" : "jinryuudragonbc:DBC5.instant_transmission",
                                    0.25F,
                                    pl.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F
                                 );
                              double x = targetEntity.field_70165_t;
                              double y = targetEntity.field_70163_u;
                              double z = targetEntity.field_70161_v;
                              JRMCoreH.playerUsedInstantTransmission(pl);

                              for (int i = teleportedEntities.size() - 1; i >= 0; i--) {
                                 EntityPlayer entity = teleportedEntities.get(i);
                                 double x2 = entity.field_70165_t - pl.field_70165_t;
                                 double z2 = entity.field_70161_v - pl.field_70161_v;
                                 boolean dimensional = ((EntityPlayerMP)entity).field_71093_bK != targetEntity.field_71093_bK;
                                 if (((EntityPlayerMP)entity).field_71093_bK == -1 && dimensional) {
                                    boolean nether = true;
                                 } else {
                                    boolean nether = false;
                                 }

                                 boolean theEnd = ((EntityPlayerMP)entity).field_71093_bK == 1 && dimensional;
                                 boolean netherTarget = targetEntity.field_71093_bK == -1 && dimensional;
                                 boolean theEndTarget = targetEntity.field_71093_bK == 1 && dimensional;
                                 if (targetEntity.field_71093_bK == 0 && dimensional) {
                                    boolean overworldTarget = true;
                                 } else {
                                    boolean overworldTarget = false;
                                 }

                                 double x3 = x + x2;
                                 double y3 = y + 1.0;
                                 double z3 = z + z2;
                                 if (((EntityPlayerMP)entity).field_71093_bK != targetEntity.field_71093_bK) {
                                    if (netherTarget || theEndTarget || theEnd) {
                                       ((EntityPlayerMP)pl).field_71133_b.func_71203_ab().func_72356_a((EntityPlayerMP)pl, targetEntity.field_71093_bK);
                                    }

                                    if (!theEndTarget) {
                                       FMLCommonHandler.instance()
                                          .getMinecraftServerInstance()
                                          .func_71203_ab()
                                          .transferPlayerToDimension(
                                             (EntityPlayerMP)entity,
                                             targetEntity.field_71093_bK,
                                             new WorldTeleporterDBCTelep(
                                                FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(targetEntity.field_71093_bK)
                                             )
                                          );
                                    }
                                 }

                                 ((EntityPlayerMP)entity).field_71135_a.func_147364_a(x3, y3, z3, entity.field_70177_z, entity.field_70125_A);
                                 ((EntityPlayerMP)entity).func_70634_a(x3, y3, z3);
                                 entity.func_71023_q(1);
                              }

                              if (isFused) {
                                 String fusionMembers = jgPlayer.getNBT().func_74779_i("jrmcFuzion");
                                 if (fusionMembers.length() > 0 && !fusionMembers.equals(" ")) {
                                    String[] fusionParticipants = fusionMembers.split(",");
                                    if (fusionParticipants.length == 3) {
                                       boolean isController = fusionParticipants[0].equalsIgnoreCase(pl.func_70005_c_());
                                       if (isController) {
                                          EntityPlayer fusedPlayerPartner = pl.field_70170_p.func_72924_a(fusionParticipants[1]);
                                          if (fusedPlayerPartner != null) {
                                             NBTTagCompound nbt2 = this.nbt(fusedPlayerPartner, "pres");
                                             String fusionMembers2 = nbt2.func_74779_i("jrmcFuzion");
                                             String[] fusionParticipants2 = fusionMembers2.split(",");
                                             if (fusionParticipants2.length == 3) {
                                                ((EntityPlayerMP)fusedPlayerPartner)
                                                   .field_71135_a
                                                   .func_147364_a(x, y + 1.5, z, fusedPlayerPartner.field_70177_z, fusedPlayerPartner.field_70125_A);
                                                ((EntityPlayerMP)fusedPlayerPartner).func_70634_a(x, y + 1.5, z);
                                                fusedPlayerPartner.func_71023_q(1);
                                             }
                                          }
                                       }
                                    }
                                 }
                              }

                              JRMCoreH.playerUsedInstantTransmission(pl);
                              if (JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_NOTIFY_SERVER_ENABLED) {
                                 mod_DragonBC.logger
                                    .info(
                                       pl.func_70005_c_()
                                          + " used Long Distance Teleportation to "
                                          + targetEntity.func_70005_c_()
                                          + " with "
                                          + (teleportedEntities.size() - 1)
                                          + " Surrounding Players to the DimensionID: "
                                          + targetEntity.field_71093_bK
                                    );
                              }

                              if (nbt.func_74764_b("jrmcInstantTransmissionTimers")) {
                                 String instantTransmissionTimers = nbt.func_74779_i("jrmcInstantTransmissionTimers");
                                 String[] values = instantTransmissionTimers.split(";");
                                 int tpShort = Integer.parseInt(values[0]);
                                 int tpLong = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_COOLDOWN[1][skillLevel - 1];
                                 JRMCoreH.setString(tpShort + ";" + tpLong, pl, "jrmcInstantTransmissionTimers");
                              } else {
                                 String instantTransmissionTimers = "0;"
                                    + JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_COOLDOWN[1][skillLevel - 1];
                                 JRMCoreH.setString(instantTransmissionTimers, pl, "jrmcInstantTransmissionTimers");
                              }

                              pl.field_70170_p
                                 .func_72956_a(
                                    pl,
                                    divine ? "jinryuudragonbc:DBC4.blacktp" : "jinryuudragonbc:DBC5.instant_transmission",
                                    0.25F,
                                    pl.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F
                                 );
                              return;
                           }

                           selectedMember++;
                        }
                     }
                  }
               }
            }
         } else if (!isEnabled) {
            String message = (isShortRange ? "Short" : "Long") + " Distance Instant Transmission Failed! It is Disabled on this Server!";
            pl.func_145747_a(new ChatComponentText(message).func_150255_a(styleRed));
            return;
         }
      } else if (b == MAJIN_ABSORPTION) {
         if (JGConfigRaces.CONFIG_MAJIN_ENABLED && JGConfigRaces.CONFIG_MAJIN_ABSORPTION_ENABLED) {
            JGPlayerMP jgPlayer = new JGPlayerMP(pl);
            NBTTagCompound nbt = this.nbt(pl, "pres");
            jgPlayer.setNBT(nbt);
            byte race = jgPlayer.getRace();
            float release = jgPlayer.getRelease() / 100.0F;
            if (JRMCoreH.isRaceMajin(race) && jgPlayer.getTransformationMeter() >= 100 && release > 0.0F) {
               boolean isCreative = JRMCoreH.isInCreativeMode(pl);
               int remainingLife = 0;
               int lifeCost = 0;
               int cooldown = 0;
               int state = jgPlayer.getState();
               String statusEffects = jgPlayer.getStatusEffects();
               boolean isMysticOn = JRMCoreH.StusEfcts(13, statusEffects);
               boolean isUltraInstinctOn = JRMCoreH.StusEfcts(19, statusEffects);
               boolean isGoDOn = JRMCoreH.StusEfcts(20, statusEffects);
               int stateID = JRMCoreH.getMajinFormID(state, isMysticOn, isUltraInstinctOn, isGoDOn);
               boolean canUse = true;
               boolean isOnCooldown = false;
               if (!isCreative) {
                  cooldown = jgPlayer.getNBT().func_74762_e("jrmcMajinAbsorptionTimer");
                  isOnCooldown = cooldown > 0;
                  if (isOnCooldown) {
                     canUse = false;
                  } else {
                     byte classID = jgPlayer.getClassID();
                     byte powerType = jgPlayer.getPowerType();
                     int[] playerAttributes = jgPlayer.getAttributes();
                     int curBody = jgPlayer.getHealth();
                     int maxBody = jgPlayer.getHealthMax(race, classID, powerType, playerAttributes);
                     lifeCost = (int)(
                        maxBody * JGConfigRaces.CONFIG_MAJIN_ABSORPTON_HEALTH_COST[stateID][1] + JGConfigRaces.CONFIG_MAJIN_ABSORPTON_HEALTH_COST[stateID][0]
                     );
                     boolean hasEnoughHealth = isCreative ? true : curBody > lifeCost;
                     remainingLife = curBody - lifeCost;
                     canUse = hasEnoughHealth;
                  }
               }

               if (canUse) {
                  if (!isCreative) {
                     JRMCoreH.setInt(remainingLife, pl, "jrmcBdy");
                     cooldown = JGConfigRaces.CONFIG_MAJIN_ABSORPTON_CD_TIMER[stateID];
                     jgPlayer.getNBT().func_74768_a("jrmcMajinAbsorptionTimer", cooldown);
                  }

                  String dns = nbt.func_74779_i("jrmcDNS");
                  float userPower = 0.0F;
                  float attackUser = 0.0F;
                  float healthUser = 0.0F;
                  float kiPowerUser = 0.0F;
                  int strength = jgPlayer.getAttribute(0);
                  attackUser = JRMCoreH.stat(0, pl, 0, strength, 0.0F);
                  attackUser = (int)(attackUser * release);
                  healthUser = jgPlayer.getHealth();
                  kiPowerUser = jgPlayer.getEnergyPower();
                  kiPowerUser = (int)(kiPowerUser * release);
                  attackUser = (int)(attackUser * JGConfigRaces.CONFIG_MAJIN_ABSORPTON_USER_POWER_MULTI[stateID][0]);
                  healthUser = (int)(healthUser * JGConfigRaces.CONFIG_MAJIN_ABSORPTON_USER_POWER_MULTI[stateID][1]);
                  kiPowerUser = (int)(kiPowerUser * JGConfigRaces.CONFIG_MAJIN_ABSORPTON_USER_POWER_MULTI[stateID][2]);
                  if (attackUser < 1.0F) {
                     attackUser = 1.0F;
                  }

                  if (healthUser < 1.0F) {
                     healthUser = 1.0F;
                  }

                  if (kiPowerUser < 1.0F) {
                     kiPowerUser = 1.0F;
                  }

                  userPower = attackUser + kiPowerUser + healthUser;
                  Entity technique = new EntityMajinAbsorption(
                     pl, 0, state == 1 ? 12561588 : (state == 3 ? 16757199 : JRMCoreH.dnsBodyCM(dns)), userPower, attackUser + kiPowerUser, stateID
                  );
                  pl.field_70170_p.func_72838_d(technique);
               } else {
                  String text = "";
                  if (isOnCooldown) {
                     text = "Unable to use Absorption! Cooldown: " + cooldown * 5 + "s";
                  } else {
                     text = "Unable to use Absorption! Health Cost: " + lifeCost;
                  }

                  pl.func_145747_a(new ChatComponentText(text).func_150255_a(styleRed));
               }

               jgPlayer.setTransformationMeter(0);
            }
         }
      } else {
         String s2 = JRMCoreH.getString(pl, JRMCoreH.techNbt[b]);
         boolean isAKiAttack = b >= 0 && b <= 7;
         if (isAKiAttack) {
            byte curRel = JRMCoreH.getByte(pl, "jrmcRelease");
            if (curRel > 0) {
               int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(pl);
               String[] PlyrSkills = JRMCoreH.getString(pl, "jrmcSSlts").split(",");
               byte pwr = JRMCoreH.getByte(pl, "jrmcPwrtyp");
               byte rc = JRMCoreH.getByte(pl, "jrmcRace");
               byte cls = JRMCoreH.getByte(pl, "jrmcClass");
               int curEn = JRMCoreH.getInt(pl, "jrmcEnrgy");
               int curBo = JRMCoreH.getInt(pl, "jrmcBdy");
               byte align = JRMCoreH.getByte(pl, "jrmcAlign");
               byte state = JRMCoreH.getByte(pl, "jrmcState");
               byte state2 = JRMCoreH.getByte(pl, "jrmcState2");
               String sklx = JRMCoreH.getString(pl, "jrmcSSltX");
               int resrv = JRMCoreH.getInt(pl, "jrmcArcRsrv");
               String absorption = JRMCoreH.getString(pl, "jrmcMajinAbsorptionData");
               byte trans = (byte)(state + 1);
               String ste = JRMCoreH.getString(pl, "jrmcStatusEff");
               boolean mj = JRMCoreH.StusEfcts(12, ste);
               boolean lg = JRMCoreH.StusEfcts(14, ste);
               boolean kk = JRMCoreH.StusEfcts(5, ste);
               boolean mc = JRMCoreH.StusEfcts(13, ste);
               boolean mn = JRMCoreH.StusEfcts(19, ste);
               boolean gd = JRMCoreH.StusEfcts(20, ste);
               boolean v = JRMCoreH.StusEfcts(10, ste) || JRMCoreH.StusEfcts(11, ste);
               byte release = JRMCoreH.getByte(pl, "jrmcRelease");
               int WIL = JRMCoreH.getPlayerAttribute(
                  pl, PlyrAttrbts, 3, state, state2, rc, sklx, curRel, resrv, lg, mj, kk, mc, mn, gd, pwr, PlyrSkills, v, absorption
               );
               int WIL2 = JRMCoreH.getPlayerAttribute(
                  pl, PlyrAttrbts, 3, 0, 0, rc, sklx, curRel, resrv, lg, mj, false, false, false, false, pwr, PlyrSkills, v, absorption
               );
               int stat = JRMCoreH.stat(pl, 3, pwr, 4, WIL, rc, cls, 0.0F);
               int stat2 = JRMCoreH.stat(pl, 3, pwr, 4, WIL2, rc, cls, 0.0F);
               Entity var8 = null;
               boolean isCustomAttack = b >= 0 && b < 4 && s2 != null && s2.length() > 0;
               boolean isFixedAttack = b >= 4 && b < 8 && s2 != null && s2.length() > 0;
               float RELEASE_MULTI = 0.01F;
               if (isCustomAttack) {
                  String[] tech = s2.toString().split(";");
                  if (tech != null && tech.length > 9) {
                     byte type = Byte.parseByte(tech[3]);
                     byte speed = Byte.parseByte(tech[4]);
                     short dam = Short.parseShort(tech[5]);
                     byte effect = Byte.parseByte(tech[6]);
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

                     int exp = 0;
                     int lvl = 0;
                     int upg = 0;
                     byte[] sts = new byte[]{0, 0, 0, 0, 0, 0, 0};
                     if (tech.length > 16) {
                        exp = Integer.parseInt(tech[16]);
                        lvl = Integer.parseInt(tech[17]);
                        upg = Integer.parseInt(tech[18]);
                        sts = JRMCoreH.tech_statmods(tech[19]);
                     }

                     int dam1 = JRMCoreH.techDBCdmg(tech, stat, sts);
                     int cost1 = JRMCoreH.techDBCkic(tech, stat2, sts);
                     int cost2 = (int)((double)cost1 * curRel * 0.01F * (p * 0.02F) * JRMCoreConfig.dat5696[type][2]);
                     dam1 = dam1 < 1 ? 1 : dam1;
                     cost2 = cost2 < 2 ? 2 : cost2;
                     int costBo = cost2 > curEn ? curBo - (cost2 - curEn) : 0;
                     boolean canAffordKiCost = cost2 <= curEn;
                     boolean lifeAboveZero = costBo > 0;
                     if (JRMCoreConfig.dat5695[type] && (canAffordKiCost || lifeAboveZero)) {
                        if (!JRMCoreH.isInCreativeMode(pl)) {
                           int energy = curEn - cost2 < 0 ? 0 : curEn - cost2;
                           JRMCoreH.setInt(energy, pl, "jrmcEnrgy");
                           if (energy == 0) {
                              JRMCoreH.setByte(0, pl, "jrmcRelease");
                           }

                           if (costBo > 0) {
                              JRMCoreH.setInt(costBo, pl, "jrmcBdy");
                           }
                        }

                        pl.field_70170_p.func_72956_a(pl, "jinryuudragonbc:" + JRMCoreH.techSnds(type, 1, sfire), type == 6 ? 0.2F : 1.0F, 1.0F);
                        NBTTagCompound nbt = JRMCoreH.nbt(pl, "pres");
                        String StE = nbt.func_74779_i("jrmcStatusEff");
                        boolean setGoDOn = JRMCoreH.StusEfcts(20, StE);
                        var8 = new EntityEnergyAtt(
                           pl, type, speed, dam, effect, color, density, sincantation, sfire, smove, p, dam1, cost1, sts, b, release, align
                        );
                        if (setGoDOn && JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED && JGConfigDBCGoD.CONFIG_GOD_ENABLED) {
                           ((EntityEnergyAtt)var8).destroyer = setGoDOn;
                        }

                        if (JGConfigDBCFormMastery.FM_Enabled && release > 0) {
                           JRMCoreH.addToFormMasteriesValue(
                              pl, JGConfigDBCFormMastery.FM_GainKiFire, JGConfigDBCFormMastery.FM_GainKiFire, rc, state, state2, kk, mc, mn, gd, 3
                           );
                        }

                        pl.field_70170_p.func_72838_d(var8);
                        JRMCoreH.a1t3(pl);
                     }
                  }
               } else if (isFixedAttack) {
                  int tn = Integer.parseInt(s2);
                  String[] tech = JRMCoreH.pmdbc[tn];
                  if (tech[0].equals("KAFakeMoon")) {
                     int kicost = Integer.parseInt(tech[7]);
                     if (curEn >= kicost) {
                        if (!JRMCoreH.isInCreativeMode(pl)) {
                           int energy = curEn - kicost < 0 ? 0 : curEn - kicost;
                           JRMCoreH.setInt(energy, pl, "jrmcEnrgy");
                           if (energy == 0) {
                              JRMCoreH.setByte(0, pl, "jrmcRelease");
                           }
                        }

                        Entity var168 = new EntityEnergyFM(pl);
                        pl.field_70170_p.func_72838_d(var168);
                     }
                  } else if (tech != null && tech.length > 9) {
                     byte type = Byte.parseByte(tech[3]);
                     byte speed = Byte.parseByte(tech[4]);
                     short dam = Short.parseShort(tech[5]);
                     byte cast = Byte.parseByte(tech[8]);
                     byte effect = Byte.parseByte(tech[6]);
                     byte color = Byte.parseByte(tech[10]);
                     byte density = Byte.parseByte(tech[11]);
                     byte sincantation = Byte.parseByte(tech[12]);
                     byte sfire = Byte.parseByte(tech[13]);
                     byte smove = Byte.parseByte(tech[14]);
                     if (tech.length > 12) {
                        sincantation = Byte.parseByte(tech[12]);
                        sfire = Byte.parseByte(tech[13]);
                        smove = Byte.parseByte(tech[14]);
                     }

                     int exp = 0;
                     int lvl = 0;
                     int upg = 0;
                     byte[] sts = new byte[]{0, 0, 0, 0, 0, 0, 0};
                     if (tech.length > 16) {
                        exp = Integer.parseInt(tech[16]);
                        lvl = Integer.parseInt(tech[17]);
                        upg = Integer.parseInt(tech[18]);
                        sts = JRMCoreH.tech_statmods(tech[19]);
                     }

                     int dam1 = JRMCoreH.techDBCdmg(tech, stat, sts);
                     int cost1 = JRMCoreH.techDBCkic(tech, stat2, sts);
                     int dam2 = (int)((double)dam1 * curRel * 0.01F);
                     int cost2 = (int)((double)cost1 * curRel * 0.01F * (p * 0.02F) * JRMCoreConfig.dat5696[type][2]);
                     dam1 = dam1 < 1 ? 1 : dam1;
                     dam2 = dam2 < 1 ? 1 : dam2;
                     cost2 = cost2 < 2 ? 2 : cost2;
                     int costBo = cost2 > curEn ? curBo - (cost2 - curEn) : 0;
                     boolean canAffordKiCost = cost2 <= curEn;
                     boolean lifeAboveZero = costBo > 0;
                     if (JRMCoreConfig.dat5695[type] && (canAffordKiCost || lifeAboveZero)) {
                        if (sfire > 0) {
                           pl.field_70170_p.func_72956_a(pl, "jinryuudragonbc:" + JRMCoreH.techSnds(10, 1, (short)(sfire - 1)), 1.0F, 1.0F);
                           if (cast > 0) {
                              pl.field_70170_p.func_72956_a(pl, "jinryuudragonbc:" + JRMCoreH.techSnds(10, 3, (short)(cast - 1)), 1.0F, 1.0F);
                           }
                        }

                        if (sincantation > 0) {
                           pl.field_70170_p.func_72956_a(pl, "jinryuudragonbc:" + JRMCoreH.techSnds(10, 3, (short)(sincantation - 1)), 1.0F, 1.0F);
                        }

                        if (!JRMCoreH.isInCreativeMode(pl)) {
                           int energy = curEn - cost2 < 0 ? 0 : curEn - cost2;
                           JRMCoreH.setInt(energy, pl, "jrmcEnrgy");
                           if (energy == 0) {
                              JRMCoreH.setByte(0, pl, "jrmcRelease");
                           }

                           if (costBo > 0) {
                              JRMCoreH.setInt(costBo, pl, "jrmcBdy");
                           }
                        }

                        var8 = new EntityEnergyAtt(
                           pl, type, speed, dam2, effect, color, density, sincantation, sfire, smove, p, dam1, cost1, sts, b, release, align
                        );
                        if (JGConfigDBCFormMastery.FM_Enabled && release > 0) {
                           JRMCoreH.addToFormMasteriesValue(
                              pl, JGConfigDBCFormMastery.FM_GainKiFire, JGConfigDBCFormMastery.FM_GainKiFire, rc, state, state2, kk, mc, mn, gd, 3
                           );
                        }

                        pl.field_70170_p.func_72838_d(var8);
                        JRMCoreH.a1t3(pl);
                     }
                  }
               }
            }
         }
      }
   }

   public void handleDBCdri(int dri, EntityPlayer p) {
      NBTTagCompound tag = this.nbt(p, "");
      if (dri == 5 || dri == 6) {
         tag.func_74768_a("DBCdriS", dri);
      }

      if (dri == 1 || dri == 2) {
         tag.func_74768_a("DBCdriF", dri);
      }

      if (dri == 3 || dri == 4) {
         tag.func_74768_a("DBCdriY", dri);
      }

      if (dri >= 100) {
         ChatStyle styl = new ChatStyle().func_150238_a(EnumChatFormatting.WHITE);
         if (JRMCoreConfig.DebugInfo) {
            String info = "DBC has found Potential hacking at ID:06 by player: %";
            mod_DragonBC.logger.info(String.format(info, p.func_70005_c_()));
         }
      }
   }

   public void send(EntityPlayerMP Player) {
      Player.func_145747_a(new ChatComponentText(JRMCoreH.trl("jrmc", "notenoughtp")));
   }

   public void handleDBCwish(int id, String s, EntityPlayer p) {
      MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
      if (id == 0) {
         String[] sa = s.split(";");
         int wish = Integer.parseInt(sa[0]);
         EntityPlayer target = null;
         if (sa.length > 1) {
            for (int i = 0; i < server.field_71305_c.length; i++) {
               int d = server.field_71305_c[i].field_73011_w.field_76574_g;
               if (d == DBCConfig.otherWorld) {
                  target = server.field_71305_c[i].func_72924_a(sa[1]);
               }
            }
         }

         if (wish < 100) {
            int wsh = JRMCoreH.getInt(p, "jrmcWishes");
            int dragon = JRMCoreH.getInt(p, "jrmcDrgn");
            ArrayList<String> wl = dragon == 0 ? DBCH.wishS : DBCH.wishP;
            if (wsh > 0) {
               p.field_70170_p.func_72908_a(p.field_70165_t, p.field_70163_u, p.field_70161_v, "jinryuudragonbc:dragon.grant", 1.0F, 1.0F);
               if (wl.get(wish).contains(";+;")) {
                  String[] ia = wl.get(wish).replace(";+;", ";+").split(";+");
                  String in = ia[0];
                  int iz = Integer.parseInt(ia[1]);
                  int im = Integer.parseInt(ia[2]);
                  Item item = JRMCoreH.getItemByText(in);
                  if (item != null) {
                     ItemStack is = new ItemStack(item, iz, im);
                     p.field_71071_by.func_70441_a(is);
                     p.field_71071_by.field_70459_e = true;
                     this.closeInventoryChange(p);
                  }
               } else if (wl.get(wish).equals("revive") && target != null) {
                  int alive = JRMCoreH.getByte(target, "jrmcAlv");
                  if (alive == 1 && target != null && p.field_71093_bK != DBCConfig.otherWorld && target.field_71093_bK == DBCConfig.otherWorld) {
                     JRMCoreH.setInt(0, target, "jrmcReviveTmer");
                     String t = JRMCoreH.trlai("dbc", "canrevivenow");
                     ChatStyle styl = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
                     target.func_145747_a(new ChatComponentTranslation(t, new Object[0]).func_150255_a(styl));
                  }
               } else if (wl.get(wish).equals("reviventp") && target != null) {
                  int alive = JRMCoreH.getByte(target, "jrmcAlv");
                  if (alive == 1 && target != null && p.field_71093_bK != DBCConfig.otherWorld && target.field_71093_bK == DBCConfig.otherWorld) {
                     JRMCoreH.setString(
                        p.func_70005_c_() + ";" + p.field_71093_bK + ";" + (int)p.field_70165_t + ";" + (int)p.field_70163_u + ";" + (int)p.field_70161_v,
                        target,
                        "jrmcRevtpInit"
                     );
                     String t = JRMCoreH.trlai("dbc", "reviveoffered");
                     ChatStyle styl = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
                     target.func_145747_a(
                        new ChatComponentTranslation(
                              t,
                              new Object[]{
                                 p.func_70005_c_(),
                                 new ChatComponentTranslation(JRMCoreH.trlai("dbc", (String)DBCH.plntNms.get(p.field_71093_bK)), new Object[0]),
                                 (int)p.field_70165_t + ", " + (int)p.field_70163_u + ", " + (int)p.field_70161_v
                              }
                           )
                           .func_150255_a(styl)
                     );
                  }
               } else if (!wl.get(wish).equals("reviveall")) {
                  if (wl.get(wish).equals("young")) {
                     JRMCoreH.setFloat(90, p, "JRYCAge");
                  } else if (wl.get(wish).equals("child")) {
                     JRMCoreH.setFloat(25, p, "JRYCAge");
                  } else if (wl.get(wish).equals("old")) {
                     JRMCoreH.setFloat(300, p, "JRYCAge");
                  } else if (wl.get(wish).equals("kicolor")) {
                     JRMCoreH.setInt(Integer.parseInt(sa[1]), p, "jrmcAuraColor");
                  } else if (wl.get(wish).equals("arcultformcolor")) {
                     String d = JRMCoreH.getString(p, "jrmcDNSAU");
                     if (d.contains(";")) {
                        JRMCoreH.setString(d.substring(1), p, "jrmcDNSAU");
                     }
                  }
               } else if (p.field_71093_bK != DBCConfig.otherWorld) {
                  int al = JRMCoreH.Algnmnt(JRMCoreH.getByte(p, "jrmcAlign"));

                  for (int i = 0; i < server.field_71305_c.length; i++) {
                     int d = server.field_71305_c[i].field_73011_w.field_76574_g;
                     if (d == DBCConfig.otherWorld) {
                        for (EntityPlayer var30 : server.field_71305_c[i].field_73010_i) {
                           int alive = JRMCoreH.getByte(var30, "jrmcAlv");
                           if (alive == 1 && var30 != null && var30.field_71093_bK == DBCConfig.otherWorld) {
                              int tal = JRMCoreH.Algnmnt(JRMCoreH.getByte(var30, "jrmcAlign"));
                              if (al == tal) {
                                 JRMCoreH.setInt(0, var30, "jrmcReviveTmer");
                                 String t = JRMCoreH.trlai("dbc", "canrevivenow");
                                 ChatStyle styl = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
                                 var30.func_145747_a(new ChatComponentTranslation(t, new Object[0]).func_150255_a(styl));
                              }
                           }
                        }
                     }
                  }
               }
            }

            JRMCoreH.setInt(--wsh, p, "jrmcWishes");
         }
      } else if (id == 1) {
         int wish = Integer.parseInt(s);
         if (wish == 100 && p.field_71093_bK == DBCConfig.otherWorld && p != null && p.field_70154_o == null && p.field_70153_n == null) {
            int RevTmr = JRMCoreH.getInt(p, "jrmcReviveTmer");
            if (RevTmr <= 0) {
               if (JRMCoreHDBC.DBCgetConfigDeadInv()
                  && p.field_70170_p.func_82736_K().func_82766_b("keepInventory")
                  && !p.field_71075_bZ.field_75098_d
                  && JRMCoreHDBC.hasHalo(p)) {
                  JRMCoreH.nbt(p).func_74782_a("InventoryDead", p.field_71071_by.func_70442_a(new NBTTagList()));
                  p.field_71071_by.func_70443_b(JRMCoreH.nbt(p).func_150295_c("InventoryLiving", 10));
                  p.getEntityData().func_74782_a("Inventory", p.field_71071_by.func_70442_a(new NBTTagList()));
               }

               byte by = (byte)JRMCoreH.Algnmnt(JRMCoreH.getByte(p, "jrmcAlign"));
               JRMCoreH.setByte(0, p, "jrmcAlv");
               FMLCommonHandler.instance()
                  .getMinecraftServerInstance()
                  .func_71203_ab()
                  .transferPlayerToDimension(
                     (EntityPlayerMP)p,
                     DBCConfig.RevDim[by],
                     new WorldTeleporterDBCTelep(FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(DBCConfig.planetEarth))
                  );
               p.func_71023_q(1);
               double[] d = new double[]{50.0, 220.0, 50.0};
               switch (by) {
                  case 0:
                     d = DBCConfig.RevLocG;
                     break;
                  case 1:
                     d = DBCConfig.RevLocN;
                     break;
                  case 2:
                     d = DBCConfig.RevLocE;
               }

               ((EntityPlayerMP)p).field_71135_a.func_147364_a(d[0], d[1], d[2], (float)DBCConfig.RevAng[by][0], (float)DBCConfig.RevAng[by][1]);
               ((EntityPlayerMP)p).field_71135_a.func_147364_a(d[0], d[1] + 1.0, d[2], (float)DBCConfig.RevAng[by][0], (float)DBCConfig.RevAng[by][1]);
               mod_DragonBC.logger.info(p.func_70005_c_() + " revived!");
            }
         }

         if (wish == 102) {
         }

         if (wish == 101) {
            JRMCoreHDBC.reincarnate(p);
            mod_DragonBC.logger.info(p.func_70005_c_() + " choose to Reincarnate!");
         }

         if (wish == 199 && this.nbt(p, "pres").func_74762_e("DBCSenzu") == 0) {
            this.nbt(p, "pres").func_74768_a("DBCSenzu", 1);
            p.field_71071_by.func_70441_a(new ItemStack(ItemsDBC.ItemSenzu, DBCConfig.senzuFromKarin));
            p.field_71071_by.field_70459_e = true;
            this.closeInventoryChange(p);
         }

         if (wish == 202) {
            p.field_71071_by.func_70441_a(new ItemStack(ItemsDBC.KintounItem, 1));
            p.field_71071_by.field_70459_e = true;
            this.closeInventoryChange(p);
         }

         if (wish == 203) {
            p.field_71071_by.func_70441_a(new ItemStack(ItemsDBC.KintounBlackItem, 1));
            p.field_71071_by.field_70459_e = true;
            this.closeInventoryChange(p);
         }
      } else if (id == 2) {
         String[] s2 = s.split(";");
         int i = Integer.parseInt(s2[0]);
         float a = Float.parseFloat(s2[1]);
         ItemStack stack = new ItemStack(
            i == 0
               ? ItemsDBC.ItemWeightHandLeg
               : (i == 1 ? ItemsDBC.ItemWeightShell : (i == 2 ? ItemsDBC.ItemWeightShirt : (i == 3 ? ItemsDBC.ItemWeightCape : ItemsDBC.ItemWeightHeavySuit))),
            1,
            1
         );
         if (stack != null) {
            JRMCoreH.addItemWeightStats(stack, a);
            stack.func_77964_b(0);
            p.field_71071_by.func_70441_a(stack);
            p.field_71071_by.field_70459_e = true;
            this.closeInventoryChange(p);
         }
      } else if (id == 3) {
         String list = ";";

         for (int i = 0; i < server.field_71305_c.length; i++) {
            int d = server.field_71305_c[i].field_73011_w.field_76574_g;
            if (d == DBCConfig.otherWorld) {
               for (EntityPlayer target : server.field_71305_c[i].field_73010_i) {
                  if (target != null && target.field_71093_bK == DBCConfig.otherWorld) {
                     int alive = JRMCoreH.getByte(target, "jrmcAlv");
                     if (alive == 1) {
                        list = list + ";" + target.func_70005_c_();
                     }
                  }
               }
            }

            PD.sendTo(new DBCPwish(id, list), (EntityPlayerMP)p);
         }
      } else if (id == 4) {
         String[] s2 = s.split(";");
         int x = Integer.parseInt(s2[0]);
         int y = Integer.parseInt(s2[1]);
         int z = Integer.parseInt(s2[2]);
         TileEntity te = p.field_70170_p.func_147438_o(x, y, z);
         float g = Float.parseFloat(s2[3]);
         if (te instanceof ArtGravDevTileEntity) {
            ((ArtGravDevTileEntity)te).setGravity(g);
            p.field_70170_p.func_147471_g(x, y, z);
            te.func_70296_d();
         }
      } else if (id == 5) {
         JRMCoreH.StusEfcts(12, p, s.equals("1"));
      } else if (id == 6) {
         if (DBCConfig.CanWhisTeleport && p != null && p.field_70154_o == null && p.field_70153_n == null) {
            if (!JRMCoreH.isFused(p)) {
               boolean earth = p.field_71093_bK == DBCConfig.dimNullRealm;
               p.func_71023_q(1);
               double[] d = earth ? DBCConfig.WhisTeleportLocationBack : DBCConfig.WhisTeleportLocationNull;
               FMLCommonHandler.instance()
                  .getMinecraftServerInstance()
                  .func_71203_ab()
                  .transferPlayerToDimension(
                     (EntityPlayerMP)p,
                     earth ? DBCConfig.planetEarth : DBCConfig.dimNullRealm,
                     new WorldTeleporterDBCTelep(FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(DBCConfig.planetEarth))
                  );
               ((EntityPlayerMP)p).field_71135_a.func_147364_a(d[0], d[1], d[2], 0.0F, 0.0F);
               ((EntityPlayerMP)p).field_71135_a.func_147364_a(d[0], d[1] + 1.0, d[2], 0.0F, 0.0F);
               mod_DragonBC.logger.info(p.func_70005_c_() + " was Teleported by Master Whis!");
            } else {
               p.func_145747_a(new ChatComponentText("Unable to teleport while fused").func_150255_a(styleRed));
            }
         }
      } else if (id == 7) {
         double[] d = DBCConfig.WhisTeleportLocationNull;
         ((EntityPlayerMP)p).field_71135_a.func_147364_a(d[0], d[1], d[2], 0.0F, 0.0F);
         ((EntityPlayerMP)p).field_71135_a.func_147364_a(d[0], d[1] + 1.0, d[2], 0.0F, 0.0F);
         mod_DragonBC.logger.info(p.func_70005_c_() + " has fallen down in the Null Realm!");
      }
   }

   public void handleDBCtick(int dbctick, EntityPlayer Player) {
      if (dbctick != 0 && dbctick != 26 && (dbctick < 10 || dbctick > 25)) {
         if (dbctick == -2) {
            if (ExtendedPlayer.get(Player).inventory.func_70301_a(2) != null) {
               ExtendedPlayer.get(Player).inventory.func_70299_a(2, null);
               this.soundPowerUp(Player, "jinryuudragonbc:scouter.expl");
            }
         } else if (dbctick == -3) {
            short GTrnng = JRMCoreH.getShort(Player, "jrmcGTrnng");
            int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(Player);
            byte pwr = JRMCoreH.getByte(Player, "jrmcPwrtyp");
            byte rc = JRMCoreH.getByte(Player, "jrmcRace");
            byte cls = JRMCoreH.getByte(Player, "jrmcClass");
            int maxBody = JRMCoreH.stat(Player, 2, pwr, 2, PlyrAttrbts[2], rc, cls, 0.0F);
            int curBody = JRMCoreH.getInt(Player, "jrmcBdy");
            int maxEnergy = JRMCoreH.stat(Player, 5, pwr, 5, PlyrAttrbts[5], rc, cls, JRMCoreH.SklLvl_KiBs(Player, pwr));
            int curEnergy = JRMCoreH.getInt(Player, "jrmcEnrgy");
            if (GTrnng < DBCConfig.maxTrnExp && curBody > maxBody / 2 && curEnergy > maxEnergy / 2) {
               int all = curBody - 50;
               int alle = curEnergy - 50;
               if (all < 0) {
                  Player.func_70606_j(Player.func_110143_aJ() / 2.0F);
                  JRMCoreH.setInt(0, Player, "jrmcBdy");
               } else {
                  JRMCoreH.setInt(all, Player, "jrmcBdy");
               }

               if (alle < 0) {
                  alle = 0;
                  JRMCoreH.setByte(0, Player, "jrmcRelease");
               }

               if (!JRMCoreH.isInCreativeMode(Player)) {
                  JRMCoreH.setInt(alle, Player, "jrmcEnrgy");
               }

               JRMCoreH.setShort(GTrnng + 1, Player, "jrmcGTrnng");
               JRMCoreH.expPls(Player, 1);
            }
         } else if (dbctick == -6) {
            FMLCommonHandler.instance()
               .getMinecraftServerInstance()
               .func_71203_ab()
               .transferPlayerToDimension(
                  (EntityPlayerMP)Player,
                  DBCConfig.planetEarth,
                  new WorldTeleporterDBCTelep(FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(DBCConfig.planetEarth))
               );
            ((EntityPlayerMP)Player).field_71135_a.func_147364_a(77.5, 217.0, 10.5, 0.0F, 0.0F);
            Player.func_71023_q(1);
            JRMCoreH.setShort(2400, Player, "jrmcHTCTmO");
            String t = "dbc.timechamber.mustwaitaday";
            ChatStyle styl = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
            Player.func_145747_a(new ChatComponentTranslation(t, new Object[0]).func_150255_a(styl));
         } else if (dbctick == -100) {
            int dbcnum = this.nbt(Player, "pres").func_74762_e("DBCSenzu");
            PD.sendTo(new DBCPspacepod1(dbcnum), (EntityPlayerMP)Player);
         } else if (dbctick == -101) {
            int dbcnum = this.nbt(Player, "pres").func_74762_e("jrmcMaster");
            if (dbcnum == 0) {
               this.nbt(Player, "pres").func_74768_a("jrmcMaster", 1);
            }

            PD.sendTo(new DBCPtick(dbcnum + 2), (EntityPlayerMP)Player);
         }
      }
   }

   public void handleDBCspacepod1(int planetID, EntityPlayer p) {
      Side side = FMLCommonHandler.instance().getEffectiveSide();
      if (side == Side.SERVER) {
         Entity entity = null;
         if (planetID == 1) {
            if (p.field_70154_o != null) {
               entity = p.field_70154_o;
               entity.func_70106_y();
            }

            FMLCommonHandler.instance()
               .getMinecraftServerInstance()
               .func_71203_ab()
               .transferPlayerToDimension(
                  (EntityPlayerMP)p,
                  DBCConfig.planetNamek,
                  new WorldTeleporterDBCSpacePod01(FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(DBCConfig.planetNamek), entity)
               );
            p.func_71023_q(1);
         }

         if (planetID == 2) {
            if (p.field_70154_o != null) {
               entity = p.field_70154_o;
               entity.func_70106_y();
            }

            int plnt = DBCConfig.planetVegeta;
            if (!DBCConfig.plntVegeta) {
               plnt = DBCConfig.planetEarth;
            }

            FMLCommonHandler.instance()
               .getMinecraftServerInstance()
               .func_71203_ab()
               .transferPlayerToDimension(
                  (EntityPlayerMP)p,
                  plnt,
                  new WorldTeleporterDBCSpacePod01(FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(plnt), entity)
               );
            p.func_71023_q(1);
         }

         if (planetID == 3) {
            if (p.field_70154_o != null) {
               entity = p.field_70154_o;
               entity.func_70106_y();
            }

            FMLCommonHandler.instance()
               .getMinecraftServerInstance()
               .func_71203_ab()
               .transferPlayerToDimension(
                  (EntityPlayerMP)p,
                  DBCConfig.planetEarth,
                  new WorldTeleporterDBCSpacePod01(FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(DBCConfig.planetEarth), entity)
               );
            p.func_71023_q(1);
         }
      }
   }

   public void handleDBCchargepart(byte dbcchargepart, String dbcCharger, EntityPlayer p) {
      PD.sendToDimension(new DBCPchargepart(dbcchargepart, dbcCharger), p.field_71093_bK);
   }

   public void ki(NBTTagCompound tag, int e) {
      if (e == 2) {
         tag.func_74768_a("DBCKiCharge", tag.func_74762_e("DBCKiCharge") * e);
      }

      if (e == 3) {
         tag.func_74768_a("DBCKiCharge", tag.func_74762_e("DBCKiCharge") + tag.func_74762_e("DBCKiCharge") / (e - 1));
      }

      if (e == 4) {
         tag.func_74768_a("DBCKiCharge", tag.func_74762_e("DBCKiCharge") + tag.func_74762_e("DBCKiCharge") / (e - 1));
      }

      if (e == -4) {
         tag.func_74768_a("DBCKiCharge", tag.func_74762_e("DBCKiCharge") / 4);
      }

      if (e == -3) {
         tag.func_74768_a("DBCKiCharge", tag.func_74762_e("DBCKiCharge") / 3);
      }

      if (e == -2) {
         tag.func_74768_a("DBCKiCharge", tag.func_74762_e("DBCKiCharge") / 2);
      }
   }

   private boolean hasInstantTransformUnlocked(EntityPlayer p, boolean isRacial, int formID, int race) {
      double hasUnlocked = 0.0;
      if (JGConfigDBCFormMastery.FM_Enabled) {
         double unlockLevel = JGConfigDBCFormMastery.getDouble(
            race, formID + (isRacial ? 0 : JRMCoreH.trans[race].length), JGConfigDBCFormMastery.DATA_ID_INSTANT_TRANSFORM_UNLOCK
         );
         String message = "";
         if (unlockLevel <= -1.0) {
            hasUnlocked = -1.0;
            message = "Instant Transformation is not available for this Form!";
         } else if (unlockLevel > 0.0) {
            double masteryLevel = JRMCoreH.getFormMasteryValue(p, formID + (isRacial ? 0 : JRMCoreH.trans[race].length));
            if (masteryLevel < unlockLevel) {
               hasUnlocked = unlockLevel;
               String formName = isRacial ? JRMCoreH.trans[race][formID] : JRMCoreH.transNonRacial[formID];
               message = "Failed to Instant Transform! Required " + formName + " Mastery Level: " + hasUnlocked + " to be unlocked!";
            }
         }

         if (hasUnlocked != 0.0) {
            p.func_145747_a(new ChatComponentText(message).func_150255_a(styleRed));
            return false;
         }
      }

      return true;
   }

   public void handleDBCascend(byte dbcascend, EntityPlayer p) {
      Side side = FMLCommonHandler.instance().getEffectiveSide();
      if (side == Side.SERVER) {
         JGPlayerMP jgPlayer = new JGPlayerMP(p);
         NBTTagCompound nbt = this.nbt(p, "pres");
         jgPlayer.setNBT(nbt);
         boolean quickTransform = dbcascend == -1;
         int transformationMeter = quickTransform ? 100 : jgPlayer.getTransformationMeter();
         String StE = jgPlayer.getStatusEffects();
         boolean statusKaiokenOn = JRMCoreH.StusEfcts(13, StE);
         boolean statusMysticOn = JRMCoreH.StusEfcts(13, StE);
         boolean statusUltraInstinctOn = JRMCoreH.StusEfcts(19, StE);
         boolean statusGodOfDestructionOn = JRMCoreH.StusEfcts(20, StE);
         int kaiokenSkillLevel = JRMCoreH.SklLvl(8, p);
         boolean isKaiokenAvailable = JRMCoreH.PlyrSettingsB(nbt, 0)
            && kaiokenSkillLevel > 0
            && (DBCConfig.MysticKaiokenOn || !statusMysticOn)
            && !statusUltraInstinctOn
            && !statusGodOfDestructionOn;
         if (transformationMeter < 100 && !isKaiokenAvailable) {
            return;
         }

         jgPlayer.setTransformationMeter(0);
         int[] playerAttributes = jgPlayer.getAttributes();
         byte race = jgPlayer.getRace();
         byte classID = jgPlayer.getClassID();
         byte powerType = jgPlayer.getPowerType();
         int quickTransformKiLoss = -1;
         byte st = jgPlayer.getState();
         byte st2 = jgPlayer.getState2();
         if (quickTransform) {
            int curEnergy = jgPlayer.getEnergy();
            int maxEnergy = jgPlayer.getEnergyMax(race, classID, powerType, playerAttributes, JRMCoreH.SklLvl_KiBs(p, powerType));
            boolean costPercentage = true;
            double costMulti = DBCConfig.InstantTransformKiPercentageCost / 100.0;
            double energyCost = maxEnergy * costMulti + DBCConfig.InstantTransformKiCost;
            if (!(curEnergy >= energyCost) || !DBCConfig.InstantTransformOn) {
               p.func_145747_a(new ChatComponentText("Not Enough Ki to Transform Instantly! Cost: " + energyCost).func_150255_a(styleRed));
               return;
            }

            int remainingEnergy = curEnergy - (int)energyCost;
            quickTransformKiLoss = remainingEnergy;
         }

         int align = jgPlayer.getAlignment();
         int level = jgPlayer.getLevel(playerAttributes);
         String s1 = nbt.func_74779_i("jrmcSSltX");
         String s2 = nbt.func_74779_i("jrmcSSltY");
         int stUp = st;
         boolean isPainZero = nbt.func_74762_e("jrmcGyJ7dp") <= 0;
         int godSkillLevel = JRMCoreH.SklLvl(9, p);
         int mysticSkillLevel = JRMCoreH.SklLvl(10, p);
         int ultraInstinctSkillLevel = JRMCoreH.SklLvl(16, p);
         int godOfDestructionSkillLevel = JRMCoreH.SklLvl(18, p);
         boolean isMysticAvailable = JRMCoreH.PlyrSettingsB(nbt, 6) && mysticSkillLevel > 0;
         boolean isUIAvailable = JRMCoreH.PlyrSettingsB(nbt, 11) && ultraInstinctSkillLevel > 0 && JGConfigUltraInstinct.CONFIG_UI_LEVELS > 0;
         boolean isGoDAvailable = JRMCoreH.PlyrSettingsB(nbt, 16) && godOfDestructionSkillLevel > 0;
         boolean playerAscendNormal = JRMCoreH.PlyrSettingsI(p, 1, 0);
         boolean playerAscendGod = JRMCoreH.PlyrSettingsI(p, 1, 1);
         boolean playerAscendBlue = JRMCoreH.PlyrSettingsI(p, 1, 2);
         boolean playerAscendSS4 = JRMCoreH.PlyrSettingsI(p, 1, 3);
         boolean transformToOozaru = false;
         boolean tailMode = false;
         if (JRMCoreH.isRaceSaiyan(race)) {
            int n = 200;
            AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(
               p.field_70165_t - 200.0, 200.0, p.field_70161_v - 200.0, p.field_70165_t + 200.0, 255.0, p.field_70161_v + 200.0
            );
            List l = p.field_70170_p.func_72872_a(EntityEnergyFM.class, aabb);
            boolean pwbl = !l.isEmpty();
            tailMode = JRMCoreH.tailHas(JRMCoreH.getByte(p, "jrmcTlmd"));
            boolean night = JRMCoreH.StusEfcts(8, StE) || pwbl;
            boolean lookup = p.field_70125_A <= -90.0F;
            transformToOozaru = night && lookup && tailMode && st == 0;
         }

         boolean isNotFused = !JRMCoreH.isFused(p);
         boolean isFusionSelectedAvailable = JRMCoreH.PlyrSettingsB(nbt, 4) && isNotFused;
         String fusionMembers = nbt.func_74779_i("jrmcFuzion");
         boolean tryToUseUltraInstinct = false;
         int baseStateID = race == 4 ? 4 : 0;
         boolean isInBaseForm = st == baseStateID;
         boolean hasGodForm = godSkillLevel > (JRMCoreH.isRaceSaiyan(race) ? 1 : 0);
         if (!statusUltraInstinctOn) {
            if (!statusMysticOn && isMysticAvailable && mysticSkillLevel > 0 && !transformToOozaru) {
               boolean canUseMystic = isInBaseForm && DBCConfig.MysticKaiokenOn ? !statusUltraInstinctOn : st2 == 0;
               if (canUseMystic) {
                  if (quickTransformKiLoss != -1 && !DBCConfig.IsInstantTransformEnabled[2]) {
                     return;
                  }

                  if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, false, 1, race)) {
                     return;
                  }

                  if (JGConfigDBCFormMastery.FM_Enabled && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, "Mystic")) {
                     return;
                  }

                  StE = JRMCoreH.StusEfcts(13, StE, nbt, true);
                  jgPlayer.setStateAndTransformationMeter(stUp = race == 4 ? 4 : 0, 0);
                  JRMCoreH.PlyrSettingsRem(nbt, 6);
               } else {
                  p.func_145747_a(new ChatComponentText("Unable to use Mystic form in your current state!").func_150255_a(styleRed));
               }
            } else if (!statusGodOfDestructionOn && isGoDAvailable && !transformToOozaru) {
               boolean canUseGoD = isInBaseForm
                  && st2 == 0
                  && hasGodForm
                  && align <= JGConfigDBCGoD.CONFIG_GOD_MAX_ALIGNMENT
                  && JGConfigDBCGoD.CONFIG_GOD_ENABLED;
               if (canUseGoD) {
                  if (quickTransformKiLoss != -1 && !DBCConfig.IsInstantTransformEnabled[3]) {
                     return;
                  }

                  if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, false, 3, race)) {
                     return;
                  }

                  if (JGConfigDBCFormMastery.FM_Enabled && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, "GodOfDestruction")) {
                     return;
                  }

                  int levelRequirement = JGConfigDBCGoD.CONFIG_GOD_LEVEL_REQUIREMENT;
                  if (level <= levelRequirement && (byte)new Random().nextInt(100) > JGConfigDBCGoD.CONFIG_GOD_REGARDLESS_LEVEL_REQUIREMENT) {
                     p.func_145747_a(
                        new ChatComponentText(
                              "Your level was not enough to use God of Destruction ("
                                 + level
                                 + "/"
                                 + levelRequirement
                                 + ") (Failed secondary "
                                 + JGConfigDBCGoD.CONFIG_GOD_REGARDLESS_LEVEL_REQUIREMENT
                                 + "% success chance)"
                           )
                           .func_150255_a(styleRed)
                     );
                  } else {
                     StE = JRMCoreH.StusEfcts(20, StE, nbt, true);
                     stUp = baseStateID;
                     jgPlayer.setStateAndTransformationMeter(baseStateID, 0);
                     JRMCoreH.PlyrSettingsRem(nbt, 16);
                  }
               } else {
                  String message;
                  if (!JGConfigDBCGoD.CONFIG_GOD_ENABLED) {
                     message = "God of Destruction form is Disabled!";
                  } else if (align > JGConfigDBCGoD.CONFIG_GOD_MAX_ALIGNMENT) {
                     message = "Your Alignment is too high to use God of Destruction! Max Alignment: " + JGConfigDBCGoD.CONFIG_GOD_MAX_ALIGNMENT;
                  } else {
                     message = "Unable to use God of Destruction form in your current state!";
                  }

                  p.func_145747_a(new ChatComponentText(message).func_150255_a(styleYellow));
               }
            } else if (!statusMysticOn && isUIAvailable && isInBaseForm && !transformToOozaru && st2 < JGConfigUltraInstinct.CONFIG_UI_LEVELS && hasGodForm) {
               if (quickTransformKiLoss != -1 && !DBCConfig.IsInstantTransformEnabled[1]) {
                  return;
               }

               if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, false, 2, race)) {
                  return;
               }

               if (JGConfigDBCFormMastery.FM_Enabled && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, "UltraInstict")) {
                  return;
               }

               tryToUseUltraInstinct = true;
            } else if (!statusMysticOn && isFusionSelectedAvailable && !JRMCoreConfig.fuzn) {
               if (quickTransformKiLoss != -1) {
                  return;
               }

               String t = JRMCoreH.trlai("jrmc", "specskilldisabled");
               p.func_145747_a(new ChatComponentTranslation(t, new Object[]{JRMCoreH.DBCSkillNames[0]}).func_150255_a(styleYellow));
            } else if (!statusMysticOn && isFusionSelectedAvailable && JRMCoreConfig.fuzn) {
               if (quickTransformKiLoss != -1) {
                  return;
               }

               if (statusMysticOn) {
                  StE = JRMCoreH.StusEfcts(13, StE, nbt, false);
               }

               if (fusionMembers.equals(" ")) {
                  jgPlayer.setTransformationMeter(0);
                  int n = 3;
                  AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(
                     p.field_70165_t - 3.0, p.field_70163_u - 3.0, p.field_70161_v - 3.0, p.field_70165_t + 3.0, p.field_70163_u + 3.0, p.field_70161_v + 3.0
                  );
                  List entityList = p.field_70170_p.func_72872_a(EntityPlayer.class, aabb);
                  if (!entityList.isEmpty()) {
                     for (int i = 0; i < entityList.size(); i++) {
                        EntityPlayer pl2 = (EntityPlayer)entityList.get(i);
                        if (!pl2.func_70005_c_().equals(p.func_70005_c_())) {
                           NBTTagCompound nbt2 = this.nbt(pl2, "pres");
                           int rc2 = nbt2.func_74771_c("jrmcRace");
                           if (JRMCoreH.race_match(race, rc2)) {
                              boolean isNotFused2 = !JRMCoreH.isFused(pl2);
                              boolean f2 = JRMCoreH.PlyrSettingsB(nbt2, 4) && isNotFused2;
                              String StE2 = nbt2.func_74779_i("jrmcStatusEff");
                              boolean pl2HasAura = JRMCoreH.StusEfcts(4, StE2);
                              Random rand = new Random();
                              int random = rand.nextInt(100);
                              int fusionLevel1 = JRMCoreH.SklLvl(0, p);
                              int fusionLevel2 = JRMCoreH.SklLvl(0, pl2);
                              if (pl2HasAura) {
                                 if (30 + fusionLevel1 * 3 + fusionLevel2 * 3 < random) {
                                    JRMCoreH.PlyrSettingsRem(nbt2, 4);
                                    JRMCoreH.PlyrSettingsRem(nbt, 4);
                                    String t = JRMCoreH.trlai("dbc", "playersFuseFaild");
                                    p.func_145747_a(
                                       new ChatComponentTranslation(t, new Object[]{p.func_70005_c_(), pl2.func_70005_c_()}).func_150255_a(styleYellow)
                                    );
                                    pl2.func_145747_a(
                                       new ChatComponentTranslation(t, new Object[]{p.func_70005_c_(), pl2.func_70005_c_()}).func_150255_a(styleYellow)
                                    );
                                    mod_DragonBC.logger.info(p.func_70005_c_() + " and " + pl2.func_70005_c_() + " fusion failed!");
                                    nbt.func_74778_a("jrmcFuzion", "" + JRMCoreConfig.FznOverTime);
                                    nbt2.func_74778_a("jrmcFuzion", "" + JRMCoreConfig.FznOverTime);
                                    JRMCoreH.StusEfcts(10, nbt, false);
                                    JRMCoreH.StusEfcts(11, nbt, false);
                                    JRMCoreH.StusEfcts(10, nbt2, false);
                                    JRMCoreH.StusEfcts(11, nbt2, false);
                                    break;
                                 }

                                 if (f2) {
                                    JRMCoreH.PlyrSettingsRem(nbt2, 4);
                                    JRMCoreH.PlyrSettingsRem(nbt, 4);
                                    StE = JRMCoreH.StusEfcts(10, StE, nbt, true);
                                    StE2 = JRMCoreH.StusEfcts(11, StE2, nbt2, true);
                                    String fznn = JRMCoreHDBC.f_namgen(p.func_70005_c_(), pl2.func_70005_c_());
                                    int FznTime = JRMCoreConfig.FznTime
                                       - (
                                          JRMCoreH.isRaceArcosian(race) && stUp > 4
                                             ? (stUp - 4) * 5
                                             : (JRMCoreH.isRaceHumanOrNamekian(race) ? stUp * 5 : stUp * 2)
                                       );
                                    nbt2.func_74778_a("jrmcFuzion", p.func_70005_c_() + "," + pl2.func_70005_c_() + "," + FznTime);
                                    nbt.func_74778_a("jrmcFuzion", p.func_70005_c_() + "," + pl2.func_70005_c_() + "," + FznTime);
                                    mod_DragonBC.logger.info(p.func_70005_c_() + " and " + pl2.func_70005_c_() + " fused to " + fznn + "!");
                                    String t = JRMCoreH.trlai("dbc", "playersFused");
                                    p.func_145747_a(
                                       new ChatComponentTranslation(t, new Object[]{p.func_70005_c_(), pl2.func_70005_c_(), fznn}).func_150255_a(styleYellow)
                                    );
                                    pl2.func_145747_a(
                                       new ChatComponentTranslation(t, new Object[]{p.func_70005_c_(), pl2.func_70005_c_(), fznn}).func_150255_a(styleYellow)
                                    );
                                    this.soundPowerUp(p, "jinryuudragonbc:DBC.fusefin");
                                    StE = JRMCoreH.StusEfcts(3, StE, nbt, false);
                                    StE = JRMCoreH.StusEfcts(5, StE, nbt, false);
                                    StE = JRMCoreH.StusEfcts(4, StE, nbt, false);
                                    StE2 = JRMCoreH.StusEfcts(3, StE2, nbt2, false);
                                    StE2 = JRMCoreH.StusEfcts(5, StE2, nbt2, false);
                                    StE2 = JRMCoreH.StusEfcts(4, StE2, nbt2, false);
                                    nbt.func_74774_a("jrmcState2", (byte)0);
                                    nbt2.func_74774_a("jrmcState2", (byte)0);
                                    String[] PlyrSkills = JRMCoreH.PlyrSkills(p);
                                    byte pwr = nbt.func_74771_c("jrmcPwrtyp");
                                    byte rce = nbt.func_74771_c("jrmcRace");
                                    byte cls = nbt.func_74771_c("jrmcClass");
                                    int maxBody = JRMCoreH.stat(p, 2, pwr, 2, playerAttributes[2], rce, cls, 0.0F);
                                    int ki = JRMCoreH.stat(p, 5, pwr, 5, playerAttributes[5], rce, cls, JRMCoreH.SklLvl_KiBs(PlyrSkills, pwr));
                                    playerAttributes = JRMCoreH.PlyrAttrbts(pl2);
                                    PlyrSkills = JRMCoreH.PlyrSkills(pl2);
                                    int maxBodyF = JRMCoreH.stat(pl2, 2, pwr, 2, playerAttributes[2], rce, cls, 0.0F);
                                    int kiF = JRMCoreH.stat(pl2, 5, pwr, 5, playerAttributes[5], rce, cls, JRMCoreH.SklLvl_KiBs(PlyrSkills, pwr));
                                    double curBody = nbt.func_74762_e("jrmcBdy");
                                    double curEn = nbt.func_74762_e("jrmcEnrgy");
                                    nbt.func_74768_a("jrmcBdy", (int)(curBody / maxBody * maxBodyF));
                                    nbt.func_74768_a("jrmcEnrgy", (int)(curEn / ki * kiF));
                                    break;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            } else if (!statusMysticOn && s1 != null && (!isKaiokenAvailable || transformToOozaru)) {
               if (statusMysticOn) {
                  StE = JRMCoreH.StusEfcts(13, StE, nbt, false);
               }

               if (statusGodOfDestructionOn) {
                  StE = JRMCoreH.StusEfcts(20, StE, nbt, false);
               }

               if (statusUltraInstinctOn) {
                  StE = JRMCoreH.StusEfcts(19, StE, nbt, false);
               }

               int racialSkillLevel = JRMCoreH.SklLvlX(1, s1);
               if (JRMCoreH.isRaceSaiyan(race)) {
                  int s4ft = JRMCoreH.getInt(p, "jrmcAfGFtStFT");
                  int ssgHelp = 0;
                  int n = 3;
                  AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(
                     p.field_70165_t - 3.0, p.field_70163_u - 3.0, p.field_70161_v - 3.0, p.field_70165_t + 3.0, p.field_70163_u + 3.0, p.field_70161_v + 3.0
                  );
                  List list = p.field_70170_p.func_72872_a(EntityPlayer.class, aabb);
                  if (!list.isEmpty()) {
                     for (int i = 0; i < list.size(); i++) {
                        EntityPlayer pl = (EntityPlayer)list.get(i);
                        if (!pl.func_70005_c_().equals(p.func_70005_c_())) {
                           String StE2 = JRMCoreH.getString(pl, "jrmcStatusEff");
                           int align2 = JRMCoreH.getByte(pl, "jrmcAlign");
                           boolean a = JRMCoreH.StusEfcts(4, StE2);
                           int sg = JRMCoreH.getInt(pl, "jrmcGodStrain");
                           int s = JRMCoreH.getByte(pl, "jrmcState");
                           int rcpl = JRMCoreH.getByte(pl, "jrmcRace");
                           boolean state = s == 1 || s == 4 || s == 0;
                           if (state && a && sg <= 0 && JRMCoreH.Algnmnt(align2) == 0 && JRMCoreH.isRaceSaiyan(rcpl)) {
                              ssgHelp++;
                           }
                        }
                     }
                  }

                  if (ssgHelp == 0) {
                     aabb = AxisAlignedBB.func_72330_a(
                        p.field_70165_t - 3.0,
                        p.field_70163_u - 3.0,
                        p.field_70161_v - 3.0,
                        p.field_70165_t + 3.0,
                        p.field_70163_u + 3.0,
                        p.field_70161_v + 3.0
                     );
                     List listMasters = p.field_70170_p.func_72872_a(EntityDBCKami.class, aabb);
                     if (!listMasters.isEmpty()) {
                        for (int i = 0; i < listMasters.size(); i++) {
                           EntityDBCKami entity = (EntityDBCKami)listMasters.get(i);
                           if (entity instanceof EntityMasterGoku
                              || entity instanceof EntityMasterGohan
                              || entity instanceof EntityMasterTrunksFuture
                              || entity instanceof EntityMasterVegeta) {
                              ssgHelp++;
                           }
                        }
                     }
                  }

                  if (transformToOozaru) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 7, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][7])) {
                        return;
                     }

                     stUp = 7;
                     jgPlayer.setStateAndTransformationMeter(7, 0);
                  } else if (racialSkillLevel >= 2 && st == 7) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 8, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][8])) {
                        return;
                     }

                     stUp = 8;
                     jgPlayer.setStateAndTransformationMeter(8, 0);
                  } else if (racialSkillLevel >= 8 && st == 8 && tailMode && DBCConfig.DBGT) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 14, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][14])) {
                        return;
                     }

                     stUp = 14;
                     jgPlayer.setStateAndTransformationMeter(14, 0);
                     JRMCoreH.setInt(1, p, "jrmcAfGFtStFT");
                  } else if (JRMCoreHDBC.DBCgetConfigGodform() && st == 0 && ssgHelp >= DBCConfig.SSGHelp && JRMCoreH.Algnmnt(align) == 0) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 11, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][11])) {
                        return;
                     }

                     stUp = 11;
                     jgPlayer.setStateAndTransformationMeter(11, 0);
                     int gplvl = JRMCoreH.getPlayerLevel(playerAttributes);
                     int gp = 0;
                     if (!list.isEmpty()) {
                        int hlprs = 0;

                        for (int i = 0; i < list.size(); i++) {
                           EntityPlayer pl = (EntityPlayer)list.get(i);
                           if (!pl.func_70005_c_().equals(p.func_70005_c_())) {
                              int align2 = JRMCoreH.getByte(pl, "jrmcAlign");
                              int sg = JRMCoreH.getInt(pl, "jrmcGodStrain");
                              int s = JRMCoreH.getByte(pl, "jrmcState");
                              boolean state = s == 1 || s == 4 || s == 0;
                              int rcpl = JRMCoreH.getByte(pl, "jrmcRace");
                              if (state && sg <= 0 && JRMCoreH.Algnmnt(align2) == 0 && JRMCoreH.isRaceSaiyan(rcpl)) {
                                 gp += JRMCoreH.getPlayerLevel(JRMCoreH.PlyrAttrbts(pl));
                                 double reqMulti = 1.0;
                                 if (JGConfigDBCFormMastery.FM_Enabled) {
                                    int formID = JRMCoreH.getFormID(JRMCoreH.trans[race][11], race);
                                    double masteryLevel = JRMCoreH.getFormMasteryValue(pl, formID);
                                    reqMulti = (float)JGConfigDBCFormMastery.getCostMulti(
                                       masteryLevel, race, formID, JGConfigDBCFormMastery.DATA_ID_GOD_RITUAL_STRAIN_COST_MULTI
                                    );
                                 }

                                 JRMCoreH.setInt((int)(DBCConfig.StrainGod * reqMulti), pl, "jrmcGodStrain");
                                 JRMCoreH.setInt(0, pl, "jrmcEnrgy");
                                 hlprs++;
                              }
                           }
                        }

                        gp = (hlprs == 0 ? gplvl : gp) / DBCConfig.SSGHelp;
                        float gpf = (float)gp / gplvl;
                        gpf = gpf > 2.0F ? 2.0F : (gpf < 0.2F ? 0.2F : gpf);
                        double reqMulti = 1.0;
                        if (JGConfigDBCFormMastery.FM_Enabled) {
                           int formID = JRMCoreH.getFormID(JRMCoreH.trans[race][11], race);
                           double masteryLevel = JRMCoreH.getFormMasteryValue(p, formID);
                           reqMulti = (float)JGConfigDBCFormMastery.getCostMulti(
                              masteryLevel, race, formID, JGConfigDBCFormMastery.DATA_ID_GOD_RITUAL_TIMER_MULTI
                           );
                        }

                        gp = (int)(120.0F * gpf * reqMulti);
                     }

                     JRMCoreH.setInt(gp, p, "jrmcGodPwr");
                  } else if (JRMCoreHDBC.DBCgetConfigGodform()
                     && JRMCoreHDBC.godformslr(racialSkillLevel)
                     && (st == 0 || st == 9)
                     && godSkillLevel > 1
                     && playerAscendBlue) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 10, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][10])) {
                        return;
                     }

                     stUp = 10;
                     jgPlayer.setStateAndTransformationMeter(10, 0);
                  } else if (racialSkillLevel >= 6
                     && JRMCoreHDBC.DBCgetConfigGodform()
                     && JRMCoreHDBC.godformslr(racialSkillLevel)
                     && godSkillLevel > 2
                     && (playerAscendBlue || playerAscendGod)
                     && st == 10) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 15, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][15])) {
                        return;
                     }

                     stUp = 15;
                     jgPlayer.setStateAndTransformationMeter(15, 0);
                  } else if (JRMCoreHDBC.DBCgetConfigGodform() && JRMCoreHDBC.godformslr(racialSkillLevel) && godSkillLevel > 0 && playerAscendGod && st == 0) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 9, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][9])) {
                        return;
                     }

                     stUp = 9;
                     jgPlayer.setStateAndTransformationMeter(9, 0);
                  } else if (JRMCoreHDBC.DBCgetConfigGodform() && JRMCoreHDBC.godformslr(racialSkillLevel) && godSkillLevel > 1 && playerAscendGod && st == 9) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 10, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][10])) {
                        return;
                     }

                     stUp = 10;
                     jgPlayer.setStateAndTransformationMeter(10, 0);
                  } else if (racialSkillLevel >= 8 && st == 0 && playerAscendSS4 && s4ft > 0 && tailMode && DBCConfig.DBGT) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 14, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][14])) {
                        return;
                     }

                     stUp = 14;
                     jgPlayer.setStateAndTransformationMeter(14, 0);
                  } else if (racialSkillLevel >= 5 && st == 0) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 4, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][4])) {
                        return;
                     }

                     stUp = 4;
                     jgPlayer.setStateAndTransformationMeter(4, 0);
                  } else if (racialSkillLevel >= 2 && st == 0) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 1, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][1])) {
                        return;
                     }

                     stUp = 1;
                     jgPlayer.setStateAndTransformationMeter(1, 0);
                  } else if (racialSkillLevel >= 6 && st == 4 && playerAscendNormal) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 5, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][5])) {
                        return;
                     }

                     stUp = 5;
                     jgPlayer.setStateAndTransformationMeter(5, 0);
                  } else if (racialSkillLevel < 3 || st != 1 && st != 4) {
                     if (racialSkillLevel >= 4 && st == 2) {
                        if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 3, race)) {
                           return;
                        }

                        if (JGConfigDBCFormMastery.FM_Enabled
                           && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][3])) {
                           return;
                        }

                        stUp = 3;
                        jgPlayer.setStateAndTransformationMeter(3, 0);
                     } else if (racialSkillLevel >= 7 && st == 5) {
                        if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 6, race)) {
                           return;
                        }

                        if (JGConfigDBCFormMastery.FM_Enabled
                           && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][6])) {
                           return;
                        }

                        stUp = 6;
                        jgPlayer.setStateAndTransformationMeter(6, 0);
                     } else {
                        quickTransformKiLoss = -1;
                     }
                  } else {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 2, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][2])) {
                        return;
                     }

                     stUp = 2;
                     jgPlayer.setStateAndTransformationMeter(2, 0);
                  }
               } else if (JRMCoreH.isRaceArcosian(race)) {
                  String bns = JRMCoreH.StusEfcts[6];
                  if (StE.contains(bns)) {
                     nbt.func_74778_a("jrmcStatusEff", StE.replace(bns, ""));
                  }

                  nbt.func_74774_a("jrmcTlmd", (byte)0);
                  if (racialSkillLevel >= 1 && st == 0) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 1, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][1])) {
                        return;
                     }

                     stUp = 1;
                     jgPlayer.setStateAndTransformationMeter(1, 0);
                  } else if (racialSkillLevel >= 1 && st == 1) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 2, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][2])) {
                        return;
                     }

                     stUp = 2;
                     jgPlayer.setStateAndTransformationMeter(2, 0);
                  } else if (racialSkillLevel >= 1 && st == 2) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 3, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][3])) {
                        return;
                     }

                     stUp = 3;
                     jgPlayer.setStateAndTransformationMeter(3, 0);
                  } else if (racialSkillLevel >= 1 && st == 3) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 4, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][4])) {
                        return;
                     }

                     stUp = 4;
                     jgPlayer.setStateAndTransformationMeter(4, 0);
                  } else if (racialSkillLevel >= 7 && st == 4 && godSkillLevel > 0 && playerAscendGod) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 7, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][7])) {
                        return;
                     }

                     stUp = 7;
                     jgPlayer.setStateAndTransformationMeter(7, 0);
                  } else if (racialSkillLevel >= 7 && st == 4 && playerAscendNormal) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 6, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][6])) {
                        return;
                     }

                     stUp = 6;
                     jgPlayer.setStateAndTransformationMeter(6, 0);
                  } else if (racialSkillLevel >= 4 && st == 4) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 5, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][5])) {
                        return;
                     }

                     stUp = 5;
                     jgPlayer.setStateAndTransformationMeter(5, 0);
                  } else {
                     quickTransformKiLoss = -1;
                  }
               } else if (JRMCoreH.isRaceMajin(race)) {
                  if (JRMCoreHDBC.DBCgetConfigGodform()
                     && JRMCoreHDBC.hasMajinGodRacialRequirement(racialSkillLevel)
                     && st == 0
                     && godSkillLevel > 0
                     && playerAscendGod) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 4, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][4])) {
                        return;
                     }

                     stUp = 4;
                     jgPlayer.setStateAndTransformationMeter(4, 0);
                  } else if (racialSkillLevel >= 6 && st == 0 && playerAscendNormal) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 3, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][3])) {
                        return;
                     }

                     stUp = 3;
                     jgPlayer.setStateAndTransformationMeter(3, 0);
                  } else if (racialSkillLevel >= 3 && st == 0) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 1, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][1])) {
                        return;
                     }

                     stUp = 1;
                     jgPlayer.setStateAndTransformationMeter(1, 0);
                  } else if (racialSkillLevel >= 4 && st == 1) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 2, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][2])) {
                        return;
                     }

                     stUp = 2;
                     jgPlayer.setStateAndTransformationMeter(2, 0);
                  } else {
                     quickTransformKiLoss = -1;
                  }
               } else if (JRMCoreH.isRaceHumanOrNamekian(race)) {
                  if (JRMCoreHDBC.DBCgetConfigGodform() && JRMCoreHDBC.godformslrHN(racialSkillLevel) && st == 0 && godSkillLevel > 0 && playerAscendGod) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 3, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][3])) {
                        return;
                     }

                     stUp = 3;
                     jgPlayer.setStateAndTransformationMeter(3, 0);
                  } else if (racialSkillLevel >= 3 && st == 0 && playerAscendNormal) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 1, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][1])) {
                        return;
                     }

                     stUp = 1;
                     jgPlayer.setStateAndTransformationMeter(1, 0);
                  } else if (racialSkillLevel >= 2 && st == 0) {
                     if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, true, 2, race)) {
                        return;
                     }

                     if (JGConfigDBCFormMastery.FM_Enabled
                        && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, JRMCoreH.trans[race][2])) {
                        return;
                     }

                     stUp = 2;
                     jgPlayer.setStateAndTransformationMeter(2, 0);
                  } else {
                     quickTransformKiLoss = -1;
                  }
               }
            } else if (isKaiokenAvailable && kaiokenSkillLevel > st2) {
               if (quickTransformKiLoss != -1 && !DBCConfig.IsInstantTransformEnabled[0]) {
                  return;
               }

               if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, false, 0, race)) {
                  return;
               }

               if (JGConfigDBCFormMastery.FM_Enabled && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, "Kaioken")) {
                  return;
               }

               if (JRMCoreH.TransKaiDmg.length - 1 > st2) {
                  nbt.func_74774_a("jrmcState2", (byte)(st2 + 1));
                  p.func_145747_a(new ChatComponentText("Kaioken " + JRMCoreH.TransKaiNms[st2 + 1]).func_150255_a(styleGold));
               }
            }
         } else if (isUIAvailable
            && st == (race == 4 ? 4 : 0)
            && !transformToOozaru
            && st2 < JGConfigUltraInstinct.CONFIG_UI_LEVELS
            && godSkillLevel > (JRMCoreH.isRaceSaiyan(race) ? 1 : 0)) {
            if (quickTransformKiLoss != -1 && !DBCConfig.IsInstantTransformEnabled[1]) {
               return;
            }

            if (DBCConfig.InstantTransformOn && quickTransform && !this.hasInstantTransformUnlocked(p, false, 2, race)) {
               return;
            }

            if (JGConfigDBCFormMastery.FM_Enabled && !JRMCoreH.hasRequiredFormMasteries(p, JRMCoreH.getFormMasteryData(p), race, "UltraInstict")) {
               return;
            }

            tryToUseUltraInstinct = true;
         }

         if (tryToUseUltraInstinct) {
            if (quickTransformKiLoss != -1 && !DBCConfig.IsInstantTransformEnabled[1]) {
               return;
            }

            if (isPainZero) {
               boolean uiCheckEachLevel = JGConfigUltraInstinct.CONFIG_UI_CHECK_SKIP_REQUIREMENT_EACH_LEVEL == 0;
               byte experiencePainMode = JGConfigUltraInstinct.CONFIG_UI_EXPERIENCE_PAIN_MODE;
               boolean canIgnorePain = experiencePainMode <= 0 ? true : nbt.func_74771_c("jrmcUIWasInPain") > 0;
               jgPlayer.setStateAndTransformationMeter(stUp = JRMCoreH.getBaseForm(race), 0);
               boolean succeded = false;
               boolean skippable = true;
               Random rand = new Random();
               byte random = (byte)rand.nextInt(100);
               byte randomLast = random;
               int curBody = jgPlayer.getHealth();
               int maxBody = jgPlayer.getHealthMax(race, classID, powerType, playerAttributes);
               int[] levelRequirement = JGConfigUltraInstinct.CONFIG_UI_LEVEL_REQUIREMENT;
               byte[] healthPercentage = JGConfigUltraInstinct.CONFIG_UI_HEALTH_PERCENTAGE;
               double reqMulti = 1.0;
               if (JGConfigDBCFormMastery.FM_Enabled) {
                  int formID = JRMCoreH.getFormID("UltraInstict", race);
                  double masteryLevel = JRMCoreH.getFormMasteryValue(p, formID);
                  reqMulti = JGConfigDBCFormMastery.getCostMulti(masteryLevel, race, formID, JGConfigDBCFormMastery.DATA_ID_UI_HEALTH_REQ_MULTI);
               }

               if (uiCheckEachLevel) {
                  while (
                     st2 < JGConfigUltraInstinct.CONFIG_UI_LEVELS
                        && ultraInstinctSkillLevel > st2
                        && skippable
                        && (level > levelRequirement[st2] || random <= JGConfigUltraInstinct.CONFIG_UI_REGARDLESS_LEVEL_REQUIREMENT[st2])
                        && ((int)(healthPercentage[st2] * reqMulti) >= 100 || curBody / (maxBody / 100) <= (int)(healthPercentage[st2] * reqMulti))
                        && (!JGConfigUltraInstinct.CONFIG_UI_HAIR_WHITE[st2] || canIgnorePain)
                  ) {
                     if (JGConfigUltraInstinct.CONFIG_UI_HAIR_WHITE[st2] && experiencePainMode == 2) {
                        nbt.func_74774_a("jrmcUIWasInPain", (byte)0);
                     }

                     succeded = true;
                     randomLast = random;
                     random = (byte)rand.nextInt(100);
                     skippable = JGConfigUltraInstinct.CONFIG_UI_SKIP[st2];
                     st2++;
                     int UI_HighestStateReached = nbt.func_74771_c("jrmcUIStateReach");
                     if (st2 > UI_HighestStateReached) {
                        nbt.func_74774_a("jrmcUIStateReach", st2);
                     }
                  }
               } else {
                  boolean[] conditionsMet = new boolean[JGConfigUltraInstinct.CONFIG_UI_LEVELS];

                  for (int i = 0; i < conditionsMet.length; i++) {
                     conditionsMet[i] = false;
                  }

                  while (st2 < JGConfigUltraInstinct.CONFIG_UI_LEVELS && ultraInstinctSkillLevel > st2 && skippable) {
                     skippable = JGConfigUltraInstinct.CONFIG_UI_SKIP[st2];
                     conditionsMet[st2] = st2 < JGConfigUltraInstinct.CONFIG_UI_LEVELS
                        && ultraInstinctSkillLevel > st2
                        && (level > levelRequirement[st2] || random <= JGConfigUltraInstinct.CONFIG_UI_REGARDLESS_LEVEL_REQUIREMENT[st2])
                        && ((int)(healthPercentage[st2] * reqMulti) >= 100 || curBody / (maxBody / 100) <= (int)(healthPercentage[st2] * reqMulti))
                        && (!JGConfigUltraInstinct.CONFIG_UI_HAIR_WHITE[st2] || canIgnorePain);
                     randomLast = random;
                     random = (byte)rand.nextInt(100);
                     st2++;
                  }

                  int conditionCount = conditionsMet.length;

                  for (int i = conditionCount - 1; i >= 0; i--) {
                     if (conditionsMet[i]) {
                        st2 = (byte)i;
                        if (JGConfigUltraInstinct.CONFIG_UI_HAIR_WHITE[st2] && experiencePainMode == 2) {
                           nbt.func_74774_a("jrmcUIWasInPain", (byte)0);
                        }

                        succeded = true;
                        skippable = JGConfigUltraInstinct.CONFIG_UI_SKIP[st2];
                        st2++;
                        int UI_HighestStateReached = nbt.func_74771_c("jrmcUIStateReach");
                        if (st2 > UI_HighestStateReached) {
                           nbt.func_74774_a("jrmcUIStateReach", st2);
                        }
                        break;
                     }
                  }
               }

               if (succeded) {
                  StE = JRMCoreH.StusEfcts(19, StE, nbt, true);
                  jgPlayer.setState2(st2);
                  p.func_145747_a(
                     new ChatComponentText(JGConfigUltraInstinct.CONFIG_UI_CHAT_SUCCEED[st2 - 1].replace("@p", p.getDisplayName())).func_150255_a(styleYellow)
                  );
               }

               if (st2 <= JGConfigUltraInstinct.CONFIG_UI_LEVELS) {
                  if (st2 == JGConfigUltraInstinct.CONFIG_UI_LEVELS) {
                     st2--;
                  }

                  if (ultraInstinctSkillLevel > st2) {
                     if (level <= levelRequirement[st2] && randomLast > JGConfigUltraInstinct.CONFIG_UI_REGARDLESS_LEVEL_REQUIREMENT[st2]) {
                        p.func_145747_a(
                           new ChatComponentText(
                                 "Your level was not enough to use an Ultra Instinct Lvl ("
                                    + level
                                    + "/"
                                    + levelRequirement[st2]
                                    + ") (Failed secondary "
                                    + JGConfigUltraInstinct.CONFIG_UI_REGARDLESS_LEVEL_REQUIREMENT[st2]
                                    + "% success chance)"
                              )
                              .func_150255_a(styleRed)
                        );
                     } else if ((int)(healthPercentage[st2] * reqMulti) < 100 && curBody / (maxBody / 100) > (int)(healthPercentage[st2] * reqMulti)) {
                        p.func_145747_a(
                           new ChatComponentText(
                                 "Your health was not low enough to use an Ultra Instinct Level (" + (int)(healthPercentage[st2] * reqMulti) + "% or below)"
                              )
                              .func_150255_a(styleRed)
                        );
                     } else if (JGConfigUltraInstinct.CONFIG_UI_HAIR_WHITE[st2] && !canIgnorePain) {
                        p.func_145747_a(
                           new ChatComponentText("You must experience 'Pain' Status Effect before using Complete Ultra Instinct").func_150255_a(styleRed)
                        );
                     }
                  }

                  if (st2 == JGConfigUltraInstinct.CONFIG_UI_LEVELS) {
                     st2++;
                  }
               }
            } else {
               p.func_145747_a(new ChatComponentText("You can't use Ultra Instinct while in Pain").func_150255_a(styleRed));
            }
         }

         if (!fusionMembers.equals(" ") && !isKaiokenAvailable) {
            String[] fusionParticipants = fusionMembers.split(",");
            if (fusionParticipants.length == 3) {
               boolean isParticipentCommandSender = fusionParticipants[0].equalsIgnoreCase(p.func_70005_c_());
               if (isParticipentCommandSender) {
                  EntityPlayer pl2 = p.field_70170_p.func_72924_a(fusionParticipants[1]);
                  boolean isPlayer2Null = pl2 == null;
                  int fusionTime = Integer.parseInt(fusionParticipants[2])
                     - (JRMCoreH.isRaceArcosian(race) && stUp > 4 ? (stUp - 4) * 5 : (JRMCoreH.isRaceHumanOrNamekian(race) ? stUp * 5 : stUp * 2));
                  if (!isPlayer2Null) {
                     NBTTagCompound nbt2 = JRMCoreH.nbt(pl2);
                     nbt.func_74778_a("jrmcFuzion", fusionParticipants[0] + "," + fusionParticipants[1] + "," + fusionTime);
                     nbt2.func_74778_a("jrmcFuzion", fusionParticipants[0] + "," + fusionParticipants[1] + "," + fusionTime);
                  }
               }
            }
         }

         st2 = nbt.func_74771_c("jrmcState2");
         if (st2 > 0 && isKaiokenAvailable) {
            if (quickTransformKiLoss != -1 && !DBCConfig.IsInstantTransformEnabled[0]) {
               return;
            }

            int skl = JRMCoreH.SklLvlX(1, JRMCoreH.getString(p, "jrmcSSltX"));
            int strn = JRMCoreH.getInt(p, "jrmcStrain");
            int strnTmp = nbt.func_74762_e("jrmcStrainTemp");
            int strnAct = nbt.func_74762_e("jrmcStrainActv");
            int curBody = jgPlayer.getHealth();
            int maxBody = jgPlayer.getHealthMax(race, classID, powerType, playerAttributes);
            double kaiokenCost = JRMCoreH.KaiKCost(p) * maxBody;
            int ske = JRMCoreH.SklLvl(4, p);
            int r = new Random().nextInt(100);
            float rd = 0.5F + 0.05F * (10 - ske);
            if (kaiokenSkillLevel * 3 > r) {
               kaiokenCost = 0.0;
            } else if (kaiokenSkillLevel * 5 > r) {
               kaiokenCost *= rd;
            }

            int curen = (int)(curBody - kaiokenCost < 1.0 ? 1.0 : curBody - kaiokenCost);
            if (!JRMCoreH.isInCreativeMode(p)) {
               JRMCoreH.setInt(curen, p, "jrmcBdy");
            }

            float strainMulti = 1.0F;
            float strainMulti2 = 1.0F;
            if (JGConfigDBCFormMastery.FM_Enabled) {
               int kkID = JRMCoreH.getFormID("Kaioken", race);
               double kkMasteryLevel = JRMCoreH.getFormMasteryValue(p, kkID);
               strainMulti = (float)JGConfigDBCFormMastery.getCostMulti(kkMasteryLevel, race, kkID, JGConfigDBCFormMastery.DATA_ID_KAIOKEN_STRAIN_TEMP_MULTI);
               strainMulti2 = (float)JGConfigDBCFormMastery.getCostMulti(kkMasteryLevel, race, kkID, JGConfigDBCFormMastery.DATA_ID_KAIOKEN_STRAIN_ACTIVE_MULTI);
            }

            if (!JRMCoreConfig.sskai) {
               int strainTemp = (
                     JRMCoreH.isRaceSaiyan(race) && stUp == 0 ? 1 : (race == 4 && stUp <= 4 ? 1 : (JRMCoreH.isRaceHumanOrNamekian(race) && stUp <= 1 ? 1 : 12))
                  )
                  + (20 - kaiokenSkillLevel);
               strainTemp = (int)(strainTemp * strainMulti);
               strainTemp += strnTmp;
               JRMCoreH.setInt(strainTemp, p, "jrmcStrainTemp");
            }

            if (curen <= maxBody * 0.1F && !JRMCoreH.isInCreativeMode(p)) {
               jgPlayer.setState(JRMCoreH.getBaseForm(race));
               jgPlayer.setState2(0);
               JRMCoreH.setInt(curen, p, "jrmcBdy");
            }

            if (!JRMCoreConfig.sskai) {
               if (strnAct <= 0) {
                  int sa = 100 + kaiokenSkillLevel * 6 - MathHelper.func_76123_f(JRMCoreH.KaiKFBal(race, st, st2, skl, strn)) * 2 - st2 * 2;
                  sa = (int)(sa * strainMulti);
                  JRMCoreH.setInt(sa, p, "jrmcStrainActv");
               } else {
                  strnAct = (int)(strnAct - strainMulti2 * (MathHelper.func_76123_f(JRMCoreH.KaiKFBal(race, st, st2, skl, strn)) * 2 - st2 * 2));
                  JRMCoreH.setInt(strnAct, p, "jrmcStrainActv");
               }
            }
         }

         if (quickTransformKiLoss != -1 && !JRMCoreH.isInCreativeMode(p)) {
            JRMCoreH.setInt(quickTransformKiLoss, p, "jrmcEnrgy");
         }
      }
   }

   public void handleDBCdescend(byte dbcdescend, EntityPlayer p) {
      NBTTagCompound nbt = this.nbt(p, "pres");
      byte race = nbt.func_74771_c("jrmcRace");
      int state = nbt.func_74771_c("jrmcState");
      int state2 = nbt.func_74771_c("jrmcState2");
      String StE = nbt.func_74779_i("jrmcStatusEff");
      boolean statusMysticOn = JRMCoreH.StusEfcts(13, StE);
      boolean statusGoDOn = JRMCoreH.StusEfcts(20, StE);
      boolean isKaiokenSelected = JRMCoreH.PlyrSettingsB(nbt, 0);
      int baseState = JRMCoreH.isRaceArcosian(race) && state >= 4 ? 4 : 0;
      boolean lowerState2 = state > baseState
         ? (isKaiokenSelected ? state2 > 0 : false)
         : (isKaiokenSelected ? state2 > 0 : !statusGoDOn && !statusMysticOn && state2 > 0);
      if (lowerState2) {
         boolean isKaiokenOn = JRMCoreH.StusEfcts(5, StE);
         boolean kaiokenTurnedOff = true;
         if (DBCConfig.KaiokenSingleFormDescendOn && dbcdescend == -1 && isKaiokenOn) {
            if (state2 > 0) {
               state2--;
            }

            if (state2 > 0) {
               kaiokenTurnedOff = false;
               p.func_145747_a(new ChatComponentText(JRMCoreH.clgd + "Kaioken " + JRMCoreH.TransKaiNms[state2]));
            }

            nbt.func_74774_a("jrmcState2", (byte)(state2 > 0 ? state2 : 0));
         } else {
            nbt.func_74774_a("jrmcState2", (byte)0);
         }

         if (isKaiokenOn) {
            if (kaiokenTurnedOff) {
               int strnTmp = nbt.func_74762_e("jrmcStrainTemp");
               int strnAct = nbt.func_74762_e("jrmcStrainActv");
               int strn = nbt.func_74762_e("jrmcStrain");
               JRMCoreH.KaiKStrainAct(p, nbt, state2, strn, strnTmp, strnAct);
            }
         } else {
            StE = JRMCoreH.StusEfcts(19, nbt, false);
         }
      } else {
         if (statusMysticOn) {
            StE = JRMCoreH.StusEfcts(13, StE, nbt, false);
         }

         if (statusGoDOn) {
            StE = JRMCoreH.StusEfcts(20, StE, nbt, false);
         }

         int newState = baseState;
         if (dbcdescend == -1 && DBCConfig.SingleFormDescendOn && JRMCoreH.isRaceSaiyan(race)) {
            if (JRMCoreH.isInState(state, 15)) {
               newState = 10;
            } else if (JRMCoreH.isInState(state, 10)) {
               newState = 9;
            } else if (JRMCoreH.isInState(state, 2)) {
               String s1 = nbt.func_74779_i("jrmcSSltX");
               int racialSkillLevel = JRMCoreH.SklLvlX(1, s1);
               newState = racialSkillLevel - 1 >= 4 ? 4 : 1;
            } else if (JRMCoreH.isInState(state, 3)) {
               newState = 2;
            } else if (JRMCoreH.isInState(state, 5)) {
               String s1 = nbt.func_74779_i("jrmcSSltX");
               int racialSkillLevel = JRMCoreH.SklLvlX(1, s1);
               newState = racialSkillLevel - 1 >= 4 ? 4 : 1;
            } else if (JRMCoreH.isInState(state, 6)) {
               newState = 5;
            }
         }

         nbt.func_74774_a("jrmcState", (byte)newState);
      }

      if (dbcdescend == 3) {
         if (statusMysticOn) {
            StE = JRMCoreH.StusEfcts(13, StE, nbt, false);
         }

         if (statusGoDOn) {
            StE = JRMCoreH.StusEfcts(20, StE, nbt, false);
         }

         nbt.func_74774_a("jrmcState", (byte)(JRMCoreH.isRaceArcosian(race) && state >= 4 ? 4 : 0));
      }

      if (dbcdescend == 4) {
         if (statusMysticOn) {
            StE = JRMCoreH.StusEfcts(13, StE, nbt, false);
         }

         if (statusGoDOn) {
            StE = JRMCoreH.StusEfcts(20, StE, nbt, false);
         }

         nbt.func_74774_a("jrmcState", (byte)0);
      }
   }

   public void handleDBCchargesound(int c, String s, EntityPlayer p) {
      this.soundPowerUp(p, s);
   }

   public void handleDBCjumpsound(int dbcjumpsound, int dbcp, EntityPlayer p) {
      if (dbcjumpsound == -2) {
         PD.sendTo(new DBCPduo(dbcjumpsound, JRMCoreH.getInt(p, "jrmcWishes")), (EntityPlayerMP)p);
      }

      if (dbcjumpsound == -1) {
         PD.sendTo(new DBCPduo(dbcjumpsound, JRMCoreH.getInt(p, "jrmcReviveTmer")), (EntityPlayerMP)p);
      }

      if (dbcjumpsound < 999 && dbcjumpsound >= 0) {
         int n = 16;
         AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(
            p.field_70165_t - 16.0, p.field_70163_u - 16.0, p.field_70161_v - 16.0, p.field_70165_t + 16.0, p.field_70163_u + 16.0, p.field_70161_v + 16.0
         );
         List l = p.field_70170_p.func_72872_a(EntityPlayer.class, aabb);

         for (int i = 0; i < l.size(); i++) {
            EntityPlayer entity2 = (EntityPlayer)l.get(i);
            PD.sendTo(new DBCPduo(dbcjumpsound, dbcp), (EntityPlayerMP)entity2);
         }
      } else if (dbcjumpsound == 1000 && p.field_70170_p.func_73045_a(dbcp) != null) {
         p.field_70170_p.func_73045_a(dbcp).func_70106_y();
      }
   }

   public void handleDBCascendsound(int c, EntityPlayer p) {
      int n = 16;
      AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(
         p.field_70165_t - 16.0, p.field_70163_u - 16.0, p.field_70161_v - 16.0, p.field_70165_t + 16.0, p.field_70163_u + 16.0, p.field_70161_v + 16.0
      );
      List l = p.field_70170_p.func_72872_a(EntityPlayer.class, aabb);

      for (int i = 0; i < l.size(); i++) {
         EntityPlayer entity2 = (EntityPlayer)l.get(i);
         PD.sendTo(new DBCPascendsound(c), (EntityPlayerMP)entity2);
      }
   }

   public void handleDBCdescendsound(int c, EntityPlayer p) {
      int n = 16;
      AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(
         p.field_70165_t - 16.0, p.field_70163_u - 16.0, p.field_70161_v - 16.0, p.field_70165_t + 16.0, p.field_70163_u + 16.0, p.field_70161_v + 16.0
      );
      List l = p.field_70170_p.func_72872_a(EntityPlayer.class, aabb);

      for (int i = 0; i < l.size(); i++) {
         EntityPlayer entity2 = (EntityPlayer)l.get(i);
         PD.sendTo(new DBCPdescendsound(c), (EntityPlayerMP)entity2);
      }
   }

   public void handleDBCscouter1(int c, EntityPlayer p) {
      int n = 16;
      AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(
         p.field_70165_t - 16.0, p.field_70163_u - 16.0, p.field_70161_v - 16.0, p.field_70165_t + 16.0, p.field_70163_u + 16.0, p.field_70161_v + 16.0
      );
      List l = p.field_70170_p.func_72872_a(EntityPlayer.class, aabb);

      for (int i = 0; i < l.size(); i++) {
         EntityPlayer entity2 = (EntityPlayer)l.get(i);
         PD.sendTo(new DBCPscouter1(c), (EntityPlayerMP)entity2);
      }
   }

   public void handleDBCscouter2(int c, EntityPlayer p) {
      int n = 16;
      AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(
         p.field_70165_t - 16.0, p.field_70163_u - 16.0, p.field_70161_v - 16.0, p.field_70165_t + 16.0, p.field_70163_u + 16.0, p.field_70161_v + 16.0
      );
      List l = p.field_70170_p.func_72872_a(EntityPlayer.class, aabb);

      for (int i = 0; i < l.size(); i++) {
         EntityPlayer entity2 = (EntityPlayer)l.get(i);
         PD.sendTo(new DBCPscouter2(c), (EntityPlayerMP)entity2);
      }
   }

   public void handleDBCscouter3(int c, EntityPlayer p) {
      int n = 16;
      AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(
         p.field_70165_t - 16.0, p.field_70163_u - 16.0, p.field_70161_v - 16.0, p.field_70165_t + 16.0, p.field_70163_u + 16.0, p.field_70161_v + 16.0
      );
      List l = p.field_70170_p.func_72872_a(EntityPlayer.class, aabb);

      for (int i = 0; i < l.size(); i++) {
         EntityPlayer entity2 = (EntityPlayer)l.get(i);
         PD.sendTo(new DBCPscouter3(c), (EntityPlayerMP)entity2);
      }
   }

   public void handleDBCscouter4(int c, EntityPlayer p) {
      int n = 16;
      AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(
         p.field_70165_t - 16.0, p.field_70163_u - 16.0, p.field_70161_v - 16.0, p.field_70165_t + 16.0, p.field_70163_u + 16.0, p.field_70161_v + 16.0
      );
      List l = p.field_70170_p.func_72872_a(EntityPlayer.class, aabb);

      for (int i = 0; i < l.size(); i++) {
         EntityPlayer entity2 = (EntityPlayer)l.get(i);
         PD.sendTo(new DBCPscouter4(c), (EntityPlayerMP)entity2);
      }
   }

   public void soundPowerUp(EntityPlayer var4, String var2) {
      var4.field_70170_p.func_72956_a(var4, var2, 0.15F, 1.0F);
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

   public void closeInventoryChange(EntityPlayer p) {
      p.field_71071_by.field_70459_e = false;
   }
}
