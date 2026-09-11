/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 */
package com.tobiasmjc.dbcadditions.mixin.late.packet;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.p.DBC.DBCPacketHandlerServer;
import JinRyuu.JRMCore.server.JGPlayerMP;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCFormMastery;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.llamalad7.mixinextras.sugar.ref.LocalByteRef;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.tobiasmjc.dbcadditions.DBCAConfig;
import com.tobiasmjc.dbcadditions.data.DBCAPlayer;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForm;
import com.tobiasmjc.dbcadditions.data.forms.DBCAFormMastery;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForms;
import com.tobiasmjc.dbcadditions.data.forms.FormItem;
import com.tobiasmjc.dbcadditions.data.forms.FormItemsDBA;
import com.tobiasmjc.dbcadditions.data.races.DBCARaces;
import com.tobiasmjc.dbcadditions.packets.DBUPacketTransform;
import com.tobiasmjc.dbcadditions.packets.DBUPackets;
import com.tobiasmjc.dbcadditions.utils.DBCAUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={DBCPacketHandlerServer.class}, remap=false)
public class MixinDBCPacketHandler {
    private static final ChatStyle styleRed = new ChatStyle().setColor(EnumChatFormatting.RED);

    @Redirect(method={"handleDBCascend"}, at=@At(value="INVOKE", target="LJinRyuu/JRMCore/JRMCoreH;race_match(II)Z"))
    private boolean handleFusionCustomRaces(int race1, int race2, @Local(name={"pl2"}) LocalRef<EntityPlayer> pl2, @Local(name={"jgPlayer"}) LocalRef<JGPlayerMP> jgPlayer) {
        DBCAPlayer dbca1 = DBCAPlayer.get(jgPlayer.get().player);
        DBCAPlayer dbca2 = DBCAPlayer.get(pl2.get());
        return dbca1.DBARace == dbca2.DBARace && JRMCoreH.race_match(race1, race2);
    }

