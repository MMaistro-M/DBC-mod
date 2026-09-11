/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 */
package kamkeel.npcs.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import kamkeel.npcs.controllers.AttributeController;
import kamkeel.npcs.controllers.data.attribute.AttributeDefinition;
import kamkeel.npcs.controllers.data.attribute.AttributeValueType;
import kamkeel.npcs.controllers.data.attribute.requirement.IRequirementChecker;
import kamkeel.npcs.controllers.data.attribute.requirement.RequirementCheckerRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.KeyPressHandler;
import noppes.npcs.controllers.MagicController;
import noppes.npcs.controllers.data.Magic;

public class AttributeItemUtil {
    public static final String TAG_RPGCORE = "RPGCore";
    public static final String TAG_ATTRIBUTES = "Attributes";
    public static final String TAG_MAGIC = "Magic";
    public static final String TAG_REQUIREMENTS = "Requirements";

    public static void applyAttribute(ItemStack item, String attributeKey, float value) {
        NBTTagCompound root;
        if (item == null) {
            return;
        }
        if (item.field_77990_d == null) {
            item.field_77990_d = new NBTTagCompound();
        }
        NBTTagCompound rpgCore = (root = item.field_77990_d).func_74764_b(TAG_RPGCORE) ? root.func_74775_l(TAG_RPGCORE) : new NBTTagCompound();
        NBTTagCompound attrTag = rpgCore.func_74764_b(TAG_ATTRIBUTES) ? rpgCore.func_74775_l(TAG_ATTRIBUTES) : new NBTTagCompound();
        attrTag.func_74776_a(attributeKey, value);
        rpgCore.func_74782_a(TAG_ATTRIBUTES, (NBTBase)attrTag);
        root.func_74782_a(TAG_RPGCORE, (NBTBase)rpgCore);
    }

    public static void applyAttribute(ItemStack item, AttributeDefinition definition, float value) {
        AttributeItemUtil.applyAttribute(item, definition.getKey(), value);
    }

    public static void removeAttribute(ItemStack item, String attributeKey) {
        NBTTagCompound rpgCore;
        if (item == null || item.field_77990_d == null) {
            return;
        }
        NBTTagCompound root = item.field_77990_d;
        if (root.func_74764_b(TAG_RPGCORE) && (rpgCore = root.func_74775_l(TAG_RPGCORE)).func_74764_b(TAG_ATTRIBUTES)) {
            NBTTagCompound attrTag = rpgCore.func_74775_l(TAG_ATTRIBUTES);
            attrTag.func_82580_o(attributeKey);
            if (attrTag.func_150296_c().isEmpty()) {
                rpgCore.func_82580_o(TAG_ATTRIBUTES);
            } else {
                rpgCore.func_74782_a(TAG_ATTRIBUTES, (NBTBase)attrTag);
            }
            root.func_74782_a(TAG_RPGCORE, (NBTBase)rpgCore);
        }
    }

    public static Map<String, Float> readAttributes(ItemStack item) {
        NBTTagCompound rpgCore;
        HashMap<String, Float> map = new HashMap<String, Float>();
        if (item == null || item.field_77990_d == null) {
            return map;
        }
        NBTTagCompound root = item.field_77990_d;
        if (root.func_74764_b(TAG_RPGCORE) && (rpgCore = root.func_74775_l(TAG_RPGCORE)).func_74764_b(TAG_ATTRIBUTES)) {
            NBTTagCompound attrTag = rpgCore.func_74775_l(TAG_ATTRIBUTES);
            Set keys = attrTag.func_150296_c();
            for (String key : keys) {
                map.put(key, Float.valueOf(attrTag.func_74760_g(key)));
            }
        }
        return map;
    }

    public static Map<Integer, Float> readMagicAttributeMap(ItemStack item, String attributeTag) {
        NBTTagCompound magicCompound;
        NBTTagCompound rpgCore;
        HashMap<Integer, Float> map = new HashMap<Integer, Float>();
        if (item == null || item.field_77990_d == null) {
            return map;
        }
        NBTTagCompound root = item.field_77990_d;
        if (root.func_74764_b(TAG_RPGCORE) && (rpgCore = root.func_74775_l(TAG_RPGCORE)).func_74764_b(TAG_MAGIC) && (magicCompound = rpgCore.func_74775_l(TAG_MAGIC)).func_74764_b(attributeTag)) {
            NBTTagCompound magicMap = magicCompound.func_74775_l(attributeTag);
            Set keys = magicMap.func_150296_c();
            for (String key : keys) {
                try {
                    int magicId = Integer.parseInt(key);
                    map.put(magicId, Float.valueOf(magicMap.func_74760_g(key)));
                }
                catch (NumberFormatException numberFormatException) {}
            }
        }
        return map;
    }

