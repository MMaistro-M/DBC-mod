/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 */
package JinRyuu.DragonBC.common.Gui;

import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class ComSkill
extends CommandBase {
    public String func_71517_b() {
        return "dbcskill";
    }

    public int func_82362_a() {
        return 2;
    }

    public void func_71515_b(ICommandSender par1ICommandSender, String[] stringArray) {
        if (stringArray.length < 2) {
            throw new WrongUsageException(this.func_71518_a(par1ICommandSender), new Object[0]);
        }
        String s = stringArray[0];
        boolean give = s.toLowerCase().contentEquals("give");
        boolean givelvl = s.toLowerCase().contentEquals("givelvl");
        boolean take = s.toLowerCase().contentEquals("take");
        boolean all = stringArray[1].toLowerCase().contentEquals("all");
        int id = -1;
        if (!all) {
            for (int i = 0; i < JRMCoreH.DBCSkillNames.length; ++i) {
                if (!stringArray[1].toLowerCase().equals(JRMCoreH.DBCSkillNames[i].toLowerCase())) continue;
                id = i;
                break;
            }
        }
        EntityPlayerMP entityplayermp = stringArray.length > (givelvl ? 3 : 2) ? ComSkill.func_82359_c((ICommandSender)par1ICommandSender, (String)stringArray[givelvl ? 3 : 2]) : ComSkill.func_71521_c((ICommandSender)par1ICommandSender);
        String entitycommansender = "Console";
        try {
            EntityPlayerMP commansender = ComSkill.func_71521_c((ICommandSender)par1ICommandSender);
            entitycommansender = commansender.func_70005_c_();
        }
        catch (Exception commansender) {
            // empty catch block
        }
        boolean n = entitycommansender.equals("Console") ? JRMCoreConfig.ComANAC : (entitycommansender.equals(entityplayermp.func_70005_c_()) ? JRMCoreConfig.ComANAS : JRMCoreConfig.ComANAO);
        NBTTagCompound nbt = JRMCoreH.nbt((Entity)entityplayermp, "pres");
        EntityPlayerMP p = entityplayermp;
        byte pwrtyp = JRMCoreH.getByte((EntityPlayer)p, "jrmcPwrtyp");
        byte rc = JRMCoreH.getByte((EntityPlayer)p, "jrmcRace");
        int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts((EntityPlayer)entityplayermp);
        String[] PlyrSkills = nbt.func_74779_i("jrmcSSlts").split(",");
        int PlyrSkills_Amount = PlyrSkills.length;
        String[] skls = JRMCoreH.DBCSkillsIDs;
        String[] sklsNms = JRMCoreH.DBCSkillNames;
        if (id >= 0 || all) {
            if (give || givelvl) {
                int i;
                boolean stop = false;
                if (all && givelvl) {
                    for (i = 0; i < JRMCoreH.DBCSkillNames.length; i = (int)((byte)(i + 1))) {
                        for (int j = 0; j < PlyrSkills_Amount; ++j) {
                            int re;
                            if (!PlyrSkills[j].contains(skls[i])) continue;
                            boolean po = stringArray[2].contains("+");
                            boolean ne = stringArray[2].contains("-");
                            int lv = Integer.parseInt(stringArray[2]);
                            int slv = JRMCoreH.SklLvl(j, 1, PlyrSkills) - 1;
                            int n2 = re = po || ne ? slv + lv : lv - 1;
                            re = re > 9 ? 9 : (re < 0 ? 0 : re);
                            String sn2 = JRMCoreH.cleanUpCommas(nbt.func_74779_i("jrmcSSlts").replaceAll(PlyrSkills[j], skls[i] + re));
                            nbt.func_74778_a("jrmcSSlts", sn2);
                            String t = JRMCoreH.trlai("jrmc", "skillupped");
                            ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
                            entityplayermp.func_145747_a(new ChatComponentTranslation(t, new Object[]{sklsNms[i], re + 1}).func_150255_a(color));
                            if (!n) continue;
                            this.notifyAdmins(par1ICommandSender, "%s has upgraded skill %s to lvl %s", new Object[]{entityplayermp.func_70005_c_(), sklsNms[i], re + 1});
                        }
                    }
                } else if (id >= 0) {
                    for (i = 0; i < PlyrSkills_Amount; i = (int)((byte)(i + 1))) {
                        int re;
                        if (!PlyrSkills[i].contains(skls[id]) || !(stop = true) || !givelvl) continue;
                        boolean po = stringArray[2].contains("+");
                        boolean ne = stringArray[2].contains("-");
                        int lv = Integer.parseInt(stringArray[2]);
                        int slv = JRMCoreH.SklLvl(id, 1, PlyrSkills) - 1;
                        int n3 = re = po || ne ? slv + lv : lv - 1;
                        re = re > 9 ? 9 : (re < 0 ? 0 : re);
                        String sn2 = JRMCoreH.cleanUpCommas(nbt.func_74779_i("jrmcSSlts").replaceAll(PlyrSkills[i], skls[id] + re));
                        nbt.func_74778_a("jrmcSSlts", sn2);
                        String t = JRMCoreH.trlai("jrmc", "skillupped");
                        ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
                        entityplayermp.func_145747_a(new ChatComponentTranslation(t, new Object[]{sklsNms[id], re + 1}).func_150255_a(color));
                        if (n) {
                            this.notifyAdmins(par1ICommandSender, "%s has upgraded skill %s to lvl %s", new Object[]{entityplayermp.func_70005_c_(), sklsNms[id], re + 1});
                        }
                        return;
                    }
                }
                if (stop) {
                    String t = JRMCoreH.trlai("jrmc", "alreadyhaveskill");
                    ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
                    entityplayermp.func_145747_a(new ChatComponentTranslation(t, new Object[0]).func_150255_a(color));
                    if (n) {
                        this.notifyAdmins(par1ICommandSender, "%s already has skill %s", new Object[]{entityplayermp.func_70005_c_(), sklsNms[id]});
                    }
                } else {
                    PlyrSkills = nbt.func_74779_i("jrmcSSlts").split(",");
                    PlyrSkills_Amount = PlyrSkills.length;
                    if (all) {
                        for (i = 0; i < JRMCoreH.DBCSkillNames.length; i = (int)((byte)(i + 1))) {
                            boolean have = false;
                            for (int j = 0; j < PlyrSkills_Amount; ++j) {
                                if (!PlyrSkills[j].contains(skls[i])) continue;
                                have = true;
                            }
                            if (have) continue;
                            int re = 0;
                            if (givelvl) {
                                boolean po = stringArray[2].contains("+");
                                boolean ne = stringArray[2].contains("-");
                                int lv = Integer.parseInt(stringArray[2]);
                                int slv = 0;
                                int n4 = re = po || ne ? slv + lv : lv - 1;
                                re = re > 9 ? 9 : (re < 0 ? 0 : re);
                            }
                            nbt.func_74778_a("jrmcSSlts", JRMCoreH.cleanUpCommas(nbt.func_74779_i("jrmcSSlts") + "," + skls[i] + re));
                            String t = JRMCoreH.trlai("jrmc", "skilladded");
                            ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
                            entityplayermp.func_145747_a(new ChatComponentTranslation(t, new Object[]{sklsNms[i]}).func_150255_a(color));
                            if (!n) continue;
                            this.notifyAdmins(par1ICommandSender, "%s has received skill %s to lvl %s", new Object[]{entityplayermp.func_70005_c_(), sklsNms[i], re + 1});
                        }
                    } else {
                        int re = 0;
                        if (givelvl) {
                            boolean po = stringArray[2].contains("+");
                            boolean ne = stringArray[2].contains("-");
                            int lv = Integer.parseInt(stringArray[2]);
                            int slv = 0;
                            int n5 = re = po || ne ? slv + lv : lv - 1;
                            re = re > 9 ? 9 : (re < 0 ? 0 : re);
                        }
                        nbt.func_74778_a("jrmcSSlts", JRMCoreH.cleanUpCommas(nbt.func_74779_i("jrmcSSlts") + "," + skls[id] + re));
                        String t = JRMCoreH.trlai("jrmc", "skilladded");
                        ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
                        entityplayermp.func_145747_a(new ChatComponentTranslation(t, new Object[]{sklsNms[id]}).func_150255_a(color));
                        if (n) {
                            this.notifyAdmins(par1ICommandSender, "%s has received skill %s to lvl %s", new Object[]{entityplayermp.func_70005_c_(), sklsNms[id], re + 1});
                        }
                    }
                }
            }
            if (take) {
                if (id >= 0) {
                    for (int i = 0; i < PlyrSkills_Amount; i = (int)((byte)(i + 1))) {
                        String sn2;
                        if (!PlyrSkills[i].contains(skls[id])) continue;
                        if (id == 8) {
                            JRMCoreH.PlyrSettingsRem(nbt, 0);
                        }
                        if (id == 16) {
                            JRMCoreH.PlyrSettingsRem(nbt, 11);
                        }
                        if (id == 18) {
                            JRMCoreH.PlyrSettingsRem(nbt, 16);
                        }
                        nbt.func_74778_a("jrmcSSlts", (sn2 = JRMCoreH.cleanUpCommas(nbt.func_74779_i("jrmcSSlts").replaceAll(PlyrSkills[i], ""))).length() < 3 ? "," : sn2);
                        String skl = sklsNms[id];
                        String t = "Skill " + skl + " removed";
                        ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
                        entityplayermp.func_145747_a(new ChatComponentText(t).func_150255_a(color));
                        if (!n) continue;
                        this.notifyAdmins(par1ICommandSender, "%s's skill %s has been removed!", new Object[]{entityplayermp.func_70005_c_(), skl});
                    }
                } else {
                    nbt.func_74778_a("jrmcSSlts", ",");
                    String t = JRMCoreH.trlai("jrmc", "skillallremoved");
                    ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
                    entityplayermp.func_145747_a(new ChatComponentTranslation(t, new Object[0]).func_150255_a(color));
                    if (n) {
                        this.notifyAdmins(par1ICommandSender, "%s's all skill has been removed!", new Object[]{entityplayermp.func_70005_c_()});
                    }
                }
            }
        } else {
            String t = JRMCoreH.trlai("jrmc", "skillnameinvalid");
            ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
            entityplayermp.func_145747_a(new ChatComponentTranslation(t, new Object[0]).func_150255_a(color));
        }
    }

    private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
        ComSkill.func_152373_a((ICommandSender)par1iCommandSender, (ICommand)this, (String)string, (Object[])objects);
    }

    public String getSkillList() {
        String list = "";
        for (int i = 0; i < JRMCoreH.DBCSkillNames.length; ++i) {
            list = list + ", " + JRMCoreH.DBCSkillNames[i];
        }
        return list;
    }

    public String func_71518_a(ICommandSender icommandsender) {
        String list = this.getSkillList();
        return "/dbcskill (give or take) (skillName) [playerName] OR /dbcskill take all [playerName] OR /dbcskill givelvl (skillName) (lvl 1-10) [playerName] --> skillNames can be: " + list;
    }

    public List func_71516_a(ICommandSender par1ICommandSender, String[] arrayString) {
        int length = arrayString.length;
        switch (length) {
            case 1: {
                return ComSkill.func_71530_a((String[])arrayString, (String[])new String[]{"give", "take", "givelvl"});
            }
            case 2: {
                return ComSkill.func_71530_a((String[])arrayString, (String[])this.getSkillList().split(", "));
            }
            case 3: {
                String[] stringArray;
                if (arrayString[0].equals("givelvl")) {
                    String[] stringArray2 = new String[1];
                    stringArray = stringArray2;
                    stringArray2[0] = "1";
                } else {
                    stringArray = this.getListOfPlayers();
                }
                return ComSkill.func_71530_a((String[])arrayString, (String[])stringArray);
            }
            case 4: {
                return arrayString[0].equals("givelvl") ? ComSkill.func_71530_a((String[])arrayString, (String[])this.getListOfPlayers()) : null;
            }
        }
        return null;
    }

    protected String[] getListOfPlayers() {
        return MinecraftServer.func_71276_C().func_71213_z();
    }

    public boolean isUsernameIndex(int id) {
        return id == 0;
    }
}

