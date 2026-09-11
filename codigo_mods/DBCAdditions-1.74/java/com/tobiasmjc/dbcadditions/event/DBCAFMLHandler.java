/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedOutEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$ServerTickEvent
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.DamageSource
 */
package com.tobiasmjc.dbcadditions.event;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.server.JGPlayerMP;
import com.tobiasmjc.dbcadditions.DBCAConfig;
import com.tobiasmjc.dbcadditions.DBCAKeys;
import com.tobiasmjc.dbcadditions.data.DBCAPlayer;
import com.tobiasmjc.dbcadditions.data.ability.AbilityData;
import com.tobiasmjc.dbcadditions.data.ability.DBCAAbilities;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForm;
import com.tobiasmjc.dbcadditions.data.forms.DBCAFormMastery;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForms;
import com.tobiasmjc.dbcadditions.data.forms.FormItem;
import com.tobiasmjc.dbcadditions.data.forms.FormItemsDBA;
import com.tobiasmjc.dbcadditions.data.races.DBCARace;
import com.tobiasmjc.dbcadditions.data.races.DBCARaces;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkill;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkills;
import com.tobiasmjc.dbcadditions.entities.DBASpawnCheck;
import com.tobiasmjc.dbcadditions.packets.DBUPacketAbsorb;
import com.tobiasmjc.dbcadditions.packets.DBUPacketResetData;
import com.tobiasmjc.dbcadditions.packets.DBUPacketSync;
import com.tobiasmjc.dbcadditions.packets.DBUPackets;
import com.tobiasmjc.dbcadditions.utils.LivingUtils;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;

