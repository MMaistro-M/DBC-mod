/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package invtweaks;

import invtweaks.InvTweaksContainerManager;
import invtweaks.api.container.ContainerSection;
import java.util.List;
import java.util.concurrent.TimeoutException;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class InvTweaksContainerSectionManager {
    private InvTweaksContainerManager containerMgr;
    private ContainerSection section;

    public InvTweaksContainerSectionManager(Minecraft mc, ContainerSection section) throws Exception {
        this(new InvTweaksContainerManager(mc), section);
    }

    public void setClickDelay(int delay) {
        this.containerMgr.setClickDelay(delay);
    }

    public InvTweaksContainerSectionManager(InvTweaksContainerManager manager, ContainerSection section) throws Exception {
        this.containerMgr = manager;
        this.section = section;
        if (!this.containerMgr.hasSection(section)) {
            throw new Exception("Section not available");
        }
    }

    public boolean move(int srcIndex, int destIndex) {
        return this.containerMgr.move(this.section, srcIndex, this.section, destIndex);
    }

    public boolean moveSome(int srcIndex, int destIndex, int amount) {
        return this.containerMgr.moveSome(this.section, srcIndex, this.section, destIndex, amount);
    }

    public boolean drop(int srcIndex) throws TimeoutException {
        return this.containerMgr.drop(this.section, srcIndex);
    }

    public boolean dropSome(int srcIndex, int amount) throws TimeoutException {
        return this.containerMgr.dropSome(this.section, srcIndex, amount);
    }

    public boolean putHoldItemDown(int destIndex) throws TimeoutException {
        return this.containerMgr.putHoldItemDown(this.section, destIndex);
    }

    public void leftClick(int index) throws TimeoutException {
        this.containerMgr.leftClick(this.section, index);
    }

    public void rightClick(int index) throws TimeoutException {
        this.containerMgr.rightClick(this.section, index);
    }

    public void click(int index, boolean rightClick) throws TimeoutException {
        this.containerMgr.click(this.section, index, rightClick);
    }

    public List<Slot> getSlots() {
        return this.containerMgr.getSlots(this.section);
    }

    public int getSize() {
        return this.containerMgr.getSize(this.section);
    }

    public int getFirstEmptyIndex() {
        return this.containerMgr.getFirstEmptyIndex(this.section);
    }

    public boolean isSlotEmpty(int slot) {
        return this.containerMgr.isSlotEmpty(this.section, slot);
    }

    public Slot getSlot(int index) {
        return this.containerMgr.getSlot(this.section, index);
    }

    public int getSlotIndex(int slotNumber) {
        if (this.isSlotInSection(slotNumber)) {
            return this.containerMgr.getSlotIndex(slotNumber);
        }
        return -1;
    }

    public boolean isSlotInSection(int slotNumber) {
        return this.containerMgr.getSlotSection(slotNumber) == this.section;
    }

    public ItemStack getItemStack(int index) throws NullPointerException, IndexOutOfBoundsException {
        return this.containerMgr.getItemStack(this.section, index);
    }

    public Container getContainer() {
        return this.containerMgr.getContainer();
    }
}