    public static void applyMagicAttribute(ItemStack item, String attributeTag, int magicId, float value) {
        AttributeItemUtil.writeMagicAttribute(item, attributeTag, magicId, value);
    }

    public static void writeMagicAttribute(ItemStack item, String attributeTag, int magicId, float value) {
        NBTTagCompound root;
        if (item == null) {
            return;
        }
        if (item.field_77990_d == null) {
            item.field_77990_d = new NBTTagCompound();
        }
        NBTTagCompound rpgCore = (root = item.field_77990_d).func_74764_b(TAG_RPGCORE) ? root.func_74775_l(TAG_RPGCORE) : new NBTTagCompound();
        NBTTagCompound magicCompound = rpgCore.func_74764_b(TAG_MAGIC) ? rpgCore.func_74775_l(TAG_MAGIC) : new NBTTagCompound();
        NBTTagCompound magicMap = magicCompound.func_74764_b(attributeTag) ? magicCompound.func_74775_l(attributeTag) : new NBTTagCompound();
        magicMap.func_74776_a(String.valueOf(magicId), value);
        magicCompound.func_74782_a(attributeTag, (NBTBase)magicMap);
        rpgCore.func_74782_a(TAG_MAGIC, (NBTBase)magicCompound);
        root.func_74782_a(TAG_RPGCORE, (NBTBase)rpgCore);
    }

    public static void removeMagicAttribute(ItemStack item, String attributeTag, int magicId) {
        NBTTagCompound magicCompound;
        NBTTagCompound rpgCore;
        if (item == null || item.field_77990_d == null) {
            return;
        }
        NBTTagCompound root = item.field_77990_d;
        if (root.func_74764_b(TAG_RPGCORE) && (rpgCore = root.func_74775_l(TAG_RPGCORE)).func_74764_b(TAG_MAGIC) && (magicCompound = rpgCore.func_74775_l(TAG_MAGIC)).func_74764_b(attributeTag)) {
            NBTTagCompound magicMap = magicCompound.func_74775_l(attributeTag);
            magicMap.func_82580_o(String.valueOf(magicId));
            if (magicMap.func_150296_c().isEmpty()) {
                magicCompound.func_82580_o(attributeTag);
            } else {
                magicCompound.func_74782_a(attributeTag, (NBTBase)magicMap);
            }
            rpgCore.func_74782_a(TAG_MAGIC, (NBTBase)magicCompound);
            root.func_74782_a(TAG_RPGCORE, (NBTBase)rpgCore);
        }
    }

    public static void applyRequirement(ItemStack item, String reqKey, Object value) {
        NBTTagCompound root;
        if (item == null) {
            return;
        }
        if (item.field_77990_d == null) {
            item.field_77990_d = new NBTTagCompound();
        }
        NBTTagCompound rpgCore = (root = item.field_77990_d).func_74764_b(TAG_RPGCORE) ? root.func_74775_l(TAG_RPGCORE) : new NBTTagCompound();
        NBTTagCompound reqTag = rpgCore.func_74764_b(TAG_REQUIREMENTS) ? rpgCore.func_74775_l(TAG_REQUIREMENTS) : new NBTTagCompound();
        IRequirementChecker checker = RequirementCheckerRegistry.getChecker(reqKey);
        if (checker != null) {
            checker.apply(reqTag, value);
        }
        rpgCore.func_74782_a(TAG_REQUIREMENTS, (NBTBase)reqTag);
        root.func_74782_a(TAG_RPGCORE, (NBTBase)rpgCore);
    }

