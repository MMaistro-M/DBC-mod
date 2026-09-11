/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.common.registry.GameRegistry$UniqueIdentifier
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 */
package riskyken.armourersWorkshop.common.command;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import riskyken.armourersWorkshop.common.addons.ModAddonManager;
import riskyken.armourersWorkshop.common.command.ModCommand;
import riskyken.armourersWorkshop.common.config.ConfigHandlerOverrides;
import riskyken.armourersWorkshop.utils.ModLogger;

public class CommandSetItemAsSkinnable
extends ModCommand {
    public String func_71517_b() {
        return "setItemAsSkinnable";
    }

    public void func_71515_b(ICommandSender commandSender, String[] currentCommand) {
        EntityPlayerMP player = CommandSetItemAsSkinnable.func_71521_c((ICommandSender)commandSender);
        if (player == null) {
            return;
        }
        ItemStack stack = player.func_71045_bC();
        if (stack != null) {
            Configuration config = ConfigHandlerOverrides.config;
            Property prop = config.get(ConfigHandlerOverrides.CATEGORY_OVERRIDES, "itemOverrides", ModAddonManager.getDefaultOverrides());
            String[] itemOverrides = prop.getStringList();
            String[] newItemOverrides = new String[itemOverrides.length + 1];
            System.arraycopy(itemOverrides, 0, newItemOverrides, 0, itemOverrides.length);
            GameRegistry.UniqueIdentifier uniqueIdentifier = GameRegistry.findUniqueIdentifierFor((Item)stack.func_77973_b());
            newItemOverrides[newItemOverrides.length - 1] = "sword:" + uniqueIdentifier.toString();
            ModLogger.log(String.format("Setting item %s as skinnable.", uniqueIdentifier.toString()));
            prop.set(newItemOverrides);
            config.save();
        }
    }
}

