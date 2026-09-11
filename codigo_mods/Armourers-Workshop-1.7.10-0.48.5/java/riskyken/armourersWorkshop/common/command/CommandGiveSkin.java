/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.command;

import java.awt.Color;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.common.command.ModCommand;
import riskyken.armourersWorkshop.common.library.LibraryFile;
import riskyken.armourersWorkshop.common.skin.cache.CommonSkinCache;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinDye;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.SkinIOUtils;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class CommandGiveSkin
extends ModCommand {
    public String func_71517_b() {
        return "giveSkin";
    }

    public List func_71516_a(ICommandSender commandSender, String[] currentCommand) {
        if (currentCommand.length == 2) {
            return CommandGiveSkin.func_71530_a((String[])currentCommand, (String[])this.getPlayers());
        }
        return null;
    }

    public void func_71515_b(ICommandSender commandSender, String[] currentCommand) {
        if (currentCommand.length < 3) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{currentCommand});
        }
        String playerName = currentCommand[1];
        EntityPlayerMP player = CommandGiveSkin.func_82359_c((ICommandSender)commandSender, (String)playerName);
        if (player == null) {
            return;
        }
        String skinName = currentCommand[2];
        if (!skinName.substring(0, 1).equals("\"")) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{skinName});
        }
        int usedCommands = 2;
        if (!skinName.substring(skinName.length() - 1, skinName.length()).equals("\"")) {
            for (int i = 3; i < currentCommand.length; ++i) {
                if (!(skinName = skinName + " " + currentCommand[i]).substring(skinName.length() - 1, skinName.length()).equals("\"")) continue;
                usedCommands = i;
                break;
            }
        }
        ModLogger.log("usedCommands used: " + usedCommands);
        ModLogger.log("total commands used: " + currentCommand.length);
        if (!skinName.substring(skinName.length() - 1, skinName.length()).equals("\"")) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{skinName});
        }
        skinName = skinName.replace("\"", "");
        SkinDye skinDye = new SkinDye();
        for (int i = usedCommands + 1; i < currentCommand.length; ++i) {
            int b;
            int g;
            int r;
            String dyeCommand = currentCommand[i];
            ModLogger.log("Command dye: " + dyeCommand);
            if (!dyeCommand.contains("-")) {
                throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{skinName});
            }
            String[] commandSplit = dyeCommand.split("-");
            if (commandSplit.length != 2) {
                throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{skinName});
            }
            int dyeIndex = CommandGiveSkin.func_71532_a((ICommandSender)commandSender, (String)commandSplit[0], (int)1, (int)8) - 1;
            String dye = commandSplit[1];
            if (dye.startsWith("#") && dye.length() == 7) {
                if (this.isValidHex(dye)) {
                    Color dyeColour = Color.decode(dye);
                    r = dyeColour.getRed();
                    g = dyeColour.getGreen();
                    b = dyeColour.getBlue();
                    skinDye.addDye(dyeIndex, new byte[]{(byte)r, (byte)g, (byte)b, -1});
                    continue;
                }
                throw new WrongUsageException("commands.armourers.invalidDyeFormat", new Object[]{dye});
            }
            if (dye.length() >= 5 & dye.contains(",")) {
                String[] dyeValues = dye.split(",");
                if (dyeValues.length != 3) {
                    throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{skinName});
                }
                r = CommandGiveSkin.func_71532_a((ICommandSender)commandSender, (String)dyeValues[0], (int)0, (int)255);
                g = CommandGiveSkin.func_71532_a((ICommandSender)commandSender, (String)dyeValues[1], (int)0, (int)255);
                b = CommandGiveSkin.func_71532_a((ICommandSender)commandSender, (String)dyeValues[2], (int)0, (int)255);
                skinDye.addDye(dyeIndex, new byte[]{(byte)r, (byte)g, (byte)b, -1});
                continue;
            }
            throw new WrongUsageException("commands.armourers.invalidDyeFormat", new Object[]{dye});
        }
        LibraryFile libraryFile = new LibraryFile(skinName);
        Skin skin = SkinIOUtils.loadSkinFromLibraryFile(libraryFile);
        if (skin == null) {
            throw new WrongUsageException("commands.armourers.fileNotFound", new Object[]{skinName});
        }
        try {
            skin.lightHash();
        }
        catch (Exception e) {
            ModLogger.log(Level.ERROR, String.format("Unable to create ID for file %s.", libraryFile.toString()));
            return;
        }
        CommonSkinCache.INSTANCE.addEquipmentDataToCache(skin, libraryFile);
        SkinIdentifier skinIdentifier = new SkinIdentifier(0, libraryFile, 0, skin.getSkinType());
        ItemStack skinStack = SkinNBTHelper.makeEquipmentSkinStack(new SkinPointer(skinIdentifier, skinDye));
        EntityItem entityItem = player.func_71019_a(skinStack, false);
        entityItem.field_145804_b = 0;
        entityItem.func_145797_a(player.func_70005_c_());
    }

    private boolean isValidHex(String colorStr) {
        ModLogger.log(colorStr);
        String hexPatten = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
        Pattern pattern = Pattern.compile(hexPatten);
        Matcher matcher = pattern.matcher(colorStr);
        return matcher.matches();
    }
}

