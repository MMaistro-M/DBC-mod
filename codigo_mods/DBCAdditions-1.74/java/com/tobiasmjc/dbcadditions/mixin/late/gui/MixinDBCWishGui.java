/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.tobiasmjc.dbcadditions.mixin.late.gui;

import JinRyuu.DragonBC.common.Gui.DBCWishGui;
import JinRyuu.JRMCore.JRMCoreH;
import com.tobiasmjc.dbcadditions.data.races.DBCARace;
import com.tobiasmjc.dbcadditions.data.races.DBCARaces;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkill;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkills;
import com.tobiasmjc.dbcadditions.utils.DBCAUtils;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={DBCWishGui.class}, remap=false)
public class MixinDBCWishGui
extends GuiScreen {
    @Redirect(method={"drawScreen"}, at=@At(value="INVOKE", target="LJinRyuu/JRMCore/JRMCoreH;trl(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;"), remap=true)
    private static String getTranslateNames(String mod, String name) {
        DBCARace race;
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        for (DBCASkill customSkill : DBCASkills.getPlayerSkills(DataUtils.getDBCASkills((EntityPlayer)player))) {
            if (name.equalsIgnoreCase(customSkill.getName())) {
                return DBCAUtils.translate(customSkill.getName());
            }
            if (!name.equalsIgnoreCase(customSkill.getName() + "Desc")) continue;
            return DBCAUtils.translate(customSkill.getName() + "-Desc");
        }
        byte raceID = DataUtils.getDBCARace((EntityPlayer)player);
        if (raceID > 0 && (race = DBCARaces.getRace(raceID)).getColorMinRacial() != -1 && name.equalsIgnoreCase("wisharcultformcolor")) {
            return DBCAUtils.translate("wish_reset-color") + " " + DBCAUtils.translate(race.getName()) + " " + DBCAUtils.translate(race.getName() + "-UltimateForm");
        }
        return JRMCoreH.trl(mod, name);
    }
}

