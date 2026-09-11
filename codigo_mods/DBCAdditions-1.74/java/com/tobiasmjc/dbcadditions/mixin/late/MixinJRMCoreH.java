/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package com.tobiasmjc.dbcadditions.mixin.late;

import JinRyuu.JRMCore.ComJrmcaBonus;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.entity.ExplosionJRMC;
import JinRyuu.JRMCore.server.JGMathHelper;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCFormMastery;
import JinRyuu.JRMCore.server.config.dbc.JGConfigRaces;
import com.tobiasmjc.dbcadditions.DBCAConfig;
import com.tobiasmjc.dbcadditions.common.CommonProxy;
import com.tobiasmjc.dbcadditions.data.DBCAPlayer;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForm;
import com.tobiasmjc.dbcadditions.data.forms.DBCAFormMastery;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForms;
import com.tobiasmjc.dbcadditions.data.races.DBCARace;
import com.tobiasmjc.dbcadditions.data.races.DBCARaces;
import com.tobiasmjc.dbcadditions.utils.DBCAUtils;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={JRMCoreH.class}, remap=false)
public class MixinJRMCoreH {
    @Shadow
    private static float[] TransKaiDmg;

    @Inject(method={"newExpl"}, at={@At(value="INVOKE")}, cancellable=true)
    private static void handleGriefing(World worldObj, Entity entity, double par2, double par4, double par6, float par8, boolean par9, double dam, Entity origin, byte type, CallbackInfoReturnable<ExplosionJRMC> ci) {
        if (worldObj.field_73011_w.field_76574_g == 99 && !DBCAConfig.BeerusGriefing) {
            ExplosionJRMC var10 = new ExplosionJRMC(worldObj, entity, par4, type, par6, par8, true, dam, origin, type);
            var10.field_77286_a = par9;
            var10.func_77278_a();
            var10.func_77279_a(true);
            Side side = FMLCommonHandler.instance().getEffectiveSide();
            if (side == Side.SERVER) {
                for (EntityPlayer var13 : worldObj.field_73010_i) {
                    if (!(var13.func_70092_e(par2, par4, par6) < 4096.0)) continue;
                    JRMCoreH.expCliPack((Entity)var13, par2, par4, par6, par8, var10.field_77281_g, (Vec3)var10.func_77277_b().get(var13), JRMCoreConfig.expGriOff, dam, origin, type);
                }
            }
            ci.setReturnValue(var10);
            ci.cancel();
        }
    }

    @Inject(method={"mstc_sai(I)I"}, remap=false, at={@At(value="HEAD")}, cancellable=true)
    private static void fixMysticCrashRacialOverLimit(int skl, CallbackInfoReturnable<Integer> ci) {
        int[] in = new int[]{1, 1, 1, 4, 5, 6, 6, 6, 6, 6};
        ci.setReturnValue(in[skl]);
    }

