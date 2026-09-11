/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package invtweaks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import invtweaks.InvTweaks;
import invtweaks.InvTweaksObfuscation;
import invtweaks.api.container.ContainerSection;
import invtweaks.forge.InvTweaksMod;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class InvTweaksContainerManager {
    public static final int DROP_SLOT = -999;
    public static final int HOTBAR_SIZE = 9;
    private GuiContainer guiContainer;
    private Container container;
    private Map<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
    private int clickDelay = 0;

    @SideOnly(value=Side.CLIENT)
    public InvTweaksContainerManager(Minecraft mc) {
        GuiScreen currentScreen = mc.field_71462_r;
        if (currentScreen instanceof GuiContainer) {
            this.guiContainer = (GuiContainer)currentScreen;
            this.container = this.guiContainer.field_147002_h;
        } else {
            this.container = mc.field_71439_g.field_71069_bz;
        }
        this.initSlots();
    }

    public InvTweaksContainerManager(Container cont, GuiContainer gui) {
        this.guiContainer = gui;
        this.container = cont;
        this.initSlots();
    }

    private void initSlots() {
        List slots;
        int size;
        this.slotRefs = InvTweaksObfuscation.getContainerSlotMap(this.container);
        if (this.slotRefs == null) {
            this.slotRefs = new HashMap<ContainerSection, List<Slot>>();
        }
        if ((size = (slots = this.container.field_75151_b).size()) >= 36 && !this.slotRefs.containsKey((Object)ContainerSection.INVENTORY)) {
            this.slotRefs.put(ContainerSection.INVENTORY, slots.subList(size - 36, size));
            this.slotRefs.put(ContainerSection.INVENTORY_NOT_HOTBAR, slots.subList(size - 36, size - 9));
            this.slotRefs.put(ContainerSection.INVENTORY_HOTBAR, slots.subList(size - 9, size));
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean move(ContainerSection srcSection, int srcIndex, ContainerSection destSection, int destIndex) {
        Slot destSlot;
        ItemStack srcStack = this.getItemStack(srcSection, srcIndex);
        ItemStack destStack = this.getItemStack(destSection, destIndex);
        if (srcStack == null && destIndex != -999) {
            return false;
        }
        if (srcSection == destSection && srcIndex == destIndex) {
            return true;
        }
        if (destIndex != -999 && !(destSlot = this.getSlot(destSection, destIndex)).func_75214_a(srcStack)) {
            return false;
        }
        if (InvTweaks.getInstance().getHeldStack() != null) {
            int firstEmptyIndex = this.getFirstEmptyIndex(ContainerSection.INVENTORY);
            if (firstEmptyIndex == -1) return false;
            this.leftClick(ContainerSection.INVENTORY, firstEmptyIndex);
        }
        if (destStack != null && srcStack.func_77973_b() == destStack.func_77973_b() && (srcStack.func_77976_d() == 1 || srcStack.func_77942_o() || destStack.func_77942_o())) {
            int intermediateSlot = this.getFirstEmptyUsableSlotNumber();
            ContainerSection intermediateSection = this.getSlotSection(intermediateSlot);
            int intermediateIndex = this.getSlotIndex(intermediateSlot);
            if (intermediateIndex == -1) return false;
            Slot interSlot = this.getSlot(intermediateSection, intermediateIndex);
            if (!interSlot.func_75214_a(destStack)) {
                return false;
            }
            Slot srcSlot = this.getSlot(srcSection, srcIndex);
            if (!srcSlot.func_75214_a(destStack)) {
                return false;
            }
            this.leftClick(destSection, destIndex);
            this.leftClick(intermediateSection, intermediateIndex);
            this.leftClick(srcSection, srcIndex);
            this.leftClick(destSection, destIndex);
            this.leftClick(intermediateSection, intermediateIndex);
            this.leftClick(srcSection, srcIndex);
            return true;
        } else {
            this.leftClick(srcSection, srcIndex);
            this.leftClick(destSection, destIndex);
            if (InvTweaks.getInstance().getHeldStack() == null) return true;
            Slot srcSlot = this.getSlot(srcSection, srcIndex);
            if (srcSlot.func_75214_a(InvTweaks.getInstance().getHeldStack())) {
                this.leftClick(srcSection, srcIndex);
                return true;
            } else {
                int firstEmptyIndex = this.getFirstEmptyIndex(ContainerSection.INVENTORY);
                if (firstEmptyIndex == -1) return true;
                this.leftClick(ContainerSection.INVENTORY, firstEmptyIndex);
            }
        }
        return true;
    }

    public boolean moveSome(ContainerSection srcSection, int srcIndex, ContainerSection destSection, int destIndex, int amount) {
        ItemStack source = this.getItemStack(srcSection, srcIndex);
        if (source == null || srcSection == destSection && srcIndex == destIndex) {
            return true;
        }
        ItemStack destination = this.getItemStack(srcSection, srcIndex);
        int sourceSize = source.field_77994_a;
        int movedAmount = Math.min(amount, sourceSize);
        if (destination == null || InvTweaksObfuscation.areItemStacksEqual(source, destination)) {
            this.leftClick(srcSection, srcIndex);
            for (int i = 0; i < movedAmount; ++i) {
                this.rightClick(destSection, destIndex);
            }
            if (movedAmount < sourceSize) {
                this.leftClick(srcSection, srcIndex);
            }
            return true;
        }
        return false;
    }

    public boolean drop(ContainerSection srcSection, int srcIndex) {
        return this.move(srcSection, srcIndex, null, -999);
    }

    public boolean dropSome(ContainerSection srcSection, int srcIndex, int amount) {
        return this.moveSome(srcSection, srcIndex, null, -999, amount);
    }

    public boolean putHoldItemDown(ContainerSection destSection, int destIndex) {
        ItemStack heldStack = InvTweaks.getInstance().getHeldStack();
        if (heldStack != null) {
            if (this.getItemStack(destSection, destIndex) == null) {
                this.click(destSection, destIndex, false);
                return true;
            }
            return false;
        }
        return true;
    }

    public void leftClick(ContainerSection section, int index) {
        this.click(section, index, false);
    }

    public void rightClick(ContainerSection section, int index) {
        this.click(section, index, true);
    }

    public void click(ContainerSection section, int index, boolean rightClick) {
        int slot = this.indexToSlot(section, index);
        if (slot != -1) {
            int data = rightClick ? 1 : 0;
            InvTweaksMod.proxy.slotClick(InvTweaks.getInstance().getPlayerController(), this.container.field_75152_c, slot, data, 0, InvTweaks.getInstance().getThePlayer());
        }
        if (this.clickDelay > 0) {
            try {
                Thread.sleep(this.clickDelay);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean hasSection(ContainerSection section) {
        return this.slotRefs.containsKey((Object)section);
    }

    public List<Slot> getSlots(ContainerSection section) {
        return this.slotRefs.get((Object)section);
    }

    public int getSize() {
        int result = 0;
        for (List<Slot> slots : this.slotRefs.values()) {
            result += slots.size();
        }
        return result;
    }

    public int getSize(ContainerSection section) {
        if (this.hasSection(section)) {
            return this.slotRefs.get((Object)section).size();
        }
        return 0;
    }

    public int getFirstEmptyIndex(ContainerSection section) {
        int i = 0;
        for (Slot slot : this.slotRefs.get((Object)section)) {
            if (!slot.func_75216_d()) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    public boolean isSlotEmpty(ContainerSection section, int slot) {
        if (this.hasSection(section)) {
            return this.getItemStack(section, slot) == null;
        }
        return false;
    }

    public Slot getSlot(ContainerSection section, int index) {
        List<Slot> slots = this.slotRefs.get((Object)section);
        if (slots != null) {
            return slots.get(index);
        }
        return null;
    }

    public int getSlotIndex(int slotNumber) {
        return this.getSlotIndex(slotNumber, false);
    }

    public int getSlotIndex(int slotNumber, boolean preferInventory) {
        for (ContainerSection section : this.slotRefs.keySet()) {
            if ((preferInventory || section == ContainerSection.INVENTORY) && (!preferInventory || section == ContainerSection.INVENTORY_NOT_HOTBAR || section == ContainerSection.INVENTORY_HOTBAR)) continue;
            int i = 0;
            for (Slot slot : this.slotRefs.get((Object)section)) {
                if (InvTweaksObfuscation.getSlotNumber(slot) == slotNumber) {
                    return i;
                }
                ++i;
            }
        }
        return -1;
    }

    public ContainerSection getSlotSection(int slotNumber) {
        for (ContainerSection section : this.slotRefs.keySet()) {
            if (section == ContainerSection.INVENTORY) continue;
            for (Slot slot : this.slotRefs.get((Object)section)) {
                if (InvTweaksObfuscation.getSlotNumber(slot) != slotNumber) continue;
                return section;
            }
        }
        return null;
    }

    public ItemStack getItemStack(ContainerSection section, int index) throws NullPointerException, IndexOutOfBoundsException {
        int slot = this.indexToSlot(section, index);
        if (slot >= 0 && slot < this.container.field_75151_b.size()) {
            return InvTweaksObfuscation.getSlotStack(this.container, slot);
        }
        return null;
    }

    public Container getContainer() {
        return this.container;
    }

    private int getFirstEmptyUsableSlotNumber() {
        for (ContainerSection section : this.slotRefs.keySet()) {
            for (Slot slot : this.slotRefs.get((Object)section)) {
                if (!InvTweaksObfuscation.isBasicSlot(slot) || slot.func_75216_d()) continue;
                return InvTweaksObfuscation.getSlotNumber(slot);
            }
        }
        return -1;
    }

    private int indexToSlot(ContainerSection section, int index) {
        if (index == -999) {
            return -999;
        }
        if (this.hasSection(section)) {
            Slot slot = this.slotRefs.get((Object)section).get(index);
            if (slot != null) {
                return InvTweaksObfuscation.getSlotNumber(slot);
            }
            return -1;
        }
        return -1;
    }

    public void setClickDelay(int delay) {
        this.clickDelay = delay;
    }
}

