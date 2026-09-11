/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 */
package kamkeel.npcs.command;

import java.util.Iterator;
import java.util.List;
import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import noppes.npcs.api.handler.data.IAnimation;
import noppes.npcs.controllers.AnimationController;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.PlayerData;

public class AnimationCommand
extends CommandKamkeelBase {
    public String func_71517_b() {
        return "animation";
    }

    @Override
    public String getDescription() {
        return "Animation operations";
    }

    @CommandKamkeelBase.SubCommand(desc="set an animation to a player", usage="<player> <num>")
    public void set(ICommandSender sender, String[] args) throws CommandException {
        int animationId;
        String playername = args[0];
        try {
            animationId = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "Animation num must be an integer: " + args[1]);
            return;
        }
        List<PlayerData> data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (data.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        IAnimation animation = AnimationController.getInstance().get(animationId);
        if (animation == null) {
            ColorUtil.sendError(sender, "No Animation ID found: " + animationId);
            return;
        }
        Iterator<PlayerData> iterator = data.iterator();
        if (iterator.hasNext()) {
            PlayerData playerdata = iterator.next();
            playerdata.animationData.setEnabled(false);
            playerdata.animationData.setAnimation(animation);
            playerdata.animationData.updateClient();
            playerdata.save();
            ColorUtil.sendResult(sender, String.format("Animation set to Player '\u00a7b%s\u00a77' on ID \u00a7d%d", playerdata.playername, animationId));
            return;
        }
    }

    @CommandKamkeelBase.SubCommand(desc="clear an animation from a player", usage="<player>")
    public void clear(ICommandSender sender, String[] args) throws CommandException {
        String playername = args[0];
        List<PlayerData> data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (data.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        Iterator<PlayerData> iterator = data.iterator();
        if (iterator.hasNext()) {
            PlayerData playerdata = iterator.next();
            playerdata.animationData.setEnabled(false);
            playerdata.animationData.setAnimation(null);
            playerdata.animationData.updateClient();
            playerdata.save();
            ColorUtil.sendResult(sender, String.format("Animation cleared from Player '\u00a7b%s\u00a77'", playerdata.playername));
            return;
        }
    }

    @CommandKamkeelBase.SubCommand(desc="enable an animation on a player", usage="<player>")
    public void enable(ICommandSender sender, String[] args) throws CommandException {
        String playername = args[0];
        List<PlayerData> data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (data.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        Iterator<PlayerData> iterator = data.iterator();
        if (iterator.hasNext()) {
            PlayerData playerdata = iterator.next();
            if (playerdata.animationData.getAnimation() == null) {
                ColorUtil.sendError(sender, String.format("Player '\u00a7b%s\u00a74' does not have an animation set", playerdata.playername));
                return;
            }
            playerdata.animationData.setEnabled(true);
            playerdata.animationData.updateClient();
            playerdata.save();
            ColorUtil.sendResult(sender, String.format("Animation Enabled for Player '\u00a7b%s\u00a77'", playerdata.playername));
            return;
        }
    }

    @CommandKamkeelBase.SubCommand(desc="disable an animation on a player", usage="<player>")
    public void disable(ICommandSender sender, String[] args) throws CommandException {
        String playername = args[0];
        List<PlayerData> data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (data.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        Iterator<PlayerData> iterator = data.iterator();
        if (iterator.hasNext()) {
            PlayerData playerdata = iterator.next();
            if (playerdata.animationData.getAnimation() == null) {
                ColorUtil.sendError(sender, String.format("Player '\u00a7b%s\u00a74' does not have an animation set", playerdata.playername));
                return;
            }
            playerdata.animationData.setEnabled(false);
            playerdata.animationData.updateClient();
            playerdata.save();
            ColorUtil.sendResult(sender, String.format("Animation Disabled for Player '\u00a7b%s\u00a77'", playerdata.playername));
            return;
        }
    }

    @CommandKamkeelBase.SubCommand(desc="reload animations")
    public void reload(ICommandSender sender, String[] args) {
        AnimationController.Instance.load();
        ColorUtil.sendResult(sender, "Animations Reloaded");
    }
}

