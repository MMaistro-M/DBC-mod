/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 */
package kamkeel.npcs.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.CustomEffect;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.scripted.event.player.PlayerEvent;

public class EffectCommand
extends CommandKamkeelBase {
    @Override
    public String getDescription() {
        return "Custom Effect operations";
    }

    public String func_71517_b() {
        return "effect";
    }

    @CommandKamkeelBase.SubCommand(desc="Lists all Effects", usage="[page]")
    public void list(ICommandSender sender, String[] args) throws CommandException {
        CustomEffectController controller = CustomEffectController.getInstance();
        if (controller.indexMapper.isEmpty()) {
            ColorUtil.sendError(sender, "No effects found.");
            return;
        }
        ArrayList<EffectEntry> effectsList = new ArrayList<EffectEntry>();
        for (Map.Entry<Integer, HashMap<Integer, CustomEffect>> entry : controller.indexMapper.entrySet()) {
            int idx = entry.getKey();
            Map effects = entry.getValue();
            if (effects == null || effects.isEmpty()) continue;
            for (Map.Entry effectEntry : effects.entrySet()) {
                int id = (Integer)effectEntry.getKey();
                CustomEffect effect = (CustomEffect)effectEntry.getValue();
                effectsList.add(new EffectEntry(idx, id, effect));
            }
        }
        Collections.sort(effectsList, Comparator.comparingInt(e -> e.index).thenComparingInt(e -> e.id));
        int page = 1;
        if (args.length > 0) {
            try {
                page = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException entry) {
                // empty catch block
            }
        }
        int perPage = 10;
        int total = effectsList.size();
        int totalPages = (int)Math.ceil((double)total / (double)perPage);
        if (page < 1) {
            page = 1;
        }
        if (page > totalPages) {
            page = totalPages;
        }
        int startIndex = (page - 1) * perPage;
        int endIndex = Math.min(startIndex + perPage, total);
        ColorUtil.sendResult(sender, "--------------------");
        ColorUtil.sendResult(sender, "Effects (Page " + page + "/" + totalPages + "):");
        for (int i = startIndex; i < endIndex; ++i) {
            EffectEntry entry = (EffectEntry)effectsList.get(i);
            ColorUtil.sendResult(sender, String.format("\u00a7b%s \u00a77(ID: %d, INDEX: %d)", entry.effect.getName(), entry.id, entry.index));
        }
        ColorUtil.sendResult(sender, "--------------------");
    }

    @CommandKamkeelBase.SubCommand(desc="Gives an effect to a player", usage="<player> <time> <effectName>")
    public void give(ICommandSender sender, String[] args) throws CommandException {
        int time;
        if (args.length < 3) {
            ColorUtil.sendError(sender, "Usage: <player> <time> <effectName>");
            return;
        }
        String playername = args[0];
        try {
            time = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e) {
            ColorUtil.sendError(sender, "Invalid time: " + args[1]);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < args.length; ++i) {
            sb.append(args[i]).append(i < args.length - 1 ? " " : "");
        }
        String effectName = sb.toString();
        List<EffectEntry> matches = this.findEffectsByName(effectName);
        if (matches.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown effect: " + effectName);
            return;
        }
        if (matches.size() > 1) {
            StringBuilder ambig = new StringBuilder();
            ambig.append("Ambiguous effect name '").append(effectName).append("'. Multiple matches: ");
            for (EffectEntry match : matches) {
                ambig.append(match.effect.getName()).append(" (ID: ").append(match.id).append(", INDEX: ").append(match.index).append("), ");
            }
            ColorUtil.sendError(sender, ambig.substring(0, ambig.length() - 2));
            return;
        }
        EffectEntry match = matches.get(0);
        List<PlayerData> players = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (players.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        for (PlayerData pdata : players) {
            if (pdata.player == null) continue;
            CustomEffectController.getInstance().applyEffect(pdata.player, match.id, time, (byte)1, match.index);
            ColorUtil.sendResult(sender, String.format("%s \u00a7agiven to \u00a77'\u00a7b%s\u00a77'", match.effect.getName(), pdata.playername));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Removes an effect from a player", usage="<player> <effectName>")
    public void remove(ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 2) {
            ColorUtil.sendError(sender, "Usage: <player> <effectName>");
            return;
        }
        String playername = args[0];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; ++i) {
            sb.append(args[i]).append(i < args.length - 1 ? " " : "");
        }
        String effectName = sb.toString();
        List<EffectEntry> matches = this.findEffectsByName(effectName);
        if (matches.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown effect: " + effectName);
            return;
        }
        if (matches.size() > 1) {
            StringBuilder ambig = new StringBuilder();
            ambig.append("Ambiguous effect name '").append(effectName).append("'. Multiple matches: ");
            for (EffectEntry match : matches) {
                ambig.append(match.effect.getName()).append(" (ID: ").append(match.id).append(", INDEX: ").append(match.index).append("), ");
            }
            ColorUtil.sendError(sender, ambig.substring(0, ambig.length() - 2));
            return;
        }
        EffectEntry match = matches.get(0);
        List<PlayerData> players = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (players.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        for (PlayerData pdata : players) {
            if (pdata.player == null) continue;
            CustomEffectController.getInstance().removeEffect(pdata.player, match.id, match.index, PlayerEvent.EffectEvent.ExpirationType.REMOVED);
            ColorUtil.sendResult(sender, String.format("Effect %s removed from %s", match.effect.getName(), pdata.playername));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Gives an effect to a player by ID", usage="<player> <time> <effectId> [index]")
    public void giveId(ICommandSender sender, String[] args) throws CommandException {
        List<PlayerData> players;
        int effectId;
        int time;
        if (args.length < 3) {
            ColorUtil.sendError(sender, "Usage: <player> <time> <effectId> [index]");
            return;
        }
        String playername = args[0];
        try {
            time = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e) {
            ColorUtil.sendError(sender, "Invalid time: " + args[1]);
            return;
        }
        int index = 0;
        try {
            effectId = Integer.parseInt(args[2]);
        }
        catch (NumberFormatException e) {
            ColorUtil.sendError(sender, "Invalid effect ID: " + args[2]);
            return;
        }
        if (args.length > 3) {
            try {
                index = Integer.parseInt(args[3]);
            }
            catch (NumberFormatException e) {
                index = 0;
            }
        }
        if ((players = PlayerDataController.Instance.getPlayersData(sender, playername)).isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        CustomEffect effect = CustomEffectController.getInstance().get(effectId, index);
        if (effect == null) {
            ColorUtil.sendError(sender, "Unknown effect with ID: " + effectId + " and index " + index);
            return;
        }
        for (PlayerData pdata : players) {
            if (pdata.player == null) continue;
            CustomEffectController.getInstance().applyEffect(pdata.player, effect.id, time, (byte)1, effect.index);
            ColorUtil.sendResult(sender, String.format("%s \u00a7agiven to \u00a77'\u00a7b%s\u00a77'", effect.getName(), pdata.playername));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Removes an effect from a player by ID", usage="<player> <effectId> [index]")
    public void removeId(ICommandSender sender, String[] args) throws CommandException {
        List<PlayerData> players;
        int effectId;
        if (args.length < 2) {
            ColorUtil.sendError(sender, "Usage: <player> <effectId> [index]");
            return;
        }
        String playername = args[0];
        int index = 0;
        try {
            effectId = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e) {
            ColorUtil.sendError(sender, "Invalid effect ID: " + args[1]);
            return;
        }
        if (args.length > 2) {
            try {
                index = Integer.parseInt(args[2]);
            }
            catch (NumberFormatException e) {
                index = 0;
            }
        }
        if ((players = PlayerDataController.Instance.getPlayersData(sender, playername)).isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        CustomEffect effect = CustomEffectController.getInstance().get(effectId, index);
        if (effect == null) {
            ColorUtil.sendError(sender, "Unknown effect with ID: " + effectId + " and index " + index);
            return;
        }
        for (PlayerData pdata : players) {
            if (pdata.player == null) continue;
            CustomEffectController.getInstance().removeEffect(pdata.player, effect.id, effect.index, PlayerEvent.EffectEvent.ExpirationType.REMOVED);
            ColorUtil.sendResult(sender, String.format("Effect %s removed from %s", effect.getName(), pdata.playername));
        }
    }

    private List<EffectEntry> findEffectsByName(String name) {
        String strippedName = EffectCommand.stripColorCodes(name);
        ArrayList<EffectEntry> matches = new ArrayList<EffectEntry>();
        CustomEffectController controller = CustomEffectController.getInstance();
        for (Map.Entry<Integer, HashMap<Integer, CustomEffect>> entry : controller.indexMapper.entrySet()) {
            int idx = entry.getKey();
            Map effects = entry.getValue();
            if (effects == null) continue;
            for (Map.Entry effectEntry : effects.entrySet()) {
                int id = (Integer)effectEntry.getKey();
                CustomEffect effect = (CustomEffect)effectEntry.getValue();
                String effectName = EffectCommand.stripColorCodes(effect.getName());
                if (effectName.equalsIgnoreCase(strippedName)) {
                    ArrayList<EffectEntry> exact = new ArrayList<EffectEntry>();
                    exact.add(new EffectEntry(idx, id, effect));
                    return exact;
                }
                if (!effectName.toLowerCase().contains(strippedName.toLowerCase())) continue;
                matches.add(new EffectEntry(idx, id, effect));
            }
        }
        return matches;
    }

    private static String stripColorCodes(String s) {
        return s.replaceAll("\u00a7[0-9a-fk-or]", "");
    }

    public static List<String> getSortedEffectNames() {
        ArrayList<EffectEntry> entries = new ArrayList<EffectEntry>();
        CustomEffectController controller = CustomEffectController.getInstance();
        for (Map.Entry<Integer, HashMap<Integer, CustomEffect>> indexEntry : controller.indexMapper.entrySet()) {
            int index = indexEntry.getKey();
            Map effects = indexEntry.getValue();
            if (effects == null) continue;
            for (Map.Entry effectEntry : effects.entrySet()) {
                int id = (Integer)effectEntry.getKey();
                CustomEffect effect = (CustomEffect)effectEntry.getValue();
                entries.add(new EffectEntry(index, id, effect));
            }
        }
        entries.sort(Comparator.comparingInt(e -> e.index).thenComparingInt(e -> e.id));
        ArrayList<String> effectNames = new ArrayList<String>();
        for (EffectEntry entry : entries) {
            effectNames.add(entry.effect.getName());
        }
        return effectNames;
    }

    private static class EffectEntry {
        int index;
        int id;
        CustomEffect effect;

        public EffectEntry(int index, int id, CustomEffect effect) {
            this.index = index;
            this.id = id;
            this.effect = effect;
        }
    }
}

