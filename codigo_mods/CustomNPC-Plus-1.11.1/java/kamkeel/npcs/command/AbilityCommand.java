/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package kamkeel.npcs.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.ChainedAbility;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.PlayerData;

public class AbilityCommand
extends CommandKamkeelBase {
    public String func_71517_b() {
        return "ability";
    }

    @Override
    public String getDescription() {
        return "Ability operations";
    }

    @CommandKamkeelBase.SubCommand(desc="List all custom abilities")
    public void list(ICommandSender sender, String[] args) {
        Set<String> names = AbilityController.Instance.getCustomAbilityNames();
        if (names.isEmpty()) {
            ColorUtil.sendResult(sender, "No custom abilities found.");
            return;
        }
        ColorUtil.sendResult(sender, "Custom Abilities (" + names.size() + "):");
        for (String name : names) {
            Ability ability = AbilityController.Instance.getCustomAbility(name);
            if (ability != null) {
                ColorUtil.sendResult(sender, "  - \u00a7b" + name + "\u00a77 (" + ability.getTypeId() + ")");
                continue;
            }
            ColorUtil.sendResult(sender, "  - \u00a7b" + name);
        }
    }

    @CommandKamkeelBase.SubCommand(desc="List all registered ability types")
    public void types(ICommandSender sender, String[] args) {
        String[] typeIds = AbilityController.Instance.getTypes();
        if (typeIds.length == 0) {
            ColorUtil.sendResult(sender, "No ability types registered.");
            return;
        }
        ArrayList<String> sortedTypes = new ArrayList<String>();
        for (String typeId : typeIds) {
            sortedTypes.add(typeId);
        }
        Collections.sort(sortedTypes);
        ColorUtil.sendResult(sender, "Registered Ability Types (" + sortedTypes.size() + "):");
        for (String typeId : sortedTypes) {
            ColorUtil.sendResult(sender, "  - \u00a7d" + typeId);
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Reload abilities from disk")
    public void reload(ICommandSender sender, String[] args) {
        AbilityController.Instance.load();
        ColorUtil.sendResult(sender, "Abilities and chained abilities reloaded from disk.");
    }

    @CommandKamkeelBase.SubCommand(desc="Delete a custom ability by name", usage="<name>")
    public void delete(ICommandSender sender, String[] args) {
        if (args.length < 1) {
            ColorUtil.sendError(sender, "Usage: /kam ability delete <name>");
            return;
        }
        String name = this.joinArgs(args, 0);
        if (!AbilityController.Instance.hasCustomAbilityName(name)) {
            ColorUtil.sendError(sender, "No custom ability with name: " + name);
            return;
        }
        AbilityController.Instance.deleteCustomAbility(name);
        ColorUtil.sendResult(sender, "Deleted ability: \u00a7b" + name);
    }

    @CommandKamkeelBase.SubCommand(desc="Get info about an ability", usage="<name or uuid>")
    public void info(ICommandSender sender, String[] args) {
        if (args.length < 1) {
            ColorUtil.sendError(sender, "Usage: /kam ability info <name or uuid>");
            return;
        }
        String key = this.joinArgs(args, 0);
        Ability ability = AbilityController.Instance.resolveAbility(key);
        if (ability == null) {
            ColorUtil.sendError(sender, "No ability found for: " + key);
            return;
        }
        ColorUtil.sendResult(sender, "Ability: \u00a7b" + ability.getDisplayName());
        ColorUtil.sendResult(sender, "  Name: \u00a77" + ability.getName());
        ColorUtil.sendResult(sender, "  Type: \u00a7d" + ability.getTypeId());
        ColorUtil.sendResult(sender, "  Cooldown: \u00a7e" + ability.getCooldownTicks() + " ticks");
        ColorUtil.sendResult(sender, "  Wind Up: \u00a7e" + ability.getWindUpTicks() + " ticks");
        if (ability.isInterruptible()) {
            ColorUtil.sendResult(sender, "  Dazed: \u00a7e" + ability.getDazedTicks() + " ticks");
        }
        ColorUtil.sendResult(sender, "  Range: \u00a7e" + ability.getMinRange() + " - " + ability.getMaxRange());
        ColorUtil.sendResult(sender, "  Telegraph: \u00a7e" + (ability.isShowTelegraph() ? "Yes" : "No"));
        ColorUtil.sendResult(sender, "  Allowed By: \u00a7e" + ability.getAllowedBy().name());
    }

    @CommandKamkeelBase.SubCommand(desc="List all built-in abilities available for players")
    public void prebuilts(ICommandSender sender, String[] args) {
        Ability ability;
        Set<String> names = AbilityController.Instance.getAbilityNames();
        if (names.isEmpty()) {
            ColorUtil.sendResult(sender, "No built-in abilities found.");
            return;
        }
        ArrayList<String> playerAbilities = new ArrayList<String>();
        for (String name : names) {
            ability = AbilityController.Instance.getAbility(name);
            if (ability == null || !ability.getAllowedBy().allowsPlayer()) continue;
            playerAbilities.add(name);
        }
        if (playerAbilities.isEmpty()) {
            ColorUtil.sendResult(sender, "No player-usable built-in abilities found.");
            return;
        }
        Collections.sort(playerAbilities);
        ColorUtil.sendResult(sender, "Built-in Player Abilities (" + playerAbilities.size() + "):");
        for (String name : playerAbilities) {
            ability = AbilityController.Instance.getAbility(name);
            String userType = ability.getAllowedBy() == UserType.PLAYER_ONLY ? "\u00a7d[PLAYER]" : "\u00a7a[BOTH]";
            ColorUtil.sendResult(sender, "  - \u00a7b" + name + " " + userType);
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Give an ability to a player", usage="<player> <ability>")
    public void give(ICommandSender sender, String[] args) {
        if (args.length < 2) {
            ColorUtil.sendError(sender, "Usage: /kam ability give <player> <ability>");
            return;
        }
        String playerName = args[0];
        String abilityKey = this.joinArgs(args, 1);
        EntityPlayerMP player = (EntityPlayerMP)NoppesUtilServer.getPlayerByName(playerName);
        if (player == null) {
            ColorUtil.sendError(sender, "Player not found: " + playerName);
            return;
        }
        Ability ability = AbilityController.Instance.resolveAbility(abilityKey);
        if (ability == null) {
            ColorUtil.sendError(sender, "Ability not found: " + abilityKey);
            return;
        }
        String canonicalKey = ability.getId();
        if (canonicalKey == null || canonicalKey.isEmpty()) {
            canonicalKey = abilityKey;
        }
        String displayName = ability.getDisplayName();
        if (!ability.getAllowedBy().allowsPlayer()) {
            ColorUtil.sendError(sender, "Ability '\u00a7b" + displayName + "\u00a7c' is NPC-only and cannot be given to players.");
            return;
        }
        PlayerData data = PlayerDataController.Instance.getPlayerData((EntityPlayer)player);
        if (data.abilityData.hasUnlockedAbility(canonicalKey)) {
            ColorUtil.sendError(sender, "Player already has ability: " + displayName);
            return;
        }
        data.abilityData.unlockAbility(canonicalKey);
        ColorUtil.sendResult(sender, "Gave ability '\u00a7b" + displayName + "\u00a77' to player \u00a7a" + playerName);
    }

    @CommandKamkeelBase.SubCommand(desc="Remove an ability from a player", usage="<player> <ability>")
    public void remove(ICommandSender sender, String[] args) {
        if (args.length < 2) {
            ColorUtil.sendError(sender, "Usage: /kam ability remove <player> <ability>");
            return;
        }
        String playerName = args[0];
        String abilityKey = this.joinArgs(args, 1);
        EntityPlayerMP player = (EntityPlayerMP)NoppesUtilServer.getPlayerByName(playerName);
        if (player == null) {
            ColorUtil.sendError(sender, "Player not found: " + playerName);
            return;
        }
        Ability ability = AbilityController.Instance.resolveAbility(abilityKey);
        if (ability == null) {
            ColorUtil.sendError(sender, "Ability not found: " + abilityKey);
            return;
        }
        String canonicalKey = ability.getId() != null ? ability.getId() : abilityKey;
        String displayName = ability.getDisplayName();
        PlayerData data = PlayerDataController.Instance.getPlayerData((EntityPlayer)player);
        if (!data.abilityData.hasUnlockedAbility(canonicalKey)) {
            ColorUtil.sendError(sender, "Player doesn't have ability: " + displayName);
            return;
        }
        data.abilityData.lockAbility(canonicalKey);
        ColorUtil.sendResult(sender, "Removed ability '\u00a7b" + displayName + "\u00a77' from player \u00a7a" + playerName);
    }

    @CommandKamkeelBase.SubCommand(desc="Give a chained ability to a player", usage="<player> <chain>")
    public void giveChain(ICommandSender sender, String[] args) {
        if (args.length < 2) {
            ColorUtil.sendError(sender, "Usage: /kam ability giveChain <player> <chain>");
            return;
        }
        String playerName = args[0];
        String chainKey = this.joinArgs(args, 1);
        EntityPlayerMP player = (EntityPlayerMP)NoppesUtilServer.getPlayerByName(playerName);
        if (player == null) {
            ColorUtil.sendError(sender, "Player not found: " + playerName);
            return;
        }
        ChainedAbility chain = AbilityController.Instance.resolveChainedAbility(chainKey);
        if (chain == null) {
            ColorUtil.sendError(sender, "Chained ability not found: " + chainKey);
            return;
        }
        if (!chain.getAllowedBy().allowsPlayer()) {
            ColorUtil.sendError(sender, "Chained ability '\u00a7b" + chain.getDisplayName() + "\u00a7c' is NPC-only and cannot be given to players.");
            return;
        }
        String chainId = chain.getId();
        if (chainId == null || chainId.isEmpty()) {
            ColorUtil.sendError(sender, "Chained ability has no ID: " + chain.getDisplayName());
            return;
        }
        String storageKey = "chain:" + chainId;
        PlayerData data = PlayerDataController.Instance.getPlayerData((EntityPlayer)player);
        if (data.abilityData.hasUnlockedAbility(storageKey)) {
            ColorUtil.sendError(sender, "Player already has chained ability: " + chain.getDisplayName());
            return;
        }
        data.abilityData.unlockAbility(storageKey);
        ColorUtil.sendResult(sender, "Gave chained ability '\u00a7b" + chain.getDisplayName() + "\u00a77' to player \u00a7a" + playerName);
    }

    @CommandKamkeelBase.SubCommand(desc="Remove a chained ability from a player", usage="<player> <chain>")
    public void removeChain(ICommandSender sender, String[] args) {
        if (args.length < 2) {
            ColorUtil.sendError(sender, "Usage: /kam ability removeChain <player> <chain>");
            return;
        }
        String playerName = args[0];
        String chainKey = this.joinArgs(args, 1);
        EntityPlayerMP player = (EntityPlayerMP)NoppesUtilServer.getPlayerByName(playerName);
        if (player == null) {
            ColorUtil.sendError(sender, "Player not found: " + playerName);
            return;
        }
        ChainedAbility chain = AbilityController.Instance.resolveChainedAbility(chainKey);
        if (chain == null) {
            ColorUtil.sendError(sender, "Chained ability not found: " + chainKey);
            return;
        }
        String chainId = chain.getId();
        if (chainId == null || chainId.isEmpty()) {
            ColorUtil.sendError(sender, "Chained ability has no ID: " + chain.getDisplayName());
            return;
        }
        String storageKey = "chain:" + chainId;
        PlayerData data = PlayerDataController.Instance.getPlayerData((EntityPlayer)player);
        if (!data.abilityData.hasUnlockedAbility(storageKey)) {
            ColorUtil.sendError(sender, "Player doesn't have chained ability: " + chain.getDisplayName());
            return;
        }
        data.abilityData.lockAbility(storageKey);
        ColorUtil.sendResult(sender, "Removed chained ability '\u00a7b" + chain.getDisplayName() + "\u00a77' from player \u00a7a" + playerName);
    }

    @CommandKamkeelBase.SubCommand(desc="List abilities unlocked for a player", usage="<player>")
    public void player(ICommandSender sender, String[] args) {
        if (args.length < 1) {
            ColorUtil.sendError(sender, "Usage: /kam ability player <player>");
            return;
        }
        String playerName = args[0];
        EntityPlayerMP player = (EntityPlayerMP)NoppesUtilServer.getPlayerByName(playerName);
        if (player == null) {
            ColorUtil.sendError(sender, "Player not found: " + playerName);
            return;
        }
        PlayerData data = PlayerDataController.Instance.getPlayerData((EntityPlayer)player);
        String[] abilityKeys = data.abilityData.getUnlockedAbilities();
        if (abilityKeys.length == 0) {
            ColorUtil.sendResult(sender, "Player \u00a7a" + playerName + "\u00a77 has no unlocked abilities.");
            return;
        }
        ColorUtil.sendResult(sender, "Abilities for \u00a7a" + playerName + "\u00a77 (" + abilityKeys.length + "):");
        int selected = data.abilityData.getSelectedIndex();
        for (int i = 0; i < abilityKeys.length; ++i) {
            String displayName;
            String prefix;
            String key = abilityKeys[i];
            String string = prefix = i == selected ? "\u00a7e> " : "  ";
            if (key.startsWith("chain:")) {
                String chainName = key.substring("chain:".length());
                ChainedAbility chain = AbilityController.Instance.resolveChainedAbility(chainName);
                String display = chain != null ? chain.getDisplayName() : chainName;
                ColorUtil.sendResult(sender, prefix + "\u00a7d[Chain] \u00a7b" + display);
                continue;
            }
            Ability ability = AbilityController.Instance.resolveAbility(key);
            String string2 = displayName = ability != null ? ability.getDisplayName() : key;
            if (!displayName.equals(key)) {
                ColorUtil.sendResult(sender, prefix + "\u00a7b" + displayName + " \u00a78[" + key + "]");
                continue;
            }
            ColorUtil.sendResult(sender, prefix + "\u00a7b" + displayName);
        }
    }

    private String joinArgs(String[] args, int startIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i < args.length; ++i) {
            if (i > startIndex) {
                sb.append(" ");
            }
            sb.append(args[i]);
        }
        return sb.toString();
    }

    public static List<String> getPlayerAbilityNames() {
        Set<String> keys = AbilityController.Instance.getPlayerAbilityKeys();
        ArrayList<String> names = new ArrayList<String>();
        for (String key : keys) {
            Ability a = AbilityController.Instance.resolveAbility(key);
            names.add(a != null ? a.getName() : key);
        }
        Collections.sort(names);
        return names;
    }

    public static List<String> getPlayerChainNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (String chainName : AbilityController.Instance.getChainedAbilityNamesSet()) {
            ChainedAbility chain = AbilityController.Instance.getChainedAbility(chainName);
            if (chain == null || !chain.getAllowedBy().allowsPlayer()) continue;
            names.add(chainName);
        }
        Collections.sort(names);
        return names;
    }
}

