/*
 * Decompiled with CFR 0.152.
 */
package invtweaks;

import invtweaks.InvTweaksObfuscation;
import invtweaks.api.IItemTreeItem;
import org.apache.commons.lang3.ObjectUtils;

public class InvTweaksItemTreeItem
implements IItemTreeItem {
    private String name;
    private String id;
    private int damage;
    private int order;

    public InvTweaksItemTreeItem(String name, String id, int damage, int order) {
        this.name = name;
        this.id = InvTweaksObfuscation.getNamespacedID(id);
        this.damage = damage;
        this.order = order;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public int getOrder() {
        return this.order;
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof IItemTreeItem)) {
            return false;
        }
        IItemTreeItem item = (IItemTreeItem)o;
        return ObjectUtils.equals(this.id, item.getId()) && (this.damage == Short.MAX_VALUE || this.damage == item.getDamage());
    }

    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(IItemTreeItem item) {
        return item.getOrder() - this.getOrder();
    }
}

