/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 */
package com.tobiasmjc.dbcadditions.mixin.late;

import JinRyuu.JRMCore.JRMCoreComTickH;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.server.JGPlayerMP;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.tobiasmjc.dbcadditions.DBCAConfig;
import com.tobiasmjc.dbcadditions.data.DBCAPlayer;
import com.tobiasmjc.dbcadditions.data.DBCAPlayerEProperties;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForm;
import com.tobiasmjc.dbcadditions.data.forms.DBCAFormMastery;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForms;
import com.tobiasmjc.dbcadditions.data.forms.FormItem;
import com.tobiasmjc.dbcadditions.data.forms.FormItemsDBA;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={JRMCoreComTickH.class}, remap=false)
public class MixinJRMCoreComTickH {
    @Shadow
    private static String[] dat10 = null;
    @Shadow
    private static String[] dat19 = null;
    @Shadow
    private static String[] dat16 = null;

    @Inject(method={"updatePlayersData"}, at={@At(value="TAIL")})
    private void addDBCAData(MinecraftServer server, int playerID, EntityPlayerMP player, JGPlayerMP jgPlayer, NBTTagCompound nbt, CallbackInfo ci) {
        String au = "";
        DBCAPlayer dbplayer = DBCAPlayer.get((EntityPlayer)player);
        MixinJRMCoreComTickH.dat10[playerID] = "" + nbt.func_74771_c("jrmcRelease") + ";" + nbt.func_74762_e("jrmcStamina") + ";" + dbplayer.DBAForm + ";" + dbplayer.formMasteries + ";" + dbplayer.DBARace + ";" + (dbplayer.PotaraFusion ? "1" : "0") + ";" + dbplayer.PotaraCooldown;
        String statusEffects = nbt.func_74779_i("jrmcStatusEff");
        DBCAPlayerEProperties prop = DBCAPlayerEProperties.get((EntityPlayer)player);
        ItemStack rightS = prop.inventory.func_70301_a(0);
        ItemStack leftS = prop.inventory.func_70301_a(1);
        int right = rightS == null ? -1 : Item.func_150891_b((Item)rightS.func_77973_b());
        int left = leftS == null ? -1 : Item.func_150891_b((Item)leftS.func_77973_b());
        MixinJRMCoreComTickH.dat19[playerID] = "" + nbt.func_74771_c("jrmcTlmd") + ";" + statusEffects + ";" + dbplayer.Skills + ";" + right + ";" + left;
        if (dbplayer.DBARace != -1) {
            MixinJRMCoreComTickH.dat16[playerID] = "" + (dbplayer.DBARace != -1 ? ((au = nbt.func_74779_i("jrmcDNSAU")).length() > 6 ? au : " ") : " ");
        }
    }

    @Inject(method={"updateFusion"}, at={@At(value="FIELD", target="LJinRyuu/JRMCore/JRMCoreConfig;FznOverTime", ordinal=1)}, cancellable=true)
    public void updateFusion(EntityPlayerMP player, NBTTagCompound nbt, int curBody, int curEnergy, String statusEffects, CallbackInfo ci, @Local(name={"fusionMembers"}) LocalRef<String> members) {
        if (DataUtils.isPotaraFusion((EntityPlayer)player)) {
            EntityPlayer p2 = DataUtils.getFusionPartner((EntityPlayer)player);
            if (p2 == null) {
                return;
            }
            DBCAPlayerEProperties prop1 = (DBCAPlayerEProperties)player.getExtendedProperties("DBCAInventory");
            DBCAPlayerEProperties prop2 = (DBCAPlayerEProperties)p2.getExtendedProperties("DBCAInventory");
            prop1.inventory.func_70298_a(0, 1);
            prop1.inventory.func_70298_a(1, 1);
            prop2.inventory.func_70298_a(0, 1);
            prop2.inventory.func_70298_a(1, 1);
        }
    }

