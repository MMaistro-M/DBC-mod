/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.oredict.OreDictionary
 *  net.minecraftforge.oredict.OreDictionary$OreRegisterEvent
 *  org.apache.logging.log4j.Logger
 */
package invtweaks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import invtweaks.InvTweaks;
import invtweaks.InvTweaksItemTreeCategory;
import invtweaks.InvTweaksItemTreeItem;
import invtweaks.api.IItemTree;
import invtweaks.api.IItemTreeCategory;
import invtweaks.api.IItemTreeItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.Logger;

public class InvTweaksItemTree
implements IItemTree {
    public static final int MAX_CATEGORY_RANGE = 1000;
    public static final String UNKNOWN_ITEM = "unknown";
    private static final Logger log = InvTweaks.log;
    private Map<String, IItemTreeCategory> categories = new HashMap<String, IItemTreeCategory>();
    private Map<String, Vector<IItemTreeItem>> itemsById = new HashMap<String, Vector<IItemTreeItem>>(500);
    private static Vector<IItemTreeItem> defaultItems = null;
    private Map<String, Vector<IItemTreeItem>> itemsByName = new HashMap<String, Vector<IItemTreeItem>>(500);
    private String rootCategory;
    private List<OreDictInfo> oresRegistered = new ArrayList<OreDictInfo>();

    public InvTweaksItemTree() {
        this.reset();
    }

    public void reset() {
        if (defaultItems == null) {
            defaultItems = new Vector();
            defaultItems.add(new InvTweaksItemTreeItem(UNKNOWN_ITEM, null, Short.MAX_VALUE, Integer.MAX_VALUE));
        }
        this.categories.clear();
        this.itemsByName.clear();
        this.itemsById.clear();
    }

    @Override
    public boolean matches(List<IItemTreeItem> items, String keyword) {
        if (items == null) {
            return false;
        }
        for (IItemTreeItem item : items) {
            if (item.getName() == null || !item.getName().equals(keyword)) continue;
            return true;
        }
        IItemTreeCategory category = this.getCategory(keyword);
        if (category != null) {
            for (IItemTreeItem item : items) {
                if (!category.contains(item)) continue;
                return true;
            }
        }
        return keyword.equals(this.rootCategory);
    }

    @Override
    public int getKeywordDepth(String keyword) {
        try {
            return this.getRootCategory().findKeywordDepth(keyword);
        }
        catch (NullPointerException e) {
            log.error("The root category is missing: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int getKeywordOrder(String keyword) {
        List<IItemTreeItem> items = this.getItems(keyword);
        if (items != null && items.size() != 0) {
            return items.get(0).getOrder();
        }
        try {
            return this.getRootCategory().findCategoryOrder(keyword);
        }
        catch (NullPointerException e) {
            log.error("The root category is missing: " + e.getMessage());
            return -1;
        }
    }

    @Override
    public boolean isKeywordValid(String keyword) {
        if (this.containsItem(keyword)) {
            return true;
        }
        IItemTreeCategory category = this.getCategory(keyword);
        return category != null;
    }

    @Override
    public Collection<IItemTreeCategory> getAllCategories() {
        return this.categories.values();
    }

    @Override
    public IItemTreeCategory getRootCategory() {
        return this.categories.get(this.rootCategory);
    }

    @Override
    public IItemTreeCategory getCategory(String keyword) {
        return this.categories.get(keyword);
    }

    @Override
    public boolean isItemUnknown(String id, int damage) {
        return this.itemsById.get(id) == null;
    }

    @Override
    public List<IItemTreeItem> getItems(String id, int damage) {
        if (id == null) {
            return new ArrayList<IItemTreeItem>();
        }
        List items = this.itemsById.get(id);
        ArrayList<IItemTreeItem> filteredItems = new ArrayList<IItemTreeItem>();
        if (items != null) {
            filteredItems.addAll(items);
        }
        if (items != null && !items.isEmpty()) {
            for (IItemTreeItem item : items) {
                if (item.getDamage() == Short.MAX_VALUE || item.getDamage() == damage) continue;
                filteredItems.remove(item);
            }
        }
        if (filteredItems.isEmpty()) {
            InvTweaksItemTreeItem newItemId = new InvTweaksItemTreeItem(String.format("%s-%d", id, damage), id, damage, 5000 + damage);
            InvTweaksItemTreeItem newItemDamage = new InvTweaksItemTreeItem(id, id, Short.MAX_VALUE, 5000);
            this.addItem(this.getRootCategory().getName(), newItemId);
            this.addItem(this.getRootCategory().getName(), newItemDamage);
            filteredItems.add(newItemId);
            filteredItems.add(newItemDamage);
        }
        Iterator it = filteredItems.iterator();
        while (it.hasNext()) {
            if (it.next() != null) continue;
            it.remove();
        }
        return filteredItems;
    }

    @Override
    public List<IItemTreeItem> getItems(String name) {
        return this.itemsByName.get(name);
    }

    @Override
    public IItemTreeItem getRandomItem(Random r) {
        return (IItemTreeItem)this.itemsByName.values().toArray()[r.nextInt(this.itemsByName.size())];
    }

    @Override
    public boolean containsItem(String name) {
        return this.itemsByName.containsKey(name);
    }

    @Override
    public boolean containsCategory(String name) {
        return this.categories.containsKey(name);
    }

    @Override
    public void setRootCategory(IItemTreeCategory category) {
        this.rootCategory = category.getName();
        this.categories.put(this.rootCategory, category);
    }

    @Override
    public IItemTreeCategory addCategory(String parentCategory, String newCategory) throws NullPointerException {
        InvTweaksItemTreeCategory addedCategory = new InvTweaksItemTreeCategory(newCategory);
        this.addCategory(parentCategory, addedCategory);
        return addedCategory;
    }

    @Override
    public IItemTreeItem addItem(String parentCategory, String name, String id, int damage, int order) throws NullPointerException {
        InvTweaksItemTreeItem addedItem = new InvTweaksItemTreeItem(name, id, damage, order);
        this.addItem(parentCategory, addedItem);
        return addedItem;
    }

    @Override
    public void addCategory(String parentCategory, IItemTreeCategory newCategory) throws NullPointerException {
        this.categories.get(parentCategory.toLowerCase()).addCategory(newCategory);
        this.categories.put(newCategory.getName(), newCategory);
    }

    @Override
    public void addItem(String parentCategory, IItemTreeItem newItem) throws NullPointerException {
        Vector<IItemTreeItem> list;
        this.categories.get(parentCategory.toLowerCase()).addItem(newItem);
        if (this.itemsByName.containsKey(newItem.getName())) {
            this.itemsByName.get(newItem.getName()).add(newItem);
        } else {
            list = new Vector<IItemTreeItem>();
            list.add(newItem);
            this.itemsByName.put(newItem.getName(), list);
        }
        if (this.itemsById.containsKey(newItem.getId())) {
            this.itemsById.get(newItem.getId()).add(newItem);
        } else {
            list = new Vector();
            list.add(newItem);
            this.itemsById.put(newItem.getId(), list);
        }
    }

    private void log(IItemTreeCategory category, int indentLevel) {
        String logIdent = "";
        for (int i = 0; i < indentLevel; ++i) {
            logIdent = logIdent + "  ";
        }
        log.info(logIdent + category.getName());
        for (IItemTreeCategory iItemTreeCategory : category.getSubCategories()) {
            this.log(iItemTreeCategory, indentLevel + 1);
        }
        for (List list : category.getItems()) {
            for (IItemTreeItem item : list) {
                log.info(logIdent + "  " + item + " " + item.getId() + " " + item.getDamage());
            }
        }
    }

    @Override
    public void registerOre(String category, String name, String oreName, int order) {
        for (ItemStack i : OreDictionary.getOres((String)oreName)) {
            if (i != null) {
                this.addItem(category, new InvTweaksItemTreeItem(name, Item.field_150901_e.func_148750_c((Object)i.func_77973_b()), i.func_77960_j(), order));
                continue;
            }
            log.warn(String.format("An OreDictionary entry for %s is null", oreName));
        }
        this.oresRegistered.add(new OreDictInfo(category, name, oreName, order));
    }

    @SubscribeEvent
    public void oreRegistered(OreDictionary.OreRegisterEvent ev) {
        for (OreDictInfo ore : this.oresRegistered) {
            if (!ore.oreName.equals(ev.Name)) continue;
            if (ev.Ore.func_77973_b() != null) {
                this.addItem(ore.category, new InvTweaksItemTreeItem(ore.name, Item.field_150901_e.func_148750_c((Object)ev.Ore.func_77973_b()), ev.Ore.func_77960_j(), ore.order));
                continue;
            }
            log.warn(String.format("An OreDictionary entry for %s is null", ev.Name));
        }
    }

    private class OreDictInfo {
        String category;
        String name;
        String oreName;
        int order;

        OreDictInfo(String category, String name, String oreName, int order) {
            this.category = category;
            this.name = name;
            this.oreName = oreName;
            this.order = order;
        }
    }
}

