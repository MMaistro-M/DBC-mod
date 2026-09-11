/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.Teleporter
 */
package JinRyuu.DragonBC.common.Gui;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.DBCH;
import JinRyuu.DragonBC.common.Worlds.WorldTeleporterDBCTelep;
import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHDBC;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.Teleporter;

public class ComDbc
extends CommandBase {
    public String func_71517_b() {
        return "dbc";
    }

    public String func_71518_a(ICommandSender icommandsender) {
        return "Use '/dbc locations' or '/dbc loc' to receive the locations in DBC and Use '/dbc accept' to accept offers like revive with teleport.";
    }

    public int func_82362_a() {
        return 0;
    }

    public boolean func_71519_b(ICommandSender par1ICommandSender) {
        return true;
    }

    private NBTTagCompound nbt(EntityPlayer p, String s) {
        return JRMCoreH.nbt((Entity)p, s);
    }

    public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        if (par2ArrayOfStr.length <= 0) {
            throw new WrongUsageException("Use '/dbc locations' or '/dbc loc' to receive the locations in DBC and Use '/dbc accept' to accept offers like revive with teleport.", new Object[0]);
        }
        EntityPlayerMP entityplayermp = ComDbc.func_71521_c((ICommandSender)par1ICommandSender);
        String s = par2ArrayOfStr[0];
        boolean loc = s.toLowerCase().contains("locations") || s.toLowerCase().contains("loc");
        boolean accept = s.toLowerCase().contains("accept");
        boolean heal = false;
        if (loc) {
            ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
            if (entityplayermp.field_71093_bK == 0) {
                String[] kamh = DBCH.genKH.split(";");
                String[] clar = DBCH.genCA.split(";");
                String[] gkhs = DBCH.genGH.split(";");
                String[] bs2 = DBCH.genBS.split(";");
                entityplayermp.func_145747_a(new ChatComponentText(StatCollector.func_74838_a((String)"dbc.com.loc.coords")).func_150255_a(color));
                entityplayermp.func_145747_a(new ChatComponentText(StatCollector.func_74838_a((String)"dbc.com.loc.kami")).func_150255_a(color));
                if (kamh.length > 2) {
                    entityplayermp.func_145747_a(new ChatComponentText(StatCollector.func_74838_a((String)"dbc.com.loc.kame") + ": " + (JRMCoreH.parseInt(kamh[0]) + DBCH.genKHnpc1[0]) + " " + (JRMCoreH.parseInt(kamh[1]) + DBCH.genKHnpc1[1]) + " " + (JRMCoreH.parseInt(kamh[2]) + DBCH.genKHnpc1[2])).func_150255_a(color));
                } else {
                    entityplayermp.func_145747_a(new ChatComponentText(StatCollector.func_74838_a((String)"dbc.com.loc.kamenotexp")).func_150255_a(color));
                }
                if (clar.length > 2) {
                    entityplayermp.func_145747_a(new ChatComponentText(StatCollector.func_74838_a((String)"dbc.com.loc.cell") + ": " + (JRMCoreH.parseInt(clar[0]) + DBCH.genCAnpc1[0]) + " " + (JRMCoreH.parseInt(clar[1]) + DBCH.genCAnpc1[1]) + " " + (JRMCoreH.parseInt(clar[2]) + DBCH.genCAnpc1[2])).func_150255_a(color));
                } else {
                    entityplayermp.func_145747_a(new ChatComponentText(StatCollector.func_74838_a((String)"dbc.com.loc.cellnotexp")).func_150255_a(color));
                }
                if (gkhs.length > 2) {
                    entityplayermp.func_145747_a(new ChatComponentText(StatCollector.func_74838_a((String)"dbc.com.loc.goku") + ": " + (JRMCoreH.parseInt(gkhs[0]) + DBCH.genGHnpc1[0]) + " " + (JRMCoreH.parseInt(gkhs[1]) + DBCH.genGHnpc1[1]) + " " + (JRMCoreH.parseInt(gkhs[2]) + DBCH.genGHnpc1[2])).func_150255_a(color));
                } else {
                    entityplayermp.func_145747_a(new ChatComponentText(StatCollector.func_74838_a((String)"dbc.com.loc.gokunotexp")).func_150255_a(color));
                }
                if (bs2.length > 2) {
                    entityplayermp.func_145747_a(new ChatComponentText(StatCollector.func_74838_a((String)"dbc.com.loc.babidi") + ": " + (JRMCoreH.parseInt(bs2[0]) + DBCH.genBSnpc1[0]) + " " + (JRMCoreH.parseInt(bs2[1]) + DBCH.genBSnpc1[1]) + " " + (JRMCoreH.parseInt(bs2[2]) + DBCH.genBSnpc1[2])).func_150255_a(color));
                } else {
                    entityplayermp.func_145747_a(new ChatComponentText(StatCollector.func_74838_a((String)"dbc.com.loc.babidinotexp")).func_150255_a(color));
                }
            }
            if (entityplayermp.field_71093_bK == DBCConfig.planetNamek) {
                String fzsp = DBCH.genFS.replace(";", " ");
                String guru = DBCH.genGuru.replace(";", " ");
                entityplayermp.func_145747_a(new ChatComponentText(StatCollector.func_74838_a((String)"dbc.com.loc.coords")).func_150255_a(color));
                if (fzsp.length() > 3) {
                    entityplayermp.func_145747_a(new ChatComponentText(StatCollector.func_74838_a((String)"dbc.com.loc.freeza") + ": " + fzsp).func_150255_a(color));
                } else {
                    entityplayermp.func_145747_a(new ChatComponentText(StatCollector.func_74838_a((String)"dbc.com.loc.freezanotexp")).func_150255_a(color));
                }
                entityplayermp.func_145747_a(new ChatComponentText(StatCollector.func_74838_a((String)"dbc.com.loc.coords")).func_150255_a(color));
                if (guru.length() > 3) {
                    entityplayermp.func_145747_a(new ChatComponentText(StatCollector.func_74838_a((String)"dbc.com.loc.guruhouse") + ": " + guru).func_150255_a(color));
                } else {
                    entityplayermp.func_145747_a(new ChatComponentText(StatCollector.func_74838_a((String)"dbc.com.loc.guruhousenotexp")).func_150255_a(color));
                }
            }
        }
        if (accept) {
            String[] sa;
            MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
            EntityPlayerMP p = entityplayermp;
            if (p.field_71093_bK == DBCConfig.otherWorld && p != null && p.field_70154_o == null && p.field_70153_n == null && (sa = JRMCoreH.getString((EntityPlayer)p, "jrmcRevtpInit").split(";")).length > 3) {
                EntityPlayerMP target;
                String wisherNam = sa[0];
                int reviveDim = Integer.parseInt(sa[1]);
                int x = Integer.parseInt(sa[2]);
                int y = Integer.parseInt(sa[3]);
                int z = Integer.parseInt(sa[4]);
                JRMCoreH.setByte(0, (EntityPlayer)p, "jrmcAlv");
                server.func_71203_ab().transferPlayerToDimension(p, reviveDim, (Teleporter)new WorldTeleporterDBCTelep(server.func_71218_a(reviveDim)));
                p.func_71023_q(1);
                double[] d = new double[]{x, y, z};
                p.field_71135_a.func_147364_a(d[0], d[1], d[2], 0.0f, 0.0f);
                mod_DragonBC.logger.info(p.func_70005_c_() + " revived by " + wisherNam + "!");
                if (JRMCoreHDBC.DBCgetConfigDeadInv() && p.field_70170_p.func_82736_K().func_82766_b("keepInventory") && !p.field_71075_bZ.field_75098_d && JRMCoreH.getByte((EntityPlayer)p, "jrmcAlv") == 1) {
                    JRMCoreH.nbt((EntityPlayer)p).func_74782_a("InventoryDead", (NBTBase)p.field_71071_by.func_70442_a(new NBTTagList()));
                    p.field_71071_by.func_70443_b(JRMCoreH.nbt((EntityPlayer)p).func_150295_c("InventoryLiving", 10));
                    p.getEntityData().func_74782_a("Inventory", (NBTBase)p.field_71071_by.func_70442_a(new NBTTagList()));
                }
                if ((target = JRMCoreH.getPlayerForUsername(server, wisherNam)) != null) {
                    String t = JRMCoreH.trlai("dbc", "reviveaccepted");
                    ChatStyle styl = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
                    target.func_145747_a(new ChatComponentTranslation(t, new Object[]{p.func_70005_c_(), JRMCoreH.trl("dbc", DBCH.plntNms.get(reviveDim)), x + ", " + y + ", " + z}).func_150255_a(styl));
                }
            }
            JRMCoreH.setString("e", (EntityPlayer)p, "jrmcRevtpInit");
        }
    }

    private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
        ComDbc.func_152373_a((ICommandSender)par1iCommandSender, (ICommand)this, (String)string, (Object[])objects);
    }
}

