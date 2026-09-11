/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 */
package invtweaks.containers;

import invtweaks.api.container.ContainerSection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class CompatibilitySlotMaps {
    public static Map<ContainerSection, List<Slot>> ee3PortableCraftingSlots(Container container) {
        HashMap<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
        slotRefs.put(ContainerSection.CRAFTING_OUT, container.field_75151_b.subList(0, 1));
        slotRefs.put(ContainerSection.CRAFTING_IN, container.field_75151_b.subList(1, 10));
        return slotRefs;
    }

    public static Map<ContainerSection, List<Slot>> galacticraftPlayerSlots(Container container) {
        HashMap<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
        slotRefs.put(ContainerSection.CRAFTING_OUT, container.field_75151_b.subList(0, 1));
        slotRefs.put(ContainerSection.CRAFTING_IN, container.field_75151_b.subList(1, 5));
        slotRefs.put(ContainerSection.ARMOR, container.field_75151_b.subList(5, 9));
        slotRefs.put(ContainerSection.INVENTORY, container.field_75151_b.subList(9, 45));
        slotRefs.put(ContainerSection.INVENTORY_NOT_HOTBAR, container.field_75151_b.subList(9, 36));
        slotRefs.put(ContainerSection.INVENTORY_HOTBAR, container.field_75151_b.subList(36, 45));
        return slotRefs;
    }
}