    @Inject(method={"handleDBCascend"}, at={@At(value="INVOKE", target="LJinRyuu/JRMCore/JRMCoreH;isRaceSaiyan(I)Z", ordinal=1)}, cancellable=true)
    public void dbcAscend(byte dbcascend, EntityPlayer p, CallbackInfo ci, @Local(name={"isInBaseForm"}) LocalBooleanRef isInBaseForm, @Local(name={"StE"}) LocalRef<String> StE, @Local(name={"st"}) LocalByteRef st, @Local(name={"statusKaiokenOn"}) LocalBooleanRef statusKaiokenOn, @Local(name={"statusMysticOn"}) LocalBooleanRef statusMysticOn, @Local(name={"statusUltraInstinctOn"}) LocalBooleanRef statusUltraInstinctOn, @Local(name={"statusGodOfDestructionOn"}) LocalBooleanRef statusGodOfDestructionOn, @Local(name={"st2"}) LocalByteRef st2, @Local(name={"isKaiokenAvailable"}) LocalBooleanRef isKaiokenAvailable, @Local(name={"isUIAvailable"}) LocalBooleanRef isUIAvailable, @Local(name={"isGoDAvailable"}) LocalBooleanRef isGoDAvailable, @Local(name={"isMysticAvailable"}) LocalBooleanRef isMysticAvailable, @Local(name={"playerAscendNormal"}) LocalBooleanRef playerAscendNormal, @Local(name={"playerAscendGod"}) LocalBooleanRef playerAscendGod, @Local(name={"playerAscendBlue"}) LocalBooleanRef playerAscendBlue, @Local(name={"playerAscendSS4"}) LocalBooleanRef playerAscendSS4, @Local(name={"quickTransform"}) LocalBooleanRef quickTransform, @Local(name={"transformToOozaru"}) LocalBooleanRef transformToOozaru, @Local(name={"isFusionSelectedAvailable"}) LocalBooleanRef isFusionSelectedAvailable, @Local(name={"quickTransformKiLoss"}) LocalIntRef quickTransformKiLoss, @Local(name={"jgPlayer"}) LocalRef<JGPlayerMP> jgPlayer) {
        DBCAForm nextForm;
        DBCAPlayer player = DBCAPlayer.get(p);
        int selectedForm = player.selectedForm;
        if (DBCAConfig.CustomForms && isKaiokenAvailable.get() && player.DBAForm > 0) {
            return;
        }
        if (isFusionSelectedAvailable.get()) {
            return;
        }
        boolean customAscend = false;
        int dbaFormID = -1;
        int dbcFormID = 0;
        boolean shouldTransform = !isKaiokenAvailable.get() && !isGoDAvailable.get() && !isMysticAvailable.get() && !isUIAvailable.get() && !transformToOozaru.get();
        byte race = player.getRace();
        DBCAForm currForm = DBCAForms.getForm(player.DBAForm);
        if (quickTransform.get()) {
            double costMulti;
            int maxEnergy;
            double energyCost;
            int[] playerAttributes = jgPlayer.get().getAttributes();
            byte classID = jgPlayer.get().getClassID();
            byte powerType = jgPlayer.get().getPowerType();
            int curEnergy = jgPlayer.get().getEnergy();
            if ((double)curEnergy >= (energyCost = (double)(maxEnergy = jgPlayer.get().getEnergyMax(race, classID, powerType, playerAttributes, JRMCoreH.SklLvl_KiBs(p, (int)powerType))) * (costMulti = (double)DBCConfig.InstantTransformKiPercentageCost / 100.0) + (double)DBCConfig.InstantTransformKiCost) && DBCConfig.InstantTransformOn) {
                int remainingEnergy = curEnergy - (int)energyCost;
                quickTransformKiLoss.set(remainingEnergy);
            } else {
                p.addChatMessage(new ChatComponentText("Not Enough Ki to Transform Instantly! Cost: " + energyCost).setChatStyle(styleRed));
                return;
            }
        }
        FormItem selectedFormItem = FormItemsDBA.getFormItem(selectedForm);
        if (selectedFormItem != null && !selectedFormItem.canTransform(p)) {
            ci.cancel();
            return;
        }
        DBCAForm dBCAForm = nextForm = currForm != null ? currForm.getNextForm() : null;
        if (nextForm != null && FormItemsDBA.getFormItemByForm(nextForm.getID()).canTransform(p)) {
            customAscend = true;
            dbaFormID = currForm.getNextForm().getID();
        } else {
            if (currForm != null) {
                ci.cancel();
                return;
            }
            if (DBCARaces.isCustomRace(p)) {
                if (DBCARaces.isRace(p, DBCARaces.BIO_ANDROID)) {
                    int formID = DBCAForms.SemiPerfect.getID();
                    customAscend = true;
                    if (selectedFormItem != null && selectedFormItem.isCustomForm()) {
                        formID = selectedFormItem.getCustomForm().getID();
                    }
                    dbaFormID = formID;
                }
            } else {
                if (selectedFormItem != null && selectedFormItem.isCustomForm()) {
                    dbaFormID = selectedFormItem.getCustomForm().getID();
                    customAscend = true;
                }
                if (JRMCoreH.isRaceSaiyan(race)) {
                    if (player.getDBCForm() == 7 || player.getDBCForm() == 8) {
                        return;
                    }
                    playerAscendNormal.set(false);
                    playerAscendBlue.set(false);
                    playerAscendGod.set(false);
                    playerAscendSS4.set(false);
                    if (selectedForm == FormItemsDBA.SS2.getID()) {
                        playerAscendNormal.set(true);
                    }
                    if (selectedForm == FormItemsDBA.SS4.getID()) {
                        playerAscendSS4.set(true);
                    }
                    if (selectedForm == FormItemsDBA.SSGod.getID()) {
                        playerAscendGod.set(true);
                    }
                    if (selectedForm == FormItemsDBA.SSBlue.getID()) {
                        playerAscendBlue.set(true);
                    }
                    if (selectedForm == FormItemsDBA.SSBlueEv.getID()) {
                        dbcFormID = 15;
                        customAscend = true;
                    }
                }
                if (JRMCoreH.isRaceHuman(race)) {
                    if (selectedForm == FormItemsDBA.FullReleasedHuman.getID()) {
                        playerAscendNormal.set(true);
                    }
                    if (selectedForm == FormItemsDBA.GodHuman.getID()) {
                        playerAscendGod.set(true);
                    }
                }
                if (JRMCoreH.isRaceNamekian(race)) {
                    if (selectedForm == FormItemsDBA.FullReleasedNamekian.getID()) {
                        playerAscendNormal.set(true);
                    }
                    if (selectedForm == FormItemsDBA.GodNamekian.getID()) {
                        playerAscendGod.set(true);
                    }
                }
                if (JRMCoreH.isRaceArcosian(race)) {
                    if (selectedForm == FormItemsDBA.GodArcosian.getID()) {
                        playerAscendGod.set(true);
                    }
                    if (selectedForm == FormItemsDBA.Golden.getID()) {
                        playerAscendNormal.set(true);
                    }
                }
                if (JRMCoreH.isRaceMajin(race)) {
                    if (selectedForm == FormItemsDBA.FullPower.getID()) {
                        dbcFormID = 2;
                        customAscend = true;
                    }
                    if (selectedForm == FormItemsDBA.Pure.getID()) {
                        playerAscendNormal.set(true);
                    }
                    if (selectedForm == FormItemsDBA.GodMajin.getID()) {
                        playerAscendGod.set(true);
                    }
                }
            }
        }
        if (customAscend) {
            if (!shouldTransform) {
                if (player.DBAForm > 0 && (isGoDAvailable.get() || isMysticAvailable.get() || isUIAvailable.get())) {
                    ci.cancel();
                } else {
                    return;
                }
            }
            if (dbaFormID != -1) {
                if (DBCConfig.InstantTransformOn && quickTransform.get() && !this.hasInstantTransformUnlockedCustomForm(p, dbaFormID)) {
                    ci.cancel();
                    return;
                }
                player.ascendDBAForm(dbaFormID);
                DBUPackets.sendToClient(new DBUPacketTransform(dbaFormID), (EntityPlayerMP)p);
            } else if (dbcFormID != 0) {
                if (DBCConfig.InstantTransformOn && quickTransform.get() && !this.hasInstantTransformUnlocked(p, true, dbcFormID, race)) {
                    ci.cancel();
                    return;
                }
                player.ascendDBCForm((byte)dbcFormID);
            }
            if (quickTransformKiLoss.get() != -1) {
                JRMCoreH.setInt(quickTransformKiLoss.get(), p, "jrmcEnrgy");
            }
            ci.cancel();
        }
    }