    @Inject(method={"stat(Lnet/minecraft/entity/Entity;IIIIIIF)I"}, at={@At(value="INVOKE")}, cancellable=true)
    private static void stat(Entity player, int attributeID, int powerType, int stat, int attribute, int race, int classID, float skillBonus, CallbackInfoReturnable<Integer> ci) {
        if (!DBCAConfig.CustomRaces) {
            return;
        }
        float[] attributes = JRMCoreH.getStatIncreases(powerType, race, classID);
        if (!(player instanceof EntityPlayer)) {
            return;
        }
        if (race > 5) {
            ci.cancel();
        }
        stat = attributes.length > stat ? stat : attributes.length - 1;
        double bs = attributes[stat] * (float)attribute;
        int value = (int)JRMCoreH.round(bs + (double)JRMCoreH.getStatBonus(powerType, race, classID, stat, false) * 0.01 * bs + (double)JRMCoreH.getStatBonus(powerType, race, classID, stat, true) * 0.01 * bs + bs * (double)skillBonus, 0, 0);
        if (JRMCoreConfig.JRMCABonusOn && attributeID > -1 && attributeID <= 5 && player instanceof EntityPlayer) {
            String nbtValue = "NONE";
            if (!player.field_70170_p.field_72995_K) {
                NBTTagCompound nbt = DataUtils.nbt((EntityPlayer)player, "pres");
                nbtValue = nbt.func_74779_i("jrmcAttrBonus" + ComJrmcaBonus.ATTRIBUTES_SHORT[attributeID]);
            } else {
                nbtValue = JRMCoreH.getBonusAttributes(player.func_70005_c_(), attributeID);
            }
            if (!nbtValue.equals("NONE") && !nbtValue.equals("n")) {
                double bonusValueResult = value;
                String[] bonus = nbtValue.split("\\|");
                String[][] bonusValues = new String[bonus.length][2];
                if (bonus.length > 0 && bonus[0].length() > 0) {
                    for (int i = 0; i < bonus.length; ++i) {
                        if (bonus[i].length() <= 1) continue;
                        String[] bonusValue = bonus[i].split("\\;");
                        bonusValues[i][1] = bonusValue[1];
                        try {
                            double value2 = Double.parseDouble(bonusValues[i][1].substring(1));
                            bonusValueResult = JGMathHelper.StringMethod(bonusValues[i][1].substring(0, 1), bonusValueResult, value2);
                            continue;
                        }
                        catch (Exception exception) {
                            // empty catch block
                        }
                    }
                }
                value = (int)bonusValueResult;
            }
        }
        if (powerType == 1 && attributeID > -1 && attributeID <= 5) {
            double multiplier = JGConfigRaces.CONFIG_RACES_ATTRIBUTE_MULTI[race > 5 ? 0 : race][classID][attributeID];
            value = (int)((double)value * multiplier);
        }
        ci.setReturnValue(value);
    }

    @SideOnly(value=Side.SERVER)
    @Inject(method={"skillTPCost_X(Ljava/lang/String;I[[IZ)I"}, at={@At(value="HEAD")}, cancellable=true)
    private static void customRaceTpCostS(String playerRacialSkill, int race, int[][] tpCosts, boolean granted, CallbackInfoReturnable<Integer> ci) {
        if (!DBCAConfig.CustomRaces) {
            return;
        }
        EntityPlayer player = CommonProxy.CurrentPlayerSkill;
        if (player == null) {
            return;
        }
        int z = !granted ? 1 : 0;
        int lvl = playerRacialSkill.length() > 2 ? Integer.parseInt(playerRacialSkill.substring(2)) + z : 0;
        DBCARace dbcaRace = DBCARaces.getRace(DataUtils.getDBCARace(player));
        if (dbcaRace == null) {
            if (!DBCAUtils.modifyMindOrTPS(race, lvl)) {
                return;
            }
            ci.setReturnValue(DBCAUtils.getTPCost(race, lvl));
            return;
        }
        int length = dbcaRace.getTPCosts().length;
        if (lvl == 0) {
            ci.setReturnValue(0);
        } else {
            lvl = lvl > length ? length : lvl;
            int cost = dbcaRace.getTPCosts()[lvl - 1];
            ci.setReturnValue(cost);
        }
    }

    @Inject(method={"skillMindRequirement_X(Ljava/lang/String;I[[IZ)I"}, at={@At(value="HEAD")}, cancellable=true)
    private static void customRaceMindCostS(String playerRacialSkill, int race, int[][] mindrequirement, boolean granted, CallbackInfoReturnable<Integer> ci) {
        if (!DBCAConfig.CustomRaces) {
            return;
        }
        int z = !granted ? 1 : 0;
        int lvl = playerRacialSkill.length() > 2 ? Integer.parseInt(playerRacialSkill.substring(2)) + z : 0;
        EntityPlayer player = CommonProxy.CurrentPlayerSkill;
        if (player == null) {
            return;
        }
        DBCARace dbcaRace = DBCARaces.getRace(DataUtils.getDBCARace(player));
        if (dbcaRace == null) {
            if (!DBCAUtils.modifyMindOrTPS(race, lvl)) {
                return;
            }
            ci.setReturnValue(DBCAUtils.getMindCost(race, lvl));
            return;
        }
        if (playerRacialSkill.length() > 1) {
            int[] mindRequirement = dbcaRace.getMindCosts();
            int length = dbcaRace.getMindCosts().length;
            if (lvl == 0) {
                ci.setReturnValue(0);
            } else {
                lvl = lvl > length ? length : lvl;
                int cost = mindRequirement[lvl - 1];
                ci.setReturnValue(cost);
            }
        }
    }