    public static void removeRequirement(ItemStack item, String reqKey) {
        NBTTagCompound rpgCore;
        if (item == null || item.field_77990_d == null) {
            return;
        }
        NBTTagCompound root = item.field_77990_d;
        if (root.func_74764_b(TAG_RPGCORE) && (rpgCore = root.func_74775_l(TAG_RPGCORE)).func_74764_b(TAG_REQUIREMENTS)) {
            NBTTagCompound reqTag = rpgCore.func_74775_l(TAG_REQUIREMENTS);
            reqTag.func_82580_o(reqKey);
            if (reqTag.func_150296_c().isEmpty()) {
                rpgCore.func_82580_o(TAG_REQUIREMENTS);
            } else {
                rpgCore.func_74782_a(TAG_REQUIREMENTS, (NBTBase)reqTag);
            }
            root.func_74782_a(TAG_RPGCORE, (NBTBase)rpgCore);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public static List<String> getToolTip(List<String> original, NBTTagCompound compound, boolean override) {
        NBTTagCompound attrTag;
        ArrayList<String> tooltip = new ArrayList<String>(original);
        NBTTagCompound rpgCore = compound.func_74764_b(TAG_RPGCORE) ? compound.func_74775_l(TAG_RPGCORE) : new NBTTagCompound();
        NBTTagCompound nBTTagCompound = attrTag = rpgCore.func_74764_b(TAG_ATTRIBUTES) ? rpgCore.func_74775_l(TAG_ATTRIBUTES) : new NBTTagCompound();
        if (KeyPressHandler.isKeyBindDown(ClientProxy.NPCButton) || override) {
            ArrayList newTooltips = new ArrayList();
            if (!tooltip.isEmpty() && !override) {
                newTooltips.add(tooltip.get(0));
            }
            ArrayList<TooltipEntry> baseList = new ArrayList<TooltipEntry>();
            ArrayList<TooltipEntry> modifierList = new ArrayList<TooltipEntry>();
            ArrayList<TooltipEntry> statsList = new ArrayList<TooltipEntry>();
            ArrayList<TooltipEntry> infoList = new ArrayList<TooltipEntry>();
            ArrayList<TooltipEntry> extraList = new ArrayList<TooltipEntry>();
            Set keys = attrTag.func_150296_c();
            block6: for (String key : keys) {
                Float value = Float.valueOf(attrTag.func_74760_g(key));
                AttributeDefinition def = AttributeController.getAttribute(key);
                if (def == null) continue;
                AttributeDefinition.AttributeSection section = def != null ? def.getSection() : AttributeDefinition.AttributeSection.EXTRA;
                String plainName = AttributeItemUtil.getTranslatedAttributeName(key, def);
                String formattedLine = AttributeItemUtil.formatAttributeLine(def, section, value, plainName);
                TooltipEntry entry = new TooltipEntry(plainName, formattedLine);
                switch (section) {
                    case BASE: {
                        baseList.add(entry);
                        continue block6;
                    }
                    case MODIFIER: {
                        modifierList.add(entry);
                        continue block6;
                    }
                    case STATS: {
                        statsList.add(entry);
                        continue block6;
                    }
                    case INFO: {
                        infoList.add(entry);
                        continue block6;
                    }
                }
                extraList.add(entry);
            }
            AttributeItemUtil.processMagicAttributes(compound, baseList, modifierList, infoList, extraList);
            HashMap<String, Integer> baseOrder = new HashMap<String, Integer>();
            baseOrder.put("Health", 1);
            baseOrder.put("Main Attack Damage", 2);
            baseOrder.put("Neutral Damage", 3);
            HashMap<String, Integer> modOrder = new HashMap<String, Integer>();
            modOrder.put("Health Boost", 1);
            modOrder.put("Main Attack Damage", 2);
            modOrder.put("Neutral Damage", 3);
            modOrder.put("Movement Speed", 4);
            modOrder.put("Knockback Resistance", 5);
            newTooltips.addAll(AttributeItemUtil.buildSection(baseList, baseOrder));
            newTooltips.addAll(AttributeItemUtil.buildSection(modifierList, modOrder));
            newTooltips.addAll(AttributeItemUtil.buildSection(statsList));
            newTooltips.addAll(AttributeItemUtil.buildSection(infoList));
            newTooltips.addAll(AttributeItemUtil.buildSection(extraList));
            if (rpgCore.func_74764_b(TAG_REQUIREMENTS)) {
                NBTTagCompound reqTag = rpgCore.func_74775_l(TAG_REQUIREMENTS);
                ArrayList<TooltipEntry> reqEntries = new ArrayList<TooltipEntry>();
                Minecraft mc = Minecraft.func_71410_x();
                EntityClientPlayerMP clientPlayer = mc.field_71439_g;
                Set requirements = reqTag.func_150296_c();
                for (String reqKey : requirements) {
                    IRequirementChecker checker = RequirementCheckerRegistry.getChecker(reqKey);
                    if (checker == null) continue;
                    boolean met = clientPlayer != null && checker.check((EntityPlayer)clientPlayer, reqTag);
                    String tooltipValue = checker.getTooltipValue(reqTag);
                    String color = met ? EnumChatFormatting.GRAY.toString() : EnumChatFormatting.RED.toString();
                    String line = EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)checker.getTranslation()) + ": " + color + tooltipValue;
                    reqEntries.add(new TooltipEntry(AttributeItemUtil.stripFormatting(StatCollector.func_74838_a((String)checker.getTranslation())), line));
                }
                newTooltips.addAll(AttributeItemUtil.buildSection(reqEntries));
            }
            if (override) {
                tooltip.addAll(newTooltips);
            } else {
                tooltip = newTooltips;
            }
        } else {
            String keyName = GameSettings.func_74298_c((int)ClientProxy.NPCButton.func_151463_i());
            tooltip.add(EnumChatFormatting.YELLOW + "" + EnumChatFormatting.ITALIC + StatCollector.func_74838_a((String)"rpgcore:tooltip").replace("%key%", keyName));
        }
        return tooltip;
    }

