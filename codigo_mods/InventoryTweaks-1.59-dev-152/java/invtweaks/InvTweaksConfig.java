/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.MinecraftForge
 *  org.apache.logging.log4j.Logger
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksConfigInventoryRuleset;
import invtweaks.InvTweaksConfigProperties;
import invtweaks.InvTweaksConfigSortingRule;
import invtweaks.InvTweaksConst;
import invtweaks.InvTweaksItemTree;
import invtweaks.InvTweaksItemTreeLoader;
import invtweaks.api.IItemTreeItem;
import invtweaks.forge.ClientProxy;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;

public class InvTweaksConfig {
    private static final Logger log = InvTweaks.log;
    public static final String PROP_VERSION = "version";
    public static final String PROP_ENABLE_MIDDLE_CLICK = "enableMiddleClick";
    public static final String PROP_SHOW_CHEST_BUTTONS = "showChestButtons";
    public static final String PROP_ENABLE_SORTING_ON_PICKUP = "enableSortingOnPickup";
    public static final String PROP_ENABLE_AUTO_EQUIP_ARMOR = "enableAutoEquipArmor";
    public static final String PROP_ENABLE_AUTO_REFILL = "enableAutoRefill";
    public static final String PROP_AUTO_REFILL_BEFORE_BREAK = "autoRefillBeforeBreak";
    public static final String PROP_AUTO_REFILL_DAMAGE_THRESHHOLD = "autoRefillDamageThreshhold";
    @Deprecated
    public static final String PROP_KEY_SORT_INVENTORY = "keySortInventory";
    public static final String PROP_ENABLE_SHORTCUTS = "enableShortcuts";
    public static final String PROP_SHORTCUT_PREFIX = "shortcutKey";
    public static final String PROP_SHORTCUT_ONE_ITEM = "shortcutKeyOneItem";
    public static final String PROP_OBSOLETE_SHORTCUT_ONE_STACK = "shortcutKeyOneStack";
    public static final String PROP_SHORTCUT_ALL_ITEMS = "shortcutKeyAllItems";
    public static final String PROP_SHORTCUT_EVERYTHING = "shortcutKeyEverything";
    public static final String PROP_SHORTCUT_DROP = "shortcutKeyDrop";
    public static final String PROP_SHORTCUT_UP = "shortcutKeyToUpperSection";
    public static final String PROP_SHORTCUT_DOWN = "shortcutKeyToLowerSection";
    public static final String PROP_ENABLE_SOUNDS = "enableSounds";
    public static final String PROP_OBSOLETE_ENABLE_SORTING_SOUND = "enableSortingSound";
    public static final String PROP_OBSOLETE_ENABLE_AUTO_REFILL_SOUND = "enableAutoRefillSound";
    public static final String PROP_ENABLE_SERVER_ITEMSWAP = "enableServerItemSwap";
    public static final String VALUE_TRUE = "true";
    public static final String VALUE_FALSE = "false";
    public static final String VALUE_CI_COMPATIBILITY = "convenientInventoryCompatibility";
    public static final String LOCKED = "locked";
    public static final String FROZEN = "frozen";
    public static final String AUTOREFILL = "autorefill";
    public static final String AUTOREFILL_NOTHING = "nothing";
    public static final String DEBUG = "debug";
    public static final boolean DEFAULT_AUTO_REFILL_BEHAVIOUR = true;
    private File rulesFile;
    private File treeFile;
    private InvTweaksConfigProperties properties;
    private InvTweaksItemTree tree;
    private Vector<InvTweaksConfigInventoryRuleset> rulesets;
    private int currentRuleset = 0;
    private String currentRulesetName = null;
    private Vector<String> invalidKeywords;
    private long storedConfigLastModified;

