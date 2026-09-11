/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.common.command;

import java.io.File;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.command.ModCommand;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.skin.exporter.ISkinExporter;
import riskyken.armourersWorkshop.common.skin.exporter.SkinExportManager;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class CommandExportSkin
extends ModCommand {
    public String func_71517_b() {
        return "exportSkin";
    }

    public void func_71515_b(ICommandSender commandSender, String[] currentCommand) {
        Skin skin;
        if (currentCommand.length < 3) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{currentCommand});
        }
        EntityPlayerMP player = CommandExportSkin.func_71521_c((ICommandSender)commandSender);
        if (player == null) {
            return;
        }
        ItemStack stack = player.func_71045_bC();
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stack);
        if (skinPointer == null) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{currentCommand});
        }
        String fileExtension = currentCommand[1];
        ISkinExporter skinExporter = SkinExportManager.getSkinExporter(fileExtension);
        if (skinExporter == null) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{currentCommand});
        }
        String exportName = currentCommand[2];
        if (!exportName.substring(0, 1).equals("\"")) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{exportName});
        }
        int usedCommands = 2;
        if (!exportName.substring(exportName.length() - 1, exportName.length()).equals("\"")) {
            for (int i = 3; i < currentCommand.length; ++i) {
                if (!(exportName = exportName + " " + currentCommand[i]).substring(exportName.length() - 1, exportName.length()).equals("\"")) continue;
                usedCommands = i;
                break;
            }
        }
        if (!exportName.substring(exportName.length() - 1, exportName.length()).equals("\"")) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{exportName});
        }
        exportName = exportName.replace("\"", "");
        float scale = 0.0625f;
        if (currentCommand.length > usedCommands + 1) {
            scale = (float)CommandExportSkin.func_82363_b((ICommandSender)commandSender, (String)currentCommand[usedCommands + 1]);
        }
        if ((skin = ClientSkinCache.INSTANCE.getSkin(skinPointer)) == null) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{currentCommand});
        }
        File exportDir = new File(System.getProperty("user.dir"), "model-exports");
        if (!exportDir.exists()) {
            exportDir.mkdir();
        }
        SkinExportManager.exportSkin(skin, skinExporter, new File(exportDir, String.format("%s.%s", exportName, fileExtension)), scale);
    }
}

