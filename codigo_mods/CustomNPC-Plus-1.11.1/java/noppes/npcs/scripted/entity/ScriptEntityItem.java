/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.scripted.entity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import noppes.npcs.api.entity.IEntityItem;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.entity.ScriptEntity;

public class ScriptEntityItem<T extends EntityItem>
extends ScriptEntity<T>
implements IEntityItem {
    private final T entity;

    public ScriptEntityItem(T entityItem) {
        super(entityItem);
        this.entity = entityItem;
    }

    @Override
    public String getOwner() {
        return this.entity.func_145798_i();
    }

    @Override
    public void setOwner(String name) {
        this.entity.func_145797_a(name);
    }

    @Override
    public String getThrower() {
        return this.entity.func_145800_j();
    }

    @Override
    public void setThrower(String name) {
        this.entity.func_145799_b(name);
    }

    @Override
    public int getPickupDelay() {
        return ((EntityItem)this.entity).field_145804_b;
    }

    @Override
    public void setPickupDelay(int delay) {
        ((EntityItem)this.entity).field_145804_b = delay;
    }

    @Override
    public int getType() {
        return 6;
    }

    @Override
    public long getAge() {
        return ((EntityItem)this.entity).field_70292_b;
    }

    @Override
    public void setAge(long age) {
        age = Math.max(Math.min(age, Integer.MAX_VALUE), Integer.MIN_VALUE);
        ((EntityItem)this.entity).field_70292_b = (int)age;
    }

    @Override
    public int getLifeSpawn() {
        return ((EntityItem)this.entity).lifespan;
    }

    @Override
    public void setLifeSpawn(int age) {
        ((EntityItem)this.entity).lifespan = age;
    }

    @Override
    public IItemStack getItem() {
        return NpcAPI.Instance().getIItemStack(this.entity.func_92059_d());
    }

    @Override
    public void setItem(IItemStack item) {
        ItemStack stack = item == null ? null : item.getMCItemStack();
        this.entity.func_92058_a(stack);
    }
}

