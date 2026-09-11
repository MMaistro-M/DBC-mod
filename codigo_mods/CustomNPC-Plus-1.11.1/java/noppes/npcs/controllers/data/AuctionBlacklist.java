/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.common.registry.GameRegistry$UniqueIdentifier
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.config.ConfigMarket;

public class AuctionBlacklist {
    private static volatile Set<String> blacklistedItems = new HashSet<String>();
    private static volatile Set<Pattern> wildcardPatterns = new HashSet<Pattern>();
    private static volatile Set<String> blacklistedMods = new HashSet<String>();
    private static volatile Set<String> blacklistedNBTTags = new HashSet<String>();
    private static final String SOULBIND_KEY = "cnpc_soulbind";
    private static final String PROFILE_SLOT_KEY = "cnpc_profile_slot";
    private static final String BYPASS_PERMISSION = "customnpcs.auction.blacklist.bypass";

    public static void reload() {
        HashSet<String> newItems = new HashSet<String>();
        HashSet<Pattern> newWildcards = new HashSet<Pattern>();
        HashSet<String> newMods = new HashSet<String>();
        HashSet<String> newNBTTags = new HashSet<String>();
        for (String item : ConfigMarket.BlacklistedItems) {
            if (item == null || item.isEmpty()) continue;
            if ((item = item.toLowerCase().trim()).contains("*")) {
                String regex = "^" + item.replace(".", "\\.").replace("*", ".*") + "$";
                newWildcards.add(Pattern.compile(regex));
                continue;
            }
            newItems.add(item);
        }
        for (String mod : ConfigMarket.BlacklistedMods) {
            if (mod == null || mod.isEmpty()) continue;
            newMods.add(mod.toLowerCase().trim());
        }
        for (String tag : ConfigMarket.BlacklistedNBTTags) {
            if (tag == null || tag.isEmpty()) continue;
            newNBTTags.add(tag.trim());
        }
        blacklistedItems = newItems;
        wildcardPatterns = newWildcards;
        blacklistedMods = newMods;
        blacklistedNBTTags = newNBTTags;
    }

    public static boolean canBypass(EntityPlayer player) {
        if (player == null) {
            return false;
        }
        if (player.func_70003_b(2, BYPASS_PERMISSION)) {
            return true;
        }
        return CustomNpcsPermissions.hasCustomPermission(player, BYPASS_PERMISSION);
    }

