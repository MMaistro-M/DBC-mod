/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 */
package JinRyuu.DragonBC.common.Gui;

import JinRyuu.DragonBC.common.DBCH;
import JinRyuu.DragonBC.common.Npcs.EntityAngelAwamo;
import JinRyuu.DragonBC.common.Npcs.EntityAngelCamparri;
import JinRyuu.DragonBC.common.Npcs.EntityAngelCognac;
import JinRyuu.DragonBC.common.Npcs.EntityAngelCukatail;
import JinRyuu.DragonBC.common.Npcs.EntityAngelKorn;
import JinRyuu.DragonBC.common.Npcs.EntityAngelKusu;
import JinRyuu.DragonBC.common.Npcs.EntityAngelMarcarita;
import JinRyuu.DragonBC.common.Npcs.EntityAngelMartinu;
import JinRyuu.DragonBC.common.Npcs.EntityAngelMohito;
import JinRyuu.DragonBC.common.Npcs.EntityAngelSour;
import JinRyuu.DragonBC.common.Npcs.EntityAniraza;
import JinRyuu.DragonBC.common.Npcs.EntityBarta;
import JinRyuu.DragonBC.common.Npcs.EntityBasil;
import JinRyuu.DragonBC.common.Npcs.EntityBeerus;
import JinRyuu.DragonBC.common.Npcs.EntityBeerusMonaka;
import JinRyuu.DragonBC.common.Npcs.EntityBeerusMonaka2;
import JinRyuu.DragonBC.common.Npcs.EntityBeerusMonaka3;
import JinRyuu.DragonBC.common.Npcs.EntityBergamo;
import JinRyuu.DragonBC.common.Npcs.EntityBerryblue;
import JinRyuu.DragonBC.common.Npcs.EntityBiarra;
import JinRyuu.DragonBC.common.Npcs.EntityBorareta;
import JinRyuu.DragonBC.common.Npcs.EntityBotamo;
import JinRyuu.DragonBC.common.Npcs.EntityBrianne;
import JinRyuu.DragonBC.common.Npcs.EntityBuuBuffed;
import JinRyuu.DragonBC.common.Npcs.EntityBuuEvil;
import JinRyuu.DragonBC.common.Npcs.EntityBuuFat;
import JinRyuu.DragonBC.common.Npcs.EntityBuuFusion;
import JinRyuu.DragonBC.common.Npcs.EntityBuuKid;
import JinRyuu.DragonBC.common.Npcs.EntityBuuPiccolo;
import JinRyuu.DragonBC.common.Npcs.EntityBuuSuper;
import JinRyuu.DragonBC.common.Npcs.EntityBuuUltimate;
import JinRyuu.DragonBC.common.Npcs.EntityCabba;
import JinRyuu.DragonBC.common.Npcs.EntityCabbaSSJ;
import JinRyuu.DragonBC.common.Npcs.EntityCaulifla;
import JinRyuu.DragonBC.common.Npcs.EntityCaulifla2;
import JinRyuu.DragonBC.common.Npcs.EntityCell1;
import JinRyuu.DragonBC.common.Npcs.EntityCell2;
import JinRyuu.DragonBC.common.Npcs.EntityCell3;
import JinRyuu.DragonBC.common.Npcs.EntityCell4;
import JinRyuu.DragonBC.common.Npcs.EntityCellJr;
import JinRyuu.DragonBC.common.Npcs.EntityChampa;
import JinRyuu.DragonBC.common.Npcs.EntityCocotte;
import JinRyuu.DragonBC.common.Npcs.EntityCyborg16;
import JinRyuu.DragonBC.common.Npcs.EntityCyborg17;
import JinRyuu.DragonBC.common.Npcs.EntityCyborg18;
import JinRyuu.DragonBC.common.Npcs.EntityCyborg19;
import JinRyuu.DragonBC.common.Npcs.EntityCyborg20;
import JinRyuu.DragonBC.common.Npcs.EntityDBC;
import JinRyuu.DragonBC.common.Npcs.EntityDBCEvil;
import JinRyuu.DragonBC.common.Npcs.EntityDarbura;
import JinRyuu.DragonBC.common.Npcs.EntityDercori;
import JinRyuu.DragonBC.common.Npcs.EntityDino01;
import JinRyuu.DragonBC.common.Npcs.EntityDino02;
import JinRyuu.DragonBC.common.Npcs.EntityDino03;
import JinRyuu.DragonBC.common.Npcs.EntityDodoria;
import JinRyuu.DragonBC.common.Npcs.EntityDyspo;
import JinRyuu.DragonBC.common.Npcs.EntityFreeza1;
import JinRyuu.DragonBC.common.Npcs.EntityFreeza2;
import JinRyuu.DragonBC.common.Npcs.EntityFreeza3;
import JinRyuu.DragonBC.common.Npcs.EntityFreeza4;
import JinRyuu.DragonBC.common.Npcs.EntityFreeza5;
import JinRyuu.DragonBC.common.Npcs.EntityFreeza6;
import JinRyuu.DragonBC.common.Npcs.EntityFreeza7;
import JinRyuu.DragonBC.common.Npcs.EntityFreezaFather;
import JinRyuu.DragonBC.common.Npcs.EntityFreezaMecha;
import JinRyuu.DragonBC.common.Npcs.EntityFreezaSoldier1;
import JinRyuu.DragonBC.common.Npcs.EntityFreezaSoldier2;
import JinRyuu.DragonBC.common.Npcs.EntityFreezaSoldier3;
import JinRyuu.DragonBC.common.Npcs.EntityFrost1;
import JinRyuu.DragonBC.common.Npcs.EntityFrost2;
import JinRyuu.DragonBC.common.Npcs.EntityFrost3;
import JinRyuu.DragonBC.common.Npcs.EntityFrost4;
import JinRyuu.DragonBC.common.Npcs.EntityFrost5;
import JinRyuu.DragonBC.common.Npcs.EntityFrost6;
import JinRyuu.DragonBC.common.Npcs.EntityGanos;
import JinRyuu.DragonBC.common.Npcs.EntityGanos2;
import JinRyuu.DragonBC.common.Npcs.EntityGinyu;
import JinRyuu.DragonBC.common.Npcs.EntityGodArak;
import JinRyuu.DragonBC.common.Npcs.EntityGodBelmod;
import JinRyuu.DragonBC.common.Npcs.EntityGodGiin;
import JinRyuu.DragonBC.common.Npcs.EntityGodHeles;
import JinRyuu.DragonBC.common.Npcs.EntityGodIwan;
import JinRyuu.DragonBC.common.Npcs.EntityGodLiquiir;
import JinRyuu.DragonBC.common.Npcs.EntityGodMosco;
import JinRyuu.DragonBC.common.Npcs.EntityGodQuitela;
import JinRyuu.DragonBC.common.Npcs.EntityGodRumsshi;
import JinRyuu.DragonBC.common.Npcs.EntityGodSidra;
import JinRyuu.DragonBC.common.Npcs.EntityGohan;
import JinRyuu.DragonBC.common.Npcs.EntityGohan2;
import JinRyuu.DragonBC.common.Npcs.EntityGohan_orange;
import JinRyuu.DragonBC.common.Npcs.EntityGohan_orange2;
import JinRyuu.DragonBC.common.Npcs.EntityGoku;
import JinRyuu.DragonBC.common.Npcs.EntityGoku2;
import JinRyuu.DragonBC.common.Npcs.EntityGoku3;
import JinRyuu.DragonBC.common.Npcs.EntityGoku4;
import JinRyuu.DragonBC.common.Npcs.EntityGokuBlack;
import JinRyuu.DragonBC.common.Npcs.EntityGokuBlackRose;
import JinRyuu.DragonBC.common.Npcs.EntityGuldo;
import JinRyuu.DragonBC.common.Npcs.EntityHell01;
import JinRyuu.DragonBC.common.Npcs.EntityHell02;
import JinRyuu.DragonBC.common.Npcs.EntityHit;
import JinRyuu.DragonBC.common.Npcs.EntityHop;
import JinRyuu.DragonBC.common.Npcs.EntityJeice;
import JinRyuu.DragonBC.common.Npcs.EntityJiren;
import JinRyuu.DragonBC.common.Npcs.EntityJiren2;
import JinRyuu.DragonBC.common.Npcs.EntityKahseral;
import JinRyuu.DragonBC.common.Npcs.EntityKakunsa;
import JinRyuu.DragonBC.common.Npcs.EntityKale;
import JinRyuu.DragonBC.common.Npcs.EntityKale2;
import JinRyuu.DragonBC.common.Npcs.EntityKatopesla;
import JinRyuu.DragonBC.common.Npcs.EntityKatopesla2;
import JinRyuu.DragonBC.common.Npcs.EntityKatopesla3;
import JinRyuu.DragonBC.common.Npcs.EntityKatopesla4;
import JinRyuu.DragonBC.common.Npcs.EntityKefla;
import JinRyuu.DragonBC.common.Npcs.EntityKefla2;
import JinRyuu.DragonBC.common.Npcs.EntityKiwi;
import JinRyuu.DragonBC.common.Npcs.EntityKoichiarator;
import JinRyuu.DragonBC.common.Npcs.EntityKoitsukai;
import JinRyuu.DragonBC.common.Npcs.EntityKrillin;
import JinRyuu.DragonBC.common.Npcs.EntityKunshi;
import JinRyuu.DragonBC.common.Npcs.EntityLavender;
import JinRyuu.DragonBC.common.Npcs.EntityMagetta;
import JinRyuu.DragonBC.common.Npcs.EntityMajora;
import JinRyuu.DragonBC.common.Npcs.EntityMonaka;
import JinRyuu.DragonBC.common.Npcs.EntityMurichim;
import JinRyuu.DragonBC.common.Npcs.EntityNamekian01;
import JinRyuu.DragonBC.common.Npcs.EntityNapapa;
import JinRyuu.DragonBC.common.Npcs.EntityNarirama;
import JinRyuu.DragonBC.common.Npcs.EntityObni;
import JinRyuu.DragonBC.common.Npcs.EntityPanchia;
import JinRyuu.DragonBC.common.Npcs.EntityPaparoni;
import JinRyuu.DragonBC.common.Npcs.EntityPiccolo;
import JinRyuu.DragonBC.common.Npcs.EntityPiccolo2;
import JinRyuu.DragonBC.common.Npcs.EntityPuipui;
import JinRyuu.DragonBC.common.Npcs.EntityRRMecha;
import JinRyuu.DragonBC.common.Npcs.EntityRecoome;
import JinRyuu.DragonBC.common.Npcs.EntityRoasie;
import JinRyuu.DragonBC.common.Npcs.EntityRoshi_super;
import JinRyuu.DragonBC.common.Npcs.EntityRylibeu;
import JinRyuu.DragonBC.common.Npcs.EntitySabertooth;
import JinRyuu.DragonBC.common.Npcs.EntitySaibaiman;
import JinRyuu.DragonBC.common.Npcs.EntitySaiyan01;
import JinRyuu.DragonBC.common.Npcs.EntitySaiyan02;
import JinRyuu.DragonBC.common.Npcs.EntitySaiyanNappa;
import JinRyuu.DragonBC.common.Npcs.EntitySaiyanRaditz;
import JinRyuu.DragonBC.common.Npcs.EntitySaiyanVegeta;
import JinRyuu.DragonBC.common.Npcs.EntityShisami;
import JinRyuu.DragonBC.common.Npcs.EntityShosa;
import JinRyuu.DragonBC.common.Npcs.EntitySorbet;
import JinRyuu.DragonBC.common.Npcs.EntitySorrel;
import JinRyuu.DragonBC.common.Npcs.EntityTagoma;
import JinRyuu.DragonBC.common.Npcs.EntityToppo;
import JinRyuu.DragonBC.common.Npcs.EntityToppo2;
import JinRyuu.DragonBC.common.Npcs.EntityTrunks;
import JinRyuu.DragonBC.common.Npcs.EntityTrunks2;
import JinRyuu.DragonBC.common.Npcs.EntityTrunks_dbs;
import JinRyuu.DragonBC.common.Npcs.EntityTrunks_dbs2;
import JinRyuu.DragonBC.common.Npcs.EntityVados;
import JinRyuu.DragonBC.common.Npcs.EntityVegeta;
import JinRyuu.DragonBC.common.Npcs.EntityVegeta2;
import JinRyuu.DragonBC.common.Npcs.EntityVegeta3;
import JinRyuu.DragonBC.common.Npcs.EntityVegeta4;
import JinRyuu.DragonBC.common.Npcs.EntityVegetaCopy;
import JinRyuu.DragonBC.common.Npcs.EntityVegetaCopyBlue;
import JinRyuu.DragonBC.common.Npcs.EntityWhis;
import JinRyuu.DragonBC.common.Npcs.EntityYakon;
import JinRyuu.DragonBC.common.Npcs.EntityZamasu;
import JinRyuu.DragonBC.common.Npcs.EntityZamasu_Fused;
import JinRyuu.DragonBC.common.Npcs.EntityZamasu_Fused2;
import JinRyuu.DragonBC.common.Npcs.EntityZamasu_Fused3;
import JinRyuu.DragonBC.common.Npcs.EntityZarbon;
import JinRyuu.DragonBC.common.Npcs.EntityZarbon2;
import JinRyuu.DragonBC.common.Npcs.EntityZeno;
import JinRyuu.DragonBC.common.Npcs.db.EntityBearThief;
import JinRyuu.DragonBC.common.Npcs.db.EntityBora;
import JinRyuu.DragonBC.common.Npcs.db.EntityDBMasterRoshi;
import JinRyuu.DragonBC.common.Npcs.db.EntityDBMasterRoshi2;
import JinRyuu.DragonBC.common.Npcs.db.EntityDBMasterRoshi3;
import JinRyuu.DragonBC.common.Npcs.db.EntityOolong;
import JinRyuu.DragonBC.common.Npcs.db.EntityPuar;
import JinRyuu.DragonBC.common.Npcs.db.EntityTigerBandit;
import JinRyuu.DragonBC.common.Npcs.db.EntityUpa;
import JinRyuu.DragonBC.common.Npcs.db.EntityYajirobe;
import JinRyuu.DragonBC.common.Npcs.db.EntityYamcha;
import JinRyuu.DragonBC.common.Npcs.db.EntityYamcha2;
import JinRyuu.DragonBC.common.Npcs.db.EntityYamcha3;
import JinRyuu.DragonBC.common.Npcs.db.EntityYamcha4;
import JinRyuu.DragonBC.common.Npcs.db.EntityYamcha5;
import JinRyuu.DragonBC.common.Npcs.dbbaba.EntityDevil;
import JinRyuu.DragonBC.common.Npcs.dbbaba.EntityFortunetellerBaba;
import JinRyuu.DragonBC.common.Npcs.dbbaba.EntityGrandpaGohan;
import JinRyuu.DragonBC.common.Npcs.dbbaba.EntityGrandpaGohan2;
import JinRyuu.DragonBC.common.Npcs.dbbaba.EntityInvisibleMan;
import JinRyuu.DragonBC.common.Npcs.dbbaba.EntityMummy;
import JinRyuu.DragonBC.common.Npcs.dbbaba.EntityVampire;
import JinRyuu.DragonBC.common.Npcs.dbkingpiccolo.EntityCymbal;
import JinRyuu.DragonBC.common.Npcs.dbkingpiccolo.EntityDrum;
import JinRyuu.DragonBC.common.Npcs.dbkingpiccolo.EntityKingPiccolo;
import JinRyuu.DragonBC.common.Npcs.dbkingpiccolo.EntityKingPiccolo2;
import JinRyuu.DragonBC.common.Npcs.dbkingpiccolo.EntityPiano;
import JinRyuu.DragonBC.common.Npcs.dbkingpiccolo.EntityTambourine;
import JinRyuu.DragonBC.common.Npcs.dbpilaf.EntityMai;
import JinRyuu.DragonBC.common.Npcs.dbpilaf.EntityMaiMecha;
import JinRyuu.DragonBC.common.Npcs.dbpilaf.EntityPilaf;
import JinRyuu.DragonBC.common.Npcs.dbpilaf.EntityPilafMecha;
import JinRyuu.DragonBC.common.Npcs.dbpilaf.EntityPilafMechaCombined;
import JinRyuu.DragonBC.common.Npcs.dbpilaf.EntityShu;
import JinRyuu.DragonBC.common.Npcs.dbpilaf.EntityShuMecha;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityAndroid8;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityBuyon;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityColonelSilver;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityColonelViolet;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityCommanderRed;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityGeneralBlue;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityGeneralBlue2;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityGeneralWhite;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityGeneralWhite2;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityLaunch;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityLaunch2;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityMajorMetallitron;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityMajorMetallitron1;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityMajorMetallitron2;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityMajorMetallitron3;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityMercenaryTao;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityMercenaryTao2;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityMercenaryTao3;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityNinjaMurasaki;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityOfficerBlack;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityOfficerBlack2;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityOfficerBlack3;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityRedRibbonSoldier;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityRedRibbonSoldier2;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityRedRibbonSoldier3;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityRedRibbonSoldierB;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityRedRibbonSoldierB2;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityRedRibbonSoldierB3;
import JinRyuu.DragonBC.common.Npcs.dbsbroly.EntityCheelai;
import JinRyuu.DragonBC.common.Npcs.dbsbroly.EntityCheelai2;
import JinRyuu.DragonBC.common.Npcs.dbsbroly.EntityDBSBroly1;
import JinRyuu.DragonBC.common.Npcs.dbsbroly.EntityDBSBroly2;
import JinRyuu.DragonBC.common.Npcs.dbsbroly.EntityDBSBroly3;
import JinRyuu.DragonBC.common.Npcs.dbsbroly.EntityDBSBroly4;
import JinRyuu.DragonBC.common.Npcs.dbsbroly.EntityDBSBrolyBuff;
import JinRyuu.DragonBC.common.Npcs.dbsbroly.EntityDBSBrolyBuffSSJ;
import JinRyuu.DragonBC.common.Npcs.dbsbroly.EntityDBSBrolyLegendary;
import JinRyuu.DragonBC.common.Npcs.dbsbroly.EntityDBSParagus;
import JinRyuu.DragonBC.common.Npcs.dbsbroly.EntityDBSParagus2;
import JinRyuu.DragonBC.common.Npcs.dbsbroly.EntityLemo;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityBacterian;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityChiaotzu;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityChiaotzu2;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityChiaotzu3;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityChiaotzu4;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityGiran;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityJackieChun;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityJackieChun2;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityJackieChun3;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityKingChappa;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityManWolf;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityMasterShen;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityNam;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityPamput;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityTien;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityTien2;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityTien3;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityTien4;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityTien5;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityTien6;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityTien7;
import JinRyuu.DragonBC.common.Npcs.dbtournament.EntityTournamentAnnouncer;
import JinRyuu.DragonBC.common.Npcs.dbz.EntityOfficeOgre;
import JinRyuu.DragonBC.common.Npcs.dbz.EntityOfficeOgre2;
import JinRyuu.DragonBC.common.Npcs.dbz.EntityOfficeOgre3;
import JinRyuu.DragonBC.common.Npcs.dbz.EntityOfficeOgre4;
import JinRyuu.DragonBC.common.Npcs.dbz.EntityOfficeOgre5;
import JinRyuu.DragonBC.common.Npcs.dbz.EntitySpirit;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.server.JGEntityHelper;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class ComDbcSpawn
extends CommandBase {
    public String func_71517_b() {
        return "dbcspawn";
    }

    public String func_71518_a(ICommandSender icommandsender) {
        return "Use '/dbcspawn (DBCmobName) or /dbcspawn (DBCmobName) (Health)' to spawn the DBC mobs or /dbcspawn (DBCmobName) (Health) (Damage)' to spawn the DBC mobs.";
    }

    public int func_82362_a() {
        return 2;
    }

    public boolean func_71519_b(ICommandSender par1ICommandSender) {
        return MinecraftServer.func_71276_C().func_71264_H() || super.func_71519_b(par1ICommandSender);
    }

    private NBTTagCompound nbt(EntityPlayer p, String s) {
        return JRMCoreH.nbt((Entity)p, s);
    }

    public void func_71515_b(ICommandSender commandSender, String[] stringArray) {
        if (stringArray.length <= 0) {
            throw new WrongUsageException("Use '/dbcspawn (DBCmobName) or /dbcspawn (DBCmobName) (Health)' to spawn the DBC mobs or /dbcspawn (DBCmobName) (Health) (Damage)' to spawn the DBC mobs.", new Object[0]);
        }
        if (stringArray.length <= 1) {
            EntityPlayerMP entityplayermp = ComDbcSpawn.func_71521_c((ICommandSender)commandSender);
            EntityDBC[] ent = this.getEntityList(entityplayermp);
            String[] entnams = this.getEntityNameList();
            String s2 = stringArray[0].toLowerCase();
            EntityDBC entity = null;
            boolean spawned = false;
            for (int i1 = 0; i1 < entnams.length; ++i1) {
                if (!entnams[i1].toLowerCase().contains(s2)) continue;
                entity = ent[i1];
                entity.func_70029_a(entityplayermp.field_70170_p);
                String[] v = DBCH.NpcSpawnLoc((int)entityplayermp.field_70165_t, (int)entityplayermp.field_70163_u, (int)entityplayermp.field_70161_v, entityplayermp.field_70170_p).split(";");
                entity.func_70012_b(Double.parseDouble(v[0]), Double.parseDouble(v[1]), Double.parseDouble(v[2]), 0.0f, 0.0f);
                if (entity instanceof EntityDBCEvil) {
                    ((EntityDBCEvil)entity).setSpwner((Entity)entityplayermp);
                }
                entityplayermp.field_70170_p.func_72838_d((Entity)entity);
                this.notifyAdmins(commandSender, "%s spawned %s!", new Object[]{entityplayermp.func_70005_c_(), entnams[i1]});
                spawned = true;
                break;
            }
            if (!spawned) {
                this.notifyAdmins(commandSender, "%s couldn't spawn %s!", new Object[]{entityplayermp.func_70005_c_(), s2});
            }
        } else if (stringArray.length <= 4) {
            EntityPlayerMP entityplayermp = stringArray.length > 3 ? ComDbcSpawn.func_82359_c((ICommandSender)commandSender, (String)stringArray[3]) : ComDbcSpawn.func_71521_c((ICommandSender)commandSender);
            String s = stringArray[0];
            String stringHealth = "";
            String stringDamage = "";
            if (stringArray.length >= 2) {
                stringHealth = stringArray[1];
                if (stringArray.length == 3) {
                    stringDamage = stringArray[2];
                }
            }
            EntityDBC[] ent = this.getEntityList(entityplayermp);
            String[] entnams = this.getEntityNameList();
            String s2 = s.toLowerCase();
            EntityDBC entity = null;
            boolean spawned = false;
            for (int i1 = 0; i1 < entnams.length; ++i1) {
                if (!entnams[i1].toLowerCase().contains(s2)) continue;
                entity = ent[i1];
                entity.func_70029_a(entityplayermp.field_70170_p);
                String[] v = DBCH.NpcSpawnLoc((int)entityplayermp.field_70165_t, (int)entityplayermp.field_70163_u, (int)entityplayermp.field_70161_v, entityplayermp.field_70170_p).split(";");
                entity.func_70012_b(Double.parseDouble(v[0]), Double.parseDouble(v[1]), Double.parseDouble(v[2]), 0.0f, 0.0f);
                if (entity instanceof EntityDBCEvil) {
                    ((EntityDBCEvil)entity).setSpwner((Entity)entityplayermp);
                }
                NBTTagCompound nbt = JRMCoreH.nbt((Entity)entity);
                JGEntityHelper.setAttributes((EntityLivingBase)entity, stringHealth, stringDamage, "", "", 1);
                entityplayermp.field_70170_p.func_72838_d((Entity)entity);
                this.notifyAdmins(commandSender, "%s spawned %s!", new Object[]{entityplayermp.func_70005_c_(), entnams[i1]});
                spawned = true;
                break;
            }
            if (!spawned) {
                this.notifyAdmins(commandSender, "%s couldn't spawn %s!", new Object[]{entityplayermp.func_70005_c_(), s2});
            }
        }
    }

    private void notifyAdmins(ICommandSender commandSender, String string, Object[] objects) {
        ComDbcSpawn.func_152373_a((ICommandSender)commandSender, (ICommand)this, (String)string, (Object[])objects);
    }

    private String[] getEntityNameList() {
        String[] entityNames = new String[]{"Nam", "Piccolo", "Piccolo2", "Raditz", "Nappa", "Vegeta", "Saibaiman", "Saiyan01", "Saiyan02", "Ogre1", "Ogre2", "Kiwi", "Dodoria", "Zarbon", "Zarbon2", "Guldo", "Recoome", "Barta", "Jeice", "Ginyu", "FreezaSoldier1", "FreezaSoldier2", "FreezaSoldier3", "Freeza1", "Freeza2", "Freeza3", "Freeza4", "Freeza5", "Freeza6", "FreezaGolden", "KingCold", "MechaFreeza", "Cyborg16", "Cyborg17", "Cyborg18", "Cyborg19", "Cyborg20", "Cell1", "Cell2", "Cell3", "Cell4", "Namekian01", "PuiPui", "Yakon", "Dabura", "BuuFat", "BuuSuper", "Dino01", "Beerus", "Dino02", "Dino03", "RRMecha1", "Sabertooth", "CellJr", "BuuEvil", "BuuFusion", "BuuPiccolo", "BuuUltimate", "BuuBuffed", "BuuKid", "Monaka", "BeerusMonaka", "BeerusMonaka2", "BeerusMonaka3", "Berryblue", "Botamo", "Champa", "Frost1", "Frost2", "Frost3", "Frost4", "Frost5", "Frost6", "Hit", "Krillin", "Magetta", "Roshi_super", "Shisami", "Sorbet", "Tagoma", "Vados", "Whis", "GokuBlack", "GokuBlackRose", "VegetaCopy", "VegetaCopyBlue", "Cabba", "CabbaSSJ", "Zamasu", "Zamasu_fused", "Zamasu_fused2", "Zamasu_fused3", "Zeno", "Goku_enemy", "Goku_enemy2", "Goku_enemy3", "Goku_enemy4", "Vegeta_enemy", "Vegeta_enemy2", "Vegeta_enemy3", "Vegeta_enemy4", "Gohan_enemy", "Gohan_enemy2", "Gohan_enemy2_1", "Gohan_enemy2_2", "Trunks_enemy", "Trunks_enemy2", "Trunks_enemy2_1", "Trunks_enemy2_2", "Awamo", "Camparri", "Cognac", "Cuktail", "Korn", "Kusu", "Marcarita", "Martinu", "Mohito", "Sour", "Arak", "Belmod", "Giin", "Heles", "Iwan", "Liquiir", "Mosco", "Quitela", "Rumsshi", "Sidra", "Basil", "Bergamo", "Lavender", "Brianne", "Caulifla", "Caulifla2", "Dyspo", "Jiren", "Jiren2", "Kale", "Kale2", "Kefla", "Kefla2", "Toppo", "Toppo2", "Aniraza", "Biarra", "Cocotte", "Dercori", "Ganos", "Ganos2", "Hop", "Kahseral", "Kakunsa", "Kunshi", "Majora", "Murichim", "Napapa", "Narirama", "Obni", "Paparoni", "Roasie", "Rylibeu", "Shosa", "Sorrel", "Borareta", "Koichiarator", "Koitsukai", "Panchia", "Katopesla", "Katopesla2", "Katopesla3", "Katopesla4", "DBSBroly", "DBSBroly2", "DBSBroly3", "DBSBroly4", "DBSBrolyBuff", "DBSBrolyBuffSSJ", "DBSBrolyLegendary", "DBSParagus", "DBSParagusOld", "Cheelai", "Cheelai2", "Lemo", "FortunetellerBaba", "BandagesTheMummy", "SeeThroughTheInvisibleMan", "FangsTheVampire", "SpikeTheDevil", "GrandpaGohan", "GrandpaGohan2", "Cymbal", "Drum", "KingPiccolo", "KingPiccolo2", "Piano", "Tambourine", "DBMasterRoshi", "DBMasterRoshi2", "DBMasterRoshi3", "BearThief", "TigerBandit", "Puar", "Yamcha", "Yamcha2", "Yamcha3", "Yamcha4", "Yamcha5", "DBMai", "DBPilaf", "DBShu", "DBMaiMecha", "DBPilafMecha", "DBShuMecha", "DBPilafMechaCombined", "Bacterian", "Giran", "JackieChun", "JackieChun2", "JackieChun3", "TournamentAnnouncer", "KingChappa", "ManWolf", "MasterShen", "Pamput", "TienShinhan", "TienShinhan2", "TienShinhan3", "Android8", "Buyon", "ColonelSilver", "ColonelViolet", "CommanderRed", "GeneralBlue", "GeneralBlue2", "GeneralWhite", "GeneralWhite2", "LaunchGood", "LaunchBad", "MajorMetallitron", "MajorMetallitron1", "MajorMetallitron2", "MajorMetallitron3", "MercenaryTao", "NinjaMurasaki", "RedRibbonSoldier", "RedRibbonSoldier2", "RedRibbonSoldier3", "RedRibbonSoldierB", "RedRibbonSoldierB2", "RedRibbonSoldierB3", "DBBora", "DBUpa", "Oolong", "Yajirobe", "MercenaryTao2", "MercenaryTao3", "OfficerBlack", "OfficerBlack2", "OfficerBlack3", "OfficeOgre", "OfficeOgre2", "OfficeOgre3", "OfficeOgre4", "OfficeOgre5", "DBZSpirit", "Chiaotzu", "Chiaotzu2", "Chiaotzu3", "Chiaotzu4", "TienShinhan4", "TienShinhan5", "TienShinhan6", "TienShinhan7"};
        return entityNames;
    }

    private EntityDBC[] getEntityList(EntityPlayerMP entityplayermp) {
        EntityDBC[] entities = new EntityDBC[]{new EntityNam(entityplayermp.field_70170_p), new EntityPiccolo(entityplayermp.field_70170_p), new EntityPiccolo2(entityplayermp.field_70170_p), new EntitySaiyanRaditz(entityplayermp.field_70170_p), new EntitySaiyanNappa(entityplayermp.field_70170_p), new EntitySaiyanVegeta(entityplayermp.field_70170_p), new EntitySaibaiman(entityplayermp.field_70170_p), new EntitySaiyan01(entityplayermp.field_70170_p), new EntitySaiyan02(entityplayermp.field_70170_p), new EntityHell01(entityplayermp.field_70170_p), new EntityHell02(entityplayermp.field_70170_p), new EntityKiwi(entityplayermp.field_70170_p), new EntityDodoria(entityplayermp.field_70170_p), new EntityZarbon(entityplayermp.field_70170_p), new EntityZarbon2(entityplayermp.field_70170_p), new EntityGuldo(entityplayermp.field_70170_p), new EntityRecoome(entityplayermp.field_70170_p), new EntityBarta(entityplayermp.field_70170_p), new EntityJeice(entityplayermp.field_70170_p), new EntityGinyu(entityplayermp.field_70170_p), new EntityFreezaSoldier1(entityplayermp.field_70170_p), new EntityFreezaSoldier2(entityplayermp.field_70170_p), new EntityFreezaSoldier3(entityplayermp.field_70170_p), new EntityFreeza1(entityplayermp.field_70170_p), new EntityFreeza2(entityplayermp.field_70170_p), new EntityFreeza3(entityplayermp.field_70170_p), new EntityFreeza4(entityplayermp.field_70170_p), new EntityFreeza5(entityplayermp.field_70170_p), new EntityFreeza6(entityplayermp.field_70170_p), new EntityFreeza7(entityplayermp.field_70170_p), new EntityFreezaFather(entityplayermp.field_70170_p), new EntityFreezaMecha(entityplayermp.field_70170_p), new EntityCyborg16(entityplayermp.field_70170_p), new EntityCyborg17(entityplayermp.field_70170_p), new EntityCyborg18(entityplayermp.field_70170_p), new EntityCyborg19(entityplayermp.field_70170_p), new EntityCyborg20(entityplayermp.field_70170_p), new EntityCell1(entityplayermp.field_70170_p), new EntityCell2(entityplayermp.field_70170_p), new EntityCell3(entityplayermp.field_70170_p), new EntityCell4(entityplayermp.field_70170_p), new EntityNamekian01(entityplayermp.field_70170_p), new EntityPuipui(entityplayermp.field_70170_p), new EntityYakon(entityplayermp.field_70170_p), new EntityDarbura(entityplayermp.field_70170_p), new EntityBuuFat(entityplayermp.field_70170_p), new EntityBuuSuper(entityplayermp.field_70170_p), new EntityDino01(entityplayermp.field_70170_p), new EntityBeerus(entityplayermp.field_70170_p), new EntityDino02(entityplayermp.field_70170_p), new EntityDino03(entityplayermp.field_70170_p), new EntityRRMecha(entityplayermp.field_70170_p), new EntitySabertooth(entityplayermp.field_70170_p), new EntityCellJr(entityplayermp.field_70170_p), new EntityBuuEvil(entityplayermp.field_70170_p), new EntityBuuFusion(entityplayermp.field_70170_p), new EntityBuuPiccolo(entityplayermp.field_70170_p), new EntityBuuUltimate(entityplayermp.field_70170_p), new EntityBuuBuffed(entityplayermp.field_70170_p), new EntityBuuKid(entityplayermp.field_70170_p), new EntityMonaka(entityplayermp.field_70170_p), new EntityBeerusMonaka(entityplayermp.field_70170_p), new EntityBeerusMonaka2(entityplayermp.field_70170_p), new EntityBeerusMonaka3(entityplayermp.field_70170_p), new EntityBerryblue(entityplayermp.field_70170_p), new EntityBotamo(entityplayermp.field_70170_p), new EntityChampa(entityplayermp.field_70170_p), new EntityFrost1(entityplayermp.field_70170_p), new EntityFrost2(entityplayermp.field_70170_p), new EntityFrost3(entityplayermp.field_70170_p), new EntityFrost4(entityplayermp.field_70170_p), new EntityFrost5(entityplayermp.field_70170_p), new EntityFrost6(entityplayermp.field_70170_p), new EntityHit(entityplayermp.field_70170_p), new EntityKrillin(entityplayermp.field_70170_p), new EntityMagetta(entityplayermp.field_70170_p), new EntityRoshi_super(entityplayermp.field_70170_p), new EntityShisami(entityplayermp.field_70170_p), new EntitySorbet(entityplayermp.field_70170_p), new EntityTagoma(entityplayermp.field_70170_p), new EntityVados(entityplayermp.field_70170_p), new EntityWhis(entityplayermp.field_70170_p), new EntityGokuBlack(entityplayermp.field_70170_p), new EntityGokuBlackRose(entityplayermp.field_70170_p), new EntityVegetaCopy(entityplayermp.field_70170_p), new EntityVegetaCopyBlue(entityplayermp.field_70170_p), new EntityCabba(entityplayermp.field_70170_p), new EntityCabbaSSJ(entityplayermp.field_70170_p), new EntityZamasu(entityplayermp.field_70170_p), new EntityZamasu_Fused(entityplayermp.field_70170_p), new EntityZamasu_Fused2(entityplayermp.field_70170_p), new EntityZamasu_Fused3(entityplayermp.field_70170_p), new EntityZeno(entityplayermp.field_70170_p), new EntityGoku(entityplayermp.field_70170_p), new EntityGoku2(entityplayermp.field_70170_p), new EntityGoku3(entityplayermp.field_70170_p), new EntityGoku4(entityplayermp.field_70170_p), new EntityVegeta(entityplayermp.field_70170_p), new EntityVegeta2(entityplayermp.field_70170_p), new EntityVegeta3(entityplayermp.field_70170_p), new EntityVegeta4(entityplayermp.field_70170_p), new EntityGohan(entityplayermp.field_70170_p), new EntityGohan2(entityplayermp.field_70170_p), new EntityGohan_orange(entityplayermp.field_70170_p), new EntityGohan_orange2(entityplayermp.field_70170_p), new EntityTrunks(entityplayermp.field_70170_p), new EntityTrunks2(entityplayermp.field_70170_p), new EntityTrunks_dbs(entityplayermp.field_70170_p), new EntityTrunks_dbs2(entityplayermp.field_70170_p), new EntityAngelAwamo(entityplayermp.field_70170_p), new EntityAngelCamparri(entityplayermp.field_70170_p), new EntityAngelCognac(entityplayermp.field_70170_p), new EntityAngelCukatail(entityplayermp.field_70170_p), new EntityAngelKorn(entityplayermp.field_70170_p), new EntityAngelKusu(entityplayermp.field_70170_p), new EntityAngelMarcarita(entityplayermp.field_70170_p), new EntityAngelMartinu(entityplayermp.field_70170_p), new EntityAngelMohito(entityplayermp.field_70170_p), new EntityAngelSour(entityplayermp.field_70170_p), new EntityGodArak(entityplayermp.field_70170_p), new EntityGodBelmod(entityplayermp.field_70170_p), new EntityGodGiin(entityplayermp.field_70170_p), new EntityGodHeles(entityplayermp.field_70170_p), new EntityGodIwan(entityplayermp.field_70170_p), new EntityGodLiquiir(entityplayermp.field_70170_p), new EntityGodMosco(entityplayermp.field_70170_p), new EntityGodQuitela(entityplayermp.field_70170_p), new EntityGodRumsshi(entityplayermp.field_70170_p), new EntityGodSidra(entityplayermp.field_70170_p), new EntityBasil(entityplayermp.field_70170_p), new EntityBergamo(entityplayermp.field_70170_p), new EntityLavender(entityplayermp.field_70170_p), new EntityBrianne(entityplayermp.field_70170_p), new EntityCaulifla(entityplayermp.field_70170_p), new EntityCaulifla2(entityplayermp.field_70170_p), new EntityDyspo(entityplayermp.field_70170_p), new EntityJiren(entityplayermp.field_70170_p), new EntityJiren2(entityplayermp.field_70170_p), new EntityKale(entityplayermp.field_70170_p), new EntityKale2(entityplayermp.field_70170_p), new EntityKefla(entityplayermp.field_70170_p), new EntityKefla2(entityplayermp.field_70170_p), new EntityToppo(entityplayermp.field_70170_p), new EntityToppo2(entityplayermp.field_70170_p), new EntityAniraza(entityplayermp.field_70170_p), new EntityBiarra(entityplayermp.field_70170_p), new EntityCocotte(entityplayermp.field_70170_p), new EntityDercori(entityplayermp.field_70170_p), new EntityGanos(entityplayermp.field_70170_p), new EntityGanos2(entityplayermp.field_70170_p), new EntityHop(entityplayermp.field_70170_p), new EntityKahseral(entityplayermp.field_70170_p), new EntityKakunsa(entityplayermp.field_70170_p), new EntityKunshi(entityplayermp.field_70170_p), new EntityMajora(entityplayermp.field_70170_p), new EntityMurichim(entityplayermp.field_70170_p), new EntityNapapa(entityplayermp.field_70170_p), new EntityNarirama(entityplayermp.field_70170_p), new EntityObni(entityplayermp.field_70170_p), new EntityPaparoni(entityplayermp.field_70170_p), new EntityRoasie(entityplayermp.field_70170_p), new EntityRylibeu(entityplayermp.field_70170_p), new EntityShosa(entityplayermp.field_70170_p), new EntitySorrel(entityplayermp.field_70170_p), new EntityBorareta(entityplayermp.field_70170_p), new EntityKoichiarator(entityplayermp.field_70170_p), new EntityKoitsukai(entityplayermp.field_70170_p), new EntityPanchia(entityplayermp.field_70170_p), new EntityKatopesla(entityplayermp.field_70170_p), new EntityKatopesla2(entityplayermp.field_70170_p), new EntityKatopesla3(entityplayermp.field_70170_p), new EntityKatopesla4(entityplayermp.field_70170_p), new EntityDBSBroly1(entityplayermp.field_70170_p), new EntityDBSBroly2(entityplayermp.field_70170_p), new EntityDBSBroly3(entityplayermp.field_70170_p), new EntityDBSBroly4(entityplayermp.field_70170_p), new EntityDBSBrolyBuff(entityplayermp.field_70170_p), new EntityDBSBrolyBuffSSJ(entityplayermp.field_70170_p), new EntityDBSBrolyLegendary(entityplayermp.field_70170_p), new EntityDBSParagus(entityplayermp.field_70170_p), new EntityDBSParagus2(entityplayermp.field_70170_p), new EntityCheelai(entityplayermp.field_70170_p), new EntityCheelai2(entityplayermp.field_70170_p), new EntityLemo(entityplayermp.field_70170_p), new EntityFortunetellerBaba(entityplayermp.field_70170_p), new EntityMummy(entityplayermp.field_70170_p), new EntityInvisibleMan(entityplayermp.field_70170_p), new EntityVampire(entityplayermp.field_70170_p), new EntityDevil(entityplayermp.field_70170_p), new EntityGrandpaGohan(entityplayermp.field_70170_p), new EntityGrandpaGohan2(entityplayermp.field_70170_p), new EntityCymbal(entityplayermp.field_70170_p), new EntityDrum(entityplayermp.field_70170_p), new EntityKingPiccolo(entityplayermp.field_70170_p), new EntityKingPiccolo2(entityplayermp.field_70170_p), new EntityPiano(entityplayermp.field_70170_p), new EntityTambourine(entityplayermp.field_70170_p), new EntityDBMasterRoshi(entityplayermp.field_70170_p), new EntityDBMasterRoshi2(entityplayermp.field_70170_p), new EntityDBMasterRoshi3(entityplayermp.field_70170_p), new EntityBearThief(entityplayermp.field_70170_p), new EntityTigerBandit(entityplayermp.field_70170_p), new EntityPuar(entityplayermp.field_70170_p), new EntityYamcha(entityplayermp.field_70170_p), new EntityYamcha2(entityplayermp.field_70170_p), new EntityYamcha3(entityplayermp.field_70170_p), new EntityYamcha4(entityplayermp.field_70170_p), new EntityYamcha5(entityplayermp.field_70170_p), new EntityMai(entityplayermp.field_70170_p), new EntityPilaf(entityplayermp.field_70170_p), new EntityShu(entityplayermp.field_70170_p), new EntityMaiMecha(entityplayermp.field_70170_p), new EntityPilafMecha(entityplayermp.field_70170_p), new EntityShuMecha(entityplayermp.field_70170_p), new EntityPilafMechaCombined(entityplayermp.field_70170_p), new EntityBacterian(entityplayermp.field_70170_p), new EntityGiran(entityplayermp.field_70170_p), new EntityJackieChun(entityplayermp.field_70170_p), new EntityJackieChun2(entityplayermp.field_70170_p), new EntityJackieChun3(entityplayermp.field_70170_p), new EntityTournamentAnnouncer(entityplayermp.field_70170_p), new EntityKingChappa(entityplayermp.field_70170_p), new EntityManWolf(entityplayermp.field_70170_p), new EntityMasterShen(entityplayermp.field_70170_p), new EntityPamput(entityplayermp.field_70170_p), new EntityTien(entityplayermp.field_70170_p), new EntityTien2(entityplayermp.field_70170_p), new EntityTien3(entityplayermp.field_70170_p), new EntityAndroid8(entityplayermp.field_70170_p), new EntityBuyon(entityplayermp.field_70170_p), new EntityColonelSilver(entityplayermp.field_70170_p), new EntityColonelViolet(entityplayermp.field_70170_p), new EntityCommanderRed(entityplayermp.field_70170_p), new EntityGeneralBlue(entityplayermp.field_70170_p), new EntityGeneralBlue2(entityplayermp.field_70170_p), new EntityGeneralWhite(entityplayermp.field_70170_p), new EntityGeneralWhite2(entityplayermp.field_70170_p), new EntityLaunch(entityplayermp.field_70170_p), new EntityLaunch2(entityplayermp.field_70170_p), new EntityMajorMetallitron(entityplayermp.field_70170_p), new EntityMajorMetallitron1(entityplayermp.field_70170_p), new EntityMajorMetallitron2(entityplayermp.field_70170_p), new EntityMajorMetallitron3(entityplayermp.field_70170_p), new EntityMercenaryTao(entityplayermp.field_70170_p), new EntityNinjaMurasaki(entityplayermp.field_70170_p), new EntityRedRibbonSoldier(entityplayermp.field_70170_p), new EntityRedRibbonSoldier2(entityplayermp.field_70170_p), new EntityRedRibbonSoldier3(entityplayermp.field_70170_p), new EntityRedRibbonSoldierB(entityplayermp.field_70170_p), new EntityRedRibbonSoldierB2(entityplayermp.field_70170_p), new EntityRedRibbonSoldierB3(entityplayermp.field_70170_p), new EntityBora(entityplayermp.field_70170_p), new EntityUpa(entityplayermp.field_70170_p), new EntityOolong(entityplayermp.field_70170_p), new EntityYajirobe(entityplayermp.field_70170_p), new EntityMercenaryTao2(entityplayermp.field_70170_p), new EntityMercenaryTao3(entityplayermp.field_70170_p), new EntityOfficerBlack(entityplayermp.field_70170_p), new EntityOfficerBlack2(entityplayermp.field_70170_p), new EntityOfficerBlack3(entityplayermp.field_70170_p), new EntityOfficeOgre(entityplayermp.field_70170_p), new EntityOfficeOgre2(entityplayermp.field_70170_p), new EntityOfficeOgre3(entityplayermp.field_70170_p), new EntityOfficeOgre4(entityplayermp.field_70170_p), new EntityOfficeOgre5(entityplayermp.field_70170_p), new EntitySpirit(entityplayermp.field_70170_p), new EntityChiaotzu(entityplayermp.field_70170_p), new EntityChiaotzu2(entityplayermp.field_70170_p), new EntityChiaotzu3(entityplayermp.field_70170_p), new EntityChiaotzu4(entityplayermp.field_70170_p), new EntityTien4(entityplayermp.field_70170_p), new EntityTien5(entityplayermp.field_70170_p), new EntityTien6(entityplayermp.field_70170_p), new EntityTien7(entityplayermp.field_70170_p)};
        return entities;
    }

    public List func_71516_a(ICommandSender par1ICommandSender, String[] arrayString) {
        int length = arrayString.length;
        switch (length) {
            case 1: {
                return ComDbcSpawn.func_71530_a((String[])arrayString, (String[])this.getEntityNameList());
            }
            case 2: {
                return ComDbcSpawn.func_71530_a((String[])arrayString, (String[])new String[]{"1000"});
            }
            case 3: {
                return ComDbcSpawn.func_71530_a((String[])arrayString, (String[])new String[]{"1000"});
            }
            case 4: {
                return ComDbcSpawn.func_71530_a((String[])arrayString, (String[])this.getListOfPlayers());
            }
        }
        return null;
    }

    protected String[] getListOfPlayers() {
        return MinecraftServer.func_71276_C().func_71213_z();
    }

    public boolean isUsernameIndex(int id) {
        return id == 3;
    }
}