    @Redirect(method={"serverTick"}, at=@At(value="INVOKE", target="LJinRyuu/JRMCore/server/JGRaceHelper;getRacialSkillLevel(Lnet/minecraft/nbt/NBTTagCompound;)B"))
    private static byte fixMajinCrash(NBTTagCompound nbt) {
        String key;
        boolean dbc = nbt.func_74771_c("jrmcPwrtyp") == 1;
        boolean nc = nbt.func_74771_c("jrmcPwrtyp") == 2;
        byte race = nbt.func_74771_c("jrmcRace");
        boolean currentLevel = false;
        String string = key = dbc ? "jrmcSSltX" : "jrmcSSltY";
        if (nbt.func_74764_b(key) && !nbt.func_74779_i(key).contains("pty") && nbt.func_74779_i(key).length() > 1 && (!nc || !nbt.func_74779_i("jrmcSSltY").contains("Sai") && race != 1 && race != 2)) {
            byte r = Byte.parseByte(nbt.func_74779_i(key).substring(2));
            if (race == 5 && r > 4) {
                return 4;
            }
            return r;
        }
        return -1;
    }

    @Inject(method={"serverTick"}, at={@At(value="INVOKE", target="LJinRyuu/JRMCore/JRMCoreH;isPowerTypeKi(I)Z", ordinal=1)}, cancellable=true)
    private void serverTick(MinecraftServer server, CallbackInfo ci, @Local(name={"jgPlayer"}) LocalRef<JGPlayerMP> jgPlayer) {
        int ko;
        int formID;
        if (!DBCAConfig.CustomForms) {
            return;
        }
        EntityPlayer p = jgPlayer.get().player;
        DBCAPlayer dbcaPlayer = DBCAPlayer.get(p);
        NBTTagCompound nbt = jgPlayer.get().getNBT();
        if (dbcaPlayer.PotaraFusion) {
            String[] fusionParticipants;
            boolean keep = false;
            String fusionMembers = nbt.func_74779_i("jrmcFuzion");
            if (fusionMembers.length() > 0 && !fusionMembers.equals(" ") && (fusionParticipants = fusionMembers.split(",")).length == 3) {
                keep = true;
            }
            if (!keep) {
                dbcaPlayer.setPotaraFusion(false);
                dbcaPlayer.saveNBTData();
            }
        }
        if ((formID = dbcaPlayer.DBAForm) <= 0) {
            return;
        }
        DBCAForm form = DBCAForms.getForm(formID);
        if (form == null) {
            return;
        }
        FormItem item = FormItemsDBA.getFormItemByForm(form.getID());
        if (!(item == null || item.isRaceCorrect(jgPlayer.get().getRace(), (byte)dbcaPlayer.DBARace) && item.canTransform(p))) {
            dbcaPlayer.descend();
            return;
        }
        if (JRMCoreH.isInCreativeMode((Entity)p)) {
            return;
        }
        byte release = jgPlayer.get().getRelease();
        int[] playerAttributes = jgPlayer.get().getAttributes();
        String s1 = nbt.func_74779_i("jrmcSSltX");
        int st = 6;
        float cost = (float)(JRMCoreH.getPlayerAttribute(playerAttributes, 0, st, 0, 1, s1, jgPlayer.get().getRelease(), jgPlayer.get().getReserve(), false, false, false, false, false, 1, null, false) - playerAttributes[0]) * 0.4f + (float)(JRMCoreH.getPlayerAttribute(playerAttributes, 1, st, 0, 1, s1, jgPlayer.get().getRelease(), jgPlayer.get().getReserve(), false, false, false, false, false, 1, null, false) - playerAttributes[1]) * 0.25f + (float)(JRMCoreH.getPlayerAttribute(playerAttributes, 3, st, 0, 1, s1, jgPlayer.get().getRelease(), jgPlayer.get().getReserve(), false, false, false, false, false, 1, null, false) - playerAttributes[3]) * 0.35f;
        cost *= form.getKiDrain();
        int r2 = release < 5 ? 5 : (int)release;
        cost *= (float)r2 * 0.01f;
        DBCAFormMastery mastery = form.getMastery(p);
        double formMasteryReduction = mastery.level * mastery.MasteryData.kiDrainMultiplier;
        cost = (float)((double)cost - (double)cost * formMasteryReduction);
        int maxEnergy = jgPlayer.get().getEnergyMax(jgPlayer.get().getRace(), jgPlayer.get().getClassID(), (byte)1, playerAttributes, JRMCoreH.SklLvl_KiBs(jgPlayer.get().getSkills(), 1));
        int energy = jgPlayer.get().getEnergy() + (int)cost;
        if (energy < 0) {
            nbt.func_74768_a("jrmcRelease", 0);
            energy = 0;
            dbcaPlayer.descend();
        }
        if ((ko = nbt.func_74762_e("jrmcHar4va")) > 0) {
            nbt.func_74768_a("jrmcRelease", 0);
            energy = 0;
            dbcaPlayer.descend();
        }
        int newEnergy = energy > maxEnergy ? maxEnergy : energy;
        JRMCoreH.setInt(newEnergy, p, "jrmcEnrgy");
    }