    public static boolean isBlacklisted(ItemStack item) {
        if (item == null || item.func_77973_b() == null) {
            return true;
        }
        if (AuctionBlacklist.hasBoundRequirement(item)) {
            return true;
        }
        if (!ConfigMarket.BlacklistEnabled) {
            return false;
        }
        GameRegistry.UniqueIdentifier uid = GameRegistry.findUniqueIdentifierFor((Item)item.func_77973_b());
        if (uid == null) {
            return true;
        }
        String registryName = (uid.modId + ":" + uid.name).toLowerCase();
        String modId = uid.modId.toLowerCase();
        if (blacklistedItems.contains(registryName)) {
            return true;
        }
        for (Pattern pattern : wildcardPatterns) {
            if (!pattern.matcher(registryName).matches()) continue;
            return true;
        }
        if (blacklistedMods.contains(modId)) {
            return true;
        }
        if (item.func_77942_o() && !blacklistedNBTTags.isEmpty()) {
            NBTTagCompound tag = item.func_77978_p();
            for (String nbtKey : blacklistedNBTTags) {
                if (!tag.func_74764_b(nbtKey)) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean isBlacklistedForPlayer(ItemStack item, EntityPlayer player) {
        if (AuctionBlacklist.canBypass(player)) {
            return false;
        }
        return AuctionBlacklist.isBlacklisted(item);
    }

    public static String getRegistryName(ItemStack item) {
        if (item == null || item.func_77973_b() == null) {
            return null;
        }
        GameRegistry.UniqueIdentifier uid = GameRegistry.findUniqueIdentifierFor((Item)item.func_77973_b());
        if (uid == null) {
            return null;
        }
        return uid.modId + ":" + uid.name;
    }

    private static boolean hasBoundRequirement(ItemStack item) {
        if (item == null || !item.func_77942_o()) {
            return false;
        }
        NBTTagCompound root = item.func_77978_p();
        if (!root.func_74764_b("RPGCore")) {
            return false;
        }
        NBTTagCompound rpgCore = root.func_74775_l("RPGCore");
        if (!rpgCore.func_74764_b("Requirements")) {
            return false;
        }
        NBTTagCompound requirements = rpgCore.func_74775_l("Requirements");
        return requirements.func_74764_b(SOULBIND_KEY) || requirements.func_74764_b(PROFILE_SLOT_KEY);
    }

    public static boolean addItem(String registryName) {
        if (registryName == null || registryName.isEmpty()) {
            return false;
        }
        if ((registryName = registryName.toLowerCase().trim()).contains("*")) {
            String regex = "^" + registryName.replace(".", "\\.").replace("*", ".*") + "$";
            HashSet<Pattern> newWildcards = new HashSet<Pattern>(wildcardPatterns);
            newWildcards.add(Pattern.compile(regex));
            wildcardPatterns = newWildcards;
        } else {
            HashSet<String> newItems = new HashSet<String>(blacklistedItems);
            newItems.add(registryName);
            blacklistedItems = newItems;
        }
        return true;
    }

    public static boolean removeItem(String registryName) {
        if (registryName == null || registryName.isEmpty()) {
            return false;
        }
        if ((registryName = registryName.toLowerCase().trim()).contains("*")) {
            String regex = "^" + registryName.replace(".", "\\.").replace("*", ".*") + "$";
            HashSet<Pattern> newWildcards = new HashSet<Pattern>(wildcardPatterns);
            boolean removed = false;
            Iterator it = newWildcards.iterator();
            while (it.hasNext()) {
                if (!((Pattern)it.next()).pattern().equals(regex)) continue;
                it.remove();
                removed = true;
            }
            wildcardPatterns = newWildcards;
            return removed;
        }
        HashSet<String> newItems = new HashSet<String>(blacklistedItems);
        boolean removed = newItems.remove(registryName);
        blacklistedItems = newItems;
        return removed;
    }

    public static boolean addMod(String modId) {
        if (modId == null || modId.isEmpty()) {
            return false;
        }
        HashSet<String> newMods = new HashSet<String>(blacklistedMods);
        boolean added = newMods.add(modId.toLowerCase().trim());
        blacklistedMods = newMods;
        return added;
    }

    public static boolean removeMod(String modId) {
        if (modId == null || modId.isEmpty()) {
            return false;
        }
        HashSet<String> newMods = new HashSet<String>(blacklistedMods);
        boolean removed = newMods.remove(modId.toLowerCase().trim());
        blacklistedMods = newMods;
        return removed;
    }

    public static boolean addNBTTag(String tag) {
        if (tag == null || tag.isEmpty()) {
            return false;
        }
        HashSet<String> newTags = new HashSet<String>(blacklistedNBTTags);
        boolean added = newTags.add(tag.trim());
        blacklistedNBTTags = newTags;
        return added;
    }

    public static boolean removeNBTTag(String tag) {
        if (tag == null || tag.isEmpty()) {
            return false;
        }
        HashSet<String> newTags = new HashSet<String>(blacklistedNBTTags);
        boolean removed = newTags.remove(tag.trim());
        blacklistedNBTTags = newTags;
        return removed;
    }

    public static List<String> getBlacklistedItems() {
        ArrayList<String> result = new ArrayList<String>(blacklistedItems);
        for (Pattern p : wildcardPatterns) {
            String pattern = p.pattern();
            pattern = pattern.substring(1, pattern.length() - 1);
            pattern = pattern.replace("\\.", ".").replace(".*", "*");
            result.add(pattern);
        }
        return result;
    }

    public static List<String> getBlacklistedMods() {
        return new ArrayList<String>(blacklistedMods);
    }

    public static List<String> getBlacklistedNBTTags() {
        return new ArrayList<String>(blacklistedNBTTags);
    }
}