    @Inject(method={"LJinRyuu/JRMCore/JRMCoreH;getStatIncreases(III)[F"}, at={@At(value="HEAD")}, cancellable=true)
    private static void getStatIncreases(int powerType, int race, int classID, CallbackInfoReturnable<float[]> ci) {
        if (!DBCAConfig.CustomRaces) {
            return;
        }
        if (powerType == 1) {
            ci.setReturnValue(JGConfigRaces.CONFIG_RACES_STATS_MULTI[race > 5 ? 0 : race][classID]);
        }
        ci.setReturnValue(JRMCoreH.statInc[powerType]);
    }

    @Inject(method={"LJinRyuu/JRMCore/JRMCoreH;getStatBonus(IIIIZ)I"}, at={@At(value="HEAD")}, cancellable=true)
    private static void getStatBonus(int powerType, int race, int classID, int stat, boolean classBonusOnly, CallbackInfoReturnable<Integer> ci) {
        if (!DBCAConfig.CustomRaces) {
            return;
        }
        if (powerType == 1) {
            if (classBonusOnly) {
                ci.setReturnValue(0);
            } else {
                ci.setReturnValue(JGConfigRaces.CONFIG_RACES_STAT_BONUS[race > 5 ? 0 : race][classID][stat]);
            }
        }
        if (classBonusOnly) {
            ci.setReturnValue(JRMCoreH.statIncBonusRaceDBC[stat][race > 5 ? 0 : race]);
        } else {
            ci.setReturnValue(JRMCoreH.statIncBonusClass[powerType][stat][classID]);
        }
    }

    @Inject(method={"resetChar(Lnet/minecraft/entity/player/EntityPlayer;ZZZF)V"}, at={@At(value="TAIL")})
    private static void resetChar(EntityPlayer p, boolean keepSkills, boolean keepTechs, boolean keepMasteries, float perc, CallbackInfo ci) {
        DBCAPlayer player = new DBCAPlayer(p);
        player.descend();
        if (!keepSkills) {
            player.Skills = "";
        }
        if (!keepMasteries) {
            player.formMasteries = "";
        }
        if (!keepSkills || !keepMasteries) {
            player.saveNBTData();
        }
    }

