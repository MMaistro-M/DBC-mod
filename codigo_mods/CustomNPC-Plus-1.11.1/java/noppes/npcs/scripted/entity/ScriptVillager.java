/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.passive.EntityVillager
 */
package noppes.npcs.scripted.entity;

import net.minecraft.entity.passive.EntityVillager;
import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.api.entity.IVillager;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.entity.ScriptLiving;

public class ScriptVillager<T extends EntityVillager>
extends ScriptLiving<T>
implements IVillager {
    public ScriptVillager(T entity) {
        super(entity);
    }

    @Override
    public int getProfession() {
        return ((EntityVillager)this.entity).func_70946_n();
    }

    @Override
    public boolean getIsTrading() {
        return ((EntityVillager)this.entity).func_70940_q();
    }

    @Override
    public IEntityLivingBase getCustomer() {
        return NpcAPI.Instance().getPlayer(((EntityVillager)this.entity).func_70931_l_().func_70005_c_());
    }

    @Override
    public int getType() {
        return 9;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 9 || super.typeOf(type);
    }
}

