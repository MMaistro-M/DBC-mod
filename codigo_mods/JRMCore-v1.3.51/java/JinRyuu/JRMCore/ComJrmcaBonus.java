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
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.server.JGMathHelper;
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
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class ComJrmcaBonus
extends CommandBase {
    public static final String NBT1 = "nbt_";
    public static final String NBT2 = "NBT_";
    private final String name = "jrmcabonus";
    private final byte MODE_ADD = 0;
    private final byte MODE_ADD_TO = 1;
    private final byte MODE_SET = (byte)2;
    private final byte MODE_GET = (byte)3;
    private final byte MODE_REMOVE = (byte)4;
    private final byte MODE_CLEAR = (byte)5;
    private final byte MODE_BAD_MODE = (byte)-1;
    private final byte MODE_STR = 0;
    private final byte MODE_DEX = 1;
    private final byte MODE_CON = (byte)2;
    private final byte MODE_WILL = (byte)3;
    private final byte MODE_MIND = (byte)4;
    private final byte MODE_SPI = (byte)5;
    private final String[] ATTRIBUTES_LONG = new String[]{"strength", "dexterity", "constitution", "willpower", "mind", "spirit"};
    public static final String[] ATTRIBUTES_SHORT = new String[]{"str", "dex", "con", "wil", "mnd", "spi"};
    private final String[] MODES = new String[]{"add", "addto", "set", "get", "remove", "clear"};
    private final String[] OPERATIONS = new String[]{"+1", "-1", "*1", "/1", "%1", "nbt_nbtValueName"};
    private final String[] END = new String[]{"true", "false"};

    public String func_71517_b() {
        return "jrmcabonus";
    }

    public String func_71518_a(ICommandSender icommandsender) {
        return "/jrmcabonus (Add/AddTo/Set/Get/Remove/clear) (Attribute) (BonusName or ID) (Math Operation - Amount (USE IF: mode is ADD or SET!)) (Add To Top/Start(false) or Bottom/End(true) of the List (USE IF: mode is ADD)) [playerName]. Attribute can be Strength, Dexterity, Constitution, Willpower, Mind, Spirit. Example: '/jrmcabonus add Strength saiyanRage *2.0 false @p' OR '/jrmcabonus get Strength'";
    }

    public int func_82362_a() {
        return 2;
    }

    public List func_71516_a(ICommandSender commandSender, String[] stringArray) {
        String modeSting = stringArray[0].toLowerCase();
        int mode = modeSting.equals("add") ? 0 : (modeSting.equals("addto") ? 1 : (modeSting.equals("set") ? 2 : (modeSting.equals("get") ? 3 : (modeSting.equals("clear") ? 5 : (modeSting.equals("remove") || modeSting.equals("delete") ? 4 : -1)))));
        int length = stringArray.length;
        switch (length) {
            case 1: {
                return ComJrmcaBonus.func_71530_a((String[])stringArray, (String[])this.MODES);
            }
            case 2: {
                return ComJrmcaBonus.func_71530_a((String[])stringArray, (String[])this.ATTRIBUTES_LONG);
            }
            case 3: {
                String[] stringArray2;
                if (mode == 3 || mode == 5) {
                    stringArray2 = this.getListOfPlayers();
                } else {
                    String[] stringArray3 = new String[1];
                    stringArray2 = stringArray3;
                    stringArray3[0] = "nameid";
                }
                return ComJrmcaBonus.func_71530_a((String[])stringArray, (String[])stringArray2);
            }
            case 4: {
                return mode == 3 || mode == 5 ? null : ComJrmcaBonus.func_71530_a((String[])stringArray, (String[])this.OPERATIONS);
            }
            case 5: {
                return mode == 3 || mode == 5 ? null : ComJrmcaBonus.func_71530_a((String[])stringArray, (String[])(mode == 0 ? this.END : this.getListOfPlayers()));
            }
            case 6: {
                return mode == 0 ? ComJrmcaBonus.func_71530_a((String[])stringArray, (String[])this.getListOfPlayers()) : null;
            }
        }
        return null;
    }

    protected String[] getListOfPlayers() {
        return MinecraftServer.func_71276_C().func_71213_z();
    }

    private NBTTagCompound nbt(EntityPlayer p, String s) {
        return JRMCoreH.nbt((Entity)p, s);
    }

    private void notifyAdmins(ICommandSender commandSender, String string, Object[] objects) {
        ComJrmcaBonus.func_152373_a((ICommandSender)commandSender, (ICommand)this, (String)string, (Object[])objects);
    }

    public void func_71515_b(ICommandSender commandSender, String[] stringArray) {
        int id;
        double value;
        int i;
        if (stringArray.length <= 0) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[0]);
        }
        String modeSting = stringArray[0].toLowerCase();
        String attributeString = stringArray[1].toLowerCase();
        String bonusNameIDString = "";
        String bonusAmountString = "";
        String endOfTheList = "";
        int mode = modeSting.equals("add") ? 0 : (modeSting.equals("addto") ? 1 : (modeSting.equals("set") ? 2 : (modeSting.equals("get") ? 3 : (modeSting.equals("clear") ? 5 : (modeSting.equals("remove") || modeSting.equals("delete") ? 4 : -1)))));
        int attribute = 0;
        for (int i2 = 0; i2 < this.ATTRIBUTES_LONG.length; i2 = (int)((byte)(i2 + 1))) {
            if (!attributeString.equals(this.ATTRIBUTES_LONG[i2]) && !attributeString.equals(ATTRIBUTES_SHORT[i2])) continue;
            attribute = i2;
        }
        if (mode == 0 || mode == 2 || mode == 4 || mode == 1) {
            bonusNameIDString = stringArray[2];
            if (mode == 0 || mode == 2 || mode == 1) {
                bonusAmountString = stringArray[3];
                if (mode == 0) {
                    endOfTheList = stringArray[4].toLowerCase();
                }
            }
        }
        int playerNameID = mode == 3 || mode == 5 ? 2 : (mode == 4 ? 3 : (mode == 0 ? 5 : 4));
        EntityPlayerMP entityplayermp = stringArray.length > playerNameID ? ComJrmcaBonus.func_82359_c((ICommandSender)commandSender, (String)stringArray[playerNameID]) : ComJrmcaBonus.func_71521_c((ICommandSender)commandSender);
        String entitycommansender = "Console";
        EntityPlayerMP commansender = null;
        try {
            commansender = ComJrmcaBonus.func_71521_c((ICommandSender)commandSender);
            entitycommansender = commansender.func_70005_c_();
        }
        catch (Exception exception) {
            // empty catch block
        }
        boolean notify = entitycommansender.equals("Console") ? JRMCoreConfig.CommandNotifyAdminJRMCABonusConsole : (entitycommansender.equals(entityplayermp.func_70005_c_()) ? JRMCoreConfig.CommandNotifyAdminJRMCABonusSelf : JRMCoreConfig.CommandNotifyAdminJRMCABonusOthers);
        boolean run = false;
        NBTTagCompound nbt = this.nbt((EntityPlayer)entityplayermp, "pres");
        String startString = nbt.func_74779_i("jrmcAttrBonus" + ATTRIBUTES_SHORT[attribute]);
        String[] bonus = startString.split("\\|");
        String[][] bonusValues = new String[bonus.length][2];
        if (bonus.length > 0 && bonus[0].length() > 0) {
            for (i = 0; i < bonus.length; ++i) {
                String[] bonusValue = bonus[i].split("\\;");
                bonusValues[i][0] = bonusValue[0];
                bonusValues[i][1] = bonusValue[1];
            }
        }
        if (mode == 0) {
            boolean doesItContainElement = false;
            for (int i3 = 0; i3 < bonus.length; ++i3) {
                String[] bonusValue = bonus[i3].split("\\;");
                bonusValues[i3][0] = bonusValue[0];
                if (!bonusValues[i3][0].equals(bonusNameIDString)) continue;
                doesItContainElement = true;
                break;
            }
            if (!doesItContainElement) {
                String data;
                boolean addToEnd = Boolean.parseBoolean(endOfTheList);
                if (addToEnd) {
                    data = bonusNameIDString + ";" + bonusAmountString;
                    startString = startString.length() == 0 ? data : startString + "|" + data;
                } else {
                    data = bonusNameIDString + ";" + bonusAmountString;
                    startString = startString.length() == 0 ? data : data + "|" + startString;
                }
                bonus = startString.split("\\|");
                bonusValues = new String[bonus.length][2];
                for (int i4 = 0; i4 < bonus.length; ++i4) {
                    String[] bonusValue = bonus[i4].split("\\;");
                    bonusValues[i4][0] = bonusValue[0];
                    bonusValues[i4][1] = bonusValue[1];
                }
                run = true;
            } else if (notify) {
                this.notifyAdmins(commandSender, "Bonus Attribute Failed to be added for " + entityplayermp.func_70005_c_() + ", Reason: Bonus list already contains a value with this ID: " + bonusNameIDString, new Object[0]);
            }
        } else if (mode == 1) {
            boolean number;
            boolean nbtFail = false;
            int id2 = -1;
            try {
                id2 = Integer.parseInt(bonusNameIDString);
                number = true;
            }
            catch (Exception e) {
                number = false;
            }
            for (int i5 = 0; i5 < bonus.length; ++i5) {
                String data;
                String[] bonusValue = bonus[i5].split("\\;");
                bonusValues[i5][0] = bonusValue[0];
                if ((!number || i5 != id2) && (number || !bonusValues[i5][0].equals(bonusNameIDString))) continue;
                if (bonusValues[i5][1].contains(NBT1) || bonusValues[i5][1].contains(NBT2) || bonusAmountString.contains(NBT1) || bonusAmountString.contains(NBT2)) {
                    nbtFail = true;
                    break;
                }
                value = Double.parseDouble(bonusValues[i5][1].substring(1));
                double value2 = Double.parseDouble(bonusAmountString.substring(1));
                double resultValue = JGMathHelper.StringMethod(bonusAmountString.substring(0, 1), value, value2);
                String result = bonusValues[i5][1].substring(0, 1) + resultValue;
                bonus[i5] = data = bonusValues[i5][0] + ";" + result;
                bonusValue = bonus[i5].split("\\;");
                bonusValues[i5][0] = bonusValue[0];
                bonusValues[i5][1] = bonusValue[1];
                run = true;
                break;
            }
            if (!run && notify) {
                if (nbtFail) {
                    this.notifyAdmins(commandSender, "Bonus Attribute Failed to be added to for " + entityplayermp.func_70005_c_() + ", Reason: One of the Bonus values is an NBT value name.", new Object[0]);
                } else {
                    this.notifyAdmins(commandSender, "Bonus Attribute Failed to be added to for " + entityplayermp.func_70005_c_() + ", Reason: Bonus list didn't contain a value with this ID: " + bonusNameIDString, new Object[0]);
                }
            }
        } else if (mode == 2) {
            boolean number;
            id = -1;
            try {
                id = Integer.parseInt(bonusNameIDString);
                number = true;
            }
            catch (Exception e) {
                number = false;
            }
            for (int i6 = 0; i6 < bonus.length; ++i6) {
                String[] bonusValue = bonus[i6].split("\\;");
                bonusValues[i6][0] = bonusValue[0];
                if ((!number || i6 != id) && (number || !bonusValues[i6][0].equals(bonusNameIDString))) continue;
                String data = bonusValues[i6][0] + ";" + bonusAmountString;
                bonus[i6] = "";
                run = true;
                bonus[i6] = data;
                bonusValue = bonus[i6].split("\\;");
                bonusValues[i6][0] = bonusValue[0];
                bonusValues[i6][1] = bonusValue[1];
                break;
            }
            if (!run && notify) {
                this.notifyAdmins(commandSender, "Bonus Attribute Failed to be added to for " + entityplayermp.func_70005_c_() + ", Reason: Bonus list didn't contain a value with this " + (number ? "ID" : "Name") + ": " + bonusNameIDString, new Object[0]);
            }
        } else if (mode == 3) {
            ChatStyle colorG = new ChatStyle().func_150238_a(EnumChatFormatting.GOLD);
            ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
            if (commansender != null) {
                commansender.func_145747_a(new ChatComponentText(entityplayermp.getDisplayName() + " " + this.ATTRIBUTES_LONG[attribute] + " Bonus Attributes:").func_150255_a(colorG));
            } else if (entitycommansender.equals("Console")) {
                JRMCoreH.log(entityplayermp.getDisplayName() + " " + this.ATTRIBUTES_LONG[attribute] + " Bonus Attributes:");
            }
            if (bonus.length > 0 && bonus[0].length() > 0) {
                for (int i7 = 0; i7 < bonus.length; ++i7) {
                    String[] bonusValue = bonus[i7].split("\\;");
                    bonusValues[i7][0] = bonusValue[0];
                    bonusValues[i7][1] = bonusValue[1];
                    if (bonusValues[i7][1].contains(NBT1) || bonusValues[i7][1].contains(NBT2)) {
                        String noNBTText = bonusValues[i7][1].replace(NBT1, "").replace(NBT2, "");
                        value = nbt.func_74769_h(noNBTText.substring(1));
                        bonusValues[i7][1] = noNBTText.substring(0, 1) + "(" + value + ")";
                    }
                    if (commansender != null) {
                        commansender.func_145747_a(new ChatComponentText("ID " + bonusValues[i7][0] + " | VALUE: " + bonusValues[i7][1]).func_150255_a(color));
                        continue;
                    }
                    if (!entitycommansender.equals("Console")) continue;
                    JRMCoreH.log("ID " + bonusValues[i7][0] + " | VALUE: " + bonusValues[i7][1]);
                }
            } else if (commansender != null) {
                commansender.func_145747_a(new ChatComponentText("EMPTY").func_150255_a(color));
            } else if (entitycommansender.equals("Console")) {
                JRMCoreH.log("EMPTY");
            }
        } else if (mode == 4) {
            boolean number;
            id = -1;
            try {
                id = Integer.parseInt(bonusNameIDString);
                number = true;
            }
            catch (Exception e) {
                number = false;
            }
            for (int i8 = 0; i8 < bonus.length; ++i8) {
                String[] bonusValue = bonus[i8].split("\\;");
                bonusValues[i8][0] = bonusValue[0];
                if ((!number || i8 != id) && (number || !bonusValues[i8][0].equals(bonusNameIDString))) continue;
                bonus[i8] = "";
                run = true;
                break;
            }
            if (!run && notify) {
                this.notifyAdmins(commandSender, "Bonus Attribute Failed to be added to for " + entityplayermp.func_70005_c_() + ", Reason: Bonus list didn't contain a value with this " + (number ? "ID" : "Name") + ": " + bonusNameIDString, new Object[0]);
            }
        } else if (mode == 5) {
            for (i = 0; i < bonus.length; ++i) {
                bonus[i] = "";
            }
            run = true;
        }
        if (run) {
            startString = "";
            for (int i9 = 0; i9 < bonus.length; ++i9) {
                if (bonus[i9] == null || bonus[i9].length() <= 0) continue;
                startString = startString + bonus[i9] + (bonus.length - 1 == i9 ? "" : "|");
            }
            nbt.func_74778_a("jrmcAttrBonus" + ATTRIBUTES_SHORT[attribute], startString);
        }
        if (notify && run) {
            this.notifyAdmins(commandSender, "Bonus Attribute " + this.MODES[mode].toUpperCase() + " finished for " + entityplayermp.func_70005_c_(), new Object[0]);
        }
        if (!JRMCoreConfig.JRMCABonusOn) {
            if (commansender != null) {
                ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.RED);
                commansender.func_145747_a(new ChatComponentText("JRMCABonus Attributes are DISABLED in the configs!").func_150255_a(color));
            } else if (entitycommansender.equals("Console")) {
                JRMCoreH.log("JRMCABonus Attributes are DISABLED in the configs!");
            }
        }
    }
}