    @Inject(method={"serverTick"}, at={@At(value="INVOKE", target="LJinRyuu/JRMCore/JRMCoreH;isPowerTypeKi(I)Z", ordinal=1)}, cancellable=true)
    private void handleTickServer(MinecraftServer server, CallbackInfo ci, @Local(name={"jgPlayer"}) LocalRef<JGPlayerMP> jgPlayer) {
        int ko;
        int formID;
        if (!DBCAConfig.CustomForms) {
            return;
        }
        EntityPlayer p = jgPlayer.get().player;
        DBCAPlayer dbcaPlayer = DBCAPlayer.get(p);
        NBTTagCompound nbt = jgPlayer.get().getNBT();
        if (dbcaPlayer.PotaraFusion) {
            String[] fusionParticipants;
            boolean keep = false;
            String fusionMembers = nbt.func_74779_i("jrmcFuzion");
            if (fusionMembers.length() > 0 && !fusionMembers.equals(" ") && (fusionParticipants = fusionMembers.split(",")).length == 3) {
                keep = true;
            }
            if (!keep) {
                dbcaPlayer.setPotaraFusion(false);
                dbcaPlayer.saveNBTData();
            }
        }
        if ((formID = dbcaPlayer.DBAForm) <= 0) {
            return;
        }
        DBCAForm form = DBCAForms.getForm(formID);
        if (form == null) {
            return;
        }
        FormItem item = FormItemsDBA.getFormItemByForm(form.getID());
        if (!(item == null || item.isRaceCorrect(jgPlayer.get().getRace(), (byte)dbcaPlayer.DBARace) && item.canTransform(p))) {
            dbcaPlayer.descend();
            return;
        }
        if (JRMCoreH.isInCreativeMode((Entity)p)) {
            return;
        }
        byte release = jgPlayer.get().getRelease();
        int[] playerAttributes = jgPlayer.get().getAttributes();
        String s1 = nbt.func_74779_i("jrmcSSltX");
        int st = 6;
        float cost = (float)(JRMCoreH.getPlayerAttribute(playerAttributes, 0, st, 0, 1, s1, jgPlayer.get().getRelease(), jgPlayer.get().getReserve(), false, false, false, false, false, 1, null, false) - playerAttributes[0]) * 0.4f + (float)(JRMCoreH.getPlayerAttribute(playerAttributes, 1, st, 0, 1, s1, jgPlayer.get().getRelease(), jgPlayer.get().getReserve(), false, false, false, false, false, 1, null, false) - playerAttributes[1]) * 0.25f + (float)(JRMCoreH.getPlayerAttribute(playerAttributes, 3, st, 0, 1, s1, jgPlayer.get().getRelease(), jgPlayer.get().getReserve(), false, false, false, false, false, 1, null, false) - playerAttributes[3]) * 0.35f;
        cost *= form.getKiDrain();
        int r2 = release < 5 ? 5 : (int)release;
        cost *= (float)r2 * 0.01f;
        DBCAFormMastery mastery = form.getMastery(p);
        double formMasteryReduction = mastery.level * mastery.MasteryData.kiDrainMultiplier;
        cost = (float)((double)cost - (double)cost * formMasteryReduction);
        int maxEnergy = jgPlayer.get().getEnergyMax(jgPlayer.get().getRace(), jgPlayer.get().getClassID(), (byte)1, playerAttributes, JRMCoreH.SklLvl_KiBs(jgPlayer.get().getSkills(), 1));
        int energy = jgPlayer.get().getEnergy() + (int)cost;
        if (energy < 0) {
            nbt.func_74768_a("jrmcRelease", 0);
            energy = 0;
            dbcaPlayer.descend();
        }
        if ((ko = nbt.func_74762_e("jrmcHar4va")) > 0) {
            nbt.func_74768_a("jrmcRelease", 0);
            energy = 0;
            dbcaPlayer.descend();
        }
        int newEnergy = energy > maxEnergy ? maxEnergy : energy;
        JRMCoreH.setInt(newEnergy, p, "jrmcEnrgy");
    }
}