    @Inject(method={"PlyrAttrbts(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/entity/player/EntityPlayer;)[I"}, at={@At(value="RETURN")}, cancellable=true)
    private static void applyPotaraFusionExtraMulti(EntityPlayer p, EntityPlayer p2, CallbackInfoReturnable<int[]> ci) {
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER) {
            DBCAPlayer dbcaPlayer = DBCAPlayer.get(p);
            if (!dbcaPlayer.PotaraFusion) {
                return;
            }
            int[] returnValue = ci.getReturnValue();
            for (int i = 0; i < returnValue.length; i = (int)((byte)(i + 1))) {
                if (i == 4) continue;
                returnValue[i] = (int)((double)returnValue[i] * DBCAConfig.PotaraMultiplier);
            }
            ci.setReturnValue(returnValue);
        }
    }

    @Inject(method={"getPlayerAttribute(Lnet/minecraft/entity/player/EntityPlayer;[IIIIILjava/lang/String;IIZZZZZZI[Ljava/lang/String;ZLjava/lang/String;)I"}, at={@At(value="HEAD")}, cancellable=true)
    private static void applyFormAttributes(EntityPlayer player, int[] currAttributes, int attribute, int st, int st2, int race, String SklX, int currRelease, int arcRel, boolean legendOn, boolean majinOn, boolean kaiokenOn, boolean mysticOn, boolean uiOn, boolean GoDOn, int powerType, String[] Skls, boolean isFused, String majinAbs, CallbackInfoReturnable<Integer> info) {
        byte formID;
        if (!DBCAConfig.CustomForms) {
            return;
        }
        if (player == null) {
            return;
        }
        double kaioMultiplier = 1.0;
        if (kaiokenOn && st2 > 0) {
            kaioMultiplier = (double)TransKaiDmg[st2] * JRMCoreH.getFormMasteryAttributeMulti(player, "Kaioken", st, st2, race, kaiokenOn, mysticOn, uiOn, GoDOn);
        }
        if ((formID = DataUtils.getDBCAState(player)) <= 0) {
            if (!(st != 0 || GoDOn || mysticOn || uiOn)) {
                if (!DBCAConfig.CustomRaces) {
                    return;
                }
                DBCARace crace = DBCARaces.getRace(DataUtils.getDBCARace(player));
                if (crace == null) {
                    return;
                }
                info.setReturnValue((int)((double)currAttributes[attribute] * kaioMultiplier * crace.getBaseMultiplier()));
                return;
            }
            return;
        }
        DBCAForm form = DBCAForms.getForm(formID);
        if (form == null) {
            return;
        }
        DBCAFormMastery mastery = form.getMastery(player);
        double multiplier = kaioMultiplier * (double)form.getMultiplier() * (1.0 + mastery.calculateMultiplier());
        if (JRMCoreH.isRaceArcosian(race)) {
            if (arcRel > 0) {
                multiplier *= (double)(1.0f + Math.max(0.0f, JRMCoreH.getArcosianReserveMaxPointPercentage(arcRel) * 1.0f));
            }
        } else if (JRMCoreH.isRaceMajin(race) && majinAbs != null && majinAbs.length() > 0) {
            multiplier *= (double)(1.0f + Math.max(0.0f, JGConfigRaces.CONFIG_MAJIN_ABSORPTON_ATTRIBUTE_MULTI[st] * (float)JRMCoreH.getMajinAbsorptionValueS(majinAbs)));
        }
        info.setReturnValue((int)(multiplier * (double)currAttributes[attribute]));
    }

    @Inject(method={"changeFormMasteryValue"}, at={@At(value="HEAD")}, remap=false, cancellable=true)
    private static void applyFormMastery(EntityPlayer player, double value, boolean add, int race, int formID, boolean racial, int gainMultiID, CallbackInfo info) {
        if (!DBCAConfig.CustomForms) {
            return;
        }
        if (!JGConfigDBCFormMastery.FM_Enabled) {
            return;
        }
        if (JRMCoreH.isFused((Entity)player)) {
            return;
        }
        DBCAPlayer dbcaPlayer = DBCAPlayer.get(player);
        if (!add) {
            return;
        }
        if (dbcaPlayer.DBAForm <= 0) {
            return;
        }
        DBCAFormMastery mastery = DBCAFormMastery.getFormMastery(player, dbcaPlayer.DBAForm);
        mastery.increaseFormMastery(value);
        if (racial) {
            info.cancel();
        }
    }

    @Inject(method={"KaiKCost"}, at={@At(value="TAIL")}, remap=false, cancellable=true)
    private static void handleCustomFormHealthDrain(EntityPlayer p, CallbackInfoReturnable<Double> ci) {
        if (!DBCAConfig.CustomForms) {
            return;
        }
        DBCAPlayer dbcaPlayer = DBCAPlayer.get(p);
        if (dbcaPlayer.DBAForm <= 0) {
            return;
        }
        DBCAForm form = DBCAForms.getForm(dbcaPlayer.DBAForm);
        if (form == null) {
            return;
        }
        double cost = ci.getReturnValue() * (double)form.getHealthDrainKaio();
        DBCAFormMastery mastery = form.getMastery(p);
        double formMasteryReduction = mastery.level * mastery.MasteryData.healthDrainMultiplier;
        cost -= cost * formMasteryReduction;
        if (!form.canStackWithKaioken() || !JRMCoreConfig.csskai) {
            cost = 2.147483647E9;
        }
        ci.setReturnValue(cost);
    }
}