    private boolean hasInstantTransformUnlockedCustomForm(EntityPlayer p, int formID) {
        DBCAForm form = DBCAForms.getForm(formID);
        if (form == null) {
            return false;
        }
        DBCAFormMastery mastery = form.getMastery(p);
        double hasUnlocked = 0.0;
        if (JGConfigDBCFormMastery.FM_Enabled) {
            double masteryLevel;
            double unlockLevel = mastery.MasteryData.instantTransformLevel;
            String message = "";
            if (unlockLevel <= -1.0) {
                hasUnlocked = -1.0;
                message = "Instant Transformation is not available for this Form!";
            } else if (unlockLevel > 0.0 && (masteryLevel = mastery.level) < unlockLevel) {
                hasUnlocked = unlockLevel;
                String formName = form.DisplayName == null ? DBCAUtils.translate(form.getName()) : form.DisplayName;
                message = "Failed to Instant Transform! Required " + formName + " Mastery Level: " + hasUnlocked + " to be unlocked!";
            }
            if (hasUnlocked != 0.0) {
                p.addChatMessage(new ChatComponentText(message).setChatStyle(styleRed));
                return false;
            }
        }
        return true;
    }

    private boolean hasInstantTransformUnlocked(EntityPlayer p, boolean isRacial, int formID, int race) {
        double hasUnlocked = 0.0;
        if (JGConfigDBCFormMastery.FM_Enabled) {
            double masteryLevel;
            double unlockLevel = JGConfigDBCFormMastery.getDouble(race, formID + (isRacial ? 0 : JRMCoreH.trans[race].length), JGConfigDBCFormMastery.DATA_ID_INSTANT_TRANSFORM_UNLOCK);
            String message = "";
            if (unlockLevel <= -1.0) {
                hasUnlocked = -1.0;
                message = "Instant Transformation is not available for this Form!";
            } else if (unlockLevel > 0.0 && (masteryLevel = JRMCoreH.getFormMasteryValue(p, formID + (isRacial ? 0 : JRMCoreH.trans[race].length))) < unlockLevel) {
                hasUnlocked = unlockLevel;
                String formName = isRacial ? JRMCoreH.trans[race][formID] : JRMCoreH.transNonRacial[formID];
                message = "Failed to Instant Transform! Required " + formName + " Mastery Level: " + hasUnlocked + " to be unlocked!";
            }
            if (hasUnlocked != 0.0) {
                p.addChatMessage(new ChatComponentText(message).setChatStyle(styleRed));
                return false;
            }
        }
        return true;
    }
}