public class DBCAFMLHandler {
    private int timer;
    private int players;
    private boolean initData;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.ClientTickEvent event) {
        if (event.side == Side.SERVER) {
            return;
        }
        DBCAKeys.update();
    }

    @SubscribeEvent
    public void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        if (DBCAAbilities.isAbsorbing(event.player)) {
            DBCAAbilities.stopAbsorbing(event.player);
        }
        DBUPackets.sendToClient(new DBUPacketResetData(), (EntityPlayerMP)event.player);
    }

    private void updateData(MinecraftServer server) {
        DBUPackets.sendToAll(new DBUPacketResetData());
        NBTTagCompound compound = new NBTTagCompound();
        NBTTagList list = new NBTTagList();
        for (DBCAForm form : DBCAForms.HOST_FORMS) {
            list.func_74742_a((NBTBase)form.write());
            if (list.func_74745_c() <= 4) continue;
            compound = new NBTTagCompound();
            compound.func_74782_a("Forms", (NBTBase)list);
            DBUPackets.sendToAll(new DBUPacketSync(1, compound));
            list = new NBTTagList();
        }
        if (list.func_74745_c() > 0) {
            compound.func_74782_a("Forms", (NBTBase)list);
            DBUPackets.sendToAll(new DBUPacketSync(1, compound));
        }
        compound = new NBTTagCompound();
        list = new NBTTagList();
        for (DBCASkill skill : DBCASkills.HOST_SKILLS) {
            list.func_74742_a((NBTBase)skill.write());
            if (list.func_74745_c() <= 4) continue;
            compound = new NBTTagCompound();
            compound.func_74782_a("Skills", (NBTBase)list);
            DBUPackets.sendToAll(new DBUPacketSync(2, compound));
            list = new NBTTagList();
        }
        if (list.func_74745_c() > 0) {
            compound.func_74782_a("Skills", (NBTBase)list);
            DBUPackets.sendToAll(new DBUPacketSync(2, compound));
        }
        compound = new NBTTagCompound();
        list = new NBTTagList();
        for (FormItem form : FormItemsDBA.FormItemsHost) {
            list.func_74742_a((NBTBase)form.write());
            if (list.func_74745_c() <= 4) continue;
            compound = new NBTTagCompound();
            compound.func_74782_a("Forms", (NBTBase)list);
            DBUPackets.sendToAll(new DBUPacketSync(0, compound));
            list = new NBTTagList();
        }
        if (list.func_74745_c() > 0) {
            compound.func_74782_a("Forms", (NBTBase)list);
            DBUPackets.sendToAll(new DBUPacketSync(0, compound));
        }
        compound = new NBTTagCompound();
        list = new NBTTagList();
        for (DBCARace race : DBCARaces.HOST_RACES) {
            NBTTagCompound r = new NBTTagCompound();
            r.func_74774_a("ID", race.ID);
            r.func_74783_a("TPCosts", race.getTPCosts());
            r.func_74783_a("MindCosts", race.getMindCosts());
            r.func_74780_a("Multiplier", race.getBaseMultiplier());
            list.func_74742_a((NBTBase)r);
            if (list.func_74745_c() <= 8) continue;
            compound = new NBTTagCompound();
            compound.func_74782_a("Races", (NBTBase)list);
            DBUPackets.sendToAll(new DBUPacketSync(3, compound));
            list = new NBTTagList();
        }
        if (list.func_74745_c() > 0) {
            compound.func_74782_a("Races", (NBTBase)list);
            DBUPackets.sendToAll(new DBUPacketSync(3, compound));
        }
        compound = new NBTTagCompound();
        compound.func_74774_a("SaiyanRacial", DBCAConfig.HSaiyanMaxRacial);
        compound.func_74774_a("HumanRacial", DBCAConfig.HHumanMaxRacial);
        compound.func_74774_a("ArcosianRacial", DBCAConfig.HArcosianMaxRacial);
        compound.func_74774_a("NamekianRacial", DBCAConfig.HNamekianMaxRacial);
        compound.func_74774_a("MajinRacial", DBCAConfig.HMajinMaxRacial);
        compound.func_74783_a("SaiyanTPCosts", DBCAConfig.HSaiyanTPCosts);
        compound.func_74783_a("HumanTPCosts", DBCAConfig.HHumanTPCosts);
        compound.func_74783_a("ArcosianTPCosts", DBCAConfig.HArcosianTPCosts);
        compound.func_74783_a("NamekianTPCosts", DBCAConfig.HNamekianTPCosts);
        compound.func_74783_a("MajinTPCosts", DBCAConfig.HMajinTPCosts);
        compound.func_74783_a("SaiyanMindCosts", DBCAConfig.HSaiyanMindCosts);
        compound.func_74783_a("HumanMindCosts", DBCAConfig.HHumanMindCosts);
        compound.func_74783_a("ArcosianMindCosts", DBCAConfig.HArcosianMindCosts);
        compound.func_74783_a("NamekianMindCosts", DBCAConfig.HNamekianMindCosts);
        compound.func_74783_a("MajinMindCosts", DBCAConfig.HMajinMindCosts);
        compound.func_74768_a("PotaraMinLevel", DBCAConfig.HPotaraMinLevel);
        DBUPackets.sendToAll(new DBUPacketSync(4, compound));
        this.players = server.func_71233_x();
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        DBCAPlayer dbcaPlayer;
        EntityPlayerMP player;
        DBASpawnCheck.spawnCheck();
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        if (!server.func_71264_H() && !this.initData) {
            DBCAForms.FORMS = new LinkedHashSet<DBCAForm>(DBCAForms.HOST_FORMS);
            DBCASkills.SKILLS = new LinkedHashSet<DBCASkill>(DBCASkills.HOST_SKILLS);
            FormItemsDBA.FormItems = new LinkedHashSet<FormItem>(FormItemsDBA.FormItemsHost);
            this.initData = true;
        }
        if (this.players != server.func_71233_x()) {
            this.updateData(server);
        }
        if (event.side == Side.SERVER) {
            for (Map.Entry<String, AbilityData> ability : DBCAAbilities.AbsorbingMap.entrySet()) {
                Optional<Object> opt = server.func_71203_ab().field_72404_b.stream().filter(p -> p instanceof EntityPlayer && ((EntityPlayer)p).func_70005_c_().equals(ability.getKey())).findFirst();
                if (!opt.isPresent()) {
                    DBCAAbilities.stopAbsorption(ability.getKey());
                    DBUPackets.sendToAll(new DBUPacketAbsorb(ability.getKey(), -1, false));
                    continue;
                }
                EntityPlayer player2 = (EntityPlayer)opt.get();
                AbilityData data = ability.getValue();
                if (player2 == null || data == null) {
                    DBCAAbilities.stopAbsorption(ability.getKey());
                    DBUPackets.sendToAll(new DBUPacketAbsorb(ability.getKey(), -1, false));
                    continue;
                }
                data.time = (float)((double)data.time + 0.5);
                Entity target = player2.field_70170_p.func_73045_a(data.target);
                if (target != null && !(target instanceof EntityCreature) || target == null) {
                    DBCAAbilities.stopAbsorbing(player2);
                    DBUPackets.sendToAll(new DBUPacketAbsorb(player2.func_70005_c_(), target == null ? -1 : target.func_145782_y(), false));
                    continue;
                }
                if (data.time % 20.0f == 0.0f) {
                    player2.field_70170_p.func_72956_a((Entity)player2, "dbcadditions:bioandroid.absorb", 3.0f, player2.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
                    int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(player2);
                    byte rce = JRMCoreH.getByte(player2, "jrmcRace");
                    byte clss = JRMCoreH.getByte(player2, "jrmcClass");
                    int maxBody = JRMCoreH.stat((Entity)player2, 2, 1, 2, PlyrAttrbts[2], rce, clss, 0.0f);
                    int maxEnergy = JRMCoreH.stat((Entity)player2, 5, 1, 5, PlyrAttrbts[5], rce, clss, JRMCoreH.SklLvl_KiBs(player2, 1));
                    float damage = (float)((double)PlyrAttrbts[0] * (Math.random() * (double)0.15f + (double)1.73f));
                    int currBody = JRMCoreH.getInt(player2, "jrmcBdy");
                    int currEnergy = JRMCoreH.getInt(player2, "jrmcEnrgy");
                    float health = ((EntityCreature)target).func_110143_aJ();
                    if ((double)data.receivedDamage >= (double)maxBody / (100.0 / DBCAConfig.AbsorbMaxDamage)) {
                        LivingUtils.knockback((EntityLivingBase)player2, (Entity)((EntityLivingBase)target), 4);
                        DBCAAbilities.stopAbsorbing(player2);
                        DBUPackets.sendToAll(new DBUPacketAbsorb(player2.func_70005_c_(), target.func_145782_y(), false));
                        continue;
                    }
                    data.totalDamage = (int)((float)data.totalDamage + damage);
                    currBody = (int)((double)currBody + (double)damage * (Math.random() * 0.5 + 0.6));
                    currEnergy = (int)((double)currEnergy + (double)damage * (Math.random() * 0.5 + 0.5));
                    player2.func_71024_bL().func_75122_a(Math.min(8, Math.max(2, (int)health / 50)), 0.5f);
                    if (health - damage <= 0.0f) {
                        int tps = (int)((double)((int)((double)data.totalDamage / (3.0 + Math.random()))) * DBCAConfig.AbsorbTPMultiplier);
                        int currTPS = JRMCoreH.getInt(player2, "jrmcTpint");
                        JRMCoreH.setInt(Math.min(currTPS + tps, 2000000000), player2, "jrmcTpint");
                    }
                    JRMCoreH.setInt(Math.min(maxEnergy, currEnergy), player2, "jrmcEnrgy");
                    JRMCoreH.setInt(Math.min(maxBody, currBody), player2, "jrmcBdy");
                    target.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)player2), damage);
                }
                if ((double)data.time >= DBCAConfig.AbsorbTimeLimit * 20.0 || target == null || !target.func_70089_S()) {
                    DBCAAbilities.stopAbsorbing(player2);
                    DBUPackets.sendToAll(new DBUPacketAbsorb(player2.func_70005_c_(), target.func_145782_y(), false));
                    continue;
                }
                float yawRad = (float)Math.toRadians(player2.field_70177_z);
                double newX = player2.field_70165_t + -Math.sin(yawRad) / 1.4;
                double newZ = player2.field_70161_v + Math.cos(yawRad) / 1.4;
                double newY = player2.field_70163_u;
                target.func_70012_b(newX, newY, newZ, 0.0f, 0.0f);
                DBCAFMLHandler.playerLookAtEntity(player2, target);
                player2.func_70634_a(player2.field_70165_t, player2.field_70163_u, player2.field_70161_v);
                target.field_70159_w = 0.0;
                target.field_70181_x = 0.0;
                target.field_70179_y = 0.0;
            }
        }
        if (event.phase == TickEvent.Phase.START) {
            ++this.timer;
        }
        if (this.timer % 40 == 0) {
            for (String playerName : MinecraftServer.func_71276_C().func_71213_z()) {
                player = JRMCoreH.getPlayerForUsername(MinecraftServer.func_71276_C(), playerName);
                dbcaPlayer = DBCAPlayer.get((EntityPlayer)player);
                if (dbcaPlayer.DBAForm <= 0) continue;
                if (dbcaPlayer.getRelease() <= 0 || DBCAForms.getForm(dbcaPlayer.DBAForm) == null) {
                    dbcaPlayer.descend();
                    return;
                }
                JGPlayerMP jgPlayer = new JGPlayerMP((EntityPlayer)player);
                jgPlayer.connectBaseNBT();
                int maxhp = jgPlayer.getHealthMax(jgPlayer.getRace(), jgPlayer.getClassID(), jgPlayer.getPowerType(), jgPlayer.getAttributes());
                int hp = jgPlayer.getHealth();
                if (jgPlayer.getState2() > 0 && hp <= 1) {
                    dbcaPlayer.descend();
                    return;
                }
                if (JRMCoreH.isInCreativeMode((Entity)player)) {
                    return;
                }
                DBCAForm form = DBCAForms.getForm(dbcaPlayer.DBAForm);
                if (!(form.getHealthDrain() > 0.0f)) continue;
                DBCAFormMastery mastery = form.getMastery((EntityPlayer)player);
                double cost = (double)((float)maxhp * form.getHealthDrain()) / 100.0;
                cost -= cost / 1.5 * mastery.level * mastery.MasteryData.healthDrainMultiplier;
                cost = Math.max(cost, 0.0);
                int newHP = (int)Math.max((double)hp - cost, 1.0);
                jgPlayer.getNBT().func_74768_a("jrmcBdy", newHP);
                if (newHP != 1) continue;
                dbcaPlayer.descend();
                jgPlayer.getNBT().func_74768_a("jrmcHar4va", 5);
            }
        }
        if (this.timer % 600 == 0 && this.timer > 0 && event.phase == TickEvent.Phase.START) {
            for (String playerName : MinecraftServer.func_71276_C().func_71213_z()) {
                player = JRMCoreH.getPlayerForUsername(MinecraftServer.func_71276_C(), playerName);
                dbcaPlayer = DBCAPlayer.get((EntityPlayer)player);
                if (dbcaPlayer.PotaraCooldown <= 0) continue;
                --dbcaPlayer.PotaraCooldown;
                dbcaPlayer.saveNBTData();
            }
        }
    }

    private static void playerLookAtEntity(EntityPlayer player, Entity target) {
        if (player != null && target != null) {
            float yaw;
            double deltaX = target.field_70165_t - player.field_70165_t;
            double deltaY = target.field_70163_u + (double)target.func_70047_e() - (player.field_70163_u + (double)player.func_70047_e());
            double deltaZ = target.field_70161_v - player.field_70161_v;
            player.field_70177_z = yaw = (float)(Math.atan2(deltaZ, deltaX) * 57.29577951308232) - 90.0f;
        }
    }
}

