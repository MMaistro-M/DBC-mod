/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 */
package com.tobiasmjc.dbcadditions.mixin.late;

import JinRyuu.DragonBC.common.DBCKiTech;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.entity.EntitySafeZone;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.tobiasmjc.dbcadditions.DBCAConfig;
import com.tobiasmjc.dbcadditions.data.DBCAClientData;
import com.tobiasmjc.dbcadditions.data.ability.DBCAAbilities;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForm;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForms;
import com.tobiasmjc.dbcadditions.data.forms.FormItemsDBA;
import com.tobiasmjc.dbcadditions.data.forms.display.CustomAura;
import com.tobiasmjc.dbcadditions.data.races.DBCARaces;
import com.tobiasmjc.dbcadditions.packets.DBUPacketAbsorb;
import com.tobiasmjc.dbcadditions.packets.DBUPacketTransform;
import com.tobiasmjc.dbcadditions.packets.DBUPackets;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={DBCKiTech.class}, remap=false)
public class MixinDBCKiTech {
    private static int ptime1 = 0;
    private static int ascend1 = 0;

    @Inject(method={"Descend"}, at={@At(value="FIELD", target="LJinRyuu/JRMCore/JRMCoreH;kiInSuper:I", shift=At.Shift.AFTER)}, cancellable=true)
    private static void handleDescendClient(KeyBinding K, CallbackInfo ci) {
        if (!DBCAConfig.CustomForms) {
            return;
        }
        byte form = DataUtils.getDBCAState((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
        if (form <= 0 || JRMCoreH.State2 != 0) {
            return;
        }
        DBCAClientData.currentDBAForm = 0;
        DBUPackets.sendToServer(new DBUPacketTransform(0));
        if (JRMCoreH.State == 4 && JRMCoreH.isRaceArcosian(JRMCoreH.Race)) {
            DBCKiTech.soundAsc("jinryuudragonbc:DBC.descend");
        }
    }

    @Inject(method={"Ascend"}, at={@At(value="INVOKE")}, cancellable=true)
    private static void handleAscendClient(KeyBinding K, CallbackInfo ci, @Local(name={"playerSettingsGodOn"}) LocalBooleanRef playerSettingsGodOn, @Local(name={"playerSettingsBlueOn"}) LocalBooleanRef playerSettingsBlueOn, @Local(name={"playerSettingsBlueOnInBlue"}) LocalBooleanRef playerSettingsBlueOnInBlue, @Local(name={"isTransformKeyPressed"}) LocalBooleanRef isTransformKeyPressed, @Local(name={"useKaioken"}) LocalBooleanRef useKaioken) {
        if (!DBCAConfig.CustomForms) {
            return;
        }
        EntityClientPlayerMP p = Minecraft.func_71410_x().field_71439_g;
        byte currentFormID = DataUtils.getDBCAState((EntityPlayer)p);
        int selectedForm = DBCAClientData.selectedForm;
        DBCAForm currentForm = DBCAForms.getForm(currentFormID);
        byte raceC = DataUtils.getDBCARace((EntityPlayer)p);
        if (DBCAClientData.absorbCooldown > 0) {
            --DBCAClientData.absorbCooldown;
        }
        if (raceC == DBCARaces.BIO_ANDROID.ID && JRMCoreH.StusEfctsMe(21)) {
            if (DBCAAbilities.isAbsorbing((EntityPlayer)p) || DBCAClientData.absorbCooldown > 0 || currentFormID != DBCAForms.SemiPerfect.getID() && currentFormID > 0) {
                isTransformKeyPressed.set(false);
                return;
            }
            if (isTransformKeyPressed.get() && JRMCoreH.StusEfctsMe(21)) {
                int s;
                int p1 = 5;
                if (++ptime1 >= p1) {
                    ptime1 = 0;
                    int ic = 15;
                    ic = ic > 100 ? 100 : ic;
                    DBCKiTech.triForce(1, 0, ic);
                }
                if (++ascend1 >= (s = 10) && JRMCoreH.TransSaiCurRg >= 100) {
                    ascend1 = 0;
                    ptime1 = 0;
                    DBCKiTech.pup = 0;
                    DBCKiTech.pup2 = 0;
                    DBCAClientData.absorbCooldown = 1000;
                    List entities = p.field_70170_p.func_72872_a(EntityCreature.class, AxisAlignedBB.func_72330_a((double)(p.field_70165_t - 3.0), (double)(p.field_70163_u - 3.0), (double)(p.field_70161_v - 3.0), (double)(p.field_70165_t + 3.0), (double)(p.field_70163_u + 3.0), (double)(p.field_70161_v + 3.0)));
                    entities.removeIf(entity -> entity instanceof EntitySafeZone);
                    EntityCreature closest = null;
                    float distance = 10.0f;
                    for (EntityCreature entity2 : entities) {
                        float entityDist = entity2.func_70032_d((Entity)p);
                        if (!(distance > entityDist)) continue;
                        distance = entityDist;
                        closest = entity2;
                    }
                    if (closest != null) {
                        DBCAAbilities.startAbsorbing((EntityPlayer)p, closest.func_145782_y());
                        DBUPackets.sendToServer(new DBUPacketAbsorb(p.func_70005_c_(), closest.func_145782_y(), true));
                    } else {
                        DBUPackets.sendToServer(new DBUPacketAbsorb(p.func_70005_c_(), -1, true));
                    }
                }
            }
            ci.cancel();
            return;
        }
        if (currentFormID > 0) {
            boolean hasNext;
            if (currentForm == null) {
                return;
            }
            DBCAForm nextForm = currentForm.getNextForm();
            boolean bl = hasNext = nextForm != null && FormItemsDBA.getFormItemByForm(nextForm.getID()) != null && FormItemsDBA.getFormItemByForm(nextForm.getID()).canTransform((EntityPlayer)p);
            if (!useKaioken.get() && isTransformKeyPressed.get() && !hasNext) {
                ci.cancel();
            }
            return;
        }
        byte race = JRMCoreH.Race;
        if (JRMCoreH.isRaceSaiyan(race)) {
            if (selectedForm == 3) {
                playerSettingsGodOn.set(true);
            }
            if (selectedForm == 4 || selectedForm == 5) {
                playerSettingsBlueOn.set(true);
                if (JRMCoreH.State == 10 || JRMCoreH.State == 9) {
                    playerSettingsBlueOnInBlue.set(true);
                }
            }
        } else if (JRMCoreH.isRaceHumanOrNamekian(race)) {
            if (selectedForm == 2) {
                playerSettingsGodOn.set(true);
            }
        } else if (JRMCoreH.isRaceArcosian(race)) {
            if (selectedForm == 2) {
                playerSettingsGodOn.set(true);
            }
        } else if (JRMCoreH.isRaceMajin(race) && JRMCoreH.State == 3) {
            isTransformKeyPressed.set(false);
        }
    }

    @Inject(method={"chargePart(Lnet/minecraft/entity/player/EntityPlayer;IIIIIZLjava/lang/String;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private static void renderCustomAura(EntityPlayer p, int r, int a, int c, int s, int k, boolean b, String se, CallbackInfo ci) {
        if (!DBCAConfig.CustomForms) {
            return;
        }
        byte formID = DataUtils.getDBCAState(p);
        if (formID <= 0) {
            return;
        }
        DBCAForm form = DBCAForms.getForm(formID);
        if (form == null) {
            return;
        }
        List<CustomAura> auras = form.getAuras();
        for (CustomAura aura : auras) {
            if (aura == null) {
                return;
            }
            float state2 = 0.0f;
            String dbcCharger = p.func_70005_c_();
            if (JRMCoreH.plyrs != null && JRMCoreH.plyrs.length > 0 && JRMCoreH.dnn(2) && JRMCoreH.dnn(10)) {
                for (int pl = 0; pl < JRMCoreH.plyrs.length; ++pl) {
                    if (!JRMCoreH.plyrs[pl].equals(dbcCharger)) continue;
                    String[] states = JRMCoreH.data2[pl].split(";");
                    state2 = Integer.parseInt(states[1]);
                }
            }
            if (state2 > 0.0f) {
                aura.spawnKaioken(p, state2);
                if (b) {
                    DBCKiTech.soundAsc("jinryuudragonbc:1610.aurabk");
                }
            } else if (b) {
                DBCKiTech.soundAsc(aura.getSound());
            }
            aura.spawn(p);
            ci.cancel();
        }
    }

    @Redirect(method={"Ascend"}, at=@At(value="INVOKE", target="LJinRyuu/DragonBC/common/DBCKiTech;getClientRacialSkillLevel()I"))
    private static int getClientRacialSkillLevel() {
        int racial = Integer.parseInt(JRMCoreH.PlyrSkillX.replaceAll("TR", ""));
        if (!DBCAConfig.CustomForms) {
            return racial;
        }
        if (JRMCoreH.isRaceArcosian(JRMCoreH.Race) && racial >= 1 && racial < 3) {
            return 3;
        }
        return racial;
    }
}

