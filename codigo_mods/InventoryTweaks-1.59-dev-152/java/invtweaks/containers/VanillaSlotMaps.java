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

public class VanillaSlotMaps {
    public static Map<ContainerSection, List<Slot>> containerPlayerSlots(Container container) {
        HashMap<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
        slotRefs.put(ContainerSection.CRAFTING_OUT, container.field_75151_b.subList(0, 1));
        slotRefs.put(ContainerSection.CRAFTING_IN, container.field_75151_b.subList(1, 5));
        slotRefs.put(ContainerSection.ARMOR, container.field_75151_b.subList(5, 9));
        return slotRefs;
    }

    public static Map<ContainerSection, List<Slot>> containerChestDispenserSlots(Container container) {
        HashMap<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
        slotRefs.put(ContainerSection.CHEST, container.field_75151_b.subList(0, container.field_75151_b.size() - 36));
        return slotRefs;
    }

    public static Map<ContainerSection, List<Slot>> containerFurnaceSlots(Container container) {
        HashMap<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
        slotRefs.put(ContainerSection.FURNACE_IN, container.field_75151_b.subList(0, 1));
        slotRefs.put(ContainerSection.FURNACE_FUEL, container.field_75151_b.subList(1, 2));
        slotRefs.put(ContainerSection.FURNACE_OUT, container.field_75151_b.subList(2, 3));
        return slotRefs;
    }

    public static Map<ContainerSection, List<Slot>> containerWorkbenchSlots(Container container) {
        HashMap<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
        slotRefs.put(ContainerSection.CRAFTING_OUT, container.field_75151_b.subList(0, 1));
        slotRefs.put(ContainerSection.CRAFTING_IN, container.field_75151_b.subList(1, 10));
        return slotRefs;
    }

    public static Map<ContainerSection, List<Slot>> containerEnchantmentSlots(Container container) {
        HashMap<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
        slotRefs.put(ContainerSection.ENCHANTMENT, container.field_75151_b.subList(0, 1));
        return slotRefs;
    }

    public static Map<ContainerSection, List<Slot>> containerBrewingSlots(Container container) {
        HashMap<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
        slotRefs.put(ContainerSection.BREWING_BOTTLES, container.field_75151_b.subList(0, 3));
        slotRefs.put(ContainerSection.BREWING_INGREDIENT, container.field_75151_b.subList(3, 4));
        return slotRefs;
    }

    public static Map<ContainerSection, List<Slot>> unknownContainerSlots(Container container) {
        HashMap<ContainerSection, List<Slot>> slotRefs = new HashMap<ContainerSection, List<Slot>>();
        int size = container.field_75151_b.size();
        if (size >= 36) {
            slotRefs.put(ContainerSection.CHEST, container.field_75151_b.subList(0, size - 36));
        } else {
            slotRefs.put(ContainerSection.CHEST, container.field_75151_b.subList(0, size));
        }
        return slotRefs;
    }
}

