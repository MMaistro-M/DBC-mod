/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.StringUtils
 */
package riskyken.armourersWorkshop.common.command;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.StringUtils;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.command.ModCommand;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.wardrobe.ExPropsPlayerSkinData;

public class CommandSetUnlockedWardrobeSlots
extends ModCommand {
    public String func_71517_b() {
        return "setUnlockedWardrobeSlots";
    }

    public List func_71516_a(ICommandSender commandSender, String[] currentCommand) {
        if (currentCommand.length == 2) {
            return CommandSetUnlockedWardrobeSlots.func_71530_a((String[])currentCommand, (String[])this.getPlayers());
        }
        if (currentCommand.length == 3) {
            ArrayList<ISkinType> skinTypes = SkinTypeRegistry.INSTANCE.getRegisteredSkinTypes();
            String[] skinTypesNames = new String[skinTypes.size()];
            for (int i = 0; i < skinTypes.size(); ++i) {
                skinTypesNames[i] = skinTypes.get(i).getRegistryName();
            }
            return CommandSetUnlockedWardrobeSlots.func_71530_a((String[])currentCommand, (String[])skinTypesNames);
        }
        return null;
    }

    public void func_71515_b(ICommandSender commandSender, String[] currentCommand) {
        if (currentCommand.length != 4) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{currentCommand});
        }
        String playerName = currentCommand[1];
        EntityPlayerMP player = CommandSetUnlockedWardrobeSlots.func_82359_c((ICommandSender)commandSender, (String)playerName);
        if (player == null) {
            return;
        }
        String skinTypeName = currentCommand[2];
        if (StringUtils.func_151246_b((String)skinTypeName)) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{currentCommand});
        }
        int count = 3;
        count = CommandSetUnlockedWardrobeSlots.func_71532_a((ICommandSender)commandSender, (String)currentCommand[3], (int)1, (int)8);
        ISkinType skinType = SkinTypeRegistry.INSTANCE.getSkinTypeFromRegistryName(skinTypeName);
        if (skinType == null) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{currentCommand});
        }
        ExPropsPlayerSkinData.get((EntityPlayer)player).setSkinColumnCount(skinType, count);
    }
}

