/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.apache.logging.log4j.Logger
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksConfig;
import invtweaks.InvTweaksConfigSortingRule;
import invtweaks.InvTweaksConfigSortingRuleType;
import invtweaks.InvTweaksContainerSectionManager;
import invtweaks.InvTweaksItemTree;
import invtweaks.InvTweaksObfuscation;
import invtweaks.api.IItemTreeItem;
import invtweaks.api.container.ContainerSection;
import invtweaks.forge.InvTweaksMod;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

public class InvTweaksHandlerAutoRefill
extends InvTweaksObfuscation {
    private static final Logger log = InvTweaks.log;
    private InvTweaksConfig config = null;

    public InvTweaksHandlerAutoRefill(Minecraft mc, InvTweaksConfig config) {
        super(mc);
        this.setConfig(config);
    }

    public void setConfig(InvTweaksConfig config) {
        this.config = config;
    }

    public void autoRefillSlot(int slot, String wantedId, int wantedDamage) throws Exception {
        InvTweaksContainerSectionManager container = new InvTweaksContainerSectionManager(this.mc, ContainerSection.INVENTORY);
        ItemStack replacementStack = null;
        int replacementStackSlot = -1;
        boolean refillBeforeBreak = this.config.getProperty("autoRefillBeforeBreak").equals("true");
        boolean hasSubtypes = false;
        Item original = (Item)Item.field_150901_e.func_82594_a(wantedId);
        if (original != null) {
            hasSubtypes = original.func_77614_k();
        }
        ArrayList<InvTweaksConfigSortingRule> matchingRules = new ArrayList<InvTweaksConfigSortingRule>();
        Vector<InvTweaksConfigSortingRule> rules = this.config.getRules();
        InvTweaksItemTree tree = this.config.getTree();
        if (!tree.isItemUnknown(wantedId, wantedDamage)) {
            List<IItemTreeItem> items = tree.getItems(wantedId, wantedDamage);
            for (IItemTreeItem item : items) {
                if (hasSubtypes && item.getDamage() != wantedDamage && item.getDamage() != Short.MAX_VALUE) continue;
                matchingRules.add(new InvTweaksConfigSortingRule(tree, "D" + (slot - 26), item.getName(), 36, 9));
            }
            block1: for (InvTweaksConfigSortingRule rule : rules) {
                if (rule.getType() != InvTweaksConfigSortingRuleType.SLOT && rule.getType() != InvTweaksConfigSortingRuleType.COLUMN) continue;
                for (int preferredSlot : rule.getPreferredSlots()) {
                    if (slot != preferredSlot) continue;
                    matchingRules.add(rule);
                    continue block1;
                }
            }
            for (InvTweaksConfigSortingRule rule : matchingRules) {
                for (int i = 0; i < 36; ++i) {
                    List<IItemTreeItem> candidateItems;
                    ItemStack candidateStack = container.getItemStack(i);
                    if (candidateStack == null || !tree.matches(candidateItems = tree.getItems(Item.field_150901_e.func_148750_c((Object)candidateStack.func_77973_b()), candidateStack.func_77960_j()), rule.getKeyword())) continue;
                    if (candidateStack.func_77976_d() == 1) {
                        if (replacementStack != null && candidateStack.func_77960_j() <= replacementStack.func_77960_j() || refillBeforeBreak && candidateStack.func_77958_k() - candidateStack.func_77960_j() <= this.config.getIntProperty("autoRefillDamageThreshhold")) continue;
                        replacementStack = candidateStack;
                        replacementStackSlot = i;
                        continue;
                    }
                    if (replacementStack != null && candidateStack.field_77994_a >= replacementStack.field_77994_a) continue;
                    replacementStack = candidateStack;
                    replacementStackSlot = i;
                }
            }
        } else {
            for (int i = 0; i < 36; ++i) {
                ItemStack candidateStack = container.getItemStack(i);
                if (candidateStack == null || !ObjectUtils.equals(Item.field_150901_e.func_148750_c((Object)candidateStack.func_77973_b()), wantedId) || candidateStack.func_77960_j() != wantedDamage) continue;
                replacementStack = candidateStack;
                replacementStackSlot = i;
                break;
            }
        }
        if (replacementStack != null || refillBeforeBreak && container.getSlot(slot).func_75211_c() != null) {
            log.info("Automatic stack replacement.");
            InvTweaks.getInstance().addScheduledTask(this.mc.field_71441_e.func_82737_E() + 1L, new Runnable(){
                private InvTweaksContainerSectionManager containerMgr;
                private int targetedSlot;
                private int i;
                private String expectedItemId;
                private boolean refillBeforeBreak;

                public Runnable init(Minecraft mc, int i, int currentItem, boolean refillBeforeBreak) throws Exception {
                    this.containerMgr = new InvTweaksContainerSectionManager(mc, ContainerSection.INVENTORY);
                    this.targetedSlot = currentItem;
                    if (i != -1) {
                        this.i = i;
                        this.expectedItemId = Item.field_150901_e.func_148750_c((Object)this.containerMgr.getItemStack(i).func_77973_b());
                    } else {
                        this.i = this.containerMgr.getFirstEmptyIndex();
                        this.expectedItemId = null;
                    }
                    this.refillBeforeBreak = refillBeforeBreak;
                    return this;
                }

                @Override
                public void run() {
                    ItemStack stack = this.containerMgr.getItemStack(this.i);
                    if (stack != null && StringUtils.equals(Item.field_150901_e.func_148750_c((Object)stack.func_77973_b()), this.expectedItemId) || this.refillBeforeBreak) {
                        if (this.containerMgr.move(this.targetedSlot, this.i) || this.containerMgr.move(this.i, this.targetedSlot)) {
                            if (!InvTweaksHandlerAutoRefill.this.config.getProperty("enableSounds").equals("false")) {
                                InvTweaksHandlerAutoRefill.this.mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a((ResourceLocation)new ResourceLocation("mob.chicken.plop"), (float)1.0f));
                            }
                            if (this.containerMgr.getItemStack(this.i) != null && this.i >= 27) {
                                for (int j = 0; j < 36; ++j) {
                                    if (this.containerMgr.getItemStack(j) != null) continue;
                                    this.containerMgr.move(this.i, j);
                                    break;
                                }
                            }
                            InvTweaksMod.proxy.sortComplete();
                        } else {
                            log.warn("Failed to move stack for autoreplace, despite of prior tests.");
                        }
                    }
                }
            }.init(this.mc, replacementStackSlot, slot, refillBeforeBreak));
        }
    }

    private static void trySleep(int delay) {
        try {
            Thread.sleep(delay);
        }
        catch (InterruptedException interruptedException) {
            // empty catch block
        }
    }
}

