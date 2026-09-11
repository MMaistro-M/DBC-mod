/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.tobiasmjc.dbcadditions.mixin.late;

import JinRyuu.JRMCore.JRMCoreH;
import com.tobiasmjc.dbcadditions.DBCAConfig;
import com.tobiasmjc.dbcadditions.common.CommonProxy;
import com.tobiasmjc.dbcadditions.data.races.DBCARace;
import com.tobiasmjc.dbcadditions.data.races.DBCARaces;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkill;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkills;
import com.tobiasmjc.dbcadditions.utils.DBCAUtils;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={JRMCoreH.class}, remap=false)
public class MixinJRMCoreH2 {
    @SideOnly(value=Side.CLIENT)
    @Inject(method={"skillMindRequirement_X(Ljava/lang/String;I[[IZ)I"}, at={@At(value="HEAD")}, cancellable=true)
    private static void customRaceMindCostC(String playerRacialSkill, int race, int[][] mindrequirement, boolean granted, CallbackInfoReturnable<Integer> ci) {
        DBCARace dbcaRace;
        if (!DBCAConfig.CustomRaces) {
            return;
        }
        int z = !granted ? 1 : 0;
        int lvl = playerRacialSkill.length() > 2 ? Integer.parseInt(playerRacialSkill.substring(2)) + z : 0;
        EntityPlayer player = CommonProxy.CurrentPlayerSkill;
        if (player == null) {
            player = Minecraft.func_71410_x().field_71439_g;
        }
        if ((dbcaRace = DBCARaces.getRace(DataUtils.getDBCARace(player))) == null) {
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

    @SideOnly(value=Side.CLIENT)
    @Inject(method={"skillTPCost_X(Ljava/lang/String;I[[IZ)I"}, at={@At(value="HEAD")}, cancellable=true)
    private static void customRaceTpCostC(String playerRacialSkill, int race, int[][] tpCosts, boolean granted, CallbackInfoReturnable<Integer> ci) {
        if (!DBCAConfig.CustomRaces) {
            return;
        }
        EntityPlayer player = CommonProxy.CurrentPlayerSkill;
        if (player == null) {
            player = Minecraft.func_71410_x().field_71439_g;
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

    @SideOnly(value=Side.CLIENT)
    @Inject(method={"skillSlot_MindUsed"}, at={@At(value="RETURN")}, cancellable=true)
    private static void handleMindRequirement(CallbackInfoReturnable<Integer> ci) {
        if (!DBCAConfig.CustomForms) {
            return;
        }
        EntityClientPlayerMP p = Minecraft.func_71410_x().field_71439_g;
        int mind = 0;
        for (DBCASkill skill : DBCASkills.getPlayerSkills(DataUtils.getDBCASkills((EntityPlayer)p))) {
            mind += skill.getLevel() * skill.getMindCost();
        }
        ci.setReturnValue(ci.getReturnValue() + mind);
    }
}

