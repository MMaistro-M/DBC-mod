/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.LWJGLException
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.Display
 */
package invtweaks;

import cpw.mods.fml.common.Loader;
import invtweaks.InvTweaksConfig;
import invtweaks.InvTweaksConfigManager;
import invtweaks.InvTweaksConfigSortingRule;
import invtweaks.InvTweaksContainerManager;
import invtweaks.InvTweaksContainerSectionManager;
import invtweaks.InvTweaksGuiSettingsButton;
import invtweaks.InvTweaksGuiSortingButton;
import invtweaks.InvTweaksHandlerSorting;
import invtweaks.InvTweaksItemTree;
import invtweaks.InvTweaksObfuscation;
import invtweaks.InvTweaksShortcutMapping;
import invtweaks.InvTweaksShortcutType;
import invtweaks.TickScheduledTask;
import invtweaks.api.IItemTreeItem;
import invtweaks.api.SortingMethod;
import invtweaks.api.container.ContainerSection;
import invtweaks.forge.InvTweaksMod;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.logging.Level;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class InvTweaks
extends InvTweaksObfuscation {
    public static Logger log;
    private static InvTweaks instance;
    private InvTweaksConfigManager cfgManager = null;
    private SortingMethod chestAlgorithm = SortingMethod.DEFAULT;
    private long chestAlgorithmClickTimestamp = 0L;
    private boolean chestAlgorithmButtonDown = false;
    private String storedStackId = null;
    private int storedStackDamage = Short.MAX_VALUE;
    private int storedFocusedSlot = -1;
    private ItemStack[] hotbarClone = new ItemStack[9];
    private boolean hadFocus = true;
    private boolean mouseWasDown = false;
    private boolean wasInGUI = false;
    private int tickNumber = 0;
    private int lastPollingTickNumber = -3;
    private long sortingKeyPressedDate = 0L;
    private boolean sortKeyDown = false;
    private boolean sortKeyEnabled = true;
    private boolean textboxMode = false;
    private boolean itemPickupPending = false;
    private int itemPickupTimeout = 0;
    private boolean isNEILoaded;
    private PriorityQueue<TickScheduledTask> scheduledTasks = new PriorityQueue<TickScheduledTask>(16, new TickScheduledTask.TaskComparator());
    private List<String> queuedMessages = new ArrayList<String>();
    private boolean wasNEIEnabled = false;
    private Class neiClientConfig;
    private Method neiHidden;

    public InvTweaks(Minecraft mc) {
        super(mc);
        instance = this;
        this.isNEILoaded = Loader.isModLoaded((String)"NotEnoughItems");
        this.cfgManager = new InvTweaksConfigManager(mc);
        if (this.cfgManager.makeSureConfigurationIsLoaded()) {
            log.info("Mod initialized");
        } else {
            log.error("Mod failed to initialize!");
        }
    }

    public void addScheduledTask(TickScheduledTask task) {
        this.scheduledTasks.add(task);
    }

    public void addScheduledTask(long time, final Runnable task) {
        this.scheduledTasks.add(new TickScheduledTask(time){

            @Override
            void run() {
                task.run();
            }
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void onTickInGame() {
        InvTweaks invTweaks = this;
        synchronized (invTweaks) {
            if (!this.onTick()) {
                return;
            }
            this.handleAutoRefill();
            if (this.wasInGUI) {
                this.wasInGUI = false;
                this.textboxMode = false;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void onTickInGUI(GuiScreen guiScreen) {
        InvTweaks invTweaks = this;
        synchronized (invTweaks) {
            ItemStack currentStack;
            this.handleMiddleClick(guiScreen);
            if (!this.onTick()) {
                return;
            }
            if (this.isTimeForPolling()) {
                this.unlockKeysIfNecessary();
            }
            if (guiScreen instanceof GuiContainer) {
                this.handleGUILayout((GuiContainer)guiScreen);
            }
            if (!this.wasInGUI) {
                this.mouseWasDown = true;
                log.info(guiScreen.getClass().getName());
                if (guiScreen instanceof GuiContainer) {
                    log.info(((GuiContainer)guiScreen).field_147002_h.getClass().getName());
                }
            }
            if (guiScreen instanceof GuiContainer) {
                this.handleShortcuts((GuiContainer)guiScreen);
            }
            this.storedStackId = (currentStack = this.getFocusedStack()) == null ? null : Item.field_150901_e.func_148750_c((Object)currentStack.func_77973_b());
            int n = this.storedStackDamage = currentStack == null ? 0 : currentStack.func_77960_j();
            if (!this.wasInGUI) {
                this.wasInGUI = true;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void onSortingKeyPressed() {
        InvTweaks invTweaks = this;
        synchronized (invTweaks) {
            if (!this.cfgManager.makeSureConfigurationIsLoaded()) {
                return;
            }
            GuiScreen guiScreen = this.getCurrentScreen();
            if (guiScreen == null || guiScreen instanceof GuiContainer && (InvTweaks.isValidChest(((GuiContainer)guiScreen).field_147002_h) || InvTweaks.isValidInventory(((GuiContainer)guiScreen).field_147002_h))) {
                this.handleSorting(guiScreen);
            }
        }
    }

    public void onItemPickup() {
        if (!this.cfgManager.makeSureConfigurationIsLoaded()) {
            return;
        }
        InvTweaksConfig config = this.cfgManager.getConfig();
        if (this.cfgManager.getConfig().getProperty("enableSortingOnPickup").equals("false")) {
            this.itemPickupPending = false;
            return;
        }
        try {
            InvTweaksContainerSectionManager containerMgr = new InvTweaksContainerSectionManager(this.mc, ContainerSection.INVENTORY);
            int currentSlot = -1;
            for (int i = 0; i < 9; ++i) {
                ItemStack currentHotbarStack = containerMgr.getItemStack(i + 27);
                if (currentHotbarStack == null || currentHotbarStack.field_77992_b <= 0 || this.hotbarClone[i] != null) continue;
                currentSlot = i + 27;
            }
            if (currentSlot != -1) {
                this.itemPickupPending = false;
                LinkedList<Integer> prefferedPositions = new LinkedList<Integer>();
                InvTweaksItemTree tree = config.getTree();
                ItemStack stack = containerMgr.getItemStack(currentSlot);
                List<IItemTreeItem> items = tree.getItems(Item.field_150901_e.func_148750_c((Object)stack.func_77973_b()), stack.func_77960_j());
                for (InvTweaksConfigSortingRule rule : config.getRules()) {
                    if (!tree.matches(items, rule.getKeyword())) continue;
                    for (int slot : rule.getPreferredSlots()) {
                        prefferedPositions.add(slot);
                    }
                }
                boolean hasToBeMoved = true;
                Iterator i$ = prefferedPositions.iterator();
                while (i$.hasNext()) {
                    int newSlot = (Integer)i$.next();
                    if (newSlot == currentSlot) {
                        hasToBeMoved = false;
                        break;
                    }
                    if (containerMgr.getItemStack(newSlot) != null || !containerMgr.move(currentSlot, newSlot)) continue;
                    break;
                }
                if (hasToBeMoved) {
                    for (int i = 0; !(i >= containerMgr.getSize() || containerMgr.getItemStack(i) == null && containerMgr.move(currentSlot, i)); ++i) {
                    }
                }
                InvTweaksMod.proxy.sortComplete();
            } else if (--this.itemPickupTimeout == 0) {
                this.itemPickupPending = false;
            }
        }
        catch (Exception e) {
            this.logInGameError("Failed to move picked up stack", e);
            this.itemPickupPending = false;
        }
    }

    public int compareItems(ItemStack i, ItemStack j) {
        return this.compareItems(i, j, this.getItemOrder(i), this.getItemOrder(j));
    }

    int compareItems(ItemStack i, ItemStack j, int orderI, int orderJ) {
        if (j == null) {
            return -1;
        }
        if (i == null || orderI == -1) {
            return 1;
        }
        if (orderI == orderJ) {
            if (i.func_77973_b() == j.func_77973_b()) {
                boolean iHasName = i.func_82837_s();
                boolean jHasName = j.func_82837_s();
                if (iHasName || jHasName) {
                    String jDisplayName;
                    if (!iHasName) {
                        return -1;
                    }
                    if (!jHasName) {
                        return 1;
                    }
                    String iDisplayName = i.func_82833_r();
                    if (!iDisplayName.equals(jDisplayName = j.func_82833_r())) {
                        return iDisplayName.compareTo(jDisplayName);
                    }
                }
                Map iEnchs = EnchantmentHelper.func_82781_a((ItemStack)i);
                Map jEnchs = EnchantmentHelper.func_82781_a((ItemStack)j);
                if (iEnchs.size() == jEnchs.size()) {
                    int iEnchMaxId = 0;
                    int iEnchMaxLvl = 0;
                    int jEnchMaxId = 0;
                    int jEnchMaxLvl = 0;
                    for (Map.Entry ench : iEnchs.entrySet()) {
                        if ((Integer)ench.getValue() > iEnchMaxLvl) {
                            iEnchMaxId = (Integer)ench.getKey();
                            iEnchMaxLvl = (Integer)ench.getValue();
                            continue;
                        }
                        if ((Integer)ench.getValue() != iEnchMaxLvl || (Integer)ench.getKey() <= iEnchMaxId) continue;
                        iEnchMaxId = (Integer)ench.getKey();
                    }
                    for (Map.Entry ench : jEnchs.entrySet()) {
                        if ((Integer)ench.getValue() > jEnchMaxLvl) {
                            jEnchMaxId = (Integer)ench.getKey();
                            jEnchMaxLvl = (Integer)ench.getValue();
                            continue;
                        }
                        if ((Integer)ench.getValue() != jEnchMaxLvl || (Integer)ench.getKey() <= jEnchMaxId) continue;
                        jEnchMaxId = (Integer)ench.getKey();
                    }
                    if (iEnchMaxId == jEnchMaxId) {
                        if (iEnchMaxLvl == jEnchMaxLvl) {
                            if (i.func_77960_j() != j.func_77960_j()) {
                                if (i.func_77984_f()) {
                                    return j.func_77960_j() - i.func_77960_j();
                                }
                                return i.func_77960_j() - j.func_77960_j();
                            }
                            return j.field_77994_a - i.field_77994_a;
                        }
                        return jEnchMaxLvl - iEnchMaxLvl;
                    }
                    return jEnchMaxId - iEnchMaxId;
                }
                return jEnchs.size() - iEnchs.size();
            }
            return ObjectUtils.compare(Item.field_150901_e.func_148750_c((Object)i.func_77973_b()), Item.field_150901_e.func_148750_c((Object)j.func_77973_b()));
        }
        return orderI - orderJ;
    }

    public void setItemPickupPending(boolean itemPickupPending) {
        this.itemPickupPending = itemPickupPending;
        this.itemPickupTimeout = 5;
    }

    public void setSortKeyEnabled(boolean enabled) {
        this.sortKeyEnabled = enabled;
    }

    public void setTextboxMode(boolean enabled) {
        this.textboxMode = enabled;
    }

    public void logInGame(String message) {
        this.logInGame(message, false);
    }

    public void printQueuedMessages() {
        if (this.mc.field_71456_v != null && !this.queuedMessages.isEmpty()) {
            for (String s : this.queuedMessages) {
                this.addChatMessage(s);
            }
            this.queuedMessages.clear();
        }
    }

    public void logInGame(String message, boolean alreadyTranslated) {
        String formattedMsg = this.buildlogString(Level.INFO, alreadyTranslated ? message : StatCollector.func_74838_a((String)message));
        if (this.mc.field_71456_v == null) {
            this.queuedMessages.add(formattedMsg);
        } else {
            this.addChatMessage(formattedMsg);
        }
        log.info(formattedMsg);
    }

    public void logInGameError(String message, Exception e) {
        e.printStackTrace();
        String formattedMsg = this.buildlogString(Level.SEVERE, StatCollector.func_74838_a((String)message), e);
        if (this.mc.field_71456_v == null) {
            this.queuedMessages.add(formattedMsg);
        } else {
            this.addChatMessage(formattedMsg);
        }
    }

    public static void logInGameStatic(String message) {
        InvTweaks.getInstance().logInGame(message);
    }

    public static void logInGameErrorStatic(String message, Exception e) {
        InvTweaks.getInstance().logInGameError(message, e);
    }

    public static InvTweaks getInstance() {
        return instance;
    }

    public static Minecraft getMinecraftInstance() {
        return InvTweaks.instance.mc;
    }

    public static InvTweaksConfigManager getConfigManager() {
        return InvTweaks.instance.cfgManager;
    }

    public static boolean classExists(String className) {
        try {
            return Class.forName(className) != null;
        }
        catch (ClassNotFoundException e) {
            return false;
        }
    }

    private boolean onTick() {
        GuiScreen currentScreen;
        this.printQueuedMessages();
        ++this.tickNumber;
        InvTweaksConfig config = this.cfgManager.getConfig();
        if (config == null) {
            return false;
        }
        while (!this.scheduledTasks.isEmpty() && this.scheduledTasks.peek().getScheduledTickTime() <= this.mc.field_71441_e.func_82737_E()) {
            this.scheduledTasks.poll().run();
        }
        if (this.itemPickupPending) {
            this.onItemPickup();
        }
        if ((currentScreen = this.getCurrentScreen()) == null || InvTweaks.isGuiInventory(currentScreen)) {
            this.cloneHotbar();
        }
        if (this.isSortingShortcutDown()) {
            if (!this.sortKeyDown) {
                this.sortKeyDown = true;
                this.onSortingKeyPressed();
            }
        } else {
            this.sortKeyDown = false;
        }
        this.handleConfigSwitch();
        return true;
    }

    private void handleConfigSwitch() {
        InvTweaksConfig config = this.cfgManager.getConfig();
        GuiScreen currentScreen = this.getCurrentScreen();
        this.cfgManager.getShortcutsHandler().updatePressedKeys();
        InvTweaksShortcutMapping switchMapping = this.cfgManager.getShortcutsHandler().isShortcutDown(InvTweaksShortcutType.MOVE_TO_SPECIFIC_HOTBAR_SLOT);
        if (this.isSortingShortcutDown() && switchMapping != null) {
            String newRuleset = null;
            int pressedKey = switchMapping.getKeyCodes().get(0);
            if (pressedKey >= 2 && pressedKey <= 10) {
                newRuleset = config.switchConfig(pressedKey - 2);
            } else {
                switch (pressedKey) {
                    case 79: {
                        newRuleset = config.switchConfig(0);
                        break;
                    }
                    case 80: {
                        newRuleset = config.switchConfig(1);
                        break;
                    }
                    case 81: {
                        newRuleset = config.switchConfig(2);
                        break;
                    }
                    case 75: {
                        newRuleset = config.switchConfig(3);
                        break;
                    }
                    case 76: {
                        newRuleset = config.switchConfig(4);
                        break;
                    }
                    case 77: {
                        newRuleset = config.switchConfig(5);
                        break;
                    }
                    case 71: {
                        newRuleset = config.switchConfig(6);
                        break;
                    }
                    case 72: {
                        newRuleset = config.switchConfig(7);
                        break;
                    }
                    case 73: {
                        newRuleset = config.switchConfig(8);
                    }
                }
            }
            if (newRuleset != null) {
                this.logInGame(String.format(StatCollector.func_74838_a((String)"invtweaks.loadconfig.enabled"), newRuleset), true);
                this.sortingKeyPressedDate = Integer.MAX_VALUE;
            }
        }
        if (this.isSortingShortcutDown()) {
            long currentTime = System.currentTimeMillis();
            if (this.sortingKeyPressedDate == 0L) {
                this.sortingKeyPressedDate = currentTime;
            } else if (currentTime - this.sortingKeyPressedDate > 1000L && this.sortingKeyPressedDate != Integer.MAX_VALUE) {
                String previousRuleset = config.getCurrentRulesetName();
                String newRuleset = config.switchConfig();
                if (previousRuleset != null && newRuleset != null && !previousRuleset.equals(newRuleset)) {
                    this.logInGame(String.format(StatCollector.func_74838_a((String)"invtweaks.loadconfig.enabled"), newRuleset), true);
                    this.handleSorting(currentScreen);
                }
                this.sortingKeyPressedDate = currentTime;
            }
        } else {
            this.sortingKeyPressedDate = 0L;
        }
    }

    private void handleSorting(GuiScreen guiScreen) {
        ItemStack[] mainInventory;
        ItemStack selectedItem = null;
        int focusedSlot = this.getFocusedSlot();
        if (focusedSlot < (mainInventory = this.getMainInventory()).length && focusedSlot >= 0) {
            selectedItem = mainInventory[focusedSlot];
        }
        try {
            new InvTweaksHandlerSorting(this.mc, this.cfgManager.getConfig(), ContainerSection.INVENTORY, SortingMethod.INVENTORY, 9).sort();
        }
        catch (Exception e) {
            this.logInGameError("invtweaks.sort.inventory.error", e);
            e.printStackTrace();
        }
        this.playClick();
        if (selectedItem != null && mainInventory[focusedSlot] == null) {
            this.storedStackId = null;
        }
    }

    private void handleAutoRefill() {
        ItemStack currentStack = this.getFocusedStack();
        String currentStackId = currentStack == null ? null : Item.field_150901_e.func_148750_c((Object)currentStack.func_77973_b());
        int currentStackDamage = currentStack == null ? 0 : currentStack.func_77960_j();
        int focusedSlot = this.getFocusedSlot() + 27;
        InvTweaksConfig config = this.cfgManager.getConfig();
        if (!ObjectUtils.equals(currentStackId, this.storedStackId) || currentStackDamage != this.storedStackDamage) {
            if (this.storedFocusedSlot != focusedSlot) {
                this.storedFocusedSlot = focusedSlot;
            } else if ((currentStack == null || currentStack.func_77973_b() == Items.field_151054_z && ObjectUtils.equals(this.storedStackId, "mushroom_stew")) && (this.getCurrentScreen() == null || InvTweaks.isGuiEditSign(this.getCurrentScreen()))) {
                if (config.isAutoRefillEnabled(this.storedStackId, this.storedStackDamage)) {
                    try {
                        this.cfgManager.getAutoRefillHandler().autoRefillSlot(focusedSlot, this.storedStackId, this.storedStackDamage);
                    }
                    catch (Exception e) {
                        this.logInGameError("invtweaks.sort.autorefill.error", e);
                    }
                }
            } else {
                int autoRefillThreshhold;
                int itemMaxDamage = currentStack.func_77958_k();
                if (this.canToolBeReplaced(currentStackDamage, itemMaxDamage, autoRefillThreshhold = config.getIntProperty("autoRefillDamageThreshhold")) && config.getProperty("autoRefillBeforeBreak").equals("true") && config.isAutoRefillEnabled(this.storedStackId, this.storedStackDamage)) {
                    try {
                        this.cfgManager.getAutoRefillHandler().autoRefillSlot(focusedSlot, this.storedStackId, this.storedStackDamage);
                    }
                    catch (Exception e) {
                        this.logInGameError("invtweaks.sort.autorefill.error", e);
                    }
                }
            }
        }
        this.storedStackId = currentStackId;
        this.storedStackDamage = currentStackDamage;
    }

    private boolean canToolBeReplaced(int currentStackDamage, int itemMaxDamage, int autoRefillThreshhold) {
        return itemMaxDamage != 0 && itemMaxDamage - currentStackDamage < autoRefillThreshhold && itemMaxDamage - this.storedStackDamage >= autoRefillThreshhold;
    }

    private void handleMiddleClick(GuiScreen guiScreen) {
        if (Mouse.isButtonDown((int)2)) {
            if (!this.cfgManager.makeSureConfigurationIsLoaded()) {
                return;
            }
            InvTweaksConfig config = this.cfgManager.getConfig();
            if (config.getProperty("enableMiddleClick").equals("true") && guiScreen instanceof GuiContainer) {
                GuiContainer guiContainer = (GuiContainer)guiScreen;
                Container container = guiContainer.field_147002_h;
                if (!this.chestAlgorithmButtonDown) {
                    this.chestAlgorithmButtonDown = true;
                    InvTweaksContainerManager containerMgr = new InvTweaksContainerManager(this.mc);
                    Slot slotAtMousePosition = InvTweaksObfuscation.getSlotAtMousePosition((GuiContainer)this.getCurrentScreen());
                    ContainerSection target = null;
                    if (slotAtMousePosition != null) {
                        target = containerMgr.getSlotSection(InvTweaks.getSlotNumber(slotAtMousePosition));
                    }
                    if (InvTweaks.isValidChest(container)) {
                        if (ContainerSection.CHEST.equals((Object)target)) {
                            this.playClick();
                            long timestamp = System.currentTimeMillis();
                            if (timestamp - this.chestAlgorithmClickTimestamp > 2000L) {
                                this.chestAlgorithm = SortingMethod.DEFAULT;
                            }
                            try {
                                new InvTweaksHandlerSorting(this.mc, this.cfgManager.getConfig(), ContainerSection.CHEST, this.chestAlgorithm, this.getContainerRowSize(guiContainer)).sort();
                            }
                            catch (Exception e) {
                                this.logInGameError("invtweaks.sort.chest.error", e);
                                e.printStackTrace();
                            }
                            this.chestAlgorithm = SortingMethod.values()[(this.chestAlgorithm.ordinal() + 1) % 3];
                            this.chestAlgorithmClickTimestamp = timestamp;
                        } else if (ContainerSection.CRAFTING_IN.equals((Object)target) || ContainerSection.CRAFTING_IN_PERSISTENT.equals((Object)target)) {
                            try {
                                new InvTweaksHandlerSorting(this.mc, this.cfgManager.getConfig(), target, SortingMethod.EVEN_STACKS, containerMgr.getSize(target) == 9 ? 3 : 2).sort();
                            }
                            catch (Exception e) {
                                this.logInGameError("invtweaks.sort.crafting.error", e);
                                e.printStackTrace();
                            }
                        } else if (ContainerSection.INVENTORY_HOTBAR.equals((Object)target) || ContainerSection.INVENTORY_NOT_HOTBAR.equals((Object)target)) {
                            this.handleSorting(guiScreen);
                        }
                    } else if (InvTweaks.isValidInventory(container)) {
                        if (ContainerSection.CRAFTING_IN.equals((Object)target) || ContainerSection.CRAFTING_IN_PERSISTENT.equals((Object)target)) {
                            try {
                                new InvTweaksHandlerSorting(this.mc, this.cfgManager.getConfig(), target, SortingMethod.EVEN_STACKS, containerMgr.getSize(target) == 9 ? 3 : 2).sort();
                            }
                            catch (Exception e) {
                                this.logInGameError("invtweaks.sort.crafting.error", e);
                                e.printStackTrace();
                            }
                        } else {
                            this.handleSorting(guiScreen);
                        }
                    }
                }
            }
        } else {
            this.chestAlgorithmButtonDown = false;
        }
    }

    private void handleGUILayout(GuiContainer guiContainer) {
        InvTweaksConfig config = this.cfgManager.getConfig();
        Container container = guiContainer.field_147002_h;
        boolean isValidChest = InvTweaks.isValidChest(container);
        if (InvTweaks.showButtons(container) && !InvTweaks.isGuiEnchantmentTable(guiContainer)) {
            int w = 10;
            int h = 10;
            boolean isNEIEnabled = this.isNotEnoughItemsEnabled();
            boolean relayout = this.wasNEIEnabled != isNEIEnabled;
            this.wasNEIEnabled = isNEIEnabled;
            boolean customButtonsAdded = false;
            List controlList = guiContainer.field_146292_n;
            ArrayList<GuiButton> toRemove = new ArrayList<GuiButton>();
            for (Object o : controlList) {
                if (!InvTweaks.isGuiButton(o)) continue;
                GuiButton button = (GuiButton)o;
                if (button.field_146127_k < 54696386 || button.field_146127_k >= 54696390) continue;
                if (relayout) {
                    toRemove.add(button);
                    continue;
                }
                customButtonsAdded = true;
                break;
            }
            controlList.removeAll(toRemove);
            guiContainer.field_146292_n = controlList;
            if (!customButtonsAdded) {
                boolean customTextureAvailable = this.hasTexture(new ResourceLocation("inventorytweaks", "textures/gui/button10px.png"));
                if (!isValidChest) {
                    controlList.add(new InvTweaksGuiSettingsButton(this.cfgManager, 54696386, guiContainer.field_147003_i + guiContainer.field_146999_f - 15, guiContainer.field_147009_r + 5, w, h, "...", StatCollector.func_74838_a((String)"invtweaks.button.settings.tooltip"), customTextureAvailable));
                } else {
                    this.chestAlgorithmClickTimestamp = 0L;
                    int id = 54696386;
                    int x = guiContainer.field_147003_i + guiContainer.field_146999_f - 16;
                    int y = guiContainer.field_147009_r + 5;
                    boolean isChestWayTooBig = InvTweaks.isLargeChest(guiContainer.field_147002_h);
                    if (isChestWayTooBig && isNEIEnabled) {
                        x = guiContainer.field_147003_i + guiContainer.field_146999_f - 35;
                        y += 50;
                    }
                    controlList.add(new InvTweaksGuiSettingsButton(this.cfgManager, id++, isChestWayTooBig ? x + 22 : x - 1, isChestWayTooBig ? y - 3 : y, w, h, "...", StatCollector.func_74838_a((String)"invtweaks.button.settings.tooltip"), customTextureAvailable));
                    if (!config.getProperty("showChestButtons").equals("false")) {
                        int rowSize = this.getContainerRowSize(guiContainer);
                        InvTweaksGuiSortingButton button = new InvTweaksGuiSortingButton(this.cfgManager, id++, isChestWayTooBig ? x + 22 : x - 13, isChestWayTooBig ? y + 12 : y, w, h, "h", StatCollector.func_74838_a((String)"invtweaks.button.chest3.tooltip"), SortingMethod.HORIZONTAL, rowSize, customTextureAvailable);
                        controlList.add(button);
                        button = new InvTweaksGuiSortingButton(this.cfgManager, id++, isChestWayTooBig ? x + 22 : x - 25, isChestWayTooBig ? y + 25 : y, w, h, "v", StatCollector.func_74838_a((String)"invtweaks.button.chest2.tooltip"), SortingMethod.VERTICAL, rowSize, customTextureAvailable);
                        controlList.add(button);
                        button = new InvTweaksGuiSortingButton(this.cfgManager, id++, isChestWayTooBig ? x + 22 : x - 37, isChestWayTooBig ? y + 38 : y, w, h, "s", StatCollector.func_74838_a((String)"invtweaks.button.chest1.tooltip"), SortingMethod.DEFAULT, rowSize, customTextureAvailable);
                        controlList.add(button);
                    }
                }
            }
        } else if (InvTweaks.isGuiInventoryCreative(guiContainer)) {
            List controlList = guiContainer.field_146292_n;
            GuiButton buttonToRemove = null;
            for (Object o : controlList) {
                if (!InvTweaks.isGuiButton(o) || ((GuiButton)o).field_146127_k != 54696386) continue;
                buttonToRemove = (GuiButton)o;
                break;
            }
            if (buttonToRemove != null) {
                controlList.remove(buttonToRemove);
            }
        }
    }

    private boolean isNotEnoughItemsEnabled() {
        if (this.isNEILoaded) {
            if (this.neiHidden == null) {
                try {
                    this.neiClientConfig = Class.forName("codechicken.nei.NEIClientConfig");
                    this.neiHidden = this.neiClientConfig.getMethod("isHidden", new Class[0]);
                }
                catch (ClassNotFoundException e) {
                    return false;
                }
                catch (NoSuchMethodException e) {
                    return false;
                }
            }
            try {
                return (Boolean)this.neiHidden.invoke(null, new Object[0]) == false;
            }
            catch (IllegalAccessException e) {
                return false;
            }
            catch (InvocationTargetException e) {
                return false;
            }
        }
        return false;
    }

    private void handleShortcuts(GuiContainer guiScreen) {
        if (!InvTweaks.isValidChest(guiScreen.field_147002_h) && !InvTweaks.isValidInventory(guiScreen.field_147002_h)) {
            return;
        }
        if (Mouse.isButtonDown((int)0) || Mouse.isButtonDown((int)1)) {
            if (!this.mouseWasDown) {
                this.mouseWasDown = true;
                if (this.cfgManager.getConfig().getProperty("enableShortcuts").equals("true")) {
                    this.cfgManager.getShortcutsHandler().handleShortcut();
                }
            }
        } else {
            this.mouseWasDown = false;
        }
    }

    private int getItemOrder(ItemStack itemStack) {
        List<IItemTreeItem> items = this.cfgManager.getConfig().getTree().getItems(Item.field_150901_e.func_148750_c((Object)itemStack.func_77973_b()), itemStack.func_77960_j());
        return items != null && items.size() > 0 ? items.get(0).getOrder() : Integer.MAX_VALUE;
    }

    private int getContainerRowSize(GuiContainer guiContainer) {
        return InvTweaks.getSpecialChestRowSize(guiContainer.field_147002_h);
    }

    private boolean isSortingShortcutDown() {
        if (this.sortKeyEnabled && !this.textboxMode) {
            int keyCode = this.cfgManager.getConfig().getSortKeyCode();
            if (keyCode > 0) {
                return Keyboard.isKeyDown((int)keyCode);
            }
            return Mouse.isButtonDown((int)(100 + keyCode));
        }
        return false;
    }

    private boolean isTimeForPolling() {
        if (this.tickNumber - this.lastPollingTickNumber >= 3) {
            this.lastPollingTickNumber = this.tickNumber;
        }
        return this.tickNumber - this.lastPollingTickNumber == 0;
    }

    private void unlockKeysIfNecessary() {
        boolean hasFocus = Display.isActive();
        if (!this.hadFocus && hasFocus) {
            Keyboard.destroy();
            boolean firstTry = true;
            while (!Keyboard.isCreated()) {
                try {
                    Keyboard.create();
                }
                catch (LWJGLException e) {
                    if (!firstTry) continue;
                    this.logInGameError("invtweaks.keyboardfix.error", (Exception)((Object)e));
                    firstTry = false;
                }
            }
            if (!firstTry) {
                this.logInGame("invtweaks.keyboardfix.recover");
            }
        }
        this.hadFocus = hasFocus;
    }

    private void cloneHotbar() {
        ItemStack[] mainInventory = this.getMainInventory();
        for (int i = 0; i < 9; ++i) {
            this.hotbarClone[i] = mainInventory[i] != null ? mainInventory[i].func_77946_l() : null;
        }
    }

    private void playClick() {
        if (!this.cfgManager.getConfig().getProperty("enableSounds").equals("false")) {
            this.mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a((ResourceLocation)new ResourceLocation("gui.button.press"), (float)1.0f));
        }
    }

    private String buildlogString(Level level, String message, Exception e) {
        if (e != null) {
            StackTraceElement exceptionLine = e.getStackTrace()[0];
            if (exceptionLine != null && exceptionLine.getFileName() != null) {
                return this.buildlogString(level, message) + ": " + e.getMessage() + " (l" + exceptionLine.getLineNumber() + " in " + exceptionLine.getFileName().replace("InvTweaks", "") + ")";
            }
            return this.buildlogString(level, message) + ": " + e.getMessage();
        }
        return this.buildlogString(level, message);
    }

    private String buildlogString(Level level, String message) {
        return "InvTweaks: " + (level.equals(Level.SEVERE) ? "[ERROR] " : "") + message;
    }
}