    public InvTweaksConfig(File rulesFile, File treeFile) {
        this.rulesFile = rulesFile;
        this.treeFile = treeFile;
        this.reset();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void load() throws Exception {
        InvTweaksConfig invTweaksConfig = this;
        synchronized (invTweaksConfig) {
            this.reset();
            this.loadProperties();
            this.saveProperties();
            if (this.tree != null) {
                MinecraftForge.EVENT_BUS.unregister((Object)this.tree);
            }
            this.tree = InvTweaksItemTreeLoader.load(this.treeFile);
            char[] bytes = new char[(int)this.rulesFile.length()];
            FileReader reader = null;
            try {
                reader = new FileReader(this.rulesFile);
                reader.read(bytes);
            }
            finally {
                if (reader != null) {
                    reader.close();
                }
            }
            String[] configLines = String.valueOf(bytes).replace("\r\n", "\n").replace('\r', '\n').split("\n");
            InvTweaksConfigInventoryRuleset activeRuleset = new InvTweaksConfigInventoryRuleset(this.tree, "Default");
            boolean defaultRuleset = true;
            boolean defaultRulesetEmpty = true;
            for (String line : configLines) {
                String trimmedLine = line.trim();
                if (trimmedLine.isEmpty()) continue;
                if (trimmedLine.matches("^[\\w]*[\\s]*\\:$")) {
                    if (!defaultRuleset || !defaultRulesetEmpty) {
                        activeRuleset.finalizeRules();
                        this.rulesets.add(activeRuleset);
                    }
                    activeRuleset = new InvTweaksConfigInventoryRuleset(this.tree, trimmedLine.substring(0, trimmedLine.length() - 1));
                    continue;
                }
                try {
                    String invalidKeyword = activeRuleset.registerLine(trimmedLine);
                    if (defaultRuleset) {
                        defaultRulesetEmpty = false;
                    }
                    if (invalidKeyword == null) continue;
                    this.invalidKeywords.add(invalidKeyword);
                }
                catch (InvalidParameterException e) {
                    // empty catch block
                }
            }
            activeRuleset.finalizeRules();
            this.rulesets.add(activeRuleset);
            this.currentRuleset = 0;
            if (this.currentRulesetName != null) {
                int rulesetIndex = 0;
                for (InvTweaksConfigInventoryRuleset ruleset : this.rulesets) {
                    if (ruleset.getName().equals(this.currentRulesetName)) {
                        this.currentRuleset = rulesetIndex;
                        break;
                    }
                    ++rulesetIndex;
                }
            }
            if (this.currentRuleset == 0) {
                this.currentRulesetName = !this.rulesets.isEmpty() ? this.rulesets.get(this.currentRuleset).getName() : null;
            }
        }
    }

    public boolean refreshProperties() throws IOException {
        long configLastModified = InvTweaksConst.CONFIG_PROPS_FILE.lastModified();
        if (this.storedConfigLastModified != configLastModified) {
            this.storedConfigLastModified = configLastModified;
            this.loadProperties();
            return true;
        }
        return false;
    }

    public void saveProperties() {
        File configPropsFile = this.getPropertyFile();
        if (configPropsFile.exists()) {
            try {
                FileOutputStream fos = new FileOutputStream(configPropsFile);
                this.properties.store(fos, "Inventory Tweaks Configuration\n(Regarding shortcuts, all key names can be found at: http://www.lwjgl.org/javadoc/org/lwjgl/input/Keyboard.html)");
                fos.flush();
                fos.close();
                this.storedConfigLastModified = InvTweaksConst.CONFIG_PROPS_FILE.lastModified();
            }
            catch (IOException e) {
                InvTweaks.logInGameStatic("Failed to save config file " + InvTweaksConst.CONFIG_PROPS_FILE);
            }
        }
    }

    public Map<String, String> getProperties(String prefix) {
        HashMap<String, String> result = new HashMap<String, String>();
        for (Object o : this.properties.keySet()) {
            String key = (String)o;
            if (!key.startsWith(prefix)) continue;
            result.put(key, this.properties.getProperty(key));
        }
        return result;
    }

    public String getProperty(String key) {
        String value = this.properties.getProperty(key);
        return value != null ? value : "";
    }

    public int getIntProperty(String key) {
        return Integer.parseInt(this.getProperty(key));
    }

    public void setProperty(String key, String value) {
        this.properties.put(key, (Object)value);
        this.saveProperties();
        if (key.equals(PROP_ENABLE_MIDDLE_CLICK)) {
            this.resolveConvenientInventoryConflicts();
        }
    }

    public InvTweaksItemTree getTree() {
        return this.tree;
    }

    public String getCurrentRulesetName() {
        return this.currentRulesetName;
    }

    public String switchConfig(int i) {
        if (!this.rulesets.isEmpty() && i < this.rulesets.size() && i != this.currentRuleset) {
            this.currentRuleset = i;
            this.currentRulesetName = this.rulesets.get(this.currentRuleset).getName();
            return this.currentRulesetName;
        }
        return null;
    }

    public String switchConfig() {
        if (this.currentRuleset == -1) {
            return this.switchConfig(0);
        }
        return this.switchConfig((this.currentRuleset + 1) % this.rulesets.size());
    }

    public Vector<InvTweaksConfigSortingRule> getRules() {
        return this.rulesets.get(this.currentRuleset).getRules();
    }

    public Vector<String> getInvalidKeywords() {
        return this.invalidKeywords;
    }

    public int[] getLockPriorities() {
        return this.rulesets.get(this.currentRuleset).getLockPriorities();
    }

    public boolean[] getFrozenSlots() {
        return this.rulesets.get(this.currentRuleset).getFrozenSlots();
    }

    public Vector<Integer> getLockedSlots() {
        return this.rulesets.get(this.currentRuleset).getLockedSlots();
    }

    public boolean isAutoRefillEnabled(String itemID, int itemDamage) {
        if (!this.getProperty(PROP_ENABLE_AUTO_REFILL).equals(VALUE_FALSE)) {
            List<IItemTreeItem> items = this.tree.getItems(itemID, itemDamage);
            Vector<String> autoReplaceRules = this.rulesets.get(this.currentRuleset).getAutoReplaceRules();
            boolean found = false;
            for (String keyword : autoReplaceRules) {
                if (keyword.equals(AUTOREFILL_NOTHING)) {
                    return false;
                }
                if (!this.tree.matches(items, keyword)) continue;
                found = true;
            }
            if (found) {
                return true;
            }
            return autoReplaceRules.isEmpty();
        }
        return false;
    }

    public void resolveConvenientInventoryConflicts() {
        boolean defaultCISortingShortcutEnabled;
        boolean convenientInventoryInstalled;
        block15: {
            convenientInventoryInstalled = false;
            defaultCISortingShortcutEnabled = false;
            try {
                Class<?> convenientInventory = Class.forName("ConvenientInventory");
                convenientInventoryInstalled = true;
                Field middleClickField = null;
                try {
                    middleClickField = convenientInventory.getDeclaredField("middleClickEnabled");
                }
                catch (NoSuchFieldException e) {
                    // empty catch block
                }
                if (middleClickField != null) {
                    boolean middleClickSorting = this.getProperty(PROP_ENABLE_MIDDLE_CLICK).equals(VALUE_TRUE);
                    middleClickField.setAccessible(true);
                    middleClickField.setBoolean(null, !middleClickSorting);
                    break block15;
                }
                Field initializedField = convenientInventory.getDeclaredField("initialized");
                initializedField.setAccessible(true);
                Boolean initialized = (Boolean)initializedField.get(null);
                if (!initialized.booleanValue()) {
                    Method initializeMethod = convenientInventory.getDeclaredMethod("initialize", new Class[0]);
                    initializeMethod.setAccessible(true);
                    initializeMethod.invoke(null, new Object[0]);
                }
                Field actionMapField = convenientInventory.getDeclaredField("actionMap");
                actionMapField.setAccessible(true);
                List[][] actionMap = (List[][])actionMapField.get(null);
                if (actionMap == null || actionMap[7] == null) break block15;
                for (List combo : actionMap[7]) {
                    if (combo == null || combo.size() != 1 || (Integer)combo.get(0) != 2) continue;
                    defaultCISortingShortcutEnabled = true;
                    break;
                }
            }
            catch (ClassNotFoundException e) {
            }
            catch (LinkageError e) {
            }
            catch (Exception e) {
                InvTweaks.logInGameErrorStatic("invtweaks.modcompat.ci.error", e);
            }
        }
        String shortcutsProp = this.getProperty(PROP_ENABLE_SHORTCUTS);
        if (convenientInventoryInstalled && !shortcutsProp.equals(VALUE_CI_COMPATIBILITY)) {
            this.setProperty(PROP_ENABLE_SHORTCUTS, VALUE_CI_COMPATIBILITY);
        } else if (!convenientInventoryInstalled && shortcutsProp.equals(VALUE_CI_COMPATIBILITY)) {
            this.setProperty(PROP_ENABLE_SHORTCUTS, VALUE_TRUE);
        }
        String middleClickProp = this.getProperty(PROP_ENABLE_MIDDLE_CLICK);
        if (defaultCISortingShortcutEnabled && !middleClickProp.equals(VALUE_CI_COMPATIBILITY)) {
            this.setProperty(PROP_ENABLE_MIDDLE_CLICK, VALUE_CI_COMPATIBILITY);
        } else if (!defaultCISortingShortcutEnabled && middleClickProp.equals(VALUE_CI_COMPATIBILITY)) {
            this.setProperty(PROP_ENABLE_MIDDLE_CLICK, VALUE_TRUE);
        }
    }

    private void reset() {
        this.rulesets = new Vector();
        this.currentRuleset = -1;
        this.properties = new InvTweaksConfigProperties();
        this.properties.put(PROP_ENABLE_MIDDLE_CLICK, (Object)VALUE_TRUE);
        this.properties.put(PROP_SHOW_CHEST_BUTTONS, (Object)VALUE_TRUE);
        this.properties.put(PROP_ENABLE_SORTING_ON_PICKUP, (Object)VALUE_FALSE);
        this.properties.put(PROP_ENABLE_AUTO_REFILL, (Object)VALUE_TRUE);
        this.properties.put(PROP_AUTO_REFILL_BEFORE_BREAK, (Object)VALUE_FALSE);
        this.properties.put(PROP_AUTO_REFILL_DAMAGE_THRESHHOLD, (Object)"5");
        this.properties.put(PROP_ENABLE_SOUNDS, (Object)VALUE_TRUE);
        this.properties.put(PROP_ENABLE_SHORTCUTS, (Object)VALUE_TRUE);
        this.properties.put(PROP_ENABLE_AUTO_EQUIP_ARMOR, (Object)VALUE_FALSE);
        this.properties.put(PROP_ENABLE_SERVER_ITEMSWAP, (Object)VALUE_TRUE);
        this.properties.put(PROP_SHORTCUT_ALL_ITEMS, (Object)"LCONTROL+LSHIFT, RCONTROL+RSHIFT");
        this.properties.put(PROP_SHORTCUT_EVERYTHING, (Object)"SPACE");
        this.properties.put(PROP_SHORTCUT_ONE_ITEM, (Object)"LCONTROL, RCONTROL");
        this.properties.put(PROP_SHORTCUT_UP, (Object)"UP");
        this.properties.put(PROP_SHORTCUT_DOWN, (Object)"DOWN");
        this.properties.put(PROP_SHORTCUT_DROP, (Object)"LALT, RALT");
        this.properties.put(PROP_VERSION, (Object)"1.59-dev-152-cf6e263".split(" ")[0]);
        this.invalidKeywords = new Vector();
    }

    private void loadProperties() throws IOException {
        File configPropsFile = this.getPropertyFile();
        InvTweaksConfigProperties newProperties = new InvTweaksConfigProperties();
        if (configPropsFile != null) {
            FileInputStream fis = new FileInputStream(configPropsFile);
            newProperties.load(fis);
            fis.close();
            this.resolveConvenientInventoryConflicts();
        }
        newProperties.sortKeys();
        newProperties.remove(PROP_OBSOLETE_ENABLE_SORTING_SOUND);
        newProperties.remove(PROP_OBSOLETE_ENABLE_AUTO_REFILL_SOUND);
        newProperties.remove(PROP_OBSOLETE_SHORTCUT_ONE_STACK);
        newProperties.remove(PROP_KEY_SORT_INVENTORY);
        if (newProperties.get(PROP_VERSION) != null) {
            for (Map.Entry<Object, Object> entry : newProperties.entrySet()) {
                this.properties.put(entry.getKey(), entry.getValue());
            }
            if (this.properties.contains("enableAutoreplaceSound")) {
                this.properties.put(PROP_OBSOLETE_ENABLE_AUTO_REFILL_SOUND, this.properties.get("enableAutoreplaceSound"));
                this.properties.remove("enableAutoreplaceSound");
            }
        }
    }

    private File getPropertyFile() {
        File configPropsFile = InvTweaksConst.CONFIG_PROPS_FILE;
        if (!configPropsFile.exists()) {
            try {
                configPropsFile.createNewFile();
            }
            catch (IOException e) {
                InvTweaks.logInGameStatic("invtweaks.propsfile.errors");
                return null;
            }
        }
        return configPropsFile;
    }

    public int getSortKeyCode() {
        return ClientProxy.KEYBINDING_SORT.func_151463_i();
    }
}

