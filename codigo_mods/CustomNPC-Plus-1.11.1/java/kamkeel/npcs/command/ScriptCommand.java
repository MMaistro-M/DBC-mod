/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 */
package kamkeel.npcs.command;

import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.ICommandSender;
import noppes.npcs.controllers.ScriptController;

public class ScriptCommand
extends CommandKamkeelBase {
    @CommandKamkeelBase.SubCommand(desc="Reload scripts data and folders.")
    public Boolean reload(ICommandSender sender, String[] args) {
        ScriptController.Instance.loadCategories();
        ScriptController.Instance.syncClientScripts(null);
        if (ScriptController.Instance.loadPlayerScripts()) {
            ColorUtil.sendResult(sender, "Reload player scripts successfully");
        } else {
            ColorUtil.sendError(sender, "Failed reloading player scripts");
        }
        if (ScriptController.Instance.loadForgeScripts()) {
            ColorUtil.sendResult(sender, "Reload forge scripts successfully");
        } else {
            ColorUtil.sendError(sender, "Failed reloading forge scripts");
        }
        if (ScriptController.Instance.loadStoredData()) {
            ColorUtil.sendResult(sender, "Reload stored data successfully");
        } else {
            ColorUtil.sendError(sender, "Failed reloading stored data");
        }
        return true;
    }

    public String func_71517_b() {
        return "script";
    }

    @Override
    public String getDescription() {
        return "Commands for scripts";
    }
}

