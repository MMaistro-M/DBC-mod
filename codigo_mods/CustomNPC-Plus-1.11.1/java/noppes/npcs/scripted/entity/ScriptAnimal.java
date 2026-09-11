/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.player.EntityPlayer
 */
package noppes.npcs.scripted.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.api.entity.IAnimal;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.entity.ScriptLiving;

public class ScriptAnimal<T extends EntityAnimal>
extends ScriptLiving<T>
implements IAnimal {
    protected T entity;

    public ScriptAnimal(T entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public int getType() {
        return 4;
    }

    @Override
    public boolean isBreedingItem(IItemStack itemStack) {
        return this.entity.func_70877_b(itemStack.getMCItemStack());
    }

    @Override
    public boolean interact(IPlayer player) {
        return this.entity.func_70085_c((EntityPlayer)player.getMCEntity());
    }

    @Override
    public void setFollowPlayer(IPlayer player) {
        this.entity.func_146082_f((EntityPlayer)player.getMCEntity());
    }

    @Override
    public IPlayer followingPlayer() {
        return (IPlayer)NpcAPI.Instance().getIEntity((Entity)this.entity.func_146083_cb());
    }

    @Override
    public boolean isInLove() {
        return this.entity.func_70880_s();
    }

    @Override
    public void resetInLove() {
        this.entity.func_70875_t();
    }

    @Override
    public boolean canMateWith(IAnimal animal) {
        return this.entity.func_70878_b((EntityAnimal)animal.getMCEntity());
    }

    @Override
    public boolean typeOf(int type) {
        return type == 4 || super.typeOf(type);
    }
}