    private static void processMagicAttributes(NBTTagCompound compound, List<TooltipEntry> baseList, List<TooltipEntry> modifierList, List<TooltipEntry> infoList, List<TooltipEntry> extraList) {
        String[] magicKeys;
        if (!compound.func_74764_b(TAG_RPGCORE)) {
            return;
        }
        NBTTagCompound rpgCore = compound.func_74775_l(TAG_RPGCORE);
        if (!rpgCore.func_74764_b(TAG_MAGIC)) {
            return;
        }
        NBTTagCompound magicCompound = rpgCore.func_74775_l(TAG_MAGIC);
        for (String magicKey : magicKeys = new String[]{"magic_damage", "magic_boost", "magic_defense", "magic_resistance"}) {
            if (!magicCompound.func_74764_b(magicKey)) continue;
            NBTTagCompound magicTag = magicCompound.func_74775_l(magicKey);
            AttributeDefinition def = AttributeController.getAttribute(magicKey);
            AttributeDefinition.AttributeSection section = def != null ? def.getSection() : AttributeDefinition.AttributeSection.EXTRA;
            Set keys = magicTag.func_150296_c();
            for (String key : keys) {
                try {
                    int magicId = Integer.parseInt(key);
                    Float value = Float.valueOf(magicTag.func_74760_g(key));
                    Magic magic = MagicController.getInstance().getMagic(magicId);
                    if (magic == null) continue;
                    String rawMagicName = magic.getDisplayName().replace("&", "\u00a7") + " \u00a77" + AttributeItemUtil.getMagicAppendix(magicKey);
                    String plainName = AttributeItemUtil.stripFormatting(rawMagicName);
                    String formattedLine = AttributeItemUtil.formatAttributeLine(def, section, value, rawMagicName);
                    TooltipEntry entry = new TooltipEntry(plainName, formattedLine);
                    switch (section) {
                        case BASE: {
                            baseList.add(entry);
                            break;
                        }
                        case MODIFIER: {
                            modifierList.add(entry);
                            break;
                        }
                        case INFO: {
                            infoList.add(entry);
                            break;
                        }
                        default: {
                            extraList.add(entry);
                        }
                    }
                }
                catch (NumberFormatException numberFormatException) {}
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    private static String getMagicAppendix(String type) {
        switch (type) {
            case "magic_defense": {
                return StatCollector.func_74838_a((String)"rpgcore:attribute.defense");
            }
            case "magic_resistance": {
                return StatCollector.func_74838_a((String)"rpgcore:attribute.resistance");
            }
        }
        return StatCollector.func_74838_a((String)"rpgcore:attribute.damage");
    }

    @SideOnly(value=Side.CLIENT)
    private static String getTranslatedAttributeName(String key, AttributeDefinition def) {
        key = def != null ? def.getTranslationKey() : key;
        String translation = null;
        if (StatCollector.func_94522_b((String)key)) {
            translation = StatCollector.func_74838_a((String)key);
        }
        if (translation == null && def != null) {
            translation = def.getDisplayName();
        } else if (translation == null) {
            translation = key;
        }
        return translation;
    }

    private static String formatAttributeLine(AttributeDefinition def, AttributeDefinition.AttributeSection section, Float value, String displayName) {
        String formattedValue = AttributeItemUtil.formatFloat(value);
        if (section == AttributeDefinition.AttributeSection.STATS) {
            String sign = value.floatValue() >= 0.0f ? "+" : "";
            String color = value.floatValue() >= 0.0f ? EnumChatFormatting.GREEN.toString() : EnumChatFormatting.RED.toString();
            String valueString = color + sign + formattedValue;
            if (def != null && def.getValueType() == AttributeValueType.PERCENT) {
                valueString = valueString + "%";
            }
            valueString = valueString + EnumChatFormatting.GRAY;
            displayName = def != null ? "\u00a7" + def.getColorCode() + displayName : EnumChatFormatting.AQUA + displayName;
            return valueString + " " + displayName;
        }
        if (section == AttributeDefinition.AttributeSection.MODIFIER || section == AttributeDefinition.AttributeSection.INFO) {
            String sign = value.floatValue() >= 0.0f ? "+" : "";
            String color = value.floatValue() >= 0.0f ? EnumChatFormatting.GREEN.toString() : EnumChatFormatting.RED.toString();
            String valueString = color + sign + formattedValue;
            if (def != null && (def.getValueType() == AttributeValueType.PERCENT || def.getValueType() == AttributeValueType.MAGIC)) {
                valueString = valueString + "%";
            }
            valueString = valueString + EnumChatFormatting.GRAY;
            return valueString + " " + displayName;
        }
        displayName = def != null ? "\u00a7" + def.getColorCode() + displayName : EnumChatFormatting.AQUA + displayName;
        String sign = value.floatValue() >= 0.0f ? "+" : "";
        String color = value.floatValue() >= 0.0f ? EnumChatFormatting.GREEN.toString() : EnumChatFormatting.RED.toString();
        String valueString = color + sign + formattedValue;
        return displayName + "\u00a77: " + valueString;
    }

    private static String formatFloat(Float value) {
        return new BigDecimal(Float.toString(value.floatValue())).stripTrailingZeros().toPlainString();
    }

    private static String stripFormatting(String input) {
        return input == null ? "" : Pattern.compile("(?i)\u00a7[0-9A-FK-OR]").matcher(input).replaceAll("");
    }

    private static List<String> buildSection(List<TooltipEntry> entries) {
        Collections.sort(entries, (a, b) -> a.sortKey.compareToIgnoreCase(b.sortKey));
        ArrayList<String> section = new ArrayList<String>();
        if (!entries.isEmpty()) {
            section.add("");
            for (TooltipEntry entry : entries) {
                section.add(entry.line);
            }
        }
        return section;
    }

    private static List<String> buildSection(List<TooltipEntry> entries, Map<String, Integer> orderMap) {
        Collections.sort(entries, (a, b) -> {
            int pb;
            int pa = orderMap.containsKey(a.sortKey) ? (Integer)orderMap.get(a.sortKey) : Integer.MAX_VALUE;
            int n = pb = orderMap.containsKey(b.sortKey) ? (Integer)orderMap.get(b.sortKey) : Integer.MAX_VALUE;
            if (pa != pb) {
                return Integer.compare(pa, pb);
            }
            return a.sortKey.compareToIgnoreCase(b.sortKey);
        });
        ArrayList<String> section = new ArrayList<String>();
        if (!entries.isEmpty()) {
            section.add("");
            for (TooltipEntry entry : entries) {
                section.add(entry.line);
            }
        }
        return section;
    }

    private static class TooltipEntry {
        public String sortKey;
        public String line;

        public TooltipEntry(String sortKey, String line) {
            this.sortKey = sortKey;
            this.line = line;
        }
    }
}

